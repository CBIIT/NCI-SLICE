'use strict';

goog.provide('Blockly.SLICE');

goog.require('Blockly.Generator');
goog.require('Blockly.Names');
goog.require('Blockly.inputTypes');
goog.require('Blockly.utils.global');
goog.require('Blockly.utils.object');
goog.require('Blockly.utils.string');

Blockly.SLICE = new Blockly.Generator('SLICE');

Blockly.SLICE.INDENT = '\t';

/*

Blockly.SLICE.getCode = function() {

    return Blockly.SLICE.workspaceToCode(Blockly.getMainWorkspace());
};
*/

//TODO add all property word aromatic carbon...
/**
 * List of illegal variable names.
 * This is not intended to be a security feature.  Blockly is 100% client-side,
 * so bypassing this list is trivial.  This is intended to prevent users from
 * accidentally clobbering a built-in object or function.
 * @private
 */
Blockly.SLICE.addReservedWords(
    // https://developer.mozilla.org/en-US/docs/Web/SLICE/Reference/Lexical_grammar#Keywords
    'break,exit,continue,debugger,default,delete,put,do,else,export,for,foreach,then,function,if,import,in,at,to,into,on,number,from,as,defined,than,return,this,while,size,through,any' +
    'kill,invert,permute,raise,lower,anionize,cationize,neutralize,radicalize' +
    'slightly,moderately,strongly,severely' +
    'atom,bond,group,ring,appendage,appendages,molecule,current,charge,hindered,interfering,participating' +
    'alpha,beta,gamma' +
    'equivalent,more,less,least,most' +
    'null,true,false,' +
    // Magic variable.
    'arguments,' +
    // Everything in the current environment (835 items in Chrome, 104 in Node).
    Object.getOwnPropertyNames(Blockly.utils.global).join(','));

/**
 * Order of operation ENUMs.
 * https://developer.mozilla.org/en/SLICE/Reference/Operators/Operator_Precedence
 */
