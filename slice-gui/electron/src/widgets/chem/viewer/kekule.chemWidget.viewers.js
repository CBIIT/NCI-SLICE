/**
 * @fileoverview
 * Related types and classes of chem viewer.
 * Viewer is a widget to show chem objects on HTML page.
 * @author Partridge Jiang
 */

/*
 * requires /lan/classes.js
 * requires /utils/kekule.utils.js
 * requires /xbrowsers/kekule.x.js
 * requires /core/kekule.common.js
 * requires /widgets/kekule.widget.base.js
 * requires /widgets/kekule.widget.menus.js
 * requires /widgets/kekule.widget.dialogs.js
 * requires /widgets/kekule.widget.helpers.js
 * requires /widgets/chem/kekule.chemWidget.base.js
 * requires /widgets/chem/kekule.chemWidget.chemObjDisplayers.js
 * requires /widgets/chem/uiMarker/kekule.chemWidget.uiMarkers.js
 * requires /widgets/operation/kekule.actions.js
 * requires /widgets/commonCtrls/kekule.widget.buttons.js
 * requires /widgets/commonCtrls/kekule.widget.containers.js
 *
 * requires /localization/kekule.localize.widget.js
 */

(function(){

"use strict";

var PS = Class.PropertyScope;
var AU = Kekule.ArrayUtils;
var ZU = Kekule.ZoomUtils;
var BNS = Kekule.ChemWidget.ComponentWidgetNames;
var CW = Kekule.ChemWidget;
var EM = Kekule.Widget.EvokeMode;

/** @ignore */
Kekule.globalOptions.add('chemWidget.viewer', {
	toolButtons: [
		//BNS.loadFile,
		BNS.loadData,
		BNS.saveData,
		//BNS.clearObjs,
		BNS.molDisplayType,
		BNS.molHideHydrogens,
		//BNS.molAutoGenerateCoords,
		BNS.zoomIn, BNS.zoomOut,
		BNS.rotateX, BNS.rotateY, BNS.rotateZ,
		BNS.rotateLeft, BNS.rotateRight,
		BNS.reset,
		BNS.copy,
		BNS.openEditor
	],
	menuItems: [
		BNS.loadData,
		BNS.saveData,
		Kekule.Widget.MenuItem.SEPARATOR_TEXT,
		BNS.molDisplayType,
		BNS.molHideHydrogens,
		BNS.zoomIn, BNS.zoomOut,
		{
			'text': Kekule.$L('ChemWidgetTexts.CAPTION_ROTATE'),
			'hint': Kekule.$L('ChemWidgetTexts.HINT_ROTATE'),
			'children': [
				BNS.rotateLeft, BNS.rotateRight,
				BNS.rotateX, BNS.rotateY, BNS.rotateZ
			]
		},
		BNS.reset,
		Kekule.Widget.MenuItem.SEPARATOR_TEXT,
		BNS.copy,
		BNS.openEditor,
		BNS.config
	],

	'toolbar': {
		'evokeModes': [EM.EVOKEE_CLICK, EM.EVOKEE_MOUSE_ENTER, EM.EVOKEE_TOUCH],
		'revokeModes': [EM.EVOKEE_MOUSE_LEAVE, EM.EVOKER_TIMEOUT],
		'pos': Kekule.Widget.Position.AUTO,
		'marginHorizontal': 10,
		'marginVertical': 10
	},
	'editor': {
		'modal': true,
		'restrainEditorWithCurrObj': true,
		'shareEditorInstance': true
	},

	'enableToolbar': false,
	'enableDirectInteraction': true,
	'enableTouchInteraction': false,
	'enableGestureInteraction': false,
	'showCaption': false,

	'useNormalBackground': false,
	'enableCustomCssProperties': true,

	'restraintRotation3DEdgeRatio': 0.18,
	'enableRestraintRotation3D': true
});

/** @ignore */
Kekule.ChemWidget.HtmlClassNames = Object.extend(Kekule.ChemWidget.HtmlClassNames, {
	VIEWER: 'K-Chem-Viewer',
	VIEWER2D: 'K-Chem-Viewer2D',
	VIEWER3D: 'K-Chem-Viewer3D',
	VIEWER_CLIENT: 'K-Chem-Viewer-Client',
	VIEWER_CAPTION: 'K-Chem-Viewer-Caption',
	VIEWER_EMBEDDED_TOOLBAR: 'K-Chem-Viewer-Embedded-Toolbar',
	VIEWER_UICONTEXT_PARENT: 'K-Chem-Viewer-UiContext-Parent',

	VIEWER_MENU_BUTTON: 'K-Chem-Viewer-Menu-Button',

	VIEWER_EDITOR_FULLCLIENT: 'K-Chem-Viewer-Editor-FullClient',

	// predefined actions
	ACTION_ROTATE_LEFT: 'K-Chem-RotateLeft',
	ACTION_ROTATE_RIGHT: 'K-Chem-RotateRight',
	ACTION_ROTATE_X: 'K-Chem-RotateX',
	ACTION_ROTATE_Y: 'K-Chem-RotateY',
	ACTION_ROTATE_Z: 'K-Chem-RotateZ',
	ACTION_VIEWER_EDIT: 'K-Chem-Viewer-Edit'
});

var CNS = Kekule.Widget.HtmlClassNames;
var CCNS = Kekule.ChemWidget.HtmlClassNames;

/**
 * Enumeration of some common used UI markers groups.
 * @enum
 */
Kekule.ChemWidget.ViewerUiMarkerGroup = {
	/** Ui markers for hot tracking objects. */
	HOTTRACK: 'hotTrack',
	/** Ui markers for selecting objects. */
	SELECT: 'select'
};

/**
 * An universal viewer widget for chem objects (especially molecules).
 * @class
 * @augments Kekule.ChemWidget.ChemObjDisplayer
 *
 * @param {Variant} parentOrElementOrDocument
 * @param {Kekule.ChemObject} chemObj
 * @param {Int} renderType Display in 2D or 3D. Value from {@link Kekule.Render.RendererType}.
 * @param {Kekule.ChemWidget.ChemObjDisplayerConfigs} viewerConfigs
 *
 * @property {Int} renderType Display in 2D or 3D. Value from {@link Kekule.Render.RendererType}. Read only.
 * @property {Kekule.ChemObject} chemObj Object to be drawn. Set this property will repaint the client.
 * @property {Bool} chemObjLoaded Whether the chemObj is successful loaded and drawn in viewer.
 * //@property {Object} renderConfigs Configuration for rendering.
 * // This property should be an instance of {@link Kekule.Render.Render2DConfigs} or {@link Kekule.Render.Render3DConfigs}
 * //@property {Hash} drawOptions Options to draw object.
 * //@property {Float} zoom Zoom ratio to draw chem object. Note this setting will overwrite drawOptions.zoom.
 * //@property {Bool} autoSize Whether the widget change its size to fit the dimension of chem object.
 * //@property {Int} padding Padding between chem object and edge of widget, in px. Only works when autoSize is true.
 *
 * @property {String} caption Caption of viewer.
 * @property {Bool} showCaption Whether show caption below or above viewer.
 * @property {Int} captionPos Value from {@link Kekule.Widget.Position}, now only TOP and BOTTOM are usable.
 * @property {Bool} autoCaption Set caption automatically by chemObj info.
 *
 * //@property {Bool} liveUpdate Whether viewer repaint itself automatically when containing chem object changed.
 *
 * @property {Bool} enableHotKey Whether hot key is allowed.
 * @property {Bool} enableDirectInteraction Whether interact without tool button is allowed (e.g., zoom/rotate by mouse).
 * @property {Bool} enableTouchInteraction Whether touch interaction is allowed. Note if enableDirectInteraction is false, touch interaction will also be disabled.
 * @property {Bool} enableRestraintRotation3D Set to true to rotate only on one axis of X/Y/Z when the starting point is near edge of viewer.
 * @property {Float} restraintRotation3DEdgeRatio
 * @property {Bool} enableEdit Whether a edit button is shown in toolbar to edit object in viewer. Works only in 2D mode.
 * @property {Bool} modalEdit Whether opens a modal dialog when editting object in viewer.
 * @property {Bool} enableEditFromVoid Whether editor can be launched even if viewer is empty.
 * @property {Hash} editorProperties Hash object to set properties of popup editor.
 * @property {Bool} restrainEditorWithCurrObj If true, the editor popuped can only edit current object in viewer (and add new
 *   objects is disabled). If false, the editor can do everything like a normal composer, viewer will load objects in composer
 *   after editting (and will not keep the original object in viewer).
 * @property {Bool} shareEditorInstance If true, all viewers in one document will shares one editor.
 *   This setting may reduce the cost of creating many composer widgets.
 *
 * @property {Array} toolButtons buttons in interaction tool bar. This is a array of predefined strings, e.g.: ['zoomIn', 'zoomOut', 'resetZoom', 'molDisplayType', ...]. <br />
 *   In the array, complex hash can also be used to add custom buttons, e.g.: <br />
 *     [ <br />
 *       'zoomIn', 'zoomOut',<br />
 *       {'name': 'myCustomButton1', 'widgetClass': 'Kekule.Widget.Button', 'action': actionClass},<br />
 *       {'name': 'myCustomButton2', 'htmlClass': 'MyClass' 'caption': 'My Button', 'hint': 'My Hint', '#execute': function(){ ... }},<br />
 *     ]<br />
 *   most hash fields are similar to the param of {@link Kekule.Widget.Utils.createFromHash}.<br />
 *   If this property is not set, default buttons will be used.
 * @property {Bool} enableToolbar Whether show tool bar in viewer.
 * @property {Int} toolbarPos Value from {@link Kekule.Widget.Position}, position of toolbar in viewer.
 *   For example, set this property to TOP will make toolbar shows in the center below the top edge of viewer,
 *   TOP_RIGHT will make the toolbar shows at the top right corner. Default value is BOTTOM_RIGHT.
 *   Set this property to AUTO, viewer will set toolbar position (including margin) automatically.
 * @property {Int} toolbarMarginHorizontal Horizontal margin of toolbar to viewer edge, in px.
 *   Negative value means toolbar outside viewer.
 * @property {Int} toolbarMarginVertical Vertical margin of toolbar to viewer edge, in px.
 *   Negative value means toolbar outside viewer.
 * //@property {Array} toolbarShowEvents Events to cause the display of toolbar. If set to null, the toolbar will always be visible.
 * @property {Array} toolbarEvokeModes Interaction modes to show the toolbar. Array item values should from {@link Kekule.Widget.EvokeMode}.
 *   Set enableToolbar to true and include {@link Kekule.Widget.EvokeMode.ALWAYS} will always show the toolbar.
 * @property {Array} toolbarRevokeModes Interaction modes to hide the toolbar. Array item values should from {@link Kekule.Widget.EvokeMode}.
 * @property {Int} toolbarRevokeTimeout Toolbar should be hidden after how many milliseconds after shown.
 *   Only available when {@link Kekule.Widget.EvokeMode.EVOKEE_TIMEOUT} or {@link Kekule.Widget.EvokeMode.EVOKER_TIMEOUT} in toolbarRevokeModes.
 *
 * @property {Array} allowedMolDisplayTypes Molecule types can be changed in tool bar.
 */
/**
 * Invoked when the chem object (or null) in viewer has been edited by the popup editor.
 *   event param of it has one fields: {obj: Object}
 * @name Kekule.ChemWidget.Viewer#editingDone
 * @event
 */
/**
 * Invoked when the pointer is hot tracking on objects in viewer.
 *   event param of it has fields: {objects: Array}.
 * @name Kekule.ChemWidget.Viewer#hotTrackOnObjects
 * @event
 */
/**
 * Invoked when objects are selected in view.
 *   event param of it has fields: {objects: Array}.
 * @name Kekule.ChemWidget.Viewer#selectionChange
 * @event
 */
Kekule.ChemWidget.Viewer = Class.create(Kekule.ChemWidget.ChemObjDisplayer,
/** @lends Kekule.ChemWidget.Viewer# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.Viewer',
	/** @private */
	BINDABLE_TAG_NAMES: ['div', 'span', 'img'],
	/** @private */
	DEF_BGCOLOR_2D: null,
	/** @private */
	DEF_BGCOLOR_3D: '#000000',
	/** @private */
	DEF_TOOLBAR_EVOKE_MODES: [/*EM.ALWAYS,*/ EM.EVOKEE_CLICK, EM.EVOKEE_MOUSE_ENTER, EM.EVOKEE_TOUCH],
	/** @private */
	DEF_TOOLBAR_REVOKE_MODES: [/*EM.ALWAYS,*/ /*EM.EVOKEE_CLICK,*/ EM.EVOKEE_MOUSE_LEAVE, EM.EVOKER_TIMEOUT],
	/** @private */
	OBSERVING_GESTURES: ['pinch', 'pinchstart', 'pinchmove', 'pinchend', 'pinchcancel', 'pinchin', 'pinchout'],
	/** @construct */
	initialize: function(/*$super, */parentOrElementOrDocument, chemObj, renderType, viewerConfigs)
	{
		//this._errorReportElem = null;  // use internally

		this.setPropStoreFieldValue('renderType', renderType || Kekule.Render.RendererType.R2D); // must set this value first
		this.setPropStoreFieldValue('useCornerDecoration', true);
		/*
		this.setPropStoreFieldValue('enableToolbar', false);
		this.setPropStoreFieldValue('toolbarEvokeModes', this.DEF_TOOLBAR_EVOKE_MODES);
		this.setPropStoreFieldValue('toolbarRevokeModes', this.DEF_TOOLBAR_REVOKE_MODES);
		this.setPropStoreFieldValue('enableDirectInteraction', true);
		this.setPropStoreFieldValue('toolbarPos', Kekule.Widget.Position.AUTO);
		this.setPropStoreFieldValue('toolbarMarginHorizontal', 10);
		this.setPropStoreFieldValue('toolbarMarginVertical', 10);
		this.setPropStoreFieldValue('showCaption', false);
		*/
		var oneOf = Kekule.oneOf;
		var options = Kekule.globalOptions.get('chemWidget.viewer') || {};
		this.setPropStoreFieldValue('enableToolbar', oneOf(options.enableToolbar));
		this.setPropStoreFieldValue('enableDirectInteraction', oneOf(options.enableDirectInteraction, true));
		this.setPropStoreFieldValue('showCaption', oneOf(options.showCaption, false));

		options = options.toolbar || {};
		this.setPropStoreFieldValue('toolbarEvokeModes', oneOf(options.evokeModes, this.DEF_TOOLBAR_EVOKE_MODES));
		this.setPropStoreFieldValue('toolbarRevokeModes', oneOf(options.revokeModes, this.DEF_TOOLBAR_REVOKE_MODES));
		this.setPropStoreFieldValue('toolbarPos', oneOf(options.pos, Kekule.Widget.Position.AUTO));
		this.setPropStoreFieldValue('toolbarMarginHorizontal', oneOf(options.marginHorizontal,10));
		this.setPropStoreFieldValue('toolbarMarginVertical', oneOf(options.marginVertical, 10));

		this.tryApplySuper('initialize', [parentOrElementOrDocument, chemObj, renderType /*, viewerConfigs*/])  /* $super(parentOrElementOrDocument, chemObj, renderType, viewerConfigs) */;

		this.setPropStoreFieldValue('viewerConfigs', viewerConfigs || this.createDefaultConfigs());

		this.beginUpdate();
		try
		{
			this.setPadding(this.getRenderConfigs().getLengthConfigs().getActualLength('autofitContextPadding'));
			/*
			 if (chemObj)
			 {
			 this.setChemObj(chemObj);
			 }
			 */

			this._isContinuousRepainting = false;  // flag, use internally
			//this._lastRotate3DMatrix = null;  // store the last 3D rotation information

			var RT = Kekule.Render.RendererType;
			var color2D = (this.getRenderType() === RT.R2D) ? (this.getBackgroundColor() || this.DEF_BGCOLOR_2D) : this.DEF_BGCOLOR_2D;
			var color3D = (this.getRenderType() === RT.R3D) ? (this.getBackgroundColor() || this.DEF_BGCOLOR_3D) : this.DEF_BGCOLOR_3D;
			this.setBackgroundColorOfType(color2D, RT.R2D);
			this.setBackgroundColorOfType(color3D, RT.R3D);

			this.useCornerDecorationChanged();
			this.doResize();  // adjust caption and drawParent size
		}
		finally
		{
			this.endUpdate();
		}

		var gOptions = Kekule.globalOptions.get('chemWidget.viewer') || {};
		if (Kekule.ObjUtils.isUnset(this.getEnableGesture()))
			this.setEnableGesture(oneOf(gOptions.enableGestureInteraction, false));  // the hammer event reactor need to be installed after element is bind
		this.addIaController('default', new Kekule.ChemWidget.ViewerBasicInteractionController(this), true);
	},
	/** @private */
	doFinalize: function(/*$super*/)
	{
		var markers = this.getUiMarkers();
		if (markers)
			markers.finalize();
		//this.getPainter().finalize();
		var toolBar = this.getToolbar();
		this.tryApplySuper('doFinalize')  /* $super() */;
		if (toolBar)
			toolBar.finalize();
		if (this._composerDialog)
			this._composerDialog.finalize();
		if (this._composerPanel)
			this._composerPanel.finalize();
	},
	/** @private */
	initProperties: function()
	{
		/*
		this.defineProp('chemObj', {'dataType': 'Kekule.ChemObject', 'serializable': false,
			'setter': function(value)
			{
				this.setPropStoreFieldValue('chemObj', value);
				this.chemObjChanged(value);
			}
		});
		this.defineProp('chemObjLoaded', {'dataType': DataType.BOOL, 'serializable': false, 'setter': null,
			'getter': function() { return this.getChemObj() && this.getPropStoreFieldValue('chemObjLoaded'); }
		});
		this.defineProp('renderType', {'dataType': DataType.INT, 'serializable': false, 'setter': null});

		this.defineProp('renderConfigs', {'dataType': DataType.OBJECT, 'serializable': false});
		this.defineProp('drawOptions', {'dataType': DataType.HASH, 'serializable': false,
			'getter': function()
			{
				var result = this.getPropStoreFieldValue('drawOptions');
				if (!result)
				{
					result = {};
					this.setPropStoreFieldValue('drawOptions', result);
				}
				return result;
			}
		});
		*/

		//this.defineProp('zoom', {'dataType': DataType.FLOAT, 'serializable': false});

		this.defineProp('viewerConfigs', {'dataType': 'Kekule.ChemWidget.ChemObjDisplayerConfigs', 'serializable': false,
			'getter': function() { return this.getDisplayerConfigs(); },
			'setter': function(value) { return this.setDisplayerConfigs(value); }
		});

		//this.defineProp('enableUiContext', {'dataType': DataType.BOOL});
		this.defineProp('uiDrawBridge', {'dataType': DataType.OBJECT, 'serializable': false, 'setter': null,
			'getter': function()
			{
				if (!this.getEnableUiContext())
					return null;
				var result = this.getPropStoreFieldValue('uiDrawBridge');
				if (!result && !this.__$uiDrawBridgeInitialized$__)
				{
					this.__$uiDrawBridgeInitialized$__ = true;
					result = this.createUiDrawBridge();
					this.setPropStoreFieldValue('uiDrawBridge', result);
				}
				return result;
			}
		});
		this.defineProp('uiContext', {'dataType': DataType.OBJECT, 'serializable': false,
			'getter': function()
			{
				if (!this.getEnableUiContext())
					return null;
				var result = this.getPropStoreFieldValue('uiContext');
				if (!result)
				{
					var bridge = this.getUiDrawBridge();
					if (bridge)
					{
						var elem = this.getUiContextParentElem();
						if (!elem)
							return null;
						else
						{
							var dim = Kekule.HtmlElementUtils.getElemScrollDimension(elem);
							//var dim = Kekule.HtmlElementUtils.getElemClientDimension(elem);
							result = bridge.createContext(elem, dim.width, dim.height);
							this.setPropStoreFieldValue('uiContext', result);
						}
					}
				}
				return result;
			}
		});
		this.defineProp('uiPainter', {'dataType': 'Kekule.Render.ChemObjPainter', 'serializable': false, 'setter': null,
			'getter': function()
			{
				var result = this.getPropStoreFieldValue('uiPainter');
				if (!result)
				{
					// ui painter will always in 2D mode
					var markers = this.getUiMarkers();
					result = new Kekule.Render.ChemObjPainter(Kekule.Render.RendererType.R2D, markers, this.getUiDrawBridge());
					result.setCanModifyTargetObj(true);
					this.setPropStoreFieldValue('uiPainter', result);
					return result;
				}
				return result;
			}
		});
		this.defineProp('uiRenderer', {'dataType': 'Kekule.Render.AbstractRenderer', 'serializable': false, 'setter': null,
			'getter': function()
			{
				var p = this.getUiPainter();
				if (p)
				{
					var r = p.getRenderer();
					if (!r)
						p.prepareRenderer();
					return p.getRenderer() || null;
				}
				else
					return null;
			}
		});
		// private ui marks properties
		this.defineProp('uiMarkers', {'dataType': 'Kekule.ChemWidget.UiMarkerCollection', 'serializable': false, 'setter': null,
			'getter': function()
			{
				var result = this.getPropStoreFieldValue('uiMarkers');
				if (!result)
				{
					result = new Kekule.ChemWidget.UiMarkerCollection();
					this.setPropStoreFieldValue('uiMarkers', result);
				}
				return result;
			}
		});

		this.defineProp('allowedMolDisplayTypes', {'dataType': DataType.ARRAY, 'scope': PS.PUBLIC,
			'setter': function(value)
			{
				this.setPropStoreFieldValue('allowedMolDisplayTypes', value);
				//this.updateToolbar();
				this.updateUiComps();
			}
		});
		this.defineProp('retainAspect', {'dataType': DataType.BOOL, 'serializable': false,
			'getter': function()
			{
				var op = this.getDrawOptions() || {};
				return op.retainAspect;
			},
			'setter': function(value)
			{
				var op = this.getDrawOptions() || {};
				op.retainAspect = !!value;
				return this.setDrawOptions(op);
			}
		});

		this.defineProp('enableRestraintRotation3D', {'dataType': DataType.BOOL});
		this.defineProp('restraintRotation3DEdgeRatio', {'dataType': DataType.FLOAT});
		//this.defineProp('liveUpdate', {'dataType': DataType.BOOL});

		this.defineProp('enableHotKey', {'dataType': DataType.FLOAT});
		this.defineProp('enableEdit', {'dataType': DataType.BOOL,
			'getter': function()
			{
				// TODO: now only allows 2D editing
				return this.getPropStoreFieldValue('enableEdit') && (this.getCoordMode() !== Kekule.CoordMode.COORD3D);
			}
		});
		this.defineProp('shareEditorInstance', {'dataType': DataType.BOOL});
		this.defineProp('enableEditFromVoid', {'dataType': DataType.BOOL});
		this.defineProp('restrainEditorWithCurrObj', {'dataType': DataType.BOOL});
		this.defineProp('modalEdit', {'dataType': DataType.BOOL});
		this.defineProp('editorProperties', {'dataType': DataType.HASH});

		this.defineProp('toolButtons', {'dataType': DataType.ARRAY, 'scope': PS.PUBLIC,
			'getter': function()
			{
				var result = this.getPropStoreFieldValue('toolButtons');
				/*
				if (!result)  // create default one
				{
					result = this.getDefaultToolBarButtons();
					this.setPropStoreFieldValue('toolButtons', result);
				}
				*/
				return result;
			},
			'setter': function(value)
			{
				this.setPropStoreFieldValue('toolButtons', value);
				this.updateToolbar();
			}
		});
		this.defineProp('menuItems', {'dataType': DataType.ARRAY, 'scope': PS.PUBLIC,
			'setter': function(value)
			{
				this.setPropStoreFieldValue('menuItems', value);
				this.updateMenu();
			}
		});
		/*
		// private
		this.defineProp('toolButtonNameMapping', {'dataType': DataType.HASH, 'serializable': false, 'scope': PS.PRIVATE,
			'setter': null,
			'getter': function()
			{
				var result = this.getPropStoreFieldValue('toolButtonNameMapping');
				if (!result)  // create default one
				{
					result = this.createDefaultToolButtonNameMapping();
					this.setPropStoreFieldValue('toolButtonNameMapping', result);
				}
				return result;
			}
		});
		*/
		// private
		this.defineProp('menu', {'dataType': 'Kekule.Widget.Menu', 'serializable': false, 'scope': PS.PRIVATE,
			'setter': function(value)
			{
				var old = this.getMenu();
				if (value !== old)
				{
					if (old)
					{
						old.finalize();
						old = null;
					}
					this.setPropStoreFieldValue('menu', value);
				}
			}
		});

		this.defineProp('toolbar', {'dataType': 'Kekule.Widget.ButtonGroup', 'serializable': false, 'scope': PS.PRIVATE,
			'setter': function(value)
			{
				var old = this.getToolbar();
				var evokeHelper = this.getToolbarEvokeHelper();
				if (value !== old)
				{
					if (old)
					{
						old.finalize();
						var helper = this.getToolbarEvokeHelper();
						if (helper)
							helper.finalize();
						old = null;
					}
					if (evokeHelper)
						evokeHelper.finalize();
					this.setPropStoreFieldValue('toolbar', value);
					// hide the new toolbar and wait for the evoke helper to display it
					//value.setDisplayed(false);
					if (value)
					{
						this.setPropStoreFieldValue('toolbarEvokeHelper',
							new Kekule.Widget.DynamicEvokeHelper(this, value, this.getToolbarEvokeModes(), this.getToolbarRevokeModes()));
					}
				}
			}
		});

		this.defineProp('enableToolbar', {'dataType': DataType.BOOL,
			'setter': function(value)
			{
				this.setPropStoreFieldValue('enableToolbar', value);
				this.updateToolbar();
			}
		});

		this.defineProp('toolbarPos', {'dataType': DataType.INT, 'enumSource': Kekule.Widget.Position,
			'setter': function(value)
			{
				this.setPropStoreFieldValue('toolbarPos', value);
				this.adjustToolbarPos();
			}
		});
		this.defineProp('toolbarMarginVertical', {'dataType': DataType.INT,
			'setter': function(value)
			{
				this.setPropStoreFieldValue('toolbarMarginVertical', value);
				this.adjustToolbarPos();
			}
		});
		this.defineProp('toolbarMarginHorizontal', {'dataType': DataType.INT,
			'setter': function(value)
			{
				this.setPropStoreFieldValue('toolbarMarginHorizontal', value);
				this.adjustToolbarPos();
			}
		});
		/*
		this.defineProp('toolbarShowEvents', {'dataType': DataType.ARRAY});
		this.defineProp('toolbarAlwaysShow', {'dataType': DataType.BOOL, 'serializable': false,
			'getter': function() { return !!this.getToolbarShowEvents(); },
			'setter': null
		});
		*/

		this.defineProp('toolbarEvokeHelper', {'dataType': 'Kekule.Widget.DynamicEvokeHelper',
			'serializable': false, 'setter': null, 'scope': PS.PRIVATE}); // private
		this.defineProp('toolbarEvokeModes', {'dataType': DataType.ARRAY, 'scope': PS.PUBLIC,
			'setter': function(value)
			{
				this.setPropStoreFieldValue('toolbarEvokeModes', value || []);
				if (this.getToolbarEvokeHelper())
					this.getToolbarEvokeHelper().setEvokeModes(value || []);
			}
		});
		this.defineProp('toolbarRevokeModes', {'dataType': DataType.ARRAY, 'scope': PS.PUBLIC,
			'setter': function(value)
			{
				this.setPropStoreFieldValue('toolbarRevokeModes', value || []);
				if (this.getToolbarEvokeHelper())
					this.getToolbarEvokeHelper().setRevokeModes(value || []);
			}
		});
		this.defineProp('toolbarRevokeTimeout', {'dataType': DataType.INT,
			'setter': function(value)
			{
				this.setPropStoreFieldValue('toolbarRevokeTimeout', value);
				if (this.getToolbarEvokeHelper())
					this.getToolbarEvokeHelper().setTimeout(value);
			}
		});

		this.defineProp('toolbarParentElem', {'dataType': DataType.OBJECT, 'serializable': false,
			'setter': function(value)
			{
				if (this.getToolbarParentElem() !== value)
				{
					this.setPropStoreFieldValue('toolbarParentElem', value);
					this.updateToolbar();
				}
			}
		});

		this.defineProp('caption', {'dataType': DataType.STRING,
			'setter': function(value)
			{
				this.setPropStoreFieldValue('caption', value);
				Kekule.DomUtils.setElementText(this.getCaptionElem(), value || '');
				this.captionChanged();
			}
		});
		this.defineProp('showCaption', {'dataType': DataType.BOOL,
			'setter': function(value)
			{
				this.setPropStoreFieldValue('showCaption', value);
				this.captionChanged();
			}
		});
		this.defineProp('captionPos', {'dataType': DataType.INT, 'enumSource': Kekule.Widget.Position,
			'setter': function(value)
			{
				this.setPropStoreFieldValue('captionPos', value);
				this.captionChanged();
			}
		});
		this.defineProp('autoCaption', {'dataType': DataType.BOOL,
			'setter': function(value)
			{
				this.setPropStoreFieldValue('autoCaption', value);
				if (value)
					this.autoDetectCaption();
			}
		});
		this.defineProp('captionElem', {'dataType': DataType.OBJECT, 'scope': PS.PRIVATE,
			'setter': null,
			'getter': function(doNotAutoCreate)
			{
				var result = this.getPropStoreFieldValue('captionElem');
				if (!result && !doNotAutoCreate)  // create new
				{
					result = this.doCreateCaptionElem();
					this.setPropStoreFieldValue('captionElem', result);
				}
				return result;
			}
		});

		this.defineProp('actions', {'dataType': 'Kekule.ActionList', 'serializable': false, 'scope': PS.PUBLIC,
			'setter': null,
			'getter': function()
			{
				var result = this.getPropStoreFieldValue('actions');
				if (!result)
				{
					result = new Kekule.ActionList();
					this.setPropStoreFieldValue('actions', result);
				}
				return result;
			}
		});
		this.defineProp('actionMap', {'dataType': 'Kekule.MapEx', 'serializable': false, 'scope': PS.PRIVATE,
			'setter': null,
			'getter': function()
			{
				var result = this.getPropStoreFieldValue('actionMap');
				if (!result)
				{
					result = new Kekule.MapEx(true);
					this.setPropStoreFieldValue('actionMap', result);
				}
				return result;
			}
		});

		this.defineProp('enableDirectInteraction', {'dataType': DataType.BOOL});
		this.defineProp('enableTouchInteraction', {'dataType': DataType.BOOL,
			'setter': function(value)
			{
				this.setPropStoreFieldValue('enableTouchInteraction', !!value);
				this.setTouchAction(value? 'none': null);
			}
		});
		this.defineProp('enableGesture', {'dataType': DataType.BOOL,
			'setter': function(value)
			{
				var bValue = !!value;
				if (this.getEnableGesture() !== bValue)
				{
					this.setPropStoreFieldValue('enableGesture', bValue);
					if (bValue)
					{
						this.startObservingGestureEvents(this.OBSERVING_GESTURES);
					}
					else
					{
						this.stopObservingGestureEvents(this.OBSERVING_GESTURES);
					}
				}
			}
		});

		this.defineProp('hotTrackedObjects', {
			'dataType': DataType.ARRAY,
			'serializable': false,
			'getter': function()
			{
				return this.getPropStoreFieldValue('hotTrackedObjects') || [];
			},
			'setter': function(value)
			{
				//this.setPropStoreFieldValue('hotTrackedObjects', AU.toArray(value));
				this.changeHotTrackedObjects(value && AU.toArray(value), true);
			}
		});
		this.defineProp('selectedObjects', {
			'dataType': DataType.ARRAY,
			'serializable': false,
			'getter': function()
			{
				return this.getPropStoreFieldValue('selectedObjects') || [];
			},
			'setter': function(value)
			{
				this.changeSelectedObjects(value && AU.toArray(value), true);
			}
		});
	},
	/** @ignore */
	initPropValues: function(/*$super*/)
	{
		// debug
		/*
		this.setEnableEdit(true);
		*/
		this.setStyleMode(Kekule.Widget.StyleMode.INHERITED);  // embedded in document
		/*
		this.setUseNormalBackground(false);
		//this.setInheritedRenderColor(true);
		this.setEnableCustomCssProperties(true);

		this.setModalEdit(true);
		this.setRestrainEditorWithCurrObj(true);
		this.setRestraintRotation3DEdgeRatio(0.18);
		this.setEnableRestraintRotation3D(true);
		this.setShareEditorInstance(true);
		this.setEnableTouchInteraction(!true);
		*/
		var oneOf = Kekule.oneOf;
		var options = Kekule.globalOptions.get('chemWidget.viewer') || {};
		this.setUseNormalBackground(oneOf(options.useNormalBackground, false));
		this.setEnableCustomCssProperties(oneOf(options.enableCustomCssProperties, true));
		this.setRestraintRotation3DEdgeRatio(oneOf(options.restraintRotation3DEdgeRatio, 0.18));
		this.setEnableRestraintRotation3D(oneOf(options.enableRestraintRotation3D, true));
		this.setEnableTouchInteraction(oneOf(options.enableTouchInteraction, false));

		options = options.editor || {};
		this.setModalEdit(oneOf(options.modal, true));
		this.setRestrainEditorWithCurrObj(oneOf(options.restrainEditorWithCurrObj, true));
		this.setShareEditorInstance(oneOf(options.shareEditorInstance, true));
	},

	/** @ignore */
	createDefaultConfigs: function()
	{
		return new Kekule.ChemWidget.ViewerConfigs();
	},

	/** @ignore */
	canUsePlaceHolderOnElem: function(elem)
	{
		// When using a img element with src image, it may contains the figure of chem object
		var imgSrc = elem.getAttribute('src');
		return (elem.tagName.toLowerCase() === 'img') && (!!imgSrc);
	},

	/** @ignore */
	doObjectChange: function(/*$super, */modifiedPropNames)
	{
		this.tryApplySuper('doObjectChange', [modifiedPropNames])  /* $super(modifiedPropNames) */;
		this.updateActions();
	},

	/** @ignore */
	doSetElement: function(/*$super, */element)
	{
		var elem = element;
		if (elem)
		{
			var tagName = elem.tagName.toLowerCase();
			if (tagName === 'img')  // is an image element, need to use span to replace it
			{
				elem = Kekule.DomUtils.replaceTagName(elem, 'span');
				//this.setElement(elem);
				//console.log('replace img to span');
			}
		}
		return this.tryApplySuper('doSetElement', [elem])  /* $super(elem) */;
	},
	/** @ignore */
	doUnbindElement: function(/*$super, */element)
	{
		// unbind old element, the context parent element should be set to null
		if (this._drawContextParentElem && this._drawContextParentElem.parentNode)
		{
			this._drawContextParentElem.parentNode.removeChild(this._drawContextParentElem);
			this._drawContextParentElem = null;
		}
		return this.tryApplySuper('doUnbindElement', [element])  /* $super(element) */;
	},

	/** @ignore */
	elementBound: function(element)
	{
		this.setObserveElemResize(true);
	},

	/** @ignore */
	doCreateRootElement: function(doc)
	{
		var result = doc.createElement('div');
		return result;
	},
	/** @ignore */
	doCreateSubElements: function(doc, docFragment)
	{
		// client element
		var clientElem = doc.createElement('div');
		clientElem.className = CCNS.VIEWER_CLIENT;
		this._clientElement = clientElem;
		docFragment.appendChild(clientElem);
		return [clientElem];
	},
	/** @ignore */
	doGetWidgetClassName: function(/*$super*/)
	{
		var result = this.tryApplySuper('doGetWidgetClassName')  /* $super() */ + ' ' + CCNS.VIEWER;
		try  // may raise exception when called with class prototype (required by placeholder related methods)
		{
			var renderType = this.getRenderType();
			var additional = this._getRenderTypeSpecifiedHtmlClassName(renderType);
			result += ' ' + additional;
		}
		catch(e)
		{

		}
		return result;
	},
	/** @private */
	_getRenderTypeSpecifiedHtmlClassName: function(renderType)
	{
		return (renderType === Kekule.Render.RendererType.R3D)?
			CCNS.VIEWER3D: CCNS.VIEWER2D;
	},

	/** @ignore */
	getClientElement: function()
	{
		return this._clientElement;
	},

	/** @ignore */
	getResizerElement: function()
	{
		//return this.getDrawContextParentElem();
		return this.getElement();
	},

	/** @ignore */
	doResize: function(/*$super*/)
	{
		//$super();
		this.adjustDrawParentDim();
		this.adjustToolbarPos();
		this.tryApplySuper('doResize')  /* $super() */;
	},
	/** @ignore */
	doWidgetShowStateChanged: function(isShown)
	{
		if (isShown)
		{
			//console.log('update toolbar');
			//this.updateToolbar();
			this.updateActions();
		}
	},
	/** @ignore */
	refitDrawContext: function(/*$super, */doNotRepaint)
	{
		// resize context, means client size changed, so toolbar should also be adjusted.
		this.tryApplySuper('refitDrawContext', [doNotRepaint])  /* $super(doNotRepaint) */;
		this.adjustToolbarPos();
	},
	/** @ignore */
	changeContextDimension: function(newDimension)
	{
		if (this.getEnableUiContext())
		{
			if (this.getUiDrawBridge() && this.getUiContext())
			{
				this.doChangeContextDimension(this.getUiContext(), this.getUiDrawBridge(), newDimension, true);
			}
		}
		return this.tryApplySuper('changeContextDimension', [newDimension]);
	},

	/** @ignore */
	getAllowRenderTypeChange: function()
	{
		return true;
	},
	/** @ignore */
	resetRenderType: function(/*$super, */oldType, newType)
	{
		this.tryApplySuper('resetRenderType', [oldType, newType])  /* $super(oldType, newType) */;
		// classname
		var oldHtmlClassName = this._getRenderTypeSpecifiedHtmlClassName(oldType);
		var newHtmlClassName = this._getRenderTypeSpecifiedHtmlClassName(newType);
		this.removeClassName(oldHtmlClassName);
		this.addClassName(newHtmlClassName);
		// toolbar
		//this.updateToolbar();
		this.updateUiComps();
	},

	/** @ignore */
	doLoad: function(chemObj)
	{
		// clear UI markers when loading a new object
		/*
		this.setVisibleOfUiMarkerGroup(Kekule.ChemWidget.ViewerUiMarkerGroup.SELECT, false, false);
		this.setVisibleOfUiMarkerGroup(Kekule.ChemWidget.ViewerUiMarkerGroup.HOTTRACK, false, false);
		*/
		this.beginUpdateUiMarkers();
		try
		{
			this.clearHotTrackedItems();
			this.clearSelectedItems();
			this.clearUiMarkers();
		}
		finally
		{
			this.endUpdateUiMarkers();
		}
		this.tryApplySuper('doLoad', [chemObj]);
	},
	/** @private */
	doLoadEnd: function(chemObj)
	{
		this.updateActions();
		this.autoDetectCaption();
	},

	/** @ignore */
	_repaintCore: function(overrideOptions)
	{
		//console.log('do repaint');
		this.tryApplySuper('_repaintCore', [overrideOptions]);
	},
	/** @ignore */
	chemObjRendered: function(chemObj, renderOptions)
	{
		var result = this.tryApplySuper('chemObjRendered', [chemObj, renderOptions]);
		this.updateUiMarkers(true);
		//this.repaintUiMarker();
		return result;
	},

	/** @private */
	doSetUseCornerDecoration: function(/*$super, */value)
	{
		this.tryApplySuper('doSetUseCornerDecoration', [value])  /* $super(value) */;
		this.useCornerDecorationChanged();
	},
	/** @private */
	useCornerDecorationChanged: function()
	{
		var elem = this.getDrawContextParentElem();  // do not auto create element
		if (elem)
		{
			var v = this.getUseCornerDecoration();
			if (v)
				Kekule.HtmlElementUtils.addClass(elem, CNS.CORNER_ALL);
			else
				Kekule.HtmlElementUtils.removeClass(elem, CNS.CORNER_ALL);
		}
	},

	/** @ignore */
	getBoundInfosAtCoord: function(screenCoord, filterFunc, boundInflation)
	{
		var boundInfos = this.tryApplySuper('getBoundInfosAtCoord', [screenCoord, filterFunc, boundInflation]);
		var enableTrackNearest = this.getViewerConfigs().getInteractionConfigs().getEnableTrackOnNearest();
		if (boundInfos && boundInfos.length && enableTrackNearest)  // sort result by distance to screenCoord
		{
			var distanceMap = new Kekule.MapEx();
			try
			{
				var SU = Kekule.Render.MetaShapeUtils;
				for (var i = boundInfos.length - 1; i >= 0; --i)
				{
					var info = boundInfos[i];
					var shapeInfo = info.boundInfo;
					var currDistance = SU.getDistance(screenCoord, shapeInfo);
					distanceMap.set(info, currDistance);
				}
				// sort by z-index, the smaller index on bottom
				boundInfos.sort(function (b1, b2)	{
					var result = - (b1.boundInfo.shapeType - b2.boundInfo.shapeType);
					if (result === 0)
						result = - (distanceMap.get(b1) - distanceMap.get(b2));
					return result;
				});
			}
			finally
			{
				distanceMap.finalize();
			}
		}
		//console.log('boundInfos', screenCoord, boundInfos);
		return boundInfos;
	},

	/** @ignore */
	/*
	getTopmostBoundInfoAtCoord: function(screenCoord, excludeObjs, boundInflation)
	{
		var enableTrackNearest = this.getViewerConfigs().getInteractionConfigs().getEnableTrackOnNearest();
		if (!enableTrackNearest)
			return this.tryApplySuper('getTopmostBoundInfoAtCoord', [screenCoord, excludeObjs, boundInflation]);

		// else, track on nearest
		var SU = Kekule.Render.MetaShapeUtils;
		var boundInfos = this.getBoundInfosAtCoord(screenCoord, null, boundInflation);
		//var filteredBoundInfos = [];
		var result, lastShapeInfo, lastDistance;
		var setResult = function(boundInfo, shapeInfo, distance)
		{
			result = boundInfo;
			lastShapeInfo = shapeInfo || boundInfo.boundInfo;
			if (Kekule.ObjUtils.notUnset(distance))
				lastDistance = distance;
			else
				lastDistance = SU.getDistance(screenCoord, lastShapeInfo);
		};
		for (var i = boundInfos.length - 1; i >= 0; --i)
		{
			var info = boundInfos[i];
			if (excludeObjs && (excludeObjs.indexOf(info.obj) >= 0))
				continue;
			if (!result)
				setResult(info);
			else
			{
				var shapeInfo = info.boundInfo;
				if (shapeInfo.shapeType < lastShapeInfo.shapeType)
					setResult(info, shapeInfo);
				else if (shapeInfo.shapeType === lastShapeInfo.shapeType)
				{
					var currDistance = SU.getDistance(screenCoord, shapeInfo);
					if (currDistance < lastDistance)
					{
						//console.log('distanceCompare', currDistance, lastDistance);
						setResult(info, shapeInfo, currDistance);
					}
				}
			}
		}
		return result;
	},
	*/

	/**
	 * Returns whether the UI marker context is enabled in viewer.
	 * Descendants or extensions may override this method.
	 * @returns {Bool}
	 */
	getEnableUiContext: function()
	{
		return true;
	},
	/**
	 * Returns parent element to create UI context inside viewer.
	 * @private
	 */
	getUiContextParentElem: function(disableAutoCreate)
	{
		if (!this.getEnableUiContext())
			return null;
		var result = this._uiContextParentElem;
		if (!result && !disableAutoCreate)  // create new
		{
			result = this.getDocument().createElement('div'); // IMPORTANT: span may cause dimension calc problem of context
			this._uiContextParentElem = result;
			Kekule.HtmlElementUtils.addClass(result, CNS.DYN_CREATED);
			Kekule.HtmlElementUtils.addClass(result, CCNS.VIEWER_UICONTEXT_PARENT);
			// IMPORTANT: force to fullfill the parent, otherwise draw context dimension calculation may have problem
			result.style.width = '100%';
			result.style.height = '100%';
			// insert after draw context parent elment
			var drawContextParentElem = this.getDrawContextParentElem();
			var root = drawContextParentElem? drawContextParentElem.parentNode: this.getElement();
			var refSibling = drawContextParentElem && drawContextParentElem.nextSibling;
			if (refSibling)
				root.insertBefore(result, refSibling);
			else
				root.appendChild(result);
		}
		return result;
	},
	/** @private */
	createUiDrawBridge: function()
	{
		// UI marker will always be in 2D
		var result = Kekule.Render.DrawBridge2DMananger.getPreferredBridgeInstance();
		if (!result)   // can not find suitable draw bridge
		{
			//Kekule.error(Kekule.$L('ErrorMsg.DRAW_BRIDGE_NOT_SUPPORTED'));
			var errorMsg = Kekule.Render.DrawBridge2DMananger.getUnavailableMessage() || Kekule.error(Kekule.$L('ErrorMsg.DRAW_BRIDGE_NOT_SUPPORTED'));
			if (errorMsg)
				this.reportException(errorMsg, Kekule.ExceptionLevel.NOT_FATAL_ERROR);
		}
		return result;
	},

	/** @private */
	adjustDrawParentDim: function()
	{
		var drawParentElem = this.getDrawContextParentElem();
		var parentElem = drawParentElem.parentNode;
		var captionElem = this.getCaptionElem(true);  // do not auto create
		var dimParent = Kekule.HtmlElementUtils.getElemClientDimension(parentElem);
		//var t, h;
		// drawParentElem is now position: absolute
		if (captionElem && this.captionIsShown() && captionElem.parentNode === parentElem)
		{
			var dimCaption = Kekule.HtmlElementUtils.getElemClientDimension(captionElem);
			var h = dimCaption.height || 0;
			//console.log('here');
			if (this.getCaptionPos() & Kekule.Widget.Position.TOP)
			{
				drawParentElem.style.top = h + 'px';
				drawParentElem.style.bottom = '0px';
			}
			else
			{
				drawParentElem.style.top = '0px';
				drawParentElem.style.bottom = h + 'px';
			}
			Kekule.StyleUtils.removeStyleProperty(drawParentElem.style, 'height');
			/*
			h = Math.max(dimParent.height - dimCaption.height, 0);  // avoid value < 0
			t = (this.getCaptionPos() & Kekule.Widget.Position.TOP)? dimCaption.height: 0;
			drawParentElem.style.top = t + 'px';
			drawParentElem.style.height = h + 'px';
			*/
		}
		else
		{
			/*
			t = 0;
			h = dimParent.height;
			*/
			/*
			// restore 100% height setting
			Kekule.StyleUtils.removeStyleProperty(drawParentElem.style, 'top');
			//Kekule.StyleUtils.removeStyleProperty(drawParentElem.style, 'height');
			//drawParentElem.style.height = dimParent.height + 'px';  // explicit set height, or the height may not be updated in some mobile browsers
			drawParentElem.style.height = '100%';   // some mobile browser has wrong height of parentElem, so here we set it to 100%
			*/
			Kekule.StyleUtils.removeStyleProperty(drawParentElem.style, 'top');
			Kekule.StyleUtils.removeStyleProperty(drawParentElem.style, 'bottom');
			drawParentElem.style.height = '100%';   // some mobile browser has wrong height of parentElem, so here we set it to 100%
		}

		//this.refitDrawContext();
	},

	/** @private */
	getInteractionReceiverElem: function()
	{
		//return this.getDrawContextParentElem();
		return this.getClientElement();
	},

	/** @ignore */
	setDrawDimension: function(/*$super, */width, height)
	{
		var newHeight = height;
		if (this.captionIsShown())  // height need add the height of caption
		{
			var dimCaption = Kekule.HtmlElementUtils.getElemClientDimension(this.getCaptionElem());
			newHeight += dimCaption.height || 0;
		}
		this.tryApplySuper('setDrawDimension', [width, newHeight])  /* $super(width, newHeight) */;
	},

	/// Methods about caption: currently not used ///////////
	/* @private */
	doCreateCaptionElem: function()
	{
		var result = this.getDocument().createElement('span');
		result.className = CNS.DYN_CREATED + ' ' + ' ' + CNS.SELECTABLE + ' ' + CCNS.VIEWER_CAPTION;
		this.getElement().appendChild(result);
		return result;
	},
	/**
	 * Called when caption or showCaption or captionPos property changes.
	 * @private
	 */
	captionChanged: function()
	{
		if (this.captionIsShown())
		{
			var elem = this.getCaptionElem();
			var style = elem.style;
			var pos = this.getCaptionPos();
			if (pos & Kekule.Widget.Position.TOP)
			{
				style.top = 0;
				style.bottom = 'auto';
			}
			else
			{
				style.bottom = 0;
				style.top = 'auto';
			}
			style.display = 'block';
		}
		else  // caption need to be hidden
		{
			var elem = this.getCaptionElem(true);  // do not auto create
			if (elem)
				elem.style.display = 'none';
		}
		//this.adjustDrawParentDim();
		this.doResize();
	},
	/**
	 * Returns whether the caption is actually displayed.
	 */
	captionIsShown: function()
	{
		return this.getCaption() && this.getShowCaption();
	},

	/*
	 * Called when caption or showCaption property has been changed.
	 * @private
	 */
	/*
	captionChanged: function()
	{
		var displayCaption = this.getShowCaption() && this.getCaption();
		var elem = this.getCaptionElem();
		Kekule.DomUtils.setElementText(elem, this.getCaption());
		elem.style.display = displayCaption? 'inherit': 'none';
	},
	*/

	//////////////////// methods about UI markers ///////////////////////////////
	/**
	 * Notify that currently is modifing UI markers and the editor need not to repaint them.
	 * @private
	 */
	beginUpdateUiMarkers: function()
	{
		if (Kekule.ObjUtils.isUnset(this._uiMarkerUpdateFlag))
			this._uiMarkerUpdateFlag = 0;
		--this._uiMarkerUpdateFlag;
		this.beginUpdate();   // some times the context should also be repainted to reflect the select/hot track markers
	},
	/**
	 * Call this method to indicate the UI marker update process is over and should be immediately updated.
	 * @private
	 */
	endUpdateUiMarkers: function()
	{
		this.endUpdate();
		++this._uiMarkerUpdateFlag;
		if (this._uiMarkerUpdateFlag > 0)
			this._uiMarkerUpdateFlag = 0;
		if (!this.isUpdatingUiMarkers())
			this.repaintUiMarker();
	},
	/**
	 * Check if the editor is under continuous UI marker update.
	 * @returns {Bool}
	 * @private
	 */
	isUpdatingUiMarkers: function()
	{
		return (this._uiMarkerUpdateFlag < 0);
	},
	/** @private */
	_getUiMarkerOfName: function(markerName, groups, creationMethod)
	{
		var result = this.getUiMarkers().getMarkerOfName(markerName);
		if (!result && creationMethod)  // auto create one
		{
			result = creationMethod.apply(this);
			if (result)
			{
				result.setName(markerName);
				result.setGroups(groups);
			}
		}
		return result;
	},
	/** @private */
	_getDefaultHotTrackUiMarker: function(autoCreate)
	{
		var creationMethod;
		if (autoCreate)
			creationMethod = this.createShapeBasedMarker;
		var result = this._getUiMarkerOfName('hotTrackMarker', [Kekule.ChemWidget.ViewerUiMarkerGroup.HOTTRACK], creationMethod);
		return result;
	},
	/** @private */
	_getDefaultSelectionUiMarker: function(autoCreate)
	{
		var creationMethod;
		if (autoCreate)
			creationMethod = this.createShapeBasedMarker;
		var result = this._getUiMarkerOfName('selectionMarker', [Kekule.ChemWidget.ViewerUiMarkerGroup.SELECT], creationMethod);
		return result;
	},
	/**
	 * Called when transform has been made to objects and UI markers need to be modified according to it.
	 * The UI markers will also be repainted.
	 * @private
	 */
	recalcUiMarkers: function()
	{
		if (this.getUiDrawBridge())
		{
			this.beginUpdateUiMarkers();
			try
			{
				var marker;
				// hot track
				var hotTrackedObjs = this.getHotTrackedObjects();
				//console.log('recalcUiMarkers ui', hotTrackedObjs);
				if (hotTrackedObjs && hotTrackedObjs.length)
				{
					var bounds = this._calcBoundsOfObjects(hotTrackedObjs);
					var drawStyles = this.getViewerConfigs().getUiMarkerConfigs().getHotTrackMarkerStyles() || {};
					marker = this._getDefaultHotTrackUiMarker(true);  // auto create
					this.modifyShapeBasedMarker(marker, bounds, drawStyles, false);
					this.showUiMarker(marker);
				}
				else   // hide hot track marker
				{
					marker = this._getDefaultHotTrackUiMarker(false);  // not need to auto create
					if (marker)
						this.hideUiMarker(marker, false);
				}
				// selected
				var selectedObjs = this.getSelectedObjects();
				if (selectedObjs && selectedObjs.length)
				{
					var bounds = this._calcBoundsOfObjects(selectedObjs);
					var drawStyles = this.getViewerConfigs().getUiMarkerConfigs().getSelectionMarkerStyles() || {};
					marker = this._getDefaultSelectionUiMarker(true);  // auto create
					this.modifyShapeBasedMarker(marker, bounds, drawStyles, false);
					this.showUiMarker(marker);
				}
				else   // hide hot track marker
				{
					marker = this._getDefaultSelectionUiMarker(false);  // not need to auto create
					if (marker)
						this.hideUiMarker(marker, false);
				}
			}
			finally
			{
				this.endUpdateUiMarkers();
			}
		}
	},
	/** @private */
	repaintUiMarker: function()
	{
		//console.log('call repaintUiMarker', this._uiMarkerUpdateFlag, this.getHotTrackedObjects());
		if (this.isUpdatingUiMarkers())
			return;
		if (this.getUiDrawBridge() && this.getUiContext())
		{
			this.clearUiContext();
			var drawParams = this.calcDrawParams();
			this.getUiPainter().draw(this.getUiContext(), drawParams.baseCoord, drawParams.drawOptions);
		}
	},
	/**
	 * Update the properties of existed UI markers according the current chem object state.
	 * @private
	 */
	updateUiMarkers: function(doRepaint)
	{
		this.doUpdateUiMarkers();
		if (doRepaint)
			this.repaintUiMarker();
	},
	/** @private */
	doUpdateUiMarkers: function()
	{
		this.recalcUiMarkers();
	},
	/**
	 * Create a new marker based on shapeInfo.
	 * @private
	 */
	createShapeBasedMarker: function(/*markerPropName,*/ shapeInfo, drawStyles, extraPropValues, updateRenderer)
	{
		var marker = new Kekule.ChemWidget.MetaShapeUiMarker();
		if (shapeInfo)
			marker.setShapeInfo(shapeInfo);
		if (drawStyles)
			marker.setDrawStyles(drawStyles);
		if (extraPropValues)
			marker.setPropValues(extraPropValues);
		//this.setPropStoreFieldValue(markerPropName, marker);
		this.getUiMarkers().addMarker(marker);
		if (updateRenderer)
		{
			//var updateType = Kekule.Render.ObjectUpdateType.ADD;
			//this.getUiRenderer().update(this.getUiContext(), this.getUiMarkers(), marker, updateType);
			this.repaintUiMarker();
		}
		return marker;
	},
	/**
	 * Change the shape info of a meta shape based marker, or create a new marker based on shape info.
	 * @private
	 */
	modifyShapeBasedMarker: function(marker, newShapeInfo, drawStyles, updateRenderer)
	{
		var updateType = Kekule.Render.ObjectUpdateType.MODIFY;
		if (newShapeInfo)
			marker.setShapeInfo(newShapeInfo);
		if (drawStyles)
			marker.setDrawStyles(drawStyles);
		// notify change and update renderer
		if (updateRenderer)
		{
			//this.getUiPainter().redraw();
			//this.getUiRenderer().update(this.getUiContext(), this.getUiMarkers(), marker, updateType);
			this.repaintUiMarker();
		}
	},
	/**
	 * Create a new marker based on shapeInfo.
	 * @private
	 */
	createTextBasedMarker: function(coord, text, drawStyles, extraPropValues, updateRenderer)
	{
		var marker = new Kekule.ChemWidget.TextUiMarker();
		if (coord)
			marker.setCoord(coord);
		if (text)
			marker.setText(text);
		if (drawStyles)
			marker.setDrawStyles(drawStyles);
		if (extraPropValues)
			marker.setPropValues(extraPropValues);
		//this.setPropStoreFieldValue(markerPropName, marker);
		this.getUiMarkers().addMarker(marker);
		if (updateRenderer)
		{
			//var updateType = Kekule.Render.ObjectUpdateType.ADD;
			//this.getUiRenderer().update(this.getUiContext(), this.getUiMarkers(), marker, updateType);
			this.repaintUiMarker();
		}
		return marker;
	},
	/**
	 * Change the shape info of a meta shape based marker, or create a new marker based on shape info.
	 * @private
	 */
	modifyTextBasedMarker: function(marker, newCoord, newText, drawStyles, updateRenderer)
	{
		var updateType = Kekule.Render.ObjectUpdateType.MODIFY;
		if (newCoord)
			marker.setCoord(newCoord);
		if (newText)
			marker.setText(newText);
		if (drawStyles)
			marker.setDrawStyles(drawStyles);
		// notify change and update renderer
		if (updateRenderer)
		{
			//this.getUiPainter().redraw();
			//this.getUiRenderer().update(this.getUiContext(), this.getUiMarkers(), marker, updateType);
			this.repaintUiMarker();
		}
	},
	/**
	 * Returns the UI markers in a specified group.
	 * @param {String} group
	 * @returns {Array} Array of {Kekule.ChemWidget.AbstractMarker}.
	 */
	getUiMarkersOfGroup: function(group)
	{
		return this.getUiMarkers().getMarkersOfGroup(group);
	},
	/**
	 * Change UI markers with a new set of property values.
	 * @param {Array} markers
	 * @param {Hash} propValues
	 */
	modifyUiMarkers: function(markers, propValues)
	{
		var ms = (markers instanceof Kekule.ChemWidget.UiMarkerCollection)? markers.getMarkers(): AU.toArray(markers);
		for (var i = 0, l = ms.length; i < l; ++i)
		{
			ms[i].setPropValues(propValues);
		}
	},
	/**
	 * Set the visible property a series of UI markers.
	 * @param {Array} markers
	 * @param {Bool} visible
	 * @param {Bool} updateRenderer
	 */
	setVisibleOfUiMarkers: function(markers, visible, updateRenderer)
	{
		this.modifyUiMarkers(markers, {'visible': visible});
		if (updateRenderer)
		{
			this.repaintUiMarker();
		}
		return this;
	},
	/**
	 * Set the visible properties of a group of UI markers.
	 * @param {String} markerGroup
	 * @param {Bool} visible
	 * @param {Bool} updateRenderer
	 */
	setVisibleOfUiMarkerGroup: function(markerGroup, visible, updateRenderer)
	{
		var markers = this.getUiMarkersOfGroup(markerGroup);
		return this.setVisibleOfUiMarkers(markers, visible, updateRenderer);
	},
	/**
	 * Hide a UI marker.
	 * @param {Kekule.ChemWidget.AbstractMarker} marker
	 * @param {Bool} updateRenderer
	 */
	hideUiMarker: function(marker, updateRenderer)
	{
		marker.setVisible(false);
		// notify change and update renderer
		if (updateRenderer)
		{
			//this.getUiRenderer().update(this.getUiContext(), this.getUiMarkers(), marker, Kekule.Render.ObjectUpdateType.MODIFY);
			this.repaintUiMarker();
		}
	},
	/**
	 * Show an UI marker.
	 * @param {Kekule.ChemWidget.AbstractMarker} marker
	 * @param {Bool} updateRenderer
	 */
	showUiMarker: function(marker, updateRenderer)
	{
		marker.setVisible(true);
		if (updateRenderer)
		{
			//this.getUiRenderer().update(this.getUiContext(), this.getUiMarkers(), marker, Kekule.Render.ObjectUpdateType.MODIFY);
			this.repaintUiMarker();
		}
	},
	/**
	 * Remove a marker from collection.
	 * @private
	 */
	removeUiMarker: function(marker)
	{
		if (marker)
		{
			this.getUiMarkers().removeMarker(marker);
			//this.getUiRenderer().update(this.getUiContext(), this.getUiMarkers(), marker, Kekule.Render.ObjectUpdateType.REMOVE);
			this.repaintUiMarker();
		}
	},
	/**
	 * Clear all UI markers.
	 * @private
	 */
	clearUiMarkers: function()
	{
		this.getUiMarkers().clearMarkers();
		//this.getUiRenderer().redraw(this.getUiContext());
		//this.redraw();
		this.repaintUiMarker();
	},
	/** @private */
	clearUiContext: function()
	{
		if (this.getUiContext() && this.getUiDrawBridge())
			this.getUiDrawBridge().clearContext(this.getUiContext());
	},

	////////////////////////////////////////////////////
	/**
	 * Returns whether the select interaction is enabled in viewer.
	 * @returns {Bool}
	 */
	getEnableObjectSelect: function()
	{
		// TODO: currently only allowed in 2D viewer
		return this.getRenderType() === Kekule.Render.RendererType.R2D
			&& this.getViewerConfigs().getInteractionConfigs().getEnableBasicObjectSelect();
	},
	/**
	 * Returns whether the hot track interaction is enabled in viewer.
	 * @returns {Bool}
	 */
	getEnableObjectHotTrack: function()
	{
		// TODO: currently only allowed in 2D viewer
		return this.getRenderType() === Kekule.Render.RendererType.R2D
			&& this.getViewerConfigs().getInteractionConfigs().getEnableBasicObjectHotTrack();
	},

	/**
	 * Add object(s) to selection.
	 * @param {Variant} objs
	 */
	addToSelection: function(objs)
	{
		var selection = AU.clone(this.getSelectedObjects());
		var objects = objs? AU.toArray(objs): [];
		var changed = false;
		for (var i = 0, l = objects.length; i < l; ++i)
		{
			var obj = objects[i];
			if (selection.indexOf(obj) >= 0)  // already inside, bypass
				continue;
			else
			{
				selection.push(obj);
				changed = true;
			}
		}
		if (changed)
			this.changeSelectedObjects(selection, true);
	},
	/**
	 * Remove object(s) from selection.
	 * @param {Variant} objs
	 */
	removeFromSelection: function(objs)
	{
		var selection = AU.clone(this.getSelectedObjects());
		var objects = objs? AU.toArray(objs): [];
		var changed = false;
		for (var i = 0, l = objects.length; i < l; ++i)
		{
			var obj = objects[i];
			var index = selection.indexOf(obj);
			if (index >= 0)  // inside, remove it
			{
				selection.splice(index, 1);
				changed = true;
			}
		}
		if (changed)
			this.changeSelectedObjects(selection, true);
	},
	/**
	 * Toggle selection state of data items.
	 * @param {Variant} objs
	 */
	toggleSelectingState: function(objs)
	{
		var selection = AU.clone(this.getSelectedObjects());
		var objects = AU.toArray(objs);
		for (var i = 0, l = objects.length; i < l; ++i)
		{
			var obj = objects[i];
			var index = selection.indexOf(obj);
			if (index >= 0)  // inside, remove it
			{
				selection.splice(index, 1);
			}
			else  // not inside, add it
			{
				selection.push(obj);
			}
		}
		this.changeSelectedObjects(selection, true);
	},
	/**
	 * Select object(s).
	 * @param {Variant} objs
	 */
	select: function(objs)
	{
		var objects = objs? AU.toArray(objs): [];
		this.changeSelectedObjects(objects, true);
	},

	/**
	 * Clear all hot track items in viewer and all its child sub views.
	 * @param {Array} byPassedHosts
	 * @param {Bool} doRepaint
	 * @private
	 */
	clearHotTrackedItems: function(byPassedHosts, doRepaint)
	{
		// subviews
		this.iterateSubViews(function(subView) {
			if (!byPassedHosts || byPassedHosts.indexOf(subView) < 0)
				subView.doClearHotTrackedItems();
		});
		// self
		if (!byPassedHosts || byPassedHosts.indexOf(this) < 0)
			this.doClearHotTrackedItems();
		if (doRepaint)
			this.repaintUiMarker();
	},
	/** @private */
	doClearHotTrackedItems: function(doRepaint)
	{
		this.changeHotTrackedObjects([], doRepaint);
	},
	/**
	 * Clear all selected items in viewer and all its child sub views.
	 * @param {Array} byPassedHosts
	 * @param {Bool} doRepaint
	 * @private
	 */
	clearSelectedItems: function(byPassedHosts, doRepaint)
	{
		// subviews
		this.iterateSubViews(function(subView) {
			if (byPassedHosts && byPassedHosts.indexOf(subView) < 0)
				subView.doClearSelectedItems();
		});
		// self
		if (byPassedHosts && byPassedHosts.indexOf(this) < 0)
			this.doClearSelectedItems();
		if (doRepaint)
			this.repaintUiMarker();
	},
	/** @private */
	doClearSelectedItems: function(doRepaint)
	{
		this.changeSelectedObjects([], doRepaint);
	},

	/** @private */
	_isSameHotTrackedOrSelectedObjs: function(objs1, objs2)
	{
		var o1 = objs1, o2 = objs2;
		if (o1 && !o1.length)
			o1 = null;
		if (o2 && !o2.length)
			o2 = null;
		if (o1 == o2)
			return true;
		else if (o1 && o2)
		{
			return AU.compare(o1, o2, function(a, b) { return (a === b)? 0: -1}) === 0;
		}
		else  // !objs1 || !objs2
			return false;
	},
	/** @private */
	_changeHotTrackOrSelectObjectRenderStyles: function(oldObjs, newObjs, isSelect, doRepaint)
	{
		var needRepaintContext =  false;
		var objRenderStyleFieldName = isSelect? '_selectedObjectRenderStyles': '_hotTrackedObjectRenderStyles';
		if (oldObjs && oldObjs.length)
		{
			var oldRenderStyles = this[objRenderStyleFieldName];
			if (oldRenderStyles)
			{
				for (var i = 0, l = oldObjs.length; i < l; ++i)
				{
					var obj = oldObjs[i];
					if (obj.removeOverrideRenderOptionItem)
					{
						obj.removeOverrideRenderOptionItem(oldRenderStyles);
						needRepaintContext = true;
					}
				}
			}
		}
		if (newObjs && newObjs.length)
		{
			var renderStyles = isSelect ?
				this.getViewerConfigs().getUiMarkerConfigs().getSelectedObjectStyles() :
				this.getViewerConfigs().getUiMarkerConfigs().getHotTrackedObjectStyles();
			if (renderStyles)
			{
				this[objRenderStyleFieldName] = renderStyles;
				for (var i = 0, l = newObjs.length; i < l; ++i)
				{
					var obj = newObjs[i];
					if (obj.addOverrideRenderOptionItem)
					{
						obj.addOverrideRenderOptionItem(renderStyles);
						needRepaintContext = true;
					}
				}
			}
		}
		else
			this[objRenderStyleFieldName] = null;

		if (doRepaint && needRepaintContext)
			this.requestRepaint();
	},
	/** @private */
	changeHotTrackedObjects: function(newObjects, doRepaint)
	{
		var old = this.getHotTrackedObjects() || [];
		var newObjs = newObjects? AU.toArray(newObjects): [];
		//console.log('set hot track', newObjects, old, this._isSameHotTrackedOrSelectedObjs(old, newObjects));
		//console.log('vewer.changeHotTrackedObjects', this.isUpdatingUiMarkers(), this._uiMarkerUpdateFlag, doRepaint);
		if (!this._isSameHotTrackedOrSelectedObjs(old, newObjs))
		{
			if (doRepaint)
				this.beginUpdateUiMarkers();
			try
			{
				this._changeHotTrackOrSelectObjectRenderStyles(old, newObjs, false, doRepaint);

				this.setPropStoreFieldValue('hotTrackedObjects', newObjs);
				if (newObjs && newObjs.length)
					this.clearHotTrackedItems([this], false);
				//console.log('new hot track', newObjects, doRepaint);
				if (doRepaint)
					this.updateUiMarkers(true);

				this.invokeEvent('hotTrackOnObjects', {'objects': newObjs, 'prevObjects': old});
			}
			finally
			{
				if (doRepaint)
					this.endUpdateUiMarkers();
			}
		}
	},
	/** @private */
	changeSelectedObjects: function(newObjects, doRepaint)
	{
		var old = this.getSelectedObjects();
		var newObjs = newObjects? AU.toArray(newObjects): [];
		if (!this._isSameHotTrackedOrSelectedObjs(old, newObjs))
		{
			if (doRepaint)
				this.beginUpdateUiMarkers();
			try
			{
				this._changeHotTrackOrSelectObjectRenderStyles(old, newObjs, true, doRepaint);

				this.setPropStoreFieldValue('selectedObjects', newObjs);
				if (newObjs && newObjs.length)
					this.clearSelectedItems([this], false);
				if (doRepaint)
					this.updateUiMarkers(true);

				this.invokeEvent('selectionChange', {'objects': newObjs, 'prevObjects': old});
			}
			finally
			{
				if (doRepaint)
					this.endUpdateUiMarkers();
			}
		}
	},

	/** @private */
	_calcBoundsOfObjects: function(objects, boundInflation)
	{
		var bounds = [];
		if (Kekule.ObjUtils.isUnset(boundInflation))
			boundInflation = this.getViewerConfigs().getInteractionConfigs().getObjBoundTrackInflation();
		for (var i = 0; i < objects.length; ++i)
		{
			var obj = objects[i];
			var infos = this.getBoundInfoRecorder().getBelongedInfos(this.getDrawContext(), obj);
			if (infos && infos.length)
			{
				for (var j = 0, k = infos.length; j < k; ++j)
				{
					var info = infos[j];
					var bound = info.boundInfo;
					if (bound)
					{
						// inflate
						bound = Kekule.Render.MetaShapeUtils.inflateShape(bound, boundInflation);
						Kekule.ArrayUtils.pushUnique(bounds, bound);
					}
				}
			}
		}
		return bounds;
	},

	/// Methods about popup editing ////////////////
	/**
	 * Returns whether editor can be lauched in current viewer.
	 */
	getAllowEditing: function()
	{
		return (this.getCoordMode() !== Kekule.CoordMode.COORD3D) &&
			this.getEnableEdit() && (this.getChemObj() || this.getEnableEditFromVoid());
	},
	/** @private */
	getComposerDialog: function()
	{
		var result;
		if (this.getShareEditorInstance())
			result = Kekule.ChemWidget.Viewer._composerDialog;
		else
			result = this._composerDialog;
		if (!result)
		{
			if (Kekule.Editor.ComposerDialog)
			{
				result = new Kekule.Editor.ComposerDialog(this.getDocument(), Kekule.$L('ChemWidgetTexts.CAPTION_EDIT_OBJ'), //CWT.CAPTION_EDIT_OBJ,
						[Kekule.Widget.DialogButtons.OK, Kekule.Widget.DialogButtons.CANCEL]);
			}
		}
		if (this.getShareEditorInstance())
			Kekule.ChemWidget.Viewer._composerDialog = result;
		else
			this._composerDialog = result;
		return result;
	},
	/** @private */
	getComposerPanel: function()
	{
		var result;
		if (this.getShareEditorInstance())
			result = Kekule.ChemWidget.Viewer._composerPanel;
		else
			result = this._composerPanel;
		if (!result)  // create new
		{
			if (Kekule.Editor.Composer/* && Kekule.Editor.ComposerFrame*/)
			{
				result = new Kekule.Editor.Composer(this.getDocument());
				//result = new Kekule.Editor.ComposerFrame(this.getDocument());
				result.addClassName(CCNS.VIEWER_EDITOR_FULLCLIENT);
				result.setUseNormalBackground(true);
				//result.setAutoAdjustSizeOnPopup(true);
				var minDim = Kekule.Editor.Composer.getMinPreferredDimension();
				result.setMinDimension(minDim);
				//result.setAutoSetMinDimension(true);
				result.setEnableDimensionTransform(true);
				result.setAutoResizeConstraints({'width': 1, 'height': 1});
				// set toolbar buttons, remove config and inspector to save place
				var btns = Kekule.globalOptions.chemWidget.composer.commonToolButtons;
				btns = AU.exclude(btns, [BNS.cut, BNS.config, BNS.objInspector]);
				result.setCommonToolButtons(btns);

				// two custom buttons to save or discard edits
				var customButtons = [
					{
						'text': Kekule.$L('ChemWidgetTexts.CAPTION_EDITOR_DONE'),
						'hint': Kekule.$L('ChemWidgetTexts.HINT_EDITOR_DONE'),
						'htmlClass': 'K-Res-Button-YesOk',
						'showText': true,
						'#execute': function() { result._doneEditCallback(); result.hide(); }
					},
					{
						'text': Kekule.$L('ChemWidgetTexts.CAPTION_EDITOR_CANCEL'),
						'hint': Kekule.$L('ChemWidgetTexts.HINT_EDITOR_CANCEL'),
						'htmlClass': 'K-Res-Button-NoCancel',
						'showText': true,
						'#execute': function() { result.hide(); }
					}
				];
				result._customEndEditButtons = customButtons;  // use a special field to store in composer
			}
		}
		if (this.getShareEditorInstance())
			Kekule.ChemWidget.Viewer._composerPanel = result;
		else
			this._composerPanel = result;
		result._invokerViewer = this;
		result._doneEditCallback = null;
		return result;
	},
	/**
	 * Open a popup editor to modify displayed object.
	 * @param {Kekule.Widget.BaseWidget} callerWidget Who invokes edit action, default is the viewer itself.
	 */
	openEditor: function(callerWidget)
	{
		//if (this.getEnableEdit() && this.getChemObj())
		if (this.getAllowEditing())
		{
			// load object in editor
			var chemObj = this.getChemObj();
			var editFromVoid = !chemObj;
			var editFromEmpty =  chemObj && chemObj.isEmpty && chemObj.isEmpty(); // has chem object but obj is empty (e.g., mol with no atom and bond)
			var restrainObj = this.getRestrainEditorWithCurrObj();

			var clientDim = Kekule.DocumentUtils.getClientDimension(this.getDocument());
			//var clientDim = Kekule.DocumentUtils.getClientVisibleBox(this.getDocument());
			//console.log(clientDim);
			var minComposerDim = Kekule.Editor.Composer.getMinPreferredDimension();
			if (clientDim.width <= minComposerDim.width + 50 || clientDim.height < minComposerDim.height + 100)
			{
				this._openEditComposer(callerWidget, chemObj, restrainObj, editFromVoid, editFromEmpty);
			}
			else
			{
				this._openEditComposerDialog(callerWidget, chemObj, restrainObj, editFromVoid, editFromEmpty);
			}
		}
	},
	/** @private */
	_prepareEditComposer: function(composer, restrainObj, editFromVoid, editFromEmpty)
	{
		composer.setEnableCreateNewDoc(editFromVoid || !restrainObj);
		composer.setEnableLoadNewFile(editFromVoid || !restrainObj);
		composer.setAllowCreateNewChild(editFromVoid || !restrainObj);

		var editorProperties = this.getEditorProperties();
		if (editorProperties)
			composer.setPropValues(editorProperties);

		//composer.updateAllActions();
		//console.log(composer.getEnableLoadNewFile(), editFromVoid);
	},
	/** @private */
	_feedbackEditResult: function(composer, chemObj, editFromVoid)
	{
		if (!composer.isDirty())
			return;
		var newObj = composer.getSavingTargetObj();
		if (editFromVoid)
		{
			this.setChemObj(newObj.clone());
		}
		else if (this.getRestrainEditorWithCurrObj())
		{
			if (chemObj.getClass() === newObj.getClass())  // same type of object in editor
				chemObj.assign(newObj.clone());
			else  // preserve old object type in viewer
				chemObj.assign(newObj);
			// clear src info data
			chemObj.setSrcInfo(null);
			//self.repaint();
			this.setChemObj(chemObj); // force repaint, as repaint() will not reflect to object changes
		}
		else // not restrain, load object in composer directy into viewer
		{
			//console.log(newObj);
			this.setChemObj(newObj);
		}
		this.invokeEvent('editingDone', {'obj': this.getChemObj()});
	},
	/** @private */
	_openEditComposerDialog: function(callerWidget, chemObj, restrainObj, editFromVoid, editFromEmpty)
	{
		var dialog = this.getComposerDialog();
		if (!dialog)  // can not invoke composer dialog
		{
			Kekule.error(Kekule.$L('ErrorMsg.CAN_NOT_CREATE_EDITOR'));
			return;
		}

		var composer = dialog.getComposer();
		this._prepareEditComposer(composer, restrainObj, editFromVoid, editFromEmpty);

		var cloneObj;
		if (!editFromVoid && !editFromEmpty)
		{
			cloneObj = chemObj.clone();  // edit this cloned one, avoid affect chemObj directly
			dialog.setChemObj(cloneObj);
		}
		else
		{
			//dialog.setChemObj(null);
			dialog.getComposer().newDoc();
		}

		var self = this;
		var callback = function(dialogResult)
		{
			if (dialogResult === Kekule.Widget.DialogButtons.OK && dialog.getComposer().isDirty())  // feedback result
			{
				/*
				var newObj = dialog.getSavingTargetObj();
				if (editFromVoid)
				{
					self.setChemObj(newObj.clone());
				}
				else
				{
					if (self.getRestrainEditorWithCurrObj())
					{
						if (chemObj.getClass() === newObj.getClass())  // same type of object in editor
							chemObj.assign(newObj.clone());
						else  // preserve old object type in viewer
							chemObj.assign(cloneObj);
						// clear src info data
						chemObj.setSrcInfo(null);
						//self.repaint();
						self.setChemObj(chemObj); // force repaint, as repaint() will not reflect to object changes
					}
					else // not restrain, load object in composer directy into viewer
					{
						//console.log(newObj);
						self.setChemObj(newObj);
					}
				}
				*/
				self._feedbackEditResult(dialog.getComposer(), chemObj, editFromVoid);
			}
			//dialog.finalize();
		};
		if (this.getModalEdit())
			dialog.openModal(callback, callerWidget || this);
		else
			dialog.openPopup(callback, callerWidget || this);
	},
	/** @private */
	_openEditComposer: function(callerWidget, chemObj, restrainObj, editFromVoid, editFromEmpty)
	{
		var composer = this.getComposerPanel();
		if (!composer)  // can not invoke composer
		{
			Kekule.error(Kekule.$L('ErrorMsg.CAN_NOT_CREATE_EDITOR'));
			return;
		}

		//var composer = composerFrame.getComposer();
		this._prepareEditComposer(composer, restrainObj, editFromVoid, editFromEmpty);
		//composer.newDoc();

		// ensure save & cancel buttons are in toolbar
		var customButtons = composer._customEndEditButtons;
		var toolbtns = composer.getCommonToolButtons() || [];
		var btnModified = false;
		for (var i = 0, l = customButtons.length; i < l; ++i)
		{
			var btn = customButtons[i];
			if (toolbtns.indexOf(btn) < 0)
			{
				toolbtns.push(btn);
				btnModified = true;
			}
		}
		if (btnModified)
			composer.setCommonToolButtons(toolbtns);


		if (!editFromVoid && !editFromEmpty)
		{
			//composer.updateDimensionTransform();
			var cloneObj = chemObj.clone();  // edit this cloned one, avoid affect chemObj directly
			composer.setChemObj(cloneObj);
		}
		else
		{
			composer.newDoc();
		}

		var self = this;
		composer._doneEditCallback = function(){
			self._feedbackEditResult(composer, chemObj, editFromVoid);
		};
		composer.show(callerWidget, function(){
			//var cloneObj;
			if (!editFromVoid && !editFromEmpty)
			{
				//composer.updateDimensionTransform();
				//cloneObj = chemObj.clone();  // edit this cloned one, avoid affect chemObj directly
				composer.setChemObj(cloneObj); // set chemObj again, or it will not be displayed in some mobile browsers
			}
			else
			{
				//dialog.setChemObj(null);
				//composer.newDoc();
			}
		}, Kekule.Widget.ShowHideType.POPUP);
	},
	/*
	 * Returns a new widget to edit object in viewer.
	 * @private
	 */
	/*
	createEditorWidget: function()
	{
		var result = new Kekule.Editor.Composer(this.getDocument());
		var editor = result.getEditor();
		editor.setEnableCreateNewDoc(false);
		editor.setEnableLoadNewFile(false);
		editor.setAllowCreateNewChild(false);
		editor.addClassName(CNS.DYN_CREATED);
		return result;
	},
	*/


	////////////////////////////////////////////////
	/**
	 * Reset viewer to initial state (no zoom, rotation and so on).
	 */
	resetView: function()
	{
		return this.resetDisplay();
	},


	/**
	 * Returns current 2D rotation angle (in arc).
	 * @returns {Float}
	 */
	getCurr2DRotationAngle: function()
	{
		return this.getDrawOptions().rotateAngle || 0;
	},
	/**
	 * Do a 2D rotation base on delta.
 	 * @param {Float} delta In arc.
	 * @param {Bool} suspendRendering Set this to true if a immediate repaint is not needed.
	 */
	rotate2DBy: function(delta, suspendRendering)
	{
		return this.rotate2DTo(this.getCurr2DRotationAngle() + delta, suspendRendering);
	},
	/**
	 * Do a 2D rotation to angle.
	 * @param {Float} angle In arc.
	 * @param {Bool} suspendRendering Set this to true if a immediate repaint is not needed.
	 */
	rotate2DTo: function(angle, suspendRendering)
	{
		this.getDrawOptions().rotateAngle = angle;
		//this.drawOptionChanged();
		if (!suspendRendering)
			this.geometryOptionChanged();
		return this;
	},

	/**
	 * Returns current 3D rotation info.
	 * @returns {Hash} {rotateMatrix, rotateX, rotateY, rotateZ, rotateAngle, rotateAxisVector}
	 */
	getCurr3DRotationInfo: function()
	{
		var result = {};
		var fields = ['rotateMatrix', 'rotateX', 'rotateY', 'rotateZ', 'rotateAngle'];
		var ops = this.getDrawOptions();
		for (var i = 0, l = fields.length; i < l; ++i)
		{
			var field = fields[i];
			result[field] = ops[field] || 0;
		}
		// rotateAxisVector
		result.rotateAxisVector = ops.rotateAxisVector || null;
		return result;
	},
	/**
	 * Set 3D rotation matrix.
	 * @param {Array} matrix A 4X4 rotation matrix.
	 * @param {Bool} suspendRendering Set this to true if a immediate repaint is not needed.
	 */
	setRotate3DMatrix: function(matrix, suspendRendering)
	{
		this.getDrawOptions().rotateMatrix = matrix;
		//this.drawOptionChanged();
		if (!suspendRendering)
			this.geometryOptionChanged();
		return this;
	},

	/**
	 * Do a 3D rotation base on delta.
	 * @param {Float} deltaX In arc.
	 * @param {Float} deltaY In arc.
	 * @param {Float} deltaZ In arc.
	 * @param {Bool} suspendRendering Set this to true if a immediate repaint is not needed.
	 */
	rotate3DBy: function(deltaX, deltaY, deltaZ, suspendRendering)
	{
		var lastInfo = this.getCurr3DRotationInfo();
		var lastMatrix = lastInfo.rotateMatrix || Kekule.MatrixUtils.createIdentity(4);
		//console.log('lastMatrix', lastMatrix);
		var currMatrix = Kekule.CoordUtils.calcRotate3DMatrix({
			'rotateX': deltaX,
			'rotateY': deltaY,
			'rotateZ': deltaZ
		});
		//var matrix = Kekule.MatrixUtils.multiply(lastMatrix, currMatrix);
		var matrix = Kekule.MatrixUtils.multiply(currMatrix, lastMatrix);   // x/y/z system changes also after each rotation
		//console.log('nowMatrix', matrix);
		this.setRotate3DMatrix(matrix, suspendRendering);
		/*
		angles.rotateX += deltaX || 0;
		angles.rotateY += deltaY || 0;
		angles.rotateZ += deltaZ || 0;
		return this.rotate3DTo(angles.rotateX, angles.rotateY, angles.rotateZ);
		*/
		return this;
	},
	/**
	 * Do a 3D rotation around axis.
	 * @param {Float} angle In arc.
	 * @param {Hash} axisVector Axis vector coord.
	 * @param {Bool} suspendRendering Set this to true if a immediate repaint is not needed.
	 */
	rotate3DByAxis: function(angle, axisVector, suspendRendering)
	{
		var lastInfo = this.getCurr3DRotationInfo();
		var lastMatrix = lastInfo.rotateMatrix || Kekule.MatrixUtils.createIdentity(4);
		var currMatrix = Kekule.CoordUtils.calcRotate3DMatrix({
			'rotateAngle': angle,
			'rotateAxisVector': axisVector
		});
		var matrix = Kekule.MatrixUtils.multiply(currMatrix, lastMatrix);   // sequence is IMPORTANT!
		//var matrix = Kekule.MatrixUtils.multiply(lastMatrix, currMatrix);
		this.setRotate3DMatrix(matrix, suspendRendering);
		return this;
	},
	/*
	 * Do a 3D rotation to angle on x/y/z axis
	 * @param {Float} x Rotation on X axis
	 * @param {Float} y Rotation on Y axis
	 * @param {Float} z Rotation on Z axis
	 */
	/*
	rotate3DTo: function(x, y, z)
	{
		var ops = this.getDrawOptions();
		ops.rotateX = x || 0;
		ops.rotateY = y || 0;
		ops.rotateZ = z || 0;

		this.drawOptionChanged();
		return this;
	},
	*/

	// methods about tool buttons
	/** @private */
	getDefaultToolBarButtons: function()
	{
		return Kekule.globalOptions.chemWidget.viewer.toolButtons;
		/*
		var buttons = [
			//BNS.loadFile,
			BNS.loadData,
			BNS.saveData,
			//BNS.clearObjs,
			BNS.molDisplayType,
			BNS.molHideHydrogens,
			BNS.zoomIn, BNS.zoomOut
		];
		// rotate
		//if (this.getRenderType() === Kekule.Render.RendererType.R3D)
		{
			buttons = buttons.concat([BNS.rotateX, BNS.rotateY, BNS.rotateZ]);
		}
		//else
		{
			buttons = buttons.concat([BNS.rotateLeft, BNS.rotateRight]);
		}
		buttons.push(BNS.reset);
		// debug
		buttons.push(BNS.openEditor);
		// config
		buttons.push(BNS.config);
		// debug
		//buttons.push(BNS.menu);

		return buttons;
		*/
	},

	/* @private */
	/*
	createDefaultToolButtonNameMapping: function()
	{
		var result = {};
		result[BNS.loadFile] = CW.ActionDisplayerLoadFile;
		result[BNS.loadData] = CW.ActionDisplayerLoadData;
		result[BNS.saveData] = CW.ActionDisplayerSaveFile;
		result[BNS.clearObjs] = CW.ActionDisplayerClear;
		result[BNS.zoomIn] = CW.ActionDisplayerZoomIn;
		result[BNS.zoomOut] = CW.ActionDisplayerZoomOut;
		result[BNS.rotateLeft] = CW.ActionViewerRotateLeft;
		result[BNS.rotateRight] = CW.ActionViewerRotateRight;
		result[BNS.rotateX] = CW.ActionViewerRotateX;
		result[BNS.rotateY] = CW.ActionViewerRotateY;
		result[BNS.rotateZ] = CW.ActionViewerRotateZ;
		result[BNS.reset] = CW.ActionDisplayerReset;
		result[BNS.molHideHydrogens] = CW.ActionDisplayerHideHydrogens;
		result[BNS.molDisplayType] = CW.ActionViewerChangeMolDisplayTypeStub;

		result[BNS.openEditor] = CW.ActionViewerEdit;
		result[BNS.config] = Kekule.Widget.ActionOpenConfigWidget;

		return result;
	},
	*/

	/**
	 * Return whether toolbarParentElem is not set the the toolbar is directly embedded in viewer itself.
	 * @returns {Bool}
	 */
	isToolbarEmbedded: function()
	{
		return !this.getToolbarParentElem();
	},

	/**
	 * Recalc and set toolbar position.
	 * @private
	 */
	adjustToolbarPos: function()
	{
		var toolbar = this.getToolbar();
		if (!toolbar)
			return;
		if (this.isToolbarEmbedded())
		{
			//if (toolbar)
			{
				var WP = Kekule.Widget.Position;
				//var viewerClientRect = Kekule.HtmlElementUtils.getElemBoundingClientRect(this.getDrawContextParentElem()); //this.getBoundingClientRect();
				var viewerClientRect = Kekule.HtmlElementUtils.getElemPageRect(this.getDrawContextParentElem());
				//var toolbarClientRect = toolbar.getBoundingClientRect();
				var toolbarClientRect = Kekule.HtmlElementUtils.getElemPageRect(toolbar);
				var pos = this.getToolbarPos();
				var hMargin = this.getToolbarMarginHorizontal();
				var vMargin = this.getToolbarMarginVertical();

				// default
				var hPosProp = 'left', vPosProp = 'top';
				var hPosPropUnused = 'right', vPosPropUnused = 'bottom';
				var hPosValue = (viewerClientRect.width - toolbarClientRect.width) / 2;
				var vPosValue = (viewerClientRect.height - toolbarClientRect.height) / 2;

				if (pos === WP.AUTO || !pos)  // auto decide position, including margin
				{
					var toolbarTotalW = ((hMargin > 0) ? hMargin : 0) * 2 + toolbarClientRect.width;
					var toolbarTotalH = ((vMargin > 0) ? vMargin : 0) * 2 + toolbarClientRect.height;
					if (toolbarTotalW > viewerClientRect.width)  // can not fit in viewer
					{
						pos |= WP.LEFT;
						hMargin = 0;
					}
					else
						pos |= WP.RIGHT;
					if (toolbarTotalH > viewerClientRect.height / 2)  // can not fit in viewer
					{
						pos |= WP.BOTTOM;
						vMargin = -1;
					}
					else
						pos |= WP.BOTTOM;
				}

				// horizontal direction
				if (pos & WP.LEFT)
				{
					hPosProp = 'left';
					hPosPropUnused = 'right';
					hPosValue = (hMargin >= 0) ? hMargin : hMargin - toolbarClientRect.width;
				}
				else if (pos & WP.RIGHT)
				{
					hPosProp = 'right';
					hPosPropUnused = 'left';
					hPosValue = (hMargin >= 0) ? hMargin : hMargin - toolbarClientRect.width;
				}
				// vertical direction
				if (pos & WP.TOP)
				{
					vPosProp = 'top';
					vPosPropUnused = 'bottom';
					vPosValue = (vMargin >= 0) ? vMargin : vMargin - toolbarClientRect.height;
				}
				else if (pos & WP.BOTTOM)
				{
					vPosProp = 'bottom';
					vPosPropUnused = 'top';
					vPosValue = (vMargin >= 0) ? vMargin : vMargin - toolbarClientRect.height;
				}
				toolbar.removeStyleProperty(hPosPropUnused);
				toolbar.removeStyleProperty(vPosPropUnused);
				toolbar.setStyleProperty(hPosProp, hPosValue + 'px');
				toolbar.setStyleProperty(vPosProp, vPosValue + 'px');
			}
		}
		else  // toolbar parent appointed
		{
			toolbar.removeStyleProperty('left');
			toolbar.removeStyleProperty('top');
			toolbar.removeStyleProperty('width');
			toolbar.removeStyleProperty('height');
		}
	},
	/**
	 * Update toolbar in viewer.
	 */
	updateToolbar: function()
	{
		if (this.getEnableToolbar())
		{
			this.createToolbar();
			this.adjustToolbarPos();
		}
		else
			this.setToolbar(null);
	},
	/**
	 * Update menu in viewer.
	 */
	updateMenu: function()
	{
		this.createMenu();
	},
	/**
	 * Update toolbar and menu in viewer.
	 */
	updateUiComps: function()
	{
		this.updateToolbar();
		this.updateMenu();
	},

	/**
	 * Update toolbar actions.
	 * @private
	 */
	updateActions: function()
	{
		if (this.getActions())
			this.getActions().updateAll();
	},

	/** @private */
	clearActions: function()
	{
		this.getActions().clear();
		this.getActionMap().clear();
	},

	/** @private */
	getCompActionClass: function(compName)
	{
		//return this.getToolButtonNameMapping()[compName];
		return this.getChildActionClass(compName, true);
	},

	/** @private */
	_getActionOfComp: function(compNameOrComp, canCreate, defActionClass)
	{
		var map = this.getActionMap();
		var result = map.get(compNameOrComp);
		if (!result && canCreate)
		{
			var c = this.getCompActionClass(compNameOrComp) || defActionClass;
			if (c)
			{
				result = new c(this);
				map.set(compNameOrComp, result);
				this.getActions().add(result);
			}
		}
		return result;
	},

	/** @ignore */
	getChildAction: function(actionName, checkSupClasses)
	{
		var result = this._getActionOfComp(actionName, true);
		if (!result)
		{
			result = this.tryApplySuper('getChildAction', [actionName, checkSupClasses]);
		}
		return result;
	},

	/** @private */
	createToolbar: function()
	{
		this.clearActions();
		var toolBar = new Kekule.Widget.ButtonGroup(this);
		toolBar.addClassName(CNS.DYN_CREATED);
		toolBar.setDisplayed(false);  // hide at first, evokeHelper controls its visibility
		//console.log('After create, display to: ', toolBar.getDisplayed());
		//toolBar.show();
		// add buttons
		//var settings = this.getToolButtonSettings();
		toolBar.setShowText(false);
		toolBar.doSetShowGlyph(true);

		var btns = this.getToolButtons() || this.getDefaultToolBarButtons(); //settings.buttons;
		for (var i = 0, l = btns.length; i < l; ++i)
		{
			var name = btns[i];
			var btn = this.createToolButton(name, toolBar);
		}
		toolBar.addClassName(CCNS.INNER_TOOLBAR);
		if (this.isToolbarEmbedded())
			toolBar.addClassName(CCNS.VIEWER_EMBEDDED_TOOLBAR);

		toolBar.appendToElem(this.getToolbarParentElem() || this.getElement()/*this.getDrawContextParentElem()*/);
			// IMPORTANT, must append to widget before setToolbar,
			// otherwise in Chrome the tool bar may be hidden at first even if we set it to always show
		//console.log('After append to widget: ', toolBar.getDisplayed());
		this.setToolbar(toolBar);
		//console.log('After set tool bar, display to: ', toolBar.getDisplayed());
		//this.updateActions();

		return toolBar;
	},
	/** @private */
	createToolButton: function(btnName, parentGroup)
	{
		var result = null;
		var beginContinuousRepaintingBind = this.beginContinuousRepainting.bind(this);
		var endContinuousRepaintingBind = this.endContinuousRepainting.bind(this);

		var rotateBtnNames = [BNS.rotateX, BNS.rotateY, BNS.rotateZ, BNS.rotateLeft, BNS.rotateRight];

		if (DataType.isObjectValue(btnName))  // custom button
		{
			var objDefHash = Object.extend({'widget': Kekule.Widget.Button}, btnName);
			result = Kekule.Widget.Utils.createFromHash(parentGroup, objDefHash);
			var actionClass = objDefHash.actionClass;
			if (actionClass)  // create action
			{
				if (typeof(actionClass) === 'string')
					actionClass = ClassEx.findClass(objDefHash.actionClass);
			}
			if (actionClass)
			{
				//var action = new actionClass(this);
				//this.getActions().add(action);
				var action = this._getActionOfComp(result, true, actionClass);
				if (action)
					result.setAction(action);
			}
		}
		else
		{
			if (btnName === BNS.molDisplayType)  // in 2D or 3D mode, type differs a lot, need handle separately
			{
				return this.createMolDisplayTypeButton(parentGroup);
			}
			else if (btnName === BNS.menu)  // menu button
			{
				return this.createMenuButton(parentGroup);
			}

			var actionClass = this.getCompActionClass(btnName);
			var btnClass = Kekule.Widget.Button;
			if (btnName === BNS.molHideHydrogens)
				btnClass = Kekule.Widget.CheckButton;
			//if (actionClass)
			{
				result = new btnClass(parentGroup);
				//result.addClassName(CCNS.PREFIX + btnName);
				//var action = new actionClass(this);
				var action = this._getActionOfComp(btnName, true);
				//this.getActions().add(action);
				if (action)
					result.setAction(action);
				if (rotateBtnNames.indexOf(btnName) >= 0)
				{
					result.setPeriodicalExecInterval(20);
					result.setEnablePeriodicalExec(true);
					result.addEventListener('activate', beginContinuousRepaintingBind);
					result.addEventListener('deactivate', endContinuousRepaintingBind);
				}
			}
		}
		return result;
	},
	/** @private */
	createMenuButton: function(parentGroup)
	{
		var result = new Kekule.Widget.DropDownButton(parentGroup);
		result.setText(Kekule.$L('WidgetTexts.CAPTION_MENU')).setHint(Kekule.$L('WidgetTexts.HINT_MENU'));
		result.addClassName(CCNS.VIEWER_MENU_BUTTON);
		// create menu if essential
		if (!this.getMenu())
			this.createMenu();
		result.setDropDownWidget(this.getMenu());

		return result;
	},
	/** @private */
	createMolDisplayTypeButton: function(parentGroup)
	{
		var result = new Kekule.Widget.CompactButtonSet(parentGroup);
		result.getButtonSet().addClassName(CCNS.INNER_TOOLBAR);
		  // IMPORTANT: buttonSet may be popup and moved in DOM tree before showing,
		  // without this, the button size setting in CSS may be lost
		  // TODO: may find a better solution to solve popup widget style lost problem
		result.setShowText(false);
		//result.setHint(CWT.HINT_MOL_DISPLAY_TYPE);
		//var action = new Kekule.ChemWidget.ActionViewerChangeMolDisplayTypeStub(this);
		var action = this._getActionOfComp(BNS.molDisplayType, true);
		result.setAction(action);
		this.getActions().add(action);

		// DONE: Now we fix the drop down direction
		//result.setDropPosition(Kekule.Widget.Position.TOP);

		var doc = this.getDocument();
		var allowedType = this.getAllowedMolDisplayTypes();
		var actionClasses = this._getUsableMolDisplayActionClasses(allowedType);

		for (var i = 0, l = actionClasses.length; i < l; ++i)
		{
			var displayType = actionClasses[i].TYPE;
			/*
			if (allowedType && (allowedType.indexOf(displayType) < 0) && (displayType !== this.getCurrMoleculeDisplayType()))  // not in allowed type
				continue;
			*/

			var btn = new Kekule.Widget.RadioButton(doc);
			//action = new actionClasses[i](this);
			var action = this._getActionOfComp(BNS.molDisplayType + displayType, true, actionClasses[i]);
			//this.getActions().add(action);
			btn.setAction(action);
			result.append(btn, displayType === this.getCurrMoleculeDisplayType());
		}
		return result;
	},

	/* @private */
	_getUsableMolDisplayActionClasses: function(allowedTypes)
	{
		var result = [];
		var actionClasses = (this.getRenderType() === Kekule.Render.RendererType.R3D)?
			CW.Viewer.molDisplayType3DActionClasses: CW.Viewer.molDisplayType2DActionClasses;
		for (var i = 0, l = actionClasses.length; i < l; ++i)
		{
			var displayType = actionClasses[i].TYPE;
			if (allowedTypes && (allowedTypes.indexOf(displayType) < 0) && (displayType !== this.getCurrMoleculeDisplayType()))  // not in allowed type
				continue;
			result.push(actionClasses[i]);
		}
		return result;
	},

	// abount menu
	/** @private */
	getDefaultMenuItems: function()
	{
		return Kekule.globalOptions.chemWidget.viewer.menuItems;
		/*
		var sSeparator = Kekule.Widget.MenuItem.SEPARATOR_TEXT;
		var items = [
			BNS.loadData,
			BNS.saveData,
			sSeparator,
			BNS.molDisplayType,
			BNS.molHideHydrogens,
			BNS.zoomIn, BNS.zoomOut
		];
		// rotate
		items.push({
			'text': Kekule.$L('ChemWidgetTexts.CAPTION_ROTATE'),
			'hint': Kekule.$L('ChemWidgetTexts.HINT_ROTATE'),
			'children': [
				BNS.rotateLeft, BNS.rotateRight,
				BNS.rotateX, BNS.rotateY, BNS.rotateZ
			]
		});
		items.push(BNS.reset);
		items.push(sSeparator);
		items.push(BNS.openEditor);
		// config
		items.push(BNS.config);
		return items;
		*/
	},
	/** @private */
	prepareMenuItems: function(itemDefs)
	{
		var items = [];
		var sSeparator = Kekule.Widget.MenuItem.SEPARATOR_TEXT;
		for (var i = 0, l = itemDefs.length; i < l; ++i)
		{
			var itemDef = itemDefs[i];
			if (typeof(itemDef) === 'string')  // not hash, but a predefined comp name or separator
			{
				if (itemDef !== sSeparator)
				{
					var defHash = this.createPredefinedMenuItemDefHash(itemDef);
					if (defHash)
						items.push(defHash);
				}
			}
			else  // hash definition
			{
				var newItem = Object.extend({}, itemDef);
				if (!itemDef.widget && !itemDef.widgetClass)
				{
					newItem.widget = Kekule.Widget.MenuItem;
				}
				if (itemDef.children && itemDef.children.length)
				{
					var childItems = this.prepareMenuItems(itemDef.children);
					newItem.children = childItems;
				}
				items.push(newItem);
			}
		}
		return items;
	},
	/** @private */
	createPredefinedMenuItemDefHash: function(compName)
	{
		if (compName === BNS.molDisplayType)  // in 2D or 3D mode, type differs a lot, need handle separately
		{
			return this.createMolDisplayMenuDefHash();
		}
		var rotateCompNames = [BNS.rotateX, BNS.rotateY, BNS.rotateZ, BNS.rotateLeft, BNS.rotateRight];
		var beginContinuousRepaintingBind = this.beginContinuousRepainting.bind(this);
		var endContinuousRepaintingBind = this.endContinuousRepainting.bind(this);

		var actionClass = this.getCompActionClass(compName);
		var itemClass = Kekule.Widget.MenuItem;

		var result = null;

		var action = this._getActionOfComp(compName, true);
		if (action)
		{
			//console.log('menu item', action.getClassName(), action.getDisplayer? action.getDisplayer().getElement().id: '-', this.getElement().id);
			result = {
				'widget': itemClass,
				'action': action
			};
			if (rotateCompNames.indexOf(compName) >= 0)
			{
				result = Object.extend(result, {
					'periodicalExecInterval': 20,
					'enablePeriodicalExec': true,
					'#activate': beginContinuousRepaintingBind,
					'@deactivate': endContinuousRepaintingBind
				});
			}
		}
		return result;
	},
	/** @private */
	createMolDisplayMenuDefHash: function()
	{
		var action = this._getActionOfComp(BNS.molDisplayType, true);
		var result = {
			'widget': Kekule.Widget.MenuItem,
			'action': action
		};

		var children = [];
		var allowedType = this.getAllowedMolDisplayTypes();
		var actionClasses = this._getUsableMolDisplayActionClasses(allowedType);

		for (var i = 0, l = actionClasses.length; i < l; ++i)
		{
			var displayType = actionClasses[i].TYPE;
			var action = this._getActionOfComp(BNS.molDisplayType + displayType, true, actionClasses[i]);
			children.push({
				'widget': Kekule.Widget.MenuItem,
				'action': action
			});
		}
		if (children.length)
		{
			result.children = [{
				'widget': Kekule.Widget.PopupMenu,
				'children': children
			}];
		}

		return result;
	},
	/** @private */
	createMenu: function()
	{
		var result = this.getMenu();
		if (!result)
		{
			result = new Kekule.Widget.PopupMenu(this);
			result.addClassName(CNS.DYN_CREATED);
			result.setDisplayed(false);  // hide at first;
		}
		else  // clear old first
		{
			result.clearMenuItems();
		}
		var items = this.getMenuItems() || this.getDefaultMenuItems();
		//console.log('create menu', this.getId(), items);
		items = this.prepareMenuItems(items);
		/*
		for (var i = 0, l = items.length; i < l; ++i)
		{
			var menuItem = Kekule.Widget.Utils.createFromHash(result, items[i]);
			result.appendMenuItem(menuItem);
		}
		*/
		result.createChildrenByDefs(items);
		this.setMenu(result);
		//this.updateActions();
		return result;
	},

	/** @private */
	autoDetectCaption: function()
	{
		if (this.getAutoCaption())
		{
			var obj = this.getChemObj();
			if (obj)
			{
				var info = obj && obj.getInfo();
				var srcInfo = obj && obj.getSrcInfo();
				if (info && srcInfo)
				{
					var caption = info.title || info.caption || obj.getName() || srcInfo.fileName;
					if (caption)
						this.setCaption(caption);
				}
			}
		}
	},

	// event handle functions
	/**
	 * React to a HTML event to find if it is a registered hotkey, then react to it when necessary.
	 * @param {HTMLEvent} e
	 * @returns {Bool} Returns true if a hot key is found and handled.
	 * @private
	 */
	reactHotKeys: function(e)
	{
		// react to hotkeys
		if (this.getEnableHotKey())
		{
			var coordMode = this.getCoordMode() || Kekule.CoordMode.COORD2D;
			var hotKeys = this.getViewerConfigs().getHotKeyConfigs().getHotKeys();
			var srcParams = Kekule.Widget.KeyboardUtils.getKeyParamsFromEvent(e);
			var done = false;
			var pendingOperations = [];
			for (var i = hotKeys.length - 1; i >= 0; --i)
			{
				var hotKeyInfo = hotKeys[i];
				var keyParams = Kekule.Widget.KeyboardUtils.shortcutLabelToKeyParams(hotKeys[i].key, null, false);
				keyParams.repeat = hotKeyInfo.repeat;
				if (Kekule.Widget.KeyboardUtils.matchKeyParams(srcParams, keyParams, false))  // not strict match
				{
					var actionId = hotKeyInfo.action;
					if (actionId && (!hotKeyInfo.coordMode || hotKeyInfo.coordMode === coordMode))  // hot key settings differs in 2D/3D mode
					{
						var action = this.getChildAction(actionId, true);
						if (action)
						{
							done = action.execute(this, e) || done;
						}
					}
				}
			}
			if (done)
			{
				e.stopPropagation();
				e.preventDefault();
				return true;   // already do the modification, returns a flag
			}
		}
	},
	/** @ignore */
	react_keydown: function(e)
	{
		var handled = this.tryApplySuper('react_keydown', [e]);
		if (!handled)
			return this.reactHotKeys(e);
	}
});

/**
 * Base class for sub view classes for viewer widget.
 * @class
 * @augments Kekule.ChemWidget.ChemObjDisplayerSubView
 *
 * @param {Kekule.ChemWidget.ChemObjDisplayer} displayer The parent displayer widget.
 * @param {Kekule.ChemObject} target The target object inside displayer of this sub view.
 *
 * @property {Kekule.ChemWidget.ChemObjDisplayer} displayer The parent displayer widget.
 * @property {Kekule.ChemObject} target The target object inside displayer of this sub view.
 */
Kekule.ChemWidget.ViewerSubView = Class.create(Kekule.ChemWidget.ChemObjDisplayerSubView,
/** @lends Kekule.ChemWidget.ViewerSubView# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.ViewerSubView',
	/** @private */
	initProperties: function()
	{
		this.defineProp('viewer', {
			'dataType': 'Kekule.ChemWidget.Viewer',
			'serializable': false,
			'setter': null,
			'getter': function ()
			{
				return this.getParent();
			}
		});
	},
	/**
	 * Clear all hot track items in sub view.
	 * @param {Bool} doRepaint
	 * @private
	 */
	doClearHotTrackedItems: function(doRepaint)
	{
		// do nothing here
	},
	/**
	 * Clear all selected items in viewer and all its child sub views.
	 * @param {Bool} doRepaint
	 * @private
	 */
	doClearSelectedItems: function(doRepaint)
	{
		// do nothing here
	},
});

