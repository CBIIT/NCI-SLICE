# SLICE Designer
SLICE Designer is a platform for writing SLICE chemistries and for generating molecules. This repository does not contain the piece of code that allow to generate molecules (it is located in the repository slice-engine).

<picture>
  <source media="(prefers-color-scheme: dark)" srcset="https://github.com/victoriendelannee/slice-app/blob/main/electron/Icon-white/icon.png">
  <source media="(prefers-color-scheme: light)" srcset="https://github.com/victoriendelannee/slice-app/blob/main/electron/Icon-white/icon.png">
  <img alt="Shows an illustrated sun in light color mode and a moon with stars in dark color mode." src="https://user-images.githubusercontent.com/25423296/163456779-a8556205-d0a5-45e2-ac17-42d089e3c3f8.png">
</picture>


SLICE Designer is compatible with framework Electron.js -> https://www.electronjs.org/

***********

**General prerequisites:**

- NodeJS https://nodejs.org/en/download
- npm install blockly

***********

**Electron prequisites:**
- npm install --save-dev electron-packager
- npm install electron-forge
- npm install --save @electron/remote

***********

**Electron compilation to generate an excecutable file :**

Navigate to the electron / directory then for:
- MacOS: npm run package-mac
- Linux: npm run package-linux
- Windwos 32bit: npm run package-win32
- Windows 64bit: npm run package-win64

The excecutable file will be located in the folder named "release".
***********