/*
Blockly.SLICE.ORDER_ATOMIC = 0;           // 0 "" ...
Blockly.SLICE.ORDER_NEW = 1.1;            // new
Blockly.SLICE.ORDER_MEMBER = 1.2;         // . []
Blockly.SLICE.ORDER_FUNCTION_CALL = 2;    // ()
Blockly.SLICE.ORDER_INCREMENT = 3;        // ++
Blockly.SLICE.ORDER_DECREMENT = 3;        // --
Blockly.SLICE.ORDER_BITWISE_NOT = 4.1;    // ~
Blockly.SLICE.ORDER_UNARY_PLUS = 4.2;     // +
Blockly.SLICE.ORDER_UNARY_NEGATION = 4.3; // -
Blockly.SLICE.ORDER_LOGICAL_NOT = 4.4;    // !
Blockly.SLICE.ORDER_TYPEOF = 4.5;         // typeof
Blockly.SLICE.ORDER_VOID = 4.6;           // void
Blockly.SLICE.ORDER_DELETE = 4.7;         // delete
Blockly.SLICE.ORDER_AWAIT = 4.8;          // await
Blockly.SLICE.ORDER_EXPONENTIATION = 5.0; // **
Blockly.SLICE.ORDER_MULTIPLICATION = 5.1; // *
Blockly.SLICE.ORDER_DIVISION = 5.2;       // /
Blockly.SLICE.ORDER_MODULUS = 5.3;        // %
Blockly.SLICE.ORDER_SUBTRACTION = 6.1;    // -
Blockly.SLICE.ORDER_ADDITION = 6.2;       // +
Blockly.SLICE.ORDER_BITWISE_SHIFT = 7;    // << >> >>>
Blockly.SLICE.ORDER_RELATIONAL = 8;       // < <= > >=
Blockly.SLICE.ORDER_IN = 8;               // in
Blockly.SLICE.ORDER_INSTANCEOF = 8;       // instanceof
Blockly.SLICE.ORDER_EQUALITY = 9;         // == != === !==
Blockly.SLICE.ORDER_BITWISE_AND = 10;     // &
Blockly.SLICE.ORDER_BITWISE_XOR = 11;     // ^
Blockly.SLICE.ORDER_BITWISE_OR = 12;      // |
Blockly.SLICE.ORDER_LOGICAL_AND = 13;     // &&
Blockly.SLICE.ORDER_LOGICAL_OR = 14;      // ||
Blockly.SLICE.ORDER_CONDITIONAL = 15;     // ?:
Blockly.SLICE.ORDER_ASSIGNMENT = 16;      // = += -= **= *= /= %= <<= >>= ...
Blockly.SLICE.ORDER_YIELD = 17;           // yield
Blockly.SLICE.ORDER_COMMA = 18;           // ,
Blockly.SLICE.ORDER_NONE = 99;            // (...)
*/
Blockly.SLICE.ORDER_ATOMIC = 0;           // 0 "" ...
Blockly.SLICE.ORDER_MEMBER = 1;         // . []
Blockly.SLICE.ORDER_FUNCTION_CALL = 2;    // ()
Blockly.SLICE.ORDER_INCREMENT = 3;        // ++
Blockly.SLICE.ORDER_DECREMENT = 3;        // --
Blockly.SLICE.ORDER_BITWISE_NOT = 4.1;    // ~
Blockly.SLICE.ORDER_UNARY_PLUS = 4.2;     // +
Blockly.SLICE.ORDER_UNARY_NEGATION = 4.3; // -
Blockly.SLICE.ORDER_LOGICAL_NOT = 4.4;    // !
Blockly.SLICE.ORDER_EXPONENTIATION = 5.0; // **
Blockly.SLICE.ORDER_MULTIPLICATION = 5.1; // *
Blockly.SLICE.ORDER_DIVISION = 5.2;       // /
Blockly.SLICE.ORDER_MODULUS = 5.3;        // %
Blockly.SLICE.ORDER_SUBTRACTION = 6.1;    // -
Blockly.SLICE.ORDER_ADDITION = 6.2;       // +
Blockly.SLICE.ORDER_RELATIONAL = 7;       // < <= > >=
//Blockly.SLICE.ORDER_IN = 7;               // in
Blockly.SLICE.ORDER_EQUALITY = 8;         // == != === !==
Blockly.SLICE.ORDER_LOGICAL_AND = 9;     // and
Blockly.SLICE.ORDER_LOGICAL_OR = 10;        //or
Blockly.SLICE.ORDER_LOGICAL_ANDIF = 11;     // and if
Blockly.SLICE.ORDER_LOGICAL_ORIF = 12;      // or if
Blockly.SLICE.ORDER_ASSIGNMENT = 13;      // = += -= **= *= /= %= <<= >>= ...
Blockly.SLICE.ORDER_COMMA = 14;           // ,
Blockly.SLICE.ORDER_NONE = 99;            // (...)

/**
 * List of outer-inner pairings that do NOT require parentheses.
 * @type {!Array<!Array<number>>}
 */
Blockly.SLICE.ORDER_OVERRIDES = [
  // (foo()).bar -> foo().bar
  // (foo())[0] -> foo()[0]
  [Blockly.SLICE.ORDER_FUNCTION_CALL, Blockly.SLICE.ORDER_MEMBER],
  // (foo())() -> foo()()
  [Blockly.SLICE.ORDER_FUNCTION_CALL, Blockly.SLICE.ORDER_FUNCTION_CALL],
  // (foo.bar).baz -> foo.bar.baz
  // (foo.bar)[0] -> foo.bar[0]
  // (foo[0]).bar -> foo[0].bar
  // (foo[0])[1] -> foo[0][1]
  [Blockly.SLICE.ORDER_MEMBER, Blockly.SLICE.ORDER_MEMBER],
  // (foo.bar)() -> foo.bar()
  // (foo[0])() -> foo[0]()
  [Blockly.SLICE.ORDER_MEMBER, Blockly.SLICE.ORDER_FUNCTION_CALL],

  // !(!foo) -> !!foo
  [Blockly.SLICE.ORDER_LOGICAL_NOT, Blockly.SLICE.ORDER_LOGICAL_NOT],
  // a * (b * c) -> a * b * c
  [Blockly.SLICE.ORDER_MULTIPLICATION, Blockly.SLICE.ORDER_MULTIPLICATION],
  // a + (b + c) -> a + b + c
  [Blockly.SLICE.ORDER_ADDITION, Blockly.SLICE.ORDER_ADDITION],
  // a && (b && c) -> a && b && c
  [Blockly.SLICE.ORDER_LOGICAL_AND, Blockly.SLICE.ORDER_LOGICAL_AND],
  // a || (b || c) -> a || b || c
  [Blockly.SLICE.ORDER_LOGICAL_OR, Blockly.SLICE.ORDER_LOGICAL_OR]
];

