//reload the interface
window.reloadInterface = function () {
    location.reload()
}
//generate molecules
/*
window.generateMolecules = async function (evt) {
    console.log(currentFilePath);
    var response = await electronApi.openFileDialog('Open Buidling blocks files', [
        { name: 'BBS', extensions: ['tsv','csv','txt'] }
      ]);
    if (response.length > 0) {
        openWin();
        currentbuildingblocks = response[0];
        const rawData = await fs.readFileSync(currentbuildingblocks, 'utf8');
        console.log(currentbuildingblocks);
        const __dirname = path.resolve(path.dirname(''));
        console.log(__dirname);
        const relativePath = 'java -jar '+__dirname+'/jar/SAVI-1.0.0-jar-with-dependencies.jar -b'+currentbuildingblocks+ ' -s '+currentFilePath;
        console.log(relativePath);
       // const exec = fs.require('child_process').exec;
        const childPorcess = exec(relativePath, function(err, stdout, stderr) {
            if (err) {
                console.log(err)
            }
            console.log(stdout)
        })
    }
}*/

window.OpengenerateMolecules = async function (evt) {
   let popup= document.getElementById("popup");
    popup.classList.add("open-popup");
}


window.openWin= function (){
    var myBars = 'directories=no,location=no,menubar=no,status=no';
    
    myBars += ',titlebar=no,toolbar=no';
    var myOptions = 'scrollbars=no,width=400,height=200,resizeable=no';
    var myFeatures = myBars + ',' + myOptions;
    var myReadme = 'This is a test.'
    
    var newWin = open('', 'myDoc', myFeatures);
    
    newWin.document.writeln('<form>');
    newWin.document.writeln('<table>');
    newWin.document.writeln('<tr valign=TOP><td>');
    newWin.document.writeln('<textarea cols=45 rows=7 wrap=SOFT>');
    newWin.document.writeln(myReadme + '</textarea>');
    newWin.document.writeln('</td></tr>');
    newWin.document.writeln('<tr><td>');
    newWin.document.writeln('<input type=BUTTON value="Close"');
    newWin.document.writeln(' onClick="window.close()">');
    newWin.document.writeln('</td></tr>');
    newWin.document.writeln('</table></form>');
    newWin.document.close();
    newWin.focus();

}
window.refresh = function () {
    //old way which refresh the whole app
    //location.reload()
    //reset variables
    defaultMol = null;
    chemObjectInUseByBlockly = null
    selectedChemObject = null;
    newSelectedChemObject = null;
    molID = null;
    chemObjId = null;
    reactions = [];
    mols = [];
    smirksList = [];
    domDict = {};
    addDefaultField = true;
    showPeriodicTable = true;
    objEditor = objDefaultEditor;
    //clear kekule
    chemComposer.newDoc();
    //clear editor panel
    var toDelete = [];
    var sliceContainerChildren = document.getElementById("sliceContainer").childNodes;
    for (var i = 0, l = sliceContainerChildren.length; i < l; i++) {
        var childID = sliceContainerChildren[i].id;
        if (childID) {
            if (childID.endsWith("_editor")) {
                toDelete.push(childID);
            }
        }
    }
    toDelete.forEach(id => document.getElementById(id).remove());
    //reset Description tab
    document.getElementById("historyDiv").innerHTML = "";
    document.getElementById("bibliographyDiv").innerHTML = "";
    document.getElementById("ChemDescriptionForm").reset();
    //reset Patterns tab
    document.getElementById("ChemPattern").innerHTML = "<p>Pattern will be seen, once a molecule is created</p>";
    //reset Conditions tab
    toDelete = [];
    var chemConditionsChildren = document.getElementById("ChemConditions").childNodes;
    for (var i = 0, l = chemConditionsChildren.length; i < l; i++) {
        var childID = chemConditionsChildren[i].id;
        if (childID) {
            if (childID.includes("conditionDiv_")) {
                toDelete.push(childID);
            }
        }
    }
    toDelete.forEach(id => document.getElementById(id).remove());
    //reset logic
    blocklyXML = {};
    //document.getElementById("ChemLogic-info").style.display = "block";
    var logic_tab = document.getElementById("ChemLogic");
    var reactionImageDiv = document.getElementById("reactionImage");
    //clear previous content
    while (reactionImageDiv.firstChild) {
        reactionImageDiv.removeChild(reactionImageDiv.firstChild);
    }
    var btns = logic_tab.getElementsByClassName("reactionButton");

    // Loop through the buttons and add the active class to the current/clicked button
    for (var i = 0, l = btns.length; i < l; i++) {
        if (i == 0) {
            btns[i].innerHTML = "Unclassified Molecules";
        } else {
            btns[i].remove();
        }
    }

    document.getElementById("blocklyDiv").style.display = "none";
    logic_tab.style.display = "block";
    //reset slice editor
    document.getElementById("ChemEditorText").innerHTML = "";

    descriptionAndCondition["transform"] = {};
    descriptionAndCondition["history"] = {};
    descriptionAndCondition["setup"] = {};
    descriptionAndCondition["bibliography"] = {};
    descriptionAndCondition["comments"] = {};
    descriptionAndCondition["conditions"] = {};
    // reset title
    document.title = "SLICE Designer 1.3 - Untitled.jslice [Unsaved]"
}