var XEvent = Kekule.X.Event;

/**
 * Basic Interaction controller for general viewers, can do zoomIn/out job.
 * @class
 * @augments Kekule.Widget.InteractionController
 *
 * @param {Kekule.ChemWidget.Viewer} viewer Viewer of current object being installed to.
 */
Kekule.ChemWidget.ViewerBasicInteractionController = Class.create(Kekule.Widget.InteractionController,
/** @lends Kekule.ChemWidget.ViewerBasicInteractionController# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.ViewerBasicInteractionController',
	/** @constructs */
	initialize: function(/*$super, */viewer)
	{
		this.tryApplySuper('initialize', [viewer])  /* $super(viewer) */;
		this._enableMouseRotate = true;  // private
		this._transformInfo = {
			'isTransforming': false,
			//'isRotating': false,
			'lastCoord': null,
			'lastZoom': null
		};
		this._restraintCoord = null;
		this._doInteractiveTransformStepBind = this._doInteractiveTransformStep.bind(this);
		/*
		this._zoomInfo = {
			'isTransforming': false,
			'lastZoom': null
		}
		*/
	},
	/** @private */
	initProperties: function()
	{
		this.defineProp('viewer', {'dataType': 'Kekule.ChemWidget.Viewer', 'serializable': false,
			'getter': function() { return this.getWidget(); }, 'setter': function(value) { this.setWidget(value); } });
	},
	/** @private */
	getViewerRenderType: function()
	{
		return this.getViewer().getRenderType();
	},
	/** @private */
	getEnableInteraction: function()
	{
		var v = this.getViewer();
		var result = !!(v && v.getEnableDirectInteraction() && v.getChemObjLoaded());
		//console.log('enabledInteraction', result);
		return result;
	},
	/** @private */
	getEnableTouchInteraction: function()
	{
		var v = this.getViewer();
		var result = !!(v && v.getEnableDirectInteraction() && v.getEnableTouchInteraction() && v.getChemObjLoaded());
		return result;
	},
	/** @private */
	getEnableGestureManipulation: function()
	{
		var v = this.getViewer();
		var result = !!(v && v.getEnableDirectInteraction() && v.getEnableTouchInteraction() && v.getEnableGesture() && v.getChemObjLoaded());
		return result;
	},
	/** @private */
	_initTransform: function()
	{
		var viewer = this.getViewer();
		var w = viewer.getOffsetWidth();
		var h = viewer.getOffsetHeight();
		var refLength;
		var info = this._transformInfo;
		var rc = this._restraintCoord;
		if (rc)
		{
			refLength = (rc === 'x')? h: w;
			info.angleRatio = 1 / refLength * Math.PI * 2;
		}
		else
		{
			refLength = Math.min(w, h);
			info.angleRatio = 1 / refLength * Math.PI;
		}
		//info.lastRotateXYZ = {'x': 0, 'y': 0, 'z': 0};
	},
	/** @private */
	zoomViewer: function(delta, zoomCenterCoord)
	{
		var v = this.getViewer();
		if (!v || !v.getChemObj())
			return;
		if (delta > 0)
		{
			if (v.zoomIn)
				v.zoomIn(delta, zoomCenterCoord);
		}
		else if (delta < 0)
		{
			if (v.zoomOut)
				v.zoomOut(-delta, zoomCenterCoord);
		}
	},
	/** @private */
	isEventFromInteractionArea: function(e)
	{
		var target = e.getTarget();
		var interactionElem = this.getViewer().getInteractionReceiverElem();
		return (target === interactionElem) || Kekule.DomUtils.isDescendantOf(target, interactionElem);
	},
	/** @private */
	_beginInteractTransformAtCoord: function(screenX, screenY, clientX, clientY, htmlEvent, pointerId)
	{
		var viewer = this.getViewer();
		if (viewer && viewer.getChemObj())
		{
			//if (viewer.getRenderType() === Kekule.Render.RendererType.R3D)
			{
				//this._clearInteractionTransformInfo();
				var info = this._transformInfo;
				info.isTransforming = true;
				info.interactingPointerId = pointerId;
				info.lastCoord = {'x': screenX, 'y': screenY};
				info.transformInitCoord = {'x': screenX, 'y': screenY};

				if (this.getViewerRenderType() !== Kekule.Render.RendererType.R3D)
				{
					info.initBaseCoordOffset = viewer.getBaseCoordOffset() || {};
				}

				/*
				 var minLength = Math.min(viewer.getOffsetWidth(), viewer.getOffsetHeight());
				 info.angleRatio = 1 / minLength * Math.PI;
				 */
				this._restraintCoord = this._calcRestraintRotateCoord(clientX, clientY);

				this._initTransform();
				//this.getViewer().setTouchAction('none');

				this._requestInteractiveTransform(screenX, screenY);
			}
		}
	},
	/** @private */
	_beginGestureInteractTransform: function(e)
	{
		var viewer = this.getViewer();
		if (viewer && viewer.getChemObj())
		{
			//this._clearInteractionTransformInfo();
			// currently we only react to the zoom gesture
			var info = this._transformInfo;
			info.isGestureTransforming = true;
			info.isTransforming = false;  // disable normal pointer move transform when doing gesture
			info.initialGestureZoomLevel = viewer.getCurrZoom();

			this._requestInteractiveTransform();
		}
	},
	/** @private */
	_clearInteractionTransformInfo: function()
	{
		this._transformInfo.interactingPointerId = null;
		this._transformInfo.isTransforming = false;
		this._transformInfo.lastCoord = null;
		this._transformInfo.transformInitCoord = null;
		this._transformInfo.initBaseCoordOffset = null;
		this._transformInfo.isGestureTransforming = false;
		this._transformInfo.initialGestureZoomLevel = null;
	},
	/** @private */
	_endInteractTransform: function()
	{
		this._clearInteractionTransformInfo();
		//this.getViewer().setTouchAction(null);
		this._doInteractiveTransformEnd();
	},
	/** @private */
	isTransforming: function()
	{
		return this._transformInfo.isTransforming;
	},
	/** @private */
	_calcRestraintRotateCoord: function(clientX, clientY)
	{
		var viewer = this.getViewer();
		var result = null;
		// check if ned restraint rotate
		var restraintRotateEdgeSize = this._getRestraintRotate3DEdgeSize();
		if (restraintRotateEdgeSize > 0)
		{
			var elem = viewer.getDrawContextParentElem(true);
			//var rect = Kekule.HtmlElementUtils.getElemBoundingClientRect(elem, false);
			var rect = Kekule.HtmlElementUtils.getElemPageRect(elem, true);
			var x1 = clientX - rect.left;
			var y1 = clientY - rect.top;
			var x2 = rect.right - clientX; //rect.right - screenX;
			var y2 = rect.bottom - clientY; //rect.bottom - screenY;
			var minX, minY, flagX, flagY;
			if (x1 <= x2)
			{
				flagX = 1;
				minX = x1;
			}
			else
			{
				flagX = -1;
				minX = x2;
			}
			if (y1 <= y2)
			{
				flagY = 1;
				minY = y1;
			}
			else
			{
				flagY = -1;
				minY = y2;
			}
			var minOffset = Math.min(minX, minY);
			if (minOffset > restraintRotateEdgeSize)  // no restraint
				return null;
			else  // calc restraint coord
			{
				if (minY > minOffset)  // more near to left or right edge
				{
					if (flagX < 0)  // on right edge
						result = 'x';
				}
				else  // more near to top/bottom edge
				{
					if (flagY > 0)  // on top edge, rotate on z axis
						result = 'z';
					else  // on bottom edge
						result = 'y';
				}
			}
		}
		//console.log('rotate restraint coord', result);
		return result;
	},
	/** @private */
	_interactTransformAtCoord: function(screenX, screenY, pointerId)
	{
		if (pointerId !== this._transformInfo.interactingPointerId)  // move with other pointer device other than the one invoking transform, ignore
			return;
		var lastCoord = this._transformInfo.lastCoord;
		if (lastCoord)
		{
			var currCoord = {'x': screenX, 'y': screenY};
			var distance = Kekule.CoordUtils.getDistance(lastCoord, currCoord);
			if (distance < 5)  // moves too little to react
				return;
		}
		this._requestInteractiveTransform(screenX, screenY);
	},
	/** @private */
	_requestInteractiveTransform: function(screenX, screenY)
	{
		/*
		if (!this._transformInfo)
		{
			this._transformInfo = {};
		}
		*/
		this._transformInfo.interactScreenCoord = {x: screenX, y: screenY};

		if (!this._interactiveTransformStepId && Kekule.$jsRoot.requestAnimationFrame)
			this._interactiveTransformStepId = Kekule.$jsRoot.requestAnimationFrame(this._doInteractiveTransformStepBind);
	},
	/** @private */
	_doInteractiveTransformStep: function()
	{
		if (this._transformInfo)
		{
			if (this._transformInfo.isGestureTransforming)
			{
				this._doGestureInteractTransformStep();
			}
			else if (this._transformInfo.isTransforming)
			{
				var screenCoord = this._transformInfo.interactScreenCoord;
				if (this.getViewerRenderType() === Kekule.Render.RendererType.R3D)
					this.rotateByXYDistance(screenCoord.x, screenCoord.y);
				else
					this.moveByXYDistance(screenCoord.x, screenCoord.y);
			}
			this._interactiveTransformStepId = window.requestAnimationFrame(this._doInteractiveTransformStepBind);
		}
	},
	/** @private */
	_doGestureInteractTransformStep: function()
	{
		var viewer = this.getViewer();
		if (viewer && viewer.getChemObj())
		{
			var info = this._transformInfo;
			if (info.isGestureTransforming && info.pinchScale)
			{
				var gestureScale = info.pinchScale;
				if (gestureScale)
				{
					var newZoom = info.initialGestureZoomLevel * gestureScale;
					viewer.zoomTo(newZoom);
				}
			}
		}
	},
	/** @private */
	_doInteractiveTransformEnd: function()
	{
		if (this._interactiveTransformStepId)
		{
			if (Kekule.$jsRoot.cancelAnimationFrame)
				Kekule.$jsRoot.cancelAnimationFrame(this._interactiveTransformStepId);
			this._interactiveTransformStepId = null;
		}
	},
	/** @private */
	_getRestraintRotate3DEdgeSize: function()
	{
		var viewer = this.getViewer();
		if (viewer.getEnableRestraintRotation3D() && viewer.getRenderType() === Kekule.Render.RendererType.R3D)
		{
			var dim = viewer.getDimension();
			var length = Math.min(dim.width, dim.height);
			return length * (viewer.getRestraintRotation3DEdgeRatio() || 0);
		}
		else
			return 0;
	},

	/** @private */
	tryHotTrackBasicObject: function(screenCoord, boundInflation)
	{
	  return this.doTryHotTrackBasicObject(screenCoord, boundInflation);
	},
	/** @private */
	doTryHotTrackBasicObject: function(screenCoord, boundInflation)
	{
		var viewer = this.getViewer();
		if (viewer.getEnableObjectHotTrack())
		{
			var basicObject = viewer.getTopmostBasicObjectAtCoord(screenCoord, boundInflation);
			//console.log('basic object', basicObject, screenCoord);
			viewer.setHotTrackedObjects(basicObject || null);
		}
	},
	/** @private */
	trySelectBasicObject: function(screenCoord, isToggle, boundInflation)
	{
		return this.doTrySelectBasicObject(screenCoord, isToggle, boundInflation);
	},
	/** @private */
	doTrySelectBasicObject: function(screenCoord, isToggle, boundInflation)
	{
		var viewer = this.getViewer();
		if (viewer.getEnableObjectSelect())
		{
			var basicObject = viewer.getTopmostBasicObjectAtCoord(screenCoord, boundInflation);
			//console.log('basic object', basicObject, screenCoord, isToggle);
			if (isToggle && this.getViewer().getViewerConfigs().getInteractionConfigs().getEnableBasicObjectMultiSelect())
			{
				if (basicObject)
					viewer.toggleSelectingState(basicObject);
			}
			else
				viewer.select(basicObject || null);
		}
	},

	/** @private */
	needReactEvent: function(e)
	{
		return this.getEnableInteraction() && this.isEventFromInteractionArea(e);
	},
	/* @private */
	/*
	needReactToTouchEvent: function(e)
	{
		var touches = e.getTouches();
		return this.getEnableTouchInteraction() && touches && touches.length > 1;
	},
	*/
	/** @private */
	react_dblclick: function(e)
	{
		if (this.needReactEvent(e))
		{
			this.getViewer().resetDisplay();
		}
	},
	/** @private */
	react_mousewheel: function(e)
	{
		//console.log('wheel', this.needReactEvent(e));
		if (this.needReactEvent(e))
		{
			var delta = e.wheelDeltaY || e.wheelDelta;
			if (delta)
				delta /= 120;
			var centerCoord = e.getOffsetCoord();
			this.zoomViewer(delta, centerCoord);
			e.preventDefault();
			return true;
		}
	},
	/** @private */
	react_pointerdown: function(e)
	{
		if (!this.needReactEvent(e))
			return;
		if (e.getButton() === XEvent.MouseButton.LEFT)
		{
			if (e.getPointerType() !== XEvent.PointerType.TOUCH || this.getEnableTouchInteraction())
			{
				// record the coord of pointer down, and later compare it in pointerup event, to determinate whether it is a simple click
				this._pointerLeftButtonDownInitCoord = {'x': e.getScreenX(), 'y': e.getScreenY()};
				// start mouse drag rotation in 3D render mode
				//console.log('pointer down', e.pointerId);
				this._beginInteractTransformAtCoord(e.getScreenX(), e.getScreenY(), e.getClientX(), e.getClientY(), e, e.pointerId);
				//e.preventDefault();
			}
		}
	},
	/** @private */
	react_pointerhold: function(e)
	{
		if (!this.needReactEvent(e))
			return;
		if (e.getPointerType() === XEvent.PointerType.TOUCH && e.getButton() === XEvent.MouseButton.LEFT)
		{
			//this.getViewer().setEnableTouchInteraction(!this.getViewer().getEnableTouchInteraction());
			/*
			if (!this._transformInfo.isTransforming)
			{
				// start mouse drag rotation in 3D render mode
				this._beginInteractTransformAtCoord(e.getScreenX(), e.getScreenY(), e.getClientX(), e.getClientY());
				e.preventDefault();
			}
      */
		}
	},
	/** @private */
	/*
	react_touchstart: function(e)
	{
		if (!this.needReactEvent(e) || !this.needReactToTouchEvent(e))
			return;
		var touchInfo = e.getTouches()[0];
		var notUnset = Kekule.ObjUtils.notUnset;
		if (touchInfo && notUnset(touchInfo.screenX) && notUnset(touchInfo.screenY))
		{
			this._beginInteractTransformAtCoord(touchInfo.screenX, touchInfo.screenY, touchInfo.clientX, touchInfo.clientY);
			e.stopPropagation();
			e.preventDefault();
		}
	},
	*/
	/* @private */
	// TODO: PointerLeave event trigger seems has bug in Chrome, ocurrs at some wrong occassion?

	react_pointerleave: function(e)
	{
		//this._transformInfo.isTransforming = false;
		if (this._transformInfo.isTransforming)
			this._endInteractTransform();
		this.getViewer().clearHotTrackedItems(null, true);
	},

	/** @private */
	/*
	react_touchleave: function(e)
	{
		this._transformInfo.isTransforming = false;
	},
	*/
	/** @private */
	/*
	react_touchcancel: function(e)
	{
		this._transformInfo.isTransforming = false;
	},
	*/
	/** @private */
	react_pointerup: function(e)
	{
		if (e.getButton() === XEvent.MouseButton.LEFT)
		{
			//this._transformInfo.isTransforming = false;
			if (this._transformInfo.isTransforming)
				this._endInteractTransform();
			if (this._pointerLeftButtonDownInitCoord)
			{
				var pointerLeftButtonUpCoord = {'x': e.getScreenX(), 'y': e.getScreenY()};
				var distance = Kekule.CoordUtils.getDistance(pointerLeftButtonUpCoord, this._pointerLeftButtonDownInitCoord);
				if (distance < 3)  // TODO: currently the allowed move distance is fixed
				{
					// no move between down-up, should be a click event, and we may select object in viewer now
					//if (this.getViewer().getViewerConfigs().getInteractionConfigs().getEnableBasicObjectSelect())
					{
						var screenCoord = this._getEventMouseCoord(e);
						var isToggle = e.getShiftKey() || e.getCtrlKey();
						this.trySelectBasicObject(screenCoord, isToggle, this.getViewer().getViewerConfigs().getInteractionConfigs().getObjBoundTrackInflation());
					}
				}
				this._pointerLeftButtonDownInitCoord = null;
			}
			//e.preventDefault();
		}
	},
	/** @private */
	/*
	react_touchend: function(e)
	{
		this._transformInfo.isTransforming = false;
	},
	*/
	/** @private */
	react_pointermove: function(e)
	{
		if (!this.needReactEvent(e))
			return;

		/*
		if (this.getViewerRenderType() === Kekule.Render.RendererType.R3D)
			this.rotateByXYDistance(e.getScreenX(), e.getScreenY());
		else
			this.moveByXYDistance(e.getScreenX(), e.getScreenY());
		*/
		if (this._transformInfo.isTransforming)
		{
			//console.log('pointer move', e.pointerId);
			this._interactTransformAtCoord(e.getScreenX(), e.getScreenY(), e.pointerId);
			try
			{
				e.preventDefault();
			}
			catch(e)
			{

			}
		}
		//else if (this.getViewer().getViewerConfigs().getInteractionConfigs().getEnableBasicObjectHotTrack())
		{
			var screenCoord = this._getEventMouseCoord(e);
			this.tryHotTrackBasicObject(screenCoord, this.getViewer().getViewerConfigs().getInteractionConfigs().getObjBoundTrackInflation());
		}
	},
	/** @private */
	/*
	react_touchmove: function(e)
	{
		if (!this.needReactEvent(e) || !this.needReactToTouchEvent(e))
			return;
		//console.log(e.touches);
		var touchInfo = e.getTouches()[0];
		var notUnset = Kekule.ObjUtils.notUnset;
		if (touchInfo && notUnset(touchInfo.screenX) && notUnset(touchInfo.screenY))
		{
			this._interactTransformAtCoord(touchInfo.screenX, touchInfo.screenY);
			e.stopPropagation();
			e.preventDefault();
		}
	},
	*/
	/** @private */
	moveByXYDistance: function(currX, currY)
	{
		var info = this._transformInfo;
		if (info && info.isTransforming && (!info.calculating))
		{
			var viewer = this.getViewer();
			if (viewer.getRenderType() === Kekule.Render.RendererType.R3D)
				return;
			info.calculating = true;
			try
			{
				var currCoord = {'x': currX, 'y': currY};
				if (info.lastCoord && Kekule.CoordUtils.isEqual(currCoord, info.lastCoord))  // coord has no change bypass
				{
					// do nothing
				}
				else
				{
					//var delta = Kekule.CoordUtils.substract(currCoord, info.lastCoord);
					//var baseCoordOffset = viewer.getBaseCoordOffset() || {};
					var delta = Kekule.CoordUtils.substract(currCoord, info.transformInitCoord);
					var baseCoordOffset = info.initBaseCoordOffset;
					baseCoordOffset = Kekule.CoordUtils.add(baseCoordOffset, delta);
					viewer.setBaseCoordOffset(baseCoordOffset);
					info.lastCoord = currCoord;
				}
			}
			finally
			{
				info.calculating = false;
			}
		}
	},
	/** @private */
	rotateByXYDistance: function(currX, currY)
	{
		var info = this._transformInfo;
		if (info && info.isTransforming && (!info.calculating))
		{
			var viewer = this.getViewer();
			if (viewer.getRenderType() !== Kekule.Render.RendererType.R3D)
				return;
			info.calculating = true;
			try
			{
				var currCoord = {'x': currX, 'y': currY};
				if (info.lastCoord && Kekule.CoordUtils.isEqual(currCoord, info.lastCoord))  // coord has no change bypass
				{
					// do nothing
				}
				else
				{
					var delta = Kekule.CoordUtils.substract(currCoord, info.lastCoord);
					delta.y = -delta.y;

					var dis, rotateAngle, axisVector;
					if (this._restraintCoord)  // restraint rotation on one axis
					{
						var rc = this._restraintCoord;

						if (rc === 'x')
						{
							dis = delta.y;
							axisVector = {'x': 1, 'y': 0, 'z': 0};
						}
						else
						{
							dis = delta.x;
							if (rc === 'y')
								axisVector = {'x': 0, 'y': -1, 'z': 0};
							else
								axisVector = {'x': 0, 'y': 0, 'z': 1};
						}
						rotateAngle = -dis * info.angleRatio;
					}
					else  // normal rotation
					{
						dis = Kekule.CoordUtils.getDistance({'x': 0, 'y': 0}, delta);
						rotateAngle = dis * info.angleRatio;
						axisVector = {'x': -delta.y, 'y': delta.x, 'z': 0};
					}

					viewer.rotate3DByAxis(rotateAngle, axisVector);

					info.lastCoord = currCoord;
					info.calculating = false;
				}
			}
			catch(e) {}   // fix IE finally bug
			finally
			{
				info.calculating = false;
			}
		}
	},

	/* @private */
	/*
	react_transformstart: function(e)
	{
		if (!this.getEnableTouchInteraction() || !this.needReactEvent(e))
			return;
		var viewer = this.getViewer();
		if (viewer)
		{
			var info = this._transformInfo;
			info.isTransforming = true;
			info.lastZoom = viewer.getCurrZoom();
			info.lastCoord = {'x': 0, 'y': 0};
			this._initTransform();
		}
	},
	*/
	/* @private */
	/*
	react_transformend: function(e)
	{
		if (!this.getEnableTouchInteraction() || !this.needReactEvent(e))
			return;
		this._transformInfo.isTransforming = false;
	},
	*/
	/* @private */
	/*
	react_transform: function(e)
	{
		if (!this.getEnableTouchInteraction() || !this.needReactEvent(e))
			return;
		var viewer = this.getViewer();
		var info = this._transformInfo;
		if (viewer && info.isTransforming && (!info.calculating))
		{
			try
			{
				// TODO: the event detail data is binded to hammer.js, may need to change later
				var gesture = e.gesture;
				if (gesture)
				{
					// zoom

					var scale = e.gesture.scale;
					viewer.zoomTo((info.lastZoom || 1) * scale, true);  // suspend render, as we will rotate further

					//console.log('new scale', scale);


					// rotate
					var dx = gesture.deltaX;
					var dy = gesture.deltaY;

					//console.log(dx, dy, info.lastCoord.x, info.lastCoord.y);
					this.rotateByXYDistance(dx, dy);

					e.gesture.preventDefault();
					e.gesture.stopPropagation();
				}
			}
			finally
			{
				//info.calculating = false;
			}
		}
		//console.log(e, e.gesture);
	},
  */

	/** @private */
	doTestMouseCursor: function(coord, e)
	{
		var result = '';
		var info = this._transformInfo;
		if (info.isTransforming)
			result = 'move';  //'grabbing';
		return result;
	},

	/** @ignore */
	react_pinchstart: function(e)
	{
		//console.log('pinch start');
		if (this.getEnableGestureManipulation())
			this._beginGestureInteractTransform(e);
	},
	/** @ignore */
	react_pinchmove: function(e)
	{
		if (this.getEnableGestureManipulation())
		{
			var info = this._transformInfo;
			if (info.isGestureTransforming)
			{
				//console.log('pinch move', e.scale);
				info.pinchScale = e.scale;
			}
		}
	},
	/** @ignore */
	react_pinchend: function(e)
	{
		if (this.getEnableGestureManipulation() && this._transformInfo.isGestureTransforming)
			this._endInteractTransform();
	},
	/** @ignore */
	react_pinchcancel: function(e)
	{
		if (this.getEnableGestureManipulation() && this._transformInfo.isGestureTransforming)
			this._endGestureInteractTransform();
	}
});

