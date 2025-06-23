window.addHistory = function () {
    var parent = document.getElementById("historyDiv");

    var historyDiv = document.createElement("div");
    var num = getNum(parent);
    historyDiv.setAttribute("id", "historyDiv_" + num);
    parent.appendChild(historyDiv);

    var deleteButton = document.createElement("button");
    deleteButton.setAttribute("type", "button");
    deleteButton.setAttribute("id", "historyAuthorDelete_" + num);
    deleteButton.setAttribute("class", "deleteButton");
    deleteButton.setAttribute("onclick", "console.log(\"here\", descriptionAndCondition['history']);descriptionAndCondition['history'].splice(this.id.split('_').pop(),1);this.parentNode.remove();writeSliceFile();");
    var icon = createRoundDeleteSvg("deleteAuthor_" + num, "16", "16");
    deleteButton.appendChild(icon);
    historyDiv.appendChild(deleteButton);

    var labelType = document.createElement("label");
    labelType.setAttribute("for", "historyModifType");
    labelType.innerHTML = "Type";
    historyDiv.appendChild(labelType);

    var selectType = document.createElement("select");
    selectType.setAttribute("id", "historyModifType_" + num);
    selectType.setAttribute("name", "historyModifType");
    selectType.setAttribute("onchange", "updateSliceFile('history',this.id.split('_').pop(),'type',this.value);")
    addOptions(selectType, ["created", "modified"]);
    addDefaultOptionText(selectType);
    historyDiv.appendChild(selectType);

    var labelDate = document.createElement("label");
    labelDate.setAttribute("for", "historyDate");
    labelDate.innerHTML = "Date";
    historyDiv.appendChild(labelDate);

    var date = document.createElement("input");
    date.setAttribute("type", "date");
    date.setAttribute("id", "historyDate_" + num);
    date.setAttribute("name", "historyDate");
    date.setAttribute("onchange", "updateSliceFile('history',this.id.split('_').pop(),'date',this.value);")
    historyDiv.appendChild(date);

    var authorLabel = document.createElement("label");
    authorLabel.setAttribute("for", "historyAuthor");
    authorLabel.innerHTML = "Author";
    historyDiv.appendChild(authorLabel);

    var author = document.createElement("input");
    author.setAttribute("type", "text");
    author.setAttribute("list", "authorsDB");
    author.setAttribute("id", "historyAuthor_" + num);
    author.setAttribute("name", "historyAuthor");
    author.setAttribute("onchange", "updateSliceFile('history',this.id.split('_').pop(),'author',this.value,0);")
    historyDiv.appendChild(author);

    var authorList = document.createElement("datalist");
    authorList.setAttribute("id", "authorsDB");
    authorList.setAttribute("autocomplete", "off");
    createDataList(authorList, authorsDB);
    historyDiv.appendChild(authorList);
    historyDiv.appendChild(document.createElement("br"));
}

