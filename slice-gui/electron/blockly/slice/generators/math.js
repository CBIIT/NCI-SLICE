'use strict';

goog.provide('Blockly.SLICE.math');

goog.require('Blockly.SLICE');


Blockly.SLICE['math_number'] = function (block) {
  // Numeric value.
  var code = Number(block.getFieldValue('NUM'));
  var order = code >= 0 ? Blockly.SLICE.ORDER_ATOMIC :
    Blockly.SLICE.ORDER_UNARY_NEGATION;
  return [code, order];
};

Blockly.SLICE['logic_boolean'] = function (block) {
  var code = (block.getFieldValue('BOOL') == 'TRUE') ? 'true' : 'false';
  return [code, Blockly.SLICE.ORDER_ATOMIC];
};

Blockly.SLICE['math_arithmetic'] = function (block) {
  // Basic arithmetic operators, and power.
  var OPERATORS = {
    'ADD': [' + ', Blockly.SLICE.ORDER_ADDITION],
    'MINUS': [' - ', Blockly.SLICE.ORDER_SUBTRACTION],
    'MULTIPLY': [' * ', Blockly.SLICE.ORDER_MULTIPLICATION],
    'DIVIDE': [' / ', Blockly.SLICE.ORDER_DIVISION],
    'POWER': [null, Blockly.SLICE.ORDER_NONE] // Handle power separately.
  };
  var tuple = OPERATORS[block.getFieldValue('OP')];
  var operator = tuple[0];
  var order = tuple[1];
  var argument0 = Blockly.SLICE.valueToCode(block, 'A', order) || '0';
  var argument1 = Blockly.SLICE.valueToCode(block, 'B', order) || '0';
  var code;
  // Power in SLICE requires a special case since it has no operator.
  if (!operator) {
    code = 'Math.pow(' + argument0 + ', ' + argument1 + ')';
    return [code, Blockly.SLICE.ORDER_FUNCTION_CALL];
  }
  code = argument0 + operator + argument1;
  return [code, order];
};

Blockly.SLICE['math_arithmetic2'] = function (block) {
  // Basic arithmetic operators, and power.
  var OPERATORS = {
    'ADD': [' + ', Blockly.SLICE.ORDER_ADDITION],
    'MINUS': [' - ', Blockly.SLICE.ORDER_SUBTRACTION],
    'MULTIPLY': [' * ', Blockly.SLICE.ORDER_MULTIPLICATION],
    'DIVIDE': [' / ', Blockly.SLICE.ORDER_DIVISION],
    'POWER': [null, Blockly.SLICE.ORDER_NONE] // Handle power separately.
  };
  var tuple = OPERATORS[block.getFieldValue('OP')];
  var operator = tuple[0];
  var order = tuple[1];
  var argument0 = Blockly.SLICE.valueToCode(block, 'A', order) || '0';
  var argument1 = Blockly.SLICE.valueToCode(block, 'B', order) || '0';
  var code;
  // Power in SLICE requires a special case since it has no operator.
  if (!operator) {
    code = 'Math.pow(' + argument0 + ', ' + argument1 + ')';
    return [code, Blockly.SLICE.ORDER_FUNCTION_CALL];
  }
  code = argument0 + operator + argument1;
  //console.log(code);
  return code;
};