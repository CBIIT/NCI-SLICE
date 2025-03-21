"use strict";

goog.provide("Blockly.SLICE.blocks.imports");

goog.require("Blockly.SLICE");

Blockly.Blocks['import'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("import")
      .appendField(new Blockly.FieldTextInput('module path'), 'MODULEPATH');
    this.setPreviousStatement(true, ['_import', '_import_as', '_import_from', 'import_from_as']);
    this.setNextStatement(true, ['_import', '_import_as', '_import_from', 'import_from_as']);
    this.setInputsInline(true);
    this.setColour(150);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['import_as'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("import")
      .appendField(new Blockly.FieldTextInput('module path'), 'MODULEPATH')
      .appendField("as")
      .appendField(new Blockly.FieldTextInput('Name'), 'NAME');
    this.setPreviousStatement(true, ['_import', '_import_as', '_import_from', 'import_from_as']);
    this.setNextStatement(true, ['_import', '_import_as', '_import_from', 'import_from_as']);
    this.setInputsInline(true);
    this.setColour(150);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['import_from'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("from")
      .appendField(new Blockly.FieldTextInput('module path'), 'MODULEPATH')
      .appendField("import")
      .appendField(new Blockly.FieldTextInput('procedure name'), 'MODULENAME');
    this.setPreviousStatement(true, ['_import', '_import_as', '_import_from', 'import_from_as']);
    this.setNextStatement(true, ['_import', '_import_as', '_import_from', 'import_from_as']);
    this.setInputsInline(true);
    this.setColour(150);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['import_from_as'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("from")
      .appendField(new Blockly.FieldTextInput('module path'), 'MODULEPATH')
      .appendField("import")
      .appendField(new Blockly.FieldTextInput('procedure name'), 'MODULENAME')
      .appendField("as")
      .appendField(new Blockly.FieldTextInput('Name'), 'NAME');
    this.setPreviousStatement(true, ['_import', '_import_as', '_import_from', 'import_from_as']);
    this.setNextStatement(true, ['_import', '_import_as', '_import_from', 'import_from_as']);
    this.setInputsInline(true);
    this.setColour(150);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};