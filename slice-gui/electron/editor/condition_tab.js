window.addCondition = function () {
    var parent = document.getElementById("ChemConditions");
    var num = getNum(parent);

    var conditionDiv = document.createElement("div");
    conditionDiv.setAttribute("id", "conditionDiv_" + num);
    document.getElementById("conditionAdd").before(conditionDiv);

    var deleteButton = document.createElement("button");
    deleteButton.setAttribute("type", "button");
    deleteButton.setAttribute("id", "conditionDelete_" + num);
    deleteButton.setAttribute("class", "deleteButton");
    deleteButton.setAttribute("style", "display:inline-block;vertical-align:middle;");
    deleteButton.setAttribute("onclick", "descriptionAndCondition['conditions'][this.id.split('_').pop()]={};this.parentNode.remove()");
    var icon = createRoundDeleteSvg("deleteCondition_" + num, "16", "16");
    deleteButton.appendChild(icon);
    conditionDiv.appendChild(deleteButton);

    var fieldset = document.createElement("fieldset");
    fieldset.setAttribute("id", "fieldsetCondition_" + num);
    fieldset.setAttribute("style", "display:inline-block;vertical-align:middle");
    conditionDiv.appendChild(fieldset);

    var legend = document.createElement("legend");
    legend.setAttribute("id", "legendCondition_" + num);
    var cpt = parseInt(num, 10) + 1
    var legendID = "condition_" + num + "_" + cpt;
    legend.innerHTML = "Condition ";
    var legendName = document.createElement("input");
    legendName.setAttribute("type", "text");
    legendName.setAttribute("id", legendID);
    legendName.setAttribute("placeholder", "Name");
    legendName.setAttribute("onchange", "updateSliceFile('conditions',getPosAuthorRef(this.id),'name',this.value,this.id.split('_').pop());");
    legend.appendChild(legendName);
    fieldset.appendChild(legend);

    var chevronButton = document.createElement("button");
    chevronButton.setAttribute("type", "button");
    chevronButton.setAttribute("id", "chevronButtonCondition_" + num);
    chevronButton.setAttribute("class", "showButton");
    chevronButton.setAttribute("onclick", "hideShow(" + "'conditionContainer_" + num + "','chevronButtonCondition_" + num + "')");
    legend.appendChild(chevronButton);
    var chevronIcon = createChevronUpSvg(null, "16", "16");
    chevronButton.appendChild(chevronIcon);

    var conditionContainer = document.createElement("div");
    conditionContainer.setAttribute("id", "conditionContainer_" + num);
    fieldset.appendChild(conditionContainer);

    //step
    var stepContainer = document.createElement("div");
    var stepContainerID = "stepContainer_" + num;
    stepContainer.setAttribute("id", stepContainerID);
    conditionContainer.appendChild(stepContainer);

    var labelStep = document.createElement("label");
    labelStep.innerHTML = "Step";
    stepContainer.appendChild(labelStep);

    var stepTxt = document.createElement("input");
    stepTxt.setAttribute("type", "number");
    stepTxt.setAttribute("id", stepContainerID + "_" + num);
    stepTxt.setAttribute("name", stepContainerID);
    stepTxt.setAttribute("onchange", "updateSliceFile('conditions',getPosAuthorRef(this.id),'step',this.value);");
    stepContainer.appendChild(stepTxt);

    //reagent
    var reagentContainer = document.createElement("div");
    var reagentContainerID = "reagentContainer_" + num;
    reagentContainer.setAttribute("id", reagentContainerID);
    conditionContainer.appendChild(reagentContainer);

    var labelReagent = document.createElement("label");
    labelReagent.innerHTML = "Reagent(s)";
    reagentContainer.appendChild(labelReagent);

    var reagentAddButton = document.createElement("button");
    reagentAddButton.setAttribute("type", "button");
    reagentAddButton.setAttribute("id", "reagentAdd_" + num);
    reagentAddButton.setAttribute("class", "addButton");
    reagentAddButton.setAttribute(
        "onclick",
        "addSpecificCondition(this.id,'reagentContainer','reagent')"
    );
    var icon = createRoundPlusSvg("addReagent_" + num, "16", "16");
    reagentAddButton.appendChild(icon);
    reagentContainer.appendChild(reagentAddButton);

    addSpecificCondition("reagentAdd_" + num, "reagentContainer", "reagent");

    var reagentList = document.createElement("datalist");
    reagentList.setAttribute("id", "reagentsDB");
    reagentList.setAttribute("autocomplete", "off");
    createDataList(reagentList, reagentsDB);
    reagentContainer.appendChild(reagentList);

    //solvent
    var solventContainer = document.createElement("div");
    var solventContainerID = "solventContainer_" + num;
    solventContainer.setAttribute("id", solventContainerID);
    conditionContainer.appendChild(solventContainer);

    var labelSolvent = document.createElement("label");
    labelSolvent.innerHTML = "Solvent(s)";
    solventContainer.appendChild(labelSolvent);

    var solventAddButton = document.createElement("button");
    solventAddButton.setAttribute("type", "button");
    solventAddButton.setAttribute("id", "solventAdd_" + num);
    solventAddButton.setAttribute("class", "addButton");
    solventAddButton.setAttribute(
        "onclick",
        "addSpecificCondition(this.id,'solventContainer','solvent')"
    );
    var icon = createRoundPlusSvg("addSolvent_" + num, "16", "16");
    solventAddButton.appendChild(icon);
    solventContainer.appendChild(solventAddButton);

    addSpecificCondition("solventAdd_" + num, "solventContainer", "solvent");

    var solventList = document.createElement("datalist");
    solventList.setAttribute("id", "solventsDB");
    solventList.setAttribute("autocomplete", "off");
    createDataList(solventList, solventsDB);
    solventContainer.appendChild(solventList);

    //Temperature
    var temperatureContainer = document.createElement("div");
    var temperatureContainerID = "temperatureContainer_" + num;
    temperatureContainer.setAttribute("id", temperatureContainerID);
    conditionContainer.appendChild(temperatureContainer);

    var labelTemperature = document.createElement("label");
    labelTemperature.innerHTML = "Temperature(s)";
    temperatureContainer.appendChild(labelTemperature);

    var temperatureAddButton = document.createElement("button");
    temperatureAddButton.setAttribute("type", "button");
    temperatureAddButton.setAttribute("id", "temperatureAdd_" + num);
    temperatureAddButton.setAttribute("class", "addButton");
    temperatureAddButton.setAttribute(
        "onclick",
        "addSpecificCondition(this.id,'temperatureContainer','temperature')"
    );
    var icon = createRoundPlusSvg("addTemperature_" + num, "16", "16");
    temperatureAddButton.appendChild(icon);
    temperatureContainer.appendChild(temperatureAddButton);

    addSpecificCondition("temperatureAdd_" + num, "temperatureContainer", "temperature");

    //Time
    var timeContainer = document.createElement("div");
    var timeContainerID = "timeContainer_" + num;
    timeContainer.setAttribute("id", timeContainerID);
    conditionContainer.appendChild(timeContainer);

    var labelTime = document.createElement("label");
    labelTime.innerHTML = "Time(s)";
    timeContainer.appendChild(labelTime);

    var timeAddButton = document.createElement("button");
    timeAddButton.setAttribute("type", "button");
    timeAddButton.setAttribute("id", "timeAdd_" + num);
    timeAddButton.setAttribute("class", "addButton");
    timeAddButton.setAttribute(
        "onclick",
        "addSpecificCondition(this.id,'timeContainer','time')"
    );
    var icon = createRoundPlusSvg("addTime_" + num, "16", "16");
    timeAddButton.appendChild(icon);
    timeContainer.appendChild(timeAddButton);

    addSpecificCondition("timeAdd_" + num, "timeContainer", "time");

    //Supplementary Information
    var supInfoContainer = document.createElement("div");
    var supInfoContainerID = "supInfoContainer_" + num;
    supInfoContainer.setAttribute("id", supInfoContainerID);
    conditionContainer.appendChild(supInfoContainer);

    var labelSupInfo = document.createElement("label");
    labelSupInfo.innerHTML = "Supplementary Information";
    supInfoContainer.appendChild(labelSupInfo);

    var supInfoAddButton = document.createElement("button");
    supInfoAddButton.setAttribute("type", "button");
    supInfoAddButton.setAttribute("id", "supInfoAdd_" + num);
    supInfoAddButton.setAttribute("class", "addButton");
    supInfoAddButton.setAttribute(
        "onclick",
        "addSpecificCondition(this.id,'supInfoContainer','supInfo')"
    );
    var icon = createRoundPlusSvg("addsupInfo_" + num, "16", "16");
    supInfoAddButton.appendChild(icon);
    supInfoContainer.appendChild(supInfoAddButton);

    addSpecificCondition("supInfoAdd_" + num, "supInfoContainer", "supInfo");
}

