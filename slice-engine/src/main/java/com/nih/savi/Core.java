/**
 * 
 */
package com.nih.savi;

import static org.openscience.cdk.DefaultChemObjectBuilder.getInstance;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IReaction;
import org.openscience.cdk.isomorphism.Pattern;
import org.openscience.cdk.smarts.SmartsPattern;
import org.openscience.cdk.smiles.SmiFlavor;
import org.openscience.cdk.smiles.SmilesGenerator;
import org.openscience.cdk.smiles.SmilesParser;

import com.nih.parser.ChemicalFormatParser;
import com.nih.slice.Slice;
import com.nih.slice.SliceTester;
import com.nih.smirks.Transformer;
import com.nih.tools.ColouredSystemOutPrintln;

/**
 * This class is the entry class for parsing building blocks files, processing  SLICE chemistries and generating molecules
 * It's called by the CLI, but can also be executed for testing purposes
 * The variables (with the exception of numOfCores) defined below must be adapted if this class is used for testing purposes.
 * @author delanneevc
 *
 */
public class Core {


	/*private static int numOfCores = 1024;
	private static String outputPath = "";
	private static String outputName = "results";
	private static String bbPath = "";
	private static String sliceFilepath = "";
	private static String sliceChemistryFolder = "";
	private static String lowerRating= "desactivated";
	private static String ghostMolecules = "activated";
	private static String inchikey= "desactivated";*/