/**
 * Whether the init method has been called.
 * @type {?boolean}
 */
Blockly.SLICE.isInitialized = false;

/**
 * Initialise the database of variable names.
 * @param {!Blockly.Workspace} workspace Workspace to generate code from.
 */
Blockly.SLICE.init = function(workspace) {
  // Call Blockly.Generator's init.
  Object.getPrototypeOf(this).init.call(this);
  
  // Optionally override
  // Create a dictionary of definitions to be printed before the code.
  this.definitions_ = Object.create(null);
  // Create a dictionary mapping desired developer-defined function names in
  // definitions_ to actual function names (to avoid collisions with
  // user-defined procedures).
  this.functionNames_ = Object.create(null);

  if (!this.nameDB_) {
    this.nameDB_ = new Blockly.Names(this.RESERVED_WORDS_);
  } else {
    this.nameDB_.reset();
  }

  this.nameDB_.setVariableMap(workspace.getVariableMap());
  this.nameDB_.populateVariables(workspace);
  this.nameDB_.populateProcedures(workspace);

  var defvars = [];
  // Add developer variables (not created or named by the user).
  var devVarList = Blockly.Variables.allDeveloperVariables(workspace);
  for (var i = 0; i < devVarList.length; i++) {
    defvars.push(this.nameDB_.getName(devVarList[i],
        Blockly.Names.DEVELOPER_VARIABLE_TYPE));
  }

  // Add user variables, but only ones that are being used.
  var variables = Blockly.Variables.allUsedVarModels(workspace);
  for (var i = 0; i < variables.length; i++) {
    defvars.push(this.nameDB_.getName(variables[i].getId(),
        Blockly.VARIABLE_CATEGORY_NAME));
  }

  // Declare all of the variables.
  if (defvars.length) {
    this.definitions_['variables'] = defvars.join(', ');
  }
  this.isInitialized = true;
};

/**
 * Prepend the generated code with the variable definitions.
 * @param {string} code Generated code.
 * @return {string} Completed code.
 */
Blockly.SLICE.finish = function(code) {
  // Convert the definitions dictionary into a list.
  var definitions = [];
  if (this.definitions_) {
      Blockly.utils.object.values(this.definitions_);
  }
  // Call Blockly.Generator's finish.
  code = Object.getPrototypeOf(this).finish.call(this, code);
  this.isInitialized = false;

  this.nameDB_.reset();
  return definitions.join('\n\n') + '\n\n\n' + code;
};

/**
 * Naked values are top-level blocks with outputs that aren't plugged into
 * anything.  A trailing semicolon is needed to make this legal.
 * @param {string} line Line of generated code.
 * @return {string} Legal line of code.
 */
Blockly.SLICE.scrubNakedValue = function(line) {
  return line + '\n';
};

/**
 * Encode a string as a properly escaped SLICE string, complete with
 * quotes.
 * @param {string} string Text to encode.
 * @return {string} SLICE string.
 * @protected
 */
Blockly.SLICE.quote_ = function(string) {
  // Can't use goog.string.quote since Google's style guide recommends
  // JS string literals use single quotes.
  string = string.replace(/\\/g, '\\\\')
                 .replace(/\n/g, '\\\n')
                 .replace(/'/g, '\\\'');
  return '\'' + string + '\'';
};

/**
 * Encode a string as a properly escaped multiline SLICE string, complete
 * with quotes.
 * @param {string} string Text to encode.
 * @return {string} SLICE string.
 * @protected
 */
