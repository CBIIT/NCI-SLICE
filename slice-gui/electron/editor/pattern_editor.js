var previousValueDict = {}

//atom section
window.createAtomSection = function (chemObjId, chemObj, form) {
    //TODO if atom is variableAtom setsymbol to C and uncomment nextline
    //chemEditor.modifyObjectsRenderOptions(this, {'atomRadius': 5}, false, true);
    //chemEditor.modifyObjectsRenderOptions(chemObj, {'atomRadius': 0}, false, true);

    /*
     *
     * Common Properties
     *
     */

    var cAtomTitle = document.createElement("div");
    cAtomTitle.setAttribute("id", "atomCommonContainerTitle-" + chemObjId);
    cAtomTitle.setAttribute("class", "catomTitle");
    cAtomTitle.innerHTML = "Atom-Common-Properties";
    form.appendChild(cAtomTitle);

    var cAtomDiv = document.createElement("div");
    cAtomDiv.setAttribute("id", "atomCommonContainer_" + chemObjId);
    cAtomDiv.setAttribute("style", "overflow:auto;");
    cAtomDiv.setAttribute("class", "atomCommonContainer");
    form.appendChild(cAtomDiv); // put it into the DOM

    var chevronButton = document.createElement("button");
    chevronButton.setAttribute("type", "button");
    chevronButton.setAttribute("id", "chevronButtonAtomCommonContainerProperty_" + chemObjId);
    chevronButton.setAttribute("class", "showButton");
    chevronButton.setAttribute("onclick", "hideShow(" + "'atomCommonContainer_" + chemObjId + "','chevronButtonAtomCommonContainerProperty_" + chemObjId + "')");
    cAtomTitle.appendChild(chevronButton);
    var chevronIcon = createChevronUpSvg(null, "16", "16");
    chevronButton.appendChild(chevronIcon);

    var cAtomProp = document.createElement("div");
    cAtomProp.setAttribute("id", chemObjId + "_atomCommonAAM_0");
    cAtomProp.setAttribute("style", "overflow:auto;");
    //cAtomProp.setAttribute("class", "atomCommonContainer");
    cAtomProp.innerHTML = "Atom Mapping";
    cAtomDiv.appendChild(cAtomProp); // put it into the DOM

    var aamInput = document.createElement("input");
    aamInput.type = "number";
    aamInput.setAttribute("id", chemObjId + "_cAAM");
    aamInput.setAttribute("name", "atomMapping");
    aamInput.setAttribute("min", "0");
    aamInput.setAttribute("max", "999");
    aamInput.setAttribute("onchange", "storePropertyInArray(this,true)");
    domDict[chemObjId + "_cAAM"] = aamInput;
    cAtomProp.appendChild(aamInput);

    //Uncomment to reactivate the possibility to add common properties
    //It has been deactivated by default to avoid possible confusions
    /*
        var labelAnd = document.createElement("label");
        labelAnd.setAttribute("id", "atomCommonPropertyLabelAdd-" + chemObjId);
        labelAnd.setAttribute("for", "commonAtomPropertyBlockAddButton_" + chemObjId);
        labelAnd.innerHTML = "";
        cAtomDiv.appendChild(labelAnd);

        var commonAtomPropertyAddButton = document.createElement("button");
        commonAtomPropertyAddButton.setAttribute("type", "button");
        commonAtomPropertyAddButton.setAttribute("id", "commonAtomPropertyBlockAddButton_" + chemObjId);
        commonAtomPropertyAddButton.setAttribute("class", "addButton");
        commonAtomPropertyAddButton.setAttribute(
            "onclick",
            "addCommonAtomPropertyBlock('" + chemObjId + "');"
        );
        var icon2 = createRoundPlusSvg("addCommonAtomProperty", "16", "16");
        commonAtomPropertyAddButton.appendChild(icon2);
        cAtomDiv.appendChild(commonAtomPropertyAddButton);
        */


    /*
     *
     * Specific Properties
     *
     */

    var sAtomTitle = document.createElement("div");
    sAtomTitle.setAttribute("id", "atomSpecificContainerTitle-" + chemObjId);
    sAtomTitle.setAttribute("class", "satomTitle");
    sAtomTitle.innerHTML = "Atom-Specific-Properties";
    form.appendChild(sAtomTitle);

    var sAtomDiv = document.createElement("div");
    sAtomDiv.setAttribute("id", "atomSpecificContainer_" + chemObjId);
    sAtomDiv.setAttribute("style", "overflow:auto;");
    sAtomDiv.setAttribute("class", "atomSpecificContainer");
    //sAtomDiv.innerHTML = "Atom-Specific-Properties </br>";
    form.appendChild(sAtomDiv); // put it into the DOM

    var chevronButton = document.createElement("button");
    chevronButton.setAttribute("type", "button");
    chevronButton.setAttribute("id", "chevronButtonAtomSpecificContainerProperty_" + chemObjId);
    chevronButton.setAttribute("class", "showButton");
    chevronButton.setAttribute("onclick", "hideShow(" + "'atomSpecificContainer_" + chemObjId + "','chevronButtonAtomSpecificContainerProperty_" + chemObjId + "')");
    sAtomTitle.appendChild(chevronButton);
    var chevronIcon = createChevronUpSvg(null, "16", "16");
    chevronButton.appendChild(chevronIcon);

    var specificAtomPropertyAddButton = document.createElement("button");
    specificAtomPropertyAddButton.setAttribute("type", "button");
    specificAtomPropertyAddButton.setAttribute("id", "specificAtomPropertyBlockAddButton_" + chemObjId);
    specificAtomPropertyAddButton.setAttribute("class", "addButton");
    specificAtomPropertyAddButton.setAttribute(
        "onclick",
        "addSpecificAtomPropertyBlock('" + chemObjId + "');"
    );
    var icon = createRoundPlusSvg("addSpecificAtomProperty", "16", "16");
    specificAtomPropertyAddButton.appendChild(icon);
    sAtomDiv.appendChild(specificAtomPropertyAddButton);

    /*
     *
     * Chemical Environment
     *
     */

    var envAtomTitle = document.createElement("div");
    envAtomTitle.setAttribute("id", "atomSpecificEnvContainerTitle-" + chemObjId);
    envAtomTitle.setAttribute("class", "satomTitle");
    envAtomTitle.innerHTML = "Chemical Environments";
    form.appendChild(envAtomTitle);

    var envAtomDiv = document.createElement("div");
    envAtomDiv.setAttribute("id", "atomSpecificEnvContainer_" + chemObjId);
    envAtomDiv.setAttribute("style", "overflow:auto;");
    envAtomDiv.setAttribute("class", "atomSpecificContainer");
    form.appendChild(envAtomDiv); // put it into the DOM

    var chevronEnvButton = document.createElement("button");
    chevronEnvButton.setAttribute("type", "button");
    chevronEnvButton.setAttribute("id", "chevronButtonAtomSpecificEnvContainerProperty_" + chemObjId);
    chevronEnvButton.setAttribute("class", "showButton");
    chevronEnvButton.setAttribute("onclick", "hideShow(" + "'atomSpecificEnvContainer_" + chemObjId + "','chevronButtonAtomSpecificEnvContainerProperty_" + chemObjId + "')");
    envAtomTitle.appendChild(chevronEnvButton);
    var chevronIcon = createChevronUpSvg(null, "16", "16");
    chevronEnvButton.appendChild(chevronIcon);

    var specificAtomEnvPropertyAddButton = document.createElement("button");
    specificAtomEnvPropertyAddButton.setAttribute("type", "button");
    specificAtomEnvPropertyAddButton.setAttribute("id", "specificAtomEnvPropertyBlockAddButton_" + chemObjId);
    specificAtomEnvPropertyAddButton.setAttribute("class", "addButton");
    specificAtomEnvPropertyAddButton.setAttribute(
        "onclick",
        "addSpecificAtomEnvPropertyBlock('" + chemObjId + "');"
    );
    var icon = createRoundPlusSvg("addSpecificAtomEnvProperty", "16", "16");
    specificAtomEnvPropertyAddButton.appendChild(icon);
    envAtomDiv.appendChild(specificAtomEnvPropertyAddButton);


    //add default field
    if (addDefaultField) {
        showPeriodicTable = false;
        addSpecificAtomPropertyBlock(chemObjId);
        var div = addOneSpecificAtomProperty(document.getElementById(chemObjId + "_atomSpecificPropertyAdd-0"), 0);
        var children = div.childNodes;
        var select = children[1];
        //select value
        select.selectedIndex = atomProperties.indexOf("Chemical Element");
        showSelectParameters(select.id);
        children[4].innerHTML = chemObj.isotopeId;
        showPeriodicTable = true;
    }
}

