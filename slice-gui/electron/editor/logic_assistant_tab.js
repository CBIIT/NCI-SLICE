var reactionIndex = -1;
var molInBlocklyIndex = -1;

window.loadLogicAssistantPage = function (evt) {
    // Get the container element
    var btnContainer = document.getElementById("ChemLogic");

    // Get all buttons with class="btn" inside the container
    var btns = btnContainer.getElementsByClassName("reactionButton");

    var prevReactionIndex = -1;
    // Loop through the buttons and add the active class to the current/clicked button
    for (var i = 0, l = btns.length; i < l; i++) {
        var btn = btns[i];
        var className = btn.className;
        //console.log(btn.innerHTML);
        if (className.includes("active") && btn.innerHTML.includes("Reaction")) {
            prevReactionIndex = i;
        }
        btn.className = className.replace(" active", "");
    }
    var btn = evt.currentTarget;
    btn.className += " active";

    updateLogicAssistantPage();

    if (btn.innerHTML.includes("Reaction")) {
        reactionIndex = parseInt(btn.id.split("_")[1]) - 1;
    } else {
        reactionIndex = -1;
    }

    if (prevReactionIndex == -1 & reactionIndex > -1) {
        document.getElementById("blocklyDiv").style.display = "block";
    }

    if (reactionIndex == -1) {
        document.getElementById("blocklyDiv").style.display = "none";
    } else {
        if (molInBlocklyIndex) {
            loadLogic(molInBlocklyIndex, 0);
        }
    }
};

window.loadMoleculeSpecificLogicAssistantPage = function (button) {
    var btnContainer = document.getElementById("reactionImage");

    // Get all buttons with class="btn" inside the container
    var btns = btnContainer.getElementsByClassName("mol_button");

    var prevMolIndex = -1;
    var currentMolIndex = -1;
    // Loop through the buttons and add the active class to the current/clicked button
    for (var i = 0, l = btns.length; i < l; i++) {
        var btn = btns[i];
        var className = btn.className;
        if (className.includes("active") && !btn.innerHTML.includes("Unclassified")) {
            prevMolIndex = parseInt(btn.id.split("_")[1]);
        }
        btn.className = className.replace(" active", "");
    }
    button.className += " active";
    currentMolIndex = parseInt(button.id.split("_")[1]);
    //console.log(prevMolIndex,currentMolIndex, button.id);
    loadLogic(prevMolIndex, currentMolIndex);
    //TODO load reaction detect active so save previous workspace and set reaction workspace
    /*blocklyXML[selectedChemObject.getParentFragment().id] = xml;
    Blockly.mainWorkspace.clear();
    //console.log("SAVED",xml);
    var xmlToLoad = blocklyXML[newSelectedChemObject.getParentFragment().id];
    if (xmlToLoad) {
        loadBlocks(xmlToLoad);
    }*/
};

