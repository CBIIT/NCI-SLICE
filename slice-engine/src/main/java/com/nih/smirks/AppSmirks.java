package com.nih.smirks;

import static org.openscience.cdk.DefaultChemObjectBuilder.getInstance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.openscience.cdk.Bond;
import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.aromaticity.Aromaticity;
import org.openscience.cdk.aromaticity.ElectronDonation;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.graph.CycleFinder;
import org.openscience.cdk.graph.Cycles;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IAtomContainerSet;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IReaction;
import org.openscience.cdk.interfaces.IReactionSet;
import org.openscience.cdk.isomorphism.Pattern;
import org.openscience.cdk.isomorphism.matchers.IQueryBond;
import org.openscience.cdk.isomorphism.matchers.QueryBond;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smarts.Smarts;
import org.openscience.cdk.smarts.SmartsPattern;
import org.openscience.cdk.smiles.SmiFlavor;
import org.openscience.cdk.smiles.SmilesGenerator;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.tools.manipulator.AtomContainerManipulator;

import com.nih.fragments.FragmentUtils;
import com.nih.smarts.Smarts2;
import com.nih.smarts.SmartsPattern2;

/**
 * Hello world!
 *
 */
public class AppSmirks 
{
    public static void main( String[] args ) throws CDKException, CloneNotSupportedException
    {
    	//String smirksString = "[NH2:1][cH:6]1[s:7][cH2:15][cH2:16][cH:9]1[C:18]#[N:20].[F:2][cH:3]1[cH:4]([N+:8](=[O:13])[O-:14])[cH2:10][cH:17]([cH:11]([cH2:5]1)[F:12])[F:19]>>[NH:1]([cH:3]1[cH:4]([N+:8](=[O:13])[O-:14])[cH2:10][cH:17]([cH:11]([cH2:5]1)[F:12])[F:19])[cH:6]2[s:7][cH2:15][cH2:16][cH:9]2[C:18]#[N:20].[FH:2]";
//    	String smirksString = "[NH2:1][cH:6]([s:7])[cH:9].[F:2][cH:3]([cH:4])[cH2:5]>>[NH:1]([cH:3]([cH:4])[cH2:5])[cH:6]([s:7])[cH:9].[FH:2]";
//    	String reactantsSmiles = "[NH2:1][cH:6]1[s:7][cH2:15][cH2:16][cH:9]1[C:18]#[N:20].[F:2][cH:3]1[cH:4]([N+:8](=[O:13])[O-:14])[cH2:10][cH:17]([cH:11]([cH2:5]1)[F:12])[F:19]";
//    	try {
//        	IAtomContainerSet products = applyTranform(reactantsSmiles, smirksString);
//    	}
//		catch (Exception e) {
//			System.out.println("err");
//		}
//    	
//    	String reactantSmi = "OC1=C(C2=CC=CC=C2C=C1)/C=N/C3=CC=C([N+]([O-])=O)C=C3";
//    	String smartsPtrn = "[O,S,Se,Te:1][CH1:2]=[CH1:5][CH1:6]=[CH1,N:3]>>[O,S,Se,Te:1]=[CH0:2][CH1:5]=[CH1:6][CH1,N:3]";
    	
    	//String reactantSmi = "O=C(C1=CC=CC=C1)N(C)C(C2=CC=CC=C2)=P(C3=CC=CC=C3)(C4=CC=CC=C4)C5=CC=CC=C5";
    	//String smartsPtrn = "[PX4:1]=[CX3:2][NX3:3][CX3:4]=[OX1:5]>>[PX5:1]1[CX3:2][NX3+:3]=[CX3:4][OX2:5]1";
    	
    	testTautomerizer("/home/delanneev/Downloads/tautomerizer_rules"); 
    	String reactantSmi = "CN1C(NC2=NC=CC=C2)=CC=C1" ;
    	//String smartsPtrn = "[#1:1][O,S,NX3:2][cX3;z2;r5:3]=[c;r5:4][c;r5:5]=[c;z{1-2};r5;R{1-2}:6]>>[O,S,NX2:2]=[CX3;z2;r5:3][C;r5:4]=[C;r5:5][Cz{1-2};r5;R{12}:6][#1:1]" ;
    	String smartsPtrn = "[O,S,NX3;H1:2][cX3;z2;r5:3]:[c;r5:4]:[c;r5:5]:[c;z{1-2}:6]>>[O,S,NX2:2]=[CX3;z2;r5:3][C;r5:4]=[C;r5:5][C;!H0:6]" ;

    	IAtomContainer smartMol = SilentChemObjectBuilder.getInstance().newAtomContainer();
    	//Smarts.parse(smartMol, smartsPtrn, Smarts.FLAVOR_CACTVS);
    	
    	//applyTranform2(reactantSmi, smartsPtrn);
	}
    
