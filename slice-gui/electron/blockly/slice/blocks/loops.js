"use strict";

goog.provide("Blockly.SLICE.blocks.loops");

goog.require("Blockly.SLICE");

import * as blockFun from "../sliceBlock.js";

Blockly.Blocks['foreach_loop'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("foreach");
    this.appendValueInput("Subject")
      .setCheck([
        '_atom_loop',
        '_atom_type_loop',
        '_atom_properties_loop',
        '_atom_type_and_properties_loop',
        '_atom_by_loc_loop',
        '_atom_by_loc_loop2',
        "_atom_on_bond_loop",
        '_bond_loop',
        '_bond_properties_loop',
        '_bond_on_atom_loop',
        '_group_properties_loop',
        '_functional_group_loop',
        '_other_group_loop',
        "Generic",
        "Subject",
      ]);
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["   ", "NULL"],
        ["anywhere", "ANYWHERE"],
        ["onpath", "ONPATH"],
        ["offpath", "OFFPATH"],
        ["onring", "ONRING"],
        ["offring", "OFFRING"],
        ["on current ring", "ONCURRENTRING"],
        ["off current ring", "OFFCURRENTRING"],
        ["on the bridge", "ONTHEBRIDGE"],
        ["off the bridge", "OFFTHEBRIDGE"]
      ]), "LOCALIZATION");
    this.appendDummyInput()
      .appendField("defined as")
      .appendField(new Blockly.FieldVariable(
        blockFun.getUniqueVarName("myVar_"),
        null,
        ['Generic'],
        'Generic'
      ), 'VAR');
    this.appendDummyInput()
      .appendField("in")
    this.appendValueInput("Predicate")
      .setCheck([
        "molecule_subject",
        'Generic',
        'Predicate',
        'Set',
      ]);
    this.appendStatementInput("do")
      //TODO
      .setCheck(['_if', '_foreach'])
    this.setPreviousStatement(true, ['_if', '_foreach', '_function', '_comment']);
    this.setNextStatement(true, ['_if', '_foreach', '_function', '_comment']);
    this.setInputsInline(true);
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['atom_loop'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("atom");
    this.setInputsInline(true);
    this.setOutput(true, '_atom_loop');
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['atom_type_loop'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["carbon", "CARBON"],
        ["nitrogen", "NITROGEN"],
        ["oxygen", "OXYGEN"],
        ["fluorine", "FLUORINE"],
        ["phosphorus", "PHOSPHORUS"],
        ["sulfur", "SULFUR"],
        ["chlorine", "CHLORINE"],
        ["bromine", "BROMINE"],
        ["iodine", "IODINE"],
        ["hydrogen", "HYDROGEN"],
        ["helium", "HELIUM"],
        ["lithium", "LITHIUM"],
        ["beryllium", "BERYLLIUM"],
        ["boron", "BORON"],
        ["neon", "NEON"],
        ["sodium", "SODIUM"],
        ["magnesium", "MAGNESIUM"],
        ["aluminium", "ALUMINIUM"],
        ["silicon", "SILICON"],
        ["argon", "ARGON"],
        ["potassium", "POTASSIUM"],
        ["calcium", "CALCIUM"],
        ["scandium", "SCANDIUM"],
        ["titanium", "TITANIUM"],
        ["vanadium", "VANADIUM"],
        ["chromium", "CHROMIUM"],
        ["iron", "IRON"],
        ["cobalt", "COBALT"],
        ["nickel", "NICKEL"],
        ["copper", "COPPER"],
        ["zinc", "ZINC"],
        ["gallium", "GALLIUM"],
        ["germanium", "GERMANIUM"],
        ["arsenic", "ARSENIC"],
        ["selenium", "SELENIUM"],
        ["krypton", "KRYPTON"],
        ["rubidium", "RUBIDIUM"],
        ["strontium", "STRONTIUM"],
        ["yttrbium", "YTTRBIUM"],
        ["zirconium", "ZIRCONIUM"],
        ["niobium", "NIOBIUM"],
        ["molybdenum", "MOLYBDENUM"],
        ["technetium", "TECHNETIUM"],
        ["ruthenium", "RUTHENIUM"],
        ["rhodium", "RHODIUM"],
        ["palladium", "PALLADIUM"],
        ["silver", "SILVER"],
        ["cadmium", "CADMIUM"],
        ["indium", "INDIUM"],
        ["tin", "TIN"],
        ["antimony", "ANTIMONY"],
        ["tellurium", "TELLURIUM"],
        ["xenon", "XENON"],
        ["cesium", "CESIUM"],
        ["barium", "BARIUM"],
        ["lanthanum", "LANTHANUM"],
        ["hafnium", "HAFNIUM"],
        ["tantalum", "TANTALUM"],
        ["tungsten", "TUNGSTEN"],
        ["rhenium", "RHENIUM"],
        ["osmium", "OSMIUM"],
        ["iridium", "IRIDIUM"],
        ["platinum", "PLATINUM"],
        ["gold", "GOLD"],
        ["mercury", "MERCURY"],
        ["thallium", "THALLIUM"],
        ["lead", "LEAD"],
        ["bismuth", "BISMUTH"],
        ["polonium", "POLONIUM"],
        ["astatine", "ASTATINE"],
        ["radon", "RADON"],
        ["francium", "FRANCIUM"],
        ["radium", "RADIUM"],
        ["actinoids", "ACTINOIDS"],
        ["rutherfordium", "RUTHERFORDIUM"],
        ["dubnium", "DUBNIUM"],
        ["seaborgium", "SEABORGIUM"],
        ["bohrium", "BOHRIUM"],
        ["hassium", "HASSIUM"],
        ["meitnerium", "MEITNERIUM"],
        ["darmstadium", "DARMSTADIUM"],
        ["roentgenium", "ROENTGENIUM"],
        ["copernicium", "COPERNICIUM"],
        ["nihonium", "NIHONIUM"],
        ["flerovium", "FLEROVIUM"],
        ["moscovium ", "MOSCOVIUM"],
        ["livermorium", "LIVERMORIUM"],
        ["tennessine", "TENNESSINE"],
        ["oganesson", "OGANESSON"],
        ["cerium", "CERIUM"],
        ["praseodymium", "PRASEODYMIUM"],
        ["neodymium", "NEODYMIUM"],
        ["promethium", "PROMETHIUM"],
        ["samarium", "SAMARIUM"],
        ["europium", "EUROPIUM"],
        ["gadolinium", "GADOLINIUM"],
        ["terbium", "TERBIUM"],
        ["dysprosium", "DYSPROSIUM"],
        ["holmium", "HOLMIUM"],
        ["erbium", "ERBIUM"],
        ["thulium", "THULIUM"],
        ["lutetium", "LUTETIUM"],
        ["thorium", "THORIUM"],
        ["protactinium", "PROTACTINIUM"],
        ["uranium", "URANIUM"],
        ["neptunium", "NEPTUNIUM"],
        ["plutonium", "PLUTONIUM"],
        ["americium", "AMERICIUM"],
        ["curium", "CURIUM"],
        ["berkelium", "BERKELIUM"],
        ["californium", "CALIFORNIUM"],
        ["einsteinium", "EINSTEINIUM"],
        ["fermium", "FERMIUM"],
        ["mendelevium", "MENDELEVIUM"],
        ["nobelium", "NOBELIUM"],
        ["lawrencium", "LAWRENCIUM"]
      ]), "TYPE")
      .appendField("atom");
    this.setInputsInline(true);
    this.setOutput(true, '_atom_type_loop');
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['atom_properties_loop'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["alkali metal", "ALKALI_METAL"],
        ["allylic", "ALLYLIC"],
        ["anti", "ANTI"],
        ["aromatic", "AROMATIC"],
        ["benzylic", "BENZYLIC"],
        ["bredt-strained", "BREDT_STRAINED"],
        ["bridgehead", "BRIDGEHEAD"],
        ["cis-olefin", "CIS_OLEFIN"],
        ["cis", "CIS"],
        ["conjugated", "CONJUGATED"],
        ["doubly", "DOUBLY"],
        ["enolizable", "ENOLIZABLE"],
        ["fusion", "FUSION"],
        ["halogen", "HALOGEN"],
        ["hetero ", "HETERO"],
        ["multiply bonded", "MULTIPLY"],
        ["strained", "STRAINED"],
        ["syn", "SYN"],
        ["trans-olefin", "TRANS_OLEFIN"],
        ["trans", "TRANS"],
        ["triply", "TRIPLY"]
      ], this.validate), "PROPERTY")
      .appendField("atom");
    this.setInputsInline(true);
    this.setOutput(true, "_atom_properties_loop");
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  },
  validate: function (newValue) {
    this.getSourceBlock().updateConnections(newValue);
    return newValue;
  },

  updateConnections: function (newValue) {}
};

