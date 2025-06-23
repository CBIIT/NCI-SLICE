"use strict";

goog.provide("Blockly.SLICE.subjects");

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

var H_INCLUSION = {
  INCHYDROGEN: "including explicit hydrogens",
  NOTINCHYDROGEN: "not including explicit hydrogens",
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

Blockly.SLICE["select_atom_by_id"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var code = "atom " + Number(block.inputList[0].fieldRow[1].value_);
  return [code, order];
};

Blockly.SLICE["select_bond_by_range"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var input = block.inputList[0].fieldRow;
  var code =
    "bond between atom " +
    Number(input[1].value_) +
    " and " +
    Number(input[3].value_);
  return [code, order];
};

Blockly.SLICE["select_bond_by_range2"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var argument1 = Blockly.SLICE.valueToCode(block, "Atom1", order) || "atom1ToDefine";
  var argument2 = Blockly.SLICE.valueToCode(block, "Atom2", order) || "atom2ToDefine";

  var code = "bond between " + argument1 + " and " + argument2;
  return [code, order];
};

Blockly.SLICE["select_atom_by_loc"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var neighbor = LOCS[block.getFieldValue("NEIGHBOR")];
  //var hydrogen = H_INCLUSION[block.getFieldValue("INCLUSION")];
  var argument = Blockly.SLICE.valueToCode(block, "Object", order) || "subject";

  var code = neighbor + " to " + argument /*+ " " + hydrogen*/ ;
  return [code, order];
};

Blockly.SLICE["select_atom_by_loc2"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var type = ATOM_TYPE[block.getFieldValue("TYPE")];
  var neighbor = LOCS[block.getFieldValue("NEIGHBOR")];
  //var hydrogen = H_INCLUSION[block.getFieldValue("INCLUSION")];
  var argument = Blockly.SLICE.valueToCode(block, "Object", order) || "subject";

  var code = type + " " + neighbor + " to " + argument /*+ " " + hydrogen*/ ;
  return [code, order];
};

Blockly.SLICE["select_atom_by_atom_type"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var type = ATOM_TYPE[block.getFieldValue("TYPE")];

  var code = type + " atoms";
  return [code, order];
};

Blockly.SLICE["select_ring"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;

  var code = "";
  return [code, order];
};

Blockly.SLICE["select_current_ring"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;

  var code = "current ring";
  return [code, order];
};

Blockly.SLICE["select_molecule"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;

  var code = "molecule";
  return [code, order];
};

Blockly.SLICE["select_appendages_by_atom_mapping"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var code = "atom " + Number(block.inputList[0].fieldRow[1].value_);
  return [code, order];
};

Blockly.SLICE["select_appendages_by_atom_loc"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var neighbor = LOCS[block.getFieldValue("NEIGHBOR")];
  //var hydrogen = H_INCLUSION[block.getFieldValue("INCLUSION")];
  var argument = Blockly.SLICE.valueToCode(block, "Object", order) || "subject";

  var code = neighbor + " to " + argument /*+ " " + hydrogen*/ ;
  return [code, order];
};

Blockly.SLICE["select_appendages_by_atom_loc2"] = function (block) {
  var order = Blockly.SLICE.ORDER_ATOMIC;
  var type = ATOM_TYPE[block.getFieldValue("TYPE")];
  var neighbor = LOCS[block.getFieldValue("NEIGHBOR")];
  //var hydrogen = H_INCLUSION[block.getFieldValue("INCLUSION")];
  var argument = Blockly.SLICE.valueToCode(block, "Object", order) || "subject";

  var code = type + " " + neighbor + " to " + argument /*+ " " + hydrogen*/ ;
  return [code, order];
};