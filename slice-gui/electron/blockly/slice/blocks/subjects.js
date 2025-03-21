"use strict";

goog.provide("Blockly.SLICE.blocks.subjects");

goog.require("Blockly.SLICE");

Blockly.Blocks['select_atom_by_id'] = {
  init: function () {
    this.appendDummyInput("selectAtomByID")
      .appendField("atom")
      .appendField(new Blockly.FieldNumber(0, 0), "atomMapping");
    this.setInputsInline(true);
    this.setOutput(true, 'atom_subject');
    this.setColour(135);
    this.setTooltip("Replace 0 by the desire atom number");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['select_bond_by_range'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("bond between atoms")
      .appendField(new Blockly.FieldNumber(0, 0), "atomMapping1")
      .appendField("and")
      .appendField(new Blockly.FieldNumber(0, 0), "atomMapping2");
    this.setInputsInline(true);
    this.setOutput(true, 'bond_subject');
    this.setColour(135);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['select_bond_by_range2'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("bond between ");
    this.appendValueInput("Atom1")
      .setCheck(["atom_subject",
        "Generic",
        "Subject"
      ]);
    this.appendDummyInput()
      .appendField("and");
    this.appendValueInput("Atom2")
      .setCheck(["atom_subject",
        "Generic",
        "Subject"
      ]);
    this.setInputsInline(true);
    this.setOutput(true, 'bond_subject');
    this.setColour(135);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['select_atom_by_loc'] = {
  init: function () {
    this.appendDummyInput()
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
    this.setOutput(true, 'atom_set_subject');
    this.setColour(135);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['select_atom_by_loc2'] = {
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
    this.setOutput(true, 'atom_set_subject');
    this.setColour(135);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['select_atom_by_atom_type'] = {
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
      .appendField("atom(s)");
    this.setInputsInline(true);
    this.setOutput(true, 'atom_set_subject');
    this.setColour(135);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['select_ring'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("ring")
    this.setInputsInline(true);
    this.setOutput(true, 'ring_subject');
    this.setColour(135);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

/*Blockly.Blocks['select_current_ring'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("current ring")
    this.setInputsInline(true);
    this.setOutput(true,'selected_current_ring');
    this.setColour(135);
 this.setTooltip("");
 this.setHelpUrl("");
  }
};*/

Blockly.Blocks['select_molecule'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("molecule")
    this.setInputsInline(true);
    this.setOutput(true, 'molecule_subject');
    this.setColour(135);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['select_appendages_by_atom_mapping'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("atom")
      .appendField(new Blockly.FieldNumber(0, 0), "atomMapping");
    this.setInputsInline(true);
    this.setOutput(true, 'atom_subject');
    this.setColour(135);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['select_appendages_by_atom_loc'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["alpha", "ALPHA"],
        ["beta", "BETA"],
        ["gamma", "GAMMA"],
        ["within beta", "WBETA"],
        ["within gamma", "WGAMMA"]
      ]), "NEIGHBOR")
      .appendField("to");
    this.appendValueInput("Object")
      .setCheck(['atom_subject', 'Generic', 'Subject']);
    //this.appendDummyInput()
    // .appendField(new Blockly.FieldDropdown([["not including explicit hydrogens","INCHYDROGEN"], ["including explicit hydrogens","NOTINCHYDROGEN"]]), "INCLUSION");
    this.setInputsInline(true);
    this.setOutput(true, 'atom_set_subject');
    this.setColour(135);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['select_appendages_by_atom_loc2'] = {
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
      .setCheck(['atom_subject', 'Generic', 'Subject']);
    //this.appendDummyInput()
    //    .appendField(new Blockly.FieldDropdown([["not including explicit hydrogens","INCHYDROGEN"], ["including explicit hydrogens","NOTINCHYDROGEN"]]), "INCLUSION");
    this.setInputsInline(true);
    this.setOutput(true, 'atom_set_subject');
    this.setColour(135);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};