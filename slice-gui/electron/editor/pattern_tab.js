var isBlocklyVisible = false;

window.makePatternTab = function () {
    var patternDiv = document.getElementById("ChemPattern");
    patternDiv.innerHTML = "";
    var text = "";
    for (var i = 0; i < smirksList.length; i++) {
        var smirks = smirksList[i];
        var j = i + 1;
        text += "ReactionSMIRKS " + j + ":</br>" + smirks + "</br></br>";
        createReactionButton(i + 1);
    }
    if (text.length > 0) {
        text += "</br></br>";
    }
    for (var i = 0; i < mols.length; i++) {
        var mol = mols[i];
        if (!mol.isClassified) {
            var j = i + 1;
            text += "Unclassified Molecule " + j + ":</br>" + mol.smarts + "</br></br>";
        }
    }
    patternDiv.innerHTML = text;

    if (!isBlocklyVisible && mols.length > 0) {
        document.getElementById("blocklyDiv").style.display = "block";
        document.getElementById("ChemLogic-info").style.display = "none";
        isBlocklyVisible = true;
    } else if (isBlocklyVisible && mols.length == 0) {
        document.getElementById("blocklyDiv").style.display = "none";
        document.getElementById("ChemLogic-info").style.display = "block";
        isBlocklyVisible = false;
        isLogicInitialize = false;
    }
}