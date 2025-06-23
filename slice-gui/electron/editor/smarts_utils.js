var flavor;
var mol;
var molID;
var str;
var pos;
var atom;
var bond;
var atomIndex;
var bondIndex;
var form;
var returnedReference;
var array;
var cpt;
var isSpecific;
var op = "or";
var not = false;

let stack;

var FLAVOR_LOOSE = 0x01;
var FLAVOR_DAYLIGHT = 0x02;
var FLAVOR_CACTVS = 0x04;
var FLAVOR_MOE = 0x08;
var FLAVOR_OECHEM = 0x10;
var FLAVOR_CDK = FLAVOR_LOOSE;
var FLAVOR_CDK_LEGACY = 0x40;

window.parse = function (molecule, smarts) {
    flavor = FLAVOR_CACTVS;
    mol = molecule;
    molID = mol.id;
    str = smarts;
    pos = 0;
    atomIndex = 0;
    bondIndex = 0;
    array = [];
    stack = [];
    cpt = 0;
    parse();
}

window.parseAtomSmarts = async function (at) {
    flavor = FLAVOR_CACTVS;
    atom = at;
    //separate chem env and atom smarts
    var arr = at.smarts.split("[");
    //console.log(at.smarts);
    str = "[" + arr[0] + "]";
    pos = 0;
    array = [];
    stack = [];
    cpt = 0;
    op = "or";
    createAtomEditor();
    parse();
    if (arr.length == 2) {
        arr[1] = arr[1].slice(0, -1);
        parseChemEnv(arr[1]);
    }
    atom.specificProperties = specificProperties;
    atom.commonProperties = commonProperties;
    makeLocalSmarts();
}

window.parseAtomSmartsWithoutCreatingEditor = function (at) {
    flavor = FLAVOR_CACTVS;
    atom = at;
    //separate chem env and atom smarts
    var arr = at.smarts.split("[");
    //console.log(at.smarts);
    str = "[" + arr[0] + "]";
    pos = 0;
    array = [];
    stack = [];
    cpt = 0;
    op = "or";
    parse();
    if (arr.length == 2) {
        arr[1] = arr[1].slice(0, -1);
        parseChemEnv(arr[1]);
    }
    atom.specificProperties = specificProperties;
    atom.commonProperties = commonProperties;
    makeLocalSmarts();
}

window.parseBondSmarts = function (b) {
    flavor = FLAVOR_CACTVS;
    bond = b;
    str = b.smarts;
    pos = 0;
    array = [];
    stack = [];
    cpt = 0;
    op = "or";
    createBondEditor();
    parse();
    bond.specificProperties = specificProperties;
    bond.commonProperties = commonProperties;
    makeLocalSmarts();
}

function createAtomEditor() {
    //old way
    //chemObjId = molID+'_Kekule.Atom_'+atom.id+'_editor';
    chemObjId = 'Kekule.Atom_' + atom.id + '_editor';
    //console.log(chemObjId, str);
    array.push(atom);
    if (newSelectedChemObject != null) {
        newSelectedChemObject.commonProperties = commonProperties;
        newSelectedChemObject.specificProperties = specificProperties;
    }
    newSelectedChemObject = atom;
    commonProperties = [];
    specificProperties = [];
    form = createChemObjEditor(chemObjId, atom);
    document.getElementById(chemObjId).style.display = "none";
}

function createBondEditor() {
    //old way
    //chemObjId = molID+'_Kekule.Bond_'+bond.id+'_editor';
    chemObjId = 'Kekule.Bond_' + bond.id + '_editor';
    //console.log(chemObjId, str);
    if (newSelectedChemObject != null) {
        newSelectedChemObject.commonProperties = commonProperties;
    }
    newSelectedChemObject = bond;
    commonProperties = [];
    specificProperties = [];
    form = createChemObjEditor(chemObjId, bond);
    document.getElementById(chemObjId).style.display = "none";
}

//used when the whole molecule was parsed
function createAtomEditor_OLD() {
    atom = mol.getNodeAt(atomIndex);
    //console.log("index ",atomIndex,atom);
    //old way
    //chemObjId = molID+'_Kekule.Atom_'+atom.id+'_editor';
    chemObjId = 'Kekule.Atom_' + atom.id + '_editor';
    if (newSelectedChemObject != null) {
        newSelectedChemObject.commonProperties = commonProperties;
        newSelectedChemObject.specificProperties = specificProperties;
    }
    newSelectedChemObject = atom;
    commonProperties = [];
    specificProperties = [];
    form = createChemObjEditor(chemObjId, atom);
    if (stack.length != 0) {
        stack.pop();
        form = createChemObjEditor(chemObjId, bond);
        bondIndex++;
    }
    stack.push(atom);
    atomIndex++;
}

//used when the whole molecule was parsed
function createBondEditor_OLD() {
    bond = mol.getNodeAt(bondIndex);
    //old way
    //chemObjId = molID+'_Kekule.Bond_'+bond.id+'_editor';
    chemObjId = 'Kekule.Bond_' + bond.id + '_editor';
    if (newSelectedChemObject != null) {
        newSelectedChemObject.commonProperties = commonProperties;
    }
    newSelectedChemObject = bond;
    commonProperties = [];
    specificProperties = [];
    form = createChemObjEditor(chemObjId, bond);
}