window.addSetup = function () {
    var parent = document.getElementById("setupDiv");
    //var options = ["N.A.", "bad", "poor", "fair", "good", "very good", "excellent"];

    var labelYield = document.createElement("label");
    labelYield.setAttribute("for", "yieldSelect");
    labelYield.innerHTML = "Yield";
    parent.appendChild(labelYield);

    var yieldSelect = document.createElement("select");
    yieldSelect.setAttribute("id", "yieldSelect");
    yieldSelect.setAttribute("name", "yieldSelect");
    yieldSelect.setAttribute("onchange", "updateSliceFile('setup','yield',null,this.value);")
    addOptions(yieldSelect, options);
    yieldSelect.options[0].selected = true;
    parent.appendChild(yieldSelect);
    parent.appendChild(document.createElement("br"));

    var labelReliability = document.createElement("label");
    labelReliability.setAttribute("for", "reliabilitySelect");
    labelReliability.innerHTML = "Reliability";
    parent.appendChild(labelReliability);

    var reliabilitySelect = document.createElement("select");
    reliabilitySelect.setAttribute("id", "reliabilitySelect");
    reliabilitySelect.setAttribute("name", "reliabilitySelect");
    reliabilitySelect.setAttribute("onchange", "updateSliceFile('setup','reliability',null,this.value);")
    addOptions(reliabilitySelect, options);
    reliabilitySelect.options[0].selected = true;
    parent.appendChild(reliabilitySelect);
    parent.appendChild(document.createElement("br"));

    var labelReputation = document.createElement("label");
    labelReputation.setAttribute("for", "yieldSelect");
    labelReputation.innerHTML = "Reputation";
    parent.appendChild(labelReputation);

    var reputationSelect = document.createElement("select");
    reputationSelect.setAttribute("id", "reputationSelect");
    reputationSelect.setAttribute("name", "reputationSelect");
    reputationSelect.setAttribute("onchange", "updateSliceFile('setup','reputation',null,this.value);")
    addOptions(reputationSelect, options);
    reputationSelect.options[0].selected = true;
    parent.appendChild(reputationSelect);
    parent.appendChild(document.createElement("br"));

    var labelHomoselectivity = document.createElement("label");
    labelHomoselectivity.setAttribute("for", "homoselectivitySelect");
    labelHomoselectivity.innerHTML = "Homoselectivity";
    parent.appendChild(labelHomoselectivity);

    var homoselectivitySelect = document.createElement("select");
    homoselectivitySelect.setAttribute("id", "homoselectivitySelect");
    homoselectivitySelect.setAttribute("name", "homoselectivitySelect");
    homoselectivitySelect.setAttribute("onchange", "updateSliceFile('setup','homoselectivity',null,this.value);")
    addOptions(homoselectivitySelect, options);
    homoselectivitySelect.options[0].selected = true;
    parent.appendChild(homoselectivitySelect);
    parent.appendChild(document.createElement("br"));

    var labelHeteroselectivity = document.createElement("label");
    labelHeteroselectivity.setAttribute("for", "heteroselectivitySelect");
    labelHeteroselectivity.innerHTML = "Heteroselectivity";
    parent.appendChild(labelHeteroselectivity);

    var heteroselectivitySelect = document.createElement("select");
    heteroselectivitySelect.setAttribute("id", "heteroselectivitySelect");
    heteroselectivitySelect.setAttribute("name", "heteroselectivitySelect");
    heteroselectivitySelect.setAttribute("onchange", "updateSliceFile('setup','heteroselectivity',null,this.value);")
    addOptions(heteroselectivitySelect, options);
    heteroselectivitySelect.options[0].selected = true;
    parent.appendChild(heteroselectivitySelect);
    parent.appendChild(document.createElement("br"));

    var labelOrientationalSelectivity = document.createElement("label");
    labelOrientationalSelectivity.setAttribute(
        "for",
        "orientationalSelectivitySelect"
    );
    labelOrientationalSelectivity.innerHTML = "Orientational Selectivity";
    parent.appendChild(labelOrientationalSelectivity);

    var orientationalSelectivitySelect = document.createElement("select");
    orientationalSelectivitySelect.setAttribute(
        "id",
        "orientationalSelectivitySelect"
    );
    orientationalSelectivitySelect.setAttribute(
        "name",
        "orientationalSelectivitySelect"
    );
    orientationalSelectivitySelect.setAttribute("onchange", "updateSliceFile('setup','orientationalSelectivity',null,this.value);")
    addOptions(orientationalSelectivitySelect, options);
    orientationalSelectivitySelect.options[0].selected = true;
    parent.appendChild(orientationalSelectivitySelect);
    parent.appendChild(document.createElement("br"));

    var labelConditionSelectivity = document.createElement("label");
    labelConditionSelectivity.setAttribute("for", "conditionSelectivitySelect");
    labelConditionSelectivity.innerHTML = "Condition Flexibility";
    parent.appendChild(labelConditionSelectivity);

    var conditionSelectivitySelect = document.createElement("select");
    conditionSelectivitySelect.setAttribute("id", "conditionSelectivitySelect");
    conditionSelectivitySelect.setAttribute("name", "conditionSelectivitySelect");
    conditionSelectivitySelect.setAttribute("onchange", "updateSliceFile('setup','conditionSelectivity',null,this.value);")
    addOptions(conditionSelectivitySelect, options);
    conditionSelectivitySelect.options[0].selected = true;
    parent.appendChild(conditionSelectivitySelect);
    parent.appendChild(document.createElement("br"));

    var labelThermodynamics = document.createElement("label");
    labelThermodynamics.setAttribute("for", "thermodynamicsSelect");
    labelThermodynamics.innerHTML = "Thermodynamics";
    parent.appendChild(labelThermodynamics);

    var thermodynamicsSelect = document.createElement("select");
    thermodynamicsSelect.setAttribute("id", "thermodynamicsSelect");
    thermodynamicsSelect.setAttribute("name", "thermodynamicsSelect");
    thermodynamicsSelect.setAttribute("onchange", "updateSliceFile('setup','thermodynamics',null,this.value);")
    addOptions(thermodynamicsSelect, options);
    thermodynamicsSelect.options[0].selected = true;
    parent.appendChild(thermodynamicsSelect);
}