window.loadFile = async function (evt) {
    loadFile2(evt);
}

window.saveAsFile = async function () {
    saveAsFile2();
}

window.saveFile = async function () {
    if (currentFilePath == null) {
        saveAsFile();
    }
    else {
        writeFile();
    }
}

window.writeFile = async function() {
    //var data = Kekule.IO.saveFormatData(mol, 'cml');
    writeSliceFile;
    var json = {};
    //TODO associate smarts to mol id chemComposer.exportObjs(Kekule.Molecule);
    //TODO check import 
    var mols = chemComposer.exportObjs(Kekule.Molecule);
    var info = {};
    for (var i = 0; i < mols.length; i++) {
        var mol = mols[i];
        info[mol.id] = {};
        info[mol.id]["smarts"] = mol.smarts;
        info[mol.id]["reactionComponent"] = mol.reactionComponent;
        for (var i1 = 0, l1 = mol.getNodeCount(); i1 < l1; ++i1) {
            var node = mol.getNodeAt(i1);
            //console.log(i1, node.absCoord2D);//STEFI
            //info[node.id] = node.smarts;
            if (node.simpfiedSmarts) {
                info[node.id] = node.simpfiedSmarts;
            }
            else {
                info[node.id] = "C";
            }
        }
        // iterate all connectors(bonds)
        for (var i1 = 0, l1 = mol.getConnectorCount(); i1 < l1; ++i1) {
            var connector = mol.getConnectorAt(i1);
            console.log(connector.id, connector.smarts);
            if (connector.smarts) {
                info[connector.id] = connector.smarts;
            }
            else {
                info[connector.id] = "-";
            }
        }
    }
    json["ChemEditor"] = document.getElementById("ChemEditorText").textContent;

    //fix Kekule bug, where coordinates are not updated while saving
    json["kekule"] = Kekule.IO.saveFormatData(chemComposer.getChemObj(), 'Kekule-JSON');
    json["kekule"] = updateCoordinates(json["kekule"]);
    //console.log(JSON.parse(json["kekule"]));
    json["info"] = info;
    //json["sliceContainer"] = document.getElementById("sliceContainer").innerHTML;

    //save logic for selected molecule
    if (chemObjectInUseByBlockly) {
        if (chemObjectInUseByBlockly.id) {
            var xml = saveBlocks();
            blocklyXML[chemObjectInUseByBlockly.id] = xml;
        } else {
            var btnContainer = document.getElementById("ChemLogic");
            var btns = btnContainer.getElementsByClassName("reactionButton");
            // Loop through the buttons and add the active class to the current/clicked button
            for (var i = 0, l = btns.length; i < l; i++) {
                var btn = btns[i];
                var className = btn.className;
                if (className.includes("active") && btn.innerHTML.includes("Reaction")) {
                    var xml = saveBlocks();
                    blocklyXML["reaction_" + i] = xml;
                }
            }
        }
    }

    json["blockly"] = blocklyXML;
    const content = JSON.stringify(json);

    
    fs.writeFile(currentFilePath,content,(err) => {
        if (err)
          console.log(err);
        else {
          console.log("File written successfully");
        }
      });
      fs.writeFile(currentFilePath.replace(".jslice", ".slice"),json["ChemEditor"],(err) => {
        if (err)
          console.log(err);
        
      });

    //URL.revokeObjectURL();
}

function updateCoordinates(jsonText) {
    var data = JSON.parse(jsonText);
    var objs = data.root.children.items;
    var arrows = chemComposer.exportObjs(Kekule.Glyph.StraightLine);
    arrows.push.apply(arrows, chemComposer.exportObjs(Kekule.Glyph.ReactionArrow));

    for (var i = 0, l = objs.length; i < l; i++) {
        var obj = objs[i];
        if (obj.id.startsWith('p')) {
            for (var j = 0, m = arrows.length; j < m; j++) {
                if (arrows[j].id === obj.id) {
                    if (arrows[j].savedCoord2D) {
                        obj.coord2D = arrows[j].savedCoord2D;
                    }
                }
            }
        }
    }
    return JSON.stringify(data);
}

