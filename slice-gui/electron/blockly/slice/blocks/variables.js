"use strict";

goog.provide("Blockly.SLICE.blocks.variables");

goog.require("Blockly.SLICE");

import * as blockFun from "../sliceBlock.js";

Blockly.Blocks["set"] = {
  init: function () {
    this.appendDummyInput().appendField("put");
    this.appendValueInput("Subject").setCheck([
      "_electron_density_predefined_block",
      "_number_of_predefined_block",
      "Set",
      "atom_subject",
      "bond_subject",
      "atom_set_subject",
      "Generic"
    ]);
    this.appendDummyInput()
      .appendField("into")
      .appendField(
        new Blockly.FieldVariable(
          blockFun.getUniqueVarName("mySet_"),
          null,
          ["Set"],
          "Set"
        ),
        "VAR"
      );
    this.setInputsInline(true);
    this.setPreviousStatement(true, [
      "_if",
      "_foreach",
      "_function",
      "_comment",
      "_set",
    ]);
    this.setNextStatement(true, [
      "_if",
      "_foreach",
      "_function",
      "_comment",
      "_set",
    ]);
    this.setColour(255);
    this.setTooltip("");
    this.setHelpUrl("");
  },
  test: function () {
    //console.log(this[0].name);
  },
};

Blockly.Blocks["remove_from_set"] = {
  init: function () {
    this.appendDummyInput().appendField("remove");
    this.appendValueInput("Subject").setCheck([
      "_electron_density_predefined_block",
      "_number_of_predefined_block",
      //"Set",
      "atom_subject",
      "bond_subject",
      "atom_set_subject",
      "Generic"
    ]);
    this.appendDummyInput()
      .appendField("from");
    this.appendValueInput("Set").setCheck([
        "Set",
      ]);
    this.setInputsInline(true);
    this.setPreviousStatement(true, [
      "_if",
      "_foreach",
      "_function",
      "_comment",
      "_set",
    ]);
    this.setNextStatement(true, [
      "_if",
      "_foreach",
      "_function",
      "_comment",
      "_set",
    ]);
    this.setColour(255);
    this.setTooltip("");
    this.setHelpUrl("");
  },
  test: function () {
    //console.log(this[0].name);
  },
};

Blockly.Blocks["empty_set"] = {
  init: function () {
    this.appendDummyInput().appendField("empty out");
    this.appendValueInput("Set").setCheck([
      "Set"
    ]);
    this.setInputsInline(true);
    this.setPreviousStatement(true, [
      "_if",
      "_foreach",
      "_function",
      "_comment",
      "_set",
    ]);
    this.setNextStatement(true, [
      "_if",
      "_foreach",
      "_function",
      "_comment",
      "_set",
    ]);
    this.setColour(255);
    this.setTooltip("");
    this.setHelpUrl("");
  },
  test: function () {
    //console.log(this[0].name);
  },
};