/**
 * An 2D viewer widget for chem objects, actually a specialization of {@link Kekule.ChemWidget.Viewer}.
 * @class
 * @augments Kekule.ChemWidget.Viewer
 *
 * @param {Variant} parentOrElementOrDocument
 * @param {Kekule.ChemObject} chemObj
 */
Kekule.ChemWidget.Viewer2D = Class.create(Kekule.ChemWidget.Viewer,
/** @lends Kekule.ChemWidget.Viewer2D# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.Viewer2D',
	/** @construct */
	initialize: function(/*$super, */parentOrElementOrDocument, chemObj)
	{
		this.tryApplySuper('initialize', [parentOrElementOrDocument, chemObj, Kekule.Render.RendererType.R2D])  /* $super(parentOrElementOrDocument, chemObj, Kekule.Render.RendererType.R2D) */;
	},
	/** @ignore */
	getAllowRenderTypeChange: function()
	{
		return false;
	}
});

/**
 * An 3D viewer widget for chem objects, actually a specialization of {@link Kekule.ChemWidget.Viewer}.
 * @class
 * @augments Kekule.ChemWidget.Viewer
 *
 * @param {Variant} parentOrElementOrDocument
 * @param {Kekule.ChemObject} chemObj
 */
Kekule.ChemWidget.Viewer3D = Class.create(Kekule.ChemWidget.Viewer,
/** @lends Kekule.ChemWidget.Viewer3D# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.Viewer3D',
	/** @construct */
	initialize: function(/*$super, */parentOrElementOrDocument, chemObj)
	{
		this.tryApplySuper('initialize', [parentOrElementOrDocument, chemObj, Kekule.Render.RendererType.R3D])  /* $super(parentOrElementOrDocument, chemObj, Kekule.Render.RendererType.R3D) */;
	},
	/** @ignore */
	getAllowRenderTypeChange: function()
	{
		return false;
	}
});

