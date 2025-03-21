window.getSMIRKS = function (index) {
    var smirks = "";
    //console.log("INDEX",index);
    if (index == null) {
        for (var i = 0, l = mols.length; i < l; ++i) {
            var mol = mols[i];
            //console.log("SMA",mol.smarts);
            smirks += mol.smarts;
            if (i < l - 1) {
                smirks += ".";
            }
        }
    } else {
        //console.log(reaction, mols)
        var reaction = reactions[index];
        // reactants
        for (var i = 0, l = reaction.getReactantCount(); i < l; ++i) {
            var mol = reaction.getReactantAt(i);
            smirks += mol.smarts;
            if (i < l - 1) {
                smirks += ".";
            }
            //add reaction logic
            if (mol.reactionLogic != null) {
                if (reaction.logic == null) {
                    reaction.logic = mol.reactionLogic;
                }
            }
        }
        smirks += ">";
        //agents
        for (var i = 0, l = reaction.getSubstancesCount(); i < l; ++i) {
            var mol = reaction.getSubstanceAt(i);
            smirks += mol.smarts;
            if (i < l - 1) {
                smirks += ".";
            }
        }
        smirks += ">";
        // products
        for (var i = 0, l = reaction.getProductCount(); i < l; ++i) {
            var mol = reaction.getProductAt(i);
            smirks += mol.smarts;
            if (i < l - 1) {
                smirks += ".";
            }
        }
    }
    return smirks;
}


