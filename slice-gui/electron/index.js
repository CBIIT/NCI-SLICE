//TODO onclose app update database with added values in toAddTo array like toAddToAuthorsDB 
//TODO for customized environment/group, open a new modal with a new iteration of SLICE editor (kekule+right panel)
//https://www.iconfinder.com/search?q=import
window.fixBlocklyBug = function () {
    //console.log("RESIZE1 ",isLogicInitialize, newSelectedChemObject, currentTabName);;
    //if (!isLogicInitialize && newSelectedChemObject && currentTabName === "ChemLogic") {
    if (!isLogicInitialize && document.getElementById("blocklyDiv").style.display == "block" && currentTabName === "ChemLogic") {
        /*document.getElementById("ChemLogic-info").style.display = "none";
        document.getElementById("blocklyDiv").style.display = "block";*/
        //console.log("RESIZE2",newSelectedChemObject);
        workspace.createVariable("current ring");
        workspace.createVariable("interfering group");
        workspace.createVariable("participating group");

        document.getElementById('blocklyArea').style = "block";
        onresize();

        //old way to fix the bug by resizing
        /*window.resizeTo(
            window.innerWidth-1,
            window.innerHeight-1
            );
            window.resizeTo(
                window.innerWidth+1,
                window.innerHeight+1
                );
                */
        isLogicInitialize = true;
    }
}

