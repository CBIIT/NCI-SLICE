"use strict";

goog.provide("Blockly.SLICE.loops");

goog.require("Blockly.SLICE");

var SIZE = {
  THROUGH: "through",
  ORSMALLER: "or smaller",
  ORLARGER: "or larger",
};

var RING_PREFIX = {
  AN: "an",
  CARBOCYCLIC: "carbocyclic",
  COMMON: "common",
  HETEROCYCLIC: "heterocyclic",
};

var AROMATICITY = {
  ALIPHATIC_OR_AROMATIC: "aliphatic or aromatic",
  ALIPHATIC: "aliphatic",
  AROMATIC: "aromatic",
};

var LOCALIZATION = {
  NULL: "",
  ANYWHERE: "anywhere",
  ONPATH: "onpath",
  OFFPATH: "offpath",
  ONRING: "onring",
  OFFRING: "offring",
  ONCURRENTRING: "on current ring",
  OFFCURRENTRING: "off current ring",
  ONTHEBRIDGE: "on current bridge",
  OFFTHEBRIDGE: "off current bridge",
};

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
  LAWRENCIUM: "lawrencium",
};

var ATOM_PROPERTY = {
  ALKALI_METAL: "alkali metal",
  ALLYLIC: "allylic",
  ANTI: "anti",
  AROMATIC: "aromatic",
  BENZYLIC: "benzylic",
  BREDT_STRAINED: "bredt-strained",
  BRIDGEHEAD: "bridgehead",
  CIS_OLEFIN: "cis-olefin",
  CIS: "cis",
  CONJUGATED: "conjugated",
  DOUBLY: "doubly",
  ENOLIZABLE: "enolizable",
  FUSION: "fusion",
  HALOGEN: "halogen",
  HETERO: "hetero",
  MULTIPLY: "multiply bonded",
  STRAINED: "strained",
  SYN: "syn",
  TRANS_OLEFIN: "trans-olefin",
  TRANS: "trans",
  TRIPLY: "triply",
};