window.makeLocalSmarts = function () {
    var simplfiedSmarts = "";
    var fullSmarts = "";
    var numberOfChemicalElement = 0;
    var numberOfBonds = 0;
    var bType = null;
    var symbol;
    //console.log(specificProperties,commonProperties);
    //console.time("C");
    var chemEnv = "";
    var chemEnvSimplified = "";
    for (var propertyBlockOr in specificProperties) {
        var addProp = false;
        var addEnv = false;
        var symbols = "";
        var props = "";
        for (var propertyAnd in specificProperties[propertyBlockOr]) {
            //console.log(propertyBlockOr,propertyAnd);
            for (var i = 0, l = specificProperties[propertyBlockOr][propertyAnd].length; i < l; i++) {
                //var elt = specificProperties[propertyBlockOr][propertyAnd];
                var elt = specificProperties[propertyBlockOr][propertyAnd][i];
                if (propertyAnd === "Chemical Element") {
                    if (!elt.includes("!")) {
                        symbol = elt;
                        numberOfChemicalElement++;
                    } else {
                        numberOfChemicalElement--;
                    }
                    //simplfiedSmarts += elt;
                    symbols += elt;
                    addProp = true;
                } else if (propertyAnd === "Chemical Environment") {
                    var array = elt.split("<>");
                    //simplfiedSmarts += array[1];
                    //fullSmarts += array[0];
                    if (array[1] === "customized") {
                        if (array[0].length > 0) {
                            if (!array[0].startsWith("$("))
                                chemEnvSimplified += "$(" + array[0] + ");";
                            else
                                chemEnvSimplified += array[0] + ";";
                        }
                    } else {
                        if (array[1].length > 0)
                            chemEnvSimplified += array[1] + ";";
                    }
                    chemEnv += array[0];
                    addEnv = true;
                } else {
                    //simplfiedSmarts += elt;
                    props += elt;
                    if (propertyAnd.includes("Bond")) {
                        if (!propertyAnd.includes("Electrons Involved In"))
                            bType = elt;
                        if (numberOfBonds == propertyBlockOr)
                            numberOfBonds++;
                    }
                    addProp = true;
                }
            }
            //console.log(chemEnvSimplified);
            //chemEnvSimplified = chemEnvSimplified.replace(/\s/g, "");
            if (chemEnvSimplified.length > 0 && chemEnvSimplified.slice(chemEnvSimplified.length - 1) === ";") {
                chemEnvSimplified = chemEnvSimplified.slice(0, -1);
            }
        }
        fullSmarts += symbols + props;
        if (addProp) {
            //simplfiedSmarts += ",";
            fullSmarts += ",";
        }
        if (addEnv) {
            chemEnvSimplified += ",";
            chemEnv += ",";
        }
    }
    //console.timeEnd("C");
    //remove extra ,
    //simplfiedSmarts = simplfiedSmarts.slice(0, -1);
    //remove comma at the end of the string
    if (fullSmarts.length > 0 && fullSmarts.slice(fullSmarts.length - 1) === ",") {
        fullSmarts = fullSmarts.slice(0, -1);
    }

    for (var propertyAnd in commonProperties) {
        var arr = commonProperties[propertyAnd];
        if (!propertyAnd === "aam") {
            for (var i = 0, l = arr.length; i < l; ++i) {
                var elt = arr[i];
                if (propertyAnd === "Chemical Element") {
                    if (!elt.includes("!")) {
                        symbol = elt;
                        numberOfChemicalElement++;
                    } else {
                        numberOfChemicalElement--;
                    }
                    //simplfiedSmarts += elt;
                    fullSmarts += elt;
                }
                if (propertyAnd === "Chemical Environment") {
                    var array = elt.split("<>");
                    //simplfiedSmarts += array[1];
                    fullSmarts += array[0];
                } else {
                    simplfiedSmarts += elt;
                    fullSmarts += elt;
                }
            }
        }
    }
    //remove ";"" if no other common properties
    /* if (fullSmarts.slice(fullSmarts.length - 1) === ";") {
         //simplfiedSmarts = simplfiedSmarts.slice(0, -1);
         fullSmarts = fullSmarts.slice(0, -1);
     }*/

    if ("aam" in commonProperties) {
        var prop = commonProperties["aam"];
        if (prop !== ":0") {
            //simplfiedSmarts += prop;
            fullSmarts += prop;
        }
        var atomMappingNumber = parseInt(commonProperties["aam"].slice(1), 10);
        newSelectedChemObject.setAtomMappingNumber(atomMappingNumber);
    }

    //remove extra ;
    if (chemEnv.length > 0) {
        chemEnv = chemEnv.slice(0, -1);
        chemEnvSimplified = chemEnvSimplified.slice(0, -1);
        //simplfiedSmarts += "([" + chemEnvSimplified + "])";
        //fullSmarts += "([" + chemEnv + "])";
    }

    //console.time("D");
    if (newSelectedChemObject.getClassName() === 'Kekule.Atom') {
        /*if (numberOfChemicalElement == 0) {
            simplfiedSmarts = newSelectedChemObject.symbol.concat(simplfiedSmarts);
            fullSmarts = newSelectedChemObject.symbol.concat(fullSmarts);
        }*/
        //console.time("E");
        if (numberOfChemicalElement == 1) {
            if (symbol.charAt(0) === "\#") {
                if (symbol !== "A" && symbol !== "a" && symbol !== "*") {
                    var tatom = new Kekule.Atom('tempAtom', parseInt(symbol.slice(1)));
                    newSelectedChemObject.symbol = tatom.symbol;
                } else {
                    newSelectedChemObject.symbol = "C";
                }
            } else {
                if (symbol !== "A" && symbol !== "a" && symbol !== "*") {
                    newSelectedChemObject.symbol = symbol.charAt(0).toUpperCase() + symbol.slice(1);
                    /*var newData = {};
                    var props = {} ;
                    props["isotopeId"] = symbol.charAt(0).toUpperCase() + symbol.slice(1);
                    newData["nodeClass"] = newSelectedChemObject.getClass();
                    newData["props"] = props;
                    var op = Kekule.Editor.OperationUtils.createNodeModificationOperationFromData(newSelectedChemObject, newData, chemEditor);
                    console.log(op);
                    if (op != null)
                    chemEditor.execOperation(op);
                    console.log(chemEditor.getRenderConfigs().getMoleculeDisplayConfigs());
                    console.log(chemComposer.exportObjs(Kekule.Molecule));
                    //newSelectedChemObject.setPropValue("isotopeId", symbol.charAt(0).toUpperCase() + symbol.slice(1));
                    console.log(symbol);*/
                } else {
                    newSelectedChemObject.symbol = "C";
                }
            }
        }
        //console.time("F");
        //console.log(specificProperties);
        //console.log(numberOfChemicalElement); 
        var opt = newSelectedChemObject.renderOptions;
        if (numberOfChemicalElement > 1) {
            if (newSelectedChemObject.symbol !== "C") {
                newSelectedChemObject.symbol = "C";
            }
            if (!opt) {
                chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                    'atomRadius': 3
                }, false, true);
            }
            else {
                //order must be maintained. atomColor needs to be checked first as it's linked to undo redo function
                if ("atomColor" in opt && "atomRadius" in opt == false) {
                    if (!opt.atomColor.includes("000000")) {
                        chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                            'atomColor': '#000000'
                        }, false, true);
                    }
                }
                else if ("atomRadius" in opt && "atomColor" in opt == false) {
                    if (opt.atomRadius != 3) {
                        chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                            'atomRadius': 3
                        }, false, true);
                    }
                }
                else {
                    if (opt.atomRadius != 3 && !opt.atomColor.includes("000000")) {
                        chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                            'atomRadius': 3, 'atomColor': '#000000'
                        }, false, true);
                    }
                    else if (opt.atomRadius == 3 && !opt.atomColor.includes("000000")) {
                        chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                            'atomColor': '#000000'
                        }, false, true);
                    }
                    else if (opt.atomRadius != 3 && !opt.atomColor.includes("000000")) {
                        chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                            'atomRadius': 3
                        }, false, true);
                    }
                }
            }
        } else if (numberOfChemicalElement < 0) {
            if (newSelectedChemObject.symbol !== "C") {
                newSelectedChemObject.symbol = "C";
            }
            if (!opt) {
                chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                    'atomRadius': 3, 'atomColor': '#FF0000'
                }, false, true);
            }
            else {
                //order must be maintained. atomColor needs to be checked first as it's linked to undo redo function
                if ("atomColor" in opt == false && "atomRadius" in opt) {
                    if (!newSelectedChemObject.renderOptions.atomColor.includes("FF0000")) {
                        chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                            'atomColor': '#FF0000'
                        }, false, true);
                    }
                }
                else if ("atomRadius" in opt == false && "atomColor" in opt) {
                    if (opt.atomRadius != 3) {
                        chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                            'atomRadius': 3
                        }, false, true);
                    }
                }
                else {
                    if (opt.atomRadius != 3 && !opt.atomColor.includes("FF0000")) {
                        chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                            'atomRadius': 3, 'atomColor': '#FF0000'
                        }, false, true);
                    }
                    else if (opt.atomRadius == 3 && !opt.atomColor.includes("FF0000")) {
                        chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                            'atomColor': '#FF0000'
                        }, false, true);
                    }
                    else if (opt.atomRadius != 3 && !opt.atomColor.includes("FF0000")) {
                        chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                            'atomColor': '#FF0000', 'atomRadius': 3
                        }, false, true);
                    }
                }
            }
        } else {
            //order must be maintained. atomColor needs to be checked first as it's linked to undo redo function
            if (depictReactions) {
                if (newSelectedChemObject.symbol === "C") {
                    if (opt) {
                        if ("atomColor" in opt && "atomRadius" in opt == false) {
                            if (!opt.atomColor.includes("000000")) {
                                chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                                    'atomColor': '#000000'
                                }, false, true);
                            }
                        }
                        else if ("atomRadius" in opt && "atomColor" in opt == false) {
                            if (opt.atomRadius != 0) {
                                chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                                    'atomRadius': 0
                                }, false, true);
                            }
                        }
                        else {
                            if (opt.atomRadius != 0 && !opt.atomColor.includes("000000")) {
                                chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                                    'atomRadius': 0, 'atomColor': '#000000'
                                }, false, true);
                            }
                            else if (opt.atomRadius == 3 && !opt.atomColor.includes("000000")) {
                                chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                                    'atomColor': '#000000'
                                }, false, true);
                            }
                            else if (opt.atomRadius != 0 && !opt.atomColor.includes("000000")) {
                                chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                                    'atomRadius': 0
                                }, false, true);
                            }
                        }
                    }
                }
                else {
                    if (opt) {
                        if ("atomColor" in opt) {
                            if (!opt.atomColor.includes("000000")) {
                                chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                                    'atomColor': '#000000'
                                }, false, true);
                            }
                        }
                    }
                }
            }
        }
        //console.timeEnd("F");
        //console.timeEnd("E");
    } else if (newSelectedChemObject.getClassName() === 'Kekule.Bond') {
        if (numberOfBonds == 1) {
            newSelectedChemObject.bondType = Kekule.BondType.COVALENT;
            if (bType === "=") {
                newSelectedChemObject.bondOrder = 2;
                newSelectedChemObject.stereo = Kekule.BondStereo.NONE;
            } else if (bType === "#") {
                newSelectedChemObject.bondOrder = 3;
                newSelectedChemObject.stereo = Kekule.BondStereo.NONE;
            } else if (bType === "$") {
                newSelectedChemObject.bondOrder = 4;
                newSelectedChemObject.stereo = Kekule.BondStereo.NONE;
            } else if (bType === ":") {
                newSelectedChemObject.bondOrder = 10;
                newSelectedChemObject.stereo = Kekule.BondStereo.NONE;
            } else if (bType === "/") {
                newSelectedChemObject.bondOrder = 1;
                newSelectedChemObject.stereo = Kekule.BondStereo.UP;
            } else if (bType === "/\?") {
                newSelectedChemObject.bondOrder = 1;
                newSelectedChemObject.stereo = Kekule.BondStereo.UP;
            } else if (bType === "\\") {
                newSelectedChemObject.bondOrder = 1;
                newSelectedChemObject.stereo = Kekule.BondStereo.DOWN;
            } else if (bType === "\\\?") {
                newSelectedChemObject.bondOrder = 1;
                newSelectedChemObject.stereo = Kekule.BondStereo.DOWN;
            } else if (bType === "~") {
                newSelectedChemObject.bondOrder = 1;
                newSelectedChemObject.stereo = Kekule.BondStereo.CLOSER;
                //replace by outer bond
                //newSelectedChemObject.stereo = Kekule.BondStereo.NONE;
                //newSelectedChemObject.bondType = Kekule.BondType.HYDROGEN;
            } else {
                if (depictReactions) {
                    newSelectedChemObject.bondOrder = 1;
                    newSelectedChemObject.stereo = Kekule.BondStereo.NONE;
                }
            }
        } else {
            newSelectedChemObject.bondOrder = 1;
            newSelectedChemObject.bondType = Kekule.BondType.HYDROGEN;
        }
    }

    //console.timeEnd("D");
    simplfiedSmarts = fullSmarts;
    if (chemEnv.length > 0) {
        simplfiedSmarts += "[" + chemEnvSimplified + "]";
    }
    localSmartsTextField.value = simplfiedSmarts;
    //localSmartsTextField.value = simplfiedSmarts;
    // console.log("localSmartsTextField",simplfiedSmarts,localSmartsTextField);
    newSelectedChemObject.simpfiedSmarts = simplfiedSmarts;
    newSelectedChemObject.smarts = fullSmarts;

    newSelectedChemObject.chemEnv = chemEnv;
    newSelectedChemObject.chemEnvSimplified = chemEnvSimplified;

    //console.log("newSelectedChemObject ",newSelectedChemObject);

    //commented for performance improvment (mapping will be updated when the selection changes), 
    //all other operations are update on the fly
    /*if (depictReactions) {
        displayReactionSMARTS(false, false);
    }*/
    return simplfiedSmarts;
}

