package com.nih.smirks;

import static org.openscience.cdk.DefaultChemObjectBuilder.getInstance;
import static org.openscience.cdk.interfaces.IBond.Order.SINGLE;
import static org.openscience.cdk.interfaces.IBond.Order.UNSET;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.openscience.cdk.Atom;
import org.openscience.cdk.Bond;
import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.aromaticity.Aromaticity;
import org.openscience.cdk.aromaticity.ElectronDonation;
import org.openscience.cdk.depict.DepictionGenerator;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.graph.Cycles;
import org.openscience.cdk.interfaces.*;
import org.openscience.cdk.interfaces.IBond.Order;
import org.openscience.cdk.interfaces.IBond.Stereo;
import org.openscience.cdk.isomorphism.Mappings;
import org.openscience.cdk.isomorphism.Pattern;
import org.openscience.cdk.isomorphism.matchers.Expr;
import org.openscience.cdk.isomorphism.matchers.QueryAtom;
import org.openscience.cdk.isomorphism.matchers.QueryAtomContainer;
import org.openscience.cdk.isomorphism.matchers.QueryBond;
import org.openscience.cdk.isomorphism.matchers.Expr.Type;
import org.openscience.cdk.ringsearch.AllRingsFinder;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smarts.Smarts;
import org.openscience.cdk.smarts.SmartsPattern;
import org.openscience.cdk.smiles.SmiFlavor;
import org.openscience.cdk.smiles.SmilesGenerator;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.stereo.DoubleBondStereochemistry;
import org.openscience.cdk.stereo.TetrahedralChirality;
import org.openscience.cdk.tools.manipulator.AtomContainerManipulator;

import com.nih.fragments.FragmentUtils;
import com.nih.reaction.additionalConstants;
import com.nih.tools.ElementCalculation;
import com.nih.tools.Tools;

public class Transformer {

	int stoichiometry = 1; 
	boolean aamDone = false;
	//store molecule, which doen't match with at least one pattern
	List<IAtomContainer> reagentsList = new ArrayList<IAtomContainer>();
	
