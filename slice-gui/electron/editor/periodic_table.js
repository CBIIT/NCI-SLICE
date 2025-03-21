window.selectMissingElement = function (div) {
    if (div.value == false) {
        Kekule.Widget.getWidgetById('periodicTable').deselectAll();
        var any = document.getElementById("periodicTable_AnyElement");
        var anyAli = document.getElementById("periodicTable_AnyAliphaticElement");
        var anyAro = document.getElementById("periodicTable_AnyAromaticElement");
        if (any.value == true) {
            any.value = false;
            any.setAttribute("style", "border: none;;filter: brightness(100%) contrast(100%)");
        }
        if (anyAli.value == true) {
            anyAli.value = false;
            anyAli.setAttribute("style", "border: none;;filter: brightness(100%) contrast(100%)");
        }
        if (anyAro.value == true) {
            anyAro.value = false;
            anyAro.setAttribute("style", "border: none;;filter: brightness(100%) contrast(100%)");
        }
        div.value = true;
        div.setAttribute("style", "border: 1px solid #000000;filter: brightness(145%) contrast(100%);");
    } else {
        div.value = false;
        div.setAttribute("style", "border: none;;filter: brightness(100%) contrast(100%)");
    }
}

window.createPeriodicTable = function (elementID) {
    var modal = document.getElementById("periodicTableModal");

    modal = document.createElement("div");
    modal.setAttribute("id", "periodicTableModal");
    modal.setAttribute("class", "modal");
    var parent = document.getElementById("sliceContainer");
    parent.appendChild(modal);

    var modalContent = document.createElement("div");
    modalContent.setAttribute("id", "periodicTableContent");
    modalContent.setAttribute("class", "modal-content");
    modal.appendChild(modalContent);

    var table = new Kekule.ChemWidget.PeriodicTable(modalContent);
    table.id = "periodicTable";
    table.setEnableSelect(true);
    table.setEnableMultiSelect(false);
    table.setUseMiniMode(true);
    table.setDisplayedComponents(['symbol', 'name', 'atomicNumber', 'legend']);

    var tableMissingElt = document.createElement("table");
    var tr = document.createElement("tr");
    tableMissingElt.appendChild(tr);
    var td1 = document.createElement("td");
    td1.setAttribute("class", "K-Chem-Periodic-Table-Elem-Cell");
    tr.appendChild(td1);

    var div1 = document.createElement("div");
    div1.setAttribute("id", "periodicTable_AnyElement");
    div1.setAttribute("class", "K-Chem-Periodic-Table-Elem-Cell-Content Actinides");
    div1.value = false;
    div1.setAttribute("onclick", "selectMissingElement(this)");
    td1.appendChild(div1);
    var span11 = document.createElement("span");
    span11.setAttribute("class", "K-Chem-Atomic-Num");
    span11.innerHTML = "997";
    var span12 = document.createElement("span");
    span12.setAttribute("class", "K-Chem-Elem-Symbol");
    span12.innerHTML = "*";
    var span13 = document.createElement("span");
    span13.setAttribute("class", "K-Chem-Elem-Name");
    span13.innerHTML = "Any Element";
    div1.appendChild(span11);
    div1.appendChild(span12);
    div1.appendChild(span13);

    var div2 = document.createElement("div");
    div2.setAttribute("id", "periodicTable_AnyAliphaticElement");
    div2.setAttribute("class", "K-Chem-Periodic-Table-Elem-Cell-Content Actinides");
    div2.value = false;
    div2.setAttribute("onclick", "selectMissingElement(this)");
    td1.appendChild(div2);
    var span21 = document.createElement("span");
    span21.setAttribute("class", "K-Chem-Atomic-Num");
    span21.innerHTML = "998";
    var span22 = document.createElement("span");
    span22.setAttribute("class", "K-Chem-Elem-Symbol");
    span22.innerHTML = "A";
    var span23 = document.createElement("span");
    span23.setAttribute("class", "K-Chem-Elem-Name");
    span23.innerHTML = "Any Aliphatic Element";
    div2.appendChild(span21);
    div2.appendChild(span22);
    div2.appendChild(span23);

    var div3 = document.createElement("div");
    div3.setAttribute("id", "periodicTable_AnyAromaticElement");
    div3.setAttribute("class", "K-Chem-Periodic-Table-Elem-Cell-Content Actinides");
    div3.value = false;
    div3.setAttribute("onclick", "selectMissingElement(this)");
    td1.appendChild(div3);
    var span31 = document.createElement("span");
    span31.setAttribute("class", "K-Chem-Atomic-Num");
    span31.innerHTML = "999";
    var span32 = document.createElement("span");
    span32.setAttribute("class", "K-Chem-Elem-Symbol");
    span32.innerHTML = "a";
    var span33 = document.createElement("span");
    span33.setAttribute("class", "K-Chem-Elem-Name");
    span33.innerHTML = "Any Aromatic Element";
    div3.appendChild(span31);
    div3.appendChild(span32);
    div3.appendChild(span33);

    modalContent.appendChild(tableMissingElt);

    modalContent.appendChild(document.createElement("br"));
    modalContent.appendChild(document.createElement("br"));

    var cancelButton = document.createElement("button");
    cancelButton.setAttribute("type", "button");
    cancelButton.setAttribute("id", "periodicTableModalOK");
    cancelButton.setAttribute("class", "cancelButton");
    cancelButton.setAttribute("style", "float:right");
    cancelButton.innerHTML = "cancel";
    cancelButton.setAttribute(
        "onclick",
        "this.parentNode.parentNode.remove();"
    );
    modalContent.appendChild(cancelButton);

    var okButton = document.createElement("button");
    okButton.setAttribute("type", "button");
    okButton.setAttribute("id", "periodicTableModalOK-" + elementID);
    okButton.setAttribute("class", "okButton");
    okButton.setAttribute("style", "float:right");
    okButton.innerHTML = "OK";
    okButton.setAttribute(
        "onclick",
        "savePeriodicElement(this);"
    );
    modalContent.appendChild(okButton);

    modal.style.display = "block";

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.remove();
        }
    };
}