var BOND_PROPERTY = {
  ALLYLIC: "allylic",
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
  TRANS_OLEFIN: "trans-olefin",
  TRIPLE_BOND: "triple bond",
  WITHDRAWING: "withdrawing",
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
  AMIDE: "amine",
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

var LOCS = {
  ALPHA: "alpha",
  BETA: "beta",
  GAMMA: "gamma",
  WBETA: "within beta",
  WGAMMA: "within gamma",
  RADICALIZE: "radicalize",
  RACEMIZE: "racemize at",
};

var H_INCLUSION = {
  INCHYDROGEN: "including explicit hydrogens",
  NOTINCHYDROGEN: "not including explicit hydrogens",
};


Blockly.SLICE["foreach_loop"] = function (block) {
  var subject =
    Blockly.SLICE.valueToCode(block, "Subject", Blockly.SLICE.ORDER_NONE) ||
    "SubjectToDefine";
  var where = LOCALIZATION[block.getFieldValue("LOCALIZATION")];
  var variable = Blockly.SLICE.nameDB_.getName(
    block.getFieldValue("VAR"),
    Blockly.VARIABLE_CATEGORY_NAME
  );
  var predicate =
    Blockly.SLICE.valueToCode(block, "Predicate", Blockly.SLICE.ORDER_NONE) ||
    "PredicateToDefine";

  var indexVar = Blockly.SLICE.nameDB_.getDistinctName(
    variable + "_index",
    Blockly.VARIABLE_CATEGORY_NAME
  );

  var branch = Blockly.SLICE.statementToCode(block, "do");
  branch = Blockly.SLICE.addLoopTrap(branch, block);
  var code = "";

  code +=
    "foreach " +
    subject +
    " " +
    where +
    " defined as " +
    indexVar.split("_index")[0] +
    " in " +
    predicate +
    " {\n" +
    branch +
    "}\n";
  return code;
};

Blockly.SLICE["atom_loop"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var code = "atom";
  return [code, order];
};

Blockly.SLICE["atom_type_loop"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var type = ATOM_TYPE[block.getFieldValue("TYPE")];
  var code = type + " atom";
  return [code, order];
};

Blockly.SLICE["atom_properties_loop"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var property = ATOM_PROPERTY[block.getFieldValue("PROPERTY")];
  var code = property + " atom";
  return [code, order];
};

Blockly.SLICE["atom_type_and_properties_loop"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var property = ATOM_PROPERTY[block.getFieldValue("PROPERTY")];
  var type = ATOM_TYPE[block.getFieldValue("TYPE")];
  var code = property + " " + type + " atom";
  return [code, order];
};

Blockly.SLICE["atom_by_loc_loop"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var neighbor = LOCS[block.getFieldValue("NEIGHBOR")];
  //var hydrogen = H_INCLUSION[block.getFieldValue("INCLUSION")];
  var argument = Blockly.SLICE.valueToCode(block, "Object", order) || "subject";

  var code = neighbor + " to " + argument /*+ " " + hydrogen*/ ;
  return [code, order];
};

Blockly.SLICE["atom_by_loc_loop2"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var type = ATOM_TYPE[block.getFieldValue("TYPE")];
  var neighbor = LOCS[block.getFieldValue("NEIGHBOR")];
  //var hydrogen = H_INCLUSION[block.getFieldValue("INCLUSION")];
  var argument = Blockly.SLICE.valueToCode(block, "Object", order) || "subject";

  var code = type + " " + neighbor + " to " + argument /*+ " " + hydrogen*/ ;
  return [code, order];
};

Blockly.SLICE["atom_on_bond_loop"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var argument = Blockly.SLICE.valueToCode(block, "Bond", order) || "bondToDefine";
  var code = "atom on " + argument;
  return [code, order];
};


Blockly.SLICE["bond_loop"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var code = "bond";
  return [code, order];
};

Blockly.SLICE["bond_properties_loop"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var property = BOND_PROPERTY[block.getFieldValue("PROPERTY")];
  var code = property + " bond";
  return [code, order];
};

Blockly.SLICE["bond_on_atom_loop"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var argument = Blockly.SLICE.valueToCode(block, "Atom", order) || "atomToDefine";
  var code = "atom on " + argument;
  return [code, order];
};

Blockly.SLICE["ring_loop"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var code = "ring";
  return [code, order];
};

Blockly.SLICE["ring_size_loop"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var code = "ring of size " + block.inputList[0].fieldRow[1].value_;
  return [code, order];
};

Blockly.SLICE["ring_size_loop2"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var range = SIZE[block.getFieldValue("SIZE")];
  var size = block.inputList[0].fieldRow[1].value_;

  var code = "ring of size " + size + " " + range;
  if (block.getInput("maxRingSizeInput")) {
    var size2 = block.inputList[1].fieldRow[0].value_;
    code += " " + size2;
  }
  return [code, order];
};

Blockly.SLICE["ring_properties_loop"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var prefix = RING_PREFIX[block.getFieldValue("PREFIX")];
  var aromaticity = AROMATICITY[block.getFieldValue("AROMATICITY")];

  var code = prefix + " " + aromaticity + " ring";
  return [code, order];
};

Blockly.SLICE["ring_size_properties_loop"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var prefix = RING_PREFIX[block.getFieldValue("PREFIX")];
  var aromaticity = AROMATICITY[block.getFieldValue("AROMATICITY")];
  var size = block.inputList[0].fieldRow[3].value_;

  var code = prefix + " " + aromaticity + " ring of size " + size;
  return [code, order];
};

Blockly.SLICE["ring_size_properties_loop2"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var prefix = RING_PREFIX[block.getFieldValue("PREFIX")];
  var aromaticity = AROMATICITY[block.getFieldValue("AROMATICITY")];
  var range = SIZE[block.getFieldValue("SIZE")];
  var size = block.inputList[0].fieldRow[3].value_;

  var code = prefix + " " + aromaticity + " ring of size " + size + " " + range;
  if (block.getInput("maxRingSizeInput")) {
    var size2 = block.inputList[1].fieldRow[0].value_;
    code += " " + size2;
  }
  return [code, order];
};

Blockly.SLICE["group_properties_loop"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var type = GROUP_PROPERTY[block.getFieldValue("PROPERTY")];
  var code = type + " group";
  return [code, order];
};

Blockly.SLICE["functional_group_loop"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var type = FUNCTIONAL_GROUP[block.getFieldValue("FUNCTIONAL")];
  var code = type + " group";
  return [code, order];
};

Blockly.SLICE["other_group_loop"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var type = OTHER_GROUP[block.getFieldValue("OTHER")];
  var code = type + " group";
  return [code, order];
};