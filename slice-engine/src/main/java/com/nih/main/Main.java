package com.nih.main;

import static java.io.File.separator;
import static java.lang.System.out;
import static java.util.logging.Level.SEVERE;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.tools.ILoggingTool;
import org.openscience.cdk.tools.LoggingToolFactory;

import com.nih.parser.ChemicalFormatParser;
import com.nih.savi.Core;
import com.nih.tools.ColouredSystemOutPrintln;

public class Main {
	private static final ILoggingTool LOGGER = LoggingToolFactory.createLoggingTool(Main.class);

	public static void main(String[] args) throws CDKException, IOException, ParseException {
		try {
			CommandLineOptions cmd = new CommandLineOptions();
			Options createSaviALaCarteOptions = cmd.createSaviALaCarteOptions();

			DefaultParser parser1 = new DefaultParser();
			CommandLine saviALaCarteOptions = parser1.parse(createSaviALaCarteOptions, args, true);

			/*
			 * Print the Header
			 */
			Helper.getHeader();
			if (saviALaCarteOptions.hasOption('b') && saviALaCarteOptions.hasOption('s')) {

				if (saviALaCarteOptions.hasOption('v')) {
					System.out.println(ColouredSystemOutPrintln.ANSI_PURPLE + "Count compatible BBs"
							+ ColouredSystemOutPrintln.ANSI_RESET);
					countCompatibleBBS(saviALaCarteOptions, createSaviALaCarteOptions, "");
				} else {
					if (saviALaCarteOptions.hasOption('m')) {
						System.out.println(ColouredSystemOutPrintln.ANSI_PURPLE + "Count matching BBs"
								+ ColouredSystemOutPrintln.ANSI_RESET);
						countCompatibleBBS_pattern(saviALaCarteOptions, createSaviALaCarteOptions, "");
					} else {
						System.out.println(ColouredSystemOutPrintln.ANSI_PURPLE + "Generate Molecules"
								+ ColouredSystemOutPrintln.ANSI_RESET);
						generateMolecules(saviALaCarteOptions, createSaviALaCarteOptions, "");
					}

				}
			}
			else {
				out.println("-- HELP --");
				if (saviALaCarteOptions.hasOption('e')) {
					Helper.printUsageExamples();
				} else {
					Map<String, Options> options = new LinkedHashMap<String, Options>();
					options.put("SAVI a la carte options", createSaviALaCarteOptions);
					Helper.printHelp(options, 80, "SAVI a la carte", "End of Help", 5, 3, true, out);
				}
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} /*catch (ParseException ex) {
			LOGGER.error(SEVERE, null, ex);
		} catch (Exception ex) {
			LOGGER.error(SEVERE, null, ex);
		}*/
	}
	
	private static void generateMolecules(CommandLine commands, Options options, String format) throws CDKException, IOException, CloneNotSupportedException {
		String outputDirectory,outputName,lowerRating,ghostMolecules,inchikey;
		//parse option and remove non-ASCII char
		String buildingBLockFile = commands.getOptionValue("b").replaceAll("[^\\x00-\\x7F]", "");
		String sliceChemistryFolder = commands.getOptionValue("s").replaceAll("[^\\x00-\\x7F]", "");
		if (commands.hasOption('o')) {
			outputDirectory = new File(commands.getOptionValue('o')).getCanonicalPath() + separator;
		} else {
			if (new File(buildingBLockFile).isFile()) {
				outputDirectory = Paths.get(buildingBLockFile).toAbsolutePath().getParent() + separator;
			} else {
				outputDirectory = Paths.get(buildingBLockFile).toAbsolutePath() + separator;
			}
		}
		if (commands.hasOption('l')){
			lowerRating = "activated";
		}
		else {
			lowerRating = "desactivated";
		}
		
		if (commands.hasOption('g')){
			ghostMolecules = "activated";
		}
		else {
			ghostMolecules = "desactivated";
		}
		if (commands.hasOption('i')){
			inchikey = "activated";
		}
		else {
			inchikey = "desactivated";
		}
		System.out.println(sliceChemistryFolder);

		Path path_output =  Paths.get(sliceChemistryFolder);
		String file_extension = path_output.getFileName().toString();
		int dotIndex = file_extension.lastIndexOf(".");
		String name_output_slice =(dotIndex == -1) ?file_extension : file_extension.substring(0,dotIndex);

		Path path_output_end =  Paths.get(buildingBLockFile);
		String file_extension2 = path_output_end.getFileName().toString();
		int dotIndex2 = file_extension2.lastIndexOf(".");
		String name_output_bbs =(dotIndex2 == -1) ?file_extension2 : file_extension2.substring(0,dotIndex2);
		outputName = "results_"+name_output_slice+"_"+name_output_bbs;

		Core core = new Core();
		core.setNumOfCores(4096);
		System.out.println("Building block files :"+ buildingBLockFile);
		System.out.println("SLICE chemistry folder :"+ sliceChemistryFolder);
		System.out.println("Output directory :"+ outputDirectory);
		System.out.println("Output name :"+ outputName);

		core.setBbPath(buildingBLockFile);
		core.setSliceChemistryFolder(sliceChemistryFolder);
		core.setSliceFilepath(sliceChemistryFolder);
		core.setOutputPath(outputDirectory);
		core.setOutputName(outputName);
		core.setLowerRating(lowerRating);
		core.setGhostMolecules(ghostMolecules);
		core.setInchikey(inchikey);
		core.exec();
	}
	
	private static void countCompatibleBBS(CommandLine commands, Options options, String format) throws CDKException, IOException, CloneNotSupportedException {
		String outputDirectory,outputName;
		//parse option and remove non-ASCII char
		String buildingBLockFile = commands.getOptionValue("b").replaceAll("[^\\x00-\\x7F]", "");
		String sliceChemistryFolder = commands.getOptionValue("s").replaceAll("[^\\x00-\\x7F]", "");
		if (commands.hasOption('o')) {
			outputDirectory = new File(commands.getOptionValue('o')).getCanonicalPath() + separator;
		} else {
			if (new File(buildingBLockFile).isFile()) {
				outputDirectory = Paths.get(buildingBLockFile).toAbsolutePath().getParent() + separator;
			} else {
				outputDirectory = Paths.get(buildingBLockFile).toAbsolutePath() + separator;
			}
		}

		System.out.println(sliceChemistryFolder);

		Path path_output =  Paths.get(sliceChemistryFolder);
		String file_extension = path_output.getFileName().toString();
		int dotIndex = file_extension.lastIndexOf(".");
		String name_output_slice =(dotIndex == -1) ?file_extension : file_extension.substring(0,dotIndex);


		Path path_output2 =  Paths.get(buildingBLockFile);
		String file_extension2 = path_output2.getFileName().toString();
		int dotIndex2 = file_extension2.lastIndexOf(".");
		String name_output_bb =(dotIndex2 == -1) ?file_extension2 : file_extension2.substring(0,dotIndex2);
		outputName = "results_"+name_output_slice+"_"+name_output_bb;

		Core core = new Core();
		core.setNumOfCores(-1);
		core.setBbPath(buildingBLockFile);
		core.setSliceChemistryFolder(sliceChemistryFolder);
		core.setOutputPath(outputDirectory);
		core.setOutputName(outputName);
		core.countBBS();
	}

	private static void countCompatibleBBS_pattern(CommandLine commands, Options options, String format) throws CDKException, IOException, CloneNotSupportedException {
		String outputDirectory,outputName;
		//parse option and remove non-ASCII char
		String buildingBLockFile = commands.getOptionValue("b").replaceAll("[^\\x00-\\x7F]", "");
		String sliceChemistryFolder = commands.getOptionValue("s").replaceAll("[^\\x00-\\x7F]", "");
		if (commands.hasOption('o')) {
			outputDirectory = new File(commands.getOptionValue('o')).getCanonicalPath() + separator;
		} else {
			if (new File(buildingBLockFile).isFile()) {
				outputDirectory = Paths.get(buildingBLockFile).toAbsolutePath().getParent() + separator;
			} else {
				outputDirectory = Paths.get(buildingBLockFile).toAbsolutePath() + separator;
			}
		}

		System.out.println(sliceChemistryFolder);

		Path path_output =  Paths.get(sliceChemistryFolder);
		String file_extension = path_output.getFileName().toString();
		int dotIndex = file_extension.lastIndexOf(".");
		String name_output_slice =(dotIndex == -1) ?file_extension : file_extension.substring(0,dotIndex);


		Path path_output2 =  Paths.get(buildingBLockFile);
		String file_extension2 = path_output2.getFileName().toString();
		int dotIndex2 = file_extension2.lastIndexOf(".");
		String name_output_bb =(dotIndex2 == -1) ?file_extension2 : file_extension2.substring(0,dotIndex2);
		outputName = "results_"+name_output_slice+"_"+name_output_bb;

		Core core = new Core();
		core.setNumOfCores(-1);
		core.setBbPath(buildingBLockFile);
		core.setSliceChemistryFolder(sliceChemistryFolder);
		core.setOutputPath(outputDirectory);
		core.setOutputName(outputName);
		core.countBBS_matching();
	}
}
