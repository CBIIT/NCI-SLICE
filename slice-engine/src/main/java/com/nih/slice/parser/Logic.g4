    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

grammar Logic;

/*
* Rules
*/

start : instructions ;
instructions : stmt* ;
//instructions : NEWLINE 
//	| stmt* ;

stmt: simple_stmt | compound_stmt ;
simple_stmt: small_stmt (SEMIC small_stmt)* (SEMIC)? ;
small_stmt: func_stmt | expr_stmt | kill_stmt  | rating_stmt | return_stmt | exit_stmt |  import_stmt 
          | designate_stmt | permute_stmt | mech_stmt ;

//TODO add and manage parenthesis calculation
//expr_stmt: testlist (augassign2 | (augassign testlist) | ('=' testlist)*) ;
expr_stmt: var (augassign2 | (augassign value) | (EQ par* value (par* augassign3 par* value par*)*)) ;
value : (alpha_num|numeric_num|bool|complex_prestmt);

augassign : ('+=' | '-=' | '*=' | '/=' | '%=' | '&=' | '|=' | '^=' | '<<=' | '>>=' | '**=' | '//=') ;
augassign2 : ('++' | '--') ;
augassign3 : PLUS|MINUS|TIMES|DIV|MODULUS ;
par : LPAR | RPAR ;

//NAME is the name of the set
set : PUT (select_stmt|multiselect_stmt|select_chemical_objects_by_appendages|var|INT|alpha_num|numeric_num|bool) INTO NAME;

kill_stmt : KILL ;

rating_stmt : (ADD | SUBTRACT) INT ;

//return_stmt : RETURN testlist? ;
return_stmt : RETURN (var|func_stmt|alpha_num|numeric_num|bool|(var EQ (var|alpha_num|numeric_num|bool))) ;

exit_stmt : EXIT ;

designate_stmt : DESIGNATE (IT|complex_prestmt) AS (atom_prefix|bond_prefix|group_prefix);

permute_stmt : PERMUTE preposition? BOND INT ;

mech_stmt : ((INVERT | DEFINED_DOWN | DEFINED_UP) preposition? chemical_object range?) 
    | ((ANIONIZE | CATIONIZE | NEUTRALIZE | RADICALIZE) preposition? ATOM INT) ;

import_stmt: import_module | import_from ;
import_module: IMPORT import_as_names ;
import_from: (FROM (NAME|path|STRING) IMPORT (TIMES | LPAR import_as_names RPAR | import_as_names)) ;
import_as_name: (NAME|path|STRING) (AS NAME)? ;
import_as_names: import_as_name (',' import_as_name)* (',')? ;

compound_stmt : if_stmt | foreach_stmt | funcdef;

//if_stmt : IF test (block | (THEN small_stmt)) (ELIF test (block | (THEN small_stmt)))* (ELSE (block | (THEN small_stmt)))? ;
if_stmt : IF test ((THEN? block) | (THEN small_stmt)) (ELIF test ((THEN? block) | (THEN small_stmt)))* (ELSE ((THEN? block) | (THEN? small_stmt)))? ;
test : or_test  ;
or_test : and_test (ORIF and_test)* ;
and_test : (test_block | test_stmt) (ANDIF (test_block|test_stmt))*;
test_block : LPAR test_stmt RPAR;

foreach_stmt: FOREACH loop_stmt block ;
block : CLPAR suite CRPAR ;
suite: stmt* ;
//block : NEWLINE? CLPAR NEWLINE? suite NEWLINE? CRPAR ;
//suite: NEWLINE* stmt* NEWLINE*;

funcdef: FUNCTION func_stmt block ;
func_stmt : CALL? function_name parameters ;
parameters: LPAR (fplist)? RPAR ;
fplist: fpdef (COMMA fpdef)* (COMMA)?;
fpdef: (complex_prestmt|numeric_num|alpha_num) (EQ complex_prestmt|numeric_num|alpha_num)?;


//todo manage or: + and:
test_stmt : term|comparison ;

//comp_op: LT|GT|GE|LE|NE|FEWER_THAN|FEWER|OR_LARGER|LARGER|OR_SMALLER|SMALLER|LESS_THAN|MORE_THAN|LESS_HINDERED|MORE_HINDERED|THROUGH|notcontain|notequal|equal|contain ;
//comp_op: LT|GT|GE|LE|NE|EQ|FEWER_THAN|FEWER|OR_LARGER|LARGER|OR_SMALLER|SMALLER|LESS_THAN|MORE_THAN|LESS_HINDERED|MORE_HINDERED|THROUGH ;
comp_op: LT|GT|GE|LE|FEWER_THAN|FEWER|OR_LARGER|LARGER|OR_SMALLER|SMALLER|LESS_THAN|MORE_THAN|LESS_HINDERED|MORE_HINDERED|THROUGH|relation ;
relation : pos_relation | neg_relation ;
pos_relation : contain | equal ;
neg_relation : notcontain | notequal ; 

comparison : or_comp ;
or_comp : and_comp (OR and_comp)* ;
and_comp: expr (AND expr)* ;
expr: term ((PLUS|MINUS|TIMES|DIV|MODULUS) term)* ;

term : term_block | atom1 ((IS|HAS)? comp_op atom2?)? ;
term_block : LPAR atom1 ((IS|HAS)? comp_op atom2?)? RPAR; 
atom1 : (func_stmt|(complex_prestmt var?)|alpha_num|numeric_num|bool) ;
atom2 : (func_stmt|(complex_poststmt var?)|alpha_num|numeric_num|bool|STRING) ; 

//complex_prestmt : prestmt atom_loc? ANYWHERE? where_stmt? ;
complex_prestmt : prestmt ANYWHERE? where_stmt? ;

//remove implicit statements ex if a thiol is anywhere should be written as if molecule has a thiol anywhere
// -> delete (atom_loc? ANYWHERE) and (atom_loc? ANYWHERE? where_stmt) 
complex_poststmt : (poststmt ANYWHERE? where_stmt?) ;

prestmt : select_stmt
    | select_chemical_objects_by_appendages
    | var ;

poststmt : (preposition select_chemical_objects_by_appendages)
    | multiselect_stmt
    | var ;

//multi prestmt selection
multiselect_stmt : or_select ;
or_select : and_select (OR and_select)* ;
and_select: select_stmt (AND select_stmt)* ; 

select_stmt : select_chemical_objects | select_chemical_object | select_chemical_groups;

//select_chemical_object : select_chemical_object_by_id | select_chemical_object_by_loc | select_chemical_object_by_range;
select_chemical_object : select_chemical_object_by_id | select_chemical_object_by_range;
select_chemical_object_by_id : chemical_object INT ;
select_chemical_object_by_range :  chemical_object range ;

select_chemical_objects : select_chemical_objects_by_locs | select_chemical_objects_by_type | select_chemical_object_by_set;
select_chemical_objects_by_locs : (alpha_num | INT | ANY | ALL)? (atomtype|centre_stmt)? chemical_object? WITHIN? atom_loc preposition? atomtype? ((chemical_object INT?)|var)? ;
select_chemical_objects_by_type : (alpha_num | INT | ANY | ALL)? atomtype chemical_object? ;
select_chemical_object_by_set : (chemical_object preposition)? properties_stmt ;
select_chemical_objects_by_appendages : appendages_start_stmt (AND appendages_suite_stmt)* ;
appendages_start_stmt : APPENDAGE FROM ((select_chemical_object) | (atom_loc preposition? (chemical_object|var)?) | var) ANYWHERE? where_stmt? preposition article? ((select_chemical_object) | (atom_loc preposition? (chemical_object|var)?) | var)  where_stmt? ;
appendages_suite_stmt : APPENDAGE? FROM ((select_chemical_object) | (atom_loc preposition? (chemical_object|var)?) | var) ANYWHERE? where_stmt? preposition article? ((select_chemical_object) | (atom_loc preposition? (chemical_object|var)?) | var) where_stmt? ;
//error 
article : INT;
select_chemical_groups : select_chemical_groups_by_type | select_chemical_groups_by_locs;
select_chemical_groups_by_locs : (alpha_num | INT | ANY | ALL)? (smarts | functional_group | other_group | group_prefix) GROUP? WITHIN? atom_loc preposition? atomtype? ((chemical_object INT?)|var)? ;
select_chemical_groups_by_type : (alpha_num | INT | ANY | ALL)? (smarts | functional_group | other_group | group_prefix) GROUP?;