function createChemDoc() {
    var chemDoc = new Kekule.ChemDocument();
    // the chem doc settings are borrowed from the default editor configs
    var editorConfigs = Kekule.Editor.ChemSpaceEditorConfigs.getInstance();
    var renderConfigs = Kekule.Render.Render2DConfigs.getInstance();
    var screenSize = editorConfigs.getChemSpaceConfigs().getDefScreenSize2D();
    var objRefLength = editorConfigs.getStructureConfigs().getDefBondLength();
    var screenRefLength = renderConfigs.getLengthConfigs().getDefBondLength();
    var ratio = objRefLength / screenRefLength;
    chemDoc.setDefAutoScaleRefLength(objRefLength);
    chemDoc.setObjScreenLengthRatio(ratio);
    chemDoc.setSize2D({
        'x': screenSize.x * ratio,
        'y': screenSize.y * ratio
    });

    chemDoc.beginUpdate();
    return chemDoc;
}

function drawReactionFromJson(kekuleJson, info) {
    var chemDoc = createChemDoc();
    var data = JSON.parse(kekuleJson);
    var items = data.root.children.items;
    var atomDict = {}
    var reactionList = new Kekule.ReactionList();
    var reaction = new Kekule.Reaction();
    var arrows = [];
    //console.log(reaction);
    //TODO USE CDK to generate 2d coordinates in order to only take into account the smarts pattern
    for (var i = 0; i < items.length; i++) {
        var item = items[i];
        if (item.__type__ === "Kekule.Molecule") {
            var mol = new Kekule.Molecule();
            mol.id = item.id;
            mol.reactionComponent = info[mol.id].reactionComponent;
            mol.smarts = info[mol.id].smarts;
            var atoms = item.ctab.nodes;
            // STEFI: I remove it to make atoms appears on the screen
            if (item.coord2D) {
                mol.coord2D = item.coord2D;
            }
            for (var j = 0, m = atoms.length; j < m; j++) {
                var at = atoms[j];
                var atom = null;
                //console.log("item",item);
                if (m == 1) {
                    var atom = (new Kekule.Atom()).setSymbol(at.isotopeId).setId(at.id).setCoord2D({
                        'x': 0,
                        'y': 0
                    });
                } else {
                    var atom = (new Kekule.Atom()).setSymbol(at.isotopeId).setId(at.id).setCoord2D({
                        'x': at.coord2D.x,
                        'y': at.coord2D.y
                    });
                }
                atom.smarts = info[at.id];
                mol.appendNode(atom);
                atomDict[at.id] = atom;
            }
            var bonds = item.ctab.connectors;
            for (var j = 0; j < bonds.length; j++) {
                var b = bonds[j];
                var bond = (new Kekule.Bond()).setBondOrder(b.bondOrder).setId(b.id).setConnectedObjs([atomDict[atoms[b.connectedObjs[0]].id], atomDict[atoms[b.connectedObjs[1]].id]]);
                bond.smarts = info[b.id];
                mol.appendConnector(bond);
            }
            if (mol.reactionComponent === "reactant") {
                if (reaction.getReactantCount > 0 && reaction.getProductCount > 0) {
                    reactionList.append(reaction);
                    reaction = new Kekule.Reaction();
                    //console.log("NEW",item);
                }
                reaction.appendReactant(mol, null, 1, null);
            } else if (mol.reactionComponent === "substance") {
                reaction.appendSubstance(mol, null, 1, null);
            } else {
                reaction.appendProduct(mol, null, 1, null);
            }
            chemDoc.appendChild(mol);
            //console.log(mol);
        } else if (item.__type__ === "Kekule.Glyph.ReactionArrow" || item.__type__ === "Kekule.Glyph.StraightLine") {
            //normal arrow
            var arrow = new Kekule.Glyph.StraightLine(null, chemDoc.getDefAutoScaleRefLength(), {
                'endArrowType': Kekule.Glyph.ArrowType.OPEN,
                'endArrowWidth': 0.25,
                'endArrowLength': 0.25,
                'startArrowType': Kekule.Glyph.ArrowType.NONE,
                'lineLength': 1.5
            });
            //reversible
            /*var arrow = new Kekule.Glyph.StraightLine(null, chemDoc.getDefAutoScaleRefLength(), {
                'startArrowType': Kekule.Glyph.ArrowType.OPEN,
				'startArrowSide': Kekule.Glyph.ArrowSide.REVERSED,
				'endArrowType': Kekule.Glyph.ArrowType.OPEN,
				'endArrowSide': Kekule.Glyph.ArrowSide.SINGLE,
				'lineGap': 0.1,
				'lineCount': 2,
                'startArrowWidth': 0.25,
                'startArrowLength': 0.25,
                'endArrowWidth': 0.25,
                'endArrowLength': 0.25,
                //'lineLength': 1.5
              });*/
            arrow.coord2D = item.coord2D;
            arrow.id = item.id;
            arrow.reactionType = item.reactionType;
            chemDoc.appendChild(arrow);
        } else if (item.__type__ === "Kekule.Glyph.AddSymbol") {
            var plus = new Kekule.Glyph.AddSymbol(null, chemDoc.getDefAutoScaleRefLength(), {
                'lineLength': 1.0
            });
            plus.coord2D = item.coord2D;
            plus.id = item.id;
            chemDoc.appendChild(plus);
        }
    }
    reactionList.append(reaction);
    //if reaction is used exportsObjs(Kekule.Molecule) doesn't work anymore, for consistency it's imported as molecule
    //chemComposer.setChemObj(reactionList);

    //https://github.com/partridgejiang/Kekule.js/blob/9d30df24bfe9273fe6d419e264e431728358e175/test/widgetTest/chemWidgets/rxnEmbedTest.html
    //console.log(chemDoc);
    //chemDoc.appendChild(reactionList);
    chemDoc.endUpdate();
    chemComposer.setChemObj(chemDoc);
    //console.log(reactionList);
    return reactionList;
}

