/*window.initBLockly = function() {
    var blocklyDiv = document.getElementById('blocklyDiv');
    var workspace = Blockly.inject(blocklyDiv,
        {toolbox: document.getElementById('toolbox')});
        console.log("INJECTED2");
    /*var onresize = function(e) {
      // Compute the absolute coordinates and dimensions of blocklyArea.
      var element = blocklyArea;
      var x = 0;
      var y = 0;
      do {
        x += element.offsetLeft;
        y += element.offsetTop;
        element = element.offsetParent;
      } while (element);
      // Position blocklyDiv over blocklyArea.
      blocklyDiv.style.left = x + 'px';
      blocklyDiv.style.top = y + 'px';
      blocklyDiv.style.width = blocklyArea.offsetWidth + 'px';
      blocklyDiv.style.height = blocklyArea.offsetHeight + 'px';
      Blockly.svgResize(workspace);
    };
    window.addEventListener('resize', onresize, false);
    onresize();
    Blockly.svgResize(workspace);*/
  
    /*function myUpdateFunction(event) {
      if (Object.keys(workspace.blockDB_).length >0) {
        var code = Blockly.SLICE.workspaceToCode(workspace);
      console.log(code);
      //document.getElementById('textarea').innerHTML = code;
      }
  }
  workspace.addChangeListener(myUpdateFunction);
}*/
