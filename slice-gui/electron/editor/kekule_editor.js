var savedEditorSections = {};
var savedBlockly = {};
var operationCounter = 0;

window.getComposer = function (divID) {
    var composer = new Kekule.Editor.Composer(document);
    composer.setDimension("100%", "400px");
    composer.appendToElem(document.getElementById(divID));

    composer
        .setEnableOperHistory(true)
        .setEnableLoadNewFile(true)
        .setEnableCreateNewDoc(true)
        .setAllowCreateNewChild(true)
        .setCommonToolButtons([
            //"newDoc",
            //"loadData",
            //"saveData",
            "undo",
            "redo",
            "copy",
            "cut",
            "paste",
            "zoomIn",
            "reset",
            "zoomOut",
        ]) // create all default common tool buttons
        .setChemToolButtons([
            "manipulate",
            "erase",
            "bond",
            "atomAndFormula",
            "ring",
            "charge",
            "glyph",
            "textImage",
        ]) // create all default chem tool buttons
        .setStyleToolComponentNames([
            "fontName",
            "fontSize",
            "color",
            "textDirection",
            "textAlign",
        ]); // create all default style components

    return composer;
}

window.createDefaultEditor = function () {
    var container = document.getElementById("sliceContainer");
    var div = document.createElement("div");

    div.setAttribute("id", "defaultObjEditor");
    div.setAttribute("style", "height:400px;overflow:auto");
    div.setAttribute("class", "chemObjEditor");
    div.innerHTML = "Click on an atom or bond to display its property editor";
    div.setAttribute("style", "text-align: center;")
    //div.setAttribute("onchange", "displayAtomOptions('" + id + "')");
    container.appendChild(div);
    return div;
}

window.createChemObjEditor = function (chemObjId, chemObj) {
    var isAtom = true;
    if (!chemObjId.includes("Atom")) {
        isAtom = false;
    }

    var container = document.getElementById("sliceContainer");
    var div = document.createElement("div");

    div.setAttribute("id", chemObjId);
    div.setAttribute("style", "height:400px;overflow:auto");
    div.setAttribute("class", "chemObjEditor");
    //div.setAttribute("onchange", "displayAtomOptions('" + id + "')");
    container.appendChild(div);

    localSmartsTextField = document.createElement("input");
    localSmartsTextField.type = "text";
    //smarts.className = "css-class-name"; // set the CSS class
    localSmartsTextField.setAttribute("id", chemObjId + "_smarts");
    localSmartsTextField.setAttribute("class", "chemObjSmarts");
    //console.log(chemObj);
    localSmartsTextField.setAttribute("style", "width:97%");
    domDict[chemObjId + "_smarts"] = localSmartsTextField;
    div.appendChild(localSmartsTextField); // put it into the DOM

    var form = document.createElement("form");
    //localSmartsTextField.className = "css-class-name"; // set the CSS class
    form.setAttribute("id", chemObjId + "_form");
    form.setAttribute("class", "chemObjEditorForm");
    form.setAttribute("onsubmit", "return false;");
    div.appendChild(form); // put it into the DOM

    if (isAtom) {
        createAtomSection(chemObjId, chemObj, form);
    } else {
        createBondSection(chemObjId, chemObj, form)
    }
    localSmartsTextField.setAttribute("value", "");
    return div;
}


//percent of overlap left
window.percentageLeft = function (mol, minXarr) {
    var result = 0.0;
    var l = mol.getNodeCount();
    for (var i = 0; i < l; ++i) {
        var x = mol.getNodeAt(i).absCoord2D.x;
        if (x > minXarr) {
            result++;
        }

    }
    result = (result / l) * 100;
    return result;

}
//percent of overlap right
window.percentageRight = function (mol, maxXarr) {
    var result = 0.0;
    var l = mol.getNodeCount();
    for (var i = 0; i < l; ++i) {
        var x = mol.getNodeAt(i).absCoord2D.x;
        if (x < maxXarr) {
            result++;
        }

    }
    result = (result / l) * 100;
    return result;

}

//percent of on arrow 
window.percentageArrow = function (mol, arrow) {
    var minXarr = arrow.minMaxCoord[0];
    var maxXarr = arrow.minMaxCoord[2];
    var result = 0.0;
    var l = mol.getNodeCount();
    for (var i = 0; i < l; ++i) {
        var x = mol.getNodeAt(i).absCoord2D.x;
        if ((minXarr < x) && (x < maxXarr)) {
            result++;
        }

    }
    result = (result / l) * 100;
    return result;

}

//proximity (distance) mol to arrow
window.proximityMolArrow = function (mol, arrow) {
    var minYarr = arrow.minMaxCoord[1];
    var maxYarr = arrow.minMaxCoord[3];
    var distance;

    var res = getMinMaxCoord(mol);
    var minYmol = res[1];
    var maxYmol = res[3];

    if (maxYmol < minYarr) { //mol is under the arrow
        distance = minYarr - maxYmol;
    } else { //mol is above the arrow
        distance = minYmol - maxYarr;
    }

    return distance;

}