async function createChemObjEditors(reactionList) {
    showPeriodicTable = false;
    addDefaultField = false;
    for (var i = 0, l = reactionList.length; i < l; ++i) {
        var reaction = reactionList[i];
        if (reaction.reactants) {
            for (var j = 0, m = reaction.reactants.length; j < m; ++j) {
                var reactant = reaction.reactants[j].item;
                for (var k = 0, n = reactant.nodes.length; k < n; ++k) {
                    var atom = reactant.nodes[k];
                    parseAtomSmarts(atom);
                }
                for (var k = 0, n = reactant.connectors.length; k < n; ++k) {
                    var bond = reactant.connectors[k];
                    parseBondSmarts(bond);
                }
            }
        }

        if (reaction.substances) {
            for (var j = 0, m = reaction.substances.length; j < m; ++j) {
                var substance = reaction.substances[j].item;
                for (var k = 0, n = substance.nodes.length; k < n; ++k) {
                    var atom = substance.nodes[k];
                    parseAtomSmarts(atom);
                }
                for (var k = 0, n = substance.connectors.length; k < n; ++k) {
                    var bond = substance.connectors[k];
                    parseBondSmarts(bond);
                }
            }
        }

        if (reaction.products) {
            for (var j = 0, m = reaction.products.length; j < m; ++j) {
                var product = reaction.products[j].item;
                for (var k = 0, n = product.nodes.length; k < n; ++k) {
                    var atom = product.nodes[k];
                    parseAtomSmarts(atom);
                }
                for (var k = 0, n = product.connectors.length; k < n; ++k) {
                    var bond = product.connectors[k];
                    parseBondSmarts(bond);
                }
            }
        }
    }

}

async function drawReactions(data) {
    var reactionList = null
    if (data["kekule"].length > 0) {
        //console.log(data["kekule"]);
        //console.log(data);
        reactionList = drawReactionFromJson(data["kekule"], data["info"]);
    }
return reactionList;
}

async function createAllChemObjEditors(reactionList) {
    if (reactionList.items.length > 0)
        await createChemObjEditors(reactionList.items);

    showPeriodicTable = true;
    addDefaultField = true;
    //load editor panels
    fileOnLoad = false;

     addBlockly();
     showPeriodicTable = true;
     selectedChemObject = null;
     newSelectedChemObject = null;
     writeSliceFile();
}