window.addBibliography = function () {
    var ref = document.getElementById("bibliographyDiv");
    var varEntriesNum = getNum(ref);
    var modal = document.createElement("div");
    modal.setAttribute("id", "bibliographyModal_" + varEntriesNum);
    modal.setAttribute("class", "modal");
    ref.appendChild(modal);

    var modalContent = document.createElement("div");
    modalContent.setAttribute("id", "bibliographyModalContent_" + varEntriesNum);
    modalContent.setAttribute("class", "modal-content");
    modal.appendChild(modalContent);

    var span = document.createElement("span");
    span.setAttribute("id", "bibliographySpan_" + varEntriesNum);
    span.setAttribute("class", "close");
    span.setAttribute("onclick", "descriptionAndCondition['bibliography'][this.id.split('_').pop()]={};closeBibliographyModal(this.id)");
    span.innerHTML = "&times;";
    modalContent.appendChild(span);

    var labelAuthors = document.createElement("label");
    labelAuthors.setAttribute("for", "bibliographyAuthorsDiv_" + varEntriesNum);
    labelAuthors.innerHTML = "Authors";
    modalContent.appendChild(labelAuthors);

    var authors = document.createElement("div");
    authors.setAttribute("id", "bibliographyAuthorsDiv_" + varEntriesNum);
    modalContent.appendChild(authors);

    var num = getNum(authors);
    var addButton = document.createElement("button");
    addButton.setAttribute("type", "button");
    addButton.setAttribute("id", "bibliographyAuthorAdd_" + varEntriesNum);
    addButton.setAttribute("class", "addButton");
    addButton.setAttribute("onclick", "addAuthorInBiblio(this.id)");
    var icon = createRoundPlusSvg(
        "addAuthor_" + varEntriesNum + "_" + num,
        "16",
        "16"
    );
    authors.appendChild(addButton);
    addButton.appendChild(icon);
    addAuthorInBiblio("bibliographyAuthorAdd_" + varEntriesNum);

    var authorList = document.createElement("datalist");
    authorList.setAttribute("id", "authorsDB");
    authorList.setAttribute("autocomplete", "off");
    createDataList(authorList, authorsDB);
    authors.appendChild(authorList);
    modalContent.appendChild(document.createElement("br"));

    var labelTitle = document.createElement("label");
    labelTitle.setAttribute("for", "bibliographyTitle_" + varEntriesNum);
    labelTitle.innerHTML = "Title";
    modalContent.appendChild(labelTitle);

    var title = document.createElement("input");
    title.setAttribute("type", "text");
    title.setAttribute("id", "bibliographyTitle_" + varEntriesNum);
    title.setAttribute("size", "50");
    title.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'title',this.value);")
    modalContent.appendChild(title);
    modalContent.appendChild(document.createElement("br"));

    var labelJournal = document.createElement("label");
    labelJournal.setAttribute("for", "bibliographyJournal_" + varEntriesNum);
    labelJournal.innerHTML = "Journal/Book";
    modalContent.appendChild(labelJournal);

    var journal = document.createElement("input");
    journal.setAttribute("type", "text");
    journal.setAttribute("list", journalsDB);
    journal.setAttribute("id", "bibliographyJournal_" + varEntriesNum);
    journal.setAttribute("name", "bibliographyJournal_" + varEntriesNum);
    journal.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'journal',this.value);")
    modalContent.appendChild(journal);

    var journalList = document.createElement("datalist");
    journalList.setAttribute("id", "journals");
    journalList.setAttribute("autocomplete", "off");
    createDataList(journalList, journalsDB);
    modalContent.appendChild(journalList);
    modalContent.appendChild(document.createElement("br"));

    var labelYear = document.createElement("label");
    labelYear.setAttribute("for", "bibliographyYear_" + varEntriesNum);
    labelYear.innerHTML = "Year";
    modalContent.appendChild(labelYear);

    var year = document.createElement("input");
    year.setAttribute("type", "number");
    year.setAttribute("id", "bibliographyYear_" + varEntriesNum);
    year.setAttribute("size", "4");
    year.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'year',this.value);")
    modalContent.appendChild(year);
    modalContent.appendChild(document.createElement("br"));

    var labelVolume = document.createElement("label");
    labelVolume.setAttribute("for", "bibliographyVolume_" + varEntriesNum);
    labelVolume.innerHTML = "Volume";
    modalContent.appendChild(labelVolume);

    var volume = document.createElement("input");
    volume.setAttribute("type", "text");
    volume.setAttribute("id", "bibliographyVolume_" + varEntriesNum);
    volume.setAttribute("size", "20");
    volume.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'volume',this.value);")
    modalContent.appendChild(volume);
    modalContent.appendChild(document.createElement("br"));

    var labelPart = document.createElement("label");
    labelPart.setAttribute("for", "bibliographyPart_" + varEntriesNum);
    labelPart.innerHTML = "Part";
    modalContent.appendChild(labelPart);

    var part = document.createElement("input");
    part.setAttribute("type", "text");
    part.setAttribute("id", "bibliographyPart_" + varEntriesNum);
    part.setAttribute("size", "6");
    part.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'part',this.value);")
    modalContent.appendChild(part);
    modalContent.appendChild(document.createElement("br"));

    var labelPages = document.createElement("label");
    labelPages.setAttribute("for", "bibliographyPages_" + varEntriesNum);
    labelPages.innerHTML = "Pages";
    modalContent.appendChild(labelPages);

    var pages = document.createElement("input");
    pages.setAttribute("type", "text");
    pages.setAttribute("id", "bibliographyPages_" + varEntriesNum);
    pages.setAttribute("size", "10");
    pages.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'pages',this.value);")
    modalContent.appendChild(pages);
    modalContent.appendChild(document.createElement("br"));

    var labelLink = document.createElement("label");
    labelLink.setAttribute("for", "bibliographyLink_" + varEntriesNum);
    labelLink.innerHTML = "Link";
    modalContent.appendChild(labelLink);

    var link = document.createElement("input");
    link.setAttribute("type", "text");
    link.setAttribute("id", "bibliographyLink_" + varEntriesNum);
    link.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'link',this.value);")
    link.setAttribute("size", "50");
    modalContent.appendChild(link);
    modalContent.appendChild(document.createElement("br"));

    var labelEditor = document.createElement("label");
    labelEditor.setAttribute("for", "bibliographyEditor_" + varEntriesNum);
    labelEditor.innerHTML = "Editor";
    modalContent.appendChild(labelEditor);

    var editor = document.createElement("input");
    editor.setAttribute("type", "text");
    editor.setAttribute("id", "bibliographyEditor_" + varEntriesNum);
    editor.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'editor',this.value);")
    editor.setAttribute("size", "50");
    modalContent.appendChild(editor);
    modalContent.appendChild(document.createElement("br"));

    var labelPublisher = document.createElement("label");
    labelPublisher.setAttribute("for", "bibliographyPublisher_" + varEntriesNum);
    labelPublisher.innerHTML = "Publisher";
    modalContent.appendChild(labelPublisher);

    var publisher = document.createElement("input");
    publisher.setAttribute("type", "text");
    publisher.setAttribute("id", "bibliographyPublisher_" + varEntriesNum);
    publisher.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'publisher',this.value);")
    publisher.setAttribute("size", "50");
    modalContent.appendChild(publisher);
    modalContent.appendChild(document.createElement("br"));

    var labelCity = document.createElement("label");
    labelCity.setAttribute("for", "bibliographyCity_" + varEntriesNum);
    labelCity.innerHTML = "City of publication";
    modalContent.appendChild(labelCity);

    var city = document.createElement("input");
    city.setAttribute("type", "text");
    city.setAttribute("id", "bibliographyCity_" + varEntriesNum);
    city.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'city',this.value);")
    city.setAttribute("size", "50");
    modalContent.appendChild(city);


    modalContent.appendChild(document.createElement("br"));
    modalContent.appendChild(document.createElement("br"));

    var okButton = document.createElement("button");
    okButton.setAttribute("type", "button");
    okButton.setAttribute("id", "bibliographyOK_" + varEntriesNum);
    okButton.setAttribute("class", "okButton");
    okButton.setAttribute("style", "text-align:center");
    okButton.innerHTML = "OK";
    okButton.setAttribute(
        "onclick",
        "addReference(this.id);closeBibliographyModal(this.id)"
    );
    modalContent.appendChild(okButton);

    modal.style.display = "block";

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.remove();
            descriptionAndCondition['bibliography'][varEntriesNum] = {};
        }
    };
}

