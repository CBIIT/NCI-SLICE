"use strict";

goog.provide('Blockly.SLICE.statements');

goog.require('Blockly.SLICE');


//console.log("g",goog.getObjectByName('Blockly.SLICE'));
//console.log("g",goog);

Blockly.SLICE["controls_if"] = function (block) {
    // If/elseif/else condition.
    var n = 0;
    var code = "",
        branchCode,
        conditionCode;
    if (Blockly.SLICE.STATEMENT_PREFIX) {
        // Automatic prefix insertion is switched off for this block.  Add manually.
        code += Blockly.SLICE.injectId(Blockly.SLICE.STATEMENT_PREFIX, block);
    }
    do {
        conditionCode =
            Blockly.SLICE.valueToCode(block, "IF" + n, Blockly.SLICE.ORDER_NONE) ||
            "LogicToDefine ";
        branchCode = Blockly.SLICE.statementToCode(block, "DO" + n);
        if (Blockly.SLICE.STATEMENT_SUFFIX) {
            branchCode =
                Blockly.SLICE.prefixLines(
                    Blockly.SLICE.injectId(Blockly.SLICE.STATEMENT_SUFFIX, block),
                    Blockly.SLICE.INDENT
                ) + branchCode;
        }
        branchCode = branchCode.endsWith("\n") ? branchCode.replace(/\n$/, "") : branchCode;
        if (branchCode.split("\n").length > 1 || branchCode.includes("if ")) {
            code +=
                (n > 0 ? "\nelse " : "") +
                "if " +
                conditionCode +
                " then {\n   " +
                branchCode +
                //"}";
                "\n}";
        } else {
            code +=
                (n > 0 ? "\n" + "else " : "") + "if " + conditionCode + " then " + branchCode;
        }
        ++n;
    } while (block.getInput("IF" + n));

    if (block.getInput("ELSE") || Blockly.SLICE.STATEMENT_SUFFIX) {
        branchCode = Blockly.SLICE.statementToCode(block, "ELSE");
        if (Blockly.SLICE.STATEMENT_SUFFIX) {
            branchCode =
                Blockly.SLICE.prefixLines(
                    Blockly.SLICE.injectId(Blockly.SLICE.STATEMENT_SUFFIX, block),
                    Blockly.SLICE.INDENT
                ) + branchCode;
        }
        branchCode = branchCode.endsWith("\n") ? branchCode.replace(/\n$/, "") : branchCode;
        if (branchCode.split("\n").length > 1) {
            code += "\n" + "else {\n   " + branchCode + "\n" + "}";
        } else {
            code += "\n" + "else " + branchCode + "\n";
        }
    }
    return code + "\n";
};

Blockly.SLICE["controls_ifelse"] = Blockly.SLICE["controls_if"];

Blockly.SLICE['and_or_if'] = function (block) {
    // Operations 'and', 'or'.
    var code = "";
    var order = Blockly.SLICE.ORDER_LOGICAL_ANDIF;
    for (var i = 0; i < block.itemCount_; i++) {
        var operator = "";
        var elt = Blockly.SLICE.valueToCode(block, 'ADD' + i,
            Blockly.SLICE.ORDER_NONE) || 'LogicToDefine';
        if (i > 0) {
            operator = (block.inputList[i].fieldRow[0].value_ == 'ANDIF') ? ' and if ' : ' or if ';
            order = (operator == 'and') ? Blockly.SLICE.ORDER_LOGICAL_ANDIF :
                Blockly.SLICE.ORDER_LOGICAL_ORIF;
        }
        code += operator + elt;

    }
    return [code, order];
};

Blockly.SLICE['and_or'] = function (block) {
    // Operations 'and', 'or'.
    var code = "";
    var order = Blockly.SLICE.ORDER_LOGICAL_AND;
    for (var i = 0; i < block.itemCount_; i++) {
        var operator = "";
        var elt = Blockly.SLICE.valueToCode(block, 'ADD' + i,
            Blockly.SLICE.ORDER_NONE) || 'LogicToDefine';
        if (i > 0) {
            operator = (block.inputList[i].fieldRow[0].value_ == 'AND') ? ' and ' : ' or ';
            order = (operator == 'and') ? Blockly.SLICE.ORDER_LOGICAL_AND :
                Blockly.SLICE.ORDER_LOGICAL_OR;
        }
        code += operator + elt;
    }
    return [code, order];
};