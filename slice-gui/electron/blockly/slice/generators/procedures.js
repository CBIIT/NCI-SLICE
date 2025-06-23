"use strict";

goog.provide("Blockly.SLICE.procedures");

goog.require("Blockly.SLICE");

Blockly.SLICE['procedures_defreturn'] = function (block) {
  // Define a procedure with a return value.
  var funcName = Blockly.SLICE.nameDB_.getName(
    block.getFieldValue('NAME'), Blockly.PROCEDURE_CATEGORY_NAME);
  var xfix1 = '';
  if (Blockly.SLICE.STATEMENT_PREFIX) {
    xfix1 += Blockly.SLICE.injectId(Blockly.SLICE.STATEMENT_PREFIX,
      block);
  }
  if (Blockly.SLICE.STATEMENT_SUFFIX) {
    xfix1 += Blockly.SLICE.injectId(Blockly.SLICE.STATEMENT_SUFFIX,
      block);
  }
  if (xfix1) {
    xfix1 = Blockly.SLICE.prefixLines(xfix1, Blockly.SLICE.INDENT);
  }
  var loopTrap = '';
  if (Blockly.SLICE.INFINITE_LOOP_TRAP) {
    loopTrap = Blockly.SLICE.prefixLines(
      Blockly.SLICE.injectId(Blockly.SLICE.INFINITE_LOOP_TRAP,
        block), Blockly.SLICE.INDENT);
  }
  var branch = Blockly.SLICE.statementToCode(block, 'STACK');
  var returnValue = Blockly.SLICE.valueToCode(block, 'RETURN',
    Blockly.SLICE.ORDER_NONE) || '';
  var xfix2 = '';
  if (branch && returnValue) {
    // After executing the function body, revisit this block for the return.
    xfix2 = xfix1;
  }
  if (returnValue) {
    returnValue = Blockly.SLICE.INDENT + 'return ' + returnValue + '\n';
  }
  var args = [];
  var variables = block.getVars();
  for (var i = 0; i < variables.length; i++) {
    args[i] = Blockly.SLICE.nameDB_.getName(variables[i],
      Blockly.VARIABLE_CATEGORY_NAME);
  }
  var code = 'function ' + funcName + '(' + args.join(', ') + ') {\n' +
    xfix1 + loopTrap + branch + xfix2 + returnValue + '}';
  code = Blockly.SLICE.scrub_(block, code);
  // Add % so as not to collide with helper functions in definitions list.
  Blockly.SLICE.definitions_['%' + funcName] = code;
  //return null;
  return code;
};

// Defining a procedure without a return value uses the same generator as
// a procedure with a return value.
Blockly.SLICE['procedures_defnoreturn'] =
  Blockly.SLICE['procedures_defreturn'];

Blockly.SLICE['procedures_callreturn'] = function (block) {
  // Call a procedure with a return value.
  var funcName = Blockly.SLICE.nameDB_.getName(
    block.getFieldValue('NAME'), Blockly.PROCEDURE_CATEGORY_NAME);
  var args = [];
  var variables = block.getVars();
  for (var i = 0; i < variables.length; i++) {
    args[i] = Blockly.SLICE.valueToCode(block, 'ARG' + i,
      Blockly.SLICE.ORDER_NONE) || 'null';
  }
  var code = funcName + '(' + args.join(', ') + ')';
  return [code, Blockly.SLICE.ORDER_FUNCTION_CALL];
};

Blockly.SLICE['procedures_callnoreturn'] = function (block) {
  // Call a procedure with no return value.
  // Generated code is for a function call as a statement is the same as a
  // function call as a value, with the addition of line ending.
  var tuple = Blockly.SLICE['procedures_callreturn'](block);
  return tuple[0] + '\n';
};

Blockly.SLICE['procedures_ifreturn'] = function (block) {
  // Conditionally return value from a procedure.
  var condition = Blockly.SLICE.valueToCode(block, 'CONDITION',
    Blockly.SLICE.ORDER_NONE) || 'false';
  var code = 'if (' + condition + ') {\n';
  if (Blockly.SLICE.STATEMENT_SUFFIX) {
    // Inject any statement suffix here since the regular one at the end
    // will not get executed if the return is triggered.
    code += Blockly.SLICE.prefixLines(
      Blockly.SLICE.injectId(Blockly.SLICE.STATEMENT_SUFFIX, block),
      Blockly.SLICE.INDENT);
  }
  if (block.hasReturnValue_) {
    var value = Blockly.SLICE.valueToCode(block, 'VALUE',
      Blockly.SLICE.ORDER_NONE) || 'null';
    code += Blockly.SLICE.INDENT + 'return ' + value + '\n';
  } else {
    code += Blockly.SLICE.INDENT + 'return\n';
  }
  code += '}\n';
  return code;
};