"use strict";

goog.provide("Blockly.SLICE.predicates");

goog.require("Blockly.SLICE");

var LOCS = {
  ALPHA: "alpha",
  BETA: "beta",
  GAMMA: "gamma",
  WBETA: "within beta",
  WGAMMA: "within gamma",
  RADICALIZE: "radicalize",
  RACEMIZE: "racemize at",
};

var ATOM_PROPERTY = {
  ALKALI_METAL: "alkali metal",
  ALKYL: "alkyl",
  ALLYLIC: "allylic",
  ANTI: "anti",
  ANTITO: "anti to",
  ANTITO2: "anti to",
  AROMATIC: "aromatic",
  BENZYLIC: "benzylic",
  BREDT_STRAINED: "bredt-strained",
  BRIDGEHEAD: "bridgehead",
  CIS_OLEFIN: "cis-olefin",
  CIS: "cis",
  CISTO: "cis to",
  CONJUGATED: "conjugated",
  DOUBLY: "doubly",
  ENOLIZABLE: "enolizable",
  FUSION: "fusion",
  HALOGEN: "halogen",
  HETERO: "hetero",
  MULTIPLY: "multiply bonded",
  STRAINED: "strained",
  SYN: "syn",
  SYNTO: "syn to",
  SYNTO2: "syn to",
  TRANS_OLEFIN: "trans-olefin",
  TRANS: "trans",
  TRANSTO: "trans to",
  TRIPLY: "triply"
};

var BOND_PROPERTY = {
  ALLYLIC: "allylic",
  ANTI: "anti",
  ANTITO: "anti to",
  ANTITO2: "anti to",
  AROMATIC: "aromatic",
  BREDT_STRAINED: "bredt-strained",
  BRIDGEHEAD: "bridgehead",
  CIS_OLEFIN: "cis-olefin",
  CONJUGATED: "conjugated",
  DONATING: "donating",
  DOUBLE_BOND: "double bond",
  FUSION: "fusion",
  MULTIPLE: "multiple",
  SINGLE_BOND: "single bond",
  SMALL_FUSION: "small fusion",
  STRAINED: "strained",
  SYN: "syn",
  SYNTO: "syn to",
  SYNTO2: "syn to",
  TRANS_OLEFIN: "trans-olefin",
  TRIPLE_BOND: "triple bond",
  WITHDRAWING: "withdrawing"
};

var GROUP_PROPERTY = {
  DONATING: "donating",
  NON_EXPANDABLE_WITHDRAWING: "non expandable withdrawing",
  EXPANDABLE_WITHDRAWING: "expandable withdrawing",
  INTERFERING: "interfering",
  GOOD_LEAVING: "good leaving",
  LEAVING: "leaving",
  PARTICIPATING: "participating",
  PROTECTED: "protected",
  VINYL_D: "vinyl-D",
  VINYL_W: "vinyl-W",
  WITHDRAWING: "withdrawing"
};

