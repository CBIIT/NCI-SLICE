package com.nih.slice.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IRingSet;
import org.openscience.cdk.interfaces.IStereoElement;
import org.openscience.cdk.interfaces.IBond.Order;
import org.openscience.cdk.isomorphism.Mappings;
import org.openscience.cdk.ringsearch.AllRingsFinder;
import org.openscience.cdk.stereo.DoubleBondStereochemistry;
import org.openscience.cdk.stereo.ExtendedCisTrans;

import com.nih.slice.cdk.SliceConstants;

public class SliceBond {

	public static enum bondProp {
		ALLYLIC, ANTI, AROMATIC, BREDT_STRAINED, BRIDGEHEAD, CIS_OLEFIN, COMMON, CONJUGATED, DONATING, DOUBLE_BOND, FUSION, MULTIPLE,
		SINGLE_BOND, SMALL_FUSION, STRAINED, SYN, TRANS_OLEFIN, TRIPLE_BOND, WITHDRAWING;
	}
	
	/**
	 * global function to return value of all methods called with the IS relation
	 * @param prop
	 * @param atom1
	 * @param atom2
	 * @param bond
	 * @param ac
	 * @param location
	 * @return
	 * @throws CDKException
	 */
	public Boolean isProp(bondProp prop, IBond bond1, IBond bond2, IAtom atom, IAtomContainer ac, int location) throws CDKException {
		switch (prop) {
		case ALLYLIC:
			return isAllylic(bond1, ac);
		case ANTI:
			if (atom != null)
				return isAnti(atom, bond1, ac);
			else if (bond2 != null)
				return isAnti(bond1, bond2);
		case AROMATIC:
			return isAromatic(bond1);
		case BREDT_STRAINED:
			return isBredtStrained(bond1, ac);
		case BRIDGEHEAD:
			return isBridgehead(bond1, ac);
		case CIS_OLEFIN:
			return isCisOlefin(bond1, ac);
		case COMMON:
			return isCommon(bond1, atom, ac);
		case CONJUGATED:
			return isConjugated(bond1, ac);
		case DONATING:
			if (atom != null)
				return isDonating(atom, ac, location);
			else if (bond1 != null)
				return isDonating(bond1, ac, location);
		case DOUBLE_BOND:
			return isDouble(bond1);
		case FUSION:
			return isFusion(bond1, ac);
		case MULTIPLE:
			System.out.println("isMultiple: "+ isMultiple(bond1));
			return isMultiple(bond1);
		case SINGLE_BOND:
			return isSingle(bond1);
		case SMALL_FUSION:
			return isSmallFusion(bond1, ac);
		case STRAINED:
			return isStrained(bond1, ac);
		case SYN:
			return isSyn(bond1, bond2);
		case TRANS_OLEFIN:
			return isTransOlefin(bond1, ac);
		case TRIPLE_BOND:
			return isTriple(bond1);
		case WITHDRAWING:
			if (atom != null)
				return isWithDrawing(atom, ac, location);
			else if (bond1 != null)
				return isWithDrawing(bond1, ac, location);
		default:
			return null;
		}
	}
	
	/**
	 * The bond is on an allylic carbon atom and is not the bond linking that atom to the doubly bonded one.
	 * H      H
	 *   \   /
	 *    C=C
	 *   /   \
	 *  H     CH3 <-Allylic carbon
	 *  			
	 * @param bond
	 * @param ac
	 * @return
	 */
	public boolean isAllylic(IBond bond, IAtomContainer ac) {
		SliceAtom sca = new SliceAtom();
		return sca.isAllylic(bond.getBegin(), ac) || sca.isAllylic(bond.getEnd(), ac);
	}
	
