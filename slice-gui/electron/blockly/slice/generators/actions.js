"use strict";

goog.provide("Blockly.SLICE.actions");

goog.require("Blockly.SLICE");

Blockly.SLICE['kill'] = function (block) {
  var order = Blockly.SLICE.ORDER_NONE;
  var code = "kill" + "\n";
  return code;
};

Blockly.SLICE['exit'] = function (block) {
  var order = Blockly.SLICE.ORDER_NONE;
  var code = "exit" + "\n";
  return code;
};

Blockly.SLICE['ghostMolecule'] = function (block) {
  var order = Blockly.SLICE.ORDER_NONE;
  var code = "define it as ghost molecule" + "\n";
  hasReactionChanged = true;//For ghost molecule color background
  return code;
};

Blockly.SLICE['rating'] = function (block) {
  var OPERATORS = {
    'RAISE': 'raise',
    'LOWER': "lower"
  };
  ["slightly", "SLIGHTLY"], ["moderately", "MODERATELY"], ["strongly", "STRONGLY"], ["severely", "SEVERELY"]
  var GRADE = {
    'SLIGHTLY': 'slightly',
    'MODERATELY': "moderately",
    'STRONGLY': 'strongly',
    'SEVERELY': "severely"
  };
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var operator = OPERATORS[block.getFieldValue('TYPE')];
  var grade = GRADE[block.getFieldValue('GRADE')];
  var code = operator + ' rating ' + grade + "\n";
  return code;
};

Blockly.SLICE['mechanism'] = function (block) {
  var ACTIONS = {
    'INVERT': 'invert at',
    'ANIONIZE': "anionize",
    'CATIONIZE': 'cationize',
    'NEUTRALIZE': "neutralize",
    'PERMUTE': 'permute at',
    'RADICALIZE': "radicalize",
    'RACEMIZE': 'racemize at'
  };
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var action = ACTIONS[block.getFieldValue('ACTION')];
  var code = action + " ";
  if (block.getInput('atom')) {
    code += "atom " + Number(block.getInput('atom').fieldRow[1].value_);
  } else {
    var input = block.getInput('bond').fieldRow;
    var atom1 = Number(input[1].value_);
    var atom2 = Number(input[3].value_);
    code += "bond between atom " + atom1 + " and " + atom2;
  }
  return code + "\n";
};

Blockly.SLICE['return'] = function (block) {
  var order = Blockly.SLICE.ORDER_NONE;
  var argument = Blockly.SLICE.valueToCode(block, 'RETURN', order) || 'objectToDefine';
  var code = "return " + argument + "\n";
  return code;
};