properties_stmt : atom_prop_stmt | bond_prop_stmt | group_prop_stmt | ring_prop_stmt | centre_stmt | charge_stmt;
atom_prop_stmt : ((alpha_num | INT)? atom_prefix chemical_object? atom_loc?) 
    | ((alpha_num | INT | ANY)? atomtype chemical_object?) ;
bond_prop_stmt : (alpha_num |INT | ANY)?  bond_prefix chemical_object? ;
group_prop_stmt : (alpha_num | INT | ANY)? (smarts | functional_group | other_group | group_prefix) ;
ring_prop_stmt : ring_prefix_prop? RING ring_size? (INT| alpha_num)? comp_op? (INT| alpha_num)? (CONTAINED complex_poststmt)? ;

loop_stmt : loop_complex_prestmt var? (preposition (func_stmt|var|set))?
    | var EQ loop_complex_prestmt (preposition (func_stmt|var))? ;

loop_complex_prestmt : loop_prestmt ANYWHERE? where_stmt? ;
loop_prestmt : loop_select_stmt | (charge_stmt? var) ;
loop_select_stmt : loop_select_chemical_objects | loop_select_chemical_groups ;

loop_select_chemical_objects : loop_select_chemical_objects_by_locs | loop_select_chemical_objects_by_type | loop_select_chemical_objects_by_prop| loop_select_chemical_object_related_to;
loop_select_chemical_objects_by_generic_type : chemical_object ;
loop_select_chemical_objects_by_locs : (alpha_num | INT | ANY | ALL)? (atomtype| centre_stmt)? chemical_object? WITHIN? atom_loc preposition? atomtype? ((chemical_object INT?)|var)? ;
loop_select_chemical_objects_by_type : (alpha_num | INT | ANY | ALL)? atomtype chemical_object? ;
loop_select_chemical_objects_by_prop : (alpha_num | INT)? (atom_prefix|bond_prefix|((smarts|functional_group|other_group|group_prefix)GROUP?)) chemical_object? ;
loop_select_chemical_object_related_to : (bond_prop_stmt|atom_prop_stmt|((smarts|functional_group|other_group|group_prefix)GROUP?)) preposition chemical_object;

loop_select_chemical_groups : select_chemical_groups_by_type | select_chemical_groups_by_locs;
loop_select_chemical_groups_by_locs : (alpha_num | INT | ANY | ALL)? (smarts | functional_group | other_group | group_prefix) GROUP? (WITHIN|preposition)? atom_loc preposition? atomtype? ((chemical_object INT?)|var)? ;
loop_select_chemical_groups_by_type : (alpha_num | INT | ANY | ALL)? (smarts | functional_group | other_group | group_prefix) GROUP?;
        
var : defined_as? ((NAME (alpha_num|INT)?) | (chemical_object NAME?)) ;
defined_as : (DEFINED AS) | (DEFINED IT AS?);
                  
where_stmt : (OFF_CURRENT_RING 
	| OFFPATH 
	| OFFRING 
    | ONRING
	| OFF_THE_BRIDGE 
	| ON_THE_BRIDGE 
	| ONPATH 
	| ON_CURRENT_RING) ;


ring_prefix_prop : CARBOCYCLIC | COMMON | HETEROCYCLIC ;
ring_suffix_prop : BRIDGE ;
ring_size : OF SIZE ;

//TODO linked var_count and quant_prop
var_count : NUMBER_LHASA OF (smarts | functional_group | other_group | atomtype)? atom_loc? TO chemical_object INT ;

quant_prop : NUMBER_LHASA | SIZE ;

chemical_object : ATOM | BOND | MOLECULE | RING | SET;

range : BETWEEN ((chemical_object INT)|var) AND ((chemical_object? INT)|var) ;

alpha_num : ZERO
    | ONE
    | TWO
    | THREE
    | FOUR
    | FIVE
    | SIX
    | SEVEN
    | EIGHT
    | NINE
    | TEN
    | ELEVEN
    | TWELVE ;

numeric_num : INT 
    | DECIMAL ;

atomtype : HYDROGEN 
    | HELIUM 
    | LITHIUM
    | BERYLLIUM 
    | BORON 
    | CARBON 
    | NITROGEN 
    | OXYGEN 
    | FLUORINE 
    | NEON 
    | SODIUM 
    | MAGNESIUM 
    | ALUMINIUM
    | SILICON 
    | PHOSPHORUS
    | SULFUR 
    | CHLORINE 
    | ARGON 
    | POTASSIUM 
    | CALCIUM 
    | SCANDIUM 
    | TITANIUM 
    | VANADIUM 
    | CHROMIUM 
    | IRON 
    | COBALT 
    | NICKEL 
    | COPPER 
    | ZINC 
    | GALLIUM 
    | GERMANIUM 
    | ARSENIC 
    | SELENIUM 
    | BROMINE 
    | KRYPTON 
    | RUBIDIUM
    | STRONTIUM 
    | YTTRBIUM 
    | ZIRCONIUM 
    | NIOBIUM 
    | MOLYBDENUM 
    | TECHNETIUM 
    | RUTHENIUM 
    | RHODIUM 
    | PALLADIUM 
    | SILVER 
    | CADMIUM 
    | INDIUM 
    | TIN 
    | ANTIMONY 
    | TELLURIUM 
    | IODINE 
    | XENON 
    | CESIUM 
    | BARIUM 
    | LANTHANUM 
    | HAFNIUM 
    | TANTALUM 
    | TUNGSTEN 
    | RHENIUM 
    | OSMIUM 
    | IRIDIUM 
    | PLATINUM 
    | GOLD 
    | MERCURY 
    | THALLIUM 
    | LEAD 
    | BISMUTH 
    | POLONIUM 
    | ASTATINE 
    | RADON 
    | FRANCIUM 
    | RADIUM 
    | ACTINOIDS 
    | RUTHERFORDIUM 
    | DUBNIUM 
    | SEABORGIUM 
    | BOHRIUM 
    | HASSIUM 
    | MEITNERIUM 
    | DARMSTADIUM 
    | ROENTGENIUM 
    | COPERNICIUM 
    | NIHONIUM 
    | FLEROVIUM 
    | MOSCOVIUM  
    | LIVERMORIUM 
    | TENNESSINE 
    | OGANESSON 
    | CERIUM 
    | PRASEODYMIUM 
    | NEODYMIUM 
    | PROMETHIUM 
    | SAMARIUM 
    | EUROPIUM 
    | GADOLINIUM 
    | TERBIUM 
    | DYSPROSIUM 
    | HOLMIUM 
    | ERBIUM 
    | THULIUM 
    | LUTETIUM 
    | THORIUM 
    | PROTACTINIUM 
    | URANIUM 
    | NEPTUNIUM 
    | PLUTONIUM 
    | AMERICIUM 
    | CURIUM 
    | BERKELIUM 
    | CALIFORNIUM 
    | EINSTEINIUM 
    | FERMIUM
    | MENDELEVIUM
    | NOBELIUM 
    | LAWRENCIUM ;

functional_group : ACETAL
	| ACETYLENE
	| ACID
	| ACID_HALIDE	
	| ALCOHOL
	| ALDEHYDE
	| ALLENE
	| AMIDE1
	| AMIDE2
	| AMIDE3
	| AMIDE
	| AMIDZ
	| AMINE1
	| AMINE2
	| AMINE3
	| AMINE
	| AMINE_OXIDE
	| ANHYDRIDE
	| AZIDE
	| AZIRIDINE
	| AZO
	| BROMIDE
	| C_SULFONATE
	| CARBAMATE_C
	| CARBAMATE_H
	| CARBONIUM
	| CARBONYL
	| CARBOXYL
	| CHLORIDE
	| CYANO
	| DIAZO
	| DISULFIDE
	| DITHIOACETAL
	| DITHIOKETAL
	| ENAMINE
	| ENOL_ETHER	
	| EPISULFIDE
	| EPOXIDE
	| ESTER
	| ESTERX
	| ETHER
	| FLUORIDE
	| FUNCTIONAL
	| GEM_DIHALIDE
	| GLYCOL
	| HALIDE
	| HALOAMINE
	| HALOHYDRIN
	| HEMIACETAL
	| HYDRATE
	| HYDRAZONE
	| HYDROXYLAMINE
	| IMINE
	| IODIDE
	| ISOCYANATE
	| ISOCYANIDE
	| KETONE
	| LACTAM
	| LACTONE
	| METHYLENE
	| N_CARBAMATE
	| N_UREA_C
	| N_UREA_H
	| NITRITE
	| NITRO	
	| NITROSO
	| O_CARBAMATE
	| O_CARBONATE
	| O_SULFONATE
	| OLEFIN
	| OXIME
	| PEROXIDE
	| PHOSPHINE
	| PHOSPHONATE
	| SELENIDE
	| SILYLENOLETHER
	| SULFIDE
	| SULFONE
	| SULFOXIDE
	| THIOCYANATE
	| THIOESTER	
	| THIOL
	| TRIALKYLSILOXY
	| TRIALKYLSILYL
	| TRIHALIDE
	| VIC_DIHALIDE
	| VINYLSILANE ;	
	
	