Blockly.Blocks['atom_type_and_properties_loop'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["alkali metal", "ALKALI_METAL"],
        ["allylic", "ALLYLIC"],
        ["anti", "ANTI"],
        ["aromatic", "AROMATIC"],
        ["benzylic", "BENZYLIC"],
        ["bredt-strained", "BREDT_STRAINED"],
        ["bridgehead", "BRIDGEHEAD"],
        ["cis-olefin", "CIS_OLEFIN"],
        ["cis", "CIS"],
        ["conjugated", "CONJUGATED"],
        ["doubly", "DOUBLY"],
        ["enolizable", "ENOLIZABLE"],
        ["fusion", "FUSION"],
        ["halogen", "HALOGEN"],
        ["hetero ", "HETERO"],
        ["multiply", "MULTIPLY"],
        ["strained", "STRAINED"],
        ["syn", "SYN"],
        ["trans-olefin", "TRANS_OLEFIN"],
        ["trans", "TRANS"],
        ["triply", "TRIPLY"]
      ], this.validate), "PROPERTY")
      .appendField(new Blockly.FieldDropdown([
        ["carbon", "CARBON"],
        ["nitrogen", "NITROGEN"],
        ["oxygen", "OXYGEN"],
        ["fluorine", "FLUORINE"],
        ["phosphorus", "PHOSPHORUS"],
        ["sulfur", "SULFUR"],
        ["chlorine", "CHLORINE"],
        ["bromine", "BROMINE"],
        ["iodine", "IODINE"],
        ["hydrogen", "HYDROGEN"],
        ["helium", "HELIUM"],
        ["lithium", "LITHIUM"],
        ["beryllium", "BERYLLIUM"],
        ["boron", "BORON"],
        ["neon", "NEON"],
        ["sodium", "SODIUM"],
        ["magnesium", "MAGNESIUM"],
        ["aluminium", "ALUMINIUM"],
        ["silicon", "SILICON"],
        ["argon", "ARGON"],
        ["potassium", "POTASSIUM"],
        ["calcium", "CALCIUM"],
        ["scandium", "SCANDIUM"],
        ["titanium", "TITANIUM"],
        ["vanadium", "VANADIUM"],
        ["chromium", "CHROMIUM"],
        ["iron", "IRON"],
        ["cobalt", "COBALT"],
        ["nickel", "NICKEL"],
        ["copper", "COPPER"],
        ["zinc", "ZINC"],
        ["gallium", "GALLIUM"],
        ["germanium", "GERMANIUM"],
        ["arsenic", "ARSENIC"],
        ["selenium", "SELENIUM"],
        ["krypton", "KRYPTON"],
        ["rubidium", "RUBIDIUM"],
        ["strontium", "STRONTIUM"],
        ["yttrbium", "YTTRBIUM"],
        ["zirconium", "ZIRCONIUM"],
        ["niobium", "NIOBIUM"],
        ["molybdenum", "MOLYBDENUM"],
        ["technetium", "TECHNETIUM"],
        ["ruthenium", "RUTHENIUM"],
        ["rhodium", "RHODIUM"],
        ["palladium", "PALLADIUM"],
        ["silver", "SILVER"],
        ["cadmium", "CADMIUM"],
        ["indium", "INDIUM"],
        ["tin", "TIN"],
        ["antimony", "ANTIMONY"],
        ["tellurium", "TELLURIUM"],
        ["xenon", "XENON"],
        ["cesium", "CESIUM"],
        ["barium", "BARIUM"],
        ["lanthanum", "LANTHANUM"],
        ["hafnium", "HAFNIUM"],
        ["tantalum", "TANTALUM"],
        ["tungsten", "TUNGSTEN"],
        ["rhenium", "RHENIUM"],
        ["osmium", "OSMIUM"],
        ["iridium", "IRIDIUM"],
        ["platinum", "PLATINUM"],
        ["gold", "GOLD"],
        ["mercury", "MERCURY"],
        ["thallium", "THALLIUM"],
        ["lead", "LEAD"],
        ["bismuth", "BISMUTH"],
        ["polonium", "POLONIUM"],
        ["astatine", "ASTATINE"],
        ["radon", "RADON"],
        ["francium", "FRANCIUM"],
        ["radium", "RADIUM"],
        ["actinoids", "ACTINOIDS"],
        ["rutherfordium", "RUTHERFORDIUM"],
        ["dubnium", "DUBNIUM"],
        ["seaborgium", "SEABORGIUM"],
        ["bohrium", "BOHRIUM"],
        ["hassium", "HASSIUM"],
        ["meitnerium", "MEITNERIUM"],
        ["darmstadium", "DARMSTADIUM"],
        ["roentgenium", "ROENTGENIUM"],
        ["copernicium", "COPERNICIUM"],
        ["nihonium", "NIHONIUM"],
        ["flerovium", "FLEROVIUM"],
        ["moscovium ", "MOSCOVIUM"],
        ["livermorium", "LIVERMORIUM"],
        ["tennessine", "TENNESSINE"],
        ["oganesson", "OGANESSON"],
        ["cerium", "CERIUM"],
        ["praseodymium", "PRASEODYMIUM"],
        ["neodymium", "NEODYMIUM"],
        ["promethium", "PROMETHIUM"],
        ["samarium", "SAMARIUM"],
        ["europium", "EUROPIUM"],
        ["gadolinium", "GADOLINIUM"],
        ["terbium", "TERBIUM"],
        ["dysprosium", "DYSPROSIUM"],
        ["holmium", "HOLMIUM"],
        ["erbium", "ERBIUM"],
        ["thulium", "THULIUM"],
        ["lutetium", "LUTETIUM"],
        ["thorium", "THORIUM"],
        ["protactinium", "PROTACTINIUM"],
        ["uranium", "URANIUM"],
        ["neptunium", "NEPTUNIUM"],
        ["plutonium", "PLUTONIUM"],
        ["americium", "AMERICIUM"],
        ["curium", "CURIUM"],
        ["berkelium", "BERKELIUM"],
        ["californium", "CALIFORNIUM"],
        ["einsteinium", "EINSTEINIUM"],
        ["fermium", "FERMIUM"],
        ["mendelevium", "MENDELEVIUM"],
        ["nobelium", "NOBELIUM"],
        ["lawrencium", "LAWRENCIUM"]
      ]), "TYPE")
      .appendField("atom");
    this.setInputsInline(true);
    this.setOutput(true, '_atom_type_and_properties_loop');
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  },
  validate: function (newValue) {
    this.getSourceBlock().updateConnections(newValue);
    return newValue;
  },

  updateConnections: function (newValue) {}
};

