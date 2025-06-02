package com.nih.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openscience.cdk.AtomContainer;
import org.openscience.cdk.Bond;
import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.aromaticity.Aromaticity;
import org.openscience.cdk.aromaticity.ElectronDonation;
import org.openscience.cdk.aromaticity.Kekulization;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.graph.CycleFinder;
import org.openscience.cdk.graph.Cycles;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IElement;
import org.openscience.cdk.interfaces.IReaction;
import org.openscience.cdk.interfaces.IStereoElement;
import org.openscience.cdk.interfaces.ITetrahedralChirality;
//import org.openscience.cdk.interfaces.IRingSet;
//import org.openscience.cdk.ringsearch.AllRingsFinder;
import org.openscience.cdk.smiles.SmiFlavor;
import org.openscience.cdk.smiles.SmilesGenerator;
import org.openscience.cdk.stereo.ExtendedTetrahedral;
import org.openscience.cdk.stereo.TetrahedralChirality;

import com.nih.slice.config.SliceBond.bondProp;

public class Tools {
	
	/**
	 * @param ac
	 * @throws CDKException
	 */
	public static void attributeIDtoAtomsAndBonds(IAtomContainer ac) throws CDKException {
		//attribute ID
		for (IBond b : ac.bonds()) {
			List<String> ids = new ArrayList<String>();
			ids.add(b.getBegin().getProperty(CDKConstants.ATOM_ATOM_MAPPING).toString());
			ids.add(b.getEnd().getProperty(CDKConstants.ATOM_ATOM_MAPPING).toString());
			Collections.sort(ids);
			b.setID(ids.get(0)+"-"+ids.get(1));
			b.setProperty("aromatic",b.isAromatic());
		}
		for (IAtom a : ac.atoms()) {
			a.setID(a.getProperty(CDKConstants.ATOM_ATOM_MAPPING).toString());
		}
	}
	
	/**
	 * @param ac
	 * @throws CDKException
	 */
	public static void perceiveAromaticityAndflagAtomsAndBonds(IAtomContainer ac) throws CDKException {
		ElectronDonation model       = ElectronDonation.daylight();
		CycleFinder      cycles      = Cycles.all();
		Aromaticity      aromaticity = new Aromaticity(model, cycles);

		//perceive aromaticity
		aromaticity.apply(ac);

		/*//flag bond and atom in ring
		AllRingsFinder arf = new AllRingsFinder();
		IRingSet r = arf.findAllRings(ac);
		// manually flag ring bonds
		for (IAtomContainer ar : r.atomContainers()) {
			for (IBond bond : ar.bonds())
				bond.setFlag(CDKConstants.ISINRING, true);
		}
		*/
	}
	
	/**
	 * @param ac
	 */
	public static void addMissingHydrogen(IAtomContainer ac) {
		for (IAtom atom : ac.atoms()) {
			if (atom.getSymbol().equals("R")) {
				atom.setImplicitHydrogenCount(0);
				continue;
			}
			double bondOrderSum = ac.getBondOrderSum(atom);  
			int hcount = atom.getImplicitHydrogenCount() == CDKConstants.UNSET ? 0 : atom.getImplicitHydrogenCount();
			int charge = atom.getFormalCharge() == CDKConstants.UNSET ? 0 : atom.getFormalCharge();

			double valence;
			//calculate the valence
			//TODO integrate min and max valence
//			if (atom.getBondOrderSum() != null) {
//				valence = atom.getBondOrderSum();
//			}
//			else {
				valence = ElementCalculation.calculateValence(atom.getSymbol());
				//System.out.print(atom.getSymbol() + " "+ valence);
//			}

			
			if (bondOrderSum - charge + hcount != valence) {
				int diff = (int) (valence - (bondOrderSum - charge + hcount));
				int implicitHydrogen2 = atom.getImplicitHydrogenCount() + diff; 
				int implicitHydrogen = implicitHydrogen2 > 0 ? implicitHydrogen2 : 0 ;
				atom.setImplicitHydrogenCount(implicitHydrogen);
			}
		}
	}
	