	/**
	 * Return the generated reactions
	 * @param reactantsSmiles (reactant in smiles format separated by a dot. Ex CBr.CO) 
	 * @param Smirks
	 * @return
	 */
	public List<IReaction> transform(String reactantsSmiles, String smirks) {
		SmilesParser sp = new SmilesParser(getInstance());
    	sp.kekulise(true);
    	
    	IAtomContainerSet reactants = SilentChemObjectBuilder.getInstance().newInstance(IAtomContainerSet.class);
    	String[] reactantsSmi = reactantsSmiles.split("\\.");
    	for (String reactant : reactantsSmi) {
    		try {
				reactants.addAtomContainer(sp.parseSmiles(reactant));
			} catch (InvalidSmilesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return transform(reactants, smirks);
	}
	
	/*
	List<List<Integer>> indexOfTheMaps = new ArrayList<List<Integer>>();
	int index = 0;
	List<Integer> list = new ArrayList<Integer>();
	for (int i = 0; i < l1.size(); i++) {
		list.add(index);
		index++;
	}
	indexOfTheMaps.add(list);
	index = 0;
	list = new ArrayList<Integer>();
	for (int i = 0; i < l2.size(); i++) {
		list.add(index);
		index++;
	}
	indexOfTheMaps.add(list);
	List<List<Integer>> sols = new ArrayList<List<Integer>>();  
	GenerateAllCombinationsFromMultipleLists(indexOfTheMaps, sols, 0, new ArrayList<Integer>(), false);
	*/
	
	/**
	 * Return the generated reactions
	 * @param reactants
	 * @param Smirks
	 * @return
	 */
	public List<IReaction> transform(IAtomContainerSet reactants, String smirks) {
		// parse SMIRKS;
		IReaction reactionSmirks = null;
		try {
			reactionSmirks = parseSmirks(smirks);
		} catch (InvalidSmilesException e) {
			// TODO Auto-generated catch blocktransform
			e.printStackTrace();
		}
		//System.out.println("smirks: "+ smirks);
		IAtomContainer aggregateProducts = SilentChemObjectBuilder.getInstance().newAtomContainer();
		int st = 0;
		for (IAtomContainer ac : reactants.atomContainers()) {

			SmilesGenerator gen  = new SmilesGenerator(SmiFlavor.Default);

			aggregateProducts.add(ac);
			
		}

		//display aggregate 
		/*for (IAtom atom : aggregateProducts.atoms()) {
			System.out.println(atom.getSymbol() + " display map :"+atom.getProperty(CDKConstants.ATOM_ATOM_MAPPING));
		}
		System.out.println("@@@@@aggregate description : atoms ="+ aggregateProducts.getAtomCount()+" bonds ="+aggregateProducts.getBondCount());
    	for(int i =0; i < aggregateProducts.getAtomCount();i++)
    	{
    		System.out.print(aggregateProducts.getAtom(i).getSymbol()+aggregateProducts.getAtom(i).getIndex() + "("+aggregateProducts.getAtom(i).isAromatic()+")"+" +{"+aggregateProducts.getAtom(i).getImplicitHydrogenCount()+"}");
    	}
    	System.out.println();
    	for(int i =0; i < aggregateProducts.getBondCount();i++)
    	{
    		System.out.print(i + " " +aggregateProducts.getBond(i).isAromatic());
    	}
    	System.out.println();*/
		
		/*for (IAtom atom : aggregateProducts.atoms())
	    {
	    	System.out.println(atom.getIndex()+ " ("+atom.getImplicitHydrogenCount()+" ) "+atom.getProperties());
	    }*/
		// test if atom atom mapping already done
		//System.out.println("aamDone:"+ aamDone+ "testMapping(reactants)= "+ testMapping(reactants));
		//System.out.println("smirks:"+smirks);
		if (aamDone == true)
			return transform(aggregateProducts, reactionSmirks, reactants);
		else {
			if (testMapping(reactants))
				return transform(aggregateProducts, reactionSmirks, reactants);
			else
				return transform(aggregateProducts, reactionSmirks, reactants, smirks);
		}
	}
	
	private List<IReaction> transform(IAtomContainer aggregateProducts, IReaction reactionSmirks,
			IAtomContainerSet reactants) {
		reagentsList.clear();
		List<IReaction> results = new ArrayList<IReaction>();
		IReaction reaction = SilentChemObjectBuilder.getInstance().newInstance(IReaction.class);
		try {
			aggregateProducts = applyTransform(reactionSmirks, aggregateProducts.clone());
			
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reaction.setReactants(reactants);
		reaction.setProducts(FragmentUtils.makeAtomContainerSet(aggregateProducts));
		String smi = null;
		try {
			Transformer transformer = new Transformer();
			smi = transformer.makeSmiles(reaction, true);
		} catch (CDKException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		results.add(reaction);
		//System.out.println("reaction "+reaction);
		//System.out.println("smi : "+ smi);
		//convert explicit hydrogens to implicit
		convertExplicitToImplicitHydrogens(reaction);
		//reattribuate mapping
		mappingReattribution(reaction);
		return results;
	}
	
	private List<IReaction> transform(IAtomContainer aggregateProducts, IReaction reactionSmirks,
			IAtomContainerSet reactants, String smirks) {
		//reset Visited Flag (used by CDK when using SMARTS pAttern)
		resetVisitedFlag(aggregateProducts);
		reagentsList.clear();
		
		List<IReaction> results = new ArrayList<IReaction>();
		// get all possible reactants combinations matching with smarts
		Mappings[][] compatibleSmarts = findCompatibleSmarts(reactants, smirks);
		List<List<Mappings>> combinations = getCompatibleMappingAssociation(compatibleSmarts);
		
		//remove reagents from reactant
		IAtomContainerSet reagents = getReagents(reactants);
		
		for (List<Mappings> combination : combinations) {
			// index of all possible mappings for all reactants
			List<List<Integer>> indexOfTheMaps = new ArrayList<List<Integer>>();
			List<List<Map<IAtom, IAtom>>> combinationList = new ArrayList<List<Map<IAtom, IAtom>>>();
			//System.out.println(combination.size());
			for (Mappings mapping : combination) {
				// convert Iterable to List
				List<Map<IAtom, IAtom>> result = new ArrayList<Map<IAtom, IAtom>>();
				// TODO improve filtering mapping (get all possible mappings related to atoms
				// involved in the reaction center)
				mapping.uniqueAtoms().toAtomMap().forEach(result::add);
				combinationList.add(result);
				//System.out.println("Res " +result.size());
				// get the index of all possible mapping in the list
				List<Integer> list = new ArrayList<Integer>();
				for (int i = 0; i < result.size(); i++) {
					list.add(i);
				}
				indexOfTheMaps.add(list);
			}
			//System.out.println(combinationList.get(0).size());
			// get all possible combination
			List<List<Integer>> sols = new ArrayList<List<Integer>>();
			GenerateAllCombinationsFromMultipleLists(indexOfTheMaps, sols, 0, new ArrayList<Integer>(), false);
			//System.out.println(sols);
			for (int i = 0; i < sols.size(); i++) {
				//reset mapping
				if (i>0) {
					resetMapping(aggregateProducts);
				}
				List<Integer> solution = sols.get(i);
				//attribute atom atom mapping to atom in aggregatesproducts 
				//(which is a IAtomContainer containing all reactants and will be modified to generate the products)
				for (int j = 0; j < solution.size(); j++) {
					attributeAtomAtomMapping(aggregateProducts, combinationList.get(j).get(solution.get(j)));
				}
				IReaction reaction = SilentChemObjectBuilder.getInstance().newInstance(IReaction.class);

				try {
					IAtomContainer newAggregateProducts = applyTransform(reactionSmirks, aggregateProducts.clone());
					reaction.setReactants(reactants);
					reaction.setProducts(FragmentUtils.makeAtomContainerSet(newAggregateProducts));
					for (IAtomContainer ac : reagents.atomContainers()) {
						reaction.addAgent(ac);
					}
					results.add(reaction);
					//convert explicit hydrogens to implicit
					convertExplicitToImplicitHydrogens(reaction);
					//reattribuate mapping
					mappingReattribution(reaction);
					//System.out.println(makeSmiles(newAggregateProducts, true));
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return results;
	}
	
	private IReaction parseSmirks(String smirks) throws InvalidSmilesException {
		if (!smirks.contains(">"))
            throw new InvalidSmilesException("Not a reaction SMILES: " + smirks);

        final int first  = smirks.indexOf('>');
        final int second = smirks.indexOf('>', first + 1);

        if (second < 0)
            throw new InvalidSmilesException("Invalid reaction SMILES:" + smirks);

        final String reactantsSmarts = smirks.substring(0, first);
        final String agentsSmarts = smirks.substring(first + 1, second);
        final String productsSmarts = smirks.substring(second + 1, smirks.length());
        
       // System.out.println(reactantsSmarts);
       // System.out.println(productsSmarts);
        IReaction reactionSmirks = SilentChemObjectBuilder.getInstance().newInstance(IReaction.class);
        
        for (String reactantSmarts : reactantsSmarts.split("\\.")) {
        	QueryAtomContainer query = new QueryAtomContainer(null);
            if (!Smarts.parse(query, reactantSmarts, Smarts.FLAVOR_CACTVS))
                throw new IllegalArgumentException("Could not parse SMARTS: " +
                		reactantSmarts + "\n" +
                                                   Smarts.getLastErrorMesg() + "\n" +
                                                   Smarts.getLastErrorLocation());

            reactionSmirks.addReactant(query);
        }
        for (String productSmarts : productsSmarts.split("\\.")) {
        	QueryAtomContainer query = new QueryAtomContainer(null);
        	
        	
            if (!Smarts.parse(query, productSmarts, Smarts.FLAVOR_CACTVS))
                throw new IllegalArgumentException("Could not parse SMARTS: " +
                		productSmarts + "\n" +
                                                   Smarts.getLastErrorMesg() + "\n" +
                                                   Smarts.getLastErrorLocation());
           //System.out.println("PRODUCS:"+productSmarts);
            reactionSmirks.addProduct(query);
        }
		return reactionSmirks;
	}
	
	
	/**
	 * Return the Reagents (Molecules which are the same in reactants and products)
	 * @param reactants
	 * @return
	 */
	private IAtomContainerSet getReagents(IAtomContainerSet reactants) {
		IAtomContainerSet reagents = SilentChemObjectBuilder.getInstance().newInstance(IAtomContainerSet.class);
		for (IAtomContainer reagent : reagentsList) {
			reactants.removeAtomContainer(reagent);
			reagents.addAtomContainer(reagent);
		}
		return reagents;
	}
	
	private void resetVisitedFlag(IAtomContainer ac) {
		for (IAtom atom : ac.atoms()) {
			atom.setFlag(CDKConstants.VISITED, false);
		}
	}
	
	
	private void resetMapping(IAtomContainer ac) {
		for (IAtom atom : ac.atoms()) {
			atom.removeProperty(CDKConstants.ATOM_ATOM_MAPPING);
		}
	}
	
	private boolean testMapping(IAtomContainerSet set) {
		for (IAtomContainer ac : set.atomContainers()) {
			if (!testMapping(ac))
				return false;
		}
		return true;
	}
	
	private boolean testMapping(IAtomContainer ac) {
		for (IAtom atom : ac.atoms()) {
			if (atom.getProperty(CDKConstants.ATOM_ATOM_MAPPING) != null)
				return true;
		}
		return false;
	}
	
	private Mappings getMapping(String smarts, IAtomContainer ac) {
		Pattern ptrn = SmartsPattern.create(smarts, Smarts.FLAVOR_CACTVS);
		if (ptrn.matches(ac))
			return ptrn.matchAll(ac);
		return null;
	}
	
	/**
	 * for each reactant, get the mappings of all matching SMARTS
	 * store the individual mapping results (null if no mapping)
	 *       SMARTS1     SMARTS2     SMARTS3
	 * ac1     L1		 null         L2
	 * ac2     null		 L7           L3
	 * ac3     L4		 L5           L6
	 * 
	 * @param set
	 * @param smirks
	 * @return
	 */
	private Mappings[][] findCompatibleSmarts(IAtomContainerSet set, String smirks) {
		Mappings[][] possibilities = new Mappings[set.getAtomContainerCount()][smirks.split(">")[0].length()];
		for (int i = 0; i < set.getAtomContainerCount(); i++) {
			IAtomContainer ac = set.getAtomContainer(i);
			boolean matched = false;
			// match with each SMARTS pattern in reactants side
			String[] allSmarts = smirks.split(">")[0].split("\\.");
			for (int j = 0; j < allSmarts.length; j++) {
				String smarts = allSmarts[j];
				Mappings mappings = getMapping(smarts, ac);
				if (mappings == null) {
					possibilities[i][j] = null;
				}
				else{
					possibilities[i][j] = mappings;
					matched = true;
				}
			}
			if (!matched)
				reagentsList.add(ac);
		}
		Mappings[][] possibilities2 = new Mappings[set.getAtomContainerCount()-reagentsList.size()][smirks.split(">")[0].length()];
		int index = 0;
		for (int i = 0; i < set.getAtomContainerCount(); i++) {
			IAtomContainer ac = set.getAtomContainer(i);
			if (!reagentsList.contains(ac)) {
				String[] allSmarts = smirks.split(">")[0].split("\\.");
				for (int j = 0; j < allSmarts.length; j++) {
					possibilities2[index][j] = possibilities[i][j];
				}
				index++;
			}
		}
		return possibilities2;
	}
	
	/**
	 * Return all possible combinations (those combining all SMARTS, ex L1,L5,L3; L1,L7,L6; L4,L7,L2) (cf tab in findCompatibleSmarts)
	 * A valid combination is a combination, where each reactant is associated with one SMARTS
	 * result = [[L1,L5,L3,] [L1,L7,L6], [L4,L7,L2]]
	 * @param possibilities
	 * @return
	 */
	private List<List<Mappings>> getCompatibleMappingAssociation(Mappings[][] possibilities) {
		int smartsNb = possibilities[0].length;
		List<List<Mappings>> combinations = new ArrayList<List<Mappings>>();
		for (int i = 0; i < smartsNb; i++) {
			for (int j = 0; j < possibilities.length; j++) {
				if (possibilities[j][i] != null) {
					if (i == 0) {
						List<Mappings> combination = new ArrayList<Mappings>();
						combination.add(possibilities[j][i]);
						combinations.add(combination);
					}
					else {
						for (List<Mappings> combination : combinations) {
							combination.add(possibilities[j][i]);
						}
					}
				}
			}
		}
		return combinations;
	}
	
	//take hydrogen count(explicit and Implicit), charge bond order, bond made/broken, aromaticity (bond + atom), stereo
	private IAtomContainer applyTransform(IReaction reactionSmirks, IAtomContainer aggregateProducts) {


        //aggregate reactants and product in smirks
        QueryAtomContainer reactantsSmirks = new QueryAtomContainer(DefaultChemObjectBuilder.getInstance());
        QueryAtomContainer productsSmirks = new QueryAtomContainer(DefaultChemObjectBuilder.getInstance());

        for (IAtomContainer ac : reactionSmirks.getReactants().atomContainers()) {
            reactantsSmirks.add(ac);
        }
        for (IAtomContainer ac : reactionSmirks.getProducts().atomContainers()) {
            productsSmirks.add(ac);
        }

		/*System.out.println(" products smirks: "+ productsSmirks.getAtomCount()+ " "+productsSmirks.getBondCount() );
		for(int i =0; i < productsSmirks.getBondCount();i++)
    	{
			System.out.println(" " +i + " (" +productsSmirks.getBond(i).getBegin().getProperties()+ productsSmirks.getBond(i).getBegin().getProperty(CDKConstants.ATOM_ATOM_MAPPING)+"-"+ productsSmirks.getBond(i).getEnd().getProperty(CDKConstants.ATOM_ATOM_MAPPING)+" )"+productsSmirks.getBond(i).isAromatic()+ " "+productsSmirks.getBond(i).getOrder()+";");
			//STEFI
			//aggregateProducts.getBond(i).setOrder(productsSmirks.getBond(i).getOrder());
    	}
		
		System.out.println(" Agg: "+ aggregateProducts.getAtomCount()+ " "+aggregateProducts.getBondCount() );
		for(int i =0; i < aggregateProducts.getBondCount();i++)
    	{
			System.out.println(" " +i + " (" + aggregateProducts.getBond(i).getBegin().getIndex()+"-"+ aggregateProducts.getBond(i).getEnd().getIndex()+" )"+aggregateProducts.getBond(i).getBegin().getSymbol()+" "+aggregateProducts.getBond(i).isAromatic()+ " "+aggregateProducts.getBond(i).getOrder()+";");
	    }
		System.out.print(" FIN AC " );
		
		System.out.println("atoms :"+ aggregateProducts.getAtomCount()+ " bonds :"+ aggregateProducts.getBondCount());*/
        //register reactants mapping
        List<Integer> reactantRecords = registerMappings(reactantsSmirks);
        //System.out.println("smirks :"+ reactantRecords);
        //System.out.println(reactantRecords);
        List<Integer> productRecords = registerMappings(productsSmirks);
        //delete all atoms without mapping in aggregate products
        //  -> all atoms without mapping are considered absent in products
        List<IAtom> atomsToDel = new ArrayList<IAtom>();
        for (IAtom atom : aggregateProducts.atoms()) {
            atom.setFormalCharge(0);
            if (atom.getProperty("noMapping") != null)
                atomsToDel.add(atom);
        }
        for (IAtom atom : atomsToDel) {
            aggregateProducts.removeAtom(atom);
        }
        //check empty bond
        List<IBond> bondsToDel = new ArrayList<IBond>();
        for (IBond bond : aggregateProducts.bonds()) {
            if (bond.getBegin() == null || bond.getEnd() == null)
                bondsToDel.add(bond);
        }
        for (IBond bond : bondsToDel) {
            aggregateProducts.removeBond(bond);
        }

        for (IBond bond : productsSmirks.bonds()) {
            //System.out.println("bond"+bond.getBegin().getSymbol()+bond.getBegin().getID()+" "+bond.getEnd().getSymbol());
            QueryBond qb = (QueryBond) bond;
            boolean addBond = false;
            QueryAtom begin = (QueryAtom) bond.getBegin();
            QueryAtom end = (QueryAtom) bond.getEnd();
            IAtom configBegin = null;
            IAtom configEnd = null;
            //all atoms without mapping are considered absent in reactants and are created
            if (begin.getProperty("done") == null) {
                if (begin.getProperty(CDKConstants.ATOM_ATOM_MAPPING) == null) {
                    //test if multiple possible atom type

                    IAtom atom = addNewAtom(begin, productsSmirks, aggregateProducts);
                    configBegin = atom;
                    addBond = true;
                    //flag
                    begin.setProperty("done", true);
                    begin.setProperty("configAtom", atom);
                } else {
                    //get atom in aggregate product and configure it
                    if (reactantRecords.contains(begin.getProperty(CDKConstants.ATOM_ATOM_MAPPING))) {
                        int aam = begin.getProperty(CDKConstants.ATOM_ATOM_MAPPING);
                        //System.out.println(" reactantRecords index : "+reactantRecords.indexOf(aam));
                        //System.out.println("reactantsSmirks:"+ reactantsSmirks);
                        //System.out.println(reactantsSmirks.getAtom(reactantRecords.indexOf(aam)).getSymbol()+reactantsSmirks.getAtom(reactantRecords.indexOf(aam)).getIndex());
                        //System.out.println("aam: "+ aam+" "+ getAtomByAam(aggregateProducts, aam));
                        //System.out.println("aggregateProducts: "+aggregateProducts);


                        configBegin = configAtom((QueryAtom) reactantsSmirks.getAtom(reactantRecords.indexOf(aam)),
                                begin, getAtomByAam(aggregateProducts, aam), aggregateProducts);
                        begin.setProperty("configAtom", configBegin);

                    } else {
                        configBegin = configAtom(aggregateProducts, begin);
                        begin.setProperty("configAtom", configBegin);
                    }
                }
            } else {
                configBegin = begin.getProperty("configAtom");
            }
            if (end.getProperty("done") == null) {
                if (end.getProperty(CDKConstants.ATOM_ATOM_MAPPING) == null) {
                    //test if multiple possible atom type
                    IAtom atom = addNewAtom(end, productsSmirks, aggregateProducts);
                    configEnd = atom;
                    addBond = true;
                    //flag
                    end.setProperty("done", true);
                    end.setProperty("configAtom", atom);

                } else {
                    //get atom in aggregate product and configure it
                    if (reactantRecords.contains(end.getProperty(CDKConstants.ATOM_ATOM_MAPPING))) {
                        int aam = end.getProperty(CDKConstants.ATOM_ATOM_MAPPING);
		    				/*System.out.println("aam :"+aam);
		    				System.out.println("records :"+reactantRecords+"index:"+reactantRecords.indexOf(aam));
		    				System.out.println("smirks :"+reactantsSmirks.getAtomCount());
		    				
		    				System.out.println("1 :"+reactantsSmirks.getAtom(reactantRecords.indexOf(aam)));
		    				System.out.println("2 :"+end.isAromatic());
		    				System.out.println("3 :"+getAtomByAam(aggregateProducts, aam).isAromatic());
		    				System.out.println("4 :"+aggregateProducts);*/

                        configEnd = configAtom((QueryAtom) reactantsSmirks.getAtom(reactantRecords.indexOf(aam)),
                                end, getAtomByAam(aggregateProducts, aam), aggregateProducts);
                        end.setProperty("configAtom", configEnd);

                    } else {
                        configEnd = configAtom(aggregateProducts, end);
                        end.setProperty("configAtom", configEnd);
                    }
                }

            } else
                configEnd = end.getProperty("configAtom");

            if (addBond == true) {

                if (configBegin == null) {

                    configBegin = getAtomByAam(aggregateProducts, begin.getProperty(CDKConstants.ATOM_ATOM_MAPPING));
                }
                if (configEnd == null) {
                    //System.out.println("end"+ end.getProperties());
                    configEnd = getAtomByAam(aggregateProducts, end.getProperty(CDKConstants.ATOM_ATOM_MAPPING));
                }
                makeBond(aggregateProducts, configBegin, configEnd, qb);

            } else {
                //System.out.println("prop"+ begin.getProperty(CDKConstants.ATOM_ATOM_MAPPING)+ "begin"+ reactantRecords.indexOf(begin.getProperty(CDKConstants.ATOM_ATOM_MAPPING)));
                //System.out.println("prop"+ end.getProperty(CDKConstants.ATOM_ATOM_MAPPING)+"end"+ reactantRecords.indexOf(end.getProperty(CDKConstants.ATOM_ATOM_MAPPING)));

                if (begin.getProperty(CDKConstants.ATOM_ATOM_MAPPING) == null ||
                        end.getProperty(CDKConstants.ATOM_ATOM_MAPPING) == null) {
                    makeBond(aggregateProducts, configBegin, configEnd, qb);
                }
                //modify bond if necessary

                else if (reactantsSmirks.getBond(
                        reactantsSmirks.getAtom(reactantRecords.indexOf(begin.getProperty(CDKConstants.ATOM_ATOM_MAPPING))),
                        reactantsSmirks.getAtom(reactantRecords.indexOf(end.getProperty(CDKConstants.ATOM_ATOM_MAPPING)))) != null) {

                    QueryBond qReactant = (QueryBond) reactantsSmirks.getBond(
                            reactantsSmirks.getAtom(reactantRecords.indexOf(begin.getProperty(CDKConstants.ATOM_ATOM_MAPPING))),
                            reactantsSmirks.getAtom(reactantRecords.indexOf(end.getProperty(CDKConstants.ATOM_ATOM_MAPPING))));
                    IBond aggegateBond = aggregateProducts.getBond(
                            getAtomByAam(aggregateProducts, begin.getProperty(CDKConstants.ATOM_ATOM_MAPPING)),
                            getAtomByAam(aggregateProducts, end.getProperty(CDKConstants.ATOM_ATOM_MAPPING)));

                    //System.out.println("modify bond: "+ begin.getProperty(CDKConstants.ATOM_ATOM_MAPPING)+ "-"+end.getProperty(CDKConstants.ATOM_ATOM_MAPPING));

                    //System.out.println("noooooo :"+ productsSmirks.getBond(begin.getProperty(CDKConstants.ATOM_ATOM_MAPPING),
                    //			end.getProperty(CDKConstants.ATOM_ATOM_MAPPING)).getOrder());

                    configBond(qReactant, qb, aggegateBond);
                    //qReactant.getBegin().setProperty("done", true);
                    //qReactant.getEnd().setProperty("done", true);

                    //aggregateProducts.removeBond(aggegateBond);
                    //makeBond(aggregateProducts, configBegin, configEnd, qb);
                    qReactant.setProperty("done", true);

                }
                //bond made
                else {
                    //count neighbors of the two atoms
                    makeBond(aggregateProducts, configBegin, configEnd, qb);
                    //remove extra hydrogens
                    IAtom extra_hydrogen = null;
                    for (IBond bonds : aggregateProducts.bonds()) {
                        if (bonds.getBegin() == configBegin && bonds.getEnd().getSymbol().equals("H")) {
                            extra_hydrogen = bonds.getEnd();
                            break;
                        }
                        if (bonds.getEnd() == configBegin && bonds.getBegin().getSymbol().equals("H")) {
                            extra_hydrogen = bonds.getBegin();
                            break;
                        }
                    }
                    if (extra_hydrogen != null) {
                        aggregateProducts.removeAtom(extra_hydrogen);
                    }
                    extra_hydrogen = null;
                    for (IBond bonds : aggregateProducts.bonds()) {
                        if (bonds.getBegin() == configEnd && bonds.getEnd().getSymbol().equals("H")) {
                            extra_hydrogen = bonds.getEnd();
                            break;
                        }
                        if (bonds.getEnd() == configEnd && bonds.getBegin().getSymbol().equals("H")) {
                            extra_hydrogen = bonds.getBegin();
                            break;
                        }
                    }
                    if (extra_hydrogen != null) {
                        aggregateProducts.removeAtom(extra_hydrogen);
                    }
                }
            }
        }


        Set<IAtom> toRemove = new HashSet<IAtom>();
        for (IBond bond : reactantsSmirks.bonds()) {
            QueryBond qb = (QueryBond) bond;
            QueryAtom begin = (QueryAtom) qb.getBegin();
            QueryAtom end = (QueryAtom) qb.getEnd();
            IAtom aggregateBegin = null;
            IAtom aggregateEnd = null;
            boolean removeBond = false;
            //System.out.println("begin atm :"+begin.getProperties());
            if (qb.getProperty("done") == null) {
                if (begin.getProperty(CDKConstants.ATOM_ATOM_MAPPING) != null) {
                    int aam = begin.getProperty(CDKConstants.ATOM_ATOM_MAPPING);
                    aggregateBegin = getAtomByAam(aggregateProducts, aam);
                    if (!productRecords.contains(aam))
                        toRemove.add(aggregateBegin);
                }
                if (end.getProperty(CDKConstants.ATOM_ATOM_MAPPING) != null) {
                    int aam = end.getProperty(CDKConstants.ATOM_ATOM_MAPPING);
                    aggregateEnd = getAtomByAam(aggregateProducts, aam);
                    if (!productRecords.contains(aam))
                        toRemove.add(aggregateEnd);
                }
                if (begin.getProperty(CDKConstants.ATOM_ATOM_MAPPING) != null && end.getProperty(CDKConstants.ATOM_ATOM_MAPPING) != null)
                    removeBond = true;
            }

            if (removeBond) {
                IBond remove = aggregateProducts.getBond(aggregateBegin, aggregateEnd);

                aggregateProducts.removeBond(remove);

            }
        }
        for (IAtom atom : toRemove) {
            aggregateProducts.removeAtom(atom);
        }

        for (IAtom at : aggregateProducts.atoms()) {
            //at.setImplicitHydrogenCount(0);
			//remove extra hydrogens
			if(ElementCalculation.calculateValence(at.getSymbol()) < (bondSum(aggregateProducts, at) + at.getImplicitHydrogenCount())) {
				removeHydrogensonAt(aggregateProducts, at);
			}
			//add missing hydrogens
			while (ElementCalculation.calculateValence(at.getSymbol()) > (bondSum(aggregateProducts, at) + at.getImplicitHydrogenCount())) {
                IAtom hydrogen = new Atom("H");
                hydrogen.setImplicitHydrogenCount(0);
                aggregateProducts.addAtom(hydrogen);
                aggregateProducts.addBond(new Bond(at, hydrogen, IBond.Order.SINGLE));
            }

			while (at.getImplicitHydrogenCount() > 0) {
                IAtom hydrogen = new Atom("H");
                hydrogen.setImplicitHydrogenCount(0);
                aggregateProducts.addAtom(hydrogen);
                aggregateProducts.addBond(new Bond(at, hydrogen, IBond.Order.SINGLE));
                at.setImplicitHydrogenCount(at.getImplicitHydrogenCount() - 1);
            }
			if(at.getSymbol().equalsIgnoreCase("C") && at.isAromatic()){

                if( bondSumHydrogen(aggregateProducts,at) > 1){
					removeHydrogensonAt(aggregateProducts,at);
					IAtom hydrogen = new Atom("H");
					hydrogen.setImplicitHydrogenCount(0);
					aggregateProducts.addAtom(hydrogen);
					aggregateProducts.addBond(new Bond(at, hydrogen, IBond.Order.SINGLE));
				}
			}
		}

        //finalize stereo
        finalize(aggregateProducts, productsSmirks);

        return aggregateProducts;
    }
	// count number of bounds of atom at in IAtomcontainer mol
	private int bondSum(IAtomContainer mol, IAtom at){
		int sum = 0;
		//for aromatic atoms
		boolean double_bonds = false;
		for(IBond bond : mol.bonds()){
			if(bond.contains(at)){
				sum += bond.getOrder().numeric();
				if(bond.getOrder() == SINGLE && bond.isAromatic()){
					double_bonds = true;
				}
			}
		}
		if(double_bonds){
			sum ++;
		}

		return sum;
	}
	//count the number of hydrogens having a bond to atom at in IAtomcontainer mol
	private int bondSumHydrogen(IAtomContainer mol, IAtom at){
		int hydrogens_count = 0;
		for(IBond bond : mol.bonds()){
			if(bond.contains(at) && (bond.getBegin().getSymbol().equals("H") || bond.getEnd().getSymbol().equals("H"))){
				hydrogens_count++;
			}
		}
		return hydrogens_count;
	}
	//remove all hydrogens on atom at in Iatomcontainer mol
	private void removeHydrogensonAt(IAtomContainer mol, IAtom at){

		List<IAtom> atomsToDel = new ArrayList<IAtom>();
		List<IBond> bondToDel = new ArrayList<IBond>();
		for(IBond bond : mol.bonds()){
			if(bond.contains(at) && (bond.getBegin().getSymbol().equals("H") || bond.getEnd().getSymbol().equals("H"))){
				//if (at.getSymbol().equals("O") ) System.out.println("TOTO"+ bond.getBegin().getSymbol()+ " " + bond.getEnd().getSymbol());
				if(bond.getBegin().getSymbol().equals("H")) {
					atomsToDel.add(bond.getBegin());
				}
				else{
					atomsToDel.add(bond.getEnd());
				}
				bondToDel.add(bond);
			}
		}

		for (IAtom atom : atomsToDel) {
			mol.removeAtom(atom);
		}

		for (IBond bond : bondToDel) {
			mol.removeBond(bond);
		}
	}

	private IAtom addNewAtom(QueryAtom qAtom, QueryAtomContainer qac, IAtomContainer products) {
		Expr.Type[] testUniqueElement = { Expr.Type.ALIPHATIC_ELEMENT, Expr.Type.AROMATIC_ELEMENT, Expr.Type.ELEMENT };
		Map<Type, List<Integer>> exprs = qAtom.getExpression().toMap();
		boolean matches = false;
		for (Expr.Type type : testUniqueElement) {
			if (exprs.containsKey(type)) {
				if (matches) {
					System.err.println("Can not manage yet more than one element type");
					System.exit(0);
				}
				if (exprs.get(type).size() > 1) {
					System.err.println("Can not manage yet more than one element type");
					System.exit(0);
				} else {
					// calculate delta by getting min in list
					IAtom atom = new Atom(exprs.get(type).get(0));
					products.addAtom(atom);
					if (exprs.containsKey(Expr.Type.FORMAL_CHARGE)) {
						if (exprs.get(Expr.Type.FORMAL_CHARGE).size() > 1) {
							System.err.println("Can not manage yet more than one possible charge ");
							System.exit(0);
						} else
							atom.setFormalCharge(exprs.get(Expr.Type.FORMAL_CHARGE).get(0));
					}
					if (exprs.containsKey(Expr.Type.IMPL_H_COUNT)) {
						atom.setImplicitHydrogenCount(exprs.get(Expr.Type.IMPL_H_COUNT).get(0));
					}
					// get explicit hydrogen and deduce implicit number
					if (exprs.containsKey(Expr.Type.TOTAL_H_COUNT)) {
						int explicitHydrogen =  exprs.get(Expr.Type.TOTAL_H_COUNT).get(0);
						int hCount = 0;
						for (IAtom atom2 : qac.getConnectedAtomsList(atom)) {
							QueryAtom qa = (QueryAtom) atom2;
							Map<Type, List<Integer>> exprs2 = qa.getExpression().toMap();
							for (Expr.Type type2 : testUniqueElement) {
								if (exprs2.containsKey(type)) {
									if (matches) {
										System.err.println("Can not manage yet more than one element type");
										System.exit(0);
									}
									if (exprs.get(type2).size() > 1) {
										System.err.println("Can not manage yet more than one element type");
										System.exit(0);
									} else {
										if (exprs.get(type2).get(0) == 1)
											hCount++;
									}
								}
							}
						}
						//create missing explicit hydrogen
						if (hCount != explicitHydrogen) {
							for (int i = 0; i < explicitHydrogen-hCount; i++) {
								Atom newHydrogen = new Atom("H");
								newHydrogen.setImplicitHydrogenCount(0);
								products.addAtom(newHydrogen);
								Bond newBond = new Bond();
								newBond.setAtom(atom, 0);
								newBond.setAtom(newHydrogen, 1);
								newBond.setOrder(IBond.Order.SINGLE);
								products.addBond(newBond);
							}
						}
						//atom.setImplicitHydrogenCount(exprs.get(Expr.Type.TOTAL_H_COUNT).get(0) - hCount);
					}
					if (exprs.containsKey(Expr.Type.IS_AROMATIC) || type.equals(Expr.Type.AROMATIC_ELEMENT))
						atom.setIsAromatic(true);
					else
						atom.setIsAromatic(false);
					if (exprs.containsKey(Expr.Type.IS_IN_RING) || exprs.containsKey(Expr.Type.RING_COUNT)
							|| exprs.containsKey(Expr.Type.RING_SIZE))
						atom.setIsInRing(true);
					else
						atom.setIsInRing(false);
					if (exprs.containsKey(Expr.Type.VALENCE)) {
						if (exprs.get(Expr.Type.VALENCE).size() > 1) {
							System.err.println("Can not manage yet more than one possible valence ");
							System.exit(0);
						} else
							atom.setFormalCharge(exprs.get(Expr.Type.VALENCE).get(0));
					}
					return atom;
				}
			}
		}
		return null;
	}
	
	private List<Integer> registerMappings(IAtomContainer ac) {
		List<Integer> records = new ArrayList<Integer>();
		for (int i = 0; i < ac.getAtomCount(); i++) {
			IAtom atom = ac.getAtom(i);
			if (atom.getProperty(CDKConstants.ATOM_ATOM_MAPPING) != null)
				records.add(atom.getProperty(CDKConstants.ATOM_ATOM_MAPPING));
			else 
				records.add(null);
			//reset custom properties
			atom.removeProperty("configAtom");
			atom.removeProperty("done");
		}
		return records;
	}
	
	private void configBond(QueryBond source, QueryBond target, IBond toModify) {
		Map<Type, List<Integer>> exprs1 = source.getExpression().toMap();
		Map<Type, List<Integer>> exprs2 = target.getExpression().toMap();
		//System.out.println("EXPR 1: "+exprs1);
		//System.out.println("EXPR 2: "+exprs2);
		//System.out.println("QUERY BOND: "+target.getOrder());
		//System.out.println("BOND ORDER: "+toModify.getOrder());
		//System.out.println("BOND ORDER: "+toModify.getBegin().getSymbol()+toModify.getBegin().getIndex()+"-"+toModify.getEnd().getSymbol()+toModify.getEnd().getIndex());
		for (Entry<Type, List<Integer>> entry : exprs2.entrySet()) {
			Expr.Type type = entry.getKey();
			List<Integer> val2 = entry.getValue();
			//System.out.println(exprs1.containsKey(type));
			if (exprs1.containsKey(type)) {
				List<Integer> val1 = exprs1.get(type);
				if (type.equals(Expr.Type.ALIPHATIC_ORDER)) {
					int delta = findMin(val2) - findMin(val1);
					int order = toModify.getOrder().numeric() + delta;
					switch(order) {
					case 1:
						toModify.setOrder(IBond.Order.SINGLE);
						break;
					case 2:
						toModify.setOrder(IBond.Order.DOUBLE);
						break;
					case 3:
						toModify.setOrder(IBond.Order.TRIPLE);
						break;
					case 4:
						toModify.setOrder(IBond.Order.QUADRUPLE);
						break;
					}
					
				}
			}
			if (type.equals(Expr.Type.IS_AROMATIC) || type.equals(Expr.Type.AROMATIC_ELEMENT)) {
				toModify.getAtom(0).setIsAromatic(true);
				toModify.getAtom(1).setIsAromatic(true);
				//toModify.setOrder(IBond.Order.SINGLE);
				toModify.setIsAromatic(true);
			}
			else {
				toModify.setIsAromatic(false);
			}
			if (type.equals(Expr.Type.IS_IN_RING) || type.equals(Expr.Type.RING_COUNT)
					|| type.equals(Expr.Type.RING_SIZE))
				toModify.setIsInRing(true);
			else
				toModify.setIsInRing(false);
			if (type.equals(Expr.Type.STEREOCHEMISTRY)) {
				int stereo = val2.get(0);
				switch(stereo) {
				case 0x2f:
					toModify.setStereo(IBond.Stereo.UP);
					break;
				case 0x5c:
					toModify.setStereo(IBond.Stereo.DOWN);
					break;
				}
			}
		}
		//System.out.println("BOND ORDER end : "+toModify.getOrder());
	}
	
	private IBond makeBond(IAtomContainer ac, IAtom begin, IAtom end, QueryBond ref) {
		IBond newBond = new Bond();
		newBond.setAtom(begin, 0);
		newBond.setAtom(end, 1);
		newBond.setIsAromatic(false);
		newBond.setOrder(IBond.Order.UNSET);
		Map<Type, List<Integer>> exprs = ref.getExpression().toMap();
		if (exprs.containsKey(Expr.Type.SINGLE_OR_AROMATIC)) {
			newBond.setOrder(IBond.Order.SINGLE);
		}
		else if (exprs.containsKey(Expr.Type.DOUBLE_OR_AROMATIC)) {
			newBond.setOrder(IBond.Order.DOUBLE);
		}
		if (exprs.containsKey(Expr.Type.ALIPHATIC_ORDER)) {
			int order = exprs.get(Expr.Type.ALIPHATIC_ORDER).get(0);
			switch(order) {
			case 1:
				newBond.setOrder(IBond.Order.SINGLE);
				break;
			case 2:
				newBond.setOrder(IBond.Order.DOUBLE);
				break;
			case 3:
				newBond.setOrder(IBond.Order.TRIPLE);
				break;
			case 4:
				newBond.setOrder(IBond.Order.QUADRUPLE);
				break;
			}
			
		}
		if (exprs.containsKey(Expr.Type.IS_AROMATIC)) {
			begin.setIsAromatic(true);
			end.setIsAromatic(true);
			newBond.setOrder(IBond.Order.SINGLE);
			newBond.setIsAromatic(true);
		}
		if (exprs.containsKey(Expr.Type.STEREOCHEMISTRY)) {
			int stereo = exprs.get(Expr.Type.STEREOCHEMISTRY).get(0);
			switch(stereo) {
			case 0x2f:
				newBond.setStereo(IBond.Stereo.UP);
				break;
			case 0x5c:
				newBond.setStereo(IBond.Stereo.DOWN);
				break;
			}
		}
		if (exprs.containsKey(Expr.Type.IS_IN_RING) || exprs.containsKey(Expr.Type.RING_COUNT)
				|| exprs.containsKey(Expr.Type.RING_SIZE))
			newBond.setIsInRing(true);
		else
			newBond.setIsInRing(false);
		ac.addBond(newBond);
		
		return newBond;
	}
	
	private IAtom configAtom(QueryAtom source, QueryAtom target, IAtom toModify, IAtomContainer ac) {
		
		//System.out.println("1.source: "+source.getExpression().toString());
		//System.out.println("2.target: "+target.getExpression().toString());
		//System.out.println("2.target :"+target.getProperty(CDKConstants.ATOM_ATOM_MAPPING)+ ":"+target.isAromatic());
		//System.out.println("3.tomodify:"+ toModify.isAromatic()+ " toModify :"+toModify.getProperty(CDKConstants.ATOM_ATOM_MAPPING) );
		

		Map<Type, List<Integer>> exprs1 = source.getExpression().toMap();
		Map<Type, List<Integer>> exprs2 = target.getExpression().toMap();

		
		for (Entry<Type, List<Integer>> entry : exprs2.entrySet()) {
			Expr.Type type = entry.getKey();
			List<Integer> val2 = entry.getValue();
			if (exprs1.containsKey(type)) {
				List<Integer> val1 = exprs1.get(type);
				if (type.equals(Expr.Type.FORMAL_CHARGE)) {
					int delta = findMin(val2) - findMin(val1);
					int charge = toModify.getFormalCharge() + delta;
					toModify.setFormalCharge(charge);
				}
				else if (type.equals(Expr.Type.IMPL_H_COUNT)) {
					int delta = findMin(val2) - findMin(val1);
					int hCount = toModify.getImplicitHydrogenCount() + delta;
					toModify.setImplicitHydrogenCount(hCount);
				}
				else if (type.equals(Expr.Type.TOTAL_H_COUNT)) {
					int delta = findMin(val2) - findMin(val1);
					int hCount = toModify.getImplicitHydrogenCount();
					//add hydrogen
					if (delta > 0) {
						toModify.setImplicitHydrogenCount(hCount);
					}
					//remove hydrogen
					else if (delta < 0) { 
						int cpt = 0;
						//first remove implicit hydrogen and then explicit if necessary
						while (cpt != delta) {
							if (hCount + delta >= 0) {
								hCount--;
								toModify.setImplicitHydrogenCount(hCount);
							}
							else {
								for (IBond bond : ac.getConnectedBondsList(toModify)) {
									IAtom other = bond.getOther(toModify);
									if (other.getSymbol().equals("H")) {
										ac.removeBond(bond);
										ac.removeAtom(other);
									}
								}
							}
							cpt--;
						}
					}
				}
				else if (type.equals(Expr.Type.VALENCE)) {
					int delta = findMin(val2) - findMin(val1);
					int valence = toModify.getValency()+ delta;
					toModify.setValency(valence);
					
				}
				
			}
			else {
				if (type.equals(Expr.Type.FORMAL_CHARGE)) {
					if (val2.size() > 1) {
						System.err.println("Can not manage yet more than one FORMAL_CHARGE type");
						System.exit(0);
					}
					else
						toModify.setFormalCharge(val2.get(0));
				}
				else if (type.equals(Expr.Type.IMPL_H_COUNT)) {
					if (val2.size() > 1) {
						System.err.println("Can not manage yet more than one IMPL_H_COUNT type");
						System.exit(0);
					}
					else
						toModify.setImplicitHydrogenCount(val2.get(0));
				}
				else if (type.equals(Expr.Type.TOTAL_H_COUNT)) {
					
					if (val2.size() > 1) {
						
						// count the number of explicit hydrogen 
						int explicithydrogencount = 0;
						int others=0;
						boolean stereo_bond = false;
						for (IAtom neighbors : ac.getConnectedAtomsList(toModify)) {
				    		if (neighbors.getSymbol().equals("H")) {
				    			explicithydrogencount++;
				    			if(ac.getBond(neighbors, toModify).getStereo() != Stereo.NONE) {
				    				stereo_bond = true;
				    			}
				    		}
				    		else {
				    			others++;
				    		}
				    	}
						// valence
						int valence = ElementCalculation.calculateValence(toModify.getSymbol());
						//implicit hydrogen
						int implicithydrogencount = (valence - (others + explicithydrogencount));
						//display 
						//System.out.println("valence : "+valence+" explicit hydrogens : "+ explicithydrogencount+" others atoms: "+ others+" so implicit : "+ implicithydrogencount +"stereo :"+stereo_bond);
						double bondOrderSum = ac.getBondOrderSum(toModify);

						//int hcount = toModify.getImplicitHydrogenCount() == CDKConstants.UNSET ? 0 : toModify.getImplicitHydrogenCount();
						//int charge = toModify.getFormalCharge() == CDKConstants.UNSET ? 0 : toModify.getFormalCharge();
						//int radical = toModify.getProperty(additionalConstants.RADICAL) == null ? 0 : toModify.getProperty(additionalConstants.RADICAL);


						// calculate the valence
						//double cVal = bondOrderSum - charge + hcount + radical;
						if(implicithydrogencount == 0 && explicithydrogencount >0 && stereo_bond == false) {
							
							// how many hydrogens atoms to add 
							 List<IAtom> hydrogens = new ArrayList<IAtom>();
						     List<IBond> newBonds = new ArrayList<IBond>();
						     
						     for (int i = 0; i < implicithydrogencount; i++) {

			                     IAtom hydrogen = toModify.getBuilder().newInstance(IAtom.class, "H");
			                       hydrogen.setAtomTypeName("H");
			                       hydrogen.setImplicitHydrogenCount(0);
			                       hydrogens.add(hydrogen);
			                       newBonds.add(toModify.getBuilder().newInstance(IBond.class, toModify, hydrogen,
			                                Order.SINGLE));
						     }
						     for (IAtom atom : hydrogens)
						         ac.addAtom(atom);
						     for (IBond bond : newBonds)
						         ac.addBond(bond);
						}
						else {
							
							// how many hydrogens to set implicit
							toModify.setImplicitHydrogenCount(implicithydrogencount);
						}
						
						//System.err.println("Can not manage yet more than one TOTAL_H_COUNT type");
						//System.exit(0);
					}
					else
						toModify.setImplicitHydrogenCount(val2.get(0));
				}
				else if (type.equals(Expr.Type.VALENCE)) {
					if (val2.size() > 1) {
						System.err.println("Can not manage yet more than one VALENCE type");
						System.exit(0);
					}
					else
						toModify.setValency(val2.get(0));
					
				}
			}
			/*if (type.equals(Expr.Type.IS_AROMATIC) || type.equals(Expr.Type.AROMATIC_ELEMENT) || toModify.isAromatic() == true)
				toModify.setIsAromatic(true);
			else
				toModify.setIsAromatic(false);*/
			if (type.equals(Expr.Type.IS_IN_RING) || type.equals(Expr.Type.RING_COUNT)
					|| type.equals(Expr.Type.RING_SIZE))
				toModify.setIsInRing(true);
			else
				toModify.setIsInRing(false);
			
			
		}
		return toModify;
	}
	
	private IAtom configAtom(IAtomContainer ac, QueryAtom ref) {
		IAtom target = getAtomByAam(ac, ref.getProperty(CDKConstants.ATOM_ATOM_MAPPING));
		Expr.Type[] testUniqueElement = { Expr.Type.ALIPHATIC_ELEMENT, Expr.Type.AROMATIC_ELEMENT, Expr.Type.ELEMENT };
		Map<Type, List<Integer>> exprs = ref.getExpression().toMap();
		boolean matches = false;
		for (Expr.Type type : testUniqueElement) {
			if (exprs.containsKey(type)) {
				if (matches) {
					System.err.println("Can not manage yet more than one element type");
					System.exit(0);
				}
				if (exprs.get(type).size() > 1) {
					System.err.println("Can not manage yet more than one element type");
					System.exit(0);
				} else {
					// calculate delta by getting min in list
					if (exprs.containsKey(Expr.Type.FORMAL_CHARGE)) {
						if (exprs.get(Expr.Type.FORMAL_CHARGE).size() > 1) {
							System.err.println("Can not manage yet more than one possible charge ");
							System.exit(0);
						} else
							target.setFormalCharge(exprs.get(Expr.Type.FORMAL_CHARGE).get(0));
					}
					if (exprs.containsKey(Expr.Type.IMPL_H_COUNT))
						target.setImplicitHydrogenCount(exprs.get(Expr.Type.IMPL_H_COUNT).get(0));
					//get explicit hydrogen and deduce implicit number
					if (exprs.containsKey(Expr.Type.TOTAL_H_COUNT)) {
						int hCount = 0;
						for (IAtom atom2 : ac.getConnectedAtomsList(target)) {
							QueryAtom qa = (QueryAtom) atom2;
							Map<Type, List<Integer>> exprs2 = qa.getExpression().toMap();
							for (Expr.Type type2 : testUniqueElement) {
								if (exprs.containsKey(type)) {
									if (matches) {
										System.err.println("Can not manage yet more than one element type");
										System.exit(0);
									}
									if (exprs.get(type2).size() > 1) {
										System.err.println("Can not manage yet more than one element type");
										System.exit(0);
									} else {
										if (exprs.get(type).get(0) == 1)
											hCount++;
									}
								}
							}
							target.setImplicitHydrogenCount(exprs.get(Expr.Type.IMPL_H_COUNT).get(0) - hCount);
						}
						if (exprs.containsKey(Expr.Type.IS_AROMATIC) || type.equals(Expr.Type.AROMATIC_ELEMENT))
							target.setIsAromatic(true);
						else
							target.setIsAromatic(false);
						if (exprs.containsKey(Expr.Type.IS_IN_RING) || exprs.containsKey(Expr.Type.RING_COUNT)
								|| exprs.containsKey(Expr.Type.RING_SIZE))
							target.setIsInRing(true);
						else
							target.setIsInRing(false);
						if (exprs.containsKey(Expr.Type.VALENCE)) {
							if (exprs.get(Expr.Type.VALENCE).size() > 1) {
								System.err.println("Can not manage yet more than one possible valence ");
								System.exit(0);
							} else
								target.setFormalCharge(exprs.get(Expr.Type.VALENCE).get(0));
						}
					}
					return target;
				}
			}
		}
		return target;
	}
	
	private IAtom configAtom(IAtomContainer ac, Expr expr, IAtom ref) {
		if (expr.type().isLogical()) {
			Expr.Type type = expr.left().type();
			//if ()
			
		}
		/*ref.setFormalCharge(charge);
		//TODO get totalt hydrogen and chek if H or h
		ref.setImplicitHydrogenCount(hydrogenCount);
		ref.setIsAromatic(arom);
		ref.setIsInRing(arom);
		new Expr(Expr.Type.VALENCE, 1);
		ref.setValency(valency);*/
		return ref;
	}
	
	private void finalize(IAtomContainer aggregate, QueryAtomContainer productsSmirks) {
		
		

		Map<IChemObject, IStereoElement> savedStereo = new HashMap<IChemObject, IStereoElement>();
		List<IStereoElement> stereo = new ArrayList<IStereoElement>();
		for (IStereoElement se : aggregate.stereoElements()) {
			savedStereo.put(se.getFocus(), se);
			stereo.add(se);
		}
		aggregate.setStereoElements(new ArrayList<IStereoElement>());
		for (IStereoElement se : productsSmirks.stereoElements()) {
			if (se instanceof TetrahedralChirality) {
				TetrahedralChirality th = (TetrahedralChirality) se;
				IAtom chiral = getAtomByAam(aggregate, th.getChiralAtom().getProperty(CDKConstants.ATOM_ATOM_MAPPING));
				IAtom[] ligands = new IAtom[4];
				for (int i = 0; i < th.getLigands().length; i++) {
					IAtom atom = th.getLigands()[i];
					ligands[i] = getAtomByAam(aggregate, atom.getProperty(CDKConstants.ATOM_ATOM_MAPPING));
				}
				if (savedStereo.containsKey(chiral))
					stereo.remove(savedStereo.get(chiral));
				stereo.add(new TetrahedralChirality(chiral, ligands, th.getConfig()));
				//comment because it destroyed stereo outside of the pattern
				//aggregate.addStereoElement(new TetrahedralChirality(chiral, ligands, th.getConfig()));
			}
			else if (se instanceof DoubleBondStereochemistry) {
				DoubleBondStereochemistry db = (DoubleBondStereochemistry) se;
				IBond focus = aggregate.getBond(
						getAtomByAam(aggregate, db.getFocus().getBegin().getProperty(CDKConstants.ATOM_ATOM_MAPPING)),
						getAtomByAam(aggregate, db.getFocus().getEnd().getProperty(CDKConstants.ATOM_ATOM_MAPPING)));
				IBond[] ligands = new IBond[2];
				for (int i = 0; i < db.getBonds().length; i++) {
					IBond bond = db.getBonds()[i];
					ligands[i] = aggregate.getBond(
							getAtomByAam(aggregate, bond.getBegin().getProperty(CDKConstants.ATOM_ATOM_MAPPING)),
							getAtomByAam(aggregate, bond.getEnd().getProperty(CDKConstants.ATOM_ATOM_MAPPING)));
				}
				if (savedStereo.containsKey(focus))
					stereo.remove(savedStereo.get(focus));
				stereo.add(new DoubleBondStereochemistry(focus, ligands, db.getConfig()));
				//comment because it destroyed stereo outside of the pattern
				//aggregate.addStereoElement(new DoubleBondStereochemistry(focus, ligands, db.getConfig()));
			}
		}
		aggregate.setStereoElements(stereo);
	}
	
	private IAtom getAtomByAam(IAtomContainerSet set, int aam) {
		for (IAtomContainer ac : set.atomContainers()) {
			IAtom atom = getAtomByAam(ac, aam); 
			if (atom != null)
				return atom;
		}
		return null;
	}
	
	private IAtom getAtomByAam(IAtomContainer ac, int aam) {
		for (IAtom atom : ac.atoms()) {
			if (atom.getProperty(CDKConstants.ATOM_ATOM_MAPPING) != null) {
				if (atom.getProperty(CDKConstants.ATOM_ATOM_MAPPING).equals(aam))
					return atom;
			}
		}
		return null;
	}
	
	public void mappingReattribution(IReaction reaction) {
		//old mapping new mapping
		Map<Integer,Integer> aam = new TreeMap<Integer,Integer>();
		
		for (IAtomContainer ac : reaction.getReactants().atomContainers()) {
			for (IAtom atom : ac.atoms()) {
				aam.put(atom.getProperty(CDKConstants.ATOM_ATOM_MAPPING), -1);
			}
		}
		int counter = 1;
		for (Entry<Integer,Integer> e : aam.entrySet()) {
			int i = e.getKey();
			aam.put(i, counter);
			counter++;
		}
		for (IAtomContainer ac : reaction.getReactants().atomContainers()) {
			for (IAtom atom : ac.atoms()) {
				int newAam = aam.get(atom.getProperty(CDKConstants.ATOM_ATOM_MAPPING));
				atom.setProperty(CDKConstants.ATOM_ATOM_MAPPING, newAam);
			}
		}
		for (IAtomContainer ac : reaction.getProducts().atomContainers()) {
			for (IAtom atom : ac.atoms()) {
				Integer oldAam = atom.getProperty(CDKConstants.ATOM_ATOM_MAPPING);
				if (oldAam == null) {
					atom.setProperty(CDKConstants.ATOM_ATOM_MAPPING, counter);
					counter++;
				}
				else if (!aam.containsKey(oldAam)){
					atom.setProperty(CDKConstants.ATOM_ATOM_MAPPING, counter);
					counter++;
				}
				else
					atom.setProperty(CDKConstants.ATOM_ATOM_MAPPING, aam.get(oldAam));
			}
		}
	}
	
	// function to find minimum value in an unsorted 
    // list in Java using Collection 
    public Integer findMin(List<Integer> list) 
    { 
  
        // check list is empty or not 
        if (list == null || list.size() == 0) { 
            return Integer.MAX_VALUE; 
        } 
  
        // create a new list to avoid modification  
        // in the original list 
        List<Integer> sortedlist = new ArrayList<>(list); 
  
        // sort list in natural order 
        Collections.sort(sortedlist); 
  
        // first element in the sorted list 
        // would be minimum 
        return sortedlist.get(0); 
    } 
  
    // function return maximum value in an unsorted 
    // list in Java using Collection 
    public Integer findMax(List<Integer> list) 
    { 
  
        // check list is empty or not 
        if (list == null || list.size() == 0) { 
            return Integer.MIN_VALUE; 
        } 
  
        // create a new list to avoid modification 
        // in the original list 
        List<Integer> sortedlist = new ArrayList<>(list); 
  
        // sort list in natural order 
        Collections.sort(sortedlist); 
  
        // last element in the sorted list would be maximum 
        return sortedlist.get(sortedlist.size() - 1); 
    } 
    
    private void attributeAtomAtomMapping(IAtomContainer ac, Map<IAtom, IAtom> aam) {
		//key:query atom in SMARTS value: atom in AC
		for (Entry<IAtom, IAtom> e : aam.entrySet()) {
			IAtom queryAtom = e.getKey();
			if (queryAtom.getProperty(CDKConstants.ATOM_ATOM_MAPPING) == null)
				e.getValue().setProperty("noMapping", true);
			else
				e.getValue().setProperty(CDKConstants.ATOM_ATOM_MAPPING, (int)queryAtom.getProperty(CDKConstants.ATOM_ATOM_MAPPING));
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
	private void GenerateAllCombinationsFromMultipleLists(List<List<Integer>> Lists, List<List<Integer>> result, int depth, 
			List<Integer> current, boolean intraCombinations) {
		if(depth == Lists.size()) {
			result.add(new ArrayList<Integer>(current));
			return;
		}

		for(int i = 0; i < Lists.get(depth).size(); i++) {
			int t = Lists.get(depth).get(i);

			List<Integer> temp = new ArrayList<Integer>(current);
			temp.add(t);

			GenerateAllCombinationsFromMultipleLists(Lists, result, depth + 1, temp, intraCombinations);

			//generate all combination inside a list and return a list which contains lists of different combination
			//ex sublist [3, 4, 5] -> combinationResultsOfTheSublist [[3, 4, 5], [3, 4], [3, 5], [3], [4, 5], [4], [5]]
			//limit to 5 times (stoichiometry)
			if (intraCombinations == true && i > 0 && i < stoichiometry) {
				List<Integer> sublist = Lists.get(depth).subList(0, i); 
				Integer[] sublistArray = sublist.toArray(new Integer[sublist.size()]);
				List<List<Integer>> combinationResultsOfTheSublist = new ArrayList<List<Integer>>();
				generateAllCombinationsInsideAList(sublistArray, combinationResultsOfTheSublist);

				//generate all combinations of the previously generated results with the other list in Lists
				for(int j = 0; j < combinationResultsOfTheSublist.size(); j++) {
					List<Integer> temp2 = new ArrayList<Integer>(temp);
					temp2.addAll(combinationResultsOfTheSublist.get(j));
					GenerateAllCombinationsFromMultipleLists(Lists, result, depth + 1, temp2, intraCombinations);
				}
			}

		}
	}
	
	/**
	 * @param elems
	 * @param result
	 */
	private void generateAllCombinationsInsideAList(Integer[] elems, List<List<Integer>> result) {
	    List<Integer> combination = new ArrayList<>();
	    recursive_combinations(combination, 0, elems, result);
	}
	
	/**
	 * @param combination
	 * @param ndx
	 * @param elems
	 * @param result
	 */
	private void recursive_combinations(List<Integer> combination,
                                      int ndx, Integer[] elems, List<List<Integer>> result) {
	    if(ndx == elems.length) {
	
	        // (reached end of list after selecting/not selecting)
	        if (!combination.isEmpty())
	            result.add(new ArrayList<Integer>(combination));
	
	    } 
	    else {
	
	        // (include element at ndx)
	        combination.add(elems[ndx]);
	        recursive_combinations(combination, ndx+1, elems, result);
	
	        // (don't include element at ndx)
	        combination.remove(elems[ndx]);
	        recursive_combinations(combination, ndx+1, elems, result);
	
	    }
	}
	
	/**
	 * @param reaction
	 * @param aam
	 * @return
	 * @throws CDKException
	 */
	public String makeSmiles(IReaction reaction, boolean aam) throws CDKException {
		SmilesGenerator sg;
		if (aam == false) 
			sg = new SmilesGenerator(SmiFlavor.Stereo | SmiFlavor.UseAromaticSymbols);
		else
			sg = new SmilesGenerator(SmiFlavor.Stereo |SmiFlavor.AtomAtomMap | SmiFlavor.UseAromaticSymbols);
		//System.out.println(reaction.toString());
		return sg.create(reaction);
	}
	
	/**
	 * @param reaction
	 * @param aam
	 * @return
	 * @throws CDKException
	 */
	public String makeSmiles(IAtomContainer ac, boolean aam) throws CDKException {
		SmilesGenerator sg;
		if (aam == false) 
			sg = new SmilesGenerator(SmiFlavor.Stereo | SmiFlavor.UseAromaticSymbols);
		else
			sg = new SmilesGenerator(SmiFlavor.Stereo |SmiFlavor.AtomAtomMap | SmiFlavor.UseAromaticSymbols);
		return sg.create(ac);
	}


    public void convertExplicitToImplicitHydrogens(IReaction reaction) {
    	for (IAtomContainer ac : reaction.getReactants().atomContainers()) {
    		convertExplicitToImplicitHydrogens(ac);
    	}
    	for (IAtomContainer ac : reaction.getAgents().atomContainers()) {
    		convertExplicitToImplicitHydrogens(ac);
    	}
    	for (IAtomContainer ac : reaction.getProducts().atomContainers()) {
    		convertExplicitToImplicitHydrogens(ac);
    	}
    }
    
    /**
     * Returns The number of explicit hydrogens for a given IAtom.
     * @param atomContainer
     * @param atom
     * @return The number of explicit hydrogens on the given IAtom.
     */
    public void convertExplicitToImplicitHydrogens(IAtomContainer atomContainer) {
    	List<IAtom> toDelete = new ArrayList<IAtom>();
    	for (IAtom atom : atomContainer.atoms()) {
    		if (atom.getSymbol().equals("H"))
    			toDelete.add(atom);
    		int hCount = 0;
    		for (IAtom connectedAtom : atomContainer.getConnectedAtomsList(atom)) {
    			 if (connectedAtom.getSymbol().equals("H")) {
    				 for (IBond bond : atomContainer.getConnectedBondsList(connectedAtom)) {
    					 if (bond.getStereo() == null) {
    						 atomContainer.removeBond(bond);
    						 hCount++;
    					 }
    					 else {
    						 if (bond.getStereo() == IBond.Stereo.NONE) {
        						 atomContainer.removeBond(bond);
        						 hCount++;
        					 }
    						 else {
    							 toDelete.remove(connectedAtom);   						 
    						}
    					 }
    				 }
    			 }
    		}
    		atom.setImplicitHydrogenCount(hCount);
    	}
    	for (IAtom atom : toDelete) {
    		atomContainer.removeAtom(atom);
    	}
    }
	
	public int getStoichiometry() {
		return stoichiometry;
	}

	public void setStoichiometry(int stoichiometry) {
		this.stoichiometry = stoichiometry;
	}

	public boolean isAamDone() {
		return aamDone;
	}

	public void setAamDone(boolean aamDone) {
		this.aamDone = aamDone;
	}
	
	
}