window.makeLocalSmarts_OLD = function () {
    var simplfiedSmarts = "";
    var fullSmarts = "";
    var symbol;
    //console.log(specificProperties);
    var numberOfChemicalElement = 0;
    for (var i = 0; i < specificProperties.length; i++) {
        var val = "";
        for (var j = 0; j < specificProperties[i].length; j++) {
            var prop = specificProperties[i][j];
            if (prop.length > 0) {
                if (prop.slice(0, 1) === "_") {
                    prop = prop.slice(1);
                    if (!prop.includes("!")) {
                        symbol = prop;
                    } else {
                        numberOfChemicalElement--
                    }
                }
                var group = "";
                var smartsGroup = "";
                if (prop.includes("||")) {
                    var array = prop.split("||");
                    //smartsGroup=array[0];
                    //group=array[1];
                    simplfiedSmarts += array[1];
                    fullSmarts += array[0];
                } else {
                    simplfiedSmarts += prop;
                    fullSmarts += prop;
                }
            }
            val += prop;
            //console.log("porp "+prop);
        }
        //console.log("val "+val);
        if (val.length > 0 && i < specificProperties.length - 1) {
            simplfiedSmarts += ",";
            fullSmarts += ",";
        }
    }
    if (fullSmarts.length > 0) {
        simplfiedSmarts += ";"
        fullSmarts += ";"
    }

    for (var i = 1; i < commonProperties.length; i++) {
        var val = commonProperties[i];
        if (val != null) {
            if (val.length > 0) {
                if (val.slice(0, 1) === "_") {
                    val = val.slice(1);
                    if (!val.includes("!")) {
                        symbol = val;
                    } else {
                        numberOfChemicalElement--
                    }
                }
                if (val.includes("||")) {
                    var array = val.split("||");
                    //smartsGroup=array[0];
                    //group=array[1];
                    simplfiedSmarts += array[1];
                    fullSmarts += array[0];
                } else {
                    simplfiedSmarts += val;
                    fullSmarts += val;
                }
            }
        }
    }

    if (newSelectedChemObject.getClassName() === 'Kekule.Atom') {
        if (numberOfChemicalElement == 0) {
            simplfiedSmarts = newSelectedChemObject.symbol.concat(simplfiedSmarts);
            fullSmarts = newSelectedChemObject.symbol.concat(fullSmarts);
        } else if (numberOfChemicalElement == 1) {
            if (symbol.charAt(0) === "\#") {
                var tatom = new Kekule.Atom('tempAtom', parseInt(symbol.slice(1)));
                newSelectedChemObject.symbol = tatom.symbol;
            } else {
                newSelectedChemObject.symbol = symbol.charAt(0).toUpperCase() + symbol.slice(1);
            }
        }
        if (numberOfChemicalElement > 1) {
            newSelectedChemObject.symbol = "C";
            chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                'atomRadius': 3
            }, false, true);
        } else {
            //console.log(newSelectedChemObject,selectedChemObject);
            if (newSelectedChemObject.symbol === "C")
                chemEditor.modifyObjectsRenderOptions(newSelectedChemObject, {
                    'atomRadius': 0
                }, false, true);
        }
    }

    if (simplfiedSmarts.slice(simplfiedSmarts.length - 1) === ";") {
        simplfiedSmarts = simplfiedSmarts.slice(0, -1);
        fullSmarts = fullSmarts.slice(0, -1);
    }

    //add atom Mapping
    if (commonProperties[0]) {
        simplfiedSmarts += commonProperties[0];
        fullSmarts += commonProperties[0];
        var atomMappingNumber = parseInt(commonProperties[0].slice(1), 10);
        newSelectedChemObject.setAtomMappingNumber(atomMappingNumber);
    }
    //console.log("numberOfChemicalElement ",numberOfChemicalElement);

    localSmartsTextField.value = simplfiedSmarts;
    //console.log("localSmartsTextField",simplfiedSmarts,localSmartsTextField);
    newSelectedChemObject.simpfiedSmarts = simplfiedSmarts;
    newSelectedChemObject.smarts = fullSmarts;
    //console.log("newSelectedChemObject ",newSelectedChemObject);
    //console.log("simplified",simplfiedSmarts);
    displayReactionSMARTS();
    return simplfiedSmarts;
}