Blockly.Blocks['atom_on_bond_predefined_block'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("atoms on");
      this.appendValueInput("Bond") .setCheck([
        'bond_subject',
        '_bond_loop',
        '_bond_properties_loop',
        "Generic",
        "Subject",
      ]);
    this.setInputsInline(true);
    //its a Set type
    this.setOutput(true, 'Set');
    this.setColour(135);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks["atom_loc_predefined_block"] = {
  init: function () {
    this.appendDummyInput().appendField("atoms");
    this.appendDummyInput().appendField(
      new Blockly.FieldDropdown([
        ["anywhere", "ANYWHERE"],
        ["onpath", "ONPATH"],
        ["offpath", "OFFPATH"],
        ["onring", "ONRING"],
        ["offring", "OFFRING"],
        ["on current ring", "ONCURRENTRING"],
        ["off current ring", "OFFCURRENTRING"],
        ["on the bridge", "ONTHEBRIDGE"],
        ["off the bridge", "OFFTHEBRIDGE"],
      ]),
      "LOCALIZATION"
    );
    this.setInputsInline(true);
    //its a Set type
    this.setOutput(true, "Set");
    this.setColour(135);
  },
};

Blockly.Blocks["bond_loc_predefined_block"] = {
  init: function () {
    this.appendDummyInput().appendField("bonds");
    this.appendDummyInput().appendField(
      new Blockly.FieldDropdown([
        ["anywhere", "ANYWHERE"],
        ["onpath", "ONPATH"],
        ["offpath", "OFFPATH"],
        ["onring", "ONRING"],
        ["offring", "OFFRING"],
        ["on current ring", "ONCURRENTRING"],
        ["off current ring", "OFFCURRENTRING"],
        ["on the bridge", "ONTHEBRIDGE"],
        ["off the bridge", "OFFTHEBRIDGE"],
      ]),
      "LOCALIZATION"
    );
    this.setInputsInline(true);
    this.setOutput(true, "Set");
    this.setColour(135);
  },
};

Blockly.Blocks['bond_on_atom_predefined_block'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("bonds on");
      this.appendValueInput("Atom").setCheck([
        "atom_subject",
        "atom_set_subject",
        "Generic",
        "Subject",
      ]);
    this.setInputsInline(true);
    //its a Set type
    this.setOutput(true, 'Set');
    this.setColour(135);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks["appendage_predefined_block"] = {
  init: function () {
    this.appendDummyInput().appendField("appendage from");
    this.appendValueInput("appendage1").setCheck(
      "[select_appendages_by_atom_mapping,select_appendages_by_atom_loc,select_appendages_by_atom_loc2]"
    );
    this.appendDummyInput().appendField("towards");
    this.appendValueInput("appendage2").setCheck(
      "[select_appendages_by_atom_mapping,select_appendages_by_atom_loc,select_appendages_by_atom_loc2]"
    );
    this.setInputsInline(true);
    //its a Set type
    this.setOutput(true, "Set");
    this.setColour(135);
    this.setTooltip("");
    this.setHelpUrl("");
  },
};

Blockly.Blocks['ring_properties_variable'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["the", "THE"],
        ["carbocyclic", "CARBOCYCLIC"],
        ["common", "COMMON"],
        ["heterocyclic", "HETEROCYCLIC"]
      ]), "PREFIX")
      .appendField(new Blockly.FieldDropdown([
        ["aliphatic or aromatic", "ALIPHATIC_OR_AROMATIC"],
        ["aliphatic", "ALIPHATIC"],
        ["aromatic", "AROMATIC"]
      ]), "AROMATICITY")
      .appendField("ring of size")
      .appendField(new Blockly.FieldNumber(6, 0), "minRingSize");
    this.appendDummyInput()
    .appendField("containing");
    this.appendValueInput("Object")
      .setCheck(["atom_subject",
        "Generic",
        "Subject"
      ]);
    this.setInputsInline(true);
    //its a Set type
    this.setOutput(true, 'Set');
    this.setColour(135);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks["electron_density_predefined_block"] = {
  init: function () {
    this.appendDummyInput().appendField("electron density on");
    //TODO
    this.appendValueInput("subject").setCheck([
      "atom_subject",
      "atom_set_subject",
      "Subject",
      "Generic",
    ]);
    this.setInputsInline(true, "_electron_density_predefined_block");
    this.setOutput(true);
    this.setColour(135);
    this.setTooltip("");
    this.setHelpUrl("");
  },
};

Blockly.Blocks["number_of_predefined_block"] = {
  init: function () {
    this.appendDummyInput().appendField("number of");
    this.appendValueInput("subject").setCheck([
      "atom_subject",
      "atom_set_subject",
      "qualitative_atom_predicate",
      "_quantitative_atom_predicate",
      "_quantitative_hindered_comparison_predicate",
      "qualitative_bond_predicate",
      "quantitative_group_predicate",
      "ring_predicate",
      "Subject",
      "Generic",
    ]);
    this.setInputsInline(true);
    this.setOutput(true, "_number_of_predefined_block");
    this.setColour(135);
    this.setTooltip("");
    this.setHelpUrl("");
  },
};