async function enterChemistryInformation(data) {
    var parser = new DOMParser();
    var xmlDoc = parser.parseFromString(data["ChemEditor"], "text/xml");


    var transformNode = xmlDoc.getElementsByTagName("transform")[0];
    var transformID = transformNode.getAttribute("id");
    var transformName = transformNode.getAttribute("name");
    var transformVersion = transformNode.getAttribute("version");
    var commentTextArea = transformNode.getAttribute("commentTextArea");
    descriptionAndCondition["transform"]["id"] = transformID;
    descriptionAndCondition["transform"]["name"] = transformName;
    descriptionAndCondition["transform"]["version"] = transformVersion;
    document.getElementById("tranformId").value = transformID;
    document.getElementById("tranformName").value = transformName;
    document.getElementById("tranformVersion").value = transformVersion;
    document.getElementById("commentTextArea").value = commentTextArea;

    var historyNodes = xmlDoc.getElementsByTagName("history")[0].getElementsByTagName("modification");
    var historyList = [];
    for (var i = 0; i < historyNodes.length; i++) {
        var modification = historyNodes[i];
        addHistory();
        var mtype = modification.getAttribute("type");
        var mdate = modification.getAttribute("date");
        var mauthor = "";
        if (modification.getElementsByTagName("authors")[0].getElementsByTagName("author").length > 0) {
            var mauthor = modification.getElementsByTagName("authors")[0].getElementsByTagName("author")[0].getAttribute("name");
        }
        var historyDict = {};
        historyDict["type"] = mtype;
        historyDict["date"] = mdate;
        historyDict["authors"] = [mauthor];
        historyList.push(historyDict);
        document.getElementById("historyModifType_" + i).value = mtype;
        document.getElementById("historyDate_" + i).value = mdate;
        document.getElementById("historyAuthor_" + i).value = mauthor;
        //document.getElementById("historyModifType_"+i).value = transformNode.getElementsByTagName("tranformId")[0].textContent;
    }
    descriptionAndCondition["history"] = historyList;

    //var options = ["N.A.", "bad", "poor", "fair", "good", "very good", "excellent"];
    var setupNodes = xmlDoc.getElementsByTagName("setup")[0];
    var yieldd = setupNodes.getElementsByTagName("yield")[0].textContent;
    if (yieldd !== "undefined") {
        yieldd = yieldd.replaceAll('"', '');
        document.getElementById("yieldSelect").selectedIndex = options.indexOf(yieldd);
        descriptionAndCondition["setup"]["yield"] = yieldd;
    }
    var reliability = setupNodes.getElementsByTagName("reliability")[0].textContent;
    if (reliability !== "undefined") {
        reliability = reliability.replaceAll('"', '');
        document.getElementById("reliabilitySelect").selectedIndex = options.indexOf(reliability);
        descriptionAndCondition["setup"]["reliability"] = reliability;
    }
    var reputation = setupNodes.getElementsByTagName("reputation")[0].textContent;
    if (reputation !== "undefined") {
        reputation = reputation.replaceAll('"', '');
        document.getElementById("reputationSelect").selectedIndex = options.indexOf(reputation);
        descriptionAndCondition["setup"]["reputation"] = reputation;
    }
    var homoselectivity = setupNodes.getElementsByTagName("homoselectivity")[0].textContent;
    if (homoselectivity !== "undefined") {
        homoselectivity = homoselectivity.replaceAll('"', '');
        document.getElementById("homoselectivitySelect").selectedIndex = options.indexOf(homoselectivity);
        descriptionAndCondition["setup"]["homoselectivity"] = homoselectivity;
    }
    var heteroselectivity = setupNodes.getElementsByTagName("heteroselectivity")[0].textContent;
    if (heteroselectivity !== "undefined") {
        heteroselectivity = heteroselectivity.replaceAll('"', '');
        document.getElementById("heteroselectivitySelect").selectedIndex = options.indexOf(heteroselectivity);
        descriptionAndCondition["setup"]["heteroselectivity"] = heteroselectivity;
    }
    var orientationalSelectivity = setupNodes.getElementsByTagName("orientationalSelectivity")[0].textContent;
    if (orientationalSelectivity !== "undefined") {
        orientationalSelectivity = orientationalSelectivity.replaceAll('"', '');
        document.getElementById("orientationalSelectivitySelect").selectedIndex = options.indexOf(orientationalSelectivity);
        descriptionAndCondition["setup"]["orientationalSelectivity"] = orientationalSelectivity;
    }
    var conditionFlexibility = setupNodes.getElementsByTagName("conditionFlexibility")[0].textContent;
    if (conditionFlexibility !== "undefined") {
        conditionFlexibility = conditionFlexibility.replaceAll('"', '');
        document.getElementById("conditionSelectivitySelect").selectedIndex = options.indexOf(conditionFlexibility);
        descriptionAndCondition["setup"]["conditionSelectivity"] = conditionFlexibility;
    }
    var thermodynamics = setupNodes.getElementsByTagName("thermodynamics")[0].textContent;
    if (thermodynamics !== "undefined") {
        thermodynamics = thermodynamics.replaceAll('"', '');
        document.getElementById("thermodynamicsSelect").selectedIndex = options.indexOf(thermodynamics);
        descriptionAndCondition["setup"]["thermodynamics"] = thermodynamics;
    }

    var bibliographyNodes = xmlDoc.getElementsByTagName("bibliography")[0].getElementsByTagName("reference");
    var referenceList = [];
    for (var i = 0; i < bibliographyNodes.length; i++) {
        var referenceDict = {};
        var reference = bibliographyNodes[i];
        addBibliographyModal();
        var authors = reference.getElementsByTagName("authors")[0].getElementsByTagName("author");
        var authorList = [];
        for (var j = 0; j < authors.length; j++) {
            addAuthorInBiblio("author_" + i);
            var authorName = authors[j].getAttribute("name");
            document.getElementById("bibliographyAuthor_" + i + "_" + j).value = authorName;
            authorList.push(authorName);
        }
        referenceDict["authors"] = authorList;
        var title = reference.getElementsByTagName("title")[0].textContent;
        if (title !== "undefined") {
            title = title.replaceAll('"', '');
            document.getElementById("bibliographyTitle_" + i).value = title;
            referenceDict["title"] = title
        }
        var journal = reference.getElementsByTagName("journal")[0].textContent;
        if (journal !== "undefined") {
            journal = journal.replaceAll('"', '');
            document.getElementById("bibliographyJournal_" + i).value = journal;
            referenceDict["journal"] = journal
        }
        var year = reference.getElementsByTagName("year")[0].textContent;
        if (year !== "undefined") {
            document.getElementById("bibliographyYear_" + i).value = year;
            referenceDict["year"] = year
        }
        var volume = reference.getElementsByTagName("volume")[0].textContent;
        if (volume !== "undefined") {
            volume = volume.replaceAll('"', '');
            document.getElementById("bibliographyVolume_" + i).value = volume;
            referenceDict["volume"] = volume
        }
        var part = reference.getElementsByTagName("part")[0].textContent;
        if (part !== "undefined") {
            part = part.replaceAll('"', '');
            document.getElementById("bibliographyPart_" + i).value = part;
            referenceDict["part"] = part
        }
        var pages = reference.getElementsByTagName("pages")[0].textContent;
        if (pages !== "undefined") {
            pages = pages.replaceAll('"', '');
            document.getElementById("bibliographyPages_" + i).value = pages;
            referenceDict["pages"] = pages
        }
        var link = reference.getElementsByTagName("link")[0].textContent;
        if (link !== "undefined") {
            link = link.replaceAll('"', '');
            document.getElementById("bibliographyLink_" + i).value = link;
            referenceDict["link"] = link
        }
        var editor = reference.getElementsByTagName("editor")[0].textContent;
        if (editor !== "undefined") {
            editor = editor.replaceAll('"', '');
            document.getElementById("bibliographyEditor_" + i).value = editor;
            referenceDict["editor"] = editor
        }
        var publisher = reference.getElementsByTagName("publisher")[0].textContent;
        if (publisher !== "undefined") {
            publisher = publisher.replaceAll('"', '');
            document.getElementById("bibliographyPublisher_" + i).value = publisher;
            referenceDict["publisher"] = publisher
        }
        var city = reference.getElementsByTagName("city")[0].textContent;
        if (city !== "undefined") {
            city = city.replaceAll('"', '');
            document.getElementById("bibliographyCity_" + i).value = city;
            referenceDict["city"] = city
        }
        addReference("modal_" + i);
        referenceList.push(referenceDict);
    }
    descriptionAndCondition["bibliography"] = referenceList;

    var commentNodes = xmlDoc.getElementsByTagName("comment")[0];
    //console.log(commentNodes);
    if (commentNodes !== undefined) {
        //console.log(commentNodes.textContent,commentNodes.textContent.slice(1).slice(0, -1));
        document.getElementById("commentTextArea").value = commentNodes.textContent.slice(1).slice(0, -1);
        descriptionAndCondition["comments"] = [commentNodes.textContent.slice(1).slice(0, -1)];
    }

    var conditions = xmlDoc.getElementsByTagName("conditions")[0].getElementsByTagName("condition");
    var conditionsList = [];
    for (var i = 0; i < conditions.length; i++) {
        var conditionDict = {};
        var condition = conditions[i];
        addCondition();
        var num = i + 1;
        //console.log(i,num,condition);
        var cname = condition.getAttribute("name");
        if (cname) {
            cname = cname.replaceAll('"', '');
            document.getElementById("condition_" + i + "_" + num).value = cname;
            conditionDict["name"] = cname;
        }
        var step = condition.getElementsByTagName("step")[0].textContent;
        if (step.length > 0) {
            step = step.replaceAll('"', '');
            document.getElementById("stepContainer_" + i).value = step
            conditionDict["step"] = step;
        }

        var reagentsList = [];
        var reagents = condition.getElementsByTagName("reagents")[0].getElementsByTagName("reagent");
        for (var j = 0; j < reagents.length; j++) {
            var reagent = reagents[j].textContent.slice(1).slice(0, -1);
            if (j > 0) {
                document.getElementById("reagentAdd_" + i).click();
            }
            document.getElementById("reagent_" + i + "_" + j).value = reagent;
            reagentsList.push(reagent);
        }
        conditionDict["reagent"] = reagentsList;

        var solventsList = [];
        var solvents = condition.getElementsByTagName("solvents")[0].getElementsByTagName("solvent");
        for (var j = 0; j < solvents.length; j++) {
            var solvent = solvents[j].textContent.slice(1).slice(0, -1);
            if (j > 0) {
                document.getElementById("solventAdd_" + i).click();
            }
            document.getElementById("solvent_" + i + "_" + j).value = solvent;
            solventsList.push(solvent);
        }
        conditionDict["solvent"] = solventsList;

        var temperaturesList = [];
        var temperatures = condition.getElementsByTagName("temperatures")[0].getElementsByTagName("temperature");
        for (var j = 0; j < temperatures.length; j++) {
            var temperature = temperatures[j].textContent.slice(1).slice(0, -1);
            if (j > 0) {
                document.getElementById("temperatureAdd_" + i).click();
            }
            document.getElementById("temperature_" + i + "_" + j).value = temperature;
            temperaturesList.push(temperature);
        }
        conditionDict["temperature"] = temperaturesList;

        var timesList = [];
        var times = condition.getElementsByTagName("times")[0].getElementsByTagName("time");
        for (var j = 0; j < times.length; j++) {
            var time = times[j].textContent.slice(1).slice(0, -1);
            if (j > 0) {
                document.getElementById("temperatureAdd_" + i).click();
            }
            document.getElementById("time_" + i + "_" + j).value = time;
            timesList.push(time);
        }
        conditionDict["time"] = timesList;

        var supInfosList = [];
        var supInfos = condition.getElementsByTagName("informations")[0].getElementsByTagName("information");
        for (var j = 0; j < supInfos.length; j++) {
            var supInfo = supInfos[j].textContent.slice(1).slice(0, -1);
            if (j > 0) {
                document.getElementById("supInfoAdd_" + i).click();
            }
            document.getElementById("time_" + i + "_" + j).value = supInfo;
            supInfosList.push(supInfo);
        }
        conditionDict["supInfo"] = supInfosList;
        conditionsList.push(conditionDict);
    }
    descriptionAndCondition["conditions"] = conditionsList;
    //console.log("r",descriptionAndCondition);
}