window.buildEnvironmentExpression = function (parent) {
    var str = "";

    var children = parent.childNodes;
    var select = children[1];
    var is = children[2].childNodes[0];
    var group = children[3];
    var sma = children[4].value;
    var not = "";
    console.log(sma);
    if (is.checked && sma) {
        sma = sma.split("$(").join("!$(");
        not = "!";
    }
    if (group.value === "customized") {
        str += sma + "<>" + not + group.value;
    } else {
        str += sma + "<>" + not + group.value;
    }
    return str;
}

window.buildElementExpression = function (parent) {
    var not = "!";
    var str = "";

    var children = parent.childNodes;
    var select = children[1];
    var is = children[2].childNodes[0];
    var aromaticity = children[children.length - 2];
    var symbol = children[children.length - 1].innerHTML;

    if (is.checked) {
        str += not;
    }
    if (symbol === '*' || symbol === 'a' || symbol === 'A') {
        str += symbol;
    } else {
        /*if (numberOfChemicalElement == 0) {
            newSelectedChemObject.symbol = symbol;
        }
        else {
            newSelectedChemObject.symbol = "C";
        }*/
        if (aromaticity.value === "aliphatic") {
            str += symbol.charAt(0).toUpperCase() + symbol.slice(1);
        } else if (aromaticity.value === "aromatic") {
            str += symbol.charAt(0).toLowerCase() + symbol.slice(1);
        } else {
            var atom = new Kekule.Atom('atomTestAromaticity', symbol.charAt(0).toUpperCase() + symbol.slice(1));
            str += "#" + atom.atomicNumber;
        }
    }
    return str;
}

window.buildPropertyComplexExpression = function (parent, type) {
    var not = "!";
    var str = "";

    var children = parent.childNodes;
    var select = children[1];
    var is = children[2].childNodes[0];
    var equal = children[4]
    var interval = children[5];

    if (interval != null) {
        var iChildren = interval.childNodes;
        var lower = iChildren[1];
        var higher = iChildren[3];
        if (equal.value == null || (lower.value == null || higher.value == null)) {
            return "";
        }
    }

    if (is.checked) {
        str += not;
    }
    if (type.includes("Bond") && !type.includes("Ring")) {
        str += bondPropertyEncoding[type];
    } else {
        str += atomPropertyEncoding[type];
    }
    if (interval != null) {
        if (interval.style.display === "inline-block") {
            str += "{" + lower.value + "-" + higher.value + "}";
        } else {
            str += equal.value;
        }
    }
    //console.log("INTER",str,interval.style.display,interval);
    return str;
}