window.addCommonAtomPropertyBlock = function (chemObjId) {
    var parent = document.getElementById("atomCommonContainer_" + chemObjId);
    var num = getNum(parent);

    var div = document.createElement("div");
    div.setAttribute("id", chemObjId + "_atomCommonPropertySelectDiv_" + num);
    div.setAttribute("class", "propertyContainer");
    div.setAttribute("style", "overflow-y:hidden;white-space:nowrap;");
    var labelElt = document.getElementById("atomCommonPropertyLabelAdd-" + chemObjId);
    labelElt.innerHTML = "AND";
    labelElt.setAttribute("style", "float:left");
    labelElt.before(div);

    var and = document.createElement('div');
    and.setAttribute("style", "font-size:smaller;text-align:center")
    and.appendChild(document.createTextNode("AND"));
    if (div.previousSibling != null) {
        div.before(and);
    }

    var deleteButton = document.createElement("button");
    deleteButton.setAttribute("type", "button");
    deleteButton.setAttribute("id", chemObjId + "_atomCommonPropertyDelete_" + num);
    deleteButton.setAttribute("class", "deleteButton");
    deleteButton.setAttribute("style", "display:inline-block;vertical-align:middle;");
    deleteButton.setAttribute("onclick", "deleteProperty(this)");
    var icon = createRoundDeleteSvg("deleteAtomCommonPropertyBlock_" + num, "16", "16");
    deleteButton.appendChild(icon);
    div.appendChild(deleteButton);

    var cAtomSelect = document.createElement("select");
    var selectID = "atomCommonPropertySelect_" + num;
    cAtomSelect.setAttribute("id", selectID);
    cAtomSelect.setAttribute("class", "cAtomSelect");
    cAtomSelect.setAttribute("style", "width:80px");
    addOptions(cAtomSelect, atomProperties);
    cAtomSelect.setAttribute(
        "onchange",
        "showSelectParameters('" + selectID + "');"
    );
    div.appendChild(cAtomSelect);

    return div;
}

window.addSpecificAtomEnvPropertyBlock = function (chemObjId) {
    var parent = document.getElementById("atomSpecificEnvContainer_" + chemObjId);
    var num = getNum(parent);

    var atomSpecificEnvPropertyDiv = document.createElement("div");
    atomSpecificEnvPropertyDiv.setAttribute("id", chemObjId + "_atomSpecificEnvPropertyBlockDiv_" + num);
    document.getElementById("specificAtomEnvPropertyBlockAddButton_" + chemObjId).before(atomSpecificEnvPropertyDiv);

    //if (num > 0) {
    var deleteButton = document.createElement("button");
    deleteButton.setAttribute("type", "button");
    deleteButton.setAttribute("id", chemObjId + "_atomSpecificEnvPropertyBlockDelete_" + num);
    deleteButton.setAttribute("class", "deleteButton");
    deleteButton.setAttribute("style", "display:inline-block;vertical-align:middle;");
    deleteButton.setAttribute("onclick", "deleteSpecificPropertyBlock(this.parentNode)");
    var icon = createRoundDeleteSvg("deleteAtomSpecificEnvPropertyBlock_" + num, "16", "16");
    deleteButton.appendChild(icon);
    atomSpecificEnvPropertyDiv.appendChild(deleteButton);
    //}

    var fieldset = document.createElement("fieldset");
    fieldset.setAttribute("id", chemObjId + "_fieldsetAtomSpecificEnvProperty_" + num);
    fieldset.setAttribute("style", "display:inline-block;vertical-align:middle;width:80%;");
    atomSpecificEnvPropertyDiv.appendChild(fieldset);

    var legend = document.createElement("legend");
    legend.setAttribute("id", chemObjId + "_atomSpecificEnvPropertyBlockLegend-" + num);
    var cpt = parseInt(num, 10) + 1
    legend.innerHTML = "Chemical Environment Property " + cpt;
    fieldset.appendChild(legend);

    var chevronButton = document.createElement("button");
    chevronButton.setAttribute("type", "button");
    chevronButton.setAttribute("id", chemObjId + "_chevronButtonAtomSpecificEnvBlockProperty_" + num);
    chevronButton.setAttribute("class", "showButton");
    chevronButton.setAttribute("onclick", "hideShow('" + chemObjId + "_atomSpecificEnvPropertyContainer-" + num + "','" + chemObjId + "_chevronButtonAtomSpecificEnvBlockProperty_" + num + "')");
    legend.appendChild(chevronButton);
    var chevronIcon = createChevronUpSvg(null, "16", "16");
    chevronButton.appendChild(chevronIcon);

    var atomSpecificEnvPropertyContainer = document.createElement("div");
    atomSpecificEnvPropertyContainer.setAttribute("id", chemObjId + "_atomSpecificEnvPropertyContainer-" + num);
    atomSpecificEnvPropertyContainer.setAttribute("style", "overflow-y:hidden;white-space:nowrap;");
    fieldset.appendChild(atomSpecificEnvPropertyContainer);

    var labelAnd = document.createElement("label");
    //console.log(chemObjId+"_atomSpecificPropertyLabelAdd-" + num);
    labelAnd.setAttribute("id", chemObjId + "_atomSpecificEnvPropertyLabelAdd-" + num);
    labelAnd.setAttribute("for", "atomSpecificPropertyAdd-" + num);
    labelAnd.innerHTML = "";
    domDict[chemObjId + "_atomSpecificEnvPropertyLabelAdd-" + num] = labelAnd;
    atomSpecificEnvPropertyContainer.appendChild(labelAnd);

    var addButton = document.createElement("button");
    addButton.setAttribute("type", "button");
    addButton.setAttribute("style", "float: left");
    addButton.setAttribute("id", chemObjId + "_atomSpecificEnvPropertyAdd-" + num);
    addButton.setAttribute("class", "addButton");
    addButton.setAttribute(
        "onclick",
        "addOneSpecificAtomEnvProperty(this, this.id.split('-').pop());"
    );
    var icon = createRoundPlusSvg("addAtomSpecificEnvProperty-" + num, "16", "16");
    addButton.appendChild(icon);
    atomSpecificEnvPropertyContainer.appendChild(addButton);

    var orAtomSpecificEnvPropertyDiv = document.createElement("div");
    orAtomSpecificEnvPropertyDiv.setAttribute("id", chemObjId + "_orAtomSpecificEnvPropertyBlockDiv-" + num);
    orAtomSpecificEnvPropertyDiv.innerHTML = "OR";
    atomSpecificEnvPropertyDiv.appendChild(orAtomSpecificEnvPropertyDiv);

    addOneSpecificAtomEnvProperty(addButton, num);

    return addButton;
}

