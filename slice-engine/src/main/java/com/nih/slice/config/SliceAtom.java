package com.nih.slice.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.config.Elements;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IAtomType.Hybridization;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IBond.Order;
import org.openscience.cdk.isomorphism.Mappings;
import org.openscience.cdk.interfaces.IRingSet;
import org.openscience.cdk.interfaces.IStereoElement;
import org.openscience.cdk.ringsearch.AllRingsFinder;
import org.openscience.cdk.stereo.DoubleBondStereochemistry;
import org.openscience.cdk.stereo.ExtendedCisTrans;
import org.openscience.cdk.tools.periodictable.PeriodicTable;

import com.nih.slice.cdk.ExtractSubjectFromAtomContainer;
import com.nih.slice.cdk.SliceConstants;
import com.nih.slice.config.SliceGroup.groupProp;

public class SliceAtom {
	
	public static enum atomType {
		HYDROGEN, HELIUM, LITHIUM, BERYLLIUM, BORON, CARBON, NITROGEN, OXYGEN, FLUORINE, NEON, SODIUM, MAGNESIUM,
		ALUMINIUM, SILICON, PHOSPHORUS, SULFUR, CHLORINE, ARGON, POTASSIUM, CALCIUM, SCANDIUM, TITANIUM, VANADIUM,
		CHROMIUM, IRON, COBALT, NICKEL, COPPER, ZINC, GALLIUM, GERMANIUM, ARSENIC, SELENIUM, BROMINE, KRYPTON, RUBIDIUM,
		STRONTIUM, ZIRCONIUM, MOLYBDENUM, TECHNETIUM, RUTHENIUM, RHODIUM, PALLADIUM, SILVER, CADMIUM, INDIUM, TIN,
		ANTIMONY, TELLURIUM, IODINE, XENON, CESIUM, BARIUM, LANTHANUM, HAFNIUM, TANTALUM, TUNGSTEN, RHENIUM, OSMIUM,
		IRIDIUM, PLATINUM, GOLD, MERCURY, THALLIUM, LEAD, BISMUTH, POLONIUM, ASTATINE, RADON, FRANCIUM, RADIUM,
		ACTINOIDS, RUTHERFORDIUM, DUBNIUM, SEABORGIUM, BOHRIUM, HASSIUM, MEITNERIUM, DARMSTADIUM, ROENTGENIUM,
		COPERNICIUM, NIHONIUM, FLEROVIUM, MOSCOVIUM, LIVERMORIUM, TENNESSINE, OGANESSON, CERIUM, PRASEODYMIUM,
		NEODYMIUM, PROMETHIUM, SAMARIUM, EUROPIUM, GADOLINIUM, TERBIUM, DYSPROSIUM, HOLMIUM, ERBIUM, THULIUM, YTTERBIUM,
		LUTETIUM, THORIUM, PROTACTINIUM, URANIUM, NEPTUNIUM, PLUTONIUM, AMERICIUM, CURIUM, BERKELIUM, CALIFORNIUM,
		EINSTEINIUM, FERMIUM, MENDELEVIUM, NOBELIUM, LAWRENCIUM;
	}

	public static enum atomProp {
		ALKALI_METAL, ALKYL, ALLYLIC, ANTI, AROMATIC, BENZYLIC, BREDT_STRAINED, BRIDGEHEAD, CIS_OLEFIN, CIS, COMMON,
		CONJUGATED, DONATING,DOUBLY, ELECTRON_DENSITY, ENOLIZABLE, EQUIVALENT, FUSION, HALOGEN, HETERO, MULTIPLY, STRAINED, SYN,
		TRANS_OLEFIN, TRANS, TRIPLY, WITHDRAWING;
	}