window.addBibliographyModal = function () {
    var ref = document.getElementById("bibliographyDiv");
    var varEntriesNum = getNum(ref);
    var modal = document.createElement("div");
    modal.setAttribute("id", "bibliographyModal_" + varEntriesNum);
    modal.setAttribute("class", "modal");
    ref.appendChild(modal);

    var modalContent = document.createElement("div");
    modalContent.setAttribute("id", "bibliographyModalContent_" + varEntriesNum);
    modalContent.setAttribute("class", "modal-content");
    modal.appendChild(modalContent);

    var span = document.createElement("span");
    span.setAttribute("id", "bibliographySpan_" + varEntriesNum);
    span.setAttribute("class", "close");
    span.setAttribute("onclick", "descriptionAndCondition['bibliography'][this.id.split('_').pop()]={};closeBibliographyModal(this.id)");
    span.innerHTML = "&times;";
    modalContent.appendChild(span);

    var labelAuthors = document.createElement("label");
    labelAuthors.setAttribute("for", "bibliographyAuthorsDiv_" + varEntriesNum);
    labelAuthors.innerHTML = "Authors";
    modalContent.appendChild(labelAuthors);

    var authors = document.createElement("div");
    authors.setAttribute("id", "bibliographyAuthorsDiv_" + varEntriesNum);
    modalContent.appendChild(authors);

    var num = getNum(authors);
    var addButton = document.createElement("button");
    addButton.setAttribute("type", "button");
    addButton.setAttribute("id", "bibliographyAuthorAdd_" + varEntriesNum);
    addButton.setAttribute("class", "addButton");
    addButton.setAttribute("onclick", "addAuthorInBiblio(this.id)");
    var icon = createRoundPlusSvg(
        "addAuthor_" + varEntriesNum + "_" + num,
        "16",
        "16"
    );
    authors.appendChild(addButton);
    addButton.appendChild(icon);
    addAuthorInBiblio("bibliographyAuthorAdd_" + varEntriesNum);

    var authorList = document.createElement("datalist");
    authorList.setAttribute("id", "authorsDB");
    authorList.setAttribute("autocomplete", "off");
    createDataList(authorList, authorsDB);
    authors.appendChild(authorList);
    modalContent.appendChild(document.createElement("br"));

    var labelTitle = document.createElement("label");
    labelTitle.setAttribute("for", "bibliographyTitle_" + varEntriesNum);
    labelTitle.innerHTML = "Title";
    modalContent.appendChild(labelTitle);

    var title = document.createElement("input");
    title.setAttribute("type", "text");
    title.setAttribute("id", "bibliographyTitle_" + varEntriesNum);
    title.setAttribute("size", "50");
    title.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'title',this.value);")
    modalContent.appendChild(title);
    modalContent.appendChild(document.createElement("br"));

    var labelJournal = document.createElement("label");
    labelJournal.setAttribute("for", "bibliographyJournal_" + varEntriesNum);
    labelJournal.innerHTML = "Journal";
    modalContent.appendChild(labelJournal);

    var journal = document.createElement("input");
    journal.setAttribute("type", "text");
    journal.setAttribute("list", journalsDB);
    journal.setAttribute("id", "bibliographyJournal_" + varEntriesNum);
    journal.setAttribute("name", "bibliographyJournal_" + varEntriesNum);
    journal.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'journal',this.value);")
    modalContent.appendChild(journal);

    var journalList = document.createElement("datalist");
    journalList.setAttribute("id", "journals");
    journalList.setAttribute("autocomplete", "off");
    createDataList(journalList, journalsDB);
    modalContent.appendChild(journalList);
    modalContent.appendChild(document.createElement("br"));

    var labelYear = document.createElement("label");
    labelYear.setAttribute("for", "bibliographyYear_" + varEntriesNum);
    labelYear.innerHTML = "Year";
    modalContent.appendChild(labelYear);

    var year = document.createElement("input");
    year.setAttribute("type", "number");
    year.setAttribute("id", "bibliographyYear_" + varEntriesNum);
    year.setAttribute("size", "4");
    year.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'year',this.value);")
    modalContent.appendChild(year);
    modalContent.appendChild(document.createElement("br"));

    var labelVolume = document.createElement("label");
    labelVolume.setAttribute("for", "bibliographyVolume_" + varEntriesNum);
    labelVolume.innerHTML = "Volume";
    modalContent.appendChild(labelVolume);

    var volume = document.createElement("input");
    volume.setAttribute("type", "text");
    volume.setAttribute("id", "bibliographyVolume_" + varEntriesNum);
    volume.setAttribute("size", "20");
    volume.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'volume',this.value);")
    modalContent.appendChild(volume);
    modalContent.appendChild(document.createElement("br"));

    var labelPart = document.createElement("label");
    labelPart.setAttribute("for", "bibliographyPart_" + varEntriesNum);
    labelPart.innerHTML = "Part";
    modalContent.appendChild(labelPart);

    var part = document.createElement("input");
    part.setAttribute("type", "text");
    part.setAttribute("id", "bibliographyPart_" + varEntriesNum);
    part.setAttribute("size", "6");
    part.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'part',this.value);")
    modalContent.appendChild(part);
    modalContent.appendChild(document.createElement("br"));

    var labelPages = document.createElement("label");
    labelPages.setAttribute("for", "bibliographyPages_" + varEntriesNum);
    labelPages.innerHTML = "Pages";
    modalContent.appendChild(labelPages);

    var pages = document.createElement("input");
    pages.setAttribute("type", "text");
    pages.setAttribute("id", "bibliographyPages_" + varEntriesNum);
    pages.setAttribute("size", "10");
    pages.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'pages',this.value);")
    modalContent.appendChild(pages);
    modalContent.appendChild(document.createElement("br"));

    var labelLink = document.createElement("label");
    labelLink.setAttribute("for", "bibliographyLink_" + varEntriesNum);
    labelLink.innerHTML = "Link";
    modalContent.appendChild(labelLink);

    var link = document.createElement("input");
    link.setAttribute("type", "text");
    link.setAttribute("id", "bibliographyLink_" + varEntriesNum);
    link.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'link',this.value);")
    link.setAttribute("size", "50");
    modalContent.appendChild(link);
    modalContent.appendChild(document.createElement("br"));

    var labelEditor = document.createElement("editor");
    labelEditor.setAttribute("for", "bibliographyEditor_" + varEntriesNum);
    labelEditor.innerHTML = "Editor";
    modalContent.appendChild(labelEditor);

    var editor = document.createElement("input");
    editor.setAttribute("type", "text");
    editor.setAttribute("id", "bibliographyEditor_" + varEntriesNum);
    editor.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'editor',this.value);")
    editor.setAttribute("size", "50");
    modalContent.appendChild(editor);
    modalContent.appendChild(document.createElement("br"));

    var labelPublisher = document.createElement("publisher");
    labelPublisher.setAttribute("for", "bibliographyEditor_" + varEntriesNum);
    labelPublisher.innerHTML = "Publisher";
    modalContent.appendChild(labelPublisher);

    var publisher = document.createElement("input");
    publisher.setAttribute("type", "text");
    publisher.setAttribute("id", "bibliographyPublisher_" + varEntriesNum);
    publisher.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'publisher',this.value);")
    publisher.setAttribute("size", "50");
    modalContent.appendChild(publisher);
    modalContent.appendChild(document.createElement("br"));

    var labelCity = document.createElement("city");
    labelCity.setAttribute("for", "bibliographyCity_" + varEntriesNum);
    labelCity.innerHTML = "City of Publication";
    modalContent.appendChild(labelCity);

    var city = document.createElement("input");
    city.setAttribute("type", "text");
    city.setAttribute("id", "bibliographyCity_" + varEntriesNum);
    city.setAttribute("onchange", "updateSliceFile('bibliography',this.id.split('_').pop(),'city',this.value);")
    city.setAttribute("size", "50");
    modalContent.appendChild(city);

    modalContent.appendChild(document.createElement("br"));
    modalContent.appendChild(document.createElement("br"));

    var okButton = document.createElement("button");
    okButton.setAttribute("type", "button");
    okButton.setAttribute("id", "bibliographyOK_" + varEntriesNum);
    okButton.setAttribute("class", "okButton");
    okButton.setAttribute("style", "text-align:center");
    okButton.innerHTML = "OK";
    okButton.setAttribute(
        "onclick",
        "addReference(this.id);closeBibliographyModal(this.id)"
    );
    modalContent.appendChild(okButton);

    modal.style.display = "none";
}