window.addOneSpecificAtomEnvProperty = function (ref, num) {
    var num2 = getNum(ref.parentNode);
    //console.log(ref.parentNode.id, " ", ref.parentNode.childElementCount, " ", num2);
    var div = document.createElement("div");
    div.setAttribute("id", chemObjId + "_atomSpecificEnvPropertySelectDiv_" + num + "_" + num2);
    div.setAttribute("class", "propertyContainer");
    div.setAttribute("style", "overflow-y:hidden;white-space:nowrap;");
    //var labelElt = document.getElementById(chemObjId+"_atomSpecificPropertyLabelAdd-" + num);
    var labelElt = domDict[chemObjId + "_atomSpecificEnvPropertyLabelAdd-" + num];
    labelElt.setAttribute("style", "float:left");
    labelElt.innerHTML = "AND";
    labelElt.before(div);

    var and = document.createElement('div');
    and.setAttribute("style", "font-size:smaller;text-align:center")
    and.appendChild(document.createTextNode("AND"));
    if (div.previousSibling != null) {
        div.before(and);
    }
    var deleteButton = document.createElement("button");
    deleteButton.setAttribute("type", "button");
    deleteButton.setAttribute("id", chemObjId + "_atomSpecificEnvPropertyDelete_" + num + "_" + num2);
    deleteButton.setAttribute("class", "deleteButton");
    deleteButton.setAttribute("style", "display:inline-block;vertical-align:middle;");
    deleteButton.setAttribute("onclick", "deleteProperty(this)");
    var icon = createRoundDeleteSvg("deleteAtomSpecificEnvPropertyBlock_" + num, "16", "16");
    deleteButton.appendChild(icon);
    div.appendChild(deleteButton);
    if (num2 == 0) {
        deleteButton.style.display = "none";
    }

    var envLabel = document.createElement("label");
    var envLabelID = chemObjId + "_atomSpecificEnvProperty_" + num + "_" + num2;
    envLabel.setAttribute("id", envLabelID);
    //envLabel.innerHTML = '<font size="-1">Chemical Environment</font>';

    //domDict[selectID] = sAtomSelect;;
    div.appendChild(envLabel);
    var switch4 = createToggle(envLabelID + "_switchAlwaysNeverChemicalEnvironment");
    domDict[envLabelID + "_switchAlwaysNeverChemicalEnvironment"] = switch4;
    if (envLabelID.includes("Specific")) {
        switch4.setAttribute("onchange", "storePropertyInArray(this.firstChild, false);");
    } else {
        switch4.setAttribute("onchange", "storePropertyInArray(this.firstChild, true);");
    }

    var chemEnvSelect = document.createElement("select");
    var chemEnvSelectID = envLabelID + "_chemEnvSelect";
    chemEnvSelect.setAttribute("id", chemEnvSelectID);
    chemEnvSelect.setAttribute("class", "chemEnvSelect");
    chemEnvSelect.setAttribute("style", "width:80px;display:inline-block;white-space:nowrap;overflow-x:auto;overflow-y:hidden");
    addOptions(chemEnvSelect, Object.keys(functionalGroupProperties));
    chemEnvSelect.setAttribute(
        "onchange",
        "setChemicalEnv(this);"
    );

    var chemEnvProperty = document.createElement("input");
    chemEnvProperty.setAttribute("type", "text");
    chemEnvProperty.setAttribute("id", envLabelID + "_chemEnvProperty");
    chemEnvProperty.setAttribute("size", "10");
    if (envLabelID.includes("Specific")) {
        chemEnvProperty.setAttribute("onchange", "storePropertyInArray(this, false);");
    } else {
        chemEnvProperty.setAttribute("onchange", "storePropertyInArray(this, true);");
    }

    var edit = document.createElement("button");
    edit.setAttribute("type", "button");
    //TODO open new instance
    edit.setAttribute("onclick", "");
    edit.setAttribute("id", envLabelID + "_symbol");
    edit.setAttribute("value", "Edit");
    edit.innerHTML = "Edit";

    envLabel.after(switch4);
    switch4.after(chemEnvSelect);
    chemEnvSelect.after(chemEnvProperty);
    chemEnvProperty.after(edit);

    return div;
}

window.addSpecificAtomPropertyBlock = function (chemObjId) {
    var parent = document.getElementById("atomSpecificContainer_" + chemObjId);
    var num = getNum(parent);

    var atomSpecificPropertyDiv = document.createElement("div");
    atomSpecificPropertyDiv.setAttribute("id", chemObjId + "_atomSpecificPropertyBlockDiv_" + num);
    document.getElementById("specificAtomPropertyBlockAddButton_" + chemObjId).before(atomSpecificPropertyDiv);

    if (num > 0) {
        var deleteButton = document.createElement("button");
        deleteButton.setAttribute("type", "button");
        deleteButton.setAttribute("id", chemObjId + "_atomSpecificPropertyBlockDelete_" + num);
        deleteButton.setAttribute("class", "deleteButton");
        deleteButton.setAttribute("style", "display:inline-block;vertical-align:middle;");
        deleteButton.setAttribute("onclick", "deleteSpecificPropertyBlock(this.parentNode)");
        var icon = createRoundDeleteSvg("deleteAtomSpecificPropertyBlock_" + num, "16", "16");
        deleteButton.appendChild(icon);
        atomSpecificPropertyDiv.appendChild(deleteButton);
    }

    var fieldset = document.createElement("fieldset");
    fieldset.setAttribute("id", chemObjId + "_fieldsetAtomSpecificProperty_" + num);
    fieldset.setAttribute("style", "display:inline-block;vertical-align:middle;width:80%;");
    atomSpecificPropertyDiv.appendChild(fieldset);

    var legend = document.createElement("legend");
    legend.setAttribute("id", chemObjId + "_atomSpecificPropertyBlockLegend-" + num);
    var cpt = parseInt(num, 10) + 1
    legend.innerHTML = "Atom-Specific Property " + cpt;
    fieldset.appendChild(legend);

    var chevronButton = document.createElement("button");
    chevronButton.setAttribute("type", "button");
    chevronButton.setAttribute("id", chemObjId + "_chevronButtonAtomSpecificBlockProperty_" + num);
    chevronButton.setAttribute("class", "showButton");
    chevronButton.setAttribute("onclick", "hideShow('" + chemObjId + "_atomSpecificPropertyContainer-" + num + "','" + chemObjId + "_chevronButtonAtomSpecificBlockProperty_" + num + "')");
    legend.appendChild(chevronButton);
    var chevronIcon = createChevronUpSvg(null, "16", "16");
    chevronButton.appendChild(chevronIcon);

    var atomSpecificPropertyContainer = document.createElement("div");
    atomSpecificPropertyContainer.setAttribute("id", chemObjId + "_atomSpecificPropertyContainer-" + num);
    atomSpecificPropertyContainer.setAttribute("style", "overflow-y:hidden;white-space:nowrap;");
    fieldset.appendChild(atomSpecificPropertyContainer);

    var labelAnd = document.createElement("label");
    //console.log(chemObjId+"_atomSpecificPropertyLabelAdd-" + num);
    labelAnd.setAttribute("id", chemObjId + "_atomSpecificPropertyLabelAdd-" + num);
    labelAnd.setAttribute("for", "atomSpecificPropertyAdd-" + num);
    labelAnd.innerHTML = "";
    domDict[chemObjId + "_atomSpecificPropertyLabelAdd-" + num] = labelAnd;
    atomSpecificPropertyContainer.appendChild(labelAnd);

    var addButton = document.createElement("button");
    addButton.setAttribute("type", "button");
    addButton.setAttribute("style", "float: left");
    addButton.setAttribute("id", chemObjId + "_atomSpecificPropertyAdd-" + num);
    addButton.setAttribute("class", "addButton");
    addButton.setAttribute(
        "onclick",
        "addOneSpecificAtomProperty(this, this.id.split('-').pop());"
    );
    var icon = createRoundPlusSvg("addAtomSpecificProperty-" + num, "16", "16");
    addButton.appendChild(icon);
    atomSpecificPropertyContainer.appendChild(addButton);

    var orAtomSpecificPropertyDiv = document.createElement("div");
    orAtomSpecificPropertyDiv.setAttribute("id", chemObjId + "_orAtomSpecificPropertyBlockDiv-" + num);
    orAtomSpecificPropertyDiv.innerHTML = "OR";
    atomSpecificPropertyDiv.appendChild(orAtomSpecificPropertyDiv);
    return addButton;
}

window.addOneSpecificAtomProperty = function (ref, num) {
    var num2 = getNum(ref.parentNode);
    //console.log(ref.parentNode.id, " ", ref.parentNode.childElementCount, " ", num2);
    var div = document.createElement("div");
    div.setAttribute("id", chemObjId + "_atomSpecificPropertySelectDiv_" + num + "_" + num2);
    div.setAttribute("class", "propertyContainer");
    div.setAttribute("style", "overflow-y:hidden;white-space:nowrap;");
    //var labelElt = document.getElementById(chemObjId+"_atomSpecificPropertyLabelAdd-" + num);
    var labelElt = domDict[chemObjId + "_atomSpecificPropertyLabelAdd-" + num];
    labelElt.setAttribute("style", "float:left");
    labelElt.innerHTML = "AND";
    labelElt.before(div);

    var and = document.createElement('div');
    and.setAttribute("style", "font-size:smaller;text-align:center")
    and.appendChild(document.createTextNode("AND"));
    if (div.previousSibling != null) {
        div.before(and);
    }
    //console.log("num2",num2);
    var deleteButton = document.createElement("button");
    deleteButton.setAttribute("type", "button");
    deleteButton.setAttribute("id", chemObjId + "_atomSpecificPropertyDelete_" + num + "_" + num2);
    deleteButton.setAttribute("class", "deleteButton");
    deleteButton.setAttribute("style", "display:inline-block;vertical-align:middle;");
    deleteButton.setAttribute("onclick", "deleteProperty(this)");
    var icon = createRoundDeleteSvg("deleteAtomSpecificPropertyBlock_" + num, "16", "16");
    deleteButton.appendChild(icon);
    div.appendChild(deleteButton);
    if (num2 == 0) {
        deleteButton.style.display = "none";
    }

    var sAtomSelect = document.createElement("select");
    var selectID = chemObjId + "_atomSpecificPropertySelect_" + num + "_" + num2;
    sAtomSelect.setAttribute("id", selectID);
    sAtomSelect.setAttribute("class", "sAtomSelect");
    sAtomSelect.setAttribute("style", "width:80px;display:inline-block;white-space:nowrap;overflow-x:auto;overflow-y:hidden");
    addOptions(sAtomSelect, atomProperties);
    sAtomSelect.setAttribute(
        "onchange",
        "showSelectParameters('" + selectID + "');"
    );
    domDict[selectID] = sAtomSelect;;
    div.appendChild(sAtomSelect);
    return div;
}

