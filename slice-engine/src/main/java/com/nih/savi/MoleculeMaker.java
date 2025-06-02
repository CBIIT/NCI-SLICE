package com.nih.savi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.depict.DepictionGenerator;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IAtomContainerSet;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IReaction;
import org.openscience.cdk.io.MDLV2000Writer;
import org.openscience.cdk.io.SDFWriter;
import org.openscience.cdk.isomorphism.Pattern;
import org.openscience.cdk.layout.StructureDiagramGenerator;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smarts.Smarts;
import org.openscience.cdk.smarts.SmartsPattern;
import org.openscience.cdk.smiles.SmilesGenerator;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.smiles2.SmilesParser2;
import org.openscience.cdk.tools.manipulator.AtomContainerManipulator;

import com.nih.smirks.Transformer;
import com.nih.tools.Tools;

import static org.openscience.cdk.interfaces.IBond.Order.DOUBLE;
import static org.openscience.cdk.interfaces.IBond.Order.SINGLE;
import static org.openscience.cdk.interfaces.IBond.Order.UNSET;

public class MoleculeMaker {

	private String outputFile;
	private String outputFileSDF;
	private int threads = 8;
	private Map<Integer, List<IAtomContainer>> lefts;
	private Map<Integer, List<IAtomContainer>> rights;
	private List<IAtomContainer> allRights = new ArrayList<IAtomContainer>();
	
	//Identifiers
	//Unique SAVI identifier in the form <hashcode of product>_<hashcode of reactants>_<transform id>
	private boolean name = false; 
	//SMILES of the product
	private boolean smiles = true;
	//transform id name and version
	private Integer transformation_id;
	private Integer transform_version;
	private String transform_name;
	private String transform_main_product;
	private int nb_reactions = 0;

	//Properties referring to starting materials
	//Sigma-Aldrich catalog ID of building block A
	private boolean savi_building_block_a_sigma_strid = false;
	//SMILES of building block A
	private boolean savi_building_block_a_smiles = false; 
	//InChI of building block A
	private boolean savi_building_block_a_inchi = false; 
	//InChIKey of building block A
	private boolean savi_building_block_a_inchikey = false;
	//URL of the Sigma-Aldrich catalog web page for building block A
	private boolean savi_building_block_a_order_link = false; 
	//Sigma-Aldrich catalog ID of building block B
	private boolean savi_building_block_b_sigma_strid = false; 
	//SMILES of building block B
	private boolean savi_building_block_b_smiles = false;
	//InChI of building block B
	private boolean savi_building_block_b_inchi = false;
	//InChIKey of building block B
	private boolean savi_building_block_b_inchikey = false;
	//URL of the Sigma-Aldrich catalog web page for the building block B
	private boolean savi_building_block_b_order_link = false;
	//Indicates whether protection of reagent A is required in this reaction
	private boolean savi_building_block_a_protection_needed = false;
	//Indicates whether building block A used in this reaction is already a protected version of the required reagent
	private boolean savi_building_block_a_protected = false;
	//Indicates whether protection of reagent B is required in this reaction
	private boolean e_savi_building_block_b_protection_needed = false;
	//Indicates whether building block B used in this reaction is already a protected version of the required reagent
	private boolean savi_building_block_b_protected = false;
	//Name of the reaction
	private boolean e_savi_proposed_reaction = false;
	//ID of the reaction (LHASA ID of the transform that describes this reaction)
	private boolean savi_proposed_reaction_id = false;
	//Hashcode of the reaction
	private boolean savi_reaction_hashcode = false;
	//Reaction conditions according to LHASA transform rules
	private boolean savi_reaction_conditions = false;
	//Reaction warnings according to LHASA transform rules
	private boolean savi_reaction_warnings = false;
	//Cost per gram of building block A
	private boolean savi_building_block_a_cost_gram = false;
	//Cost per gram of building block B
	private boolean savi_building_block_b_cost_gram = false;
	//Total cost per gram of building block A and B
	private boolean savi_estimated_bb_cost_gram = false;
	//Cost per mole of building block A
	private boolean savi_building_block_a_cost_mol = false;
	//Cost per mole of building block B
	private boolean savi_building_block_b_cost_mol = false;
	//Total cost per mole of building block A and B
	private boolean savi_estimated_bb_cost_mol = false;

	//Properties referring to the reaction
	//"Quality" score of this reaction according to LHASA transform scoring scheme
	private boolean savi_score = true;
	//Qualitative description of estimated yield of this reaction according to LHASA transform (if available)
	private boolean savi_predicted_yield = false;

	//Properties referring to the product
	//Bruns and Watson Demerit score (see Bruns, R.F., Watson, I.A. Rules for identifying potentially reactive or promiscuous compounds. J. Med. Chem. 2012, 55,9763-9772)
	private boolean bruns_watson_demerit_score = false;
	//Breakdown of Bruns and Watson Demerits
	private boolean bruns_watson_demerit_components = false;
	//PAINS Filter Match
	private boolean savi_pains_filter = false;
	//Name of the PAINS filter match
	private boolean savi_pains_filter_match_name = false;
	//Number of Lipinski "rule of 5" violations
	private boolean rule_of_5_violations = false;
	//Number of "rule of 3 violations"
	private boolean rule_of_3_violations = false;
	//Number of H-bond donors
	private boolean nhdonors = false;
	//Number of H-bond acceptors
	private boolean nhacceptors = false;
	//Molecular weight
	private boolean weight = false;
	//Number of heavy atoms
	private boolean heavy_atom_count = false;
	//Number of rotatable bonds
	private boolean nrotbonds = false;
	//Xlogp2
	private boolean xlogp2 = false;
	//Xlogp
	private boolean xlogp = false;
	//Fraction of sp3-hybridized carbons
	private boolean fsp3 = false;
	//Number of stereo centers
	private boolean stereo_count = false;
	//Total polar surface area
	private boolean tpsa = false;
	//Benzenoid index
	private boolean benzenoid_index = false;
	//Molecular formula
	private boolean formula = false;
	//Genotoxic alerts (see: CE&N Sep 27, 2010, p16ff)
	private boolean genotoxic_alerts = false;
	//Complexity (see: W. D. Ihlenfeldt, Ph.D. Thesis, TU Munich, Germany, 1991)
	private boolean complexity = false;
	//InChI
	private boolean inchi = false;
	//InChIKey
	private boolean inchikey = false;
	//PubChem CID of the compound matching the product (if found using stereo-sensitive lookup)
	private boolean savi_pubchem_stereo_cid_match = false;
	//PubChem CID of the compound matching the product (if found using tautomer-sensitive lookup. Not implemented currently)
	private boolean savi_pubchem_tauto_cid_match = false;
	//Sigma-Aldrich "Aldrich Market Select" SID of the compound matching the product (if found using stereo-sensitive lookup)
	private boolean savi_ams_sid_match = false;
	//Number of charged groups
	private boolean charged_group_counts = false;
	//Number of hydrogen bond centers
	private boolean hydrogen_bond_center_count = false;
	//Number of smallest set of smallest rings (SSSR)
	private boolean savi_sssr_count = false;
	//SSSR count in product minus combined SSSR count in building blocks
	private boolean savi_sssr_count_change = false;
	//Number of aliphatic rings in product
	private boolean savi_aliring_count = false;
	//Number of aliphatic rings in product minus combined number of aliphatic rings in building blocks
	private boolean savi_aliring_count_change = false;
	//Number of aromatic rings in product
	private boolean savi_aroring_count = false;
	//Number of aromatic rings in product minus combined number of aromatic rings in building blocks
	private boolean savi_aroring_count_change = false;
	
