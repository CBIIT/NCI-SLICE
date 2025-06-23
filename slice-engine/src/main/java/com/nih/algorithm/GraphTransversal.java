package com.nih.algorithm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;

public class GraphTransversal {

	public void getGroupWithSpecificProperty(Set<IAtom> sphere, int prop, IAtomContainer ac, Set<IAtom> result) {
		IAtom nextAtom;
		Set<IAtom> newSphere = new HashSet<IAtom>();

		for (IAtom atom : sphere) {
			List<IBond> bonds = ac.getConnectedBondsList(atom);
			for (IBond bond : bonds) {
				nextAtom = bond.getOther(atom);
				if ((boolean)nextAtom.getProperty(prop)) {
					if (!sphere.contains(nextAtom)) newSphere.add(nextAtom);
				}
			}
		}
		result.addAll(sphere);
		if (newSphere.size() > 0) {
			getGroupWithSpecificProperty(newSphere, prop, ac, result);
		}
	}
	
}