async function addBlockly() {
    //generate logic associated to each molecule
    var mols = chemComposer.exportObjs(Kekule.Molecule);
    //load molecule specific logic
    Blockly.mainWorkspace.clear();
    for (var i = 0; i < mols.length; i++) {
        var mol = mols[i];
        var xmlToLoad = blocklyXML[mol.id];
        if (xmlToLoad) {
            loadBlocks(xmlToLoad);
        }
        var code = Blockly.SLICE.workspaceToCode(workspace);
        if (code.length > 0) {
            mol.logic = code;
        }
        Blockly.mainWorkspace.clear();
    }
    //load common logic to all molecule of a reaction
    for (var i = 0, l = reactions.length; i < l; ++i) {
        var xmlToLoad = blocklyXML["reaction_" + i];
        if (xmlToLoad) {
            loadBlocks(xmlToLoad);
        }
        var code = Blockly.SLICE.workspaceToCode(workspace);
        if (code.length > 0) {
            reactions[i].logic = code;
        }
        Blockly.mainWorkspace.clear();

    }
    if (mols.length > 0) {
        document.getElementById("blocklyDiv").style.display = "block";
        document.getElementById("ChemLogic-info").style.display = "none";
    }
}

window.parseSliceFile = async function (content) {
    try {
        var data = JSON.parse(content);
        enterChemistryInformation(data);
        var reactionList = await drawReactions(data);
        blocklyXML = data["blockly"];

        selectFieldsArray = [];
        depictReactions = true;

        await createAllChemObjEditors(reactionList);
      } catch(e) {
          console.error(e);
      } finally {
        displayReactionSMARTS(true, true);
      }

    //logic processes in displayReactionSMARTS;

    //load logic
    //updateLogicAssistantPage();

}