function initIndexScript() {
    chemComposer = getComposer("chemComposer");
    chemViewer = Kekule.Widget.getWidgetById('chemViewer');
    chemViewer.setAutoSize(true);
    chemEditor = chemComposer.getEditor();

    var structConfigs = chemEditor.getEditorConfigs().getStructureConfigs();
    //console.log(structConfigs);
    structConfigs.primaryOrgChemAtoms = ['C', 'H', 'O', 'N', 'P', 'S', 'F', 'Cl', 'Br', 'I', 'Si', 'D'];
    //Doesn't work it's modified directly in the source code in kekule.chemeditor.configs.js l390
    structConfigs.enabledNonAtomNodeTypes.RGroup = false;
    structConfigs.enabledNonAtomNodeTypes.pseudoatomDummy = false;
    //chemEditor.getEditorConfigs().structureConfigs = structConfigs;
    //chemEditor.getEditorConfigs().addConfigProp('structureConfigs', structConfigs);
    //reconfigure any Atom
    chemEditor.getRenderConfigs().getDisplayLabelConfigs().anyAtom = "*";
    //reconfigure not
    chemEditor.getRenderConfigs().getDisplayLabelConfigs().isoListDisallowPrefix = "!";

    var N = Kekule.ChemWidget.ComponentWidgetNames;
    //to desactivate the set change tool which appears when you select an atom
    chemComposer.setAllowedObjModifierCategories([
        Kekule.Editor.ObjModifier.Category.GENERAL
        /*, Kekule.Editor.ObjModifier.Category.CHEM_STRUCTURE, Kekule.Editor.ObjModifier.Category.STYLE, Kekule.Editor.ObjModifier.Category.GLYPH */
    ]);

    //chemComposer.setChemToolButtons(['manipulate', 'erase', 'bond', 'atomAndFormula', 'ring', 'charge']);

    // Chem toolbar buttons
    chemComposer.setChemToolButtons([
        {
            "name": N.manipulate,
            "attached": [
                N.manipulateMarquee,
                N.manipulateLasso,
                N.manipulateBrush,
                N.manipulateAncestor,
                N.dragScroll,
                N.toggleSelect
            ]
        },
        N.erase,
        {
            "name": N.molBond,
            "attached": [
                N.molBondSingle,
                N.molBondDouble,
                N.molBondTriple,
                N.molBondQuad,
                N.molBondAromatic,
                //N.molBondHydrogen,
                N.molBondAny,
                N.molBondWedgeUp,
                N.molBondWedgeDown,
                N.molChain, N.trackInput,
                //molBondWedgeUpOrDown,
                //molBondDoubleEither
            ]
        },
        //molAtom an molRepMethane Buttons has been defined as individual one
        {
            "name": N.molAtomAndFormula,
            "attached": [
                N.molRepMethane,
                N.molAtom,
                //N.molFormula
            ]
        },
        /*{
            "name": N.molRepMethane,
        },
        {
            "name": N.molAtom,
        },*/
        {
            "name": N.molRing,
            "attached": [
                N.molRing3,
                N.molRing4,
                N.molRing5,
                N.molRing6,
                N.molFlexRing,
                //N.molRingAr6,/*benzene*/
                N.molRepCyclopentaneHaworth1,
                N.molRepCyclohexaneHaworth1,
                N.molRepCyclohexaneChair1,
                N.molRepCyclohexaneChair2
            ]
        },
        /*  {
               "name": N.molCharge/*,
               "attached": [
                  /* N.molChargeClear,
                   N.molChargePositive,
                   N.molChargeNegative,
                   N.molRadicalSinglet,
                   N.molRadicalTriplet,
                   N.molRadicalDoublet,
                   N.molElectronLonePair
               ]
           },*/
        {
            "name": N.glyph,
            "attached": [
                N.glyphReactionArrowNormal,
                N.glyphReactionArrowReversible,
                //N.glyphReactionArrowResonance,
                N.glyphReactionArrowRetrosynthesis,
                //N.glyphRepSegment,
                //N.glyphElectronPushingArrowDouble,
                //N.glyphElectronPushingArrowSingle,
                //N.glyphRepHeatSymbol,
                N.glyphRepAddSymbol
            ]
        },
        /* {
             "name": N.textImage,
             "attached": [
                 N.textBlock,
                 N.imageBlock
             ]
         }*/
    ]);
    //chemEditor.getEditorConfigs().getStructureConfigs().enabledNonAtomNodeTypes.variableAtomList = false;
    //chemEditor.getEditorConfigs().getStructureConfigs().enabledNonAtomNodeTypes.variableAtomNotList = false;
    //console.log(chemComposer.getRepositoryItem().getGlyphClass());
    var reversibleGlyphButton = document.getElementsByClassName("K-Widget K-Style-Undependent K-Normal-Background K-Container K-Toolbar K-Button-Group K-NonSelectable K-Corner-All K-No-Wrap K-Layout-H K-Chem-Composer-Toolbar K-Chem-InnerToolbar K-Dynamic-Created K-Text-Hide K-Glyph-Show K-Chem-Composer-Assoc-Toolbar K-State-Normal");
    reversibleGlyphButton.class = "K-Widget K-Style-Undependent K-Normal-Background K-Container K-Toolbar K-Button-Group K-NonSelectable K-Corner-All K-No-Wrap K-Layout-H K-Chem-Composer-Toolbar K-Chem-InnerToolbar K-Dynamic-Created K-Text-Hide K-Glyph-Show K-Chem-Composer-Assoc-Toolbar K-State-Active";


    //deactivates hotkeys (if activated, it triggers commands, which disturb form filling)
    chemEditor.getEditorConfigs().getInteractionConfigs().enableHotKey = false;
    //Show only explicit hydrogen see kekule.render.base.js for other options
    chemEditor.getRenderConfigs().getMoleculeDisplayConfigs().setDefNodeDisplayMode(Kekule.Render.NodeLabelDisplayMode.SMART);
    chemEditor.getRenderConfigs().getMoleculeDisplayConfigs().setDefMoleculeDisplayType(Kekule.Render.MoleculeDisplayType.SKELETAL);
    chemEditor.getRenderConfigs().getMoleculeDisplayConfigs().setDefHydrogenDisplayLevel(Kekule.Render.HydrogenDisplayLevel.UNMATCHED_EXPLICIT);
    Kekule.globalOptions.add('algorithm.molStandardization', {
        cleanStructure: true,
        clearHydrogens: false,
        unmarshalSubFragments: true,
        doCanonicalization: false,
        doAromaticPerception: false,
        doStereoPerception: false
    });
    //Display options, useful for modifying them with Kekule.globalOptions.add();
    //console.log(Kekule.globalOptions);
    //Usage example 
    //Kekule.globalOptions.add('chemWidget.periodicTable',{
    //    'displayedComponents': ['symbol', 'name', 'atomicNumber', /*'atomicWeight',*/ 'groupHead', /*'periodHead',*/ 'legend']
    //});

    //chemEditor.addIntConfigProp('defHydrogenDisplayLevel', Kekule.Render.HydrogenDisplayLevel.DEFAULT, {'enumSource': Kekule.Render.HydrogenDisplayLevel});
    //displayReactionSMARTS
    //console.log(chemEditor.getEditorConfigs().getInteractionConfigs());
    chemEditor.addEventListener("editObjsUpdated", editObjChanged);
    //chemEditor.addEventListener("load", load);
    chemEditor.addEventListener("selectionChange", selectionChange);
    chemEditor.addEventListener("valueChange", valueChange);
    chemEditor.addEventListener('operUndo', operUndo);
    chemEditor.addEventListener('operRedo', operRedo);

    // Get the element with id="defaultOpen" and click on it
    //open Description tab by default
    document.getElementById("defaultOpen").click();

    var sliceEditor = document.getElementById("ChemEditor");
    var textEditor = document.createElement("textarea");
    textEditor.setAttribute("id", "ChemEditorText");
    textEditor.setAttribute("placeholder", "SLICE File is going to be depict here");
    textEditor.setAttribute("rows", "80");
    textEditor.setAttribute("style", "width: 100%; max-width: 100%;");
    sliceEditor.appendChild(textEditor);

    loadDatabase();
    objEditor = createDefaultEditor();
    objDefaultEditor = objEditor;
}