var FUNCTIONAL_GROUP = {
  ACETAL: "acetal",
  ACETYLENE: "acetylene",
  ACID_HALIDE: "acid halide",
  ALCOHOL: "alcohol",
  ALDEHYDE: "aldehyde",
  ALLENE: "allene",
  AMIDE1: "primary amide",
  AMIDE2: "secondary amide",
  AMIDE3: "tertiary amide",
  AMIDE: "amide",
  AMIDZ: "amidz",
  AMINE1: "primary amine",
  AMINE2: "secondary amine",
  AMINE3: "tertiary amine",
  AMINE: "amine",
  AMINE_OXIDE: "amine oxide",
  ANHYDRIDE: "anhydride",
  AZIDE: "azide",
  AZIRIDINE: "aziridine",
  AZO: "azo",
  BROMIDE: "bromide",
  C_SULFONATE: "C-sulfonate",
  CARBAMATE_C: "carbamate-C",
  CARBAMATE_H: "carbamate-H",
  CARBONIUM: "carbonium",
  CARBONYL: "carbonyl",
  CARBOXYL: "carboxylic acid",
  CARBOXYL_GROUP: "carboxyl",
  CHLORIDE: "chloride",
  CYANO: "cyano",
  DIAZO: "diazo",
  DISULFIDE: "disulfide",
  DITHIOACETAL: "dithioacetal",
  DITHIOKETAL: "dithioketal",
  ENAMINE: "enamine",
  ENOL_ETHER: "enol-ether",
  EPISULFIDE: "episulfide",
  EPOXIDE: "epoxide",
  ESTER: "ester",
  ESTERX: "esterx",
  ETHER: "ether",
  FLUORIDE: "fluoride",
  FUNCTIONAL: "functional",
  GEM_DIHALIDE: "gem-dihalide",
  GLYCOL: "glycol",
  HALIDE: "halide",
  HALOAMINE: "haloamine",
  HALOHYDRIN: "halohydrin",
  HEMIACETAL: "hemiacetal",
  HYDRATE: "hydrate",
  HYDRAZONE: "hydrazone",
  HYDROXYLAMINE: "hydroxylamine",
  IMINE: "imine",
  IODIDE: "iodide",
  ISOCYANATE: "isocyanate",
  ISOCYANIDE: "isocyanide",
  KETONE: "ketone",
  LACTAM: "lactam",
  LACTONE: "lactone",
  METHYLENE: "methylene",
  N_CARBAMATE: "N-carbamate",
  N_UREA_C: "N-urea-C",
  N_UREA_H: "N-urea-H",
  NITRITE: "nitrite",
  NITRO: "nitro",
  NITROSO: "nitroso",
  O_CARBAMATE: "O-carbamate",
  O_CARBONATE: "O-carbonate",
  O_SULFONATE: "O-sulfonate",
  OLEFIN: "olefin",
  OXIME: "oxime",
  PEROXIDE: "peroxide",
  PHOSPHINE: "phosphine",
  PHOSPHONATE: "phosphonate",
  SELENIDE: "selenide",
  SILYLENOLETHER: "silylenolether",
  SULFIDE: "sulfide",
  SULFONE: "sulfone",
  SULFOXIDE: "sulfoxide",
  THIOCYANATE: "thiocyanate",
  THIOESTER: "thioester",
  THIOL: "thiol",
  TRIALKYLSILOXY: "trialkylsiloxy",
  TRIALKYLSILYL: "trialkylsilyl",
  TRIHALIDE: "trihalide",
  VIC_DIHALIDE: "vic-dihalide",
  VINYLSILANE: "vinylsilane"
}

var OTHER_GROUP = {
  ISOPROPYL: "isopropyl",
  METHYL: "methyl",
  PHENYL: "phenyl",
  T_BUTYL: "t-butyl"
};

var CENTER = {
  PRIMARY: "primary",
  SECONDARY: "secondary",
  TERTIARY: "tertiary",
  QUATERNARY: "quaternary"
};

var OPERATOR = {
  EQ: "exactly",
  LE: "at least",
  GE: "at most",
  LT: "less than",
  GT: "more than"
};

var SIGN = {
  POSITIVE: "positive",
  NEGATIVE: "negative"
};

var LESS_MORE = {
  LESS_HINDERED: "less",
  MORE_HINDERED: "more"
};

var SIZE = {
  THROUGH: "through",
  ORSMALLER: "or smaller",
  ORLARGER: "or larger"
}

var RING_PREFIX = {
  AN: "an",
  CARBOCYCLIC: "carbocyclic",
  COMMON: "common",
  HETEROCYCLIC: "heterocyclic"
}

var AROMATICITY = {
  ALIPHATIC_OR_AROMATIC: "aliphatic or aromatic",
  ALIPHATIC: "aliphatic",
  AROMATIC: "aromatic"
}