window.loadLogic = function(prevMolIndex, currentMolIndex) {
    //console.log(prevMolIndex, currentMolIndex,reactionIndex);
    if (currentMolIndex > -1) {
        molInBlocklyIndex = currentMolIndex;
        //save blockly 
        var xml = saveBlocks();
        //console.log(xml);
        //save logic
        if (chemObjectInUseByBlockly) {
            var thisClass = chemObjectInUseByBlockly.getClassName();
            if (thisClass === 'Kekule.Molecule') {
                if (!xml.includes('<xml xmlns="https://developers.google.com/blockly/xml"></xml>'))
                blocklyXML[chemObjectInUseByBlockly.id] = xml;
            }
            else if (thisClass === 'Kekule.Reaction')  {
                if (!xml.includes('<xml xmlns="https://developers.google.com/blockly/xml"></xml>'))
                    blocklyXML["reaction_" + chemObjectInUseByBlockly.id] = xml;
            }
        }
        //OLD way 
        /*if (prevMolIndex == 99999) {
            //console.log("reaction_"+reactionIndex);
            //save only if at least one block is in the workspace
            if (!xml.includes('<xml xmlns="https://developers.google.com/blockly/xml"></xml>'))
                blocklyXML["reaction_" + reactionIndex] = xml;
        } else if (prevMolIndex == -1) {
            //save only if at least one block is in the workspace
            if (!xml.includes('<xml xmlns="https://developers.google.com/blockly/xml"></xml>'))
                blocklyXML["reaction_" + reactionIndex] = xml;
        } else {
            //console.log(mols[prevMolIndex].id);
            blocklyXML[mols[prevMolIndex].id] = xml;
        }*/

        chemObjectInUseByBlockly = null;
        Blockly.mainWorkspace.clear();
        var xmlToLoad = null;
        //console.log(currentMolIndex,reactionIndex);
        //console.log(blocklyXML);
        //it's a reaction
        if (currentMolIndex == 99999) {
            //console.log("reaction_"+reactionIndex);
            //console.log(reactions[reactionIndex]);
            chemObjectInUseByBlockly = reactions[reactionIndex];
            xmlToLoad = blocklyXML["reaction_" + reactionIndex];
            //console.log(xmlToLoad);
        }
        //it's a molecule
        else {
            //console.log("mol_"+mols[currentMolIndex].id);
            //console.log(mols[currentMolIndex]);
            chemObjectInUseByBlockly = mols[currentMolIndex];
            xmlToLoad = blocklyXML[chemObjectInUseByBlockly.id];
        }
    }
    //console.log(mols);
    //console.log(chemObjectInUseByBlockly.id);
    //console.log(blocklyXML);
    //load
    if (xmlToLoad) {
        //console.log(xmlToLoad);
        loadBlocks(xmlToLoad);
        //Clean up the workspace by ordering all the blocks in a column.
        Blockly.mainWorkspace.cleanUp();
    }
}