function parseChemEnv(envs) {
    var chemEnv = "";
    var chemEnvSimplified = "";
    var or_arr = envs.split(",");
    for (var i = 0, l = or_arr.length; i < l; i++) {
        if (or_arr[i].length == 0)
            continue;
        var and_arr = or_arr[i].split(";");
        var andButton = addSpecificAtomEnvPropertyBlock(chemObjId);
        var group;
        var isFirstEnvInit = false;
        for (var j = 0, m = and_arr.length; j < m; j++) {
            var groupName = and_arr[j];
            if (groupName.length == 0) {
                continue;
            }
            var envDiv;
            if (!isFirstEnvInit) {
                envDiv = andButton.parentNode.childNodes[0];
                group = envDiv.childNodes[3];
                isFirstEnvInit = true;
            } else {
                envDiv = addOneSpecificAtomEnvProperty(andButton, i);
                group = envDiv.childNodes[3];
            }
            let groupNameList = Object.keys(functionalGroupProperties);
            let indexGroup;
            let not = "";
            if (groupName.includes("!")) {
                var is = envDiv.childNodes[2].childNodes[0];
                is.checked = true;
                not += "!"
                groupName = groupName.replaceAll("!","");
                indexGroup = groupNameList.indexOf(groupName);
            }
            else {
                indexGroup = groupNameList.indexOf(groupName);
            }
            if (indexGroup < 0) {
                group.selectedIndex = groupNameList.indexOf("customized");
                envDiv.childNodes[4].value = groupName;
            } else {
                group.selectedIndex = indexGroup;
                envDiv.childNodes[4].value = functionalGroupProperties[groupName];
            }
            chemEnv += envDiv.childNodes[4].value + ";";
            fileOnLoad = false;
            if (groupName === "customized") {
                chemEnvSimplified += envDiv.childNodes[4].value + ";";
                storePropertyInArray(envDiv.childNodes[4], false);
            } else {
                chemEnvSimplified += not + groupName + ";";
                storePropertyInArray(envDiv.childNodes[1], false);
            }
            fileOnLoad = true;
        }
    }
    atom.chemEnv = chemEnv;
    atom.chemEnvSimplified = chemEnvSimplified;
}

function addAtomProp(field, values) {
    fileOnLoad = true;
    var div;
    //console.log(field,values,chemObjId,isSpecific,op);
    if (field === "Atom Mapping") {
        //document.getElementById(chemObjId+"_cAAM").value = values[0];
        domDict[chemObjId + "_cAAM"].value = values[0];
        let atomMappingNumber = parseInt(values[0], 10);
        atom.setAtomMappingNumber(atomMappingNumber);
        commonProperties["aam"] = ":" + atomMappingNumber;
        //console.log("SMARTS",lsmarts);
    } else {
        if (isSpecific) {
            if (op === "or") {
                cpt = 0;
                returnedReference = addSpecificAtomPropertyBlock(chemObjId);
            }
            div = addOneSpecificAtomProperty(returnedReference, returnedReference.id.split('-').pop());
            var children = div.childNodes;
            var select = children[1];
            //select value
            select.selectedIndex = atomProperties.indexOf(field);
            showSelectParameters(select.id);
            //console.log(isSpecific,op,div.childNodes);
            //check is checkbox if needed
            var is = children[2].childNodes[0];
            if (not) {
                is.checked = true;
            }
            if (field === "Chemical Element") {
                var aromaticity = children[3];
                aromaticity.innerHTML = values[0];
                aromaticity.setAttribute("value", values[0]);
                var symbol = children[4];
                symbol.innerHTML = values[1];
                symbol.value = values[1];
            } else if (field === "Chemical Environment") {
                //select value
                var group = children[3];
                let groupNameList = Object.keys(functionalGroupProperties);
                //init customized smarts
                if (values[0] === "customized") {
                    group.selectedIndex = groupNameList.indexOf("customized");
                    var sma = children[4];
                    sma.value = values[1];
                } else {
                    //get key by its value (get group name by its SMARTS)
                    let groupName = groupNameList.find(key => functionalGroupProperties[key] === values[0]);
                    group.selectedIndex = groupNameList.indexOf(groupName);
                }
            } else {
                if (values.length == 1) {
                    var equal = children[4];
                    equal.value = values[0];
                } else {
                    children[3].childNodes[0].checked = true;
                    var interval = children[5];
                    var iChildren = interval.childNodes;
                    var lower = iChildren[1];
                    var higher = iChildren[3];
                    lower.value = values[0];
                    higher.value = values[1];
                    children[4].style.display = "none";
                    interval.style.display = "inline-block";
                }
            }
            fileOnLoad = false;
            storePropertyInArray(select, false);
        } else {
            fileOnLoad = false;
            div = addCommonAtomPropertyBlock(chemObjId);
        }
        //changer chemblock par reference et modifier return addonprop par return addButton
        //console.log(isSpecific,op,returnedReference.id);
        cpt++;
        op = "and";
        not = false;
    }
}

function addBondProp(field, values) {
    //console.log(field,values,chemObjId,isSpecific,op);
    fileOnLoad = true;
    var div;
    if (op === "or") {
        cpt = 0;
        returnedReference = addSpecificBondPropertyBlock(chemObjId);
    }
    div = addOneSpecificBondProperty(returnedReference, returnedReference.id.split('-').pop());
    var children = div.childNodes;
    var select = children[1];
    //select value
    select.selectedIndex = bondProperties.indexOf(field);
    showSelectParameters(select.id);
    //check is checkbox if needed
    var is = children[2].childNodes[0];
    if (not) {
        is.checked = true;
    }
    if (field === "Electrons Involved In Bond") {
        if (values.length == 1) {
            var equal = children[4];
            equal.value = values[0];
        } else {
            children[3].childNodes[0].checked = true;
            var interval = children[5];
            var iChildren = interval.childNodes;
            var lower = iChildren[1];
            var higher = iChildren[3];
            lower.value = values[0];
            higher.value = values[1];
            children[4].style.display = "none";
            interval.style.display = "inline-block";
        }
    }
    fileOnLoad = false;
    storePropertyInArray(select, false);
    cpt++;
    op = "and";
    not = false;
}

function checkSpecific(st) {
    var sub = st.substring(0, st.indexOf("]"));
    if (isCommonSectionActivated()) {
        isSpecific = sub.includes(";") ? true : false;
    } else {
        isSpecific = true;
    }
}

function isCommonSectionActivated() {
    var isLabelPresent = document.getElementById("atomCommonPropertyLabelAdd-" + chemObjId);
    if (isLabelPresent) {
        return true;
    } else {
        return false;
    }
}