/*// Block for variable getter.
Blockly.Blocks['variables_get_panda'] = {
  init: function() {
    this.appendDummyInput()
      .appendField(new Blockly.FieldVariable(
          "panda",
          null,
          ["Generic"],
          "Generic"
        ),
        "VAR");
    this.setOutput(true, 'Generic');
  }
};

// Block for variable setter.
Blockly.Blocks['variables_set_panda'] = {
  init: function() {
    this.appendValueInput("NAME")
        .setCheck('Panda')
        .appendField("set")
        .appendField(new Blockly.FieldVariable(
          "panda",
          null,
          ["Generic"],
          "Generic"
        ),
        "VAR")
        .appendField("to");
        this.setPreviousStatement(true, null);
        this.setNextStatement(true, null);
  }
};*/

//create variable block
const createFlyout = function (workspace) {
  let xmlList = [];
  // Add your button and give it a callback name.
  const button = document.createElement("button");
  button.setAttribute("text", "Create variable...");
  button.setAttribute("callbackKey", "CREATE_NEW_VARIABLE");

  xmlList.push(button);

  // This gets all the variables that the user creates and adds them to the
  // flyout.
  const blockList = Blockly.VariablesDynamic.flyoutCategoryBlocks(workspace);
  /*for (var i = 0; i < blockList.length; i++) {
    var block = blockList[i];
    if (!block.innerHTML.includes('variabletype="Set"')) {
      xmlList.push(block);
    }
  }*/

  xmlList = xmlList.concat(blockList);

  //It's define into another category
  /*const label = document.createElement("label");
  label.setAttribute("text", "   ");
  xmlList.push(label);

  var blockText = '<block type="set"></block>';
  var block = Blockly.Xml.textToDom(blockText);
  xmlList.push(block);

  var blockText = '<block type="remove_from_set"></block>';
  var block = Blockly.Xml.textToDom(blockText);
  xmlList.push(block);

  var blockText = '<block type="empty_set"></block>';
  var block = Blockly.Xml.textToDom(blockText);
  xmlList.push(block);*/


  return xmlList;
};

//create set block
const createSetFlyout = function (workspace) {
  let xmlList = [];

  // This gets all the variables that the user creates and adds them to the
  // flyout.
  const blockList = Blockly.VariablesDynamic.flyoutCategoryBlocks(workspace);
  for (var i = 0; i < blockList.length; i++) {
    var block = blockList[i];
    if (block.innerHTML.includes('variabletype="Set"')) {
      xmlList.push(block);
    }
  }
  //xmlList = xmlList.concat(blockList);

  /*var blockText = '<block type="variables_get_panda"></block>';
  var block = Blockly.Xml.textToDom(blockText);
  xmlList.push(block);

  xmlList.push("<sep></sep>");*/

  /*const label = document.createElement("label");
  label.setAttribute("text", " ");
  xmlList.push(label);*/

  var blockText = '<block type="set"></block>';
  var block = Blockly.Xml.textToDom(blockText);
  xmlList.push(block);

  var blockText = '<block type="remove_from_set"></block>';
  var block = Blockly.Xml.textToDom(blockText);
  xmlList.push(block);

  var blockText = '<block type="empty_set"></block>';
  var block = Blockly.Xml.textToDom(blockText);
  xmlList.push(block);

  return xmlList;
};

//Moved to index as the were not initialized
//workspace.createVariable("current ring", "Generic");
//workspace.createVariable("interfering group", "Generic");
//workspace.createVariable("participating group", "Generic");

/*workspace.registerToolboxCategoryCallback(
  "CREATE_SET",
  createSetFlyout
);*/

workspace.registerToolboxCategoryCallback(
  "CREATE_TYPED_VARIABLE",
  createFlyout
);

workspace.registerButtonCallback("CREATE_NEW_VARIABLE", function(button) {
  //Blockly.Variables.createVariableButtonHandler(button.getTargetWorkspace(), null, "Generic");
  Blockly.Variables.createVariableButtonHandler(button.getTargetWorkspace());
});


/*const typedVarModal = new TypedVariableModal(workspace, "CREATE_NEW_VARIABLE", [
  ["Generic", ""],
  /*["Subject", "SUBJECT"],
  ["Predicate", "PREDICATE"],*/
 // ["Set", "Set"],
  /*["Integer", "INT"],
  ["Float", "FLOAT"],
  ["String", "STRING"],*/
/*]);
typedVarModal.init();*/