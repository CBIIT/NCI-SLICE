// preload.js
// Toutes les APIs Node.js sont disponibles dans le processus de préchargement.
// Il a la même sandbox qu'une extension Chrome.
window.addEventListener('DOMContentLoaded', () => {
  const replaceText = (selector, text) => {
    const element = document.getElementById(selector)
    if (element) element.innerText = text
  }

  for (const dependency of ['chrome', 'node', 'electron']) {
    replaceText(`${dependency}-version`, process.versions[dependency])
  }
})

const {contextBridge,ipcRenderer} = require("electron");
//contextBridge.exposeInMainWorld("electron", require('electron'));
//contextBridge.exposeInMainWorld("dialog2", dialog);
var currentFilePath = null;
const {dialog} = require("@electron/remote");
contextBridge.exposeInMainWorld('electronApi', {
  openFileDialog: async (title, filters) => {
    const response = await dialog.showOpenDialog({      title,      filters,      properties: ['openFile'],    });    return response.filePaths;
  },
  saveFileDialog: async (title, filters) => {
    const response = await dialog.showSaveDialog({      title,      filters,      properties: ['createDirectory', 'showOverwriteConfirmation'],    });    return response;
  },
  getWindow: (currentFilePath) => {
    ipcRenderer.send('getWindow',{currentFilePath});
  },
  getcurrentFilePath: () => {
     return currentFilePath;
  }
});

ipcRenderer.on('getcurrentFilePath', function (event,arg) {

  currentFilePath  = arg;
 });

contextBridge.exposeInMainWorld('fs',require("fs"));
contextBridge.exposeInMainWorld('path',require("path"));
contextBridge.exposeInMainWorld('spawn',require('node:child_process'));
contextBridge.exposeInMainWorld('exec',require('child_process').exec);
contextBridge.exposeInMainWorld('electronAPI',{
  getAppPath: () => ipcRenderer.invoke('get-app-path')
});
