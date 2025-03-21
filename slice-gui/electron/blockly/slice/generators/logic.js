"use strict";

goog.provide("Blockly.SLICE.logic");

goog.require("Blockly.SLICE");

Blockly.SLICE['main_logic_structure'] = function (block) {
  var OPERATORS = {
    'IS': 'is',
    'ISNOT': "is not",
    'ARE': 'are',
    'ARENOT': "are not",
    'HAS': 'has',
    'HASNOT': "has not",
    'HAVE': 'have',
    'HAVENOT': "have not"
  };
  var LOCALIZATIONS = {
    'ANYWHERE': 'anywhere',
    'NULL': '',
    'ONPATH': "onpath",
    'OFFPATH': 'offpath',
    'ONRING': "onring",
    'OFFRING': 'offring',
    'ONCURRENTRING': "on current ring",
    'OFFCURRENTRING': 'off current ring',
    'ONTHEBRIDGE': "on the bridge",
    'OFFTHEBRIDGE': "off the bridge"
  };

  var operator = OPERATORS[block.getFieldValue('RELATION')];
  var order = Blockly.SLICE.ORDER_NONE;
  var argument0 = Blockly.SLICE.valueToCode(block, 'Subject', order) || 'subject';
  var argument1 = Blockly.SLICE.valueToCode(block, 'Predicate', order) || 'predicate';
  var localization = LOCALIZATIONS[block.getFieldValue('LOCALIZATION')];
  if (block.getFieldValue('LOCALIZATION') !== "NULL") {
    var code = argument0 + ' ' + operator + ' ' + argument1 + ' ' + localization;
  }
  else {
    var code = argument0 + ' ' + operator + ' ' + argument1
  }
  return [code, order];
};

Blockly.SLICE['select_appendage'] = function (block) {
  var OPERATORS = {
    'IDENTICAL': 'are identical',
    'NOTINDENTICAL': "are not identical"
  };
  var operator = OPERATORS[block.getFieldValue('RELATION')];
  var order = Blockly.SLICE.ORDER_NONE;
  var argument0 = Blockly.SLICE.valueToCode(block, 'appendage1', order) || 'subject1';
  var argument1 = Blockly.SLICE.valueToCode(block, 'appendage2', order) || 'subject2';
  var argument2 = Blockly.SLICE.valueToCode(block, 'appendage3', order) || 'subject3';
  var argument3 = Blockly.SLICE.valueToCode(block, 'appendage4', order) || 'subject4';
  var code = 'the appendage from ' + argument0 + ' towards ' + argument1 + ' ' + operator + ' to the appendage from ' + argument2 + ' towards ' + argument3 + ' ';
  return [code, order];
};

Blockly.SLICE['logic_compare'] = function (block) {
  // Comparison operator.
  var OPERATORS = {
    'EQ': '==',
    'NEQ': '!=',
    'LT': '<',
    'LTE': '<=',
    'GT': '>',
    'GTE': '>='
  };
  var operator = OPERATORS[block.getFieldValue('OP')];
  var order = (operator == '==' || operator == '!=') ?
    Blockly.SLICE.ORDER_EQUALITY : Blockly.SLICE.ORDER_RELATIONAL;
  var argument0 = Blockly.SLICE.valueToCode(block, 'A', order) || 'value1';
  var argument1 = Blockly.SLICE.valueToCode(block, 'B', order) || 'value2';
  var code = argument0 + ' ' + operator + ' ' + argument1 + ' ';
  return [code, order];
};