async function displayReactionSMARTS(recalculateReaction, forceImageGeneration) {
    if (recalculateReaction)
        getReactions();

    if (forceImageGeneration) {
        generateMolImg(true);
    }

    //var mol = Kekule.ChemStructureUtils.getTotalStructFragment(
    //    chemComposer.getChemObj()
    //);
    var mol;
    if (newSelectedChemObject) {
        var thisClass = newSelectedChemObject.getClassName();
        //console.log(thisClass);
        if (thisClass === 'Kekule.Atom' || thisClass === 'Kekule.Bond' || thisClass === 'Kekule.VariableAtom') {
            mol = newSelectedChemObject.getParentFragment();
        }
        else {
            mol = null;
        }
    }
    else {
        mol = defaultMol;
    }

    if (mol) {
        //console.log(mol);
        var sma;
        var smirks;
        if (mol) sma = Kekule.IO.saveMimeData(mol, "chemical/x-daylight-smarts");
        //hasSmartsChanged(mol, sma);
        mol.smarts = sma;
        //console.log("ID ",mol.id, mol.isClassified, mol.reactionIndex);
        if (mol.isClassified) {
            smirks = getSMIRKS(mol.reactionIndex);
            //smirksList[mol.reactionIndex]=smirks;
        }
        else {
            smirks = sma;
        }
        mol.smirks = smirks;
        writeSliceFile();
        //to display the SMIRKS generated (uncomment also the corresponding DIV in html file)
        /*Kekule.DomUtils.setElementText(
            document.getElementById("labelSmiles"),
            smirks || "");*/
    }
    else {
        var mols = chemComposer.exportObjs(Kekule.Molecule);
        for (var i = 0; i < mols.length; i++) {
            var mol = mols[i];
            if (mol.smarts == null) {
                if (mol) sma = Kekule.IO.saveMimeData(mol, "chemical/x-daylight-smarts");
                mol.smarts = sma;
            }
            //hasSmartsChanged(mol, sma);
            //console.log("ID ",mol.id, mol.isClassified, mol.reactionIndex);
            if (mol.isClassified) {
                smirks = getSMIRKS(mol.reactionIndex);
                //smirksList[mol.reactionIndex]=smirks;
            }
            else {
                smirks = sma;
            }
            mol.smirks = smirks;
            writeSliceFile();
        }
    }

    makePatternTab();
    if (mol) {
        if (mol.isChanged == null) {
            mol.isChanged = true;
        }
        //if (updateLogicReactionImages && !areReactionImagesUpdated) {
        if (mol.isChanged) {
            generateMolImg();
        }
        if (hasReactionChanged) {
            //console.log(mol.id + " is changed " + mol.isChanged);
            //console.time("updateLogicTime");
            updateLogicAssistantPage();
            //console.timeEnd("updateLogicTime");
            areReactionImagesUpdated = true;
            hasReactionChanged = false;
        }
    }

}

function hasSmartsChanged(mol, sma) {
    if (mol.smarts) {
        if (mol.smarts === sma) {
            mol.isChanged = false;
        }
        else if (sma == null) {
            //do nothing
        }
        else {
            mol.isChanged = true;
            areReactionImagesUpdated = false;
        }
    }
    else {
        mol.isChanged = true;
    }
}

window.newWindow = async function (currentFilePath) {
    await electronApi.getWindow(currentFilePath);
    console.log(currentFilePath);
}
//to hide instead
//this.parentElement.style.display='none'

//todo if atom selected and div creacted (named div_atid), then unhide and hide the current one, else create template