function readTextFile(file, callback) {
    var rawFile = new XMLHttpRequest();
    rawFile.overrideMimeType("application/json");
    rawFile.open("GET", file, true);
    rawFile.onreadystatechange = function () {
        if (rawFile.readyState === 4 && rawFile.status == "200") {
            callback(rawFile.responseText);
        }
    }
    rawFile.send(null);
}

window.openTab = function (evt, tabName) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
    currentTabName = tabName;
}

window.loadDatabase = function () {
    
    authorsDB = data.authors;
    journalsDB = data.journals;
    reagentsDB = data.reagents;
    solventsDB = data.solvents;
}

window.removeFromArray = function (array, value) {
    const index = array.indexOf(value);
    if (index > -1) {
        array.slice(index, 1);
    }
}


window.hideShow = function (divID, buttonID) {
    var elt = document.getElementById(divID);
    var button = document.getElementById(buttonID);

    button.innerHTML = "";
    if (elt.style.display === "none") {
        elt.style.display = "inline-block";
        button.append(createChevronUpSvg(null, "16", "16"));
    } else {
        elt.style.display = "none";
        button.append(createChevronDownSvg(null, "16", "16"));
    }
}

window.getNum = function (ref) {
    var num = ref.childElementCount;
    if (num > 0) {
        var children = ref.childNodes;
        for (var i = children.length - 1; i > -1; i--) {
            if (children[i].type === "button") {
                continue;
            }
            var cId = children[i].id;
            if (cId != undefined) {
                if (cId.includes("_")) {
                    var lastElt = cId.split("_").pop();
                    if (!isNaN(lastElt)) {
                        var val = parseInt(lastElt) + 1;
                        return val;
                    }
                }
            }
        }
    } else {
        return 0;
    }
    return 0;
}

