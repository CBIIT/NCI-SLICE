# SLICE (SMARTS and Logic In ChEmistry): Fast generation of molecules, using advanced chemical synthesis logic and modern coding style

This reposirtory contains 2 folders :


***********

**Examples:**

This folder contains four transforms TF 2201, TF 2875, TF 6005 and TF 7009

- BBS_Enamine_284k.tsv  : The set of Enamine BBS of 284K
- block1.tsv : target compound used for TF 7009
  
- For each transform XXXX, there is :

          - A TF_XXXX_Vj.jslice file needed to re-open in the SLICE Designer or to run it in the SLICE engine.
          - A TF_XXXX_Vj.slice file easily readable by user to see the chemistry
          - A XXXX_set_BBS_20.txt small set of compatibles Building blocks with 20 reactants 

- script_target_compound.sh : script file to generate molecules for a target compound. The command line to run it is :

  ./script_target_compound.sh block1.tsv BBS_Enamine_284k.tsv TF_7009_v8.jslice SAVI-1.0.0-jar-with-dependencies.jar 5000



***********

**SLICE-public:**

This repository contains SLICE-engine and SLICE-GUI with release to install. There is a README for each of them for more details.

  
