"use strict";

goog.provide("Blockly.SLICE.blocks.comments");

goog.require("Blockly.SLICE");

Blockly.Blocks['comment'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("multiline comment")
      .appendField(new Blockly.FieldMultilineInput('Enter your comments'),
        'COMMENT');
    this.setInputsInline(true);
    this.setPreviousStatement(true);
    this.setNextStatement(true);
    this.setColour(135);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};