window.updateLogicAssistantPage = async function () {
    //delete unnecessary button in logic assistant
    removeUnnecessaryReactionButtons();
    var maxHeight = 0;

    var div = document.getElementById("reactionImage");
    var activeMolIndex = -1;
    //clear previous content
    while (div.firstChild) {
        if (div.firstChild.className.includes("active")) {
            activeMolIndex = parseInt(div.firstChild.id.split("_")[1]);
        }
        div.removeChild(div.firstChild);
    }

    if (activeMolIndex == -1) {
        activeMolIndex = 0;
    }
    div.appendChild(createLogicTitle(80, 85));

    var div2 = document.createElement("div");
    div2.setAttribute("style", "margin-left:85px; border: 2px double black");
    div.appendChild(div2);

    //get active button
    var num;
    var reactionButton;
    var btnContainer = document.getElementById("ChemLogic");
    var btns = btnContainer.getElementsByClassName("reactionButton");
    for (var i = 0, l = btns.length; i < l; i++) {
        if (btns[i].className.includes(" active")) {
            num = i;
            reactionButton = btns[i];
            break;
        }
    }
    if (!reactionButton) {
        reactionButton = btns[0];
        num = 0;
    }
    /* var reactionImageButton = document.createElement("button");
      reactionImageButton.setAttribute("class", "reaction-image-outer-button");
      reactionImageButton.innerHTML = "Reaction " + num;
      div.appendChild(reactionImageButton);*/
    //console.log(reactionButton);
    var ghost_molecule = false;
    if (reactionButton) {
        if (reactionButton.innerHTML.includes("Reaction")) {
            if (reactions.length > 0) {
                var reaction = reactions[num];
                reactionIndex = num;
                if (reaction.reactants) {
                    for (var j = 0, m = reaction.reactants.length; j < m; ++j) {
                        ghost_molecule = false;
                        var reactant = reaction.reactants[j].item;
                        var indexMol = mols.indexOf(reactant);
                        var teste = mols[indexMol].logic;
                        //console.log("reactant logic:",teste );//STEFI
                        if( mols[indexMol].logic && (mols[indexMol].logic).includes("define it as ghost molecule"))
                            ghost_molecule = true;
                       // console.log("reactant :",blocklyXML );//STEFI
                        var molButton = createMolButton(
                            reactant,
                            indexMol,
                            indexMol == activeMolIndex ? true : false,
                            j + 1,
                            "Reactant",
                            ghost_molecule
                        );
                        div2.appendChild(molButton);
                        if (j == 0) {
                            molButton.before(
                                createAllMolButton(
                                    activeMolIndex == 99999 ? true : false,
                                    molButton.getAttribute("height"),
                                    molButton.getAttribute("width")
                                )
                            );
                        }
                        if (molButton.lastChild.getAttribute("height") > maxHeight) {
                            maxHeight = molButton.lastChild.getAttribute("height");
                        }
                        /*var reactionElementImageButton = document.createElement("button");
                              reactionElementImageButton.setAttribute("class", "reaction-image-inner-button");
                              reactionElementImageButton.innerHTML = "Reactant " + j;
                              reactionElementImageButton.appendChild(moleculeToSVG(reactant));
                              div.appendChild(reactionElementImageButton);*/
                    }
                }
                ghost_molecule = false;
                if (reaction.substances) {
                    for (var j = 0, m = reaction.substances.length; j < m; ++j) {
                        var substance = reaction.substances[j].item;
                        var indexMol = mols.indexOf(substance);
                        var molButton = createMolButton(
                            substance,
                            indexMol,
                            indexMol == activeMolIndex ? true : false,
                            j + 1,
                            "Reagent",
                            ghost_molecule
                        );
                        div2.appendChild(molButton);
                        if (molButton.lastChild.getAttribute("height") > maxHeight) {
                            maxHeight = molButton.lastChild.getAttribute("height");
                        }
                    }
                }
                ghost_molecule = false;
                if (reaction.products) {
                    for (var j = 0, m = reaction.products.length; j < m; ++j) {
                        ghost_molecule = false;
                        var product = reaction.products[j].item;
                        var indexMol = mols.indexOf(product);
                        //var teste = mols[indexMol].logic;
                        //console.log("products logic:",teste );//STEFI
                        if(mols[indexMol].logic && (mols[indexMol].logic).includes("define it as ghost molecule"))
                            ghost_molecule = true;
                        var molButton = createMolButton(
                            product,
                            indexMol,
                            indexMol == activeMolIndex ? true : false,
                            j + 1,
                            "Product",
                            ghost_molecule
                        );
                        div2.appendChild(molButton);
                        if (molButton.lastChild.getAttribute("height") > maxHeight) {
                            maxHeight = molButton.lastChild.getAttribute("height");
                        }
                    }
                }
                //Init blockly
                if (!blocklyXML["reaction_" + num]) {
                    //console.log("TEST2",reactionIndex);
                    document.getElementById("blocklyDiv").style.display = "block";
                    document.getElementById("ChemLogic-info").style.display = "none";
                    loadLogic(0, 0);
                    reaction.isLogicLoaded = true;
                    fixBlocklyBug();
                } else {
                    if (!reaction.isLogicLoaded) {
                        //console.log("TEST",reactionIndex);
                        //TODO load file 30 and 32
                        document.getElementById("blocklyDiv").style.display = "block";
                        document.getElementById("ChemLogic-info").style.display = "none";
                        loadLogic(-1, 0);
                        reaction.isLogicLoaded = true;
                        fixBlocklyBug();
                    }
                }
            }
        }

        //create reactions button for unclassified reactions
        var unclassifiedButton = null;
        var unclassifiedCounter = 1;
        for (var i = 0; i < mols.length; i++) {
            var mol = mols[i];
            if (!mol.isClassified) {
                if (!unclassifiedButton) {
                    //console.log(reactions.length, reactions);
                    var num2 = reactions.length + 1;
                    unclassifiedButton = createReactionButton(num2);
                    unclassifiedButton.innerHTML = "Unclassified Molecules";
                    unclassifiedButton.id = "reactionButton_9999";
                    //console.log(reactions.length, reactions);
                    //console.log(num2, unclassifiedButton);
                }
                if (!reactionButton.innerHTML.includes("Reaction")) {
                    var indexMol = mols.indexOf(mol);
                    //console.log(indexMol,activeMolIndex);
                    var molButton = createMolButton(
                        mol,
                        i,
                        indexMol == activeMolIndex ? true : false,
                        unclassifiedCounter,
                        "Unclassified Molecule"
                    );
                    if (molButton.lastChild.getAttribute("height") > maxHeight) {
                        maxHeight = molButton.lastChild.getAttribute("height");
                    }
                    div2.appendChild(molButton);
                    if (unclassifiedCounter == 1) {
                        molButton.before(
                            createAllMolButton(
                                activeMolIndex == 99999 ? true : false,
                                molButton.getAttribute("height"),
                                molButton.getAttribute("width")
                            )
                        );
                        document.getElementById("blocklyDiv").style.display = "none";
                        document.getElementById("ChemLogic-info").style.display = "block";
                    }
                    unclassifiedCounter++;
                }
            }
        }
        adjustHeight(maxHeight);
        //createBorder();
        //console.log(unclassifiedButton);
        //not necessary and needs debug
        /*if (activeMolIndex == 99999) {
            addBackgroundColorToButton();
        }
        else {
            removeBackgroundColorToButton();
        }*/
    }
};