Blockly.SLICE.multiline_quote_ = function(string) {
  // Can't use goog.string.quote since Google's style guide recommends
  // JS string literals use single quotes.
  var lines = string.split(/\n/g).map(this.quote_);
  return lines.join(' + \'\\n\' +\n');
};

/**
 * Common tasks for generating SLICE from blocks.
 * Handles comments for the specified block and any connected value blocks.
 * Calls any statements following this block.
 * @param {!Blockly.Block} block The current block.
 * @param {string} code The SLICE code created for this block.
 * @param {boolean=} opt_thisOnly True to generate code for only this statement.
 * @return {string} SLICE code with comments and subsequent blocks added.
 * @protected
 */
Blockly.SLICE.scrub_ = function(block, code, opt_thisOnly) {
  var commentCode = '';
  // Only collect comments for blocks that aren't inline.
  if (!block.outputConnection || !block.outputConnection.targetConnection) {
    // Collect comment for this block.
    var comment = block.getCommentText();
    if (comment) {
      comment = Blockly.utils.string.wrap(comment, this.COMMENT_WRAP - 3);
      commentCode += this.prefixLines(comment + '\n', '// ');
    }
    // Collect comments for all value arguments.
    // Don't collect comments for nested statements.
    for (var i = 0; i < block.inputList.length; i++) {
      if (block.inputList[i].type == Blockly.inputTypes.VALUE) {
        var childBlock = block.inputList[i].connection.targetBlock();
        if (childBlock) {
          comment = this.allNestedComments(childBlock);
          if (comment) {
            commentCode += this.prefixLines(comment, '// ');
          }
        }
      }
    }
  }
  var nextBlock = block.nextConnection && block.nextConnection.targetBlock();
  var nextCode = opt_thisOnly ? '' : this.blockToCode(nextBlock);
  return commentCode + code + nextCode;
};

/**
 * Gets a property and adjusts the value while taking into account indexing.
 * @param {!Blockly.Block} block The block.
 * @param {string} atId The property ID of the element to get.
 * @param {number=} opt_delta Value to add.
 * @param {boolean=} opt_negate Whether to negate the value.
 * @param {number=} opt_order The highest order acting on this value.
 * @return {string|number}
 */
Blockly.SLICE.getAdjusted = function(block, atId, opt_delta, opt_negate,
    opt_order) {
  var delta = opt_delta || 0;
  var order = opt_order || this.ORDER_NONE;
  if (block.workspace.options.oneBasedIndex) {
    delta--;
  }
  var defaultAtIndex = block.workspace.options.oneBasedIndex ? '1' : '0';
  if (delta > 0) {
    var at = this.valueToCode(block, atId,
        this.ORDER_ADDITION) || defaultAtIndex;
  } else if (delta < 0) {
    var at = this.valueToCode(block, atId,
        this.ORDER_SUBTRACTION) || defaultAtIndex;
  } else if (opt_negate) {
    var at = this.valueToCode(block, atId,
        this.ORDER_UNARY_NEGATION) || defaultAtIndex;
  } else {
    var at = this.valueToCode(block, atId, order) || defaultAtIndex;
  }

  if (Blockly.isNumber(at)) {
    // If the index is a naked number, adjust it right now.
    at = Number(at) + delta;
    if (opt_negate) {
      at = -at;
    }
  } else {
    // If the index is dynamic, adjust it in code.
    if (delta > 0) {
      at = at + ' + ' + delta;
      var innerOrder = this.ORDER_ADDITION;
    } else if (delta < 0) {
      at = at + ' - ' + -delta;
      var innerOrder = this.ORDER_SUBTRACTION;
    }
    if (opt_negate) {
      if (delta) {
        at = '-(' + at + ')';
      } else {
        at = '-' + at;
      }
      var innerOrder = this.ORDER_UNARY_NEGATION;
    }
    innerOrder = Math.floor(innerOrder);
    order = Math.floor(order);
    if (innerOrder && order >= innerOrder) {
      at = '(' + at + ')';
    }
  }
  return at;
};
