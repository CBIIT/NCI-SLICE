'use strict';

goog.provide('Blockly.SLICE.variablesDynamic');

goog.require('Blockly.SLICE');
goog.require('Blockly.SLICE.variables');


// SLICE is dynamically typed.
Blockly.SLICE['variables_get_dynamic'] =
    Blockly.SLICE['variables_get'];
Blockly.SLICE['variables_set_dynamic'] =
    Blockly.SLICE['variables_set'];