Blockly.Blocks['atom_by_loc_loop'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("atom")
      .appendField(new Blockly.FieldDropdown([
        ["alpha", "ALPHA"],
        ["beta", "BETA"],
        ["gamma", "GAMMA"],
        ["within beta", "WBETA"],
        ["within gamma", "WGAMMA"]
      ]), "NEIGHBOR")
      .appendField("to");
    this.appendValueInput("Object")
      .setCheck(["atom_subject",
        "Generic",
        "Subject"
      ]);
    //this.appendDummyInput()
    //  .appendField(new Blockly.FieldDropdown([["including explicit hydrogens","INCHYDROGEN"], ["not including explicit hydrogens","NOTINCHYDROGEN"]]), "INCLUSION");
    this.setInputsInline(true);
    this.setOutput(true, '_atom_by_loc_loop');
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['atom_by_loc_loop2'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["carbon", "CARBON"],
        ["nitrogen", "NITROGEN"],
        ["oxygen", "OXYGEN"],
        ["fluorine", "FLUORINE"],
        ["phosphorus", "PHOSPHORUS"],
        ["sulfur", "SULFUR"],
        ["chlorine", "CHLORINE"],
        ["bromine", "BROMINE"],
        ["iodine", "IODINE"],
        ["hydrogen", "HYDROGEN"],
        ["helium", "HELIUM"],
        ["lithium", "LITHIUM"],
        ["beryllium", "BERYLLIUM"],
        ["boron", "BORON"],
        ["neon", "NEON"],
        ["sodium", "SODIUM"],
        ["magnesium", "MAGNESIUM"],
        ["aluminium", "ALUMINIUM"],
        ["silicon", "SILICON"],
        ["argon", "ARGON"],
        ["potassium", "POTASSIUM"],
        ["calcium", "CALCIUM"],
        ["scandium", "SCANDIUM"],
        ["titanium", "TITANIUM"],
        ["vanadium", "VANADIUM"],
        ["chromium", "CHROMIUM"],
        ["iron", "IRON"],
        ["cobalt", "COBALT"],
        ["nickel", "NICKEL"],
        ["copper", "COPPER"],
        ["zinc", "ZINC"],
        ["gallium", "GALLIUM"],
        ["germanium", "GERMANIUM"],
        ["arsenic", "ARSENIC"],
        ["selenium", "SELENIUM"],
        ["krypton", "KRYPTON"],
        ["rubidium", "RUBIDIUM"],
        ["strontium", "STRONTIUM"],
        ["yttrbium", "YTTRBIUM"],
        ["zirconium", "ZIRCONIUM"],
        ["niobium", "NIOBIUM"],
        ["molybdenum", "MOLYBDENUM"],
        ["technetium", "TECHNETIUM"],
        ["ruthenium", "RUTHENIUM"],
        ["rhodium", "RHODIUM"],
        ["palladium", "PALLADIUM"],
        ["silver", "SILVER"],
        ["cadmium", "CADMIUM"],
        ["indium", "INDIUM"],
        ["tin", "TIN"],
        ["antimony", "ANTIMONY"],
        ["tellurium", "TELLURIUM"],
        ["xenon", "XENON"],
        ["cesium", "CESIUM"],
        ["barium", "BARIUM"],
        ["lanthanum", "LANTHANUM"],
        ["hafnium", "HAFNIUM"],
        ["tantalum", "TANTALUM"],
        ["tungsten", "TUNGSTEN"],
        ["rhenium", "RHENIUM"],
        ["osmium", "OSMIUM"],
        ["iridium", "IRIDIUM"],
        ["platinum", "PLATINUM"],
        ["gold", "GOLD"],
        ["mercury", "MERCURY"],
        ["thallium", "THALLIUM"],
        ["lead", "LEAD"],
        ["bismuth", "BISMUTH"],
        ["polonium", "POLONIUM"],
        ["astatine", "ASTATINE"],
        ["radon", "RADON"],
        ["francium", "FRANCIUM"],
        ["radium", "RADIUM"],
        ["actinoids", "ACTINOIDS"],
        ["rutherfordium", "RUTHERFORDIUM"],
        ["dubnium", "DUBNIUM"],
        ["seaborgium", "SEABORGIUM"],
        ["bohrium", "BOHRIUM"],
        ["hassium", "HASSIUM"],
        ["meitnerium", "MEITNERIUM"],
        ["darmstadium", "DARMSTADIUM"],
        ["roentgenium", "ROENTGENIUM"],
        ["copernicium", "COPERNICIUM"],
        ["nihonium", "NIHONIUM"],
        ["flerovium", "FLEROVIUM"],
        ["moscovium ", "MOSCOVIUM"],
        ["livermorium", "LIVERMORIUM"],
        ["tennessine", "TENNESSINE"],
        ["oganesson", "OGANESSON"],
        ["cerium", "CERIUM"],
        ["praseodymium", "PRASEODYMIUM"],
        ["neodymium", "NEODYMIUM"],
        ["promethium", "PROMETHIUM"],
        ["samarium", "SAMARIUM"],
        ["europium", "EUROPIUM"],
        ["gadolinium", "GADOLINIUM"],
        ["terbium", "TERBIUM"],
        ["dysprosium", "DYSPROSIUM"],
        ["holmium", "HOLMIUM"],
        ["erbium", "ERBIUM"],
        ["thulium", "THULIUM"],
        ["lutetium", "LUTETIUM"],
        ["thorium", "THORIUM"],
        ["protactinium", "PROTACTINIUM"],
        ["uranium", "URANIUM"],
        ["neptunium", "NEPTUNIUM"],
        ["plutonium", "PLUTONIUM"],
        ["americium", "AMERICIUM"],
        ["curium", "CURIUM"],
        ["berkelium", "BERKELIUM"],
        ["californium", "CALIFORNIUM"],
        ["einsteinium", "EINSTEINIUM"],
        ["fermium", "FERMIUM"],
        ["mendelevium", "MENDELEVIUM"],
        ["nobelium", "NOBELIUM"],
        ["lawrencium", "LAWRENCIUM"]
      ]), "TYPE")
      .appendField(new Blockly.FieldDropdown([
        ["alpha", "ALPHA"],
        ["beta", "BETA"],
        ["gamma", "GAMMA"],
        ["within beta", "WBETA"],
        ["within gamma", "WGAMMA"]
      ]), "NEIGHBOR")
      .appendField("to");
    this.appendValueInput("Object")
      .setCheck(["atom_subject",
        "Generic",
        "Subject"
      ]);
    //this.appendDummyInput()
    //    .appendField(new Blockly.FieldDropdown([["not including explicit hydrogens","INCHYDROGEN"], ["including explicit hydrogens","NOTINCHYDROGEN"]]), "INCLUSION");
    this.setInputsInline(true);
    this.setOutput(true, '_atom_by_loc_loop2');
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['atom_on_bond_loop'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("atom on");
      this.appendValueInput("Bond") .setCheck([
        '_bond_loop',
        '_bond_properties_loop',
        'bond_subject',
        "Generic",
        "Subject",
      ]);
    this.setInputsInline(true);
    this.setOutput(true, '_atom_on_bond_loop');
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['bond_properties_loop'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["allylic", "ALLYLIC"],
        ["aromatic", "AROMATIC"],
        ["bredt-strained", "BREDT_STRAINED"],
        ["bridgehead", "BRIDGEHEAD"],
        ["cis-olefin", "CIS_OLEFIN"],
        ["conjugated", "CONJUGATED"],
        ["donating", "DONATING"],
        ["double bond", "DOUBLE_BOND"],
        ["fusion", "FUSION"],
        ["multiple", "MULTIPLE"],
        ["single bond", "SINGLE_BOND"],
        ["small fusion", "SMALL_FUSION"],
        ["strained", "STRAINED"],
        ["syn", "SYN"],
        ["trans-olefin", "TRANS_OLEFIN"],
        ["triple bond", "TRIPLE_BOND"],
        ["withdrawing", "WITHDRAWING"]
      ]), "PROPERTY")
      .appendField("bond");
    this.setInputsInline(true);
    this.setOutput(true, '_bond_properties_loop');
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  },
  validate: function (newValue) {
    this.getSourceBlock().updateConnections(newValue);
    return newValue;
  },

  updateConnections: function (newValue) {}
};