other_group : APPENDAGE
	| ISOPROPYL
	| METHYL
	| PHENYL
	| T_BUTYL ;

smarts : SMARTS LPAR (STRING|smarts_char) RPAR ;

atom_loc : ALPHA
    | BETA
    | GAMMA ;

atom_prefix : ALKALI_METAL
    | alkyl_number
    | ALLYLIC
    | ANTI
    | AROMATIC
    | BENZYLIC
    | BREDT_STRAINED
    | BRIDGEHEAD
    | CIS_OLEFIN
    | CIS preposition?
    | CONJUGATED
    | DOUBLY
    | ELECTRON_DENSITY preposition?
    | ENOLIZABLE
    | EQUIVALENT preposition?
    | FUSION
    | HALOGEN
    | HETERO 
    | MULTIPLY
    | STRAINED
    | SYN
    | TRANS_OLEFIN
    | TRANS
    | TRIPLY ;

atom_suffix : preposition EXPLICIT_HYDROGEN ;

bond_prefix : ALLYLIC
    | AROMATIC
    | BREDT_STRAINED
    | BRIDGEHEAD
    | CIS_OLEFIN
    | CONJUGATED
    | DONATING
    | DOUBLE_BOND
    | FUSION
    | MULTIPLE
    | SINGLE_BOND
    | SMALL_FUSION
    | STRAINED
    | SYN
    | TRANS_OLEFIN
    | TRIPLE_BOND 
    | WITHDRAWING ;

group_prefix : DONATING
    | NON_EXPANDABLE_WITHDRAWING
    | EXPANDABLE_WITHDRAWING 
    | INTERFERING
    | GOOD_LEAVING
    | LEAVING
    | PARTICIPATING
    | PROTECTED
    | VINYL_D
    | VINYL_W
    | WITHDRAWING;

charge_stmt : (alpha_num|INT)? (MINUS_CHARGE | PLUS_CHARGE) ;

centre_stmt : (PRIMARY_CENTER | SECONDARY_CENTER | TERTIARY_CENTER | QUATERNARY_CENTER) ;

ring_prop : CARBOCYCLIC | COMMON | HETEROCYCLIC ;

alkyl_number : INT ALKYL ;

bool : TRUE | FALSE ;

notcontain : (IS NOT ONLY? contain? IN)|(contain NOT ONLY?);
contain : (HAS ONLY?) | (CONTAINED ONLY?) | (IS ONLY? IN);

notequal : (IS NOT ONLY? (EQ|same)?);
equal : (IS ONLY?) | (ONLY? EQ) | (IS ONLY? same);
same : THE? ('same'|'SAME') AS?;

preposition : IN | OF | ON | TO | AT | TOWARDS;

//SLICE doesn't have article anymore
//article : THE | A | AN ;

function_name : (NAME | DOT | TIMES)+ ;
path : (NAME | '/')+ ;
smarts_char : (function_name|PLUS|MINUS|EQ|'#'|'~'|'?'|'/'|'\\'|COLON|SEMIC|COMMA|'!'|'&'|'['|']'|LPAR|RPAR|'>'|'@'|'$')+ ;

//Control Statements
EXIT : 'EXIT'|'exit' ;
FROM : 'FROM'|'from' ;
IMPORT : 'IMPORT' | 'import' ;
KILL : 'KILL' | 'kill' ;
RETURN : 'RETURN' | 'return' ;
ADD : 'ADD' | 'add' ;
DESIGNATE : 'DESIGNATE' | 'designate' ;
PERMUTE : 'PERMUTE'|'permute' ;
SUBTRACT : 'SUBTRACT' | 'subtract' ;