    private static IReaction applyTranform2(String reactantsSmiles, String smirksString) throws CloneNotSupportedException, CDKException {
    	SmilesParser sp = new SmilesParser(getInstance());
    	sp.kekulise(false);
    	IReaction reaction = DefaultChemObjectBuilder.getInstance().newInstance(IReaction.class);
    	Set<Pattern> smirksPattern = new HashSet<Pattern>();
    	IAtomContainerSet smirksReactants =  DefaultChemObjectBuilder.getInstance().newInstance(IAtomContainerSet.class);
    	IAtomContainerSet smirksProducts =  DefaultChemObjectBuilder.getInstance().newInstance(IAtomContainerSet.class);
    	Map<Pattern, IAtomContainer> reactantsMap = new HashMap<Pattern, IAtomContainer>();
    	
    	
    	for (String s : smirksString.split(">>")[0].split("\\.")) {
    		String smarts = s.replaceAll("\\s+","");
    		Pattern ptrn = SmartsPattern2.create(smarts, Smarts.FLAVOR_CACTVS) ; 
        	smirksPattern.add(ptrn);
        	IAtomContainer ac = DefaultChemObjectBuilder.getInstance().newAtomContainer();
        	Smarts2.parse(ac, smarts, Smarts.FLAVOR_CDK);
        	smirksReactants.addAtomContainer(ac);;
        	reactantsMap.put(ptrn, ac);
    	}
    	
    	for (String s : smirksString.split(">>")[1].split("\\.")) {
    		String smarts = s.replaceAll("\\s+","");
    		IAtomContainer ac = DefaultChemObjectBuilder.getInstance().newAtomContainer();
        	Smarts.parse(ac, smarts, Smarts.FLAVOR_CDK);
    		smirksProducts.addAtomContainer(ac);
    		IAtomContainer ac2 = sp.parseSmiles(smarts);
    		for (IBond b : ac.bonds()) {
    			IQueryBond b2 = (IQueryBond) b; 
    			System.out.println(b2.getOrder());
    		}
    		
    	}

    	String[] reactantsSmi = reactantsSmiles.split("\\.");
    	
    	//smirksReactants = smirksReaction.getReactants();
    	//smirksProducts = smirksReaction.getProducts();
    	IAtomContainer aggregateReactants = SilentChemObjectBuilder.getInstance().newAtomContainer();
    	IAtomContainer aggregateProducts = SilentChemObjectBuilder.getInstance().newAtomContainer();
    	
    	//prepare products container to apply the reaction and  and index bonds
    	sp.kekulise(true);
    	for (String reactant : reactantsSmi) {
    		IAtomContainer ac = sp.parseSmiles(reactant.replaceAll("\\s+","")); 
    		reaction.addReactant(ac);
    		aggregateReactants.add(ac);
    		aggregateProducts.add(ac.clone());
    	}

    	List<int[]> mappings = new ArrayList<int[]>();
    	//detect if all reactant patterns match
    	for (Pattern ptrn : smirksPattern) {
    		int[] mapping = ptrn.match(aggregateProducts);
    		if (mapping.length > 0) {
    			System.out.println("match");
    			mappings.add(mapping);
    		}
    	}
    	
    	
    	//apply the reaction
    	if (!mappings.isEmpty()) {
    		//index atom product
    		Map<Integer,IAtom> indexProduct = new HashMap<Integer,IAtom>();
    		IAtomContainer aggregateSmirksProducts = SilentChemObjectBuilder.getInstance().newAtomContainer();
    		for (IAtomContainer smirksProduct : smirksProducts.atomContainers()) {
    			aggregateSmirksProducts.add(smirksProduct);
    			for (IAtom atom : smirksProduct.atoms()) {
    				indexProduct.put(atom.getProperty(CDKConstants.ATOM_ATOM_MAPPING), atom);
    			}
    		}
    		
    		//apply changes
    		List<IBond> processed = new ArrayList<IBond>();
    		Map<IBond,IBond> changes = new HashMap<IBond,IBond>();
    		Map<Integer,IAtom> aam = new HashMap<Integer,IAtom>();
    		for (int[] mapping : mappings) {
    			for (int i = 0; i < mapping.length; i++) {
    				IAtom smirksReactantAtom = aggregateReactants.getAtom(i);
    				IAtom smirksProductAtom = indexProduct.get(smirksReactantAtom.getProperty(CDKConstants.ATOM_ATOM_MAPPING));
    				aam.put(smirksReactantAtom.getProperty(CDKConstants.ATOM_ATOM_MAPPING), aggregateProducts.getAtom(mapping[i]));
    				
    				System.out.println(smirksProductAtom);
    				
    				List<IBond> conSmirksReactants = aggregateReactants.getConnectedBondsList(smirksReactantAtom);
    				List<IBond> conSmirksProducts = aggregateSmirksProducts.getConnectedBondsList(smirksProductAtom);
    				//remove process bond in reactants and products
    				for (IBond bond : new ArrayList<IBond>(conSmirksReactants)) {
    					if (processed.contains(bond)) {
    						conSmirksReactants.remove(bond);
    					}
    				}
    				for (IBond bond : new ArrayList<IBond>(conSmirksProducts)) {
    					if (processed.contains(bond)) {
    						conSmirksProducts.remove(bond);
    					}
    				}
    				
    				changes.putAll(findChanges(conSmirksReactants, conSmirksProducts));
    				
    				
    				processed.addAll(conSmirksReactants);
    				processed.addAll(conSmirksProducts);
    				
    			}
    			
    		}
			//apply bond and atom changes
    		applyChanges(aggregateProducts, changes,  aam, indexProduct);
    		System.out.println("smi1 " +makeSmiles(aggregateProducts.clone(), false, false));
    		//reset flags and get product separated
    		FragmentUtils.resetVisitedFlags(aggregateProducts);
    		IAtomContainerSet products = FragmentUtils.makeAtomContainerSet(aggregateProducts);
    		reaction.setProducts(products);
    		for (IAtomContainer pro : products.atomContainers()) {
    			 System.out.println("smi2 " +makeSmiles(pro.clone(), false, false));
    		}

    	}
    	else {
    		System.err.println("Could not match the reaction");
    	}
		return reaction;
    	
    	//TODO check SMIRKS is correct (contains aam for all atoms)
    }

    
    private static IAtomContainerSet applyTranform(String reactantsSmiles, String smirksString) throws CloneNotSupportedException, CDKException {
    	SmilesParser sp = new SmilesParser(getInstance());
    	sp.kekulise(false);
    	IReaction smirksReaction = sp.parseReactionSmiles(smirksString);
    	String[] reactantsSmi = reactantsSmiles.split("\\.");
    	
    	IAtomContainerSet smirksReactants = smirksReaction.getReactants();
    	IAtomContainerSet smirksProducts = smirksReaction.getProducts();
    	IAtomContainer aggregateProducts = SilentChemObjectBuilder.getInstance().newAtomContainer();
    	
    	//prepare products container to apply the reaction and  and index bonds
    	sp.kekulise(true);
    	for (String reactant : reactantsSmi) {
    		aggregateProducts.add(sp.parseSmiles(reactant));
    	}

    	Map<IAtomContainer, int[]> mappings = new HashMap<IAtomContainer, int[]>();
    	//detect if all reactant patterns match
    	for (IAtomContainer smirksReactant : smirksReactants.atomContainers()) {
    		Pattern ptrn = Pattern.findSubstructure(smirksReactant);
    		int[] mapping = ptrn.match(aggregateProducts);
    		if (mapping.length > 0) {
    			mappings.put(smirksReactant, mapping);
    		}
    	}
    	
    	
    	//apply the reaction
    	if (!mappings.isEmpty()) {
    		//index atom product
    		Map<Integer,IAtom> indexProduct = new HashMap<Integer,IAtom>();
    		IAtomContainer aggregateSmirksProducts = SilentChemObjectBuilder.getInstance().newAtomContainer();
    		for (IAtomContainer smirksProduct : smirksProducts.atomContainers()) {
    			aggregateSmirksProducts.add(smirksProduct);
    			for (IAtom atom : smirksProduct.atoms()) {
    				indexProduct.put(atom.getProperty(CDKConstants.ATOM_ATOM_MAPPING), atom);
    			}
    		}
    		
    		//apply changes
    		List<IBond> processed = new ArrayList<IBond>();
    		Map<IBond,IBond> changes = new HashMap<IBond,IBond>();
    		Map<Integer,IAtom> aam = new HashMap<Integer,IAtom>();
    		for (Entry<IAtomContainer, int[]> e : mappings.entrySet()) {
    			IAtomContainer smirksReactant = e.getKey();
    			int[] mapping = e.getValue();
    			for (int i = 0; i < mapping.length; i++) {
    				IAtom smirksReactantAtom = smirksReactant.getAtom(i);
    				IAtom smirksProductAtom = indexProduct.get(smirksReactantAtom.getProperty(CDKConstants.ATOM_ATOM_MAPPING));
    				aam.put(smirksReactantAtom.getProperty(CDKConstants.ATOM_ATOM_MAPPING), aggregateProducts.getAtom(mapping[i]));
    				
    				System.out.println(smirksProductAtom);
    				
    				List<IBond> conSmirksReactants = smirksReactant.getConnectedBondsList(smirksReactantAtom);
    				List<IBond> conSmirksProducts = aggregateSmirksProducts.getConnectedBondsList(smirksProductAtom);
    				//remove process bond in reactants and products
    				for (IBond bond : new ArrayList<IBond>(conSmirksReactants)) {
    					if (processed.contains(bond)) {
    						conSmirksReactants.remove(bond);
    					}
    				}
    				for (IBond bond : new ArrayList<IBond>(conSmirksProducts)) {
    					if (processed.contains(bond)) {
    						conSmirksProducts.remove(bond);
    					}
    				}
    				
    				changes.putAll(findChanges(conSmirksReactants, conSmirksProducts));
    				
    				
    				processed.addAll(conSmirksReactants);
    				processed.addAll(conSmirksProducts);
    				
    			}
    			
    		}
			//apply bond and atom changes
    		applyChanges(aggregateProducts, changes,  aam, indexProduct);
    		//reset flags and get product separated
    		FragmentUtils.resetVisitedFlags(aggregateProducts);
    		IAtomContainerSet products = FragmentUtils.makeAtomContainerSet(aggregateProducts);


    	}
    	else {
    		System.err.println("Could not match the reaction");
    	}
		return smirksProducts;
    	
    	//TODO check SMIRKS is correct (contains aam for all atoms)
    }
   
