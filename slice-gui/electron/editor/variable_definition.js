window.currentFilePath = null;
window.chemEditor = null;
window.chemComposer = null;
window.chemViewer = null;
window.fileOnLoad = false;
window.depictReactions = true;
//last structurally created or modified mol
window.defaultMol = null;
window.chemObjectInUseByBlockly = null;
window.selectedChemObject = null;
window.newSelectedChemObject = null;
window.molID = null;
window.chemObjId = null;
window.objEditor = null;
window.objDefaultEditor = null;
window.reactions = [];
window.mols = [];
window.smirksList = [];
window.blocklyXML = {};
window.domDict = {};
window.selectFieldsArray = [];
window.addDefaultField = true;
window.showPeriodicTable = true;
window.currentbuildingblocks=null;

window.commonProperties = null;
window.specificProperties = null;
window.localSmartsTextField = null;

window.isLogicInitialize = false;
window.areReactionImagesUpdated = true;
window.hasReactionChanged = false;
window.currentTabName = "ChemDescription";
window.proceduresNames = [];

window.authorsDB = null;
window.journalsDB = null;
window.reagentsDB = null;
window.solventsDB = null;

window.toAddToAuthorsDB = new Array();
window.toAddToJournalsDB = new Array();
window.toAddToReagentsDB = new Array();
window.toAddToSolventsDB = new Array();

window.options = ["N.A.", "bad", "poor", "fair", "good", "very good", "excellent"];

window.descriptionAndCondition = {
	"transform": [],
	"history": [],
	"setup": [],
	"bibliography": [],
	"comments": [],
	"conditions": [],
};

window.atomProperties = [
	"Chemical Element",
	//"Chemical Environment",
	"Negative Charge",
	"Positive Charge",
	"Connectivity",
	"Degree",
	"Hybridization Number",
	"Total Hydrogen Count",
	//"Implicit Hydrogen Count",
	"Insaturation",
	"Periodic Group",
	"Pi Electron Count",
	"Ring Bond Count",
	"Ring Count",
	"Ring Size",
	"Aliphatic Hetero Substituent Count",
	"Hetero Substituent Count",
	"Valence",
];
window.bondProperties = [
	"Single Bond",
	"Double Bond",
	"Triple Bond",
	"Quadruple Bond",
	"Aromatic Bond",
	"Ring Bond",
	"Any Bond",
	"Up Bond",
	"Up or Unspecified Bond",
	"Down Bond",
	"Down or Unspecified Bond",
	"Electrons Involved In Bond",
];

window.atomPropertyEncoding = {
	"Atom Mapping": ":",
	//"Chemical Element": "",
	"Chemical Environment": "",
	"Negative Charge": "-",
	"Positive Charge": "+",
	"Connectivity": "X",
	"Degree": "D",
	"Pi Electron Count": "e",
	"Hybridization Number": "^",
	"Total Hydrogen Count": "H",
	//"Implicit Hydrogen Count": "h",
	"Insaturation": "i",
	"Periodic Group": "#G",
	"Ring Bond Count": "x",
	"Ring Count": "R",
	"Ring Size": "r",
	"Aliphatic Hetero Substituent Count": "Z",
	"Hetero Substituent Count": "z",
	"Valence": "v",
};

window.bondPropertyEncoding = {
	"Single Bond": "-",
	"Double Bond": "=",
	"Triple Bond": "#",
	"Quadruple Bond": "$",
	"Aromatic Bond": ":",
	"Ring Bond": "@",
	"Any Bond": "~",
	"Up Bond": "/",
	"Up or Unspecified Bond": "/\?",
	"Down Bond": "\\",
	"Down or Unspecified Bond": "\\\?",
	"Electrons Involved In Bond": "#E",
};

