"use strict";

goog.provide("Blockly.SLICE.blocks.logic");

goog.require("Blockly.SLICE");

//import * as blockFun from "../../sliceBlock.js";
import * as blockFun from "../sliceBlock.js";

Blockly.Blocks["main_logic_structure"] = {
  init: function () {
    this.appendValueInput("Subject").setCheck([
      "atom_subject",
      "bond_subject",
      "atom_set_subject",
      "ring_subject",
      "molecule_subject",
      "Generic",
      "Subject",
      "_and_or",
    ]);
    //.appendField("Subject");
    this.appendValueInput("Predicate")
      //.appendField("Relation")
      .appendField(
        new Blockly.FieldDropdown(
          [
            ["is", "IS"],
            ["isn't", "ISNOT"],
            ["are", "ARE"],
            ["aren't", "ARENOT"],
            ["has", "HAS"],
            ["hasn't", "HASNOT"],
            ["have", "HAVE"],
            ["haven't", "HAVENOT"],
          ],
          this.validate
        ),
        "RELATION"
      )
      .setCheck([
        "qualitative_atom_predicate",
        "quantitative_atom_predicate",
        "_qualitative_chemical_object_equivalent_predicate",
        "_quantitative_hindered_comparison_predicate",
        "qualitative_bond_predicate",
        "quantitative_group_predicate",
        "qualitative_group_predicate",
        "ring_predicate",
        "_and_or",
      ]);
    //.appendField("Predicate");
    this.appendDummyInput().appendField(
      new Blockly.FieldDropdown([
        ["   ", "NULL"],
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

    this.setOutput(true, '_main_logic_structure');
    //this.setPreviousStatement(true, "Operator");
    //this.setNextStatement(true, "Operator");
    this.setInputsInline(true);
    this.setColour(30);
    this.setTooltip("");
    this.setHelpUrl("");
  },
  validate: function (newValue) {
    this.getSourceBlock().updateConnections(newValue);
    return newValue;
  },

  updateConnections: function (newValue) {
    if (
      newValue == "IS" ||
      newValue == "ISNOT" ||
      newValue == "ARE" ||
      newValue == "ARENOT"
    ) {
      blockFun.disableBlocks("quantitative_");
    } else if (
      newValue == "HAS" ||
      newValue == "HASNOT" ||
      newValue == "HAVE" ||
      newValue == "HAVENOT"
    ) {
      blockFun.disableBlocks("qualitative_");
    }
  },
};

Blockly.Blocks["select_appendage"] = {
  init: function () {
    this.appendDummyInput().appendField("the appendage from");
    this.appendValueInput("appendage1").setCheck([
      "atom_subject",
      "atom_set_subject",
      "Generic",
      "Subject",
      "Predicate",
    ]);
    this.appendDummyInput().appendField("towards");
    this.appendValueInput("appendage2").setCheck([
      "atom_subject",
      "atom_set_subject",
      "Generic",
      "Subject",
      "Predicate",
    ]);
    this.appendDummyInput()
      .appendField(
        new Blockly.FieldDropdown([
          ["is\u00A0identical", "IDENTICAL"],
          ["isn't\u00A0identical", "NOTINDENTICAL"],
        ]),
        "RELATION"
      )
      .appendField("to the appendage from");
    this.appendValueInput("appendage3").setCheck([
      "atom_subject",
      "atom_set_subject",
      "Generic",
      "Subject",
      "Predicate",
    ]);
    this.appendDummyInput().appendField("towards");
    this.appendValueInput("appendage4").setCheck([
      "atom_subject",
      "atom_set_subject",
      "Generic",
      "Subject",
      "Predicate",
    ]);
    this.setInputsInline(true);
    this.setOutput(true, 'selected_appendage');
    this.setColour(30);
    this.setTooltip("");
    this.setHelpUrl("");
  },
};

Blockly.defineBlocksWithJsonArray([{
  'type': 'logic_compare',
  'message0': '%1 %2 %3',
  'args0': [
    {
      'type': 'input_value',
      'name': 'A',
      "check": null
    },
    {
      'type': 'field_dropdown',
      'name': 'OP',
      'options': [
        ['=', 'EQ'],
        ['\u2260', 'NEQ'],
        ['\u200F<', 'LT'],
        ['\u200F\u2264', 'LTE'],
        ['\u200F>', 'GT'],
        ['\u200F\u2265', 'GTE'],
      ],
    },
    {
      'type': 'input_value',
      'name': 'B',
      "check": null
    },
  ],
  'inputsInline': true,
  'output': '_logic_compare',
  "colour": 30,
  'helpUrl': '%{BKY_LOGIC_COMPARE_HELPURL}',
}, ]);