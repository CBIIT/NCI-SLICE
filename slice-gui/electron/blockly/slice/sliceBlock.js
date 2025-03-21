goog.provide('Blockly.SLICE.sliceblocks');

//TODO save and restore workspace (export xml)
  //TODO save and restore workspace
  //TODO https://google.github.io/blockly-samples/ add plugins zoom to fit, worspace search, and check content highlight to disable blocks depending of the parent structure
  //TODO advanced usage hide toolbars (add slider for advanced or beginner)
/*var propertyType = "";
var atomType = ["carbon","nitrogen","oxygen","fluorine","phosphorus","sulfur","chlorine","bromine","iodine","hydrogen","helium","lithium","beryllium","boron","neon","sodium","magnesium","aluminium","silicon","argon","potassium","calcium","scandium","titanium","vanadium","chromium","iron","cobalt","nickel","copper","zinc","gallium","germanium","arsenic","selenium","krypton","rubidium","strontium","yttrbium","zirconium","niobium","molybdenum","technetium","ruthenium","rhodium","palladium","silver","cadmium","indium","tin","antimony","tellurium","xenon","cesium","barium","lanthanum","hafnium","tantalum","tungsten","rhenium","osmium","iridium","platinum","gold","mercury","thallium","lead","bismuth","polonium","astatine","radon","francium","radium","actinoids","rutherfordium","dubnium","seaborgium","bohrium","hassium","meitnerium","darmstadium","roentgenium","copernicium","nihonium","flerovium","moscovium ","livermorium","tennessine","oganesson","cerium","praseodymium","neodymium","promethium","samarium","europium","gadolinium","terbium","dysprosium","holmium","erbium","thulium","lutetium","thorium","protactinium","uranium","neptunium","plutonium","americium","curium","berkelium","californium","einsteinium","fermium","mendelevium","nobelium","lawrencium"];
var atomProperties = [ "alkali metal","allylic","anti","aromatic","benzylic","bredt_strained","bridgehead","cis-olefin","cis to","conjugated","doubly","enolizable","fusion","halogen","hetero ","multiply","strained","syn","trans-olefin","trans", "trans to", "triply"];
var bondProperties = ["allylic","aromatic","bredt-strained","bridgehead","cis-olefin","conjugated","donating","double bond","fusion","multiple","single bond","small_fusion","strained","syn","trans-olefin","triple bond","withdrawing"];
var groupProperties =["donating","non expandable withdrawing","expandable withdrawing ","interfering","good leaving","leaving","participating","protected","vinyl-d","vinyl-w","withdrawing"];
var functionalGroupProperties = ["acetal","acetylene","acid","acid halide","alcohol","aldehyde","allene","amide1","amide2","amide3","amide","amidz","amine1","amine2","amine3","amine","amine oxide","anhydride","azide","aziridine","azo","bromide","c-sulfonate","carbamate-c","carbamate-h","carbonium","carbonyl","carboxyl","chloride","cyano","diazo","disulfide","dithioacetal","dithioketal","enamine","enol-ether","episulfide","epoxide","ester","esterx","ether","fluoride","functional","gem-dihalide","glycol","halide","haloamine","halohydrin","hemiacetal","hydrate","hydrazone","hydroxylamine","imine","iodide","isocyanate","isocyanide","ketone","lactam","lactone","methylene","n-carbamate","n-urea-c","n-urea-h","nitrite","nitro","nitroso","o-carbamate","o-carbonate","o-sulfonate","olefin","oxime","peroxide","phosphine","phosphonate","selenide","silylenolether","sulfide","sulfone","sulfoxide","thiocyanate","thioester","thiol","trialkylsiloxy","trialkylsilyl","trihalide","vic-dihalide","vinylsilane"];
var otherGroupProperties = ["isopropyl","methyl","phenyl","t-butyl"];*/