window.addSpecificCondition = function (addButtonId, parentBaseId, childBaseId) {
    var numRef = addButtonId.split("_").pop();
    var parent = document.getElementById(parentBaseId + "_" + numRef);
    var addButton = document.getElementById(childBaseId + "Add_" + numRef);
    var num = getNum(parent);

    var deleteButton = document.createElement("button");
    deleteButton.setAttribute("type", "button");
    deleteButton.setAttribute("id", childBaseId + "Delete_" + numRef + "_" + num);
    deleteButton.setAttribute("class", "deleteButton");
    deleteButton.setAttribute(
        "onclick",
        "var next = this.nextSibling; removeFieldInSliceFile(this.id,'conditions',next.id.split('_')[0],next); next.remove();this.remove()"
    );
    var icon = createRoundDeleteSvg("delete" + childBaseId + "_" + num, "16", "16");
    deleteButton.appendChild(icon);

    var obj = document.createElement("input");
    obj.setAttribute("type", "text");
    if (childBaseId === 'reagent' || childBaseId === 'solvent') {
        obj.setAttribute("list", childBaseId + "sDB");
    }
    obj.setAttribute("id", childBaseId + "_" + numRef + "_" + num);
    obj.setAttribute("name", childBaseId + "_" + num);
    obj.setAttribute("onchange", "var arr = this.id.split('_'); updateSliceFile('conditions',getPosAuthorRef(this.id),arr[0],this.value,this.id.split('_').pop());");

    addButton.before(deleteButton);
    addButton.before(obj);
}