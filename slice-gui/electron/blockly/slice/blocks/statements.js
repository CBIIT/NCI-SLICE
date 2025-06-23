"use strict";

goog.provide("Blockly.SLICE.blocks.statements");

goog.require("Blockly.SLICE");

//if statement id defined in if.js

//TODO add math check

/*Blockly.Blocks['and_or_if'] = {
    init: function() {
      this.appendValueInput("and_or_1")
          .setCheck(['_main_logic_structure','selected_appendage']);
      this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([["and\u00A0if","ANDIF"], ["or\u00A0if","ORIF"]]), "ANDORIF");
      this.appendValueInput("and_or_2")
          .setCheck(['_main_logic_structure','selected_appendage']);
      this.setInputsInline(false);
      this.setOutput(true,'_and_or_if');
      this.setColour(0);
   this.setTooltip("");
   this.setHelpUrl("");
    }
  };*/

/*Blockly.Blocks['and_or'] = {
  init: function() {
    this.appendValueInput("and_or_1")
        .setCheck([
          "selected_atom_by_id",
          "selected_bond_by_range",
          "selected_atom_by_loc",
          "selected_atom_by_loc2",
          "selected_atom_by_atom_type",
          "selected_ring",
          "selected_current_ring",
          "selected_molecule",
          "Generic",
          "Subject",
        ]);
    this.appendDummyInput()
    .appendField(new Blockly.FieldDropdown([["and","AND"], ["or","OR"]]), "AND");
    this.appendValueInput("and_or_2")
        .setCheck([
          "selected_atom_by_id",
          "selected_bond_by_range",
          "selected_atom_by_loc",
          "selected_atom_by_loc2",
          "selected_atom_by_atom_type",
          "selected_ring",
          "selected_current_ring",
          "selected_molecule",
          "Generic",
          "Subject",
        ]);
    this.setInputsInline(false);
    this.setOutput(true,'_and_or');
    this.setColour(0);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};*/