"use strict";

goog.provide("Blockly.SLICE.blocks.actions");

goog.require("Blockly.SLICE");

Blockly.Blocks["kill"] = {
  init: function () {
    this.appendDummyInput().appendField("kill reaction");
    this.setInputsInline(true);
    this.setPreviousStatement(true, "_kill");
    this.setColour(300);
    this.setTooltip("");
    this.setHelpUrl("");
  },
};

Blockly.Blocks["rating"] = {
  init: function () {
    this.appendDummyInput()
      .appendField(
        new Blockly.FieldDropdown([
          ["raise", "RAISE"],
          ["lower", "LOWER"],
        ]),
        "TYPE"
      )
      .appendField("rating")
      .appendField(
        new Blockly.FieldDropdown([
          ["slightly", "SLIGHTLY"],
          ["moderately", "MODERATELY"],
          ["strongly", "STRONGLY"],
          ["severely", "SEVERELY"],
        ]),
        "GRADE"
      );
    this.setInputsInline(true);
    this.setPreviousStatement(true, ["_rating", "_ghostMolecule", "_exit", "_mechanism", "_if", "_foreach", "comment"]);
    this.setNextStatement(true, ["_rating", "_ghostMolecule", "_exit", "_mechanism", "_if", "_foreach", "comment"]);
    this.setColour(300);
    this.setTooltip("");
    this.setHelpUrl("");
  },
};

Blockly.Blocks["exit"] = {
  init: function () {
    this.appendDummyInput().appendField("exit loop");
    this.setInputsInline(true);
    this.setPreviousStatement(true, "_exit");
    this.setColour(300);
    this.setTooltip("");
    this.setHelpUrl("");
  },
};

Blockly.Blocks["ghostMolecule"] = {
  init: function () {
    this.appendDummyInput().appendField("define it as ghost molecule");
    this.setInputsInline(true);
    this.setPreviousStatement(true, ["_ghostMolecule", "_exit", "_mechanism", "_if", "_foreach", "comment"]);
    this.setNextStatement(true, ["_ghostMolecule", "_exit", "_mechanism", "_if", "_foreach", "comment"]);
    this.setPreviousStatement(false, "_ghostMolecule");
    this.setColour(300);
    this.setTooltip("");
    this.setHelpUrl("");
  },
};

Blockly.Blocks["mechanism"] = {
  init: function () {
    this.appendDummyInput().appendField(
      new Blockly.FieldDropdown(
        [
          ["invert at", "INVERT"],
          ["anionize", "ANIONIZE"],
          ["cationize", "CATIONIZE"],
          ["neutralize", "NEUTRALIZE"],
          ["permute at", "PERMUTE"],
          ["radicalize", "RADICALIZE"],
          ["racemize at", "RACEMIZE"],
        ],
        this.validate
      ),
      "ACTION"
    );
    this.setInputsInline(true);
    this.setPreviousStatement(true, ["_mechanism", "_rating", "_ghostMolecule", "_exit"]);
    this.setNextStatement(true, ["_mechanism", "_rating", "_ghostMolecule", "_exit"]);
    this.setColour(300);
    this.setTooltip("");
    this.setHelpUrl("");
  },
  validate: function (newValue) {
    this.getSourceBlock().updateConnections(newValue);
    return newValue;
  },

  updateConnections: function (newValue) {
    if (this.getInput("atom")) {
      this.removeInput("atom");
    }
    if (newValue == "PERMUTE") {
      this.appendDummyInput("bond")
        .appendField("bond between atom")
        .appendField(new Blockly.FieldNumber(0, 0), "atom1")
        .appendField("and")
        .appendField(new Blockly.FieldNumber(0, 0), "atom2");
    } else {
      if (this.getInput("bond")) {
        this.removeInput("bond");
      }
      this.appendDummyInput("atom")
        .appendField("atom")
        .appendField(new Blockly.FieldNumber(0, 0), "atomMapping");
    }
  },
};

Blockly.Blocks["return"] = {
  init: function () {
    this.appendDummyInput().appendField("return");
    this.appendValueInput("RETURN").setCheck([
      "String",
      "Integer",
      "Float",
      "Generic",
      "Boolean",
      "Number",
      "Set",
      "Subject",
      "Predicate",
      "atom_subject",
      "bond_subject",
      "atom_set_subject",
      "ring_subject",
      "molecule_subject"
    ]);
    this.setInputsInline(true);
    this.setPreviousStatement(true, "_return");
    this.setColour(300);
    this.setTooltip("");
    this.setHelpUrl("");
  },
};