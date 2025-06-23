//close the window
window.ClosegenerateMolecules = function () {
    window.close();
}

//generate molecules usong .jar ile
window.generateMolecules = async function () {

    window.document.getElementById("buildingblock").innerHTML = "";
    const __dirname = path.resolve(path.dirname(''));
    var directory = __dirname.split('electron')[0]+"electron";
    directory = directory.replaceAll(' ', '\\ ');

    const appPath= await window.electronAPI.getAppPath();
    const path_test = `${appPath}/jar/SAVI-1.0.0-jar-with-dependencies.jar`;
    //const relativePath= 'java -jar '+ path_test +' -b '+currentbuildingblocks+ ' -s '+currentFilePath+ ' -v';
 

    var relativePath = 'java -jar '+path_test+' -b '+currentbuildingblocks+ ' -s '+currentFilePath;
    let rating =  window.document.getElementById("option1");
    if (rating.checked)
        relativePath +=' -l'; 
    let ghost =  window.document.getElementById("option2");
    if (ghost.checked)
        relativePath +=' -g'; 
    let inchiKey =  window.document.getElementById("option3");
    if (inchiKey.checked)
        relativePath +=' -i'; 
    console.log(relativePath);
    const childPorcess = exec(relativePath, {maxBuffer: 1024 * 800000}, function(err, stdout, stderr) {
    if (err) {
         console.log(err);
    }
    console.log(stdout);
    //console.log(stderr);
    //window.ClosegenerateMolecules();
    window.document.getElementById("buildingblock").innerHTML = "result saved!";
    })
   
   
}


//count de number of compatible BBs 
window.countCompatibleBB  = async function () {

    const __dirname = path.resolve(path.dirname(''));
    var directory = __dirname.split('electron')[0]+"electron";
    directory = directory.replaceAll(' ', '\\ ');
    const appPath= await window.electronAPI.getAppPath();
    const path_test = `${appPath}/jar/SAVI-1.0.0-jar-with-dependencies.jar`;
    const relativePath= 'java -jar '+ path_test +' -b '+currentbuildingblocks+ ' -s '+currentFilePath+ ' -v';
    console.log(relativePath);

    //const relativePath = 'java -jar '+directory+'/jar/SAVI-1.0.0-jar-with-dependencies.jar -b '+currentbuildingblocks+ ' -s '+currentFilePath+ ' -v';
    //console.log(relativePath);
    const childPorcess = exec(relativePath, {maxBuffer: 1024 * 800000}, function(err, stdout, stderr) {
    if (err) {
         console.log(err);
    }
    //console.log(stdout.split("countbuildingblocks")[1]);
    window.document.getElementById("buildingblock").innerHTML = stdout.split("countbuildingblocks")[1];
    })
    
}

//choose the file with the set of BBS
window.chooseFile = async function () {
    var response = await electronApi.getcurrentFilePath();
    window.currentFilePath= response.currentFilePath;
    var response = await electronApi.openFileDialog('Open Buidling blocks files', [
        { name: 'BBS', extensions: ['tsv','csv','txt'] }
      ]);
      if (response.length > 0) {
        currentbuildingblocks = response[0];
        const link= currentbuildingblocks.split("/");
        window.document.getElementById("filelink").innerHTML = link[link.length -1];
    }
    currentbuildingblocks = currentbuildingblocks.replaceAll(' ', '\\ ');
    currentFilePath = currentFilePath.replaceAll(' ', '\\ ');
}