//Atom Type
HYDROGEN : 'HYDROGEN' | ' HYDROGENS' |' hydrogen' |' hydrogens' ;
HELIUM : 'HELIUM' | 'HELIUMS' | 'helium' | 'heliums' ;
LITHIUM : 'LITHIUM' | 'LITHIUMS' | 'lithium' | 'lithiums' ;
BERYLLIUM : 'BERYLLIUM' | 'BERYLLIUMS' | 'beryllium' | 'berylliums' ;
BORON : 'BORONS' | 'BORON' | 'boron' | 'borons' ;
CARBON : 'CARBON' | 'CARBONS' | 'carbon' | 'carbons' ;
NITROGEN : 'NITROGEN' | 'NITROGENS' | 'nitrogen' | 'nitrogens' ;
OXYGEN : 'OXYGEN' | 'OXYGENS' | 'oxygen' | 'oxygens' ;
FLUORINE : 'FLUORINE' | 'FLUORINES' | 'fluorine' | 'fluorines' ;
NEON : 'NEON' | 'NEONS' | 'neon' | 'neons' ;
SODIUM : 'SODIUM' | 'SODIUMS' | 'sodium' | 'sodiums' ;
MAGNESIUM : 'MAGNESIUM' | 'MAGNESIUMS' | 'magnesium' | 'magnesiums' ;
ALUMINIUM : 'ALUMINIUM' | 'ALUMINIUMS' | 'aluminium' | 'aluminiums' ;
SILICON : 'SILICON' | 'SILICONS' | 'silicon' | 'silicons' ;
PHOSPHORUS : 'PHOSPHORUS' | 'phosphorus' ;
SULFUR : 'SULFUR' | 'SULFURS' | 'sulfur' | 'sulfurs' | 'SULPHUR' | 'SULPHURS' | 'sulphur' | 'sulphurs' ;
CHLORINE : 'CHLORINE' | 'CHLORINES' | 'chlorine' | 'chlorines' ;
ARGON : 'ARGON' | 'ARGONS' | 'argon' | 'argons' ;
POTASSIUM : 'POTASSIUM' | 'POTASSIUMS' | 'potassium' | 'potassiums' ;
CALCIUM : 'CALCIUM' | 'CALCIUMS' | 'calcium' | 'calciums' ;
SCANDIUM : 'SCANDIUM' | 'SCANDIUMS' | 'scandium' | 'scandiums' ;
TITANIUM : 'TITANIUM' | 'TITANIUMS' | 'titanium' | 'titaniums' ;
VANADIUM : 'VANADIUM' | 'VANADIUMS' | 'vanadium' | 'vanadiums' ;
CHROMIUM : 'CHROMIUM' | 'CHROMIUMS' | 'chromium' | 'chromiums' ;
IRON : 'IRON' | 'IRONS' | 'iron' | 'irons' ;
COBALT : 'COBALT' | 'COBALTS' | 'cobalt' | 'cobalts' ;
NICKEL : 'NICKEL' | 'NICKELS' | 'nickel' | 'nickels' ;
COPPER : 'COPPER' |  'COPPERS' | 'copper' | 'coppers' ;
ZINC : 'ZINC' | 'ZINCS' | 'zinc' | 'zincs' ;
GALLIUM : 'GALLIUM' | 'GALLIUMS' | 'gallium' | 'galliums' ;
GERMANIUM : 'GERMANIUM' | 'GERMANIUMS' | 'germanium' | 'germaniums' ;
ARSENIC : 'ARSENIC' | 'ARSENICS' | 'arsenic' | 'arsenics' ;
SELENIUM : 'SELENIUM' | 'SELENIUMS' | 'selenium' | 'seleniums' ;
BROMINE : 'BROMINE' | 'BROMINES' | 'bromine' | 'bromines' ;
KRYPTON : 'KRYPTON' | 'KRYPTONS' | 'krypton' | 'kryptons' ;
RUBIDIUM : 'RUBIDIUM' | 'RUBIDIUMS' | 'rubidium' | 'rubidiums' ;
STRONTIUM : 'STRONTIUM' | 'STRONTIUMS' | 'strontium' | 'strontiums' ;
YTTRBIUM : 'YTTRBIUM' | 'YTTRBIUMS' | 'yttrbium' | 'yttrbiums' ;
ZIRCONIUM : 'ZIRCONIUM' | 'ZIRCONIUMS' | 'zirconium' | 'zirconiums' ;
NIOBIUM : 'NIOBIUM' | 'NIOBIUMS' | 'niobium' | 'niobiums' ;
MOLYBDENUM : 'MOLYBDENUM' | 'MOLYBDENUMS' | 'molybdenum' | 'molybdenums' ;
TECHNETIUM : 'TECHNETIUM' | 'TECHNETIUMS' | 'technetium' | 'technetiums' ;
RUTHENIUM : 'RUTHENIUM' | 'RUTHENIUMS' | 'ruthenium' | 'rutheniums' ;
RHODIUM : 'RHODIUM' | 'RHODIUMS' | 'rhodium' | 'rhodiums' ;
PALLADIUM : 'PALLADIUM' | 'PALLADIUMS' | 'palladium' | 'palladiums' ;
SILVER : 'SILVER' | 'SILVERS' | 'silver' | 'silvers' ;
CADMIUM : 'CADMIUM' | 'CADMIUMS' | 'cadmium' | 'cadmiums' ;
INDIUM : 'INDIUM' | 'INDIUMS' | 'indium' | 'indiums' ;
TIN : 'TIN' | 'TINS' | 'tin' | 'tins' | 'STANNUM' | 'STANNUMS' | 'stannum' | 'stannums';
ANTIMONY : 'ANTIMONY' | 'ANTIMONYS' | 'antimony' | 'antimonys' ;
TELLURIUM : 'TELLURIUM' | 'TELLURIUMS' | 'tellurium' | 'telluriums' ;
IODINE : 'IODINE' | 'IODINES' | 'iodine' | 'iodines' ;
XENON : 'XENON' | 'XENONS' | 'xenon' | 'xenons' ;
CESIUM : 'CESIUM' | 'CESIUMS' | 'cesium' | 'cesiums' ;
BARIUM : 'BARIUM' | 'BARIUMS' | 'barium' | 'bariums' ;
LANTHANUM : 'LANTHANUM' | 'LANTHANUMS' | 'lanthanum' | 'lanthanums' ;
HAFNIUM : 'HAFNIUM' | 'HAFNIUMS' | 'hafnium' | 'hafniums' ;
TANTALUM : 'TANTALUM' | 'TANTALUMS' | 'tantalum' | 'tantalums' ;
TUNGSTEN : 'TUNGSTEN' | 'TUNGSTENS' | 'tungsten' | 'tungstens' ;
RHENIUM : 'RHENIUM' | 'RHENIUMS' | 'rhenium' | 'rheniums' ;
OSMIUM : 'OSMIUM' | 'OSMIUMS' | 'osmium' | 'osmiums' ;
IRIDIUM : 'IRIDIUM' | 'IRIDIUMS' | 'iridium' | 'iridiums' ;
PLATINUM : 'PLATINUM' | 'PLATINUMS' | 'platinum' | 'platinums' ;
GOLD : 'GOLD' | 'GOLDS' | 'gold' | 'golds' ;
MERCURY : 'MERCURY' | 'MERCURYS' | 'mercury' | 'mercurys' ;
THALLIUM : 'THALLIUM' | 'THALLIUMS' | 'thallium' | 'thalliums' ;
LEAD : 'LEAD' | 'LEADS' | 'lead' | 'leads' ;
BISMUTH : 'BISMUTH' | 'BISMUTHS' | 'bismuth' | 'bismuths' ;
POLONIUM : 'POLONIUM' | 'POLONIUMS' | 'polonium' | 'poloniums' ;
ASTATINE : 'ASTATINE' | 'ASTATINES' | 'astatine' | 'astatines' ;
RADON : 'RADON' | 'RADONS' | 'radon' | 'radons' ;
FRANCIUM : 'FRANCIUM' | 'FRANCIUMS' | 'francium' | 'franciums' ;
RADIUM : 'RADIUM' | 'RADIUMS' | 'radium' | 'radiums' ;
ACTINOIDS : 'ACTINOIDS' | 'actinoids' ;
RUTHERFORDIUM : 'RUTHERFORDIUM' | 'RUTHERFORDIUMS' | 'rutherfordium' | 'rutherfordiums' ;
DUBNIUM : 'DUBNIUM' | 'DUBNIUMS' | 'dubnium' | 'dubniums' ;
SEABORGIUM : 'SEABORGIUM' | 'SEABORGIUMS' | 'seaborgium' | 'seaborgiums' ;
BOHRIUM : 'BOHRIUM' | 'BOHRIUMS' | 'bohrium' | 'bohriums' ;
HASSIUM : 'HASSIUM' | 'HASSIUMS' | 'hassium' | 'hassiums' ;
MEITNERIUM : 'MEITNERIUM' | 'MEITNERIUMS' | 'meitnerium' | 'meitneriums' ;
DARMSTADIUM : 'DARMSTADIUM' | 'DARMSTADIUMS' | 'darmstadtium' | 'darmstadtiums' ;
ROENTGENIUM : 'ROENTGENIUM' | 'ROENTGENIUMS' | 'roentgenium' | 'roentgeniums' ;
COPERNICIUM : 'COPERNICIUM' | 'COPERNICIUMS' | 'copernicium' | 'coperniciums' ;
NIHONIUM : 'NIHONIUM' | 'NIHONIUMS' | 'nihonium' | 'nihoniums' ;
FLEROVIUM : 'FLEROVIUM' | 'FLEROVIUMS' | 'flerovium' | 'fleroviums' ;
MOSCOVIUM : 'MOSCOVIUM' | 'MOSCOVIUMS' | 'moscovium' | 'moscoviums' ;
LIVERMORIUM : 'LIVERMORIUM' | 'LIVERMORIUMS' | 'livermorium' | 'livermoriums' ;
TENNESSINE : 'TENNESSINE' | 'TENNESSINES' | 'tennessine' | 'tennessines' ;
OGANESSON : 'OGANESSON' | 'OGANESSONS' | 'oganesson' | 'oganessons' ;
CERIUM : 'CERIUM' | 'CERIUMS' | 'cerium' | 'ceriums' ;
PRASEODYMIUM : 'PRASEODYMIUM' | 'PRASEODYMIUMS' | 'praseodymium' | 'praseodymiums' ;
NEODYMIUM : 'NEODYMIUM' | 'NEODYMIUMS' | 'neodymium' | 'neodymiums' ;
PROMETHIUM : 'PROMETHIUM' | 'PROMETHIUMS' | 'promethium' | 'promethiums' ;
SAMARIUM : 'SAMARIUM' | 'SAMARIUMS' | 'smarium' | 'smariums' ;
EUROPIUM : 'EUROPIUM' | 'EUROPIUMS' | 'europium' | 'europiums' ;
GADOLINIUM : 'GADOLINIUM' | 'GADOLINIUMS' | 'gadolinium' | 'gadoliniums' ;
TERBIUM : 'TERBIUM' | 'TERBIUMS' | 'terbium' | 'terbiums' ;
DYSPROSIUM : 'DYSPROSIUM' | 'DYSPROSIUMS' | 'dysprosium' | 'dysprosiums' ;
HOLMIUM : 'HOLMIUM' | 'HOLMIUMS' | 'holmium' | 'holmiums' ;
ERBIUM : 'ERBIUM' | 'ERBIUMS' | 'erbium' | 'erbiums' ;
THULIUM : 'THULIUM' | 'THULIUMS' | 'thulium' | 'thuliums' ;
LUTETIUM : 'LUTETIUM' | 'LUTETIUMS' | 'lutetium' | 'lutetiums' ;
THORIUM : 'THORIUM' | 'THORIUMS' | 'thorium' | 'thoriums' ;
PROTACTINIUM : 'PROTACTINIUM' | 'PROTACTINIUMS' | 'protactinium' | 'protactiniums' ;
URANIUM : 'URANIUM' | 'URANIUMS' | 'uranium' | 'uraniums' ;
NEPTUNIUM : 'NEPTUNIUM' | 'NEPTUNIUMS' | 'neptunium' | 'neptuniums' ;
PLUTONIUM : 'PLUTONIUM' | 'PLUTONIUMS' | 'plutonium' | 'plutoniums' ;
AMERICIUM : 'AMERICIUM' | 'AMERICIUMS' | 'americium' | 'americiums' ;
CURIUM : 'CURIUM' | 'CURIUMS' | 'curium' | 'curiums' ;
BERKELIUM : 'BERKELIUM' | 'BERKELIUMS' | 'berkelium' | 'berkeliums' ;
CALIFORNIUM : 'CALIFORNIUM' | 'CALIFORNIUMS' | 'californium' | 'californiums' ;
EINSTEINIUM : 'EINSTEINIUM' | 'EINSTEINIUMS' | 'einsteinium' | 'einsteiniums' ;
FERMIUM : 'FERMIUM' | 'FERMIUMS' | 'fermium' | 'fermiums' ;
MENDELEVIUM : 'MENDELEVIUM' | 'MENDELEVIUMS' | 'mendelevium' | 'mendeleviums' ;
NOBELIUM : 'NOBELIUM' | 'NOBELIUMS' | 'nobelium' | 'nobeliums' ;
LAWRENCIUM : 'LAWRENCIUM' | 'LAWRENCIUMS' | 'lawrencium' | 'lawrenciums' ;