// register predefined settings of viewer
var SM = Kekule.ObjPropSettingManager;
SM.register('Kekule.ChemWidget.Viewer.fullFunc', {  // viewer with all functions
	enableToolbar: true,
	enableDirectInteraction: true,
	enableTouchInteraction: true,
	enableEdit: true,
	toolButtons: null   // create all default tool buttons
});
SM.register('Kekule.ChemWidget.Viewer.basic', {  // viewer with basic function, suitable for embedded chem object with limited size
	enableToolbar: true,
	enableDirectInteraction: true,
	enableTouchInteraction: false,
	toolButtons: [BNS.saveData, BNS.molDisplayType, BNS.zoomIn, BNS.zoomOut],
	menuItems: [BNS.saveData, '-', BNS.molDisplayType, BNS.zoomIn, BNS.zoomOut]
});
SM.register('Kekule.ChemWidget.Viewer.mini', {  // viewer with only one menu button
	enableToolbar: true,
	enableDirectInteraction: true,
	enableTouchInteraction: false,
	toolButtons: [BNS.menu],
	menuItems: [BNS.saveData, '-', BNS.molDisplayType, BNS.zoomIn, BNS.zoomOut]
});
SM.register('Kekule.ChemWidget.Viewer.static', {  // viewer with no interaction ability, suitable for static embedded chem object
	enableToolbar: false,
	enableDirectInteraction: false,
	enableTouchInteraction: false,
	toolButtons: [],
	menuItems: []
});
SM.register('Kekule.ChemWidget.Viewer.editOnly', {  // viewer can be editted
	enableToolbar: true,
	enableEdit: true,
	toolButtons: [BNS.openEditor],
	menuItems: [BNS.openEditor]
});