const acetal = "$([CH;Z2]([OH0])[OH0])";
const acetylene = "$([C]#[C])";
const acid_halide = "$([C](=O)[F,Cl,Br,I])";
const alcohol = "$([OH])";
const aldehyde = "$([CH1](=O))";
const allene = "$([C]=[C]=[C])";
const amide1 = "$([C](=O)[NH2])";
const amide2 = "$([C](=O)[NH1])";
const amide3 = "$([C](=O)[NH0])";
const amidz = "$([N!R][C!R]=[O])";
const amine1 = "$([NH2;+0;X3])";
const amine2 = "$([NH1;+0;X3])";
const amine3 = "$([NH0;+0;X3])";
const amine_oxide = "$([N+;X4]([O-])([#6])[#6])";
const anhydride = "$([C](=O)O[C](=O)[A])";
const azide = "$(N=[N+]=[N-])";
const aziridine = "$(C1[N+0;X3]C1)";
const azo = "$([N;R0;z1]=[N;R0;z1][#6,#1])";
const bromide = "$([Br;v1])";
const C_sulfonate = "$([S+0;v6](=O)(=O)O)";
const carbamate_C = "$([C](N(C)(C))(=O)OC)";
const carbamate_H = "$([C!R](=O)(O)[NH1!R])";
const carbonium = "$([#6+;X3])";
//TODO add acid as acid derivative
const carboxylic_acid = "$([C](=O)[OH])";
const chloride = "$([Cl;v1])";
const cyano = "$([C]#N)";
const diazo = "$([N+;i;X2;v4](=C)=[N-;X1;v2])";
const disulfide = "$([S;v2][S;v2][#6])";
const dithioacetal = "$([CH1;Z2](S[*;Z1])S[*;Z1])";
const dithioketal = "$([C;Z2](S[*;Z1])(C)(C)S[*;Z1])";
const enamine = "$([#6;Z0]=[#6;Z0][#7;Z0])";
const enol_ether = "$([#6;z1](=[#6;z0])[#8;z0][#6])";
const episulfide = "$(C1[S;v2]C1)";
const epoxide = "$(C1[O]C1)";
const ester = "$([C;!R;z2](=O)[O;!R][C;!R])";
const esterx = "$(O[C;!R]=O)";
const ether = "$(O[#6])";
const fluoride = "$([F;v1])";
const functional = "";
const gemdihalide = "$([F,Cl,Br,I][CX4][F,Cl,Br,I])";
const glycol = "$([C;z1]([O;z0])[C;z1][O;z0])";
const haloamine = "$([N;X3][F,Cl,Br,I])";
const halohydrin = "$([#6;z1]([F,Cl,Br,I])[#6][#8;z0])"; //error in CHMTRN
const hemiacetal = "$([CH1;Z2]([OH])[O;Z1])";
const hydrate = "$(C([OH])[OH])";
const hydrazone = "$([#6]=NN)";
const hydroxylamine = "$([N;R0][OH])";
const imine = "$([C]=N[#1,#6])";
const iodide = "$([I;v1])";
const isocyanate = "$(N=C=O)";
const isocyanide = "$([N+]#[C-])";
const ketone = "$([#6](=O)[#6])";
const lactam = "$([C;z2;R](=O)[NH1;R;z0])";
const lactone = "$([#6;z2;R](=O)[#8;R;z0])";
const methylene = "$([CH2;!i0;v4]=*)";
const N_carbamate = "$(NC(=O)O[C;z1])";
const N_urea = "$([N;Z0][C;Z3](=O)[N;Z0])";
const N_urea_C = "$([NH0;Z0][C;Z3](=O)[NH0;Z0])";
const N_urea_H = "$([N;!H0;Z0][C;Z3](=O)[N;Z0]),$([N;Z0][C;Z3](=O)[N;!H0;Z0])";
const nitrite = "$(ON=O)";
const nitro = "$([N+](=O)[O-])";
const nitroso = "$(N=O)";
const O_carbamate = "$(OC(=O)N)";
const O_carbonate = "$(OC(=O)O[C;z1])";
const O_sulfonate = "$(O[S+0;v6](=O)(=O)O[#1;#6])";
const olefin = "$(C=C)";
const oxime = "$([N;i1;X2](=[*])[OH])";
const peroxide = "$(OO[#6,#1])";
const phosphine = "$(P([#6,#1])[#6,#1])";
const phosphonate = "$(OP)";
const selenide = "$([Se+0;v2][#6])";
const silylenolether = "$(C(=C)O[Si]([CH3])([CH3])[CH3])";
const sulfide = "$([S+0;v2][#6])";
const sulfone = "$([S+0;v6;X4](=O)(=O)[#6])";
const sulfoxide = "$([S+0;v4](=O)[#6])";
const thiocyanate = "$(SC#N)";
const thioester = "$([C;z{2-3}](=O)S[#6])";
const thiol = "$([SH1;v2])";
const trialkylsiloxy = "$(O[Si;i0;X4])";
const trialkylsilyl = "$([Si;i0;X4])";
const trihalide = "$([F,Cl,Br,I][CX4]([F,Cl,Br,I])[F,Cl,Br,I])";
const vic_dihalide = "$(C([F,Cl,Br,I])C[F,Cl,Br,I])";
const vinylsilane = "$(C(=C)[Si;X4])";
const isopropyl = "$([CH1]([CH3])[CH3])";
const methyl = "$([CH3])";
const phenyl = "$([C]1=[CH1][CH1]=[CH1][CH1]=[CH1]1)";
const t_butyl = "$([CH1]([CH3])([CH3])[CH3])";
const vinyl_D = "$([C]=[C][CH;Z2]([OH0])[OH0]),$([C]=[C][OH]),$([C]=[C][N!R][C!R]=[O]),$([C]=[C][NH2;+0;X3]),$([C]=[C][NH1;+0;X3]),$([C]=[C][NH0;+0;X3]),$([C]=[C]O[C;!R]=O),$([C]=[C][N;X3][F,Cl,Br,I]),$([C]=[C][CH1;Z2]([OH])[O;Z1]),$([C]=[C][N;R0][OH]),$([C]=[C]NC(=O)O[C;z1]),$([C]=[C][NH0;Z0][C;Z3](=O)[NH0;Z0]),$([C]=[C][N;!H0;Z0][C;Z3](=O)[N;Z0]),$([C]=[C][N;Z0][C;Z3](=O)[N;!H0;Z0]),$([C]=[C]OC(=O)N),$([C]=[C]OC(=O)O[C;z1]),$([C]=[C]P([#6,#1])[#6,#1]),$([C]=[C][Se+0;v2][#6]),$([C]=[C][S+0;v2][#6]),$([C]=[C][SH1;v2]),$([C]=[C]O[Si;i0;X4])";
const vinyl_W = "$([C]=[C][C](=O)[F,Cl,Br,I]),$([C]=[C][CH1](=O)),$([C]=[C][C](=O)[NH2]),$([C]=[C][C](=O)[NH1]),$([C]=[C][C](=O)[NH0]),$([C]=[C][N!R][C!R]=[O]),$([C]=[C][C!R](=O)O[C!R](=O)[A]),$([C]=[C][#6+;X3]),$([C]=[C][C;!R;z2](=O)[O;!R][C;!R]),$([C]=[C][#6](=O)[#6]),$([C]=[C][C;R0]=N[#1,#6]),$([C]=[C][C]#N),$([C]=[C][N;i1;X2](=[*;R0])[OH]),$([C]=[C][C;z{2-3}](=O)S[#6]),$([C]=[C][F,Cl,Br,I][CX4]([F,Cl,Br,I])[F,Cl,Br,I]),$([C]=[C][N+](=O)[O-]),$([C]=[C][S+0;v6](=O)(=O)O),$([C]=[C][S+0;v6;X4](=O)(=O)[#6]),$([C]=[C][S+0;v4](=O)[#6])";