//Functional Groups
DITHIOACETAL : 'DITHIO' (WS|'-')? ACETAL | 'dithio' (WS|'-')? ACETAL ;
HEMIACETAL : 'HEMI'|'hemi' (WS|'-')? ACETAL ;
ACETAL : 'ACETAL' | 'acetal' | 'ACETALS' | 'acetals' ;
ACETYLENE : 'ACETYLENE' | 'acetylene' | 'ACETYLENES' | 'acetylenes' ;
ACID_HALIDE : ACID HALIDE ;
ACID : 'ACID' | 'acid' | 'ACIDS' | 'acids' ;
ALCOHOL : 'ALCOHOL'|'HYDROXYL' | 'alcohol' | 'hydroxyl' |'ALCOHOLS'|'HYDROXYLS' | 'alcohols' | 'hydroxyls' ;
ALDEHYDE : 'ALDEHYDE' | 'aldehyde' | 'ALDEHYDES' | 'aldehydes';
ALLENE : 'ALLENE' | 'allene' | 'ALLENES' | 'allenes';
AMIDE1 : AMIDE (WS|'-')? '1' ;
AMIDE2 : AMIDE (WS|'-')? '2' ;
AMIDE3 : AMIDE (WS|'-')? '3' ;
AMIDE : 'AMIDE' | 'amide'|'AMIDES' | 'amides' ;
AMIDZ : 'AMIDZ' | 'amidz'|'AMIDZS' | 'amidzs' ;
HYDROXYLAMINE : ('HYDROXYL'|'hydroxyl')(WS|'-')? AMINE ;
AMINE1 : AMINE (WS|'-') '1' ;
AMINE2 : AMINE (WS|'-')? '2' ;
AMINE3 : AMINE (WS|'-')? '3' ;
AMINE : 'AMINE' | 'amine'|'AMINES' | 'amines' ;
AMINE_OXIDE : 'AMINEOXIDE' | 'amineoxide'|'AMINE OXIDE' | 'amine oxide'|'AMINEOXIDES' | 'amineoxides'|'AMINE OXIDES' | 'amine oxides' ;
ANHYDRIDE : 'ANHYDRIDE' | 'anhydride'|'ANHYDRIDES' | 'anhydrides' ;
AZIDE : 'AZIDE' | 'azide'|'AZIDES' | 'azides' ;
AZIRIDINE : 'AZIRIDINE' | 'aziridine'|'AZIRIDINES' | 'aziridines' ;
AZO : 'AZO' | 'azo'|'AZOS' | 'azos' ;
BROMIDE : 'BROMIDE' | 'bromide'|'BROMIDES' | 'bromides' ;
C_SULFONATE : ('C'|'c')(WS|'-')?('SULFONATE'|'SULPHONATE'|'sulfonate' |'sulphonate')|('C'|'c')(WS|'-')?('SULFONATES'|'SULPHONATES'|'sulfonates' |'sulphonates') ;
CARBAMATE_C : ('CARBAMATE'|'carbamate')(WS|'-')?('C'|'c') | ('CARBAMATES'|'carbamates')(WS|'-')?('C'|'c');
CARBAMATE_H : ('CARBAMATE'|'carbamate')(WS|'-')?('H'|'h') | ('CARBAMATES'|'carbamates')(WS|'-')?('H'|'h') ;
CARBONIUM : 'CARBONIUM' | 'carbonium'|'CARBONIUMS' | 'carboniums' ;
CARBONYL : 'CARBONYL' | 'carbonyl'|'CARBONYLS' | 'carbonyls' ;
CARBOXYL : 'CARBOXYL' | 'carboxyl'|'CARBOXYLS' | 'carboxyls' ;
CHLORIDE : 'CHLORIDE' | 'chloride'|'CHLORIDES' | 'chlorides' ;
CYANO : 'CYANO'|'NITRILE' | 'cyano' | 'nitrile'|'CYANOS'|'NITRILES' | 'cyanos' | 'nitriles' ;
DIAZO : 'DIAZO' | 'diazo'|'DIAZOS' | 'diazos' ;
DISULFIDE : ('DI'|'di') (WS|'-')? SULFIDE;
DITHIOKETAL : 'DITHIOKETAL' | 'dithioketal'|'DITHIOKETALS' | 'dithioketals' ;
ENAMINE : 'ENAMINE' | 'enamine'|'ENAMINES' | 'enamines' ;
ENOL_ETHER : 'ENOL' (WS|'-')? ETHER ;
EPOXIDE : 'EPOXIDE' | 'epoxide'|'EPOXIDES' | 'epoxides' ;
THIOESTER : ('THIO'|'thio') (WS|'-')? ESTER  ;
ESTER : 'ESTER' | 'ester'|'ESTERS' | 'esters' ;
ESTERX : 'ESTERX' | 'esterx'|'ESTERXS' | 'esterxs' ;
SILYLENOLETHER : ('SILYLENOL'|'silylenol') (WS|'-')? ETHER;
ETHER : 'ETHER' | 'ether'|'ETHERS' | 'ethers' ;
FLUORIDE : 'FLUORIDE' | 'fluoride'|'FLUORIDES' | 'fluorides' ;
FUNCTIONAL : 'FUNCTIONAL' | 'functional' ;
GEM_DIHALIDE : ('GEM'|'gem') (WS|'-')? ('DI'|'di') (WS|'-')? HALIDE ;
GLYCOL : 'GLYCOL' | 'glycol'|'GLYCOLS' | 'glycols' ;
TRIHALIDE : ('TRI'|'tri') (WS|'-')? HALIDE ;
VIC_DIHALIDE : ('VIC'|'vic') (WS|'-')? ('DI'|'di') (WS|'-')? HALIDE ;
HALIDE : 'HALIDE' | 'halide'|'HALIDES' | 'halides' ;
HALOAMINE : 'HALOAMINE' | 'haloamine'|'HALOAMINES' | 'haloamines' ;
HALOHYDRIN : 'HALOHYDRIN' | 'halohydrin'|'HALOHYDRINS' | 'halohydrins' ;
HYDRATE : 'HYDRATE' | 'hydrate'|'HYDRATES' | 'hydrates' ;
HYDRAZONE : 'HYDRAZONE' | 'hydrazone'|'HYDRAZONEs' | 'hydrazones' ;
IMINE : 'IMINE' | 'imine'|'IMINES' | 'imines' ;
IODIDE : 'IODIDE' | 'iodide'|'IODIDES' | 'iodides' ;
ISOCYANATE : 'ISOCYANATE' | 'isocyanate'|'ISOCYANATES' | 'isocyanates' ;
ISOCYANIDE : 'ISOCYANIDE' | 'isocyanide'|'ISOCYANIDES' | 'isocyanides' ;
KETONE : 'KETONE' | 'ketone'|'KETONES' | 'ketones' ;
LACTAM : 'LACTAM' | 'lactam'|'LACTAMS' | 'lactams' ;
LACTONE : 'LACTONE' | 'lactone'|'LACTONES' | 'lactones' ;
METHYLENE : 'METHYLENE' | 'methylene'|'METHYLENES' | 'methylenes' ;
N_CARBAMATE : ('N'|'n') (WS|'-')? ('CARBAMATE'|'carbamate')|('N'|'n') (WS|'-')? ('CARBAMATES'|'carbamates') ;
N_UREA_C : ('N'|'n') (WS|'-')? ('UREA'|'urea') (WS|'-')? ('C'|'c') ;
N_UREA_H : ('N'|'n') (WS|'-')? ('UREA'|'urea') (WS|'-')? ('H'|'h') ;
NITRITE : 'NITRITE' | 'nitrite'|'NITRITES' | 'nitrites' ;
NITRO : 'NITRO' | 'nitro'|'NITROS' | 'nitros' ;
NITROSO : 'NITROSO' | 'nitroso'|'NITROSOS' | 'nitrosos' ;
O_CARBAMATE : ('O'|'o') (WS|'-')? ('CARBAMATE'|'carbamate') | ('O'|'o') (WS|'-')? ('CARBAMATES'|'carbamates') ;
O_CARBONATE : ('O'|'o') (WS|'-')? ('CARBONATE'|'carbonate') | ('O'|'o') (WS|'-')? ('CARBONATES'|'carbonates')  ;
O_SULFONATE : ('O'|'o') (WS|'-')? ('SULFONATE'|'SULPHONATE'|'sulfonate'|'sulphonate')|('O'|'o') (WS|'-')? ('SULFONATES'|'SULPHONATES'|'sulfonates'|'sulphonates') ;
OLEFIN : 'OLEFIN' | 'olefin'|'OLEFINS' | 'olefins' ;
OXIME : 'OXIME' | 'oxime'|'OXIMES' | 'oximes' ;
PEROXIDE : 'PEROXIDE' | 'peroxide'|'PEROXIDES' | 'peroxides' ;
PHOSPHINE : 'PHOSPHINE' | 'phosphine'|'PHOSPHINES' | 'phosphines' ;
PHOSPHONATE : 'PHOSPHONATE' | 'phosphonate'|'PHOSPHONATES' | 'phosphonates' ;
SELENIDE : 'SELENIDE' | 'selenide'|'SELENIDES' | 'selenides' ;
EPISULFIDE : ('EPI'|'epi') (WS|'-')? SULFIDE ;
SULFIDE : 'SULFIDE'|'SULPHIDE' | 'sulfide' | 'sulphide'|'SULFIDES'|'SULPHIDES' | 'sulfides' | 'sulphides' ;
SULFONE : 'SULFONE' | 'sulfone'|'SULFONES' | 'sulfones' ;
SULFOXIDE : 'SULFOXIDE' | 'sulfoxide'|'SULFOXIDES' | 'sulfoxides' ;
THIOCYANATE : 'THIOCYANATE' | 'thiocyanate'|'THIOCYANATES' | 'thiocyanates' ;
THIOL : 'THIOL' | 'thiol'|'THIOLS' | 'thiols' ;
TRIALKYLSILOXY : 'TRIALKYLSILOXY' | 'trialkylsiloxy'|'TRIALKYLSILOXYS' | 'trialkylsiloxys' ;
TRIALKYLSILYL : 'TRIALKYLSILYL' | 'trialkylsilyl'|'TRIALKYLSILYLS' | 'trialkylsilyls' ;
VINYLSILANE : 'VINYLSILANE' | 'vinylsilane'|'VINYLSILANES' | 'vinylsilanes' ;