/**
 * A special class to give a setting facade for Chem Viewer.
 * Do not use this class alone.
 * @class
 * @augments Kekule.ChemWidget.ChemObjDisplayer.Settings
 * @ignore
 */
Kekule.ChemWidget.Viewer.Settings = Class.create(Kekule.ChemWidget.ChemObjDisplayer.Settings,
/** @lends Kekule.ChemWidget.Viewer.Settings# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.Viewer.Settings',
	/** @private */
	initProperties: function()
	{
		this.defineDelegatedProps([
			'enableDirectInteraction', 'enableTouchInteraction',
			'enableToolbar', 'toolbarPos', 'toolbarMarginHorizontal', 'toolbarMarginVertical',
			'enableEdit', 'modalEdit'
		]);
	},
	/** @private */
	getViewer: function()
	{
		return this.getWidget();
	}
});

/**
 * Config class of viewer (class {@link Kekule.ChemWidget.Viewer}).
 * @class
 * @augments Kekule.ChemWidget.ChemObjDisplayerConfigs
 *
 * @property {Kekule.EditorConfigs.UiMarkerConfigs} uiMarkerConfigs
 * @property {Kekule.EditorConfigs.InteractionConfigs} interactionConfigs
 * @property {Kekule.Editor.StructureConfigs} structureConfigs
 */