function adjustHeight(height) {
    var btnContainer = document.getElementById("reactionImage");

    // Get all buttons with class="btn" inside the container
    var btns = btnContainer.getElementsByClassName("mol_button");

    // Loop through the buttons and add the active class to the current/clicked button
    for (var i = 0, l = btns.length; i < l; i++) {
        //console.log(btns[i].lastChild.getAttribute("height"));
        btns[i].lastChild.setAttribute("height", height);
        //btns[i].setAttribute("height", height);
    }
}

function addBackgroundColorToButton() {
    var btnContainer = document.getElementById("reactionImage");

    // Get all buttons with class="btn" inside the container
    var btns = btnContainer.getElementsByClassName("mol_button");

    // Loop through the buttons and add the active class to the current/clicked button
    for (var i = 1, l = btns.length; i < l; i++) {
        btns[i].setAttribute("style", btns[i].getAttribute("style") + "background-color: #efeeee4a");
    }
}

function removeBackgroundColorToButton() {
    var btnContainer = document.getElementById("reactionImage");

    // Get all buttons with class="btn" inside the container
    var btns = btnContainer.getElementsByClassName("mol_button");

    // Loop through the buttons and add the active class to the current/clicked button
    for (var i = 1, l = btns.length; i < l; i++) {
        btns[i].setAttribute("style", btns[i].getAttribute("style").split("background")[0]);
    }
}

/*function createBorder() {
    var btnContainer = document.getElementById("reactionImage");

    // Get all buttons with class="btn" inside the container
    var btns = btnContainer.getElementsByClassName("mol_button");

    // Loop through the buttons and add the active class to the current/clicked button
    for (var i = 0, l = btns.length; i < l; i++) {
        btns[i].setAttribute("style", btns[i].getAttribute("style")+"border-top: 2px double black;border-bottom: 2px double black;");
        if (i == 0) {
            btns[i].setAttribute("style", btns[i].getAttribute("style")+"border-left: 2px double black;");
        }
        else if (i == l-1) {
            btns[i].setAttribute("style", btns[i].getAttribute("style")+"border-right: 2px double black;");
        }
    }
}

function removeBorder() {
    var btnContainer = document.getElementById("reactionImage");

    // Get all buttons with class="btn" inside the container
    var btns = btnContainer.getElementsByClassName("mol_button");

    // Loop through the buttons and add the active class to the current/clicked button
    for (var i = 0, l = btns.length; i < l; i++) {
        btns[i].setAttribute("style", btns[i].getAttribute("style").split("border")[0]);
    }
}*/

function createLogicTitle(height, width) {
    var label = document.createElement("label");
    label.setAttribute("style", "float: left;");

    // variable for the namespace
    const svgns = "http://www.w3.org/2000/svg";
    var svg = document.createElementNS(svgns, "svg");
    svg.setAttribute("xlink", "http://www.w3.org/1999/xlink");
    svg.setAttribute("width", width);
    svg.setAttribute("height", height);
    svg.setAttribute(
        "style",
        "vertical-align: middle;text-align: center;display: block;"
    );

    // make a simple rectangle
    /*let newRect = document.createElementNS(svgns, "rect");
      //newRect.setAttribute("x", "150");
      //newRect.setAttribute("y", "150");
      newRect.setAttribute("width", width);
      newRect.setAttribute("height", height);
      svg.appendChild(newRect);*/

    let text = document.createElementNS("http://www.w3.org/2000/svg", "text");
    text.setAttribute("x", "10%");
    text.setAttribute("y", "50%");
    text.setAttribute("fill", "black");
    //text.setAttribute('dominant-baseline', 'middle');
    //text.setAttribute('text-anchor', 'middle');
    text.textContent = "Logic for:";
    svg.appendChild(text);
    //console.log(svgns);

    /*var text = document.createElement("text");
      text.setAttribute("style", "text-align: center;vertical-align: middle");
      text.setAttribute("height", height);
      text.setAttribute("width", width);
      text.innerHTML = "All <br/> molecules";*/
    //console.log(svg);
    label.setAttribute("height", height);
    label.setAttribute("width", width);
    //label.appendChild(text);
    label.appendChild(svg);
    return label;
}