var ATOM_TYPE = {
  CARBON: "carbon",
  NITROGEN: "nitrogen",
  OXYGEN: "oxygen",
  FLUORINE: "fluorine",
  PHOSPHORUS: "phosphorus",
  SULFUR: "sulfur",
  CHLORINE: "chlorine",
  BROMINE: "bromine",
  IODINE: "iodine",
  HYDROGEN: "hydrogen",
  HELIUM: "helium",
  LITHIUM: "lithium",
  BERYLLIUM: "beryllium",
  BORON: "boron",
  NEON: "neon",
  SODIUM: "sodium",
  MAGNESIUM: "magnesium",
  ALUMINIUM: "aluminium",
  SILICON: "silicon",
  ARGON: "argon",
  POTASSIUM: "potassium",
  CALCIUM: "calcium",
  SCANDIUM: "scandium",
  TITANIUM: "titanium",
  VANADIUM: "vanadium",
  CHROMIUM: "chromium",
  IRON: "iron",
  COBALT: "cobalt",
  NICKEL: "nickel",
  COPPER: "copper",
  ZINC: "zinc",
  GALLIUM: "gallium",
  GERMANIUM: "germanium",
  ARSENIC: "arsenic",
  SELENIUM: "selenium",
  KRYPTON: "krypton",
  RUBIDIUM: "rubidium",
  STRONTIUM: "strontium",
  YTTRBIUM: "yttrbium",
  ZIRCONIUM: "zirconium",
  NIOBIUM: "niobium",
  MOLYBDENUM: "molybdenum",
  TECHNETIUM: "technetium",
  RUTHENIUM: "ruthenium",
  RHODIUM: "rhodium",
  PALLADIUM: "palladium",
  SILVER: "silver",
  CADMIUM: "cadmium",
  INDIUM: "indium",
  TIN: "tin",
  ANTIMONY: "antimony",
  TELLURIUM: "tellurium",
  XENON: "xenon",
  CESIUM: "cesium",
  BARIUM: "barium",
  LANTHANUM: "lanthanum",
  HAFNIUM: "hafnium",
  TANTALUM: "tantalum",
  TUNGSTEN: "tungsten",
  RHENIUM: "rhenium",
  OSMIUM: "osmium",
  IRIDIUM: "iridium",
  PLATINUM: "platinum",
  GOLD: "gold",
  MERCURY: "mercury",
  THALLIUM: "thallium",
  LEAD: "lead",
  BISMUTH: "bismuth",
  POLONIUM: "polonium",
  ASTATINE: "astatine",
  RADON: "radon",
  FRANCIUM: "francium",
  RADIUM: "radium",
  ACTINOIDS: "actinoids",
  RUTHERFORDIUM: "rutherfordium",
  DUBNIUM: "dubnium",
  SEABORGIUM: "seaborgium",
  BOHRIUM: "bohrium",
  HASSIUM: "hassium",
  MEITNERIUM: "meitnerium",
  DARMSTADIUM: "darmstadium",
  ROENTGENIUM: "roentgenium",
  COPERNICIUM: "copernicium",
  NIHONIUM: "nihonium",
  FLEROVIUM: "flerovium",
  MOSCOVIUM: "moscovium",
  LIVERMORIUM: "livermorium",
  TENNESSINE: "tennessine",
  OGANESSON: "oganesson",
  CERIUM: "cerium",
  PRASEODYMIUM: "praseodymium",
  NEODYMIUM: "neodymium",
  PROMETHIUM: "promethium",
  SAMARIUM: "samarium",
  EUROPIUM: "europium",
  GADOLINIUM: "gadolinium",
  TERBIUM: "terbium",
  DYSPROSIUM: "dysprosium",
  HOLMIUM: "holmium",
  ERBIUM: "erbium",
  THULIUM: "thulium",
  LUTETIUM: "lutetium",
  THORIUM: "thorium",
  PROTACTINIUM: "protactinium",
  URANIUM: "uranium",
  NEPTUNIUM: "neptunium",
  PLUTONIUM: "plutonium",
  AMERICIUM: "americium",
  CURIUM: "curium",
  BERKELIUM: "berkelium",
  CALIFORNIUM: "californium",
  EINSTEINIUM: "einsteinium",
  FERMIUM: "fermium",
  MENDELEVIUM: "mendelevium",
  NOBELIUM: "nobelium",
  LAWRENCIUM: "lawrencium"
};

var H_INCLUSION = {
  INCHYDROGEN: "including explicit hydrogens",
  NOTINCHYDROGEN: "not including explicit hydrogens",
};

Blockly.SLICE["qualitative_atom_properties_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var property = ATOM_PROPERTY[block.getFieldValue("PROPERTY")];
  var code = property;
  if (block.getInput('atom2')) {
    var argument1 = Blockly.SLICE.valueToCode(block, 'atom2', order) || 'cisTrandAtom2';
    var argument2 = Blockly.SLICE.valueToCode(block, 'atom3', order) || 'atomOrBond1';
    var argument3 = Blockly.SLICE.valueToCode(block, 'atom4', order) || 'atomOrBond2';
    code += " " + argument1 + " about the ring containing bond between " + argument2 + " and " + argument3;
  } else if (block.getInput('atom5')) {
    var argument1 = Blockly.SLICE.valueToCode(block, 'atom5', order) || 'antiSynAtom2';
    var argument2 = Blockly.SLICE.valueToCode(block, 'atom6', order) || 'atomOrBond1';
    var argument3 = Blockly.SLICE.valueToCode(block, 'atom7', order) || 'atomOrBond2';
    code += " " + argument1 + " about the path from " + argument2 + " to " + argument3;
  }
  else if (block.getInput('atom9')) {
    var argument1 = Blockly.SLICE.valueToCode(block, 'atom9', order) || 'antiSynAtom2';
    code += " " + argument1 ;
  }
  return [code, order];
};

