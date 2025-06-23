package com.nih.slice;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

//import org.antlr.v4.gui.TreeViewer;
import com.nih.tools.ElementCalculation;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.openscience.cdk.Atom;
import org.openscience.cdk.Bond;
import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.aromaticity.Aromaticity;
import org.openscience.cdk.aromaticity.ElectronDonation;
import org.openscience.cdk.atomtype.CDKAtomTypeMatcher;
import org.openscience.cdk.config.IsotopeFactory;
import org.openscience.cdk.config.Isotopes;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.graph.Cycles;
import org.openscience.cdk.inchi.InChIGenerator;
import org.openscience.cdk.inchi.InChIGeneratorFactory;
import org.openscience.cdk.interfaces.*;
import org.openscience.cdk.io.SDFWriter;
import org.openscience.cdk.isomorphism.Mappings;
import org.openscience.cdk.isomorphism.Pattern;
import org.openscience.cdk.isomorphism.matchers.QueryAtomContainer;
import org.openscience.cdk.layout.StructureDiagramGenerator;
import org.openscience.cdk.ringsearch.AllRingsFinder;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smarts.Smarts;
import org.openscience.cdk.smarts.SmartsPattern;
import org.openscience.cdk.smiles.SmiFlavor;
import org.openscience.cdk.smiles.SmilesGenerator;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.smiles2.SmilesParser2;
import org.openscience.cdk.tools.CDKHydrogenAdder;
import org.openscience.cdk.tools.manipulator.AtomContainerManipulator;

import com.nih.parser.ChemicalFormatParser;
import com.nih.slice.Slice.SliceMolecule;
import com.nih.slice.Slice.SliceReaction;
import com.nih.slice.cdk.SliceConstants;
import com.nih.slice.parser.SliceFacade;
import com.nih.slice.parser.SliceFileParser;
import com.nih.slice.parser.SliceLexer;
import com.nih.slice.parser.SliceParser;
import com.nih.slice.parser.Value;
import com.nih.smarts.SmartsPattern2;
import com.nih.smirks.Transformer;
import com.nih.tools.ColouredSystemOutPrintln;
import com.nih.tools.Tools;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import org.openscience.cdk.tools.manipulator.AtomTypeManipulator;

import static java.nio.file.Files.newBufferedWriter;

public class SliceTester {

	private static Boolean carbonCentered = null;
	private static Boolean isKilled = false;
	private static int rating = -1;
	private static String expectedResult = null;
	private static Map<String, String> reactionSmirks;