function parse() {
    while (pos < str.length) {
        switch (str.charAt(pos++)) {
            case '[':
                returnedReference = null;
                cpt = 0;
                checkSpecific(str.substring(pos));
                parseAtom('\0');
                break;
            case '.':
                newFragment();
                break;
            case '-':
            case '=':
            case '#':
            case '$':
            case ':':
            case '@':
            case '~':
            case '!':
            case '/':
            case '\\':
                unget();
                parseBond();
                break;

            case '(':
                stack.push(stack[stack.length - 1])
                break;
            case ')':
                break;

            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                break;
            case '%':
                break;

            case '>':
                prev = null;
                break;

            case ' ':
            case '\t':
                while (true) {
                    if (isTerminalChar(next()))
                        break;
                }
                mol.setTitle(str.substring(pos - 1));
                break;
            case '\r':
            case '\n':
            case '\0':
                return finish();

            default:
                //for atoms not in [] -> no need of a dedicated window as it's just a symbol
                //createAtomEditor();
                //parseAtom('\0');
                return false;
        }
    }
    return finish();
}

//for molecule parsing OLD WAY
function parse_OLD() {
    while (pos < str.length) {
        switch (str.charAt(pos++)) {
            case '[':
                returnedReference = null;
                cpt = 0;
                checkSpecific(str.substring(pos));
                createAtomEditor();
                parseAtom('\0');
                break;
            case '.':
                newFragment();
                break;
            case '-':
            case '=':
            case '#':
            case '$':
            case ':':
            case '@':
            case '~':
            case '!':
            case '/':
            case '\\':
                unget();
                parseBond();
                break;

            case '(':
                stack.push(stack[stack.length - 1])
                break;
            case ')':
                break;

            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                break;
            case '%':
                break;

            case '>':
                prev = null;
                break;

            case ' ':
            case '\t':
                while (true) {
                    if (isTerminalChar(next()))
                        break;
                }
                mol.setTitle(str.substring(pos - 1));
                break;
            case '\r':
            case '\n':
            case '\0':
                return finish();

            default:
                //for atoms not in [] -> no need of a dedicated window as it's just a symbol
                //createAtomEditor();
                //parseAtom('\0');
                return false;
        }
    }
    return finish();
}

