"use strict";

goog.provide("Blockly.SLICE.blocks.predicates");

goog.require("Blockly.SLICE");

Blockly.Blocks['qualitative_atom_properties_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["alkali metal", "ALKALI_METAL"],
        ["allylic", "ALLYLIC"],
        ["anti", "ANTI"],
        ["anti to", "ANTITO"],
        ["anti to about the path...", "ANTITO2"],
        ["aromatic", "AROMATIC"],
        ["benzylic", "BENZYLIC"],
        ["bredt-strained", "BREDT_STRAINED"],
        ["bridgehead", "BRIDGEHEAD"],
        ["cis-olefin", "CIS_OLEFIN"],
        ["cis", "CIS"],
        ["cis to about the ring...", "CISTO"],
        ["conjugated", "CONJUGATED"],
        ["doubly", "DOUBLY"],
        ["enolizable", "ENOLIZABLE"],
        ["fusion", "FUSION"],
        ["halogen", "HALOGEN"],
        ["hetero ", "HETERO"],
        ["multiply bonded", "MULTIPLY"],
        ["strained", "STRAINED"],
        ["syn", "SYN"],
        ["syn to", "SYNTO"],
        ["anti to about the path...", "SYNTO2"],
        ["trans-olefin", "TRANS_OLEFIN"],
        ["trans", "TRANS"],
        ["trans to about the ring...", "TRANSTO"],
        ["triply", "TRIPLY"]
      ], this.validate), "PROPERTY")
    this.setInputsInline(true);
    this.setOutput(true, 'qualitative_atom_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  },
  validate: function (newValue) {
    this.getSourceBlock().updateConnections(newValue);
    return newValue;
  },

  updateConnections: function (newValue) {
    if (this.getInput("atom2")) {
      this.removeInput("atom2");
      this.removeInput("atom3");
      this.removeInput("atom4");
      this.removeInput("path1");
      this.removeInput("path2");
    } else if (this.getInput("atom5")) {
      this.removeInput("atom5");
      this.removeInput("atom6");
      this.removeInput("atom7");
      this.removeInput("path3");
      this.removeInput("path4");
    } else if (this.getInput("atom9")) {
      this.removeInput("atom9");
    }
    if (newValue == 'CISTO' || newValue == 'TRANSTO') {
      this.appendValueInput("atom2")
        .setCheck(["atom_subject",
          "Generic",
          "Subject"
        ]);
      this.appendDummyInput("path1")
        .appendField("about the ring containing bond between");
      this.appendValueInput("atom3")
        .setCheck(["atom_subject",
          "Generic",
          "Subject"
        ]);
      this.appendDummyInput("path2")
        .appendField("and");
      this.appendValueInput("atom4")
        .setCheck(["atom_subject",
          "Generic",
          "Subject"
        ]);
    }else if (newValue == 'ANTITO' || newValue == 'SYNTO') {
      this.appendValueInput("atom9")
        .setCheck(["atom_subject",
          "Generic",
          "Subject"
        ]);
    } else if (newValue == 'ANTITO2' || newValue == 'SYNTO2') {
      this.appendValueInput("atom5")
        .setCheck(["atom_subject",
          "Generic",
          "Subject"
        ]);
      this.appendDummyInput("path3")
        .appendField("about the path from");
      this.appendValueInput("atom6")
        .setCheck(["atom_subject",
          "Generic",
          "Subject"
        ]);
      this.appendDummyInput("path4")
        .appendField("to");
      this.appendValueInput("atom7")
        .setCheck(["atom_subject",
          "Generic",
          "Subject"
        ]);
    }
  }
};