	private static int numOfCores = 1024;
	private static String outputPath = "/Users/noulehoilemos2/Desktop/SAMPLE/";
	private static String outputName = "results_TF_2201_V10";
	private static String bbPath = "/Users/noulehoilemos2/Desktop/SAMPLE/2201_set_BBS.txt";
	private static String sliceFilepath = "/Users/noulehoilemos2/Desktop/SAMPLE/TF_2201_v10.jslice";
	private static String sliceChemistryFolder = "/Users/noulehoilemos2/Desktop/SAMPLE/TF_2201_v10.jslice";
	private static String lowerRating= "desactivated";
	private static String ghostMolecules = "activated";
	private static String inchikey= "desactivated";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*try {
			int[][] numberBBS = countBBS();
		} catch (CDKException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		/*try {
			int[][] numberBBS = countBBS_matching();
		} catch (CDKException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		exec();
		
		/*try {
			correctMol();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	/**
	 *
	 *
	 *counts number of compatibles BBS for each reactant of the transform
	 * @return
	 * @throws CDKException
	 */
	public static int [][] countBBS() throws CDKException {
		int[][] numberBBS = null;
		if (numOfCores == -1)
			numOfCores = Runtime.getRuntime().availableProcessors();
		System.out.println("Number of cores: "+numOfCores);
		
		//parse the building block csv file
		CSVParser csvParser = new CSVParser();
		csvParser.setRemoveHeader(true);
		//index of SMILES is 0 and building block ID is 1
		List<String[]> buildingBlocks = csvParser.read(bbPath, new String[] {"SMILES", "STRID"});
		
		//class for testing the pattern and the logic
		SliceTester sliceTester = new SliceTester();

		Map<Integer, Slice> transforms = null;
		ChemicalFormatParser parser = new ChemicalFormatParser();
		String format;
		try {
			
			format = parser.formatDetector(sliceChemistryFolder);
			if (format == null) {
				System.out.println(ColouredSystemOutPrintln.ANSI_RED + "Wrong file format" + ColouredSystemOutPrintln.ANSI_RESET);
				return null;
			}
			else if (format.contains("sliceJSON")) {
				try {
					transforms = parser.parseJSlice(sliceChemistryFolder);
				} catch (CDKException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (format.contains("sliceXML")) {
				try {
					transforms = parser.parseSliceXML(sliceChemistryFolder);
				} catch (CDKException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			numberBBS= sliceTester.countbuildingblocks(transforms,bbPath,outputPath,outputName );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return numberBBS;
		
	}
	/**
	 *
	 *
	 *counts number of compatibles BBS for each reactant of the transform
	 * @return
	 * @throws CDKException
	 */
	public static int [][] countBBS_matching() throws CDKException {
		int[][] numberBBS = null;
		if (numOfCores == -1)
			numOfCores = Runtime.getRuntime().availableProcessors();
		System.out.println("Number of cores: "+numOfCores);

		//parse the building block csv file
		CSVParser csvParser = new CSVParser();
		csvParser.setRemoveHeader(true);
		//index of SMILES is 0 and building block ID is 1
		List<String[]> buildingBlocks = csvParser.read(bbPath, new String[] {"SMILES", "STRID"});

		//class for testing the pattern and the logic
		SliceTester sliceTester = new SliceTester();

		Map<Integer, Slice> transforms = null;
		ChemicalFormatParser parser = new ChemicalFormatParser();
		String format;
		try {

			format = parser.formatDetector(sliceChemistryFolder);
			if (format == null) {
				System.out.println(ColouredSystemOutPrintln.ANSI_RED + "Wrong file format" + ColouredSystemOutPrintln.ANSI_RESET);
				return null;
			}
			else if (format.contains("sliceJSON")) {
				try {
					transforms = parser.parseJSlice(sliceChemistryFolder);
				} catch (CDKException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (format.contains("sliceXML")) {
				try {
					transforms = parser.parseSliceXML(sliceChemistryFolder);
				} catch (CDKException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			numberBBS= sliceTester.countbuildingblocks_pattern(transforms,bbPath,outputPath,outputName );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return numberBBS;

	}

	/**
	 * ENTRY point for parsing building blocks files, processing  SLICE chemistries and generating molecules
	 */
	public static void exec() {

        try {
            countBBS();
        } catch (CDKException e) {
            throw new RuntimeException(e);
        }


		Map<Integer, Slice> transforms = null;
		ChemicalFormatParser parser1 = new ChemicalFormatParser();
		String format1;
		try {

			format1 = parser1.formatDetector(sliceChemistryFolder);
			if (format1 == null) {
				System.out.println(ColouredSystemOutPrintln.ANSI_RED + "Wrong file format" + ColouredSystemOutPrintln.ANSI_RESET);
			}
			else if (format1.contains("sliceJSON")) {
				try {
					transforms = parser1.parseJSlice(sliceChemistryFolder);
				} catch (CDKException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (format1.contains("sliceXML")) {
				try {
					transforms = parser1.parseSliceXML(sliceChemistryFolder);
				} catch (CDKException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int transform_id1 = 0;
		for (Integer key : transforms.keySet() )
			transform_id1 = key;
		String file = "_all_compatible_BBS_"+outputName+".txt";

		String BB_path_name= outputPath+transform_id1+ file;

        //Get he number of available CPUs

		if (numOfCores == -1)
			numOfCores = Runtime.getRuntime().availableProcessors();
		System.out.println("Number of cores: "+numOfCores);
		
		//parse the building block csv file
		CSVParser csvParser = new CSVParser();
		csvParser.setRemoveHeader(true);
		//index of SMILES is 0 and building block ID is 1
		List<String[]> buildingBlocks = csvParser.read(BB_path_name, new String[] {"SMILES", "STRID"});
		
		//class for testing the pattern and the logic
		SliceTester sliceTester = new SliceTester();
		//uncomment for having pattern centered on a carbon atom (look at SliceGroup class for definitions)
		//scm.setCarbonCentered(true);
		long startTime = System.nanoTime();
		if (new File(sliceChemistryFolder).isFile()) {
			System.out.println("Chemistry filename: " + sliceChemistryFolder);
			  //get compatible building block with the current chemistry
			  //the Structure is {ReactionID: {MoleculeID: [compatibleBuildingBlock1,compatibleBuildingBlock2,...compatibleBuildingBlockm]}}
			  //Reaction ID is the id attributed to each reaction contains in the slice chemistry file (jslice or XML)
			  //Molecule ID is the id attributed to each molecule part of a reaction (ie reactant, or agent, or product) contains in the slice chemistry file (jslice or XML)
			  Map<String, Map<String, List<IAtomContainer>>> compatibleMolecules = getCompatibleMolecules(buildingBlocks, sliceChemistryFolder.toString(), sliceTester, 0, 1,lowerRating);


			  //Map<String, Map<String, List<IAtomContainer>>> compatibleMolecules = sliceTester.moleculesCompatibilitiesWithTransform(sliceChemistryFiles[i].toString(), buildingBlocks, 0);
			  Map<String,String> subtransformSmirks = sliceTester.getReactionSmirks();
				//print the smirks associated to the reactions contains in the  slice chemistry file (jslice or XML)
				//reactionID:SMIRKS
				System.out.println("subtransformSmirks: "+subtransformSmirks);
				
				System.out.println("Number of cores: "+numOfCores);
				
				//for (Entry<String, Map<String, List<IAtomContainer>>> e : compatibleMolecules.entrySet()) 
				List<String> ghost_molecules= new ArrayList<>();
				try {
					ghost_molecules = sliceTester.print_ghost_molecules(sliceChemistryFolder);
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("ghost: "+ghost_molecules);
				
				//get the transform id and version xx
				
				Map<Integer, Slice> sliceTransforms = null;
				ChemicalFormatParser parser = new ChemicalFormatParser();
				 //detect format and parse it if it's a slice compatible format
				 String format;
				try {
					format = parser.formatDetector(sliceFilepath);
					if (format == null) {
						System.out.println(ColouredSystemOutPrintln.ANSI_BG_GREEN + "Wrong file format" + ColouredSystemOutPrintln.ANSI_RESET);
					}
					else if (format.contains("sliceJSON")) {
						sliceTransforms = parser.parseJSlice(sliceFilepath);
					}
					else if (format.contains("sliceXML")) {
						sliceTransforms = parser.parseSliceXML(sliceFilepath);
					}
				} catch (IOException | CDKException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Integer transform_id = null;
				Integer transform_version = null;
				String transform_name = null;
				for (Integer key : sliceTransforms.keySet() )
					transform_id = key;
				transform_version = sliceTransforms.get(transform_id).getVersion();
				transform_name = sliceTransforms.get(transform_id).getName();
				//System.out.println("id :"+sliceTransforms.get(transform_id).getName() +" version:"+ sliceTransforms.get(transform_id).getVersion());
				
				
				//generate molecules
				for (Entry<String, Map<String, List<IAtomContainer>>> e : compatibleMolecules.entrySet()) {
					String reactionID = e.getKey();
					MoleculeMaker molMaker = new MoleculeMaker(subtransformSmirks.get(reactionID));
					molMaker.setThreads((numOfCores-1)*25);
					molMaker.setOutputFile(outputPath+outputName+"_"+reactionID+".csv");
					molMaker.setOutputFileSDF(outputPath+outputName+"_"+reactionID+".sdf");
					molMaker.setCalculateProperties(false);
					molMaker.setTransformation_id(transform_id);
					molMaker.setTransform_version(transform_version);
					molMaker.setTransform_name(transform_name);
					try {
						molMaker.setTransform_main_product(sliceTester.product_tranform(sliceChemistryFolder));
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//molMaker.x(e.getValue(),ghost_molecules);
					molMaker.makeMolecules(e.getValue());
				}
				long elapsedTime = System.nanoTime() - startTime;
			      
		        System.out.println("Total execution time in Java in millis: "
		                + elapsedTime/1000000);
		        System.out.println("Total execution time in Java in minutes: "
		                + (elapsedTime/1000000)/60000);
		        

				startTime = System.nanoTime();
				try {
					sliceTester.checkProducts(outputPath,outputName,sliceChemistryFolder,lowerRating,ghostMolecules,inchikey);
				} catch (CloneNotSupportedException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


				long elapsedTime2 = System.nanoTime() - startTime;

				System.out.println("checkProducts\n Total execution time to check logic on products in millis: "
						+ elapsedTime2/1000000);
				System.out.println("Total execution time to check logic on products in minutes: "
						+ (elapsedTime2/1000000)/60000);
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath+outputName+"_computation.data"));
					writer.write("Total execution time in Java in millis: " + elapsedTime/1000000);
					writer.write("\n Total execution time in Java in minutes: " + (elapsedTime/1000000)/60000);
					writer.write("\ncheckProducts and generate output file \n Total execution time to check logic/generate output file on products in millis: "+ elapsedTime2/1000000);
					writer.write("\nTotal execution time to check logic/generate output file on products in minutes: " + (elapsedTime2/1000000)/60000);
					writer.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}

        } else {
			
			File[] sliceChemistryFiles = new File(sliceChemistryFolder).listFiles();
			for (int i = 0; i < sliceChemistryFiles.length; i++) {
			  if (sliceChemistryFiles[i].isFile()) {
				  System.out.println("Chemistry filename: " + sliceChemistryFiles[i]);
				  //get compatible building block with the current chemistry
				  //the Structure is {ReactionID: {MoleculeID: [compatibleBuildingBlock1,compatibleBuildingBlock2,...compatibleBuildingBlockm]}}
				  //Reaction ID is the id attributed to each reaction contains in the slice chemistry file (jslice or XML)
				  //Molecule ID is the id attributed to each molecule part of a reaction (iereactant, or agent, or product) contains in the slice chemistry file (jslice or XML)
				  Map<String, Map<String, List<IAtomContainer>>> compatibleMolecules = getCompatibleMolecules(buildingBlocks, sliceChemistryFiles[i].toString(), sliceTester, 0, 1,lowerRating);
				  System.out.println("compatibles :"+ compatibleMolecules.size());
				  //Map<String, Map<String, List<IAtomContainer>>> compatibleMolecules = sliceTester.moleculesCompatibilitiesWithTransform(sliceChemistryFiles[i].toString(), buildingBlocks, 0);
				  Map<String,String> subtransformSmirks = sliceTester.getReactionSmirks();
					//print the smikrs associated to the reactions contains in the  slice chemistry file (jslice or XML)
					//reactionID:SMIRKS
					System.out.println("subtransformSmirks: "+subtransformSmirks);
					
					System.out.println("Number of cores: "+numOfCores);
					
					//for (Entry<String, Map<String, List<IAtomContainer>>> e : compatibleMolecules.entrySet()) 
					List<String> ghost_molecules= new ArrayList<>();
					try {
						ghost_molecules = sliceTester.print_ghost_molecules(sliceChemistryFolder);
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("ghost: "+ghost_molecules);
					//generate molecules
					for (Entry<String, Map<String, List<IAtomContainer>>> e : compatibleMolecules.entrySet()) {
						String reactionID = e.getKey();
						MoleculeMaker molMaker = new MoleculeMaker(subtransformSmirks.get(reactionID));
						molMaker.setThreads((numOfCores-1)*25);
						molMaker.setOutputFile(outputPath+outputName+"_"+reactionID+".csv");
						molMaker.setCalculateProperties(false);
						try {
							molMaker.setTransform_main_product(sliceTester.product_tranform(sliceChemistryFolder));
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//molMaker.x(e.getValue(),ghost_molecules);
						molMaker.makeMolecules(e.getValue());
					}
					
					long elapsedTime = System.nanoTime() - startTime;
				      
			        System.out.println("Total execution time in Java in millis: "
			                + elapsedTime/1000000);
			        System.out.println("Total execution time in Java in minutes: "
			                + (elapsedTime/1000000)/60000);

			        startTime = System.nanoTime();
					try {
					sliceTester.checkProducts(outputPath,outputName,sliceChemistryFolder,lowerRating,ghostMolecules,inchikey);
					} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					} catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                  elapsedTime = System.nanoTime() - startTime;
				      
			        System.out.println("Total execution products time in Java in millis: "
			                + elapsedTime/1000000);
			        System.out.println("Total execution time in Java in minutes: "
			                + (elapsedTime/1000000)/60000);
					
			  } 
			  else if (sliceChemistryFiles[i].isDirectory()) {
			    //
			  }
			}
		}

	}
	
	/**
	 * @param buildingBlocks
	 * @param sliceChemistryFilePath
	 * @param sliceTester
	 * @param smilesColumnIndex
	 * @param idIColumnIndex
	 * @return
	 */
	private static Map<String, Map<String, List<IAtomContainer>>> getCompatibleMolecules(List<String[]> buildingBlocks, 
			String sliceChemistryFilePath, SliceTester sliceTester, int smilesColumnIndex, int idIColumnIndex, String lowerRating) {
		List<CompletableFuture<Map<String, Map<String, List<IAtomContainer>>>>> futures = new ArrayList<CompletableFuture<Map<String, Map<String, List<IAtomContainer>>>>>();
		  int threads = (numOfCores-1)*25;
		  int targetPerThread = buildingBlocks.size() > threads ? (int) (buildingBlocks.size()/threads) : buildingBlocks.size();
		  int cpt = 0;
		  List<String[]> buildingBlockSubset = new ArrayList<String[]>();
		  for (int i = 0; i < buildingBlocks.size(); i++) {
			  buildingBlockSubset.add(buildingBlocks.get(i));
			  cpt++;
			  if (cpt == targetPerThread) {
				  List<String[]> temp = new ArrayList<String[]>(buildingBlockSubset);
				  CompletableFuture<Map<String, Map<String, List<IAtomContainer>>>> future = CompletableFuture.supplyAsync(() -> {
					try {
						return sliceTester.moleculesCompatibilitiesWithTransform(sliceChemistryFilePath, temp, smilesColumnIndex, idIColumnIndex,lowerRating);
					} catch (CDKException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				});
					futures.add(future);
					buildingBlockSubset = new ArrayList<String[]>();
					cpt = 0;
			  }
		  }
		  if (cpt > 0) {
			  List<String[]> temp = new ArrayList<String[]>(buildingBlockSubset);
			  CompletableFuture<Map<String, Map<String, List<IAtomContainer>>>> future = CompletableFuture.supplyAsync(() -> {
				try {
					return sliceTester.moleculesCompatibilitiesWithTransform(sliceChemistryFilePath, temp, smilesColumnIndex, idIColumnIndex,lowerRating);
				} catch (CDKException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			});
				futures.add(future);
		  }
		  
		CompletableFuture<Object> combinedFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
	                .thenApplyAsync(aVoid -> futures.stream().
	                        map(future -> future.join()).
	                        collect(Collectors.<Object>toList())
	                        );

			List<Map<String, Map<String, List<IAtomContainer>>>> result = new ArrayList<Map<String, Map<String, List<IAtomContainer>>>>();
			try {
				result = (List<Map<String, Map<String, List<IAtomContainer>>>>) combinedFuture.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Map<String, Map<String, List<IAtomContainer>>> compatibleMolecules = new HashMap<String, Map<String, List<IAtomContainer>>>();
			for (Map<String, Map<String, List<IAtomContainer>>> map : result) {
				compatibleMolecules.putAll(map);
			}
			return compatibleMolecules;
	}
	
	/**
	 * this was intended for correcting some molecules. Not used anymore
	 * @throws IOException
	 */
	private static void correctMol() throws IOException {
		String sma1 = "[NH1+0-0:1][SH0+0-0:2](=[OH0+0-0:3])(=[NH0+0-0:4])[c:5]";
		String sma2 = "[NH2+0-0:1]=[SH0+0-0:2](=[OH0+0-0:3])([NH0+0-0:4])[c:5]";
		SmilesParser sp = new SmilesParser(getInstance());
		Pattern ptrn = SmartsPattern.create(sma2);
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath+outputName+"_2"+"_corrected.csv"));
		String header = "Products" +"\t" + "Reactants" + "\t" + "SMIRKS" + "\t" + "Score";
		writer.write(header);
		writer.newLine();
		try(BufferedReader in = new BufferedReader(new FileReader(outputPath+outputName+"_2"+".csv"))) {
		    String data;
		    boolean hCheck = false;
		    while ((data = in.readLine()) != null) {
		    	if (!hCheck) {
		    		hCheck = true;
		    		continue;
		    	}
		        String[] line = data.split("\t");
		        try {
					IReaction reaction = sp.parseReactionSmiles(line[2]);
					//query.getAtom(i) is mapped to target.getAtom(p[i]);
					IAtomContainer product = reaction.getProducts().getAtomContainer(0);
					int[] mapping = ptrn.match(product);
					if (mapping.length > 0) {
						product.getAtom(mapping[0]).setImplicitHydrogenCount(1);
						product.getAtom(mapping[3]).setImplicitHydrogenCount(2);
						Transformer transformer = new Transformer();
						String smi = transformer.makeSmiles(reaction, true);
						String[] split = smi.split(">");
						String newLine = split[split.length-1] + "\t" + split[0] + "\t" + smi;
						newLine += "\t" + line[3];
						writer.write(newLine);
						writer.newLine();
					}
					else {
						System.err.println("No match");
						//TODO redo reaction
					}
				} catch (InvalidSmilesException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CDKException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		writer.close();
	}

	public int getNumOfCores() {
		return numOfCores;
	}

	public void setNumOfCores(int numOfCores) {
		this.numOfCores = numOfCores;
	}

	public static String getOutputPath() {
		return outputPath;
	}

	public static void setOutputPath(String outputPath) {
		Core.outputPath = outputPath;
	}

	public static String getOutputName() {
		return outputName;
	}

	public static void setOutputName(String outputName) {
		Core.outputName = outputName;
	}

	public static String getBbPath() {
		return bbPath;
	}

	public static void setBbPath(String bbPath) {
		Core.bbPath = bbPath;
	}

	public static String getSliceFilepath() {
		return sliceFilepath;
	}

	public static void setSliceFilepath(String sliceFilepath) {
		Core.sliceFilepath = sliceFilepath;
	}

	public String getSliceChemistryFolder() {
		return sliceChemistryFolder;
	}

	public void setSliceChemistryFolder(String sliceChemistryFolder) {
		this.sliceChemistryFolder = sliceChemistryFolder;
	}
	
	public static String getLowerRating() {
		return lowerRating;
	}

	public static void setLowerRating(String lowerRating) {
		Core.lowerRating = lowerRating;
	}
	
	public static String getGhostMolecules() {
		return ghostMolecules;
	}

	public static void setGhostMolecules(String ghostMolecules) {
		Core.ghostMolecules = ghostMolecules;
	}

	public static String getInchikey() {
		return inchikey;
	}
	public static void setInchikey(String inchikey) {
		Core.inchikey = inchikey;
	}

	
}