//bond section
window.createBondSection = function (chemObjId, chemObj, form) {
    //chemEditor.modifyObjectsRenderOptions(this, {'atomRadius': 5}, false, true);
    //chemEditor.modifyObjectsRenderOptions(chemObj, {'atomRadius': 0}, false, true);
    /*
     *
     * Bond Properties
     *
     */

    var bondTitle = document.createElement("div");
    bondTitle.setAttribute("id", "bondContainerTitle-" + chemObjId);
    bondTitle.setAttribute("class", "catomTitle");
    bondTitle.innerHTML = "Bond Properties";
    form.appendChild(bondTitle);

    var bondDiv = document.createElement("div");
    bondDiv.setAttribute("id", "bondContainer_" + chemObjId);
    bondDiv.setAttribute("style", "overflow:auto;");
    bondDiv.setAttribute("class", "bondContainer");
    domDict[chemObjId] = bondDiv;
    form.appendChild(bondDiv); // put it into the DOM

    var chevronButton = document.createElement("button");
    chevronButton.setAttribute("type", "button");
    chevronButton.setAttribute("id", "chevronButtonBondContainerProperty_" + chemObjId);
    chevronButton.setAttribute("class", "showButton");
    chevronButton.setAttribute("onclick", "hideShow(" + "'bondContainer_" + chemObjId + "','chevronButtonBondContainerProperty_" + chemObjId + "')");
    bondTitle.appendChild(chevronButton);
    var chevronIcon = createChevronUpSvg(null, "16", "16");
    chevronButton.appendChild(chevronIcon);

    var specificBondPropertyAddButton = document.createElement("button");
    specificBondPropertyAddButton.setAttribute("type", "button");
    specificBondPropertyAddButton.setAttribute("id", "specificBondPropertyBlockAddButton_" + chemObjId);
    specificBondPropertyAddButton.setAttribute("class", "addButton");
    specificBondPropertyAddButton.setAttribute(
        "onclick",
        "addSpecificBondPropertyBlock('" + chemObjId + "');"
    );
    var icon = createRoundPlusSvg("addSpecificBondProperty", "16", "16");
    domDict["specificBondPropertyBlockAddButton_" + chemObjId] = specificBondPropertyAddButton;
    specificBondPropertyAddButton.appendChild(icon);
    bondDiv.appendChild(specificBondPropertyAddButton);

    //add default field
    if (addDefaultField) {
        addSpecificBondPropertyBlock(chemObjId);
        var div = addOneSpecificBondProperty(document.getElementById(chemObjId + "_bondSpecificPropertyAdd-0"), 0);
        var children = div.childNodes;
        var select = children[1];
        //select value
        select.selectedIndex = bondProperties.indexOf("Single Bond");
        showSelectParameters(select.id);
    }
}

window.addSpecificBondPropertyBlock = function (chemObjId) {
    var parent = document.getElementById("bondContainer_" + chemObjId);
    //var parent = domDict["bondContainer_" + chemObjId];
    var num = getNum(parent);

    var bondSpecificPropertyDiv = document.createElement("div");
    bondSpecificPropertyDiv.setAttribute("id", chemObjId + "_bondSpecificPropertyBlockDiv_" + num);
    document.getElementById("specificBondPropertyBlockAddButton_" + chemObjId).before(bondSpecificPropertyDiv);
    //domDict["specificBondPropertyBlockAddButton_" + chemObjId + chemObjId].before(bondSpecificPropertyDiv);

    if (num > 0) {
        var deleteButton = document.createElement("button");
        deleteButton.setAttribute("type", "button");
        deleteButton.setAttribute("id", chemObjId + "_bondSpecificPropertyBlockDelete_" + num);
        deleteButton.setAttribute("class", "deleteButton");
        deleteButton.setAttribute("style", "display:inline-block;vertical-align:middle;");
        deleteButton.setAttribute("onclick", "deleteSpecificPropertyBlock(this.parentNode)");
        var icon = createRoundDeleteSvg("deleteAtomSpecificPropertyBlock_" + num, "16", "16");
        deleteButton.appendChild(icon);
        bondSpecificPropertyDiv.appendChild(deleteButton);
    }

    var fieldset = document.createElement("fieldset");
    fieldset.setAttribute("id", chemObjId + "_fieldsetBondSpecificProperty_" + num);
    fieldset.setAttribute("style", "display:inline-block;vertical-align:middle;width:80%;");
    bondSpecificPropertyDiv.appendChild(fieldset);

    var legend = document.createElement("legend");
    legend.setAttribute("id", chemObjId + "_BondSpecificPropertyBlockLegend-" + num);
    var cpt = parseInt(num, 10) + 1
    legend.innerHTML = "Bond Property " + cpt;
    fieldset.appendChild(legend);

    var chevronButton = document.createElement("button");
    chevronButton.setAttribute("type", "button");
    chevronButton.setAttribute("id", chemObjId + "_chevronButtonBondSpecificBlockProperty_" + num);
    chevronButton.setAttribute("class", "showButton");
    chevronButton.setAttribute("onclick", "hideShow('" + chemObjId + "bondSpecificPropertyContainer-" + num + "','" + chemObjId + "chevronButtonBondSpecificBlockProperty_" + num + "')");
    legend.appendChild(chevronButton);
    var chevronIcon = createChevronUpSvg(null, "16", "16");
    chevronButton.appendChild(chevronIcon);

    var bondSpecificPropertyContainer = document.createElement("div");
    bondSpecificPropertyContainer.setAttribute("id", chemObjId + "_bondSpecificPropertyContainer-" + num);
    bondSpecificPropertyContainer.setAttribute("style", "overflow-y:hidden;white-space:nowrap;");
    fieldset.appendChild(bondSpecificPropertyContainer);

    var labelAnd = document.createElement("label");
    labelAnd.setAttribute("id", chemObjId + "_bondSpecificPropertyLabelAdd-" + num);
    labelAnd.setAttribute("for", "bondSpecificPropertyAdd-" + num);
    labelAnd.innerHTML = "";
    domDict[chemObjId + "_bondSpecificPropertyLabelAdd-" + num] = labelAnd;
    bondSpecificPropertyContainer.appendChild(labelAnd);

    var addButton = document.createElement("button");
    addButton.setAttribute("type", "button");
    addButton.setAttribute("id", chemObjId + "_bondSpecificPropertyAdd-" + num);
    addButton.setAttribute("style", "float: left");
    addButton.setAttribute("class", "addButton");
    addButton.setAttribute(
        "onclick",
        "addOneSpecificBondProperty(this, this.id.split('-').pop());"
    );
    var icon = createRoundPlusSvg("addBondSpecificProperty-" + num, "16", "16");
    addButton.appendChild(icon);
    bondSpecificPropertyContainer.appendChild(addButton);

    var orBondSpecificPropertyDiv = document.createElement("div");
    orBondSpecificPropertyDiv.setAttribute("id", chemObjId + "_orBondSpecificPropertyBlockDiv-" + num);
    orBondSpecificPropertyDiv.innerHTML = "OR";
    bondSpecificPropertyDiv.appendChild(orBondSpecificPropertyDiv);
    return addButton;
}

window.addOneSpecificBondProperty = function (ref, num) {
    var num2 = getNum(ref.parentNode);
    //console.log(ref.parentNode.id, " ", ref.parentNode.childElementCount, " ", num2);

    var div = document.createElement("div");
    div.setAttribute("id", chemObjId + "_bondSpecificPropertySelectDiv_" + num + "_" + num2);
    div.setAttribute("class", "propertyContainer");
    div.setAttribute("style", "overflow-y:hidden;white-space:nowrap;");
    //console.log(chemObjId+"_bondSpecificPropertyLabelAdd-" + num);
    //var labelElt = document.getElementById(chemObjId+"_bondSpecificPropertyLabelAdd-" + num);
    var labelElt = domDict[chemObjId + "_bondSpecificPropertyLabelAdd-" + num];
    labelElt.setAttribute("style", "float:left");
    labelElt.innerHTML = "AND";
    labelElt.before(div);

    var and = document.createElement('span');
    and.setAttribute("style", "fontSize:small;text-align:center")
    and.appendChild(document.createTextNode("AND"));
    if (div.previousSibling != null) {
        div.before(and);
    }

    var deleteButton = document.createElement("button");
    deleteButton.setAttribute("type", "button");
    deleteButton.setAttribute("id", chemObjId + "_bondSpecificPropertyDelete_" + num + "_" + num2);
    deleteButton.setAttribute("class", "deleteButton");
    deleteButton.setAttribute("style", "display:inline-block;vertical-align:middle;");
    deleteButton.setAttribute("onclick", "deleteProperty(this)");
    var icon = createRoundDeleteSvg("deleteBondSpecificPropertyBlock_" + num, "16", "16");
    deleteButton.appendChild(icon);
    div.appendChild(deleteButton);

    var bondSelect = document.createElement("select");
    var selectID = chemObjId + "_bondSpecificPropertySelect_" + num + "_" + num2;
    bondSelect.setAttribute("id", selectID);
    bondSelect.setAttribute("class", "bondSelect");
    bondSelect.setAttribute("style", "width:80px;display:inline-block;white-space:nowrap;overflow-x:auto;overflow-y:hidden");
    addOptions(bondSelect, bondProperties);
    bondSelect.setAttribute(
        "onchange",
        "showSelectParameters('" + selectID + "');"
    );
    div.appendChild(bondSelect);
    return div;
}