//other Substructures
APPENDAGE : 'APPENDAGE' | 'appendage' | 'APPENDAGES' | 'appendages' ;
ISOPROPYL : 'ISOPROPYL' | 'isopropyl' ;
METHYL : 'METHYL' | 'methyl' ;
PHENYL : 'PHENYL' | 'phenyl' ;
T_BUTYL : 'T*BUTYL' | ('t'|'T')'*butyl'|'T'(WS|'-')'BUTYL' | ('t'|'T')(WS|'-')'butyl' ;
SMARTS : 'SMARTS' | 'smarts' ;

//Atom Properties
ALKALI_METAL : 'ALKALI*METAL' | 'alkali*metal'|'ALKALI'(WS|'-')'METAL' | 'alkali'(WS|'-')'metal';
ALKYL : 'ALKYL'|'ALKYLS'|'alkyl'|'alkyls' ;
BENZYLIC : 'BENZYLIC'|'benzylic' ;
CIS : 'CIS'|'cis' ;
DOUBLY : 'DOUBLY' | 'doubly';
ELECTRON_DENSITY : 'ELECTRON*DENSITY'|'electron*density'|'ELECTRON DENSITY'|'electron density' ;
ENOLIZABLE : 'ENOLIZABLE'|'enolizable' ;
EQUIVALENT : 'EQUIVALENT'|'equivalent' ;
EXPLICIT_HYDROGEN : 'EXPLICIT*HYDROGEN'|'explicit*hydrogen'|'EXPLICIT HYDROGEN'|'explicit hydrogen'|'EXPLICIT HYDROGENS'|'explicit hydrogens' ;
HALOGEN : 'HALOGEN'|'halogen' ;
HETERO : 'HETERO'|'hetero' ;
MINUS_CHARGE : 'MINUS*CHARGE'|'minus*charge'|'NEGATIVE*CHARGE'|'negative*charge'|'MINUS*CHARGES'|'minus*charges'|'NEGATIVE*CHARGES'|'negative*charges' 
    | 'MINUS CHARGE'|'minus charge'|'NEGATIVE CHARGE'|'negative charge'|'MINUS CHARGES'|'minus charges'|'NEGATIVE CHARGES'|'negative charges';
MULTIPLY : 'MULTIPLY'|'MULTIPLY BONDED'|'multiply'|'multiply bonded' ;
PLUS_CHARGE : 'PLUS*CHARGE'|'plus*charge'|'POSITIVE*CHARGE'|'positive*charge'|'PLUS*CHARGES'|'plus*charges'|'POSITIVE*CHARGES'|'positive*charges'
    |'PLUS CHARGE'|'plus charge'|'POSITIVE CHARGE'|'positive charge'|'PLUS CHARGES'|'plus charges'|'POSITIVE CHARGES'|'positive charges';
TRANS : 'TRANS'|'trans' ;
TRIPLY : 'TRIPLY' | 'triply' ;

//Bond Properties
DOUBLE_BOND : 'DOUBLE' | 'double';
MULTIPLE : 'MULTIPLE'|'multiple' ;
SINGLE_BOND : 'SINGLE' | 'single' ;
SMALL_FUSION : 'SMALL FUSION'|'SMALL FUSIONS'|'small fusion'|'small fusions' ;
TRIPLE_BOND : 'TRIPLE' | 'triple';

//Common atom and bond Properties
ALLYLIC : 'ALLYLIC' | 'allylic';
ANTI : 'ANTI'|'anti' ;
AROMATIC : 'AROMATIC'|'ARYL'|'aromatic'|'aryl' ;
BREDT_STRAINED : 'BREDT*STRAINED'|'bredt*strained'|'BREDT'(WS|'-')'STRAINED'|'bredt'(WS|'-')'strained' ;
BRIDGEHEAD : 'BRIDGEHEAD'|'BRIDGEHEADS'|'bridgehead'|'bridgeheads' ;
CIS_OLEFIN : 'CIS*OLEFIN'|'cis*olefin'|'CIS'(WS|'-')'OLEFIN'|'cis'(WS|'-')'olefin'  ;
CONJUGATED : 'CONJUGATED'|'conjugated' ;
FUSION : 'FUSION'|'FUSED'|'fusion'|'fused' ;
STRAINED : 'STRAINED'|'strained' ;
SYN : 'SYN'|'syn' ;
TRANS_OLEFIN : 'TRANS*OLEFIN'|'trans*olefin'|'TRANS'(WS|'-')'OLEFIN'|'trans'(WS|'-')'olefin' ;

