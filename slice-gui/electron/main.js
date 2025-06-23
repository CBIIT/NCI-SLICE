// Modules pour controler la vie de l'application et créer une fenêtre de navigation native
const { app, BrowserWindow,ipcMain} = require('electron')
const remote = require('@electron/remote/main')
const path = require('path')

remote.initialize();

function createWindow () {
  // Créer la fenêtre de navigation.
  const mainWindow = new BrowserWindow({
    width: 800,
    height: 600,
    icon: __dirname + '/Icon/Icon.icns',
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'),
      nodeIntegration: true, // is default value after Electron v5
      contextIsolation: true, // protect against prototype pollution
      enableRemoteModule: false // turn off remote
    }
  })

  remote.enable(mainWindow.webContents);

  child = new BrowserWindow({
    show: false
  });

  mainWindow.removeMenu();
  ipcMain.on("getWindow",(event, arg)=>{ 
    //const [ currentWindowX, currentWindowY ] = mainWindow.getPosition();
    let child= new BrowserWindow({
      width: 370,
      height: 350,
      show:false,
      x: 800,
      y: 320,
      fullscreenable: false,
      webPreferences: {
        preload: path.join(__dirname, 'preload.js'),
        nodeIntegration: true, // is default value after Electron v5
        contextIsolation: true, // protect against prototype pollution
        enableRemoteModule: false // turn off remote
      },
      parent:mainWindow})
      remote.enable(child.webContents);
      child.loadFile('generate.html');
      child.removeMenu();
      
      child.once('show',function(){
        child.webContents.send('getcurrentFilePath',arg);
     })
     child.once('ready-to-show', () => {
      child.show();
     });
    });
    


  // et charger l'index.html de l'application.
  mainWindow.loadFile('index.html')
  
  // Ouvrir les outils de développement.
  // mainWindow.webContents.openDevTools()
}

// Cette méthode sera appelée quand Electron aura fini
// de s'initialiser et sera prêt à créer des fenêtres de navigation.
// Certaines APIs peuvent être utilisées uniquement quant cet événement est émit.
app.whenReady().then(() => {
  createWindow()

  app.on('activate', function () {
    // Sur macOS il est d'usage de recréer une fenêtre dans l'application quand
    // l'icône du dock est cliquée et qu'il n'y a pas d'autre fenêtre ouverte.
    if (BrowserWindow.getAllWindows().length === 0) createWindow()
  })
});

// Quitter quand toutes les fenêtres sont fermées, sauf sur macOS. Sur macOS, il est courant
// pour les applications et leur barre de menu de rester actives jusqu’à ce que l’utilisateur quitte
// explicitement avec Cmd + Q.
app.on('window-all-closed', function () {
  if (process.platform !== 'darwin') app.quit()
});

ipcMain.handle('get-app-path',() =>{
  return app.getAppPath();
});

//app.handle('getWindow',()=> 'getWindow');
// Dans ce fichier, vous pouvez inclure le reste du code spécifique du
// processus principal de votre application. Vous pouvez également le mettre dans des fichiers séparés et les inclure ici.