Kekule.ChemWidget.ViewerConfigs = Class.create(Kekule.ChemWidget.ChemObjDisplayerConfigs,
/** @lends Kekule.ChemWidget.ViewerConfigs# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.ViewerConfigs',
	/** @private */
	initProperties: function()
	{
		this.addConfigProp('interactionConfigs', 'Kekule.ChemWidget.ViewerInteractionConfigs');
		this.addConfigProp('hotKeyConfigs', 'Kekule.ChemWidget.ViewerHotKeyConfigs');
		this.addConfigProp('uiMarkerConfigs', 'Kekule.ChemWidget.ViewerUiMarkerConfigs');
	},
	/** @private */
	initPropValues: function()
	{
		this.tryApplySuper('initPropValues');
		this.setPropStoreFieldValue('interactionConfigs', new Kekule.ChemWidget.ViewerInteractionConfigs());
		this.setPropStoreFieldValue('hotKeyConfigs', new Kekule.ChemWidget.ViewerHotKeyConfigs());
		this.setPropStoreFieldValue('uiMarkerConfigs', new Kekule.ChemWidget.ViewerUiMarkerConfigs());
	}
});

/**
 * Configs of interaction with viewer.
 * @class
 * @augments Kekule.AbstractConfigs
 *
 * @property {Bool} enableBasicObjectHotTrack Whether show the hot track marker when the pointer moves over a basic object (e.g., atom, bond) in viewer.
 * @property {Bool} enableBasicObjectSelect Whether show the selection marker when the pointer click over a basic object (e.g., atom, bond) in viewer.
 * @property {Bool} enableBasicObjectMultiSelect Whether multiple selection is enabled in viewer.
 */