//delete function
window.deleteSpecificPropertyBlock = function (parent) {
    var id = parent.id;
    var prefix = "";
    var num = id.split("_").pop();

    if (id.includes("atom")) {
        if (id.includes("atomSpecificEnvProperty")) {
            prefix = id.split("atomSpecificEnvPropertyBlockDiv")[0];
            deleteSpecificPropertyBlock2(prefix, prefix + "atomSpecificEnvPropertyContainer-" + num);
        } else {
            prefix = id.split("atomSpecificPropertyBlockDiv")[0];
            deleteSpecificPropertyBlock2(prefix, prefix + "atomSpecificPropertyContainer-" + num);
        }
    } else {
        prefix = id.split("bondSpecificPropertyBlockDiv")[0];
        deleteSpecificPropertyBlock2(prefix, prefix + "bondSpecificPropertyContainer-" + num);
    }

    parent.remove();
}

window.deleteSpecificPropertyBlock2 = function (prefix, id) {
    var propertyDiv = document.getElementById(id);
    var children = propertyDiv.childNodes;
    for (var i = 0; i < children.length; i++) {
        var child = children[i];
        if (child.id) {
            if (child.id.split(prefix)[1].includes("_")) {
                updatePropertyArray(child.childNodes[1], null, null);
            }
        }
    }
}

window.deleteProperty = function (propertyDiv) {
    var parent = propertyDiv.parentNode;
    var previous = parent.previousSibling;
    if (previous != null) {
        previous.remove();
    }
    parent.remove();
    updatePropertyArray(propertyDiv, null, null);
}



//other utils functions
window.configureAromaticity = function (button) {
    var symbol = document.getElementById(button.id.split("_aromaticityProp")[0] + "_symbol").value;
    if (symbol === "A" || symbol === "a" || symbol === "*") {
        return;
    }
    var value = button.getAttribute("value");
    if (value === "aliphatic") {
        value = "aromatic";
    } else if (value === "aromatic") {
        value = "either";
    } else {
        value = "aliphatic";
    }
    button.innerHTML = value;
    button.setAttribute("value", value);
}

window.setChemicalEnv = function (select) {
    var val = functionalGroupProperties[select.value];
    var textID = select.id.replace("_chemEnvSelect", "") + "_chemEnvProperty";
    var text = document.getElementById(textID);
    text.value = val;
    if (select.id.includes("Specific")) {
        storePropertyInArray(select, false);
    } else {
        storePropertyInArray(select, true);
    }
    //console.log("VALUE ",val,select.value,document.getElementById(textID).value)
}

//update array after the removal of an element
window.updatePropertyArray = function (selectedField, oldType, expr) {
    var parent = selectedField.parentNode;
    if (selectedField.id.includes("switchAlwaysNever")) {
        parent = parent.parentNode;
    }
    var children = parent.childNodes;
    var select = children[1];
    var type = select.value;

    if (type == null && selectedField.id.includes("EnvProperty")) {
        type = "Chemical Environment";
    }
    var positionsArray = selectedField.parentNode.id
        .replace("_switchAlwaysNeverChemicalElement-toggleDiv", "")
        .replace("_switchAlwaysNeverChemicalEnvironment-toggleDiv", "")
        .replace("_switchAlwaysNever-toggleDiv", "")
        .replace("_aromaticityProp", "")
        .replace("_equal", "")
        .split("_");
    var id = selectedField.id;

    if (id.includes("Specific")) {
        var order = parseInt(positionsArray[positionsArray.length - 2], 10);
        //console.log(order, type, oldType,expr);
        //console.log(specificProperties[order],specificProperties);
        if (specificProperties[order]) {
            if (oldType) {
                /*if (oldType.includes("Bond") && !type.includes("Electrons") && !type.includes("Ring")) {
                    oldType = "Bond";
                }*/
                if (oldType && oldType.length > 0) {
                    if (oldType === "Chemical Element") {
                        deleteValueByIndexInSpecificProperties(order, oldType, buildElementExpression(parent));
                    } else if (oldType === "Chemical Environment") {
                        deleteValueByIndexInSpecificProperties(order, oldType, buildEnvironmentExpression(parent));
                    } else {
                        deleteValueByIndexInSpecificProperties(order, oldType, buildPropertyComplexExpression(parent, type));
                    }
                }
            } else {
                if (type && type.length > 0) {
                    if (type === "Chemical Element") {
                        deleteValueByIndexInSpecificProperties(order, type, buildElementExpression(parent));
                    } else if (type === "Chemical Environment") {
                        deleteValueByIndexInSpecificProperties(order, type, buildEnvironmentExpression(parent));
                    } else {
                        deleteValueByIndexInSpecificProperties(order, type, buildPropertyComplexExpression(parent, type));
                    }
                }
            }
            if (Object.keys(specificProperties[order]) == 0 && !oldType) {
                delete specificProperties[order];
            }
        }
    } else {
        if (oldType === "aam") {
            delete commonProperties[type];
        }
        //old Common property store nowonly the atom mapping
        /*
        delete commonProperties[type];
        if (type === "aam") {
            delete commonProperties[type];
        }
        else if (type === "Chemical Element") {
            delete commonProperties[type][buildElementExpression(parent)];
        }
        else if (type === "Chemical Environment") {
            delete commonProperties[type][buildEnvironmentExpression(parent)];
        }
        else {
            delete commonProperties[type][buildPropertyComplexExpression(parent)];
        }
        */
    }
    //console.log(commonProperties);
    //console.log(specificProperties);
    if (!fileOnLoad) {
        var lsmarts = makeLocalSmarts();
    }
    //console.log("SMARTS",lsmarts);
}

function deleteValueByIndexInSpecificProperties(order, oldType, expr) {
    let exprs = specificProperties[order][oldType];
    //console.log(exprs,order, oldType, expr,specificProperties);
    if (exprs && exprs.length > 0) {
        var e_index = exprs.indexOf(expr);
        specificProperties[order][oldType].splice(e_index, 1);
        //console.log(specificProperties);
        if (specificProperties[order][oldType].length == 0) {
            //console.log(specificProperties,oldType,expr);
            delete specificProperties[order][oldType];
        }
    } else {
        delete specificProperties[order][oldType];
    }
}

//update array after the removal of an element
window.updatePropertyArray_OLD = function (selectedField) {
    var parent = selectedField.parentNode;
    var children = parent.childNodes;
    var select = children[1];
    var type = select.value;

    var positionsArray = selectedField.parentNode.id.split("_");
    var pos2 = parseInt(positionsArray.pop(), 10);
    var id = selectedField.id;

    if (id.includes("Specific")) {
        if (id.includes("atom")) {
            var pos1 = parseInt(positionsArray.pop(), 10);
            specificProperties[pos1][pos2] = "";
        } else {
            specificProperties[pos2] = "";
        }
    } else {
        commonProperties[pos2] = "";
    }
    //console.log(commonProperties);
    //console.log(specificProperties);
    if (!fileOnLoad)
        var lsmarts = makeLocalSmarts();
    //console.log("SMARTS",lsmarts);
}