window.addReference = function (id) {
    var isNull = true;
    var text = "";
    var num = id.split("_").pop();
    var authors = document.getElementById("bibliographyAuthorsDiv_" + num);
    var children = authors.childNodes;
    var len = children.length;
    for (var i = 0; i < len; i++) {
        var elt = children[i];
        //console.log(i, len, elt);
        if (elt.getAttribute("type") === "text") {
            var val = elt.value;
            if (val.length > 0) {
                text += elt.value + ", ";
                isNull = false;
            }
        }
    }

    if (text.length > 1) {
        text = text.slice(0, -2) + " | ";
    }

    var title = document.getElementById("bibliographyTitle_" + num);
    var val = title.value;
    if (val.length > 0) {
        text += val + " | ";
        isNull = false;
    } else {
        text += "N.A. | ";
    }

    var journal = document.getElementById("bibliographyJournal_" + num);
    var val = journal.value;
    if (val.length > 0) {
        text += val + " | ";
        isNull = false;
    } else {
        text += "N.A. | ";
    }

    var year = document.getElementById("bibliographyYear_" + num);
    var val = year.value;
    if (val.length > 0) {
        text += val + " | ";
        isNull = false;
    } else {
        text += "N.A. | ";
    }

    var volume = document.getElementById("bibliographyVolume_" + num);
    var val = volume.value;
    if (val.length > 0) {
        text += val + " | ";
        isNull = false;
    } else {
        text += "N.A. | ";
    }

    var part = document.getElementById("bibliographyPart_" + num);
    var val = part.value;
    if (val.length > 0) {
        text += val + " | ";
        isNull = false;
    } else {
        text += "N.A. | ";
    }

    var pages = document.getElementById("bibliographyPages_" + num);
    var val = pages.value;
    if (val.length > 0) {
        text += val + " | ";
        isNull = false;
    } else {
        text += "N.A. | ";
    }

    var link = document.getElementById("bibliographyLink_" + num);
    var val = link.value;
    if (val.length > 0) {
        text += val + " | ";
        isNull = false;
    } else {
        text += "N.A. | ";
    }

    var editor = document.getElementById("bibliographyEditor_" + num);
    var val = editor.value;
    if (val.length > 0) {
        text += val + " | ";
        isNull = false;
    } else {
        text += "N.A. | ";
    }

    var publisher = document.getElementById("bibliographyPublisher_" + num);
    var val = publisher.value;
    if (val.length > 0) {
        text += val + " | ";
        isNull = false;
    } else {
        text += "N.A. | ";
    }

    var city = document.getElementById("bibliographyCity_" + num);
    var val = city.value;
    if (val.length > 0) {
        text += val + " | ";
        isNull = false;
    } else {
        text += "N.A. | ";
    }

    if (isNull) {
        var modal = document.getElementById("bibliographyModal_" + num);
        modal.remove();
        //console.log("REM1", isNull);
    }

    var div = document.getElementById("reference_" + num);
    if (div === null) {
        div = document.createElement("div");
    }
    div.setAttribute("id", "reference_" + num);

    var deleteButton = document.getElementById("referenceDelete_" + num);
    var reference = document.getElementById("bibliographyReference_" + num);
    var editButton = document.getElementById("referenceEdit_" + num);
    if (deleteButton === null) {
        deleteButton = document.createElement("button");
        deleteButton.setAttribute("type", "button");
        deleteButton.setAttribute("id", "referenceDelete_" + num);
        deleteButton.setAttribute("class", "deleteButton");
        //deleteButton.setAttribute("onclick", "console.log(\"before\");console.log(descriptionAndCondition[\"bibliography\"]);this.parentNode.remove();descriptionAndCondition[\"bibliography\"].splice(this.id.split('_').pop(),1);console.log(\"AFTER\");console.log(descriptionAndCondition[\"bibliography\"]);writeSliceFile();");
        deleteButton.setAttribute("onclick", "this.parentNode.remove();descriptionAndCondition[\"bibliography\"].splice(this.id.split('_').pop(),1);writeSliceFile();");
        var icon = createRoundDeleteSvg("deleteReference_" + num, "16", "16");
        deleteButton.appendChild(icon);
        div.appendChild(deleteButton);

        reference = document.createElement("span");
        //reference.setAttribute("type", "text");
        reference.setAttribute("value", text);
        reference.innerHTML = text;
        reference.setAttribute("size", "100");
        reference.setAttribute("id", "bibliographyReference_" + num);
        reference.setAttribute("name", "bibliographyReference_" + num);
        div.appendChild(reference);

        editButton = document.createElement("button");
        editButton.setAttribute("type", "button");
        editButton.setAttribute("id", "referenceEdit_" + num);
        editButton.setAttribute("class", "editButton");
        editButton.innerHTML = "Edit";
        editButton.setAttribute("onclick", "editReference(this.id);writeSliceFile();");
        div.appendChild(editButton);

        var bibliographyDiv = document.getElementById("bibliographyDiv");
        bibliographyDiv.appendChild(div);
    } else {
        reference.setAttribute("value", text);
        reference.innerHTML = text;
    }
}