Blockly.SLICE["quantitative_atom_properties_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var op = OPERATOR[block.getFieldValue("OP")];
  var property = ATOM_PROPERTY[block.getFieldValue("PROPERTY")];
  var num = block.inputList[0].fieldRow[1].value_;
  var code = op + " " + num + " " + property + " atom";
  return [code, order];
};


Blockly.SLICE["qualitative_center_properties_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var center = CENTER[block.getFieldValue("CENTER")];
  var code = center + " center";
  return [code, order];
};

Blockly.SLICE["quantitative_charge_properties_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var op = OPERATOR[block.getFieldValue("OP")];
  var sign = SIGN[block.getFieldValue("SIGN")];
  var num = block.inputList[0].fieldRow[1].value_;
  var code = op + " " + num + " " + sign + " charge";
  return [code, order];
};

Blockly.SLICE["quantitative_atom_type_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var op = OPERATOR[block.getFieldValue("OP")];
  var type = ATOM_TYPE[block.getFieldValue("TYPE")];
  var num = block.inputList[0].fieldRow[1].value_;
  var code = op + " " + num + " " + type + " atom";
  return [code, order];
};

Blockly.SLICE["quantitative_atom_loc"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var op = OPERATOR[block.getFieldValue("OP")];
  var neighbor = LOCS[block.getFieldValue("NEIGHBOR")];
  //var hydrogen = H_INCLUSION[block.getFieldValue("INCLUSION")];
  var num = block.inputList[0].fieldRow[1].value_;
  var code = op + " " + num + " " + neighbor + " atom " /*+ hydrogen*/ ;
  return [code, order];
};

Blockly.SLICE["quantitative_atom_loc2"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var op = OPERATOR[block.getFieldValue("OP")];
  var neighbor = LOCS[block.getFieldValue("NEIGHBOR")];
  var type = ATOM_TYPE[block.getFieldValue("TYPE")];
  //var hydrogen = H_INCLUSION[block.getFieldValue("INCLUSION")];
  var num = block.inputList[0].fieldRow[1].value_;
  var code = op + " " + num + " " + neighbor + " " + type + " atom " /*+ hydrogen*/ ;
  return [code, order];
};

Blockly.SLICE["qualitative_atom_type_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var type = ATOM_TYPE[block.getFieldValue("TYPE")];
  var code = type + " atom";
  return [code, order];
};

Blockly.SLICE["quantitative_atom_bond_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var op = OPERATOR[block.getFieldValue("OP")];
  var type = BOND_PROPERTY[block.getFieldValue("PROPERTY")];
  var num = block.inputList[0].fieldRow[1].value_;
  var code = op + " " + num + " " + type + " bond";
  return [code, order];
};

Blockly.SLICE["qualitative_chemical_object_equivalent_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var argument = Blockly.SLICE.valueToCode(block, 'atomTypePredicate', order) || 'subject';
  var code = "equivalent to " + argument;
  return [code, order];
};

Blockly.SLICE["quantitative_hindered_comparison_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var op = LESS_MORE[block.getFieldValue("HINDERED")];
  var argument = Blockly.SLICE.valueToCode(block, 'atom', order) || 'atomToDefine';
  var code = op + " hindered than " + argument;
  return [code, order];
};

Blockly.SLICE["qualitative_bond_properties_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var property = BOND_PROPERTY[block.getFieldValue("PROPERTY")];
  var code = property;
  if (block.getInput('atom5')) {
    var argument1 = Blockly.SLICE.valueToCode(block, 'atom5', order) || 'antiSynAtom2';
    var argument2 = Blockly.SLICE.valueToCode(block, 'atom6', order) || 'atomOrBond1';
    var argument3 = Blockly.SLICE.valueToCode(block, 'atom7', order) || 'atomOrBond2';
    code += " " + argument1 + " about the path from " + argument2 + " to " + argument3;
  }
  else if (block.getInput('atom9')) {
    var argument1 = Blockly.SLICE.valueToCode(block, 'atom9', order) || 'antiSynAtom2';
    code += " " + argument1 ;
  }
  return [code, order];
};