var amide = amide1 + "," + amide2 + "," + amide3;
var carbonyl = aldehyde + "," + ketone;
var halide = bromide + "," + chloride + "," + fluoride + "," + iodide;


window.functionalGroupProperties = {
	"customized": "",
	"acetal": acetal,
	"acetylene": acetylene,
	"acid halide": acid_halide,
	"alcohol": alcohol,
	"aldehyde": aldehyde,
	"allene": allene,
	"amide 1": amide1,
	"amide 2": amide2,
	"amide 3": amide3,
	"amide": amide1 + "," + amide2 + "," + amide3,
	"amidz": amidz,
	"amine 1": amine1,
	"amine 2": amine2,
	"amine 3": amine3,
	"amine": amine1 + "," + amine2 + "," + amine3,
	"amine oxide": amine_oxide,
	"anhydride": anhydride,
	"azide": azide,
	"aziridine": aziridine,
	"azo": azo,
	"bromide": bromide,
	"C-sulfonate": C_sulfonate,
	"carbamate-C": carbamate_C,
	"carbamate-H": carbamate_H,
	"carbonium": carbonium,
	"carbonyl": aldehyde + "," + ketone,
	//TODO add acid as acid derivative
	"carboxylic acid": carboxylic_acid,
	"carboxyl group": carboxylic_acid + "," + ester + "," + amide + "," + acid_halide + "," + thioester + "," + anhydride,
	"chloride": chloride,
	"cyano": cyano,
	"diazo": diazo,
	"donating group": acetal + "," + alcohol + "," + amidz + "," + amine1 + "," + amine2 + "," + amine3 + "," + esterx + "," + haloamine +
		"," + hemiacetal + "," + hydroxylamine + "," + N_carbamate + "," + N_urea_C + "," + N_urea_H + "," + O_carbamate + "," + O_carbonate +
		"," + phosphine + "," + selenide + "," + sulfide + "," + thiol + "," + trialkylsiloxy + "," + vinyl_D,
	"disulfide": disulfide,
	"dithioacetal": dithioacetal,
	"dithioketal": dithioketal,
	"enamine": enamine,
	"enol ether": enol_ether,
	"episulfide": episulfide,
	"epoxide": epoxide,
	"ester": ester,
	"esterx": esterx,
	"ether": ether,
	"expandable withdrawing group": acid_halide + "," + aldehyde + "," + amide1 + "," + amide2 + "," + amide3 + "," + amidz + "," + anhydride +
		"," + carbonium + "," + ester + "," + ketone + "," + imine + "," + cyano + "," + oxime + "," + thioester + "," + trihalide + "," + vinyl_W,
	"fluoride": fluoride,
	"functional": acetal + "," + acetylene + "," + acid_halide + "," + alcohol + "," + aldehyde + "," + allene +
		"," + amide1 + "," + amide2 + "," + amide3 + "," + amidz + "," + amine1 + "," + amine2 + "," + amine3 + "," + amine_oxide + "," + anhydride +
		"," + azide + "," + aziridine + "," + azo + "," + bromide + "," + C_sulfonate + "," + carbamate_C + "," + carbamate_H + "," + carbonium + "," + carbonyl +
		"," + carboxylic_acid + "," + chloride + "," + cyano + "," + diazo + "," + disulfide + "," + dithioacetal + "," + dithioketal + "," + enamine + "," + enol_ether +
		"," + episulfide + "," + epoxide + "," + ester + "," + esterx + "," + ether + "," + fluoride + "," + gemdihalide + "," + glycol + "," + haloamine +
		"," + halohydrin + "," + hemiacetal + "," + hydrate + "," + hydrazone + "," + hydroxylamine + "," + imine + "," + iodide + "," + isocyanate + "," + isocyanide +
		"," + ketone + "," + lactam + "," + lactone + "," + methylene + "," + N_carbamate + "," + N_urea_C + "," + N_urea_H + "," + nitrite + "," + nitroso +
		"," + O_carbamate + "," + O_carbonate + "," + O_sulfonate + "," + olefin + "," + oxime + "," + peroxide + "," + phosphine + "," + phosphonate + "," + selenide +
		"," + silylenolether + "," + sulfide + "," + sulfone + "," + sulfoxide + "," + thiocyanate + "," + thioester + "," + thiol + "," + trialkylsiloxy + "," + trialkylsilyl +
		"," + trihalide + "," + vic_dihalide + "," + vinylsilane,
	"gemdihalide": gemdihalide,
	"glycol": glycol,
	"good leaving  group": aziridine + "," + bromide + "," + chloride + "," + episulfide + "," + epoxide + "," + iodide + "," + O_sulfonate +
		"," + phosphonate + "," + gemdihalide + "," + trihalide,
	"halide": bromide + "," + chloride + "," + fluoride + "," + iodide,
	"haloamine": haloamine,
	"halohydrin": halohydrin,
	"hemiacetal": hemiacetal,
	"hydrate": hydrate,
	"hydrazone": hydrazone,
	"hydroxylamine": hydroxylamine,
	"imine": imine,
	"iodide": iodide,
	"isocyanate": isocyanate,
	"isocyanide": isocyanide,
	"ketone": ketone,
	"lactam": lactam,
	"lactone": lactone,
	"leaving  group": acetal + "," + alcohol + "," + amidz + "," + aziridine + "," + N_carbamate + "," + O_carbamate + "," + dithioacetal +
		"," + dithioketal + "," + episulfide + "," + epoxide + "," + esterx + "," + ether + "," + gemdihalide + "," + halide + "," + N_urea_C + "," + N_urea_H +
		"," + O_carbonate + "," + phosphonate + "," + selenide + "," + sulfide + "," + sulfone + "," + C_sulfonate + "," + O_sulfonate + "," + thiocyanate +
		"," + thiol + "," + trialkylsiloxy + "," + trihalide,
	"methylene": methylene,
	"N-carbamate": N_carbamate,
	"N-urea-C": N_urea_C,
	"N-urea-H": N_urea_H,
	"nitrite": nitrite,
	"nitro": nitro,
	"nitroso": nitroso,
	"non expandable withdrawing group": nitro + "," + C_sulfonate + "," + sulfone + "," + sulfoxide,
	"O-carbamate": O_carbamate,
	"O-carbonate": O_carbonate,
	"O-sulfonate": O_sulfonate,
	"olefin": olefin,
	"oxime": oxime,
	"peroxide": peroxide,
	"phosphine": phosphine,
	"phosphonate": phosphonate,
	"selenide": selenide,
	"silylenolether": silylenolether,
	"sulfide": sulfide,
	"sulfone": sulfone,
	"sulfoxide": sulfoxide,
	"thiocyanate": thiocyanate,
	"thioester": thioester,
	"thiol": thiol,
	"trialkylsiloxy": trialkylsiloxy,
	"trialkylsilyl": trialkylsilyl,
	"trihalide": trihalide,
	"vinyl-D": vinyl_D,
	"vinyl-W": vinyl_W,
	"vic-dihalide": vic_dihalide,
	"vinylsilane": vinylsilane,
	"withdrawing group": acid_halide + "," + aldehyde + "," + amide1 + "," + amide2 + "," + amide3 + "," + amidz + "," + anhydride +
		"," + carbonium + "," + ester + "," + ketone + "," + imine + "," + cyano + "," + oxime + "," + thioester + "," + trihalide + 
		"," + nitro + "," + C_sulfonate + "," + sulfone + "," + sulfoxide + "," + vinyl_W,
	"isopropyl": isopropyl,
	"methyl": methyl,
	"phenyl": phenyl,
	"t-butyl": t_butyl,
}