Blockly.Blocks['bond_on_atom_loop'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("bond on");
      this.appendValueInput("Atom").setCheck([
        "atom_subject",
        "atom_set_subject",
        "Generic",
        "Subject",
      ]);
    this.setInputsInline(true);
    this.setOutput(true, '_bond_on_atom_loop');
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['bond_loop'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("bond");
    this.setInputsInline(true);
    this.setOutput(true, '_bond_loop');
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['ring_loop'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("ring");
    this.setInputsInline(true);
    this.setOutput(true, '_ring_loop');
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['group_properties_loop'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["donating", "DONATING"],
        ["non expandable withdrawing", "NON_EXPANDABLE_WITHDRAWING"],
        ["expandable withdrawing ", "EXPANDABLE_WITHDRAWING "],
        ["interfering", "INTERFERING"],
        ["good leaving", "GOOD_LEAVING"],
        ["leaving", "LEAVING"],
        ["participating", "PARTICIPATING"],
        ["protected", "PROTECTED"],
        ["vinyl-d", "VINYL_D"],
        ["vinyl-w", "VINYL_W"],
        ["withdrawing", "WITHDRAWING"]
      ]), "PROPERTY")
      .appendField("group");
    this.setInputsInline(true);
    this.setOutput(true, '_group_properties_loop');
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['functional_group_loop'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["acetal", "ACETAL"],
        ["acetylene", "ACETYLENE"],
        ["acid halide", "ACID_HALIDE"],
        ["alcohol", "ALCOHOL"],
        ["aldehyde", "ALDEHYDE"],
        ["allene", "ALLENE"],
        ["amide1", "AMIDE1"],
        ["amide2", "AMIDE2"],
        ["amide3", "AMIDE3"],
        ["amide", "AMIDE"],
        ["amidz", "AMIDZ"],
        ["amine1", "AMINE1"],
        ["amine2", "AMINE2"],
        ["amine3", "AMINE3"],
        ["amine", "AMINE"],
        ["amine oxide", "AMINE_OXIDE"],
        ["anhydride", "ANHYDRIDE"],
        ["azide", "AZIDE"],
        ["aziridine", "AZIRIDINE"],
        ["azo", "AZO"],
        ["bromide", "BROMIDE"],
        ["C-sulfonate", "C_SULFONATE"],
        ["carbamate-C", "CARBAMATE_C"],
        ["carbamate-H", "CARBAMATE_H"],
        ["carbonium", "CARBONIUM"],
        ["carbonyl", "CARBONYL"],
        ["carboxylic acid", "CARBOXYL"],
        ["carboxyl", "CARBOXYL_GROUP"],
        ["chloride", "CHLORIDE"],
        ["cyano", "CYANO"],
        ["diazo", "DIAZO"],
        ["disulfide", "DISULFIDE"],
        ["dithioacetal", "DITHIOACETAL"],
        ["dithioketal", "DITHIOKETAL"],
        ["enamine", "ENAMINE"],
        ["enol-ether", "ENOL_ETHER"],
        ["episulfide", "EPISULFIDE"],
        ["epoxide", "EPOXIDE"],
        ["ester", "ESTER"],
        ["esterx", "ESTERX"],
        ["ether", "ETHER"],
        ["fluoride", "FLUORIDE"],
        ["functional", "FUNCTIONAL"],
        ["gem-dihalide", "GEM_DIHALIDE"],
        ["glycol", "GLYCOL"],
        ["halide", "HALIDE"],
        ["haloamine", "HALOAMINE"],
        ["halohydrin", "HALOHYDRIN"],
        ["hemiacetal", "HEMIACETAL"],
        ["hydrate", "HYDRATE"],
        ["hydrazone", "HYDRAZONE"],
        ["hydroxylamine", "HYDROXYLAMINE"],
        ["imine", "IMINE"],
        ["iodide", "IODIDE"],
        ["isocyanate", "ISOCYANATE"],
        ["isocyanide", "ISOCYANIDE"],
        ["ketone", "KETONE"],
        ["lactam", "LACTAM"],
        ["lactone", "LACTONE"],
        ["methylene", "METHYLENE"],
        ["N-carbamate", "N_CARBAMATE"],
        ["N-urea-C", "N_UREA_C"],
        ["N-urea-H", "N_UREA_H"],
        ["nitrite", "NITRITE"],
        ["nitro", "NITRO"],
        ["nitroso", "NITROSO"],
        ["O-carbamate", "O_CARBAMATE"],
        ["O-carbonate", "O_CARBONATE"],
        ["O-sulfonate", "O_SULFONATE"],
        ["olefin", "OLEFIN"],
        ["oxime", "OXIME"],
        ["peroxide", "PEROXIDE"],
        ["phosphine", "PHOSPHINE"],
        ["phosphonate", "PHOSPHONATE"],
        ["selenide", "SELENIDE"],
        ["silylenolether", "SILYLENOLETHER"],
        ["sulfide", "SULFIDE"],
        ["sulfone", "SULFONE"],
        ["sulfoxide", "SULFOXIDE"],
        ["thiocyanate", "THIOCYANATE"],
        ["thioester", "THIOESTER"],
        ["thiol", "THIOL"],
        ["trialkylsiloxy", "TRIALKYLSILOXY"],
        ["trialkylsilyl", "TRIALKYLSILYL"],
        ["trihalide", "TRIHALIDE"],
        ["vic-dihalide", "VIC_DIHALIDE"],
        ["vinylsilane", "VINYLSILANE"]
      ]), "FUNCTIONAL")
      .appendField("group");
    this.setInputsInline(true);
    this.setOutput(true, '_functional_group_loop');
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['other_group_loop'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["isopropyl", "ISOPROPYL"],
        ["methyl", "METHYL"],
        ["phenyl", "PHENYL"],
        ["t-butyl", "T_BUTYL"]
      ]), "OTHER")
      .appendField("group");
    this.setInputsInline(true);
    this.setOutput(true, '_other_group_loop');
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['ring_size_loop'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("ring of size")
      .appendField(new Blockly.FieldNumber(0, 0), "minRingSize");
    this.setInputsInline(true);
    this.setOutput(true, 'Set');
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['ring_size_loop2'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("ring of size")
      .appendField(new Blockly.FieldNumber(0, 0), "minRingSize")
      .appendField(new Blockly.FieldDropdown([
        ["through", "THROUGH"],
        ["or smaller", "ORSMALLER"],
        ["or larger", "ORLARGER"]
      ], this.validate), "ringSizeQualitativeProperties");
    this.setInputsInline(true);
    this.setOutput(true, 'Set');
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  },
  validate: function (newValue) {
    this.getSourceBlock().updateConnections(newValue);
    return newValue;
  },

  updateConnections: function (newValue) {
    if (this.getInput("maxRingSizeInput")) {
      this.removeInput("maxRingSizeInput");
    }
    if (newValue == 'THROUGH') {
      this.appendDummyInput("maxRingSizeInput")
        .appendField(new Blockly.FieldNumber(0, 0), "maxRingSize")
    }
  }
};

