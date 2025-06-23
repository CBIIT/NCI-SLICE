window.loadFile2 = async function (evt) {
    var response = await electronApi.openFileDialog('Open SLICE Chemistry', [
        { name: 'slice', extensions: ['jslice'] }
      ]);
    if (response.length > 0) {
        refresh();
        currentFilePath = response[0];
        fileOnLoad = true;
        depictReactions = false;
        const rawData = await fs.readFileSync(currentFilePath, 'utf8');

        parseSliceFile(rawData);

        document.title = "SLICE Designer 1.3 - " + currentFilePath;
    
        fixBlocklyBug();
    }
}

window.saveAsFile2 = async function () {
    var response = await electronApi.saveFileDialog('Save SLICE Chemistry', [
        { name: 'slice', extensions: ['jslice'] }
      ]);
    if (!response.canceled) {
        currentFilePath = response.filePath;
        writeFile();
        document.title = "SLICE Designer 1.3 - " + currentFilePath;
    }
}

window.createProcedureBlock2 = async function () {
    var proceduresBlocks = workspace.getBlocksByType("procedures_defreturn");
    proceduresBlocks.push.apply(
        proceduresBlocks,
        workspace.getBlocksByType("procedures_defnoreturn")
    );
    //var allProcedures = Blockly.Procedures.allProcedures(workspace)[0];
    var dictProcedures = {};
    for (var i = 0, l = proceduresBlocks.length; i < l; i++) {
        var proc = proceduresBlocks[i];
        dictProcedures[proc.getFieldValue("NAME")] = proc;
    }
    const logic = chemObjectInUseByBlockly.logic;
    var lines = logic.split("\n");
    for (var i = 0, l = lines.length; i < l; i++) {
        var line = lines[i];
        if (line.includes("import")) {
            var newModuleName = null;
            var modulePath = null;
            var functionsToImport = null;
            if (line.includes(" as ")) {
                newModuleName = line.split(" as ").pop();
                line = line.substring(0, line.indexOf(" as "));
                //console.log(line);
            }
            if (line.includes("from")) {
                modulePath = line.split("from ").pop().split(" import ").shift();
                functionsToImport = line.split(" import ").pop().split(",");
            } else {
                modulePath = line.split("import ").pop();
            }
            //var libText = readTextFile2(modulePath);
            if (!modulePath.includes(".slicelib")) {
                console.err("The specified file is not a SLICE library");
                continue;
            }
            const libText = await fs.readFileSync(modulePath, 'utf8');
            var lines2 = libText.split("\n");
            for (var j = 0, m = lines2.length; j < m; j++) {
                var line2 = lines2[j];
                if (line2.includes("function ")) {
                    var functionName = line2.split("function ").pop().split("(").shift();
                    if (!proceduresNames.includes(functionName)) {
                        proceduresNames.push(functionName);
                        //console.log(functionName, proceduresNames, dictProcedures);
                        if (dictProcedures[functionName]) {
                            var proc = dictProcedures[functionName];
                            proc.setFieldValue("_" + functionName, "NAME");
                        }
                        if (functionsToImport != null) {
                            if (functionsToImport.includes(functionName)) {
                                var block = workspace.newBlock("procedures_defnoreturn");
                                block.initSvg();
                                block.setFieldValue(functionName, "NAME");
                            }
                        } else {
                            var block = workspace.newBlock("procedures_defnoreturn");
                            block.initSvg();
                            block.setFieldValue(functionName, "NAME");
                        }
                    }
                }
            }
        }
    }
};