	public Elements getCDKAtomType(atomType type) {
		switch (type) {
		case HYDROGEN:
			return Elements.Hydrogen;
		case HELIUM:
			return Elements.Helium;
		case LITHIUM:
			return Elements.Lithium;
		case BERYLLIUM:
			return Elements.Beryllium;
		case BORON:
			return Elements.Boron;
		case CARBON:
			return Elements.Carbon;
		case NITROGEN:
			return Elements.Nitrogen;
		case OXYGEN:
			return Elements.Oxygen;
		case FLUORINE:
			return Elements.Fluorine;
		case NEON:
			return Elements.Neon;
		case SODIUM:
			return Elements.Sodium;
		case MAGNESIUM:
			return Elements.Magnesium;
		case ALUMINIUM:
			return Elements.Aluminium;
		case SILICON:
			return Elements.Silicon;
		case PHOSPHORUS:
			return Elements.Phosphorus;
		case SULFUR:
			return Elements.Sulfur;
		case CHLORINE:
			return Elements.Chlorine;
		case ARGON:
			return Elements.Argon;
		case POTASSIUM:
			return Elements.Potassium;
		case CALCIUM:
			return Elements.Calcium;
		case SCANDIUM:
			return Elements.Scandium;
		case TITANIUM:
			return Elements.Titanium;
		case VANADIUM:
			return Elements.Vanadium;
		case CHROMIUM:
			return Elements.Chromium;
		case IRON:
			return Elements.Iron;
		case COBALT:
			return Elements.Cobalt;
		case NICKEL:
			return Elements.Nickel;
		case COPPER:
			return Elements.Copper;
		case ZINC:
			return Elements.Zinc;
		case GALLIUM:
			return Elements.Gallium;
		case GERMANIUM:
			return Elements.Germanium;
		case ARSENIC:
			return Elements.Arsenic;
		case SELENIUM:
			return Elements.Selenium;
		case BROMINE:
			return Elements.Bromine;
		case KRYPTON:
			return Elements.Krypton;
		case RUBIDIUM:
			return Elements.Rubidium;
		case STRONTIUM:
			return Elements.Strontium;
		case YTTERBIUM:
			return Elements.Ytterbium;
		case ZIRCONIUM:
			return Elements.Zirconium;
		case NOBELIUM:
			return Elements.Nobelium;
		case MOLYBDENUM:
			return Elements.Molybdenum;
		case TECHNETIUM:
			return Elements.Technetium;
		case RUTHENIUM:
			return Elements.Ruthenium;
		case RHODIUM:
			return Elements.Rhodium;
		case PALLADIUM:
			return Elements.Palladium;
		case SILVER:
			return Elements.Silver;
		case CADMIUM:
			return Elements.Cadmium;
		case INDIUM:
			return Elements.Indium;
		case TIN:
			return Elements.Tin;
		case ANTIMONY:
			return Elements.Antimony;
		case TELLURIUM:
			return Elements.Tellurium;
		case IODINE:
			return Elements.Iodine;
		case XENON:
			return Elements.Xenon;
		case CESIUM:
			return Elements.Cerium;
		case BARIUM:
			return Elements.Barium;
		case LANTHANUM:
			return Elements.Lanthanum;
		case HAFNIUM:
			return Elements.Hafnium;
		case TANTALUM:
			return Elements.Tantalum;
		case TUNGSTEN:
			return Elements.Tungsten;
		case RHENIUM:
			return Elements.Rhenium;
		case OSMIUM:
			return Elements.Osmium;
		case IRIDIUM:
			return Elements.Iridium;
		case PLATINUM:
			return Elements.Platinum;
		case GOLD:
			return Elements.Gold;
		case MERCURY:
			return Elements.Mercury;
		case THALLIUM:
			return Elements.Thallium;
		case LEAD:
			return Elements.Lead;
		case BISMUTH:
			return Elements.Bismuth;
		case POLONIUM:
			return Elements.Polonium;
		case ASTATINE:
			return Elements.Astatine;
		case RADON:
			return Elements.Radon;
		case FRANCIUM:
			return Elements.Francium;
		case RADIUM:
			return Elements.Radium;
		case ACTINOIDS:
		case RUTHERFORDIUM:
			return Elements.Rutherfordium;
		case DUBNIUM:
			return Elements.Dubnium;
		case SEABORGIUM:
			return Elements.Seaborgium;
		case BOHRIUM:
			return Elements.Bohrium;
		case HASSIUM:
			return Elements.Hassium;
		case MEITNERIUM:
			return Elements.Meitnerium;
		case DARMSTADIUM:
			return Elements.Darmstadtium;
		case ROENTGENIUM:
			return Elements.Roentgenium;
		case COPERNICIUM:
			return Elements.Copernicium;
		case NIHONIUM:
			return Elements.Nihonium;
		case FLEROVIUM:
			return Elements.Flerovium;
		case MOSCOVIUM:
			return Elements.Moscovium;
		case LIVERMORIUM:
			return Elements.Livermorium;
		case TENNESSINE:
			return Elements.Tennessine;
		case OGANESSON:
			return Elements.Oganesson;
		case CERIUM:
			return Elements.Cerium;
		case PRASEODYMIUM:
			return Elements.Praseodymium;
		case NEODYMIUM:
			return Elements.Neodymium;
		case PROMETHIUM:
			return Elements.Promethium;
		case SAMARIUM:
			return Elements.Samarium;
		case EUROPIUM:
			return Elements.Europium;
		case GADOLINIUM:
			return Elements.Gadolinium;
		case TERBIUM:
			return Elements.Terbium;
		case DYSPROSIUM:
			return Elements.Dysprosium;
		case HOLMIUM:
			return Elements.Holmium;
		case ERBIUM:
			return Elements.Erbium;
		case THULIUM:
			return Elements.Thulium;
		case LUTETIUM:
			return Elements.Lutetium;
		case THORIUM:
			return Elements.Thorium;
		case PROTACTINIUM:
			return Elements.Protactinium;
		case URANIUM:
			return Elements.Uranium;
		case NEPTUNIUM:
			return Elements.Neptunium;
		case PLUTONIUM:
			return Elements.Plutonium;
		case AMERICIUM:
			return Elements.Americium;
		case CURIUM:
			return Elements.Curium;
		case BERKELIUM:
			return Elements.Berkelium;
		case CALIFORNIUM:
			return Elements.Californium;
		case EINSTEINIUM:
			return Elements.Einsteinium;
		case FERMIUM:
			return Elements.Fermium;
		case MENDELEVIUM:
			return Elements.Mendelevium;
		case LAWRENCIUM:
			return Elements.Lawrencium;
		default:
			return Elements.Unknown;
		}
	}
	//conversion atomProp ap = atomProp.valueOf(myString)
	/**
	 * global function to return value of all methods called with the IS relation
	 * @param prop
	 * @param atom1
	 * @param atom2
	 * @param bond
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public Boolean isProp(atomProp prop, IAtom atom1, IAtom atom2, IBond bond, IAtomContainer ac, int location) throws CDKException {
		switch (prop) {
		case ALKALI_METAL:
			return isAlkaliMetal(atom1);
		case ALKYL:
			return isAlkyl(atom1, ac);
		case ALLYLIC:
			return isAllylic(atom1, ac);
		case ANTI:
			if (atom2 != null)
				return isAnti(atom1, atom2, ac);
			else if (bond != null)
				return isAnti(atom1, bond, ac);
		case AROMATIC:
			return isAromatic(atom1);
		case BENZYLIC:
			return isBenzylic(atom1, ac);
		case BREDT_STRAINED:
			return isBredtStrained(atom1, ac);
		case BRIDGEHEAD:
			return isBridgehead(atom1, ac);
		case CIS_OLEFIN:
			return isCisOlefin(atom1, ac);
		case COMMON:
			if (atom2 != null)
				return isCommon(atom1, atom2, ac);
			else if (bond != null)
				return isCommon(atom1, bond, ac);
		case CIS:
			return isCis(atom1, atom2, ac);
		case CONJUGATED:
			return isConjugated(atom1, ac);
		case DOUBLY:
			return isDoubly(atom1, ac);
		case ENOLIZABLE:
			return isEnolizable(atom1, ac, location);
		case EQUIVALENT:
			return isEquivalent(atom1, atom2,ac);
		case FUSION:
			return isFused(atom1, ac);
		case HALOGEN:
			return isHalogen(atom1);
		case HETERO:
			return isHetero(atom1);
		case MULTIPLY:
			return isMultiply(atom1, ac);
		case STRAINED:
			return isStrained(atom1, ac);
		case SYN:
			if (atom2 != null)
				return isSyn(atom1, atom2, ac);
			else if (bond != null)
				return isSyn(atom1, bond, ac);
		case TRANS_OLEFIN:
			return isTransOlefin(atom1, ac);
		case TRANS:
			return isTrans(atom1, atom2, ac);
		case TRIPLY:
			return isTriply(atom1, ac);
		default:
			return null;
		}
	}
	
	public boolean hasProp(atomProp prop, IAtom atom, IAtomContainer ac, int location) {
		switch (prop) {
		case DONATING:
			return hasDonatingBond(atom, ac, location);
		case WITHDRAWING:
			return hasWithdrawingBond(atom, ac, location);
		default:
			break;
		}
		return false;
	}
	
	public int numberOfProp(atomProp prop, IAtom atom, IAtomContainer ac, int location) {
		switch (prop) {
		case DONATING:
			return numberOfDonatingBonds(atom, ac, location);
		case WITHDRAWING:
			return numberOfWithdrawingBonds(atom, ac, location);
		default:
			break;
		}
		return 0;
	}
	
	public int atomTypeCount(IAtom atom, String symbol, IAtomContainer ac) {
		int cpt = 0;
		for (IAtom a2 : ac.getConnectedAtomsList(atom)) {
			if (a2.getSymbol().equals(symbol))
				cpt += 1;
		}
		return cpt;
	}
	
	public int atomTypeCount(IAtom atom, String symbol, IAtomContainer ac, int location) {
		int cpt = 0;
		
		for (IAtom a2 : ac.getConnectedAtomsList(atom)) {
			if (a2.getSymbol().equals(symbol)) {
				if (SliceConstants.ANYWHERE == location || (boolean)a2.getProperty(location) == true) {
					cpt += 1;
				}
			}
				
		}
		return cpt;
	}
	
	public int atomTypeCount(String symbol, IAtomContainer ac) {
		int cpt = 0;
		for (IAtom a : ac.atoms()) {
			if (a.getSymbol().equals(symbol))
				cpt += 1;
		}
		return cpt;
	}
	
	public int atomTypeCount(String symbol, IAtomContainer ac, int location) {
		int cpt = 0;
		for (IAtom a : ac.atoms()) {
			if (a.getSymbol().equals(symbol)) {
				if (SliceConstants.ANYWHERE == location || (boolean)a.getProperty(location) == true)
					cpt += 1;
			}
		}
		return cpt;
	}
	
	public int atomTypeCount(List<IAtom> atoms, String symbol) {
		int cpt = 0;
		for (IAtom atom : atoms) {
			if (atom.getSymbol().equals(symbol))
				cpt += 1;
		}
		return cpt;
	}
	
	public boolean isAlkaliMetal(IAtom atom) {
		if (atom.getSymbol().equals("Li"))
			return true;
		else if (atom.getSymbol().equals("Na"))
			return true;
		else if (atom.getSymbol().equals("K"))
			return true;
		else if (atom.getSymbol().equals("Rb"))
			return true;
		else if (atom.getSymbol().equals("Cs"))
			return true;
		else
			return false;
	}
	
	public boolean hasAlkaliMetal(IAtom atom, IAtomContainer ac) {
		if (numberOfAlkaliMetal(atom, ac) > 0)
			return true;
		else
			return false;
	}
	
	public int numberOfAlkaliMetal(IAtom atom, IAtomContainer ac) {
		int number = 0;
		List<IAtom> visited = new ArrayList<IAtom>();
		visited.add(atom);
		for (IAtom con : ac.getConnectedAtomsList(atom)) {
			if (!visited.contains(con) && isAlkaliMetal(con)) {
				visited.add(con);
				number++;
			}
		}
		return number;
	}
	
	/**
	 * Alkyl = An sp3 hybridized carbon atom with no bonds to heteroatoms.
	 * @param atom
	 * @param ac
	 * @return
	 */
	public boolean isAlkyl(IAtom atom, IAtomContainer ac) {
		if (atom.getHybridization().equals(Hybridization.SP3)) {
			for (IAtom con : ac.getConnectedAtomsList(atom)) {
				if (!con.getSymbol().equals("C") || !con.getSymbol().equals("H"))
					return false;
				else if (!con.getSymbol().equals("C") || !con.getSymbol().equals("H"))
					return false;
			}
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Alkyl = An sp3 hybridized carbon atom with no bonds to heteroatoms.
	 * @param atom
	 * @param ac
	 * @return
	 */
	public boolean hasAlkyl(IAtom atom, IAtomContainer ac) {
		if (numberOfAlkyl(atom, ac) > 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Number of sp3 hybridized carbon atom with no bonds to heteroatoms.
	 * @param atom
	 * @param ac
	 * @return
	 */
	public int numberOfAlkyl(IAtom atom, IAtomContainer ac) {
		int number = 0;
		List<IAtom> visited = new ArrayList<IAtom>();
		visited.add(atom);
		for (IAtom con : ac.getConnectedAtomsList(atom)) {
			if (!visited.contains(con) && isAlkyl(con, ac)) {
				visited.add(con);
				number++;
			}
		}
		return number;
	}
	
	/**
	 * Number of sp3 hybridized carbon atom with no bonds to heteroatoms.
	 * @param atom
	 * @param ac
	 * @param location
	 * @return
	 */
	public int numberOfAlkyl(IAtom atom, IAtomContainer ac, int location) {
		int number = 0;
		List<IAtom> visited = new ArrayList<IAtom>();
		visited.add(atom);
		for (IAtom con : ac.getConnectedAtomsList(atom)) {
			if (!visited.contains(con) && isAlkyl(con, ac)) {
				if (SliceConstants.ANYWHERE == location || (boolean)con.getProperty(location) == true) {
					visited.add(con);
					number++;
				}
			}
		}
		return number;
	}
	
	
	/**
	 * Number of hetero atom to ac
	 * @param atom
	 * @param ac
	 * @param location
	 * @return
	 */
	public int numberOfHetero(IAtom atom, IAtomContainer ac, int location) {
		int number = 0;
		List<IAtom> visited = new ArrayList<IAtom>();
		visited.add(atom);
		for (IAtom con : ac.getConnectedAtomsList(atom)) {
			if (!visited.contains(con) && isHetero(con)) {
				if (SliceConstants.ANYWHERE == location || (boolean)con.getProperty(location) == true) {
					visited.add(con);
					number++;
				}
			}
		}
		return number;
	}
	
	/**
	 * The atom is a saturated carbon (4 single bonds) atom alpha to a carbon-carbon double bond
	 * H      H
	 *   \   /
	 *    C=C
	 *   /   \
	 *  H     CH3 <-Allylic carbon
	 *  			
	 * @param atom
	 * @param ac
	 * @return
	 */
	public boolean isAllylic(IAtom atom, IAtomContainer ac) {
		for (IBond bond : ac.getConnectedBondsList(atom)) {
			if(!bond.getOrder().equals(Order.SINGLE))
			{
				return false;
			}
		}
		for (IAtom con1 : ac.getConnectedAtomsList(atom)) {
			if (!con1.equals(atom)) {
				for (IBond bond : ac.getConnectedBondsList(con1)) {
					if (bond.getOrder().equals(Order.DOUBLE) && bond.getOther(con1).getSymbol().equals("C"))
						return true;
				}
			}
		}
		return false;
	}
	

	/**
	 * @param atom
	 * @param bond
	 * @param ac
	 * @return
	 */
	public Boolean isAnti(IAtom atom, IBond bond, IAtomContainer ac) {
		for (IBond bond2 : ac.getConnectedBondsList(atom)) {
			if (!bond2.equals(bond)) {
				if (bond.getStereo().equals(bond2.getStereo()))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * @param atom1
	 * @param atom2
	 * @param ac
	 * @return
	 */
	public Boolean isAnti(IAtom atom1, IAtom atom2, IAtomContainer ac) {
		for (IBond bond : ac.getConnectedBondsList(atom1)) {
			boolean anti = isAnti(atom2, bond, ac);
			if (anti)
				return true;
		}
		return false;
	}
	
	/**
	 * @param bond1
	 * @param bond2
	 * @return
	 */
	public Boolean isAnti(IBond bond1, IBond bond2) {
		if (bond1.getStereo().equals(bond2.getStereo()))
			return true;
		return false;
	}
	
	public boolean isAromatic(IAtom atom) {
		return atom.isAromatic();
	}
	
	/**
	 * The atom is a saturated carbon atom that is alpha to an aromatic carbon atom.
	 *  
	 *             *   CX3 <- benzylic carbon (sp3)
	 *           // \ /      
	 *          *    *       
	 *          |    "   
	 *          *    *       
	 *           \\ /         
	 *             *           
	 *                                                                                        
	 * @param atom
	 * @param ac
	 * @return
	 */
	public boolean isBenzylic(IAtom atom, IAtomContainer ac) {
		if (atom.getHybridization().equals(Hybridization.SP3) && atom.getSymbol().equals("C")) {
			for (IAtom a : ac.getConnectedAtomsList(atom)) {
				if (a.isAromatic() && a.getSymbol().equals("C"))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * @param atom
	 * @param ac
	 * @return
	 */
	public boolean hasBenzylic(IAtom atom, IAtomContainer ac) {
		if (atom.isAromatic() && atom.getSymbol().equals("C")) {
			for (IAtom a : ac.getConnectedAtomsList(atom)) {
				if (!a.isAromatic() && a.getSymbol().equals("C") && a.getHybridization().equals(Hybridization.SP3) )
					return true;
			}
		}
		return false;
	}
	
	/**
	 * The atom is on a double bond which is at a bridgehead and in a ring of size less than eight.
	 * 	 *  
	 *             *   *
	 *           // \ /  \    
	 *          *    X    *   X is a bredt strained atom
	 *          |    "    |
	 *          *    X    *   
	 *           \\ / \  /     
	 *             *    * 
	 * @param atom
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public boolean isBredtStrained(IAtom atom, IAtomContainer ac) throws CDKException {
		if (atom.isInRing()) {
			AllRingsFinder arf = new AllRingsFinder();
			IRingSet allRS = arf.findAllRings(ac);
			IRingSet rs = allRS.getRings(atom);
			for (IBond bond : ac.getConnectedBondsList(atom)) {
				if (bond.getOrder().equals(Order.DOUBLE)) {
					IRingSet bridgeRings = rs.getRings(bond);
					if (bridgeRings.getAtomContainerCount() > 1) {
						for (IAtomContainer ac2 : bridgeRings.atomContainers()) {
							if (ac2.getAtomCount() < 8)
								return true;
						} 
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * The atom is common to at least 2 rings.
	 * 	  
	 *             *   *
	 *           // \ /  \    
	 *          *    X    *   X is a bridgehead atom
	 *          |    |    |
	 *          *    X    *   
	 *           \\ / \  /     
	 *             *    * 
	 * @param atom
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public boolean isBridgehead(IAtom atom, IAtomContainer ac) throws CDKException {
		if (atom.isInRing()) {
			AllRingsFinder arf = new AllRingsFinder();
			IRingSet allRS = arf.findAllRings(ac);
			IRingSet rs = allRS.getRings(atom);
			if (rs.getAtomContainerCount() > 1)
				return true;
		}
		return false;
	}
	
	/**
	 * Tests whether two atoms on the same ring are cis to each other.
	 * NB:
	 * @param atom1
	 * @param atom2
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public boolean isCis(IAtom atom1, IAtom atom2, IAtomContainer ac) throws CDKException {
		int config1 = 0, config2 = 0;
		for (IStereoElement se : ac.stereoElements()) {
			// both carbon are in the same stereoelement
			if (se.contains(atom1) && se.contains(atom2)) {
				if (se instanceof DoubleBondStereochemistry) {
					if (se.getConfig() == IStereoElement.TOGETHER)
						return true;
					else if (se.getConfig() == IStereoElement.OPPOSITE)
						return false;
				}
				else if (se instanceof ExtendedCisTrans) {
					if (se.getConfig() == IStereoElement.TOGETHER)
						return true;
					else if (se.getConfig() == IStereoElement.OPPOSITE)
						return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * A carbon atom joined to another carbon atom by a double bond where both carbon atoms carry one (and only one) 
	 * hydrogen atom and the substituents are in a cis relationship to each other.
	 * @param atom1
	 * @param atom2
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public boolean isCisOlefin(IAtom atom, IAtomContainer ac) throws CDKException {
		//explicit hydrogen case
		int hydrogenCount1 = getExplicitHydrogenCount(atom, ac);

		if (atom.getImplicitHydrogenCount() + hydrogenCount1 == 1) {
			for (IBond bond : ac.getConnectedBondsList(atom)) {
				if (bond.getOrder() == Order.DOUBLE) {
					//explicit hydrogen
					int hydrogenCount2 = 0;
					if (hydrogenCount1 > 0)
						hydrogenCount2 = getExplicitHydrogenCount(atom, ac);
					if (bond.getOther(atom).getImplicitHydrogenCount() + hydrogenCount2 == 1) {
						for (IStereoElement se : ac.stereoElements()) {
							// both carbon are in the same stereoelement
							if (se.contains(atom) && se.contains(bond.getOther(atom))) {
								if (se instanceof DoubleBondStereochemistry) {
									if (se.getConfig() == IStereoElement.TOGETHER)
										return true;
									else if (se.getConfig() == IStereoElement.OPPOSITE)
										return false;
								}
								else if (se instanceof ExtendedCisTrans) {
									if (se.getConfig() == IStereoElement.TOGETHER)
										return true;
									else if (se.getConfig() == IStereoElement.OPPOSITE)
										return false;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Tests whether atoms and/or bonds are in the same ring
	 * @param atom1
	 * @param atom2
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public boolean isCommon(IAtom atom1, IAtom atom2, IAtomContainer ac) throws CDKException {
		if (atom1.isInRing()) {
			AllRingsFinder arf = new AllRingsFinder();
			IRingSet allRS = arf.findAllRings(ac);
			IRingSet rs = allRS.getRings(atom1);
			if (rs.contains(atom2))
				return true;
			else
				return false;
		}
		return false;
	}
	
	/**
	 * Tests whether atoms and/or bonds are in the same ring
	 * @param atom
	 * @param bond
	 * @param set
	 * @return
	 * @throws CDKException
	 */
	public boolean isCommon(IAtom atom1, IAtom atom2, IRingSet set) throws CDKException {
		if (atom1.isInRing() && atom2.isInRing()) {
			if (set.contains(atom1) && set.contains(atom2))
				return true;
			else
				return false;
		}
		return false;
	}
	
	/**
	 * Tests whether atoms and/or bonds are in the same ring
	 * @param atom
	 * @param bond
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public boolean isCommon(IAtom atom, IBond bond, IAtomContainer ac) throws CDKException {
		if (atom.isInRing()) {
			AllRingsFinder arf = new AllRingsFinder();
			IRingSet allRS = arf.findAllRings(ac);
			IRingSet rs = allRS.getRings(bond);
			if (rs.contains(atom))
				return true;
			else
				return false;
		}
		return false;
	}
	
	/**
	 * Tests whether atoms and/or bonds are in the same ring
	 * @param atom
	 * @param bond
	 * @param set
	 * @return
	 * @throws CDKException
	 */
	public boolean isCommon(IAtom atom, IBond bond, IRingSet set) throws CDKException {
		if (atom.isInRing() && bond.isInRing()) {
			if (set.contains(atom) && set.contains(bond.getBegin()) && set.contains(bond.getEnd()))
				return true;
			else
				return false;
		}
		return false;
	}
	
	/**
	 * An atom that is alpha to a multiply-bonded atom which is connected to, but not part of, a structural group that keys the transform.
	 * @param atom
	 * @param ac
	 * @return
	 */
	public boolean isConjugated(IAtom atom, IAtomContainer ac) {
		for (IAtom a2 : ac.getConnectedAtomsList(atom)) {
			boolean isMultiplyBonded = false;
			for (IBond bond : ac.getConnectedBondsList(a2)) {
				if (bond.isAromatic() || (bond.getOrder() != Order.SINGLE && bond.getOrder() != Order.UNSET)) {
					for (IAtom a3 : ac.getConnectedAtomsList(a2)) {
						if (!a3.equals(atom)) {
							if ((boolean) a3.getProperty(SliceConstants.ONPATH) == false) {
								if (!a3.getSymbol().equals("H"))
									return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * @param atom
	 * @param set
	 * @return
	 * @throws CDKException
	 */
	public boolean isInCurrentRing(IAtom atom, IRingSet set) throws CDKException {
		if (atom.isInRing()) {
			if (set.contains(atom))
				return true;
			else
				return false;
		}
		return false;
	}
	
	/**
	 * has a bond activated/deactivated by a DONATING group
	 * @param atom
	 * @param ac
	 * @return
	 */
	public boolean hasDonatingBond(IAtom atom, IAtomContainer ac, int location) {
		if (numberOfDonatingBonds(atom, ac, location) > 0)
			return true;
		else
			return false;
	}
	
	/**
	 * number of bonds activated/deactivated by a DONATING group
	 * @param atom
	 * @param ac
	 * @param location 
	 * @return
	 */
	public int numberOfDonatingBonds(IAtom atom, IAtomContainer ac, int location) {
		int number = 0;
		SliceGroup cg = new SliceGroup();
		List<Map<IAtom, IAtom>> mappings = cg.getDonating(atom, ac, location);
		return mappings.size();
	}

	
	/**
	 * There is a double bond on the atom
	 * @param atom
	 * @param ac
	 * @return
	 */
	public boolean isDoubly(IAtom atom, IAtomContainer ac) {
		for (IBond bond : ac.getConnectedBondsList(atom)){
			if (bond.isAromatic() || bond.getOrder() == Order.DOUBLE)
				return true;
		}
		return false;
	}
	
	public Double getEletronDensity(IAtom atom) {
		Elements[] elt = new Elements[PeriodicTable.getAtomicNumber(atom.getSymbol())];
		if (elt[0].electronegativity() != null)
			return elt[0].electronegativity();
		return 0.0;
	}
	
	/**
	 * The atom is saturated, not a bridgehead atom, has at least one hydrogen atom on it, and is activated by a withdrawing bond.
	 * @param atom
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public boolean isEnolizable(IAtom atom, IAtomContainer ac, int location) throws CDKException {
		if (isBridgehead(atom,ac))
			return false;
		if (atom.getImplicitHydrogenCount() > 0 || getExplicitHydrogenCount(atom, ac) > 0) {
			SliceGroup cg = new SliceGroup();
			List<Map<IAtom, IAtom>> mappings = cg.getWithDrawing(atom, ac, location);
			if (mappings.size() > 0)
				return true;
			/*
			//case non expandable (take origin)
			SliceGroup cg = new SliceGroup();
			List<Map<IAtom, IAtom>> mappings = cg.getNonExpandableWithDrawing(atom, ac, location);
			if (mappings.size() > 0)
				return true;
			
			//case expandable (take alpha(s) to origin)
			mappings = cg.getExpandableWithDrawing(atom, ac, location);
			if (mappings.size() > 0)
				return true;*/
		}
		return false;
	}
	
	/**
	 * The atom is saturated, not a bridgehead atom, has at least one hydrogen atom on it, and is activated by a withdrawing bond.
	 * @param atom
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public List<IAtom> getEnolizableAtom(IAtom atom, IAtomContainer ac, int location) throws CDKException {
		List<IAtom> enolizable = new ArrayList<IAtom>();
		if (isBridgehead(atom,ac))
			return enolizable;
		if (atom.getImplicitHydrogenCount() > 0 || getExplicitHydrogenCount(atom, ac) > 0) {
			
			SliceGroup cg = new SliceGroup();
			List<Map<IAtom, IAtom>> mappings = cg.getWithDrawing(atom, ac, location);
			for (Map<IAtom, IAtom> map : mappings) {
				if (map.containsValue(atom))
					enolizable.add(atom);
			}

		}
		return enolizable;
	}
	
	/**
	 * Tests whether two atoms belong to the same symmetry class
	 * @param atom1
	 * @param atom2
	 * @return
	 */
	public boolean isEquivalent(IAtom atom1, IAtom atom2,IAtomContainer ac)  {
		
		boolean response = true;
		Set<IAtom> visited_atom1= new HashSet<>();
		Set<IAtom> visited_atom2= new HashSet<>();
		Stack<IAtom> stack_atom1= new Stack<>();
		Stack<IAtom> stack_atom2= new Stack<>();
		
		stack_atom1.push(atom1);
		stack_atom2.push(atom2);
		while(!stack_atom1.isEmpty() && !stack_atom2.isEmpty()) {
			IAtom current1 = stack_atom1.pop();
			IAtom current2 = stack_atom2.pop();
			
			visited_atom1.add(current1);
			visited_atom2.add(current2);
			Set<IAtom> neighbors1 = getNeighbors(ac,current1,visited_atom1);
			Set<IAtom> neighbors2 = getNeighbors(ac,current2,visited_atom2);
			if(!neighbors1.equals(neighbors2)) {
				response = false;
				break;
			}
			stack_atom1.addAll(neighbors1);
			stack_atom2.addAll(neighbors2);
			
		}
		return response;
		
		/*if(ac.getConnectedBondsList(atom1).size()== ac.getConnectedBondsList(atom2).size() ) {
			if (atom1.getSymbol().equals(atom2.getSymbol()) && atom1.getFormalCharge().equals(atom2.getFormalCharge()))
					return true;
				else
					return false;
		}
		else {
			return false;
		}*/
	}
	
	
	private Set<IAtom> getNeighbors(IAtomContainer ac, IAtom atom, Set<IAtom> visited) {
		// TODO Auto-generated method stub
		Set<IAtom> neighbors= new HashSet<>();
		for(IBond bond : ac.getConnectedBondsList(atom)) {
			IAtom neighbor= bond.getOther(atom);
			if(!visited.contains(neighbor)) {
				neighbors.add(neighbor);
			}
		}
		return neighbors;
	}
	/**
	 * The atom is at a ring fusion
	 * @param atom
	 * @param ac
	 * @return
	 * @throws CDKException 
	 */
	public Boolean isFused(IAtom atom, IAtomContainer ac) throws CDKException {
		if (atom.isInRing()) {
			AllRingsFinder arf = new AllRingsFinder();
			IRingSet allRS = arf.findAllRings(ac);
			IRingSet rs = allRS.getRings(atom);
			if (rs.getAtomContainerCount() > 1) 
				return true;
			else {
				for (IAtom a2 : rs.getAtomContainer(0).atoms()) {
					IRingSet rs2 = allRS.getRings(a2);
					if (rs2.getAtomContainerCount() > 1) 
						return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * The atom(s) is/are F, Cl, Br, or I.
	 * @param atom
	 * @return
	 */
	public boolean isHalogen(IAtom atom) {
		if (atom.getSymbol().equals("F") || atom.getSymbol().equals("Cl") || atom.getSymbol().equals("Br") 
				|| atom.getSymbol().equals("I"))
			return true;
		else
			return false;
	}
	
	/**
	 * The atom is an element other than carbon or hydrogen
	 * @param atom
	 * @return
	 */
	public boolean isHetero(IAtom atom) {
		if (!atom.getSymbol().equals("C") && !atom.getSymbol().equals("H"))
			return true;
		else
			return false;
	}
	
	/**
	 * The number of hydrogen atoms (explicit and implicit) attached to the atom (the
	 * @param atom
	 * @param ac
	 * @return
	 */
	public int getTotalHydrogenCount(IAtom atom, IAtomContainer ac) {
		return atom.getImplicitHydrogenCount() + getExplicitHydrogenCount(atom, ac);
	}
	
	/**
	 * Compares the steric hindrance at two atoms in terms of the number of non-hydrogen atoms attached to each
	 * @param atom1
	 * @param atom2
	 * @param ac
	 * @return
	 */
	public boolean isLessHindred(IAtom atom1, IAtom atom2, IAtomContainer ac) {
		return getTotalHydrogenCount(atom1, ac) < getTotalHydrogenCount(atom2, ac);
	}
	
	/**
	 * The number of methyl group (i.e. CH 3 groups) attached to the atom
	 * @param atom
	 * @param ac
	 * @return
	 */
	public int getTotalMetyl(IAtom atom, IAtomContainer ac) {
		int methyl = 0;
		for (IAtom a : ac.getConnectedAtomsList(atom)) {
			if (a.getSymbol().equals("C")) {
				if (getTotalHydrogenCount(a, ac) == 3) {
					methyl++;
				}
			}
		}
		return methyl;
	}
	
	/**
	 * A bond on the atom is multiple (non single), or aromatic.
	 * @param atom
	 * @param ac
	 * @return
	 */
	public Boolean isMultiply(IAtom atom, IAtomContainer ac) {
		for (IBond bond : ac.getConnectedBondsList(atom)) {
			if (bond.isAromatic() || (!bond.getOrder().equals(Order.SINGLE) && !bond.getOrder().equals(Order.UNSET)))
					return true;
		}
		return false;
	}
	
	/**
	 * The atom is included in the keying pattern for the transform 
	 * @param atom
	 * @param ring
	 * @return
	 */
	public boolean isOnPath(IAtom atom, IAtomContainer ring) {
		if ((boolean) atom.getProperty(SliceConstants.ONPATH) == true) 
			return true;
		else
			return false;
	}
	
	/**
	 * The atom is not included in the keying pattern for the transform 
	 * @param atom
	 * @param ring
	 * @return
	 */
	public boolean isOffPath(IAtom atom, IAtomContainer ring) {
		return !isOnPath(atom, ring);
	}
	
	/**
	 * The atom is in the ring that has been designated as the current ring.
	 * @param atom
	 * @param ring
	 * @return
	 */
	public boolean isOnRing(IAtom atom, IAtomContainer ring) {
		return ring.contains(atom);
	}
	
	/**
	 * The atom is not in the ring that has been designated as the current ring.
	 * @param atom
	 * @param ring
	 * @return
	 */
	public boolean isOffRing(IAtom atom, IAtomContainer ring) {
		return !isOnRing(atom, ring);
	}
	
	/**
	 * The atom is in the selected bridged rings
	 * @param atom
	 * @param bridgeRings (contains all bridged Rings)
	 * @return
	 */
	public boolean isOnBridge(IAtom atom, IAtomContainer bridgedRings) {
		return bridgedRings.contains(atom);
	}
	
	/**
	 * The atom is not in the selected bridged rings
	 * @param atom
	 * @param bridgeRings (contains all bridged Rings)
	 * @return
	 */
	public boolean isOffBridge(IAtom atom, IAtomContainer ring) {
		return !isOnBridge(atom, ring);
	}
	
	/**
	 * Compares the steric hindrance at two atoms in terms of the number of non-hydrogen atoms attached to each
	 * @param atom1
	 * @param atom2
	 * @param ac
	 * @return
	 */
	public boolean isMoreHindred(IAtom atom1, IAtom atom2, IAtomContainer ac) {
		return getTotalHydrogenCount(atom1, ac) > getTotalHydrogenCount(atom2, ac);
	}
	
	/**
	 * @param atom
	 * @return
	 */
	public boolean hasNegativeCharge(IAtom atom) {
		return (atom.getFormalCharge() < 0) ? true : false; 
	}
	
	/**
	 * @param atom
	 * @return
	 */
	public int numberOfNegativeCharge(IAtom atom) {
		if (hasNegativeCharge(atom))
			return atom.getFormalCharge(); 
		else 
			return 0; 
	}
	
	/**
	 * @param atom
	 * @param location
	 * @return
	 */
	public int numberOfNegativeCharge(IAtom atom, int location) {
		if (hasNegativeCharge(atom) ) {
			if (SliceConstants.ANYWHERE == location || (boolean)atom.getProperty(location) == true) {
				return atom.getFormalCharge(); 
			}
			else
				return -1;
		}
		else 
			return 0;
	}
	
	/**
	 * @param atom
	 * @return
	 */
	public boolean hasPositiveCharge(IAtom atom) {
		return (atom.getFormalCharge() > 0) ? true : false; 
	}
	
	/**
	 * @param atom
	 * @return
	 */
	public int numberOfPositiveCharge(IAtom atom) {
		if (hasPositiveCharge(atom))
			return atom.getFormalCharge(); 
		else 
			return 0;
	}
	
	/**
	 * @param atom
	 * @param location
	 * @return
	 */
	public int numberOfPositiveCharge(IAtom atom, int location) {
		if (hasPositiveCharge(atom)) {
			if (SliceConstants.ANYWHERE == location || (boolean)atom.getProperty(location) == true) {
				return atom.getFormalCharge(); 
			}
			else
				return -1;
		}
		else 
			return 0;
	}
	
	/**
	 * There is one Center no matter the type
	 * @param atom
	 * @param ac
	 * @param type
	 * @return
	 */
	public Boolean isCenter(IAtom atom, IAtomContainer ac, String type) {
		switch(type) {
		case "PRIMARY_CENTER":
			return isPrimaryCenter(atom, ac);
		case "SECONDARY_CENTER":
			return isSecondaryCenter(atom, ac);
		case "TERTIARY_CENTER":
			return isTertiaryCenter(atom, ac);
		case "QUATERNARY_CENTER":
			return isQuaternaryCenter(atom, ac);
		}
		return null;
	}
	//TODO
	/*public Boolean getCenter(IAtom atom, IAtomContainer ac, String type) {
		switch(type) {
		case "PRIMARY_CENTER":
			return isPrimaryCenter(atom, ac);
		case "SECONDARY_CENTER":
			return isSecondaryCenter(atom, ac);
		case "TERTIARY_CENTER":
			return isTertiaryCenter(atom, ac);
		case "QUATERNARY_CENTER":
			return isQuaternaryCenter(atom, ac);
		}
		return null;
	}*/
	
	/**
	 * There is one (and only one) carbon atom attached to the atom.
	 * @param atom
	 * @param ac
	 * @return
	 */
	public Boolean isPrimaryCenter(IAtom atom, IAtomContainer ac) {
		int carbonCount = 0;
		for (IAtom a : ac.getConnectedAtomsList(atom)) {
			if (a.getSymbol().equals("C")) 
				carbonCount++;
		}
		return carbonCount == 1 ? true : false;
	}
	
	/**
	 * There is two (and only two) carbon atoms attached to the atom.
	 * @param atom
	 * @param ac
	 * @return
	 */
	public Boolean isSecondaryCenter(IAtom atom, IAtomContainer ac) {
		int carbonCount = 0;
		for (IAtom a : ac.getConnectedAtomsList(atom)) {
			if (a.getSymbol().equals("C")) 
				carbonCount++;
		}
		return carbonCount == 2 ? true : false;
	}
	
	/**
	 * There is three (and only three) carbon atoms attached to the atom.
	 * @param atom
	 * @param ac
	 * @return
	 */
	public Boolean isTertiaryCenter(IAtom atom, IAtomContainer ac) {
		int carbonCount = 0;
		for (IAtom a : ac.getConnectedAtomsList(atom)) {
			if (a.getSymbol().equals("C")) 
				carbonCount++;
		}
		return carbonCount == 3 ? true : false;
	}
	
	/**
	 * There is four (and only four) carbon atoms attached to the atom.
	 * @param atom
	 * @param ac
	 * @return
	 */
	public Boolean isQuaternaryCenter(IAtom atom, IAtomContainer ac) {
		int carbonCount = 0;
		for (IAtom a : ac.getConnectedAtomsList(atom)) {
			if (a.getSymbol().equals("C")) 
				carbonCount++;
		}
		return carbonCount == 4 ? true : false;
	}
	
	/**
	 * A bond is considered strained if it is:
	 * in a three- or four-membered ring;
	 * or in a five-membered ring that is part of a [2.1.1], [2.2.1], or [3.2.1] system; (The notation [x.y.z]propellane means the member of the family whose rings have x, y, and z carbons, not counting the two bridgeheads; or x + 2, y + 2, and z + 2 carbons, counting them SOURCE: wikipedia)
	 * or a trans double bond in a ring of size 9 or less;
	 * or a double bond exocyclic to a three- or four-membered ring.
	 * @param atom
	 * @param ac
	 * @return
	 * @throws CDKException 
	 */
	public boolean isStrained(IAtom atom, IAtomContainer ac) throws CDKException {
		if (atom.isInRing()) {
			AllRingsFinder arf = new AllRingsFinder();
			IRingSet allRS = arf.findAllRings(ac);
			for (IBond bond : ac.getConnectedBondsList(atom)) {
				if (bond.isInRing()) {
					IRingSet rs = allRS.getRings(bond);
					for (IAtomContainer ac2 : rs.atomContainers()) {
						int atomCount = ac2.getAtomCount();
						if (atomCount == 3 || atomCount == 4) 
							return true;
						else if (atomCount == 5) {
							//TODO
							if (rs.getAtomContainerCount() > 2) {
								int currentNonBidgeHead1 = 0;
								List<Integer> bridge = new ArrayList<Integer>();
								List<IAtomContainer> visited = new ArrayList<IAtomContainer>();
								visited.add(ac2);
								for (IAtom a2 : ac2.atoms()) {
									IRingSet rs2 = allRS.getRings(a2);
									if (rs2.getAtomContainerCount() == 1)
										currentNonBidgeHead1++;
									else {
										int currentNonBidgeHead2 = 0;
										for (IAtomContainer ac3 : rs2.atomContainers()) {
											if (!visited.contains(ac3)) {
												visited.add(ac3);
												for (IAtom a3 : ac3.atoms()) {
													IRingSet rs3 = allRS.getRings(a2);
													if (rs3.getAtomContainerCount() == 1)
														currentNonBidgeHead2++;
												}
											}
											bridge.add(currentNonBidgeHead2);
											currentNonBidgeHead2 = 0;
										}
									}
									bridge.add(currentNonBidgeHead1);
								}
								 Collections.sort(bridge, Collections.reverseOrder());
								 if (bridge.get(0) > 1 && bridge.get(0) < 4) {
									 boolean valid1 = false;
									 boolean valid2 = false;
									 for (int i = 1; i < bridge.size(); i++) {
										 //test the 2d and third
										 //[2.1.1], [2.2.1], or [3.2.1] 
										 if (bridge.get(i) > 0 && bridge.get(i) < 3 && valid1 == false) {
											 valid1 = true;
										 }
										 else if (bridge.get(i) == 1  && valid1 == true) {
											 valid2 = true;
										 }
										 if (valid1 && valid2)
											 return true;
									 }
								 }
							}
						}
						if (bond.getOrder().equals(Order.DOUBLE)) {
							for (IStereoElement se : ac.stereoElements()) {
								if (se.contains(bond.getBegin()) || se.contains(bond.getEnd())) {
									if (se.getConfig() == IStereoElement.OPPOSITE && atomCount < 10)
										return true;
								}
							}
						}
					}
				}
			}
		}
		else {
			for (IBond bond : ac.getConnectedBondsList(atom)) {
				if (bond.getOrder().equals(Order.DOUBLE)) {
					AllRingsFinder arf = new AllRingsFinder();
					IRingSet allRS = arf.findAllRings(ac);
					for (IAtom a : ac.getConnectedAtomsList(atom)) {
						IRingSet rs = allRS.getRings(a);
						for (IAtomContainer ac2 : rs.atomContainers()) {
							int atomCount = ac2.getAtomCount();
							if (atomCount == 3 || atomCount == 4) 
								return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * @param atom
	 * @param bond
	 * @param ac
	 * @return
	 */
	public Boolean isSyn(IAtom atom, IBond bond, IAtomContainer ac) {
		return !isAnti(atom, bond, ac);
	}
	
	/**
	 * @param atom1
	 * @param atom2
	 * @param ac
	 * @return
	 */
	public Boolean isSyn(IAtom atom1, IAtom atom2, IAtomContainer ac) {
		return !isAnti(atom1, atom2, ac);
	}
	
	/**
	 * @param bond1
	 * @param bond2
	 * @return
	 */
	public Boolean isSyn(IBond bond1, IBond bond2) {
		return !isAnti(bond1, bond2);
	}
	
	/**Tests whether two atoms on the same ring are trans to each other. The two atoms and the ring must all be specified
	 * @param atom1
	 * @param atom2
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public boolean isTrans(IAtom atom1, IAtom atom2, IAtomContainer ac) throws CDKException {
		return !isCis(atom1, atom2, ac);
	}
	
	/**
	 * A carbon atom joined to another carbon atom by a double bond where both carbon atoms carry one (and only one) 
	 * hydrogen atom and the substituents are in a trans relationship to each other.
	 * @param atom
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public boolean isTransOlefin(IAtom atom, IAtomContainer ac) throws CDKException {
		return !isCisOlefin(atom, ac);
	}
	
	/**
	 * There is a triple bond on the atom
	 * @param atom
	 * @param ac
	 * @return
	 */
	public boolean isTriply(IAtom atom, IAtomContainer ac) {
		for (IBond bond : ac.getConnectedBondsList(atom)){
			if (bond.getOrder() == Order.TRIPLE)
				return true;
		}
		return false;
	}
	
	/**
	 * has a bond activated/deactivated by a Withdrawing group
	 * @param atom
	 * @param ac
	 * @return
	 */
	public boolean hasWithdrawingBond(IAtom atom, IAtomContainer ac, int location) {
		if (numberOfDonatingBonds(atom, ac, location) > 0)
			return true;
		else
			return false;
	}
	
	/**
	 * number of bonds activated/deactivated by a Withdrawing group
	 * @param atom
	 * @param ac
	 * @param location 
	 * @return
	 */
	public int numberOfWithdrawingBonds(IAtom atom, IAtomContainer ac, int location) {
		SliceBond sbond = new SliceBond();
		List<IBond> mappings = null;
		try {
			mappings = sbond.getWithDrawingBonds(atom, ac, location);
		} catch (CDKException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mappings.size();
	}
	
	/**
	 * The atoms alpha to a specified atom
	 * @param atom
	 * @param ac
	 * @return
	 */
	public List<IAtom> getAtomsToAtoms(IAtom atom, IAtomContainer ac) {
		List<IAtom> atomsToAtoms = new ArrayList<IAtom>();
		for (IAtom atom2 : ac.getConnectedAtomsList(atom)) {
			//hydrogens are ignored
			if (!atom2.getSymbol().equals("H"))
				atomsToAtoms.add(atom2);
		}
		return atomsToAtoms;
	}
	
	/**
	 * The atoms alpha to a specified set of atoms
	 * @param atom
	 * @param ac
	 * @return
	 */
	public List<IAtom> getAtomsToAtoms(List<IAtom> atoms, IAtomContainer ac) {
		Set<IAtom> atomsToAtoms = new HashSet<IAtom>();
		for (IAtom atom : atoms) {
			for (IAtom atom2 : ac.getConnectedAtomsList(atom)) {
				//hydrogens are ignored
				if (!atom2.getSymbol().equals("H"))
					atomsToAtoms.add(atom2);
			}
		}
		return new ArrayList<IAtom>(atomsToAtoms);
	}
	
	/**
	 * The bonds on a specified atom
	 * @param atom
	 * @param ac
	 * @return
	 */
	public List<IBond> getAtomsToBonds(IAtom atom, IAtomContainer ac) {
		List<IBond> atomsToBonds = new ArrayList<IBond>();
		for (IBond bond : ac.getConnectedBondsList(atom)) {
			if (!bond.getBegin().getSymbol().equals("H") && !bond.getEnd().getSymbol().equals("H"))
				atomsToBonds.add(bond);
		}
		return atomsToBonds;
	}
	
	/**
	 * The bonds a specified set of atoms
	 * @param atom
	 * @param ac
	 * @return
	 */
	public List<IBond> getAtomsToBonds(List<IAtom> atoms, IAtomContainer ac) {
		Set<IBond> atomsToBonds = new HashSet<IBond>();
		for (IAtom atom : atoms) {
			for (IBond bond : ac.getConnectedBondsList(atom)) {
				if (!bond.getBegin().getSymbol().equals("H") && !bond.getEnd().getSymbol().equals("H"))
					atomsToBonds.add(bond);
			}
		}
		return new ArrayList<IBond>(atomsToBonds);
	}
	
	/**
	 * The atoms attached to a specified bond
	 * @param atom
	 * @param ac
	 * @return
	 */
	public List<IAtom> getBondsToAtoms(IBond bond, IAtomContainer ac) {
		List<IAtom> bondsToAtoms = new ArrayList<IAtom>();
		//hydrogens are ignored
		if (!bond.getBegin().getSymbol().equals("H") && !bond.getEnd().getSymbol().equals("H")) {
			bondsToAtoms.add(bond.getBegin());
			bondsToAtoms.add(bond.getEnd());
		}
		return bondsToAtoms;
	}
	
	/**
	 * The atoms attached to a specified set of bonds
	 * @param atom
	 * @param ac
	 * @return
	 */
	public List<IAtom> getBondsToAtoms(List<IBond> bonds, IAtomContainer ac) {
		Set<IAtom> bondsToAtoms = new HashSet<IAtom>();
		for (IBond bond : bonds) {
			//hydrogens are ignored
			if (!bond.getBegin().getSymbol().equals("H") && !bond.getEnd().getSymbol().equals("H")) {
				bondsToAtoms.add(bond.getBegin());
				bondsToAtoms.add(bond.getEnd());
			}
		}
		return new ArrayList<IAtom>(bondsToAtoms);
	}
	
	/**
	 * The bonds attached to a specified bond
	 * @param atom
	 * @param ac
	 * @return
	 */
	public List<IBond> getBondsToBond(IBond bond, IAtomContainer ac) {
		Set<IBond> bondsToBonds = new HashSet<IBond>();
		for (IBond bond2 : ac.getConnectedBondsList(bond.getBegin())) {
			//hydrogens are ignored
			if (!bond2.getBegin().getSymbol().equals("H") && !bond2.getEnd().getSymbol().equals("H"))
				bondsToBonds.add(bond2);
		}
		for (IBond bond2 : ac.getConnectedBondsList(bond.getEnd())) {
			//hydrogens are ignored
			if (!bond2.getBegin().getSymbol().equals("H") && !bond2.getEnd().getSymbol().equals("H"))
				bondsToBonds.add(bond2);
		}
		return new ArrayList<IBond>(bondsToBonds);
	}
	
	/**
	 * The bonds attached to a specified set of bonds
	 * @param atom
	 * @param ac
	 * @return
	 */
	public List<IBond> getBondsTobonds(List<IBond> bonds, IAtomContainer ac) {
		Set<IBond> bondsToBonds = new HashSet<IBond>();
		for (IBond bond : bonds) {
			for (IBond bond2 : ac.getConnectedBondsList(bond.getBegin())) {
				//hydrogens are ignored
				if (!bond2.getBegin().getSymbol().equals("H") && !bond2.getEnd().getSymbol().equals("H"))
					bondsToBonds.add(bond2);
			}
			for (IBond bond2 : ac.getConnectedBondsList(bond.getEnd())) {
				//hydrogens are ignored
				if (!bond2.getBegin().getSymbol().equals("H") && !bond2.getEnd().getSymbol().equals("H"))
					bondsToBonds.add(bond2);
			}
		}
		return new ArrayList<IBond>(bondsToBonds);
	}
	
	/**
	 * get number of explicit Hydrogens on an atom
	 * @param atom
	 * @param ac
	 * @return
	 */
	public int getExplicitHydrogenCount(IAtom atom, IAtomContainer ac) {
		int hydrogenCount = 0;
		for (IAtom a : ac.getConnectedAtomsList(atom)) {
			if (a.getSymbol().equals("H"))
				hydrogenCount++;
		}
		return hydrogenCount;
	}
	
	/**
	 * @param set
	 * @param atom
	 * @param location
	 * @return
	 */
	private boolean checkMapping(Collection<IAtom> set, IAtom atom, int location) {
		boolean isCompatible = true;
		for (IAtom a : set) {
			if (!a.equals(atom)) {
				if ((boolean) a.getProperty(location) != true) {
					isCompatible = false;
					break;
				}
			}
		}
		return isCompatible;
	}
}