Blockly.Blocks['ring_properties_loop'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["an", "AN"],
        ["carbocyclic", "CARBOCYCLIC"],
        ["common", "COMMON"],
        ["heterocyclic", "HETEROCYCLIC"]
      ]), "PREFIX")
      .appendField(new Blockly.FieldDropdown([
        ["aliphatic or aromatic", "ALIPHATIC_OR_AROMATIC"],
        ["aliphatic", "ALIPHATIC"],
        ["aromatic", "AROMATIC"]
      ]), "AROMATICITY")
      .appendField("ring");
    this.setInputsInline(true);
    this.setOutput(true, 'Set');
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['ring_size_properties_loop'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["an", "AN"],
        ["carbocyclic", "CARBOCYCLIC"],
        ["common", "COMMON"],
        ["heterocyclic", "HETEROCYCLIC"]
      ]), "PREFIX")
      .appendField(new Blockly.FieldDropdown([
        ["aliphatic or aromatic", "ALIPHATIC_OR_AROMATIC"],
        ["aliphatic", "ALIPHATIC"],
        ["aromatic", "AROMATIC"]
      ]), "AROMATICITY")
      .appendField("ring of size")
      .appendField(new Blockly.FieldNumber(0, 0), "minRingSize");
    this.setInputsInline(true);
    this.setOutput(true, 'Set');
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['ring_size_properties_loop2'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["an", "AN"],
        ["carbocyclic", "CARBOCYCLIC"],
        ["common", "COMMON"],
        ["heterocyclic", "HETEROCYCLIC"]
      ]), "PREFIX")
      .appendField(new Blockly.FieldDropdown([
        ["aliphatic or aromatic", "ALIPHATIC_OR_AROMATIC"],
        ["aliphatic", "ALIPHATIC"],
        ["aromatic", "AROMATIC"]
      ]), "AROMATICITY")
      .appendField("ring of size")
      .appendField(new Blockly.FieldNumber(0, 0), "minRingSize")
      .appendField(new Blockly.FieldDropdown([
        ["through", "THROUGH"],
        ["or smaller", "ORSMALLER"],
        ["or larger", "ORLARGER"]
      ], this.validate), "SIZE");
    this.setInputsInline(true);
    this.setOutput(true, 'Set');
    this.setColour(180);
    this.setTooltip("");
    this.setHelpUrl("");
  },
  validate: function (newValue) {
    this.getSourceBlock().updateConnections(newValue);
    return newValue;
  },

  updateConnections: function (newValue) {
    if (this.getInput("maxRingSizeInput")) {
      this.removeInput("maxRingSizeInput");
    }
    if (newValue == 'THROUGH') {
      this.appendDummyInput("maxRingSizeInput")
        .appendField(new Blockly.FieldNumber(0, 0), "maxRingSize")
    }
  }
};