//lazy code for generating vinyl_d and vynyl_w
var don = acetal + "|||" + alcohol + "|||" + amidz + "|||" + amine1 + "|||" + amine2 + "|||" + amine3 + "|||" + esterx + "|||" + haloamine +
	"|||" + hemiacetal + "|||" + hydroxylamine + "|||" + N_carbamate + "|||" + N_urea_C + "|||" + "$([N;!H0;Z0][C;Z3](=O)[N;Z0])" +
	"|||" + "$([N;Z0][C;Z3](=O)[N;!H0;Z0])" + "|||" + O_carbamate + "|||" + O_carbonate +
	"|||" + phosphine + "|||" + selenide + "|||" + sulfide + "|||" + thiol + "|||" + trialkylsiloxy;

var withd = acid_halide + "|||" + aldehyde + "|||" + amide1 + "|||" + amide2 + "|||" + amide3 + "|||" + amidz + "|||" + anhydride +
	"|||" + carbonium + "|||" + ester + "|||" + ketone + "|||" + imine + "|||" + cyano + "|||" + oxime + "|||" + thioester + "|||" + trihalide + "|||" +
	"|||" + nitro + "|||" + C_sulfonate + "|||" + sulfone + "|||" + sulfoxide;

//lazy code for fenerating vinyl group
/*
var donArr = don.split("|||");
var grp1 = "";
for (var i in donArr) {
	grp1 += donArr[i].replaceAll("$(","$([C]=[C]") + ",";
}

var grp2 = "";
var withArr = withd.split("|||");
for (var i in withArr) {
	grp2 += withArr[i].replaceAll("$(","$([C]=[C]") + ",";
}

console.log(grp1);
console.log(grp2);
*/