function createAllMolButton(isActive, height, width) {
    //console.log(isActive, height, width);
    var label = document.createElement("label");
    label.setAttribute("id", "molButtonID_99999");
    label.setAttribute("class", "mol_button");
    if (isActive) {
        label.className += " active";
    }
    label.setAttribute("style", "float: left;");
    var reactionElementImageButton = document.createElement("button");
    reactionElementImageButton.style = "display:none;";
    reactionElementImageButton.setAttribute(
        "onclick",
        "loadMoleculeSpecificLogicAssistantPage(this.parentNode)"
    );
    //reactionElementImageButton.setAttribute("class", "reaction-image-inner-button");

    // variable for the namespace
    const svgns = "http://www.w3.org/2000/svg";
    var svg = document.createElementNS(svgns, "svg");
    svg.setAttribute("xlink", "http://www.w3.org/1999/xlink");
    svg.setAttribute("width", "100");
    svg.setAttribute("height", "80");
    svg.setAttribute(
        "style",
        "vertical-align: middle;text-align: center;display: block;"
    );

    // make a simple rectangle
    /*let newRect = document.createElementNS(svgns, "rect");
      //newRect.setAttribute("x", "150");
      //newRect.setAttribute("y", "150");
      newRect.setAttribute("width", width);
      newRect.setAttribute("height", height);
      svg.appendChild(newRect);*/

    let text = document.createElementNS("http://www.w3.org/2000/svg", "text");
    text.setAttribute("x", "40%");
    text.setAttribute("y", "30%");
    text.setAttribute("fill", "black");
    //text.setAttribute('dominant-baseline', 'middle');
    //text.setAttribute('text-anchor', 'middle');
    text.textContent = "All";
    svg.appendChild(text);
    let text2 = document.createElementNS("http://www.w3.org/2000/svg", "text");
    text2.setAttribute("x", "15%");
    text2.setAttribute("y", "50%");
    text2.setAttribute("fill", "black");
    //text.setAttribute('dominant-baseline', 'middle');
    //text.setAttribute('text-anchor', 'middle');
    text2.textContent = "Molecules";
    svg.appendChild(text2);
    svg.setAttribute(
        "style",
        "vertical-align: middle;text-align: center;display: block;margin-left: 0;margin-bottom: -20"
    );
    /*var text = document.createElement("text");
      text.setAttribute("style", "text-align: center;vertical-align: middle");
      text.setAttribute("height", height);
      text.setAttribute("width", width);
      text.innerHTML = "All <br/> molecules";*/
    //console.log(svg);
    label.setAttribute("height", height);
    label.setAttribute("width", width);
    label.appendChild(reactionElementImageButton);
    //label.appendChild(text);
    label.appendChild(svg);
    return label;
}

function createMolButton(mol, indexMol, isActive, molID, textID, ghost_molecule) {
    //console.log(isActive, indexMol, textID);
    var label = document.createElement("label");
    label.setAttribute("id", "molButtonID_" + indexMol);
    label.setAttribute("class", "mol_button");
    label.setAttribute("width","50px");
    if (isActive) {
        label.className += " active";
    }
    //console.log(ghost_molecule);
    if (ghost_molecule){
        label.setAttribute("style", "display: inline-block; text-align: center;background-color:rgb(104,104,104);");//STEFI
    }
    else{
        label.setAttribute("style", "display: inline-block; text-align: center;");//STEFI
    }
    
    var reactionElementImageButton = document.createElement("button");
    reactionElementImageButton.style = "display:none;";
    reactionElementImageButton.setAttribute(
        "onclick",
        "loadMoleculeSpecificLogicAssistantPage(this.parentNode)"
    );
    //reactionElementImageButton.setAttribute("class", "reaction-image-inner-button");
    var text = document.createElement("text");
    text.setAttribute("style", "text-align: center;");
    if (ghost_molecule){
        text.innerHTML = textID + " " + molID + "(Ghost)";
    }
    else{
        text.innerHTML = textID + " " + molID;
    }
    
    //console.log("GEN IMG " +mol.id + " " +mol.isChanged);
    var svg = mol.svgImg;
    var height = svg.getAttribute("height");
    var width = svg.getAttribute("width");
    //svg.setAttribute('viewBox', findSvgBoundaries(svg));
    //svg.setAttribute("transform", "scale(0.6)");
    //console.log(svg);
    label.setAttribute("height", height * 0.5);
    label.setAttribute("width", width * 0.5);
    label.appendChild(reactionElementImageButton);
    label.appendChild(text);
    label.appendChild(svg);
    return label;
}

