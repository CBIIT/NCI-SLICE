# i-slice-engine
Our internal repository for the development of the SLICE Engine code
SLICE is based on expert knowledge used to define the rules for selection of synthesis blocks and the generation of products. This repository focus on the generation of products. 
SLICE is powered by ANTLR (ANother Tool for Language Recognition), which is a parser generator for reading, processing, executing, or translating structured text or binary files. CDK is the cheminformatics core of SLICE. It parses the query molecule(s)/reactions, manages the SMARTS patterns, and interprets SLICE logic. CDK will first match a SMARTS pattern with a query molecule.
If the pattern matches, the logic applied to the molecule is then checked. The logic is coded by a set of functions in JAVA, which use CDK to reason on a molecule. 

***********
**1.Run the JAR file directly (Easiest way):**

Folder out/artifacts/SLICE/SAVI-1.0.0-jar-with-dependencies.jar

***********

**2.Download and run the project on your computer**

***********

**A. Prerequisites to run the project if downloading :**

Before running the project, ensure the following are installed on your system:

- Java https://www.java.com/en/download/help/download_options.html
- Apache Maven https://maven.apache.org/install.html


***********

**B. Installation:**

1. Clone this repository to your local machine :
```
git clone 
cd i-slice-engine 
```
2. Verify that Maven and Java are properly installed:
```
mvn -version
java -version
```
***********

**C. Generation the .jar of the application:**

 Build the engine
   Use the following command to build the project and package it with the local profile:
   ```
   mvn clean package -P local
   ```
This command will;

- Clean previous builds
- Install dependencies
- Build the project with the local profile.

***********
**3. Run the application**
- Navigate to the target/ directory:
  ```
  cd target
  ```
-Run the JAR file :
 ```
usage: java -jar SAVI-1.0.0-jar-with-dependencies.jar [-b <arg>] [-s <arg>] [-o] [-n] [-c]
       [-l] [-g] [-v] [-i] [-h]
SAVI a la carte options
     -b,--buildingBlocks <arg>             buildingBLock in TSV file
     -s,--sliceRepository <arg>            Folder containing SLICE chemistries
     -o,--outputDirectory                  Output directory
     -n,--outputFileName                   output FileName of the files
                                           containing the generated molecules
     -c,--cpu                              number of CPUs
     -l,--replace lower rating with kill   replace lower rating with kill
     -g,--ghost molecules                  delete ghost molecules
     -v,--coompatiblebuildingBlocks        count the compatible building Blocks
     -i,--print products with inchikey     inchikey of products
     -h,--help                             Help page for command usage

  ```

- Example :
```
java -jar SAVI-1.0.0-jar-with-dependencies.jar -b BBs_Enamine-258k.tsv -s TF_7009_v8.jslice -v
  ```
This will count the compatible Building blocks from file BBs_Enamine-258k.tsv according to the logic define in TF_7009_v8.jslice and put them into new files. 

.jslice file is generated usign the i-slice-gui reposirtory;It is an open-source No-Code Development Platform (NCDP) that combines a molecule,
reaction, and pattern drawing editor with a SMARTS editor and a logic assistant for generating SLICE chemistry. 
