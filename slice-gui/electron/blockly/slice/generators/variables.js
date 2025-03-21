"use strict";

goog.provide("Blockly.SLICE.variables");

goog.require("Blockly.SLICE");

var LOCALIZATION = {
  ANYWHERE: "anywhere",
  ONPATH: "onpath",
  OFFPATH: "offpath",
  ONRING: "onring",
  OFFRING: "offring",
  ONCURRENTRING: "on current ring",
  OFFCURRENTRING: "off current ring",
  ONTHEBRIDGE: "on current bridge",
  OFFTHEBRIDGE: "off current bridge"
};

var RING_PREFIX = {
  THE: "the",
  CARBOCYCLIC: "carbocyclic",
  COMMON: "common",
  HETEROCYCLIC: "heterocyclic"
}

var AROMATICITY = {
  ALIPHATIC_OR_AROMATIC: "aliphatic or aromatic",
  ALIPHATIC: "aliphatic",
  AROMATIC: "aromatic"
}

Blockly.SLICE['variables_get'] = function (block) {
  // Variable getter.
  var code = Blockly.SLICE.nameDB_.getName(block.getFieldValue('VAR'),
    Blockly.VARIABLE_CATEGORY_NAME);
  //console.log(block, code, Blockly.VARIABLE_CATEGORY_NAME);
  return [code, Blockly.SLICE.ORDER_ATOMIC];
};

Blockly.SLICE['variables_set'] = function (block) {
  // Variable setter.
  var argument0 = Blockly.SLICE.valueToCode(block, 'VALUE',
    Blockly.SLICE.ORDER_ASSIGNMENT) || 'null';
  var varName = Blockly.SLICE.nameDB_.getName(
    block.getFieldValue('VAR'), Blockly.VARIABLE_CATEGORY_NAME);
  return varName + ' = ' + argument0 + '\n';
};

Blockly.SLICE['set'] = function (block) {
  var subject = Blockly.SLICE.valueToCode(block, "Subject", Blockly.SLICE.ORDER_NONE) ||
    "SubjectToDefine";
  var variable = Blockly.SLICE.nameDB_.getName(
    block.getFieldValue('VAR'), Blockly.VARIABLE_CATEGORY_NAME);

  var indexVar = Blockly.SLICE.nameDB_.getDistinctName(
    variable + '_index', Blockly.VARIABLE_CATEGORY_NAME);
  return "put " + subject + " into " + indexVar.split("_index")[0] + '\n';
};

Blockly.SLICE['remove_from_set'] = function (block) {
  var subject = Blockly.SLICE.valueToCode(block, "Subject", Blockly.SLICE.ORDER_NONE) ||
    "SubjectToDefine";
  var set = Blockly.SLICE.valueToCode(block, "Set", Blockly.SLICE.ORDER_NONE) ||
    "SetToDefine";
  return "remove " + subject + " from " + set + '\n';
};

Blockly.SLICE['empty_set'] = function (block) {
  var set = Blockly.SLICE.valueToCode(block, "Set", Blockly.SLICE.ORDER_NONE) ||
    "SetToDefine";
  return "empty " + set + '\n';
};

Blockly.SLICE['atom_loc_predefined_block'] = function (block) {
  // Variable getter.
  var where = LOCALIZATION[block.getFieldValue("LOCALIZATION")];
  var code = "atoms " + where;
  return [code, Blockly.SLICE.ORDER_ATOMIC];
};

Blockly.SLICE["atom_on_bond_predefined_block"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var argument = Blockly.SLICE.valueToCode(block, "Bond", order) || "bondToDefine";
  var code = "atoms on " + argument;
  return [code, order];
};

Blockly.SLICE['bond_loc_predefined_block'] = function (block) {
  // Variable getter.
  var where = LOCALIZATION[block.getFieldValue("LOCALIZATION")];
  var code = "bonds " + where;
  return [code, Blockly.SLICE.ORDER_ATOMIC];
};

Blockly.SLICE["bond_on_atom_predefined_block"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var argument = Blockly.SLICE.valueToCode(block, "Atom", order) || "atomToDefine";
  var code = "bonds on " + argument;
  return [code, order];
};

Blockly.SLICE['appendage_predefined_block'] = function (block) {
  var order = Blockly.SLICE.ORDER_RELATIONAL;
  var argument0 = Blockly.SLICE.valueToCode(block, 'appendage1', order) || 'subject1';
  var argument1 = Blockly.SLICE.valueToCode(block, 'appendage2', order) || 'subject2';
  var code = 'the appendage from ' + argument0 + ' towards ' + argument1;
  return [code, order];
};

Blockly.SLICE['electron_density_predefined_block'] = function (block) {
  var order = Blockly.SLICE.ORDER_RELATIONAL;
  var argument0 = Blockly.SLICE.valueToCode(block, 'subject', order) || 'subjectToDefine';
  var code = 'electron density on ' + argument0;
  return [code, order];
};

Blockly.SLICE['number_of_predefined_block'] = function (block) {
  var order = Blockly.SLICE.ORDER_RELATIONAL;
  var argument0 = Blockly.SLICE.valueToCode(block, 'subject', order) || 'subjectToDefine';
  var code = 'number of ' + argument0;
  return [code, order];
};

Blockly.SLICE['ring_properties_variable'] = function (block) {
  var order = Blockly.SLICE.ORDER_RELATIONAL;
  var prefix = RING_PREFIX[block.getFieldValue("PREFIX")];
  var aromaticity = AROMATICITY[block.getFieldValue("AROMATICITY")];
  var argument = Blockly.SLICE.valueToCode(block, "Object", order) || "subject";
  var size = block.inputList[0].fieldRow[3].value_;
  var code = prefix + " " + aromaticity + " ring of size "+ size +" containing " + argument;
  return [code, order];
};