// Old env kept for conversion purposes only
/*
const acetal = "$([C:Z1]O[*;Z2:999]O[C:Z1])";
const acetylene = "$([A:999]#[C])";
const carboxyl_group = "$([A:999](=O)O([#1])),$([*;!R;z2:999](=O)[O;!R][C;!R]),$([A:999](=O)[N;z0]([#1])[#1]),$([*:999](=O)[N;z0]([#1])[!#1]),$([A:999](=O)[N;z0]([!#1])[!#1])"
	+ ";$([A:999](=O)[F,Cl,Br,I]),$([A;z{2-3}:999](=O)S[#6]),$([*:999](=O)OC=O)";
const acid_halide = "$([A:999](=O)[F,Cl,Br,I])";
const alcohol = "$([*;Z1:999]O[#1])";
const aldehyde = "$([A:999](=O)[#1])";
const allene = "$([A:999]=[C;A]=[C;A])";
const amide1 = "$([A:999](=O)[N;z0]([#1])[#1])";
const amide2 = "$([A:999](=O)[N;z0]([#1])[!#1])";
const amide3 = "$([A:999](=O)[N;z0]([!#1])[!#1])";
const amide = "$([A:999](=O)[N;z0]([#1])[#1])),$([A:999](=O)[N;z0]([#1])[!#1])),$([A:999](=O)[N;z0]([!#1])[!#1]))";
const amidz = "$([C;Z1][N;z0][C;z2]=O)";
const amine1 = "$([*:999][N+0;X3]([#1])[#1])";
const amine2 = "$([*:999][N+0;X3]([#6])[#1])";
const amine3 = "$([*:999][N+0;X3]([#6])[#6])";
const amine = "$([*:999][N+0;X3]([#1])[#1]),$([*:999][N+0;X3]([#6])[#1]),$([*:999][N+0;X3]([#6])[#6])";
const amine_oxide = "$([*:999][N+;X4]([O-])([#6])[#6])";
const anhydride = "$([*:999](=O)OC=O)";
const azide = "$([*:999]N=[N+]=[N-])";
const aziridine = "$([A:999]1[N+0;X3]C1)";
const azo = "$([*:999][N;R0;z1]=[N;R0;z1][#6])";
const bromide = "$([*:999][Br;v1])";
const C_sulfonate = "$([*:999][S+0;v6](=O)(=O)O)";
const carbamate_C = "$(N(C)(C)[C:999](=O)OC)";
const carbamate_H = "$(N([#1])[C:999](=O)O)";
const carbonium = "$([#6+;X3:999])";
const carbonyl = "$([A:999](=O)[#1]),$([#6][A:999](=O)[#6])";
	//TODO add acid as acid derivative
const carboxylic_acid = "$([A:999](=O)O([#1]))";
const chloride = "$([*:999]Cl)";
const cyano = "$([*:999]#N)";
const diazo = "$([*:999]=[N+]=[N-])";
const disulfide = "$([*:999][S;v2][S;v2][#6])";
const dithioacetal = "$([*;Z1]S[*;Z2:999]([#1])S[*;Z1])";
const dithioketal = "$([*;Z1]S[*;Z2:999](C)(C)S[*;Z1])";
const enamine = "$([C]=[*:999]N(C)C)";
const enol_ether = "$([C]=[*:999]O[#6;z1])";
const episulfide = "$([A:999]1[S;v2]C1)";
const epoxide = "$([*:999]1OC1)";
const ester = "$([*;!R;z2:999](=O)[O;!R][C;!R])";
const esterx = "$([*;z2:999](=O)O[#6])";
const ether = "$([CX4,c:999]O[CX4,c])";
const fluoride = "$([*:999]F)";
const functional = "";
const gemdihalide = "$([CX4:999]([F,Cl,Br,I])[F,Cl,Br,I])";
const glycol = "$([C;#1;Z1]O[*;Z1:999][C;Z1]O[C;#1;Z1])";
const halide = "$([*:999][Br;v1]),$([*:999]Cl)$([#6:999]F),$([*:999][I;v1])";
const haloamine = "$([*:999][N;X3][F,Cl,Br,I])";
const halohydrin = "$([F,Cl,Br,I][*;Z1:999][O;A][#6;#1])";
const hemiacetal = "$([C;Z1]O[*;Z2:999]O[#1][C;Z1])";
const hydrate = "$([A:999](O[#1])O[#1])";
const hydrazone = "$([*:999]NN)";
const hydroxylamine = "$([*:999][N;R0]O[#1])";
const imine = "$([*;R0:999]=N[#1,#6])";
const iodide = "$([*:999][I;v1])";
const isocyanate = "$([*:999]N=C=O)";
const isocyanide = "$([*:999][N+]#[C-])";
const ketone = "$([#6][*:999](=O)[#6])";
const lactam = "$([*;z2;R:999](=O)[N;R;z0]([#1]))";
const lactone = "$([*;R;z2:999](=O)[O;R][C;R])";
const methylene = "$([A:999]=C([#1])[#1])";
const N_carbamate = "$([A:999]NC(=O)O[C;z1])";
const N_urea_C = "$([A:999]N(C)C(=O)N(C)C)";
const N_urea_H = "$([A:999]NC(=O)N([#1])),$([A:999]N([#1])C(=O)N)";
const nitrite = "$([*:999]ON=O)";
const nitro = "$([*:999][N+](=O)[O-])";
const nitroso = "$([*:999]N=O)";
const O_carbamate = "$([A:999]OC(=O)N)";
const O_carbonate = "$([A:999]OC(=O)O[C;z1])";
const O_sulfonate = "$([*:999]O[S+0;v6](=O)(=O)O[#1;#6])";
const olefin = "$([A:999]=C)";
const oxime = "$([*;R0:999]=NO[#1])";
const peroxide = "$([*:999]OO[#6;#1])";
const phosphine = "$([*:999]P([#6;#1])[#6;#1])";
const phosphonate = "$([*:999]OP)";
const selenide = "$([*:999][Se+0;v2][#6])";
const silylenolether = "$([C]=[*:999]O[Si]([C;#1])([C;#1])[C;#1])";
const sulfide = "$([*:999][S+0;v2][#6])";
const sulfone = "$([*:999][S+0;v6;X4](=O)(=O)[#6])";
const sulfoxide = "$([*:999][S+0;v4](=O)[#6])";
const thiocyanate = "$([*:999]SC#N)";
const thioester = "$([A;z{2-3}:999](=O)S[#6])";
const thiol = "$([*;Z1:999][S;v2][#1])";
const trialkylsiloxy = "$([*:999]O[Si](C)(C)C])";
const trialkylsilyl = "$([*:999][Si](C)(C)C])";
const trihalide = "$([*;Z1:999]([F,Cl,Br,I])([F,Cl,Br,I])[F,Cl,Br,I])";
const vic_dihalide = "$([F,Cl,Br,I][*;Z1:999][C;Z1][F,Cl,Br,I])";
const vinylsilane = "$([A:999]=[C][Si]([C;#1])([C;#1])[C;#1])";
const isopropyl = "$([A:999]([#1])(C([#1])([#1])[#1])C([#1])([#1])[#1])";
const methyl = "$([A:999]([#1])([#1])[#1])";
const phenyl = "$([A:999]=1C([#1])=C([#1])C([#1])=C([#1])C([#1])=1)";
const t_butyl = "$([A:999]([#1])(C([#1])([#1])[#1])(C([#1])([#1])[#1])C([#1])([#1])[#1])";
const vinyl_D = "$([A:999]=[C]-([C:Z1]O[*;Z2:999]O[C:Z1])||$([A:999]=[C]-([*;Z1:999]O[#1])||$([A:999]=[C]-([C;Z1][N;z0][C;z2]=O)||$([A:999]=[C]-([*:999][N+0;X3]([#1])[#1])||$([A:999]=[C]-([*:999][N+0;X3]([#6])[#1])||$([A:999]=[C]-([*:999][N+0;X3]([#6])[#6])||$([A:999]=[C]-([*;z2:999](=O)O[#6])||$([A:999]=[C]-([*:999][N;X3][F,Cl,Br,I])||$([A:999]=[C]-([C;Z1]O[*;Z2:999]O[#1][C;Z1])||$([A:999]=[C]-([*:999][N;R0]O[#1])||$([A:999]=[C]-([A:999]NC(=O)O[C;z1])||$([A:999]=[C]-([A:999]N(C)C(=O)N(C)C)||$([A:999]=[C]-([A:999]NC(=O)N([#1])),$([A:999]=[C]-([A:999]N([#1])C(=O)N)||$([A:999]=[C]-([A:999]OC(=O)N)||$([A:999]=[C]-([A:999]OC(=O)O[C;z1])||$([A:999]=[C]-([*:999]P([#6;#1])[#6;#1])||$([A:999]=[C]-([*:999][Se+0;v2][#6])||$([A:999]=[C]-([*:999][S+0;v2][#6])||$([A:999]=[C]-([*;Z1:999][S;v2][#1])||$([A:999]=[C]-([*:999]O[Si](C)(C)C])||$([A:999]=[C]-([A:999]=[C]))";
const vinyl_W = "$([A:999]=[C]-([A:999](=O)[F,Cl,Br,I])||$([A:999]=[C]-([A:999](=O)[#1])||$([A:999]=[C]-([A:999](=O)[N;z0]([#1])[#1])||$([A:999]=[C]-([A:999](=O)[N;z0]([#1])[!#1])||$([A:999]=[C]-([A:999](=O)[N;z0]([!#1])[!#1])||$([A:999]=[C]-([C;Z1][N;z0][C;z2]=O)||$([A:999]=[C]-([*:999](=O)OC=O)||$([A:999]=[C]-([#6+;X3:999])||$([A:999]=[C]-([*;!R;z2:999](=O)[O;!R][C;!R])||$([A:999]=[C]-([#6][*:999](=O)[#6])||$([A:999]=[C]-([*;R0:999]=N[#1,#6])||$([A:999]=[C]-([*:999]#N)||$([A:999]=[C]-([*;R0:999]=NO[#1])||$([A:999]=[C]-([A;z{2-3}:999](=O)S[#6])||$([A:999]=[C]-([*;Z1:999]([F,Cl,Br,I])([F,Cl,Br,I])[F,Cl,Br,I])||$([A:999]=[C]-([A:999]=[C])||$([A:999]=[C]-([*:999][N+](=O)[O-])||$([A:999]=[C]-([*:999][S+0;v6](=O)(=O)O)||$([A:999]=[C]-([*:999][S+0;v6;X4](=O)(=O)[#6])||$([A:999]=[C]-([*:999][S+0;v4](=O)[#6]))";
*/