window.storePropertyInArray = function (selectedField, isCommonProperty) {
    //console.log(fileOnLoad,"storePropertyInArray 0",selectedField,isCommonProperty);
    /*if (fileOnLoad && selectedField.id.includes("Select_")) {
        selectFieldsArray.push(selectedField);
        return;
    }*/
    if (fileOnLoad)
        return;
    var expr = null;
    var parent = selectedField.parentNode;
    var id = parent.childNodes[1].id;
    var id2 = null;
    if (parent.id.includes("switchAlwaysNever")) {
        parent = parent.parentNode;
        id2 = parent.childNodes[1].id;
    }
    var type;
    var selectedFieldID = selectedField.id;
    var not = "!";
    var str = "";

    //console.log(selectedField, parent, previousValueDict[id],id, id2);
    if (id2 !== null) {
        if (previousValueDict[id2]) {
            //console.log(previousValueDict[id2], id2);
            updatePropertyArray(selectedField, previousValueDict[id2][0], previousValueDict[id2][1]);
        }
    } else {
        //console.log(previousValueDict[id], id2);
        if (previousValueDict[id]) {
            updatePropertyArray(selectedField, previousValueDict[id][0], previousValueDict[id][1]);
        }
    }

    if (selectedFieldID.includes("AAM")) {
        type = "aam";
    } else if (selectedFieldID.includes("EnvProperty")) {
        type = "Chemical Environment";
    } else {
        var children = parent.childNodes;
        var select = children[1];
        type = select.value;
    }
    //console.log("storePropertyInArray1 ",type, parent.id, pos2, positionsArray);

    if (isCommonProperty) {
        if (type === "aam") {
            commonProperties["aam"] = ":" + selectedField.value;
        } else if (type === "Chemical Element") {
            if ("Chemical Element" in commonProperties) {
                expr = buildElementExpression(parent);
                commonProperties[type] = commonProperties[type].push(expr);
            } else {
                expr = buildElementExpression(parent);
                commonProperties[type] = [buildElementExpression(parent)];
            }
        } else if (type === "Chemical Environment") {
            if ("Chemical Environment" in commonProperties) {
                expr = buildEnvironmentExpression(parent);
                commonProperties[type] = commonProperties[type].push(expr);
            } else {
                expr = buildEnvironmentExpression(parent);
                commonProperties[type] = [expr];
            }
        } else {
            if (type in commonProperties) {
                expr = buildPropertyComplexExpression(parent, type);
                commonProperties[type] = commonProperties[type].push(expr);
            } else {
                expr = buildPropertyComplexExpression(parent, type);
                commonProperties[type] = [expr];
            }
        }
    } else {
        //Specific Property block position (a block is what is contain in the fieldset aaa Property X )
        var positionsArray = parent.id.split("_");
        var order = parseInt(positionsArray[positionsArray.length - 2], 10);
        //console.log(parent.id,positionsArray,order,specificProperties);
        if (!specificProperties[order]) {
            specificProperties[order] = new Array();
        }

        if (type.includes("Bond") || type === "Ring Bond") {
            //console.log("CHECK TYPE",type);
            if (type === 'Electrons Involved In Bond') {
                if (!specificProperties[order][type]) {
                    specificProperties[order][type] = new Array();
                }
                expr = buildPropertyComplexExpression(parent, type);
                specificProperties[order][type].push(expr);
            } else {
                if (children[2].childNodes[0].checked) {
                    str += not;
                }
                str += bondPropertyEncoding[type];
                if (!specificProperties[order][type]) {
                    specificProperties[order][type] = new Array();
                }
                specificProperties[order][type].push(str);
                expr = str;
            }
            //type = "Bond";
        } else {
            if (type === "Chemical Element") {
                if (!specificProperties[order]["Chemical Element"]) {
                    specificProperties[order]["Chemical Element"] = new Array();
                }
                expr = buildElementExpression(parent);
                specificProperties[order]["Chemical Element"].push(expr);
            } else if (type === "Chemical Environment") {
                if (!specificProperties[order]["Chemical Environment"]) {
                    specificProperties[order]["Chemical Environment"] = new Array();
                }
                expr = buildEnvironmentExpression(parent);
                console.log(expr);
                specificProperties[order]["Chemical Environment"].push(expr);
            } else {
                if (!specificProperties[order][type]) {
                    specificProperties[order][type] = new Array();
                }
                expr = buildPropertyComplexExpression(parent, type);
                specificProperties[order][type].push(expr);
                str = expr;
            }
        }
    }
    //console.log(commonProperties);
    //console.log(specificProperties);
    if (!fileOnLoad)
        var lsmarts = makeLocalSmarts();
    //console.log("SMARTS",lsmarts);
    //console.log(type,expr);
    previousValueDict[id] = [type, expr];
    return expr;
}

window.storePropertyInArray_OLD = function (selectedField, isCommonProperty) {
    //console.log("storePropertyInArray 0",isCommonProperty);
    var parent = selectedField.parentNode;
    var positionsArray;
    var pos2;
    var type;
    var selectedFieldID = selectedField.id;
    var not = "!";
    var str = "";
    if (selectedFieldID.includes("_switchAlwaysNever")) {
        parent = parent.parentNode;
    }
    //console.log("storeProp",parent.id, selectedField);
    positionsArray = parent.id.split("_");
    pos2 = parseInt(positionsArray.pop(), 10);

    if (selectedFieldID.includes("AAM")) {
        type = "aam";
    } else {
        var children = parent.childNodes;
        var select = children[1];
        type = select.value;
    }

    //console.log("storePropertyInArray1 ",type, parent.id, pos2, positionsArray);

    if (isCommonProperty) {
        if (type === "aam") {
            commonProperties[pos2] = ":" + selectedField.value;
        } else if (type === "Chemical Element") {
            commonProperties[pos2] = "_" + buildElementExpression(parent);
        } else if (type === "Chemical Environment") {
            commonProperties[pos2] = buildEnvironmentExpression(parent);
        } else {
            commonProperties[pos2] = buildPropertyComplexExpression(parent, type);
        }
    } else {
        if (type.includes("Bond")) {
            var pos1 = parseInt(positionsArray.pop(), 10);
            if (type === 'Electrons Involved In Bond') {
                str = buildPropertyComplexExpression(parent, type)
            } else {
                if (children[2].childNodes[0].checked) {
                    str += not;
                }
                str += bondPropertyEncoding[type];
            }
            if (!specificProperties[pos1]) {
                specificProperties[pos1] = new Array();
            }
            specificProperties[pos1][pos2] = str;
        } else {
            var pos1 = parseInt(positionsArray.pop(), 10);
            if (type === "Chemical Element") {
                str = buildElementExpression(parent);
                //console.log("storePropertyInArray10 ",parent.id, pos1,pos2, positionsArray, str);
                if (!specificProperties[pos1] || specificProperties[pos1] == null) {
                    specificProperties[pos1] = new Array();
                }
                specificProperties[pos1][pos2] = "_" + str;
            } else if (type === "Chemical Environment") {
                str = buildEnvironmentExpression(parent);
                if (!specificProperties[pos1] || specificProperties[pos1] == null) {
                    specificProperties[pos1] = new Array();
                }
                specificProperties[pos1][pos2] = str;
            } else {
                str = buildPropertyComplexExpression(parent, type);
                //console.log("storePropertyInArray10 ",parent.id, pos1, positionsArray, str);
                if (!specificProperties[pos1] || specificProperties[pos1] == null) {
                    specificProperties[pos1] = new Array();
                }
                specificProperties[pos1][pos2] = str;
            }
        }
    }
    //console.log(commonProperties);
    //console.log(specificProperties);
    if (!fileOnLoad)
        var lsmarts = makeLocalSmarts();
    //console.log("SMARTS",lsmarts);
}

