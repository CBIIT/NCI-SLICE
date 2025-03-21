window.updateSliceFile = function (category, property, property2, value, index) {
    //console.log(category, property, property2, value,descriptionAndCondition);
    if (isNumeric(property)) {
        var dict = {};
        if (descriptionAndCondition[category][property]) {
            dict = descriptionAndCondition[category][property];
        }
        if (property2 === "author") {
            if (authorsDB.find(name => name === value)) {
                authorsDB.push(value);
                toAddToAuthorsDB.push(value);
            }
            var list = [];
            if (dict["authors"]) {
                list = dict["authors"];
            }
            list[parseInt(index)] = value;
            dict['authors'] = list;
        } else if (property2 === "conditions" || property2 === "supInfo" || property2 === "time" || property2 === "temperature" || property2 === "solvent" || property2 === "reagent") {
            var list = [];
            if (dict[property2]) {
                list = dict[property2];
            }
            list[parseInt(index)] = value;
            dict[property2] = list;
        } else {
            dict[property2] = value;
        }
        if (property2 === "journal") {
            if (journalsDB.find(name => name === value)) {
                journalsDB.push(value);
                toAddToJournalsDB.push(value);
            }
        } else if (property2 === "reagent") {
            if (reagentsDB.find(name => name === value)) {
                reagentsDB.push(value);
                toAddToReagentsDB.push(value);
            }
        } else if (property2 === "solvent") {
            if (solventsDB.find(name => name === value)) {
                solventsDB.push(value);
                toAddToSolventsDB.push(value);
            }
        }
        descriptionAndCondition[category][property] = dict;
    } else {
        descriptionAndCondition[category][property] = value;
    }
    //console.log(property,property2,isNumeric(property),descriptionAndCondition);
    writeSliceFile();
}