function findSvgBoundaries(svg) {
    //console.log(svg.children, svg.children[0].getBBox());
    const {
        xMin,
        xMax,
        yMin,
        yMax
    } = [...svg.children].reduce((acc, el) => {
        const {
            x,
            y,
            width,
            height
        } = el.getBBox();
        console.log(acc, el.getBBox());
        if (!acc.xMin || x < acc.xMin) acc.xMin = x;
        if (!acc.xMax || x + width > acc.xMax) acc.xMax = x + width;
        if (!acc.yMin || y < acc.yMin) acc.yMin = y;
        if (!acc.yMax || y + height > acc.yMax) acc.yMax = y + height;
        return acc;
    }, {});

    const viewbox = `${xMin} ${yMin} ${xMax - xMin} ${yMax - yMin}`;
    return viewbox;
}

window.createReactionButton = function (num) {
    var reactionButton;
    //console.log(num);
    if (num == 1 && !document.getElementById("reactionButton_" + num)) {
        reactionButton = document.getElementById("reactionButton_" + 9999);
    } else {
        reactionButton = document.getElementById("reactionButton_" + num);
    }
    if (!reactionButton) {
        reactionButton = document.createElement("button");
        //reactionButton.setAttribute("class",'reactionButton');
        reactionButton.className = "reactionButton";
        reactionButton.setAttribute("id", "reactionButton_" + num);
        reactionButton.setAttribute("onclick", "loadLogicAssistantPage(event)");
        reactionButton.innerHTML = "Reaction " + num;
        let prevNum = num - 1;
        //console.log(prevNum, document.getElementById("reactionButton_" + prevNum));
        document.getElementById("reactionButton_" + prevNum).after(reactionButton);
    } else {
        reactionButton.setAttribute("id", "reactionButton_" + num);
        reactionButton.innerHTML = "Reaction " + num;
    }
    return reactionButton;
};

window.removeUnnecessaryReactionButtons = function () {
    var btnContainer = document.getElementById("ChemLogic");
    var btns = btnContainer.getElementsByClassName("reactionButton");
    var isThereAnyUnclassifiedMol = false;
    for (var i = 0; i < mols.length; i++) {
        var mol = mols[i];
        if (!mol.isClassified) {
            isThereAnyUnclassifiedMol = true;
            break;
        }
    }
    if (mols.length == 0) {
        isThereAnyUnclassifiedMol = true;
    }
    //don't delete unclassified button if there's unclassified molecules
    var i = btns.length - 1;
    var m = reactions.length == 0 ? 0 : reactions.length - 1;
    for (i; i > m; i--) {
        var btn = btns[i];
        btn.remove();
    }
};

window.removeReactionButton = function (num) {
    if (num > 1) {
        document.getElementById("reactionButton_" + num).remove();
    }
};

window.createProcedureBlock = async function () {
    createProcedureBlock2();
}

function readTextFile2(file) {
    //TODO manage case where file is empty
    var rawFile = new XMLHttpRequest();
    rawFile.open("GET", file, false);
    rawFile.onreadystatechange = function () {
        if (rawFile.readyState === 4) {
            if (rawFile.status === 200 || rawFile.status == 0) {
                var allText = rawFile.responseText;
            }
        }
    };
    try {
        rawFile.send(null);
    } catch (error) {
        //console.log(error);
    }
    return rawFile.responseText;
}