	public boolean isAromatic(IBond bond) {
		return bond.isAromatic();
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
	 * @param bond1
	 * @param bond2
	 * @return
	 */
	public Boolean isAnti(IBond bond1, IBond bond2) {
		if (bond1.getStereo().equals(bond2.getStereo()))
			return true;
		return false;
	}
	
	/**
	 * The bond is a double bond at a bridgehead and in a ring of size less than eight.
	 * 	 *  
	 *             *   *
	 *           // \ /  \    
	 *          *    X    *   X is a bredt strained atom
	 *          |    "    |
	 *          *    X    *   
	 *           \\ / \  /     
	 *             *    * 
	 * @param bond
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public boolean isBredtStrained(IBond bond, IAtomContainer ac) throws CDKException {
		if (bond.isInRing() && bond.getOrder().equals(Order.DOUBLE)) {
			AllRingsFinder arf = new AllRingsFinder();
			IRingSet allRS = arf.findAllRings(ac);
			IRingSet bridgeRings = allRS.getRings(bond);
			if (bridgeRings.getAtomContainerCount() > 1) {
				for (IAtomContainer ac2 : bridgeRings.atomContainers()) {
					if (ac2.getAtomCount() < 8)
						return true;
				}
			}

		}
		return false;
	}
	
	/**
	 * The bonds attached to bridgehead atoms in bridgedcyclic systems.
	 * 	  
	 *             *   *
	 *           // \ /  \    
	 *          *    X    *   X is a bridgehead atom
	 *          |    |    |
	 *          *    X    *   
	 *           \\ / \  /     
	 *             *    * 
	 * @param bond
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public boolean isBridgehead(IBond bond, IAtomContainer ac) throws CDKException {
		SliceAtom sca = new SliceAtom();
		return sca.isBridgehead(bond.getBegin(), ac) && sca.isBridgehead(bond.getEnd(), ac);
	}
	
	/**
	 * A double bond between two carbon atoms where both carbon atoms carry one (and only one) hydrogen atom 
	 * and the substituents are in a cis relationship to each other..
	 * @param atom1
	 * @param atom2
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public boolean isCisOlefin(IBond bond, IAtomContainer ac) throws CDKException {
		if (bond.getOrder().equals(Order.DOUBLE)) {
			IAtom begin = bond.getBegin();
			IAtom end = bond.getEnd();
			
			SliceAtom sca = new SliceAtom();
			int hydrogenBeginCount = sca.getExplicitHydrogenCount(begin, ac);
			int hydrogenEndCount = sca.getExplicitHydrogenCount(end, ac);
			
			if (begin.getImplicitHydrogenCount() + hydrogenBeginCount == 1 && 
					end.getImplicitHydrogenCount() + hydrogenEndCount == 1) {
				for (IStereoElement se : ac.stereoElements()) {
					// both carbon are in the same stereoelement
					if (se.contains(begin) && se.contains(end)) {
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
		return false;
	}
	
	/**
	 * Tests whether atoms and/or bonds are in the same ring
	 * @param bond
	 * @param atom
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public boolean isCommon(IBond bond, IAtom atom, IAtomContainer ac) throws CDKException {
		if (bond.isInRing()) {
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
	public boolean isCommon(IBond bond, IAtom atom, IRingSet set) throws CDKException {
		if (atom.isInRing() && bond.isInRing()) {
			if (set.contains(atom) && set.contains(bond.getBegin()) && set.contains(bond.getEnd()))
				return true;
			else
				return false;
		}
		return false;
	}
	
	/**
	 * Tests whether atoms and/or bonds are in the same ring
	 * @param bond
	 * @param bond2
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public boolean isCommon(IBond bond, IBond bond2, IAtomContainer ac) throws CDKException {
		if (bond.isInRing()) {
			AllRingsFinder arf = new AllRingsFinder();
			IRingSet allRS = arf.findAllRings(ac);
			IRingSet rs = allRS.getRings(bond);
			if (rs.getRings(bond2).getAtomContainerCount() > 0)
				return true;
			else
				return false;
		}
		return false;
	}
	
	/**
	 * Tests whether atoms and/or bonds are in the same ring
	 * @param bond
	 * @param bond2
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public boolean isCommon(IBond bond, IBond bond2, IRingSet set) throws CDKException {
		if (bond2.isInRing() && bond.isInRing()) {
			if (set.contains(bond2.getBegin()) && set.contains(bond2.getEnd()) && set.contains(bond.getBegin()) && set.contains(bond.getEnd()))
				return true;
			else
				return false;
		}
		return false;
	}
	
	/**
	 * A bond on an atom that is alpha to a multiply-bonded atom in a functional group and is not the one connecting 
	 * the atom to the functional group.
	 * @param bond
	 * @param ac
	 * @return
	 */
	public boolean isConjugated(IBond bond, IAtomContainer ac) {
		IAtom begin = bond.getBegin();
		IAtom end = bond.getEnd();
		SliceAtom sca = new SliceAtom();
		return (sca.isConjugated(begin, ac) && !sca.isMultiply(begin, ac)) || 
				(sca.isConjugated(end, ac) && !sca.isMultiply(end, ac)) ;
	}
	
	/**
	 * @param bond
	 * @param set
	 * @return
	 * @throws CDKException
	 */
	public boolean isInCurrentRing(IBond bond, IRingSet set) throws CDKException {
		if (bond.isInRing()) {
			if (set.contains(bond.getBegin())) {
				//IRingSet set2 = set.getRings(bond.getBegin());
				if (set.contains(bond.getEnd())) {
					return true;
				}
				else
					return false;
			}
			else
				return false;
		}
		return false;
	}
	
	/**
	 * A fusion bond between two aromatic rings
	 * @param bond
	 * @param ac
	 * @return
	 * @throws CDKException 
	 */
	public boolean isDiarylFusion(IBond bond, IAtomContainer ac) throws CDKException {
		if (bond.isAromatic()) {
			AllRingsFinder arf = new AllRingsFinder();
			IRingSet allRS = arf.findAllRings(ac);
			if (allRS.getRings(bond).getAtomContainerCount() > 1)
				return true;
		}
		return false;
	}
	
	/**
	 * The bond is activated/deactivated by a DONATING group. 
	 * 
	 * @param atom
	 * @param ac
	 * @param location
	 * @return
	 * @throws CDKException
	 */
	public boolean isDonating(IAtom atom, IAtomContainer ac, int location) throws CDKException {
		SliceGroup cg = new SliceGroup();
		List<Map<IAtom, IAtom>> mappings = cg.getDonating(atom, ac, location);
		return mappings.size() > 0 ? true : false;
	}
	
	/**
	 * The bond is activated/deactivated by a DONATING group. 
	 * 
	 * @param atom
	 * @param ac
	 * @param location
	 * @return
	 * @throws CDKException
	 */
	public List<IBond> getDonatingBonds(IAtom atom, IAtomContainer ac, int location) throws CDKException {		
		List<IBond> donating = new ArrayList<IBond>();
		
		//case non expandable (take origin)
		SliceGroup cg = new SliceGroup();
		List<Map<IAtom, IAtom>> mappings = cg.getDonating(atom, ac, location);
		for (Map<IAtom, IAtom> map : mappings) {
			if (map.containsValue(atom)) {
				for (IBond bond : ac.getConnectedBondsList(atom)) {
					if (map.containsValue(bond.getOther(atom))){
						donating.add(bond);
						break;
					}
				}
			}
		}
		
		return donating;
	}
	
	/**
	 * The bond is activated/deactivated by a DONATING group
	 * @param bond
	 * @param ac
	 * @param location
	 * @return
	 * @throws CDKException
	 */
	public boolean isDonating(IBond bond, IAtomContainer ac, int location) throws CDKException {
		IAtom begin = bond.getBegin();
		IAtom end = bond.getEnd();

		SliceGroup cg = new SliceGroup();
		List<Map<IAtom, IAtom>> mappings = cg.getDonating(ac, location);
		for (Map<IAtom, IAtom> map : mappings) {
			//location should not be an issue as it's should be set up as anywhere as is is tested
			if (map.containsValue(begin) && map.containsValue(end))
				return true;
		}
		return false;
	}
	
	/**
	 * A double bond
	 * @param bond
	 * @return
	 */
	public boolean isDouble(IBond bond) {
		return bond.isAromatic() || bond.getOrder().equals(Order.DOUBLE);
	}
	
	/**
	 * A fusion bond between two rings
	 * @param bond
	 * @param ac
	 * @return
	 * @throws CDKException 
	 */
	public boolean isFusion(IBond bond, IAtomContainer ac) throws CDKException {
		AllRingsFinder arf = new AllRingsFinder();
		IRingSet allRS = arf.findAllRings(ac);
		if (allRS.getRings(bond).getAtomContainerCount() > 1)
			return true;
		return false;
	}
	
	/**
	 * A multiple bond
	 * @param bond
	 * @return
	 */
	public boolean isMultiple(IBond bond) {
		return bond.isAromatic() || (!bond.getOrder().equals(Order.SINGLE) && !bond.getOrder().equals(Order.UNSET));
	}
	
	/**
	 * The bond is included in the keying pattern for the transform 
	 * @param bond
	 * @param ring
	 * @return
	 */
	public boolean isOnPath(IBond bond, IAtomContainer ring) {
		if ((boolean) bond.getBegin().getProperty(SliceConstants.ONPATH) == true &&
				(boolean) bond.getEnd().getProperty(SliceConstants.ONPATH) == true) 
			return true;
		else
			return false;
	}
	
	/**
	 * The bond is not included in the keying pattern for the transform
	 * @param atom
	 * @param ring
	 * @return
	 */
	public boolean isOffPath(IBond bond, IAtomContainer ring) {
		return !isOnPath(bond, ring);
	}
	
	/**
	 * The atom is in the ring that has been designated as the current ring.
	 * @param atom
	 * @param ring
	 * @return
	 */
	public boolean isOnRing(IBond bond, IAtomContainer ring) {
		return ring.contains(bond);
	}
	
	/**
	 * The atom is not in the ring that has been designated as the current ring.
	 * @param atom
	 * @param ring
	 * @return
	 */
	public boolean isOffRing(IBond bond, IAtomContainer ring) {
		return !isOnRing(bond, ring);
	}
	
	/**
	 * A single bond
	 * @param bond
	 * @return
	 */
	public boolean isSingle(IBond bond) {
		return bond.isAromatic() || bond.getOrder().equals(Order.SINGLE);
	}
	
	/**
	 * A fusion bond between a pair of rings of sizes 3/3, 3/4, 3/5, 3/6, 4/4. 4/5, 4/6, or 5/5
	 * @param bond
	 * @param ac
	 * @return
	 * @throws CDKException 
	 */
	public boolean isSmallFusion(IBond bond, IAtomContainer ac) throws CDKException {
		AllRingsFinder arf = new AllRingsFinder();
		IRingSet allRS = arf.findAllRings(ac);
		IRingSet rs = allRS.getRings(bond);
		if (rs.getAtomContainerCount() > 1) {
			int count1 = rs.getAtomContainer(0).getAtomCount();
			int count2 = rs.getAtomContainer(1).getAtomCount(); 
			if (count1 == 3 && count2 > 2 && count2 < 7)
				return true;
			else if (count1 == 4 && count2 > 3 && count2 < 7)
				return true;
			else if (count1 == 5 && count2 == 5)
				return true;
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
	 * @param bond1
	 * @param bond2
	 * @return
	 */
	public Boolean isSyn(IBond bond1, IBond bond2) {
		return !isAnti(bond1, bond2);
	}
	
	
	/**
	 * A bond is considered strained if it is:
	 * in a three- or four-membered ring;
	 * or in a five-membered ring that is part of a [2.1.1], [2.2.1], or [3.2.1] system; (The notation [x.y.z]propellane means the member of the family whose rings have x, y, and z carbons, not counting the two bridgeheads; or x + 2, y + 2, and z + 2 carbons, counting them SOURCE: wikipedia)
	 * or a trans double bond in a ring of size 9 or less;
	 * or a double bond exocyclic to a three- or four-membered ring.
	 * @param bond
	 * @param ac
	 * @return
	 * @throws CDKException 
	 */
	public boolean isStrained(IBond bond, IAtomContainer ac) throws CDKException {
		if (bond.isInRing()) {
			AllRingsFinder arf = new AllRingsFinder();
			IRingSet allRS = arf.findAllRings(ac);
			IRingSet rs = allRS.getRings(bond);
			for (IAtomContainer ac2 : rs.atomContainers()) {
				int atomCount = ac2.getAtomCount();
				if (atomCount == 3 || atomCount == 4)
					return true;
				else if (atomCount == 5) {
					// TODO
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
								// test the 2d and third
								// [2.1.1], [2.2.1], or [3.2.1]
								if (bridge.get(i) > 0 && bridge.get(i) < 3 && valid1 == false) {
									valid1 = true;
								} else if (bridge.get(i) == 1 && valid1 == true) {
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
		} else {
			//or a double bond exocyclic to a three- or four-membered ring.
			if (bond.getOrder().equals(Order.DOUBLE)) {
				AllRingsFinder arf = new AllRingsFinder();
				IRingSet allRS = arf.findAllRings(ac);
				for (IAtom a : ac.getConnectedAtomsList(bond.getBegin())) {
					IRingSet rs = allRS.getRings(a);
					for (IAtomContainer ac2 : rs.atomContainers()) {
						int atomCount = ac2.getAtomCount();
						if (atomCount == 3 || atomCount == 4)
							return true;
					}
				}
				for (IAtom a : ac.getConnectedAtomsList(bond.getEnd())) {
					IRingSet rs = allRS.getRings(a);
					for (IAtomContainer ac2 : rs.atomContainers()) {
						int atomCount = ac2.getAtomCount();
						if (atomCount == 3 || atomCount == 4)
							return true;
					}
				}
			}
		}

		return false;
	}
	
	/**
	 * A double bond between two carbon atoms where both carbon atoms carry one (and only one) hydrogen atom 
	 * and the substituents are in a trans relationship to each other.
	 * @param atom1
	 * @param atom2
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public boolean isTransOlefin(IBond bond, IAtomContainer ac) throws CDKException {
		return !isCisOlefin(bond, ac);
	}
	
	/**
	 * A triple bond
	 * @param bond
	 * @return
	 */
	public boolean isTriple(IBond bond) {
		return bond.getOrder().equals(Order.TRIPLE);
	}
	
	/**
	 * The bond is activated/deactivated by a WITHDRAWING group. Note that the withdrawing bonds of a non-expandable 
	 * withdrawing group (e.g. NITRO) are on the same atom as the withdrawing group; the withdrawing bonds of an
	 * expandable withdrawing group (e.g.KETONE) are on the atom(s) alpha to it.
	 * 
	 * 
	 * An expandable withdrawing group is one that activates the atom(s) alpha to the origin of
	 * the group, (e.g., ketone, ester); a non-expanding withdrawing group actives the origin of
	 * the group itself (e.g. nitro). So WITHDRAWING BONDs associated with a non-
	 * expanding withdrawing group are the bonds on the origin itself (excluding those that are
	 * defined as part of the group); WITHDRAWING BONDs associated with an expandable
	 * withdrawing group are the bonds on the atom(s) alpha to the origin (excluding the atoms
	 * that are defined as part of the group).
	 * 
	 * @param aton
	 * @param ac
	 * @param location
	 * @return
	 * @throws CDKException
	 */
	public boolean isWithDrawing(IAtom atom, IAtomContainer ac, int location) throws CDKException {
		//WITHDRAWING BONDs associated with a non-expanding withdrawing group are the bonds on the origin (begin or end atom) itself
		//=> all bonds, where begin or end atom matches with origin 
		//WITHDRAWING BONDs associated with an expandable withdrawing group are the bonds on the atom(s) alpha to the origin (begin or end atom) 
		//=> all bonds, where begin or end matches with the alpha(s) of origin, ie all connected atom to origin
		// in that case -> get all connected atom to begin or end
		
		/**
		 * if WITHDRAWING GROUP on atom 2
		 * if non expandable take atom2 directly as orgin
		 * if expandable take connected atom (alpha) to atom 2 as origin
		 */
		
		//case non expandable (take origin)
		SliceGroup cg = new SliceGroup();
		List<Map<IAtom, IAtom>> mappings = cg.getNonExpandableWithDrawing(atom, ac, location);
		if (mappings.size() > 0)
			return true;
		
		
		//case expandable (take alpha(s) to origin)
		mappings = cg.getExpandableWithDrawing(atom, ac, location);
		return mappings.size() > 0 ? true : false;
	}
	
	/**
	 * The bond is activated/deactivated by a WITHDRAWING group. Note that the withdrawing bonds of a non-expandable 
	 * withdrawing group (e.g. NITRO) are on the same atom as the withdrawing group; the withdrawing bonds of an
	 * expandable withdrawing group (e.g.KETONE) are on the atom(s) alpha to it.
	 * 
	 * 
	 * An expandable withdrawing group is one that activates the atom(s) alpha to the origin of
	 * the group, (e.g., ketone, ester); a non-expanding withdrawing group actives the origin of
	 * the group itself (e.g. nitro). So WITHDRAWING BONDs associated with a non-
	 * expanding withdrawing group are the bonds on the origin itself (excluding those that are
	 * defined as part of the group); WITHDRAWING BONDs associated with an expandable
	 * withdrawing group are the bonds on the atom(s) alpha to the origin (excluding the atoms
	 * that are defined as part of the group).
	 * 
	 * @param atom
	 * @param ac
	 * @param location
	 * @return
	 * @throws CDKException
	 */
	public List<IBond> getWithDrawingBonds(IAtom atom, IAtomContainer ac, int location) throws CDKException {
		//WITHDRAWING BONDs associated with a non-expanding withdrawing group are the bonds on the origin (begin or end atom) itself
		//=> all bonds, where begin or end atom matches with origin 
		//WITHDRAWING BONDs associated with an expandable withdrawing group are the bonds on the atom(s) alpha to the origin (begin or end atom) 
		//=> all bonds, where begin or end matches with the alpha(s) of origin, ie all connected atom to origin
		// in that case -> get all connected atom to begin or end
		
		/**
		 * if WITHDRAWING GROUP on atom 2
		 * if non expandable take atom2 directly as origin
		 * if expandable take connected atom (alpha) to atom 2 as origin
		 */
		
		List<IBond> withdrawing = new ArrayList<IBond>();
		
		SliceGroup cg = new SliceGroup();
		
		//case non expandable (take origin)
		List<Map<IAtom, IAtom>> mappings = cg.getWithDrawing(atom, ac, location);
		for (Map<IAtom, IAtom> map : mappings) {
			if (map.containsValue(atom)) {
				for (IBond bond : ac.getConnectedBondsList(atom)) {
					if (map.containsValue(bond.getOther(atom))){
						withdrawing.add(bond);
						break;
					}
				}
			}
		}
		
		return withdrawing;
	}
	
	/**
	 * The bond is activated/deactivated by a WITHDRAWING group. Note that the withdrawing bonds of a non-expandable 
	 * withdrawing group (e.g. NITRO) are on the same atom as the withdrawing group; the withdrawing bonds of an
	 * expandable withdrawing group (e.g.KETONE) are on the atom(s) alpha to it.
	 * 
	 * 
	 * An expandable withdrawing group is one that activates the atom(s) alpha to the origin of
	 * the group, (e.g., ketone, ester); a non-expanding withdrawing group actives the origin of
	 * the group itself (e.g. nitro). So WITHDRAWING BONDs associated with a non-
	 * expanding withdrawing group are the bonds on the origin itself (excluding those that are
	 * defined as part of the group); WITHDRAWING BONDs associated with an expandable
	 * withdrawing group are the bonds on the atom(s) alpha to the origin (excluding the atoms
	 * that are defined as part of the group).
	 * 
	 * @param bond
	 * @param ac
	 * @param location
	 * @return
	 * @throws CDKException
	 */
	public boolean isWithDrawing(IBond bond, IAtomContainer ac, int location) throws CDKException {
		//WITHDRAWING BONDs associated with a non-expanding withdrawing group are the bonds on the origin (begin or end atom) itself
		//=> all bonds, where begin or end atom matches with origin 
		//WITHDRAWING BONDs associated with an expandable withdrawing group are the bonds on the atom(s) alpha to the origin (begin or end atom) 
		//=> all bonds, where begin or end matches with the alpha(s) of origin, ie all connected atom to origin
		// in that case -> get all connected atom to begin or end
		
		/**
		 * if WITHDRAWING GROUP on atom 2
		 * if non expandable take atom2 directly as origin
		 * if expandable take connected atom (alpha) to atom 2 as origin
		 */
		
		//case non expandable (take origin)
		SliceGroup cg = new SliceGroup();
		List<Map<IAtom, IAtom>> mappings = cg.getWithDrawing(ac, location);
		for (Map<IAtom, IAtom> map : mappings) {
			//location should not be an issue as it's should be set up as anywhere as is is tested
			if (map.containsValue(bond.getBegin()) && map.containsValue(bond.getEnd()))
				return true;
		}
		return false;
	}
	
	
	//TODO add location check for all group and bond when checking SMARTS
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
