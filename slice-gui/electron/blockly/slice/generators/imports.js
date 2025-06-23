'use strict';

goog.provide('Blockly.SLICE.imports');

goog.require('Blockly.SLICE');

Blockly.SLICE["import"] = function (block) {
    var order = Blockly.SLICE.ORDER_ATOMIC;
    var path = block.inputList[0].fieldRow[1].value_;

    var code = "import " + path + "\n";
    return code;
};

Blockly.SLICE["import_as"] = function (block) {
    var order = Blockly.SLICE.ORDER_ATOMIC;
    var path = block.inputList[0].fieldRow[1].value_;
    var name = block.inputList[0].fieldRow[3].value_;

    var code = "import " + path + " as " + name + "\n";
    return code;
};

Blockly.SLICE["import_from"] = function (block) {
    var order = Blockly.SLICE.ORDER_ATOMIC;
    var path = block.inputList[0].fieldRow[1].value_;
    var procedureName = block.inputList[0].fieldRow[3].value_;

    var code = "from " + path + " import " + procedureName + "\n";
    return code;
};

Blockly.SLICE["import_from_as"] = function (block) {
    var order = Blockly.SLICE.ORDER_ATOMIC;
    var path = block.inputList[0].fieldRow[1].value_;
    var procedureName = block.inputList[0].fieldRow[3].value_;
    var name = block.inputList[0].fieldRow[5].value_;

    var code = "from " + path + " import " + procedureName + " as " + name + "\n";
    return code;
};