Kekule.ChemWidget.ViewerInteractionConfigs = Class.create(Kekule.AbstractConfigs,
/** @lends Kekule.ChemWidget.ViewerInteractionConfigs# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.ViewerInteractionConfigs',
	/** @private */
	initProperties: function ()
	{
		this.addBoolConfigProp('enableBasicObjectHotTrack', false);
		this.addHashConfigProp('enableBasicObjectSelect', false);
		this.addHashConfigProp('enableBasicObjectMultiSelect', false);
		this.addBoolConfigProp('enableTrackOnNearest', true);

		this.addIntConfigProp('objBoundTrackInflation', 5);
	}
});

/**
 * Configs of hot key settings of viewer.
 * @class
 * @augments Kekule.AbstractConfigs
 *
 * @property {Array} hotKeys Hot keys to do quick action.
 *   Each item in array is a hash {key: string, action: string},
 */
Kekule.ChemWidget.ViewerHotKeyConfigs = Class.create(Kekule.AbstractConfigs,
/** @lends Kekule.ChemWidget.ViewerHotKeyConfigs# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.ViewerHotKeyConfigs',
	/** @private */
	initProperties: function()
	{
		this.defineProp('hotKeys', {'dataType': DataType.ARRAY});
	},
	/** @private */
	initPropValues: function(/*$super*/)
	{
		var CWN = Kekule.ChemWidget.ComponentWidgetNames;
		// debug, fetch default ones
		this.tryApplySuper('initPropValues');
		this.setHotKeys([
			{'key': 'Ctrl+C', 'action': CWN.copy},
			{'key': '+', 'action': CWN.zoomIn},
			{'key': '-', 'action': CWN.zoomOut},
			{'key': 'Space', 'action': CWN.reset},
			{'key': 'R', 'action': CWN.rotateLeft, repeat: null, 'coordMode': 2},
			{'key': 'Shift-R', 'action': CWN.rotateRight, repeat: null, 'coordMode': 2},
			{'key': 'X', 'action': CWN.rotateX, repeat: null, 'coordMode': 3},
			{'key': 'Shift+X', 'action': CWN.rotateX, repeat: null, 'coordMode': 3},
			{'key': 'Y', 'action': CWN.rotateY, repeat: null, 'coordMode': 3},
			{'key': 'Shift+Y', 'action': CWN.rotateY, repeat: null, 'coordMode': 3},
			{'key': 'Z', 'action': CWN.rotateZ, repeat: null, 'coordMode': 3},
			{'key': 'Shift+Z', 'action': CWN.rotateZ, repeat: null, 'coordMode': 3},
			{'key': 'H', 'action': CWN.molHideHydrogens, 'coordMode': 3},
			{'key': '1', 'action': CWN.molDisplayTypeSkeletal, 'coordMode': 2},
			{'key': '1', 'action': CWN.molDisplayTypeCondensed, 'coordMode': 2},
			{'key': '1', 'action': CWN.molDisplayTypeWire, 'coordMode': 3},
			{'key': '2', 'action': CWN.molDisplayTypeSticks, 'coordMode': 3},
			{'key': '3', 'action': CWN.molDisplayTypeBallStick, 'coordMode': 3},
			{'key': '4', 'action': CWN.molDisplayTypeSpaceFill, 'coordMode': 3}
		]);
	}
});

