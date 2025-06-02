package com.nih.slice.config;

import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IRingSet;
import org.openscience.cdk.ringsearch.AllRingsFinder;

public class SliceSet {

	/**
	 * The ring designated as current
	 * @param atom
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public IRingSet getCurrentRing(IAtom atom, IAtomContainer ac) throws CDKException {
		AllRingsFinder arf = new AllRingsFinder();
		IRingSet allRS = arf.findAllRings(ac);
		IRingSet rs = allRS.getRings(atom);
		return rs;
	}
	
	/**
	 * The ring designated as current
	 * @param bond
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public IRingSet getCurrentRing(IBond bond, IAtomContainer ac) throws CDKException {
		AllRingsFinder arf = new AllRingsFinder();
		IRingSet allRS = arf.findAllRings(ac);
		IRingSet rs = allRS.getRings(bond);
		return rs;
	}
	
	/**
	 * get all rings
	 * @param ac
	 * @return
	 * @throws CDKException
	 */
	public IRingSet getAllRing(IAtomContainer ac) throws CDKException {
		AllRingsFinder arf = new AllRingsFinder();
		IRingSet allRS = arf.findAllRings(ac);
		return allRS;
	}
}