window.closeBibliographyModal = function (id) {
    var num = id.split("_").pop();
    var reference = document.getElementById("bibliographyReference_" + num);
    var modal = document.getElementById("bibliographyModal_" + num);
    if (reference) {
        if (modal != null) {
            modal.style.display = "none";
        }
    } else {
        modal.remove();
    }
}

window.editReference = function (id) {
    var num = id.split("_").pop();
    //console.log("MODAL", num);
    var modal = document.getElementById("bibliographyModal_" + num);
    modal.style.display = "block";
}

window.addAuthorInBiblio = function (id) {
    var numRef = id.split("_").pop();
    var parent = document.getElementById("bibliographyAuthorsDiv_" + numRef);
    var addButton = document.getElementById("bibliographyAuthorAdd_" + numRef);
    var num = getNum(parent);

    var deleteButton = document.createElement("button");
    deleteButton.setAttribute("type", "button");
    deleteButton.setAttribute("id", "bibliographyAuthorDelete_" + numRef + "_" + num);
    deleteButton.setAttribute("class", "deleteButton");
    deleteButton.setAttribute(
        "onclick",
        "removeFieldInSliceFile(this.id,'bibliography','authors',this.nextSibling);this.nextSibling.remove();this.remove();writeSliceFile();"
    );
    var icon = createRoundDeleteSvg("deleteAuthor_" + num, "16", "16");
    deleteButton.appendChild(icon);

    var author = document.createElement("input");
    author.setAttribute("type", "text");
    author.setAttribute("list", "authorsDB");
    author.setAttribute("id", "bibliographyAuthor_" + numRef + "_" + num);
    author.setAttribute("name", "bibliographyAuthor_" + num);
    author.setAttribute("onchange", "updateSliceFile('bibliography',getPosAuthorRef(this.id),'author',this.value,this.id.split('_').pop());writeSliceFile();")

    addButton.before(deleteButton);
    addButton.before(author);
}

addSetup();