//proximity (distance) mol to nol
window.proximityMolToMol = function (mol1, mol2) {
    var distance;

    var res = getMinMaxCoord(mol1);
    var minYmol1 = res[1];
    var maxYmol1 = res[3];

    var res = getMinMaxCoord(mol2);
    var minYmol2 = res[1];
    var maxYmol2 = res[3];

    if (minYmol1 < minYmol2) { //mol is under the ref
        distance = minYmol2 - minYmol1;
    } else { //mol is above the rer
        distance = maxYmol1 - maxYmol2;
    }

    return distance;

}
//extract reaction form drawing area
window.getReactions = function () {
    mols = new Array();
    var arrows = new Array();
    var percent = 37.0;

    // read molecules and arrows
    var objs = chemComposer.exportObjs(Kekule.SimpleStructureNode);
    //a + sign has 5 connected objects, an arrow has 2. this function is for extracting only arrow
    var pathNodes = {};
    var dict = {};

    for (var i = 0, l = objs.length; i < l; ++i) {
        var obj = objs[i];
        var thisClass = obj.getClassName();
        if (thisClass === "Kekule.Molecule") {
            obj.isClassified = false;
            obj.nonHydrogenNodesCount = obj.nonHydrogenNodes.length;
            //save reaction logic as a new reaction is going to be defined
            if (obj.reactionIndex != null) {
                var reac = reactions[obj.reactionIndex];
                if (reac.logic != null)
                    obj.reactionLogic = reac.logic;
            }
            mols.push(obj);
        } else if (thisClass === "Kekule.Glyph.PathGlyphNode") {
            pathNodes[obj.id] = obj.linkedObjs;
            dict[obj.id] = obj;
            arrows.push(obj);
        }
    }


    for (let objId in pathNodes) {
        //it's a plus sign delete all associated glyph from arrows
        if (pathNodes[objId].length > 1) {
            let list = pathNodes[objId];
            for (var j = 0, k = list.length; j < k; ++j) {
                let index = arrows.indexOf(list[j]);
                if (index > -1) { 
                    arrows.splice(index, 1);
                }
            }
            let index = arrows.indexOf(dict[objId]);
            if (index > -1) {
                arrows.splice(index, 1);
            }
        }
    }

    reactions = new Array();

    //coordinates of molecules
    for (var j = 0, m = mols.length; j < m; ++j) {
        var mol = mols[j];
        var result = getMinMaxCoord(mol);
        mol.minMaxCoord = [result[0], result[1], result[2], result[3]];
        //console.log("mol", j, " :", mols[j].minMaxCoord);
    }
    //coordinates of arrows
    for (var i = 0, l = arrows.length; i < l; i += 2) {
        var result = getMinMaxArrow(arrows[i], arrows[i + 1]);
        arrows[i].minMaxCoord = [result[0], result[1], result[2], result[3]];
        //kekule bug fix: update parent arrow coord save update fix for saveFile function
        var parentArrow = arrows[i].parent;
        parentArrow.savedCoord2D = arrows[i].absCoord2D;
    }

    //reactions -- initialize with arrows 
    for (var i = 0, l = arrows.length; i < l; i += 2) {
        var reaction = new Kekule.Reaction();
        reaction.minMaxCoord = arrows[i].minMaxCoord;
        //console.log("Reaction ",i,reaction);
        reactions.push(reaction);
    }
    // for all molecules check if they are reactants or products or substances of a reaction
    for (var j = 0, n = reactions.length; j < n; ++j) {
        var reaction = reactions[j];
        for (var i = 0, m = mols.length; i < m; ++i) {
            var mol = mols[i];
            //console.log("mol", i, "/", m - 1, "in", j);
            if (!mol.isClassified) {
                //mol
                var minXmol = mol.minMaxCoord[0];
                var minYmol = mol.minMaxCoord[1];
                var maxXmol = mol.minMaxCoord[2];
                var maxYmol = mol.minMaxCoord[3];

                //arrow
                var minXarr = arrows[2 * j].minMaxCoord[0];
                var minYarr = arrows[2 * j].minMaxCoord[1];
                var maxXarr = arrows[2 * j].minMaxCoord[2];
                var maxYarr = arrows[2 * j].minMaxCoord[3];


                //according to y 
                if (!(minYmol > maxYarr || minYarr > maxYmol)) {
                    if (maxXmol < minXarr) { //left of the arrow : reactant without ambiguity 
                        if (arrows[2 * j].absCoord2D.x < arrows[2 * j + 1].absCoord2D.x) {
                            reaction.appendReactant(mol);
                        } else {
                            reaction.appendProduct(mol);
                        }
                        mol.reactionIndex = j;
                        mol.isClassified = true;
                    } else {
                        if (maxXarr < minXmol) { //right of the arrow : product without ambiguity
                            if (arrows[2 * j].absCoord2D.x < arrows[2 * j + 1].absCoord2D.x) {
                                mol.reactionComponent = "product";
                                reaction.appendProduct(mol);
                            } else {
                                mol.reactionComponent = "reactant";
                                reaction.appendReactant(mol);
                            }
                            mol.reactionIndex = j;
                            mol.isClassified = true;
                        } else { //reactant or substract or product according percentage of atoms
                            var p = percentageLeft(mol, minXarr);
                            if (p < percent) {
                                if (arrows[2 * j].absCoord2D.x < arrows[2 * j + 1].absCoord2D.x) {
                                    mol.reactionComponent = "reactant";
                                    reaction.appendReactant(mol);
                                } else {
                                    mol.reactionComponent = "product";
                                    reaction.appendProduct(mol);
                                }
                                mol.reactionIndex = j;
                                mol.isClassified = true;
                            } else {
                                p = percentageRight(mol, maxXarr);
                                if (p < percent) {
                                    if (arrows[2 * j].absCoord2D.x < arrows[2 * j + 1].absCoord2D.x) {
                                        mol.reactionComponent = "product";
                                        reaction.appendProduct(mol);
                                    } else {
                                        mol.reactionComponent = "reactant";
                                        reaction.appendReactant(mol);
                                    }
                                    mol.reactionIndex = j;
                                    mol.isClassified = true;
                                } else {
                                    mol.reactionComponent = "substance";
                                    reaction.appendSubstance(mol);
                                    mol.reactionIndex = j;
                                    mol.isClassified = true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //rest of molecules near an arrow as substances
    for (var i = 0, m = mols.length; i < m; ++i) {
        var mol = mols[i];
        if (!mol.isClassified) {
            var proximity = 0;
            var id_reaction;
            var minXarr = 0;
            var minYarr = 0;
            var maxXarr = 0;
            var maxYarr = 0;
            for (var j = 0, n = reactions.length; j < n; ++j) {
                if (proximity == 0) {
                    proximity = proximityMolArrow(mol, arrows[2 * j]);
                    id_reaction = j;
                    minXarr = arrows[2 * j].minMaxCoord[0];
                    minYarr = arrows[2 * j].minMaxCoord[1];
                    maxXarr = arrows[2 * j].minMaxCoord[2];
                    maxYarr = arrows[2 * j].minMaxCoord[3];
                } else {
                    var new_proximity = proximityMolArrow(mol, arrows[2 * j]);
                    if (new_proximity < proximity) {
                        proximity = new_proximity;
                        id_reaction = j;
                        minXarr = arrows[2 * j].minMaxCoord[0];
                        minYarr = arrows[2 * j].minMaxCoord[1];
                        maxXarr = arrows[2 * j].minMaxCoord[2];
                        maxYarr = arrows[2 * j].minMaxCoord[3];
                    }
                }
            }

            if (id_reaction != null) {
                var reaction = reactions[id_reaction];
                //mol
                var minXmol = mol.minMaxCoord[0];
                var minYmol = mol.minMaxCoord[1];
                var maxXmol = mol.minMaxCoord[2];
                var maxYmol = mol.minMaxCoord[3];

                //since a molecule is assign to a reaction, check if it can be assign without ambiguity
                if (maxXmol < minXarr) { //left of the arrow : reactant without ambiguity
                    if (arrows[2 * id_reaction].absCoord2D.x < arrows[2 * id_reaction + 1].absCoord2D.x) {
                        mol.reactionComponent = "reactant";
                        reaction.appendReactant(mol);
                    }
                    mol.reactionIndex = id_reaction;
                    mol.isClassified = true;
                } else if (maxXarr < minXmol) { //right of the arrow : product without ambiguity
                    if (arrows[2 * id_reaction].absCoord2D.x < arrows[2 * id_reaction + 1].absCoord2D.x) {
                        mol.reactionComponent = "product";
                        reaction.appendProduct(mol);
                    } else {
                        mol.reactionComponent = "reactant";
                        reaction.appendReactant(mol);
                    }
                    mol.reactionIndex = id_reaction;
                    mol.isClassified = true;
                }

                //if mol is still not classified classify using overlapping
                if (!mol.isClassified) {
                    var p = percentageLeft(mol, minXarr);
                    if (p < percent) {
                        if (arrows[2 * id_reaction].absCoord2D.x < arrows[2 * id_reaction + 1].absCoord2D.x) {
                            mol.reactionComponent = "reactant";
                            reaction.appendReactant(mol);
                        } else {
                            mol.reactionComponent = "product";
                            reaction.appendProduct(mol);
                        }
                        mol.reactionIndex = id_reaction;
                        mol.isClassified = true;
                    } else {
                        p = percentageRight(mol, maxXarr);
                        if (p < percent) {
                            if (arrows[2 * id_reaction].absCoord2D.x < arrows[2 * id_reaction + 1].absCoord2D.x) {
                                mol.reactionComponent = "product";
                                reaction.appendProduct(mol);
                            } else {
                                mol.reactionComponent = "reactant";
                                reaction.appendReactant(mol);
                            }
                            mol.reactionIndex = id_reaction;
                            mol.isClassified = true;
                        } else {
                            mol.reactionComponent = "substance";
                            reaction.appendSubstance(mol);
                            mol.reactionIndex = id_reaction;
                            mol.isClassified = true;
                        }
                    }
                }

                if (!mol.isClassified) {
                    //classify using its closeness to already classified molecule
                var proximityMolMol = 999;
                if (reaction.reactants) {
                    for (var j = 0, m = reaction.reactants.length; j < m; ++j) {
                        var reactant = reaction.reactants[j].item;
                        proximityMolMol = proximityMolToMol(reactant, mol);
                    }
                }
                if (reaction.substances) {
                    for (var j = 0, m = reaction.substances.length; j < m; ++j) {
                        var substance = reaction.substances[j].item;
                        proximityMolMol = proximityMolToMol(substance, mol);
                    }
                }
                if (reaction.products) {
                    for (var j = 0, m = reaction.products.length; j < m; ++j) {
                        var product = reaction.products[j].item;
                        proximityMolMol = proximityMolToMol(product, mol);
                    }
                }
                    if (proximityMolMol < 1) {
                        if (maxXmol < minXarr) { //left of the arrow : reactant without ambiguity 
                            mol.reactionComponent = "reactant";
                            reaction.appendReactant(mol);
                            mol.reactionIndex = id_reaction;
                            mol.isClassified = true;
                        } else if (maxXarr < minXmol) {
                            mol.reactionComponent = "product";
                            reaction.appendProduct(mol);
                            mol.reactionIndex = id_reaction;
                            mol.isClassified = true;
                        } else {
                            //reactant or substract or product according percentage of atoms
                            var p = percentageLeft(mol, minXarr);
                            if (p < percent) {
                                if (arrows[2 * j].absCoord2D.x < arrows[2 * j + 1].absCoord2D.x) {
                                    mol.reactionComponent = "reactant";
                                    reaction.appendReactant(mol);
                                } else {
                                    mol.reactionComponent = "product";
                                    reaction.appendProduct(mol);
                                }
                                mol.reactionIndex = id_reaction;
                                mol.isClassified = true;
                            } else {
                                p = percentageRight(mol, maxXarr);
                                if (p < percent) {
                                    if (arrows[2 * j].absCoord2D.x < arrows[2 * j + 1].absCoord2D.x) {
                                        mol.reactionComponent = "product";
                                        reaction.appendProduct(mol);
                                    } else {
                                        mol.reactionComponent = "reactant";
                                        reaction.appendReactant(mol);
                                    }
                                    mol.reactionIndex = id_reaction;
                                    mol.isClassified = true;
                                } else {
                                    mol.reactionComponent = "substance";
                                    reaction.appendSubstance(mol);
                                    mol.reactionIndex = id_reaction;
                                    mol.isClassified = true;
                                }
                            }
                        }
                    }
                }
                if (!mol.isClassified) {
                    mol.reactionComponent = "substance";
                    reaction.appendSubstance(mol);
                    mol.reactionIndex = id_reaction;
                    mol.isClassified = true;
                }
            }
        }
    }

    smirksList = [];
    //coordinates of a reaction
    for (var j = 0, n = reactions.length; j < n; ++j) {
        //console.log(reactions[j]);
        var reac = reactions[j];
        reac.id = j;
        if (chemObjectInUseByBlockly) {
            if (chemObjectInUseByBlockly.id == j) {
                chemObjectInUseByBlockly = reac;
            }
        }
        reac.minMaxCoord = getMinMaxCoordReaction(reac, j, mols);
        smirksList[j] = getSMIRKS(j);
    }

}

//minMax reaction
function getMinMaxCoordReaction(reaction, number, mols) {

    //console.log("coord :",reaction.minMaxCoord);
    for (var i = 0, m = mols.length; i < m; ++i) {
        var mol = mols[i];
        if (mol.isClassified && (mol.reactionIndex == number)) {
            //mol
            var minXmol = mol.minMaxCoord[0];
            var minYmol = mol.minMaxCoord[1];
            var maxXmol = mol.minMaxCoord[2];
            var maxYmol = mol.minMaxCoord[3];
            if (minXmol < reaction.minMaxCoord[0]) {
                reaction.minMaxCoord[0] = minXmol;
            }
            if (minYmol < reaction.minMaxCoord[1]) {
                reaction.minMaxCoord[1] = minYmol;
            }
            if (maxXmol > reaction.minMaxCoord[2]) {
                reaction.minMaxCoord[2] = maxXmol;
            }
            if (maxYmol > reaction.minMaxCoord[3]) {
                reaction.minMaxCoord[3] = maxYmol;
            }
        }
    }
    return reaction.minMaxCoord;


}


//define x,y min max coord for each molecule for guessing the reaction area
window.getMinMaxCoord = function (mol) {
    var minX = mol.getNodeAt(0).absCoord2D.x;
    var minY = mol.getNodeAt(0).absCoord2D.y;
    var maxX = mol.getNodeAt(0).absCoord2D.x;
    var maxY = mol.getNodeAt(0).absCoord2D.y;
    for (var i = 1, l = mol.getNodeCount(); i < l; ++i) {
        var node = mol.getNodeAt(i);
        var x = node.absCoord2D.x;
        var y = node.absCoord2D.y;
        if (x < minX) {
            minX = x;
        }
        if (y < minY) {
            minY = y;
        }
        if (x > maxX) {
            maxX = x;
        }
        if (y > maxY) {
            maxY = y;
        }
    }
    return [minX, minY, maxX, maxY];
}

//define x,y min max coord for each arrow for guessing the reaction area
function getMinMaxArrow(arrow_i, arrow_j) {

    var minX, minY, maxX, maxY;
    if (arrow_i.absCoord2D.x < arrow_j.absCoord2D.x) {
        minX = arrow_i.absCoord2D.x;
        maxX = arrow_j.absCoord2D.x;
    } else {
        minX = arrow_j.absCoord2D.x;
        maxX = arrow_i.absCoord2D.x;
    }
    if (arrow_i.absCoord2D.y < arrow_j.absCoord2D.y) {
        minY = arrow_i.absCoord2D.y;
        maxY = arrow_j.absCoord2D.y;
    } else {
        minY = arrow_j.absCoord2D.y;
        maxY = arrow_i.absCoord2D.y;
    }

    return [minX, minY, maxX, maxY];
}

function applyModification(node, newNode, newNodeClass, modifiedProps) {
    var parent;
    var node;
    var exit = false;
    for (var i = 0, l = mols.length; i < l; i++) {
        var mol = mols[i];
        for (var i1 = 0, l1 = mol.getNodeCount(); i1 < l1; ++i1) {
            var atom = mol.getNodeAt(i1);
            if (atom.id === newNode.id) {
                parent = atom;
                node = atom;
                exit = true;
                break;
            }
        }
        if (exit)
            break;
    }

    var newNode;
    var operGroup, oper;
    var oldNodeClass = node.getClass();
    if (newNode && !newNodeClass)
        newNodeClass = newNode.getClass();
    if (newNode || newNodeClass !== oldNodeClass) // need to replace node
    {
        operGroup = new Kekule.MacroOperation();
        //shallow copy
        //var test =  Object.assign(node);
        //deep copy for checking the value
        //console.log(Object.assign({}, newNode));
        var operReplace = new Kekule.ChemStructOperation.ReplaceNode(node, newNode, null, chemEditor);
        operGroup.add(operReplace);
    } else // no need to replace
        newNode = node;
    if (modifiedProps) {
        if (Kekule.ObjUtils.match(node, modifiedProps)) {
            // old value same as new value, no need to create operation
            // console.log(modifiedProps);
        } else {
            oper = new Kekule.ChemObjOperation.Modify(node, modifiedProps, editor);
            if (operGroup)
                operGroup.add(oper);
        }
    }

    var operation = operGroup || oper;
    if (operation) // only execute when there is real modification
    {
        operation.isVariableAtom = true;
        operation.oldSMARTS = newNode.smarts;
        var editor = chemEditor;
        editor.beginUpdateObject();
        try {
            operation.execute();
        } catch (e) {
            //Kekule.error(/*Kekule.ErrorMsg.NOT_A_VALID_ATOM*/Kekule.$L('ErrorMsg.NOT_A_VALID_ATOM'));
            throw (e);
        } finally {
            editor.endUpdateObject();
        }

        if (editor && editor.getEnableOperHistory() && operation) {
            editor.pushOperation(operation);
        }
    }
    chemEditor.select(newNode);
    return operation;
}


window.valueChange = function (e) {
    //type any hetero
    var atom = e.target.nodes[0];
    var atomID = atom.id;
    var newProp = e.value.props;
    chemEditor.select(atom);
    specificProperties = atom.specificProperties;
    var oper = chemEditor.getOperHistory().operations[chemEditor.getOperHistory().operations.length - 1];
    if (newProp.isotopeId) {
        //console.log(oper);
        oper.oldSMARTS = atom.smarts;
        generateAtomListInEditor(atomID, false, [newProp.isotopeId]);
        oper.newSMARTS = atom.smarts;
    } else if (newProp.atomType) {
        //remove last operation as the next function is going to override Kekule operation
        chemEditor.getOperHistory().operations.pop();
        oper = applyModification(null, atom, e.value.nodeClass, {
            isotopeId: 'C'
        });
        atom.symbol = "C";
        if (newProp.atomType === "any") {
            generateAtomListInEditor(atomID, false, ["*"]);
        }
    }
    //change atom type form VaraibleAtom to Atom to prevent the graph being a mess. if multiple atom type it's a dot.
    else if (newProp.allowedIsotopeIds) {
        oper = applyModification(null, atom, e.value.nodeClass, {
            isotopeId: 'C'
        });
        generateAtomListInEditor(atomID, false, newProp.allowedIsotopeIds);
    } else if (newProp.disallowedIsotopeIds) {
        //chemEditor.getOperHistory().pop();
        oper = applyModification(null, atom, e.value.nodeClass, {
            isotopeId: 'C'
        });
        generateAtomListInEditor(atomID, true, newProp.disallowedIsotopeIds);
        //console.log(chemEditor.getOperHistory().operations);
        //chemEditor.getOperHistory().operations = chemEditor.getOperHistory().operations.slice(-1);
    }
    oper.newSMARTS = atom.smarts;
    //select modified atom
    chemEditor.select(atom);

}

window.selectionChange = async function (e) {
    var selObjs = chemComposer.getSelection();
    updateChemObjectAndInterface(selObjs);
}

window.generateMolImg = async function(forceUpdate) {
    var mols = chemComposer.exportObjs(Kekule.Molecule);
    for(var i=0; i<mols.length; i++) {
        var mol = mols[i];
        if ((mol.smarts !== mol.oldSmarts && areReactionImagesUpdated == true) || forceUpdate) {
            mol.oldSmarts = mol.smarts;

            var parser = new DOMParser();
            var svg = parser.parseFromString(
                moleculeToSVG(mol),
                "image/svg+xml"
            ).documentElement;
            svg.setAttribute(
                "style",
                "vertical-align: middle;text-align: center;display: block;margin-left: 0;margin-top: -25;margin-bottom: -15"
            );
            svg.fill = "none";
            svg.setAttribute("width", "100%");
            var height = svg.getAttribute("height");
            var width = svg.getAttribute("width");
            svg.setAttribute(
                "viewBox",
                width * 0.25 + " " + 0 + " " + width * 2 + " " + height
            );
            mol.svgImg = svg;
            hasReactionChanged = true;
            areReactionImagesUpdated = false;
        }
    }
}

async function updateChemObjectAndInterface(selObjs) {
    if (selObjs.length == 1) {
        newSelectedChemObject = selObjs[0];
        var thisClass = newSelectedChemObject.getClassName();
        //TODO Kekule.Pseudoatom
        if (thisClass === 'Kekule.Atom' || thisClass === 'Kekule.Bond' || thisClass === 'Kekule.VariableAtom') {
            displaySmartsMolPanel()
        }
        else {
            if (newSelectedChemObject.id.includes("atomMapping")) {
                newSelectedChemObject = selObjs[0].parent;
                displaySmartsMolPanel();
                chemEditor.select(newSelectedChemObject);
            }
        }
    }
    if (selObjs.length == 0) {
        objEditor.style.display = "none";
        objDefaultEditor.style.display = "block";
    }
    if (depictReactions) {
        displayReactionSMARTS(true, false);
    }
}

function displaySmartsMolPanel() {
    var thisClass = newSelectedChemObject.getClassName();
    chemObjId =
        thisClass +
        "_" +
        newSelectedChemObject.getId() +
        "_" +
        "editor";
    if (!newSelectedChemObject.getParentFragment()) {
        return;
    }
    localSmartsTextField = document.getElementById(chemObjId + "_smarts");
    //console.log(chemObjId, localSmartsTextField, localSmartsTextField.value);
    molID = newSelectedChemObject.getParentFragment().id;
    if (selectedChemObject != null) {
        commonProperties = newSelectedChemObject.commonProperties;
        specificProperties = newSelectedChemObject.specificProperties;
        //old code, bonds have now specific only, so let's make a generic case
        /*if (thisClass === 'Kekule.Bond') {
            commonProperties = newSelectedChemObject.commonProperties ;
        } else {
            commonProperties = newSelectedChemObject.commonProperties;
            specificProperties = newSelectedChemObject.specificProperties;
        }*/
    } else {
        commonProperties = newSelectedChemObject.commonProperties;
        specificProperties = newSelectedChemObject.specificProperties;
        /*if (thisClass === 'Kekule.Bond') {
            commonProperties = newSelectedChemObject.commonProperties ;
        } else {
            commonProperties = newSelectedChemObject.commonProperties;
            specificProperties = newSelectedChemObject.specificProperties;
        }*/
    }

    if (selectedChemObject != null && newSelectedChemObject != null && selectedChemObject != newSelectedChemObject) {
        var toHide = document.getElementById(
            selectedChemObject.getClassName() +
            "_" +
            selectedChemObject.getId() +
            "_" +
            "editor"
        );
        if (toHide != null) {
            toHide.style.display = "none";
        }
    }

    var objEditor2 = document.getElementById(chemObjId);
    if (objEditor2 != null) {
        objEditor.style.display = "none";
        objDefaultEditor.style.display = "none";
        objEditor2.style.display = "block";
        objEditor = objEditor2;
    } else {
        objDefaultEditor.style.display = "none";
    }

    if (selectedChemObject != newSelectedChemObject)
        selectedChemObject = newSelectedChemObject;
    defaultMol = selectedChemObject.getParentFragment();
    molID = defaultMol.id;
}

window.operUndo = function (e) {
    var operations = chemEditor.getOperHistory().operations;
    var index = e.currOperIndex;
    var op = operations[index];

    var next = true;
    while (next && index > -1) {
        //console.log("TEST ",index, op.flagged, op);
        if (op.oldSMARTS) {
            showPeriodicTable = false;
            if (op.isVariableAtom) {
                chemEditor.getOperHistory().undo();
                chemEditor.getOperHistory().undo();
                index-=2;
                //SOmetimes the oper before the node change is not flagged so flag it
                operations[index].flagged = true;
            }

            var atom = op.target ? op.target : op.children[0].newNode;
            chemObjId = "Kekule.Atom_" + atom.id + "_editor";
            var elt = document.getElementById("atomSpecificContainer_" + chemObjId);
            //keep the button and the first select div
            clearAtomSpecificContainer(elt, 1);
            atom.smarts = op.oldSMARTS;
            parseAtomSmartsWithoutCreatingEditor(atom);
            chemEditor.select(atom);
            showPeriodicTable = true;
            next = false;
        }
        else if (op.flagged) {
            //console.log("TEST2 ",index, op);
            next = false;
            if (!op.children) {
                next = true;
            }
            else if (!op.children[0].children) {
                var optOld = op.children[0].oldPropValues.renderOptions;
                var optNew = op.children[0].newPropValues.renderOptions;
                //console.log("TEST3 ",optOld, optNew);
                //test variable atom
                if (optOld && optNew) {
                    if (optOld.atomRadius !== undefined && optNew.atomRadius !== undefined) {
                        if (optOld.atomRadius !== optNew.atomRadius) {
                            next = true;
                        }
                    }
                }
            }
        }
        if (next)
            chemEditor.getOperHistory().undo();
        else 
            break;
        index--;
        op = operations[index];
    }
    updateChemObjectEditor(false);
    //console.log(index,operations);
}

window.operRedo = function (e) {
    //console.log(e.operation);
    var operations = chemEditor.getOperHistory().operations;
    var index = e.currOperIndex;
    var op = operations[index];
    let next = true;
    while (next && index < operations.length) {
        commonProperties = [];
        specificProperties = [];
        //console.log(index,operations);
        if (op.oldSMARTS) {
            showPeriodicTable = false;
            chemEditor.select(op.children[1].target);
            let savedChemObjId = chemObjId;
            chemObjId = "Kekule.Atom_" + op.children[1].target.id + "_editor";
            var elt = document.getElementById("atomSpecificContainer_" + chemObjId);
            //keep the button and the first select div
            clearAtomSpecificContainer(elt, 1);
            var atom = op.target ? op.target : op.children[0].newNode;
            atom.smarts = op.newSMARTS;
            parseAtomSmartsWithoutCreatingEditor(atom);
            chemObjId = savedChemObjId;
            showPeriodicTable = true;
            index++;
            op = operations[index];
            chemEditor.getOperHistory().redo();
        } else if (op.flagged) {
            next = false;
            break;
        }
        if (next)
            chemEditor.getOperHistory().redo();
        else
            break;
    index++
    op = operations[index];
    }
    updateChemObjectEditor(false);
}

//OLD way
window.operUndo2 = function (e) {
    var operations = chemEditor.getOperHistory().operations;
    var currIndex = e.currOperIndex;
    var index = currIndex + 1;
    var op = operations[index];
    var nextOp = operations[currIndex];

    var test = true;
    
    if (!op.children) {
        test = false;
    }
   
    var forceUndo = false;
    while (test && currIndex > -1) {
        chemEditor.getOperHistory().undo();
        if (op.oldSMARTS) {
            showPeriodicTable = false;
            var op2;
            if (op.isVariableAtom) {
                var op2 = chemEditor.getOperHistory().undo();
            }
            var atom = op.target ? op.target : op.children[0].newNode;
            chemObjId = "Kekule.Atom_" + atom.id + "_editor";
            var elt = document.getElementById("atomSpecificContainer_" + chemObjId);
            //keep the button and the first select div
            clearAtomSpecificContainer(elt, 1);
            atom.smarts = op.oldSMARTS;
            parseAtomSmartsWithoutCreatingEditor(atom);
            chemEditor.select(atom);
            showPeriodicTable = true;
            //Fix for some reason, those operation are deleted. So, they are added back
            chemEditor.getOperHistory().operations.push(op);
            if (op2)
                chemEditor.getOperHistory().operations.push(op2);
            forceUndo = false;
            break;
        }
        
        else if (op.children) {
            if (!op.children[0].children) {
                test = true;
                var optOld = op.children[0].oldPropValues.renderOptions;
                var optNew = op.children[0].newPropValues.renderOptions;
                //test varaiable atom
                if (optOld && optNew) {
                    if (optOld.atomRadius !== undefined && optNew.atomRadius !== undefined) {
                        if (optOld.atomRadius !== optNew.atomRadius) {
                            test = false;
                            forceUndo = true;
                        }
                    }
                }
            } else {
                if (op.children[0].children[0].target) {
                    if (op.children[0].children[0].target.id.startsWith("p")) {
                        //temp fix for not deletting atom and arrow at the same time
                        //chemEditor.getOperHistory().redo();
                        test = false;
                    } else if (op.children[0].children[0].target.id.startsWith("a")) {
                        //chemEditor.getOperHistory().redo();
                        test = false;
                    } else if (op.children[0].children[0].target.id.startsWith("m")) {
                        //chemEditor.getOperHistory().redo();
                        test = false;
                    }
                }
            }
        } else {
            test = false
        }
        currIndex--;
        if (forceUndo && currIndex > 0) {
            test = true;
            forceUndo = false;
        }
        op = operations[currIndex];
    }
    updateChemObjectEditor(false);
}

//old way
window.operRedo2 = function (e) {
    var operations = chemEditor.getOperHistory().operations;
    var currIndex = e.currOperIndex;
    var op = operations[currIndex];
    let isAdded = false;
    //console.log(currIndex,chemEditor.getOperHistory().operations);
    if (op.children) {
        while (!isAdded && currIndex < operations.length) {
            op = operations[currIndex];
            //console.log(currIndex,op);
            //restore variable atom
            if (op.oldSMARTS) {
                showPeriodicTable = false;
                var elt = document.getElementById("atomSpecificContainer_" + chemObjId);
                //keep the button and the first select div
                clearAtomSpecificContainer(elt, 1);
                var atom = op.target ? op.target : op.children[0].newNode;
                atom.smarts = op.newSMARTS;
                parseAtomSmartsWithoutCreatingEditor(atom);
                showPeriodicTable = true;
                chemEditor.getOperHistory().redo();
            } else if (op.children[0].target) {
                var id = op.children[0].target.id;
                //restore variable atom
                if (!op.children[0].children) {
                    var optOld = op.children[0].oldPropValues.renderOptions;
                    var optNew = op.children[0].newPropValues.renderOptions;
                    if (optOld && optNew) {
                        if (optOld.atomRadius !== undefined && optNew.atomRadius !== undefined) {
                            if (optOld.atomRadius !== optNew.atomRadius) {
                                //isAdded = true;
                            }
                        }
                    }
                }
                if (op.children[0].oldPropValues) {
                    chemEditor.getOperHistory().redo();
                    console.log(currIndex, operations[currIndex]);
                }
                //console.log(id, op);
                else if (id && (id.startsWith("a") || id.startsWith("b"))) {
                    var mols = chemComposer.exportObjs(Kekule.Molecule);
                    for (var i = 0; i < mols.length; i++) {
                        var mol = mols[i];
                        if (id.startsWith("a")) {
                            for (var i1 = 0, l1 = mol.getNodeCount(); i1 < l1; ++i1) {
                                var node = mol.getNodeAt(i1);
                                if (node.id === id) {
                                    isAdded = true;
                                    break;
                                }
                            }
                        } else if (id.startsWith("b")) {
                            for (var i1 = 0, l1 = mol.getConnectorCount(); i1 < l1; ++i1) {
                                var connector = mol.getConnectorAt(i1);
                                if (connector.id === id) {
                                    isAdded = true;
                                    break;
                                }
                            }
                        }
                        if (isAdded) {
                            break;
                        }
                    }
                } else {
                    isAdded = true;
                }
                if (!isAdded) {
                    //console.log(currIndex,operations[currIndex]);
                    if (operations[currIndex]) {
                        if (operations[currIndex].children) {
                            if (operations[currIndex].children[0].children) {
                                if (operations[currIndex].children[0].children) {
                                    if (operations[currIndex].children[0].children[0].target) {
                                        if (operations[currIndex].children[0].children[0].target.id.startsWith("p")) {
                                            //console.log(currIndex,operations[currIndex]);
                                            isAdded = true;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        //console.log(currIndex,operations[currIndex]);
                        //chemEditor.getOperHistory().redo();
                    }
                }
            } else {
                isAdded = true;
            }
            currIndex++;
        }
    } else if (op.oldSMARTS) {
        showPeriodicTable = false;
        var elt = document.getElementById("atomSpecificContainer_" + chemObjId);
        //keep the button and the first select div
        clearAtomSpecificContainer(elt, 1);
        var atom = op.target ? op.target : op.children[0].newNode;
        atom.smarts = op.newSMARTS;
        parseAtomSmartsWithoutCreatingEditor(atom);
        showPeriodicTable = true;
        chemEditor.getOperHistory().redo();
    }
    updateChemObjectEditor(false);
}

window.editObjChanged = async function (e) {
    /*operationCounter = chemEditor.getOperHistory().operations.length;
    var operations = chemEditor.getOperHistory().operations;
    console.log(chemEditor.getOperHistory());
    operations[operations.length - 1].ignored = false;*/
    //1 GET CONTROLLER type 2 get details and update differences if id starts with m and then a figure, do something
    //MolBondIaController MolAtomIaController "BasicMolEraserIaController" PathGlyphIaController SelectIaController StructureInsertIaController
    //ArrowLineIaController
    //do it only once; (not for load. A change triggers 2 events: load and editObjsUpdated and only if object is modified not coord
    if (e.name === "editObjsUpdated" && !fileOnLoad) {
        //only get structural info (all renderer modifications are ignored)
        var test = (e.details[0]) ? (e.details[0]["propNames"][0] ?? "") : "";
        if (test && test !== "coord2D" && test !== "renderOptions") {
            //console.log("CHANGE",e.target.activeIaControllerId,e);
            //an object (atom, bond, molecule has been deleted)
            if (e.target.activeIaControllerId === "BasicMolEraserIaController") {
                updateChemObjectEditor(true);
                
                flagLastOperation();
            }
            //a bond has been added or modified
            else if (e.target.activeIaControllerId === "MolBondIaController") {
                var atom;
                var bond;
                var bondToAdd = await updateChemObjectEditor(true);
                var select;
                chemEditor.deselectAll();
                if (e.details[0].obj.id.startsWith("b")) {
                    bond = e.details[0].obj;
                    select = setupBond(bond.id, bond);
                } else if (bondToAdd.length == 1) {
                    bond = bondToAdd[0];
                    select = setupBond(bond.id, bond);
                } else {
                    var keys = Object.keys(e.details);
                    for (var i = keys.length - 1; i > -1; i--) {
                        if (e.details[i].obj.id.startsWith("b")) {
                            bond = e.details[i].obj;
                            select = setupBond(bond.id, bond);
                        }
                        else if (e.details[i].obj.id.startsWith("a")) {
                            if (e.details[i].propNames.includes("allowedIsotopeIds") ||
                            e.details[i].propNames.includes("disallowedIsotopeIds")) {
                                atom = e.details[i].obj;
                            }
                        }
                    }
                }
                if (bond) {
                    commonProperties = bond.commonProperties;
                    specificProperties = bond.specificProperties;
                    chemEditor.select(bond);
                    await updateChemObjectAndInterface([bond]);
                    showSelectParameters(select.id);
                }
                if (atom) {
                    chemEditor.deselectAll();
                    console.log(atom);
                    chemEditor.select(atom);
                }
                flagLastOperation();
            }
            //add single atom (like methane)
            else if (e.target.activeIaControllerId === "RepositoryStructureFragmentIaController") {
                updateChemObjectEditor(true);
                flagLastOperation();
            }
            //add ring
            else if (e.target.activeIaControllerId === "MolRingIaController") {
                updateChemObjectEditor(true);
                flagLastOperation();
            } else if (e.target.activeIaControllerId === "ArrowLineIaController") {
                updateChemObjectEditor(true);
                flagLastOperation();
            } else if (e.target.activeIaControllerId === "MolFlexRingIaController") {
                updateChemObjectEditor(true);
                flagLastOperation();
            } else if (e.target.activeIaControllerId === "MolFlexChainIaController") {
                updateChemObjectEditor(true);
                flagLastOperation();
            }  else if (e.target.activeIaControllerId === "TrackInputIaController") {
                updateChemObjectEditor(true);
                flagLastOperation();
            }
            else {
                console.log(e.target.activeIaControllerId);
            }
        }
        else if (test == "coord2D" && test !== "renderOptions") {
            if (e.target.activeIaControllerId === "MolFlexRingIaController") {
                updateChemObjectEditor(true);
            } else if (e.target.activeIaControllerId === "MolFlexChainIaController") {
                updateChemObjectEditor(true);
            }  else if (e.target.activeIaControllerId === "TrackInputIaController") {
                updateChemObjectEditor(true);
            } else if (e.target.activeIaControllerId === "SelectIaController" || 
                    e.target.activeIaControllerId === "RepositoryStructureFragmentIaController") {
                displayReactionSMARTS(true, false);
            }
            else {
                //console.log(e.target.activeIaControllerId);
            }
            flagLastOperation();
        }
    }
}

function flagLastOperation() {
    var operations = chemEditor.getOperHistory().operations;
    if (operations.length > 0) {
        operations[operations.length - 1].flagged = true;
    }
}

function setupBond(bondID, bond) {
    var select = document.getElementById("Kekule.Bond_" + bondID + "_editor_bondSpecificPropertySelect_0_0");
    //console.log(select);
    if (bond.stereo == 0) {
        if (bond.bondOrder == 1) {
            select.selectedIndex = 0;
        } else if (bond.bondOrder == 2) {
            select.selectedIndex = 1;
        } else if (bond.bondOrder == 3) {
            select.selectedIndex = 2;
        } else if (bond.bondOrder == 4) {
            select.selectedIndex = 3;
        } else if (bond.bondOrder == 10) {
            select.selectedIndex = 4;
        }
    } else {
        if (bond.stereo == 10) {
            select.selectedIndex = 6;
        } else if (bond.stereo == 1) {
            select.selectedIndex = 7;
        } else if (bond.stereo == 3) {
            select.selectedIndex = 9;
        }
    }
    return select;
}

function generateAtomListInEditor(id, not, prop) {
    showPeriodicTable = false;
    chemObjId = "Kekule.Atom_" + id + "_editor";
    var elt = document.getElementById("atomSpecificContainer_" + chemObjId);
    //keep the or button
    clearAtomSpecificContainer(elt, 1);
    for (var i = 0, l = prop.length; i < l; i++) {
        var returnedReference = addSpecificAtomPropertyBlock(chemObjId);
        var div = addOneSpecificAtomProperty(returnedReference, returnedReference.id.split('-').pop());
        var children = div.childNodes;
        var select = children[1];
        //select value
        select.selectedIndex = atomProperties.indexOf("Chemical Element");
        showSelectParameters(select.id);
        //console.log(isSpecific,op,div.childNodes);
        //check is checkbox if needed
        var is = children[2].childNodes[0];
        if (not) {
            is.checked = true;
        }
        var aromaticity = children[3];
        aromaticity.innerHTML = "either";
        aromaticity.setAttribute("value", "either");
        var symbol = children[4];
        symbol.innerHTML = prop[i];
        symbol.value = prop[i];
        if (symbol.id.includes("Specific")) {
            storePropertyInArray(symbol, false);
        } else {
            storePropertyInArray(symbol, true);
        }
    }
    showPeriodicTable = true;
}

function clearAtomSpecificContainer(parent, len) {
    while (parent.childNodes.length > len) {
        deleteSpecificPropertyBlock(parent.firstChild);
    }
}

window.moleculeToSVG = function (chemObj) {
    chemViewer.setAutoSize(true);
    if (chemObj) {
        chemViewer.setChemObj(chemObj);

        var context = chemViewer.getDrawContext();
        if (context && context.toSVG) {
            var svg = context.toSVG();
            //console.log('SVG: ', svg);
            return svg;
        }
    }
}

window.updateChemObjectEditor = async function (isIndependentAction) {
    var atoms = {};
    var bonds = {};
    var molIDs = [];
    //get all mols
    var mols = chemComposer.exportObjs(Kekule.Molecule);
    for (var i = 0, l = mols.length; i < l; ++i) {
        var mol = mols[i];
        molIDs.push(mol.id);
        for (var j = 0, m = mol.getNodeCount(); j < m; ++j) {
            var a = mol.getNodeAt(j);
            atoms[a.id] = a;
        }
        for (var j = 0, m = mol.getConnectorCount(); j < m; ++j) {
            var b = mol.getConnectorAt(j);
            bonds[b.id] = b;
        }
    }

//this delete all the blocks of molecules when any change is made
    /*if (blocklyXML) {
        if (!isIndependentAction)
            savedBlockly = blocklyXML;
        var newBlocklyIDs = molIDs;
        var oldBlocklyIDs = Object.keys(blocklyXML);
        for (var i = 0, l = reactions.length; i < l; i++) {
            newBlocklyIDs.push("reaction_" + i);
        }
        var toDelete = oldBlocklyIDs.filter(x => !Object.keys(newBlocklyIDs).includes(x));
        toDelete.forEach(id => {
            delete blocklyXML[id];
        });
        var toAdd = Object.keys(newBlocklyIDs).filter(x => !oldBlocklyIDs.includes(x));
        toAdd.forEach(id => {
            if (!isIndependentAction && savedBlockly[id]) {
                blocklyXML[id] = savedBlockly[id];
            }
        });
    }*/


    //delete div related to deleted atoms and bond
    var editorAtoms = [];
    var editorBonds = [];
    var sliceContainerChildren = document.getElementById("sliceContainer").childNodes;
    for (var i = 4, l = sliceContainerChildren.length; i < l; i++) {
        //console.log(sliceContainerChildren[i]);
        var childID = sliceContainerChildren[i].id;
        if (childID.startsWith("Kekule.Atom_") && childID.endsWith("_editor")) {
            var atID = childID.substring(childID.search("Kekule.Atom_") + "Kekule.Atom_".length, childID.search("_editor"));
            editorAtoms.push(atID);
        } else if (childID.startsWith("Kekule.Bond_") && childID.endsWith("_editor")) {
            var bID = childID.substring(childID.search("Kekule.Bond_") + "Kekule.Bond_".length, childID.search("_editor"));
            editorBonds.push(bID);
        }
    }
    //delete entries, which have no corresponding value in the molecule
    //console.log(editorAtoms,atoms);
    var toDelete = editorAtoms.filter(x => !Object.keys(atoms).includes(x));
    //console.log(toDelete);
    toDelete.forEach(atID => {
        var elt = document.getElementById("Kekule.Atom_" + atID + "_editor");
        if (!isIndependentAction)
            savedEditorSections[atID] = elt;
        elt.remove();
    });
    //console.log(editorBonds,bonds);
    toDelete = editorBonds.filter(x => !Object.keys(bonds).includes(x));
    toDelete.forEach(bID => {
        var elt = document.getElementById("Kekule.Bond_" + bID + "_editor");
        if (!isIndependentAction)
            savedEditorSections[bID] = elt;
        elt.remove();
    });
    //console.log(toDelete);

    //add editor for newly added atoms and bonds
    var atomToAdd = Object.keys(atoms).filter(x => !editorAtoms.includes(x));
    for (var i = 0, l = atomToAdd.length; i < l; ++i) {
        var atID = atomToAdd[i];
        var mol = atoms[atID].getParentFragment();
        //console.log(atID, atomToAdd);
        chemObjId = "Kekule.Atom_" + atID + "_editor";
        var objEditor2 = document.getElementById("Kekule.Atom_" + atID + "_editor");
        if (objEditor2 != null) {
            commonProperties = [];
            specificProperties = [];
            objEditor2.style.display = "none";
        } else {
            if (!isIndependentAction && savedEditorSections[atID]) {
                var container = document.getElementById("sliceContainer");
                container.appendChild(savedEditorSections[atID]);
                if (newSelectedChemObject) {
                    commonProperties = newSelectedChemObject.commonProperties;
                    specificProperties = newSelectedChemObject.specificProperties;
                }
            } else {
                commonProperties = [];
                specificProperties = [];
                newSelectedChemObject = atoms[atID];
                chemObjId = "Kekule.Atom_" + atID + "_editor";
                objEditor2 = createChemObjEditor(chemObjId, atoms[atID]);
                objEditor2.style.display = "none";
                newSelectedChemObject.commonProperties = commonProperties;
                newSelectedChemObject.specificProperties = specificProperties;
                chemEditor.deselectAll();
                newSelectedChemObject = null;
            }
        }
    }
    var bondIdToAdd = Object.keys(bonds).filter(x => !editorBonds.includes(x));
    var bondToAdd = [];
    //console.log(toAdd);
    for (var i = 0, l = bondIdToAdd.length; i < l; ++i) {
        var bID = bondIdToAdd[i];
        bondToAdd.push(bonds[bID]);
        var mol = bonds[bID].getParentFragment();
        chemObjId = "Kekule.Bond_" + bID + "_editor";
        var objEditor2 = document.getElementById(chemObjId);
        if (objEditor2 != null) {
            commonProperties = [];
            specificProperties = [];
            objEditor2.style.display = "none";
        } else {
            if (!isIndependentAction && savedEditorSections[bID]) {
                var container = document.getElementById("sliceContainer");
                container.appendChild(savedEditorSections[bID]);
                if (newSelectedChemObject) {
                    commonProperties = newSelectedChemObject.commonProperties;
                    specificProperties = newSelectedChemObject.specificProperties;
                }
            } else {
                commonProperties = [];
                specificProperties = [];
                newSelectedChemObject = bonds[bID];
                objEditor2 = createChemObjEditor(chemObjId, bonds[bID]);
                objEditor2.style.display = "none";
                newSelectedChemObject.commonProperties = commonProperties;
                newSelectedChemObject.specificProperties = specificProperties;
                newSelectedChemObject = null;
            }
        }
    }
    if (selectedChemObject) {
        commonProperties = selectedChemObject.commonProperties;
        specificProperties = selectedChemObject.specificProperties;
    }
    defaultMol = null;
    if (toDelete.length > 0 || atomToAdd.length > 0 || bondIdToAdd.length > 0)
        displayReactionSMARTS(true, false);
    else
        displayReactionSMARTS(false, false);
    //updateLogicAssistantPage();
    return bondToAdd;
}