//Common bond and group Properties
DONATING : 'DONATING'|'donating' ;
NON_EXPANDABLE_WITHDRAWING : 'NON EXPANDABLE WITHDRAWING'|'non expandable withdrawing' ;
EXPANDABLE_WITHDRAWING : 'EXPANDABLE WITHDRAWING'|'expandable withdrawing' ;
INTERFERING : 'INTERFERING'|'interfering' ;
GOOD_LEAVING : 'GOOD LEAVING'|'GOOD LEAVING GROUP'|'good leaving'|'good leaving group' ;
LEAVING : 'LEAVING*GROUP'|'leaving*group'|'LEAVING GROUP'|'leaving group'|'leaving'|'LEAVING' ;
PARTICIPATING : 'PARTICIPATING'|'participating' ;
PROTECTED : 'PROTECTED'|'protected' ;
VINYL_D : 'VINYL*D'|'vinyl*'('D'|'d')|('VINYL D'|'vinyl')(WS|'-')('D'|'d') ;
VINYL_W : 'VINYL*W'|'vinyl*'('W'|'w')|('VINYL W'|'vinyl')(WS|'-')('W'|'w') ;
WITHDRAWING : 'WITHDRAWING'|'withdrawing' ;

//Centre
PRIMARY_CENTER :'PRIMARY'(WS|'-')'CENTER'|'PRIMARY'(WS|'-')'CENTRE'|'primary'(WS|'-')'center'|'primary'(WS|'-')'centre'|'PRIMARY'(WS|'-')'CENTERS'|'PRIMARY'(WS|'-')'CENTRES'|'primary'(WS|'-')'centers'|'primary'(WS|'-')'centres' ;
SECONDARY_CENTER : 'SECONDARY'(WS|'-')'CENTER'|'SECONDARY'(WS|'-')'CENTRE'|'secondary'(WS|'-')'center'|'secondary'(WS|'-')'centre' | 'SECONDARY'(WS|'-')'CENTERS'|'SECONDARY'(WS|'-')'CENTRES'|'secondary'(WS|'-')'centers'|'secondary'(WS|'-')'centres' ;
TERTIARY_CENTER : 'TERTIARY'(WS|'-')'CENTER'|'TERTIARY'(WS|'-')'CENTRE'|'tertiary'(WS|'-')'center'|'tertiary'(WS|'-')'centre' |'TERTIARY'(WS|'-')'CENTERS'|'TERTIARY'(WS|'-')'CENTRES'|'tertiary'(WS|'-')'centers'|'tertiary'(WS|'-')'centres' ;
QUATERNARY_CENTER : 'QUATERNARY'(WS|'-')'CENTER'|'QUATERNARY'(WS|'-')'CENTRE'|'quaternary'(WS|'-')'center'|'quaternary'(WS|'-')'centre' | 'QUATERNARY'(WS|'-')'CENTERS'|'QUATERNARY'(WS|'-')'CENTRES'|'quaternary'(WS|'-')'centers'|'quaternary'(WS|'-')'centres' ;

//loc atoms
ALPHA : 'ALPHA'|'alpha' ;
BETA : 'BETA'|'beta' ;
GAMMA : 'GAMMA'|'gamma' ;

//Atom and Bond Sets and range
SET : ATOMS_TO_ATOMS | ATOMS_TO_BONDS | BONDS_TO_ATOMS | BONDS_TO_BONDS ;
ATOMS_TO_ATOMS : 'ATOMS*TO*ATOMS' | 'atoms*to*atoms' | 'ATOMS TO ATOMS' | 'atoms to atoms';
ATOMS_TO_BONDS : 'ATOMS*TO*BONDS' | 'atoms*to*bonds' | 'ATOMS TO BONDS' | 'atoms to bonds' ;
BONDS_TO_ATOMS : 'BONDS*TO*ATOMS' | 'bonds*to*atoms' | 'BONDS TO ATOMS' | 'bonds to atoms' ;
BONDS_TO_BONDS : 'BONDS*TO*BONDS' | 'bonds*to*bonds' | 'BONDS TO BONDS' | 'bonds to bonds';
//BRIDGE1 : 'BRIDGE*1' ;
//BRIDGE2 : 'BRIDGE*2' ;
//BRIDGE3 : 'BRIDGE*3' ;
//CURRENT_RING : 'CURRENT*RING' ;
//FRAGMENT1 : 'FRAGMENT*1' ;
//FRAGMENT2 : 'FRAGMENT*2' ;
//LOCANT : 'LOCANT'|'IT'|'THEM' | 'locant'|'it'|'them' ;
//replace by BOND_BETWEEN_ATOM
//SPANNING : 'SPANNING' ;

BETWEEN : '*BETWEEN*' | '*between*' | 'BETWEEN' | 'between';
WITHIN : 'WITHIN' | 'within';

//ring property prefix
CARBOCYCLIC : 'CARBOCYCLIC'|'carbocyclic' ;
COMMON : 'COMMON'|'common' ;
HETEROCYCLIC : 'HETEROCYCLIC'|'heterocyclic' ;

//ring property suffix
BRIDGE : 'BRIDGE'|'BRIDGED'|'bridge'|'bridged' ;

//location statements
ANYWHERE : 'ANYWHERE' | 'anywhere' ;
OFF_CURRENT_RING : 'OFF*CURRENT*RING' | 'off*current*ring' | 'OFF CURRENT RING' | 'off current ring' | 'OFF THE CURRENT RING' | 'off the current ring';
OFFPATH : 'OFFPATH' | 'offpath' ;
OFFRING : 'OFFRING' | 'offring' ;
OFF_THE_BRIDGE : 'OFF THE BRIDGE' | 'off the bridge' ;
ON_THE_BRIDGE : 'ON THE BRIDGE' | 'on the bridge';
ONPATH : 'ONPATH' | 'onpath' ;
ONRING : 'ONRING' | 'onring' ;
ON_CURRENT_RING : 'ON*CURRENT*RING' | 'on*current*ring' |'ON CURRENT RING' | 'on current ring' |'ON THE CURRENT RING' | 'on the current ring' ;

//set comparison
FEWER_THAN : 'FEWER*THAN'|'fewer*than'|'FEWER THAN'|'fewer than' ;
FEWER : 'FEWER'|'fewer' ;
OR_LARGER : 'OR*LARGER'|'or*larger'|'OR LARGER'|'or larger';
LARGER : 'LARGER'|'larger' ;
OR_SMALLER : 'OR*SMALLER'|'or*smaller'|'OR SMALLER'|'or smaller' ;
SMALLER : 'SMALLER'|'smaller' ;
LESS_HINDERED : 'LESS*HINDERED*THAN'|'less*hindered*than'|'LESS HINDERED THAN'|'less hindered than' ;
MORE_HINDERED : 'MORE*HINDERED*THAN'|'more*hindered*than'|'MORE HINDERED THAN'|'more hindered than' ;
LESS_THAN : 'LESS*THAN'|'less*than'|'LESS THAN'|'less than' ;
MORE_THAN : 'MORE*THAN'|'more*than'|'MORE THAN'|'more than' ;
THROUGH : 'THROUGH'|'through' ;

//quantitative prop
NUMBER_LHASA : 'NUMBER'|'number' ;
SIZE : 'SIZE'|'size' ;

//Mechanism statement TODO ANTI, SYN, CIS and TRANS
INVERT : 'INVERT' | 'invert' ;
ANIONIZE : 'ANIONIZE' | 'anionize';
CATIONIZE : 'CATIONIZE' | 'cationize';
DEFINED_DOWN : 'DEFINED*DOWN' | 'defined*down' | 'DEFINED'WS DOWN | 'defined'WS DOWN;
DEFINED_UP : 'DEFINED*UP' | 'defined*up'| 'DEFINED'WS UP |'defined'WS UP;
DEFINED_ANTI : 'DEFINED*ANTI' ;
DEFINED_CIS : 'DEFINED*CIS' ;
DEFINED_SYN : 'DEFINED*SYN' ;
DEFINED_TRANS : 'DEFINED*TRANS' ;
NEUTRALIZE : 'NEUTRALIZE' | 'neutralize';
RACEMIZE : 'RACEMIZE' | 'racemize' ;
RADICALIZE : 'RADICALIZE' | 'radicalize';
UP : 'UP'|'up' ;
DOWN : 'DOWM'|'down' ;