	private Map<String, Map<String, Boolean>> reactionPAINSFilter;
	private BufferedWriter outputWriter;
	private String smirks;
	private boolean calculateProperties = true;
	
	//TODO (for later maybe) contains informations about reaction center
	private String[][] forwardReactions;
	private String[][] retroReactions;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public MoleculeMaker(String smirks) {
		//System.out.println("smirks"+smirks);
		String new_smirks="";
		int j,k,count;
		for (int i=0; i < smirks.length(); i++)
		{
			j= i+1;
			k=j+1;
			if(smirks.charAt(i) == '(' && smirks.charAt(j) == '[')
			{
				if(smirks.charAt(k) == '!' || smirks.charAt(k) == '$' )
				{
						count =1;
						do
						{
							i++;
							if(smirks.charAt(i) == '(')
							{
								count++;
							}
							if(smirks.charAt(i) == ')')
							{
								count--;
							}
							
						}while(count !=0);
						
				}
				
			}
			
			else
			{
				new_smirks+=smirks.charAt(i);
			}
			
		}
		//System.out.println("newv smirks"+new_smirks);
		this.smirks = new_smirks;
	}
	
	/**
	 * divide the most prominent set of molecule into chunks, which need o combine with all other compatible molecules.
	 * A thread will manage one chunk.
	 * 
	 * CSV transform precomputing
	 * example: Sulfonamide
	 * Left Score Right Score
	 * smi1  X    Smi2   Y
	 * 
	 * Smiles are formated with atom atom mapping for the reaction center only ex CC[N:1]
	 * @param file
	 * @return
	 */
	private List<Map<Integer, List<IAtomContainer>>>  prepareChunksForMultiThreading(String file) {
		int molCount = 0;
		String ref = null;
		List<IAtomContainer> compatibleMoleculesRefSet = null;
		
		BufferedReader reader;
		try {reader = new BufferedReader(new FileReader(file));
			while (reader.readLine() != null) molCount++;
			reader.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//split the biggest set for multi threading
		int targetPerThread = molCount > threads ? (int) (molCount/threads) : molCount;
		int chunkCount = 0;
		int cpt = 0;
				
		SmilesParser sp = new SmilesParser(SilentChemObjectBuilder.getInstance());
		List<IAtomContainer> chunk = new ArrayList<IAtomContainer>();
		Map<Integer, List<IAtomContainer>> chunks = new HashMap<Integer, List<IAtomContainer>>();
		Map<Integer, List<IAtomContainer>> otherComaptibleMolecules = new HashMap<Integer, List<IAtomContainer>>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				String[] elts = line.split("\t");
				for (int i = 1; i < elts.length; i+=2) {
					if (elts[i].contains("NULL")) {
						IAtomContainer ac = sp.parseSmiles(elts[i]);
						ac.setProperty("sliceScore", Integer.parseInt(elts[i+1]));
						if (i == 1)
							chunk.add(ac);
						else {
							if (otherComaptibleMolecules.containsKey(i)) {
								otherComaptibleMolecules.get(i).add(ac);
							}
							else {
								List<IAtomContainer> mols = new ArrayList<IAtomContainer>();
								mols.add(ac);
								otherComaptibleMolecules.put(i, mols);
							}
						}
					}
				}
				if (cpt == targetPerThread) {
					chunks.put(chunkCount, chunk);
					chunk = new ArrayList<IAtomContainer>();
					//rights.put(chunk, rightsChunk);
					//rightsChunk = new ArrayList<IAtomContainer>();
					cpt = 0;
					chunkCount++;
				}
				cpt++;
			}
			reader.close();
		} catch (IOException | InvalidSmilesException e) {
			e.printStackTrace();
		}
		
		//add the last chunk (if still cpt < targetPerThread, the last check is not added, so refChunk is not empty)
		if (!chunk.isEmpty()) {
			chunks.put(chunkCount, chunk);
			chunk = new ArrayList<IAtomContainer>();
			cpt = 0;
			chunkCount++;
		}
		//if (!rightsChunk.isEmpty()) {
			//rights.put(chunk, rightsChunk);
			//rightsChunk = new ArrayList<IAtomContainer>();
		//}
		