window.writeSliceFile = function () {
    //console.log(descriptionAndCondition);
    var xml = "<transforms>";

    var transformDict = descriptionAndCondition["transform"];
    xml += '<transform id="'
    if (transformDict["id"]) {
        xml += transformDict["id"];
    }
    xml += '" ';
    xml += 'name="'
    if (transformDict["name"]) {
        xml += transformDict["name"];
    }
    xml += '" ';
    xml += 'version="'
    if (transformDict["version"]) {
        xml += transformDict["version"];
    }
    xml += '">';

    var historyDict = descriptionAndCondition["history"];
    xml += "<history>";
    var i = 0;
    for (const [index, value] of Object.entries(historyDict)) {
        xml += '<modification id="' + i;
        var type = value["type"];
        var date = value["date"];
        var authors = value["authors"];
        xml += '" ';
        xml += 'type="'
        if (type) {
            xml += type;
        }
        xml += '" ';
        xml += 'date="'
        if (date) {
            xml += date;
        }
        xml += '" >';
        xml += '<authors>';
        if (authors) {
            for (var j = 0; j < authors.length; j++) {
                xml += '<author name="' + authors[j] + '"/>';
            }
        }
        xml += '</authors>';
        xml += "</modification>";
        i++;
    }
    xml += "</history>";


    var historyDict = descriptionAndCondition["setup"];
    var yieldValue = historyDict["yield"];
    var reliability = historyDict["reliability"];
    var reputation = historyDict["reputation"];
    var homoselectivity = historyDict["homoselectivity"];
    var heteroselectivity = historyDict["heteroselectivity"];
    var orientationalSelectivity = historyDict["orientationalSelectivity"];
    var conditionSelectivity = historyDict["conditionSelectivity"];
    var thermodynamics = historyDict["thermodynamics"];
    xml += "<setup>";

    xml += "<yield>";
    xml += '"' + yieldValue + '"';
    xml += "</yield>";

    xml += "<reliability>";
    xml += '"' + reliability + '"';
    xml += "</reliability>";

    xml += "<reputation>";
    xml += '"' + reputation + '"';
    xml += "</reputation>";

    xml += "<homoselectivity>";
    xml += '"' + homoselectivity + '"';
    xml += "</homoselectivity>";

    xml += "<heteroselectivity>";
    xml += '"' + heteroselectivity + '"';
    xml += "</heteroselectivity>";


    xml += "<orientationalSelectivity>";
    xml += '"' + orientationalSelectivity + '"';
    xml += "</orientationalSelectivity>";

    xml += "<conditionFlexibility>";
    xml += '"' + conditionSelectivity + '"';
    xml += "</conditionFlexibility>";

    xml += "<thermodynamics>";
    xml += '"' + thermodynamics + '"';
    xml += "</thermodynamics>";

    xml += "</setup>";


    var biblioDict = descriptionAndCondition["bibliography"];
    xml += "<bibliography>";
    i = 0;
    for (const [index, value] of Object.entries(biblioDict)) {
        xml += '<reference id="' + i + '">';
        var authors = value["authors"];
        var title = value["title"];
        var journal = value["journal"];
        var year = value["year"];
        var volume = value["volume"];
        var part = value["part"];
        var pages = value["pages"];
        var link = value["link"];
        var editor = value["editor"];
        var publisher = value["publisher"];
        var city = value["city"];

        xml += '<authors>';
        if (authors) {
            for (var j = 0; j < authors.length; j++) {
                xml += '<author name="' + authors[j] + '"/>';
            }
        }
        xml += '</authors>';

        xml += "<title>";
        xml += '"' + title + '"';
        xml += "</title>";

        xml += "<journal>";
        xml += '"' + journal + '"';
        xml += "</journal>";

        xml += "<year>";
        xml += year;
        xml += "</year>";

        xml += "<volume>";
        xml += '"' + volume + '"';
        xml += "</volume>";

        xml += "<part>";
        xml += '"' + part + '"';
        xml += "</part>";

        xml += "<pages>";
        xml += '"' + pages + '"';
        xml += "</pages>";

        xml += "<link>";
        xml += '"' + link + '"';
        xml += "</link>";

        xml += "<editor>";
        xml += '"' + editor + '"';
        xml += "</editor>";

        xml += "<publisher>";
        xml += '"' + publisher + '"';
        xml += "</publisher>";

        xml += "<city>";
        xml += '"' + city + '"';
        xml += "</city>";

        xml += '</reference>';

        i++;
    }
    xml += "</bibliography>";

    var commentDict = descriptionAndCondition["comments"];
    var comment = commentDict["comment"];
    xml += "<comment>";
    if (comment) {
        xml += '"' + comment + '"';
    }
    xml += "</comment>";

    var conditionDict = descriptionAndCondition["conditions"];
    xml += "<conditions>";
    i = 0;
    for (const [index, value] of Object.entries(conditionDict)) {
        var name = value["name"];
        var step = value["step"];
        var reagent = value["reagent"];
        var solvent = value["solvent"];
        var temperature = value["temperature"];
        var time = value["time"];
        var supInfo = value["supInfo"];

        if (!name) {
            xml += '<condition id="' + i + '">';
        } else {
            xml += '<condition id="' + i + '" name="' + name + '">';
        }

        xml += '<step>';
        if (step) {
            xml += step + '"';
        }
        xml += '</step>';

        xml += '<reagents>';
        if (reagent) {
            for (var j = 0; j < reagent.length; j++) {
                xml += '<reagent id="' + j + '">"' + reagent[j] + '"</reagent>';
            }
        }
        xml += '</reagents>';

        xml += '<solvents>';
        if (solvent) {
            for (var j = 0; j < solvent.length; j++) {
                xml += '<solvent id="' + j + '">"' + solvent[j] + '"</solvent>';
            }
        }
        xml += '</solvents>';

        xml += '<temperatures>';
        if (temperature) {
            for (var j = 0; j < temperature.length; j++) {
                xml += '<temperature id="' + j + '">"' + temperature[j] + '"</temperature>';
            }
        }
        xml += '</temperatures>';

        xml += '<times>';
        if (time) {
            for (var j = 0; j < time.length; j++) {
                xml += '<time id="' + j + '">"' + time[j] + '"</time>';
            }
        }
        xml += '</times>';

        xml += '<informations>';
        if (supInfo) {
            for (var j = 0; j < supInfo.length; j++) {
                xml += '<information id="' + j + '">"' + supInfo[j] + '"</information>';
            }
        }
        xml += '</informations>';

        xml += '</condition>';

        i++;
    }
    xml += "</conditions>";

    xml += "<reactions>";
    for (var j = 0; j < reactions.length; j++) {
        xml += '<reaction id="' + j + '">';
        var reaction = reactions[j];

        // reactants
        xml += "<reactants>";
        var commonLogic = reaction.logic;
        for (var k = 0, l = reaction.getReactantCount(); k < l; ++k) {
            xml += '<reactant id="' + k + '">';
            var mol = reaction.getReactantAt(k);
            xml += '<smarts>"' + mol.smarts + '"</smarts>';
            xml += '<logic>"' + mol.logic + '"</logic>';
            xml += '</reactant>';
        }
        xml += "</reactants>";

        //agents
        xml += "<agents>";
        for (var k = 0, l = reaction.getSubstancesCount(); k < l; ++k) {
            xml += '<agent id="' + k + '">';
            var mol = reaction.getSubstanceAt(k);
            xml += '<smarts>"' + mol.smarts + '"</smarts>';
            xml += '<logic>"' + mol.logic + '"</logic>';
            xml += '</agent>';
        }
        xml += "</agents>";

        // products
        xml += "<products>";
        for (var k = 0, l = reaction.getProductCount(); k < l; ++k) {
            xml += '<product id="' + k + '">';
            var mol = reaction.getProductAt(k);
            xml += '<smarts>"' + mol.smarts + '"</smarts>';
            xml += '<logic>"' + mol.logic + '"</logic>';
            xml += '</product>';
        }
        xml += "</products>";
        xml += '</reaction>';
    }
    xml += "</reactions>";

    var trans = "</transform>";
    xml += trans;

    xml += "</transforms>";
    var beautifiedXmlText = new XmlBeautify().beautify(xml, {
        indent: "  ", //indent pattern like white spaces
        useSelfClosingElement: true //true:use self-closing element when empty element.
    });

    //console.log(document.getElementById("ChemEditorText"));
    document.getElementById("ChemEditorText").innerHTML = beautifiedXmlText;
    //console.log(beautifiedXmlText);
}