//alpha num
ZERO : 'ZERO' | 'zero' ;
ONE : 'ONE' | 'one';
TWO : 'TWO' | 'two' ;
THREE : 'THREE' | 'three' ;
FOUR : 'FOUR' | 'four' ;
FIVE : 'FIVE' | 'five' ;
SIX : 'SIX' | 'six' ;
SEVEN : 'SEVEN' | 'seven' ;
EIGHT : 'EIGHT' | 'eight' ;
NINE : 'NINE' | 'nine' ;
TEN : 'TEN' | 'ten' ;
ELEVEN : 'ELEVEN' | 'eleven' ;
TWELVE : 'TWELVE' | 'twelve' ;
THIRTEEN : 'THIRTEEN' | 'thirteen' ;
FOURTEEN : 'FOURTEEN' | 'fourteen' ;
FIFTEEN : 'FIFTEEN' | 'fifteen' ;
SIXTEEN : 'SIXTEEN' | 'sixteen' ;
SEVENTEEN : 'SEVENTEEN' | 'seventeen' ;
EIGHTEEN : 'EIGHTEEN' | 'eighteen' ;
NINETEEN : 'NINETEEN' | 'nineteen' ;
TWENTY : 'TWENTY' | 'twenty' ;

//key words
ATOM : 'ATOM' | 'atom' | 'ATOMS' | 'atoms' ;
BOND : 'BOND' | 'bond' | 'BONDS' | 'bonds' ;
MOLECULE : 'MOLECULE' | 'molecule' | 'MOLECULES' | 'molecules';
RING : 'RING' | 'ring' | 'RINGS' | 'rings' ;

//function
FUNCTION : 'FUNCTION' | 'function';

//Conditional Statements 
ELIF : 'ELIF'|'elif' ;
IF : 'IF'|'if' ;
ELSE : 'ELSE'|'else' ;

//Trigger statement
CALL : 'CALL'|'call' ;
PUT : 'PUT'|'put' ;
THEN : 'THEN'|'then' ;

//Loop statements
FOREACH : 'FOREACH'|'foreach' ;

//relation
IS : 'is'|'IS'| 'are'|'ARE' ;
HAS : 'has'|'HAS'| 'have'|'HAVE' ;

//Statement operators
ORIF : 'OR:IF' | 'or:if' | 'OR IF' | 'or if' ;
ANDIF : 'AND:IF' | 'and:if' | 'AND IF' | 'and if';

//Operators
TRUE : 'TRUE'|'SUCCESSFUL'|'SUCCESS'|'true'|'successful'|'success' ;
FALSE : 'FALSE'|'FAIL'|'UNSUCCESSFUL'|'false'|'fail'|'unsuccessful' ;
SEMIC : '\\;' ;
COLON : ':' ;
COMMA : ',' ;
EQ : '=='|'='|'IDENTICAL'|'identical'|'EQUAL'|'equal'|'EQ'|'eq';
PLUS : '+' ;
MINUS : '-' ;
TIMES : '*' ;
DIV : '/' ;
MODULUS : '%' ;
LPAR : '(' ;
RPAR : ')' ;
CLPAR : '{' ;
CRPAR : '}' ;
DOT : '.' ;
LE : '<=' | '=<'|'LE'|'le' ;
GE : '>=' | '=>'|'GE'|'ge' ;
GT : '>'| 'GT'|'gt' ;
LT : '<'|'LT'|'lt' ;
NE : '!='|'=!'|'NE'|'ne'|NOT EQ ;
NOT : 'NOT'|'!'|'NO'|'NON'|'not'|'no'|'non' ; 
AND : 'AND'|'&&'|'&'|'and' ;
OR : 'OR'|'||'|'|'|'or' ;
XOR : 'XOR'|'^'|'xor' ;
OTHERWISE : 'OTHERWISE'|'otherwise' ;
CONTAINED : 'CONTAINED' | 'CONTAINING' | 'CONTAINS' | 'CONTAIN' | 'contained' | 'containing' | 'contains' | 'contain';
AS : 'AS'|'as' ;
IT : 'IT'|'it' ;
ONLY : 'ONLY' | 'only' ;

//other authorized word
GROUP : 'GROUP' | 'group' ;

//prepositions
AT : 'AT'|'at' ;
IN : 'IN'|'in' ;
INCLUDING : 'INCLUDING'|'including' ;
OF : 'OF'|'of' ;
ON : 'ON'|'on' ;
TOWARDS : 'TOWARDS' | 'towards' ;
TO : 'TO'|'to' ;
INTO : 'INTO' | 'into' ;

ANY : 'ANY' | 'any' ;
ALL : 'ALL' | 'all' ;
DEFINED : 'DEFINED' | 'defined' | 'DEFINE' | 'define' | 'DEFINEs' | 'defines';

//article
AN : 'AN'|'an' ;
A : 'A'|'a' ;
THE : 'THE'|'the' ;

//EOF
/*
 * DO NOT MODIFY THE NEXT PART
*/

//
// Numbers
//

fragment
DIGIT : '0'..'9' ;

fragment
HEXDIGIT : 'a'..'f'|'A'..'F'|DIGIT ;

INT : DIGIT+ ;

fragment
SIGNED_INT : ('+'|'-') INT ;

DECIMAL: INT '.' INT? | '.' INT ;

EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+  ;

fragment
FLOAT : ('0'..'9')+ '.' ('0'..'9')* EXPONENT?
	| '.' ('0'..'9')+ EXPONENT?
	| ('0'..'9')+ EXPONENT ;

fragment
SIGNED_FLOAT: ('+'|'-') FLOAT ;

fragment
NUMBER : FLOAT | INT ;

fragment
SIGNED_NUMBER : ('+'|'-')  NUMBER ;

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

//
// Strings
//

STRING : '"' ( ESC_SEQ | ~('\\'|'"') )* '"' ;

fragment
CHAR: '\'' ( ESC_SEQ | ~('\''|'\\') ) '\'' ;

fragment
ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;

NAME : ('a'..'z'|'A'..'Z'|'0'..'9'|'_')+ ;




//NAME : ('a'..'z'|'A'..'Z'|'_'|'*') ('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'*')* ;

//FUNCTION_NAME : ('a'..'z'|'A'..'Z'|'_'|'*'|'.') ('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'*'|'.')* ;

//SMARTS_CHAR : ('a'..'z'|'A'..'Z'|'*'|'.'|'+'|'-'|'='|'#'|'~'|'?'|'/'|'\\'|':'|';'|','|'!'|'&'|'['|']'|'('|')'|'>'|'@'|'$') ('a'..'z'|'A'..'Z'|'*'|'.'|'+'|'-'|'='|'#'|'~'|'?'|'/'|'\\'|':'|';'|','|'!'|'&'|'['|']'|'('|')'|'>'|'@'|'$')*;

COMMENT : ('/*' .*? '*/' 
	| '...' .*?)    -> channel(HIDDEN) ;

LINE_COMMENT : ('//' ~[\r\n]* 
    | '#' ~[\r\n]*
    | '...' ~[\r\n]*)  -> channel(HIDDEN) ;

//
// Names (Variables)
//

fragment
LCASE_LETTER : 'a'..'z' ;

fragment
UCASE_LETTER : 'A'..'Z' ;

fragment
LETTER : UCASE_LETTER | LCASE_LETTER ;

fragment
WORD : LETTER+ ;

fragment
TEXT : (NAME|NUMBER|WS_INLINE)+ ;

fragment
CNAME: ('_'|LETTER) ('_'|LETTER|DIGIT)* ;


//
// Whitespace
//

fragment
SPACE : ' ' ; 

fragment
TAB : '\t' ;

fragment
CR : '\r' ;

fragment
LF : '\n' ;

fragment
NEWLINE: (CR? LF)+ ;


WS : (WS_INLINE
	| CR
	| LF) -> channel(HIDDEN) ;

WS_INLINE : (SPACE | TAB) -> channel(HIDDEN) ;

//
// Escape
//


fragment
ESC_SEQ : '\\' ('b'|'t'|'n'|'f'|'r'|'\\"'|'\''|'\\')
	| UNICODE_ESC
	| OCTAL_ESC ;

fragment
OCTAL_ESC : '\\' ('0'..'3') ('0'..'7') ('0'..'7')
	| '\\' ('0'..'7') ('0'..'7')
	| '\\' ('0'..'7') ;

fragment
UNICODE_ESC : '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT ;