/**
 * Config of the UI markers in viewer widget.
 * @class
 * @augments Kekule.AbstractConfigs
 *
 * @property {Number} spectrumDataPointSelectInflation
 *
 * @property {Hash} hotTrackMarkerStyles
 * @property {Hash} selectionMarkerStyles
 */
Kekule.ChemWidget.ViewerUiMarkerConfigs = Class.create(Kekule.AbstractConfigs,
/** @lends Kekule.ChemWidget.ViewerUiMarkerConfigs# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.ChemObjDisplayerSpectrumViewConfigs',
	/** @private */
	initProperties: function()
	{
		this.addHashConfigProp('hotTrackMarkerStyles', undefined);
		this.addHashConfigProp('selectionMarkerStyles', undefined);
		this.addHashConfigProp('hotTrackedObjectStyles', undefined);
		this.addHashConfigProp('selectedObjectStyles', undefined);
	},
	/** @ignore */
	initPropValues: function()
	{
		this.tryApplySuper('initPropValues');
		this.setHotTrackMarkerStyles({
			//'color': '#0000FF',
			'strokeColor': '#0000FF',
			'opacity': 0.2
		});
		this.setSelectionMarkerStyles({
			//'color': '#0000FF',
			'opacity': 0.35,
			'strokeColor': '#0000FF',
			//'fillColor': '#0000FF',
			'strokeWidth': 2
		});
		/*
		this.setHotTrackedObjectStyles({
			'color': '#FF0000',
			'nodeDisplayMode': Kekule.Render.NodeLabelDisplayMode.SHOWN
		});
		this.setSelectedObjectStyles({
			'color': '#FF0000',
			'nodeDisplayMode': Kekule.Render.NodeLabelDisplayMode.SHOWN
		});
		*/
	}
});

/**
 * Base class for actions for chem viewer.
 * @class
 * @augments Kekule.ChemWidget.ActionOnDisplayer
 *
 * @param {Kekule.ChemWidget.Viewer} viewer Target viewer widget.
 * @param {String} caption
 * @param {String} hint
 */
Kekule.ChemWidget.ActionOnViewer = Class.create(Kekule.ChemWidget.ActionOnDisplayer,
/** @lends Kekule.ChemWidget.ActionOnViewer# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.ActionOnViewer',
	/** @constructs */
	initialize: function(/*$super, */viewer, caption, hint)
	{
		this.tryApplySuper('initialize', [viewer, caption, hint])  /* $super(viewer, caption, hint) */;
	},
	/** @private */
	doUpdate: function()
	{
		var displayer = this.getDisplayer();
		this.setEnabled(displayer && displayer.getChemObj() && displayer.getChemObjLoaded() && displayer.getEnabled());
	},
	/**
	 * Returns target chem viewer.
	 * @returns {Kekule.ChemWidget.Viewer}
	 */
	getViewer: function()
	{
		var result = this.getDisplayer();
		return (result instanceof Kekule.ChemWidget.Viewer)? result: null;
	}
});

/**
 * Base action for make rotation in viewer.
 * @class
 * @augments Kekule.ChemWidget.ActionOnViewer
 *
 * @property {Float} delta The rotation angle.
 */
Kekule.ChemWidget.ActionViewerRotateBase = Class.create(Kekule.ChemWidget.ActionOnViewer,
/** @lends Kekule.ChemWidget.ActionViewerRotateBase# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.ActionViewerRotateBase',
	/** @constructs */
	initialize: function(/*$super, */viewer, caption, hint)
	{
		this.tryApplySuper('initialize', [viewer, caption, hint])  /* $super(viewer, caption, hint) */;
		this.setDelta(2 * Math.PI / 180);  // TODO: this default value should be configurable
	},
	/** @private */
	initProperties: function()
	{
		this.defineProp('delta', {'dataType': DataType.FLOAT});
	},
	/** @private */
	isShiftModified: function(htmlEvent)
	{
		return htmlEvent && htmlEvent.getShiftKey();
	}
});
/**
 * Base action for make rotation in 2D viewer.
 * @class
 * @augments Kekule.ChemWidget.ActionViewerRotateBase
 *
 * @property {Float} delta The rotation angle.
 */
Kekule.ChemWidget.ActionViewerRotateBase2D = Class.create(Kekule.ChemWidget.ActionViewerRotateBase,
/** @lends Kekule.ChemWidget.ActionViewerRotateBase2D# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.ActionViewerRotateBase2D',
	/** @private */
	doUpdate: function(/*$super*/)
	{
		this.tryApplySuper('doUpdate')  /* $super() */;
		var viewer = this.getViewer();
		var flag = viewer && (viewer.getRenderType() === Kekule.Render.RendererType.R2D);
		this.setDisplayed(/*this.getDisplayed() &&*/ flag).setEnabled(this.getEnabled() && flag);
	}
});
/**
 * Base action for make rotation in 3D viewer.
 * @class
 * @augments Kekule.ChemWidget.ActionViewerRotateBase
 *
 * @property {Float} delta The rotation angle.
 */
Kekule.ChemWidget.ActionViewerRotateBase3D = Class.create(Kekule.ChemWidget.ActionViewerRotateBase,
/** @lends Kekule.ChemWidget.ActionViewerRotateBase3D# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.ActionViewerRotateBase3D',
	/** @private */
	doUpdate: function(/*$super*/)
	{
		this.tryApplySuper('doUpdate')  /* $super() */;
		var viewer = this.getViewer();
		var flag = viewer && (viewer.getRenderType() === Kekule.Render.RendererType.R3D);
		this.setDisplayed(/*this.getDisplayed() &&*/ flag).setEnabled(this.getEnabled() && flag);
	}
});

/**
 * Action for do anticlockwise rotation in 2D mode.
 * @class
 * @augments Kekule.ChemWidget.ActionViewerRotateBase2D
 */
Kekule.ChemWidget.ActionViewerRotateLeft = Class.create(Kekule.ChemWidget.ActionViewerRotateBase2D,
/** @lends Kekule.ChemWidget.ActionViewerRotateLeft# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.ActionViewerRotateLeft',
	/** @private */
	HTML_CLASSNAME: CCNS.ACTION_ROTATE_LEFT,
	/** @constructs */
	initialize: function(/*$super, */viewer)
	{
		this.tryApplySuper('initialize', [viewer, /*CWT.CAPTION_ROTATELEFT, CWT.HINT_ROTATELEFT*/Kekule.$L('ChemWidgetTexts.CAPTION_ROTATELEFT'), Kekule.$L('ChemWidgetTexts.HINT_ROTATELEFT')])  /* $super(viewer, \*CWT.CAPTION_ROTATELEFT, CWT.HINT_ROTATELEFT*\Kekule.$L('ChemWidgetTexts.CAPTION_ROTATELEFT'), Kekule.$L('ChemWidgetTexts.HINT_ROTATELEFT')) */;
	},
	/** @private */
	doExecute: function(target, htmlEvent)
	{
		var delta = this.getDelta();
		this.getViewer().rotate2DBy(delta);
	}
});
/**
 * Action for do clockwise rotation in 2D mode.
 * @class
 * @augments Kekule.ChemWidget.ActionViewerRotateBase2D
 */
Kekule.ChemWidget.ActionViewerRotateRight = Class.create(Kekule.ChemWidget.ActionViewerRotateBase2D,
/** @lends Kekule.ChemWidget.ActionViewerRotateRight# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.ActionViewerRotateRight',
	/** @private */
	HTML_CLASSNAME: CCNS.ACTION_ROTATE_RIGHT,
	/** @constructs */
	initialize: function(/*$super, */viewer)
	{
		this.tryApplySuper('initialize', [viewer, /*CWT.CAPTION_ROTATERIGHT, CWT.HINT_ROTATERIGHT*/Kekule.$L('ChemWidgetTexts.CAPTION_ROTATERIGHT'), Kekule.$L('ChemWidgetTexts.HINT_ROTATERIGHT')])  /* $super(viewer, \*CWT.CAPTION_ROTATERIGHT, CWT.HINT_ROTATERIGHT*\Kekule.$L('ChemWidgetTexts.CAPTION_ROTATERIGHT'), Kekule.$L('ChemWidgetTexts.HINT_ROTATERIGHT')) */;
	},
	/** @private */
	doExecute: function(target, htmlEvent)
	{
		var delta = -this.getDelta();
		this.getViewer().rotate2DBy(delta);
	}
});
/**
 * Action for do rotation around X axis in 3D mode.
 * @class
 * @augments Kekule.ChemWidget.ActionViewerRotateBase3D
 */
Kekule.ChemWidget.ActionViewerRotateX = Class.create(Kekule.ChemWidget.ActionViewerRotateBase3D,
/** @lends Kekule.ChemWidget.ActionViewerRotateX# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.ActionViewerRotateX',
	/** @private */
	HTML_CLASSNAME: CCNS.ACTION_ROTATE_X,
	/** @constructs */
	initialize: function(/*$super, */viewer)
	{
		this.tryApplySuper('initialize', [viewer, /*CWT.CAPTION_ROTATEX, CWT.HINT_ROTATEX*/Kekule.$L('ChemWidgetTexts.CAPTION_ROTATEX'), Kekule.$L('ChemWidgetTexts.HINT_ROTATEX')])  /* $super(viewer, \*CWT.CAPTION_ROTATEX, CWT.HINT_ROTATEX*\Kekule.$L('ChemWidgetTexts.CAPTION_ROTATEX'), Kekule.$L('ChemWidgetTexts.HINT_ROTATEX')) */;
	},
	/** @private */
	doExecute: function(target, htmlEvent)
	{
		var rev = this.isShiftModified(htmlEvent);
		var delta = -this.getDelta();
		if (rev)
			delta = -delta;
		this.getViewer().rotate3DBy(delta, 0, 0);
	}
});
/**
 * Action for do rotation around Y axis in 3D mode.
 * @class
 * @augments Kekule.ChemWidget.ActionViewerRotateBase3D
 */
Kekule.ChemWidget.ActionViewerRotateY = Class.create(Kekule.ChemWidget.ActionViewerRotateBase3D,
/** @lends Kekule.ChemWidget.ActionViewerRotateY# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.ActionViewerRotateY',
	/** @private */
	HTML_CLASSNAME: CCNS.ACTION_ROTATE_Y,
	/** @constructs */
	initialize: function(/*$super, */viewer)
	{
		this.tryApplySuper('initialize', [viewer, /*CWT.CAPTION_ROTATEY, CWT.HINT_ROTATEY*/Kekule.$L('ChemWidgetTexts.CAPTION_ROTATEY'), Kekule.$L('ChemWidgetTexts.HINT_ROTATEY')])  /* $super(viewer, \*CWT.CAPTION_ROTATEY, CWT.HINT_ROTATEY*\Kekule.$L('ChemWidgetTexts.CAPTION_ROTATEY'), Kekule.$L('ChemWidgetTexts.HINT_ROTATEY')) */;
	},
	/** @private */
	doExecute: function(target, htmlEvent)
	{
		var rev = this.isShiftModified(htmlEvent);
		var delta = -this.getDelta();
		if (rev)
			delta = -delta;
		this.getViewer().rotate3DBy(0, delta, 0);
	}
});
/**
 * Action for do rotation around Z axis in 3D mode.
 * @class
 * @augments Kekule.ChemWidget.ActionViewerRotateBase3D
 */
Kekule.ChemWidget.ActionViewerRotateZ = Class.create(Kekule.ChemWidget.ActionViewerRotateBase3D,
/** @lends Kekule.ChemWidget.ActionViewerRotateZ# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.ActionViewerRotateZ',
	/** @private */
	HTML_CLASSNAME: CCNS.ACTION_ROTATE_Z,
	/** @constructs */
	initialize: function(/*$super, */viewer)
	{
		this.tryApplySuper('initialize', [viewer, /*CWT.CAPTION_ROTATEZ, CWT.HINT_ROTATEZ*/Kekule.$L('ChemWidgetTexts.CAPTION_ROTATEZ'), Kekule.$L('ChemWidgetTexts.HINT_ROTATEZ')])  /* $super(viewer, \*CWT.CAPTION_ROTATEZ, CWT.HINT_ROTATEZ*\Kekule.$L('ChemWidgetTexts.CAPTION_ROTATEZ'), Kekule.$L('ChemWidgetTexts.HINT_ROTATEZ')) */;
	},
	/** @private */
	doExecute: function(target, htmlEvent)
	{
		var rev = this.isShiftModified(htmlEvent);
		var delta = -this.getDelta();
		if (rev)
			delta = -delta;
		this.getViewer().rotate3DBy(0, 0, delta);
	}
});

/**
 * Action used for molecule display type stub button of compact button set in viewer.
 * @class
 * @augments Kekule.ChemWidget.ActionViewer
 */
Kekule.ChemWidget.ActionViewerChangeMolDisplayTypeStub = Class.create(Kekule.ChemWidget.ActionOnDisplayer,
/** @lends Kekule.ChemWidget.ActionViewerChangeMolDisplayTypeStub# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.ActionViewerChangeMolDisplayTypeStub',
	/** @constructs */
	initialize: function(/*$super, */viewer)
	{
		this.tryApplySuper('initialize', [viewer, /*CWT.CAPTION_MOL_DISPLAY_TYPE, CWT.HINT_MOL_DISPLAY_TYPE*/Kekule.$L('ChemWidgetTexts.CAPTION_MOL_DISPLAY_TYPE'), Kekule.$L('ChemWidgetTexts.HINT_MOL_DISPLAY_TYPE')])  /* $super(viewer, \*CWT.CAPTION_MOL_DISPLAY_TYPE, CWT.HINT_MOL_DISPLAY_TYPE*\Kekule.$L('ChemWidgetTexts.CAPTION_MOL_DISPLAY_TYPE'), Kekule.$L('ChemWidgetTexts.HINT_MOL_DISPLAY_TYPE')) */;
	},
	/** @private */
	doExecute: function(target)
	{
		// do nothing
	},
	/** @ignore */
	doUpdate: function()
	{
		this.tryApplySuper('doUpdate');
		var viewer = this.getDisplayer();
		this.setEnabled(this.getEnabled() && this._isMolInViewer(viewer));
	},
	/** @private */
	_isMolInViewer: function(viewer)
	{
		if (viewer)
		{
			var root = viewer.getChemObj();
			if (!root)
				return false;
			else if (root instanceof Kekule.ChemStructureNode)
				return true;
			else
			{
				var mols = Kekule.ChemStructureUtils.getAllStructFragments(root);
				return !!(mols && mols.length);
			}
		}
		return false;
	}
});

/**
 * Action to edit object in viewer.
 * @class
 * @augments Kekule.ChemWidget.ActionOnViewer
 *
 * @property {Float} delta The rotation angle.
 */
Kekule.ChemWidget.ActionViewerEdit = Class.create(Kekule.ChemWidget.ActionOnViewer,
/** @lends Kekule.ChemWidget.ActionViewerEdit# */
{
	/** @private */
	CLASS_NAME: 'Kekule.ChemWidget.ActionViewerEdit',
	/** @private */
	HTML_CLASSNAME: CCNS.ACTION_VIEWER_EDIT,
	/** @constructs */
	initialize: function(/*$super, */viewer)
	{
		this.tryApplySuper('initialize', [viewer, /*CWT.CAPTION_OPENEDITOR, CWT.HINT_OPENEDITOR*/Kekule.$L('ChemWidgetTexts.CAPTION_OPENEDITOR'), Kekule.$L('ChemWidgetTexts.HINT_OPENEDITOR')])  /* $super(viewer, \*CWT.CAPTION_OPENEDITOR, CWT.HINT_OPENEDITOR*\Kekule.$L('ChemWidgetTexts.CAPTION_OPENEDITOR'), Kekule.$L('ChemWidgetTexts.HINT_OPENEDITOR')) */;
	},
	/** @private */
	doUpdate: function(/*$super*/)
	{
		this.tryApplySuper('doUpdate')  /* $super() */;
		var viewer = this.getViewer();
		//this.setEnabled(this.getEnabled() && viewer.getChemObj() && viewer.getEnableEdit());
		//this.setEnabled(this.getEnabled() && viewer.getAllowEditing());
		this.setEnabled(viewer && viewer.getAllowEditing() && viewer.getEnabled());
		this.setDisplayed(viewer && viewer.getEnableEdit());
	},
	/** @private */
	doExecute: function(target)
	{
		var viewer = this.getViewer();
		viewer.openEditor(target);
	}
});

/** @private */
Kekule.ChemWidget.Viewer.rotate2DActionClasses = [
	CW.ActionViewerRotateLeft,
	CW.ActionViewerRotateRight
];
/** @private */
Kekule.ChemWidget.Viewer.rotate3DActionClasses = [
	CW.ActionViewerRotateX,
	CW.ActionViewerRotateY,
	CW.ActionViewerRotateZ
];
/** @private */
Kekule.ChemWidget.Viewer.molDisplayType2DActionClasses = [
	CW.ActionDisplayerChangeMolDisplayTypeSkeletal,
	CW.ActionDisplayerChangeMolDisplayTypeCondensed
];
/** @private */
Kekule.ChemWidget.Viewer.molDisplayType3DActionClasses = [
	CW.ActionDisplayerChangeMolDisplayTypeWire,
	CW.ActionDisplayerChangeMolDisplayTypeSticks,
	CW.ActionDisplayerChangeMolDisplayTypeBallStick,
	CW.ActionDisplayerChangeMolDisplayTypeSpaceFill
];

// register actions to viewer widget
Kekule._registerAfterLoadSysProc(function(){
	var AM = Kekule.ActionManager;
	var CW = Kekule.ChemWidget;
	var widgetClass = Kekule.ChemWidget.Viewer;
	var reg = AM.registerNamedActionClass;

	reg(BNS.loadFile, CW.ActionDisplayerLoadFile, widgetClass);
	reg(BNS.loadData, CW.ActionDisplayerLoadData, widgetClass);
	reg(BNS.saveData, CW.ActionDisplayerSaveFile, widgetClass);
	reg(BNS.clearObjs, CW.ActionDisplayerClear, widgetClass);
	reg(BNS.zoomIn, CW.ActionDisplayerZoomIn, widgetClass);
	reg(BNS.zoomOut, CW.ActionDisplayerZoomOut, widgetClass);
	reg(BNS.rotateLeft, CW.ActionViewerRotateLeft, widgetClass);
	reg(BNS.rotateRight, CW.ActionViewerRotateRight, widgetClass);
	reg(BNS.rotateX, CW.ActionViewerRotateX, widgetClass);
	reg(BNS.rotateY, CW.ActionViewerRotateY, widgetClass);
	reg(BNS.rotateZ, CW.ActionViewerRotateZ, widgetClass);
	reg(BNS.reset, CW.ActionDisplayerReset, widgetClass);
	reg(BNS.molHideHydrogens, CW.ActionDisplayerHideHydrogens, widgetClass);
	reg(BNS.molAutoGenerateCoords, CW.ActionDisplayerAutoGenerateCoordsForMolecule, widgetClass);
	reg(BNS.molDisplayType, CW.ActionViewerChangeMolDisplayTypeStub, widgetClass);

	reg(BNS.copy, CW.ActionDisplayerCopy, widgetClass);

	reg(BNS.openEditor, CW.ActionViewerEdit, widgetClass);
	reg(BNS.config, Kekule.Widget.ActionOpenConfigWidget, widgetClass);

	reg(BNS.molDisplayTypeCondensed, CW.ActionDisplayerChangeMolDisplayTypeCondensed, widgetClass);
	reg(BNS.molDisplayTypeSkeletal, CW.ActionDisplayerChangeMolDisplayTypeSkeletal, widgetClass);
	reg(BNS.molDisplayTypeWire, CW.ActionDisplayerChangeMolDisplayTypeWire, widgetClass);
	reg(BNS.molDisplayTypeSticks, CW.ActionDisplayerChangeMolDisplayTypeSticks, widgetClass);
	reg(BNS.molDisplayTypeBallStick, CW.ActionDisplayerChangeMolDisplayTypeBallStick, widgetClass);
	reg(BNS.molDisplayTypeSpaceFill, CW.ActionDisplayerChangeMolDisplayTypeSpaceFill, widgetClass);
});

})();