function getUniqueLogicStatement(reactionLogic, mol) {
    var molLogic = mol.logic;

    if (reactionLogic && molLogic) {

        if(molLogic.includes("define it as ghost molecule")){
            var molBlockList = getStatementBlocks(molLogic, mol);
            var logic = "";
            molBlockList.forEach(x => logic += x + "\n");
            return  logic;
        }
        else{
            var commonBlockList = getStatementBlocks(reactionLogic, mol);
            var molBlockList = getStatementBlocks(molLogic, mol);
            let difference = commonBlockList.filter(x => !molBlockList.includes(x));
            difference.push(...molBlockList);
            var combinedLogic = "";
            difference.forEach(x => combinedLogic += x + "\n");
            return combinedLogic;
        }
           
    } else if (reactionLogic && !molLogic) {
        var commonBlockList = getStatementBlocks(reactionLogic, mol);
        var logic = "";
        commonBlockList.forEach(x => logic += x + "\n");
        return  logic;
    } else if (!reactionLogic && molLogic) {
        var molBlockList = getStatementBlocks(molLogic, mol);
        var logic = "";
        molBlockList.forEach(x => logic += x + "\n");
        return  logic;
    } else if (!reactionLogic && !molLogic) {
        return reactionLogic;
    }
   
}

//presort logic by associated only the block which apply to a molecule
function getStatementBlocks(logic, mol) {
    var atomMapings = getAtomMappingNumbers(mol);
    var list = logic.split("\n");
    var blockList = [];
    var bracket_counter = 0;
    var setContent = {};
    var isItComaptible = null;
    var statements = "";
    //console.log(logic);
    //console.log("SPLIT:", list);
    const indent = "   ";
    var bracketPattern = /then\s+{/;
    for (var i = 0, l = list.length; i < l; i++) {
        var instruction = list[i];
        var instruction2 = instruction.trim();
        if (instruction2.startsWith("if ")) {
            if (!bracketPattern.test(instruction2)) {
                statements += indent.repeat(bracket_counter) + instruction2+ "\n";
            } else {
                statements += indent.repeat(bracket_counter) + instruction2 + "\n";
                bracket_counter++;
            }
        } else if (instruction2.startsWith("else if ")) {
            if (!bracketPattern.test(instruction2)) {
                statements += indent.repeat(bracket_counter) + instruction2+ "\n";
            } else {
                statements += indent.repeat(bracket_counter) + instruction2 + "\n";
                bracket_counter++;
            }
        } else if (instruction2.startsWith("else ")) {
            if (!instruction2.includes("{")) {
                statements += indent.repeat(bracket_counter) + instruction2 + "\n";
            } else {
                statements += indent.repeat(bracket_counter) + instruction2 + "\n";
                bracket_counter++;
            }
        } else if (instruction2.startsWith("function ")) {
            statements += indent.repeat(bracket_counter) + instruction2 + "\n";
            bracket_counter++;
        } else if (instruction2.startsWith("foreach")) {
            statements += indent.repeat(bracket_counter) + instruction2 + "\n";
            bracket_counter++;
            //test if set is not empty
            const pattern = /defined\sas(\s+\w+)+\s+(in)(\s+)(\w+)/g
            const array = [...instruction2.matchAll(pattern)];
            if (array.length > 0) {
                if (array[0].length > 0) {
                    const setName = array[0].pop().trim();
                    if (setName in setContent) {
                        //check if it's a set neame (molecule for instance is not a set name)
                        if (setContent[setName]) {
                            if (setContent[setName].length == 0) {
                                isItComaptible = false;
                            }
                        }
                    }
                }
            }
        } else if (instruction2.includes("}")) {
            if (bracket_counter > 0)
                bracket_counter--;
            statements += indent.repeat(bracket_counter) + instruction2+ "\n";
        }else {
            breakOtherStmt: {
                if (instruction2.length > 0) {
                    //get set name if the statement is a set
                    const pattern3 = /put(\s+\w+)+\s+(into)\s+\w+/g
                    if (pattern3.test(instruction2))
                    {
                        var splitName = instruction2.split(/into\s+/g);
                        var content = splitName[0].split(/put\s+/g)[1].trim();
                        var setName = splitName[1].trim();
                        const pattern = /atom\s\d+/g
                        const array = [...content.matchAll(pattern)];
                        var areAllAtomInMol = true;
                        for (var j = 0, m = array.length; j < m; j++) {
                            var arr = array[j];
                            var number = parseInt(arr[0].split(/\s/)[1]);
                            if (!atomMapings.includes(number)) {
                                areAllAtomInMol = false
                            }
                        }
                        var arr = [];
                        if (setName in setContent) {
                            arr = setContent[setName];
                        }
                        if (areAllAtomInMol) {
                            arr.push(content);
                        }
                        setContent[setName] = arr;
                    }
                    //empty out set
                    const pattern4 = /empty\s+\w+/g
                    if (pattern4.test(instruction2))
                    {
                        var splitName = instruction2.split(/empty\s+/g);
                        var setName = splitName[1].trim();
                        if (setName in setContent) {
                            if (setContent[setName].length == 0) {
                                break breakOtherStmt;
                            }
                        }
                    }
                    //remove lemet from set
                    const pattern5 = /remove(\s+\w+)+\s+(from)\s+\w+/g
                    if (pattern5.test(instruction2))
                    {
                        var splitName = instruction2.split(/from\s+/g);
                        var content = splitName[0].split(/remove\s+/g)[1].trim();
                        var setName = splitName[1].trim();
                        var arr = [];
                        if (setName in setContent) {
                            arr = setContent[setName];
                        }
                        const index = arr.indexOf(content);
                        if (index > -1) {
                            arr.splice(index, 1);
                        }
                        setContent[setName] = arr;
                    }
                    //console.log(bracket_counter, instruction2);
                    statements += indent.repeat(bracket_counter) + instruction2 + "\n";
                }
            }
        }
        //statements += "\n";
        //console.log(bracket_counter, l, instruction);
        if (bracket_counter == 0) {
            if (statements.length > 0) {
                //console.log("STATEMENT ",statements);
                // get atom mapping and test if they reffer to the current molecule
                const pattern = /atom\s\d+/g;
                const array = [...statements.matchAll(pattern)];
                //console.log(pattern.test(statements));
                var areAllAtomInMol = true;
                for (var j = 0, m = array.length; j < m; j++) {
                    var arr = array[j];
                    var number = parseInt(arr[0].split(/\s/)[1]);
                    if (!atomMapings.includes(number)) {
                        areAllAtomInMol = false;
                    }
                }
                //check statements where FG is used with the wrong relation and subject if if molecule is acetal group
               /* const pattern2 = /(molecule|bond|group|ring)+(\s+\w+)*\s+(is|are)(\s+\w+\s+)+(group)/g;
                //return true if uncompatible statements
                if (!pattern2.test(statements) && isItComaptible == null)
                {
                    isItComaptible = true;
                }
                else {
                    isItComaptible = false;
                }*/
                isItComaptible=true;
                // check if a set is being iterated
                if (areAllAtomInMol && isItComaptible)
                    blockList.push(statements);

                isItComaptible = null;
            }
            statements = "";
        }
        //console.log(bracket_counter, statements);
    }
    //console.log("blockList ",blockList);
    return blockList;
}

function getAtomMappingNumbers(mol) {
    var list = [];
    for (var i = 0, l = mol.getNodeCount(); i < l; i++) {
        var node = mol.getNodeAt(i);
        if (node.atomMappingNumber > 0)
            list.push(node.atomMappingNumber);
    }
    return list;
}

window.removeFieldInSliceFile = function (id, property, field, input) {
    var index = getPosAuthorRef(id);
    var array = descriptionAndCondition[property][index][field];
    array.splice(array.indexOf(input.value), 1);
    descriptionAndCondition[property][index][field] = array;
}