		List<Map<Integer, List<IAtomContainer>>> result = new ArrayList<Map<Integer, List<IAtomContainer>>>();
		result.add(chunks);
		result.add(otherComaptibleMolecules);
		System.out.println("chunk " + chunkCount + " target per thread "+ targetPerThread + " threads " + threads);
		return result;
	}
	
	/**
	 * divide the most prominent set of molecule into chunks, which need o combine with all other compatible molecules.
	 * A thread will manage one chunk.
	 * @param comp
	 * @return
	 */
	private Map<Integer, List<IAtomContainer>> prepareChunksForMultiThreading(Map<String, List<IAtomContainer>> comp) {
		int molCount = 0;
		String ref = null;
		List<IAtomContainer> compatibleMoleculesRefSet = null;
		
		//get the most prominent set
		for (Entry<String, List<IAtomContainer>> e : comp.entrySet()) {
			String sliceMolId = e.getKey();
			List<IAtomContainer> compatibleMolecules = e.getValue();
			int numberOfMolecule = compatibleMolecules.size();


			//System.out.println("number of mols :"+ numberOfMolecule);
			if (numberOfMolecule > molCount) {
				ref = sliceMolId;
				molCount = numberOfMolecule;
				compatibleMoleculesRefSet = compatibleMolecules;
			}
			
		}


		//System.out.println("test : "+ Runtime.getRuntime().availableProcessors()+ "threads "+threads );
		comp.remove(ref);
		
		//split the biggest set for multi threading
		int targetPerThread = molCount > threads ? (int) (molCount/threads) : molCount;
		int chunkCount = 0;
		int cpt = 0;
		List<IAtomContainer> chunk = new ArrayList<IAtomContainer>();
		Map<Integer, List<IAtomContainer>> chunks = new HashMap<Integer, List<IAtomContainer>>();
		for (IAtomContainer ac : compatibleMoleculesRefSet) {
			ac.setProperty("sliceScore", ac.getProperty("rating"));
			chunk.add(ac);
			if (cpt == targetPerThread) {
				chunks.put(chunkCount, chunk);
				chunk = new ArrayList<IAtomContainer>();
				cpt = 0;
				chunkCount++;
			}
			cpt++;
		}

		//add the last chunk (if still cpt < targetPerThread, the last check is not added, so refChunk is not empty)
		if (!chunk.isEmpty()) {
			chunks.put(chunkCount, chunk);
			chunk = new ArrayList<IAtomContainer>();
			cpt = 0;
			chunkCount++;
		}
		
		System.out.println("chunk " + chunkCount + " target per thread "+ targetPerThread + " threads " + threads);
		return chunks;
	}
	
	//ID_subID SMIRKS TODO complete with other SAVI reaction
	/**
	 * 
	 */
	private void initReactionProperties() {
		forwardReactions = new String[][] {
				{"0001_1","[c:1]S(=O)(=[N;H1])[N;H0;z1:3].[c:1][Cl,Br,I:2]>>[N;z0;H2:3]"},
				};
		retroReactions = new String[][] {
				{"0001_1","[N;z0;H2:3]>>[c:1]S(=O)(=[N;H1])[N;H0;z1:3].[c:1][Cl,Br,I:2]"},
				};
	}
	
	//TODO determine pains filter by guessing if a pattern has been changed.
	/**
	 * this function is check if the reaction center might generate a new pain filter
	 */
	private void initReactionPAINSFilter() {
		PAINSFilter pf = new PAINSFilter();
		reactionPAINSFilter = pf.processReactionCenter(smirks);
	}
	

	/**
	 * generates the molecules by combining compatible molecules or splitting a molecule (retrosynthetic)
	 * @param map
	 */
	public void makeMolecules(String file) {
		
		List<Map<Integer, List<IAtomContainer>>> res = prepareChunksForMultiThreading(file);
		Map<Integer, List<IAtomContainer>> chunks = res.get(0);
		Map<Integer, List<IAtomContainer>> other = res.get(1);
		//TODO improve pain filters management
		//initReactionPAINSFilter();
		
		String header = writeCSVHeader();

		List<CompletableFuture<Boolean>> futures = new ArrayList<CompletableFuture<Boolean>>();
		for (int i : chunks.keySet()) {
			CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> combine(i, chunks, other.values()));
			futures.add(future);
		}
		CompletableFuture<Object> combinedFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenApplyAsync(aVoid -> futures.stream().
                        map(future -> future.join()).
                        collect(Collectors.<Object>toList())
                        );
		
		try {
			combinedFuture.get();
			System.out.println("Merge files");
			//merge CSV and add writeCSVHeader
			outputWriter = new BufferedWriter(new FileWriter(outputFile)); 
			outputWriter.write(header);
			outputWriter.newLine();
			for (int i : chunks.keySet()) {
				System.out.println(outputFile.split("\\.")[0]+"_temp"+i+".csv");
				BufferedReader reader = new BufferedReader(new FileReader(outputFile.split("\\.")[0]+"_temp"+i+".csv"));
				String line;
				while ((line = reader.readLine()) != null) {
					outputWriter.write(line);
					outputWriter.newLine();
				}
				reader.close();
				//delete temp file
				new File(outputFile.split("\\.")[0]+"_temp"+i+".csv").delete(); 
			}
			outputWriter.close();
		} catch (InterruptedException | ExecutionException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * generates the molecules by combining compatible molecules or splitting a molecule (retrosynthetic)
	 * @param map
	 */
	public void makeMolecules(Map<String, List<IAtomContainer>> map) {
		Map<Integer, List<IAtomContainer>> chunks = prepareChunksForMultiThreading(map);
		//TODO improve pain filters management
		//initReactionPAINSFilter();
		String header = writeCSVHeader();

		List<CompletableFuture<Boolean>> futures = new ArrayList<CompletableFuture<Boolean>>();
		for (int i : chunks.keySet()) {
			CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> combine(i, chunks, map.values()));
			futures.add(future);
		}
		CompletableFuture<Object> combinedFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenApplyAsync(aVoid -> futures.stream().
                        map(future -> future.join()).
                        collect(Collectors.<Object>toList())
                        );
		try {
			combinedFuture.get();
			System.out.println("Merge files");
			//merge CSV and add writeCSVHeader
			outputWriter = new BufferedWriter(new FileWriter(outputFile)); 
			outputWriter.write(header);
			outputWriter.newLine();
			for (int i : chunks.keySet()) {
				System.out.println(outputFile.split("\\.")[0]+"_temp"+i+".csv");
				BufferedReader reader = new BufferedReader(new FileReader(outputFile.split("\\.")[0]+"_temp"+i+".csv"));
				String line;
				while ((line = reader.readLine()) != null) {
					outputWriter.write(line);
					outputWriter.newLine();
				}
				reader.close();
				//delete temp file
				new File(outputFile.split("\\.")[0]+"_temp"+i+".csv").delete();
			}
			outputWriter.close();
			
			//sdf file
			//outputWriter = new BufferedWriter(new FileWriter(outputFileSDF));
			outputWriter = new BufferedWriter(new FileWriter(outputFile.split("\\.")[0].concat(".id")));
			for (int i : chunks.keySet()) {
				System.out.println("outputFile : "+outputFile);
				System.out.println(outputFile.split("\\.")[0]+"_temp"+i+".id");
				BufferedReader reader = new BufferedReader(new FileReader(outputFile.split("\\.")[0]+"_temp"+i+".id"));
				String line;
				while ((line = reader.readLine()) != null) {
					outputWriter.write(line);
					outputWriter.newLine();
				}
				reader.close();
				//delete temp file
				new File(outputFile.split("\\.")[0]+"_temp"+i+".id").delete();
			}
			outputWriter.close();
		} catch (InterruptedException | ExecutionException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Reactions number :" + nb_reactions);
		
	}
	
	
	/**
	 * combine all compatible molecules and apply the associated reaction template (already defined) using Transformer class
	 * @param chunk
	 * @param chunks
	 * @param otherReactants
	 * @return
	 */
	private boolean combine(int chunk, Map<Integer, List<IAtomContainer>> chunks, Collection<List<IAtomContainer>> otherReactants) {
		BufferedWriter writer = null;
		BufferedWriter writer_sdf = null;

		try {
			writer = new BufferedWriter(new FileWriter(outputFile.split("\\.")[0]+"_temp"+chunk+".csv"));
			writer_sdf = new BufferedWriter(new FileWriter(outputFile.split("\\.")[0]+"_temp"+chunk+".id"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//change by combinatorial algorithm
		List<List<IAtomContainer>> start = new ArrayList<List<IAtomContainer>>();
		System.out.println("chunk number "+chunk + " ref size "+chunks.get(chunk).size());
		start.add(chunks.get(chunk));
		//start.add(rights.get(chunk));
		start.addAll(otherReactants);
		GenerateAllCombinationsFromMultipleLists(start, 0, new ArrayList<IAtomContainer>(), writer, writer_sdf);
		System.out.println("combine DONE for chunk "+chunk);
		try {
			writer.close();
			writer_sdf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	
	
	/**
	 * Generate combination from multiple lists
	 * X: [1, 2] 
	 * Y: [3, 4, 5, 6]
	 * Result: [[1, 3], [1, 4], [1, 5], [1, 6], [2, 3], [2, 4], [2, 5], [2, 6]]
	 * if parameter intraCombinations is true, generate combination between the lists and for each combination 
	 * generate all permutations inside the permuted list
	 * X: [1, 2] 
	 * [3, 4, 5, 6]
	 * Result: [[1, 3], [1, 4], [1, 5], [1, 6], [2, 3], [2, 4], [2, 5], [2, 6], [1, 4, 3], [1, 5, 3], [1, 5, 4], [1, 6, 3], 
	 * [1, 6, 4], [1, 6, 5], [2, 4, 3], [2, 5, 3], [2, 5, 4], [2, 6, 3], [2, 6, 4], [2, 6, 5], [2, 1, 3], [2, 1, 4], [2, 1, 5], 
	 * [2, 1, 6], [1, 5, 3, 4], [1, 6, 3, 4], [1, 6, 3, 5], [1, 6, 4, 5], [2, 5, 3, 4], [2, 6, 3, 4], [2, 6, 3, 5], [2, 6, 4, 5], 
	 * [2, 1, 4, 3], [2, 1, 5, 3], [2, 1, 5, 4], [2, 1, 6, 3], [2, 1, 6, 4], [2, 1, 6, 5], [1, 6, 3, 4, 5], [2, 6, 3, 4, 5], 
	 * [2, 1, 5, 3, 4], [2, 1, 6, 3, 4], [2, 1, 6, 3, 5], [2, 1, 6, 4, 5], [2, 1, 6, 3, 4, 5]]
	 * https://stackoverflow.com/questions/17192796/generate-all-combinations-from-multiple-lists
	 * https://javahungry.blogspot.com/2014/02/algorithm-for-combinations-of-string-java-code-with-example.html
	 * https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
	 * @param Lists
	 * @param depth
	 * @param current
	 * @param writer
	 */
	private void GenerateAllCombinationsFromMultipleLists(List<List<IAtomContainer>> Lists, int depth, List<IAtomContainer> current,
			BufferedWriter writer,BufferedWriter writer_id_prod) {
		if(depth == Lists.size()) {
			//prevent missing reactants
			if (current.size() == depth) {
				IAtomContainerSet reactants = DefaultChemObjectBuilder.getInstance().newInstance(IAtomContainerSet.class);
				int score = 0;
				for (IAtomContainer ac : current) {
					try {
						IAtomContainer clone = ac.clone();
						reactants.addAtomContainer(clone);
						score += (int) clone.getProperty("sliceScore");
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Transformer transformer = new Transformer();
				transformer.setAamDone(true);
				transformer.setStoichiometry(1);
				List<IReaction> reactions = transformer.transform(reactants, smirks);
				for (IReaction reaction : reactions) {
					nb_reactions++;
					writeReactionToCSV(reaction, writer, score);
					writeReaction_id_product(reaction,writer_id_prod);
					//writeReactionToSDF(reaction, writer_sdf, score);
				}
			}
			return;
		}

		for(int i = 0; i < Lists.get(depth).size(); i++) {
			IAtomContainer ac = Lists.get(depth).get(i);

			List<IAtomContainer> temp = new ArrayList<IAtomContainer>(current);
			temp.add(ac);
			GenerateAllCombinationsFromMultipleLists(Lists, depth + 1, temp, writer,writer_id_prod);
		}
	}
	
	
	
	/**
	 * Generate combination from multiple lists
	 * X: [1, 2] 
	 * Y: [3, 4, 5, 6]
	 * Result: [[1, 3], [1, 4], [1, 5], [1, 6], [2, 3], [2, 4], [2, 5], [2, 6]]
	 * if parameter intraCombinations is true, generate combination between the lists and for each combination 
	 * generate all permutations inside the permuted list
	 * X: [1, 2] 
	 * [3, 4, 5, 6]
	 * Result: [[1, 3], [1, 4], [1, 5], [1, 6], [2, 3], [2, 4], [2, 5], [2, 6], [1, 4, 3], [1, 5, 3], [1, 5, 4], [1, 6, 3], 
	 * [1, 6, 4], [1, 6, 5], [2, 4, 3], [2, 5, 3], [2, 5, 4], [2, 6, 3], [2, 6, 4], [2, 6, 5], [2, 1, 3], [2, 1, 4], [2, 1, 5], 
	 * [2, 1, 6], [1, 5, 3, 4], [1, 6, 3, 4], [1, 6, 3, 5], [1, 6, 4, 5], [2, 5, 3, 4], [2, 6, 3, 4], [2, 6, 3, 5], [2, 6, 4, 5], 
	 * [2, 1, 4, 3], [2, 1, 5, 3], [2, 1, 5, 4], [2, 1, 6, 3], [2, 1, 6, 4], [2, 1, 6, 5], [1, 6, 3, 4, 5], [2, 6, 3, 4, 5], 
	 * [2, 1, 5, 3, 4], [2, 1, 6, 3, 4], [2, 1, 6, 3, 5], [2, 1, 6, 4, 5], [2, 1, 6, 3, 4, 5]]
	 * https://stackoverflow.com/questions/17192796/generate-all-combinations-from-multiple-lists
	 * https://javahungry.blogspot.com/2014/02/algorithm-for-combinations-of-string-java-code-with-example.html
	 * https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
	 * @param Lists
	 * @param result
	 * @param depth
	 * @param current
	 */
	private void GenerateAllCombinationsFromMultipleLists(List<List<IAtomContainer>> Lists, List<IReaction> generatedReactions, int depth, 
			List<IAtomContainer> current, BufferedWriter writer) {
		if(depth == Lists.size()) {
			IAtomContainerSet reactants = DefaultChemObjectBuilder.getInstance().newInstance(IAtomContainerSet.class);
			int score = 0;
			for (IAtomContainer ac : current) {
				reactants.addAtomContainer(ac);
				score += (int) ac.getProperty("sliceScore");
			}
			Transformer transformer = new Transformer();
			transformer.setAamDone(true);
			transformer.setStoichiometry(1);
			List<IReaction> reactions = transformer.transform(reactants, smirks);
			for (IReaction reaction : reactions) {
				writeReactionToCSV(reaction, writer, score);
			}
			return;
		}

		for(int i = 0; i < Lists.get(depth).size(); i++) {
			IAtomContainer ac = Lists.get(depth).get(i);

			List<IAtomContainer> temp = new ArrayList<IAtomContainer>(current);
			temp.add(ac);

			GenerateAllCombinationsFromMultipleLists(Lists, generatedReactions, depth + 1, temp, writer);
		}
	}
	
	private void writeReactionToCSV(IReaction reaction, BufferedWriter writer, int score) {
		
		/*System.out.println(reaction.getReactantCount() + " "+reaction.getProductCount());
		for (int i = 0; i < reaction.getReactantCount(); i++) {
			System.out.println("id reactant"+reaction.getReactants().getAtomContainer(i).getProperty("emamineID"));
		}
		for (int i = 0; i < reaction.getProductCount(); i++) {
			System.out.println("id product "+reaction.getProducts().getAtomContainer(i).getProperty("emamineID"));
		}*/

		String line = "";
		String smi = null;
		try {
			Transformer transformer = new Transformer();
			smi = transformer.makeSmiles(reaction, true);
		} catch (CDKException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] split = smi.split(">");

		line += split[split.length-1] + "\t" + split[0] + "\t" + smi;
		line += "\t" + score;
		IAtomContainer product = reaction.getProducts().getAtomContainer(0);
		//0:elementName ; 1:includeTerminals ; 2:excludeAmides ; 3:checkAromaticity ; 4:salicylFlag
		Object[] params = {"*",Boolean.FALSE,Boolean.TRUE,Boolean.TRUE,Boolean.FALSE};
		
		if (calculateProperties) {
			SaviProperties sp = new SaviProperties();
			try {
				sp.setParameters(params);
				sp.calculate(product);

				if (savi_predicted_yield) {
					//TODO 
				}
				if (rule_of_5_violations) {
					line += "\t" + sp.getLipinskifailures5();
				}
				if (rule_of_3_violations) {
					line += "\t" + sp.getLipinskifailures3();
				}
				if (nhdonors) {
					line += "\t" + sp.gethBondDonors();
				}
				if (nhacceptors) {
					line += "\t" + sp.gethBondAcceptors();
				}
				if (weight) {
					line += "\t" + sp.getWeight() ;
				}
				if (heavy_atom_count) {
					line += "\t" + sp.getHeavyAtomCount();
				}
				if (nrotbonds) {
					line += "\t" + sp.getRotatablebonds();
				}
				if (xlogp2) {
					//TODO
				}
				if (xlogp) {
					line += "\t" + sp.getXlogP();
				}
				if (fsp3) {
					line += "\t" + sp.getFsp3();
				}
				if (stereo_count) {
					line += "\t" + sp.getSeCount();
				}
				if (tpsa) {
					line += "\t" + sp.getTpsa();
				}
				if (benzenoid_index) {
					//TODO 
				}
				if (formula) {
					//TODO 
				}
				if (genotoxic_alerts) {
					//TODO 
				}
				if (complexity) {
					line += "\t" + sp.getFmf() ;
				}
				if (inchi) {
					line += "\t" + sp.getInchi();
				}
				if (inchikey) {
					line += "\t" + sp.getInchikey();
				}
				if (charged_group_counts) {
					//TODO
				}
				if (hydrogen_bond_center_count) {
					//TODO
				}
				if (savi_sssr_count) {
					line += "\t" + sp.getSssrCount();
				}
				if (savi_sssr_count_change) {
					line += "\t" + SSSRCountChange(reaction);
				}
				if (savi_aliring_count) {
					line += "\t" + sp.getAliphaticRingCount();
				}
				if (savi_aliring_count_change) {
					line += "\t" + SSSRAliphaticCountChange(reaction);
				}
				if (savi_aroring_count) {
					line += "\t" + sp.getAromaticRingCount();
				}
				if (savi_aroring_count_change) {
					line += "\t" + SSSRAliphaticCountChange(reaction);
					
				}
				
				if (bruns_watson_demerit_score || bruns_watson_demerit_components || savi_pains_filter || savi_pains_filter_match_name) {
					line += brunsWatson(reaction, product, reactionPAINSFilter);
					if (bruns_watson_demerit_score) {
						//TODO https://pubs.acs.org/doi/full/10.1021/jm301008n  https://github.com/IanAWatson/Lilly-Medchem-Rules/tree/master/queries
					}
					if (bruns_watson_demerit_components) {
						//TODO https://pubs.acs.org/doi/full/10.1021/jm301008n  https://github.com/IanAWatson/Lilly-Medchem-Rules/tree/master/queries
					}
					if (savi_pains_filter) {
						//TODO https://github.com/rdkit/rdkit/tree/master/Code/GraphMol/FilterCatalog get SMARTS in pains_a pains_b pains_c
						//Faire algorithme pour evaluer si le reaction center est une substructure d'une smarts, si oui test Smarts sur le produit generer autrement pas besoin de tester et heriter des reactants
					}
					if (savi_pains_filter_match_name) {
						//TODO 
					}
				}
			} catch (CDKException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		try {
			writer.write(line);
			writer.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/* With ghost molecules*/
	private void writeReactionToCSV(IReaction reaction, BufferedWriter writer, int score,List<String> ghost_molecules) {
		String line = "";
		String smi = null;
		try {
			Transformer transformer = new Transformer();
			smi = transformer.makeSmiles(reaction, true);
			//System.out.println("smi"+smi); //STEFI
		} catch (CDKException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] split = smi.split(">");
		//products reactants
		

		line += split[split.length-1] + "\t" + split[0] + "\t" + smi;
		line += "\t" + score;
		IAtomContainer product = reaction.getProducts().getAtomContainer(0);
		//0:elementName ; 1:includeTerminals ; 2:excludeAmides ; 3:checkAromaticity ; 4:salicylFlag
		Object[] params = {"*",Boolean.FALSE,Boolean.TRUE,Boolean.TRUE,Boolean.FALSE};
		
		if (calculateProperties) {
			SaviProperties sp = new SaviProperties();
			try {
				sp.setParameters(params);
				sp.calculate(product);

				if (savi_predicted_yield) {
					//TODO 
				}
				if (rule_of_5_violations) {
					line += "\t" + sp.getLipinskifailures5();
				}
				if (rule_of_3_violations) {
					line += "\t" + sp.getLipinskifailures3();
				}
				if (nhdonors) {
					line += "\t" + sp.gethBondDonors();
				}
				if (nhacceptors) {
					line += "\t" + sp.gethBondAcceptors();
				}
				if (weight) {
					line += "\t" + sp.getWeight() ;
				}
				if (heavy_atom_count) {
					line += "\t" + sp.getHeavyAtomCount();
				}
				if (nrotbonds) {
					line += "\t" + sp.getRotatablebonds();
				}
				if (xlogp2) {
					//TODO
				}
				if (xlogp) {
					line += "\t" + sp.getXlogP();
				}
				if (fsp3) {
					line += "\t" + sp.getFsp3();
				}
				if (stereo_count) {
					line += "\t" + sp.getSeCount();
				}
				if (tpsa) {
					line += "\t" + sp.getTpsa();
				}
				if (benzenoid_index) {
					//TODO 
				}
				if (formula) {
					//TODO 
				}
				if (genotoxic_alerts) {
					//TODO 
				}
				if (complexity) {
					line += "\t" + sp.getFmf() ;
				}
				if (inchi) {
					line += "\t" + sp.getInchi();
				}
				if (inchikey) {
					line += "\t" + sp.getInchikey();
				}
				if (charged_group_counts) {
					//TODO
				}
				if (hydrogen_bond_center_count) {
					//TODO
				}
				if (savi_sssr_count) {
					line += "\t" + sp.getSssrCount();
				}
				if (savi_sssr_count_change) {
					line += "\t" + SSSRCountChange(reaction);
				}
				if (savi_aliring_count) {
					line += "\t" + sp.getAliphaticRingCount();
				}
				if (savi_aliring_count_change) {
					line += "\t" + SSSRAliphaticCountChange(reaction);
				}
				if (savi_aroring_count) {
					line += "\t" + sp.getAromaticRingCount();
				}
				if (savi_aroring_count_change) {
					line += "\t" + SSSRAliphaticCountChange(reaction);
					
				}
				
				if (bruns_watson_demerit_score || bruns_watson_demerit_components || savi_pains_filter || savi_pains_filter_match_name) {
					line += brunsWatson(reaction, product, reactionPAINSFilter);
					if (bruns_watson_demerit_score) {
						//TODO https://pubs.acs.org/doi/full/10.1021/jm301008n  https://github.com/IanAWatson/Lilly-Medchem-Rules/tree/master/queries
					}
					if (bruns_watson_demerit_components) {
						//TODO https://pubs.acs.org/doi/full/10.1021/jm301008n  https://github.com/IanAWatson/Lilly-Medchem-Rules/tree/master/queries
					}
					if (savi_pains_filter) {
						//TODO https://github.com/rdkit/rdkit/tree/master/Code/GraphMol/FilterCatalog get SMARTS in pains_a pains_b pains_c
						//Faire algorithme pour evaluer si le reaction center est une substructure d'une smarts, si oui test Smarts sur le produit generer autrement pas besoin de tester et heriter des reactants
					}
					if (savi_pains_filter_match_name) {
						//TODO 
					}
				}
			} catch (CDKException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		try {
			writer.write(line);
			writer.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeReactionToSDF(IReaction reaction, BufferedWriter writer_sdf, int score) {
		String smi = null;
		try {
			Transformer transformer = new Transformer();
			smi = transformer.makeSmiles(reaction, true);
		} catch (CDKException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SmilesGenerator smilesGenerator = new SmilesGenerator();
		SDFWriter writer = new SDFWriter(writer_sdf);
		StructureDiagramGenerator sdg = new StructureDiagramGenerator();
		String id_product="";
		for(Integer i = 0; i <reaction.getReactantCount(); i++) {
			IAtomContainer reactant = reaction.getReactants().getAtomContainer(i);
			id_product = id_product.concat((reactant.getProperty("emamineID")));
			id_product = id_product.concat("_");
			id_product = id_product.replace("-","_");
		}
		id_product = id_product.concat(Integer.toString(this.getTransformation_id()));
		//System.out.println("id_product :"+ id_product+ " product smart:"+ this.getTransform_main_product());
		for(Integer i = 0; i <reaction.getProductCount(); i++) {
			IAtomContainer product = reaction.getProducts().getAtomContainer(i);
			try {
				product.setProperty("Molecule Smile", smilesGenerator.create(product));
			} catch (CDKException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			AtomContainerManipulator.convertImplicitToExplicitHydrogens(product);

			
			//System.out.println(this.getTransform_main_product());
			SmilesParser2 sp = new SmilesParser2(SilentChemObjectBuilder.getInstance());
			IAtomContainer ac1;
			try {
				ac1 = sp.parseSmiles(smilesGenerator.create(product));
				//System.out.println(ptrn.matches(ac1));
				//if (ptrn.matches(ac1)) {
					//product.setProperty("Molecule Name", id_product);
					product.removeProperty("compatibleBB");
					product.setTitle(id_product);
					//product.setProperty("transform id",this.getTransformation_id());
					//product.setProperty("transform version",this.getTransform_version());
					//product.setProperty("transform name",this.getTransform_name());
					product.setProperty("sliceScore",score);
					//product.setProperty("slice reaction",smi);
					sdg.generateCoordinates(ac1);
					writer.write(product);
				//}
			} catch (InvalidSmilesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CDKException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}

	private void writeReaction_id_product(IReaction reaction, BufferedWriter writer_id_product) {

		//SDFWriter writer = new SDFWriter(writer_id_product);
		String id_product="";
		for(Integer i = 0; i <reaction.getReactantCount(); i++) {
			IAtomContainer reactant = reaction.getReactants().getAtomContainer(i);
			id_product = id_product.concat((reactant.getProperty("emamineID")));
			id_product = id_product.concat("_");
			id_product = id_product.replace("-","_");
		}
		id_product = id_product.concat(Integer.toString(this.getTransformation_id())).concat("\n");
        try {
            writer_id_product.write(id_product);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

	private String writeCSVHeader() {
		String line = "Products" +"\t" + "Reactants" + "\t" + "SMIRKS" + "\t" + "Score";
		
		if (savi_predicted_yield) {
			line += "\t" +"savi_predicted_yield";
		}
		if (rule_of_5_violations) {
			line += "\t" + "rule_of_5_violations";
		}
		if (rule_of_3_violations) {
			line += "\t" + "rule_of_3_violations";
		}
		if (nhdonors) {
			line +=  "\t" +"nhdonors";
		}
		if (nhacceptors) {
			line += "\t" + "nhacceptors";
		}
		if (weight) {
			line += "\t" + "weight";
		}
		if (heavy_atom_count) {
			line += "\t" + "heavy_atom_count";
		}
		if (nrotbonds) {
			line += "\t" + "nrotbonds";
		}
		if (xlogp2) {
			line += "\t" + "xlogp2";
		}
		if (xlogp) {
			line += "\t" + "xlogp";
		}
		if (fsp3) {
			line += "\t" + "fsp3";
		}
		if (stereo_count) {
			line += "\t" + "stereo_count";
		}
		if (tpsa) {
			line += "\t" + "tpsa";
		}
		if (benzenoid_index) {
			line += "\t" + "benzenoid_index";
		}
		if (formula) {
			line += "\t" + "formula";
		}
		if (genotoxic_alerts) {
			line += "\t" + "genotoxic_alerts";
		}
		if (complexity) {
			line += "\t" + "complexity";
		}
		if (inchi) {
			line += "\t" + "inchi";
		}
		if (inchikey) {
			line += "\t" + "inchikey";
		}
		if (charged_group_counts) {
			line += "\t" + "charged_group_counts";
		}
		if (hydrogen_bond_center_count) {
			line += "\t" + "hydrogen_bond_center_count";
		}
		if (savi_sssr_count) {
			line += "\t" + "savi_sssr_count";
		}
		if (savi_sssr_count_change) {
			line += "\t" + "savi_sssr_count_change";
		}
		if (savi_aliring_count) {
			line += "\t" + "savi_aliring_count";
		}
		if (savi_aliring_count_change) {
			line += "\t" + "savi_aliring_count_change";
		}
		if (savi_aroring_count) {
			line += "\t" + "savi_aroring_count";
		}
		if (savi_aroring_count_change) {
			line += "\t" + "savi_aroring_count_change";
		}

		if (bruns_watson_demerit_score || bruns_watson_demerit_components || savi_pains_filter || savi_pains_filter_match_name) {
			for (Entry<String, Map<String, Boolean>> e1 : reactionPAINSFilter.entrySet()) {
				for (String filterName : e1.getValue().keySet()) {
					line += "\t" + filterName;
				}
			}
			if (bruns_watson_demerit_score) {
				//line += "bruns_watson_demerit_score";
			}
			if (bruns_watson_demerit_components) {
				//line += "bruns_watson_demerit_components";
			}
			if (savi_pains_filter) {
				//line += "savi_pains_filter";
			}
			if (savi_pains_filter_match_name) {
				//line += "savi_pains_filter_match_name";
			}
		}
		//TODO other properties
		return line;
	}
	
	
	private int SSSRCountChange(IReaction reaction) {
		int r = 0;
		int p = 0;
		for (IAtomContainer ac : reaction.getReactants().atomContainers()) {
			r += (int) ac.getProperty("SSSRCount");
		}
		for (IAtomContainer ac : reaction.getProducts().atomContainers()) {
			p += (int) ac.getProperty("SSSRCount");
		}
		return p - r;
	}
	
	private int SSSRAliphaticCountChange(IReaction reaction) {
		int r = 0;
		int p = 0;
		for (IAtomContainer ac : reaction.getReactants().atomContainers()) {
			r += (int) ac.getProperty("aliphaticSSSRCount");
		}
		for (IAtomContainer ac : reaction.getProducts().atomContainers()) {
			p += (int) ac.getProperty("aliphaticSSSRCount");
		}
		return p - r;
	}
	
	private int SSSRAromaticCountChange(IReaction reaction) {
		int r = 0;
		int p = 0;
		for (IAtomContainer ac : reaction.getReactants().atomContainers()) {
			r += (int) ac.getProperty("aromaticSSSRCount");
		}
		for (IAtomContainer ac : reaction.getProducts().atomContainers()) {
			p += (int) ac.getProperty("aromaticSSSRCount");
		}
		return p - r;
	}
	
	
	private String brunsWatson(IReaction reaction, IAtomContainer ac, Map<String, Map<String, Boolean>> reactionPAINSFilter) {
		PAINSFilter pf = new PAINSFilter();
		return pf.calculate(reaction, ac, reactionPAINSFilter);
	}
	
	public int getThreads() {
		return threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public boolean isCalculateProperties() {
		return calculateProperties;
	}

	public void setCalculateProperties(boolean calculateProperties) {
		this.calculateProperties = calculateProperties;
	}
	
	public Integer getTransformation_id() {
		return transformation_id;
	}

	public void setTransformation_id(Integer transformation_id) {
		this.transformation_id = transformation_id;
	}

	public Integer getTransform_version() {
		return transform_version;
	}

	public void setTransform_version(Integer transform_version) {
		this.transform_version = transform_version;
	}

	public String getTransform_name() {
		return transform_name;
	}

	public void setTransform_name(String transform_name) {
		this.transform_name = transform_name;
	}

	public String getOutputFileSDF() {
		return outputFileSDF;
	}

	public void setOutputFileSDF(String outputFileSDF) {
		this.outputFileSDF = outputFileSDF;
	}

	public String getTransform_main_product() {
		return transform_main_product;
	}

	public void setTransform_main_product(String string) {
		this.transform_main_product = string;
	}
	/*
	private int ruleOfFive(IAtomContainer ac) {
		RuleOfFiveDescriptor rof = new RuleOfFiveDescriptor();
		rof.calculate(ac);
		rof.getDescriptorResultType();
		return ((IntegerResult) rof.getDescriptorResultType()).intValue();
	}
	
	private int ruleOfThree(IAtomContainer ac) {
		RuleOfThreeDescriptor rot = new RuleOfThreeDescriptor();
		rot.calculate(ac);
		rot.getDescriptorResultType();
		return ((IntegerResult) rot.getDescriptorResultType()).intValue();
	}
	
	private int hBondDonnorCount(IAtomContainer ac) {
		HBondDonorCountDescriptor desc = new HBondDonorCountDescriptor();
		desc.calculate(ac);
		desc.getDescriptorResultType();
		return ((IntegerResult) desc.getDescriptorResultType()).intValue();
	}
	
	private int hBondAcceptorCount(IAtomContainer ac) {
		HBondAcceptorCountDescriptor desc = new HBondAcceptorCountDescriptor();
		desc.calculate(ac);
		desc.getDescriptorResultType();
		return ((IntegerResult) desc.getDescriptorResultType()).intValue();
	}
	
	private Double weight(IAtomContainer ac) {
		WeightDescriptor wt = new WeightDescriptor();
		wt.calculate(ac);
		wt.getDescriptorResultType();
		return ((DoubleResult) wt.getDescriptorResultType()).doubleValue();
	}
	
	private int heavyAtomCount(IAtomContainer ac) {
		int cpt = 0;
		for (IAtom atom : ac.atoms()) {
			if (!atom.getSymbol().equals("H"))
				cpt++;
		}
		return cpt;
	}
	
	private int rotatableBondsCount(IAtomContainer ac) {
		RotatableBondsCountDescriptor rot = new RotatableBondsCountDescriptor();
		rot.calculate(ac);
		rot.getDescriptorResultType();
		return ((IntegerResult) rot.getDescriptorResultType()).intValue();
	}
	
	private double xLogP(IAtomContainer ac) {
		XLogPDescriptor log = new XLogPDescriptor();
		log.calculate(ac);
		log.getDescriptorResultType();
		return ((DoubleResult) log.getDescriptorResultType()).doubleValue();
	}
	
	private double fSp3(IAtomContainer ac) {
		HybridizationRatioDescriptor hrd = new HybridizationRatioDescriptor();
		hrd.calculate(ac);
		hrd.getDescriptorResultType();
		return ((DoubleResult) hrd.getDescriptorResultType()).doubleValue();
	}
	
	private int stereoElementCount(Iterable<IStereoElement> stereo) {
		int count = 0;
		for (IStereoElement se : stereo) {
			count++;
		}
		return count;
	}
	
	private double TPSA(IAtomContainer ac) {
		TPSADescriptor td = new TPSADescriptor();
		td.calculate(ac);
		td.getDescriptorResultType();
		return ((DoubleResult) td.getDescriptorResultType()).doubleValue();
	}
	
	private double FMF(IAtomContainer ac) {
		FMFDescriptor td = new FMFDescriptor();
		td.calculate(ac);
		td.getDescriptorResultType();
		return ((DoubleResult) td.getDescriptorResultType()).doubleValue();
	}
	
	private String getInchi(IAtomContainer ac) {
		InChIGeneratorFactory factory;
		try {
			factory = InChIGeneratorFactory.getInstance();
			return factory.getInChIGenerator(ac).getInchi();
		} catch (CDKException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private String getInchiKey(IAtomContainer ac) {
		InChIGeneratorFactory factory;
		try {
			factory = InChIGeneratorFactory.getInstance();
			return factory.getInChIGenerator(ac).getInchiKey();
		} catch (CDKException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	*/
	
	/*
	private IRingSet getAliphaticSSSR(IAtomContainer ac) throws CDKException {
		IRingSet ali =  DefaultChemObjectBuilder.getInstance().newInstance(IRingSet.class);
		Cycles   cycles = Cycles.sssr(ac);
		IRingSet rings = cycles.toRingSet();
		for (IAtomContainer ac2 : rings.atomContainers()) {
			if (!ac2.getAtom(0).isAromatic())
				ali.addAtomContainer(ac2);
		}
		return ali;
	}
	
	private IRingSet getAromaticSSSR(IAtomContainer ac) throws CDKException {
		IRingSet aro =  DefaultChemObjectBuilder.getInstance().newInstance(IRingSet.class);
		Cycles   cycles = Cycles.sssr(ac);
		IRingSet rings = cycles.toRingSet();
		for (IAtomContainer ac2 : rings.atomContainers()) {
			if (ac2.getAtom(0).isAromatic())
				aro.addAtomContainer(ac2);
		}
		return aro;
	}
	
	private IRingSet getSSSR(IAtomContainer ac) throws CDKException {
		Cycles   cycles = Cycles.sssr(ac);
	    return cycles.toRingSet();
	}
	*/

	//CSV transform precomputing
			/*
			 * {@link Deprecated}
			 * Sulfonamide
			 * Left Score Right Score
			 * smi1  X    Smi2   Y
			 * 
			 * Smiles are formated with atom atom mapping for the reaction center only ex CC[N:1]
			 */
			private void putBuildingBlocksIntoMemory_OLD(String file) {
				int lineCount = 0;
				
				BufferedReader reader;
				try {reader = new BufferedReader(new FileReader(file));
					while (reader.readLine() != null) lineCount++;
					reader.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				int targetPerThread = (int) lineCount/threads + 1;
				
				int chunk = 0;
				int cpt = 0;
				lefts = new HashMap<Integer, List<IAtomContainer>>();
				rights = new HashMap<Integer, List<IAtomContainer>>();
				List<IAtomContainer> leftsChunk = new ArrayList<IAtomContainer>();
				//List<IAtomContainer> rightsChunk = new ArrayList<IAtomContainer>();
				SmilesParser sp = new SmilesParser(SilentChemObjectBuilder.getInstance());
				try {
					reader = new BufferedReader(new FileReader(file));
					String line = reader.readLine();
					while (line != null) {
						String[] elts = line.split("\t");
						if (!elts[1].contains("NULL")) {
							IAtomContainer ac = sp.parseSmiles(elts[1]);
							ac.setProperty("sliceScore", Integer.parseInt(elts[2]));
							leftsChunk.add(ac);
						}
						if (!elts[3].contains("NULL")) {
							IAtomContainer ac2 = sp.parseSmiles(elts[3]);
							ac2.setProperty("sliceScore", Integer.parseInt(elts[4]));
							allRights.add(ac2);
						}
						if (cpt == targetPerThread) {
							lefts.put(chunk, leftsChunk);
							leftsChunk = new ArrayList<IAtomContainer>();
							//rights.put(chunk, rightsChunk);
							//rightsChunk = new ArrayList<IAtomContainer>();
							cpt = 0;
							chunk++;
						}
						cpt++;
					}
					reader.close();
				} catch (IOException | InvalidSmilesException e) {
					e.printStackTrace();
				}
				
				//add the last chunk (if still cpt < targetPerThread, the last check is not added, so leftsChunk is not empty)
				if (!leftsChunk.isEmpty()) {
					lefts.put(chunk, leftsChunk);
					leftsChunk = new ArrayList<IAtomContainer>();
					cpt = 0;
					chunk++;
				}
				//if (!rightsChunk.isEmpty()) {
					//rights.put(chunk, rightsChunk);
					//rightsChunk = new ArrayList<IAtomContainer>();
				//}
			}

		
		/**
		 * {@link Deprecated}
		 * @param comp
		 */
		private void putBuildingBlocksIntoMemory_OLD(Map<String, List<IAtomContainer>> comp) {
			int lineCount = 0;
			
			//split using the number of lefts
			if (comp.containsKey("left")) {
				lineCount = comp.get("left").size();
			}
			else if (comp.containsKey("1")) {
				lineCount = comp.get("left").size();
			}
			
			int targetPerThread = (int) (lineCount/threads);
			
			int chunk = 0;
			int cpt = 0;
			lefts = new HashMap<Integer, List<IAtomContainer>>();
			rights = new HashMap<Integer, List<IAtomContainer>>();
			List<IAtomContainer> leftsChunk = new ArrayList<IAtomContainer>();
			allRights = new ArrayList<IAtomContainer>();
			
			for (Entry<String, List<IAtomContainer>> e : comp.entrySet()) {
				String key = e.getKey();
				if (key.contains("left") || key.contains("1")) {
					for (IAtomContainer ac : e.getValue()) {
						ac.setProperty("sliceScore", ac.getProperty("rating"));
						leftsChunk.add(ac);
						if (cpt == targetPerThread) {
							lefts.put(chunk, leftsChunk);
							leftsChunk = new ArrayList<IAtomContainer>();
							cpt = 0;
							chunk++;
						}
						cpt++;
					}
				}
				else if (key.contains("right") || key.contains("2")) {
					for (IAtomContainer ac : e.getValue()) {
						ac.setProperty("sliceScore", ac.getProperty("rating"));
						allRights.add(ac);
					}
				}
			}
			//add the last chunk (if still cpt < targetPerThread, the last check is not added, so leftsChunk is not empty)
			if (!leftsChunk.isEmpty()) {
				lefts.put(chunk, leftsChunk);
				leftsChunk = new ArrayList<IAtomContainer>();
				cpt = 0;
				chunk++;
			}
			System.out.println("chunk " + chunk + " tpt "+ targetPerThread + " threads " + threads);
		}
	
		/**
		 * {@link Deprecated}
		 * @param file
		 */
		public void makeMolecules_OLD(String file) {
			prepareChunksForMultiThreading(file);
			initReactionPAINSFilter();
			
			String header = writeCSVHeader();
			//TODO parallel and make chunk
			List<CompletableFuture<Boolean>> futures = new ArrayList<CompletableFuture<Boolean>>();
			for (int i : lefts.keySet()) {
				CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> combine(i));
				futures.add(future);
			}
			CompletableFuture<Object> combinedFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
	                .thenApplyAsync(aVoid -> futures.stream().
	                        map(future -> future.join()).
	                        collect(Collectors.<Object>toList())
	                        );
			try {
				combinedFuture.get();
				System.out.println("Merge files");
				//merge CSV and add writeCSVHeader
				outputWriter = new BufferedWriter(new FileWriter(outputFile)); 
				outputWriter.write(header);
				outputWriter.newLine();
				for (int i : lefts.keySet()) {
					BufferedReader reader = new BufferedReader(new FileReader(outputFile.split("\\.")[0]+"_temp"+i+".csv"));
					String line;
					while ((line = reader.readLine()) != null) {
						outputWriter.write(line);
						outputWriter.newLine();
					}
					reader.close();
					//delete temp file
					new File(outputFile.split("\\.")[0]+"_temp"+i+".csv").delete();
				}
				outputWriter.close();
				
			} catch (InterruptedException | ExecutionException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		/**
		 * {@link Deprecated}
		 * @param map
		 */
		public void makeMolecules_OLD(Map<String, List<IAtomContainer>> map) {
			prepareChunksForMultiThreading(map);
			initReactionPAINSFilter();
			
			String header = writeCSVHeader();
			/*for (int i : lefts.keySet()) {
				combine(i);
			}*/
			
			//UNCOMMENT WHEN VALIDATED
					
			//TODO parallel and make chunk
			List<CompletableFuture<Boolean>> futures = new ArrayList<CompletableFuture<Boolean>>();
			for (int i : lefts.keySet()) {
				CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> combine(i));
				futures.add(future);
			}
			CompletableFuture<Object> combinedFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
	                .thenApplyAsync(aVoid -> futures.stream().
	                        map(future -> future.join()).
	                        collect(Collectors.<Object>toList())
	                        );
			try {
				combinedFuture.get();
				System.out.println("Merge files");
				//merge CSV and add writeCSVHeader
				outputWriter = new BufferedWriter(new FileWriter(outputFile)); 
				outputWriter.write(header);
				outputWriter.newLine();
				for (int i : lefts.keySet()) {
					System.out.println(outputFile.split("\\.")[0]+"_temp"+i+".csv");
					BufferedReader reader = new BufferedReader(new FileReader(outputFile.split("\\.")[0]+"_temp"+i+".csv"));
					String line;
					while ((line = reader.readLine()) != null) {
						outputWriter.write(line);
						outputWriter.newLine();
					}
					reader.close();
					//delete temp file
					new File(outputFile.split("\\.")[0]+"_temp"+i+".csv").delete();
				}
				outputWriter.close();
			} catch (InterruptedException | ExecutionException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		/**
		 * @param chunk
		 * @return
		 */
		private boolean combine(int chunk) {
			BufferedWriter writer = null;
			BufferedWriter writer_id_prod = null;
			try {
				writer = new BufferedWriter(new FileWriter(outputFile.split("\\.")[0]+"_temp"+chunk+".csv"));
				writer_id_prod = new BufferedWriter(new FileWriter(outputFile.split("\\.")[0]+"_temp"+chunk+".id"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			//change by combinatorial algorithm
			List<List<IAtomContainer>> start = new ArrayList<List<IAtomContainer>>();
			//System.out.println("chunk number "+chunk + " lefts size "+lefts.get(chunk).size() + " allrights size "+allRights.size());
			start.add(lefts.get(chunk));
			//start.add(rights.get(chunk));
			start.add(allRights);
			GenerateAllCombinationsFromMultipleLists(start, 0, new ArrayList<IAtomContainer>(), writer,writer_id_prod);
			/*for (IAtomContainer left : lefts.get(chunk)) {
				for (IAtomContainer right : rights.get(chunk)) {
					IAtomContainerSet reactants = SilentChemObjectBuilder.getInstance().newInstance(IAtomContainerSet.class);
					reactants.addAtomContainer(left);
					reactants.addAtomContainer(right);
					score = (int) left.getProperty("sliceScore") + (int) right.getProperty("sliceScore") ;
					List<IReaction> reactions = transformer.transform(reactants, smirks);
					for (IReaction reaction : reactions) {
						writeReactionToCSV(outputWriter, reaction, transformer);
					}
				}
			}*/
			System.out.println("combine DONE for chunk "+chunk);
			try {
				writer.close();
				writer_id_prod.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
}