    private static Map<IBond,IBond> findChanges(List<IBond> inReactants, List<IBond> inProducts) {
    	Map<IBond,IBond> res = new HashMap<IBond,IBond>();
    	List<IBond> rMatched = new ArrayList<IBond>();
    	List<IBond> pMatched = new ArrayList<IBond>();
    	for (IBond rBond : inReactants) {
    		String rid = "";
    		if ((int)rBond.getBegin().getProperty(CDKConstants.ATOM_ATOM_MAPPING) > 
    			(int)rBond.getEnd().getProperty(CDKConstants.ATOM_ATOM_MAPPING)) {
    			rid = rBond.getEnd().getProperty(CDKConstants.ATOM_ATOM_MAPPING) + "-" + 
    					rBond.getBegin().getProperty(CDKConstants.ATOM_ATOM_MAPPING);
    		}
    		else {
    			rid = rBond.getEnd().getProperty(CDKConstants.ATOM_ATOM_MAPPING) + "-" + 
    					rBond.getBegin().getProperty(CDKConstants.ATOM_ATOM_MAPPING);
    		}
    		for (IBond pBond : inProducts) {
    			if (pMatched.contains(pBond))
    				continue;
    			String pid = "";
        		if ((int)pBond.getBegin().getProperty(CDKConstants.ATOM_ATOM_MAPPING) > 
        			(int)pBond.getEnd().getProperty(CDKConstants.ATOM_ATOM_MAPPING)) {
        			pid = pBond.getEnd().getProperty(CDKConstants.ATOM_ATOM_MAPPING) + "-" + 
        					pBond.getBegin().getProperty(CDKConstants.ATOM_ATOM_MAPPING);
        		}
        		else {
        			pid = pBond.getEnd().getProperty(CDKConstants.ATOM_ATOM_MAPPING) + "-" + 
        					pBond.getBegin().getProperty(CDKConstants.ATOM_ATOM_MAPPING);
        		}
        		if (rid.equals(pid)) {
        			rMatched.add(rBond);
        			pMatched.add(pBond);
        			if (areBothBondsEquals(rBond, pBond)) {
        				res.put(rBond, pBond);
        			}
        		}
    		}
    	}
    	
    	//amde bond
    	List<IBond> formed = inProducts;
    	formed.removeAll(pMatched);
    	for (IBond bond : formed) {
    		res.put(new Bond(), bond);
    	}
    	
    	//broken bond
    	List<IBond> broken = inReactants;
    	broken.removeAll(rMatched);
    	for (IBond bond : broken) {
    		res.put(bond, new Bond());
    	}
		return res;
    }
    
