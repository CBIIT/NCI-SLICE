"use strict";

goog.provide('Blockly.SLICE.comments');

goog.require('Blockly.SLICE');

Blockly.SLICE["comment"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var code = '';
  var entry = block.inputList[0].fieldRow[1].value_;
  var arr = entry.split("\n");
  arr.forEach((line) => {
    code += '//' + line + '\n';
  });
  return '\n' + code;
};