Blockly.Blocks['quantitative_atom_properties_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["exactly", "EQ"],
        ["at least", "LE"],
        ["at most", "GE"],
        ["less than", "LT"],
        ["more than", "GT"]
      ]), "OP")
      .appendField(new Blockly.FieldNumber(0, 0), "NumberOfAtomProperty")
      .appendField("alpha")
      .appendField(new Blockly.FieldDropdown([
        ["alkyl", "ALKYL"],
        ["hetero", "HETERO"],
        ["halogen", "HALOGEN"]
      ]), "PROPERTY")
      .appendField("atom(s)")
    this.setInputsInline(true);
    this.setOutput(true, 'quantitative_atom_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['qualitative_center_properties_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["primary center", "PRIMARY"],
        ["secondary center", "SECONDARY"],
        ["tertiary center", "TERTIARY"],
        ["quaternary center", "QUATERNARY"]
      ]), "CENTER")
    this.setInputsInline(true);
    this.setOutput(true, 'qualitative_atom_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['quantitative_charge_properties_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["exactly", "EQ"],
        ["at least", "LE"],
        ["at most", "GE"],
        ["less than", "LT"],
        ["more than", "GT"]
      ]), "OP")
      .appendField(new Blockly.FieldNumber(0, 0), "NumberOfChargeProperty")
      .appendField(new Blockly.FieldDropdown([
        ["positive", "POSITIVE"],
        ["negative", "NEGATIVE"]
      ]), "SIGN")
      .appendField("charge(s)")
    this.setInputsInline(true);
    this.setOutput(true, 'quantitative_atom_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['quantitative_atom_loc'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["exactly", "EQ"],
        ["at least", "LE"],
        ["at most", "GE"],
        ["less than", "LT"],
        ["more than", "GT"]
      ]), "OP")
      .appendField(new Blockly.FieldNumber(0, 0), "NumberOfAtomProperty")
      .appendField(new Blockly.FieldDropdown([
        ["alpha", "ALPHA"],
        ["beta", "BETA"],
        ["gamma", "GAMMA"],
        ["within beta", "WBETA"],
        ["within gamma", "WGAMMA"]
      ]), "NEIGHBOR")
      .appendField("atom(s)")
    //.appendField(new Blockly.FieldDropdown([["including explicit hydrogens","INCHYDROGEN"], ["not including explicit hydrogens","NOTINCHYDROGEN"]]), "INCLUSION");
    this.setInputsInline(true);
    this.setOutput(true, 'quantitative_atom_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['quantitative_atom_loc2'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["exactly", "EQ"],
        ["at least", "LE"],
        ["at most", "GE"],
        ["less than", "LT"],
        ["more than", "GT"]
      ]), "OP")
      .appendField(new Blockly.FieldNumber(0, 0), "NumberOfAtomProperty")
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
      .appendField("atom(s)")
    //.appendField(new Blockly.FieldDropdown([["not including explicit hydrogens","INCHYDROGEN"], ["including explicit hydrogens","NOTINCHYDROGEN"]]), "INCLUSION");
    this.setInputsInline(true);
    this.setOutput(true, 'quantitative_atom_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['quantitative_atom_type_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["exactly", "EQ"],
        ["at least", "LE"],
        ["at most", "GE"],
        ["less than", "LT"],
        ["more than", "GT"]
      ]), "OP")
      .appendField(new Blockly.FieldNumber(0, 0), "NumberOfAtomType")
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
    this.setOutput(true, 'quantitative_atom_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['qualitative_atom_type_predicate'] = {
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
    this.setOutput(true, 'qualitative_atom_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};
Blockly.Blocks['quantitative_atom_bond_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["exactly", "EQ"],
        ["at least", "LE"],
        ["at most", "GE"],
        ["less than", "LT"],
        ["more than", "GT"]
      ]), "OP")
      .appendField(new Blockly.FieldNumber(0, 0), "NumberOfAtomProperty")
      .appendField(new Blockly.FieldDropdown([
        ["donating", "DONATING"],
        ["withdrawing", "WITHDRAWING"]
      ]), "PROPERTY")
      .appendField("bond(s)");
    this.setInputsInline(true);
    this.setOutput(true, 'quantitative_atom_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['qualitative_chemical_object_equivalent_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("equivalent to");
    this.appendValueInput("atomTypePredicate")
      .setCheck([
        "atom_subject",
        "bond_subject",
        "atom_set_subject",
        //"ring_subject",
        //"molecule_subject",
        'Generic',
        'Subject',
      ]);
    this.setInputsInline(true);
    this.setOutput(true, '_qualitative_chemical_object_equivalent_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['quantitative_hindered_comparison_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["less hindered than", "LESS_HINDERED"],
        ["more hindered than", "MORE_HINDERED"]
      ]), "HINDERED");
    this.appendValueInput("atom")
      .setCheck([
        "atom_subject",
        "bond_subject",
        "selected_atom_by_loc",
        "selected_atom_by_loc2",
        'Generic',
        'Subject',
      ]);
    this.setInputsInline(true);
    this.setOutput(true, '_quantitative_hindered_comparison_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['qualitative_bond_properties_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["allylic", "ALLYLIC"],
        ["anti", "ANTI"],
        ["anti to", "ANTITO"],
        ["anti to about the path...", "ANTITO2"],
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
        ["syn to", "SYNTO"],
        ["syn to about the path...", "SYNTO2"],
        ["trans-olefin", "TRANS_OLEFIN"],
        ["triple bond", "TRIPLE_BOND"],
        ["withdrawing", "WITHDRAWING"]
      ], this.validate), "PROPERTY")
    this.setInputsInline(true);
    this.setOutput(true, 'qualitative_bond_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  },
  validate: function (newValue) {
    this.getSourceBlock().updateConnections(newValue);
    return newValue;
  },

  updateConnections: function (newValue) {
    if (this.getInput("atom2")) {
      this.removeInput("atom2");
      this.removeInput("atom3");
      this.removeInput("atom4");
      this.removeInput("path1");
      this.removeInput("path2");
    } else if (this.getInput("atom5")) {
      this.removeInput("atom5");
      this.removeInput("atom6");
      this.removeInput("atom7");
      this.removeInput("path3");
      this.removeInput("path4");
    } else if (this.getInput("atom9")) {
      this.removeInput("atom9");
    }
    if (newValue == 'CISTO' || newValue == 'TRANSTO') {
      this.appendValueInput("atom2")
        .setCheck(["atom_subject",
          "Generic",
          "Subject"
        ]);
      this.appendDummyInput("path1")
        .appendField("about the ring containing bond between");
      this.appendValueInput("atom3")
        .setCheck(["atom_subject",
          "Generic",
          "Subject"
        ]);
      this.appendDummyInput("path2")
        .appendField("and");
      this.appendValueInput("atom4")
        .setCheck(["atom_subject",
          "Generic",
          "Subject"
        ]);
    }else if (newValue == 'ANTITO' || newValue == 'SYNTO') {
      this.appendValueInput("atom9")
        .setCheck(["atom_subject",
          "Generic",
          "Subject"
        ]);
    } else if (newValue == 'ANTITO2' || newValue == 'SYNTO2') {
      this.appendValueInput("atom5")
        .setCheck(["atom_subject",
          "Generic",
          "Subject"
        ]);
      this.appendDummyInput("path3")
        .appendField("about the path from");
      this.appendValueInput("atom6")
        .setCheck(["atom_subject",
          "Generic",
          "Subject"
        ]);
      this.appendDummyInput("path4")
        .appendField("to");
      this.appendValueInput("atom7")
        .setCheck(["atom_subject",
          "Generic",
          "Subject"
        ]);
    }
  }
};