    private static boolean areBothBondsEquals(IBond rBond, IBond pBond) {
    	boolean isEqual = true;
    	System.out.println(pBond.getOrder());
    	System.out.println(rBond.getOrder());
    	if (rBond.isAromatic() != pBond.isAromatic()) {
    		rBond.setIsAromatic(pBond.isAromatic());
    		isEqual = false;
    	}
    	else {
    		if (!rBond.getOrder().equals(pBond.getOrder())) {
    			rBond.setOrder(pBond.getOrder());
    			isEqual = false;
    		}
    	}
    	if (!rBond.getStereo().equals(pBond.getStereo())) {
    		rBond.setStereo(pBond.getStereo());
    		isEqual = false;
    	}
    	
    	IAtom pBeginAtom;
    	IAtom pEndAtom;
    	if (rBond.getEnd().getProperty(CDKConstants.ATOM_ATOM_MAPPING).equals(pBond.getEnd().getProperty(CDKConstants.ATOM_ATOM_MAPPING))) {
    		pBeginAtom = pBond.getBegin();
    		pEndAtom = pBond.getEnd();
    	}
    	else {
    		pBeginAtom = pBond.getEnd();
    		pEndAtom = pBond.getBegin();
    	}
    	
    	if (rBond.getBegin().getFormalCharge() != pBeginAtom.getFormalCharge()) {
    		rBond.getBegin().setFormalCharge(pBeginAtom.getFormalCharge());
    		isEqual = false;
    	}
    	if (rBond.getEnd().getFormalCharge() != pEndAtom.getFormalCharge()) {
    		rBond.getEnd().setFormalCharge(pEndAtom.getFormalCharge());
    		isEqual = false;
    	}
    	if (rBond.getBegin().getExactMass() != pBeginAtom.getExactMass()) {
    		rBond.getBegin().setExactMass(pBeginAtom.getExactMass());
    		isEqual = false;
    	}
    	if (rBond.getEnd().getExactMass() != pEndAtom.getExactMass()) {
    		rBond.getEnd().setExactMass(pEndAtom.getExactMass());
    		isEqual = false;
    	}
    	if (rBond.getBegin().isInRing() != pBeginAtom.isInRing()) {
    		rBond.getBegin().setIsInRing(pBeginAtom.isInRing());
    		isEqual = false;
    	}
    	if (rBond.getEnd().isInRing() != pEndAtom.isInRing()) {
    		rBond.getEnd().setIsInRing(pEndAtom.isInRing());
    		isEqual = false;
    	}
    	if (rBond.getBegin().getImplicitHydrogenCount() != pBeginAtom.getImplicitHydrogenCount()) {
    		rBond.getBegin().setImplicitHydrogenCount(pBeginAtom.getImplicitHydrogenCount());
    		isEqual = false;
    	}
    	if (rBond.getEnd().getImplicitHydrogenCount() != pEndAtom.getImplicitHydrogenCount()) {
    		rBond.getEnd().setImplicitHydrogenCount(pEndAtom.getImplicitHydrogenCount());
    		isEqual = false;
    	}
    	if (rBond.getBegin().getCharge() != pBeginAtom.getCharge()) {
    		rBond.getBegin().setCharge(pBeginAtom.getCharge());
    		isEqual = false;
    	}
    	if (rBond.getEnd().getCharge() != pEndAtom.getCharge()) {
    		rBond.getEnd().setCharge(pEndAtom.getCharge());
    		isEqual = false;
    	}
    	if (rBond.getBegin().getHybridization() != pBeginAtom.getHybridization()) {
    		rBond.getBegin().setHybridization(pBeginAtom.getHybridization());
    		isEqual = false;
    	}
    	if (rBond.getEnd().getHybridization() != pEndAtom.getHybridization()) {
    		rBond.getEnd().setHybridization(pEndAtom.getHybridization());
    		isEqual = false;
    	}
    	if (rBond.getBegin().getValency()!= pBeginAtom.getValency()) {
    		rBond.getBegin().setValency(pBeginAtom.getValency());
    		isEqual = false;
    	}
    	if (rBond.getEnd().getValency() != pEndAtom.getValency()) {
    		rBond.getEnd().setValency(pEndAtom.getValency());
    		isEqual = false;
    	}
    	return isEqual;
    }
    