window.isNumeric = function (n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

window.getPosAuthorRef = function (id) {
    var arr = id.split('_');
    var n1 = arr.pop();
    var n2 = arr.pop();
    return n2;
}

window.createDataList = function (datalist, array) {
    for (var i = 0; i < array.length; i++) {
        var option = document.createElement("option");
        option.value = array[i];
        datalist.appendChild(option);
    }
}

//get key of a dict by a value
window.getKeyByValue = function (object, value) {
    return Object.keys(object).find(key => object[key] === value);
}


window.addOptions = function (selectList, array) {
    //Create and append the options
    for (var i = 0; i < array.length; i++) {
        var option = document.createElement("option");
        option.value = array[i];
        option.text = array[i];
        selectList.appendChild(option);
    }
    selectList.selectedIndex = -1;
}

window.addDefaultOptionText = function (selectList) {
    var option = document.createElement("option");
    option.value = "";
    option.text = "Choose One";
    option.selected = "selected";
    option.disabled = "disabled";
    option.hidden = "hidden";
    selectList.appendChild(option);
}

window.addDeleteButton = function (method) {
    var elt = document.createElement("span");
    elt.classList.add("topright");
    elt.setAttribute("class", "topright");
    elt.setAttribute("onclick", "this." + method + ".remove()");
    elt.innerHTML = "&times";
    return elt;
}

window.createToggleSwitch = function (id, toggleType) {
    var toggleSwitch = document.createElement("label");
    toggleSwitch.setAttribute("class", "switch");
    var type = document.createElement("input");
    type.setAttribute("type", "checkbox");
    type.setAttribute("id", id);
    type.setAttribute("onclick", 'showAssociatedValues("' + id + '")');
    var span = document.createElement("span");
    //console.log(id, type);
    span.setAttribute("class", "slider round " + toggleType);
    toggleSwitch.appendChild(type);
    toggleSwitch.appendChild(span);
    return toggleSwitch;
}

window.createToggle = function (id) {
    var div = document.createElement("div");
    div.setAttribute("class", "pretty p-default p-curve p-toggle");
    div.setAttribute("id", id + "-toggleDiv");
    var toggle = document.createElement("input");
    toggle.type = "checkbox";
    toggle.id = id;
    div.appendChild(toggle);

    var divOn = document.createElement("div");
    divOn.setAttribute("class", "state p-danger p-on");
    divOn.setAttribute("style", "font-size:small");
    div.appendChild(divOn);
    var labelOn = document.createElement("label");
    labelOn.innerHTML = "not";
    divOn.appendChild(labelOn);

    var divOff = document.createElement("div");
    divOff.setAttribute("class", "state p-success p-off");
    divOff.setAttribute("style", "font-size:small");
    div.appendChild(divOff);
    var labelOff = document.createElement("label");
    labelOff.innerHTML = "is";
    divOff.appendChild(labelOff);

    return div;
}

window.bondOrderToSymbol = function (order) {
    if (order == 1) {
        return "-";
    } else if (order == 2) {
        return "=";
    } else if (order == 3) {
        return "#";
    } else {
        return "";
    }
}