	public static void main(String[] args) throws IOException, CloneNotSupportedException, CDKException {


		//String testSet = "/Users/noulehoilemos2/Documents/1171/reaction.txt";
		//String slicePath = "/Users/noulehoilemos2/Documents/1171/TF_1171_v6.jslice";

		//String testSet = "/Users/noulehoilemos2/Documents/7009/reaction.txt";
		//String slicePath = "/Users/noulehoilemos2/Documents/7009/TF_7009_v8.jslice";

		//String testSet = "/Users/noulehoilemos2/Documents/7019/reaction.txt";
		//String slicePath = "/Users/noulehoilemos2/Documents/7019/TF_7019_v10.jslice";

		//String testSet = "/Users/noulehoilemos2/Documents/7061/reaction.txt";
		//String slicePath = "/Users/noulehoilemos2/Documents/7061/TF_7061_v16.jslice";

		//String testSet = "/Users/noulehoilemos2/Documents/6026/reaction_32.txt";
		//String testSet = "/Users/noulehoilemos2/Documents/6026/reaction.txt";

		//String slicePath = "/Users/noulehoilemos2/Documents/6026/TF_6026_v8.jslice";
		//String testSet = "/Users/noulehoilemos2/Documents/6026/reaction2.txt";

		//String testSet = "/Users/noulehoilemos2/Documents/6004/reaction.txt";
		//String slicePath = "/Users/noulehoilemos2/Documents/6004/TF_6004_v5.jslice";

		//String testSet = "/Users/noulehoilemos2/Documents/6005/reaction.txt";
		//String slicePath = "/Users/noulehoilemos2/Documents/6005/TF_6005_v8.jslice";
		//String slicePath = "/Users/noulehoilemos2/Documents/6005/version7/TF_6005_v8.jslice";

		// String testSet = "/Users/noulehoilemos2/Documents/6008/x.txt";
		//String slicePath = "/Users/noulehoilemos2/Documents/6008/TF_6008_v5.jslice";

		//String testSet = "/Users/noulehoilemos2/Documents/7056/7056_all_compatible.txt";
		//String slicePath = "/Users/noulehoilemos2/Documents/7056/TF_7056_v6.jslice";

		String testSet = "/Users/noulehoilemos2/Documents/7015/reaction.txt";
		String slicePath = "/Users/noulehoilemos2/Documents/7015/TF_7015_v8.jslice";

		//String testSet = "/Users/noulehoilemos2/Documents/7061/reaction.txt";
		//String slicePath = "/Users/noulehoilemos2/Documents/7061/TF_7061_v15.jslice";

		//String testSet = "/Users/noulehoilemos2/Documents/6008/reaction.txt";
		//String slicePath = "/Users/noulehoilemos2/Documents/6008/version6/TF_6008_v6.jslice";
		//String slicePath = "/Users/noulehoilemos2/Documents/6008/TF_6008_v7.jslice";

		//String slicePath = "/Users/noulehoilemos2/Documents/6022/TF_6022_v5.jslice";
		//String testSet = "/Users/noulehoilemos2/Documents/6022/reaction.txt";

		Map<Integer, Slice> transforms = null;
		ChemicalFormatParser parser = new ChemicalFormatParser();
		String format = parser.formatDetector(slicePath);
		if (format == null) {
			System.out.println(ColouredSystemOutPrintln.ANSI_RED + "Wrong file format" + ColouredSystemOutPrintln.ANSI_RESET);
			return;
		} else if (format.contains("sliceJSON")) {
			try {
				transforms = parser.parseJSlice(slicePath);
			} catch (CDKException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (format.contains("sliceXML")) {
			try {
				transforms = parser.parseSliceXML(slicePath);
			} catch (CDKException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//for just tree checking purposes
		 /*for (Slice sc : transforms.values()) {
				for (SliceReaction scr : sc.getReactions()) {
					for (SliceMolecule mol : scr.getProducts()) {
								if (mol.getLogic() != null) {
									System.out.println(mol.logic);
									ParseTree tree = getParseTreeFromString(mol.getLogic());
								}
								
					}
				}

			}*/
		//System.out.println(transforms.toString());
		testMoleculesWithTransform(transforms, testSet);
			/*int numOfCores = 1024;
			String outputPath = "/Users/noulehoilemos2/Documents/1171/";
			String outputName = "results_TF_1171_v6_1171_set_compatibles";
			String sliceChemistryFolder = "/Users/noulehoilemos2/Documents/1171/TF_1171_v6.jslice";
			String lowerRating= "desactivated";
			String ghostMolecules = "desactivated";
			String inchikey= "desactivated";
			checkProducts(outputPath,outputName,sliceChemistryFolder,lowerRating,ghostMolecules,inchikey);
		*/
		//classifybuildingblocks(transforms,testSet);
		//testMoleculesWithTransform("/Users/delanneevc/Documents/SAVI/SLICE/Transforms/SulfonimidamideSynthesis_0001.xml",
		//		 "/Users/delanneevc/Documents/SAVI/SLICE/Transforms/testTransformData/testSMILES_SulfonimidamideSynthesis_0001.csv");
		//TODO define aamDOne = true for SAVI a la carte compatible BB

	}

	/**
	 * test the compatibilities of a set of molecule and return a Map idReaction:idmatchedSMARTS:ListOfIAtomContainer
	 * with a specific SLICE
	 *
	 * @param sliceFilepath
	 * @param buildingBlocks
	 * @param smilesColumnIndex corresponding to the SMILES of the building blocks in the array buildingBlocks
	 * @throws CDKException
	 */
	public static Map<String, Map<String, List<IAtomContainer>>> moleculesCompatibilitiesWithTransform(String sliceFilepath, List<String[]> buildingBlocks, int smilesColumnIndex, int buildingBlockIdColumnIndex, String lowerRating) throws CDKException {
		Map<Integer, Slice> sliceTransforms = null;
		ChemicalFormatParser parser = new ChemicalFormatParser();
		//detect format and parse it if it's a slice compatible format
		String format;
		try {
			format = parser.formatDetector(sliceFilepath);
			if (format == null) {
				System.out.println(ColouredSystemOutPrintln.ANSI_RED + "Wrong file format" + ColouredSystemOutPrintln.ANSI_RESET);
				return null;
			} else if (format.contains("sliceJSON")) {
				sliceTransforms = parser.parseJSlice(sliceFilepath);
			} else if (format.contains("sliceXML")) {
				sliceTransforms = parser.parseSliceXML(sliceFilepath);
			}
		} catch (IOException | CDKException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			return null;
		}
		//get smirks of each subtransform
		getSubTranformSmirks(sliceTransforms);

		SmilesParser2 sp = new SmilesParser2(SilentChemObjectBuilder.getInstance());
		Map<String, Map<String, List<IAtomContainer>>> compatibleBuildingBlocksByReactionAndMolecules = new HashMap<String, Map<String, List<IAtomContainer>>>();
		for (String[] row : buildingBlocks) {
			String smiles = row[smilesColumnIndex];
			try {
				System.out.println(smiles);
				Integer reactantNumber = tryParseInt(row[row.length - 1]);
				//System.out.println("ReactantNumber: " + reactantNumber);
				IAtomContainer buildingBlock = sp.parseSmiles(smiles);
				buildingBlock.setProperty("emamineID", row[buildingBlockIdColumnIndex]);
				Map<String, List<IAtomContainer>> result = testCompatibility(buildingBlock, sliceTransforms, lowerRating,reactantNumber);
				for (Entry<String, List<IAtomContainer>> e1 : result.entrySet()) {
					String reactionID = e1.getKey();
					if (!compatibleBuildingBlocksByReactionAndMolecules.containsKey(reactionID))
						compatibleBuildingBlocksByReactionAndMolecules.put(reactionID, new HashMap<String, List<IAtomContainer>>());
					List<IAtomContainer> compatibleMappings = e1.getValue();
					for (IAtomContainer compatibleMapping : compatibleMappings) {
						String id = compatibleMapping.getProperty("compatibleBB");
						compatibleMapping.setProperty("emamineID", row[buildingBlockIdColumnIndex]);
						//System.out.println(reactionID + " "+ id);
						if (!compatibleBuildingBlocksByReactionAndMolecules.get(reactionID).containsKey(id)) {
							Map<String, List<IAtomContainer>> temp = compatibleBuildingBlocksByReactionAndMolecules.get(reactionID);
							List<IAtomContainer> temp2 = new ArrayList<IAtomContainer>();
							temp2.add(compatibleMapping);
							temp.put(id, temp2);
							compatibleBuildingBlocksByReactionAndMolecules.put(reactionID, temp);
						} else {
							Map<String, List<IAtomContainer>> temp = compatibleBuildingBlocksByReactionAndMolecules.get(reactionID);
							List<IAtomContainer> temp2 = temp.get(id);
							temp2.add(compatibleMapping);
							temp.put(id, temp2);
							compatibleBuildingBlocksByReactionAndMolecules.put(reactionID, temp);
						}
					}
				}
			} catch (InvalidSmilesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return compatibleBuildingBlocksByReactionAndMolecules;
	}

	/**
	 * {@link Deprecated}
	 * test the compatibilities of a set of molecule and return a Map idReaction:idmatchedSMARTS:ListOfIAtomContainer
	 * with a specific SLICE
	 *
	 * @param sliceFilepath
	 * @param data
	 * @param smilesColumnIndex
	 * @param buildingBlockIdColumnIndex
	 * @param outputFilePath
	 * @param name
	 * @throws CDKException
	 */
	public static void moleculesCompatibilitiesWithTransform(String sliceFilepath, List<String[]> data, int smilesColumnIndex,
															 int buildingBlockIdColumnIndex, String outputFilePath, String name, String lowerRating) throws CDKException {
		SmilesGenerator sg = new SmilesGenerator(SmiFlavor.Absolute);
		Map<String, Map<String, List<IAtomContainer>>> temp = moleculesCompatibilitiesWithTransform(sliceFilepath, data, smilesColumnIndex, buildingBlockIdColumnIndex, lowerRating);
		for (Entry<String, Map<String, List<IAtomContainer>>> e1 : temp.entrySet()) {
			String reactionID = e1.getKey();
			Map<String, List<IAtomContainer>> map = e1.getValue();
			int outputSize = map.keySet().size() * 2;
			// Writing to an in-memory byte array. This will be printed out to the standard output so you can easily see the result.
			ByteArrayOutputStream csvResult = new ByteArrayOutputStream();

			// CsvWriter (and all other file writers) work with an instance of java.io.Writer
			Writer outputWriter = new OutputStreamWriter(csvResult);

			CsvWriterSettings settings = new CsvWriterSettings();
			settings.getFormat().setDelimiter("\t");
			settings.getFormat().setLineSeparator("\n");

			// All you need is to create an instance of CsvWriter with the default CsvWriterSettings.
			// By default, only values that contain a field separator are enclosed within quotes.
			// If quotes are part of the value, they are escaped automatically as well.
			// Empty rows are discarded automatically.
			CsvWriter writer = new CsvWriter(outputWriter, new CsvWriterSettings());

			List<String> headers = new ArrayList<String>();
			List<Object[]> rows = new ArrayList<Object[]>();
			int rowIndex = 0;
			for (Entry<String, List<IAtomContainer>> e2 : map.entrySet()) {
				headers.add(e2.getKey());
				headers.add("rating");
				int listIndex = 0;
				for (IAtomContainer ac : e2.getValue()) {
					if (rowIndex == 0) {
						Object[] temp2 = new Object[outputSize];
						try {
							temp2[rowIndex] = sg.create(ac);
							temp2[rowIndex + 1] = ac.getProperty("rating");
						} catch (CDKException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
							temp2[rowIndex] = "NULL";
							temp2[rowIndex + 1] = "NULL";
						}

						rows.add(temp2);
					} else {
						Object[] temp2 = rows.get(listIndex);
						try {
							temp2[rowIndex] = sg.create(ac);
							temp2[rowIndex + 1] = ac.getProperty("rating");
						} catch (CDKException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
							temp2[rowIndex] = "NULL";
							temp2[rowIndex + 1] = "NULL";
						}
						rows.add(temp2);
					}
					listIndex++;
				}
				rowIndex++;
			}

			// Write the record headers of this file
			writer.writeHeaders(headers);

			// Here we just tell the writer to write everything and close the given output Writer instance.
			writer.writeRowsAndClose(rows);
		}
	}

	/**
	 * Used for debug purposes
	 * test a set of molecules (CSV file column 0 = smiles and column 1 = kill+reason or score (which means success))
	 * with a specific SLICE
	 * file example
	 * C=O kill
	 * BrCC 10
	 *
	 * @param transforms
	 * @param smilesFilepath
	 * @throws CDKException
	 */
	public static void testMoleculesWithTransform(Map<Integer, Slice> transforms, String smilesFilepath) throws CDKException {
		SmilesParser2 sp = new SmilesParser2(SilentChemObjectBuilder.getInstance());
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(smilesFilepath));
			reader.readLine();
			String line = reader.readLine();
			while (line != null) {
				//System.out.println("LINE " +line);
				String[] elts = line.split("\t");
				String smiles = elts[0];
				//ignore commented line
				if (smiles.charAt(0) != '#') {
					expectedResult = elts[1];
					try {


						String smi = null;
						IAtomContainer ac = sp.parseSmiles(smiles);
						try {
							//true false true
							smi = Tools.makeSmiles(ac.clone(), true, false, true);
						} catch (CDKException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (CloneNotSupportedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						IAtomContainer ac1;
						try {
							ac1 = sp.parseSmiles(smi);
							BBCompatibilities(ac1, transforms);

						} catch (InvalidSmilesException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} catch (InvalidSmilesException | CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Used for classify building blocks compatible to reactants
	 * test a set of molecules (enamine BBS 180k SMILE AND STRID)
	 *
	 * @param smilesFilepath
	 * @param smilesFilepath
	 * @throws CDKException
	 */
	public static void classifybuildingblocks(Map<Integer, Slice> transforms, String smilesFilepath) throws CDKException {
		SmilesParser2 sp = new SmilesParser2(SilentChemObjectBuilder.getInstance());
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(smilesFilepath));
			reader.readLine();
			reader.readLine();
			String line = reader.readLine();
			int total_bb = 0;
			while (line != null) {
				String[] elts = line.split("\t");
				String smiles = elts[0];
				//ignore commented line
				if (smiles.charAt(0) != '#') {
					try {
						Create_BBCompatibilities(sp.parseSmiles(smiles), transforms, line);
					} catch (InvalidSmilesException | CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// read next line
				total_bb++;
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Used for classify building blocks compatible to reactants
	 * test a set of molecules (enamine BBS 180k SMILE AND STRID)
	 *
	 * @param outputPath
	 * @param smilesFilepath
	 * @throws CDKException
	 */
	public static int[][] countbuildingblocks(Map<Integer, Slice> transforms, String smilesFilepath, String outputPath, String outputName) throws CDKException {


		int transform_id = 0;
		for (Integer key : transforms.keySet())
			transform_id = key;
		int[][] number_BBS = null;
		SmilesParser2 sp = new SmilesParser2(SilentChemObjectBuilder.getInstance());
		BufferedReader reader;
		int count_reaction = 0, count_reactant = 0;
		for (Slice sc : transforms.values()) {
			count_reaction = 0;
			for (SliceReaction scr : sc.getReactions()) {
				for (SliceMolecule mol : scr.getReactants()) {
					String file_name = outputPath + transform_id + "_" + "compatibles_BB_" + count_reaction + "_" + count_reactant +"_"+outputName+".txt";
					//System.out.println("File "+file_name);
					try {
						boolean result = Files.deleteIfExists(Paths.get(file_name));

					} catch (IOException e) {
						e.printStackTrace();
					}
					count_reactant++;
				}
				count_reaction++;
			}
		}
		try {
			reader = new BufferedReader(new FileReader(smilesFilepath));
			reader.readLine();
			String line = reader.readLine();
			while (line != null) {
				//System.out.println("LINE : " +line);
				String[] elts = line.split("\t");
				String reactant_number = elts[elts.length -1];
				String smiles = elts[0];
				//ignore commented line
				if (smiles.charAt(0) != '#') {
					try {
						IAtomContainer ac1 = sp.parseSmiles(smiles);
						AtomContainerManipulator.convertImplicitToExplicitHydrogens(ac1);

						try {
							Count_BBCompatibilities(ac1, transforms, line, outputPath, transform_id,outputName,reactant_number);

						} catch (InvalidSmilesException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}


					} catch (InvalidSmilesException | CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// read next line
				line = reader.readLine();
			}
			reader.close();
			count_reaction = 0;
			for (Slice sc : transforms.values()) {
				for (SliceReaction scr : sc.getReactions()) {
					count_reaction++;
				}
			}
			number_BBS = new int[count_reaction][];
			count_reaction = 0;
			System.out.println("countbuildingblocks");
			for (Slice sc : transforms.values()) {
				for (SliceReaction scr : sc.getReactions()) {
					number_BBS[count_reaction] = new int[scr.getReactants().size()];
					count_reactant = 0;
					for (SliceMolecule mol : scr.getReactants()) {
						String file_name = outputPath + transform_id + "_" + "compatibles_BB_" + count_reaction + "_" + count_reactant + "_"+outputName+".txt";

						System.out.print("Reactant " + (count_reactant + 1) + ": " + countLineBufferedReader(file_name) + "; ");
						//System.out.println("FILE : " +file_name);
						number_BBS[count_reaction][count_reactant] = countLineBufferedReader(file_name);
						count_reactant++;
					}
					count_reaction++;
				}
			}
			create_all_compatibles_file(transforms, smilesFilepath, outputPath,outputName);


		} catch (IOException e) {
			e.printStackTrace();
		}

		return number_BBS;
	}
	//create a file with all compatibles BBS for all reactants
	/**
	 * Used for classify building blocks compatible to reactants - without logic -just pattern matching
	 * test a set of molecules (enamine BBS 180k SMILE AND STRID)
	 *
	 * @param outputPath
	 * @param smilesFilepath
	 * @throws CDKException
	 */
	public static int[][] countbuildingblocks_pattern(Map<Integer, Slice> transforms, String smilesFilepath, String outputPath, String outputName) throws CDKException {


		int transform_id = 0;
		for (Integer key : transforms.keySet())
			transform_id = key;
		int[][] number_BBS = null;
		SmilesParser2 sp = new SmilesParser2(SilentChemObjectBuilder.getInstance());
		BufferedReader reader;
		int count_reaction = 0, count_reactant = 0;
		for (Slice sc : transforms.values()) {
			count_reaction = 0;
			for (SliceReaction scr : sc.getReactions()) {
				for (SliceMolecule mol : scr.getReactants()) {
					String file_name = outputPath + transform_id + "_" + "compatibles_pattern_BB_" + count_reaction + "_" + count_reactant +"_"+outputName+".txt";
					//System.out.println("File "+file_name);
					try {
						boolean result = Files.deleteIfExists(Paths.get(file_name));

					} catch (IOException e) {
						e.printStackTrace();
					}
					count_reactant++;
				}
				count_reaction++;
			}
		}
		try {
			reader = new BufferedReader(new FileReader(smilesFilepath));
			reader.readLine();
			String line = reader.readLine();
			while (line != null) {
				//System.out.println("LINE : " +line);
				String[] elts = line.split("\t");
				String reactant_number = elts[elts.length -1];
				String smiles = elts[0];
				//ignore commented line
				if (smiles.charAt(0) != '#') {
					try {
						IAtomContainer ac1 = sp.parseSmiles(smiles);
						AtomContainerManipulator.convertImplicitToExplicitHydrogens(ac1);

						try {
							Count_BBCompatibilities_pattern(ac1, transforms, line, outputPath, transform_id,outputName,reactant_number);

						} catch (InvalidSmilesException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}


					} catch (InvalidSmilesException | CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// read next line
				line = reader.readLine();
			}
			reader.close();
			count_reaction = 0;
			for (Slice sc : transforms.values()) {
				for (SliceReaction scr : sc.getReactions()) {
					count_reaction++;
				}
			}
			number_BBS = new int[count_reaction][];
			count_reaction = 0;
			System.out.println("countbuildingblocks");
			for (Slice sc : transforms.values()) {
				for (SliceReaction scr : sc.getReactions()) {
					number_BBS[count_reaction] = new int[scr.getReactants().size()];
					count_reactant = 0;
					for (SliceMolecule mol : scr.getReactants()) {
						String file_name = outputPath + transform_id + "_" + "compatibles_pattern_BB_" + count_reaction + "_" + count_reactant + "_"+outputName+".txt";

						System.out.print("Reactant " + (count_reactant + 1) + ": " + countLineBufferedReader(file_name) + "; ");
						//System.out.println("FILE : " +file_name);
						number_BBS[count_reaction][count_reactant] = countLineBufferedReader(file_name);
						count_reactant++;
					}
					count_reaction++;
				}
			}


		} catch (IOException e) {
			e.printStackTrace();
		}

		return number_BBS;
	}
	//create a file with all compatibles BBS for all reactants

	public static void create_all_compatibles_file(Map<Integer, Slice> transforms, String smilesFilepath, String outputPath, String outputName) {
		int transform_id = 0;
		for (Integer key : transforms.keySet())
			transform_id = key;
		String file = "_all_compatible_BBS_"+outputName+".txt";
		String output_name = outputPath + transform_id + file;

		Path filePath = Paths.get(output_name);

		if (Files.exists(filePath)) {
			try {
				Files.delete(filePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			BufferedWriter writer_file = newBufferedWriter(Paths.get(output_name));
			writer_file.write("SMILES\tSTRID");
			writer_file.newLine();
			int count_reactant;
			int count_reaction = 0;
			for (Slice sc : transforms.values()) {
				for (SliceReaction scr : sc.getReactions()) {
					count_reactant = 0;
					for (SliceMolecule mol : scr.getReactants()) {

						String file_name = outputPath + transform_id + "_" + "compatibles_BB_" + count_reaction + "_" + count_reactant +"_"+outputName+".txt";

						Files.lines(Paths.get(file_name)).forEach(line -> writeLine(writer_file, line));
						count_reactant++;

					}
					count_reaction++;
				}
			}
			writer_file.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public static void writeLine(BufferedWriter writer, String line) {
		try {
			writer.write(line);
			writer.newLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	//count number of line of a file
	public static int countLineBufferedReader(String fileName) {

		int lines = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			while (reader.readLine() != null) lines++;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;

	}

	/**
	 * {@link Deprecated}
	 * test a set of molecules (CSV file column 0 = smiles and column 1 = kill+reason or score (which means success))
	 * with a specific SLICE
	 *
	 * @param sliceFilepath
	 * @param smilesFilepath
	 * @throws CDKException
	 */
	public static void testMoleculesWithTransform(String sliceFilepath, String smilesFilepath) throws CDKException {
		SliceFileParser scp = new SliceFileParser();
		Map<Integer, Slice> slice = scp.parse(sliceFilepath);
		SmilesParser2 sp = new SmilesParser2(SilentChemObjectBuilder.getInstance());
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(smilesFilepath));
			String line = reader.readLine();
			while (line != null) {
				System.out.println("LINE " + line);
				String[] elts = line.split("\t");
				String smiles = elts[0];
				//ignore commented line
				if (smiles.charAt(0) != '#') {
					expectedResult = elts[1];
					try {
						BBCompatibilities(sp.parseSmiles(smiles), slice);
					} catch (InvalidSmilesException | CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@link Deprecated}
	 *
	 * @param sliceFilepath
	 * @param smilesFilepath
	 * @throws CloneNotSupportedException
	 * @throws CDKException
	 */
	public void process(String sliceFilepath, String smilesFilepath) throws CloneNotSupportedException, CDKException {
		Map<String, List<List<Map<Integer, Integer>>>> res = new HashMap<String, List<List<Map<Integer, Integer>>>>();
		//parse XML
		SliceFileParser scp = new SliceFileParser();
		Map<Integer, Slice> slice = scp.parse(sliceFilepath);
		SmilesParser2 sp = new SmilesParser2(SilentChemObjectBuilder.getInstance());
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(smilesFilepath));
			String line = reader.readLine();
			while (line != null) {
				String[] elts = line.split("\t");
				BBCompatibilities(sp.parseSmiles(elts[0]), slice);
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@link Deprecated}
	 *
	 * @param sliceFilepath
	 * @param reactionSmilesFilepath
	 * @throws CloneNotSupportedException
	 * @throws CDKException
	 */
	public static void processTest(String sliceFilepath, String reactionSmilesFilepath) throws CloneNotSupportedException, CDKException {
		//parse XML
		SliceFileParser scp = new SliceFileParser();
		Map<Integer, Slice> allSlice = scp.parse(sliceFilepath);
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(reactionSmilesFilepath));
			String line = reader.readLine();
			int cpt = 0;
			while (line != null) {
				System.out.println(line);
				String[] elts = line.split("\t");
				ChemicalFormatParser cfp = new ChemicalFormatParser();
				System.out.println("cpt " + cpt + " " + elts[1]);
				IReaction reaction = cfp.parseReactionSMILES(elts[1].replace("\"", ""), true).getReaction(0);
				for (IAtomContainer reactant : reaction.getReactants().atomContainers())
					BBCompatibilities(reactant, allSlice);
				// read next line
				line = reader.readLine();
				cpt++;
				if (cpt > 1)
					break;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param grammarFilepath
	 * @return
	 * @throws IOException
	 */
	private ParseTree getParseTreeFromFile(String grammarFilepath) throws IOException {
		String filepath = "/home/delanneev/Documents/SAVI_scripts/testGrammar/testG1.txt";
		SliceLexer sliceLexer = new SliceLexer(CharStreams.fromFileName(grammarFilepath));
		CommonTokenStream commonTokenStream = new CommonTokenStream(sliceLexer);
		SliceParser sliceParser = new SliceParser(commonTokenStream);

		ParseTree tree = sliceParser.instructions();
		//System.out.println(tree.toStringTree());
		return tree;
	}

	/**
	 * generate the ParseTree. Can be displayed using the commented part
	 *
	 * @param logic
	 * @return ParseTree
	 */


	private static ParseTree getParseTreeFromString(String logic) {
		SliceLexer sliceLexer = new SliceLexer(CharStreams.fromString(logic));
		CommonTokenStream commonTokenStream = new CommonTokenStream(sliceLexer);
		SliceParser sliceParser = new SliceParser(commonTokenStream);

		ParseTree tree = sliceParser.instructions();

		//uncomment to display tree
	       /*TreeViewer viewer = new TreeViewer(Arrays.asList(
	        		sliceParser.getRuleNames()),tree);
	        viewer.setScale(1.5); // Scale a little
	        viewer.open();
	        try {
				Thread.currentThread().sleep(100000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		//System.out.println(tree.toStringTree());
		return tree;
	}


	public static void checkProducts(String outputPath, String outputName, String sliceFilepath, String lowerRating, String ghostMolecules, String inchikey) throws CloneNotSupportedException, IOException {

		//List<String> ghost_molecules= new ArrayList<>(); 
		//ghost_molecules = print_ghost_molecules(sliceFilepath);

		System.out.println("outfile file :" + outputPath + outputName + "_0.csv"); // file with products
		//write sdf files of products
		//System.out.println("outputPath"+ outputPath+outputName);
		//System.out.println("outputName"+ outputName);
		BufferedReader reader_id = new BufferedReader(new FileReader((outputPath + outputName).split("\\.")[0].concat("_0.id")));
		BufferedWriter outputsdf = new BufferedWriter(new FileWriter((outputPath + outputName).split("\\.")[0].concat(".sdf")));
		SDFWriter writer_sdf_file = new SDFWriter(outputsdf);
		SmilesGenerator smilesGenerator = new SmilesGenerator();

		// check the logic of products
		Map<Integer, Slice> sliceTransforms = null;
		ChemicalFormatParser parser = new ChemicalFormatParser();
		//detect format and parse it if it's a slice compatible format
		String format;
		try {
			format = parser.formatDetector(sliceFilepath);
			if (format == null) {
				System.out.println(ColouredSystemOutPrintln.ANSI_RED + "Wrong file format" + ColouredSystemOutPrintln.ANSI_RESET);
			} else if (format.contains("sliceJSON")) {
				sliceTransforms = parser.parseJSlice(sliceFilepath);
			} else if (format.contains("sliceXML")) {
				sliceTransforms = parser.parseSliceXML(sliceFilepath);
			}
		} catch (IOException | CDKException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		String smile;
		SmilesParser2 sp = new SmilesParser2(SilentChemObjectBuilder.getInstance());
		String final_line;


		int new_rating;
		BufferedReader reader;
		BufferedWriter outputFile;
		BufferedWriter outputFile2;
		int index = 0;
		try {
			String activated = new String("activated");
			if (ghostMolecules.equals(activated))
				outputFile = new BufferedWriter(new FileWriter(outputPath + outputName + "_no_ghost.csv"));
			else
				outputFile = new BufferedWriter(new FileWriter(outputPath + outputName + "_with_ghost.csv"));

			reader = new BufferedReader(new FileReader(outputPath + outputName + "_0.csv"));
			//outputFile = new BufferedWriter(new FileWriter(outputPath+outputName+".csv")); 
			outputFile2 = new BufferedWriter(new FileWriter(outputPath + outputName + "_delete.csv"));
			String header = reader.readLine();
			String line = reader.readLine();
			String id_product = reader_id.readLine();
			outputFile.write(header);
			outputFile.newLine();
			List<String> list_products = new ArrayList<>();
			while (line != null) {
				//System.out.println(index + "line :"+line);
				index++;
				new_rating = 0;
				final_line = "";
				smile = line.split("\t")[0]; //products
				String[] products = smile.split("\\.");
				Boolean isFound = null;
				for (String product : products) {
					isKilled = null;
					isFound = false;
					try {
						boolean prod_smiles = false;
						// TEST
						try {
							sp.parseSmiles(product);
							prod_smiles = true;
						} catch (InvalidSmilesException e) {
							prod_smiles = false;
						}
						IAtomContainer ac;
						if (prod_smiles) {
							ac = sp.parseSmiles(product);
						} else {
							String cleanProd = removeAromacity(product);
							ac = sp.parseSmiles(cleanProd);
						}
						//IAtomContainer ac =  sp.parseSmiles(product);
						IAtomContainer ac1 = ac.clone();
						AtomContainerManipulator.convertImplicitToExplicitHydrogens(ac1);
						for (Slice sc : sliceTransforms.values()) {
							for (SliceReaction scr : sc.getReactions()) {
								int nb_product = 0;
								for (SliceMolecule mol : scr.getProducts()) {
									if (!list_products.contains(mol.smarts)) {
										Pattern ptrn = SmartsPattern.create(mol.smarts, Smarts.FLAVOR_CACTVS);
										String product_no_mapping;
										if (prod_smiles) {
											product_no_mapping = product.replaceAll(":\\d+", "");
										} else {
											product_no_mapping = removeAromacity(product).replaceAll(":\\d+", "");
										}

										IAtomContainer test = sp.parseSmiles(product_no_mapping);
										AtomContainerManipulator.suppressHydrogens(test);
										SmilesGenerator gen = new SmilesGenerator(SmiFlavor.Default);
										String re = gen.create(test);
										SmilesParser sd = new SmilesParser(SilentChemObjectBuilder.getInstance());
										IAtomContainer fin = sd.parseSmiles(re);
										AtomContainerManipulator.convertImplicitToExplicitHydrogens(fin);
										String dre = gen.create(fin);
										IAtomContainer final_t = sd.parseSmiles(dre);
										if (ptrn.matches(final_t)) {
											isFound = true;
											IAtomContainer ac2 = final_t.clone();
											Mappings mapping = ptrn.matchAll(ac2).uniqueAtoms();
											for (Map<IAtom, IAtom> map : mapping.toAtomMap()) {

												isKilled = null;
												rating = 0;
												Map<Integer, Integer> aam = convertAAMapToIndexAtomConstantMap(ac2, map);
												configAtomContainer(ac2, aam, map.values());
												if (mol.getLogic() != null) {
													ParseTree tree = getParseTreeFromString(mol.getLogic());
													SliceFacade scf = new SliceFacade(ac2);
													if (carbonCentered != null)
														scf.setCarbonCentered(carbonCentered);

													Value testLogic = scf.visit(tree);
													isKilled = scf.isKill();
													rating = scf.getRating();
													//System.out.println("ISKILLED :"+ isKilled);
													//System.out.println("rating :"+ rating);
													String activated_rating = new String("activated");
													if (scf.getRatingOccurence() > 0 && lowerRating.equals(activated_rating)) {
														isKilled = true;
													}

													if (!isKilled && mol.logic.contains("ghost") && (ghostMolecules.equals(activated_rating))) {// || inchikey.equals(activated_rating)
														isKilled = true;
													}
												} else {
													isKilled = false;
												}

												if (isKilled)
													break;
											}
											//System.out.println("rating :"+ rating);
											if (!isKilled) {
												if (final_line.isEmpty()) {
													//if (!ghostMolecules.equals(activated) && !mol.logic.contains("ghost"))
													final_line = final_line.concat(gen.create(final_t));
												} else {
													//if (!ghostMolecules.equals(activated) && !mol.logic.contains("ghost")) {
													final_line = final_line.concat(".");
													final_line = final_line.concat(gen.create(final_t));
													//}
												}

												new_rating += rating;//System.out.println("new rating :"+ new_rating);
												list_products.add(mol.smarts);
											}
										}
									} else {
										isFound = true;
									}
								}
							}
						}
					} catch (InvalidSmilesException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (!isFound) {
						if (final_line.isEmpty()) {
							final_line = final_line.concat(product);
						} else {
							//if (!ghostMolecules.equals(activated) && !mol.logic.contains("ghost")) {
							final_line = final_line.concat(".");
							final_line = final_line.concat(product);
							//}
						}
					}
				}
				if (!final_line.isEmpty()) {
					boolean correctSMILES = false;
					// TEST
					try {
						sp.parseSmiles(final_line);
						correctSMILES = true;
					} catch (InvalidSmilesException e) {
						correctSMILES = false;
					}
					IAtomContainer product;
					if (correctSMILES) {
						product = sp.parseSmiles(final_line);
					} else {
						String cleanProd = removeAromacity(final_line);
						product = sp.parseSmiles(cleanProd);
					}
					try {
						product.setProperty("Molecule Smile", smilesGenerator.create(product));
					} catch (CDKException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (!inchikey.equals(activated)) {

						AtomContainerManipulator.convertImplicitToExplicitHydrogens(product);
						StructureDiagramGenerator sdg = new StructureDiagramGenerator();
						try {
							product.setTitle(id_product);
							product.setProperty("sliceScore", new_rating + Integer.parseInt(line.split("\t")[3]));
							sdg.generateCoordinates(product);
							writer_sdf_file.write(product);
							//}
						} catch (InvalidSmilesException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (CDKException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (ghostMolecules.equals(activated)) {
						if (inchikey.equals(activated)) {

							//OLD WAY TO GENERATE INCHIKEY
							/*SmilesParser smilesParser= new SmilesParser(DefaultChemObjectBuilder.getInstance());
							IAtomContainer mol = smilesParser.parseSmiles(final_line);
							InChIGeneratorFactory inchiGenFactory = InChIGeneratorFactory.getInstance();
							InChIGenerator inchiGen = inchiGenFactory.getInChIGenerator(mol);
							String product_inchi= inchiGen.getInchiKey();
							outputFile.write(product_inchi);*/
							//encode SMILE for URL
							if (!correctSMILES) {
								final_line = removeAromacity(final_line);
							}
							String encodeSmile = URLEncoder.encode(final_line.replaceAll(":\\d+", ""), "UTF-8");
							//Query resolver from NIH
							URL nih_url = new URL("https://cactus.nci.nih.gov/chemical/structure/" + encodeSmile + "/smiles");
							HttpURLConnection con = (HttpURLConnection) nih_url.openConnection();
							con.setRequestMethod("GET");
							BufferedReader read_response = new BufferedReader(new InputStreamReader(con.getInputStream()));
							String product_smiles = read_response.readLine();
							read_response.close();
							String encodeSmile2 = URLEncoder.encode(product_smiles.replaceAll(":\\d+", ""), "UTF-8");
							URL nih_url2 = new URL("https://cactus.nci.nih.gov/chemical/structure/" + encodeSmile2 + "/inchikey");
							HttpURLConnection con2 = (HttpURLConnection) nih_url2.openConnection();
							con2.setRequestMethod("GET");

							//Read response
							BufferedReader read_response2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
							String product_inchi = read_response2.readLine();
							read_response2.close();
							outputFile.write(product_inchi);
							outputFile.newLine();
						} else {
							outputFile.write(final_line + "\t" + line.split("\t")[1] + "\t" + line.split("\t")[1] + ">" + line.split("\t")[2].split(">")[1] + ">" + final_line + "\t" + (new_rating + Integer.parseInt(line.split("\t")[3])));
							//System.out.println("new rating : "+ new_rating+ " old rating : "+ Integer.parseInt(line.split("\t")[3]));
						}
						outputFile.newLine();
					} else {
						if (inchikey.equals(activated)) {
							if (!correctSMILES) {
								final_line = removeAromacity(final_line);
							}

							String encodeSmile = URLEncoder.encode(final_line.replaceAll(":\\d+", ""), "UTF-8");
							//Query resolver from NIH
							URL nih_url = new URL("https://cactus.nci.nih.gov/chemical/structure/" + encodeSmile + "/smiles");
							HttpURLConnection con = (HttpURLConnection) nih_url.openConnection();
							con.setRequestMethod("GET");
							BufferedReader read_response = new BufferedReader(new InputStreamReader(con.getInputStream()));
							String product_smiles = read_response.readLine();
							read_response.close();
							String encodeSmile2 = URLEncoder.encode(product_smiles.replaceAll(":\\d+", ""), "UTF-8");
							URL nih_url2 = new URL("https://cactus.nci.nih.gov/chemical/structure/" + encodeSmile2 + "/inchikey");
							HttpURLConnection con2 = (HttpURLConnection) nih_url2.openConnection();
							con2.setRequestMethod("GET");

							//Read response
							BufferedReader read_response2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
							String product_inchi = read_response2.readLine();
							read_response2.close();
							outputFile.write(product_inchi);
							outputFile.newLine();
						} else {
							if (final_line.split("\\.").length == products.length) {
								outputFile.write(line.split("\t")[0] + "\t" + line.split("\t")[1] + "\t" + line.split("\t")[1] + ">" + line.split("\t")[2].split(">")[1] + ">" + line.split("\t")[0] + "\t" + (new_rating + Integer.parseInt(line.split("\t")[3])));
								outputFile.newLine();
							} else {
								outputFile.write(line);
								outputFile.newLine();
							}
						}
					}
				} else {
					outputFile2.write(line);
					outputFile2.newLine();
				}
				final_line = "";
				list_products.clear();
				line = reader.readLine();
				id_product = reader_id.readLine();
			}
			reader.close();
			outputFile.close();
			outputFile2.close();
			reader_id.close();
			outputsdf.close();
			writer_sdf_file.close();
			//DEBUG DELETE FILES
			(new File(outputPath + outputName + "_0.csv")).delete();
			(new File(outputPath + outputName + "_delete.csv")).delete();
			(new File((outputPath + outputName).split("\\.")[0].concat("_0.id"))).delete();
			if (inchikey.equals(activated)) {
				(new File((outputPath + outputName).split("\\.")[0].concat(".sdf"))).delete();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidSmilesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CDKException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	remove aromaticity on molecule
	 */
	public static String removeAromacity( String smiles){
		StringBuilder output = new StringBuilder();
		int i = 0;
		while( i < smiles.length()){
			char c = smiles.charAt(i);
			if(c == '['){
				int closingBracket = smiles.indexOf(']',i);
				if(closingBracket != -1){
					String atomMap= smiles.substring(i+1,closingBracket);
					String[] parts = atomMap.split(":");

					String atomSymbol = parts[0];
					if(atomSymbol.length() == 1 && Character.isLowerCase(atomSymbol.charAt(0))){
						atomSymbol = atomSymbol.toUpperCase();
					}
					else if(atomSymbol.length() == 2 && atomSymbol.charAt(1) == 'H'){
						atomSymbol = atomSymbol.substring(0,1).toUpperCase()+atomSymbol.charAt(1);
					}
					output.append("[").append(atomSymbol);
					if(parts.length > 1){
						output.append(":").append(parts[1]);
					}
					output.append("]");
					i=closingBracket;

				}
			}else{
				output.append(c);
			}
				i++;
		}
		return output.toString();
	}
	/* all the ghost molecule in the logic
	 * 
	 */
	public List<String> print_ghost_molecules(String sliceFilepath) throws CloneNotSupportedException {
		
		List<String> ghost_molecules = new ArrayList<>();
		Map<Integer, Slice> sliceTransforms = null;
		 ChemicalFormatParser parser = new ChemicalFormatParser();
		 //detect format and parse it if it's a slice compatible format
		 String format;
		 try {
			format = parser.formatDetector(sliceFilepath);
			if (format == null) {
				System.out.println(ColouredSystemOutPrintln.ANSI_RED + "Wrong file format" + ColouredSystemOutPrintln.ANSI_RESET);
			}
			else if (format.contains("sliceJSON")) {
				sliceTransforms = parser.parseJSlice(sliceFilepath);
			}
			else if (format.contains("sliceXML")) {
				sliceTransforms = parser.parseSliceXML(sliceFilepath);
			}
		} catch (IOException | CDKException e2) {
			//TODO Auto-generated catch block
			e2.printStackTrace();
		}
		for (Slice sc : sliceTransforms.values()) {
			for (SliceReaction scr : sc.getReactions()) {
				for (SliceMolecule mol : scr.getReactants()) {
					if(mol.logic !=null && ((mol.logic).contains("ghost"))) {
						ghost_molecules.add(mol.smarts);
					}
				}
				for (SliceMolecule mol : scr.getProducts()) {
					if(mol.logic !=null && ((mol.logic).contains("ghost"))) {
						ghost_molecules.add(mol.smarts);
					}
				}
			}
		}
		return ghost_molecules;
	}
	
	/* the main product
	 * 
	 */
	public String product_tranform(String sliceFilepath) throws CloneNotSupportedException {
		
		String product = null;
		Map<Integer, Slice> sliceTransforms = null;
		 ChemicalFormatParser parser = new ChemicalFormatParser();
		 //detect format and parse it if it's a slice compatible format
		 String format;
		try {
			format = parser.formatDetector(sliceFilepath);
			if (format == null) {
				System.out.println(ColouredSystemOutPrintln.ANSI_RED + "Wrong file format" + ColouredSystemOutPrintln.ANSI_RESET);
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
		for (Slice sc : sliceTransforms.values()) {
			for (SliceReaction scr : sc.getReactions()) {
				for (SliceMolecule mol : scr.getProducts()) {
					if(mol.logic ==null) {
						product = mol.smarts;
						break;
					}
					else if(!((mol.logic).contains("ghost"))) {
						product = mol.smarts;
						break;
					}
				}
			}
		}
		return product;
	}
	 /**
	  * get all SMIRKS related to each reaction
	 * @param allSlice Map<Integer,Slice> Integer is the reaction id and Slice object contains its associated components
	 */
	private static void getSubTranformSmirks(Map<Integer, Slice> allSlice) {
		 reactionSmirks = new HashMap<String,String>();
			for (Slice sc : allSlice.values()) {
				for (SliceReaction scr : sc.getReactions()) {
					StringBuilder smirks = new StringBuilder();
					for (SliceMolecule mol : scr.getReactants()) {
						smirks.append(mol.getSmarts());
						smirks.append(".");
					}
					smirks.deleteCharAt(smirks.length()-1);
					smirks.append(">>");
					for (SliceMolecule mol : scr.getProducts()) {
						smirks.append(mol.getSmarts());
						smirks.append(".");
					}
					smirks.deleteCharAt(smirks.length()-1);
					reactionSmirks.put(scr.getId()+"", smirks.toString());
				}
			}
		}
	 
	 /**
	 * test if a building block is compatible with either a pattern and a logic associated with a reactant or more
	 * if it goes in the forward synthetic direction or with a pattern and a logic associated with a product or more
	 * if it goes in the retrosynthetic direction 
	 * @param ac Building block
	 * @param allSlice parsed Slice file. Key is the reactionID and Slice it's associated components (molecules with pattern and logic)
	 * @return {moleculeID:[compatibleMapping, ...] moleculeID is the id associated with a pattern and a logic in a SLICE file.
	 * If a building block is compatible with a pattern, all compatible Mappings with a logic  and generating a different product 
	 * are stored as a value. The molecules stores are pre-computed and pre-annotated with atom mapping. They are ready for being transformed. 
	 * @throws CloneNotSupportedException
	 * @throws CDKException 
	 */
	private static Map<String,List<IAtomContainer>> testCompatibility(IAtomContainer ac,
				Map<Integer, Slice> allSlice, String lowerRating, Integer reactantNumber) throws CloneNotSupportedException, CDKException {
		 Map<String,List<IAtomContainer>> res = new HashMap<String,List<IAtomContainer>>();

		 //convert to explicit hydrogen to match with SMARTS where hydrogens are always explicit
		//AtomContainerManipulator.percieveAtomTypesAndConfigureAtoms(ac);
		//CDKHydrogenAdder ha = CDKHydrogenAdder.getInstance(DefaultChemObjectBuilder.getInstance());
		//ha.addImplicitHydrogens(ac);
		AtomContainerManipulator.convertImplicitToExplicitHydrogens(ac);
			for (Slice sc : allSlice.values()) {
				for (SliceReaction scr : sc.getReactions()) {
					List<IAtomContainer> compatibleMolecules = new ArrayList<IAtomContainer>();
					for (SliceMolecule mol : scr.getReactants()) {
						if( reactantNumber == null || reactantNumber == Integer.parseInt(mol.getId())) {
							Pattern ptrn = SmartsPattern.create(mol.getSmarts(), Smarts.FLAVOR_CACTVS);
							// key:aam in Smarts value: atom index of the matched atom in IAtomContainer
							//System.out.println(mol.getSmarts() + " " +ptrn.matches(ac));
							SmilesParser2 sp = new SmilesParser2(SilentChemObjectBuilder.getInstance());

							IAtomContainer ac1;
							try {
								ac1 = ac.clone();
								if (ptrn.matches(ac1)) {
									System.out.println("   =>" + mol.getSmarts());
									//System.out.println(" rating  =>" + lowerRating);
									// clone IAtomContainer because matchAll cannot be used for matching multiple
									// SMARTS against the same container.
									IAtomContainer ac2 = ac1.clone();
									Mappings mapping = ptrn.matchAll(ac2).uniqueAtoms();
									//avoid duplicates (due to symmetry in the pattern for instance)
									//only new set of mapped atoms should be tested
									//The mapping with the best score is kept
									List<Set<Integer>> uniqueMappedAtomIndex = new ArrayList<Set<Integer>>();
									Map<Set<Integer>, IAtomContainer> mappingToMolecule = new HashMap<Set<Integer>, IAtomContainer>();
									for (Map<IAtom, IAtom> map : mapping.toAtomMap()) {
										isKilled = false;
										rating = -1;
										Map<Integer, Integer> aam = convertAAMapToIndexAtomConstantMap(ac2, map);
										configAtomContainer(ac2, aam, map.values());
										if (mol.getLogic() != null) {
											ParseTree tree = getParseTreeFromString(mol.getLogic());
											SliceFacade scf = new SliceFacade(ac2);
											if (carbonCentered != null)
												scf.setCarbonCentered(carbonCentered);
											//System.out.println(tree.getChildCount());
											//for(int j=0; j < tree.getChildCount();j++)
											//System.out.println(tree.getChild(j).getText().split("then")[1]);
											//System.out.println(tree.toStringTree());
											//System.out.println(tree.getChild(0).toString());
											Value testLogic = scf.visit(tree);
											isKilled = scf.isKill();
											rating = scf.getRating();
											//add all Unique mappings as they can potentially gives different molecules
											String activated_rating = new String("activated");
											if (scf.getRatingOccurence() > 0 && lowerRating.equals(activated_rating)) {
												isKilled = true;
											}

											System.out.println("Is Killed " + isKilled);
											System.out.println("Rating " + scf.getRating());
											if (isKilled == false) {
												//System.out.println("Is a compatible BB ? " + isKilled);
												Set<Integer> maapedAtomIndex = aam.keySet();
												int rating = scf.getRating();
												if (uniqueMappedAtomIndex.contains(maapedAtomIndex)) {
													IAtomContainer duplicateMappedContainer = mappingToMolecule.get(maapedAtomIndex);
													if ((int) duplicateMappedContainer.getProperty("rating") < rating) {
														IAtomContainer newAc = ac2.clone();
														newAc.setProperty("compatibleBB", mol.getId());
														newAc.setProperty("rating", rating);
														newAc.setProperty("sliceScore", rating);
														mappingToMolecule.put(maapedAtomIndex, newAc);
													}
												} else {
													uniqueMappedAtomIndex.add(maapedAtomIndex);
													IAtomContainer newAc = ac2.clone();
													newAc.setProperty("compatibleBB", mol.getId());
													newAc.setProperty("rating", rating);
													newAc.setProperty("sliceScore", rating);
													mappingToMolecule.put(maapedAtomIndex, newAc);
												}
												//old way
												//ac2.setProperty("compatibleBB", mol.getId());
												//ac2.setProperty("rating", scf.getRating());
												//compatibleMolecules.add(ac2);
											}
										}

									}
									compatibleMolecules.addAll(mappingToMolecule.values());
								}

							} catch (InvalidSmilesException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}		
					if (!compatibleMolecules.isEmpty())
						res.put(""+scr.getId(), compatibleMolecules);
				}
			}
			return res;
		}
	 
	
	/**
	 * test if a building block is compatible with either a pattern and a logic associated with a reactant or more
	 * if it goes in the forward synthetic direction or with a pattern and a logic associated with a product or more
	 * if it goes in the retrosynthetic direction 
	 * @param ac Building block
	 * @param allSlice parsed Slice file. Key is the reactionID and Slice it's associated components (molecules with pattern and logic)
	 * @return {moleculeID:[compatibleMapping, ...] moleculeID is the id associated with a pattern and a logic in a SLICE file.
	 * If a building block is compatible with a pattern, all compatible Mappings with a logic  and generating a different product 
	 * are stored as a value. The molecules stores are pre-computed and pre-annotated with atom mapping. They are ready for being transformed. 
	 * @throws CloneNotSupportedException
	 * @throws CDKException 
	 */
	private static Map<String,List<IAtomContainer>> testCompatibility_products(IAtomContainer ac,
				Map<Integer, Slice> allSlice) throws CloneNotSupportedException, CDKException {
		 Map<String,List<IAtomContainer>> res = new HashMap<String,List<IAtomContainer>>();
			//convert to explicit hydrogen to match with SMARTS where hydrogens are always explicit
			AtomContainerManipulator.convertImplicitToExplicitHydrogens(ac);
			
			for (Slice sc : allSlice.values()) {
				for (SliceReaction scr : sc.getReactions()) {
					List<IAtomContainer> compatibleMolecules = new ArrayList<IAtomContainer>();
					for (SliceMolecule mol : scr.getProducts()) {
						Pattern ptrn = SmartsPattern.create(mol.getSmarts(), Smarts.FLAVOR_CACTVS);
						// key:aam in Smarts value: atom index of the matched atom in IAtomContainer
						//System.out.println(mol.getSmarts() + " " +ptrn.matches(ac));
						if (ptrn.matches(ac)) {
							System.out.println("   =>" + mol.getSmarts());
							// clone IAtomContainer because matchAll cannot be used for matching multiple
							// SMARTS against the same container.
							IAtomContainer ac2 = ac.clone();
							Mappings mapping = ptrn.matchAll(ac2).uniqueAtoms();
							//avoid duplicates (due to symmetry in the pattern for instance)
							//only new set of mapped atoms should be tested
							//The mapping with the best score is kept
							List<Set<Integer>> uniqueMappedAtomIndex = new ArrayList<Set<Integer>>();
							Map<Set<Integer>,IAtomContainer> mappingToMolecule = new HashMap<Set<Integer>,IAtomContainer>();
							for (Map<IAtom, IAtom> map : mapping.toAtomMap()) {
								isKilled = null;
						    	rating = -1;
								Map<Integer, Integer> aam = convertAAMapToIndexAtomConstantMap(ac2, map);
								configAtomContainer(ac2, aam, map.values()); 
								if (mol.getLogic() != null) {
									ParseTree tree = getParseTreeFromString(mol.getLogic());
									SliceFacade scf = new SliceFacade(ac2);
									if (carbonCentered != null)
										scf.setCarbonCentered(carbonCentered);
									//System.out.println(tree.getText());
									//System.out.println(tree.toStringTree());
									//System.out.println(tree.getChild(0).toString());
							        Value testLogic = scf.visit(tree);
							        isKilled = scf.isKill();
							    	rating = scf.getRating();
							        System.out.println("Is Killed " + scf.isKill());
							        System.out.println("Rating "+scf.getRating());
							        //add all Unique mappings as they can potentially gives different molecules
							        
							        System.out.println("Is Killed " + scf.isKill());
							        if (isKilled == false) {
							        	//System.out.println("Is a compatible BB ? " + isKilled);
							        	Set<Integer> maapedAtomIndex = aam.keySet();
							        	int rating = scf.getRating();
							        	if (uniqueMappedAtomIndex.contains(maapedAtomIndex)) {
							        		IAtomContainer duplicateMappedContainer = mappingToMolecule.get(maapedAtomIndex);
							        		if ((int) duplicateMappedContainer.getProperty("rating") < rating) {
							        			IAtomContainer newAc = ac2.clone();
							        			newAc.setProperty("compatibleBB", mol.getId());
							        			newAc.setProperty("rating", rating);
							        			newAc.setProperty("sliceScore", rating);
							        			mappingToMolecule.put(maapedAtomIndex, newAc);
							        		}
										}
										else {
											uniqueMappedAtomIndex.add(maapedAtomIndex);
											IAtomContainer newAc = ac2.clone();
						        			newAc.setProperty("compatibleBB", mol.getId());
						        			newAc.setProperty("rating", rating);
						        			newAc.setProperty("sliceScore", rating);
						        			mappingToMolecule.put(maapedAtomIndex, newAc);
										}
							        	//old way
							        	//ac2.setProperty("compatibleBB", mol.getId());
							        	//ac2.setProperty("rating", scf.getRating());
							        	//compatibleMolecules.add(ac2);
							        }
								}
						        
							}
							compatibleMolecules.addAll(mappingToMolecule.values());	
						}  
					}		
					if (!compatibleMolecules.isEmpty())
						res.put(""+scr.getId(), compatibleMolecules);
				}
			}
			return res;
		}
	
	
	/**
	 * Debug function used for testing if a SLICE transform works as expected.
	 * it's called by testMoleculesWithTransform, which needs a transform to test and a test set 
	 * containing molecules with the expected result (kill, ratin = 10;.5v ..)
	 * @param ac
	 * @param allSlice
	 * @return
	 * @throws CloneNotSupportedException
	 * @throws CDKException 
	 */
	private static void BBCompatibilities(IAtomContainer ac,
			Map<Integer, Slice> allSlice) throws CloneNotSupportedException, CDKException {
		//convert to explicit hydrogen in order to match with SMARTS where hydrogens are always explicit
		AtomContainerManipulator.convertImplicitToExplicitHydrogens(ac);
		
		for (Slice sc : allSlice.values()) {
			for (SliceReaction scr : sc.getReactions()) {
				for (SliceMolecule mol : scr.getReactants()) {
					Pattern ptrn = SmartsPattern.create(mol.smarts, Smarts.FLAVOR_CACTVS);
					System.out.println("   =>"+ mol.smarts);
					// key:aam in Smarts value: atom index of the matched atom in IAtomContainer
					List<Map<Integer, Integer>> mappings = new ArrayList<Map<Integer, Integer>>();
					//System.out.println(mol.getSmarts() + " " +ptrn.matches(ac));
					//System.out.println("pattern:" +ptrn.matches(ac));
					
						if (ptrn.matches(ac)) {
							System.out.println("   =>"+ mol.smarts);
							//System.out.println("   =>"+ mol.logic);
							//System.out.println("   =>" + mol.getSmarts());
							// clone IAtomContainer because matchAll cannot be used for matching multiple
							// SMARTS against the same container.
							IAtomContainer ac2 = ac.clone();
							Mappings mapping = ptrn.matchAll(ac2).uniqueAtoms();
							List<Set<Integer>> uniqueMappedAtomIndex = new ArrayList<Set<Integer>>();
							List<Boolean> killResults = new ArrayList<Boolean>();
							List<Integer> ratingResults = new ArrayList<Integer>();
							for (Map<IAtom, IAtom> map : mapping.toAtomMap()) {
								isKilled = null;
						    	rating = -1;
								Map<Integer, Integer> aam = convertAAMapToIndexAtomConstantMap(ac2, map);
								configAtomContainer(ac2, aam, map.values()); 
								//avoid duplicates (due to symmetry in the pattern for instance)
								//only new set of mapped atoms should be tested
								/*Set<Integer> maapedAtomIndex = aam.keySet();
								if (uniqueMappedAtomIndex.contains(maapedAtomIndex)) {
									mappingToMolecule.put(maapedAtomIndex, ac2);
									//continue;
								}
								else {
									uniqueMappedAtomIndex.add(maapedAtomIndex);
								}*/
								if (mol.getLogic() != null) {
									ParseTree tree = getParseTreeFromString(mol.getLogic());
									SliceFacade scf = new SliceFacade(ac2);
									if (carbonCentered != null)
										scf.setCarbonCentered(carbonCentered);
									//System.out.println(tree.getText());
									//System.out.println(tree.toStringTree());
									//System.out.println(tree.getChild(0).toString());
							        Value testLogic = scf.visit(tree);
							        isKilled = scf.isKill();
							    	rating = scf.getRating();
							    	//if (scf.getRatingOccurence() > 0) {
							       // 	isKilled= true;
								   // }
							        System.out.println(" => Is Killed " + isKilled);
							        System.out.println(" => Rating "+scf.getRating());
							        System.out.println(" => Rating occurences "+scf.getRatingOccurence());
							        //if (testLogic.isBoolean()) {
							        //	System.out.println("Is a compatible BB ? " + testLogic.asBoolean());
							        //}
							        
							        killResults.add(isKilled);
							        ratingResults.add(rating);
							        
								}
	
								/*
								 * if (testLogic.asBoolean()) { for (Entry<IAtom, IAtom> e : map.entrySet()) {
								 * aam.put(Integer.parseInt(e.getKey().getProperty(CDKConstants.
								 * ATOM_ATOM_MAPPING)), ac2.indexOf(e.getValue())); } mappings.add(aam); }
								 */
							}
							boolean didItWork = false;
							boolean isKilled2 = false;
							for (int i = 0; i < killResults.size(); i++) {
						        if (expectedResult != null) {
						        	 if (expectedResult.contains("kill")) {
											if (killResults.get(i))
												didItWork = true;
										}
										else {
											int expectedScore = Integer.parseInt(expectedResult);
											if (expectedScore == ratingResults.get(i) && killResults.get(i) == false)
												didItWork = true;
										}
						        	 isKilled2 = true;
						        }
						        if (i == killResults.size() - 1) {
						        	System.out.println("**************");
						        	if (didItWork) {
						        		System.out.println("  => SUCCESS");
						        	}
						        	else {
						        		if (expectedResult.contains("kill")) {
											System.err.println("  => ERROR NOT KILL when it should be");
										}
										else {
											System.err.println("  => ERROR different rating? should be killed?");
										}
						        	}
						        	if (didItWork && !isKilled2)
						        		System.out.println("Is a compatible BB ? true");
						        	else if (didItWork && isKilled2)
						        		System.out.println("Is a compatible BB ? false");
						        	System.out.println("**************");
						        }
							}
						}                                          
				}
			}

		}
	}
	 
	
	/**
	 * add molecule in compatible bbs of reactants
	 * @param ac
	 * @param allSlice
	 * @return
	 * @throws CloneNotSupportedException
	 * @throws IOException 
	 * @throws CDKException 
	 */
	private static void Create_BBCompatibilities(IAtomContainer ac,
			Map<Integer, Slice> allSlice, String smiles) throws CloneNotSupportedException, IOException, CDKException {
		//convert to explicit hydrogen in order to match with SMARTS where hydrogens are always explicit
		AtomContainerManipulator.convertImplicitToExplicitHydrogens(ac);
		
		BufferedWriter outputWriter;
		int count_reaction, count_reactant;
		for (Slice sc : allSlice.values()) {
			count_reaction =0;
			for (SliceReaction scr : sc.getReactions()) {
				count_reaction++;
				count_reactant =0;
				for (SliceMolecule mol : scr.getReactants()) {
					count_reactant++;
					Pattern ptrn = SmartsPattern.create(mol.getSmarts(), Smarts.FLAVOR_CACTVS);
					//System.out.println("   =>" + mol.getSmarts());
					// key:aam in Smarts value: atom index of the matched atom in IAtomContainer
					List<Map<Integer, Integer>> mappings = new ArrayList<Map<Integer, Integer>>();
					//System.out.println(mol.getSmarts() + " " +ptrn.matches(ac));
					if (ptrn.matches(ac)) {
						System.out.println("   =>" + mol.getSmarts());
						// clone IAtomContainer because matchAll cannot be used for matching multiple
						// SMARTS against the same container.
						IAtomContainer ac2 = ac.clone();
						Mappings mapping = ptrn.matchAll(ac2).uniqueAtoms();
						List<Set<Integer>> uniqueMappedAtomIndex = new ArrayList<Set<Integer>>();
						List<Boolean> killResults = new ArrayList<Boolean>();
						List<Integer> ratingResults = new ArrayList<Integer>();
						for (Map<IAtom, IAtom> map : mapping.toAtomMap()) {
							isKilled = null;
					    	rating = -1;
							Map<Integer, Integer> aam = convertAAMapToIndexAtomConstantMap(ac2, map);
							configAtomContainer(ac2, aam, map.values()); 
							//avoid duplicates (due to symmetry in the pattern for instance)
							//only new set of mapped atoms should be tested
							/*Set<Integer> maapedAtomIndex = aam.keySet();
							if (uniqueMappedAtomIndex.contains(maapedAtomIndex)) {
								mappingToMolecule.put(maapedAtomIndex, ac2);
								//continue;
							}
							else {
								uniqueMappedAtomIndex.add(maapedAtomIndex);
							}*/
							if (mol.getLogic() != null) {
								ParseTree tree = getParseTreeFromString(mol.getLogic());
								SliceFacade scf = new SliceFacade(ac2);
								if (carbonCentered != null)
									scf.setCarbonCentered(carbonCentered);
								//System.out.println(tree.getText());
								//System.out.println(tree.toStringTree());
								//System.out.println(tree.getChild(0).toString());
						        Value testLogic = scf.visit(tree);
						        isKilled = scf.isKill();
						    	rating = scf.getRating();
						        System.out.println(" => Is Killed " + scf.isKill());
						        System.out.println(" => Rating "+scf.getRating());
						        //if (testLogic.isBoolean()) {
						        //	System.out.println("Is a compatible BB ? " + testLogic.asBoolean());
						        //}
						        killResults.add(isKilled);
						        ratingResults.add(rating);
						        
							}

							/*
							 * if (testLogic.asBoolean()) { for (Entry<IAtom, IAtom> e : map.entrySet()) {
							 * aam.put(Integer.parseInt(e.getKey().getProperty(CDKConstants.
							 * ATOM_ATOM_MAPPING)), ac2.indexOf(e.getValue())); } mappings.add(aam); }
							 */
						}
						boolean didItWork = true;
						for (int i = 0; i < killResults.size(); i++) {
							
							if (killResults.get(i))
								didItWork = false;
					        
					        if (i == killResults.size() - 1) {
					        	System.out.println("**************");
					        	if (didItWork) {
					        		//save in the file BB_REACTION_ID_BB_NUMBER.txt
					        		String file_name= "/Users/noulehoilemos2/Documents/1171/1171_bbs_"+count_reaction+"_"+count_reactant+".txt";
					        		outputWriter = new BufferedWriter(new FileWriter(file_name, true)); 
					    			outputWriter.write(smiles);
					    			outputWriter.newLine();
					    			outputWriter.close();
					        		System.out.println("  => SUCCESS" + smiles);
					        		
					        		
					        	}
					        	
					        }
						}
					}
				}
			}

		}
	}
	 
	
	/**
	 * check if a bb is compatible to a reactant.
	 * @param ac
	 * @param allSlice
	 * @return
	 * @throws CloneNotSupportedException
	 * @throws IOException 
	 * @throws CDKException 
	 */
	private static void Count_BBCompatibilities(IAtomContainer ac,
			Map<Integer, Slice> allSlice, String smiles, String outputPath, int transform_id, String outputName,String reactant_number) throws CloneNotSupportedException, IOException, CDKException {
		//convert to explicit hydrogen in order to match with SMARTS where hydrogens are always explicit
		AtomContainerManipulator.convertImplicitToExplicitHydrogens(ac);
		BufferedWriter outputWriter;
		int count_reaction = 0, count_reactant= 0;

		for (Slice sc : allSlice.values()) {
			count_reaction =0;
			for (SliceReaction scr : sc.getReactions()) {
				
				count_reactant =0;
				for (SliceMolecule mol : scr.getReactants()) {
					Integer reactant_specified = tryParseInt(reactant_number);
					//System.out.println("   =>" + mol.getSmarts());
					if(reactant_specified == null || (Integer.parseInt(mol.getId()) == reactant_specified)){
						Pattern ptrn = SmartsPattern.create(mol.getSmarts(), Smarts.FLAVOR_CACTVS);
						//System.out.println("   =>" + mol.getSmarts());
						// key:aam in Smarts value: atom index of the
						//matched atom in IAtomContainer
						List<Map<Integer, Integer>> mappings = new ArrayList<Map<Integer, Integer>>();
						//System.out.println(mol.getSmarts() + " " +ptrn.matches(ac));
						if (ptrn.matches(ac)) {
							//System.out.println(smiles+ "  MATCH =>" + mol.getSmarts());
							//System.out.println("   =>" + mol.getLogic());
							// clone IAtomContainer because matchAll cannot be used for matching multiple
							// SMARTS against the same container.
							IAtomContainer ac2 = ac.clone();
							Mappings mapping = ptrn.matchAll(ac2).uniqueAtoms();
							List<Set<Integer>> uniqueMappedAtomIndex = new ArrayList<Set<Integer>>();
							List<Boolean> killResults = new ArrayList<Boolean>();
							List<Integer> ratingResults = new ArrayList<Integer>();
							for (Map<IAtom, IAtom> map : mapping.toAtomMap()) {
								isKilled = null;
								rating = -1;
								Map<Integer, Integer> aam = convertAAMapToIndexAtomConstantMap(ac2, map);
								configAtomContainer(ac2, aam, map.values());
								//avoid duplicates (due to symmetry in the pattern for instance)
								//only new set of mapped atoms should be tested
								/*Set<Integer> maapedAtomIndex = aam.keySet();
								if (uniqueMappedAtomIndex.contains(maapedAtomIndex)) {
									mappingToMolecule.put(maapedAtomIndex, ac2);
									//continue;
								}
								else {
									uniqueMappedAtomIndex.add(maapedAtomIndex);
								}*/
								if (mol.getLogic() != null) {
									ParseTree tree = getParseTreeFromString(mol.getLogic());
									SliceFacade scf = new SliceFacade(ac2);
									if (carbonCentered != null)
										scf.setCarbonCentered(carbonCentered);
									//System.out.println(tree.getText());
									//System.out.println(tree.toStringTree());
									//System.out.println(tree.getChild(0).toString());
									Value testLogic = scf.visit(tree);

									isKilled = scf.isKill();
									rating = scf.getRating();
									killResults.add(isKilled);
									ratingResults.add(rating);

								}

							}
							if(isKilled == null){ // no logic on the reactant should also work
								String file_name= outputPath+transform_id+"_"+"compatibles_BB_"+count_reaction+"_"+count_reactant+"_"+outputName+".txt";
								outputWriter = new BufferedWriter(new FileWriter(file_name, true));
								outputWriter.write(smiles);
								outputWriter.newLine();
								outputWriter.close();
							}
							else {
								boolean didItWork = true;
								for (int i = 0; i < killResults.size(); i++) {

									if (killResults.get(i))
										didItWork = false;

									if (i == killResults.size() - 1) {
										//System.out.println("**************");
										if (didItWork) {
											//save in the file BB_REACTION_ID_BB_NUMBER.txt
											String file_name = outputPath + transform_id + "_" + "compatibles_BB_" + count_reaction + "_" + count_reactant + "_" + outputName + ".txt";
											outputWriter = new BufferedWriter(new FileWriter(file_name, true));
											outputWriter.write(smiles);
											outputWriter.newLine();
											outputWriter.close();
											//System.out.println("  => SUCCESS ");


										}

									}
								}
							}
						}
					}
					count_reactant++;
				}
				count_reaction++;
			}

		}
	}

	/**
	 * check if a bb match a reactant.
	 * @param ac
	 * @param allSlice
	 * @return
	 * @throws CloneNotSupportedException
	 * @throws IOException
	 * @throws CDKException
	 */
	private static void Count_BBCompatibilities_pattern(IAtomContainer ac,
												Map<Integer, Slice> allSlice, String smiles, String outputPath, int transform_id, String outputName,String reactant_number) throws CloneNotSupportedException, IOException, CDKException {
		//convert to explicit hydrogen in order to match with SMARTS where hydrogens are always explicit
		AtomContainerManipulator.convertImplicitToExplicitHydrogens(ac);
		BufferedWriter outputWriter;
		int count_reaction = 0, count_reactant= 0;

		for (Slice sc : allSlice.values()) {
			count_reaction =0;
			for (SliceReaction scr : sc.getReactions()) {
				count_reactant =0;
				for (SliceMolecule mol : scr.getReactants()) {
					Integer reactant_specified = tryParseInt(reactant_number);
					//System.out.println("   =>" + mol.getSmarts());
					if(reactant_specified == null || (Integer.parseInt(mol.getId()) == reactant_specified)){
						Pattern ptrn = SmartsPattern.create(mol.getSmarts(), Smarts.FLAVOR_CACTVS);
						//System.out.println("   =>" + mol.getSmarts());
						// key:aam in Smarts value: atom index of the
						//matched atom in IAtomContainer
						List<Map<Integer, Integer>> mappings = new ArrayList<Map<Integer, Integer>>();
						//System.out.println(mol.getSmarts() + " " +ptrn.matches(ac));
						if (ptrn.matches(ac)) {
							String file_name= outputPath+transform_id+"_"+"compatibles_pattern_BB_"+count_reaction+"_"+count_reactant+"_"+outputName+".txt";
							outputWriter = new BufferedWriter(new FileWriter(file_name, true));
							outputWriter.write(smiles);
							outputWriter.newLine();
							outputWriter.close();
						}
					}
					count_reactant++;
				}
				count_reaction++;
			}
		}
	}


	/**
	 * safely parse a string to an integer only if it is a valid integer
	 * @param element
	 * @return null if the input isn't a valid integer
	 */
	private static Integer tryParseInt(String element) {
		if(element == null || element.isEmpty()){
			return null;
		}
		try {
			return Integer.parseInt(element.trim());
		}catch (NumberFormatException e){
			return null;
		}
	}
	/**
	 * count atom that have an atom atom mapping number
	 * @param ac
	 * @return
	 */
	private static int countAtomWithMapping(IAtomContainer ac) {
		int counter = 0;
		for (int i = 0; i < ac.getAtomCount(); i++) {
			IAtom atom = ac.getAtom(i);
			if (atom.getProperty(CDKConstants.ATOM_ATOM_MAPPING) != null) {
				//System.out.println(atom.getProperty(CDKConstants.ATOM_ATOM_MAPPING) + " ");
				counter++;
			}
		}
		return counter;
	}
	
	/**
	 * return map where key are the index of the atoms in the AtomContainer and the value their associated atom atom mapping constant
	 *  (the atom matched in SMARTS0
	 * @param ac
	 * @param aam
	 * @return {indexInTheAtomContainer:atomAtomMapping}
	 */
	private static Map<Integer, Integer> convertAAMapToIndexAtomConstantMap(IAtomContainer ac, Map<IAtom, IAtom> aam) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		//key:query atom in SMARTS value: atom in AC
		for (Entry<IAtom, IAtom> e : aam.entrySet()) {
			//System.out.println(e.getKey().getProperty(CDKConstants.ATOM_ATOM_MAPPING) + " ");
			//System.out.println(ac.indexOf(e.getValue()));
			//Atoms not in the reaction center don't have any mapping info
			if (e.getKey().getProperty(CDKConstants.ATOM_ATOM_MAPPING) != null)
				map.put(ac.indexOf(e.getValue()), (int)e.getKey().getProperty(CDKConstants.ATOM_ATOM_MAPPING));
		}
		return map;
	}
	
	/** chechk if an atom belongs to a ring in the IAtomcontainer
	 *  * @param ac
	 * @param at
	 * @throws CDKException 
	 */
	public static boolean IsAtomInRing(IAtomContainer ac,IAtom at) throws CDKException
	{
		AllRingsFinder rings = new AllRingsFinder();
		IRingSet ringSet = rings.findAllRings(ac);
		
		for(IAtomContainer ring: ringSet.atomContainers()) {
			if(ring.contains(at)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * configure atoms and bond in the AtomContainer related to their location related to the pattern. 
	 * ie all unmatched atom are off path and all atoms that matched the pattern are onpath
	 * @param ac
	 * @param aam
	 * @param onPath
	 * @throws CDKException 
	 */
	private static void configAtomContainer(IAtomContainer ac, Map<Integer, Integer> aam, Collection<IAtom> onPath) throws CDKException {
		int aamCounter = 100000;
		//System.out.println(aam);
		for (int i = 0; i < ac.getAtomCount(); i++) {
			IAtom atom = ac.getAtom(i);
			if (aam.containsKey(i)) {
				atom.setProperty(SliceConstants.ONPATH, true);
				atom.setProperty(SliceConstants.OFFPATH, false);
				atom.setProperty(CDKConstants.ATOM_ATOM_MAPPING, aam.get(i));
				//System.out.println("on " +atom.getSymbol() + " " +atom.getProperties());
			}
			else if (onPath.contains(atom)) {
				atom.setProperty(SliceConstants.ONPATH, true);
				atom.setProperty(SliceConstants.OFFPATH, false);
				atom.setProperty(CDKConstants.ATOM_ATOM_MAPPING, aamCounter);
				aamCounter++;
			}
			else {
				//allow to assign a different mapping
				/*while (aam.values().contains(aamCounter)) {
					aamCounter++;
				}*/
				atom.setProperty(SliceConstants.OFFPATH, true);
				atom.setProperty(SliceConstants.ONPATH, false);
				atom.setProperty(CDKConstants.ATOM_ATOM_MAPPING, aamCounter);
				aamCounter++;
				//System.out.println("off " +atom.getSymbol() + " " +atom.getProperties());
			}
			atom.setProperty(SliceConstants.ANYWHERE, true);
			atom.setProperty(SliceConstants.ONRING, IsAtomInRing(ac,atom));
			atom.setProperty(SliceConstants.OFFRING,  !IsAtomInRing(ac,atom));
			atom.setProperty(SliceConstants.ONTHEBRIDGE, false);
			atom.setProperty(SliceConstants.OFFTHEBRIDGE, false);
		}
		
		for (IBond b : ac.bonds()) {
			if ((boolean) b.getBegin().getProperty(SliceConstants.ONPATH) && 
					(boolean) b.getEnd().getProperty(SliceConstants.ONPATH)) {
				b.setProperty(SliceConstants.ONPATH, true);
				b.setProperty(SliceConstants.OFFPATH, false);
			}
			else {
				b.setProperty(SliceConstants.ONPATH, false);
				b.setProperty(SliceConstants.OFFPATH, true);
			}
			b.setProperty(SliceConstants.ANYWHERE, true);
			b.setProperty(SliceConstants.ONRING, false);
			b.setProperty(SliceConstants.OFFRING, false);
			b.setProperty(SliceConstants.ONTHEBRIDGE, false);
			b.setProperty(SliceConstants.OFFTHEBRIDGE, false);
		}
	}

	public static Map<String, String> getReactionSmirks() {
		return reactionSmirks;
	}

	public static void setCarbonCentered(Boolean carbonCentered) {
		SliceTester.carbonCentered = carbonCentered;
	}
	
}