window.showSelectParameters = function (id) {
    var select = document.getElementById(id);
    //console.log("id:", id);

    var value = select.options[select.selectedIndex].value;

    var isSwitch = false;
    if (document.getElementById(id + "_switchEquals") != null) {
        isSwitch = true;
    }
    var chemElemProperty = null;
    if (document.getElementById(id + "_aromaticityProp") != null) {
        var chemElemProperty = document.getElementById(id + "_aromaticityProp");
    }

    var chemEnvProperty = null;
    if (document.getElementById(id + "_chemEnvProperty") != null) {
        var chemEnvProperty = document.getElementById(id + "_chemEnvProperty");
    }

    if (value === "Chemical Element") {
        //TODO add periodic table one button for list and the other for not
        if (isSwitch) {
            //console.log("isSwitch", isSwitch);
            //var switch1 = document.getElementById(id + "_switchEquals").parentNode;
            //var equal = document.getElementById(id + "_equal");
            //var interval = document.getElementById(id + "_interval");
            //var switch2 = document.getElementById(id + "_switchAlwaysNever").parentNode;
            var equal = domDict[id + "_equal"];
            var interval = domDict[id + "_interval"];
            var switch1 = domDict[id + "_switchEquals"];
            var switch2 = domDict[id + "_switchAlwaysNever"];
            switch1.style.display = "none";
            equal.style.display = "none";
            interval.style.display = "none";
            switch2.style.display = "none";
        }
        if (chemEnvProperty != null) {
            chemEnvProperty.previousSibling.previousSibling.style.display = "none";
            chemEnvProperty.previousSibling.style.display = "none";
            chemEnvProperty.style.display = "none";
            chemEnvProperty.nextSibling.style.display = "none";
        }

        var element;
        if (chemElemProperty == null) {
            var switch3 = createToggle(id + "_switchAlwaysNeverChemicalElement");
            domDict[id + "_switchAlwaysNeverChemicalElement"] = switch3;
            if (id.includes("Specific")) {
                switch3.setAttribute("onchange", "storePropertyInArray(this.firstChild, false);");
            } else {
                switch3.setAttribute("onchange", "storePropertyInArray(this.firstChild, true);");
            }

            //old code for select aromaticity property
            /*chemElemProperty = document.createElement("div");
            chemElemProperty.setAttribute("id",id+"_chemElemProperty");
            chemElemProperty.setAttribute("class","chemElemPropertyRadioGroup");

            var aliphatic = document.createElement("input");
            aliphatic.setAttribute("type","radio");
            aliphatic.setAttribute("id",id+"_aliphatic");
            aliphatic.setAttribute("name","selector");
            aliphatic.setAttribute("class","chemElemPropertyRadio");
            var aliphaticLabel = document.createElement("label");
            aliphaticLabel.setAttribute("for","aliphatic");
            aliphaticLabel.setAttribute("class","chemElemPropertyRadioLabel");
            aliphaticLabel.innerHTML = "aliphatic";
            chemElemProperty.appendChild(aliphatic);
            chemElemProperty.appendChild(aliphaticLabel);

            var aromatic = document.createElement("input");
            aromatic.setAttribute("type","radio");
            aromatic.setAttribute("id",id+"_aromatic");
            aromatic.setAttribute("name","selector");
            aromatic.setAttribute("class","chemElemPropertyRadio");
            var aromaticLabel = document.createElement("label");
            aromaticLabel.setAttribute("for","aromatic");
            aromaticLabel.setAttribute("class","chemElemPropertyRadioLabel");
            aromaticLabel.innerHTML = "aromatic";
            chemElemProperty.appendChild(aromatic);
            chemElemProperty.appendChild(aromaticLabel);

            var either = document.createElement("input");
            either.setAttribute("type","radio");
            either.setAttribute("id", id+"_either");
            either.setAttribute("name","selector");
            either.setAttribute("class","chemElemPropertyRadio");
            var eitherLabel = document.createElement("label");
            eitherLabel.setAttribute("for","either");
            eitherLabel.setAttribute("class","chemElemPropertyRadioLabel");
            eitherLabel.innerHTML = "either";
            chemElemProperty.appendChild(either);
            chemElemProperty.appendChild(eitherLabel);
            */

            chemElemProperty = document.createElement("button");
            chemElemProperty.setAttribute("type", "button");
            if (id.includes("Specific")) {
                chemElemProperty.setAttribute("onclick", "configureAromaticity(this);storePropertyInArray(this, false);");
            } else {
                chemElemProperty.setAttribute("onclick", "configureAromaticity(this);storePropertyInArray(this, true);");
            }
            chemElemProperty.setAttribute("value", "aliphatic");
            chemElemProperty.setAttribute("id", id + "_aromaticityProp");
            chemElemProperty.innerHTML = "aliphatic";

            element = document.createElement("button");
            element.setAttribute("type", "button");
            element.setAttribute("onclick", "createPeriodicTable(this.id);");
            element.setAttribute("id", id + "_symbol");
            element.setAttribute("value", newSelectedChemObject.symbol);
            element.innerHTML = newSelectedChemObject.symbol;

            select.after(switch3);
            switch3.after(chemElemProperty);
            chemElemProperty.after(element);
            if (showPeriodicTable) {
                createPeriodicTable(element.id);
            }
        } else {
            chemElemProperty.previousSibling.style.display = "inline-block";
            chemElemProperty.style.display = "inline-block";
            chemElemProperty.nextSibling.style.display = "inline-block";
        }
    } else if (value === "Chemical Environment") {
        if (chemElemProperty != null) {
            chemElemProperty.previousSibling.style.display = "none";
            chemElemProperty.style.display = "none";
            chemElemProperty.nextSibling.style.display = "none";
        }
        if (isSwitch) {
            //console.log("isSwitch", isSwitch);
            //var switch1 = document.getElementById(id + "_switchEquals").parentNode;
            //var equal = document.getElementById(id + "_equal");
            //var interval = document.getElementById(id + "_interval");
            //var switch2 = document.getElementById(id + "_switchAlwaysNever").parentNode;
            var equal = domDict[id + "_equal"];
            var interval = domDict[id + "_interval"];
            var switch1 = domDict[id + "_switchEquals"];
            var switch2 = domDict[id + "_switchAlwaysNever"];
            switch1.style.display = "none";
            equal.style.display = "none";
            interval.style.display = "none";
            switch2.style.display = "none";
        }
        if (chemEnvProperty == null) {
            var switch4 = createToggle(id + "_switchAlwaysNeverChemicalEnvironment");
            domDict[id + "_switchAlwaysNeverChemicalEnvironment"] = switch4;
            if (id.includes("Specific")) {
                switch4.setAttribute("onchange", "storePropertyInArray(this.firstChild, false);");
            } else {
                switch4.setAttribute("onchange", "storePropertyInArray(this.firstChild, true);");
            }

            var chemEnvSelect = document.createElement("select");
            var chemEnvSelectID = id + "_chemEnvSelect";
            chemEnvSelect.setAttribute("id", chemEnvSelectID);
            chemEnvSelect.setAttribute("class", "chemEnvSelect");
            chemEnvSelect.setAttribute("style", "width:80px;display:inline-block;white-space:nowrap;overflow-x:auto;overflow-y:hidden");
            addOptions(chemEnvSelect, Object.keys(functionalGroupProperties));
            chemEnvSelect.setAttribute(
                "onchange",
                "setChemicalEnv(this);"
            );

            chemEnvProperty = document.createElement("input");
            chemEnvProperty.setAttribute("type", "text");
            chemEnvProperty.setAttribute("id", id + "_chemEnvProperty");
            chemEnvProperty.setAttribute("size", "10");
            if (id.includes("Specific")) {
                chemEnvProperty.setAttribute("onchange", "storePropertyInArray(this, false);");
            } else {
                chemEnvProperty.setAttribute("onchange", "storePropertyInArray(this, true);");
            }

            var edit = document.createElement("button");
            edit.setAttribute("type", "button");
            //TODO open new instance
            edit.setAttribute("onclick", "");
            edit.setAttribute("id", id + "_symbol");
            edit.setAttribute("value", "Edit");
            edit.innerHTML = "Edit";

            select.after(switch4);
            switch4.after(chemEnvSelect);
            chemEnvSelect.after(chemEnvProperty);
            chemEnvProperty.after(edit);
        } else {
            chemEnvProperty.previousSibling.previousSibling.style.display = "inline-block";
            chemEnvProperty.previousSibling.style.display = "inline-block";
            chemEnvProperty.style.display = "inline-block";
            chemEnvProperty.nextSibling.style.display = "inline-block";
        }
    } else if (!id.includes("bond")) {
        if (chemElemProperty != null) {
            chemElemProperty.previousSibling.style.display = "none";
            chemElemProperty.style.display = "none";
            chemElemProperty.nextSibling.style.display = "none";
        }
        if (chemEnvProperty != null) {
            chemEnvProperty.previousSibling.previousSibling.style.display = "none";
            chemEnvProperty.previousSibling.style.display = "none";
            chemEnvProperty.style.display = "none";
            chemEnvProperty.nextSibling.style.display = "none";
        }
        //check if it has already been created
        if (isSwitch) {
            //var switch1 = document.getElementById(id + "_switchEquals").parentNode;
            //var equal = document.getElementById(id + "_equal");
            //var interval = document.getElementById(id + "_interval");
            //var switch2 = document.getElementById(id + "_switchAlwaysNever").parentNode;
            var equal = domDict[id + "_equal"];
            var interval = domDict[id + "_interval"];
            var switch1 = domDict[id + "_switchEquals"];
            var switch2 = domDict[id + "_switchAlwaysNever"];
            switch1.style.display = "inline-block";
            showAssociatedValues(id + "_switchEquals");
            switch2.style.display = "inline-block";
        } else {
            //create switch
            var switch1 = createToggleSwitch(id + "_switchEquals", "default");
            domDict[id + "_switchEquals"] = switch1;
            //console.log("show select", select.value, select.contains(switch1));

            var val = document.createElement("div");
            val.setAttribute("id", id + "_value");

            var equal = document.createElement("input");
            equal.setAttribute("type", "number");
            equal.setAttribute("id", id + "_equal");
            equal.setAttribute("min", "0");
            equal.setAttribute("max", "99");
            if (id.includes("Specific")) {
                equal.setAttribute("onchange", "storePropertyInArray(this, false);");
            } else {
                equal.setAttribute("onchange", "storePropertyInArray(this, true);");
            }

            domDict[id + "_equal"] = equal;

            val.appendChild(document.createTextNode("equals "));
            val.appendChild(equal);

            var interval = document.createElement("div");
            interval.setAttribute("id", id + "_interval");
            interval.setAttribute("style", "display:none");
            domDict[id + "_interval"] = interval;

            var lower = document.createElement("input");
            lower.setAttribute("type", "number");
            lower.setAttribute("id", id + "_lower");
            lower.setAttribute("min", "0");
            lower.setAttribute("max", "99");
            if (id.includes("Specific")) {
                lower.setAttribute("onchange", "storePropertyInArray(this.parentNode, false);");
            } else {
                lower.setAttribute("onchange", "storePropertyInArray(this.parentNode, true);");
            }

            var higher = document.createElement("input");
            higher.setAttribute("type", "number");
            higher.setAttribute("id", id + "_higher");
            higher.setAttribute("min", "0");
            higher.setAttribute("max", "99");
            if (id.includes("Specific")) {
                higher.setAttribute("onchange", "storePropertyInArray(this.parentNode, false);");
            } else {
                higher.setAttribute("onchange", "storePropertyInArray(this.parentNode, true);");
            }

            var between = document.createElement('span');
            between.style.fontSize = 'small';
            between.appendChild(document.createTextNode('between '));

            var and = document.createElement('span');
            and.style.fontSize = 'small';
            and.appendChild(document.createTextNode(' and '));

            interval.appendChild(between);
            interval.appendChild(lower);
            interval.appendChild(and);
            interval.appendChild(higher);

            //var switch2 = createToggleSwitch(id + "_switchAlwaysNever", "light");
            var switch2 = createToggle(id + "_switchAlwaysNever");
            domDict[id + "_switchAlwaysNever"] = switch2;
            if (id.includes("Specific")) {
                switch2.setAttribute("onchange", "storePropertyInArray(this.firstChild, false);");
            } else {
                switch2.setAttribute("onchange", "storePropertyInArray(this.firstChild, true);");
            }

            //TODO change button by checked button like the one in smartseditor or a simple background change

            /* var always = document.createElement('label');
                    always.setAttribute("id", id + "_always");
                    always.setAttribute("for", id + "_switchAlwaysNever");
                    always.innerHTML = "always";

                    var never = document.createElement('label');
                    never.setAttribute("id", id + "_never");
                    never.setAttribute("for", id + "_switchAlwaysNever");
                    never.setAttribute("style", "display:none");
                    never.innerHTML = "never"; */

            /*select.after(switch1);
            switch1.after(equal);
            equal.after(interval);
            interval.after(switch2);*/

            select.after(switch2);
            switch2.after(switch1);
            switch1.after(equal);
            equal.after(interval);
            //switch2.after(always);
            //always.after(never);
        }
    } else {
        var isSwitch = false;
        var isEqual = false;
        var switch2 = document.getElementById(id + "_switchAlwaysNever");
        if (document.getElementById(id + "_switchAlwaysNever") != null) {
            isSwitch = true;
        }
        if (document.getElementById(id + "_equal") != null) {
            isEqual = true;
        }
        if (value === "Electrons Involved In Bond") {
            if (isEqual) {
                //var equal = document.getElementById(id + "_equal");
                //var interval = document.getElementById(id + "_interval");
                //var switch1 = document.getElementById(id + "_switchEquals").parentNode;
                var equal = domDict[id + "_equal"];
                var interval = domDict[id + "_interval"];
                var switch1 = domDict[id + "_switchEquals"];
                switch1.style.display = "inline-block";
                showAssociatedValues(id + "_switchEquals");
            } else {
                //create switch
                var switch1 = createToggleSwitch(id + "_switchEquals", "default");
                domDict[id + "_switchEquals"] = switch1;
                //console.log("show select", select.value, select.contains(switch1));

                var val = document.createElement("div");
                val.setAttribute("id", id + "_value");

                var equal = document.createElement("input");
                equal.setAttribute("type", "number");
                equal.setAttribute("id", id + "_equal");
                equal.setAttribute("min", "0");
                equal.setAttribute("max", "99");
                equal.setAttribute("onchange", "storePropertyInArray(this, false);");
                domDict[id + "_equal"] = equal;

                val.appendChild(document.createTextNode("equals "));
                val.appendChild(equal);

                var interval = document.createElement("div");
                interval.setAttribute("id", id + "_interval");
                interval.setAttribute("style", "display:none");
                domDict[id + "_interval"] = interval;

                var lower = document.createElement("input");
                lower.setAttribute("type", "number");
                lower.setAttribute("id", id + "_lower");
                lower.setAttribute("min", "0");
                lower.setAttribute("max", "99");
                lower.setAttribute("onchange", "storePropertyInArray(this.parentNode, false);");

                var higher = document.createElement("input");
                higher.setAttribute("type", "number");
                higher.setAttribute("id", id + "_higher");
                higher.setAttribute("min", "0");
                higher.setAttribute("max", "99");
                higher.setAttribute("onchange", "storePropertyInArray(this.parentNode, false);");

                var between = document.createElement('span');
                between.style.fontSize = 'small';
                between.appendChild(document.createTextNode('between '));

                var and = document.createElement('span');
                and.style.fontSize = 'small';
                and.appendChild(document.createTextNode(' and '));

                interval.appendChild(between);
                interval.appendChild(lower);
                interval.appendChild(and);
                interval.appendChild(higher);

                //TODO change button by checked button like the one in smartseditor or a simple background change

                /* var always = document.createElement('label');
                        always.setAttribute("id", id + "_always");
                        always.setAttribute("for", id + "_switchAlwaysNever");
                        always.innerHTML = "always";

                        var never = document.createElement('label');
                        never.setAttribute("id", id + "_never");
                        never.setAttribute("for", id + "_switchAlwaysNever");
                        never.setAttribute("style", "display:none");
                        never.innerHTML = "never"; */

                /*select.after(switch1);
                switch1.after(equal);
                equal.after(interval);
                interval.after(switch2);*/

                if (!isSwitch) {
                    var switch2 = createToggle(id + "_switchAlwaysNever");
                    domDict[id + "_switchAlwaysNever"] = switch2;
                    switch2.setAttribute("onchange", "storePropertyInArray(this.firstChild, false);");

                    select.after(switch2);
                    switch2.after(switch1);
                    switch1.after(equal);
                    equal.after(interval);
                } else {
                    var parent = select.parentNode;
                    parent.appendChild(switch1);
                    parent.appendChild(equal);
                    parent.appendChild(interval);
                }

            }
        } else {
            if (isEqual) {
                //var equal = document.getElementById(id + "_equal");
                //var interval = document.getElementById(id + "_interval");
                //var switch1 = document.getElementById(id + "_switchEquals").parentNode;
                var equal = domDict[id + "_equal"];
                var interval = domDict[id + "_interval"];
                var switch1 = domDict[id + "_switchEquals"];
                equal.style.display = "none";
                interval.style.display = "none";
                switch1.style.display = "none";
            } else {
                //create switch
                if (!isSwitch) {
                    var switch2 = createToggle(id + "_switchAlwaysNever");
                    switch2.setAttribute("onchange", "storePropertyInArray(this.firstChild, false);");
                    select.after(switch2);
                }
            }
        }
    }

    if (id.includes("Specific")) {
        var expr = storePropertyInArray(select, false);
    } else {
        var expr = storePropertyInArray(select, true);
    }
}

window.showAssociatedValues = function (id) {
    //var checkBox = document.getElementById(id);
    var checkBox = domDict[id].firstChild;

    var def;
    var alternative;
    var baseID;

    if (id.includes("_switchAlwaysNever")) {
        baseID = id.replace("_switchAlwaysNever", "");
        //def = document.getElementById(baseID + "_always");
        def = domDict[baseID + "_always"];
        //alternative = document.getElementById(baseID + "_never");
        alternative = domDict[baseID + "_never"];
    } else if (id.includes("_switchEquals")) {
        baseID = id.replace("_switchEquals", "");
        //def = document.getElementById(baseID + "_equal");
        def = domDict[baseID + "_equal"];
        //alternative = document.getElementById(baseID + "_interval");
        alternative = domDict[baseID + "_interval"];
    }

    if (checkBox.checked == true) {
        alternative.style.display = "inline-block";
        def.style.display = "none";
    } else {
        def.style.display = "inline-block";
        alternative.style.display = "none";
    }
}