    private static void applyChanges(IAtomContainer ac, Map<IBond,IBond> changesMap, 
    		Map<Integer,IAtom> aam, Map<Integer,IAtom> indexProduct) {
    	for (Entry<IBond,IBond> e : changesMap.entrySet()) {
    		IBond rBondSmirks = e.getKey();
    		IBond pBondSmirks = e.getValue();
    		IAtom begin;
    		IAtom end;
    		//made
    		if (rBondSmirks.getBegin() == null) {
    			IBond newBond = new Bond();
    			begin = aam.get(pBondSmirks.getBegin().getProperty(CDKConstants.ATOM_ATOM_MAPPING));
    			end = aam.get(pBondSmirks.getEnd().getProperty(CDKConstants.ATOM_ATOM_MAPPING));
    			newBond.setAtom(begin, 0);
    			newBond.setAtom(end, 1);
    			newBond.setIsAromatic(pBondSmirks.isAromatic());
    			newBond.setOrder(pBondSmirks.getOrder());
    			newBond.setStereo(pBondSmirks.getStereo());
    			ac.addBond(newBond);
    			applyAtomModification(begin, indexProduct.get(pBondSmirks.getBegin().getProperty(CDKConstants.ATOM_ATOM_MAPPING)));
    			applyAtomModification(end, indexProduct.get(pBondSmirks.getEnd().getProperty(CDKConstants.ATOM_ATOM_MAPPING)));
    		}
    		//broken
    		else if (pBondSmirks.getBegin() == null) {
    			begin = aam.get(rBondSmirks.getBegin().getProperty(CDKConstants.ATOM_ATOM_MAPPING));
    			end = aam.get(rBondSmirks.getEnd().getProperty(CDKConstants.ATOM_ATOM_MAPPING));
    			IBond broken = ac.getBond(begin, end);
    			ac.removeBond(broken);
    			applyAtomModification(begin, indexProduct.get(rBondSmirks.getBegin().getProperty(CDKConstants.ATOM_ATOM_MAPPING)));
    			applyAtomModification(end, indexProduct.get(rBondSmirks.getEnd().getProperty(CDKConstants.ATOM_ATOM_MAPPING)));
    		}
    		// other change s(stereo, order, atom charge,...)
    		else {
    			begin = aam.get(pBondSmirks.getBegin().getProperty(CDKConstants.ATOM_ATOM_MAPPING));
    			end = aam.get(pBondSmirks.getEnd().getProperty(CDKConstants.ATOM_ATOM_MAPPING));
    			IBond changed = ac.getBond(begin, end);
    			changed.setAtom(begin, 0);
    			changed.setAtom(end, 1);
    			changed.setIsAromatic(pBondSmirks.isAromatic());
    			if (pBondSmirks.getOrder() != IBond.Order.UNSET)
    				changed.setOrder(pBondSmirks.getOrder());
    			changed.setStereo(pBondSmirks.getStereo());
    			applyAtomModification(begin, indexProduct.get(pBondSmirks.getBegin().getProperty(CDKConstants.ATOM_ATOM_MAPPING)));
    			applyAtomModification(end, indexProduct.get(pBondSmirks.getEnd().getProperty(CDKConstants.ATOM_ATOM_MAPPING)));
    		}		
    	}
    }
    
