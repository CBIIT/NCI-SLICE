goog.provide('Blockly.SLICE.Math');

goog.require('Blockly');
goog.require('Blockly.Constants.Math');
goog.require('Blockly.Blocks');
goog.require('Blockly.FieldDropdown');
goog.require('Blockly.FieldLabel');
goog.require('Blockly.FieldNumber');
goog.require('Blockly.FieldVariable');

Blockly.Constants.Math.HUE = 50;

Blockly.defineBlocksWithJsonArray([{
  "type": "math_arithmetic2",
  "message0": "do %1 %2 %3",
  "args0": [{
      "type": "input_value",
      "name": "A",
      "check": "Number"
    },
    {
      "type": "field_dropdown",
      "name": "OP",
      "options": [
        ["%{BKY_MATH_ADDITION_SYMBOL}", "ADD"],
        ["%{BKY_MATH_SUBTRACTION_SYMBOL}", "MINUS"],
        ["%{BKY_MATH_MULTIPLICATION_SYMBOL}", "MULTIPLY"],
        ["%{BKY_MATH_DIVISION_SYMBOL}", "DIVIDE"],
        ["%{BKY_MATH_POWER_SYMBOL}", "POWER"]
      ]
    },
    {
      "type": "input_value",
      "name": "B",
      "check": "Number"
    }
  ],
  "inputsInline": true,
  "style": "math_blocks",
  "helpUrl": "%{BKY_MATH_ARITHMETIC_HELPURL}",
  "previousStatement": null,
  "nextStatement": null,
  "extensions": ["math_op_tooltip"]
}, ]);