window.savePeriodicElement = function (button) {
    var buttonID = button.id;
    var id = buttonID.split('-').pop();
    var elt = document.getElementById(id);
    var symbol = Kekule.Widget.getWidgetById('periodicTable').getSelectedSymbol();
    if (symbol == null) {
        var any = document.getElementById("periodicTable_AnyElement");
        var anyAli = document.getElementById("periodicTable_AnyAliphaticElement");
        var anyAro = document.getElementById("periodicTable_AnyAromaticElement");
        if (any.value == true) {
            symbol = "*";
            elt.setAttribute("value", symbol);
            elt.innerHTML = symbol;
            var chemElemProperty = document.getElementById(id.split("_symbol")[0] + "_aromaticityProp");
            chemElemProperty.innerHTML = "either";
        }
        if (anyAli.value == true) {
            symbol = "A";
            elt.setAttribute("value", symbol);
            elt.innerHTML = symbol;
            var chemElemProperty = document.getElementById(id.split("_symbol")[0] + "_aromaticityProp");
            chemElemProperty.innerHTML = "aliphatic";
        }
        if (anyAro.value == true) {
            symbol = "a";
            elt.setAttribute("value", symbol);
            elt.innerHTML = symbol;
            var chemElemProperty = document.getElementById(id.split("_symbol")[0] + "_aromaticityProp");
            chemElemProperty.innerHTML = "aromatic";
        }
    } else {
        elt.setAttribute("value", symbol);
        elt.innerHTML = symbol;
    }
    button.parentNode.parentNode.remove();
    console.log(elt.id);
    if (elt.id.includes("Specific")) {
        storePropertyInArray(elt, false);

    } else {
        storePropertyInArray(elt, true);
    }
}