    private static void applyAtomModification(IAtom toModify, IAtom ref) {
    	if (ref.getCharge() != null)
    		toModify.setCharge(ref.getCharge());
    	if (ref.getExactMass() != null)
    		toModify.setExactMass(ref.getExactMass());
    	if (ref.getFormalCharge() != null)
    		toModify.setFormalCharge(ref.getFormalCharge());
    	if (ref.getHybridization() != null)
    		toModify.setHybridization(ref.getHybridization());
    	if (ref.getImplicitHydrogenCount() != null)
    		toModify.setImplicitHydrogenCount(ref.getImplicitHydrogenCount());
    	if (ref.isAromatic() == true)
    		toModify.setIsAromatic(ref.isAromatic());
    	if (ref.getValency() != null)
    		toModify.setValency(ref.getValency());
    }
    
	/**
	 * @param ac
	 * @param stereo
	 * @param aam
	 * @param missingHydrogen
	 * @return
	 * @throws CDKException
	 */
	private static String makeSmiles(IAtomContainer ac, boolean stereo, boolean aam) throws CDKException {
		SmilesGenerator sg;
		
		if (stereo == false && aam == false) 
			sg = new SmilesGenerator(SmiFlavor.Canonical);
		else if (stereo == true && aam == false) 
			sg = new SmilesGenerator(SmiFlavor.Absolute);
		else if (stereo == false && aam == true) 
			sg = new SmilesGenerator(SmiFlavor.AtomAtomMap);
		else
			sg = new SmilesGenerator(SmiFlavor.Absolute | SmiFlavor.AtomAtomMap);

		return sg.create(ac);
	}
	