//create predicate toolbox
const createFlyoutLoopPredicate = function (workspace) {
  let xmlList = [];

  // This gets all the variables that the user creates and adds them to the
  // flyout.
  var arr = Blockly.VariablesDynamic.flyoutCategoryBlocks(workspace);
  var blockList = [];

  arr.forEach(block => {
    if (block.attributes[0].nodeValue === "variables_get_dynamic" && (block.textContent !== "interfering group" && block.textContent !== "participating group")) {
      blockList.push(block)
    }
  });
  //console.log(blockList);
  //delete .attribute[0].nodeValue === "varaibles_set_dynamic"
  xmlList = xmlList.concat(blockList);

  var blockText0 = '<block type="select_molecule"></block>';
  var block0 = Blockly.Xml.textToDom(blockText0);
  xmlList.push(block0);

  var blockText1 = '<block type="ring_properties_loop"></block>';
  var block1 = Blockly.Xml.textToDom(blockText1);
  xmlList.push(block1);

  var blockText2 = '<block type="ring_size_properties_loop"></block>';
  var block2 = Blockly.Xml.textToDom(blockText2);
  xmlList.push(block2);

  var blockText3 = '<block type="ring_size_properties_loop2"></block>'
  var block3 = Blockly.Xml.textToDom(blockText3);
  xmlList.push(block3);

  return xmlList;
};

workspace.registerToolboxCategoryCallback('LOOP_PREDICATE', createFlyoutLoopPredicate);