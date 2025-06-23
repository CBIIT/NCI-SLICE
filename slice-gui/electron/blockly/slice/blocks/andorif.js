/**
 * @license
 * Copyright 2020 Google LLC
 * SPDX-License-Identifier: Apache-2.0
 */

/**
 * @fileoverview Changes the if block to use a +/- mutator UI.
 */

import {
  createMinusField
} from "../../block-plus-minus/field_minus.js";
import {
  createPlusField
} from "../../block-plus-minus/field_plus.js";

/* eslint-disable quotes */
Blockly.defineBlocksWithJsonArray([{
  "type": "and_or_if",
  "message0": "%{BKY_AND_OR_IF_TITLE} %1",
  "args0": [{
    "type": "input_dummy",
    "name": "EMPTY",
  }, ],
  "output": "Array",
  "style": "list_blocks",
  "helpUrl": "%{BKY_AND_OR_IF_HELPURL}",
  "tooltip": "%{BKY_AND_OR_IF_TOOLTIP}",
  "mutator": "and_or_if_with_mutator",
}, ]);
/* eslint-enable quotes */

const andOrIfMutator = {
  /**
   * Number of item inputs the block has.
   * @type {number}
   */
  itemCount_: 0,

  /**
   * Creates XML to represent number of text inputs.
   * @return {!Element} XML storage element.
   * @this {Blockly.Block}
   */
  mutationToDom: function () {
    const container = Blockly.utils.xml.createElement('mutation');
    container.setAttribute('items', this.itemCount_);
    return container;
  },
  /**
   * Parses XML to restore the text inputs.
   * @param {!Element} xmlElement XML storage element.
   * @this {Blockly.Block}
   */
  domToMutation: function (xmlElement) {
    const targetCount = parseInt(xmlElement.getAttribute('items'), 10);
    this.updateShape_(targetCount);
  },

  /**
   * Adds inputs to the block until it reaches the target number of inputs.
   * @param {number} targetCount The target number of inputs for the block.
   * @this {Blockly.Block}
   * @private
   */
  updateShape_: function (targetCount) {
    while (this.itemCount_ < targetCount) {
      this.addPart_();
    }
    while (this.itemCount_ > targetCount) {
      this.removePart_();
    }
    this.updateMinus_();
  },

  /**
   * Callback for the plus image. Adds an input to the end of the block and
   * updates the state of the minus.
   */
  plus: function () {
    this.addPart_();
    this.updateMinus_();
  },

  /**
   * Callback for the minus image. Removes an input from the end of the block
   * and updates the state of the minus.
   */
  minus: function () {
    if (this.itemCount_ == 0) {
      return;
    }
    this.removePart_();
    this.updateMinus_();
  },

  // To properly keep track of indices we have to increment before/after adding
  // the inputs, and decrement the opposite.
  // Because we want our first input to be ADD0 (not ADD1) we increment after.

  /**
   * Adds an input to the end of the block. If the block currently has no
   * inputs it updates the top 'EMPTY' input to receive a block.
   * @this {Blockly.Block}
   * @private
   */
  addPart_: function () {
    if (this.itemCount_ == 0) {
      this.removeInput('EMPTY');
      this.topInput_ = this.appendValueInput('ADD' + this.itemCount_)
        .appendField(createPlusField(), 'PLUS')
        .setCheck(['_main_logic_structure', 'selected_appendage']);
      this.setOutput(true, '_and_or_if');
      this.setColour(0);
    } else {
      var suffix = this.itemCount_ > 1 ? this.itemCount_ : "";
      this.appendValueInput('ADD' + this.itemCount_)
        .appendField(new Blockly.FieldDropdown([
          ["and\u00A0if", "ANDIF"],
          ["or\u00A0if", "ORIF"]
        ]), "ANDORIF"+ suffix)
        .setCheck(['_main_logic_structure', 'selected_appendage']);
      this.setOutput(true, '_and_or_if');
      this.setColour(0);
    }
    this.itemCount_++;
  },

  /**
   * Removes an input from the end of the block. If we are removing the last
   * input this updates the block to have an 'EMPTY' top input.
   * @this {Blockly.Block}
   * @private
   */
  removePart_: function () {
    this.itemCount_--;
    this.removeInput('ADD' + this.itemCount_);
    if (this.itemCount_ == 0) {
      this.topInput_ = this.appendDummyInput('EMPTY')
        .appendField(createPlusField(), 'PLUS');
    }
  },

  /**
   * Makes it so the minus is visible iff there is an input available to remove.
   * @private
   */
  updateMinus_: function () {
    const minusField = this.getField('MINUS');
    if (!minusField && this.itemCount_ > 0) {
      this.topInput_.insertFieldAt(1, createMinusField(), 'MINUS');
    } else if (minusField && this.itemCount_ < 1) {
      this.topInput_.removeField('MINUS');
    }
  },
};

/**
 * Updates the shape of the block to have 3 inputs if no mutation is provided.
 * @this {Blockly.Block}
 */
const andOrIfHelper = function () {
  this.getInput('EMPTY').insertFieldAt(0, createPlusField(), 'PLUS');
  this.updateShape_(2);
};

Blockly.Extensions.registerMutator('and_or_if_with_mutator',
  andOrIfMutator, andOrIfHelper);