Blockly.Blocks['qualitative_group_properties_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("the origin of")
      .appendField(new Blockly.FieldDropdown([
        ["donating", "DONATING"],
        ["non expandable withdrawing", "NON_EXPANDABLE_WITHDRAWING"],
        ["expandable withdrawing ", "EXPANDABLE_WITHDRAWING"],
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
    this.setOutput(true, 'qualitative_group_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['qualitative_functional_group_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("the origin of")
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
    this.setOutput(true, 'qualitative_group_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['qualitative_other_group_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("the origin of")
      .appendField(new Blockly.FieldDropdown([
        ["isopropyl", "ISOPROPYL"],
        ["methyl", "METHYL"],
        ["phenyl", "PHENYL"],
        ["t-butyl", "T_BUTYL"]
      ]), "OTHER")
      .appendField("group");
    this.setInputsInline(true);
    this.setOutput(true, 'qualitative_group_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['qualitative_smarts_group_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("the origin of")
      .appendField(new Blockly.FieldTextInput("Enter SMARTS"), "SMARTS")
      .appendField("group");
    this.setInputsInline(true);
    this.setOutput(true, 'qualitative_group_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['quantitative_group_properties_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["exactly", "EQ"],
        ["at least", "LE"],
        ["at most", "GE"],
        ["more than", "GT"],
        ["less than", "LT"]
      ]), "OP")
      .appendField(new Blockly.FieldNumber(0, 0), "NumberOfGroupType")
      //.appendField("alpha")
      .appendField(new Blockly.FieldDropdown([
        ["donating", "DONATING"],
        ["non expandable withdrawing", "NON_EXPANDABLE_WITHDRAWING"],
        ["expandable withdrawing ", "EXPANDABLE_WITHDRAWING"],
        ["interfering", "INTERFERING"],
        ["good leaving", "GOOD_LEAVING"],
        ["leaving", "LEAVING"],
        ["participating", "PARTICIPATING"],
        ["protected", "PROTECTED"],
        ["vinyl-d", "VINYL_D"],
        ["vinyl-w", "VINYL_W"],
        ["withdrawing", "WITHDRAWING"]
      ]), "PROPERTY")
      .appendField("group(s)");
    this.setInputsInline(true);
    this.setOutput(true, 'quantitative_group_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['quantitative_functional_group_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["exactly", "EQ"],
        ["at least", "LE"],
        ["at most", "GE"],
        ["more than", "GT"],
        ["less than", "LT"]
      ]), "OP")
      .appendField(new Blockly.FieldNumber(0, 0), "NumberOfFunctionalGroup")
      //.appendField("alpha")
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
      .appendField("group(s)");
    this.setInputsInline(true);
    this.setOutput(true, 'quantitative_group_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['quantitative_other_group_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["exactly", "EQ"],
        ["at least", "LE"],
        ["at most", "GE"],
        ["more than", "GT"],
        ["less than", "LT"]
      ]), "OP")
      .appendField(new Blockly.FieldNumber(0, 0), "NumberOfOtherGroup")
      //.appendField("alpha")
      .appendField(new Blockly.FieldDropdown([
        ["isopropyl", "ISOPROPYL"],
        ["methyl", "METHYL"],
        ["phenyl", "PHENYL"],
        ["t-butyl", "T_BUTYL"]
      ]), "OTHER")
      .appendField("group(s)");
    this.setInputsInline(true);
    this.setOutput(true, 'quantitative_group_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['quantitative_smarts_group_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField(new Blockly.FieldDropdown([
        ["exactly", "EQ"],
        ["at least", "LE"],
        ["at most", "GE"],
        ["more than", "GT"],
        ["less than", "LT"]
      ]), "OP")
      .appendField(new Blockly.FieldNumber(0, 0), "NumberOfSmartsGroup")
      //.appendField("alpha")
      .appendField(new Blockly.FieldTextInput("Enter SMARTS"), "SMARTS")
      .appendField("group(s)");
    this.setInputsInline(true);
    this.setOutput(true, 'quantitative_group_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['qualitative_ring_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("in ring")
    this.setInputsInline(true);
    this.setOutput(true, 'ring_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['qualitative_ring_size_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("in ring of size")
      .appendField(new Blockly.FieldNumber(0, 0), "minRingSize");
    this.setInputsInline(true);
    this.setOutput(true, 'ring_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['qualitative_ring_size_predicate2'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("in ring of size")
      .appendField(new Blockly.FieldNumber(0, 0), "minRingSize")
      .appendField(new Blockly.FieldDropdown([
        ["through", "THROUGH"],
        ["or smaller", "ORSMALLER"],
        ["or larger", "ORLARGER"]
      ], this.validate), "SIZE");
    this.setInputsInline(true);
    this.setOutput(true, 'ring_predicate');
    this.setColour(210);
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

Blockly.Blocks['qualitative_ring_properties_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("in")
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
    this.setOutput(true, 'ring_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['qualitative_ring_size_properties_predicate'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("in")
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
    this.setOutput(true, 'ring_predicate');
    this.setColour(210);
    this.setTooltip("");
    this.setHelpUrl("");
  }
};

Blockly.Blocks['qualitative_ring_size_properties_predicate2'] = {
  init: function () {
    this.appendDummyInput()
      .appendField("in")
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
    this.setOutput(true, 'ring_predicate');
    this.setColour(210);
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