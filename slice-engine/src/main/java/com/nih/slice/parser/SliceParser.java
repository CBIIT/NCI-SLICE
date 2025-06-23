// Generated from Slice.g4 by ANTLR 4.13.0
package com.nih.slice.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class SliceParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, EXIT=14, FROM=15, IMPORT=16, KILL=17, 
		RETURN=18, ADD=19, DESIGNATE=20, PERMUTE=21, SUBTRACT=22, HYDROGEN=23, 
		HELIUM=24, LITHIUM=25, BERYLLIUM=26, BORON=27, CARBON=28, NITROGEN=29, 
		OXYGEN=30, FLUORINE=31, NEON=32, SODIUM=33, MAGNESIUM=34, ALUMINIUM=35, 
		SILICON=36, PHOSPHORUS=37, SULFUR=38, CHLORINE=39, ARGON=40, POTASSIUM=41, 
		CALCIUM=42, SCANDIUM=43, TITANIUM=44, VANADIUM=45, CHROMIUM=46, IRON=47, 
		COBALT=48, NICKEL=49, COPPER=50, ZINC=51, GALLIUM=52, GERMANIUM=53, ARSENIC=54, 
		SELENIUM=55, BROMINE=56, KRYPTON=57, RUBIDIUM=58, STRONTIUM=59, YTTRBIUM=60, 
		ZIRCONIUM=61, NIOBIUM=62, MOLYBDENUM=63, TECHNETIUM=64, RUTHENIUM=65, 
		RHODIUM=66, PALLADIUM=67, SILVER=68, CADMIUM=69, INDIUM=70, TIN=71, ANTIMONY=72, 
		TELLURIUM=73, IODINE=74, XENON=75, CESIUM=76, BARIUM=77, LANTHANUM=78, 
		HAFNIUM=79, TANTALUM=80, TUNGSTEN=81, RHENIUM=82, OSMIUM=83, IRIDIUM=84, 
		PLATINUM=85, GOLD=86, MERCURY=87, THALLIUM=88, LEAD=89, BISMUTH=90, POLONIUM=91, 
		ASTATINE=92, RADON=93, FRANCIUM=94, RADIUM=95, ACTINOIDS=96, RUTHERFORDIUM=97, 
		DUBNIUM=98, SEABORGIUM=99, BOHRIUM=100, HASSIUM=101, MEITNERIUM=102, DARMSTADIUM=103, 
		ROENTGENIUM=104, COPERNICIUM=105, NIHONIUM=106, FLEROVIUM=107, MOSCOVIUM=108, 
		LIVERMORIUM=109, TENNESSINE=110, OGANESSON=111, CERIUM=112, PRASEODYMIUM=113, 
		NEODYMIUM=114, PROMETHIUM=115, SAMARIUM=116, EUROPIUM=117, GADOLINIUM=118, 
		TERBIUM=119, DYSPROSIUM=120, HOLMIUM=121, ERBIUM=122, THULIUM=123, LUTETIUM=124, 
		THORIUM=125, PROTACTINIUM=126, URANIUM=127, NEPTUNIUM=128, PLUTONIUM=129, 
		AMERICIUM=130, CURIUM=131, BERKELIUM=132, CALIFORNIUM=133, EINSTEINIUM=134, 
		FERMIUM=135, MENDELEVIUM=136, NOBELIUM=137, LAWRENCIUM=138, DITHIOACETAL=139, 
		HEMIACETAL=140, ACETAL=141, ACETYLENE=142, ACID_HALIDE=143, ACID=144, 
		ALCOHOL=145, ALDEHYDE=146, ALLENE=147, AMIDE1=148, AMIDE2=149, AMIDE3=150, 
		AMIDE=151, AMIDZ=152, HYDROXYLAMINE=153, AMINE1=154, AMINE2=155, AMINE3=156, 
		AMINE=157, AMINE_OXIDE=158, ANHYDRIDE=159, AZIDE=160, AZIRIDINE=161, AZO=162, 
		BROMIDE=163, C_SULFONATE=164, CARBAMATE_C=165, CARBAMATE_H=166, CARBONIUM=167, 
		CARBONYL=168, CARBOXYL=169, CHLORIDE=170, CYANO=171, DIAZO=172, DISULFIDE=173, 
		DITHIOKETAL=174, ENAMINE=175, ENOL_ETHER=176, EPOXIDE=177, THIOESTER=178, 
		ESTER=179, ESTERX=180, SILYLENOLETHER=181, ETHER=182, FLUORIDE=183, FUNCTIONAL=184, 
		GEM_DIHALIDE=185, GLYCOL=186, TRIHALIDE=187, VIC_DIHALIDE=188, HALIDE=189, 
		HALOAMINE=190, HALOHYDRIN=191, HYDRATE=192, HYDRAZONE=193, IMINE=194, 
		IODIDE=195, ISOCYANATE=196, ISOCYANIDE=197, KETONE=198, LACTAM=199, LACTONE=200, 
		METHYLENE=201, N_CARBAMATE=202, N_UREA_C=203, N_UREA_H=204, NITRITE=205, 
		NITRO=206, NITROSO=207, O_CARBAMATE=208, O_CARBONATE=209, O_SULFONATE=210, 
		OLEFIN=211, OXIME=212, PEROXIDE=213, PHOSPHINE=214, PHOSPHONATE=215, SELENIDE=216, 
		EPISULFIDE=217, SULFIDE=218, SULFONE=219, SULFOXIDE=220, THIOCYANATE=221, 
		THIOL=222, TRIALKYLSILOXY=223, TRIALKYLSILYL=224, VINYLSILANE=225, APPENDAGE=226, 
		ISOPROPYL=227, METHYL=228, PHENYL=229, T_BUTYL=230, SMARTS=231, ALKALI_METAL=232, 
		ALKYL=233, BENZYLIC=234, CIS=235, DOUBLY=236, ELECTRON_DENSITY=237, ENOLIZABLE=238, 
		EQUIVALENT=239, EXPLICIT_HYDROGEN=240, HALOGEN=241, HETERO=242, MINUS_CHARGE=243, 
		MULTIPLY=244, PLUS_CHARGE=245, TRANS=246, TRIPLY=247, DOUBLE_BOND=248, 
		MULTIPLE=249, SINGLE_BOND=250, SMALL_FUSION=251, TRIPLE_BOND=252, ALIPHATIC=253, 
		ALLYLIC=254, ANTI=255, AROMATIC=256, BREDT_STRAINED=257, BRIDGEHEAD=258, 
		CIS_OLEFIN=259, CONJUGATED=260, FUSION=261, STRAINED=262, SYN=263, TRANS_OLEFIN=264, 
		DONATING=265, NON_EXPANDABLE_WITHDRAWING=266, EXPANDABLE_WITHDRAWING=267, 
		INTERFERING=268, GOOD_LEAVING=269, LEAVING=270, PARTICIPATING=271, PROTECTED=272, 
		VINYL_D=273, VINYL_W=274, WITHDRAWING=275, PRIMARY_CENTER=276, SECONDARY_CENTER=277, 
		TERTIARY_CENTER=278, QUATERNARY_CENTER=279, PRIMARY=280, SECONDARY=281, 
		TERTIARY=282, QUATERNARY=283, CENTER=284, ALPHA=285, BETA=286, GAMMA=287, 
		SET=288, REMOVE=289, EMPTY=290, BETWEEN=291, WITHIN=292, CARBOCYCLIC=293, 
		COMMON=294, HETEROCYCLIC=295, BRIDGE=296, ANYWHERE=297, OFF_CURRENT_RING=298, 
		OFFPATH=299, OFFRING=300, OFF_THE_BRIDGE=301, ON_THE_BRIDGE=302, ONPATH=303, 
		ONRING=304, ON_CURRENT_RING=305, CURRENT=306, FEWER_THAN=307, FEWER=308, 
		OR_LARGER=309, LARGER=310, OR_SMALLER=311, SMALLER=312, LESS_HINDERED=313, 
		MORE_HINDERED=314, THROUGH=315, LOWER=316, RAISE=317, RATING=318, SLIGHTLY=319, 
		MODERATELY=320, STRONGLY=321, SEVERELY=322, NUMBER_LHASA=323, SIZE=324, 
		INVERT=325, ANIONIZE=326, CATIONIZE=327, DEFINED_DOWN=328, DEFINED_UP=329, 
		DEFINED_ANTI=330, DEFINED_CIS=331, DEFINED_SYN=332, DEFINED_TRANS=333, 
		NEUTRALIZE=334, RACEMIZE=335, RADICALIZE=336, UP=337, DOWN=338, ZERO=339, 
		ONE=340, TWO=341, THREE=342, FOUR=343, FIVE=344, SIX=345, SEVEN=346, EIGHT=347, 
		NINE=348, TEN=349, ELEVEN=350, TWELVE=351, THIRTEEN=352, FOURTEEN=353, 
		FIFTEEN=354, SIXTEEN=355, SEVENTEEN=356, EIGHTEEN=357, NINETEEN=358, TWENTY=359, 
		ATOM=360, BOND=361, MOLECULE=362, RING=363, GHOST=364, FUNCTION=365, ELIF=366, 
		IF=367, ELSE=368, CALL=369, PUT=370, THEN=371, FOREACH=372, IS=373, HAS=374, 
		ORIF=375, ANDIF=376, TRUE=377, FALSE=378, SEMIC=379, COLON=380, COMMA=381, 
		EQUAL=382, EQ=383, PLUS=384, MINUS=385, TIMES=386, DIV=387, MODULUS=388, 
		LPAR=389, RPAR=390, CLPAR=391, CRPAR=392, DOT=393, GT=394, LT=395, GE=396, 
		LE=397, NE=398, NOT=399, GT_SIGN=400, LT_SIGN=401, EQUAL_SIGN=402, NOT_SIGN=403, 
		GT2=404, LT2=405, GE2=406, LE2=407, AND=408, OR=409, XOR=410, OTHERWISE=411, 
		CONTAINED=412, AS=413, IT=414, ONLY=415, GROUP=416, AT=417, IN=418, INCLUDING=419, 
		OF=420, ON=421, TOWARDS=422, TO=423, INTO=424, ANY=425, ALL=426, DEFINED=427, 
		AN=428, A=429, THE=430, INT=431, DECIMAL=432, EXPONENT=433, STRING=434, 
		NAME=435, COMMENT=436, LINE_COMMENT=437, WS=438, WS_INLINE=439;
	public static final int
		RULE_start = 0, RULE_instructions = 1, RULE_stmt = 2, RULE_simple_stmt = 3, 
		RULE_small_stmt = 4, RULE_expr_stmt = 5, RULE_value = 6, RULE_par = 7, 
		RULE_set_stmt = 8, RULE_set = 9, RULE_remove_from_set = 10, RULE_empty_set = 11, 
		RULE_kill_stmt = 12, RULE_rating_stmt = 13, RULE_qualitative_rating = 14, 
		RULE_rating = 15, RULE_return_stmt = 16, RULE_exit_stmt = 17, RULE_permute_stmt = 18, 
		RULE_mech_stmt = 19, RULE_ghost_stmt = 20, RULE_import_stmt = 21, RULE_import_module = 22, 
		RULE_import_from = 23, RULE_import_as_name = 24, RULE_import_as_names = 25, 
		RULE_compound_stmt = 26, RULE_if_stmt = 27, RULE_test = 28, RULE_or_test = 29, 
		RULE_and_test = 30, RULE_test_block = 31, RULE_foreach_stmt = 32, RULE_block = 33, 
		RULE_suite = 34, RULE_funcdef = 35, RULE_func_stmt = 36, RULE_parameters = 37, 
		RULE_fplist = 38, RULE_fpdef = 39, RULE_test_stmt = 40, RULE_math_comp_op = 41, 
		RULE_relation = 42, RULE_pos_relation = 43, RULE_neg_relation = 44, RULE_term = 45, 
		RULE_term_block = 46, RULE_atom1 = 47, RULE_atom2 = 48, RULE_subjects = 49, 
		RULE_or_subject = 50, RULE_and_subject = 51, RULE_predicates = 52, RULE_or_predicate = 53, 
		RULE_and_predicate = 54, RULE_subject_appendages = 55, RULE_predicate_appendages = 56, 
		RULE_subject = 57, RULE_subject_atom = 58, RULE_subject_bond = 59, RULE_subject_ring = 60, 
		RULE_subject_molecule = 61, RULE_subject_or_predicate_atom_type = 62, 
		RULE_subject_atom_locs = 63, RULE_comp_op = 64, RULE_predicate = 65, RULE_predicate_atom = 66, 
		RULE_predicate_bond = 67, RULE_predicate_atom_property = 68, RULE_predicate_atom_center_property = 69, 
		RULE_predicate_atom_type_count = 70, RULE_predicate_charge_count = 71, 
		RULE_predicate_alkyl_count = 72, RULE_predicate_hetero_count = 73, RULE_predicate_bond_property = 74, 
		RULE_predicate_atom_or_bond_property = 75, RULE_predicate_atom_locs = 76, 
		RULE_predicate_atom_or_bond_or_group_property = 77, RULE_predicate_group_property = 78, 
		RULE_predicate_group_property_count = 79, RULE_predicate_functional_group = 80, 
		RULE_predicate_functional_group_count = 81, RULE_predicate_non_functional_group = 82, 
		RULE_predicate_non_functional_group_count = 83, RULE_predicate_smarts_group = 84, 
		RULE_predicate_smarts_group_count = 85, RULE_predicate_ring = 86, RULE_stereochemistry_predicate1 = 87, 
		RULE_stereochemistry_predicate2 = 88, RULE_stereochemistry_predicate3 = 89, 
		RULE_loop_stmt = 90, RULE_loop_subject = 91, RULE_loop_subject_atom_type = 92, 
		RULE_loop_subject_atom_property = 93, RULE_loop_subject_atom_combined = 94, 
		RULE_loop_subject_bond_property = 95, RULE_loop_subject_group_property = 96, 
		RULE_loop_subject_functional_group_property = 97, RULE_loop_subject_non_functional_group_property = 98, 
		RULE_loop_subject_atom_locs = 99, RULE_loop_predicate = 100, RULE_loop_predicate_ring = 101, 
		RULE_set_current_ring = 102, RULE_atom_on_bond = 103, RULE_bond_on_atom = 104, 
		RULE_var = 105, RULE_variable_name = 106, RULE_defined_as = 107, RULE_where_stmt = 108, 
		RULE_ring_prefix_prop = 109, RULE_ring_suffix_prop = 110, RULE_ring_size = 111, 
		RULE_alpha_num = 112, RULE_numeric_num = 113, RULE_atomtype = 114, RULE_functional_group = 115, 
		RULE_non_functional_group = 116, RULE_smarts = 117, RULE_atom_loc = 118, 
		RULE_atom_properties = 119, RULE_atom_or_bond_properties = 120, RULE_bond_properties = 121, 
		RULE_atom_or_bond_or_group_properties = 122, RULE_group_properties = 123, 
		RULE_charge_stmt = 124, RULE_centre_stmt = 125, RULE_ring_prop = 126, 
		RULE_alkyl_number = 127, RULE_bool = 128, RULE_notcontain = 129, RULE_contain = 130, 
		RULE_notequal = 131, RULE_equal = 132, RULE_same = 133, RULE_function_name = 134, 
		RULE_path = 135, RULE_smarts_char = 136;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "instructions", "stmt", "simple_stmt", "small_stmt", "expr_stmt", 
			"value", "par", "set_stmt", "set", "remove_from_set", "empty_set", "kill_stmt", 
			"rating_stmt", "qualitative_rating", "rating", "return_stmt", "exit_stmt", 
			"permute_stmt", "mech_stmt", "ghost_stmt", "import_stmt", "import_module", 
			"import_from", "import_as_name", "import_as_names", "compound_stmt", 
			"if_stmt", "test", "or_test", "and_test", "test_block", "foreach_stmt", 
			"block", "suite", "funcdef", "func_stmt", "parameters", "fplist", "fpdef", 
			"test_stmt", "math_comp_op", "relation", "pos_relation", "neg_relation", 
			"term", "term_block", "atom1", "atom2", "subjects", "or_subject", "and_subject", 
			"predicates", "or_predicate", "and_predicate", "subject_appendages", 
			"predicate_appendages", "subject", "subject_atom", "subject_bond", "subject_ring", 
			"subject_molecule", "subject_or_predicate_atom_type", "subject_atom_locs", 
			"comp_op", "predicate", "predicate_atom", "predicate_bond", "predicate_atom_property", 
			"predicate_atom_center_property", "predicate_atom_type_count", "predicate_charge_count", 
			"predicate_alkyl_count", "predicate_hetero_count", "predicate_bond_property", 
			"predicate_atom_or_bond_property", "predicate_atom_locs", "predicate_atom_or_bond_or_group_property", 
			"predicate_group_property", "predicate_group_property_count", "predicate_functional_group", 
			"predicate_functional_group_count", "predicate_non_functional_group", 
			"predicate_non_functional_group_count", "predicate_smarts_group", "predicate_smarts_group_count", 
			"predicate_ring", "stereochemistry_predicate1", "stereochemistry_predicate2", 
			"stereochemistry_predicate3", "loop_stmt", "loop_subject", "loop_subject_atom_type", 
			"loop_subject_atom_property", "loop_subject_atom_combined", "loop_subject_bond_property", 
			"loop_subject_group_property", "loop_subject_functional_group_property", 
			"loop_subject_non_functional_group_property", "loop_subject_atom_locs", 
			"loop_predicate", "loop_predicate_ring", "set_current_ring", "atom_on_bond", 
			"bond_on_atom", "var", "variable_name", "defined_as", "where_stmt", "ring_prefix_prop", 
			"ring_suffix_prop", "ring_size", "alpha_num", "numeric_num", "atomtype", 
			"functional_group", "non_functional_group", "smarts", "atom_loc", "atom_properties", 
			"atom_or_bond_properties", "bond_properties", "atom_or_bond_or_group_properties", 
			"group_properties", "charge_stmt", "centre_stmt", "ring_prop", "alkyl_number", 
			"bool", "notcontain", "contain", "notequal", "equal", "same", "function_name", 
			"path", "smarts_char"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'about the path from'", "'about the ring containing'", "'same'", 
			"'SAME'", "'['", "'#'", "'~'", "'?'", "'\\'", "'&'", "']'", "'@'", "'$'", 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "'DEFINED*ANTI'", "'DEFINED*CIS'", "'DEFINED*SYN'", 
			"'DEFINED*TRANS'", null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"'\\;'", "':'", "','", null, null, "'+'", "'-'", "'*'", "'/'", "'%'", 
			"'('", "')'", "'{'", "'}'", "'.'", null, null, null, null, null, null, 
			"'>'", "'<'", "'='", "'!'", "'more than'", "'less than'", "'at least'", 
			"'at most'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "EXIT", "FROM", "IMPORT", "KILL", "RETURN", "ADD", "DESIGNATE", 
			"PERMUTE", "SUBTRACT", "HYDROGEN", "HELIUM", "LITHIUM", "BERYLLIUM", 
			"BORON", "CARBON", "NITROGEN", "OXYGEN", "FLUORINE", "NEON", "SODIUM", 
			"MAGNESIUM", "ALUMINIUM", "SILICON", "PHOSPHORUS", "SULFUR", "CHLORINE", 
			"ARGON", "POTASSIUM", "CALCIUM", "SCANDIUM", "TITANIUM", "VANADIUM", 
			"CHROMIUM", "IRON", "COBALT", "NICKEL", "COPPER", "ZINC", "GALLIUM", 
			"GERMANIUM", "ARSENIC", "SELENIUM", "BROMINE", "KRYPTON", "RUBIDIUM", 
			"STRONTIUM", "YTTRBIUM", "ZIRCONIUM", "NIOBIUM", "MOLYBDENUM", "TECHNETIUM", 
			"RUTHENIUM", "RHODIUM", "PALLADIUM", "SILVER", "CADMIUM", "INDIUM", "TIN", 
			"ANTIMONY", "TELLURIUM", "IODINE", "XENON", "CESIUM", "BARIUM", "LANTHANUM", 
			"HAFNIUM", "TANTALUM", "TUNGSTEN", "RHENIUM", "OSMIUM", "IRIDIUM", "PLATINUM", 
			"GOLD", "MERCURY", "THALLIUM", "LEAD", "BISMUTH", "POLONIUM", "ASTATINE", 
			"RADON", "FRANCIUM", "RADIUM", "ACTINOIDS", "RUTHERFORDIUM", "DUBNIUM", 
			"SEABORGIUM", "BOHRIUM", "HASSIUM", "MEITNERIUM", "DARMSTADIUM", "ROENTGENIUM", 
			"COPERNICIUM", "NIHONIUM", "FLEROVIUM", "MOSCOVIUM", "LIVERMORIUM", "TENNESSINE", 
			"OGANESSON", "CERIUM", "PRASEODYMIUM", "NEODYMIUM", "PROMETHIUM", "SAMARIUM", 
			"EUROPIUM", "GADOLINIUM", "TERBIUM", "DYSPROSIUM", "HOLMIUM", "ERBIUM", 
			"THULIUM", "LUTETIUM", "THORIUM", "PROTACTINIUM", "URANIUM", "NEPTUNIUM", 
			"PLUTONIUM", "AMERICIUM", "CURIUM", "BERKELIUM", "CALIFORNIUM", "EINSTEINIUM", 
			"FERMIUM", "MENDELEVIUM", "NOBELIUM", "LAWRENCIUM", "DITHIOACETAL", "HEMIACETAL", 
			"ACETAL", "ACETYLENE", "ACID_HALIDE", "ACID", "ALCOHOL", "ALDEHYDE", 
			"ALLENE", "AMIDE1", "AMIDE2", "AMIDE3", "AMIDE", "AMIDZ", "HYDROXYLAMINE", 
			"AMINE1", "AMINE2", "AMINE3", "AMINE", "AMINE_OXIDE", "ANHYDRIDE", "AZIDE", 
			"AZIRIDINE", "AZO", "BROMIDE", "C_SULFONATE", "CARBAMATE_C", "CARBAMATE_H", 
			"CARBONIUM", "CARBONYL", "CARBOXYL", "CHLORIDE", "CYANO", "DIAZO", "DISULFIDE", 
			"DITHIOKETAL", "ENAMINE", "ENOL_ETHER", "EPOXIDE", "THIOESTER", "ESTER", 
			"ESTERX", "SILYLENOLETHER", "ETHER", "FLUORIDE", "FUNCTIONAL", "GEM_DIHALIDE", 
			"GLYCOL", "TRIHALIDE", "VIC_DIHALIDE", "HALIDE", "HALOAMINE", "HALOHYDRIN", 
			"HYDRATE", "HYDRAZONE", "IMINE", "IODIDE", "ISOCYANATE", "ISOCYANIDE", 
			"KETONE", "LACTAM", "LACTONE", "METHYLENE", "N_CARBAMATE", "N_UREA_C", 
			"N_UREA_H", "NITRITE", "NITRO", "NITROSO", "O_CARBAMATE", "O_CARBONATE", 
			"O_SULFONATE", "OLEFIN", "OXIME", "PEROXIDE", "PHOSPHINE", "PHOSPHONATE", 
			"SELENIDE", "EPISULFIDE", "SULFIDE", "SULFONE", "SULFOXIDE", "THIOCYANATE", 
			"THIOL", "TRIALKYLSILOXY", "TRIALKYLSILYL", "VINYLSILANE", "APPENDAGE", 
			"ISOPROPYL", "METHYL", "PHENYL", "T_BUTYL", "SMARTS", "ALKALI_METAL", 
			"ALKYL", "BENZYLIC", "CIS", "DOUBLY", "ELECTRON_DENSITY", "ENOLIZABLE", 
			"EQUIVALENT", "EXPLICIT_HYDROGEN", "HALOGEN", "HETERO", "MINUS_CHARGE", 
			"MULTIPLY", "PLUS_CHARGE", "TRANS", "TRIPLY", "DOUBLE_BOND", "MULTIPLE", 
			"SINGLE_BOND", "SMALL_FUSION", "TRIPLE_BOND", "ALIPHATIC", "ALLYLIC", 
			"ANTI", "AROMATIC", "BREDT_STRAINED", "BRIDGEHEAD", "CIS_OLEFIN", "CONJUGATED", 
			"FUSION", "STRAINED", "SYN", "TRANS_OLEFIN", "DONATING", "NON_EXPANDABLE_WITHDRAWING", 
			"EXPANDABLE_WITHDRAWING", "INTERFERING", "GOOD_LEAVING", "LEAVING", "PARTICIPATING", 
			"PROTECTED", "VINYL_D", "VINYL_W", "WITHDRAWING", "PRIMARY_CENTER", "SECONDARY_CENTER", 
			"TERTIARY_CENTER", "QUATERNARY_CENTER", "PRIMARY", "SECONDARY", "TERTIARY", 
			"QUATERNARY", "CENTER", "ALPHA", "BETA", "GAMMA", "SET", "REMOVE", "EMPTY", 
			"BETWEEN", "WITHIN", "CARBOCYCLIC", "COMMON", "HETEROCYCLIC", "BRIDGE", 
			"ANYWHERE", "OFF_CURRENT_RING", "OFFPATH", "OFFRING", "OFF_THE_BRIDGE", 
			"ON_THE_BRIDGE", "ONPATH", "ONRING", "ON_CURRENT_RING", "CURRENT", "FEWER_THAN", 
			"FEWER", "OR_LARGER", "LARGER", "OR_SMALLER", "SMALLER", "LESS_HINDERED", 
			"MORE_HINDERED", "THROUGH", "LOWER", "RAISE", "RATING", "SLIGHTLY", "MODERATELY", 
			"STRONGLY", "SEVERELY", "NUMBER_LHASA", "SIZE", "INVERT", "ANIONIZE", 
			"CATIONIZE", "DEFINED_DOWN", "DEFINED_UP", "DEFINED_ANTI", "DEFINED_CIS", 
			"DEFINED_SYN", "DEFINED_TRANS", "NEUTRALIZE", "RACEMIZE", "RADICALIZE", 
			"UP", "DOWN", "ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", 
			"EIGHT", "NINE", "TEN", "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN", 
			"SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN", "TWENTY", "ATOM", "BOND", 
			"MOLECULE", "RING", "GHOST", "FUNCTION", "ELIF", "IF", "ELSE", "CALL", 
			"PUT", "THEN", "FOREACH", "IS", "HAS", "ORIF", "ANDIF", "TRUE", "FALSE", 
			"SEMIC", "COLON", "COMMA", "EQUAL", "EQ", "PLUS", "MINUS", "TIMES", "DIV", 
			"MODULUS", "LPAR", "RPAR", "CLPAR", "CRPAR", "DOT", "GT", "LT", "GE", 
			"LE", "NE", "NOT", "GT_SIGN", "LT_SIGN", "EQUAL_SIGN", "NOT_SIGN", "GT2", 
			"LT2", "GE2", "LE2", "AND", "OR", "XOR", "OTHERWISE", "CONTAINED", "AS", 
			"IT", "ONLY", "GROUP", "AT", "IN", "INCLUDING", "OF", "ON", "TOWARDS", 
			"TO", "INTO", "ANY", "ALL", "DEFINED", "AN", "A", "THE", "INT", "DECIMAL", 
			"EXPONENT", "STRING", "NAME", "COMMENT", "LINE_COMMENT", "WS", "WS_INLINE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Slice.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SliceParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public InstructionsContext instructions() {
			return getRuleContext(InstructionsContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			instructions();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstructionsContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public InstructionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instructions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterInstructions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitInstructions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitInstructions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionsContext instructions() throws RecognitionException {
		InstructionsContext _localctx = new InstructionsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_instructions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7323648L) != 0) || ((((_la - 288)) & ~0x3f) == 0 && ((1L << (_la - 288)) & 496842622107655L) != 0) || ((((_la - 365)) & ~0x3f) == 0 && ((1L << (_la - 365)) & 4611686018697920677L) != 0) || _la==NAME) {
				{
				{
				setState(276);
				stmt();
				}
				}
				setState(281);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StmtContext extends ParserRuleContext {
		public Simple_stmtContext simple_stmt() {
			return getRuleContext(Simple_stmtContext.class,0);
		}
		public Compound_stmtContext compound_stmt() {
			return getRuleContext(Compound_stmtContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_stmt);
		try {
			setState(284);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXIT:
			case FROM:
			case IMPORT:
			case KILL:
			case RETURN:
			case ADD:
			case PERMUTE:
			case SUBTRACT:
			case SET:
			case REMOVE:
			case EMPTY:
			case LOWER:
			case RAISE:
			case INVERT:
			case ANIONIZE:
			case CATIONIZE:
			case DEFINED_DOWN:
			case DEFINED_UP:
			case NEUTRALIZE:
			case RACEMIZE:
			case RADICALIZE:
			case PUT:
			case TIMES:
			case DOT:
			case DEFINED:
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(282);
				simple_stmt();
				}
				break;
			case FUNCTION:
			case IF:
			case FOREACH:
				enterOuterAlt(_localctx, 2);
				{
				setState(283);
				compound_stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Simple_stmtContext extends ParserRuleContext {
		public List<Small_stmtContext> small_stmt() {
			return getRuleContexts(Small_stmtContext.class);
		}
		public Small_stmtContext small_stmt(int i) {
			return getRuleContext(Small_stmtContext.class,i);
		}
		public List<TerminalNode> SEMIC() { return getTokens(SliceParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(SliceParser.SEMIC, i);
		}
		public Simple_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterSimple_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitSimple_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitSimple_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Simple_stmtContext simple_stmt() throws RecognitionException {
		Simple_stmtContext _localctx = new Simple_stmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_simple_stmt);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			small_stmt();
			setState(291);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(287);
					match(SEMIC);
					setState(288);
					small_stmt();
					}
					} 
				}
				setState(293);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMIC) {
				{
				setState(294);
				match(SEMIC);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Small_stmtContext extends ParserRuleContext {
		public Func_stmtContext func_stmt() {
			return getRuleContext(Func_stmtContext.class,0);
		}
		public Expr_stmtContext expr_stmt() {
			return getRuleContext(Expr_stmtContext.class,0);
		}
		public Kill_stmtContext kill_stmt() {
			return getRuleContext(Kill_stmtContext.class,0);
		}
		public Rating_stmtContext rating_stmt() {
			return getRuleContext(Rating_stmtContext.class,0);
		}
		public Return_stmtContext return_stmt() {
			return getRuleContext(Return_stmtContext.class,0);
		}
		public Exit_stmtContext exit_stmt() {
			return getRuleContext(Exit_stmtContext.class,0);
		}
		public Import_stmtContext import_stmt() {
			return getRuleContext(Import_stmtContext.class,0);
		}
		public Mech_stmtContext mech_stmt() {
			return getRuleContext(Mech_stmtContext.class,0);
		}
		public Permute_stmtContext permute_stmt() {
			return getRuleContext(Permute_stmtContext.class,0);
		}
		public Ghost_stmtContext ghost_stmt() {
			return getRuleContext(Ghost_stmtContext.class,0);
		}
		public Set_stmtContext set_stmt() {
			return getRuleContext(Set_stmtContext.class,0);
		}
		public Small_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_small_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterSmall_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitSmall_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitSmall_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Small_stmtContext small_stmt() throws RecognitionException {
		Small_stmtContext _localctx = new Small_stmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_small_stmt);
		try {
			setState(309);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(297);
				func_stmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(298);
				expr_stmt();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(299);
				kill_stmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(300);
				rating_stmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(301);
				return_stmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(302);
				exit_stmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(303);
				import_stmt();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(304);
				mech_stmt();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(305);
				permute_stmt();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(306);
				mech_stmt();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(307);
				ghost_stmt();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(308);
				set_stmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expr_stmtContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(SliceParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(SliceParser.MINUS, 0); }
		public TerminalNode TIMES() { return getToken(SliceParser.TIMES, 0); }
		public TerminalNode DIV() { return getToken(SliceParser.DIV, 0); }
		public TerminalNode MODULUS() { return getToken(SliceParser.MODULUS, 0); }
		public TerminalNode EQUAL() { return getToken(SliceParser.EQUAL, 0); }
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public Variable_nameContext variable_name() {
			return getRuleContext(Variable_nameContext.class,0);
		}
		public Expr_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterExpr_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitExpr_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitExpr_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr_stmtContext expr_stmt() throws RecognitionException {
		Expr_stmtContext _localctx = new Expr_stmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_expr_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SET:
			case DEFINED:
				{
				setState(311);
				var();
				}
				break;
			case NAME:
				{
				setState(312);
				variable_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(315);
			_la = _input.LA(1);
			if ( !(((((_la - 382)) & ~0x3f) == 0 && ((1L << (_la - 382)) & 125L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(316);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public Variable_nameContext variable_name() {
			return getRuleContext(Variable_nameContext.class,0);
		}
		public Alpha_numContext alpha_num() {
			return getRuleContext(Alpha_numContext.class,0);
		}
		public Numeric_numContext numeric_num() {
			return getRuleContext(Numeric_numContext.class,0);
		}
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public SubjectsContext subjects() {
			return getRuleContext(SubjectsContext.class,0);
		}
		public Set_current_ringContext set_current_ring() {
			return getRuleContext(Set_current_ringContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(318);
				variable_name();
				}
				break;
			case 2:
				{
				setState(319);
				alpha_num();
				}
				break;
			case 3:
				{
				setState(320);
				numeric_num();
				}
				break;
			case 4:
				{
				setState(321);
				bool();
				}
				break;
			case 5:
				{
				setState(322);
				subjects();
				}
				break;
			case 6:
				{
				setState(323);
				set_current_ring();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(SliceParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SliceParser.RPAR, 0); }
		public ParContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_par; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParContext par() throws RecognitionException {
		ParContext _localctx = new ParContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_par);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			_la = _input.LA(1);
			if ( !(_la==LPAR || _la==RPAR) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Set_stmtContext extends ParserRuleContext {
		public SetContext set() {
			return getRuleContext(SetContext.class,0);
		}
		public Remove_from_setContext remove_from_set() {
			return getRuleContext(Remove_from_setContext.class,0);
		}
		public Empty_setContext empty_set() {
			return getRuleContext(Empty_setContext.class,0);
		}
		public Set_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterSet_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitSet_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitSet_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Set_stmtContext set_stmt() throws RecognitionException {
		Set_stmtContext _localctx = new Set_stmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_set_stmt);
		try {
			setState(331);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PUT:
				enterOuterAlt(_localctx, 1);
				{
				setState(328);
				set();
				}
				break;
			case REMOVE:
				enterOuterAlt(_localctx, 2);
				{
				setState(329);
				remove_from_set();
				}
				break;
			case EMPTY:
				enterOuterAlt(_localctx, 3);
				{
				setState(330);
				empty_set();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetContext extends ParserRuleContext {
		public TerminalNode PUT() { return getToken(SliceParser.PUT, 0); }
		public TerminalNode INTO() { return getToken(SliceParser.INTO, 0); }
		public TerminalNode NAME() { return getToken(SliceParser.NAME, 0); }
		public SubjectsContext subjects() {
			return getRuleContext(SubjectsContext.class,0);
		}
		public Subject_appendagesContext subject_appendages() {
			return getRuleContext(Subject_appendagesContext.class,0);
		}
		public Variable_nameContext variable_name() {
			return getRuleContext(Variable_nameContext.class,0);
		}
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public Alpha_numContext alpha_num() {
			return getRuleContext(Alpha_numContext.class,0);
		}
		public Numeric_numContext numeric_num() {
			return getRuleContext(Numeric_numContext.class,0);
		}
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public SetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitSet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitSet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetContext set() throws RecognitionException {
		SetContext _localctx = new SetContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_set);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
			match(PUT);
			setState(341);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(334);
				subjects();
				}
				break;
			case 2:
				{
				setState(335);
				subject_appendages();
				}
				break;
			case 3:
				{
				setState(336);
				variable_name();
				}
				break;
			case 4:
				{
				setState(337);
				match(INT);
				}
				break;
			case 5:
				{
				setState(338);
				alpha_num();
				}
				break;
			case 6:
				{
				setState(339);
				numeric_num();
				}
				break;
			case 7:
				{
				setState(340);
				bool();
				}
				break;
			}
			setState(343);
			match(INTO);
			setState(344);
			match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Remove_from_setContext extends ParserRuleContext {
		public TerminalNode REMOVE() { return getToken(SliceParser.REMOVE, 0); }
		public TerminalNode FROM() { return getToken(SliceParser.FROM, 0); }
		public TerminalNode NAME() { return getToken(SliceParser.NAME, 0); }
		public SubjectsContext subjects() {
			return getRuleContext(SubjectsContext.class,0);
		}
		public Subject_appendagesContext subject_appendages() {
			return getRuleContext(Subject_appendagesContext.class,0);
		}
		public Variable_nameContext variable_name() {
			return getRuleContext(Variable_nameContext.class,0);
		}
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public Alpha_numContext alpha_num() {
			return getRuleContext(Alpha_numContext.class,0);
		}
		public Numeric_numContext numeric_num() {
			return getRuleContext(Numeric_numContext.class,0);
		}
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public Remove_from_setContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_remove_from_set; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterRemove_from_set(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitRemove_from_set(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitRemove_from_set(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Remove_from_setContext remove_from_set() throws RecognitionException {
		Remove_from_setContext _localctx = new Remove_from_setContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_remove_from_set);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			match(REMOVE);
			setState(354);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(347);
				subjects();
				}
				break;
			case 2:
				{
				setState(348);
				subject_appendages();
				}
				break;
			case 3:
				{
				setState(349);
				variable_name();
				}
				break;
			case 4:
				{
				setState(350);
				match(INT);
				}
				break;
			case 5:
				{
				setState(351);
				alpha_num();
				}
				break;
			case 6:
				{
				setState(352);
				numeric_num();
				}
				break;
			case 7:
				{
				setState(353);
				bool();
				}
				break;
			}
			setState(356);
			match(FROM);
			setState(357);
			match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Empty_setContext extends ParserRuleContext {
		public TerminalNode EMPTY() { return getToken(SliceParser.EMPTY, 0); }
		public TerminalNode NAME() { return getToken(SliceParser.NAME, 0); }
		public Empty_setContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_empty_set; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterEmpty_set(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitEmpty_set(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitEmpty_set(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Empty_setContext empty_set() throws RecognitionException {
		Empty_setContext _localctx = new Empty_setContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_empty_set);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359);
			match(EMPTY);
			setState(360);
			match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Kill_stmtContext extends ParserRuleContext {
		public TerminalNode KILL() { return getToken(SliceParser.KILL, 0); }
		public Kill_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_kill_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterKill_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitKill_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitKill_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Kill_stmtContext kill_stmt() throws RecognitionException {
		Kill_stmtContext _localctx = new Kill_stmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_kill_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			match(KILL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Rating_stmtContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public TerminalNode ADD() { return getToken(SliceParser.ADD, 0); }
		public TerminalNode SUBTRACT() { return getToken(SliceParser.SUBTRACT, 0); }
		public Qualitative_ratingContext qualitative_rating() {
			return getRuleContext(Qualitative_ratingContext.class,0);
		}
		public Rating_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rating_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterRating_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitRating_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitRating_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rating_stmtContext rating_stmt() throws RecognitionException {
		Rating_stmtContext _localctx = new Rating_stmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_rating_stmt);
		int _la;
		try {
			setState(367);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case SUBTRACT:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(364);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==SUBTRACT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(365);
				match(INT);
				}
				}
				break;
			case LOWER:
			case RAISE:
				enterOuterAlt(_localctx, 2);
				{
				setState(366);
				qualitative_rating();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Qualitative_ratingContext extends ParserRuleContext {
		public TerminalNode RATING() { return getToken(SliceParser.RATING, 0); }
		public RatingContext rating() {
			return getRuleContext(RatingContext.class,0);
		}
		public TerminalNode LOWER() { return getToken(SliceParser.LOWER, 0); }
		public TerminalNode RAISE() { return getToken(SliceParser.RAISE, 0); }
		public Qualitative_ratingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualitative_rating; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterQualitative_rating(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitQualitative_rating(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitQualitative_rating(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Qualitative_ratingContext qualitative_rating() throws RecognitionException {
		Qualitative_ratingContext _localctx = new Qualitative_ratingContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_qualitative_rating);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369);
			_la = _input.LA(1);
			if ( !(_la==LOWER || _la==RAISE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(370);
			match(RATING);
			setState(371);
			rating();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RatingContext extends ParserRuleContext {
		public TerminalNode SLIGHTLY() { return getToken(SliceParser.SLIGHTLY, 0); }
		public TerminalNode MODERATELY() { return getToken(SliceParser.MODERATELY, 0); }
		public TerminalNode STRONGLY() { return getToken(SliceParser.STRONGLY, 0); }
		public TerminalNode SEVERELY() { return getToken(SliceParser.SEVERELY, 0); }
		public RatingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rating; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterRating(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitRating(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitRating(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RatingContext rating() throws RecognitionException {
		RatingContext _localctx = new RatingContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_rating);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			_la = _input.LA(1);
			if ( !(((((_la - 319)) & ~0x3f) == 0 && ((1L << (_la - 319)) & 15L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Return_stmtContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(SliceParser.RETURN, 0); }
		public List<Variable_nameContext> variable_name() {
			return getRuleContexts(Variable_nameContext.class);
		}
		public Variable_nameContext variable_name(int i) {
			return getRuleContext(Variable_nameContext.class,i);
		}
		public Func_stmtContext func_stmt() {
			return getRuleContext(Func_stmtContext.class,0);
		}
		public Alpha_numContext alpha_num() {
			return getRuleContext(Alpha_numContext.class,0);
		}
		public Numeric_numContext numeric_num() {
			return getRuleContext(Numeric_numContext.class,0);
		}
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public TerminalNode EQ() { return getToken(SliceParser.EQ, 0); }
		public Return_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterReturn_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitReturn_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitReturn_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_stmtContext return_stmt() throws RecognitionException {
		Return_stmtContext _localctx = new Return_stmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_return_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			match(RETURN);
			setState(389);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(376);
				variable_name();
				}
				break;
			case 2:
				{
				setState(377);
				func_stmt();
				}
				break;
			case 3:
				{
				setState(378);
				alpha_num();
				}
				break;
			case 4:
				{
				setState(379);
				numeric_num();
				}
				break;
			case 5:
				{
				setState(380);
				bool();
				}
				break;
			case 6:
				{
				{
				setState(381);
				variable_name();
				setState(382);
				match(EQ);
				setState(387);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NAME:
					{
					setState(383);
					variable_name();
					}
					break;
				case ZERO:
				case ONE:
				case TWO:
				case THREE:
				case FOUR:
				case FIVE:
				case SIX:
				case SEVEN:
				case EIGHT:
				case NINE:
				case TEN:
				case ELEVEN:
				case TWELVE:
					{
					setState(384);
					alpha_num();
					}
					break;
				case INT:
				case DECIMAL:
					{
					setState(385);
					numeric_num();
					}
					break;
				case TRUE:
				case FALSE:
					{
					setState(386);
					bool();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Exit_stmtContext extends ParserRuleContext {
		public TerminalNode EXIT() { return getToken(SliceParser.EXIT, 0); }
		public Exit_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exit_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterExit_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitExit_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitExit_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exit_stmtContext exit_stmt() throws RecognitionException {
		Exit_stmtContext _localctx = new Exit_stmtContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_exit_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			match(EXIT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Permute_stmtContext extends ParserRuleContext {
		public TerminalNode PERMUTE() { return getToken(SliceParser.PERMUTE, 0); }
		public TerminalNode AT() { return getToken(SliceParser.AT, 0); }
		public Subject_bondContext subject_bond() {
			return getRuleContext(Subject_bondContext.class,0);
		}
		public Permute_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_permute_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPermute_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPermute_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPermute_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Permute_stmtContext permute_stmt() throws RecognitionException {
		Permute_stmtContext _localctx = new Permute_stmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_permute_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(393);
			match(PERMUTE);
			setState(394);
			match(AT);
			setState(395);
			subject_bond();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Mech_stmtContext extends ParserRuleContext {
		public Subject_bondContext subject_bond() {
			return getRuleContext(Subject_bondContext.class,0);
		}
		public TerminalNode INVERT() { return getToken(SliceParser.INVERT, 0); }
		public TerminalNode DEFINED_DOWN() { return getToken(SliceParser.DEFINED_DOWN, 0); }
		public TerminalNode DEFINED_UP() { return getToken(SliceParser.DEFINED_UP, 0); }
		public TerminalNode AT() { return getToken(SliceParser.AT, 0); }
		public TerminalNode ATOM() { return getToken(SliceParser.ATOM, 0); }
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public TerminalNode ANIONIZE() { return getToken(SliceParser.ANIONIZE, 0); }
		public TerminalNode CATIONIZE() { return getToken(SliceParser.CATIONIZE, 0); }
		public TerminalNode NEUTRALIZE() { return getToken(SliceParser.NEUTRALIZE, 0); }
		public TerminalNode RADICALIZE() { return getToken(SliceParser.RADICALIZE, 0); }
		public TerminalNode RACEMIZE() { return getToken(SliceParser.RACEMIZE, 0); }
		public Mech_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mech_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterMech_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitMech_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitMech_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Mech_stmtContext mech_stmt() throws RecognitionException {
		Mech_stmtContext _localctx = new Mech_stmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_mech_stmt);
		int _la;
		try {
			setState(408);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INVERT:
			case DEFINED_DOWN:
			case DEFINED_UP:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(397);
				_la = _input.LA(1);
				if ( !(((((_la - 325)) & ~0x3f) == 0 && ((1L << (_la - 325)) & 25L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(399);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AT) {
					{
					setState(398);
					match(AT);
					}
				}

				setState(401);
				subject_bond();
				}
				}
				break;
			case ANIONIZE:
			case CATIONIZE:
			case NEUTRALIZE:
			case RACEMIZE:
			case RADICALIZE:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(402);
				_la = _input.LA(1);
				if ( !(((((_la - 326)) & ~0x3f) == 0 && ((1L << (_la - 326)) & 1795L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(404);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AT) {
					{
					setState(403);
					match(AT);
					}
				}

				setState(406);
				match(ATOM);
				setState(407);
				match(INT);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Ghost_stmtContext extends ParserRuleContext {
		public TerminalNode DEFINED() { return getToken(SliceParser.DEFINED, 0); }
		public TerminalNode IT() { return getToken(SliceParser.IT, 0); }
		public TerminalNode AS() { return getToken(SliceParser.AS, 0); }
		public TerminalNode GHOST() { return getToken(SliceParser.GHOST, 0); }
		public TerminalNode MOLECULE() { return getToken(SliceParser.MOLECULE, 0); }
		public Ghost_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ghost_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterGhost_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitGhost_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitGhost_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ghost_stmtContext ghost_stmt() throws RecognitionException {
		Ghost_stmtContext _localctx = new Ghost_stmtContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_ghost_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(410);
			match(DEFINED);
			setState(411);
			match(IT);
			setState(412);
			match(AS);
			setState(413);
			match(GHOST);
			setState(414);
			match(MOLECULE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Import_stmtContext extends ParserRuleContext {
		public Import_moduleContext import_module() {
			return getRuleContext(Import_moduleContext.class,0);
		}
		public Import_fromContext import_from() {
			return getRuleContext(Import_fromContext.class,0);
		}
		public Import_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterImport_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitImport_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitImport_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Import_stmtContext import_stmt() throws RecognitionException {
		Import_stmtContext _localctx = new Import_stmtContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_import_stmt);
		try {
			setState(418);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IMPORT:
				enterOuterAlt(_localctx, 1);
				{
				setState(416);
				import_module();
				}
				break;
			case FROM:
				enterOuterAlt(_localctx, 2);
				{
				setState(417);
				import_from();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Import_moduleContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(SliceParser.IMPORT, 0); }
		public Import_as_namesContext import_as_names() {
			return getRuleContext(Import_as_namesContext.class,0);
		}
		public Import_moduleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_module; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterImport_module(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitImport_module(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitImport_module(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Import_moduleContext import_module() throws RecognitionException {
		Import_moduleContext _localctx = new Import_moduleContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_import_module);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(420);
			match(IMPORT);
			setState(421);
			import_as_names();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Import_fromContext extends ParserRuleContext {
		public TerminalNode FROM() { return getToken(SliceParser.FROM, 0); }
		public TerminalNode IMPORT() { return getToken(SliceParser.IMPORT, 0); }
		public TerminalNode NAME() { return getToken(SliceParser.NAME, 0); }
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public TerminalNode STRING() { return getToken(SliceParser.STRING, 0); }
		public TerminalNode TIMES() { return getToken(SliceParser.TIMES, 0); }
		public TerminalNode LPAR() { return getToken(SliceParser.LPAR, 0); }
		public Import_as_namesContext import_as_names() {
			return getRuleContext(Import_as_namesContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(SliceParser.RPAR, 0); }
		public Import_fromContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_from; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterImport_from(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitImport_from(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitImport_from(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Import_fromContext import_from() throws RecognitionException {
		Import_fromContext _localctx = new Import_fromContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_import_from);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(423);
			match(FROM);
			setState(427);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(424);
				match(NAME);
				}
				break;
			case 2:
				{
				setState(425);
				path();
				}
				break;
			case 3:
				{
				setState(426);
				match(STRING);
				}
				break;
			}
			setState(429);
			match(IMPORT);
			setState(436);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TIMES:
				{
				setState(430);
				match(TIMES);
				}
				break;
			case LPAR:
				{
				setState(431);
				match(LPAR);
				setState(432);
				import_as_names();
				setState(433);
				match(RPAR);
				}
				break;
			case DIV:
			case STRING:
			case NAME:
				{
				setState(435);
				import_as_names();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Import_as_nameContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(SliceParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(SliceParser.NAME, i);
		}
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public TerminalNode STRING() { return getToken(SliceParser.STRING, 0); }
		public TerminalNode AS() { return getToken(SliceParser.AS, 0); }
		public Import_as_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_as_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterImport_as_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitImport_as_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitImport_as_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Import_as_nameContext import_as_name() throws RecognitionException {
		Import_as_nameContext _localctx = new Import_as_nameContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_import_as_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(438);
				match(NAME);
				}
				break;
			case 2:
				{
				setState(439);
				path();
				}
				break;
			case 3:
				{
				setState(440);
				match(STRING);
				}
				break;
			}
			setState(445);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(443);
				match(AS);
				setState(444);
				match(NAME);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Import_as_namesContext extends ParserRuleContext {
		public List<Import_as_nameContext> import_as_name() {
			return getRuleContexts(Import_as_nameContext.class);
		}
		public Import_as_nameContext import_as_name(int i) {
			return getRuleContext(Import_as_nameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SliceParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SliceParser.COMMA, i);
		}
		public Import_as_namesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_as_names; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterImport_as_names(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitImport_as_names(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitImport_as_names(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Import_as_namesContext import_as_names() throws RecognitionException {
		Import_as_namesContext _localctx = new Import_as_namesContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_import_as_names);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(447);
			import_as_name();
			setState(452);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(448);
					match(COMMA);
					setState(449);
					import_as_name();
					}
					} 
				}
				setState(454);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			setState(456);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(455);
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Compound_stmtContext extends ParserRuleContext {
		public If_stmtContext if_stmt() {
			return getRuleContext(If_stmtContext.class,0);
		}
		public Foreach_stmtContext foreach_stmt() {
			return getRuleContext(Foreach_stmtContext.class,0);
		}
		public FuncdefContext funcdef() {
			return getRuleContext(FuncdefContext.class,0);
		}
		public Compound_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterCompound_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitCompound_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitCompound_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Compound_stmtContext compound_stmt() throws RecognitionException {
		Compound_stmtContext _localctx = new Compound_stmtContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_compound_stmt);
		try {
			setState(461);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
				enterOuterAlt(_localctx, 1);
				{
				setState(458);
				if_stmt();
				}
				break;
			case FOREACH:
				enterOuterAlt(_localctx, 2);
				{
				setState(459);
				foreach_stmt();
				}
				break;
			case FUNCTION:
				enterOuterAlt(_localctx, 3);
				{
				setState(460);
				funcdef();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class If_stmtContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(SliceParser.IF, 0); }
		public List<TestContext> test() {
			return getRuleContexts(TestContext.class);
		}
		public TestContext test(int i) {
			return getRuleContext(TestContext.class,i);
		}
		public List<TerminalNode> ELIF() { return getTokens(SliceParser.ELIF); }
		public TerminalNode ELIF(int i) {
			return getToken(SliceParser.ELIF, i);
		}
		public TerminalNode ELSE() { return getToken(SliceParser.ELSE, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<TerminalNode> THEN() { return getTokens(SliceParser.THEN); }
		public TerminalNode THEN(int i) {
			return getToken(SliceParser.THEN, i);
		}
		public List<Small_stmtContext> small_stmt() {
			return getRuleContexts(Small_stmtContext.class);
		}
		public Small_stmtContext small_stmt(int i) {
			return getRuleContext(Small_stmtContext.class,i);
		}
		public If_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterIf_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitIf_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitIf_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_stmtContext if_stmt() throws RecognitionException {
		If_stmtContext _localctx = new If_stmtContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_if_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(463);
			match(IF);
			setState(464);
			test();
			setState(471);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				{
				setState(466);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==THEN) {
					{
					setState(465);
					match(THEN);
					}
				}

				setState(468);
				block();
				}
				}
				break;
			case 2:
				{
				{
				setState(469);
				match(THEN);
				setState(470);
				small_stmt();
				}
				}
				break;
			}
			setState(485);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELIF) {
				{
				{
				setState(473);
				match(ELIF);
				setState(474);
				test();
				setState(481);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					{
					setState(476);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==THEN) {
						{
						setState(475);
						match(THEN);
						}
					}

					setState(478);
					block();
					}
					}
					break;
				case 2:
					{
					{
					setState(479);
					match(THEN);
					setState(480);
					small_stmt();
					}
					}
					break;
				}
				}
				}
				setState(487);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(499);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(488);
				match(ELSE);
				setState(497);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					{
					setState(490);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==THEN) {
						{
						setState(489);
						match(THEN);
						}
					}

					setState(492);
					block();
					}
					}
					break;
				case 2:
					{
					{
					setState(494);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==THEN) {
						{
						setState(493);
						match(THEN);
						}
					}

					setState(496);
					small_stmt();
					}
					}
					break;
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TestContext extends ParserRuleContext {
		public Or_testContext or_test() {
			return getRuleContext(Or_testContext.class,0);
		}
		public TestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitTest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitTest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestContext test() throws RecognitionException {
		TestContext _localctx = new TestContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_test);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(501);
			or_test();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Or_testContext extends ParserRuleContext {
		public List<And_testContext> and_test() {
			return getRuleContexts(And_testContext.class);
		}
		public And_testContext and_test(int i) {
			return getRuleContext(And_testContext.class,i);
		}
		public List<TerminalNode> ORIF() { return getTokens(SliceParser.ORIF); }
		public TerminalNode ORIF(int i) {
			return getToken(SliceParser.ORIF, i);
		}
		public Or_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterOr_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitOr_test(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitOr_test(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Or_testContext or_test() throws RecognitionException {
		Or_testContext _localctx = new Or_testContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_or_test);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(503);
			and_test();
			setState(508);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ORIF) {
				{
				{
				setState(504);
				match(ORIF);
				setState(505);
				and_test();
				}
				}
				setState(510);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class And_testContext extends ParserRuleContext {
		public List<Test_blockContext> test_block() {
			return getRuleContexts(Test_blockContext.class);
		}
		public Test_blockContext test_block(int i) {
			return getRuleContext(Test_blockContext.class,i);
		}
		public List<Test_stmtContext> test_stmt() {
			return getRuleContexts(Test_stmtContext.class);
		}
		public Test_stmtContext test_stmt(int i) {
			return getRuleContext(Test_stmtContext.class,i);
		}
		public List<TerminalNode> ANDIF() { return getTokens(SliceParser.ANDIF); }
		public TerminalNode ANDIF(int i) {
			return getToken(SliceParser.ANDIF, i);
		}
		public And_testContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and_test; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterAnd_test(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitAnd_test(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitAnd_test(this);
			else return visitor.visitChildren(this);
		}
	}

	public final And_testContext and_test() throws RecognitionException {
		And_testContext _localctx = new And_testContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_and_test);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(513);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(511);
				test_block();
				}
				break;
			case 2:
				{
				setState(512);
				test_stmt();
				}
				break;
			}
			setState(522);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ANDIF) {
				{
				{
				setState(515);
				match(ANDIF);
				setState(518);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
				case 1:
					{
					setState(516);
					test_block();
					}
					break;
				case 2:
					{
					setState(517);
					test_stmt();
					}
					break;
				}
				}
				}
				setState(524);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Test_blockContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(SliceParser.LPAR, 0); }
		public Test_stmtContext test_stmt() {
			return getRuleContext(Test_stmtContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(SliceParser.RPAR, 0); }
		public Test_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_test_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterTest_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitTest_block(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitTest_block(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Test_blockContext test_block() throws RecognitionException {
		Test_blockContext _localctx = new Test_blockContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_test_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(525);
			match(LPAR);
			setState(526);
			test_stmt();
			setState(527);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Foreach_stmtContext extends ParserRuleContext {
		public TerminalNode FOREACH() { return getToken(SliceParser.FOREACH, 0); }
		public Loop_stmtContext loop_stmt() {
			return getRuleContext(Loop_stmtContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Foreach_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreach_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterForeach_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitForeach_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitForeach_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Foreach_stmtContext foreach_stmt() throws RecognitionException {
		Foreach_stmtContext _localctx = new Foreach_stmtContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_foreach_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(529);
			match(FOREACH);
			setState(530);
			loop_stmt();
			setState(531);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode CLPAR() { return getToken(SliceParser.CLPAR, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public TerminalNode CRPAR() { return getToken(SliceParser.CRPAR, 0); }
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(533);
			match(CLPAR);
			setState(534);
			suite();
			setState(535);
			match(CRPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SuiteContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterSuite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitSuite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitSuite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_suite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(540);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7323648L) != 0) || ((((_la - 288)) & ~0x3f) == 0 && ((1L << (_la - 288)) & 496842622107655L) != 0) || ((((_la - 365)) & ~0x3f) == 0 && ((1L << (_la - 365)) & 4611686018697920677L) != 0) || _la==NAME) {
				{
				{
				setState(537);
				stmt();
				}
				}
				setState(542);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncdefContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(SliceParser.FUNCTION, 0); }
		public Func_stmtContext func_stmt() {
			return getRuleContext(Func_stmtContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FuncdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcdef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterFuncdef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitFuncdef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitFuncdef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncdefContext funcdef() throws RecognitionException {
		FuncdefContext _localctx = new FuncdefContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_funcdef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(543);
			match(FUNCTION);
			setState(544);
			func_stmt();
			setState(545);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_stmtContext extends ParserRuleContext {
		public Function_nameContext function_name() {
			return getRuleContext(Function_nameContext.class,0);
		}
		public ParametersContext parameters() {
			return getRuleContext(ParametersContext.class,0);
		}
		public Func_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterFunc_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitFunc_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitFunc_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_stmtContext func_stmt() throws RecognitionException {
		Func_stmtContext _localctx = new Func_stmtContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_func_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(547);
			function_name();
			setState(548);
			parameters();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParametersContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(SliceParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SliceParser.RPAR, 0); }
		public FplistContext fplist() {
			return getRuleContext(FplistContext.class,0);
		}
		public ParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametersContext parameters() throws RecognitionException {
		ParametersContext _localctx = new ParametersContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_parameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(550);
			match(LPAR);
			setState(552);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 23)) & ~0x3f) == 0 && ((1L << (_la - 23)) & -1L) != 0) || ((((_la - 87)) & ~0x3f) == 0 && ((1L << (_la - 87)) & 4503599627370495L) != 0) || ((((_la - 226)) & ~0x3f) == 0 && ((1L << (_la - 226)) & 4035225266123964417L) != 0) || ((((_la - 292)) & ~0x3f) == 0 && ((1L << (_la - 292)) & 2147500033L) != 0) || ((((_la - 360)) & ~0x3f) == 0 && ((1L << (_la - 360)) & 393231L) != 0) || ((((_la - 430)) & ~0x3f) == 0 && ((1L << (_la - 430)) & 39L) != 0)) {
				{
				setState(551);
				fplist();
				}
			}

			setState(554);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FplistContext extends ParserRuleContext {
		public List<FpdefContext> fpdef() {
			return getRuleContexts(FpdefContext.class);
		}
		public FpdefContext fpdef(int i) {
			return getRuleContext(FpdefContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SliceParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SliceParser.COMMA, i);
		}
		public FplistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fplist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterFplist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitFplist(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitFplist(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FplistContext fplist() throws RecognitionException {
		FplistContext _localctx = new FplistContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_fplist);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(556);
			fpdef();
			setState(561);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(557);
					match(COMMA);
					setState(558);
					fpdef();
					}
					} 
				}
				setState(563);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			}
			setState(565);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(564);
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FpdefContext extends ParserRuleContext {
		public Variable_nameContext variable_name() {
			return getRuleContext(Variable_nameContext.class,0);
		}
		public Numeric_numContext numeric_num() {
			return getRuleContext(Numeric_numContext.class,0);
		}
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public SubjectsContext subjects() {
			return getRuleContext(SubjectsContext.class,0);
		}
		public FpdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fpdef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterFpdef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitFpdef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitFpdef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FpdefContext fpdef() throws RecognitionException {
		FpdefContext _localctx = new FpdefContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_fpdef);
		try {
			setState(571);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(567);
				variable_name();
				}
				break;
			case INT:
			case DECIMAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(568);
				numeric_num();
				}
				break;
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(569);
				bool();
				}
				break;
			case HYDROGEN:
			case HELIUM:
			case LITHIUM:
			case BERYLLIUM:
			case BORON:
			case CARBON:
			case NITROGEN:
			case OXYGEN:
			case FLUORINE:
			case NEON:
			case SODIUM:
			case MAGNESIUM:
			case ALUMINIUM:
			case SILICON:
			case PHOSPHORUS:
			case SULFUR:
			case CHLORINE:
			case ARGON:
			case POTASSIUM:
			case CALCIUM:
			case SCANDIUM:
			case TITANIUM:
			case VANADIUM:
			case CHROMIUM:
			case IRON:
			case COBALT:
			case NICKEL:
			case COPPER:
			case ZINC:
			case GALLIUM:
			case GERMANIUM:
			case ARSENIC:
			case SELENIUM:
			case BROMINE:
			case KRYPTON:
			case RUBIDIUM:
			case STRONTIUM:
			case YTTRBIUM:
			case ZIRCONIUM:
			case NIOBIUM:
			case MOLYBDENUM:
			case TECHNETIUM:
			case RUTHENIUM:
			case RHODIUM:
			case PALLADIUM:
			case SILVER:
			case CADMIUM:
			case INDIUM:
			case TIN:
			case ANTIMONY:
			case TELLURIUM:
			case IODINE:
			case XENON:
			case CESIUM:
			case BARIUM:
			case LANTHANUM:
			case HAFNIUM:
			case TANTALUM:
			case TUNGSTEN:
			case RHENIUM:
			case OSMIUM:
			case IRIDIUM:
			case PLATINUM:
			case GOLD:
			case MERCURY:
			case THALLIUM:
			case LEAD:
			case BISMUTH:
			case POLONIUM:
			case ASTATINE:
			case RADON:
			case FRANCIUM:
			case RADIUM:
			case ACTINOIDS:
			case RUTHERFORDIUM:
			case DUBNIUM:
			case SEABORGIUM:
			case BOHRIUM:
			case HASSIUM:
			case MEITNERIUM:
			case DARMSTADIUM:
			case ROENTGENIUM:
			case COPERNICIUM:
			case NIHONIUM:
			case FLEROVIUM:
			case MOSCOVIUM:
			case LIVERMORIUM:
			case TENNESSINE:
			case OGANESSON:
			case CERIUM:
			case PRASEODYMIUM:
			case NEODYMIUM:
			case PROMETHIUM:
			case SAMARIUM:
			case EUROPIUM:
			case GADOLINIUM:
			case TERBIUM:
			case DYSPROSIUM:
			case HOLMIUM:
			case ERBIUM:
			case THULIUM:
			case LUTETIUM:
			case THORIUM:
			case PROTACTINIUM:
			case URANIUM:
			case NEPTUNIUM:
			case PLUTONIUM:
			case AMERICIUM:
			case CURIUM:
			case BERKELIUM:
			case CALIFORNIUM:
			case EINSTEINIUM:
			case FERMIUM:
			case MENDELEVIUM:
			case NOBELIUM:
			case LAWRENCIUM:
			case APPENDAGE:
			case ALPHA:
			case BETA:
			case GAMMA:
			case WITHIN:
			case CURRENT:
			case NUMBER_LHASA:
			case ATOM:
			case BOND:
			case MOLECULE:
			case RING:
			case THE:
				enterOuterAlt(_localctx, 4);
				{
				setState(570);
				subjects();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Test_stmtContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public Test_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_test_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterTest_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitTest_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitTest_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Test_stmtContext test_stmt() throws RecognitionException {
		Test_stmtContext _localctx = new Test_stmtContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_test_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(573);
			term();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Math_comp_opContext extends ParserRuleContext {
		public TerminalNode EQUAL() { return getToken(SliceParser.EQUAL, 0); }
		public TerminalNode NE() { return getToken(SliceParser.NE, 0); }
		public TerminalNode LT() { return getToken(SliceParser.LT, 0); }
		public TerminalNode GT() { return getToken(SliceParser.GT, 0); }
		public TerminalNode GE() { return getToken(SliceParser.GE, 0); }
		public TerminalNode LE() { return getToken(SliceParser.LE, 0); }
		public Math_comp_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_math_comp_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterMath_comp_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitMath_comp_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitMath_comp_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Math_comp_opContext math_comp_op() throws RecognitionException {
		Math_comp_opContext _localctx = new Math_comp_opContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_math_comp_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(575);
			_la = _input.LA(1);
			if ( !(((((_la - 382)) & ~0x3f) == 0 && ((1L << (_la - 382)) & 126977L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelationContext extends ParserRuleContext {
		public Pos_relationContext pos_relation() {
			return getRuleContext(Pos_relationContext.class,0);
		}
		public Neg_relationContext neg_relation() {
			return getRuleContext(Neg_relationContext.class,0);
		}
		public RelationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterRelation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitRelation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitRelation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationContext relation() throws RecognitionException {
		RelationContext _localctx = new RelationContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_relation);
		try {
			setState(579);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(577);
				pos_relation();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(578);
				neg_relation();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Pos_relationContext extends ParserRuleContext {
		public ContainContext contain() {
			return getRuleContext(ContainContext.class,0);
		}
		public EqualContext equal() {
			return getRuleContext(EqualContext.class,0);
		}
		public Pos_relationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pos_relation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPos_relation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPos_relation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPos_relation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Pos_relationContext pos_relation() throws RecognitionException {
		Pos_relationContext _localctx = new Pos_relationContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_pos_relation);
		try {
			setState(583);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case HAS:
			case CONTAINED:
				enterOuterAlt(_localctx, 1);
				{
				setState(581);
				contain();
				}
				break;
			case T__4:
			case HYDROGEN:
			case HELIUM:
			case LITHIUM:
			case BERYLLIUM:
			case BORON:
			case CARBON:
			case NITROGEN:
			case OXYGEN:
			case FLUORINE:
			case NEON:
			case SODIUM:
			case MAGNESIUM:
			case ALUMINIUM:
			case SILICON:
			case PHOSPHORUS:
			case SULFUR:
			case CHLORINE:
			case ARGON:
			case POTASSIUM:
			case CALCIUM:
			case SCANDIUM:
			case TITANIUM:
			case VANADIUM:
			case CHROMIUM:
			case IRON:
			case COBALT:
			case NICKEL:
			case COPPER:
			case ZINC:
			case GALLIUM:
			case GERMANIUM:
			case ARSENIC:
			case SELENIUM:
			case BROMINE:
			case KRYPTON:
			case RUBIDIUM:
			case STRONTIUM:
			case YTTRBIUM:
			case ZIRCONIUM:
			case NIOBIUM:
			case MOLYBDENUM:
			case TECHNETIUM:
			case RUTHENIUM:
			case RHODIUM:
			case PALLADIUM:
			case SILVER:
			case CADMIUM:
			case INDIUM:
			case TIN:
			case ANTIMONY:
			case TELLURIUM:
			case IODINE:
			case XENON:
			case CESIUM:
			case BARIUM:
			case LANTHANUM:
			case HAFNIUM:
			case TANTALUM:
			case TUNGSTEN:
			case RHENIUM:
			case OSMIUM:
			case IRIDIUM:
			case PLATINUM:
			case GOLD:
			case MERCURY:
			case THALLIUM:
			case LEAD:
			case BISMUTH:
			case POLONIUM:
			case ASTATINE:
			case RADON:
			case FRANCIUM:
			case RADIUM:
			case ACTINOIDS:
			case RUTHERFORDIUM:
			case DUBNIUM:
			case SEABORGIUM:
			case BOHRIUM:
			case HASSIUM:
			case MEITNERIUM:
			case DARMSTADIUM:
			case ROENTGENIUM:
			case COPERNICIUM:
			case NIHONIUM:
			case FLEROVIUM:
			case MOSCOVIUM:
			case LIVERMORIUM:
			case TENNESSINE:
			case OGANESSON:
			case CERIUM:
			case PRASEODYMIUM:
			case NEODYMIUM:
			case PROMETHIUM:
			case SAMARIUM:
			case EUROPIUM:
			case GADOLINIUM:
			case TERBIUM:
			case DYSPROSIUM:
			case HOLMIUM:
			case ERBIUM:
			case THULIUM:
			case LUTETIUM:
			case THORIUM:
			case PROTACTINIUM:
			case URANIUM:
			case NEPTUNIUM:
			case PLUTONIUM:
			case AMERICIUM:
			case CURIUM:
			case BERKELIUM:
			case CALIFORNIUM:
			case EINSTEINIUM:
			case FERMIUM:
			case MENDELEVIUM:
			case NOBELIUM:
			case LAWRENCIUM:
			case DITHIOACETAL:
			case HEMIACETAL:
			case ACETAL:
			case ACETYLENE:
			case ACID_HALIDE:
			case ACID:
			case ALCOHOL:
			case ALDEHYDE:
			case ALLENE:
			case AMIDE1:
			case AMIDE2:
			case AMIDE3:
			case AMIDE:
			case AMIDZ:
			case HYDROXYLAMINE:
			case AMINE1:
			case AMINE2:
			case AMINE3:
			case AMINE:
			case AMINE_OXIDE:
			case ANHYDRIDE:
			case AZIDE:
			case AZIRIDINE:
			case AZO:
			case BROMIDE:
			case C_SULFONATE:
			case CARBAMATE_C:
			case CARBAMATE_H:
			case CARBONIUM:
			case CARBONYL:
			case CARBOXYL:
			case CHLORIDE:
			case CYANO:
			case DIAZO:
			case DISULFIDE:
			case DITHIOKETAL:
			case ENAMINE:
			case ENOL_ETHER:
			case EPOXIDE:
			case THIOESTER:
			case ESTER:
			case ESTERX:
			case SILYLENOLETHER:
			case ETHER:
			case FLUORIDE:
			case FUNCTIONAL:
			case GEM_DIHALIDE:
			case GLYCOL:
			case TRIHALIDE:
			case VIC_DIHALIDE:
			case HALIDE:
			case HALOAMINE:
			case HALOHYDRIN:
			case HYDRATE:
			case HYDRAZONE:
			case IMINE:
			case IODIDE:
			case ISOCYANATE:
			case ISOCYANIDE:
			case KETONE:
			case LACTAM:
			case LACTONE:
			case METHYLENE:
			case N_CARBAMATE:
			case N_UREA_C:
			case N_UREA_H:
			case NITRITE:
			case NITRO:
			case NITROSO:
			case O_CARBAMATE:
			case O_CARBONATE:
			case O_SULFONATE:
			case OLEFIN:
			case OXIME:
			case PEROXIDE:
			case PHOSPHINE:
			case PHOSPHONATE:
			case SELENIDE:
			case EPISULFIDE:
			case SULFIDE:
			case SULFONE:
			case SULFOXIDE:
			case THIOCYANATE:
			case THIOL:
			case TRIALKYLSILOXY:
			case TRIALKYLSILYL:
			case VINYLSILANE:
			case ISOPROPYL:
			case METHYL:
			case PHENYL:
			case T_BUTYL:
			case ALKALI_METAL:
			case BENZYLIC:
			case CIS:
			case DOUBLY:
			case ELECTRON_DENSITY:
			case ENOLIZABLE:
			case EQUIVALENT:
			case HALOGEN:
			case HETERO:
			case MULTIPLY:
			case TRANS:
			case TRIPLY:
			case DOUBLE_BOND:
			case MULTIPLE:
			case SINGLE_BOND:
			case SMALL_FUSION:
			case TRIPLE_BOND:
			case ALLYLIC:
			case ANTI:
			case AROMATIC:
			case BREDT_STRAINED:
			case BRIDGEHEAD:
			case CIS_OLEFIN:
			case CONJUGATED:
			case FUSION:
			case STRAINED:
			case SYN:
			case TRANS_OLEFIN:
			case DONATING:
			case NON_EXPANDABLE_WITHDRAWING:
			case EXPANDABLE_WITHDRAWING:
			case INTERFERING:
			case GOOD_LEAVING:
			case LEAVING:
			case PARTICIPATING:
			case PROTECTED:
			case VINYL_D:
			case VINYL_W:
			case WITHDRAWING:
			case PRIMARY_CENTER:
			case SECONDARY_CENTER:
			case TERTIARY_CENTER:
			case QUATERNARY_CENTER:
			case FEWER_THAN:
			case FEWER:
			case OR_LARGER:
			case LARGER:
			case OR_SMALLER:
			case SMALLER:
			case LESS_HINDERED:
			case MORE_HINDERED:
			case THROUGH:
			case ZERO:
			case ONE:
			case TWO:
			case THREE:
			case FOUR:
			case FIVE:
			case SIX:
			case SEVEN:
			case EIGHT:
			case NINE:
			case TEN:
			case ELEVEN:
			case TWELVE:
			case ATOM:
			case BOND:
			case IS:
			case TRUE:
			case FALSE:
			case EQ:
			case GT2:
			case LT2:
			case GE2:
			case LE2:
			case ONLY:
			case IN:
			case TO:
			case THE:
			case INT:
			case DECIMAL:
			case STRING:
			case NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(582);
				equal();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Neg_relationContext extends ParserRuleContext {
		public NotcontainContext notcontain() {
			return getRuleContext(NotcontainContext.class,0);
		}
		public NotequalContext notequal() {
			return getRuleContext(NotequalContext.class,0);
		}
		public Neg_relationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_neg_relation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterNeg_relation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitNeg_relation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitNeg_relation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Neg_relationContext neg_relation() throws RecognitionException {
		Neg_relationContext _localctx = new Neg_relationContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_neg_relation);
		try {
			setState(587);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case HAS:
			case CONTAINED:
				enterOuterAlt(_localctx, 1);
				{
				setState(585);
				notcontain();
				}
				break;
			case IS:
				enterOuterAlt(_localctx, 2);
				{
				setState(586);
				notequal();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public Term_blockContext term_block() {
			return getRuleContext(Term_blockContext.class,0);
		}
		public Atom1Context atom1() {
			return getRuleContext(Atom1Context.class,0);
		}
		public Atom2Context atom2() {
			return getRuleContext(Atom2Context.class,0);
		}
		public Where_stmtContext where_stmt() {
			return getRuleContext(Where_stmtContext.class,0);
		}
		public RelationContext relation() {
			return getRuleContext(RelationContext.class,0);
		}
		public Math_comp_opContext math_comp_op() {
			return getRuleContext(Math_comp_opContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(591);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAR:
				{
				setState(589);
				term_block();
				}
				break;
			case HYDROGEN:
			case HELIUM:
			case LITHIUM:
			case BERYLLIUM:
			case BORON:
			case CARBON:
			case NITROGEN:
			case OXYGEN:
			case FLUORINE:
			case NEON:
			case SODIUM:
			case MAGNESIUM:
			case ALUMINIUM:
			case SILICON:
			case PHOSPHORUS:
			case SULFUR:
			case CHLORINE:
			case ARGON:
			case POTASSIUM:
			case CALCIUM:
			case SCANDIUM:
			case TITANIUM:
			case VANADIUM:
			case CHROMIUM:
			case IRON:
			case COBALT:
			case NICKEL:
			case COPPER:
			case ZINC:
			case GALLIUM:
			case GERMANIUM:
			case ARSENIC:
			case SELENIUM:
			case BROMINE:
			case KRYPTON:
			case RUBIDIUM:
			case STRONTIUM:
			case YTTRBIUM:
			case ZIRCONIUM:
			case NIOBIUM:
			case MOLYBDENUM:
			case TECHNETIUM:
			case RUTHENIUM:
			case RHODIUM:
			case PALLADIUM:
			case SILVER:
			case CADMIUM:
			case INDIUM:
			case TIN:
			case ANTIMONY:
			case TELLURIUM:
			case IODINE:
			case XENON:
			case CESIUM:
			case BARIUM:
			case LANTHANUM:
			case HAFNIUM:
			case TANTALUM:
			case TUNGSTEN:
			case RHENIUM:
			case OSMIUM:
			case IRIDIUM:
			case PLATINUM:
			case GOLD:
			case MERCURY:
			case THALLIUM:
			case LEAD:
			case BISMUTH:
			case POLONIUM:
			case ASTATINE:
			case RADON:
			case FRANCIUM:
			case RADIUM:
			case ACTINOIDS:
			case RUTHERFORDIUM:
			case DUBNIUM:
			case SEABORGIUM:
			case BOHRIUM:
			case HASSIUM:
			case MEITNERIUM:
			case DARMSTADIUM:
			case ROENTGENIUM:
			case COPERNICIUM:
			case NIHONIUM:
			case FLEROVIUM:
			case MOSCOVIUM:
			case LIVERMORIUM:
			case TENNESSINE:
			case OGANESSON:
			case CERIUM:
			case PRASEODYMIUM:
			case NEODYMIUM:
			case PROMETHIUM:
			case SAMARIUM:
			case EUROPIUM:
			case GADOLINIUM:
			case TERBIUM:
			case DYSPROSIUM:
			case HOLMIUM:
			case ERBIUM:
			case THULIUM:
			case LUTETIUM:
			case THORIUM:
			case PROTACTINIUM:
			case URANIUM:
			case NEPTUNIUM:
			case PLUTONIUM:
			case AMERICIUM:
			case CURIUM:
			case BERKELIUM:
			case CALIFORNIUM:
			case EINSTEINIUM:
			case FERMIUM:
			case MENDELEVIUM:
			case NOBELIUM:
			case LAWRENCIUM:
			case APPENDAGE:
			case ALPHA:
			case BETA:
			case GAMMA:
			case WITHIN:
			case CURRENT:
			case NUMBER_LHASA:
			case ATOM:
			case BOND:
			case MOLECULE:
			case RING:
			case THE:
			case NAME:
				{
				setState(590);
				atom1();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(599);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -8388576L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -1L) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & -1L) != 0) || ((((_la - 192)) & ~0x3f) == 0 && ((1L << (_la - 192)) & -2317386249217769473L) != 0) || ((((_la - 256)) & ~0x3f) == 0 && ((1L << (_la - 256)) & 1150669704809938943L) != 0) || ((((_la - 339)) & ~0x3f) == 0 && ((1L << (_la - 339)) & 1116919972046577663L) != 0) || ((((_la - 404)) & ~0x3f) == 0 && ((1L << (_la - 404)) & 3691530511L) != 0)) {
				{
				setState(595);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__4:
				case HYDROGEN:
				case HELIUM:
				case LITHIUM:
				case BERYLLIUM:
				case BORON:
				case CARBON:
				case NITROGEN:
				case OXYGEN:
				case FLUORINE:
				case NEON:
				case SODIUM:
				case MAGNESIUM:
				case ALUMINIUM:
				case SILICON:
				case PHOSPHORUS:
				case SULFUR:
				case CHLORINE:
				case ARGON:
				case POTASSIUM:
				case CALCIUM:
				case SCANDIUM:
				case TITANIUM:
				case VANADIUM:
				case CHROMIUM:
				case IRON:
				case COBALT:
				case NICKEL:
				case COPPER:
				case ZINC:
				case GALLIUM:
				case GERMANIUM:
				case ARSENIC:
				case SELENIUM:
				case BROMINE:
				case KRYPTON:
				case RUBIDIUM:
				case STRONTIUM:
				case YTTRBIUM:
				case ZIRCONIUM:
				case NIOBIUM:
				case MOLYBDENUM:
				case TECHNETIUM:
				case RUTHENIUM:
				case RHODIUM:
				case PALLADIUM:
				case SILVER:
				case CADMIUM:
				case INDIUM:
				case TIN:
				case ANTIMONY:
				case TELLURIUM:
				case IODINE:
				case XENON:
				case CESIUM:
				case BARIUM:
				case LANTHANUM:
				case HAFNIUM:
				case TANTALUM:
				case TUNGSTEN:
				case RHENIUM:
				case OSMIUM:
				case IRIDIUM:
				case PLATINUM:
				case GOLD:
				case MERCURY:
				case THALLIUM:
				case LEAD:
				case BISMUTH:
				case POLONIUM:
				case ASTATINE:
				case RADON:
				case FRANCIUM:
				case RADIUM:
				case ACTINOIDS:
				case RUTHERFORDIUM:
				case DUBNIUM:
				case SEABORGIUM:
				case BOHRIUM:
				case HASSIUM:
				case MEITNERIUM:
				case DARMSTADIUM:
				case ROENTGENIUM:
				case COPERNICIUM:
				case NIHONIUM:
				case FLEROVIUM:
				case MOSCOVIUM:
				case LIVERMORIUM:
				case TENNESSINE:
				case OGANESSON:
				case CERIUM:
				case PRASEODYMIUM:
				case NEODYMIUM:
				case PROMETHIUM:
				case SAMARIUM:
				case EUROPIUM:
				case GADOLINIUM:
				case TERBIUM:
				case DYSPROSIUM:
				case HOLMIUM:
				case ERBIUM:
				case THULIUM:
				case LUTETIUM:
				case THORIUM:
				case PROTACTINIUM:
				case URANIUM:
				case NEPTUNIUM:
				case PLUTONIUM:
				case AMERICIUM:
				case CURIUM:
				case BERKELIUM:
				case CALIFORNIUM:
				case EINSTEINIUM:
				case FERMIUM:
				case MENDELEVIUM:
				case NOBELIUM:
				case LAWRENCIUM:
				case DITHIOACETAL:
				case HEMIACETAL:
				case ACETAL:
				case ACETYLENE:
				case ACID_HALIDE:
				case ACID:
				case ALCOHOL:
				case ALDEHYDE:
				case ALLENE:
				case AMIDE1:
				case AMIDE2:
				case AMIDE3:
				case AMIDE:
				case AMIDZ:
				case HYDROXYLAMINE:
				case AMINE1:
				case AMINE2:
				case AMINE3:
				case AMINE:
				case AMINE_OXIDE:
				case ANHYDRIDE:
				case AZIDE:
				case AZIRIDINE:
				case AZO:
				case BROMIDE:
				case C_SULFONATE:
				case CARBAMATE_C:
				case CARBAMATE_H:
				case CARBONIUM:
				case CARBONYL:
				case CARBOXYL:
				case CHLORIDE:
				case CYANO:
				case DIAZO:
				case DISULFIDE:
				case DITHIOKETAL:
				case ENAMINE:
				case ENOL_ETHER:
				case EPOXIDE:
				case THIOESTER:
				case ESTER:
				case ESTERX:
				case SILYLENOLETHER:
				case ETHER:
				case FLUORIDE:
				case FUNCTIONAL:
				case GEM_DIHALIDE:
				case GLYCOL:
				case TRIHALIDE:
				case VIC_DIHALIDE:
				case HALIDE:
				case HALOAMINE:
				case HALOHYDRIN:
				case HYDRATE:
				case HYDRAZONE:
				case IMINE:
				case IODIDE:
				case ISOCYANATE:
				case ISOCYANIDE:
				case KETONE:
				case LACTAM:
				case LACTONE:
				case METHYLENE:
				case N_CARBAMATE:
				case N_UREA_C:
				case N_UREA_H:
				case NITRITE:
				case NITRO:
				case NITROSO:
				case O_CARBAMATE:
				case O_CARBONATE:
				case O_SULFONATE:
				case OLEFIN:
				case OXIME:
				case PEROXIDE:
				case PHOSPHINE:
				case PHOSPHONATE:
				case SELENIDE:
				case EPISULFIDE:
				case SULFIDE:
				case SULFONE:
				case SULFOXIDE:
				case THIOCYANATE:
				case THIOL:
				case TRIALKYLSILOXY:
				case TRIALKYLSILYL:
				case VINYLSILANE:
				case ISOPROPYL:
				case METHYL:
				case PHENYL:
				case T_BUTYL:
				case ALKALI_METAL:
				case BENZYLIC:
				case CIS:
				case DOUBLY:
				case ELECTRON_DENSITY:
				case ENOLIZABLE:
				case EQUIVALENT:
				case HALOGEN:
				case HETERO:
				case MULTIPLY:
				case TRANS:
				case TRIPLY:
				case DOUBLE_BOND:
				case MULTIPLE:
				case SINGLE_BOND:
				case SMALL_FUSION:
				case TRIPLE_BOND:
				case ALLYLIC:
				case ANTI:
				case AROMATIC:
				case BREDT_STRAINED:
				case BRIDGEHEAD:
				case CIS_OLEFIN:
				case CONJUGATED:
				case FUSION:
				case STRAINED:
				case SYN:
				case TRANS_OLEFIN:
				case DONATING:
				case NON_EXPANDABLE_WITHDRAWING:
				case EXPANDABLE_WITHDRAWING:
				case INTERFERING:
				case GOOD_LEAVING:
				case LEAVING:
				case PARTICIPATING:
				case PROTECTED:
				case VINYL_D:
				case VINYL_W:
				case WITHDRAWING:
				case PRIMARY_CENTER:
				case SECONDARY_CENTER:
				case TERTIARY_CENTER:
				case QUATERNARY_CENTER:
				case FEWER_THAN:
				case FEWER:
				case OR_LARGER:
				case LARGER:
				case OR_SMALLER:
				case SMALLER:
				case LESS_HINDERED:
				case MORE_HINDERED:
				case THROUGH:
				case ZERO:
				case ONE:
				case TWO:
				case THREE:
				case FOUR:
				case FIVE:
				case SIX:
				case SEVEN:
				case EIGHT:
				case NINE:
				case TEN:
				case ELEVEN:
				case TWELVE:
				case ATOM:
				case BOND:
				case IS:
				case HAS:
				case TRUE:
				case FALSE:
				case EQ:
				case GT2:
				case LT2:
				case GE2:
				case LE2:
				case CONTAINED:
				case ONLY:
				case IN:
				case TO:
				case THE:
				case INT:
				case DECIMAL:
				case STRING:
				case NAME:
					{
					setState(593);
					relation();
					}
					break;
				case EQUAL:
				case GT:
				case LT:
				case GE:
				case LE:
				case NE:
					{
					setState(594);
					math_comp_op();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(597);
				atom2();
				}
			}

			setState(602);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 297)) & ~0x3f) == 0 && ((1L << (_la - 297)) & 511L) != 0)) {
				{
				setState(601);
				where_stmt();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Term_blockContext extends ParserRuleContext {
		public TerminalNode LPAR() { return getToken(SliceParser.LPAR, 0); }
		public Atom1Context atom1() {
			return getRuleContext(Atom1Context.class,0);
		}
		public TerminalNode RPAR() { return getToken(SliceParser.RPAR, 0); }
		public Atom2Context atom2() {
			return getRuleContext(Atom2Context.class,0);
		}
		public Where_stmtContext where_stmt() {
			return getRuleContext(Where_stmtContext.class,0);
		}
		public RelationContext relation() {
			return getRuleContext(RelationContext.class,0);
		}
		public Math_comp_opContext math_comp_op() {
			return getRuleContext(Math_comp_opContext.class,0);
		}
		public Term_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterTerm_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitTerm_block(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitTerm_block(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Term_blockContext term_block() throws RecognitionException {
		Term_blockContext _localctx = new Term_blockContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_term_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(604);
			match(LPAR);
			setState(605);
			atom1();
			setState(612);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -8388576L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -1L) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & -1L) != 0) || ((((_la - 192)) & ~0x3f) == 0 && ((1L << (_la - 192)) & -2317386249217769473L) != 0) || ((((_la - 256)) & ~0x3f) == 0 && ((1L << (_la - 256)) & 1150669704809938943L) != 0) || ((((_la - 339)) & ~0x3f) == 0 && ((1L << (_la - 339)) & 1116919972046577663L) != 0) || ((((_la - 404)) & ~0x3f) == 0 && ((1L << (_la - 404)) & 3691530511L) != 0)) {
				{
				setState(608);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__4:
				case HYDROGEN:
				case HELIUM:
				case LITHIUM:
				case BERYLLIUM:
				case BORON:
				case CARBON:
				case NITROGEN:
				case OXYGEN:
				case FLUORINE:
				case NEON:
				case SODIUM:
				case MAGNESIUM:
				case ALUMINIUM:
				case SILICON:
				case PHOSPHORUS:
				case SULFUR:
				case CHLORINE:
				case ARGON:
				case POTASSIUM:
				case CALCIUM:
				case SCANDIUM:
				case TITANIUM:
				case VANADIUM:
				case CHROMIUM:
				case IRON:
				case COBALT:
				case NICKEL:
				case COPPER:
				case ZINC:
				case GALLIUM:
				case GERMANIUM:
				case ARSENIC:
				case SELENIUM:
				case BROMINE:
				case KRYPTON:
				case RUBIDIUM:
				case STRONTIUM:
				case YTTRBIUM:
				case ZIRCONIUM:
				case NIOBIUM:
				case MOLYBDENUM:
				case TECHNETIUM:
				case RUTHENIUM:
				case RHODIUM:
				case PALLADIUM:
				case SILVER:
				case CADMIUM:
				case INDIUM:
				case TIN:
				case ANTIMONY:
				case TELLURIUM:
				case IODINE:
				case XENON:
				case CESIUM:
				case BARIUM:
				case LANTHANUM:
				case HAFNIUM:
				case TANTALUM:
				case TUNGSTEN:
				case RHENIUM:
				case OSMIUM:
				case IRIDIUM:
				case PLATINUM:
				case GOLD:
				case MERCURY:
				case THALLIUM:
				case LEAD:
				case BISMUTH:
				case POLONIUM:
				case ASTATINE:
				case RADON:
				case FRANCIUM:
				case RADIUM:
				case ACTINOIDS:
				case RUTHERFORDIUM:
				case DUBNIUM:
				case SEABORGIUM:
				case BOHRIUM:
				case HASSIUM:
				case MEITNERIUM:
				case DARMSTADIUM:
				case ROENTGENIUM:
				case COPERNICIUM:
				case NIHONIUM:
				case FLEROVIUM:
				case MOSCOVIUM:
				case LIVERMORIUM:
				case TENNESSINE:
				case OGANESSON:
				case CERIUM:
				case PRASEODYMIUM:
				case NEODYMIUM:
				case PROMETHIUM:
				case SAMARIUM:
				case EUROPIUM:
				case GADOLINIUM:
				case TERBIUM:
				case DYSPROSIUM:
				case HOLMIUM:
				case ERBIUM:
				case THULIUM:
				case LUTETIUM:
				case THORIUM:
				case PROTACTINIUM:
				case URANIUM:
				case NEPTUNIUM:
				case PLUTONIUM:
				case AMERICIUM:
				case CURIUM:
				case BERKELIUM:
				case CALIFORNIUM:
				case EINSTEINIUM:
				case FERMIUM:
				case MENDELEVIUM:
				case NOBELIUM:
				case LAWRENCIUM:
				case DITHIOACETAL:
				case HEMIACETAL:
				case ACETAL:
				case ACETYLENE:
				case ACID_HALIDE:
				case ACID:
				case ALCOHOL:
				case ALDEHYDE:
				case ALLENE:
				case AMIDE1:
				case AMIDE2:
				case AMIDE3:
				case AMIDE:
				case AMIDZ:
				case HYDROXYLAMINE:
				case AMINE1:
				case AMINE2:
				case AMINE3:
				case AMINE:
				case AMINE_OXIDE:
				case ANHYDRIDE:
				case AZIDE:
				case AZIRIDINE:
				case AZO:
				case BROMIDE:
				case C_SULFONATE:
				case CARBAMATE_C:
				case CARBAMATE_H:
				case CARBONIUM:
				case CARBONYL:
				case CARBOXYL:
				case CHLORIDE:
				case CYANO:
				case DIAZO:
				case DISULFIDE:
				case DITHIOKETAL:
				case ENAMINE:
				case ENOL_ETHER:
				case EPOXIDE:
				case THIOESTER:
				case ESTER:
				case ESTERX:
				case SILYLENOLETHER:
				case ETHER:
				case FLUORIDE:
				case FUNCTIONAL:
				case GEM_DIHALIDE:
				case GLYCOL:
				case TRIHALIDE:
				case VIC_DIHALIDE:
				case HALIDE:
				case HALOAMINE:
				case HALOHYDRIN:
				case HYDRATE:
				case HYDRAZONE:
				case IMINE:
				case IODIDE:
				case ISOCYANATE:
				case ISOCYANIDE:
				case KETONE:
				case LACTAM:
				case LACTONE:
				case METHYLENE:
				case N_CARBAMATE:
				case N_UREA_C:
				case N_UREA_H:
				case NITRITE:
				case NITRO:
				case NITROSO:
				case O_CARBAMATE:
				case O_CARBONATE:
				case O_SULFONATE:
				case OLEFIN:
				case OXIME:
				case PEROXIDE:
				case PHOSPHINE:
				case PHOSPHONATE:
				case SELENIDE:
				case EPISULFIDE:
				case SULFIDE:
				case SULFONE:
				case SULFOXIDE:
				case THIOCYANATE:
				case THIOL:
				case TRIALKYLSILOXY:
				case TRIALKYLSILYL:
				case VINYLSILANE:
				case ISOPROPYL:
				case METHYL:
				case PHENYL:
				case T_BUTYL:
				case ALKALI_METAL:
				case BENZYLIC:
				case CIS:
				case DOUBLY:
				case ELECTRON_DENSITY:
				case ENOLIZABLE:
				case EQUIVALENT:
				case HALOGEN:
				case HETERO:
				case MULTIPLY:
				case TRANS:
				case TRIPLY:
				case DOUBLE_BOND:
				case MULTIPLE:
				case SINGLE_BOND:
				case SMALL_FUSION:
				case TRIPLE_BOND:
				case ALLYLIC:
				case ANTI:
				case AROMATIC:
				case BREDT_STRAINED:
				case BRIDGEHEAD:
				case CIS_OLEFIN:
				case CONJUGATED:
				case FUSION:
				case STRAINED:
				case SYN:
				case TRANS_OLEFIN:
				case DONATING:
				case NON_EXPANDABLE_WITHDRAWING:
				case EXPANDABLE_WITHDRAWING:
				case INTERFERING:
				case GOOD_LEAVING:
				case LEAVING:
				case PARTICIPATING:
				case PROTECTED:
				case VINYL_D:
				case VINYL_W:
				case WITHDRAWING:
				case PRIMARY_CENTER:
				case SECONDARY_CENTER:
				case TERTIARY_CENTER:
				case QUATERNARY_CENTER:
				case FEWER_THAN:
				case FEWER:
				case OR_LARGER:
				case LARGER:
				case OR_SMALLER:
				case SMALLER:
				case LESS_HINDERED:
				case MORE_HINDERED:
				case THROUGH:
				case ZERO:
				case ONE:
				case TWO:
				case THREE:
				case FOUR:
				case FIVE:
				case SIX:
				case SEVEN:
				case EIGHT:
				case NINE:
				case TEN:
				case ELEVEN:
				case TWELVE:
				case ATOM:
				case BOND:
				case IS:
				case HAS:
				case TRUE:
				case FALSE:
				case EQ:
				case GT2:
				case LT2:
				case GE2:
				case LE2:
				case CONTAINED:
				case ONLY:
				case IN:
				case TO:
				case THE:
				case INT:
				case DECIMAL:
				case STRING:
				case NAME:
					{
					setState(606);
					relation();
					}
					break;
				case EQUAL:
				case GT:
				case LT:
				case GE:
				case LE:
				case NE:
					{
					setState(607);
					math_comp_op();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(610);
				atom2();
				}
			}

			setState(615);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 297)) & ~0x3f) == 0 && ((1L << (_la - 297)) & 511L) != 0)) {
				{
				setState(614);
				where_stmt();
				}
			}

			setState(617);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Atom1Context extends ParserRuleContext {
		public SubjectsContext subjects() {
			return getRuleContext(SubjectsContext.class,0);
		}
		public Variable_nameContext variable_name() {
			return getRuleContext(Variable_nameContext.class,0);
		}
		public Atom1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterAtom1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitAtom1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitAtom1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Atom1Context atom1() throws RecognitionException {
		Atom1Context _localctx = new Atom1Context(_ctx, getState());
		enterRule(_localctx, 94, RULE_atom1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(621);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case HYDROGEN:
			case HELIUM:
			case LITHIUM:
			case BERYLLIUM:
			case BORON:
			case CARBON:
			case NITROGEN:
			case OXYGEN:
			case FLUORINE:
			case NEON:
			case SODIUM:
			case MAGNESIUM:
			case ALUMINIUM:
			case SILICON:
			case PHOSPHORUS:
			case SULFUR:
			case CHLORINE:
			case ARGON:
			case POTASSIUM:
			case CALCIUM:
			case SCANDIUM:
			case TITANIUM:
			case VANADIUM:
			case CHROMIUM:
			case IRON:
			case COBALT:
			case NICKEL:
			case COPPER:
			case ZINC:
			case GALLIUM:
			case GERMANIUM:
			case ARSENIC:
			case SELENIUM:
			case BROMINE:
			case KRYPTON:
			case RUBIDIUM:
			case STRONTIUM:
			case YTTRBIUM:
			case ZIRCONIUM:
			case NIOBIUM:
			case MOLYBDENUM:
			case TECHNETIUM:
			case RUTHENIUM:
			case RHODIUM:
			case PALLADIUM:
			case SILVER:
			case CADMIUM:
			case INDIUM:
			case TIN:
			case ANTIMONY:
			case TELLURIUM:
			case IODINE:
			case XENON:
			case CESIUM:
			case BARIUM:
			case LANTHANUM:
			case HAFNIUM:
			case TANTALUM:
			case TUNGSTEN:
			case RHENIUM:
			case OSMIUM:
			case IRIDIUM:
			case PLATINUM:
			case GOLD:
			case MERCURY:
			case THALLIUM:
			case LEAD:
			case BISMUTH:
			case POLONIUM:
			case ASTATINE:
			case RADON:
			case FRANCIUM:
			case RADIUM:
			case ACTINOIDS:
			case RUTHERFORDIUM:
			case DUBNIUM:
			case SEABORGIUM:
			case BOHRIUM:
			case HASSIUM:
			case MEITNERIUM:
			case DARMSTADIUM:
			case ROENTGENIUM:
			case COPERNICIUM:
			case NIHONIUM:
			case FLEROVIUM:
			case MOSCOVIUM:
			case LIVERMORIUM:
			case TENNESSINE:
			case OGANESSON:
			case CERIUM:
			case PRASEODYMIUM:
			case NEODYMIUM:
			case PROMETHIUM:
			case SAMARIUM:
			case EUROPIUM:
			case GADOLINIUM:
			case TERBIUM:
			case DYSPROSIUM:
			case HOLMIUM:
			case ERBIUM:
			case THULIUM:
			case LUTETIUM:
			case THORIUM:
			case PROTACTINIUM:
			case URANIUM:
			case NEPTUNIUM:
			case PLUTONIUM:
			case AMERICIUM:
			case CURIUM:
			case BERKELIUM:
			case CALIFORNIUM:
			case EINSTEINIUM:
			case FERMIUM:
			case MENDELEVIUM:
			case NOBELIUM:
			case LAWRENCIUM:
			case APPENDAGE:
			case ALPHA:
			case BETA:
			case GAMMA:
			case WITHIN:
			case CURRENT:
			case NUMBER_LHASA:
			case ATOM:
			case BOND:
			case MOLECULE:
			case RING:
			case THE:
				{
				setState(619);
				subjects();
				}
				break;
			case NAME:
				{
				setState(620);
				variable_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Atom2Context extends ParserRuleContext {
		public PredicatesContext predicates() {
			return getRuleContext(PredicatesContext.class,0);
		}
		public Alpha_numContext alpha_num() {
			return getRuleContext(Alpha_numContext.class,0);
		}
		public Numeric_numContext numeric_num() {
			return getRuleContext(Numeric_numContext.class,0);
		}
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public Variable_nameContext variable_name() {
			return getRuleContext(Variable_nameContext.class,0);
		}
		public TerminalNode STRING() { return getToken(SliceParser.STRING, 0); }
		public Atom2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterAtom2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitAtom2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitAtom2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Atom2Context atom2() throws RecognitionException {
		Atom2Context _localctx = new Atom2Context(_ctx, getState());
		enterRule(_localctx, 96, RULE_atom2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(629);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				setState(623);
				predicates();
				}
				break;
			case 2:
				{
				setState(624);
				alpha_num();
				}
				break;
			case 3:
				{
				setState(625);
				numeric_num();
				}
				break;
			case 4:
				{
				setState(626);
				bool();
				}
				break;
			case 5:
				{
				setState(627);
				variable_name();
				}
				break;
			case 6:
				{
				setState(628);
				match(STRING);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SubjectsContext extends ParserRuleContext {
		public Or_subjectContext or_subject() {
			return getRuleContext(Or_subjectContext.class,0);
		}
		public SubjectsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subjects; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterSubjects(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitSubjects(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitSubjects(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubjectsContext subjects() throws RecognitionException {
		SubjectsContext _localctx = new SubjectsContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_subjects);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(631);
			or_subject();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Or_subjectContext extends ParserRuleContext {
		public List<And_subjectContext> and_subject() {
			return getRuleContexts(And_subjectContext.class);
		}
		public And_subjectContext and_subject(int i) {
			return getRuleContext(And_subjectContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(SliceParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(SliceParser.OR, i);
		}
		public Or_subjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or_subject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterOr_subject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitOr_subject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitOr_subject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Or_subjectContext or_subject() throws RecognitionException {
		Or_subjectContext _localctx = new Or_subjectContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_or_subject);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(633);
			and_subject();
			setState(638);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(634);
				match(OR);
				setState(635);
				and_subject();
				}
				}
				setState(640);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class And_subjectContext extends ParserRuleContext {
		public List<SubjectContext> subject() {
			return getRuleContexts(SubjectContext.class);
		}
		public SubjectContext subject(int i) {
			return getRuleContext(SubjectContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(SliceParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(SliceParser.AND, i);
		}
		public And_subjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and_subject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterAnd_subject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitAnd_subject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitAnd_subject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final And_subjectContext and_subject() throws RecognitionException {
		And_subjectContext _localctx = new And_subjectContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_and_subject);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(641);
			subject();
			setState(646);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(642);
				match(AND);
				setState(643);
				subject();
				}
				}
				setState(648);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PredicatesContext extends ParserRuleContext {
		public Or_predicateContext or_predicate() {
			return getRuleContext(Or_predicateContext.class,0);
		}
		public PredicatesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicates; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicates(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicates(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicates(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicatesContext predicates() throws RecognitionException {
		PredicatesContext _localctx = new PredicatesContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_predicates);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(649);
			or_predicate();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Or_predicateContext extends ParserRuleContext {
		public List<And_predicateContext> and_predicate() {
			return getRuleContexts(And_predicateContext.class);
		}
		public And_predicateContext and_predicate(int i) {
			return getRuleContext(And_predicateContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(SliceParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(SliceParser.OR, i);
		}
		public Or_predicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterOr_predicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitOr_predicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitOr_predicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Or_predicateContext or_predicate() throws RecognitionException {
		Or_predicateContext _localctx = new Or_predicateContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_or_predicate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(651);
			and_predicate();
			setState(656);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(652);
				match(OR);
				setState(653);
				and_predicate();
				}
				}
				setState(658);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class And_predicateContext extends ParserRuleContext {
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(SliceParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(SliceParser.AND, i);
		}
		public And_predicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterAnd_predicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitAnd_predicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitAnd_predicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final And_predicateContext and_predicate() throws RecognitionException {
		And_predicateContext _localctx = new And_predicateContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_and_predicate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(659);
			predicate();
			setState(664);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(660);
				match(AND);
				setState(661);
				predicate();
				}
				}
				setState(666);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Subject_appendagesContext extends ParserRuleContext {
		public TerminalNode APPENDAGE() { return getToken(SliceParser.APPENDAGE, 0); }
		public TerminalNode FROM() { return getToken(SliceParser.FROM, 0); }
		public TerminalNode TOWARDS() { return getToken(SliceParser.TOWARDS, 0); }
		public List<Subject_atom_locsContext> subject_atom_locs() {
			return getRuleContexts(Subject_atom_locsContext.class);
		}
		public Subject_atom_locsContext subject_atom_locs(int i) {
			return getRuleContext(Subject_atom_locsContext.class,i);
		}
		public List<Variable_nameContext> variable_name() {
			return getRuleContexts(Variable_nameContext.class);
		}
		public Variable_nameContext variable_name(int i) {
			return getRuleContext(Variable_nameContext.class,i);
		}
		public TerminalNode THE() { return getToken(SliceParser.THE, 0); }
		public List<TerminalNode> ATOM() { return getTokens(SliceParser.ATOM); }
		public TerminalNode ATOM(int i) {
			return getToken(SliceParser.ATOM, i);
		}
		public List<TerminalNode> INT() { return getTokens(SliceParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(SliceParser.INT, i);
		}
		public Subject_appendagesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subject_appendages; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterSubject_appendages(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitSubject_appendages(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitSubject_appendages(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Subject_appendagesContext subject_appendages() throws RecognitionException {
		Subject_appendagesContext _localctx = new Subject_appendagesContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_subject_appendages);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(668);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THE) {
				{
				setState(667);
				match(THE);
				}
			}

			setState(670);
			match(APPENDAGE);
			setState(671);
			match(FROM);
			setState(676);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATOM:
				{
				{
				setState(672);
				match(ATOM);
				setState(673);
				match(INT);
				}
				}
				break;
			case HYDROGEN:
			case HELIUM:
			case LITHIUM:
			case BERYLLIUM:
			case BORON:
			case CARBON:
			case NITROGEN:
			case OXYGEN:
			case FLUORINE:
			case NEON:
			case SODIUM:
			case MAGNESIUM:
			case ALUMINIUM:
			case SILICON:
			case PHOSPHORUS:
			case SULFUR:
			case CHLORINE:
			case ARGON:
			case POTASSIUM:
			case CALCIUM:
			case SCANDIUM:
			case TITANIUM:
			case VANADIUM:
			case CHROMIUM:
			case IRON:
			case COBALT:
			case NICKEL:
			case COPPER:
			case ZINC:
			case GALLIUM:
			case GERMANIUM:
			case ARSENIC:
			case SELENIUM:
			case BROMINE:
			case KRYPTON:
			case RUBIDIUM:
			case STRONTIUM:
			case YTTRBIUM:
			case ZIRCONIUM:
			case NIOBIUM:
			case MOLYBDENUM:
			case TECHNETIUM:
			case RUTHENIUM:
			case RHODIUM:
			case PALLADIUM:
			case SILVER:
			case CADMIUM:
			case INDIUM:
			case TIN:
			case ANTIMONY:
			case TELLURIUM:
			case IODINE:
			case XENON:
			case CESIUM:
			case BARIUM:
			case LANTHANUM:
			case HAFNIUM:
			case TANTALUM:
			case TUNGSTEN:
			case RHENIUM:
			case OSMIUM:
			case IRIDIUM:
			case PLATINUM:
			case GOLD:
			case MERCURY:
			case THALLIUM:
			case LEAD:
			case BISMUTH:
			case POLONIUM:
			case ASTATINE:
			case RADON:
			case FRANCIUM:
			case RADIUM:
			case ACTINOIDS:
			case RUTHERFORDIUM:
			case DUBNIUM:
			case SEABORGIUM:
			case BOHRIUM:
			case HASSIUM:
			case MEITNERIUM:
			case DARMSTADIUM:
			case ROENTGENIUM:
			case COPERNICIUM:
			case NIHONIUM:
			case FLEROVIUM:
			case MOSCOVIUM:
			case LIVERMORIUM:
			case TENNESSINE:
			case OGANESSON:
			case CERIUM:
			case PRASEODYMIUM:
			case NEODYMIUM:
			case PROMETHIUM:
			case SAMARIUM:
			case EUROPIUM:
			case GADOLINIUM:
			case TERBIUM:
			case DYSPROSIUM:
			case HOLMIUM:
			case ERBIUM:
			case THULIUM:
			case LUTETIUM:
			case THORIUM:
			case PROTACTINIUM:
			case URANIUM:
			case NEPTUNIUM:
			case PLUTONIUM:
			case AMERICIUM:
			case CURIUM:
			case BERKELIUM:
			case CALIFORNIUM:
			case EINSTEINIUM:
			case FERMIUM:
			case MENDELEVIUM:
			case NOBELIUM:
			case LAWRENCIUM:
			case ALPHA:
			case BETA:
			case GAMMA:
			case WITHIN:
			case NUMBER_LHASA:
				{
				setState(674);
				subject_atom_locs();
				}
				break;
			case NAME:
				{
				setState(675);
				variable_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(678);
			match(TOWARDS);
			setState(683);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATOM:
				{
				{
				setState(679);
				match(ATOM);
				setState(680);
				match(INT);
				}
				}
				break;
			case HYDROGEN:
			case HELIUM:
			case LITHIUM:
			case BERYLLIUM:
			case BORON:
			case CARBON:
			case NITROGEN:
			case OXYGEN:
			case FLUORINE:
			case NEON:
			case SODIUM:
			case MAGNESIUM:
			case ALUMINIUM:
			case SILICON:
			case PHOSPHORUS:
			case SULFUR:
			case CHLORINE:
			case ARGON:
			case POTASSIUM:
			case CALCIUM:
			case SCANDIUM:
			case TITANIUM:
			case VANADIUM:
			case CHROMIUM:
			case IRON:
			case COBALT:
			case NICKEL:
			case COPPER:
			case ZINC:
			case GALLIUM:
			case GERMANIUM:
			case ARSENIC:
			case SELENIUM:
			case BROMINE:
			case KRYPTON:
			case RUBIDIUM:
			case STRONTIUM:
			case YTTRBIUM:
			case ZIRCONIUM:
			case NIOBIUM:
			case MOLYBDENUM:
			case TECHNETIUM:
			case RUTHENIUM:
			case RHODIUM:
			case PALLADIUM:
			case SILVER:
			case CADMIUM:
			case INDIUM:
			case TIN:
			case ANTIMONY:
			case TELLURIUM:
			case IODINE:
			case XENON:
			case CESIUM:
			case BARIUM:
			case LANTHANUM:
			case HAFNIUM:
			case TANTALUM:
			case TUNGSTEN:
			case RHENIUM:
			case OSMIUM:
			case IRIDIUM:
			case PLATINUM:
			case GOLD:
			case MERCURY:
			case THALLIUM:
			case LEAD:
			case BISMUTH:
			case POLONIUM:
			case ASTATINE:
			case RADON:
			case FRANCIUM:
			case RADIUM:
			case ACTINOIDS:
			case RUTHERFORDIUM:
			case DUBNIUM:
			case SEABORGIUM:
			case BOHRIUM:
			case HASSIUM:
			case MEITNERIUM:
			case DARMSTADIUM:
			case ROENTGENIUM:
			case COPERNICIUM:
			case NIHONIUM:
			case FLEROVIUM:
			case MOSCOVIUM:
			case LIVERMORIUM:
			case TENNESSINE:
			case OGANESSON:
			case CERIUM:
			case PRASEODYMIUM:
			case NEODYMIUM:
			case PROMETHIUM:
			case SAMARIUM:
			case EUROPIUM:
			case GADOLINIUM:
			case TERBIUM:
			case DYSPROSIUM:
			case HOLMIUM:
			case ERBIUM:
			case THULIUM:
			case LUTETIUM:
			case THORIUM:
			case PROTACTINIUM:
			case URANIUM:
			case NEPTUNIUM:
			case PLUTONIUM:
			case AMERICIUM:
			case CURIUM:
			case BERKELIUM:
			case CALIFORNIUM:
			case EINSTEINIUM:
			case FERMIUM:
			case MENDELEVIUM:
			case NOBELIUM:
			case LAWRENCIUM:
			case ALPHA:
			case BETA:
			case GAMMA:
			case WITHIN:
			case NUMBER_LHASA:
				{
				setState(681);
				subject_atom_locs();
				}
				break;
			case NAME:
				{
				setState(682);
				variable_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_appendagesContext extends ParserRuleContext {
		public TerminalNode THE() { return getToken(SliceParser.THE, 0); }
		public TerminalNode APPENDAGE() { return getToken(SliceParser.APPENDAGE, 0); }
		public TerminalNode FROM() { return getToken(SliceParser.FROM, 0); }
		public TerminalNode TOWARDS() { return getToken(SliceParser.TOWARDS, 0); }
		public List<Subject_atom_locsContext> subject_atom_locs() {
			return getRuleContexts(Subject_atom_locsContext.class);
		}
		public Subject_atom_locsContext subject_atom_locs(int i) {
			return getRuleContext(Subject_atom_locsContext.class,i);
		}
		public List<Variable_nameContext> variable_name() {
			return getRuleContexts(Variable_nameContext.class);
		}
		public Variable_nameContext variable_name(int i) {
			return getRuleContext(Variable_nameContext.class,i);
		}
		public TerminalNode TO() { return getToken(SliceParser.TO, 0); }
		public List<TerminalNode> ATOM() { return getTokens(SliceParser.ATOM); }
		public TerminalNode ATOM(int i) {
			return getToken(SliceParser.ATOM, i);
		}
		public List<TerminalNode> INT() { return getTokens(SliceParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(SliceParser.INT, i);
		}
		public Predicate_appendagesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_appendages; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_appendages(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_appendages(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_appendages(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_appendagesContext predicate_appendages() throws RecognitionException {
		Predicate_appendagesContext _localctx = new Predicate_appendagesContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_predicate_appendages);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(686);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TO) {
				{
				setState(685);
				match(TO);
				}
			}

			setState(688);
			match(THE);
			setState(689);
			match(APPENDAGE);
			setState(690);
			match(FROM);
			setState(695);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATOM:
				{
				{
				setState(691);
				match(ATOM);
				setState(692);
				match(INT);
				}
				}
				break;
			case HYDROGEN:
			case HELIUM:
			case LITHIUM:
			case BERYLLIUM:
			case BORON:
			case CARBON:
			case NITROGEN:
			case OXYGEN:
			case FLUORINE:
			case NEON:
			case SODIUM:
			case MAGNESIUM:
			case ALUMINIUM:
			case SILICON:
			case PHOSPHORUS:
			case SULFUR:
			case CHLORINE:
			case ARGON:
			case POTASSIUM:
			case CALCIUM:
			case SCANDIUM:
			case TITANIUM:
			case VANADIUM:
			case CHROMIUM:
			case IRON:
			case COBALT:
			case NICKEL:
			case COPPER:
			case ZINC:
			case GALLIUM:
			case GERMANIUM:
			case ARSENIC:
			case SELENIUM:
			case BROMINE:
			case KRYPTON:
			case RUBIDIUM:
			case STRONTIUM:
			case YTTRBIUM:
			case ZIRCONIUM:
			case NIOBIUM:
			case MOLYBDENUM:
			case TECHNETIUM:
			case RUTHENIUM:
			case RHODIUM:
			case PALLADIUM:
			case SILVER:
			case CADMIUM:
			case INDIUM:
			case TIN:
			case ANTIMONY:
			case TELLURIUM:
			case IODINE:
			case XENON:
			case CESIUM:
			case BARIUM:
			case LANTHANUM:
			case HAFNIUM:
			case TANTALUM:
			case TUNGSTEN:
			case RHENIUM:
			case OSMIUM:
			case IRIDIUM:
			case PLATINUM:
			case GOLD:
			case MERCURY:
			case THALLIUM:
			case LEAD:
			case BISMUTH:
			case POLONIUM:
			case ASTATINE:
			case RADON:
			case FRANCIUM:
			case RADIUM:
			case ACTINOIDS:
			case RUTHERFORDIUM:
			case DUBNIUM:
			case SEABORGIUM:
			case BOHRIUM:
			case HASSIUM:
			case MEITNERIUM:
			case DARMSTADIUM:
			case ROENTGENIUM:
			case COPERNICIUM:
			case NIHONIUM:
			case FLEROVIUM:
			case MOSCOVIUM:
			case LIVERMORIUM:
			case TENNESSINE:
			case OGANESSON:
			case CERIUM:
			case PRASEODYMIUM:
			case NEODYMIUM:
			case PROMETHIUM:
			case SAMARIUM:
			case EUROPIUM:
			case GADOLINIUM:
			case TERBIUM:
			case DYSPROSIUM:
			case HOLMIUM:
			case ERBIUM:
			case THULIUM:
			case LUTETIUM:
			case THORIUM:
			case PROTACTINIUM:
			case URANIUM:
			case NEPTUNIUM:
			case PLUTONIUM:
			case AMERICIUM:
			case CURIUM:
			case BERKELIUM:
			case CALIFORNIUM:
			case EINSTEINIUM:
			case FERMIUM:
			case MENDELEVIUM:
			case NOBELIUM:
			case LAWRENCIUM:
			case ALPHA:
			case BETA:
			case GAMMA:
			case WITHIN:
			case NUMBER_LHASA:
				{
				setState(693);
				subject_atom_locs();
				}
				break;
			case NAME:
				{
				setState(694);
				variable_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(697);
			match(TOWARDS);
			setState(702);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATOM:
				{
				{
				setState(698);
				match(ATOM);
				setState(699);
				match(INT);
				}
				}
				break;
			case HYDROGEN:
			case HELIUM:
			case LITHIUM:
			case BERYLLIUM:
			case BORON:
			case CARBON:
			case NITROGEN:
			case OXYGEN:
			case FLUORINE:
			case NEON:
			case SODIUM:
			case MAGNESIUM:
			case ALUMINIUM:
			case SILICON:
			case PHOSPHORUS:
			case SULFUR:
			case CHLORINE:
			case ARGON:
			case POTASSIUM:
			case CALCIUM:
			case SCANDIUM:
			case TITANIUM:
			case VANADIUM:
			case CHROMIUM:
			case IRON:
			case COBALT:
			case NICKEL:
			case COPPER:
			case ZINC:
			case GALLIUM:
			case GERMANIUM:
			case ARSENIC:
			case SELENIUM:
			case BROMINE:
			case KRYPTON:
			case RUBIDIUM:
			case STRONTIUM:
			case YTTRBIUM:
			case ZIRCONIUM:
			case NIOBIUM:
			case MOLYBDENUM:
			case TECHNETIUM:
			case RUTHENIUM:
			case RHODIUM:
			case PALLADIUM:
			case SILVER:
			case CADMIUM:
			case INDIUM:
			case TIN:
			case ANTIMONY:
			case TELLURIUM:
			case IODINE:
			case XENON:
			case CESIUM:
			case BARIUM:
			case LANTHANUM:
			case HAFNIUM:
			case TANTALUM:
			case TUNGSTEN:
			case RHENIUM:
			case OSMIUM:
			case IRIDIUM:
			case PLATINUM:
			case GOLD:
			case MERCURY:
			case THALLIUM:
			case LEAD:
			case BISMUTH:
			case POLONIUM:
			case ASTATINE:
			case RADON:
			case FRANCIUM:
			case RADIUM:
			case ACTINOIDS:
			case RUTHERFORDIUM:
			case DUBNIUM:
			case SEABORGIUM:
			case BOHRIUM:
			case HASSIUM:
			case MEITNERIUM:
			case DARMSTADIUM:
			case ROENTGENIUM:
			case COPERNICIUM:
			case NIHONIUM:
			case FLEROVIUM:
			case MOSCOVIUM:
			case LIVERMORIUM:
			case TENNESSINE:
			case OGANESSON:
			case CERIUM:
			case PRASEODYMIUM:
			case NEODYMIUM:
			case PROMETHIUM:
			case SAMARIUM:
			case EUROPIUM:
			case GADOLINIUM:
			case TERBIUM:
			case DYSPROSIUM:
			case HOLMIUM:
			case ERBIUM:
			case THULIUM:
			case LUTETIUM:
			case THORIUM:
			case PROTACTINIUM:
			case URANIUM:
			case NEPTUNIUM:
			case PLUTONIUM:
			case AMERICIUM:
			case CURIUM:
			case BERKELIUM:
			case CALIFORNIUM:
			case EINSTEINIUM:
			case FERMIUM:
			case MENDELEVIUM:
			case NOBELIUM:
			case LAWRENCIUM:
			case ALPHA:
			case BETA:
			case GAMMA:
			case WITHIN:
			case NUMBER_LHASA:
				{
				setState(700);
				subject_atom_locs();
				}
				break;
			case NAME:
				{
				setState(701);
				variable_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SubjectContext extends ParserRuleContext {
		public Subject_atomContext subject_atom() {
			return getRuleContext(Subject_atomContext.class,0);
		}
		public Subject_bondContext subject_bond() {
			return getRuleContext(Subject_bondContext.class,0);
		}
		public Subject_ringContext subject_ring() {
			return getRuleContext(Subject_ringContext.class,0);
		}
		public Subject_moleculeContext subject_molecule() {
			return getRuleContext(Subject_moleculeContext.class,0);
		}
		public Subject_atom_locsContext subject_atom_locs() {
			return getRuleContext(Subject_atom_locsContext.class,0);
		}
		public Subject_or_predicate_atom_typeContext subject_or_predicate_atom_type() {
			return getRuleContext(Subject_or_predicate_atom_typeContext.class,0);
		}
		public Subject_appendagesContext subject_appendages() {
			return getRuleContext(Subject_appendagesContext.class,0);
		}
		public SubjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterSubject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitSubject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitSubject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubjectContext subject() throws RecognitionException {
		SubjectContext _localctx = new SubjectContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_subject);
		try {
			setState(711);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(704);
				subject_atom();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(705);
				subject_bond();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(706);
				subject_ring();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(707);
				subject_molecule();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(708);
				subject_atom_locs();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(709);
				subject_or_predicate_atom_type();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(710);
				subject_appendages();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Subject_atomContext extends ParserRuleContext {
		public TerminalNode ATOM() { return getToken(SliceParser.ATOM, 0); }
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public Subject_atomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subject_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterSubject_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitSubject_atom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitSubject_atom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Subject_atomContext subject_atom() throws RecognitionException {
		Subject_atomContext _localctx = new Subject_atomContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_subject_atom);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(713);
			match(ATOM);
			setState(714);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Subject_bondContext extends ParserRuleContext {
		public TerminalNode BOND() { return getToken(SliceParser.BOND, 0); }
		public TerminalNode BETWEEN() { return getToken(SliceParser.BETWEEN, 0); }
		public TerminalNode AND() { return getToken(SliceParser.AND, 0); }
		public List<Variable_nameContext> variable_name() {
			return getRuleContexts(Variable_nameContext.class);
		}
		public Variable_nameContext variable_name(int i) {
			return getRuleContext(Variable_nameContext.class,i);
		}
		public List<TerminalNode> ATOM() { return getTokens(SliceParser.ATOM); }
		public TerminalNode ATOM(int i) {
			return getToken(SliceParser.ATOM, i);
		}
		public List<TerminalNode> INT() { return getTokens(SliceParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(SliceParser.INT, i);
		}
		public Subject_bondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subject_bond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterSubject_bond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitSubject_bond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitSubject_bond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Subject_bondContext subject_bond() throws RecognitionException {
		Subject_bondContext _localctx = new Subject_bondContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_subject_bond);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(716);
			match(BOND);
			setState(717);
			match(BETWEEN);
			setState(721);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATOM:
				{
				{
				setState(718);
				match(ATOM);
				setState(719);
				match(INT);
				}
				}
				break;
			case NAME:
				{
				setState(720);
				variable_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(723);
			match(AND);
			setState(729);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATOM:
			case INT:
				{
				{
				setState(725);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ATOM) {
					{
					setState(724);
					match(ATOM);
					}
				}

				setState(727);
				match(INT);
				}
				}
				break;
			case NAME:
				{
				setState(728);
				variable_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Subject_ringContext extends ParserRuleContext {
		public TerminalNode RING() { return getToken(SliceParser.RING, 0); }
		public TerminalNode CURRENT() { return getToken(SliceParser.CURRENT, 0); }
		public Subject_ringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subject_ring; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterSubject_ring(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitSubject_ring(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitSubject_ring(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Subject_ringContext subject_ring() throws RecognitionException {
		Subject_ringContext _localctx = new Subject_ringContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_subject_ring);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(732);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CURRENT) {
				{
				setState(731);
				match(CURRENT);
				}
			}

			setState(734);
			match(RING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Subject_moleculeContext extends ParserRuleContext {
		public TerminalNode MOLECULE() { return getToken(SliceParser.MOLECULE, 0); }
		public Subject_moleculeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subject_molecule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterSubject_molecule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitSubject_molecule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitSubject_molecule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Subject_moleculeContext subject_molecule() throws RecognitionException {
		Subject_moleculeContext _localctx = new Subject_moleculeContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_subject_molecule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(736);
			match(MOLECULE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Subject_or_predicate_atom_typeContext extends ParserRuleContext {
		public AtomtypeContext atomtype() {
			return getRuleContext(AtomtypeContext.class,0);
		}
		public TerminalNode ATOM() { return getToken(SliceParser.ATOM, 0); }
		public Subject_or_predicate_atom_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subject_or_predicate_atom_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterSubject_or_predicate_atom_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitSubject_or_predicate_atom_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitSubject_or_predicate_atom_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Subject_or_predicate_atom_typeContext subject_or_predicate_atom_type() throws RecognitionException {
		Subject_or_predicate_atom_typeContext _localctx = new Subject_or_predicate_atom_typeContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_subject_or_predicate_atom_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(738);
			atomtype();
			setState(739);
			match(ATOM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Subject_atom_locsContext extends ParserRuleContext {
		public Atom_locContext atom_loc() {
			return getRuleContext(Atom_locContext.class,0);
		}
		public TerminalNode TO() { return getToken(SliceParser.TO, 0); }
		public Variable_nameContext variable_name() {
			return getRuleContext(Variable_nameContext.class,0);
		}
		public TerminalNode NUMBER_LHASA() { return getToken(SliceParser.NUMBER_LHASA, 0); }
		public TerminalNode OF() { return getToken(SliceParser.OF, 0); }
		public TerminalNode WITHIN() { return getToken(SliceParser.WITHIN, 0); }
		public AtomtypeContext atomtype() {
			return getRuleContext(AtomtypeContext.class,0);
		}
		public TerminalNode INCLUDING() { return getToken(SliceParser.INCLUDING, 0); }
		public TerminalNode EXPLICIT_HYDROGEN() { return getToken(SliceParser.EXPLICIT_HYDROGEN, 0); }
		public TerminalNode ATOM() { return getToken(SliceParser.ATOM, 0); }
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public TerminalNode NOT() { return getToken(SliceParser.NOT, 0); }
		public Subject_atom_locsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subject_atom_locs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterSubject_atom_locs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitSubject_atom_locs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitSubject_atom_locs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Subject_atom_locsContext subject_atom_locs() throws RecognitionException {
		Subject_atom_locsContext _localctx = new Subject_atom_locsContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_subject_atom_locs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(743);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMBER_LHASA) {
				{
				setState(741);
				match(NUMBER_LHASA);
				setState(742);
				match(OF);
				}
			}

			setState(746);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WITHIN) {
				{
				setState(745);
				match(WITHIN);
				}
			}

			setState(749);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 23)) & ~0x3f) == 0 && ((1L << (_la - 23)) & -1L) != 0) || ((((_la - 87)) & ~0x3f) == 0 && ((1L << (_la - 87)) & 4503599627370495L) != 0)) {
				{
				setState(748);
				atomtype();
				}
			}

			setState(751);
			atom_loc();
			setState(752);
			match(TO);
			setState(756);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATOM:
				{
				{
				setState(753);
				match(ATOM);
				setState(754);
				match(INT);
				}
				}
				break;
			case NAME:
				{
				setState(755);
				variable_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(763);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT || _la==INCLUDING) {
				{
				setState(759);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(758);
					match(NOT);
					}
				}

				setState(761);
				match(INCLUDING);
				setState(762);
				match(EXPLICIT_HYDROGEN);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Comp_opContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(SliceParser.EQ, 0); }
		public TerminalNode LT2() { return getToken(SliceParser.LT2, 0); }
		public TerminalNode GT2() { return getToken(SliceParser.GT2, 0); }
		public TerminalNode GE2() { return getToken(SliceParser.GE2, 0); }
		public TerminalNode LE2() { return getToken(SliceParser.LE2, 0); }
		public TerminalNode FEWER_THAN() { return getToken(SliceParser.FEWER_THAN, 0); }
		public TerminalNode FEWER() { return getToken(SliceParser.FEWER, 0); }
		public TerminalNode OR_LARGER() { return getToken(SliceParser.OR_LARGER, 0); }
		public TerminalNode LARGER() { return getToken(SliceParser.LARGER, 0); }
		public TerminalNode OR_SMALLER() { return getToken(SliceParser.OR_SMALLER, 0); }
		public TerminalNode SMALLER() { return getToken(SliceParser.SMALLER, 0); }
		public TerminalNode LESS_HINDERED() { return getToken(SliceParser.LESS_HINDERED, 0); }
		public TerminalNode MORE_HINDERED() { return getToken(SliceParser.MORE_HINDERED, 0); }
		public TerminalNode THROUGH() { return getToken(SliceParser.THROUGH, 0); }
		public Comp_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterComp_op(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitComp_op(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitComp_op(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Comp_opContext comp_op() throws RecognitionException {
		Comp_opContext _localctx = new Comp_opContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_comp_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(765);
			_la = _input.LA(1);
			if ( !(((((_la - 307)) & ~0x3f) == 0 && ((1L << (_la - 307)) & 511L) != 0) || ((((_la - 383)) & ~0x3f) == 0 && ((1L << (_la - 383)) & 31457281L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PredicateContext extends ParserRuleContext {
		public Predicate_atom_propertyContext predicate_atom_property() {
			return getRuleContext(Predicate_atom_propertyContext.class,0);
		}
		public Subject_or_predicate_atom_typeContext subject_or_predicate_atom_type() {
			return getRuleContext(Subject_or_predicate_atom_typeContext.class,0);
		}
		public Predicate_atom_center_propertyContext predicate_atom_center_property() {
			return getRuleContext(Predicate_atom_center_propertyContext.class,0);
		}
		public Predicate_atom_type_countContext predicate_atom_type_count() {
			return getRuleContext(Predicate_atom_type_countContext.class,0);
		}
		public Predicate_charge_countContext predicate_charge_count() {
			return getRuleContext(Predicate_charge_countContext.class,0);
		}
		public Predicate_alkyl_countContext predicate_alkyl_count() {
			return getRuleContext(Predicate_alkyl_countContext.class,0);
		}
		public Predicate_hetero_countContext predicate_hetero_count() {
			return getRuleContext(Predicate_hetero_countContext.class,0);
		}
		public Subject_bondContext subject_bond() {
			return getRuleContext(Subject_bondContext.class,0);
		}
		public Predicate_bond_propertyContext predicate_bond_property() {
			return getRuleContext(Predicate_bond_propertyContext.class,0);
		}
		public Predicate_atom_or_bond_propertyContext predicate_atom_or_bond_property() {
			return getRuleContext(Predicate_atom_or_bond_propertyContext.class,0);
		}
		public Predicate_atom_locsContext predicate_atom_locs() {
			return getRuleContext(Predicate_atom_locsContext.class,0);
		}
		public Predicate_atom_or_bond_or_group_propertyContext predicate_atom_or_bond_or_group_property() {
			return getRuleContext(Predicate_atom_or_bond_or_group_propertyContext.class,0);
		}
		public Predicate_group_propertyContext predicate_group_property() {
			return getRuleContext(Predicate_group_propertyContext.class,0);
		}
		public Predicate_group_property_countContext predicate_group_property_count() {
			return getRuleContext(Predicate_group_property_countContext.class,0);
		}
		public Predicate_functional_groupContext predicate_functional_group() {
			return getRuleContext(Predicate_functional_groupContext.class,0);
		}
		public Predicate_functional_group_countContext predicate_functional_group_count() {
			return getRuleContext(Predicate_functional_group_countContext.class,0);
		}
		public Predicate_non_functional_groupContext predicate_non_functional_group() {
			return getRuleContext(Predicate_non_functional_groupContext.class,0);
		}
		public Predicate_non_functional_group_countContext predicate_non_functional_group_count() {
			return getRuleContext(Predicate_non_functional_group_countContext.class,0);
		}
		public Predicate_smarts_groupContext predicate_smarts_group() {
			return getRuleContext(Predicate_smarts_groupContext.class,0);
		}
		public Predicate_smarts_group_countContext predicate_smarts_group_count() {
			return getRuleContext(Predicate_smarts_group_countContext.class,0);
		}
		public Predicate_ringContext predicate_ring() {
			return getRuleContext(Predicate_ringContext.class,0);
		}
		public Predicate_appendagesContext predicate_appendages() {
			return getRuleContext(Predicate_appendagesContext.class,0);
		}
		public Predicate_atomContext predicate_atom() {
			return getRuleContext(Predicate_atomContext.class,0);
		}
		public Comp_opContext comp_op() {
			return getRuleContext(Comp_opContext.class,0);
		}
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_predicate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(768);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 307)) & ~0x3f) == 0 && ((1L << (_la - 307)) & 511L) != 0) || ((((_la - 383)) & ~0x3f) == 0 && ((1L << (_la - 383)) & 31457281L) != 0)) {
				{
				setState(767);
				comp_op();
				}
			}

			setState(793);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
			case 1:
				{
				setState(770);
				predicate_atom_property();
				}
				break;
			case 2:
				{
				setState(771);
				subject_or_predicate_atom_type();
				}
				break;
			case 3:
				{
				setState(772);
				predicate_atom_center_property();
				}
				break;
			case 4:
				{
				setState(773);
				predicate_atom_type_count();
				}
				break;
			case 5:
				{
				setState(774);
				predicate_charge_count();
				}
				break;
			case 6:
				{
				setState(775);
				predicate_alkyl_count();
				}
				break;
			case 7:
				{
				setState(776);
				predicate_hetero_count();
				}
				break;
			case 8:
				{
				setState(777);
				subject_bond();
				}
				break;
			case 9:
				{
				setState(778);
				predicate_bond_property();
				}
				break;
			case 10:
				{
				setState(779);
				predicate_atom_or_bond_property();
				}
				break;
			case 11:
				{
				setState(780);
				predicate_atom_locs();
				}
				break;
			case 12:
				{
				setState(781);
				predicate_atom_or_bond_or_group_property();
				}
				break;
			case 13:
				{
				setState(782);
				predicate_group_property();
				}
				break;
			case 14:
				{
				setState(783);
				predicate_group_property_count();
				}
				break;
			case 15:
				{
				setState(784);
				predicate_functional_group();
				}
				break;
			case 16:
				{
				setState(785);
				predicate_functional_group_count();
				}
				break;
			case 17:
				{
				setState(786);
				predicate_non_functional_group();
				}
				break;
			case 18:
				{
				setState(787);
				predicate_non_functional_group_count();
				}
				break;
			case 19:
				{
				setState(788);
				predicate_smarts_group();
				}
				break;
			case 20:
				{
				setState(789);
				predicate_smarts_group_count();
				}
				break;
			case 21:
				{
				setState(790);
				predicate_ring();
				}
				break;
			case 22:
				{
				setState(791);
				predicate_appendages();
				}
				break;
			case 23:
				{
				setState(792);
				predicate_atom();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_atomContext extends ParserRuleContext {
		public TerminalNode ATOM() { return getToken(SliceParser.ATOM, 0); }
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public Predicate_atomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_atom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_atom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_atomContext predicate_atom() throws RecognitionException {
		Predicate_atomContext _localctx = new Predicate_atomContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_predicate_atom);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(795);
			match(ATOM);
			setState(796);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_bondContext extends ParserRuleContext {
		public TerminalNode BOND() { return getToken(SliceParser.BOND, 0); }
		public TerminalNode BETWEEN() { return getToken(SliceParser.BETWEEN, 0); }
		public TerminalNode AND() { return getToken(SliceParser.AND, 0); }
		public List<Variable_nameContext> variable_name() {
			return getRuleContexts(Variable_nameContext.class);
		}
		public Variable_nameContext variable_name(int i) {
			return getRuleContext(Variable_nameContext.class,i);
		}
		public List<TerminalNode> ATOM() { return getTokens(SliceParser.ATOM); }
		public TerminalNode ATOM(int i) {
			return getToken(SliceParser.ATOM, i);
		}
		public List<TerminalNode> INT() { return getTokens(SliceParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(SliceParser.INT, i);
		}
		public Predicate_bondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_bond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_bond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_bond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_bond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_bondContext predicate_bond() throws RecognitionException {
		Predicate_bondContext _localctx = new Predicate_bondContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_predicate_bond);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(798);
			match(BOND);
			setState(799);
			match(BETWEEN);
			setState(803);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATOM:
				{
				{
				setState(800);
				match(ATOM);
				setState(801);
				match(INT);
				}
				}
				break;
			case NAME:
				{
				setState(802);
				variable_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(805);
			match(AND);
			setState(811);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATOM:
			case INT:
				{
				{
				setState(807);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ATOM) {
					{
					setState(806);
					match(ATOM);
					}
				}

				setState(809);
				match(INT);
				}
				}
				break;
			case NAME:
				{
				setState(810);
				variable_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_atom_propertyContext extends ParserRuleContext {
		public Atom_propertiesContext atom_properties() {
			return getRuleContext(Atom_propertiesContext.class,0);
		}
		public Predicate_atomContext predicate_atom() {
			return getRuleContext(Predicate_atomContext.class,0);
		}
		public Variable_nameContext variable_name() {
			return getRuleContext(Variable_nameContext.class,0);
		}
		public Stereochemistry_predicate2Context stereochemistry_predicate2() {
			return getRuleContext(Stereochemistry_predicate2Context.class,0);
		}
		public Predicate_atom_propertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_atom_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_atom_property(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_atom_property(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_atom_property(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_atom_propertyContext predicate_atom_property() throws RecognitionException {
		Predicate_atom_propertyContext _localctx = new Predicate_atom_propertyContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_predicate_atom_property);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(813);
			atom_properties();
			setState(817);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				{
				setState(814);
				predicate_atom();
				}
				break;
			case 2:
				{
				setState(815);
				variable_name();
				}
				break;
			case 3:
				{
				setState(816);
				stereochemistry_predicate2();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_atom_center_propertyContext extends ParserRuleContext {
		public Centre_stmtContext centre_stmt() {
			return getRuleContext(Centre_stmtContext.class,0);
		}
		public Predicate_atom_center_propertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_atom_center_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_atom_center_property(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_atom_center_property(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_atom_center_property(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_atom_center_propertyContext predicate_atom_center_property() throws RecognitionException {
		Predicate_atom_center_propertyContext _localctx = new Predicate_atom_center_propertyContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_predicate_atom_center_property);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(819);
			centre_stmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_atom_type_countContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public Subject_or_predicate_atom_typeContext subject_or_predicate_atom_type() {
			return getRuleContext(Subject_or_predicate_atom_typeContext.class,0);
		}
		public Predicate_atom_type_countContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_atom_type_count; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_atom_type_count(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_atom_type_count(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_atom_type_count(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_atom_type_countContext predicate_atom_type_count() throws RecognitionException {
		Predicate_atom_type_countContext _localctx = new Predicate_atom_type_countContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_predicate_atom_type_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(821);
			match(INT);
			setState(822);
			subject_or_predicate_atom_type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_charge_countContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public Charge_stmtContext charge_stmt() {
			return getRuleContext(Charge_stmtContext.class,0);
		}
		public Predicate_charge_countContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_charge_count; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_charge_count(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_charge_count(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_charge_count(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_charge_countContext predicate_charge_count() throws RecognitionException {
		Predicate_charge_countContext _localctx = new Predicate_charge_countContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_predicate_charge_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(824);
			match(INT);
			setState(825);
			charge_stmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_alkyl_countContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public TerminalNode ALKYL() { return getToken(SliceParser.ALKYL, 0); }
		public TerminalNode ATOM() { return getToken(SliceParser.ATOM, 0); }
		public Predicate_alkyl_countContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_alkyl_count; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_alkyl_count(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_alkyl_count(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_alkyl_count(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_alkyl_countContext predicate_alkyl_count() throws RecognitionException {
		Predicate_alkyl_countContext _localctx = new Predicate_alkyl_countContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_predicate_alkyl_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(827);
			match(INT);
			setState(828);
			match(ALKYL);
			setState(829);
			match(ATOM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_hetero_countContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public TerminalNode HETERO() { return getToken(SliceParser.HETERO, 0); }
		public TerminalNode ATOM() { return getToken(SliceParser.ATOM, 0); }
		public Predicate_hetero_countContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_hetero_count; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_hetero_count(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_hetero_count(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_hetero_count(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_hetero_countContext predicate_hetero_count() throws RecognitionException {
		Predicate_hetero_countContext _localctx = new Predicate_hetero_countContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_predicate_hetero_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(831);
			match(INT);
			setState(832);
			match(HETERO);
			setState(833);
			match(ATOM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_bond_propertyContext extends ParserRuleContext {
		public Bond_propertiesContext bond_properties() {
			return getRuleContext(Bond_propertiesContext.class,0);
		}
		public TerminalNode BOND() { return getToken(SliceParser.BOND, 0); }
		public Predicate_bond_propertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_bond_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_bond_property(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_bond_property(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_bond_property(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_bond_propertyContext predicate_bond_property() throws RecognitionException {
		Predicate_bond_propertyContext _localctx = new Predicate_bond_propertyContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_predicate_bond_property);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(835);
			bond_properties();
			setState(837);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BOND) {
				{
				setState(836);
				match(BOND);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_atom_or_bond_propertyContext extends ParserRuleContext {
		public Atom_or_bond_propertiesContext atom_or_bond_properties() {
			return getRuleContext(Atom_or_bond_propertiesContext.class,0);
		}
		public Stereochemistry_predicate1Context stereochemistry_predicate1() {
			return getRuleContext(Stereochemistry_predicate1Context.class,0);
		}
		public Stereochemistry_predicate3Context stereochemistry_predicate3() {
			return getRuleContext(Stereochemistry_predicate3Context.class,0);
		}
		public Predicate_atom_or_bond_propertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_atom_or_bond_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_atom_or_bond_property(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_atom_or_bond_property(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_atom_or_bond_property(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_atom_or_bond_propertyContext predicate_atom_or_bond_property() throws RecognitionException {
		Predicate_atom_or_bond_propertyContext _localctx = new Predicate_atom_or_bond_propertyContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_predicate_atom_or_bond_property);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(839);
			atom_or_bond_properties();
			setState(842);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
			case 1:
				{
				setState(840);
				stereochemistry_predicate1();
				}
				break;
			case 2:
				{
				setState(841);
				stereochemistry_predicate3();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_atom_locsContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public Atom_locContext atom_loc() {
			return getRuleContext(Atom_locContext.class,0);
		}
		public TerminalNode ATOM() { return getToken(SliceParser.ATOM, 0); }
		public TerminalNode WITHIN() { return getToken(SliceParser.WITHIN, 0); }
		public AtomtypeContext atomtype() {
			return getRuleContext(AtomtypeContext.class,0);
		}
		public TerminalNode INCLUDING() { return getToken(SliceParser.INCLUDING, 0); }
		public TerminalNode EXPLICIT_HYDROGEN() { return getToken(SliceParser.EXPLICIT_HYDROGEN, 0); }
		public TerminalNode NOT() { return getToken(SliceParser.NOT, 0); }
		public Predicate_atom_locsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_atom_locs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_atom_locs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_atom_locs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_atom_locs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_atom_locsContext predicate_atom_locs() throws RecognitionException {
		Predicate_atom_locsContext _localctx = new Predicate_atom_locsContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_predicate_atom_locs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(844);
			match(INT);
			setState(846);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WITHIN) {
				{
				setState(845);
				match(WITHIN);
				}
			}

			setState(848);
			atom_loc();
			setState(850);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 23)) & ~0x3f) == 0 && ((1L << (_la - 23)) & -1L) != 0) || ((((_la - 87)) & ~0x3f) == 0 && ((1L << (_la - 87)) & 4503599627370495L) != 0)) {
				{
				setState(849);
				atomtype();
				}
			}

			setState(852);
			match(ATOM);
			setState(858);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT || _la==INCLUDING) {
				{
				setState(854);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(853);
					match(NOT);
					}
				}

				setState(856);
				match(INCLUDING);
				setState(857);
				match(EXPLICIT_HYDROGEN);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_atom_or_bond_or_group_propertyContext extends ParserRuleContext {
		public Atom_or_bond_or_group_propertiesContext atom_or_bond_or_group_properties() {
			return getRuleContext(Atom_or_bond_or_group_propertiesContext.class,0);
		}
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public TerminalNode ATOM() { return getToken(SliceParser.ATOM, 0); }
		public TerminalNode BOND() { return getToken(SliceParser.BOND, 0); }
		public TerminalNode GROUP() { return getToken(SliceParser.GROUP, 0); }
		public Predicate_atom_or_bond_or_group_propertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_atom_or_bond_or_group_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_atom_or_bond_or_group_property(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_atom_or_bond_or_group_property(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_atom_or_bond_or_group_property(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_atom_or_bond_or_group_propertyContext predicate_atom_or_bond_or_group_property() throws RecognitionException {
		Predicate_atom_or_bond_or_group_propertyContext _localctx = new Predicate_atom_or_bond_or_group_propertyContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_predicate_atom_or_bond_or_group_property);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(861);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INT) {
				{
				setState(860);
				match(INT);
				}
			}

			setState(863);
			atom_or_bond_or_group_properties();
			setState(865);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 360)) & ~0x3f) == 0 && ((1L << (_la - 360)) & 72057594037927939L) != 0)) {
				{
				setState(864);
				_la = _input.LA(1);
				if ( !(((((_la - 360)) & ~0x3f) == 0 && ((1L << (_la - 360)) & 72057594037927939L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_group_propertyContext extends ParserRuleContext {
		public Group_propertiesContext group_properties() {
			return getRuleContext(Group_propertiesContext.class,0);
		}
		public TerminalNode GROUP() { return getToken(SliceParser.GROUP, 0); }
		public Predicate_group_propertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_group_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_group_property(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_group_property(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_group_property(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_group_propertyContext predicate_group_property() throws RecognitionException {
		Predicate_group_propertyContext _localctx = new Predicate_group_propertyContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_predicate_group_property);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(867);
			group_properties();
			setState(868);
			match(GROUP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_group_property_countContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public Group_propertiesContext group_properties() {
			return getRuleContext(Group_propertiesContext.class,0);
		}
		public TerminalNode GROUP() { return getToken(SliceParser.GROUP, 0); }
		public Predicate_group_property_countContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_group_property_count; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_group_property_count(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_group_property_count(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_group_property_count(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_group_property_countContext predicate_group_property_count() throws RecognitionException {
		Predicate_group_property_countContext _localctx = new Predicate_group_property_countContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_predicate_group_property_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(870);
			match(INT);
			setState(871);
			group_properties();
			setState(872);
			match(GROUP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_functional_groupContext extends ParserRuleContext {
		public Functional_groupContext functional_group() {
			return getRuleContext(Functional_groupContext.class,0);
		}
		public TerminalNode GROUP() { return getToken(SliceParser.GROUP, 0); }
		public Predicate_functional_groupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_functional_group; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_functional_group(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_functional_group(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_functional_group(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_functional_groupContext predicate_functional_group() throws RecognitionException {
		Predicate_functional_groupContext _localctx = new Predicate_functional_groupContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_predicate_functional_group);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(874);
			functional_group();
			setState(875);
			match(GROUP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_functional_group_countContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public Functional_groupContext functional_group() {
			return getRuleContext(Functional_groupContext.class,0);
		}
		public TerminalNode GROUP() { return getToken(SliceParser.GROUP, 0); }
		public Predicate_functional_group_countContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_functional_group_count; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_functional_group_count(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_functional_group_count(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_functional_group_count(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_functional_group_countContext predicate_functional_group_count() throws RecognitionException {
		Predicate_functional_group_countContext _localctx = new Predicate_functional_group_countContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_predicate_functional_group_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(877);
			match(INT);
			setState(878);
			functional_group();
			setState(879);
			match(GROUP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_non_functional_groupContext extends ParserRuleContext {
		public Non_functional_groupContext non_functional_group() {
			return getRuleContext(Non_functional_groupContext.class,0);
		}
		public TerminalNode GROUP() { return getToken(SliceParser.GROUP, 0); }
		public Predicate_non_functional_groupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_non_functional_group; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_non_functional_group(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_non_functional_group(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_non_functional_group(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_non_functional_groupContext predicate_non_functional_group() throws RecognitionException {
		Predicate_non_functional_groupContext _localctx = new Predicate_non_functional_groupContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_predicate_non_functional_group);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(881);
			non_functional_group();
			setState(882);
			match(GROUP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_non_functional_group_countContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public Non_functional_groupContext non_functional_group() {
			return getRuleContext(Non_functional_groupContext.class,0);
		}
		public TerminalNode GROUP() { return getToken(SliceParser.GROUP, 0); }
		public Predicate_non_functional_group_countContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_non_functional_group_count; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_non_functional_group_count(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_non_functional_group_count(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_non_functional_group_count(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_non_functional_group_countContext predicate_non_functional_group_count() throws RecognitionException {
		Predicate_non_functional_group_countContext _localctx = new Predicate_non_functional_group_countContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_predicate_non_functional_group_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(884);
			match(INT);
			setState(885);
			non_functional_group();
			setState(886);
			match(GROUP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_smarts_groupContext extends ParserRuleContext {
		public SmartsContext smarts() {
			return getRuleContext(SmartsContext.class,0);
		}
		public TerminalNode GROUP() { return getToken(SliceParser.GROUP, 0); }
		public Predicate_smarts_groupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_smarts_group; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_smarts_group(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_smarts_group(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_smarts_group(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_smarts_groupContext predicate_smarts_group() throws RecognitionException {
		Predicate_smarts_groupContext _localctx = new Predicate_smarts_groupContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_predicate_smarts_group);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(888);
			smarts();
			setState(889);
			match(GROUP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_smarts_group_countContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public SmartsContext smarts() {
			return getRuleContext(SmartsContext.class,0);
		}
		public TerminalNode GROUP() { return getToken(SliceParser.GROUP, 0); }
		public Predicate_smarts_group_countContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_smarts_group_count; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_smarts_group_count(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_smarts_group_count(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_smarts_group_count(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_smarts_group_countContext predicate_smarts_group_count() throws RecognitionException {
		Predicate_smarts_group_countContext _localctx = new Predicate_smarts_group_countContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_predicate_smarts_group_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(891);
			match(INT);
			setState(892);
			smarts();
			setState(893);
			match(GROUP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Predicate_ringContext extends ParserRuleContext {
		public TerminalNode IN() { return getToken(SliceParser.IN, 0); }
		public TerminalNode RING() { return getToken(SliceParser.RING, 0); }
		public TerminalNode AN() { return getToken(SliceParser.AN, 0); }
		public Ring_prefix_propContext ring_prefix_prop() {
			return getRuleContext(Ring_prefix_propContext.class,0);
		}
		public TerminalNode ALIPHATIC() { return getToken(SliceParser.ALIPHATIC, 0); }
		public TerminalNode AROMATIC() { return getToken(SliceParser.AROMATIC, 0); }
		public TerminalNode OR() { return getToken(SliceParser.OR, 0); }
		public Ring_sizeContext ring_size() {
			return getRuleContext(Ring_sizeContext.class,0);
		}
		public List<TerminalNode> INT() { return getTokens(SliceParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(SliceParser.INT, i);
		}
		public TerminalNode OR_SMALLER() { return getToken(SliceParser.OR_SMALLER, 0); }
		public TerminalNode OR_LARGER() { return getToken(SliceParser.OR_LARGER, 0); }
		public TerminalNode THROUGH() { return getToken(SliceParser.THROUGH, 0); }
		public Predicate_ringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate_ring; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPredicate_ring(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPredicate_ring(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPredicate_ring(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Predicate_ringContext predicate_ring() throws RecognitionException {
		Predicate_ringContext _localctx = new Predicate_ringContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_predicate_ring);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(895);
			match(IN);
			setState(898);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AN:
				{
				setState(896);
				match(AN);
				}
				break;
			case CARBOCYCLIC:
			case COMMON:
			case HETEROCYCLIC:
				{
				setState(897);
				ring_prefix_prop();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(905);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
			case 1:
				{
				setState(900);
				match(ALIPHATIC);
				}
				break;
			case 2:
				{
				setState(901);
				match(AROMATIC);
				}
				break;
			case 3:
				{
				{
				setState(902);
				match(ALIPHATIC);
				setState(903);
				match(OR);
				setState(904);
				match(AROMATIC);
				}
				}
				break;
			}
			setState(907);
			match(RING);
			setState(917);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OF) {
				{
				{
				setState(908);
				ring_size();
				setState(909);
				match(INT);
				}
				setState(915);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OR_SMALLER:
					{
					setState(911);
					match(OR_SMALLER);
					}
					break;
				case OR_LARGER:
					{
					setState(912);
					match(OR_LARGER);
					}
					break;
				case THROUGH:
					{
					{
					setState(913);
					match(THROUGH);
					setState(914);
					match(INT);
					}
					}
					break;
				case ANYWHERE:
				case OFF_CURRENT_RING:
				case OFFPATH:
				case OFFRING:
				case OFF_THE_BRIDGE:
				case ON_THE_BRIDGE:
				case ONPATH:
				case ONRING:
				case ON_CURRENT_RING:
				case THEN:
				case ORIF:
				case ANDIF:
				case RPAR:
				case CLPAR:
				case AND:
				case OR:
					break;
				default:
					break;
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Stereochemistry_predicate1Context extends ParserRuleContext {
		public TerminalNode TO() { return getToken(SliceParser.TO, 0); }
		public List<Predicate_atomContext> predicate_atom() {
			return getRuleContexts(Predicate_atomContext.class);
		}
		public Predicate_atomContext predicate_atom(int i) {
			return getRuleContext(Predicate_atomContext.class,i);
		}
		public Predicate_bondContext predicate_bond() {
			return getRuleContext(Predicate_bondContext.class,0);
		}
		public List<Variable_nameContext> variable_name() {
			return getRuleContexts(Variable_nameContext.class);
		}
		public Variable_nameContext variable_name(int i) {
			return getRuleContext(Variable_nameContext.class,i);
		}
		public Stereochemistry_predicate1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stereochemistry_predicate1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterStereochemistry_predicate1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitStereochemistry_predicate1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitStereochemistry_predicate1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Stereochemistry_predicate1Context stereochemistry_predicate1() throws RecognitionException {
		Stereochemistry_predicate1Context _localctx = new Stereochemistry_predicate1Context(_ctx, getState());
		enterRule(_localctx, 174, RULE_stereochemistry_predicate1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(922);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATOM:
				{
				setState(919);
				predicate_atom();
				}
				break;
			case BOND:
				{
				setState(920);
				predicate_bond();
				}
				break;
			case NAME:
				{
				setState(921);
				variable_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(924);
			match(T__0);
			setState(927);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATOM:
				{
				setState(925);
				predicate_atom();
				}
				break;
			case NAME:
				{
				setState(926);
				variable_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(929);
			match(TO);
			setState(932);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATOM:
				{
				setState(930);
				predicate_atom();
				}
				break;
			case NAME:
				{
				setState(931);
				variable_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Stereochemistry_predicate2Context extends ParserRuleContext {
		public Predicate_atomContext predicate_atom() {
			return getRuleContext(Predicate_atomContext.class,0);
		}
		public List<Predicate_bondContext> predicate_bond() {
			return getRuleContexts(Predicate_bondContext.class);
		}
		public Predicate_bondContext predicate_bond(int i) {
			return getRuleContext(Predicate_bondContext.class,i);
		}
		public List<Variable_nameContext> variable_name() {
			return getRuleContexts(Variable_nameContext.class);
		}
		public Variable_nameContext variable_name(int i) {
			return getRuleContext(Variable_nameContext.class,i);
		}
		public Stereochemistry_predicate2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stereochemistry_predicate2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterStereochemistry_predicate2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitStereochemistry_predicate2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitStereochemistry_predicate2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Stereochemistry_predicate2Context stereochemistry_predicate2() throws RecognitionException {
		Stereochemistry_predicate2Context _localctx = new Stereochemistry_predicate2Context(_ctx, getState());
		enterRule(_localctx, 176, RULE_stereochemistry_predicate2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(937);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATOM:
				{
				setState(934);
				predicate_atom();
				}
				break;
			case BOND:
				{
				setState(935);
				predicate_bond();
				}
				break;
			case NAME:
				{
				setState(936);
				variable_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(939);
			match(T__1);
			setState(942);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOND:
				{
				setState(940);
				predicate_bond();
				}
				break;
			case NAME:
				{
				setState(941);
				variable_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Stereochemistry_predicate3Context extends ParserRuleContext {
		public Predicate_atomContext predicate_atom() {
			return getRuleContext(Predicate_atomContext.class,0);
		}
		public Predicate_bondContext predicate_bond() {
			return getRuleContext(Predicate_bondContext.class,0);
		}
		public Variable_nameContext variable_name() {
			return getRuleContext(Variable_nameContext.class,0);
		}
		public Stereochemistry_predicate3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stereochemistry_predicate3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterStereochemistry_predicate3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitStereochemistry_predicate3(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitStereochemistry_predicate3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Stereochemistry_predicate3Context stereochemistry_predicate3() throws RecognitionException {
		Stereochemistry_predicate3Context _localctx = new Stereochemistry_predicate3Context(_ctx, getState());
		enterRule(_localctx, 178, RULE_stereochemistry_predicate3);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(947);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATOM:
				{
				setState(944);
				predicate_atom();
				}
				break;
			case BOND:
				{
				setState(945);
				predicate_bond();
				}
				break;
			case NAME:
				{
				setState(946);
				variable_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Loop_stmtContext extends ParserRuleContext {
		public Loop_subjectContext loop_subject() {
			return getRuleContext(Loop_subjectContext.class,0);
		}
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public TerminalNode IN() { return getToken(SliceParser.IN, 0); }
		public Loop_predicateContext loop_predicate() {
			return getRuleContext(Loop_predicateContext.class,0);
		}
		public Where_stmtContext where_stmt() {
			return getRuleContext(Where_stmtContext.class,0);
		}
		public Loop_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterLoop_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitLoop_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitLoop_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Loop_stmtContext loop_stmt() throws RecognitionException {
		Loop_stmtContext _localctx = new Loop_stmtContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_loop_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(949);
			loop_subject();
			setState(951);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 297)) & ~0x3f) == 0 && ((1L << (_la - 297)) & 511L) != 0)) {
				{
				setState(950);
				where_stmt();
				}
			}

			setState(953);
			var();
			setState(954);
			match(IN);
			setState(955);
			loop_predicate();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Loop_subjectContext extends ParserRuleContext {
		public TerminalNode ATOM() { return getToken(SliceParser.ATOM, 0); }
		public TerminalNode BOND() { return getToken(SliceParser.BOND, 0); }
		public TerminalNode RING() { return getToken(SliceParser.RING, 0); }
		public Loop_subject_atom_typeContext loop_subject_atom_type() {
			return getRuleContext(Loop_subject_atom_typeContext.class,0);
		}
		public Loop_subject_atom_propertyContext loop_subject_atom_property() {
			return getRuleContext(Loop_subject_atom_propertyContext.class,0);
		}
		public Loop_subject_atom_combinedContext loop_subject_atom_combined() {
			return getRuleContext(Loop_subject_atom_combinedContext.class,0);
		}
		public Loop_subject_bond_propertyContext loop_subject_bond_property() {
			return getRuleContext(Loop_subject_bond_propertyContext.class,0);
		}
		public Loop_subject_group_propertyContext loop_subject_group_property() {
			return getRuleContext(Loop_subject_group_propertyContext.class,0);
		}
		public Loop_subject_functional_group_propertyContext loop_subject_functional_group_property() {
			return getRuleContext(Loop_subject_functional_group_propertyContext.class,0);
		}
		public Loop_subject_non_functional_group_propertyContext loop_subject_non_functional_group_property() {
			return getRuleContext(Loop_subject_non_functional_group_propertyContext.class,0);
		}
		public Loop_subject_atom_locsContext loop_subject_atom_locs() {
			return getRuleContext(Loop_subject_atom_locsContext.class,0);
		}
		public Atom_on_bondContext atom_on_bond() {
			return getRuleContext(Atom_on_bondContext.class,0);
		}
		public Bond_on_atomContext bond_on_atom() {
			return getRuleContext(Bond_on_atomContext.class,0);
		}
		public Loop_subjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop_subject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterLoop_subject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitLoop_subject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitLoop_subject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Loop_subjectContext loop_subject() throws RecognitionException {
		Loop_subjectContext _localctx = new Loop_subjectContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_loop_subject);
		try {
			setState(971);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(957);
				match(ATOM);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(958);
				match(BOND);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(959);
				match(RING);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(960);
				loop_subject_atom_type();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(961);
				loop_subject_atom_property();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(962);
				loop_subject_atom_combined();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(963);
				loop_subject_bond_property();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(964);
				loop_subject_group_property();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(965);
				loop_subject_functional_group_property();
				setState(966);
				loop_subject_non_functional_group_property();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(968);
				loop_subject_atom_locs();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(969);
				atom_on_bond();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(970);
				bond_on_atom();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Loop_subject_atom_typeContext extends ParserRuleContext {
		public AtomtypeContext atomtype() {
			return getRuleContext(AtomtypeContext.class,0);
		}
		public TerminalNode ATOM() { return getToken(SliceParser.ATOM, 0); }
		public Loop_subject_atom_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop_subject_atom_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterLoop_subject_atom_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitLoop_subject_atom_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitLoop_subject_atom_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Loop_subject_atom_typeContext loop_subject_atom_type() throws RecognitionException {
		Loop_subject_atom_typeContext _localctx = new Loop_subject_atom_typeContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_loop_subject_atom_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(973);
			atomtype();
			setState(974);
			match(ATOM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Loop_subject_atom_propertyContext extends ParserRuleContext {
		public TerminalNode ATOM() { return getToken(SliceParser.ATOM, 0); }
		public Atom_propertiesContext atom_properties() {
			return getRuleContext(Atom_propertiesContext.class,0);
		}
		public Atom_or_bond_propertiesContext atom_or_bond_properties() {
			return getRuleContext(Atom_or_bond_propertiesContext.class,0);
		}
		public SubjectsContext subjects() {
			return getRuleContext(SubjectsContext.class,0);
		}
		public Loop_subject_atom_propertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop_subject_atom_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterLoop_subject_atom_property(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitLoop_subject_atom_property(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitLoop_subject_atom_property(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Loop_subject_atom_propertyContext loop_subject_atom_property() throws RecognitionException {
		Loop_subject_atom_propertyContext _localctx = new Loop_subject_atom_propertyContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_loop_subject_atom_property);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(978);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ALKALI_METAL:
			case BENZYLIC:
			case CIS:
			case DOUBLY:
			case ELECTRON_DENSITY:
			case ENOLIZABLE:
			case EQUIVALENT:
			case HALOGEN:
			case HETERO:
			case MULTIPLY:
			case TRANS:
			case TRIPLY:
			case BREDT_STRAINED:
			case BRIDGEHEAD:
			case CONJUGATED:
			case FUSION:
				{
				setState(976);
				atom_properties();
				}
				break;
			case ALLYLIC:
			case ANTI:
			case AROMATIC:
			case CIS_OLEFIN:
			case STRAINED:
			case SYN:
			case TRANS_OLEFIN:
				{
				setState(977);
				atom_or_bond_properties();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(981);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
			case 1:
				{
				setState(980);
				subjects();
				}
				break;
			}
			setState(983);
			match(ATOM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Loop_subject_atom_combinedContext extends ParserRuleContext {
		public AtomtypeContext atomtype() {
			return getRuleContext(AtomtypeContext.class,0);
		}
		public TerminalNode ATOM() { return getToken(SliceParser.ATOM, 0); }
		public Atom_propertiesContext atom_properties() {
			return getRuleContext(Atom_propertiesContext.class,0);
		}
		public Atom_or_bond_propertiesContext atom_or_bond_properties() {
			return getRuleContext(Atom_or_bond_propertiesContext.class,0);
		}
		public SubjectsContext subjects() {
			return getRuleContext(SubjectsContext.class,0);
		}
		public Loop_subject_atom_combinedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop_subject_atom_combined; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterLoop_subject_atom_combined(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitLoop_subject_atom_combined(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitLoop_subject_atom_combined(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Loop_subject_atom_combinedContext loop_subject_atom_combined() throws RecognitionException {
		Loop_subject_atom_combinedContext _localctx = new Loop_subject_atom_combinedContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_loop_subject_atom_combined);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(987);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ALKALI_METAL:
			case BENZYLIC:
			case CIS:
			case DOUBLY:
			case ELECTRON_DENSITY:
			case ENOLIZABLE:
			case EQUIVALENT:
			case HALOGEN:
			case HETERO:
			case MULTIPLY:
			case TRANS:
			case TRIPLY:
			case BREDT_STRAINED:
			case BRIDGEHEAD:
			case CONJUGATED:
			case FUSION:
				{
				setState(985);
				atom_properties();
				}
				break;
			case ALLYLIC:
			case ANTI:
			case AROMATIC:
			case CIS_OLEFIN:
			case STRAINED:
			case SYN:
			case TRANS_OLEFIN:
				{
				setState(986);
				atom_or_bond_properties();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(990);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,104,_ctx) ) {
			case 1:
				{
				setState(989);
				subjects();
				}
				break;
			}
			setState(992);
			atomtype();
			setState(993);
			match(ATOM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Loop_subject_bond_propertyContext extends ParserRuleContext {
		public TerminalNode BOND() { return getToken(SliceParser.BOND, 0); }
		public Atom_or_bond_propertiesContext atom_or_bond_properties() {
			return getRuleContext(Atom_or_bond_propertiesContext.class,0);
		}
		public Bond_propertiesContext bond_properties() {
			return getRuleContext(Bond_propertiesContext.class,0);
		}
		public Loop_subject_bond_propertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop_subject_bond_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterLoop_subject_bond_property(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitLoop_subject_bond_property(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitLoop_subject_bond_property(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Loop_subject_bond_propertyContext loop_subject_bond_property() throws RecognitionException {
		Loop_subject_bond_propertyContext _localctx = new Loop_subject_bond_propertyContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_loop_subject_bond_property);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(997);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ALLYLIC:
			case ANTI:
			case AROMATIC:
			case CIS_OLEFIN:
			case STRAINED:
			case SYN:
			case TRANS_OLEFIN:
				{
				setState(995);
				atom_or_bond_properties();
				}
				break;
			case DOUBLE_BOND:
			case MULTIPLE:
			case SINGLE_BOND:
			case SMALL_FUSION:
			case TRIPLE_BOND:
			case BREDT_STRAINED:
			case BRIDGEHEAD:
			case CONJUGATED:
			case FUSION:
				{
				setState(996);
				bond_properties();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(999);
			match(BOND);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Loop_subject_group_propertyContext extends ParserRuleContext {
		public TerminalNode GROUP() { return getToken(SliceParser.GROUP, 0); }
		public Atom_or_bond_or_group_propertiesContext atom_or_bond_or_group_properties() {
			return getRuleContext(Atom_or_bond_or_group_propertiesContext.class,0);
		}
		public Group_propertiesContext group_properties() {
			return getRuleContext(Group_propertiesContext.class,0);
		}
		public Loop_subject_group_propertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop_subject_group_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterLoop_subject_group_property(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitLoop_subject_group_property(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitLoop_subject_group_property(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Loop_subject_group_propertyContext loop_subject_group_property() throws RecognitionException {
		Loop_subject_group_propertyContext _localctx = new Loop_subject_group_propertyContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_loop_subject_group_property);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1003);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DONATING:
			case WITHDRAWING:
				{
				setState(1001);
				atom_or_bond_or_group_properties();
				}
				break;
			case NON_EXPANDABLE_WITHDRAWING:
			case EXPANDABLE_WITHDRAWING:
			case INTERFERING:
			case GOOD_LEAVING:
			case LEAVING:
			case PARTICIPATING:
			case PROTECTED:
			case VINYL_D:
			case VINYL_W:
				{
				setState(1002);
				group_properties();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1005);
			match(GROUP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Loop_subject_functional_group_propertyContext extends ParserRuleContext {
		public Functional_groupContext functional_group() {
			return getRuleContext(Functional_groupContext.class,0);
		}
		public TerminalNode GROUP() { return getToken(SliceParser.GROUP, 0); }
		public Loop_subject_functional_group_propertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop_subject_functional_group_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterLoop_subject_functional_group_property(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitLoop_subject_functional_group_property(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitLoop_subject_functional_group_property(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Loop_subject_functional_group_propertyContext loop_subject_functional_group_property() throws RecognitionException {
		Loop_subject_functional_group_propertyContext _localctx = new Loop_subject_functional_group_propertyContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_loop_subject_functional_group_property);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1007);
			functional_group();
			setState(1008);
			match(GROUP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Loop_subject_non_functional_group_propertyContext extends ParserRuleContext {
		public Non_functional_groupContext non_functional_group() {
			return getRuleContext(Non_functional_groupContext.class,0);
		}
		public TerminalNode GROUP() { return getToken(SliceParser.GROUP, 0); }
		public Loop_subject_non_functional_group_propertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop_subject_non_functional_group_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterLoop_subject_non_functional_group_property(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitLoop_subject_non_functional_group_property(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitLoop_subject_non_functional_group_property(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Loop_subject_non_functional_group_propertyContext loop_subject_non_functional_group_property() throws RecognitionException {
		Loop_subject_non_functional_group_propertyContext _localctx = new Loop_subject_non_functional_group_propertyContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_loop_subject_non_functional_group_property);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1010);
			non_functional_group();
			setState(1011);
			match(GROUP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Loop_subject_atom_locsContext extends ParserRuleContext {
		public Atom_locContext atom_loc() {
			return getRuleContext(Atom_locContext.class,0);
		}
		public TerminalNode TO() { return getToken(SliceParser.TO, 0); }
		public Variable_nameContext variable_name() {
			return getRuleContext(Variable_nameContext.class,0);
		}
		public TerminalNode WITHIN() { return getToken(SliceParser.WITHIN, 0); }
		public AtomtypeContext atomtype() {
			return getRuleContext(AtomtypeContext.class,0);
		}
		public TerminalNode INCLUDING() { return getToken(SliceParser.INCLUDING, 0); }
		public TerminalNode EXPLICIT_HYDROGEN() { return getToken(SliceParser.EXPLICIT_HYDROGEN, 0); }
		public TerminalNode ATOM() { return getToken(SliceParser.ATOM, 0); }
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public TerminalNode NOT() { return getToken(SliceParser.NOT, 0); }
		public Loop_subject_atom_locsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop_subject_atom_locs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterLoop_subject_atom_locs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitLoop_subject_atom_locs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitLoop_subject_atom_locs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Loop_subject_atom_locsContext loop_subject_atom_locs() throws RecognitionException {
		Loop_subject_atom_locsContext _localctx = new Loop_subject_atom_locsContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_loop_subject_atom_locs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1014);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WITHIN) {
				{
				setState(1013);
				match(WITHIN);
				}
			}

			setState(1017);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 23)) & ~0x3f) == 0 && ((1L << (_la - 23)) & -1L) != 0) || ((((_la - 87)) & ~0x3f) == 0 && ((1L << (_la - 87)) & 4503599627370495L) != 0)) {
				{
				setState(1016);
				atomtype();
				}
			}

			setState(1019);
			atom_loc();
			setState(1020);
			match(TO);
			setState(1024);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATOM:
				{
				{
				setState(1021);
				match(ATOM);
				setState(1022);
				match(INT);
				}
				}
				break;
			case NAME:
				{
				setState(1023);
				variable_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1031);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT || _la==INCLUDING) {
				{
				setState(1027);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(1026);
					match(NOT);
					}
				}

				setState(1029);
				match(INCLUDING);
				setState(1030);
				match(EXPLICIT_HYDROGEN);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Loop_predicateContext extends ParserRuleContext {
		public TerminalNode MOLECULE() { return getToken(SliceParser.MOLECULE, 0); }
		public Loop_predicate_ringContext loop_predicate_ring() {
			return getRuleContext(Loop_predicate_ringContext.class,0);
		}
		public Variable_nameContext variable_name() {
			return getRuleContext(Variable_nameContext.class,0);
		}
		public Set_current_ringContext set_current_ring() {
			return getRuleContext(Set_current_ringContext.class,0);
		}
		public TerminalNode CURRENT() { return getToken(SliceParser.CURRENT, 0); }
		public TerminalNode RING() { return getToken(SliceParser.RING, 0); }
		public TerminalNode INTERFERING() { return getToken(SliceParser.INTERFERING, 0); }
		public TerminalNode GROUP() { return getToken(SliceParser.GROUP, 0); }
		public TerminalNode PARTICIPATING() { return getToken(SliceParser.PARTICIPATING, 0); }
		public Loop_predicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterLoop_predicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitLoop_predicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitLoop_predicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Loop_predicateContext loop_predicate() throws RecognitionException {
		Loop_predicateContext _localctx = new Loop_predicateContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_loop_predicate);
		try {
			setState(1043);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MOLECULE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1033);
				match(MOLECULE);
				}
				break;
			case IN:
				enterOuterAlt(_localctx, 2);
				{
				setState(1034);
				loop_predicate_ring();
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 3);
				{
				setState(1035);
				variable_name();
				}
				break;
			case CARBOCYCLIC:
			case COMMON:
			case HETEROCYCLIC:
			case THE:
				enterOuterAlt(_localctx, 4);
				{
				setState(1036);
				set_current_ring();
				}
				break;
			case CURRENT:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(1037);
				match(CURRENT);
				setState(1038);
				match(RING);
				}
				}
				break;
			case INTERFERING:
				enterOuterAlt(_localctx, 6);
				{
				{
				setState(1039);
				match(INTERFERING);
				setState(1040);
				match(GROUP);
				}
				}
				break;
			case PARTICIPATING:
				enterOuterAlt(_localctx, 7);
				{
				{
				setState(1041);
				match(PARTICIPATING);
				setState(1042);
				match(GROUP);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Loop_predicate_ringContext extends ParserRuleContext {
		public TerminalNode IN() { return getToken(SliceParser.IN, 0); }
		public TerminalNode RING() { return getToken(SliceParser.RING, 0); }
		public TerminalNode AN() { return getToken(SliceParser.AN, 0); }
		public Ring_prefix_propContext ring_prefix_prop() {
			return getRuleContext(Ring_prefix_propContext.class,0);
		}
		public TerminalNode ALIPHATIC() { return getToken(SliceParser.ALIPHATIC, 0); }
		public TerminalNode AROMATIC() { return getToken(SliceParser.AROMATIC, 0); }
		public TerminalNode OR() { return getToken(SliceParser.OR, 0); }
		public Ring_sizeContext ring_size() {
			return getRuleContext(Ring_sizeContext.class,0);
		}
		public List<TerminalNode> INT() { return getTokens(SliceParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(SliceParser.INT, i);
		}
		public TerminalNode OR_SMALLER() { return getToken(SliceParser.OR_SMALLER, 0); }
		public TerminalNode OR_LARGER() { return getToken(SliceParser.OR_LARGER, 0); }
		public TerminalNode THROUGH() { return getToken(SliceParser.THROUGH, 0); }
		public Loop_predicate_ringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop_predicate_ring; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterLoop_predicate_ring(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitLoop_predicate_ring(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitLoop_predicate_ring(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Loop_predicate_ringContext loop_predicate_ring() throws RecognitionException {
		Loop_predicate_ringContext _localctx = new Loop_predicate_ringContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_loop_predicate_ring);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1045);
			match(IN);
			setState(1048);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AN:
				{
				setState(1046);
				match(AN);
				}
				break;
			case CARBOCYCLIC:
			case COMMON:
			case HETEROCYCLIC:
				{
				setState(1047);
				ring_prefix_prop();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1055);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,114,_ctx) ) {
			case 1:
				{
				setState(1050);
				match(ALIPHATIC);
				}
				break;
			case 2:
				{
				setState(1051);
				match(AROMATIC);
				}
				break;
			case 3:
				{
				{
				setState(1052);
				match(ALIPHATIC);
				setState(1053);
				match(OR);
				setState(1054);
				match(AROMATIC);
				}
				}
				break;
			}
			setState(1057);
			match(RING);
			setState(1067);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OF) {
				{
				{
				setState(1058);
				ring_size();
				setState(1059);
				match(INT);
				}
				setState(1065);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OR_SMALLER:
					{
					setState(1061);
					match(OR_SMALLER);
					}
					break;
				case OR_LARGER:
					{
					setState(1062);
					match(OR_LARGER);
					}
					break;
				case THROUGH:
					{
					{
					setState(1063);
					match(THROUGH);
					setState(1064);
					match(INT);
					}
					}
					break;
				case CLPAR:
					break;
				default:
					break;
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Set_current_ringContext extends ParserRuleContext {
		public TerminalNode RING() { return getToken(SliceParser.RING, 0); }
		public Ring_sizeContext ring_size() {
			return getRuleContext(Ring_sizeContext.class,0);
		}
		public List<TerminalNode> INT() { return getTokens(SliceParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(SliceParser.INT, i);
		}
		public TerminalNode CONTAINED() { return getToken(SliceParser.CONTAINED, 0); }
		public TerminalNode THE() { return getToken(SliceParser.THE, 0); }
		public Ring_prefix_propContext ring_prefix_prop() {
			return getRuleContext(Ring_prefix_propContext.class,0);
		}
		public TerminalNode ALIPHATIC() { return getToken(SliceParser.ALIPHATIC, 0); }
		public TerminalNode AROMATIC() { return getToken(SliceParser.AROMATIC, 0); }
		public TerminalNode ATOM() { return getToken(SliceParser.ATOM, 0); }
		public Variable_nameContext variable_name() {
			return getRuleContext(Variable_nameContext.class,0);
		}
		public TerminalNode OR() { return getToken(SliceParser.OR, 0); }
		public Set_current_ringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set_current_ring; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterSet_current_ring(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitSet_current_ring(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitSet_current_ring(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Set_current_ringContext set_current_ring() throws RecognitionException {
		Set_current_ringContext _localctx = new Set_current_ringContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_set_current_ring);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1071);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case THE:
				{
				setState(1069);
				match(THE);
				}
				break;
			case CARBOCYCLIC:
			case COMMON:
			case HETEROCYCLIC:
				{
				setState(1070);
				ring_prefix_prop();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1078);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,118,_ctx) ) {
			case 1:
				{
				setState(1073);
				match(ALIPHATIC);
				}
				break;
			case 2:
				{
				setState(1074);
				match(AROMATIC);
				}
				break;
			case 3:
				{
				{
				setState(1075);
				match(ALIPHATIC);
				setState(1076);
				match(OR);
				setState(1077);
				match(AROMATIC);
				}
				}
				break;
			}
			setState(1080);
			match(RING);
			setState(1081);
			ring_size();
			setState(1082);
			match(INT);
			setState(1083);
			match(CONTAINED);
			setState(1087);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATOM:
				{
				setState(1084);
				match(ATOM);
				setState(1085);
				match(INT);
				}
				break;
			case NAME:
				{
				setState(1086);
				variable_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Atom_on_bondContext extends ParserRuleContext {
		public TerminalNode ATOM() { return getToken(SliceParser.ATOM, 0); }
		public TerminalNode ON() { return getToken(SliceParser.ON, 0); }
		public Variable_nameContext variable_name() {
			return getRuleContext(Variable_nameContext.class,0);
		}
		public Subject_bondContext subject_bond() {
			return getRuleContext(Subject_bondContext.class,0);
		}
		public Atom_on_bondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom_on_bond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterAtom_on_bond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitAtom_on_bond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitAtom_on_bond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Atom_on_bondContext atom_on_bond() throws RecognitionException {
		Atom_on_bondContext _localctx = new Atom_on_bondContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_atom_on_bond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1089);
			match(ATOM);
			setState(1090);
			match(ON);
			setState(1093);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME:
				{
				setState(1091);
				variable_name();
				}
				break;
			case BOND:
				{
				setState(1092);
				subject_bond();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Bond_on_atomContext extends ParserRuleContext {
		public TerminalNode BOND() { return getToken(SliceParser.BOND, 0); }
		public TerminalNode ON() { return getToken(SliceParser.ON, 0); }
		public Subject_atomContext subject_atom() {
			return getRuleContext(Subject_atomContext.class,0);
		}
		public Subject_atom_locsContext subject_atom_locs() {
			return getRuleContext(Subject_atom_locsContext.class,0);
		}
		public Subject_or_predicate_atom_typeContext subject_or_predicate_atom_type() {
			return getRuleContext(Subject_or_predicate_atom_typeContext.class,0);
		}
		public Variable_nameContext variable_name() {
			return getRuleContext(Variable_nameContext.class,0);
		}
		public Bond_on_atomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bond_on_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterBond_on_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitBond_on_atom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitBond_on_atom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bond_on_atomContext bond_on_atom() throws RecognitionException {
		Bond_on_atomContext _localctx = new Bond_on_atomContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_bond_on_atom);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1095);
			match(BOND);
			setState(1096);
			match(ON);
			setState(1101);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,121,_ctx) ) {
			case 1:
				{
				setState(1097);
				subject_atom();
				}
				break;
			case 2:
				{
				setState(1098);
				subject_atom_locs();
				}
				break;
			case 3:
				{
				setState(1099);
				subject_or_predicate_atom_type();
				}
				break;
			case 4:
				{
				setState(1100);
				variable_name();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarContext extends ParserRuleContext {
		public TerminalNode SET() { return getToken(SliceParser.SET, 0); }
		public Variable_nameContext variable_name() {
			return getRuleContext(Variable_nameContext.class,0);
		}
		public TerminalNode TO() { return getToken(SliceParser.TO, 0); }
		public Defined_asContext defined_as() {
			return getRuleContext(Defined_asContext.class,0);
		}
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_var);
		try {
			setState(1110);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SET:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(1103);
				match(SET);
				setState(1104);
				variable_name();
				setState(1105);
				match(TO);
				}
				}
				break;
			case DEFINED:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(1107);
				defined_as();
				setState(1108);
				variable_name();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Variable_nameContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(SliceParser.NAME, 0); }
		public Alpha_numContext alpha_num() {
			return getRuleContext(Alpha_numContext.class,0);
		}
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public Variable_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterVariable_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitVariable_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitVariable_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_nameContext variable_name() throws RecognitionException {
		Variable_nameContext _localctx = new Variable_nameContext(_ctx, getState());
		enterRule(_localctx, 212, RULE_variable_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1112);
			match(NAME);
			setState(1115);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,123,_ctx) ) {
			case 1:
				{
				setState(1113);
				alpha_num();
				}
				break;
			case 2:
				{
				setState(1114);
				match(INT);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Defined_asContext extends ParserRuleContext {
		public TerminalNode DEFINED() { return getToken(SliceParser.DEFINED, 0); }
		public TerminalNode AS() { return getToken(SliceParser.AS, 0); }
		public TerminalNode IT() { return getToken(SliceParser.IT, 0); }
		public Defined_asContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defined_as; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterDefined_as(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitDefined_as(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitDefined_as(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Defined_asContext defined_as() throws RecognitionException {
		Defined_asContext _localctx = new Defined_asContext(_ctx, getState());
		enterRule(_localctx, 214, RULE_defined_as);
		int _la;
		try {
			setState(1124);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,125,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(1117);
				match(DEFINED);
				setState(1118);
				match(AS);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(1119);
				match(DEFINED);
				setState(1120);
				match(IT);
				setState(1122);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(1121);
					match(AS);
					}
				}

				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Where_stmtContext extends ParserRuleContext {
		public TerminalNode ANYWHERE() { return getToken(SliceParser.ANYWHERE, 0); }
		public TerminalNode OFF_CURRENT_RING() { return getToken(SliceParser.OFF_CURRENT_RING, 0); }
		public TerminalNode OFFPATH() { return getToken(SliceParser.OFFPATH, 0); }
		public TerminalNode OFFRING() { return getToken(SliceParser.OFFRING, 0); }
		public TerminalNode ONRING() { return getToken(SliceParser.ONRING, 0); }
		public TerminalNode OFF_THE_BRIDGE() { return getToken(SliceParser.OFF_THE_BRIDGE, 0); }
		public TerminalNode ON_THE_BRIDGE() { return getToken(SliceParser.ON_THE_BRIDGE, 0); }
		public TerminalNode ONPATH() { return getToken(SliceParser.ONPATH, 0); }
		public TerminalNode ON_CURRENT_RING() { return getToken(SliceParser.ON_CURRENT_RING, 0); }
		public Where_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterWhere_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitWhere_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitWhere_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Where_stmtContext where_stmt() throws RecognitionException {
		Where_stmtContext _localctx = new Where_stmtContext(_ctx, getState());
		enterRule(_localctx, 216, RULE_where_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1126);
			_la = _input.LA(1);
			if ( !(((((_la - 297)) & ~0x3f) == 0 && ((1L << (_la - 297)) & 511L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Ring_prefix_propContext extends ParserRuleContext {
		public TerminalNode CARBOCYCLIC() { return getToken(SliceParser.CARBOCYCLIC, 0); }
		public TerminalNode COMMON() { return getToken(SliceParser.COMMON, 0); }
		public TerminalNode HETEROCYCLIC() { return getToken(SliceParser.HETEROCYCLIC, 0); }
		public Ring_prefix_propContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ring_prefix_prop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterRing_prefix_prop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitRing_prefix_prop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitRing_prefix_prop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ring_prefix_propContext ring_prefix_prop() throws RecognitionException {
		Ring_prefix_propContext _localctx = new Ring_prefix_propContext(_ctx, getState());
		enterRule(_localctx, 218, RULE_ring_prefix_prop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1128);
			_la = _input.LA(1);
			if ( !(((((_la - 293)) & ~0x3f) == 0 && ((1L << (_la - 293)) & 7L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Ring_suffix_propContext extends ParserRuleContext {
		public TerminalNode BRIDGE() { return getToken(SliceParser.BRIDGE, 0); }
		public Ring_suffix_propContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ring_suffix_prop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterRing_suffix_prop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitRing_suffix_prop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitRing_suffix_prop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ring_suffix_propContext ring_suffix_prop() throws RecognitionException {
		Ring_suffix_propContext _localctx = new Ring_suffix_propContext(_ctx, getState());
		enterRule(_localctx, 220, RULE_ring_suffix_prop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1130);
			match(BRIDGE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Ring_sizeContext extends ParserRuleContext {
		public TerminalNode OF() { return getToken(SliceParser.OF, 0); }
		public TerminalNode SIZE() { return getToken(SliceParser.SIZE, 0); }
		public Ring_sizeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ring_size; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterRing_size(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitRing_size(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitRing_size(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ring_sizeContext ring_size() throws RecognitionException {
		Ring_sizeContext _localctx = new Ring_sizeContext(_ctx, getState());
		enterRule(_localctx, 222, RULE_ring_size);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1132);
			match(OF);
			setState(1133);
			match(SIZE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Alpha_numContext extends ParserRuleContext {
		public TerminalNode ZERO() { return getToken(SliceParser.ZERO, 0); }
		public TerminalNode ONE() { return getToken(SliceParser.ONE, 0); }
		public TerminalNode TWO() { return getToken(SliceParser.TWO, 0); }
		public TerminalNode THREE() { return getToken(SliceParser.THREE, 0); }
		public TerminalNode FOUR() { return getToken(SliceParser.FOUR, 0); }
		public TerminalNode FIVE() { return getToken(SliceParser.FIVE, 0); }
		public TerminalNode SIX() { return getToken(SliceParser.SIX, 0); }
		public TerminalNode SEVEN() { return getToken(SliceParser.SEVEN, 0); }
		public TerminalNode EIGHT() { return getToken(SliceParser.EIGHT, 0); }
		public TerminalNode NINE() { return getToken(SliceParser.NINE, 0); }
		public TerminalNode TEN() { return getToken(SliceParser.TEN, 0); }
		public TerminalNode ELEVEN() { return getToken(SliceParser.ELEVEN, 0); }
		public TerminalNode TWELVE() { return getToken(SliceParser.TWELVE, 0); }
		public Alpha_numContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alpha_num; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterAlpha_num(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitAlpha_num(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitAlpha_num(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Alpha_numContext alpha_num() throws RecognitionException {
		Alpha_numContext _localctx = new Alpha_numContext(_ctx, getState());
		enterRule(_localctx, 224, RULE_alpha_num);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1135);
			_la = _input.LA(1);
			if ( !(((((_la - 339)) & ~0x3f) == 0 && ((1L << (_la - 339)) & 8191L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Numeric_numContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public TerminalNode DECIMAL() { return getToken(SliceParser.DECIMAL, 0); }
		public Numeric_numContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric_num; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterNumeric_num(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitNumeric_num(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitNumeric_num(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numeric_numContext numeric_num() throws RecognitionException {
		Numeric_numContext _localctx = new Numeric_numContext(_ctx, getState());
		enterRule(_localctx, 226, RULE_numeric_num);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1137);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==DECIMAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomtypeContext extends ParserRuleContext {
		public TerminalNode HYDROGEN() { return getToken(SliceParser.HYDROGEN, 0); }
		public TerminalNode HELIUM() { return getToken(SliceParser.HELIUM, 0); }
		public TerminalNode LITHIUM() { return getToken(SliceParser.LITHIUM, 0); }
		public TerminalNode BERYLLIUM() { return getToken(SliceParser.BERYLLIUM, 0); }
		public TerminalNode BORON() { return getToken(SliceParser.BORON, 0); }
		public TerminalNode CARBON() { return getToken(SliceParser.CARBON, 0); }
		public TerminalNode NITROGEN() { return getToken(SliceParser.NITROGEN, 0); }
		public TerminalNode OXYGEN() { return getToken(SliceParser.OXYGEN, 0); }
		public TerminalNode FLUORINE() { return getToken(SliceParser.FLUORINE, 0); }
		public TerminalNode NEON() { return getToken(SliceParser.NEON, 0); }
		public TerminalNode SODIUM() { return getToken(SliceParser.SODIUM, 0); }
		public TerminalNode MAGNESIUM() { return getToken(SliceParser.MAGNESIUM, 0); }
		public TerminalNode ALUMINIUM() { return getToken(SliceParser.ALUMINIUM, 0); }
		public TerminalNode SILICON() { return getToken(SliceParser.SILICON, 0); }
		public TerminalNode PHOSPHORUS() { return getToken(SliceParser.PHOSPHORUS, 0); }
		public TerminalNode SULFUR() { return getToken(SliceParser.SULFUR, 0); }
		public TerminalNode CHLORINE() { return getToken(SliceParser.CHLORINE, 0); }
		public TerminalNode ARGON() { return getToken(SliceParser.ARGON, 0); }
		public TerminalNode POTASSIUM() { return getToken(SliceParser.POTASSIUM, 0); }
		public TerminalNode CALCIUM() { return getToken(SliceParser.CALCIUM, 0); }
		public TerminalNode SCANDIUM() { return getToken(SliceParser.SCANDIUM, 0); }
		public TerminalNode TITANIUM() { return getToken(SliceParser.TITANIUM, 0); }
		public TerminalNode VANADIUM() { return getToken(SliceParser.VANADIUM, 0); }
		public TerminalNode CHROMIUM() { return getToken(SliceParser.CHROMIUM, 0); }
		public TerminalNode IRON() { return getToken(SliceParser.IRON, 0); }
		public TerminalNode COBALT() { return getToken(SliceParser.COBALT, 0); }
		public TerminalNode NICKEL() { return getToken(SliceParser.NICKEL, 0); }
		public TerminalNode COPPER() { return getToken(SliceParser.COPPER, 0); }
		public TerminalNode ZINC() { return getToken(SliceParser.ZINC, 0); }
		public TerminalNode GALLIUM() { return getToken(SliceParser.GALLIUM, 0); }
		public TerminalNode GERMANIUM() { return getToken(SliceParser.GERMANIUM, 0); }
		public TerminalNode ARSENIC() { return getToken(SliceParser.ARSENIC, 0); }
		public TerminalNode SELENIUM() { return getToken(SliceParser.SELENIUM, 0); }
		public TerminalNode BROMINE() { return getToken(SliceParser.BROMINE, 0); }
		public TerminalNode KRYPTON() { return getToken(SliceParser.KRYPTON, 0); }
		public TerminalNode RUBIDIUM() { return getToken(SliceParser.RUBIDIUM, 0); }
		public TerminalNode STRONTIUM() { return getToken(SliceParser.STRONTIUM, 0); }
		public TerminalNode YTTRBIUM() { return getToken(SliceParser.YTTRBIUM, 0); }
		public TerminalNode ZIRCONIUM() { return getToken(SliceParser.ZIRCONIUM, 0); }
		public TerminalNode NIOBIUM() { return getToken(SliceParser.NIOBIUM, 0); }
		public TerminalNode MOLYBDENUM() { return getToken(SliceParser.MOLYBDENUM, 0); }
		public TerminalNode TECHNETIUM() { return getToken(SliceParser.TECHNETIUM, 0); }
		public TerminalNode RUTHENIUM() { return getToken(SliceParser.RUTHENIUM, 0); }
		public TerminalNode RHODIUM() { return getToken(SliceParser.RHODIUM, 0); }
		public TerminalNode PALLADIUM() { return getToken(SliceParser.PALLADIUM, 0); }
		public TerminalNode SILVER() { return getToken(SliceParser.SILVER, 0); }
		public TerminalNode CADMIUM() { return getToken(SliceParser.CADMIUM, 0); }
		public TerminalNode INDIUM() { return getToken(SliceParser.INDIUM, 0); }
		public TerminalNode TIN() { return getToken(SliceParser.TIN, 0); }
		public TerminalNode ANTIMONY() { return getToken(SliceParser.ANTIMONY, 0); }
		public TerminalNode TELLURIUM() { return getToken(SliceParser.TELLURIUM, 0); }
		public TerminalNode IODINE() { return getToken(SliceParser.IODINE, 0); }
		public TerminalNode XENON() { return getToken(SliceParser.XENON, 0); }
		public TerminalNode CESIUM() { return getToken(SliceParser.CESIUM, 0); }
		public TerminalNode BARIUM() { return getToken(SliceParser.BARIUM, 0); }
		public TerminalNode LANTHANUM() { return getToken(SliceParser.LANTHANUM, 0); }
		public TerminalNode HAFNIUM() { return getToken(SliceParser.HAFNIUM, 0); }
		public TerminalNode TANTALUM() { return getToken(SliceParser.TANTALUM, 0); }
		public TerminalNode TUNGSTEN() { return getToken(SliceParser.TUNGSTEN, 0); }
		public TerminalNode RHENIUM() { return getToken(SliceParser.RHENIUM, 0); }
		public TerminalNode OSMIUM() { return getToken(SliceParser.OSMIUM, 0); }
		public TerminalNode IRIDIUM() { return getToken(SliceParser.IRIDIUM, 0); }
		public TerminalNode PLATINUM() { return getToken(SliceParser.PLATINUM, 0); }
		public TerminalNode GOLD() { return getToken(SliceParser.GOLD, 0); }
		public TerminalNode MERCURY() { return getToken(SliceParser.MERCURY, 0); }
		public TerminalNode THALLIUM() { return getToken(SliceParser.THALLIUM, 0); }
		public TerminalNode LEAD() { return getToken(SliceParser.LEAD, 0); }
		public TerminalNode BISMUTH() { return getToken(SliceParser.BISMUTH, 0); }
		public TerminalNode POLONIUM() { return getToken(SliceParser.POLONIUM, 0); }
		public TerminalNode ASTATINE() { return getToken(SliceParser.ASTATINE, 0); }
		public TerminalNode RADON() { return getToken(SliceParser.RADON, 0); }
		public TerminalNode FRANCIUM() { return getToken(SliceParser.FRANCIUM, 0); }
		public TerminalNode RADIUM() { return getToken(SliceParser.RADIUM, 0); }
		public TerminalNode ACTINOIDS() { return getToken(SliceParser.ACTINOIDS, 0); }
		public TerminalNode RUTHERFORDIUM() { return getToken(SliceParser.RUTHERFORDIUM, 0); }
		public TerminalNode DUBNIUM() { return getToken(SliceParser.DUBNIUM, 0); }
		public TerminalNode SEABORGIUM() { return getToken(SliceParser.SEABORGIUM, 0); }
		public TerminalNode BOHRIUM() { return getToken(SliceParser.BOHRIUM, 0); }
		public TerminalNode HASSIUM() { return getToken(SliceParser.HASSIUM, 0); }
		public TerminalNode MEITNERIUM() { return getToken(SliceParser.MEITNERIUM, 0); }
		public TerminalNode DARMSTADIUM() { return getToken(SliceParser.DARMSTADIUM, 0); }
		public TerminalNode ROENTGENIUM() { return getToken(SliceParser.ROENTGENIUM, 0); }
		public TerminalNode COPERNICIUM() { return getToken(SliceParser.COPERNICIUM, 0); }
		public TerminalNode NIHONIUM() { return getToken(SliceParser.NIHONIUM, 0); }
		public TerminalNode FLEROVIUM() { return getToken(SliceParser.FLEROVIUM, 0); }
		public TerminalNode MOSCOVIUM() { return getToken(SliceParser.MOSCOVIUM, 0); }
		public TerminalNode LIVERMORIUM() { return getToken(SliceParser.LIVERMORIUM, 0); }
		public TerminalNode TENNESSINE() { return getToken(SliceParser.TENNESSINE, 0); }
		public TerminalNode OGANESSON() { return getToken(SliceParser.OGANESSON, 0); }
		public TerminalNode CERIUM() { return getToken(SliceParser.CERIUM, 0); }
		public TerminalNode PRASEODYMIUM() { return getToken(SliceParser.PRASEODYMIUM, 0); }
		public TerminalNode NEODYMIUM() { return getToken(SliceParser.NEODYMIUM, 0); }
		public TerminalNode PROMETHIUM() { return getToken(SliceParser.PROMETHIUM, 0); }
		public TerminalNode SAMARIUM() { return getToken(SliceParser.SAMARIUM, 0); }
		public TerminalNode EUROPIUM() { return getToken(SliceParser.EUROPIUM, 0); }
		public TerminalNode GADOLINIUM() { return getToken(SliceParser.GADOLINIUM, 0); }
		public TerminalNode TERBIUM() { return getToken(SliceParser.TERBIUM, 0); }
		public TerminalNode DYSPROSIUM() { return getToken(SliceParser.DYSPROSIUM, 0); }
		public TerminalNode HOLMIUM() { return getToken(SliceParser.HOLMIUM, 0); }
		public TerminalNode ERBIUM() { return getToken(SliceParser.ERBIUM, 0); }
		public TerminalNode THULIUM() { return getToken(SliceParser.THULIUM, 0); }
		public TerminalNode LUTETIUM() { return getToken(SliceParser.LUTETIUM, 0); }
		public TerminalNode THORIUM() { return getToken(SliceParser.THORIUM, 0); }
		public TerminalNode PROTACTINIUM() { return getToken(SliceParser.PROTACTINIUM, 0); }
		public TerminalNode URANIUM() { return getToken(SliceParser.URANIUM, 0); }
		public TerminalNode NEPTUNIUM() { return getToken(SliceParser.NEPTUNIUM, 0); }
		public TerminalNode PLUTONIUM() { return getToken(SliceParser.PLUTONIUM, 0); }
		public TerminalNode AMERICIUM() { return getToken(SliceParser.AMERICIUM, 0); }
		public TerminalNode CURIUM() { return getToken(SliceParser.CURIUM, 0); }
		public TerminalNode BERKELIUM() { return getToken(SliceParser.BERKELIUM, 0); }
		public TerminalNode CALIFORNIUM() { return getToken(SliceParser.CALIFORNIUM, 0); }
		public TerminalNode EINSTEINIUM() { return getToken(SliceParser.EINSTEINIUM, 0); }
		public TerminalNode FERMIUM() { return getToken(SliceParser.FERMIUM, 0); }
		public TerminalNode MENDELEVIUM() { return getToken(SliceParser.MENDELEVIUM, 0); }
		public TerminalNode NOBELIUM() { return getToken(SliceParser.NOBELIUM, 0); }
		public TerminalNode LAWRENCIUM() { return getToken(SliceParser.LAWRENCIUM, 0); }
		public AtomtypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomtype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterAtomtype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitAtomtype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitAtomtype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomtypeContext atomtype() throws RecognitionException {
		AtomtypeContext _localctx = new AtomtypeContext(_ctx, getState());
		enterRule(_localctx, 228, RULE_atomtype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1139);
			_la = _input.LA(1);
			if ( !(((((_la - 23)) & ~0x3f) == 0 && ((1L << (_la - 23)) & -1L) != 0) || ((((_la - 87)) & ~0x3f) == 0 && ((1L << (_la - 87)) & 4503599627370495L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Functional_groupContext extends ParserRuleContext {
		public TerminalNode ACETAL() { return getToken(SliceParser.ACETAL, 0); }
		public TerminalNode ACETYLENE() { return getToken(SliceParser.ACETYLENE, 0); }
		public TerminalNode ACID() { return getToken(SliceParser.ACID, 0); }
		public TerminalNode ACID_HALIDE() { return getToken(SliceParser.ACID_HALIDE, 0); }
		public TerminalNode ALCOHOL() { return getToken(SliceParser.ALCOHOL, 0); }
		public TerminalNode ALDEHYDE() { return getToken(SliceParser.ALDEHYDE, 0); }
		public TerminalNode ALLENE() { return getToken(SliceParser.ALLENE, 0); }
		public TerminalNode AMIDE1() { return getToken(SliceParser.AMIDE1, 0); }
		public TerminalNode AMIDE2() { return getToken(SliceParser.AMIDE2, 0); }
		public TerminalNode AMIDE3() { return getToken(SliceParser.AMIDE3, 0); }
		public TerminalNode AMIDE() { return getToken(SliceParser.AMIDE, 0); }
		public TerminalNode AMIDZ() { return getToken(SliceParser.AMIDZ, 0); }
		public TerminalNode AMINE1() { return getToken(SliceParser.AMINE1, 0); }
		public TerminalNode AMINE2() { return getToken(SliceParser.AMINE2, 0); }
		public TerminalNode AMINE3() { return getToken(SliceParser.AMINE3, 0); }
		public TerminalNode AMINE() { return getToken(SliceParser.AMINE, 0); }
		public TerminalNode AMINE_OXIDE() { return getToken(SliceParser.AMINE_OXIDE, 0); }
		public TerminalNode ANHYDRIDE() { return getToken(SliceParser.ANHYDRIDE, 0); }
		public TerminalNode AZIDE() { return getToken(SliceParser.AZIDE, 0); }
		public TerminalNode AZIRIDINE() { return getToken(SliceParser.AZIRIDINE, 0); }
		public TerminalNode AZO() { return getToken(SliceParser.AZO, 0); }
		public TerminalNode BROMIDE() { return getToken(SliceParser.BROMIDE, 0); }
		public TerminalNode C_SULFONATE() { return getToken(SliceParser.C_SULFONATE, 0); }
		public TerminalNode CARBAMATE_C() { return getToken(SliceParser.CARBAMATE_C, 0); }
		public TerminalNode CARBAMATE_H() { return getToken(SliceParser.CARBAMATE_H, 0); }
		public TerminalNode CARBONIUM() { return getToken(SliceParser.CARBONIUM, 0); }
		public TerminalNode CARBONYL() { return getToken(SliceParser.CARBONYL, 0); }
		public TerminalNode CARBOXYL() { return getToken(SliceParser.CARBOXYL, 0); }
		public TerminalNode CHLORIDE() { return getToken(SliceParser.CHLORIDE, 0); }
		public TerminalNode CYANO() { return getToken(SliceParser.CYANO, 0); }
		public TerminalNode DIAZO() { return getToken(SliceParser.DIAZO, 0); }
		public TerminalNode DISULFIDE() { return getToken(SliceParser.DISULFIDE, 0); }
		public TerminalNode DITHIOACETAL() { return getToken(SliceParser.DITHIOACETAL, 0); }
		public TerminalNode DITHIOKETAL() { return getToken(SliceParser.DITHIOKETAL, 0); }
		public TerminalNode ENAMINE() { return getToken(SliceParser.ENAMINE, 0); }
		public TerminalNode ENOL_ETHER() { return getToken(SliceParser.ENOL_ETHER, 0); }
		public TerminalNode EPISULFIDE() { return getToken(SliceParser.EPISULFIDE, 0); }
		public TerminalNode EPOXIDE() { return getToken(SliceParser.EPOXIDE, 0); }
		public TerminalNode ESTER() { return getToken(SliceParser.ESTER, 0); }
		public TerminalNode ESTERX() { return getToken(SliceParser.ESTERX, 0); }
		public TerminalNode ETHER() { return getToken(SliceParser.ETHER, 0); }
		public TerminalNode FLUORIDE() { return getToken(SliceParser.FLUORIDE, 0); }
		public TerminalNode FUNCTIONAL() { return getToken(SliceParser.FUNCTIONAL, 0); }
		public TerminalNode GEM_DIHALIDE() { return getToken(SliceParser.GEM_DIHALIDE, 0); }
		public TerminalNode GLYCOL() { return getToken(SliceParser.GLYCOL, 0); }
		public TerminalNode HALIDE() { return getToken(SliceParser.HALIDE, 0); }
		public TerminalNode HALOAMINE() { return getToken(SliceParser.HALOAMINE, 0); }
		public TerminalNode HALOHYDRIN() { return getToken(SliceParser.HALOHYDRIN, 0); }
		public TerminalNode HEMIACETAL() { return getToken(SliceParser.HEMIACETAL, 0); }
		public TerminalNode HYDRATE() { return getToken(SliceParser.HYDRATE, 0); }
		public TerminalNode HYDRAZONE() { return getToken(SliceParser.HYDRAZONE, 0); }
		public TerminalNode HYDROXYLAMINE() { return getToken(SliceParser.HYDROXYLAMINE, 0); }
		public TerminalNode IMINE() { return getToken(SliceParser.IMINE, 0); }
		public TerminalNode IODIDE() { return getToken(SliceParser.IODIDE, 0); }
		public TerminalNode ISOCYANATE() { return getToken(SliceParser.ISOCYANATE, 0); }
		public TerminalNode ISOCYANIDE() { return getToken(SliceParser.ISOCYANIDE, 0); }
		public TerminalNode KETONE() { return getToken(SliceParser.KETONE, 0); }
		public TerminalNode LACTAM() { return getToken(SliceParser.LACTAM, 0); }
		public TerminalNode LACTONE() { return getToken(SliceParser.LACTONE, 0); }
		public TerminalNode METHYLENE() { return getToken(SliceParser.METHYLENE, 0); }
		public TerminalNode N_CARBAMATE() { return getToken(SliceParser.N_CARBAMATE, 0); }
		public TerminalNode N_UREA_C() { return getToken(SliceParser.N_UREA_C, 0); }
		public TerminalNode N_UREA_H() { return getToken(SliceParser.N_UREA_H, 0); }
		public TerminalNode NITRITE() { return getToken(SliceParser.NITRITE, 0); }
		public TerminalNode NITRO() { return getToken(SliceParser.NITRO, 0); }
		public TerminalNode NITROSO() { return getToken(SliceParser.NITROSO, 0); }
		public TerminalNode O_CARBAMATE() { return getToken(SliceParser.O_CARBAMATE, 0); }
		public TerminalNode O_CARBONATE() { return getToken(SliceParser.O_CARBONATE, 0); }
		public TerminalNode O_SULFONATE() { return getToken(SliceParser.O_SULFONATE, 0); }
		public TerminalNode OLEFIN() { return getToken(SliceParser.OLEFIN, 0); }
		public TerminalNode OXIME() { return getToken(SliceParser.OXIME, 0); }
		public TerminalNode PEROXIDE() { return getToken(SliceParser.PEROXIDE, 0); }
		public TerminalNode PHOSPHINE() { return getToken(SliceParser.PHOSPHINE, 0); }
		public TerminalNode PHOSPHONATE() { return getToken(SliceParser.PHOSPHONATE, 0); }
		public TerminalNode SELENIDE() { return getToken(SliceParser.SELENIDE, 0); }
		public TerminalNode SILYLENOLETHER() { return getToken(SliceParser.SILYLENOLETHER, 0); }
		public TerminalNode SULFIDE() { return getToken(SliceParser.SULFIDE, 0); }
		public TerminalNode SULFONE() { return getToken(SliceParser.SULFONE, 0); }
		public TerminalNode SULFOXIDE() { return getToken(SliceParser.SULFOXIDE, 0); }
		public TerminalNode THIOCYANATE() { return getToken(SliceParser.THIOCYANATE, 0); }
		public TerminalNode THIOESTER() { return getToken(SliceParser.THIOESTER, 0); }
		public TerminalNode THIOL() { return getToken(SliceParser.THIOL, 0); }
		public TerminalNode TRIALKYLSILOXY() { return getToken(SliceParser.TRIALKYLSILOXY, 0); }
		public TerminalNode TRIALKYLSILYL() { return getToken(SliceParser.TRIALKYLSILYL, 0); }
		public TerminalNode TRIHALIDE() { return getToken(SliceParser.TRIHALIDE, 0); }
		public TerminalNode VIC_DIHALIDE() { return getToken(SliceParser.VIC_DIHALIDE, 0); }
		public TerminalNode VINYLSILANE() { return getToken(SliceParser.VINYLSILANE, 0); }
		public Functional_groupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functional_group; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterFunctional_group(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitFunctional_group(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitFunctional_group(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Functional_groupContext functional_group() throws RecognitionException {
		Functional_groupContext _localctx = new Functional_groupContext(_ctx, getState());
		enterRule(_localctx, 230, RULE_functional_group);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1141);
			_la = _input.LA(1);
			if ( !(((((_la - 139)) & ~0x3f) == 0 && ((1L << (_la - 139)) & -1L) != 0) || ((((_la - 203)) & ~0x3f) == 0 && ((1L << (_la - 203)) & 8388607L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Non_functional_groupContext extends ParserRuleContext {
		public TerminalNode ISOPROPYL() { return getToken(SliceParser.ISOPROPYL, 0); }
		public TerminalNode METHYL() { return getToken(SliceParser.METHYL, 0); }
		public TerminalNode PHENYL() { return getToken(SliceParser.PHENYL, 0); }
		public TerminalNode T_BUTYL() { return getToken(SliceParser.T_BUTYL, 0); }
		public Non_functional_groupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_non_functional_group; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterNon_functional_group(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitNon_functional_group(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitNon_functional_group(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Non_functional_groupContext non_functional_group() throws RecognitionException {
		Non_functional_groupContext _localctx = new Non_functional_groupContext(_ctx, getState());
		enterRule(_localctx, 232, RULE_non_functional_group);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1143);
			_la = _input.LA(1);
			if ( !(((((_la - 227)) & ~0x3f) == 0 && ((1L << (_la - 227)) & 15L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SmartsContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(SliceParser.STRING, 0); }
		public Smarts_charContext smarts_char() {
			return getRuleContext(Smarts_charContext.class,0);
		}
		public SmartsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_smarts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterSmarts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitSmarts(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitSmarts(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SmartsContext smarts() throws RecognitionException {
		SmartsContext _localctx = new SmartsContext(_ctx, getState());
		enterRule(_localctx, 234, RULE_smarts);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1147);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				{
				setState(1145);
				match(STRING);
				}
				break;
			case T__4:
				{
				setState(1146);
				smarts_char();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Atom_locContext extends ParserRuleContext {
		public TerminalNode ALPHA() { return getToken(SliceParser.ALPHA, 0); }
		public TerminalNode BETA() { return getToken(SliceParser.BETA, 0); }
		public TerminalNode GAMMA() { return getToken(SliceParser.GAMMA, 0); }
		public Atom_locContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom_loc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterAtom_loc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitAtom_loc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitAtom_loc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Atom_locContext atom_loc() throws RecognitionException {
		Atom_locContext _localctx = new Atom_locContext(_ctx, getState());
		enterRule(_localctx, 236, RULE_atom_loc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1149);
			_la = _input.LA(1);
			if ( !(((((_la - 285)) & ~0x3f) == 0 && ((1L << (_la - 285)) & 7L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Atom_propertiesContext extends ParserRuleContext {
		public TerminalNode ALKALI_METAL() { return getToken(SliceParser.ALKALI_METAL, 0); }
		public TerminalNode BENZYLIC() { return getToken(SliceParser.BENZYLIC, 0); }
		public TerminalNode BREDT_STRAINED() { return getToken(SliceParser.BREDT_STRAINED, 0); }
		public TerminalNode BRIDGEHEAD() { return getToken(SliceParser.BRIDGEHEAD, 0); }
		public TerminalNode CIS() { return getToken(SliceParser.CIS, 0); }
		public TerminalNode TO() { return getToken(SliceParser.TO, 0); }
		public TerminalNode CONJUGATED() { return getToken(SliceParser.CONJUGATED, 0); }
		public TerminalNode DOUBLY() { return getToken(SliceParser.DOUBLY, 0); }
		public TerminalNode ELECTRON_DENSITY() { return getToken(SliceParser.ELECTRON_DENSITY, 0); }
		public TerminalNode OF() { return getToken(SliceParser.OF, 0); }
		public TerminalNode ENOLIZABLE() { return getToken(SliceParser.ENOLIZABLE, 0); }
		public TerminalNode EQUIVALENT() { return getToken(SliceParser.EQUIVALENT, 0); }
		public TerminalNode FUSION() { return getToken(SliceParser.FUSION, 0); }
		public TerminalNode HALOGEN() { return getToken(SliceParser.HALOGEN, 0); }
		public TerminalNode HETERO() { return getToken(SliceParser.HETERO, 0); }
		public TerminalNode MULTIPLY() { return getToken(SliceParser.MULTIPLY, 0); }
		public TerminalNode TRANS() { return getToken(SliceParser.TRANS, 0); }
		public TerminalNode TRIPLY() { return getToken(SliceParser.TRIPLY, 0); }
		public Atom_propertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom_properties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterAtom_properties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitAtom_properties(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitAtom_properties(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Atom_propertiesContext atom_properties() throws RecognitionException {
		Atom_propertiesContext _localctx = new Atom_propertiesContext(_ctx, getState());
		enterRule(_localctx, 238, RULE_atom_properties);
		int _la;
		try {
			setState(1179);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ALKALI_METAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(1151);
				match(ALKALI_METAL);
				}
				break;
			case BENZYLIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(1152);
				match(BENZYLIC);
				}
				break;
			case BREDT_STRAINED:
				enterOuterAlt(_localctx, 3);
				{
				setState(1153);
				match(BREDT_STRAINED);
				}
				break;
			case BRIDGEHEAD:
				enterOuterAlt(_localctx, 4);
				{
				setState(1154);
				match(BRIDGEHEAD);
				}
				break;
			case CIS:
				enterOuterAlt(_localctx, 5);
				{
				setState(1155);
				match(CIS);
				setState(1157);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TO) {
					{
					setState(1156);
					match(TO);
					}
				}

				}
				break;
			case CONJUGATED:
				enterOuterAlt(_localctx, 6);
				{
				setState(1159);
				match(CONJUGATED);
				}
				break;
			case DOUBLY:
				enterOuterAlt(_localctx, 7);
				{
				setState(1160);
				match(DOUBLY);
				}
				break;
			case ELECTRON_DENSITY:
				enterOuterAlt(_localctx, 8);
				{
				setState(1161);
				match(ELECTRON_DENSITY);
				setState(1163);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OF) {
					{
					setState(1162);
					match(OF);
					}
				}

				}
				break;
			case ENOLIZABLE:
				enterOuterAlt(_localctx, 9);
				{
				setState(1165);
				match(ENOLIZABLE);
				}
				break;
			case EQUIVALENT:
				enterOuterAlt(_localctx, 10);
				{
				setState(1166);
				match(EQUIVALENT);
				setState(1168);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TO) {
					{
					setState(1167);
					match(TO);
					}
				}

				}
				break;
			case FUSION:
				enterOuterAlt(_localctx, 11);
				{
				setState(1170);
				match(FUSION);
				}
				break;
			case HALOGEN:
				enterOuterAlt(_localctx, 12);
				{
				setState(1171);
				match(HALOGEN);
				}
				break;
			case HETERO:
				enterOuterAlt(_localctx, 13);
				{
				setState(1172);
				match(HETERO);
				}
				break;
			case MULTIPLY:
				enterOuterAlt(_localctx, 14);
				{
				setState(1173);
				match(MULTIPLY);
				}
				break;
			case TRANS:
				enterOuterAlt(_localctx, 15);
				{
				setState(1174);
				match(TRANS);
				setState(1176);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TO) {
					{
					setState(1175);
					match(TO);
					}
				}

				}
				break;
			case TRIPLY:
				enterOuterAlt(_localctx, 16);
				{
				setState(1178);
				match(TRIPLY);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Atom_or_bond_propertiesContext extends ParserRuleContext {
		public TerminalNode ALLYLIC() { return getToken(SliceParser.ALLYLIC, 0); }
		public TerminalNode ANTI() { return getToken(SliceParser.ANTI, 0); }
		public TerminalNode AROMATIC() { return getToken(SliceParser.AROMATIC, 0); }
		public TerminalNode CIS_OLEFIN() { return getToken(SliceParser.CIS_OLEFIN, 0); }
		public TerminalNode STRAINED() { return getToken(SliceParser.STRAINED, 0); }
		public TerminalNode SYN() { return getToken(SliceParser.SYN, 0); }
		public TerminalNode TRANS_OLEFIN() { return getToken(SliceParser.TRANS_OLEFIN, 0); }
		public Atom_or_bond_propertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom_or_bond_properties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterAtom_or_bond_properties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitAtom_or_bond_properties(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitAtom_or_bond_properties(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Atom_or_bond_propertiesContext atom_or_bond_properties() throws RecognitionException {
		Atom_or_bond_propertiesContext _localctx = new Atom_or_bond_propertiesContext(_ctx, getState());
		enterRule(_localctx, 240, RULE_atom_or_bond_properties);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1181);
			_la = _input.LA(1);
			if ( !(((((_la - 254)) & ~0x3f) == 0 && ((1L << (_la - 254)) & 1831L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Bond_propertiesContext extends ParserRuleContext {
		public TerminalNode BREDT_STRAINED() { return getToken(SliceParser.BREDT_STRAINED, 0); }
		public TerminalNode BRIDGEHEAD() { return getToken(SliceParser.BRIDGEHEAD, 0); }
		public TerminalNode CONJUGATED() { return getToken(SliceParser.CONJUGATED, 0); }
		public TerminalNode DOUBLE_BOND() { return getToken(SliceParser.DOUBLE_BOND, 0); }
		public TerminalNode FUSION() { return getToken(SliceParser.FUSION, 0); }
		public TerminalNode MULTIPLE() { return getToken(SliceParser.MULTIPLE, 0); }
		public TerminalNode SINGLE_BOND() { return getToken(SliceParser.SINGLE_BOND, 0); }
		public TerminalNode SMALL_FUSION() { return getToken(SliceParser.SMALL_FUSION, 0); }
		public TerminalNode TRIPLE_BOND() { return getToken(SliceParser.TRIPLE_BOND, 0); }
		public Bond_propertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bond_properties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterBond_properties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitBond_properties(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitBond_properties(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bond_propertiesContext bond_properties() throws RecognitionException {
		Bond_propertiesContext _localctx = new Bond_propertiesContext(_ctx, getState());
		enterRule(_localctx, 242, RULE_bond_properties);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1183);
			_la = _input.LA(1);
			if ( !(((((_la - 248)) & ~0x3f) == 0 && ((1L << (_la - 248)) & 13855L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Atom_or_bond_or_group_propertiesContext extends ParserRuleContext {
		public TerminalNode DONATING() { return getToken(SliceParser.DONATING, 0); }
		public TerminalNode WITHDRAWING() { return getToken(SliceParser.WITHDRAWING, 0); }
		public Atom_or_bond_or_group_propertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom_or_bond_or_group_properties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterAtom_or_bond_or_group_properties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitAtom_or_bond_or_group_properties(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitAtom_or_bond_or_group_properties(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Atom_or_bond_or_group_propertiesContext atom_or_bond_or_group_properties() throws RecognitionException {
		Atom_or_bond_or_group_propertiesContext _localctx = new Atom_or_bond_or_group_propertiesContext(_ctx, getState());
		enterRule(_localctx, 244, RULE_atom_or_bond_or_group_properties);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1185);
			_la = _input.LA(1);
			if ( !(_la==DONATING || _la==WITHDRAWING) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Group_propertiesContext extends ParserRuleContext {
		public TerminalNode NON_EXPANDABLE_WITHDRAWING() { return getToken(SliceParser.NON_EXPANDABLE_WITHDRAWING, 0); }
		public TerminalNode EXPANDABLE_WITHDRAWING() { return getToken(SliceParser.EXPANDABLE_WITHDRAWING, 0); }
		public TerminalNode INTERFERING() { return getToken(SliceParser.INTERFERING, 0); }
		public TerminalNode GOOD_LEAVING() { return getToken(SliceParser.GOOD_LEAVING, 0); }
		public TerminalNode LEAVING() { return getToken(SliceParser.LEAVING, 0); }
		public TerminalNode PARTICIPATING() { return getToken(SliceParser.PARTICIPATING, 0); }
		public TerminalNode PROTECTED() { return getToken(SliceParser.PROTECTED, 0); }
		public TerminalNode VINYL_D() { return getToken(SliceParser.VINYL_D, 0); }
		public TerminalNode VINYL_W() { return getToken(SliceParser.VINYL_W, 0); }
		public Group_propertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_group_properties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterGroup_properties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitGroup_properties(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitGroup_properties(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Group_propertiesContext group_properties() throws RecognitionException {
		Group_propertiesContext _localctx = new Group_propertiesContext(_ctx, getState());
		enterRule(_localctx, 246, RULE_group_properties);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1187);
			_la = _input.LA(1);
			if ( !(((((_la - 266)) & ~0x3f) == 0 && ((1L << (_la - 266)) & 511L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Charge_stmtContext extends ParserRuleContext {
		public TerminalNode MINUS_CHARGE() { return getToken(SliceParser.MINUS_CHARGE, 0); }
		public TerminalNode PLUS_CHARGE() { return getToken(SliceParser.PLUS_CHARGE, 0); }
		public Charge_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charge_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterCharge_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitCharge_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitCharge_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Charge_stmtContext charge_stmt() throws RecognitionException {
		Charge_stmtContext _localctx = new Charge_stmtContext(_ctx, getState());
		enterRule(_localctx, 248, RULE_charge_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1189);
			_la = _input.LA(1);
			if ( !(_la==MINUS_CHARGE || _la==PLUS_CHARGE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Centre_stmtContext extends ParserRuleContext {
		public TerminalNode PRIMARY_CENTER() { return getToken(SliceParser.PRIMARY_CENTER, 0); }
		public TerminalNode SECONDARY_CENTER() { return getToken(SliceParser.SECONDARY_CENTER, 0); }
		public TerminalNode TERTIARY_CENTER() { return getToken(SliceParser.TERTIARY_CENTER, 0); }
		public TerminalNode QUATERNARY_CENTER() { return getToken(SliceParser.QUATERNARY_CENTER, 0); }
		public Centre_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_centre_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterCentre_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitCentre_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitCentre_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Centre_stmtContext centre_stmt() throws RecognitionException {
		Centre_stmtContext _localctx = new Centre_stmtContext(_ctx, getState());
		enterRule(_localctx, 250, RULE_centre_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1191);
			_la = _input.LA(1);
			if ( !(((((_la - 276)) & ~0x3f) == 0 && ((1L << (_la - 276)) & 15L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Ring_propContext extends ParserRuleContext {
		public TerminalNode CARBOCYCLIC() { return getToken(SliceParser.CARBOCYCLIC, 0); }
		public TerminalNode COMMON() { return getToken(SliceParser.COMMON, 0); }
		public TerminalNode HETEROCYCLIC() { return getToken(SliceParser.HETEROCYCLIC, 0); }
		public Ring_propContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ring_prop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterRing_prop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitRing_prop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitRing_prop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ring_propContext ring_prop() throws RecognitionException {
		Ring_propContext _localctx = new Ring_propContext(_ctx, getState());
		enterRule(_localctx, 252, RULE_ring_prop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1193);
			_la = _input.LA(1);
			if ( !(((((_la - 293)) & ~0x3f) == 0 && ((1L << (_la - 293)) & 7L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Alkyl_numberContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(SliceParser.INT, 0); }
		public TerminalNode ALKYL() { return getToken(SliceParser.ALKYL, 0); }
		public Alkyl_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alkyl_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterAlkyl_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitAlkyl_number(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitAlkyl_number(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Alkyl_numberContext alkyl_number() throws RecognitionException {
		Alkyl_numberContext _localctx = new Alkyl_numberContext(_ctx, getState());
		enterRule(_localctx, 254, RULE_alkyl_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1195);
			match(INT);
			setState(1196);
			match(ALKYL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BoolContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(SliceParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(SliceParser.FALSE, 0); }
		public BoolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitBool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitBool(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolContext bool() throws RecognitionException {
		BoolContext _localctx = new BoolContext(_ctx, getState());
		enterRule(_localctx, 256, RULE_bool);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1198);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NotcontainContext extends ParserRuleContext {
		public ContainContext contain() {
			return getRuleContext(ContainContext.class,0);
		}
		public TerminalNode NOT() { return getToken(SliceParser.NOT, 0); }
		public TerminalNode ONLY() { return getToken(SliceParser.ONLY, 0); }
		public NotcontainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notcontain; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterNotcontain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitNotcontain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitNotcontain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotcontainContext notcontain() throws RecognitionException {
		NotcontainContext _localctx = new NotcontainContext(_ctx, getState());
		enterRule(_localctx, 258, RULE_notcontain);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1200);
			contain();
			setState(1201);
			match(NOT);
			setState(1203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ONLY) {
				{
				setState(1202);
				match(ONLY);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContainContext extends ParserRuleContext {
		public TerminalNode HAS() { return getToken(SliceParser.HAS, 0); }
		public TerminalNode ONLY() { return getToken(SliceParser.ONLY, 0); }
		public TerminalNode CONTAINED() { return getToken(SliceParser.CONTAINED, 0); }
		public ContainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contain; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterContain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitContain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitContain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContainContext contain() throws RecognitionException {
		ContainContext _localctx = new ContainContext(_ctx, getState());
		enterRule(_localctx, 260, RULE_contain);
		int _la;
		try {
			setState(1213);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case HAS:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(1205);
				match(HAS);
				setState(1207);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ONLY) {
					{
					setState(1206);
					match(ONLY);
					}
				}

				}
				}
				break;
			case CONTAINED:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(1209);
				match(CONTAINED);
				setState(1211);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ONLY) {
					{
					setState(1210);
					match(ONLY);
					}
				}

				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NotequalContext extends ParserRuleContext {
		public TerminalNode IS() { return getToken(SliceParser.IS, 0); }
		public TerminalNode NOT() { return getToken(SliceParser.NOT, 0); }
		public TerminalNode ONLY() { return getToken(SliceParser.ONLY, 0); }
		public SameContext same() {
			return getRuleContext(SameContext.class,0);
		}
		public NotequalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notequal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterNotequal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitNotequal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitNotequal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotequalContext notequal() throws RecognitionException {
		NotequalContext _localctx = new NotequalContext(_ctx, getState());
		enterRule(_localctx, 262, RULE_notequal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(1215);
			match(IS);
			setState(1216);
			match(NOT);
			setState(1218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ONLY) {
				{
				setState(1217);
				match(ONLY);
				}
			}

			setState(1221);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,137,_ctx) ) {
			case 1:
				{
				setState(1220);
				same();
				}
				break;
			}
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EqualContext extends ParserRuleContext {
		public TerminalNode IS() { return getToken(SliceParser.IS, 0); }
		public TerminalNode ONLY() { return getToken(SliceParser.ONLY, 0); }
		public SameContext same() {
			return getRuleContext(SameContext.class,0);
		}
		public EqualContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterEqual(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitEqual(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitEqual(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualContext equal() throws RecognitionException {
		EqualContext _localctx = new EqualContext(_ctx, getState());
		enterRule(_localctx, 264, RULE_equal);
		int _la;
		try {
			setState(1235);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,141,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(1223);
				match(IS);
				setState(1225);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ONLY) {
					{
					setState(1224);
					match(ONLY);
					}
				}

				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1228);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ONLY) {
					{
					setState(1227);
					match(ONLY);
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(1230);
				match(IS);
				setState(1232);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ONLY) {
					{
					setState(1231);
					match(ONLY);
					}
				}

				setState(1234);
				same();
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SameContext extends ParserRuleContext {
		public TerminalNode THE() { return getToken(SliceParser.THE, 0); }
		public TerminalNode AS() { return getToken(SliceParser.AS, 0); }
		public SameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_same; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterSame(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitSame(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitSame(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SameContext same() throws RecognitionException {
		SameContext _localctx = new SameContext(_ctx, getState());
		enterRule(_localctx, 266, RULE_same);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==THE) {
				{
				setState(1237);
				match(THE);
				}
			}

			setState(1240);
			_la = _input.LA(1);
			if ( !(_la==T__2 || _la==T__3) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(1241);
				match(AS);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Function_nameContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(SliceParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(SliceParser.NAME, i);
		}
		public List<TerminalNode> DOT() { return getTokens(SliceParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(SliceParser.DOT, i);
		}
		public List<TerminalNode> TIMES() { return getTokens(SliceParser.TIMES); }
		public TerminalNode TIMES(int i) {
			return getToken(SliceParser.TIMES, i);
		}
		public Function_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterFunction_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitFunction_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitFunction_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_nameContext function_name() throws RecognitionException {
		Function_nameContext _localctx = new Function_nameContext(_ctx, getState());
		enterRule(_localctx, 268, RULE_function_name);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1245); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1244);
					_la = _input.LA(1);
					if ( !(((((_la - 386)) & ~0x3f) == 0 && ((1L << (_la - 386)) & 562949953421441L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1247); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,144,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PathContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(SliceParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(SliceParser.NAME, i);
		}
		public List<TerminalNode> DIV() { return getTokens(SliceParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(SliceParser.DIV, i);
		}
		public PathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_path; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitPath(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitPath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PathContext path() throws RecognitionException {
		PathContext _localctx = new PathContext(_ctx, getState());
		enterRule(_localctx, 270, RULE_path);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1250); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1249);
					_la = _input.LA(1);
					if ( !(_la==DIV || _la==NAME) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1252); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,145,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Smarts_charContext extends ParserRuleContext {
		public List<Function_nameContext> function_name() {
			return getRuleContexts(Function_nameContext.class);
		}
		public Function_nameContext function_name(int i) {
			return getRuleContext(Function_nameContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(SliceParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(SliceParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(SliceParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(SliceParser.MINUS, i);
		}
		public List<TerminalNode> EQ() { return getTokens(SliceParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(SliceParser.EQ, i);
		}
		public List<TerminalNode> DIV() { return getTokens(SliceParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(SliceParser.DIV, i);
		}
		public List<TerminalNode> COLON() { return getTokens(SliceParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(SliceParser.COLON, i);
		}
		public List<TerminalNode> SEMIC() { return getTokens(SliceParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(SliceParser.SEMIC, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SliceParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SliceParser.COMMA, i);
		}
		public List<TerminalNode> NOT_SIGN() { return getTokens(SliceParser.NOT_SIGN); }
		public TerminalNode NOT_SIGN(int i) {
			return getToken(SliceParser.NOT_SIGN, i);
		}
		public List<TerminalNode> INT() { return getTokens(SliceParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(SliceParser.INT, i);
		}
		public List<TerminalNode> LPAR() { return getTokens(SliceParser.LPAR); }
		public TerminalNode LPAR(int i) {
			return getToken(SliceParser.LPAR, i);
		}
		public List<TerminalNode> RPAR() { return getTokens(SliceParser.RPAR); }
		public TerminalNode RPAR(int i) {
			return getToken(SliceParser.RPAR, i);
		}
		public List<TerminalNode> CLPAR() { return getTokens(SliceParser.CLPAR); }
		public TerminalNode CLPAR(int i) {
			return getToken(SliceParser.CLPAR, i);
		}
		public List<TerminalNode> CRPAR() { return getTokens(SliceParser.CRPAR); }
		public TerminalNode CRPAR(int i) {
			return getToken(SliceParser.CRPAR, i);
		}
		public List<TerminalNode> GT() { return getTokens(SliceParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(SliceParser.GT, i);
		}
		public List<TerminalNode> EQUAL() { return getTokens(SliceParser.EQUAL); }
		public TerminalNode EQUAL(int i) {
			return getToken(SliceParser.EQUAL, i);
		}
		public Smarts_charContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_smarts_char; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).enterSmarts_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SliceListener ) ((SliceListener)listener).exitSmarts_char(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SliceVisitor ) return ((SliceVisitor<? extends T>)visitor).visitSmarts_char(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Smarts_charContext smarts_char() throws RecognitionException {
		Smarts_charContext _localctx = new Smarts_charContext(_ctx, getState());
		enterRule(_localctx, 272, RULE_smarts_char);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1254);
			match(T__4);
			setState(1280); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(1280);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case TIMES:
					case DOT:
					case NAME:
						{
						setState(1255);
						function_name();
						}
						break;
					case PLUS:
						{
						setState(1256);
						match(PLUS);
						}
						break;
					case MINUS:
						{
						setState(1257);
						match(MINUS);
						}
						break;
					case EQ:
						{
						setState(1258);
						match(EQ);
						}
						break;
					case T__5:
						{
						setState(1259);
						match(T__5);
						}
						break;
					case T__6:
						{
						setState(1260);
						match(T__6);
						}
						break;
					case T__7:
						{
						setState(1261);
						match(T__7);
						}
						break;
					case DIV:
						{
						setState(1262);
						match(DIV);
						}
						break;
					case T__8:
						{
						setState(1263);
						match(T__8);
						}
						break;
					case COLON:
						{
						setState(1264);
						match(COLON);
						}
						break;
					case SEMIC:
						{
						setState(1265);
						match(SEMIC);
						}
						break;
					case COMMA:
						{
						setState(1266);
						match(COMMA);
						}
						break;
					case NOT_SIGN:
						{
						setState(1267);
						match(NOT_SIGN);
						}
						break;
					case T__9:
						{
						setState(1268);
						match(T__9);
						}
						break;
					case T__4:
						{
						setState(1269);
						match(T__4);
						}
						break;
					case T__10:
						{
						setState(1270);
						match(T__10);
						}
						break;
					case INT:
						{
						setState(1271);
						match(INT);
						}
						break;
					case LPAR:
						{
						setState(1272);
						match(LPAR);
						}
						break;
					case RPAR:
						{
						setState(1273);
						match(RPAR);
						}
						break;
					case CLPAR:
						{
						setState(1274);
						match(CLPAR);
						}
						break;
					case CRPAR:
						{
						setState(1275);
						match(CRPAR);
						}
						break;
					case GT:
						{
						setState(1276);
						match(GT);
						}
						break;
					case EQUAL:
						{
						setState(1277);
						match(EQUAL);
						}
						break;
					case T__11:
						{
						setState(1278);
						match(T__11);
						}
						break;
					case T__12:
						{
						setState(1279);
						match(T__12);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1282); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,147,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(1284);
			match(T__10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u01b7\u0507\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007"+
		"\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007"+
		"\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007"+
		"\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007"+
		"\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007"+
		"\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007"+
		",\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u0007"+
		"1\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u0007"+
		"6\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007"+
		";\u0002<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007"+
		"@\u0002A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007D\u0002E\u0007"+
		"E\u0002F\u0007F\u0002G\u0007G\u0002H\u0007H\u0002I\u0007I\u0002J\u0007"+
		"J\u0002K\u0007K\u0002L\u0007L\u0002M\u0007M\u0002N\u0007N\u0002O\u0007"+
		"O\u0002P\u0007P\u0002Q\u0007Q\u0002R\u0007R\u0002S\u0007S\u0002T\u0007"+
		"T\u0002U\u0007U\u0002V\u0007V\u0002W\u0007W\u0002X\u0007X\u0002Y\u0007"+
		"Y\u0002Z\u0007Z\u0002[\u0007[\u0002\\\u0007\\\u0002]\u0007]\u0002^\u0007"+
		"^\u0002_\u0007_\u0002`\u0007`\u0002a\u0007a\u0002b\u0007b\u0002c\u0007"+
		"c\u0002d\u0007d\u0002e\u0007e\u0002f\u0007f\u0002g\u0007g\u0002h\u0007"+
		"h\u0002i\u0007i\u0002j\u0007j\u0002k\u0007k\u0002l\u0007l\u0002m\u0007"+
		"m\u0002n\u0007n\u0002o\u0007o\u0002p\u0007p\u0002q\u0007q\u0002r\u0007"+
		"r\u0002s\u0007s\u0002t\u0007t\u0002u\u0007u\u0002v\u0007v\u0002w\u0007"+
		"w\u0002x\u0007x\u0002y\u0007y\u0002z\u0007z\u0002{\u0007{\u0002|\u0007"+
		"|\u0002}\u0007}\u0002~\u0007~\u0002\u007f\u0007\u007f\u0002\u0080\u0007"+
		"\u0080\u0002\u0081\u0007\u0081\u0002\u0082\u0007\u0082\u0002\u0083\u0007"+
		"\u0083\u0002\u0084\u0007\u0084\u0002\u0085\u0007\u0085\u0002\u0086\u0007"+
		"\u0086\u0002\u0087\u0007\u0087\u0002\u0088\u0007\u0088\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0005\u0001\u0116\b\u0001\n\u0001\f\u0001\u0119\t\u0001"+
		"\u0001\u0002\u0001\u0002\u0003\u0002\u011d\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0005\u0003\u0122\b\u0003\n\u0003\f\u0003\u0125\t\u0003\u0001"+
		"\u0003\u0003\u0003\u0128\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u0136\b\u0004\u0001\u0005\u0001"+
		"\u0005\u0003\u0005\u013a\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003"+
		"\u0006\u0145\b\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0003"+
		"\b\u014c\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0003\t\u0156\b\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u0163\b\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r"+
		"\u0001\r\u0003\r\u0170\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0003\u0010\u0184\b\u0010\u0003\u0010\u0186\b"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0003\u0013\u0190\b\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0003\u0013\u0195\b\u0013\u0001\u0013\u0001\u0013\u0003"+
		"\u0013\u0199\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0003\u0015\u01a3\b\u0015\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0003\u0017\u01ac\b\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u01b5\b\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u01ba\b\u0018\u0001\u0018\u0001"+
		"\u0018\u0003\u0018\u01be\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0005"+
		"\u0019\u01c3\b\u0019\n\u0019\f\u0019\u01c6\t\u0019\u0001\u0019\u0003\u0019"+
		"\u01c9\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u01ce\b"+
		"\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u01d3\b\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u01d8\b\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0003\u001b\u01dd\b\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0003\u001b\u01e2\b\u001b\u0005\u001b\u01e4\b\u001b\n\u001b\f\u001b"+
		"\u01e7\t\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u01eb\b\u001b\u0001"+
		"\u001b\u0001\u001b\u0003\u001b\u01ef\b\u001b\u0001\u001b\u0003\u001b\u01f2"+
		"\b\u001b\u0003\u001b\u01f4\b\u001b\u0001\u001c\u0001\u001c\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0005\u001d\u01fb\b\u001d\n\u001d\f\u001d\u01fe"+
		"\t\u001d\u0001\u001e\u0001\u001e\u0003\u001e\u0202\b\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0003\u001e\u0207\b\u001e\u0005\u001e\u0209\b"+
		"\u001e\n\u001e\f\u001e\u020c\t\u001e\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0001 \u0001 \u0001 \u0001 \u0001!\u0001!\u0001!\u0001!\u0001"+
		"\"\u0005\"\u021b\b\"\n\"\f\"\u021e\t\"\u0001#\u0001#\u0001#\u0001#\u0001"+
		"$\u0001$\u0001$\u0001%\u0001%\u0003%\u0229\b%\u0001%\u0001%\u0001&\u0001"+
		"&\u0001&\u0005&\u0230\b&\n&\f&\u0233\t&\u0001&\u0003&\u0236\b&\u0001\'"+
		"\u0001\'\u0001\'\u0001\'\u0003\'\u023c\b\'\u0001(\u0001(\u0001)\u0001"+
		")\u0001*\u0001*\u0003*\u0244\b*\u0001+\u0001+\u0003+\u0248\b+\u0001,\u0001"+
		",\u0003,\u024c\b,\u0001-\u0001-\u0003-\u0250\b-\u0001-\u0001-\u0003-\u0254"+
		"\b-\u0001-\u0001-\u0003-\u0258\b-\u0001-\u0003-\u025b\b-\u0001.\u0001"+
		".\u0001.\u0001.\u0003.\u0261\b.\u0001.\u0001.\u0003.\u0265\b.\u0001.\u0003"+
		".\u0268\b.\u0001.\u0001.\u0001/\u0001/\u0003/\u026e\b/\u00010\u00010\u0001"+
		"0\u00010\u00010\u00010\u00030\u0276\b0\u00011\u00011\u00012\u00012\u0001"+
		"2\u00052\u027d\b2\n2\f2\u0280\t2\u00013\u00013\u00013\u00053\u0285\b3"+
		"\n3\f3\u0288\t3\u00014\u00014\u00015\u00015\u00015\u00055\u028f\b5\n5"+
		"\f5\u0292\t5\u00016\u00016\u00016\u00056\u0297\b6\n6\f6\u029a\t6\u0001"+
		"7\u00037\u029d\b7\u00017\u00017\u00017\u00017\u00017\u00017\u00037\u02a5"+
		"\b7\u00017\u00017\u00017\u00017\u00017\u00037\u02ac\b7\u00018\u00038\u02af"+
		"\b8\u00018\u00018\u00018\u00018\u00018\u00018\u00018\u00038\u02b8\b8\u0001"+
		"8\u00018\u00018\u00018\u00018\u00038\u02bf\b8\u00019\u00019\u00019\u0001"+
		"9\u00019\u00019\u00019\u00039\u02c8\b9\u0001:\u0001:\u0001:\u0001;\u0001"+
		";\u0001;\u0001;\u0001;\u0003;\u02d2\b;\u0001;\u0001;\u0003;\u02d6\b;\u0001"+
		";\u0001;\u0003;\u02da\b;\u0001<\u0003<\u02dd\b<\u0001<\u0001<\u0001=\u0001"+
		"=\u0001>\u0001>\u0001>\u0001?\u0001?\u0003?\u02e8\b?\u0001?\u0003?\u02eb"+
		"\b?\u0001?\u0003?\u02ee\b?\u0001?\u0001?\u0001?\u0001?\u0001?\u0003?\u02f5"+
		"\b?\u0001?\u0003?\u02f8\b?\u0001?\u0001?\u0003?\u02fc\b?\u0001@\u0001"+
		"@\u0001A\u0003A\u0301\bA\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001"+
		"A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001"+
		"A\u0001A\u0001A\u0001A\u0001A\u0001A\u0001A\u0003A\u031a\bA\u0001B\u0001"+
		"B\u0001B\u0001C\u0001C\u0001C\u0001C\u0001C\u0003C\u0324\bC\u0001C\u0001"+
		"C\u0003C\u0328\bC\u0001C\u0001C\u0003C\u032c\bC\u0001D\u0001D\u0001D\u0001"+
		"D\u0003D\u0332\bD\u0001E\u0001E\u0001F\u0001F\u0001F\u0001G\u0001G\u0001"+
		"G\u0001H\u0001H\u0001H\u0001H\u0001I\u0001I\u0001I\u0001I\u0001J\u0001"+
		"J\u0003J\u0346\bJ\u0001K\u0001K\u0001K\u0003K\u034b\bK\u0001L\u0001L\u0003"+
		"L\u034f\bL\u0001L\u0001L\u0003L\u0353\bL\u0001L\u0001L\u0003L\u0357\b"+
		"L\u0001L\u0001L\u0003L\u035b\bL\u0001M\u0003M\u035e\bM\u0001M\u0001M\u0003"+
		"M\u0362\bM\u0001N\u0001N\u0001N\u0001O\u0001O\u0001O\u0001O\u0001P\u0001"+
		"P\u0001P\u0001Q\u0001Q\u0001Q\u0001Q\u0001R\u0001R\u0001R\u0001S\u0001"+
		"S\u0001S\u0001S\u0001T\u0001T\u0001T\u0001U\u0001U\u0001U\u0001U\u0001"+
		"V\u0001V\u0001V\u0003V\u0383\bV\u0001V\u0001V\u0001V\u0001V\u0001V\u0003"+
		"V\u038a\bV\u0001V\u0001V\u0001V\u0001V\u0001V\u0001V\u0001V\u0001V\u0003"+
		"V\u0394\bV\u0003V\u0396\bV\u0001W\u0001W\u0001W\u0003W\u039b\bW\u0001"+
		"W\u0001W\u0001W\u0003W\u03a0\bW\u0001W\u0001W\u0001W\u0003W\u03a5\bW\u0001"+
		"X\u0001X\u0001X\u0003X\u03aa\bX\u0001X\u0001X\u0001X\u0003X\u03af\bX\u0001"+
		"Y\u0001Y\u0001Y\u0003Y\u03b4\bY\u0001Z\u0001Z\u0003Z\u03b8\bZ\u0001Z\u0001"+
		"Z\u0001Z\u0001Z\u0001[\u0001[\u0001[\u0001[\u0001[\u0001[\u0001[\u0001"+
		"[\u0001[\u0001[\u0001[\u0001[\u0001[\u0001[\u0003[\u03cc\b[\u0001\\\u0001"+
		"\\\u0001\\\u0001]\u0001]\u0003]\u03d3\b]\u0001]\u0003]\u03d6\b]\u0001"+
		"]\u0001]\u0001^\u0001^\u0003^\u03dc\b^\u0001^\u0003^\u03df\b^\u0001^\u0001"+
		"^\u0001^\u0001_\u0001_\u0003_\u03e6\b_\u0001_\u0001_\u0001`\u0001`\u0003"+
		"`\u03ec\b`\u0001`\u0001`\u0001a\u0001a\u0001a\u0001b\u0001b\u0001b\u0001"+
		"c\u0003c\u03f7\bc\u0001c\u0003c\u03fa\bc\u0001c\u0001c\u0001c\u0001c\u0001"+
		"c\u0003c\u0401\bc\u0001c\u0003c\u0404\bc\u0001c\u0001c\u0003c\u0408\b"+
		"c\u0001d\u0001d\u0001d\u0001d\u0001d\u0001d\u0001d\u0001d\u0001d\u0001"+
		"d\u0003d\u0414\bd\u0001e\u0001e\u0001e\u0003e\u0419\be\u0001e\u0001e\u0001"+
		"e\u0001e\u0001e\u0003e\u0420\be\u0001e\u0001e\u0001e\u0001e\u0001e\u0001"+
		"e\u0001e\u0001e\u0003e\u042a\be\u0003e\u042c\be\u0001f\u0001f\u0003f\u0430"+
		"\bf\u0001f\u0001f\u0001f\u0001f\u0001f\u0003f\u0437\bf\u0001f\u0001f\u0001"+
		"f\u0001f\u0001f\u0001f\u0001f\u0003f\u0440\bf\u0001g\u0001g\u0001g\u0001"+
		"g\u0003g\u0446\bg\u0001h\u0001h\u0001h\u0001h\u0001h\u0001h\u0003h\u044e"+
		"\bh\u0001i\u0001i\u0001i\u0001i\u0001i\u0001i\u0001i\u0003i\u0457\bi\u0001"+
		"j\u0001j\u0001j\u0003j\u045c\bj\u0001k\u0001k\u0001k\u0001k\u0001k\u0003"+
		"k\u0463\bk\u0003k\u0465\bk\u0001l\u0001l\u0001m\u0001m\u0001n\u0001n\u0001"+
		"o\u0001o\u0001o\u0001p\u0001p\u0001q\u0001q\u0001r\u0001r\u0001s\u0001"+
		"s\u0001t\u0001t\u0001u\u0001u\u0003u\u047c\bu\u0001v\u0001v\u0001w\u0001"+
		"w\u0001w\u0001w\u0001w\u0001w\u0003w\u0486\bw\u0001w\u0001w\u0001w\u0001"+
		"w\u0003w\u048c\bw\u0001w\u0001w\u0001w\u0003w\u0491\bw\u0001w\u0001w\u0001"+
		"w\u0001w\u0001w\u0001w\u0003w\u0499\bw\u0001w\u0003w\u049c\bw\u0001x\u0001"+
		"x\u0001y\u0001y\u0001z\u0001z\u0001{\u0001{\u0001|\u0001|\u0001}\u0001"+
		"}\u0001~\u0001~\u0001\u007f\u0001\u007f\u0001\u007f\u0001\u0080\u0001"+
		"\u0080\u0001\u0081\u0001\u0081\u0001\u0081\u0003\u0081\u04b4\b\u0081\u0001"+
		"\u0082\u0001\u0082\u0003\u0082\u04b8\b\u0082\u0001\u0082\u0001\u0082\u0003"+
		"\u0082\u04bc\b\u0082\u0003\u0082\u04be\b\u0082\u0001\u0083\u0001\u0083"+
		"\u0001\u0083\u0003\u0083\u04c3\b\u0083\u0001\u0083\u0003\u0083\u04c6\b"+
		"\u0083\u0001\u0084\u0001\u0084\u0003\u0084\u04ca\b\u0084\u0001\u0084\u0003"+
		"\u0084\u04cd\b\u0084\u0001\u0084\u0001\u0084\u0003\u0084\u04d1\b\u0084"+
		"\u0001\u0084\u0003\u0084\u04d4\b\u0084\u0001\u0085\u0003\u0085\u04d7\b"+
		"\u0085\u0001\u0085\u0001\u0085\u0003\u0085\u04db\b\u0085\u0001\u0086\u0004"+
		"\u0086\u04de\b\u0086\u000b\u0086\f\u0086\u04df\u0001\u0087\u0004\u0087"+
		"\u04e3\b\u0087\u000b\u0087\f\u0087\u04e4\u0001\u0088\u0001\u0088\u0001"+
		"\u0088\u0001\u0088\u0001\u0088\u0001\u0088\u0001\u0088\u0001\u0088\u0001"+
		"\u0088\u0001\u0088\u0001\u0088\u0001\u0088\u0001\u0088\u0001\u0088\u0001"+
		"\u0088\u0001\u0088\u0001\u0088\u0001\u0088\u0001\u0088\u0001\u0088\u0001"+
		"\u0088\u0001\u0088\u0001\u0088\u0001\u0088\u0001\u0088\u0001\u0088\u0004"+
		"\u0088\u0501\b\u0088\u000b\u0088\f\u0088\u0502\u0001\u0088\u0001\u0088"+
		"\u0001\u0088\u0000\u0000\u0089\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPR"+
		"TVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\u008e"+
		"\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2\u00a4\u00a6"+
		"\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba\u00bc\u00be"+
		"\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2\u00d4\u00d6"+
		"\u00d8\u00da\u00dc\u00de\u00e0\u00e2\u00e4\u00e6\u00e8\u00ea\u00ec\u00ee"+
		"\u00f0\u00f2\u00f4\u00f6\u00f8\u00fa\u00fc\u00fe\u0100\u0102\u0104\u0106"+
		"\u0108\u010a\u010c\u010e\u0110\u0000\u001c\u0002\u0000\u017e\u017e\u0180"+
		"\u0184\u0001\u0000\u0185\u0186\u0002\u0000\u0013\u0013\u0016\u0016\u0001"+
		"\u0000\u013c\u013d\u0001\u0000\u013f\u0142\u0002\u0000\u0145\u0145\u0148"+
		"\u0149\u0002\u0000\u0146\u0147\u014e\u0150\u0002\u0000\u017e\u017e\u018a"+
		"\u018e\u0003\u0000\u0133\u013b\u017f\u017f\u0194\u0197\u0002\u0000\u0168"+
		"\u0169\u01a0\u01a0\u0001\u0000\u0129\u0131\u0001\u0000\u0125\u0127\u0001"+
		"\u0000\u0153\u015f\u0001\u0000\u01af\u01b0\u0001\u0000\u0017\u008a\u0001"+
		"\u0000\u008b\u00e1\u0001\u0000\u00e3\u00e6\u0001\u0000\u011d\u011f\u0003"+
		"\u0000\u00fe\u0100\u0103\u0103\u0106\u0108\u0003\u0000\u00f8\u00fc\u0101"+
		"\u0102\u0104\u0105\u0002\u0000\u0109\u0109\u0113\u0113\u0001\u0000\u010a"+
		"\u0112\u0002\u0000\u00f3\u00f3\u00f5\u00f5\u0001\u0000\u0114\u0117\u0001"+
		"\u0000\u0179\u017a\u0001\u0000\u0003\u0004\u0003\u0000\u0182\u0182\u0189"+
		"\u0189\u01b3\u01b3\u0002\u0000\u0183\u0183\u01b3\u01b3\u059d\u0000\u0112"+
		"\u0001\u0000\u0000\u0000\u0002\u0117\u0001\u0000\u0000\u0000\u0004\u011c"+
		"\u0001\u0000\u0000\u0000\u0006\u011e\u0001\u0000\u0000\u0000\b\u0135\u0001"+
		"\u0000\u0000\u0000\n\u0139\u0001\u0000\u0000\u0000\f\u0144\u0001\u0000"+
		"\u0000\u0000\u000e\u0146\u0001\u0000\u0000\u0000\u0010\u014b\u0001\u0000"+
		"\u0000\u0000\u0012\u014d\u0001\u0000\u0000\u0000\u0014\u015a\u0001\u0000"+
		"\u0000\u0000\u0016\u0167\u0001\u0000\u0000\u0000\u0018\u016a\u0001\u0000"+
		"\u0000\u0000\u001a\u016f\u0001\u0000\u0000\u0000\u001c\u0171\u0001\u0000"+
		"\u0000\u0000\u001e\u0175\u0001\u0000\u0000\u0000 \u0177\u0001\u0000\u0000"+
		"\u0000\"\u0187\u0001\u0000\u0000\u0000$\u0189\u0001\u0000\u0000\u0000"+
		"&\u0198\u0001\u0000\u0000\u0000(\u019a\u0001\u0000\u0000\u0000*\u01a2"+
		"\u0001\u0000\u0000\u0000,\u01a4\u0001\u0000\u0000\u0000.\u01a7\u0001\u0000"+
		"\u0000\u00000\u01b9\u0001\u0000\u0000\u00002\u01bf\u0001\u0000\u0000\u0000"+
		"4\u01cd\u0001\u0000\u0000\u00006\u01cf\u0001\u0000\u0000\u00008\u01f5"+
		"\u0001\u0000\u0000\u0000:\u01f7\u0001\u0000\u0000\u0000<\u0201\u0001\u0000"+
		"\u0000\u0000>\u020d\u0001\u0000\u0000\u0000@\u0211\u0001\u0000\u0000\u0000"+
		"B\u0215\u0001\u0000\u0000\u0000D\u021c\u0001\u0000\u0000\u0000F\u021f"+
		"\u0001\u0000\u0000\u0000H\u0223\u0001\u0000\u0000\u0000J\u0226\u0001\u0000"+
		"\u0000\u0000L\u022c\u0001\u0000\u0000\u0000N\u023b\u0001\u0000\u0000\u0000"+
		"P\u023d\u0001\u0000\u0000\u0000R\u023f\u0001\u0000\u0000\u0000T\u0243"+
		"\u0001\u0000\u0000\u0000V\u0247\u0001\u0000\u0000\u0000X\u024b\u0001\u0000"+
		"\u0000\u0000Z\u024f\u0001\u0000\u0000\u0000\\\u025c\u0001\u0000\u0000"+
		"\u0000^\u026d\u0001\u0000\u0000\u0000`\u0275\u0001\u0000\u0000\u0000b"+
		"\u0277\u0001\u0000\u0000\u0000d\u0279\u0001\u0000\u0000\u0000f\u0281\u0001"+
		"\u0000\u0000\u0000h\u0289\u0001\u0000\u0000\u0000j\u028b\u0001\u0000\u0000"+
		"\u0000l\u0293\u0001\u0000\u0000\u0000n\u029c\u0001\u0000\u0000\u0000p"+
		"\u02ae\u0001\u0000\u0000\u0000r\u02c7\u0001\u0000\u0000\u0000t\u02c9\u0001"+
		"\u0000\u0000\u0000v\u02cc\u0001\u0000\u0000\u0000x\u02dc\u0001\u0000\u0000"+
		"\u0000z\u02e0\u0001\u0000\u0000\u0000|\u02e2\u0001\u0000\u0000\u0000~"+
		"\u02e7\u0001\u0000\u0000\u0000\u0080\u02fd\u0001\u0000\u0000\u0000\u0082"+
		"\u0300\u0001\u0000\u0000\u0000\u0084\u031b\u0001\u0000\u0000\u0000\u0086"+
		"\u031e\u0001\u0000\u0000\u0000\u0088\u032d\u0001\u0000\u0000\u0000\u008a"+
		"\u0333\u0001\u0000\u0000\u0000\u008c\u0335\u0001\u0000\u0000\u0000\u008e"+
		"\u0338\u0001\u0000\u0000\u0000\u0090\u033b\u0001\u0000\u0000\u0000\u0092"+
		"\u033f\u0001\u0000\u0000\u0000\u0094\u0343\u0001\u0000\u0000\u0000\u0096"+
		"\u0347\u0001\u0000\u0000\u0000\u0098\u034c\u0001\u0000\u0000\u0000\u009a"+
		"\u035d\u0001\u0000\u0000\u0000\u009c\u0363\u0001\u0000\u0000\u0000\u009e"+
		"\u0366\u0001\u0000\u0000\u0000\u00a0\u036a\u0001\u0000\u0000\u0000\u00a2"+
		"\u036d\u0001\u0000\u0000\u0000\u00a4\u0371\u0001\u0000\u0000\u0000\u00a6"+
		"\u0374\u0001\u0000\u0000\u0000\u00a8\u0378\u0001\u0000\u0000\u0000\u00aa"+
		"\u037b\u0001\u0000\u0000\u0000\u00ac\u037f\u0001\u0000\u0000\u0000\u00ae"+
		"\u039a\u0001\u0000\u0000\u0000\u00b0\u03a9\u0001\u0000\u0000\u0000\u00b2"+
		"\u03b3\u0001\u0000\u0000\u0000\u00b4\u03b5\u0001\u0000\u0000\u0000\u00b6"+
		"\u03cb\u0001\u0000\u0000\u0000\u00b8\u03cd\u0001\u0000\u0000\u0000\u00ba"+
		"\u03d2\u0001\u0000\u0000\u0000\u00bc\u03db\u0001\u0000\u0000\u0000\u00be"+
		"\u03e5\u0001\u0000\u0000\u0000\u00c0\u03eb\u0001\u0000\u0000\u0000\u00c2"+
		"\u03ef\u0001\u0000\u0000\u0000\u00c4\u03f2\u0001\u0000\u0000\u0000\u00c6"+
		"\u03f6\u0001\u0000\u0000\u0000\u00c8\u0413\u0001\u0000\u0000\u0000\u00ca"+
		"\u0415\u0001\u0000\u0000\u0000\u00cc\u042f\u0001\u0000\u0000\u0000\u00ce"+
		"\u0441\u0001\u0000\u0000\u0000\u00d0\u0447\u0001\u0000\u0000\u0000\u00d2"+
		"\u0456\u0001\u0000\u0000\u0000\u00d4\u0458\u0001\u0000\u0000\u0000\u00d6"+
		"\u0464\u0001\u0000\u0000\u0000\u00d8\u0466\u0001\u0000\u0000\u0000\u00da"+
		"\u0468\u0001\u0000\u0000\u0000\u00dc\u046a\u0001\u0000\u0000\u0000\u00de"+
		"\u046c\u0001\u0000\u0000\u0000\u00e0\u046f\u0001\u0000\u0000\u0000\u00e2"+
		"\u0471\u0001\u0000\u0000\u0000\u00e4\u0473\u0001\u0000\u0000\u0000\u00e6"+
		"\u0475\u0001\u0000\u0000\u0000\u00e8\u0477\u0001\u0000\u0000\u0000\u00ea"+
		"\u047b\u0001\u0000\u0000\u0000\u00ec\u047d\u0001\u0000\u0000\u0000\u00ee"+
		"\u049b\u0001\u0000\u0000\u0000\u00f0\u049d\u0001\u0000\u0000\u0000\u00f2"+
		"\u049f\u0001\u0000\u0000\u0000\u00f4\u04a1\u0001\u0000\u0000\u0000\u00f6"+
		"\u04a3\u0001\u0000\u0000\u0000\u00f8\u04a5\u0001\u0000\u0000\u0000\u00fa"+
		"\u04a7\u0001\u0000\u0000\u0000\u00fc\u04a9\u0001\u0000\u0000\u0000\u00fe"+
		"\u04ab\u0001\u0000\u0000\u0000\u0100\u04ae\u0001\u0000\u0000\u0000\u0102"+
		"\u04b0\u0001\u0000\u0000\u0000\u0104\u04bd\u0001\u0000\u0000\u0000\u0106"+
		"\u04bf\u0001\u0000\u0000\u0000\u0108\u04d3\u0001\u0000\u0000\u0000\u010a"+
		"\u04d6\u0001\u0000\u0000\u0000\u010c\u04dd\u0001\u0000\u0000\u0000\u010e"+
		"\u04e2\u0001\u0000\u0000\u0000\u0110\u04e6\u0001\u0000\u0000\u0000\u0112"+
		"\u0113\u0003\u0002\u0001\u0000\u0113\u0001\u0001\u0000\u0000\u0000\u0114"+
		"\u0116\u0003\u0004\u0002\u0000\u0115\u0114\u0001\u0000\u0000\u0000\u0116"+
		"\u0119\u0001\u0000\u0000\u0000\u0117\u0115\u0001\u0000\u0000\u0000\u0117"+
		"\u0118\u0001\u0000\u0000\u0000\u0118\u0003\u0001\u0000\u0000\u0000\u0119"+
		"\u0117\u0001\u0000\u0000\u0000\u011a\u011d\u0003\u0006\u0003\u0000\u011b"+
		"\u011d\u00034\u001a\u0000\u011c\u011a\u0001\u0000\u0000\u0000\u011c\u011b"+
		"\u0001\u0000\u0000\u0000\u011d\u0005\u0001\u0000\u0000\u0000\u011e\u0123"+
		"\u0003\b\u0004\u0000\u011f\u0120\u0005\u017b\u0000\u0000\u0120\u0122\u0003"+
		"\b\u0004\u0000\u0121\u011f\u0001\u0000\u0000\u0000\u0122\u0125\u0001\u0000"+
		"\u0000\u0000\u0123\u0121\u0001\u0000\u0000\u0000\u0123\u0124\u0001\u0000"+
		"\u0000\u0000\u0124\u0127\u0001\u0000\u0000\u0000\u0125\u0123\u0001\u0000"+
		"\u0000\u0000\u0126\u0128\u0005\u017b\u0000\u0000\u0127\u0126\u0001\u0000"+
		"\u0000\u0000\u0127\u0128\u0001\u0000\u0000\u0000\u0128\u0007\u0001\u0000"+
		"\u0000\u0000\u0129\u0136\u0003H$\u0000\u012a\u0136\u0003\n\u0005\u0000"+
		"\u012b\u0136\u0003\u0018\f\u0000\u012c\u0136\u0003\u001a\r\u0000\u012d"+
		"\u0136\u0003 \u0010\u0000\u012e\u0136\u0003\"\u0011\u0000\u012f\u0136"+
		"\u0003*\u0015\u0000\u0130\u0136\u0003&\u0013\u0000\u0131\u0136\u0003$"+
		"\u0012\u0000\u0132\u0136\u0003&\u0013\u0000\u0133\u0136\u0003(\u0014\u0000"+
		"\u0134\u0136\u0003\u0010\b\u0000\u0135\u0129\u0001\u0000\u0000\u0000\u0135"+
		"\u012a\u0001\u0000\u0000\u0000\u0135\u012b\u0001\u0000\u0000\u0000\u0135"+
		"\u012c\u0001\u0000\u0000\u0000\u0135\u012d\u0001\u0000\u0000\u0000\u0135"+
		"\u012e\u0001\u0000\u0000\u0000\u0135\u012f\u0001\u0000\u0000\u0000\u0135"+
		"\u0130\u0001\u0000\u0000\u0000\u0135\u0131\u0001\u0000\u0000\u0000\u0135"+
		"\u0132\u0001\u0000\u0000\u0000\u0135\u0133\u0001\u0000\u0000\u0000\u0135"+
		"\u0134\u0001\u0000\u0000\u0000\u0136\t\u0001\u0000\u0000\u0000\u0137\u013a"+
		"\u0003\u00d2i\u0000\u0138\u013a\u0003\u00d4j\u0000\u0139\u0137\u0001\u0000"+
		"\u0000\u0000\u0139\u0138\u0001\u0000\u0000\u0000\u013a\u013b\u0001\u0000"+
		"\u0000\u0000\u013b\u013c\u0007\u0000\u0000\u0000\u013c\u013d\u0003\f\u0006"+
		"\u0000\u013d\u000b\u0001\u0000\u0000\u0000\u013e\u0145\u0003\u00d4j\u0000"+
		"\u013f\u0145\u0003\u00e0p\u0000\u0140\u0145\u0003\u00e2q\u0000\u0141\u0145"+
		"\u0003\u0100\u0080\u0000\u0142\u0145\u0003b1\u0000\u0143\u0145\u0003\u00cc"+
		"f\u0000\u0144\u013e\u0001\u0000\u0000\u0000\u0144\u013f\u0001\u0000\u0000"+
		"\u0000\u0144\u0140\u0001\u0000\u0000\u0000\u0144\u0141\u0001\u0000\u0000"+
		"\u0000\u0144\u0142\u0001\u0000\u0000\u0000\u0144\u0143\u0001\u0000\u0000"+
		"\u0000\u0145\r\u0001\u0000\u0000\u0000\u0146\u0147\u0007\u0001\u0000\u0000"+
		"\u0147\u000f\u0001\u0000\u0000\u0000\u0148\u014c\u0003\u0012\t\u0000\u0149"+
		"\u014c\u0003\u0014\n\u0000\u014a\u014c\u0003\u0016\u000b\u0000\u014b\u0148"+
		"\u0001\u0000\u0000\u0000\u014b\u0149\u0001\u0000\u0000\u0000\u014b\u014a"+
		"\u0001\u0000\u0000\u0000\u014c\u0011\u0001\u0000\u0000\u0000\u014d\u0155"+
		"\u0005\u0172\u0000\u0000\u014e\u0156\u0003b1\u0000\u014f\u0156\u0003n"+
		"7\u0000\u0150\u0156\u0003\u00d4j\u0000\u0151\u0156\u0005\u01af\u0000\u0000"+
		"\u0152\u0156\u0003\u00e0p\u0000\u0153\u0156\u0003\u00e2q\u0000\u0154\u0156"+
		"\u0003\u0100\u0080\u0000\u0155\u014e\u0001\u0000\u0000\u0000\u0155\u014f"+
		"\u0001\u0000\u0000\u0000\u0155\u0150\u0001\u0000\u0000\u0000\u0155\u0151"+
		"\u0001\u0000\u0000\u0000\u0155\u0152\u0001\u0000\u0000\u0000\u0155\u0153"+
		"\u0001\u0000\u0000\u0000\u0155\u0154\u0001\u0000\u0000\u0000\u0156\u0157"+
		"\u0001\u0000\u0000\u0000\u0157\u0158\u0005\u01a8\u0000\u0000\u0158\u0159"+
		"\u0005\u01b3\u0000\u0000\u0159\u0013\u0001\u0000\u0000\u0000\u015a\u0162"+
		"\u0005\u0121\u0000\u0000\u015b\u0163\u0003b1\u0000\u015c\u0163\u0003n"+
		"7\u0000\u015d\u0163\u0003\u00d4j\u0000\u015e\u0163\u0005\u01af\u0000\u0000"+
		"\u015f\u0163\u0003\u00e0p\u0000\u0160\u0163\u0003\u00e2q\u0000\u0161\u0163"+
		"\u0003\u0100\u0080\u0000\u0162\u015b\u0001\u0000\u0000\u0000\u0162\u015c"+
		"\u0001\u0000\u0000\u0000\u0162\u015d\u0001\u0000\u0000\u0000\u0162\u015e"+
		"\u0001\u0000\u0000\u0000\u0162\u015f\u0001\u0000\u0000\u0000\u0162\u0160"+
		"\u0001\u0000\u0000\u0000\u0162\u0161\u0001\u0000\u0000\u0000\u0163\u0164"+
		"\u0001\u0000\u0000\u0000\u0164\u0165\u0005\u000f\u0000\u0000\u0165\u0166"+
		"\u0005\u01b3\u0000\u0000\u0166\u0015\u0001\u0000\u0000\u0000\u0167\u0168"+
		"\u0005\u0122\u0000\u0000\u0168\u0169\u0005\u01b3\u0000\u0000\u0169\u0017"+
		"\u0001\u0000\u0000\u0000\u016a\u016b\u0005\u0011\u0000\u0000\u016b\u0019"+
		"\u0001\u0000\u0000\u0000\u016c\u016d\u0007\u0002\u0000\u0000\u016d\u0170"+
		"\u0005\u01af\u0000\u0000\u016e\u0170\u0003\u001c\u000e\u0000\u016f\u016c"+
		"\u0001\u0000\u0000\u0000\u016f\u016e\u0001\u0000\u0000\u0000\u0170\u001b"+
		"\u0001\u0000\u0000\u0000\u0171\u0172\u0007\u0003\u0000\u0000\u0172\u0173"+
		"\u0005\u013e\u0000\u0000\u0173\u0174\u0003\u001e\u000f\u0000\u0174\u001d"+
		"\u0001\u0000\u0000\u0000\u0175\u0176\u0007\u0004\u0000\u0000\u0176\u001f"+
		"\u0001\u0000\u0000\u0000\u0177\u0185\u0005\u0012\u0000\u0000\u0178\u0186"+
		"\u0003\u00d4j\u0000\u0179\u0186\u0003H$\u0000\u017a\u0186\u0003\u00e0"+
		"p\u0000\u017b\u0186\u0003\u00e2q\u0000\u017c\u0186\u0003\u0100\u0080\u0000"+
		"\u017d\u017e\u0003\u00d4j\u0000\u017e\u0183\u0005\u017f\u0000\u0000\u017f"+
		"\u0184\u0003\u00d4j\u0000\u0180\u0184\u0003\u00e0p\u0000\u0181\u0184\u0003"+
		"\u00e2q\u0000\u0182\u0184\u0003\u0100\u0080\u0000\u0183\u017f\u0001\u0000"+
		"\u0000\u0000\u0183\u0180\u0001\u0000\u0000\u0000\u0183\u0181\u0001\u0000"+
		"\u0000\u0000\u0183\u0182\u0001\u0000\u0000\u0000\u0184\u0186\u0001\u0000"+
		"\u0000\u0000\u0185\u0178\u0001\u0000\u0000\u0000\u0185\u0179\u0001\u0000"+
		"\u0000\u0000\u0185\u017a\u0001\u0000\u0000\u0000\u0185\u017b\u0001\u0000"+
		"\u0000\u0000\u0185\u017c\u0001\u0000\u0000\u0000\u0185\u017d\u0001\u0000"+
		"\u0000\u0000\u0186!\u0001\u0000\u0000\u0000\u0187\u0188\u0005\u000e\u0000"+
		"\u0000\u0188#\u0001\u0000\u0000\u0000\u0189\u018a\u0005\u0015\u0000\u0000"+
		"\u018a\u018b\u0005\u01a1\u0000\u0000\u018b\u018c\u0003v;\u0000\u018c%"+
		"\u0001\u0000\u0000\u0000\u018d\u018f\u0007\u0005\u0000\u0000\u018e\u0190"+
		"\u0005\u01a1\u0000\u0000\u018f\u018e\u0001\u0000\u0000\u0000\u018f\u0190"+
		"\u0001\u0000\u0000\u0000\u0190\u0191\u0001\u0000\u0000\u0000\u0191\u0199"+
		"\u0003v;\u0000\u0192\u0194\u0007\u0006\u0000\u0000\u0193\u0195\u0005\u01a1"+
		"\u0000\u0000\u0194\u0193\u0001\u0000\u0000\u0000\u0194\u0195\u0001\u0000"+
		"\u0000\u0000\u0195\u0196\u0001\u0000\u0000\u0000\u0196\u0197\u0005\u0168"+
		"\u0000\u0000\u0197\u0199\u0005\u01af\u0000\u0000\u0198\u018d\u0001\u0000"+
		"\u0000\u0000\u0198\u0192\u0001\u0000\u0000\u0000\u0199\'\u0001\u0000\u0000"+
		"\u0000\u019a\u019b\u0005\u01ab\u0000\u0000\u019b\u019c\u0005\u019e\u0000"+
		"\u0000\u019c\u019d\u0005\u019d\u0000\u0000\u019d\u019e\u0005\u016c\u0000"+
		"\u0000\u019e\u019f\u0005\u016a\u0000\u0000\u019f)\u0001\u0000\u0000\u0000"+
		"\u01a0\u01a3\u0003,\u0016\u0000\u01a1\u01a3\u0003.\u0017\u0000\u01a2\u01a0"+
		"\u0001\u0000\u0000\u0000\u01a2\u01a1\u0001\u0000\u0000\u0000\u01a3+\u0001"+
		"\u0000\u0000\u0000\u01a4\u01a5\u0005\u0010\u0000\u0000\u01a5\u01a6\u0003"+
		"2\u0019\u0000\u01a6-\u0001\u0000\u0000\u0000\u01a7\u01ab\u0005\u000f\u0000"+
		"\u0000\u01a8\u01ac\u0005\u01b3\u0000\u0000\u01a9\u01ac\u0003\u010e\u0087"+
		"\u0000\u01aa\u01ac\u0005\u01b2\u0000\u0000\u01ab\u01a8\u0001\u0000\u0000"+
		"\u0000\u01ab\u01a9\u0001\u0000\u0000\u0000\u01ab\u01aa\u0001\u0000\u0000"+
		"\u0000\u01ac\u01ad\u0001\u0000\u0000\u0000\u01ad\u01b4\u0005\u0010\u0000"+
		"\u0000\u01ae\u01b5\u0005\u0182\u0000\u0000\u01af\u01b0\u0005\u0185\u0000"+
		"\u0000\u01b0\u01b1\u00032\u0019\u0000\u01b1\u01b2\u0005\u0186\u0000\u0000"+
		"\u01b2\u01b5\u0001\u0000\u0000\u0000\u01b3\u01b5\u00032\u0019\u0000\u01b4"+
		"\u01ae\u0001\u0000\u0000\u0000\u01b4\u01af\u0001\u0000\u0000\u0000\u01b4"+
		"\u01b3\u0001\u0000\u0000\u0000\u01b5/\u0001\u0000\u0000\u0000\u01b6\u01ba"+
		"\u0005\u01b3\u0000\u0000\u01b7\u01ba\u0003\u010e\u0087\u0000\u01b8\u01ba"+
		"\u0005\u01b2\u0000\u0000\u01b9\u01b6\u0001\u0000\u0000\u0000\u01b9\u01b7"+
		"\u0001\u0000\u0000\u0000\u01b9\u01b8\u0001\u0000\u0000\u0000\u01ba\u01bd"+
		"\u0001\u0000\u0000\u0000\u01bb\u01bc\u0005\u019d\u0000\u0000\u01bc\u01be"+
		"\u0005\u01b3\u0000\u0000\u01bd\u01bb\u0001\u0000\u0000\u0000\u01bd\u01be"+
		"\u0001\u0000\u0000\u0000\u01be1\u0001\u0000\u0000\u0000\u01bf\u01c4\u0003"+
		"0\u0018\u0000\u01c0\u01c1\u0005\u017d\u0000\u0000\u01c1\u01c3\u00030\u0018"+
		"\u0000\u01c2\u01c0\u0001\u0000\u0000\u0000\u01c3\u01c6\u0001\u0000\u0000"+
		"\u0000\u01c4\u01c2\u0001\u0000\u0000\u0000\u01c4\u01c5\u0001\u0000\u0000"+
		"\u0000\u01c5\u01c8\u0001\u0000\u0000\u0000\u01c6\u01c4\u0001\u0000\u0000"+
		"\u0000\u01c7\u01c9\u0005\u017d\u0000\u0000\u01c8\u01c7\u0001\u0000\u0000"+
		"\u0000\u01c8\u01c9\u0001\u0000\u0000\u0000\u01c93\u0001\u0000\u0000\u0000"+
		"\u01ca\u01ce\u00036\u001b\u0000\u01cb\u01ce\u0003@ \u0000\u01cc\u01ce"+
		"\u0003F#\u0000\u01cd\u01ca\u0001\u0000\u0000\u0000\u01cd\u01cb\u0001\u0000"+
		"\u0000\u0000\u01cd\u01cc\u0001\u0000\u0000\u0000\u01ce5\u0001\u0000\u0000"+
		"\u0000\u01cf\u01d0\u0005\u016f\u0000\u0000\u01d0\u01d7\u00038\u001c\u0000"+
		"\u01d1\u01d3\u0005\u0173\u0000\u0000\u01d2\u01d1\u0001\u0000\u0000\u0000"+
		"\u01d2\u01d3\u0001\u0000\u0000\u0000\u01d3\u01d4\u0001\u0000\u0000\u0000"+
		"\u01d4\u01d8\u0003B!\u0000\u01d5\u01d6\u0005\u0173\u0000\u0000\u01d6\u01d8"+
		"\u0003\b\u0004\u0000\u01d7\u01d2\u0001\u0000\u0000\u0000\u01d7\u01d5\u0001"+
		"\u0000\u0000\u0000\u01d8\u01e5\u0001\u0000\u0000\u0000\u01d9\u01da\u0005"+
		"\u016e\u0000\u0000\u01da\u01e1\u00038\u001c\u0000\u01db\u01dd\u0005\u0173"+
		"\u0000\u0000\u01dc\u01db\u0001\u0000\u0000\u0000\u01dc\u01dd\u0001\u0000"+
		"\u0000\u0000\u01dd\u01de\u0001\u0000\u0000\u0000\u01de\u01e2\u0003B!\u0000"+
		"\u01df\u01e0\u0005\u0173\u0000\u0000\u01e0\u01e2\u0003\b\u0004\u0000\u01e1"+
		"\u01dc\u0001\u0000\u0000\u0000\u01e1\u01df\u0001\u0000\u0000\u0000\u01e2"+
		"\u01e4\u0001\u0000\u0000\u0000\u01e3\u01d9\u0001\u0000\u0000\u0000\u01e4"+
		"\u01e7\u0001\u0000\u0000\u0000\u01e5\u01e3\u0001\u0000\u0000\u0000\u01e5"+
		"\u01e6\u0001\u0000\u0000\u0000\u01e6\u01f3\u0001\u0000\u0000\u0000\u01e7"+
		"\u01e5\u0001\u0000\u0000\u0000\u01e8\u01f1\u0005\u0170\u0000\u0000\u01e9"+
		"\u01eb\u0005\u0173\u0000\u0000\u01ea\u01e9\u0001\u0000\u0000\u0000\u01ea"+
		"\u01eb\u0001\u0000\u0000\u0000\u01eb\u01ec\u0001\u0000\u0000\u0000\u01ec"+
		"\u01f2\u0003B!\u0000\u01ed\u01ef\u0005\u0173\u0000\u0000\u01ee\u01ed\u0001"+
		"\u0000\u0000\u0000\u01ee\u01ef\u0001\u0000\u0000\u0000\u01ef\u01f0\u0001"+
		"\u0000\u0000\u0000\u01f0\u01f2\u0003\b\u0004\u0000\u01f1\u01ea\u0001\u0000"+
		"\u0000\u0000\u01f1\u01ee\u0001\u0000\u0000\u0000\u01f2\u01f4\u0001\u0000"+
		"\u0000\u0000\u01f3\u01e8\u0001\u0000\u0000\u0000\u01f3\u01f4\u0001\u0000"+
		"\u0000\u0000\u01f47\u0001\u0000\u0000\u0000\u01f5\u01f6\u0003:\u001d\u0000"+
		"\u01f69\u0001\u0000\u0000\u0000\u01f7\u01fc\u0003<\u001e\u0000\u01f8\u01f9"+
		"\u0005\u0177\u0000\u0000\u01f9\u01fb\u0003<\u001e\u0000\u01fa\u01f8\u0001"+
		"\u0000\u0000\u0000\u01fb\u01fe\u0001\u0000\u0000\u0000\u01fc\u01fa\u0001"+
		"\u0000\u0000\u0000\u01fc\u01fd\u0001\u0000\u0000\u0000\u01fd;\u0001\u0000"+
		"\u0000\u0000\u01fe\u01fc\u0001\u0000\u0000\u0000\u01ff\u0202\u0003>\u001f"+
		"\u0000\u0200\u0202\u0003P(\u0000\u0201\u01ff\u0001\u0000\u0000\u0000\u0201"+
		"\u0200\u0001\u0000\u0000\u0000\u0202\u020a\u0001\u0000\u0000\u0000\u0203"+
		"\u0206\u0005\u0178\u0000\u0000\u0204\u0207\u0003>\u001f\u0000\u0205\u0207"+
		"\u0003P(\u0000\u0206\u0204\u0001\u0000\u0000\u0000\u0206\u0205\u0001\u0000"+
		"\u0000\u0000\u0207\u0209\u0001\u0000\u0000\u0000\u0208\u0203\u0001\u0000"+
		"\u0000\u0000\u0209\u020c\u0001\u0000\u0000\u0000\u020a\u0208\u0001\u0000"+
		"\u0000\u0000\u020a\u020b\u0001\u0000\u0000\u0000\u020b=\u0001\u0000\u0000"+
		"\u0000\u020c\u020a\u0001\u0000\u0000\u0000\u020d\u020e\u0005\u0185\u0000"+
		"\u0000\u020e\u020f\u0003P(\u0000\u020f\u0210\u0005\u0186\u0000\u0000\u0210"+
		"?\u0001\u0000\u0000\u0000\u0211\u0212\u0005\u0174\u0000\u0000\u0212\u0213"+
		"\u0003\u00b4Z\u0000\u0213\u0214\u0003B!\u0000\u0214A\u0001\u0000\u0000"+
		"\u0000\u0215\u0216\u0005\u0187\u0000\u0000\u0216\u0217\u0003D\"\u0000"+
		"\u0217\u0218\u0005\u0188\u0000\u0000\u0218C\u0001\u0000\u0000\u0000\u0219"+
		"\u021b\u0003\u0004\u0002\u0000\u021a\u0219\u0001\u0000\u0000\u0000\u021b"+
		"\u021e\u0001\u0000\u0000\u0000\u021c\u021a\u0001\u0000\u0000\u0000\u021c"+
		"\u021d\u0001\u0000\u0000\u0000\u021dE\u0001\u0000\u0000\u0000\u021e\u021c"+
		"\u0001\u0000\u0000\u0000\u021f\u0220\u0005\u016d\u0000\u0000\u0220\u0221"+
		"\u0003H$\u0000\u0221\u0222\u0003B!\u0000\u0222G\u0001\u0000\u0000\u0000"+
		"\u0223\u0224\u0003\u010c\u0086\u0000\u0224\u0225\u0003J%\u0000\u0225I"+
		"\u0001\u0000\u0000\u0000\u0226\u0228\u0005\u0185\u0000\u0000\u0227\u0229"+
		"\u0003L&\u0000\u0228\u0227\u0001\u0000\u0000\u0000\u0228\u0229\u0001\u0000"+
		"\u0000\u0000\u0229\u022a\u0001\u0000\u0000\u0000\u022a\u022b\u0005\u0186"+
		"\u0000\u0000\u022bK\u0001\u0000\u0000\u0000\u022c\u0231\u0003N\'\u0000"+
		"\u022d\u022e\u0005\u017d\u0000\u0000\u022e\u0230\u0003N\'\u0000\u022f"+
		"\u022d\u0001\u0000\u0000\u0000\u0230\u0233\u0001\u0000\u0000\u0000\u0231"+
		"\u022f\u0001\u0000\u0000\u0000\u0231\u0232\u0001\u0000\u0000\u0000\u0232"+
		"\u0235\u0001\u0000\u0000\u0000\u0233\u0231\u0001\u0000\u0000\u0000\u0234"+
		"\u0236\u0005\u017d\u0000\u0000\u0235\u0234\u0001\u0000\u0000\u0000\u0235"+
		"\u0236\u0001\u0000\u0000\u0000\u0236M\u0001\u0000\u0000\u0000\u0237\u023c"+
		"\u0003\u00d4j\u0000\u0238\u023c\u0003\u00e2q\u0000\u0239\u023c\u0003\u0100"+
		"\u0080\u0000\u023a\u023c\u0003b1\u0000\u023b\u0237\u0001\u0000\u0000\u0000"+
		"\u023b\u0238\u0001\u0000\u0000\u0000\u023b\u0239\u0001\u0000\u0000\u0000"+
		"\u023b\u023a\u0001\u0000\u0000\u0000\u023cO\u0001\u0000\u0000\u0000\u023d"+
		"\u023e\u0003Z-\u0000\u023eQ\u0001\u0000\u0000\u0000\u023f\u0240\u0007"+
		"\u0007\u0000\u0000\u0240S\u0001\u0000\u0000\u0000\u0241\u0244\u0003V+"+
		"\u0000\u0242\u0244\u0003X,\u0000\u0243\u0241\u0001\u0000\u0000\u0000\u0243"+
		"\u0242\u0001\u0000\u0000\u0000\u0244U\u0001\u0000\u0000\u0000\u0245\u0248"+
		"\u0003\u0104\u0082\u0000\u0246\u0248\u0003\u0108\u0084\u0000\u0247\u0245"+
		"\u0001\u0000\u0000\u0000\u0247\u0246\u0001\u0000\u0000\u0000\u0248W\u0001"+
		"\u0000\u0000\u0000\u0249\u024c\u0003\u0102\u0081\u0000\u024a\u024c\u0003"+
		"\u0106\u0083\u0000\u024b\u0249\u0001\u0000\u0000\u0000\u024b\u024a\u0001"+
		"\u0000\u0000\u0000\u024cY\u0001\u0000\u0000\u0000\u024d\u0250\u0003\\"+
		".\u0000\u024e\u0250\u0003^/\u0000\u024f\u024d\u0001\u0000\u0000\u0000"+
		"\u024f\u024e\u0001\u0000\u0000\u0000\u0250\u0257\u0001\u0000\u0000\u0000"+
		"\u0251\u0254\u0003T*\u0000\u0252\u0254\u0003R)\u0000\u0253\u0251\u0001"+
		"\u0000\u0000\u0000\u0253\u0252\u0001\u0000\u0000\u0000\u0254\u0255\u0001"+
		"\u0000\u0000\u0000\u0255\u0256\u0003`0\u0000\u0256\u0258\u0001\u0000\u0000"+
		"\u0000\u0257\u0253\u0001\u0000\u0000\u0000\u0257\u0258\u0001\u0000\u0000"+
		"\u0000\u0258\u025a\u0001\u0000\u0000\u0000\u0259\u025b\u0003\u00d8l\u0000"+
		"\u025a\u0259\u0001\u0000\u0000\u0000\u025a\u025b\u0001\u0000\u0000\u0000"+
		"\u025b[\u0001\u0000\u0000\u0000\u025c\u025d\u0005\u0185\u0000\u0000\u025d"+
		"\u0264\u0003^/\u0000\u025e\u0261\u0003T*\u0000\u025f\u0261\u0003R)\u0000"+
		"\u0260\u025e\u0001\u0000\u0000\u0000\u0260\u025f\u0001\u0000\u0000\u0000"+
		"\u0261\u0262\u0001\u0000\u0000\u0000\u0262\u0263\u0003`0\u0000\u0263\u0265"+
		"\u0001\u0000\u0000\u0000\u0264\u0260\u0001\u0000\u0000\u0000\u0264\u0265"+
		"\u0001\u0000\u0000\u0000\u0265\u0267\u0001\u0000\u0000\u0000\u0266\u0268"+
		"\u0003\u00d8l\u0000\u0267\u0266\u0001\u0000\u0000\u0000\u0267\u0268\u0001"+
		"\u0000\u0000\u0000\u0268\u0269\u0001\u0000\u0000\u0000\u0269\u026a\u0005"+
		"\u0186\u0000\u0000\u026a]\u0001\u0000\u0000\u0000\u026b\u026e\u0003b1"+
		"\u0000\u026c\u026e\u0003\u00d4j\u0000\u026d\u026b\u0001\u0000\u0000\u0000"+
		"\u026d\u026c\u0001\u0000\u0000\u0000\u026e_\u0001\u0000\u0000\u0000\u026f"+
		"\u0276\u0003h4\u0000\u0270\u0276\u0003\u00e0p\u0000\u0271\u0276\u0003"+
		"\u00e2q\u0000\u0272\u0276\u0003\u0100\u0080\u0000\u0273\u0276\u0003\u00d4"+
		"j\u0000\u0274\u0276\u0005\u01b2\u0000\u0000\u0275\u026f\u0001\u0000\u0000"+
		"\u0000\u0275\u0270\u0001\u0000\u0000\u0000\u0275\u0271\u0001\u0000\u0000"+
		"\u0000\u0275\u0272\u0001\u0000\u0000\u0000\u0275\u0273\u0001\u0000\u0000"+
		"\u0000\u0275\u0274\u0001\u0000\u0000\u0000\u0276a\u0001\u0000\u0000\u0000"+
		"\u0277\u0278\u0003d2\u0000\u0278c\u0001\u0000\u0000\u0000\u0279\u027e"+
		"\u0003f3\u0000\u027a\u027b\u0005\u0199\u0000\u0000\u027b\u027d\u0003f"+
		"3\u0000\u027c\u027a\u0001\u0000\u0000\u0000\u027d\u0280\u0001\u0000\u0000"+
		"\u0000\u027e\u027c\u0001\u0000\u0000\u0000\u027e\u027f\u0001\u0000\u0000"+
		"\u0000\u027fe\u0001\u0000\u0000\u0000\u0280\u027e\u0001\u0000\u0000\u0000"+
		"\u0281\u0286\u0003r9\u0000\u0282\u0283\u0005\u0198\u0000\u0000\u0283\u0285"+
		"\u0003r9\u0000\u0284\u0282\u0001\u0000\u0000\u0000\u0285\u0288\u0001\u0000"+
		"\u0000\u0000\u0286\u0284\u0001\u0000\u0000\u0000\u0286\u0287\u0001\u0000"+
		"\u0000\u0000\u0287g\u0001\u0000\u0000\u0000\u0288\u0286\u0001\u0000\u0000"+
		"\u0000\u0289\u028a\u0003j5\u0000\u028ai\u0001\u0000\u0000\u0000\u028b"+
		"\u0290\u0003l6\u0000\u028c\u028d\u0005\u0199\u0000\u0000\u028d\u028f\u0003"+
		"l6\u0000\u028e\u028c\u0001\u0000\u0000\u0000\u028f\u0292\u0001\u0000\u0000"+
		"\u0000\u0290\u028e\u0001\u0000\u0000\u0000\u0290\u0291\u0001\u0000\u0000"+
		"\u0000\u0291k\u0001\u0000\u0000\u0000\u0292\u0290\u0001\u0000\u0000\u0000"+
		"\u0293\u0298\u0003\u0082A\u0000\u0294\u0295\u0005\u0198\u0000\u0000\u0295"+
		"\u0297\u0003\u0082A\u0000\u0296\u0294\u0001\u0000\u0000\u0000\u0297\u029a"+
		"\u0001\u0000\u0000\u0000\u0298\u0296\u0001\u0000\u0000\u0000\u0298\u0299"+
		"\u0001\u0000\u0000\u0000\u0299m\u0001\u0000\u0000\u0000\u029a\u0298\u0001"+
		"\u0000\u0000\u0000\u029b\u029d\u0005\u01ae\u0000\u0000\u029c\u029b\u0001"+
		"\u0000\u0000\u0000\u029c\u029d\u0001\u0000\u0000\u0000\u029d\u029e\u0001"+
		"\u0000\u0000\u0000\u029e\u029f\u0005\u00e2\u0000\u0000\u029f\u02a4\u0005"+
		"\u000f\u0000\u0000\u02a0\u02a1\u0005\u0168\u0000\u0000\u02a1\u02a5\u0005"+
		"\u01af\u0000\u0000\u02a2\u02a5\u0003~?\u0000\u02a3\u02a5\u0003\u00d4j"+
		"\u0000\u02a4\u02a0\u0001\u0000\u0000\u0000\u02a4\u02a2\u0001\u0000\u0000"+
		"\u0000\u02a4\u02a3\u0001\u0000\u0000\u0000\u02a5\u02a6\u0001\u0000\u0000"+
		"\u0000\u02a6\u02ab\u0005\u01a6\u0000\u0000\u02a7\u02a8\u0005\u0168\u0000"+
		"\u0000\u02a8\u02ac\u0005\u01af\u0000\u0000\u02a9\u02ac\u0003~?\u0000\u02aa"+
		"\u02ac\u0003\u00d4j\u0000\u02ab\u02a7\u0001\u0000\u0000\u0000\u02ab\u02a9"+
		"\u0001\u0000\u0000\u0000\u02ab\u02aa\u0001\u0000\u0000\u0000\u02aco\u0001"+
		"\u0000\u0000\u0000\u02ad\u02af\u0005\u01a7\u0000\u0000\u02ae\u02ad\u0001"+
		"\u0000\u0000\u0000\u02ae\u02af\u0001\u0000\u0000\u0000\u02af\u02b0\u0001"+
		"\u0000\u0000\u0000\u02b0\u02b1\u0005\u01ae\u0000\u0000\u02b1\u02b2\u0005"+
		"\u00e2\u0000\u0000\u02b2\u02b7\u0005\u000f\u0000\u0000\u02b3\u02b4\u0005"+
		"\u0168\u0000\u0000\u02b4\u02b8\u0005\u01af\u0000\u0000\u02b5\u02b8\u0003"+
		"~?\u0000\u02b6\u02b8\u0003\u00d4j\u0000\u02b7\u02b3\u0001\u0000\u0000"+
		"\u0000\u02b7\u02b5\u0001\u0000\u0000\u0000\u02b7\u02b6\u0001\u0000\u0000"+
		"\u0000\u02b8\u02b9\u0001\u0000\u0000\u0000\u02b9\u02be\u0005\u01a6\u0000"+
		"\u0000\u02ba\u02bb\u0005\u0168\u0000\u0000\u02bb\u02bf\u0005\u01af\u0000"+
		"\u0000\u02bc\u02bf\u0003~?\u0000\u02bd\u02bf\u0003\u00d4j\u0000\u02be"+
		"\u02ba\u0001\u0000\u0000\u0000\u02be\u02bc\u0001\u0000\u0000\u0000\u02be"+
		"\u02bd\u0001\u0000\u0000\u0000\u02bfq\u0001\u0000\u0000\u0000\u02c0\u02c8"+
		"\u0003t:\u0000\u02c1\u02c8\u0003v;\u0000\u02c2\u02c8\u0003x<\u0000\u02c3"+
		"\u02c8\u0003z=\u0000\u02c4\u02c8\u0003~?\u0000\u02c5\u02c8\u0003|>\u0000"+
		"\u02c6\u02c8\u0003n7\u0000\u02c7\u02c0\u0001\u0000\u0000\u0000\u02c7\u02c1"+
		"\u0001\u0000\u0000\u0000\u02c7\u02c2\u0001\u0000\u0000\u0000\u02c7\u02c3"+
		"\u0001\u0000\u0000\u0000\u02c7\u02c4\u0001\u0000\u0000\u0000\u02c7\u02c5"+
		"\u0001\u0000\u0000\u0000\u02c7\u02c6\u0001\u0000\u0000\u0000\u02c8s\u0001"+
		"\u0000\u0000\u0000\u02c9\u02ca\u0005\u0168\u0000\u0000\u02ca\u02cb\u0005"+
		"\u01af\u0000\u0000\u02cbu\u0001\u0000\u0000\u0000\u02cc\u02cd\u0005\u0169"+
		"\u0000\u0000\u02cd\u02d1\u0005\u0123\u0000\u0000\u02ce\u02cf\u0005\u0168"+
		"\u0000\u0000\u02cf\u02d2\u0005\u01af\u0000\u0000\u02d0\u02d2\u0003\u00d4"+
		"j\u0000\u02d1\u02ce\u0001\u0000\u0000\u0000\u02d1\u02d0\u0001\u0000\u0000"+
		"\u0000\u02d2\u02d3\u0001\u0000\u0000\u0000\u02d3\u02d9\u0005\u0198\u0000"+
		"\u0000\u02d4\u02d6\u0005\u0168\u0000\u0000\u02d5\u02d4\u0001\u0000\u0000"+
		"\u0000\u02d5\u02d6\u0001\u0000\u0000\u0000\u02d6\u02d7\u0001\u0000\u0000"+
		"\u0000\u02d7\u02da\u0005\u01af\u0000\u0000\u02d8\u02da\u0003\u00d4j\u0000"+
		"\u02d9\u02d5\u0001\u0000\u0000\u0000\u02d9\u02d8\u0001\u0000\u0000\u0000"+
		"\u02daw\u0001\u0000\u0000\u0000\u02db\u02dd\u0005\u0132\u0000\u0000\u02dc"+
		"\u02db\u0001\u0000\u0000\u0000\u02dc\u02dd\u0001\u0000\u0000\u0000\u02dd"+
		"\u02de\u0001\u0000\u0000\u0000\u02de\u02df\u0005\u016b\u0000\u0000\u02df"+
		"y\u0001\u0000\u0000\u0000\u02e0\u02e1\u0005\u016a\u0000\u0000\u02e1{\u0001"+
		"\u0000\u0000\u0000\u02e2\u02e3\u0003\u00e4r\u0000\u02e3\u02e4\u0005\u0168"+
		"\u0000\u0000\u02e4}\u0001\u0000\u0000\u0000\u02e5\u02e6\u0005\u0143\u0000"+
		"\u0000\u02e6\u02e8\u0005\u01a4\u0000\u0000\u02e7\u02e5\u0001\u0000\u0000"+
		"\u0000\u02e7\u02e8\u0001\u0000\u0000\u0000\u02e8\u02ea\u0001\u0000\u0000"+
		"\u0000\u02e9\u02eb\u0005\u0124\u0000\u0000\u02ea\u02e9\u0001\u0000\u0000"+
		"\u0000\u02ea\u02eb\u0001\u0000\u0000\u0000\u02eb\u02ed\u0001\u0000\u0000"+
		"\u0000\u02ec\u02ee\u0003\u00e4r\u0000\u02ed\u02ec\u0001\u0000\u0000\u0000"+
		"\u02ed\u02ee\u0001\u0000\u0000\u0000\u02ee\u02ef\u0001\u0000\u0000\u0000"+
		"\u02ef\u02f0\u0003\u00ecv\u0000\u02f0\u02f4\u0005\u01a7\u0000\u0000\u02f1"+
		"\u02f2\u0005\u0168\u0000\u0000\u02f2\u02f5\u0005\u01af\u0000\u0000\u02f3"+
		"\u02f5\u0003\u00d4j\u0000\u02f4\u02f1\u0001\u0000\u0000\u0000\u02f4\u02f3"+
		"\u0001\u0000\u0000\u0000\u02f5\u02fb\u0001\u0000\u0000\u0000\u02f6\u02f8"+
		"\u0005\u018f\u0000\u0000\u02f7\u02f6\u0001\u0000\u0000\u0000\u02f7\u02f8"+
		"\u0001\u0000\u0000\u0000\u02f8\u02f9\u0001\u0000\u0000\u0000\u02f9\u02fa"+
		"\u0005\u01a3\u0000\u0000\u02fa\u02fc\u0005\u00f0\u0000\u0000\u02fb\u02f7"+
		"\u0001\u0000\u0000\u0000\u02fb\u02fc\u0001\u0000\u0000\u0000\u02fc\u007f"+
		"\u0001\u0000\u0000\u0000\u02fd\u02fe\u0007\b\u0000\u0000\u02fe\u0081\u0001"+
		"\u0000\u0000\u0000\u02ff\u0301\u0003\u0080@\u0000\u0300\u02ff\u0001\u0000"+
		"\u0000\u0000\u0300\u0301\u0001\u0000\u0000\u0000\u0301\u0319\u0001\u0000"+
		"\u0000\u0000\u0302\u031a\u0003\u0088D\u0000\u0303\u031a\u0003|>\u0000"+
		"\u0304\u031a\u0003\u008aE\u0000\u0305\u031a\u0003\u008cF\u0000\u0306\u031a"+
		"\u0003\u008eG\u0000\u0307\u031a\u0003\u0090H\u0000\u0308\u031a\u0003\u0092"+
		"I\u0000\u0309\u031a\u0003v;\u0000\u030a\u031a\u0003\u0094J\u0000\u030b"+
		"\u031a\u0003\u0096K\u0000\u030c\u031a\u0003\u0098L\u0000\u030d\u031a\u0003"+
		"\u009aM\u0000\u030e\u031a\u0003\u009cN\u0000\u030f\u031a\u0003\u009eO"+
		"\u0000\u0310\u031a\u0003\u00a0P\u0000\u0311\u031a\u0003\u00a2Q\u0000\u0312"+
		"\u031a\u0003\u00a4R\u0000\u0313\u031a\u0003\u00a6S\u0000\u0314\u031a\u0003"+
		"\u00a8T\u0000\u0315\u031a\u0003\u00aaU\u0000\u0316\u031a\u0003\u00acV"+
		"\u0000\u0317\u031a\u0003p8\u0000\u0318\u031a\u0003\u0084B\u0000\u0319"+
		"\u0302\u0001\u0000\u0000\u0000\u0319\u0303\u0001\u0000\u0000\u0000\u0319"+
		"\u0304\u0001\u0000\u0000\u0000\u0319\u0305\u0001\u0000\u0000\u0000\u0319"+
		"\u0306\u0001\u0000\u0000\u0000\u0319\u0307\u0001\u0000\u0000\u0000\u0319"+
		"\u0308\u0001\u0000\u0000\u0000\u0319\u0309\u0001\u0000\u0000\u0000\u0319"+
		"\u030a\u0001\u0000\u0000\u0000\u0319\u030b\u0001\u0000\u0000\u0000\u0319"+
		"\u030c\u0001\u0000\u0000\u0000\u0319\u030d\u0001\u0000\u0000\u0000\u0319"+
		"\u030e\u0001\u0000\u0000\u0000\u0319\u030f\u0001\u0000\u0000\u0000\u0319"+
		"\u0310\u0001\u0000\u0000\u0000\u0319\u0311\u0001\u0000\u0000\u0000\u0319"+
		"\u0312\u0001\u0000\u0000\u0000\u0319\u0313\u0001\u0000\u0000\u0000\u0319"+
		"\u0314\u0001\u0000\u0000\u0000\u0319\u0315\u0001\u0000\u0000\u0000\u0319"+
		"\u0316\u0001\u0000\u0000\u0000\u0319\u0317\u0001\u0000\u0000\u0000\u0319"+
		"\u0318\u0001\u0000\u0000\u0000\u031a\u0083\u0001\u0000\u0000\u0000\u031b"+
		"\u031c\u0005\u0168\u0000\u0000\u031c\u031d\u0005\u01af\u0000\u0000\u031d"+
		"\u0085\u0001\u0000\u0000\u0000\u031e\u031f\u0005\u0169\u0000\u0000\u031f"+
		"\u0323\u0005\u0123\u0000\u0000\u0320\u0321\u0005\u0168\u0000\u0000\u0321"+
		"\u0324\u0005\u01af\u0000\u0000\u0322\u0324\u0003\u00d4j\u0000\u0323\u0320"+
		"\u0001\u0000\u0000\u0000\u0323\u0322\u0001\u0000\u0000\u0000\u0324\u0325"+
		"\u0001\u0000\u0000\u0000\u0325\u032b\u0005\u0198\u0000\u0000\u0326\u0328"+
		"\u0005\u0168\u0000\u0000\u0327\u0326\u0001\u0000\u0000\u0000\u0327\u0328"+
		"\u0001\u0000\u0000\u0000\u0328\u0329\u0001\u0000\u0000\u0000\u0329\u032c"+
		"\u0005\u01af\u0000\u0000\u032a\u032c\u0003\u00d4j\u0000\u032b\u0327\u0001"+
		"\u0000\u0000\u0000\u032b\u032a\u0001\u0000\u0000\u0000\u032c\u0087\u0001"+
		"\u0000\u0000\u0000\u032d\u0331\u0003\u00eew\u0000\u032e\u0332\u0003\u0084"+
		"B\u0000\u032f\u0332\u0003\u00d4j\u0000\u0330\u0332\u0003\u00b0X\u0000"+
		"\u0331\u032e\u0001\u0000\u0000\u0000\u0331\u032f\u0001\u0000\u0000\u0000"+
		"\u0331\u0330\u0001\u0000\u0000\u0000\u0331\u0332\u0001\u0000\u0000\u0000"+
		"\u0332\u0089\u0001\u0000\u0000\u0000\u0333\u0334\u0003\u00fa}\u0000\u0334"+
		"\u008b\u0001\u0000\u0000\u0000\u0335\u0336\u0005\u01af\u0000\u0000\u0336"+
		"\u0337\u0003|>\u0000\u0337\u008d\u0001\u0000\u0000\u0000\u0338\u0339\u0005"+
		"\u01af\u0000\u0000\u0339\u033a\u0003\u00f8|\u0000\u033a\u008f\u0001\u0000"+
		"\u0000\u0000\u033b\u033c\u0005\u01af\u0000\u0000\u033c\u033d\u0005\u00e9"+
		"\u0000\u0000\u033d\u033e\u0005\u0168\u0000\u0000\u033e\u0091\u0001\u0000"+
		"\u0000\u0000\u033f\u0340\u0005\u01af\u0000\u0000\u0340\u0341\u0005\u00f2"+
		"\u0000\u0000\u0341\u0342\u0005\u0168\u0000\u0000\u0342\u0093\u0001\u0000"+
		"\u0000\u0000\u0343\u0345\u0003\u00f2y\u0000\u0344\u0346\u0005\u0169\u0000"+
		"\u0000\u0345\u0344\u0001\u0000\u0000\u0000\u0345\u0346\u0001\u0000\u0000"+
		"\u0000\u0346\u0095\u0001\u0000\u0000\u0000\u0347\u034a\u0003\u00f0x\u0000"+
		"\u0348\u034b\u0003\u00aeW\u0000\u0349\u034b\u0003\u00b2Y\u0000\u034a\u0348"+
		"\u0001\u0000\u0000\u0000\u034a\u0349\u0001\u0000\u0000\u0000\u034a\u034b"+
		"\u0001\u0000\u0000\u0000\u034b\u0097\u0001\u0000\u0000\u0000\u034c\u034e"+
		"\u0005\u01af\u0000\u0000\u034d\u034f\u0005\u0124\u0000\u0000\u034e\u034d"+
		"\u0001\u0000\u0000\u0000\u034e\u034f\u0001\u0000\u0000\u0000\u034f\u0350"+
		"\u0001\u0000\u0000\u0000\u0350\u0352\u0003\u00ecv\u0000\u0351\u0353\u0003"+
		"\u00e4r\u0000\u0352\u0351\u0001\u0000\u0000\u0000\u0352\u0353\u0001\u0000"+
		"\u0000\u0000\u0353\u0354\u0001\u0000\u0000\u0000\u0354\u035a\u0005\u0168"+
		"\u0000\u0000\u0355\u0357\u0005\u018f\u0000\u0000\u0356\u0355\u0001\u0000"+
		"\u0000\u0000\u0356\u0357\u0001\u0000\u0000\u0000\u0357\u0358\u0001\u0000"+
		"\u0000\u0000\u0358\u0359\u0005\u01a3\u0000\u0000\u0359\u035b\u0005\u00f0"+
		"\u0000\u0000\u035a\u0356\u0001\u0000\u0000\u0000\u035a\u035b\u0001\u0000"+
		"\u0000\u0000\u035b\u0099\u0001\u0000\u0000\u0000\u035c\u035e\u0005\u01af"+
		"\u0000\u0000\u035d\u035c\u0001\u0000\u0000\u0000\u035d\u035e\u0001\u0000"+
		"\u0000\u0000\u035e\u035f\u0001\u0000\u0000\u0000\u035f\u0361\u0003\u00f4"+
		"z\u0000\u0360\u0362\u0007\t\u0000\u0000\u0361\u0360\u0001\u0000\u0000"+
		"\u0000\u0361\u0362\u0001\u0000\u0000\u0000\u0362\u009b\u0001\u0000\u0000"+
		"\u0000\u0363\u0364\u0003\u00f6{\u0000\u0364\u0365\u0005\u01a0\u0000\u0000"+
		"\u0365\u009d\u0001\u0000\u0000\u0000\u0366\u0367\u0005\u01af\u0000\u0000"+
		"\u0367\u0368\u0003\u00f6{\u0000\u0368\u0369\u0005\u01a0\u0000\u0000\u0369"+
		"\u009f\u0001\u0000\u0000\u0000\u036a\u036b\u0003\u00e6s\u0000\u036b\u036c"+
		"\u0005\u01a0\u0000\u0000\u036c\u00a1\u0001\u0000\u0000\u0000\u036d\u036e"+
		"\u0005\u01af\u0000\u0000\u036e\u036f\u0003\u00e6s\u0000\u036f\u0370\u0005"+
		"\u01a0\u0000\u0000\u0370\u00a3\u0001\u0000\u0000\u0000\u0371\u0372\u0003"+
		"\u00e8t\u0000\u0372\u0373\u0005\u01a0\u0000\u0000\u0373\u00a5\u0001\u0000"+
		"\u0000\u0000\u0374\u0375\u0005\u01af\u0000\u0000\u0375\u0376\u0003\u00e8"+
		"t\u0000\u0376\u0377\u0005\u01a0\u0000\u0000\u0377\u00a7\u0001\u0000\u0000"+
		"\u0000\u0378\u0379\u0003\u00eau\u0000\u0379\u037a\u0005\u01a0\u0000\u0000"+
		"\u037a\u00a9\u0001\u0000\u0000\u0000\u037b\u037c\u0005\u01af\u0000\u0000"+
		"\u037c\u037d\u0003\u00eau\u0000\u037d\u037e\u0005\u01a0\u0000\u0000\u037e"+
		"\u00ab\u0001\u0000\u0000\u0000\u037f\u0382\u0005\u01a2\u0000\u0000\u0380"+
		"\u0383\u0005\u01ac\u0000\u0000\u0381\u0383\u0003\u00dam\u0000\u0382\u0380"+
		"\u0001\u0000\u0000\u0000\u0382\u0381\u0001\u0000\u0000\u0000\u0383\u0389"+
		"\u0001\u0000\u0000\u0000\u0384\u038a\u0005\u00fd\u0000\u0000\u0385\u038a"+
		"\u0005\u0100\u0000\u0000\u0386\u0387\u0005\u00fd\u0000\u0000\u0387\u0388"+
		"\u0005\u0199\u0000\u0000\u0388\u038a\u0005\u0100\u0000\u0000\u0389\u0384"+
		"\u0001\u0000\u0000\u0000\u0389\u0385\u0001\u0000\u0000\u0000\u0389\u0386"+
		"\u0001\u0000\u0000\u0000\u038a\u038b\u0001\u0000\u0000\u0000\u038b\u0395"+
		"\u0005\u016b\u0000\u0000\u038c\u038d\u0003\u00deo\u0000\u038d\u038e\u0005"+
		"\u01af\u0000\u0000\u038e\u0393\u0001\u0000\u0000\u0000\u038f\u0394\u0005"+
		"\u0137\u0000\u0000\u0390\u0394\u0005\u0135\u0000\u0000\u0391\u0392\u0005"+
		"\u013b\u0000\u0000\u0392\u0394\u0005\u01af\u0000\u0000\u0393\u038f\u0001"+
		"\u0000\u0000\u0000\u0393\u0390\u0001\u0000\u0000\u0000\u0393\u0391\u0001"+
		"\u0000\u0000\u0000\u0393\u0394\u0001\u0000\u0000\u0000\u0394\u0396\u0001"+
		"\u0000\u0000\u0000\u0395\u038c\u0001\u0000\u0000\u0000\u0395\u0396\u0001"+
		"\u0000\u0000\u0000\u0396\u00ad\u0001\u0000\u0000\u0000\u0397\u039b\u0003"+
		"\u0084B\u0000\u0398\u039b\u0003\u0086C\u0000\u0399\u039b\u0003\u00d4j"+
		"\u0000\u039a\u0397\u0001\u0000\u0000\u0000\u039a\u0398\u0001\u0000\u0000"+
		"\u0000\u039a\u0399\u0001\u0000\u0000\u0000\u039b\u039c\u0001\u0000\u0000"+
		"\u0000\u039c\u039f\u0005\u0001\u0000\u0000\u039d\u03a0\u0003\u0084B\u0000"+
		"\u039e\u03a0\u0003\u00d4j\u0000\u039f\u039d\u0001\u0000\u0000\u0000\u039f"+
		"\u039e\u0001\u0000\u0000\u0000\u03a0\u03a1\u0001\u0000\u0000\u0000\u03a1"+
		"\u03a4\u0005\u01a7\u0000\u0000\u03a2\u03a5\u0003\u0084B\u0000\u03a3\u03a5"+
		"\u0003\u00d4j\u0000\u03a4\u03a2\u0001\u0000\u0000\u0000\u03a4\u03a3\u0001"+
		"\u0000\u0000\u0000\u03a5\u00af\u0001\u0000\u0000\u0000\u03a6\u03aa\u0003"+
		"\u0084B\u0000\u03a7\u03aa\u0003\u0086C\u0000\u03a8\u03aa\u0003\u00d4j"+
		"\u0000\u03a9\u03a6\u0001\u0000\u0000\u0000\u03a9\u03a7\u0001\u0000\u0000"+
		"\u0000\u03a9\u03a8\u0001\u0000\u0000\u0000\u03aa\u03ab\u0001\u0000\u0000"+
		"\u0000\u03ab\u03ae\u0005\u0002\u0000\u0000\u03ac\u03af\u0003\u0086C\u0000"+
		"\u03ad\u03af\u0003\u00d4j\u0000\u03ae\u03ac\u0001\u0000\u0000\u0000\u03ae"+
		"\u03ad\u0001\u0000\u0000\u0000\u03af\u00b1\u0001\u0000\u0000\u0000\u03b0"+
		"\u03b4\u0003\u0084B\u0000\u03b1\u03b4\u0003\u0086C\u0000\u03b2\u03b4\u0003"+
		"\u00d4j\u0000\u03b3\u03b0\u0001\u0000\u0000\u0000\u03b3\u03b1\u0001\u0000"+
		"\u0000\u0000\u03b3\u03b2\u0001\u0000\u0000\u0000\u03b4\u00b3\u0001\u0000"+
		"\u0000\u0000\u03b5\u03b7\u0003\u00b6[\u0000\u03b6\u03b8\u0003\u00d8l\u0000"+
		"\u03b7\u03b6\u0001\u0000\u0000\u0000\u03b7\u03b8\u0001\u0000\u0000\u0000"+
		"\u03b8\u03b9\u0001\u0000\u0000\u0000\u03b9\u03ba\u0003\u00d2i\u0000\u03ba"+
		"\u03bb\u0005\u01a2\u0000\u0000\u03bb\u03bc\u0003\u00c8d\u0000\u03bc\u00b5"+
		"\u0001\u0000\u0000\u0000\u03bd\u03cc\u0005\u0168\u0000\u0000\u03be\u03cc"+
		"\u0005\u0169\u0000\u0000\u03bf\u03cc\u0005\u016b\u0000\u0000\u03c0\u03cc"+
		"\u0003\u00b8\\\u0000\u03c1\u03cc\u0003\u00ba]\u0000\u03c2\u03cc\u0003"+
		"\u00bc^\u0000\u03c3\u03cc\u0003\u00be_\u0000\u03c4\u03cc\u0003\u00c0`"+
		"\u0000\u03c5\u03c6\u0003\u00c2a\u0000\u03c6\u03c7\u0003\u00c4b\u0000\u03c7"+
		"\u03cc\u0001\u0000\u0000\u0000\u03c8\u03cc\u0003\u00c6c\u0000\u03c9\u03cc"+
		"\u0003\u00ceg\u0000\u03ca\u03cc\u0003\u00d0h\u0000\u03cb\u03bd\u0001\u0000"+
		"\u0000\u0000\u03cb\u03be\u0001\u0000\u0000\u0000\u03cb\u03bf\u0001\u0000"+
		"\u0000\u0000\u03cb\u03c0\u0001\u0000\u0000\u0000\u03cb\u03c1\u0001\u0000"+
		"\u0000\u0000\u03cb\u03c2\u0001\u0000\u0000\u0000\u03cb\u03c3\u0001\u0000"+
		"\u0000\u0000\u03cb\u03c4\u0001\u0000\u0000\u0000\u03cb\u03c5\u0001\u0000"+
		"\u0000\u0000\u03cb\u03c8\u0001\u0000\u0000\u0000\u03cb\u03c9\u0001\u0000"+
		"\u0000\u0000\u03cb\u03ca\u0001\u0000\u0000\u0000\u03cc\u00b7\u0001\u0000"+
		"\u0000\u0000\u03cd\u03ce\u0003\u00e4r\u0000\u03ce\u03cf\u0005\u0168\u0000"+
		"\u0000\u03cf\u00b9\u0001\u0000\u0000\u0000\u03d0\u03d3\u0003\u00eew\u0000"+
		"\u03d1\u03d3\u0003\u00f0x\u0000\u03d2\u03d0\u0001\u0000\u0000\u0000\u03d2"+
		"\u03d1\u0001\u0000\u0000\u0000\u03d3\u03d5\u0001\u0000\u0000\u0000\u03d4"+
		"\u03d6\u0003b1\u0000\u03d5\u03d4\u0001\u0000\u0000\u0000\u03d5\u03d6\u0001"+
		"\u0000\u0000\u0000\u03d6\u03d7\u0001\u0000\u0000\u0000\u03d7\u03d8\u0005"+
		"\u0168\u0000\u0000\u03d8\u00bb\u0001\u0000\u0000\u0000\u03d9\u03dc\u0003"+
		"\u00eew\u0000\u03da\u03dc\u0003\u00f0x\u0000\u03db\u03d9\u0001\u0000\u0000"+
		"\u0000\u03db\u03da\u0001\u0000\u0000\u0000\u03dc\u03de\u0001\u0000\u0000"+
		"\u0000\u03dd\u03df\u0003b1\u0000\u03de\u03dd\u0001\u0000\u0000\u0000\u03de"+
		"\u03df\u0001\u0000\u0000\u0000\u03df\u03e0\u0001\u0000\u0000\u0000\u03e0"+
		"\u03e1\u0003\u00e4r\u0000\u03e1\u03e2\u0005\u0168\u0000\u0000\u03e2\u00bd"+
		"\u0001\u0000\u0000\u0000\u03e3\u03e6\u0003\u00f0x\u0000\u03e4\u03e6\u0003"+
		"\u00f2y\u0000\u03e5\u03e3\u0001\u0000\u0000\u0000\u03e5\u03e4\u0001\u0000"+
		"\u0000\u0000\u03e6\u03e7\u0001\u0000\u0000\u0000\u03e7\u03e8\u0005\u0169"+
		"\u0000\u0000\u03e8\u00bf\u0001\u0000\u0000\u0000\u03e9\u03ec\u0003\u00f4"+
		"z\u0000\u03ea\u03ec\u0003\u00f6{\u0000\u03eb\u03e9\u0001\u0000\u0000\u0000"+
		"\u03eb\u03ea\u0001\u0000\u0000\u0000\u03ec\u03ed\u0001\u0000\u0000\u0000"+
		"\u03ed\u03ee\u0005\u01a0\u0000\u0000\u03ee\u00c1\u0001\u0000\u0000\u0000"+
		"\u03ef\u03f0\u0003\u00e6s\u0000\u03f0\u03f1\u0005\u01a0\u0000\u0000\u03f1"+
		"\u00c3\u0001\u0000\u0000\u0000\u03f2\u03f3\u0003\u00e8t\u0000\u03f3\u03f4"+
		"\u0005\u01a0\u0000\u0000\u03f4\u00c5\u0001\u0000\u0000\u0000\u03f5\u03f7"+
		"\u0005\u0124\u0000\u0000\u03f6\u03f5\u0001\u0000\u0000\u0000\u03f6\u03f7"+
		"\u0001\u0000\u0000\u0000\u03f7\u03f9\u0001\u0000\u0000\u0000\u03f8\u03fa"+
		"\u0003\u00e4r\u0000\u03f9\u03f8\u0001\u0000\u0000\u0000\u03f9\u03fa\u0001"+
		"\u0000\u0000\u0000\u03fa\u03fb\u0001\u0000\u0000\u0000\u03fb\u03fc\u0003"+
		"\u00ecv\u0000\u03fc\u0400\u0005\u01a7\u0000\u0000\u03fd\u03fe\u0005\u0168"+
		"\u0000\u0000\u03fe\u0401\u0005\u01af\u0000\u0000\u03ff\u0401\u0003\u00d4"+
		"j\u0000\u0400\u03fd\u0001\u0000\u0000\u0000\u0400\u03ff\u0001\u0000\u0000"+
		"\u0000\u0401\u0407\u0001\u0000\u0000\u0000\u0402\u0404\u0005\u018f\u0000"+
		"\u0000\u0403\u0402\u0001\u0000\u0000\u0000\u0403\u0404\u0001\u0000\u0000"+
		"\u0000\u0404\u0405\u0001\u0000\u0000\u0000\u0405\u0406\u0005\u01a3\u0000"+
		"\u0000\u0406\u0408\u0005\u00f0\u0000\u0000\u0407\u0403\u0001\u0000\u0000"+
		"\u0000\u0407\u0408\u0001\u0000\u0000\u0000\u0408\u00c7\u0001\u0000\u0000"+
		"\u0000\u0409\u0414\u0005\u016a\u0000\u0000\u040a\u0414\u0003\u00cae\u0000"+
		"\u040b\u0414\u0003\u00d4j\u0000\u040c\u0414\u0003\u00ccf\u0000\u040d\u040e"+
		"\u0005\u0132\u0000\u0000\u040e\u0414\u0005\u016b\u0000\u0000\u040f\u0410"+
		"\u0005\u010c\u0000\u0000\u0410\u0414\u0005\u01a0\u0000\u0000\u0411\u0412"+
		"\u0005\u010f\u0000\u0000\u0412\u0414\u0005\u01a0\u0000\u0000\u0413\u0409"+
		"\u0001\u0000\u0000\u0000\u0413\u040a\u0001\u0000\u0000\u0000\u0413\u040b"+
		"\u0001\u0000\u0000\u0000\u0413\u040c\u0001\u0000\u0000\u0000\u0413\u040d"+
		"\u0001\u0000\u0000\u0000\u0413\u040f\u0001\u0000\u0000\u0000\u0413\u0411"+
		"\u0001\u0000\u0000\u0000\u0414\u00c9\u0001\u0000\u0000\u0000\u0415\u0418"+
		"\u0005\u01a2\u0000\u0000\u0416\u0419\u0005\u01ac\u0000\u0000\u0417\u0419"+
		"\u0003\u00dam\u0000\u0418\u0416\u0001\u0000\u0000\u0000\u0418\u0417\u0001"+
		"\u0000\u0000\u0000\u0419\u041f\u0001\u0000\u0000\u0000\u041a\u0420\u0005"+
		"\u00fd\u0000\u0000\u041b\u0420\u0005\u0100\u0000\u0000\u041c\u041d\u0005"+
		"\u00fd\u0000\u0000\u041d\u041e\u0005\u0199\u0000\u0000\u041e\u0420\u0005"+
		"\u0100\u0000\u0000\u041f\u041a\u0001\u0000\u0000\u0000\u041f\u041b\u0001"+
		"\u0000\u0000\u0000\u041f\u041c\u0001\u0000\u0000\u0000\u0420\u0421\u0001"+
		"\u0000\u0000\u0000\u0421\u042b\u0005\u016b\u0000\u0000\u0422\u0423\u0003"+
		"\u00deo\u0000\u0423\u0424\u0005\u01af\u0000\u0000\u0424\u0429\u0001\u0000"+
		"\u0000\u0000\u0425\u042a\u0005\u0137\u0000\u0000\u0426\u042a\u0005\u0135"+
		"\u0000\u0000\u0427\u0428\u0005\u013b\u0000\u0000\u0428\u042a\u0005\u01af"+
		"\u0000\u0000\u0429\u0425\u0001\u0000\u0000\u0000\u0429\u0426\u0001\u0000"+
		"\u0000\u0000\u0429\u0427\u0001\u0000\u0000\u0000\u0429\u042a\u0001\u0000"+
		"\u0000\u0000\u042a\u042c\u0001\u0000\u0000\u0000\u042b\u0422\u0001\u0000"+
		"\u0000\u0000\u042b\u042c\u0001\u0000\u0000\u0000\u042c\u00cb\u0001\u0000"+
		"\u0000\u0000\u042d\u0430\u0005\u01ae\u0000\u0000\u042e\u0430\u0003\u00da"+
		"m\u0000\u042f\u042d\u0001\u0000\u0000\u0000\u042f\u042e\u0001\u0000\u0000"+
		"\u0000\u0430\u0436\u0001\u0000\u0000\u0000\u0431\u0437\u0005\u00fd\u0000"+
		"\u0000\u0432\u0437\u0005\u0100\u0000\u0000\u0433\u0434\u0005\u00fd\u0000"+
		"\u0000\u0434\u0435\u0005\u0199\u0000\u0000\u0435\u0437\u0005\u0100\u0000"+
		"\u0000\u0436\u0431\u0001\u0000\u0000\u0000\u0436\u0432\u0001\u0000\u0000"+
		"\u0000\u0436\u0433\u0001\u0000\u0000\u0000\u0437\u0438\u0001\u0000\u0000"+
		"\u0000\u0438\u0439\u0005\u016b\u0000\u0000\u0439\u043a\u0003\u00deo\u0000"+
		"\u043a\u043b\u0005\u01af\u0000\u0000\u043b\u043f\u0005\u019c\u0000\u0000"+
		"\u043c\u043d\u0005\u0168\u0000\u0000\u043d\u0440\u0005\u01af\u0000\u0000"+
		"\u043e\u0440\u0003\u00d4j\u0000\u043f\u043c\u0001\u0000\u0000\u0000\u043f"+
		"\u043e\u0001\u0000\u0000\u0000\u0440\u00cd\u0001\u0000\u0000\u0000\u0441"+
		"\u0442\u0005\u0168\u0000\u0000\u0442\u0445\u0005\u01a5\u0000\u0000\u0443"+
		"\u0446\u0003\u00d4j\u0000\u0444\u0446\u0003v;\u0000\u0445\u0443\u0001"+
		"\u0000\u0000\u0000\u0445\u0444\u0001\u0000\u0000\u0000\u0446\u00cf\u0001"+
		"\u0000\u0000\u0000\u0447\u0448\u0005\u0169\u0000\u0000\u0448\u044d\u0005"+
		"\u01a5\u0000\u0000\u0449\u044e\u0003t:\u0000\u044a\u044e\u0003~?\u0000"+
		"\u044b\u044e\u0003|>\u0000\u044c\u044e\u0003\u00d4j\u0000\u044d\u0449"+
		"\u0001\u0000\u0000\u0000\u044d\u044a\u0001\u0000\u0000\u0000\u044d\u044b"+
		"\u0001\u0000\u0000\u0000\u044d\u044c\u0001\u0000\u0000\u0000\u044e\u00d1"+
		"\u0001\u0000\u0000\u0000\u044f\u0450\u0005\u0120\u0000\u0000\u0450\u0451"+
		"\u0003\u00d4j\u0000\u0451\u0452\u0005\u01a7\u0000\u0000\u0452\u0457\u0001"+
		"\u0000\u0000\u0000\u0453\u0454\u0003\u00d6k\u0000\u0454\u0455\u0003\u00d4"+
		"j\u0000\u0455\u0457\u0001\u0000\u0000\u0000\u0456\u044f\u0001\u0000\u0000"+
		"\u0000\u0456\u0453\u0001\u0000\u0000\u0000\u0457\u00d3\u0001\u0000\u0000"+
		"\u0000\u0458\u045b\u0005\u01b3\u0000\u0000\u0459\u045c\u0003\u00e0p\u0000"+
		"\u045a\u045c\u0005\u01af\u0000\u0000\u045b\u0459\u0001\u0000\u0000\u0000"+
		"\u045b\u045a\u0001\u0000\u0000\u0000\u045b\u045c\u0001\u0000\u0000\u0000"+
		"\u045c\u00d5\u0001\u0000\u0000\u0000\u045d\u045e\u0005\u01ab\u0000\u0000"+
		"\u045e\u0465\u0005\u019d\u0000\u0000\u045f\u0460\u0005\u01ab\u0000\u0000"+
		"\u0460\u0462\u0005\u019e\u0000\u0000\u0461\u0463\u0005\u019d\u0000\u0000"+
		"\u0462\u0461\u0001\u0000\u0000\u0000\u0462\u0463\u0001\u0000\u0000\u0000"+
		"\u0463\u0465\u0001\u0000\u0000\u0000\u0464\u045d\u0001\u0000\u0000\u0000"+
		"\u0464\u045f\u0001\u0000\u0000\u0000\u0465\u00d7\u0001\u0000\u0000\u0000"+
		"\u0466\u0467\u0007\n\u0000\u0000\u0467\u00d9\u0001\u0000\u0000\u0000\u0468"+
		"\u0469\u0007\u000b\u0000\u0000\u0469\u00db\u0001\u0000\u0000\u0000\u046a"+
		"\u046b\u0005\u0128\u0000\u0000\u046b\u00dd\u0001\u0000\u0000\u0000\u046c"+
		"\u046d\u0005\u01a4\u0000\u0000\u046d\u046e\u0005\u0144\u0000\u0000\u046e"+
		"\u00df\u0001\u0000\u0000\u0000\u046f\u0470\u0007\f\u0000\u0000\u0470\u00e1"+
		"\u0001\u0000\u0000\u0000\u0471\u0472\u0007\r\u0000\u0000\u0472\u00e3\u0001"+
		"\u0000\u0000\u0000\u0473\u0474\u0007\u000e\u0000\u0000\u0474\u00e5\u0001"+
		"\u0000\u0000\u0000\u0475\u0476\u0007\u000f\u0000\u0000\u0476\u00e7\u0001"+
		"\u0000\u0000\u0000\u0477\u0478\u0007\u0010\u0000\u0000\u0478\u00e9\u0001"+
		"\u0000\u0000\u0000\u0479\u047c\u0005\u01b2\u0000\u0000\u047a\u047c\u0003"+
		"\u0110\u0088\u0000\u047b\u0479\u0001\u0000\u0000\u0000\u047b\u047a\u0001"+
		"\u0000\u0000\u0000\u047c\u00eb\u0001\u0000\u0000\u0000\u047d\u047e\u0007"+
		"\u0011\u0000\u0000\u047e\u00ed\u0001\u0000\u0000\u0000\u047f\u049c\u0005"+
		"\u00e8\u0000\u0000\u0480\u049c\u0005\u00ea\u0000\u0000\u0481\u049c\u0005"+
		"\u0101\u0000\u0000\u0482\u049c\u0005\u0102\u0000\u0000\u0483\u0485\u0005"+
		"\u00eb\u0000\u0000\u0484\u0486\u0005\u01a7\u0000\u0000\u0485\u0484\u0001"+
		"\u0000\u0000\u0000\u0485\u0486\u0001\u0000\u0000\u0000\u0486\u049c\u0001"+
		"\u0000\u0000\u0000\u0487\u049c\u0005\u0104\u0000\u0000\u0488\u049c\u0005"+
		"\u00ec\u0000\u0000\u0489\u048b\u0005\u00ed\u0000\u0000\u048a\u048c\u0005"+
		"\u01a4\u0000\u0000\u048b\u048a\u0001\u0000\u0000\u0000\u048b\u048c\u0001"+
		"\u0000\u0000\u0000\u048c\u049c\u0001\u0000\u0000\u0000\u048d\u049c\u0005"+
		"\u00ee\u0000\u0000\u048e\u0490\u0005\u00ef\u0000\u0000\u048f\u0491\u0005"+
		"\u01a7\u0000\u0000\u0490\u048f\u0001\u0000\u0000\u0000\u0490\u0491\u0001"+
		"\u0000\u0000\u0000\u0491\u049c\u0001\u0000\u0000\u0000\u0492\u049c\u0005"+
		"\u0105\u0000\u0000\u0493\u049c\u0005\u00f1\u0000\u0000\u0494\u049c\u0005"+
		"\u00f2\u0000\u0000\u0495\u049c\u0005\u00f4\u0000\u0000\u0496\u0498\u0005"+
		"\u00f6\u0000\u0000\u0497\u0499\u0005\u01a7\u0000\u0000\u0498\u0497\u0001"+
		"\u0000\u0000\u0000\u0498\u0499\u0001\u0000\u0000\u0000\u0499\u049c\u0001"+
		"\u0000\u0000\u0000\u049a\u049c\u0005\u00f7\u0000\u0000\u049b\u047f\u0001"+
		"\u0000\u0000\u0000\u049b\u0480\u0001\u0000\u0000\u0000\u049b\u0481\u0001"+
		"\u0000\u0000\u0000\u049b\u0482\u0001\u0000\u0000\u0000\u049b\u0483\u0001"+
		"\u0000\u0000\u0000\u049b\u0487\u0001\u0000\u0000\u0000\u049b\u0488\u0001"+
		"\u0000\u0000\u0000\u049b\u0489\u0001\u0000\u0000\u0000\u049b\u048d\u0001"+
		"\u0000\u0000\u0000\u049b\u048e\u0001\u0000\u0000\u0000\u049b\u0492\u0001"+
		"\u0000\u0000\u0000\u049b\u0493\u0001\u0000\u0000\u0000\u049b\u0494\u0001"+
		"\u0000\u0000\u0000\u049b\u0495\u0001\u0000\u0000\u0000\u049b\u0496\u0001"+
		"\u0000\u0000\u0000\u049b\u049a\u0001\u0000\u0000\u0000\u049c\u00ef\u0001"+
		"\u0000\u0000\u0000\u049d\u049e\u0007\u0012\u0000\u0000\u049e\u00f1\u0001"+
		"\u0000\u0000\u0000\u049f\u04a0\u0007\u0013\u0000\u0000\u04a0\u00f3\u0001"+
		"\u0000\u0000\u0000\u04a1\u04a2\u0007\u0014\u0000\u0000\u04a2\u00f5\u0001"+
		"\u0000\u0000\u0000\u04a3\u04a4\u0007\u0015\u0000\u0000\u04a4\u00f7\u0001"+
		"\u0000\u0000\u0000\u04a5\u04a6\u0007\u0016\u0000\u0000\u04a6\u00f9\u0001"+
		"\u0000\u0000\u0000\u04a7\u04a8\u0007\u0017\u0000\u0000\u04a8\u00fb\u0001"+
		"\u0000\u0000\u0000\u04a9\u04aa\u0007\u000b\u0000\u0000\u04aa\u00fd\u0001"+
		"\u0000\u0000\u0000\u04ab\u04ac\u0005\u01af\u0000\u0000\u04ac\u04ad\u0005"+
		"\u00e9\u0000\u0000\u04ad\u00ff\u0001\u0000\u0000\u0000\u04ae\u04af\u0007"+
		"\u0018\u0000\u0000\u04af\u0101\u0001\u0000\u0000\u0000\u04b0\u04b1\u0003"+
		"\u0104\u0082\u0000\u04b1\u04b3\u0005\u018f\u0000\u0000\u04b2\u04b4\u0005"+
		"\u019f\u0000\u0000\u04b3\u04b2\u0001\u0000\u0000\u0000\u04b3\u04b4\u0001"+
		"\u0000\u0000\u0000\u04b4\u0103\u0001\u0000\u0000\u0000\u04b5\u04b7\u0005"+
		"\u0176\u0000\u0000\u04b6\u04b8\u0005\u019f\u0000\u0000\u04b7\u04b6\u0001"+
		"\u0000\u0000\u0000\u04b7\u04b8\u0001\u0000\u0000\u0000\u04b8\u04be\u0001"+
		"\u0000\u0000\u0000\u04b9\u04bb\u0005\u019c\u0000\u0000\u04ba\u04bc\u0005"+
		"\u019f\u0000\u0000\u04bb\u04ba\u0001\u0000\u0000\u0000\u04bb\u04bc\u0001"+
		"\u0000\u0000\u0000\u04bc\u04be\u0001\u0000\u0000\u0000\u04bd\u04b5\u0001"+
		"\u0000\u0000\u0000\u04bd\u04b9\u0001\u0000\u0000\u0000\u04be\u0105\u0001"+
		"\u0000\u0000\u0000\u04bf\u04c0\u0005\u0175\u0000\u0000\u04c0\u04c2\u0005"+
		"\u018f\u0000\u0000\u04c1\u04c3\u0005\u019f\u0000\u0000\u04c2\u04c1\u0001"+
		"\u0000\u0000\u0000\u04c2\u04c3\u0001\u0000\u0000\u0000\u04c3\u04c5\u0001"+
		"\u0000\u0000\u0000\u04c4\u04c6\u0003\u010a\u0085\u0000\u04c5\u04c4\u0001"+
		"\u0000\u0000\u0000\u04c5\u04c6\u0001\u0000\u0000\u0000\u04c6\u0107\u0001"+
		"\u0000\u0000\u0000\u04c7\u04c9\u0005\u0175\u0000\u0000\u04c8\u04ca\u0005"+
		"\u019f\u0000\u0000\u04c9\u04c8\u0001\u0000\u0000\u0000\u04c9\u04ca\u0001"+
		"\u0000\u0000\u0000\u04ca\u04d4\u0001\u0000\u0000\u0000\u04cb\u04cd\u0005"+
		"\u019f\u0000\u0000\u04cc\u04cb\u0001\u0000\u0000\u0000\u04cc\u04cd\u0001"+
		"\u0000\u0000\u0000\u04cd\u04d4\u0001\u0000\u0000\u0000\u04ce\u04d0\u0005"+
		"\u0175\u0000\u0000\u04cf\u04d1\u0005\u019f\u0000\u0000\u04d0\u04cf\u0001"+
		"\u0000\u0000\u0000\u04d0\u04d1\u0001\u0000\u0000\u0000\u04d1\u04d2\u0001"+
		"\u0000\u0000\u0000\u04d2\u04d4\u0003\u010a\u0085\u0000\u04d3\u04c7\u0001"+
		"\u0000\u0000\u0000\u04d3\u04cc\u0001\u0000\u0000\u0000\u04d3\u04ce\u0001"+
		"\u0000\u0000\u0000\u04d4\u0109\u0001\u0000\u0000\u0000\u04d5\u04d7\u0005"+
		"\u01ae\u0000\u0000\u04d6\u04d5\u0001\u0000\u0000\u0000\u04d6\u04d7\u0001"+
		"\u0000\u0000\u0000\u04d7\u04d8\u0001\u0000\u0000\u0000\u04d8\u04da\u0007"+
		"\u0019\u0000\u0000\u04d9\u04db\u0005\u019d\u0000\u0000\u04da\u04d9\u0001"+
		"\u0000\u0000\u0000\u04da\u04db\u0001\u0000\u0000\u0000\u04db\u010b\u0001"+
		"\u0000\u0000\u0000\u04dc\u04de\u0007\u001a\u0000\u0000\u04dd\u04dc\u0001"+
		"\u0000\u0000\u0000\u04de\u04df\u0001\u0000\u0000\u0000\u04df\u04dd\u0001"+
		"\u0000\u0000\u0000\u04df\u04e0\u0001\u0000\u0000\u0000\u04e0\u010d\u0001"+
		"\u0000\u0000\u0000\u04e1\u04e3\u0007\u001b\u0000\u0000\u04e2\u04e1\u0001"+
		"\u0000\u0000\u0000\u04e3\u04e4\u0001\u0000\u0000\u0000\u04e4\u04e2\u0001"+
		"\u0000\u0000\u0000\u04e4\u04e5\u0001\u0000\u0000\u0000\u04e5\u010f\u0001"+
		"\u0000\u0000\u0000\u04e6\u0500\u0005\u0005\u0000\u0000\u04e7\u0501\u0003"+
		"\u010c\u0086\u0000\u04e8\u0501\u0005\u0180\u0000\u0000\u04e9\u0501\u0005"+
		"\u0181\u0000\u0000\u04ea\u0501\u0005\u017f\u0000\u0000\u04eb\u0501\u0005"+
		"\u0006\u0000\u0000\u04ec\u0501\u0005\u0007\u0000\u0000\u04ed\u0501\u0005"+
		"\b\u0000\u0000\u04ee\u0501\u0005\u0183\u0000\u0000\u04ef\u0501\u0005\t"+
		"\u0000\u0000\u04f0\u0501\u0005\u017c\u0000\u0000\u04f1\u0501\u0005\u017b"+
		"\u0000\u0000\u04f2\u0501\u0005\u017d\u0000\u0000\u04f3\u0501\u0005\u0193"+
		"\u0000\u0000\u04f4\u0501\u0005\n\u0000\u0000\u04f5\u0501\u0005\u0005\u0000"+
		"\u0000\u04f6\u0501\u0005\u000b\u0000\u0000\u04f7\u0501\u0005\u01af\u0000"+
		"\u0000\u04f8\u0501\u0005\u0185\u0000\u0000\u04f9\u0501\u0005\u0186\u0000"+
		"\u0000\u04fa\u0501\u0005\u0187\u0000\u0000\u04fb\u0501\u0005\u0188\u0000"+
		"\u0000\u04fc\u0501\u0005\u018a\u0000\u0000\u04fd\u0501\u0005\u017e\u0000"+
		"\u0000\u04fe\u0501\u0005\f\u0000\u0000\u04ff\u0501\u0005\r\u0000\u0000"+
		"\u0500\u04e7\u0001\u0000\u0000\u0000\u0500\u04e8\u0001\u0000\u0000\u0000"+
		"\u0500\u04e9\u0001\u0000\u0000\u0000\u0500\u04ea\u0001\u0000\u0000\u0000"+
		"\u0500\u04eb\u0001\u0000\u0000\u0000\u0500\u04ec\u0001\u0000\u0000\u0000"+
		"\u0500\u04ed\u0001\u0000\u0000\u0000\u0500\u04ee\u0001\u0000\u0000\u0000"+
		"\u0500\u04ef\u0001\u0000\u0000\u0000\u0500\u04f0\u0001\u0000\u0000\u0000"+
		"\u0500\u04f1\u0001\u0000\u0000\u0000\u0500\u04f2\u0001\u0000\u0000\u0000"+
		"\u0500\u04f3\u0001\u0000\u0000\u0000\u0500\u04f4\u0001\u0000\u0000\u0000"+
		"\u0500\u04f5\u0001\u0000\u0000\u0000\u0500\u04f6\u0001\u0000\u0000\u0000"+
		"\u0500\u04f7\u0001\u0000\u0000\u0000\u0500\u04f8\u0001\u0000\u0000\u0000"+
		"\u0500\u04f9\u0001\u0000\u0000\u0000\u0500\u04fa\u0001\u0000\u0000\u0000"+
		"\u0500\u04fb\u0001\u0000\u0000\u0000\u0500\u04fc\u0001\u0000\u0000\u0000"+
		"\u0500\u04fd\u0001\u0000\u0000\u0000\u0500\u04fe\u0001\u0000\u0000\u0000"+
		"\u0500\u04ff\u0001\u0000\u0000\u0000\u0501\u0502\u0001\u0000\u0000\u0000"+
		"\u0502\u0500\u0001\u0000\u0000\u0000\u0502\u0503\u0001\u0000\u0000\u0000"+
		"\u0503\u0504\u0001\u0000\u0000\u0000\u0504\u0505\u0005\u000b\u0000\u0000"+
		"\u0505\u0111\u0001\u0000\u0000\u0000\u0094\u0117\u011c\u0123\u0127\u0135"+
		"\u0139\u0144\u014b\u0155\u0162\u016f\u0183\u0185\u018f\u0194\u0198\u01a2"+
		"\u01ab\u01b4\u01b9\u01bd\u01c4\u01c8\u01cd\u01d2\u01d7\u01dc\u01e1\u01e5"+
		"\u01ea\u01ee\u01f1\u01f3\u01fc\u0201\u0206\u020a\u021c\u0228\u0231\u0235"+
		"\u023b\u0243\u0247\u024b\u024f\u0253\u0257\u025a\u0260\u0264\u0267\u026d"+
		"\u0275\u027e\u0286\u0290\u0298\u029c\u02a4\u02ab\u02ae\u02b7\u02be\u02c7"+
		"\u02d1\u02d5\u02d9\u02dc\u02e7\u02ea\u02ed\u02f4\u02f7\u02fb\u0300\u0319"+
		"\u0323\u0327\u032b\u0331\u0345\u034a\u034e\u0352\u0356\u035a\u035d\u0361"+
		"\u0382\u0389\u0393\u0395\u039a\u039f\u03a4\u03a9\u03ae\u03b3\u03b7\u03cb"+
		"\u03d2\u03d5\u03db\u03de\u03e5\u03eb\u03f6\u03f9\u0400\u0403\u0407\u0413"+
		"\u0418\u041f\u0429\u042b\u042f\u0436\u043f\u0445\u044d\u0456\u045b\u0462"+
		"\u0464\u047b\u0485\u048b\u0490\u0498\u049b\u04b3\u04b7\u04bb\u04bd\u04c2"+
		"\u04c5\u04c9\u04cc\u04d0\u04d3\u04d6\u04da\u04df\u04e4\u0500\u0502";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}