function recolor(block, hue) {
  var oldInit = block.init;
  block.init = function() {
  oldInit.call(this);
  this.setColour(hue);
  }
  }
  
  recolor(Blockly.Blocks['controls_if'], 0);
  recolor(Blockly.Blocks['math_number'], 50);
  recolor(Blockly.Blocks['math_arithmetic'], 50);
  recolor(Blockly.Blocks['logic_compare'], 30);
  

  function getUniqueVarName(baseName) {
    var num = 0;
    var allVar = Blockly.Variables.allUsedVarModels(workspace);
    for (var i = 0, l = allVar.length; i < l; i++) {
        var varName = allVar[i].name;
        var arr = varName.split("_");
        if (arr[0] === "mySet") {
          var num2 = parseInt(arr[1],10);
          if (num2 > num) {
            num = num2;
          }
        }
      }
      num++;
      return baseName += num;
  }
  
  function disableBlocks(property) {
      var inputs = document.getElementsByTagName('block');
      for (var i = 0; i < inputs.length; i++) {
          var block = inputs[i];
          if (block.getAttribute("type").includes(property)) {
              block.setAttribute("disabled","true");
          }
          else {
              block.setAttribute("disabled","false");
          }
      }
  }
  
  export { disableBlocks, getUniqueVarName };

/*Blockly.Blocks['if'] = {
    length: 0,
    init: function() {
      this.appendValueInput("if")
          .setCheck(["and_or,main_logic_structure"])
          .appendField("if");
          this.setPreviousStatement(true, "if");
            this.setNextStatement(true, "if");
      this.appendStatementInput("then")
          .setCheck("Action")
          .appendField("then");
     this.appendDummyInput('addIfStmt')
          .appendField(new Blockly.FieldTextbutton('+', function() { this.sourceBlock_.appendKeyIfStmt(); }) );

      this.setColour(0);
   this.setTooltip("");
   this.setHelpUrl("");
    },
    validate: function(newValue) {
        this.getSourceBlock().updateConnections(newValue);
        return newValue;
      },

        updateConnections: function(newValue) {
          },

    appendKeyIfStmt: function() {
        var options = [
            ['else if', 'ELSEIF'],
            ['else', 'ELSE'],];

        var lastIndex = this.length++;

        var appended_input = this.appendValueInput('element_'+lastIndex)
        .appendField(new Blockly.FieldTextbutton('–', function() { this.sourceBlock_.deleteIfStmt(appended_input); }) )
        .appendField(new Blockly.FieldDropdown(options, this.validate), 'ifStmtDropdown')
        .setCheck(["and_or,main_logic_structure"]);
    this.appendStatementInput("then_"+lastIndex)
        .setCheck("Action")
        .appendField("then");


        this.moveInputBefore('element_'+lastIndex, 'addIfStmt');
        this.moveInputBefore("then_"+lastIndex, 'addIfStmt');

        return appended_input;
  },

  deleteIfStmt: function(inputToDelete) {

        var inputNameToDelete = inputToDelete.name;

        var substructure = this.getInputTargetBlock(inputNameToDelete);
        if(substructure) {
            substructure.dispose(true, true);
        }
        var inputIndexToDelete = parseInt(inputToDelete.name.match(/\d+/)[0]);
        this.removeInput(inputNameToDelete);
        this.removeInput("then_"+inputIndexToDelete);

        var lastIndex = --this.length;

        for(var i=inputIndexToDelete+1; i<=lastIndex; i++) { // rename all the subsequent element-inputs
            var input       = this.getInput( 'element_'+i );
            input.name      = 'element_'+(i-1);

            var then_field   = this.getField_( 'then_'+i );
            then_field.name  = 'then_'+(i-1);
        }
  }
  };*/


//variables and functions info
//https://developers.google.com/blockly/guides/configure/web/toolbox#dynamic_categories
//https://developers.google.com/blockly/guides/create-custom-blocks/variables#javascript
//https://blockly-demo.appspot.com/static/demos/toolbox/index.html
//https://developers.google.com/blockly/guides/configure/web/toolbox#buttons_and_labels

//make modal for enter a variable -> look at mondal plugins
//Blockly.Workspace.getAllVariables()
//console.log(Blockly.Workspace.getAllVariables);
//console.log(Blockly.Variables.allDeveloperVariables);