	/**
	 * @param fileName
	 * @throws InvalidSmilesException
	 * @throws CloneNotSupportedException
	 */
	private static void testTautomerizer(String fileName) throws InvalidSmilesException, CloneNotSupportedException {
		SmilesParser sp = new SmilesParser(getInstance());
    	sp.kekulise(false);
    	
    	BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line; 
			int cpt = 0;
			int valid = 0;
			while ((line = br.readLine()) != null) {
				String[] props = line.split("\t");
				System.out.println(props[0]);
				//if (!props[0].equals("PT_49_00")) continue;
				String smirks = props[1];
				String[] reactantsSmi = props[2].split("\\.");
				Set<Pattern> smirksPattern = new HashSet<Pattern>();
				for (String s : smirks.split(">>")[0].split("\\.")) {
		    		String smarts = s.replaceAll("\\s+","");
		    		//try {
		    			Pattern ptrn = SmartsPattern2.create(smarts, Smarts.FLAVOR_CACTVS); 
		    			smirksPattern.add(ptrn);
		    		//}
//		    		catch (Exception e) {
//		    			System.err.println(props[0] + " " + smirks);
//		    			continue;
//		    		}
		    	}
				
				for (String s : smirks.split(">>")[1].split("\\.")) {
		    		String smarts = s.replaceAll("\\s+","");
		    		try {
		    			Pattern ptrn = SmartsPattern2.create(smarts, Smarts.FLAVOR_CACTVS) ; 
		    			smirksPattern.add(ptrn);
		    		}
		    		catch (Exception e) {
		    			System.err.println(props[0] + " " + smirks);
		    			continue;
		    		}
		    	}
				
				
				IAtomContainer aggregateProducts = SilentChemObjectBuilder.getInstance().newAtomContainer();
		    	
				boolean matchReac = false;
				
		    	//prepare products container to apply the reaction and  and index bonds
		    	sp.kekulise(true);
		    	for (String reactant : reactantsSmi) {
		    		//System.out.println(reactant);
		    		IAtomContainer ac = sp.parseSmiles(reactant.replaceAll("\\s+","")); 

		    		for (IBond b : ac.bonds()) {
		    			//System.out.println(b);
		    		}
		    		aggregateProducts.add(ac.clone());
		    	}
		    	//try {
					for (Pattern ptrn : smirksPattern) {
						AtomContainerManipulator.convertImplicitToExplicitHydrogens(aggregateProducts);	
						for (IAtom a : aggregateProducts.atoms()) {
							a.setIsAromatic(false);
						}
			    		int[] mapping = ptrn.match(aggregateProducts);
	
			    		if (mapping.length > 0) {
			    			System.out.println("->match");
			    			matchReac = true;
			    		}
			    		else {
			    			//System.out.println("->X");
			    		}
			    	}
		    	//}
		    	/*catch (Exception e) {
	    			System.err.println("err match " +props[0] + " " + smirks + " " + e);
	    			continue;
	    		}*/
			if (matchReac) valid++ ;
			cpt++;
			//if (cpt > 2) break;
			} 
			System.out.println(valid+"/"+cpt);
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
    
}