	/**
	 * @param ac
	 * @return
	 */
	public static boolean isValencyOfMoleculeValid(IAtomContainer ac) {
		boolean mustReturn = false;
		try {
			Kekulization.kekulize(ac);
		} catch (CDKException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			mustReturn = true;
			//return true;
		}
		if (mustReturn)
			return true;
		for (IAtom atom : ac.atoms()) {
			if (atom.getSymbol().equals("R")) {
				atom.setImplicitHydrogenCount(0);
				continue;
			}
			if (ac.getConnectedBondsCount(atom)== 0) {
				continue;
			}
			double bondOrderSum = ac.getBondOrderSum(atom);  
			int hcount = atom.getImplicitHydrogenCount() == CDKConstants.UNSET ? 0 : atom.getImplicitHydrogenCount();
			int charge = atom.getFormalCharge() == CDKConstants.UNSET ? 0 : atom.getFormalCharge();

			double valence;
			//calculate the valence
			valence = ElementCalculation.calculateValence(atom.getSymbol());
			
			if (bondOrderSum - charge + hcount != valence) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @param atom
	 * @return
	 */
	public static boolean isValencyOfMoleculeValid(IAtomContainer ac, IAtom atom) {
		if (atom.getSymbol().equals("R")) {
			atom.setImplicitHydrogenCount(0);
			return true;
		}
		if (ac.getConnectedBondsCount(atom)== 0) {
			return true;
		}
		double bondOrderSum = ac.getBondOrderSum(atom);  
		int hcount = atom.getImplicitHydrogenCount() == CDKConstants.UNSET ? 0 : atom.getImplicitHydrogenCount();
		int charge = atom.getFormalCharge() == CDKConstants.UNSET ? 0 : atom.getFormalCharge();

		double valence;
		//calculate the valence
		valence = ElementCalculation.calculateValence(atom.getSymbol());

		if (bondOrderSum - charge + hcount != valence) {
			return false;
		}

		return true;
	}
	
	public static int caclulateHydrogen(IAtomContainer ac, IAtom atom) {
		if (atom.getSymbol().equals("R")) {
			atom.setImplicitHydrogenCount(0);
			return -1;
		}
		double bondOrderSum = ac.getBondOrderSum(atom);  
		int hcount = atom.getImplicitHydrogenCount() == CDKConstants.UNSET ? 0 : atom.getImplicitHydrogenCount();
		int charge = atom.getFormalCharge() == CDKConstants.UNSET ? 0 : atom.getFormalCharge();

		double valence;
		//calculate the valence
		valence = ElementCalculation.calculateValence(atom.getSymbol());
		if (bondOrderSum - charge + hcount != valence) {
			int diff = (int) (valence - (bondOrderSum - charge + hcount));
			int implicitHydrogen2 = atom.getImplicitHydrogenCount() + diff; 
			int implicitHydrogen = implicitHydrogen2 > 0 ? implicitHydrogen2 : 0;
			return implicitHydrogen;
		}
		return -1;
	}
	

	/**
	 * @param reaction
	 * @param aam
	 * @return
	 * @throws CDKException
	 */
	public static String makeSmiles(IReaction reaction, boolean aam) throws CDKException {
		SmilesGenerator sg;
		if (aam == false) 
			sg = new SmilesGenerator(SmiFlavor.Stereo | SmiFlavor.UseAromaticSymbols);
		else
			sg = new SmilesGenerator(SmiFlavor.Stereo |SmiFlavor.AtomAtomMap | SmiFlavor.UseAromaticSymbols);
		return sg.create(reaction);
	}
	
	/**
	 * @param ac
	 * @param stereo
	 * @param aam
	 * @param missingHydrogen
	 * @return
	 * @throws CDKException
	 */
	public static String makeSmiles(IAtomContainer ac, boolean stereo, boolean aam, boolean missingHydrogen) throws CDKException {
		SmilesGenerator sg;
		
		if (missingHydrogen)
			addMissingHydrogen(ac);
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
	
	 public static IAtomContainer convertExplicitToImplicitHydrogens(IAtomContainer atomContainer) {
	        
	        IAtomContainer atomContainer2 = new AtomContainer();
	        
	        Map<IAtom, IAtom> atomMapping = new HashMap<>();
	        Map<IBond, IBond> bondMapping = new HashMap<>();
	        
	        for (IAtom atom : atomContainer.atoms()) {
	        	int hCount = 0;
	            if (atom.getAtomicNumber() != 1) {
	            	IAtom atom2 = atom.getBuilder().newInstance(IAtom.class, atom.getSymbol());
	            	atom2.setProperties(atom.getProperties());
	            	atom2.setCharge(atom.getCharge());
	            	atom2.setFormalCharge(atom.getFormalCharge());
	            	atom2.setHybridization(atom.getHybridization());
	            	atom2.setID(atom.getID());
	            	atom2.setIsAromatic(atom.isAromatic());
	            	atom2.setIsInRing(atom.isInRing());
	            	atomMapping.put(atom, atom2);
	            	for (IAtom neighbor : atomContainer.getConnectedAtomsList(atom)) {
	            		if (neighbor.getSymbol().equals("H")) {
	            			hCount++;
	            		}
	            	}
	            	atom2.setImplicitHydrogenCount(hCount);
	            	atomContainer2.addAtom(atom2);
	            }
	            else {
	            	
	            }
	        }

	        for (IBond bond : atomContainer.bonds()) {
	        	if (!bond.getBegin().getSymbol().equals("H") && !bond.getEnd().getSymbol().equals("H")) {
	        		Bond b = new Bond(atomMapping.get(bond.getBegin()), atomMapping.get(bond.getEnd()), bond.getOrder());
	        		b.setIsAromatic(bond.isAromatic());
	        		b.setIsInRing(bond.isInRing());
	        		b.setProperties(bond.getProperties());
	        		b.setStereo(bond.getStereo());
	        		atomContainer2.addBond(b);
	        		bondMapping.put(bond, b);
	        	}
	        }
	       // System.out.println("atom container_2 : "+ atomContainer2.getBondCount());
	        

	        // update stereo elements with an implicit part
	        List<IStereoElement> stereos = new ArrayList<>();
	        for (IStereoElement stereo : atomContainer.stereoElements()) {
	            if (stereo instanceof ITetrahedralChirality) {
	                ITetrahedralChirality tc = (ITetrahedralChirality) stereo;
	                
	                IAtom   focus    = atomMapping.get(tc.getFocus());
	                IAtom[] carriers = new IAtom[4];
	                for (int i = 0; i < tc.getCarriers().size(); i++) {
	                	IAtom atom = tc.getCarriers().get(i);
	                	if(atom.getSymbol().equals("H"))
	                	{
	                		IAtom atom2 = atom.getBuilder().newInstance(IAtom.class, atom.getSymbol());
	    	            	atom2.setProperties(atom.getProperties());
	    	            	atom2.setCharge(atom.getCharge());
	    	            	atom2.setFormalCharge(atom.getFormalCharge());
	    	            	atom2.setHybridization(atom.getHybridization());
	    	            	atom2.setID(atom.getID());
	    	            	atom2.setIsAromatic(atom.isAromatic());
	    	            	atom2.setIsInRing(atom.isInRing());
	    	            	atomMapping.put(atom, atom2);
	    	            	for (IBond bond : atomContainer.getConnectedBondsList(atom)) {
	    	            		Bond b = new Bond(atomMapping.get(bond.getBegin()), atomMapping.get(bond.getEnd()), bond.getOrder());
	    		        		b.setIsAromatic(bond.isAromatic());
	    		        		b.setIsInRing(bond.isInRing());
	    		        		b.setProperties(bond.getProperties());
	    		        		b.setStereo(bond.getStereo());
	    		        		atomContainer2.addBond(b);
	    		        		bondMapping.put(bond, b);
	    	            	}
	                	}
	                	carriers[i] = atomMapping.get(atom);
	                }
	                TetrahedralChirality newStereo = new TetrahedralChirality(focus, carriers, tc.getStereo());
	                  stereos.add(newStereo);
	            } else if (stereo instanceof ExtendedTetrahedral) {
	                ExtendedTetrahedral tc = (ExtendedTetrahedral) stereo;

	                IAtom   focus    = atomMapping.get(tc.getFocus());
	                IAtom[] carriers = new IAtom[4];
	                for (int i = 0; i < tc.getCarriers().size(); i++) {
	                	IAtom atom = tc.getCarriers().get(i);
	                	if(atom.getSymbol().equals("H"))
	                	{
	                		IAtom atom2 = atom.getBuilder().newInstance(IAtom.class, atom.getSymbol());
	    	            	atom2.setProperties(atom.getProperties());
	    	            	atom2.setCharge(atom.getCharge());
	    	            	atom2.setFormalCharge(atom.getFormalCharge());
	    	            	atom2.setHybridization(atom.getHybridization());
	    	            	atom2.setID(atom.getID());
	    	            	atom2.setIsAromatic(atom.isAromatic());
	    	            	atom2.setIsInRing(atom.isInRing());
	    	            	atomMapping.put(atom, atom2);
	    	            	for (IBond bond : atomContainer.getConnectedBondsList(atom)) {
	    	            		Bond b = new Bond(atomMapping.get(bond.getBegin()), atomMapping.get(bond.getEnd()), bond.getOrder());
	    		        		b.setIsAromatic(bond.isAromatic());
	    		        		b.setIsInRing(bond.isInRing());
	    		        		b.setProperties(bond.getProperties());
	    		        		b.setStereo(bond.getStereo());
	    		        		atomContainer2.addBond(b);
	    		        		bondMapping.put(bond, b);
	    	            	}
	                	}
	                	carriers[i] = atomMapping.get(atom);
	                }

	               stereos.add(new ExtendedTetrahedral(focus, carriers, tc.getConfigOrder()));
	              
	            }
	        }
	        atomContainer2.setStereoElements(stereos);
	        return atomContainer2;
	    }
}