Blockly.SLICE["qualitative_group_properties_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var property = GROUP_PROPERTY[block.getFieldValue("PROPERTY")];
  var code = property  + " group";
  return [code, order];
};

Blockly.SLICE["qualitative_functional_group_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var type = FUNCTIONAL_GROUP[block.getFieldValue("FUNCTIONAL")];
  var code = type + " group";
  return [code, order];
};

Blockly.SLICE["qualitative_other_group_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var type = OTHER_GROUP[block.getFieldValue("OTHER")];
  var code = type + " group";
  return [code, order];
};

Blockly.SLICE["qualitative_smarts_group_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var smarts = block.inputList[0].fieldRow[1].value_;
  var code = smarts + " group";
  return [code, order];
};

Blockly.SLICE["quantitative_group_properties_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var op = OPERATOR[block.getFieldValue("OP")];
  var type = GROUP_PROPERTY[block.getFieldValue("PROPERTY")];
  var num = block.inputList[0].fieldRow[1].value_;
  var code = op + " " + num + " " + type + " group";
  return [code, order];
};

Blockly.SLICE["quantitative_functional_group_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var op = OPERATOR[block.getFieldValue("OP")];
  var type = FUNCTIONAL_GROUP[block.getFieldValue("FUNCTIONAL")];
  var num = block.inputList[0].fieldRow[1].value_;
  var code = op + " " + num + " " + type + " group";
  return [code, order];
};

Blockly.SLICE["quantitative_other_group_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var op = OPERATOR[block.getFieldValue("OP")];
  var type = OTHER_GROUP[block.getFieldValue("OTHER")];
  var num = block.inputList[0].fieldRow[1].value_;
  var code = op + " " + num + " " + type + " group";
  return [code, order];
};

Blockly.SLICE["quantitative_smarts_group_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var op = OPERATOR[block.getFieldValue("OP")];
  var num = block.inputList[0].fieldRow[1].value_;
  var smarts = block.inputList[0].fieldRow[2].value_;
  var code = op + " " + num + " " + smarts + " group";
  return [code, order];
};

Blockly.SLICE["qualitative_ring_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var code = "in ring";
  return [code, order];
};

Blockly.SLICE["qualitative_ring_size_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var code = "in ring of size " + block.inputList[0].fieldRow[1].value_;
  return [code, order];
};

Blockly.SLICE["qualitative_ring_size_predicate2"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var range = SIZE[block.getFieldValue("SIZE")];
  var size = block.inputList[0].fieldRow[1].value_;

  var code = "in ring of size " + size + " " + range;
  if (block.getInput('maxRingSizeInput')) {
    var size2 = block.inputList[1].fieldRow[0].value_;
    code += " " + size2;
  }
  return [code, order];
};

Blockly.SLICE["qualitative_ring_properties_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var prefix = RING_PREFIX[block.getFieldValue("PREFIX")];
  var aromaticity = AROMATICITY[block.getFieldValue("AROMATICITY")];

  var code = "in " + prefix + " " + aromaticity + " ring";
  return [code, order];
};

Blockly.SLICE["qualitative_ring_size_properties_predicate"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var prefix = RING_PREFIX[block.getFieldValue("PREFIX")];
  var aromaticity = AROMATICITY[block.getFieldValue("AROMATICITY")];
  var size = block.inputList[0].fieldRow[4].value_;

  var code = "in " + prefix + " " + aromaticity + " ring of size " + size;
  return [code, order];
};

Blockly.SLICE["qualitative_ring_size_properties_predicate2"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var prefix = RING_PREFIX[block.getFieldValue("PREFIX")];
  var aromaticity = AROMATICITY[block.getFieldValue("AROMATICITY")];
  var range = SIZE[block.getFieldValue("SIZE")];
  var size = block.inputList[0].fieldRow[4].value_;

  var code = "in " + prefix + " " + aromaticity + " ring of size " + size + " " + range;
  if (block.getInput('maxRingSizeInput')) {
    var size2 = block.inputList[1].fieldRow[0].value_;
    code += " " + size2;
  }
  return [code, order];
};