function parseAtom(lastOp) {
    var num;
    var currOp;
    while (true) {
        currOp = '&'; // implicit and
        switch (next()) {
            case '*':
                addAtomProp("Chemical Element", ["either", "*"]);
                break;
            case 'A':
                switch (next()) {
                    case 'c': // Ac=Actinium
                        addAtomProp("Chemical Element", ["aliphatic", "Ac"]);
                        break;
                    case 'g': // Ag=Silver
                        addAtomProp("Chemical Element", ["aliphatic", "Ag"]);
                        break;
                    case 'l': // Al=Aluminum
                        addAtomProp("Chemical Element", ["aliphatic", "Al"]);
                        break;
                    case 'm': // Am=Americium
                        addAtomProp("Chemical Element", ["aliphatic", "Am"]);
                        break;
                    case 'r': // Ar=Argon
                        addAtomProp("Chemical Element", ["aliphatic", "Ar"]);
                        break;
                    case 's': // As=Arsenic
                        addAtomProp("Chemical Element", ["aliphatic", "As"]);
                        break;
                    case 't': // At=Astatine
                        addAtomProp("Chemical Element", ["aliphatic", "At"]);
                        break;
                    case 'u': // Au=Gold
                        addAtomProp("Chemical Element", ["aliphatic", "Au"]);
                        break;
                    default: // A=None
                        unget();
                        addAtomProp("Chemical Element", ["aliphatic", "A"]);
                        break;
                }
                break;
            case 'B':
                switch (next()) {
                    case 'a': // Ba=Barium
                        addAtomProp("Chemical Element", ["aliphatic", "Ba"]);
                        break;
                    case 'e': // Be=Beryllium
                        addAtomProp("Chemical Element", ["aliphatic", "Be"]);
                        break;
                    case 'h': // Bh=Bohrium
                        addAtomProp("Chemical Element", ["aliphatic", "Bh"]);
                        break;
                    case 'i': // Bi=Bismuth
                        addAtomProp("Chemical Element", ["aliphatic", "Bi"]);
                        break;
                    case 'k': // Bk=Berkelium
                        addAtomProp("Chemical Element", ["aliphatic", "Bk"]);
                        break;
                    case 'r': // Br=Bromine
                        addAtomProp("Chemical Element", ["aliphatic", "Br"]);
                        break;
                    default: // B=Boron
                        unget();
                        addAtomProp("Chemical Element", ["aliphatic", "B"]);
                        break;
                }
                break;
            case 'C':
                switch (next()) {
                    case 'a': // Ca=Calcium
                        addAtomProp("Chemical Element", ["aliphatic", "Ca"]);
                        break;
                    case 'd': // Cd=Cadmium
                        addAtomProp("Chemical Element", ["aliphatic", "Cd"]);
                        break;
                    case 'e': // Ce=Cerium
                        addAtomProp("Chemical Element", ["aliphatic", "Ce"]);
                        break;
                    case 'f': // Cf=Californium
                        addAtomProp("Chemical Element", ["aliphatic", "Cf"]);
                        break;
                    case 'l': // Cl=Chlorine
                        addAtomProp("Chemical Element", ["aliphatic", "Cl"]);
                        break;
                    case 'm': // Cm=Curium
                        addAtomProp("Chemical Element", ["aliphatic", "Cm"]);
                        break;
                    case 'n': // Cn=Copernicium
                        addAtomProp("Chemical Element", ["aliphatic", "Cn"]);
                        break;
                    case 'o': // Co=Cobalt
                        addAtomProp("Chemical Element", ["aliphatic", "Co"]);
                        break;
                    case 'r': // Cr=Chromium
                        addAtomProp("Chemical Element", ["aliphatic", "Cr"]);
                        break;
                    case 's': // Cs=Cesium
                        addAtomProp("Chemical Element", ["aliphatic", "Cs"]);
                        break;
                    case 'u': // Cu=Copper
                        addAtomProp("Chemical Element", ["aliphatic", "Cu"]);
                        break;
                    default: // C=Carbon
                        unget();
                        addAtomProp("Chemical Element", ["aliphatic", "C"]);
                        break;
                }
                break;
            case 'D':
                switch (next()) {
                    case 'b': // Db=Dubnium
                        addAtomProp("Chemical Element", ["aliphatic", "Db"]);
                        break;
                    case 's': // Ds=Darmstadtium
                        addAtomProp("Chemical Element", ["aliphatic", "Ds"]);
                        break;
                    case 'y': // Dy=Dysprosium
                        addAtomProp("Chemical Element", ["aliphatic", "Dy"]);
                        break;
                    default: // D=Degree
                        unget();
                        num = nextUnsignedInt();
                        //TODO differentiate both flavor
                        if (num < 0) {
                            // CACTVS style ranges D{0-2}
                            if (peek() === '{') {
                                var val = parseRange();
                                if (!val)
                                    return false;
                                addAtomProp("Degree", [val[0], val[1]]);
                            }
                        } else {
                            if (isFlavor(FLAVOR_CDK_LEGACY))
                                addAtomProp("Degree", [num]);
                            else
                                addAtomProp("Degree", [num]);
                        }
                        break;
                }
                break;
            case 'E':
                switch (next()) {
                    case 'r': // Er=Erbium
                        addAtomProp("Chemical Element", ["aliphatic", "Er"]);
                        break;
                    case 's': // Es=Einsteinium
                        addAtomProp("Chemical Element", ["aliphatic", "Es"]);
                        break;
                    case 'u': // Eu=Europium
                        addAtomProp("Chemical Element", ["aliphatic", "Eu"]);
                        break;
                    default: // E=None
                        return false;
                }
                break;
            case 'F':
                switch (next()) {
                    case 'e': // Fe=Iron
                        addAtomProp("Chemical Element", ["aliphatic", "Fe"]);
                        break;
                    case 'l': // Fl=Flerovium
                        addAtomProp("Chemical Element", ["aliphatic", "Fl"]);
                        break;
                    case 'm': // Fm=Fermium
                        addAtomProp("Chemical Element", ["aliphatic", "Fm"]);
                        break;
                    case 'r': // Fr=Francium
                        addAtomProp("Chemical Element", ["aliphatic", "Fr"]);
                        break;
                    default: // F=Fluorine
                        unget();
                        addAtomProp("Chemical Element", ["aliphatic", "F"]);
                        break;
                }
                break;
            case 'G':
                switch (next()) {
                    case 'a': // Ga=Gallium
                        addAtomProp("Chemical Element", ["aliphatic", "Ga"]);
                        break;
                    case 'd': // Gd=Gadolinium
                        addAtomProp("Chemical Element", ["aliphatic", "Gd"]);
                        break;
                    case 'e': // Ge=Germanium
                        addAtomProp("Chemical Element", ["aliphatic", "Ge"]);
                        break;
                    default: // G=None or Periodic Group or Insaturation
                        unget();
                        num = nextUnsignedInt();
                        if (num <= 0 || num > 18)
                            return false;
                        if (isFlavor(FLAVOR_CDK_LEGACY))
                            addAtomProp("Periodic Group", [num]);
                        else if (isFlavor(FLAVOR_CACTVS))
                            addAtomProp("Insaturation", [num]);
                        else if (isFlavor(SLICE))
                            addAtomProp("Periodic Group", [num]);
                        else
                            return false;
                        break;
                }
                break;
            case 'H':
                switch (next()) {
                    case 'e': // He=Helium
                        addAtomProp("Chemical Element", ["aliphatic", "He"]);
                        break;
                    case 'f': // Hf=Hafnium
                        addAtomProp("Chemical Element", ["aliphatic", "Hf"]);
                        break;
                    case 'g': // Hg=Mercury
                        addAtomProp("Chemical Element", ["aliphatic", "Hg"]);
                        break;
                    case 'o': // Ho=Holmium
                        addAtomProp("Chemical Element", ["aliphatic", "Ho"]);
                        break;
                    case 's': // Hs=Hassium
                        addAtomProp("Chemical Element", ["aliphatic", "Hs"]);
                        break;
                    default: // H=Hydrogen
                        unget();
                        num = nextUnsignedInt();
                        if (num < 0) {
                            // CACTVS style ranges H{0-2}
                            if (peek() === '{') {
                                var val = parseRange();
                                if (!val)
                                    return false;
                                addAtomProp("Total Hydrogen Count", [val[0], val[1]]);
                            }
                        } else
                            addAtomProp("Total Hydrogen Count", [num]);
                        break;
                }
                break;
            case 'I':
                switch (next()) {
                    case 'n': // In=Indium
                        addAtomProp("Chemical Element", ["aliphatic", "In"]);
                        break;
                    case 'r': // Ir=Iridium
                        addAtomProp("Chemical Element", ["aliphatic", "Ir"]);
                        break;
                    default: // I=Iodine
                        unget();
                        addAtomProp("Chemical Element", ["aliphatic", "I"]);
                        break;
                }
                break;
            case 'K':
                switch (next()) {
                    case 'r': // Kr=Krypton
                        addAtomProp("Chemical Element", ["aliphatic", "Kr"]);
                        break;
                    default: // K=Potassium
                        unget();
                        addAtomProp("Chemical Element", ["aliphatic", "K"]);
                        break;
                }
                break;
            case 'L':
                switch (next()) {
                    case 'a': // La=Lanthanum
                        addAtomProp("Chemical Element", ["aliphatic", "La"]);
                        break;
                    case 'i': // Li=Lithium
                        addAtomProp("Chemical Element", ["aliphatic", "Li"]);
                        break;
                    case 'r': // Lr=Lawrencium
                        addAtomProp("Chemical Element", ["aliphatic", "Lr"]);
                        break;
                    case 'u': // Lu=Lutetium
                        addAtomProp("Chemical Element", ["aliphatic", "Lu"]);
                        break;
                    case 'v': // Lv=Livermorium
                        addAtomProp("Chemical Element", ["aliphatic", "Lv"]);
                        break;
                    default: // L=None
                        return false;
                }
                break;
            case 'M':
                switch (next()) {
                    case 'c': // Mc=Moscovium
                        addAtomProp("Chemical Element", ["aliphatic", "Mc"]);
                        break;
                    case 'd': // Md=Mendelevium
                        addAtomProp("Chemical Element", ["aliphatic", "Md"]);
                        break;
                    case 'g': // Mg=Magnesium
                        addAtomProp("Chemical Element", ["aliphatic", "Mg"]);
                        break;
                    case 'n': // Mn=Manganese
                        addAtomProp("Chemical Element", ["aliphatic", "Mn"]);
                        break;
                    case 'o': // Mo=Molybdenum
                        addAtomProp("Chemical Element", ["aliphatic", "Mo"]);
                        break;
                    case 't': // Mt=Meitnerium
                        addAtomProp("Chemical Element", ["aliphatic", "Mt"]);
                        break;
                    default: // M=None
                        return false;
                }
                break;
            case 'N':
                switch (next()) {
                    case 'a': // Na=Sodium
                        addAtomProp("Chemical Element", ["aliphatic", "Na"]);
                        break;
                    case 'b': // Nb=Niobium
                        addAtomProp("Chemical Element", ["aliphatic", "Nb"]);
                        break;
                    case 'd': // Nd=Neodymium
                        addAtomProp("Chemical Element", ["aliphatic", "Nd"]);
                        break;
                    case 'e': // Ne=Neon
                        addAtomProp("Chemical Element", ["aliphatic", "Ne"]);
                        break;
                    case 'h': // Nh=Nihonium
                        addAtomProp("Chemical Element", ["aliphatic", "Nh"]);
                        break;
                    case 'i': // Ni=Nickel
                        addAtomProp("Chemical Element", ["aliphatic", "Ni"]);
                        break;
                    case 'o': // No=Nobelium
                        addAtomProp("Chemical Element", ["aliphatic", "No"]);
                        break;
                    case 'p': // Np=Neptunium
                        addAtomProp("Chemical Element", ["aliphatic", "Np"]);
                        break;
                    default: // N=Nitrogen
                        unget();
                        addAtomProp("Chemical Element", ["aliphatic", "N"]);
                        break;
                }
                break;
            case 'O':
                switch (next()) {
                    case 'g': // Og=Oganesson
                        addAtomProp("Chemical Element", ["aliphatic", "Og"]);
                        break;
                    case 's': // Os=Osmium
                        addAtomProp("Chemical Element", ["aliphatic", "Os"]);
                        break;
                    default: // O=Oxygen
                        unget();
                        addAtomProp("Chemical Element", ["aliphatic", "O"]);
                        break;
                }
                break;
            case 'P':
                switch (next()) {
                    case 'a': // Pa=Protactinium
                        addAtomProp("Chemical Element", ["aliphatic", "Pa"]);
                        break;
                    case 'b': // Pb=Lead
                        addAtomProp("Chemical Element", ["aliphatic", "Pb"]);
                        break;
                    case 'd': // Pd=Palladium
                        addAtomProp("Chemical Element", ["aliphatic", "Pd"]);
                        break;
                    case 'm': // Pm=Promethium
                        addAtomProp("Chemical Element", ["aliphatic", "Pm"]);
                        break;
                    case 'o': // Po=Polonium
                        addAtomProp("Chemical Element", ["aliphatic", "Po"]);
                        break;
                    case 'r': // Pr=Praseodymium
                        addAtomProp("Chemical Element", ["aliphatic", "Pr"]);
                        break;
                    case 't': // Pt=Platinum
                        addAtomProp("Chemical Element", ["aliphatic", "Pt"]);
                        break;
                    case 'u': // Pu=Plutonium
                        addAtomProp("Chemical Element", ["aliphatic", "Pu"]);
                        break;
                    default: // P=Phosphorus
                        unget();
                        addAtomProp("Chemical Element", ["aliphatic", "P"]);
                        break;
                }
                break;
            case 'Q':
                return false;
            case 'R':
                switch (next()) {
                    case 'a': // Ra=Radium
                        addAtomProp("Chemical Element", ["aliphatic", "Ra"]);
                        break;
                    case 'b': // Rb=Rubidium
                        addAtomProp("Chemical Element", ["aliphatic", "Rb"]);
                        break;
                    case 'e': // Re=Rhenium
                        addAtomProp("Chemical Element", ["aliphatic", "Re"]);
                        break;
                    case 'f': // Rf=Rutherfordium
                        addAtomProp("Chemical Element", ["aliphatic", "Rf"]);
                        break;
                    case 'g': // Rg=Roentgenium
                        addAtomProp("Chemical Element", ["aliphatic", "Rg"]);
                        break;
                    case 'h': // Rh=Rhodium
                        addAtomProp("Chemical Element", ["aliphatic", "Rh"]);
                        break;
                    case 'n': // Rn=Radon
                        addAtomProp("Chemical Element", ["aliphatic", "Rn"]);
                        break;
                    case 'u': // Ru=Ruthenium
                        addAtomProp("Chemical Element", ["aliphatic", "Ru"]);
                        break;
                    default: // R=Ring Count
                        unget();
                        num = nextUnsignedInt();
                        if (num < 0) {
                            // CACTVS style ranges R{0-2}
                            if (peek() === '{') {
                                var val = parseRange();
                                if (!val)
                                    return false;
                                addAtomProp("Ring Count", [val[0], val[1]]);
                            }
                        } else if (num == 0)
                            addAtomProp("Ring Count", [num]);
                        else if (isFlavor(FLAVOR_OECHEM))
                            addAtomProp("Ring Bond Count", [num]);
                        else
                            addAtomProp("Ring Count", [num]);
                        break;
                }
                break;
            case 'S':
                switch (next()) {
                    case 'b': // Sb=Antimony
                        addAtomProp("Chemical Element", ["aliphatic", "Sb"]);
                        break;
                    case 'c': // Sc=Scandium
                        addAtomProp("Chemical Element", ["aliphatic", "Sc"]);
                        break;
                    case 'e': // Se=Selenium
                        addAtomProp("Chemical Element", ["aliphatic", "Se"]);
                        break;
                    case 'g': // Sg=Seaborgium
                        addAtomProp("Chemical Element", ["aliphatic", "Sg"]);
                        break;
                    case 'i': // Si=Silicon
                        addAtomProp("Chemical Element", ["aliphatic", "Si"]);
                        break;
                    case 'm': // Sm=Samarium
                        addAtomProp("Chemical Element", ["aliphatic", "Sm"]);
                        break;
                    case 'n': // Sn=Tin
                        addAtomProp("Chemical Element", ["aliphatic", "Sn"]);
                        break;
                    case 'r': // Sr=Strontium
                        addAtomProp("Chemical Element", ["aliphatic", "Sr"]);
                        break;
                    default: // S=Sulfur
                        unget();
                        addAtomProp("Chemical Element", ["aliphatic", "S"]);
                        break;
                }
                break;
            case 'T':
                switch (next()) {
                    case 'a': // Ta=Tantalum
                        addAtomProp("Chemical Element", ["aliphatic", "Ta"]);
                        break;
                    case 'b': // Tb=Terbium
                        addAtomProp("Chemical Element", ["aliphatic", "Tb"]);
                        break;
                    case 'c': // Tc=Technetium
                        addAtomProp("Chemical Element", ["aliphatic", "Tc"]);
                        break;
                    case 'e': // Te=Tellurium
                        addAtomProp("Chemical Element", ["aliphatic", "Te"]);
                        break;
                    case 'h': // Th=Thorium
                        addAtomProp("Chemical Element", ["aliphatic", "Th"]);
                        break;
                    case 'i': // Ti=Titanium
                        addAtomProp("Chemical Element", ["aliphatic", "Ti"]);
                        break;
                    case 'l': // Tl=Thallium
                        addAtomProp("Chemical Element", ["aliphatic", "Tl"]);
                        break;
                    case 'm': // Tm=Thulium
                        addAtomProp("Chemical Element", ["aliphatic", "Tm"]);
                        break;
                    case 's': // Ts=Tennessine
                        addAtomProp("Chemical Element", ["aliphatic", "Ts"]);
                        break;
                    default: // T=None
                        return false;
                }
                break;
            case 'U':
                switch (next()) {
                    default: // U=Uranium
                        unget();
                        addAtomProp("Chemical Element", ["aliphatic", "U"]);
                        break;
                }
                break;
            case 'V':
                switch (next()) {
                    default: // V=Vanadium
                        unget();
                        addAtomProp("Chemical Element", ["aliphatic", "V"]);
                        break;
                }
                break;
            case 'W':
                switch (next()) {
                    default: // W=Tungsten
                        unget();
                        addAtomProp("Chemical Element", ["aliphatic", "W"]);
                        break;
                }
                break;
            case 'X':
                switch (next()) {
                    case 'e': // Xe=Xenon
                        addAtomProp("Chemical Element", ["aliphatic", "Xe"]);
                        break;
                    default: // X=Connectivity
                        unget();
                        num = nextUnsignedInt();
                        if (num < 0) {
                            // CACTVS style ranges X{0-2}
                            if (peek() === '{') {
                                var val = parseRange();
                                if (!val)
                                    return false;
                                addAtomProp("Connectivity", [val[0], val[1]]);
                            }
                        } else
                            addAtomProp("Connectivity", [num]);
                        break;
                }
                break;
            case 'Y':
                switch (next()) {
                    case 'b': // Yb=Ytterbium
                        addAtomProp("Chemical Element", ["aliphatic", "Yb"]);
                        break;
                    default: // Y=Yttrium
                        unget();
                        addAtomProp("Chemical Element", ["aliphatic", "Y"]);
                        break;
                }
                break;
            case 'Z':
                switch (next()) {
                    case 'n': // Zn=Zinc
                        addAtomProp("Chemical Element", ["aliphatic", "Zn"]);
                        break;
                    case 'r': // Zr=Zirconium
                        addAtomProp("Chemical Element", ["aliphatic", "Zr"]);
                        break;
                    default: // Z=None
                        unget();
                        num = nextUnsignedInt();
                        if (isFlavor(FLAVOR_DAYLIGHT)) {
                            addAtomProp("Ring Size", [num]);
                        } else if (isFlavor(FLAVOR_CACTVS)) {
                            if (num < 0) {
                                // CACTVS style ranges X{0-2}
                                if (peek() === '{') {
                                    var val = parseRange();
                                    if (!val)
                                        return false;
                                    addAtomProp("Aliphatic Hetero Substituent Count", [val[0], val[1]]);
                                }
                            } else if (num == 0)
                                addAtomProp("Aliphatic Hetero Substituent Count", [num]);
                            else
                                addAtomProp("Aliphatic Hetero Substituent Count", [num]);
                            break;
                        } else {
                            return false;
                        }
                        break;
                }
                break;
            case 'a':
                switch (next()) {
                    case 'l': // al=Aluminum (aromatic)
                        addAtomProp("Chemical Element", ["aromatic", "al"]);
                        break;
                    case 's': // as=Arsenic (aromatic)
                        addAtomProp("Chemical Element", ["aromatic", "as"]);
                        break;
                    default:
                        unget();
                        addAtomProp("Chemical Element", ["aromatic", "a"]);
                        break;
                }
                break;
            case 'b':
                switch (next()) {
                    default: // b=Boron (aromatic)
                        unget();
                        addAtomProp("Chemical Element", ["aromatic", "b"]);
                        break;
                }
                break;
            case 'c':
                addAtomProp("Chemical Element", ["aromatic", "c"]);
                break;
            case 'e':
                if (!isFlavor(FLAVOR_CACTVS)) {
                    return false;
                }
                num = nextUnsignedInt();
                if (num < 0) {
                    // CACTVS style ranges X{0-2}
                    if (peek() === '{') {
                        var val = parseRange();
                        if (!val)
                            return false;
                        addAtomProp("Pi Electron Count", [val[0], val[1]]);
                    }
                } else if (num == 0)
                    addAtomProp("Pi Electron Count", [num]);
                else
                    addAtomProp("Pi Electron Count", [num]);
                break;
            case 'n':
                addAtomProp("Chemical Element", ["aromatic", "n"]);
                break;
            case 'o':
                addAtomProp("Chemical Element", ["aromatic", "o"]);
                break;
            case 'p':
                addAtomProp("Chemical Element", ["aromatic", "p"]);
                break;
            case 's':
                switch (next()) {
                    case 'b': // sb=Antimony (aromatic)
                        addAtomProp("Chemical Element", ["aromatic", "sb"]);
                        break;
                    case 'e': // se=Selenium (aromatic)
                        addAtomProp("Chemical Element", ["aromatic", "se"]);
                        break;
                    case 'i': // si=Silicon (aromatic)
                        addAtomProp("Chemical Element", ["aromatic", "si"]);
                        break;
                    default: // s=Sulfur (aromatic)
                        unget();
                        addAtomProp("Chemical Element", ["aromatic", "s"]);
                        break;
                }
                break;
            case 't':
                switch (next()) {
                    case 'e': // te=Tellurium (aromatic)
                        addAtomProp("Chemical Element", ["aromatic", "te"]);
                        break;
                    default:
                        unget();
                        return false;
                }
                break;
            case 'r':
                num = nextUnsignedInt();
                if (num < 0) {
                    // CACTVS style ranges X{0-2}
                    if (peek() === '{') {
                        var val = parseRange();
                        if (!val)
                            return false;
                        addAtomProp("Ring Size", [val[0], val[1]]);
                    }
                } else if (num == 0)
                    addAtomProp("Ring Size", [num]);
                else if (num > 2)
                    addAtomProp("Ring Size", [num]);
                else
                    return false;
                break;
            case 'v':
                num = nextUnsignedInt();
                if (num < 0) {
                    // CACTVS style ranges X{0-2}
                    if (peek() === '{') {
                        var val = parseRange();
                        if (!val)
                            return false;
                        addAtomProp("Valence", [val[0], val[1]]);
                    }
                } else
                    addAtomProp("Valence", [num]);
                break;
            case 'h':
                num = nextUnsignedInt();
                if (num < 0) {
                    // CACTVS style ranges X{0-2}
                    if (peek() === '{') {
                        var val = parseRange();
                        if (!val)
                            return false;
                        addAtomProp("Implicit Hydrogen Count", [val[0], val[1]]);
                    }
                } else
                    addAtomProp("Implicit Hydrogen Count", [num]);
                break;
            case 'x':
                num = nextUnsignedInt();
                if (num < 0) {
                    // CACTVS style ranges X{0-2}
                    if (peek() === '{') {
                        var val = parseRange();
                        if (!val)
                            return false;
                        addAtomProp("Ring Bond Count", [val[0], val[1]]);
                    }
                } else if (num == 0)
                    addAtomProp("Ring Bond Count", [num]);
                else if (num > 1)
                    addAtomProp("Ring Bond Count", [num]);
                else
                    return false;
                break;
            case '#':
                num = nextUnsignedInt();
                if (num < 0) {
                    if (isFlavor(FLAVOR_LOOSE | FLAVOR_CACTVS | FLAVOR_MOE)) {
                        switch (next()) {
                            //case 'X':
                            //   expr = new Expr(Expr.Type.IS_HETERO);
                            //  break;
                            case 'G':
                                num = nextUnsignedInt();
                                if (num <= 0 || num > 18)
                                    return false;
                                addAtomProp("Periodic Group", [num]);
                                break;
                            default:
                                return false;
                        }

                    } else {
                        return false;
                    }
                } else {
                    var tatom = new Kekule.Atom('tempAtom', parseInt(num));
                    addAtomProp("Chemical Element", ["either", tatom.symbol]);
                }
                break;
            case '^':
                if (!isFlavor(FLAVOR_LOOSE | FLAVOR_OECHEM | FLAVOR_CDK_LEGACY))
                    return false;
                num = nextUnsignedInt();
                if (num <= 0 || num > 8)
                    return false;
                addAtomProp("Hybridization Number", [num]);
                break;
            case 'i':
                if (!isFlavor(FLAVOR_MOE | FLAVOR_CACTVS))
                    return false;
                num = nextUnsignedInt();
                if (num <= 0 || num > 8)
                    return false;
                addAtomProp("Insaturation", [num]);
                break;
            case 'z':
                if (!isFlavor(FLAVOR_CACTVS)) {
                    return false;
                }
                num = nextUnsignedInt();
                // MODIFICATION to parse range for z
                if (num < 0) {
                    // CACTVS style ranges X{0-2}
                    if (peek() === '{') {
                        var val = parseRange();
                        if (!val)
                            return false;
                        addAtomProp("Hetero Substituent Count", [val[0], val[1]]);
                    }
                } else if (num == 0)
                    addAtomProp("Hetero Substituent Count", [num]);
                else
                    addAtomProp("Hetero Substituent Count", [num]);
                break;
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                //TODO isotope [14C:1] implement in UI and parser
                unget();
                num = nextUnsignedInt();
                /*if (num == 0)
                    expr = new Expr(Expr.Type.HAS_UNSPEC_ISOTOPE);
                else
                    expr = new Expr(Expr.Type.ISOTOPE, num);*/
                break;
            case '-':
                num = nextUnsignedInt();
                if (num < 0) {
                    num = 1;
                    var ppeek = peek();
                    if (ppeek === '{') {
                        var val = parseRange();
                        if (!val)
                            return false;
                        addAtomProp("Negative Charge", [val[0], val[1]]);
                    } else if (ppeek === '-') {
                        num++;
                        pos++;
                        while (peek() === '-') {
                            num++;
                            pos++;
                        }
                    }
                } else
                    addAtomProp("Negative Charge", [num]);
                break;
            case '+':
                num = nextUnsignedInt();
                if (num < 0) {
                    num = 1;
                    var ppeek = peek();
                    if (ppeek === '{') {
                        var val = parseRange();
                        if (!val)
                            return false;
                        addAtomProp("Positive Charge", [val[0], val[1]]);
                    } else if (ppeek === '+') {
                        num++;
                        pos++;
                        while (peek() === '-') {
                            num++;
                            pos++;
                        }
                    }
                } else
                    addAtomProp("Positive Charge", [num]);
                break;
            case '@':
                num = IStereoElement.LEFT;
                if (peek() === '@') {
                    next();
                    // num = IStereoElement.RIGHT;
                }
                //expr = new Expr(Expr.Type.STEREOCHEMISTRY, num);
                // "or unspecified"
                if (peek() === '?') {
                    next();
                    //expr.or(new Expr(Expr.Type.STEREOCHEMISTRY, 0));
                }

                // neigbours will be index on 'finish()'
                //astereo.add(atom);
                break;

            case '&':
                //parseAtom('&');
                break;
            case ';':
                returnedReference = null;
                cpt = 0;
                isSpecific = false;
                break;
            case ',':
                op = "or";
                break;
            case '!':
                //TODO NEGATE
                not = true;
                break;
            case '$':
                if (next() != '(')
                    return false;
                //TODO match with define env
                var beg = pos;
                var end = beg;
                var depth = 1;
                while (end < str.length) {
                    switch (str.charAt(end++)) {
                        case '(':
                            depth++;
                            break;
                        case ')':
                            depth--;
                            break;
                    }
                    if (depth == 0)
                        break;
                }
                if (end == str.length)
                    return false;
                var env = "$(" + str.substring(beg, end - 1) + ")";
                var envName = "customized";
                for (var fg in functionalGroupProperties) {
                    if (functionalGroupProperties[fg] === env) {
                        envName = functionalGroupProperties[fg];
                        break;
                    }
                }
                addAtomProp("Chemical Environment", [envName, env]);
                pos = end;
                break;
            case ':':
                num = nextUnsignedInt();
                if (num < 0)
                    return false;
                if (num != 0)
                    addAtomProp("Atom Mapping", [num]);
                // should be add end of expr
                atomIndex++;
                if (lastOp != 0)
                    return peek() === ']';
                else
                    return next() === ']';
            case ']':
                if (lastOp != 0)
                    unget();
                atomIndex++
                return true;
            default:
                return false;
        }
    }
}

function parseBond() {
    returnedReference = null;
    cpt = 0;
    //OLD way
    //form = createBondEditor();
    var bool = true;
    while (bool) {
        switch (next()) {
            // MODIFICATION expr for single and double bond The commented version is the original one
            case '-':
                addBondProp("Single Bond", null);
                break;
            case '=':
                addBondProp("Double Bond", null);
                break;
                //END MODIFICATION
            case '#':
                if (peek() == 'E') {
                    next();
                    var num = nextUnsignedInt();
                    if (num < 0) {
                        // CACTVS style ranges X{0-2}
                        if (peek() === '{') {
                            var val = parseRange();
                            if (!val)
                                return false;
                            addBondProp("Electrons Involved In Bond", [val[0], val[1]]);
                        } else if (num >= 0) {
                            addBondProp("Electrons Involved In Bond", [num]);
                        } else
                            return false
                    }
                } else {
                    addBondProp("Triple Bond", null);
                }
                break;
            case '$':
                addBondProp("Quadruple Bond", null);
                break;
            case ':':
                addBondProp("Aromatic Bond", null);
                break;
            case '~':
                addBondProp("Any Bond", null);
                break;
            case '@':
                addBondProp("Ring Bond", null);
                break;
            case ';':
                // Nothing for now
                break;
            case ',':
                op = "or";
                break;
            case '!':
                not = true;
                break;
            case '/':
                if (peek() === '?') {
                    next();
                    addBondProp("Up Bond", null);
                } else {
                    addBondProp("Up or Unspecified Bond", null);
                }
                break;
            case '\\':
                if (peek() === '?') {
                    next();
                    addBondProp("Down Bond", null);
                } else {
                    addBondProp("Down or Unspecified Bond", null);
                }
                break;
            default:
                bool = false;
                pos--;
        }
    }

    bondIndex++;
    //remove prev connection

    const index = array.indexOf(atom);
    if (index > -1) {
        stack.splice(index, 1);
    }
}

function finish() {
    return true;
}

function parseRange() {
    if (next() != '{')
        return false;
    var lo = nextUnsignedInt();
    if (next() != '-')
        return false;
    var hi = nextUnsignedInt();
    if (hi == -1 && isFlavor(FLAVOR_CACTVS))
        hi = 10;
    next();
    return [lo, hi];
}

function nextUnsignedInt() {
    if (!isDigit(peek()))
        return -1;
    var res = next() - '0';
    while (isDigit(peek()))
        res = 10 * res + (next() - '0');
    return res;
}

function unget() {
    if (pos <= str.length)
        pos--;
}

function newFragment() {
    prev = null;
}

function peek() {
    return pos < str.length ? str.charAt(pos) : '\0';
}

function next() {
    if (pos < str.length)
        return str.charAt(pos++);
    pos++;
    return '\0';
}

function nextWithoutIncrementing() {
    if (pos < str.length)
        return str.charAt(pos++);
    return '\0';
}

function isDigit(c) {
    return c >= '0' && c <= '9';
}

function isFlavor(flav) {
    return (flavor & flav) != 0;
}