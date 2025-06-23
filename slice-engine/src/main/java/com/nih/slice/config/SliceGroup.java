package com.nih.slice.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openscience.cdk.Atom;
import org.openscience.cdk.Bond;
import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.exception.InvalidSmilesException;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.isomorphism.Mappings;
import org.openscience.cdk.isomorphism.Pattern;
import org.openscience.cdk.isomorphism.matchers.QueryAtomContainer;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smarts.Smarts;
import org.openscience.cdk.smarts.SmartsPattern;
import org.openscience.cdk.smiles2.SmilesParser2;
import org.openscience.cdk.tools.manipulator.AtomContainerManipulator;

import com.nih.algorithm.GraphTransversal;
import com.nih.slice.cdk.SliceConstants;
import com.nih.tools.Tools;

public class SliceGroup {
	
	//IN CHMTRN groupe used to be carbon centered, so the functional group was not necessary alpha as it can be part of the group
	//by default in SLICE the group is alpha to an atom
	private boolean carbonCentered = false;
	
	FunctionalGroup[] donating = {FunctionalGroup.ACETAL, FunctionalGroup.ALCOHOL, FunctionalGroup.AMIDZ, FunctionalGroup.AMIDE,
			FunctionalGroup.ESTERX, FunctionalGroup.ETHER, FunctionalGroup.HALOAMINE, FunctionalGroup.HYDROXYLAMINE, FunctionalGroup.N_CARBAMATE,
			FunctionalGroup.N_UREA_C, FunctionalGroup.N_UREA_H, FunctionalGroup.O_CARBONATE, FunctionalGroup.PHOSPHINE, FunctionalGroup.SELENIDE,
			FunctionalGroup.SULFIDE, FunctionalGroup.THIOL, FunctionalGroup.TRIALKYLSILOXY};
	
	FunctionalGroup[] expandable_withdrawing = {FunctionalGroup.ACID_HALIDE, FunctionalGroup.ALDEHYDE, FunctionalGroup.AMIDE, FunctionalGroup.ANHYDRIDE,
			FunctionalGroup.CARBONIUM, FunctionalGroup.ESTER, FunctionalGroup.KETONE, FunctionalGroup.IMINE, FunctionalGroup.CYANO,
			FunctionalGroup.OXIME, FunctionalGroup.THIOESTER, FunctionalGroup.TRIHALIDE};

	FunctionalGroup[] good_leaving = {FunctionalGroup.AZIRIDINE, FunctionalGroup.BROMIDE, FunctionalGroup.CHLORIDE, FunctionalGroup.EPISULFIDE,
			FunctionalGroup.EPOXIDE, FunctionalGroup.IODIDE, FunctionalGroup.O_SULFONATE, FunctionalGroup.PHOSPHONATE,FunctionalGroup.GEM_DIHALIDE,
			FunctionalGroup.TRIHALIDE};
	
	FunctionalGroup[] leaving = {FunctionalGroup.ACETAL, FunctionalGroup.ALCOHOL, FunctionalGroup.AMIDZ, FunctionalGroup.AZIRIDINE, FunctionalGroup.CARBAMATE_C,
			FunctionalGroup.CARBAMATE_H, FunctionalGroup.DITHIOACETAL, FunctionalGroup.DITHIOKETAL, FunctionalGroup.EPISULFIDE, FunctionalGroup.EPOXIDE,
			FunctionalGroup.ESTERX, FunctionalGroup.ETHER, FunctionalGroup.GEM_DIHALIDE, FunctionalGroup.HALIDE, FunctionalGroup.N_UREA_C,
			FunctionalGroup.N_UREA_H, FunctionalGroup.O_CARBONATE, FunctionalGroup.PHOSPHONATE, FunctionalGroup.SELENIDE, FunctionalGroup.SULFIDE,
			FunctionalGroup.SULFONE, FunctionalGroup.C_SULFONATE, FunctionalGroup.O_SULFONATE, FunctionalGroup.THIOCYANATE, FunctionalGroup.THIOL,
			FunctionalGroup.TRIALKYLSILOXY, FunctionalGroup.TRIHALIDE};
	
	FunctionalGroup[] non_expandable_withdrawing = {FunctionalGroup.NITRO, FunctionalGroup.C_SULFONATE, FunctionalGroup.SULFONE, FunctionalGroup.SULFOXIDE};
	
	FunctionalGroup[] withdrawing = {FunctionalGroup.ACID,FunctionalGroup.ACID_HALIDE, FunctionalGroup.ALDEHYDE, FunctionalGroup.AMIDE, FunctionalGroup.ANHYDRIDE,
			FunctionalGroup.CARBONIUM, FunctionalGroup.ESTER, FunctionalGroup.KETONE, FunctionalGroup.IMINE, FunctionalGroup.CYANO,
			FunctionalGroup.OXIME, FunctionalGroup.THIOESTER, FunctionalGroup.TRIHALIDE,FunctionalGroup.NITRO, FunctionalGroup.C_SULFONATE, FunctionalGroup.SULFONE, FunctionalGroup.SULFOXIDE };
	
	FunctionalGroup[] functional = {FunctionalGroup.ACETAL, FunctionalGroup.ACETYLENE, FunctionalGroup.ACID, 
			FunctionalGroup.ACID_HALIDE, FunctionalGroup.ALCOHOL, FunctionalGroup.ALDEHYDE, FunctionalGroup.ALLENE, 
			FunctionalGroup.AMIDE1, FunctionalGroup.AMIDE2, FunctionalGroup.AMIDE3, FunctionalGroup.AMIDE, FunctionalGroup.AMIDZ, 
			FunctionalGroup.AMINE1, FunctionalGroup.AMINE2, FunctionalGroup.AMINE3, FunctionalGroup.AMINE, FunctionalGroup.AMINE_OXIDE, 
			FunctionalGroup.ANHYDRIDE, FunctionalGroup.AZIDE, FunctionalGroup.AZIRIDINE, FunctionalGroup.AZO, FunctionalGroup.BROMIDE, 
			FunctionalGroup.C_SULFONATE, FunctionalGroup.CARBAMATE_C, FunctionalGroup.CARBAMATE_H, FunctionalGroup.CARBONIUM, 
			FunctionalGroup.CARBONYL, FunctionalGroup.CARBOXYL, FunctionalGroup.CHLORIDE, FunctionalGroup.CYANO, FunctionalGroup.DIAZO, 
			FunctionalGroup.DISULFIDE, FunctionalGroup.DITHIOACETAL, FunctionalGroup.DITHIOKETAL, FunctionalGroup.ENAMINE, 
			FunctionalGroup.ENOL_ETHER, FunctionalGroup.EPISULFIDE, FunctionalGroup.EPOXIDE, FunctionalGroup.ESTER, FunctionalGroup.ESTERX, 
			FunctionalGroup.ETHER, FunctionalGroup.FLUORIDE, FunctionalGroup.GEM_DIHALIDE, FunctionalGroup.GLYCOL,
			FunctionalGroup.HALIDE, FunctionalGroup.HALOAMINE, FunctionalGroup.HALOHYDRIN, FunctionalGroup.HEMIACETAL, FunctionalGroup.HYDRATE, 
			FunctionalGroup.HYDRAZONE, FunctionalGroup.HYDROXYLAMINE, FunctionalGroup.IMINE, FunctionalGroup.IODIDE, FunctionalGroup.ISOCYANATE,
			FunctionalGroup.ISOCYANIDE, FunctionalGroup.KETONE, FunctionalGroup.LACTAM, FunctionalGroup.LACTONE, FunctionalGroup.METHYLENE, 
			FunctionalGroup.N_CARBAMATE, FunctionalGroup.N_UREA, FunctionalGroup.N_UREA_C, FunctionalGroup.N_UREA_H, FunctionalGroup.NITRITE, 
			FunctionalGroup.NITRO, FunctionalGroup.NITROSO, FunctionalGroup.O_CARBAMATE, FunctionalGroup.O_CARBONATE, 
			FunctionalGroup.O_SULFONATE, FunctionalGroup.OLEFIN, FunctionalGroup.OXIME, FunctionalGroup.PEROXIDE, FunctionalGroup.PHOSPHINE, 
			FunctionalGroup.PHOSPHONATE, FunctionalGroup.SELENIDE, FunctionalGroup.SILYLENOLETHER, FunctionalGroup.SULFIDE, 
			FunctionalGroup.SULFONE, FunctionalGroup.SULFOXIDE, FunctionalGroup.THIOCYANATE, FunctionalGroup.THIOESTER, 
			FunctionalGroup.THIOL, FunctionalGroup.TRIALKYLSILOXY, FunctionalGroup.TRIALKYLSILYL,
			FunctionalGroup.TRIHALIDE, FunctionalGroup.VIC_DIHALIDE, FunctionalGroup.VINYLSILANE, FunctionalGroup.ISOPROPYL, 
			FunctionalGroup.METHYL, FunctionalGroup.PHENYL, FunctionalGroup.T_BUTYL};
	
	
	public static enum FunctionalGroup {
		ACETAL, ACETYLENE, ACID, ACID_HALIDE, ALCOHOL, ALDEHYDE, ALLENE, AMIDE1, AMIDE2, AMIDE3, AMIDE, AMIDZ, AMINE1,
		AMINE2, AMINE3, AMINE, AMINE_OXIDE, ANHYDRIDE, AZIDE, AZIRIDINE, AZO, BROMIDE, C_SULFONATE, CARBAMATE_C,
		CARBAMATE_H, CARBONIUM, CARBONYL, CARBOXYL, CHLORIDE, CYANO, DIAZO, DISULFIDE, DITHIOACETAL, DITHIOKETAL,
		ENAMINE, ENOL_ETHER, EPISULFIDE, EPOXIDE, ESTER, ESTERX, ETHER, FLUORIDE, FUNCTIONAL, GEM_DIHALIDE, GLYCOL,
		HALIDE, HALOAMINE, HALOHYDRIN, HEMIACETAL, HYDRATE, HYDRAZONE, HYDROXYLAMINE, IMINE, IODIDE, ISOCYANATE,
		ISOCYANIDE, KETONE, LACTAM, LACTONE, METHYLENE, N_CARBAMATE, N_UREA, N_UREA_C, N_UREA_H, NITRITE, NITRO, NITROSO,
		O_CARBAMATE, O_CARBONATE, O_SULFONATE, OLEFIN, OXIME, PEROXIDE, PHOSPHINE, PHOSPHONATE, SELENIDE,
		SILYLENOLETHER, SULFIDE, SULFONE, SULFOXIDE, THIOCYANATE, THIOESTER, THIOL, TRIALKYLSILOXY, TRIALKYLSILYL,
		TRIHALIDE, VIC_DIHALIDE, VINYLSILANE, ISOPROPYL, METHYL, PHENYL, T_BUTYL;
	}
	

	public static enum groupProp {
		DONATING, NON_EXPANDABLE_WITHDRAWING, EXPANDABLE_WITHDRAWING, INTERFERING, GOOD_LEAVING, LEAVING, PARTICIPATING,
		PROTECTED, VINYL_D, VINYL_W, WITHDRAWING;
	}
	
	public static class ChemSmarts {
		String smarts;
		
		public ChemSmarts(String sm) {
			this.smarts = sm;
		}
	}

	/**
	 * check if has at least one group property is DONATING NON_EXPANDABLE_WITHDRAWING
	 * EXPANDABLE_WITHDRAWING INTERFERING GOOD_LEAVING LEAVING PROTECTED VINYL_D
	 * VINYL_WW and ITHDRAWING
	 * @param prop
	 * @param ac
	 * @param location
	 * @return
	 */
	public boolean hasGroup(groupProp prop, IAtomContainer ac, int location) {
		switch (prop) {
		case DONATING:
			return getDonating(ac, location).size() > 0 ? true : false;
		case NON_EXPANDABLE_WITHDRAWING:
			return getNonExpandableWithDrawing(ac, location).size() > 0 ? true : false;
		case EXPANDABLE_WITHDRAWING:
			return getExpandableWithDrawing(ac, location).size() > 0 ? true : false;
		case INTERFERING:
			return getDonating(ac, location).size() > 0 ? true : false;
		case GOOD_LEAVING:
			return getGoodLeaving(ac, location).size() > 0 ? true : false;
		case LEAVING:
			return getLeaving(ac, location).size() > 0 ? true : false;
		// case PARTICIPATING:
		// return getParticipating(ac);
		case PROTECTED:
			return getDonating(ac, location).size() > 0 ? true : false;
		case VINYL_D:
			return getVinylD(ac, location).size() > 0 ? true : false;
		case VINYL_W:
			return getVinylW(ac, location).size() > 0 ? true : false;
		case WITHDRAWING:
			return getWithDrawing(ac, location).size() > 0 ? true : false;
		default:
			break;
		}
		return false;
	}

	/**
	 * get mapped atom with a group property such as DONATING NON_EXPANDABLE_WITHDRAWING
	 * EXPANDABLE_WITHDRAWING INTERFERING GOOD_LEAVING LEAVING PROTECTED VINYL_D
	 * VINYL_WW and ITHDRAWING
	 * @param prop
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getGroup(groupProp prop, IAtomContainer ac, int location) {
		switch (prop) {
		case DONATING:
			return getDonating(ac, location);
		case NON_EXPANDABLE_WITHDRAWING:
			return getNonExpandableWithDrawing(ac, location);
		case EXPANDABLE_WITHDRAWING:
			return getExpandableWithDrawing(ac, location);
		case INTERFERING:
			return getDonating(ac, location);
		case GOOD_LEAVING:
			return getGoodLeaving(ac, location);
		case LEAVING:
			return getLeaving(ac, location);
		// case PARTICIPATING:
		// return getParticipating(ac);
		case PROTECTED:
			return getDonating(ac, location);
		case VINYL_D:
			return getVinylD(ac, location);
		case VINYL_W:
			return getVinylW(ac, location);
		case WITHDRAWING:
			return getWithDrawing(ac, location);
		default:
			break;
		}
		return null;
	}
	
	/**
	 * get the number of group properties, which has matched with
 	 * DONATING NON_EXPANDABLE_WITHDRAWING
	 * EXPANDABLE_WITHDRAWING INTERFERING GOOD_LEAVING LEAVING PROTECTED VINYL_D
	 * VINYL_WW and ITHDRAWING
	 * @param prop
	 * @param ac
	 * @param location
	 * @return
	 */
	public int groupCount(groupProp prop, IAtomContainer ac, int location) {
		List<Map<IAtom, IAtom>> mappings = new ArrayList<Map<IAtom, IAtom>>();
		switch (prop) {
		case DONATING:
			mappings = getDonating(ac, location);
			break;
		case NON_EXPANDABLE_WITHDRAWING:
			mappings =  getNonExpandableWithDrawing(ac, location);
			break;
		case EXPANDABLE_WITHDRAWING:
			mappings =  getExpandableWithDrawing(ac, location);
			break;
		case INTERFERING:
			mappings =  getDonating(ac, location);
			break;
		case GOOD_LEAVING:
			mappings =  getGoodLeaving(ac, location);
			break;
		case LEAVING:
			mappings =  getLeaving(ac, location);
			break;
		// case PARTICIPATING:
		// return getParticipating(ac);
		case PROTECTED:
			mappings =  getDonating(ac, location);
			break;
		case VINYL_D:
			mappings =  getVinylD(ac, location);
			break;
		case VINYL_W:
			mappings =  getVinylW(ac, location);
			break;
		case WITHDRAWING:
			mappings =  getWithDrawing(ac, location);
			break;
		default:
			break;
		}
		return mappings.size();
	}
	
	/**
	 * get the number of group properties, which has matched with
 	 * DONATING NON_EXPANDABLE_WITHDRAWING
	 * EXPANDABLE_WITHDRAWING INTERFERING GOOD_LEAVING LEAVING PROTECTED VINYL_D
	 * VINYL_WW and ITHDRAWING
	 * @param prop
	 * @param a
	 * @param ac
	 * @param location
	 * @return
	 */
	public int groupCount(groupProp prop, IAtom a, IAtomContainer ac, int location) {
		List<Map<IAtom, IAtom>> mappings = new ArrayList<Map<IAtom, IAtom>>();
		switch (prop) {
		case DONATING:
			mappings = getDonating(a, ac, location);
			break;
		case NON_EXPANDABLE_WITHDRAWING:
			mappings =  getNonExpandableWithDrawing(a, ac, location);
			break;
		case EXPANDABLE_WITHDRAWING:
			mappings =  getExpandableWithDrawing(a, ac, location);
			break;
		//case INTERFERING:
		//	mappings =  getDonating(ac);
		case GOOD_LEAVING:
			mappings =  getGoodLeaving(a, ac, location);
			break;
		case LEAVING:
			mappings =  getLeaving(a, ac, location);
			break;
		// case PARTICIPATING:
		// return getParticipating(ac);
		//case PROTECTED:
		//	mappings =  getDonating(ac);
		case VINYL_D:
			mappings =  getVinylD(a, ac, location);
			break;
		case VINYL_W:
			mappings =  getVinylW(a, ac, location);
			break;
		case WITHDRAWING:
			mappings =  getWithDrawing(a, ac, location);
			break;
		default:
			break;
		}
		//System.out.println("CPT " +cpt);
		//System.out.println("-------------------------------");
		return mappings.size();
	}
	
	/**
	 * check if the molecule has at least one a functional group
	 * @param fg
	 * @param ac
	 * @param location
	 * @return
	 */
	public boolean hasFunctionalGroup(FunctionalGroup fg, IAtomContainer ac, int location) {
		return getUniqueFunctionalGroup(fg, ac, location).size() > 0 ? true : false;
	}
	
	/**
	 * get the number of functional groups in the molecule
	 * @param fg
	 * @param ac
	 * @param location
	 * @return
	 */
	public int functionalGroupCount(FunctionalGroup fg, IAtomContainer ac, int location) {
		
		List<Map<IAtom, IAtom>> mappings = getUniqueFunctionalGroup(fg, ac, location);
		return mappings.size();
	}
	
	/**
	 * check if the atom has at least one a functional group
	 * @param fg
	 * @param a
	 * @param ac
	 * @param location
	 * @return
	 */
	public boolean hasFunctionalGroup(FunctionalGroup fg, IAtom a, IAtomContainer ac, int location) {
		List<Map<IAtom, IAtom>> mappings = getUniqueFunctionalGroup(fg, a, ac, location);
		return mappings.size() > 0 ? true : false;
		
	}
	
	/**
	 * get the number of functional groups attached to the atom
	 * @param fg
	 * @param a
	 * @param ac
	 * @param location
	 * @return
	 */
	public int functionalGroupCount(FunctionalGroup fg, IAtom a, IAtomContainer ac, int location) {
		List<Map<IAtom, IAtom>> mappings = getUniqueFunctionalGroup(fg, a, ac, location);
		//System.out.println("mappings.size():" + mappings.size());
		return mappings.size();
		
	}
	
	/**
	 * get the number of SMARTS matches in the molecule
	 * @param smarts
	 * @param ac
	 * @param location
	 * @return
	 */
	public int smartsCount(String smarts, IAtomContainer ac, int location) {
		//System.out.println (smarts);
		List<Map<IAtom, IAtom>> mappings = getUniqueSmartsGroup(smarts, ac, location);
		return mappings.size();
	}
	
	/**
	 * get the number of SMARTS matches attached to the atom
	 * @param smarts
	 * @param a
	 * @param ac
	 * @param location
	 * @return
	 */
	public int smartsCount(String smarts, IAtom a, IAtomContainer ac, int location) {
		List<Map<IAtom, IAtom>> mappings = getUniqueSmartsGroup(smarts, a, ac, location);
		return mappings.size();
		
	}
	/**
	 * get the mapped atom with functional groups attached to the atom
	 * @param fg
	 * @param a
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getFunctionalGroup(FunctionalGroup fg, IAtom a, IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();
		String smarts;
		if (fg.equals(FunctionalGroup.FUNCTIONAL)) {
			for (FunctionalGroup fg2 : FunctionalGroup.values() ) {
				smarts = getSmarts(fg2);
				for (String exp : smarts.split("\\|\\|")) {
					Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
					if (ptrn.matches(ac))
						res.add(ptrn.matchAll(ac));
				}
			}
		}
		else {
			smarts = getSmarts(fg);
			for (String exp : smarts.split("\\|\\|")) {
				//exp = "[#6;Z1:999][O;H1]";
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				//System.out.println(exp + " "+ ptrn.matches(ac));
				//.unique is removed now (ie case disulfide, we want both carbon annotated as 999)
				if (ptrn.matches(ac))
					res.add(ptrn.matchAll(ac));
			}
		}
		
		return getUniqueMappings(res, a, location);
	}
	
	//used just for counting number of unique group (useful for stmt molecule has x fg)
	/**
	 * get the unique mapped atom with functional groups attached to the atom
	 * @param fg
	 * @param a
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getUniqueFunctionalGroup(FunctionalGroup fg, IAtom a, IAtomContainer ac, int location) {
		//System.out.println("here");
		List<Mappings> res = new ArrayList<Mappings>();
		String smarts;
		if (fg.equals(FunctionalGroup.FUNCTIONAL)) {
			for (FunctionalGroup fg2 : FunctionalGroup.values() ) {
				smarts = getSmarts(fg2);
				for (String exp : smarts.split("\\|\\|")) {
					Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
					if (ptrn.matches(ac))
						res.add(ptrn.matchAll(ac).uniqueAtoms());
				}
			}
		}
		else {
			//System.out.println("non functional");
			smarts = getSmarts(fg);
			for (String exp : smarts.split("\\|\\|")) {
				//System.out.println(fg.toString() + " exp "+exp);
				//exp = "[#6;Z1:999][O;H1]";
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac))
					res.add(ptrn.matchAll(ac).uniqueAtoms());
			}
		}
		return getUniqueMappings(res, a, location);
	}
	
	/**
	 * get the mapped atom with SMARTS attached to the atom
	 * @param smarts
	 * @param a
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getUniqueSmartsGroup(String smarts, IAtom a, IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();
			for (String exp : smarts.split("\\|\\|")) {
				//System.out.println(fg.toString() + " exp "+exp);
				//exp = "[#6;Z1:999][O;H1]";
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				//System.out.println(exp + " "+ ptrn.matches(ac));
				if (ptrn.matches(ac))
					res.add(ptrn.matchAll(ac).uniqueAtoms());
			}
		return getUniqueMappings(res, a, location);
	}
	
	/**
	 * check if there is at least one SMARTS match attached to the atom
	 * @param smarts
	 * @param a
	 * @param ac
	 * @param location
	 * @return
	 */
	public boolean hasFunctionalGroup(String smarts, IAtom a, IAtomContainer ac, int location) {
		return getFunctionalGroup(smarts, a, ac, location).size() > 0 ? true : false;
	}
	
	/**
	 * get the mapped atom with the SMARTS attached to the atom
	 * @param smarts
	 * @param a
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getFunctionalGroup(String smarts, IAtom a, IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();

		for (String exp : smarts.split("\\|\\|")) {
			Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
			if (ptrn.matches(ac))
				res.add(ptrn.matchAll(ac).uniqueAtoms());
		}
		return getUniqueMappings(res, a, location);
	}
	
	/**
	 * get the mapped atom with functional groups in the molecule
	 * @param fg
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getFunctionalGroup(FunctionalGroup fg, IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();
		String smarts;
		if (fg.equals(FunctionalGroup.FUNCTIONAL)) {
			for (FunctionalGroup fg2 : FunctionalGroup.values() ) {
				smarts = getSmarts(fg2);
				for (String exp : smarts.split("\\|\\|")) {
					Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
					if (ptrn.matches(ac))
						res.add(ptrn.matchAll(ac));
				}
			}
		}
		else {
			smarts = getSmarts(fg);
			for (String exp : smarts.split("\\|\\|")) {
				//exp = "[#6;Z1:999][O;H1]";
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				//System.out.println(exp + " "+ ptrn.matches(ac));
				//.unique is removed now (ie case disulfide, we want both carbon annotated as 999)
				if (ptrn.matches(ac))
					res.add(ptrn.matchAll(ac));
			}
		}
		
		return getUniqueMappings(res, location);
	}
	
	//used just for counting number of unique group (useful for stmt molecule has x fg)
	/**
	 * get the unique mapped atom with functional groups in the molecule
	 * @param fg
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getUniqueFunctionalGroup(FunctionalGroup fg, IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();
		String smarts;
		if (fg.equals(FunctionalGroup.FUNCTIONAL)) {
			for (FunctionalGroup fg2 : FunctionalGroup.values() ) {
				smarts = getSmarts(fg2);
				for (String exp : smarts.split("\\|\\|")) {
					Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
					if (ptrn.matches(ac))
						res.add(ptrn.matchAll(ac).uniqueAtoms());
				}
			}
		}
		else {
			smarts = getSmarts(fg);
			for (String exp : smarts.split("\\|\\|")) {
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac))
					res.add(ptrn.matchAll(ac).uniqueAtoms());
			}
		}
		return getUniqueMappings(res, location);
	}
	
	/**
	 * get the mapped atom with SMARTS in the molecule
	 * @param smarts
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getUniqueSmartsGroup(String smarts, IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();
			for (String exp : smarts.split("\\|\\|")) {
				//System.out.println(fg.toString() + " exp "+exp);
				//exp = "[#6;Z1:999][O;H1]";
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				//System.out.println(exp + " "+ ptrn.matches(ac));
				if (ptrn.matches(ac))
					res.add(ptrn.matchAll(ac).uniqueAtoms());
			}
		return getUniqueMappings(res, location);
	}
	
	/**
	 * check if there is at least one match with the SMARTS in the molecule
	 * @param smarts
	 * @param ac
	 * @param location
	 * @return
	 */
	public boolean hasFunctionalGroup(String smarts, IAtomContainer ac, int location) {
		return getFunctionalGroup(smarts, ac, location).size() > 0 ? true : false;
	}
	
	/**
	 * get the matched atoms with the SMARTS in the molecule
	 * @param smarts
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getFunctionalGroup(String smarts, IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();

		for (String exp : smarts.split("\\|\\|")) {
			Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
			if (ptrn.matches(ac))
				res.add(ptrn.matchAll(ac).uniqueAtoms());
		}
		return getUniqueMappings(res, location);
	}
	
	/**
	 * @param group
	 * @return
	 */
	public String getSmarts(FunctionalGroup group) {
		
		String acid= "[C:999](=O)[OH]";
		String acid_halide= "[C:999](=O)[F,Cl,Br,I]";
		String aldehyde= "[CH1:999](=O)";
		String ketone= "[#6:999](=O)([#6])([#6])";
		String amide1= "[#6:999](=O)[NH2]";
		String amide2= "[C;z2:999](=O)[NH1][C;z1]||[C;z2:999](=O)[NH1][c]";
		String amide3= "[#6:999](=O)[NH0]([C;Z1])[C;Z1]||[#6:999](=O)[NH0]([c])[C;Z1]||[#6:999](=O)[NH0]([c])[c]";
		String amide= amide1+ "||" + amide2+ "||" + amide3;
		String amine1="[C;Z1;X4:999][NH2;X3;Z0]||[c;X3:999][NH2;X3;Z0]";
		String amine2="[C;X4;Z1:999][NH1;X3;Z0][C;Z1;X4]||[c;X3:999][NH1;X3;Z0][C;Z1;X4]||[c;X3:999][NH1;X3;Z0][c;X3]||[c;X3:999][nH1;X3;Z0][c;X3]||[c;X3][NH1;X3;Z0][C;Z1;X4:999]";
		String amine3="[C;Z1;X4:999][NH0;X3;Z0]([C;X4;Z1])[C;X4;Z1]||[C;Z1;X4:999][NH0;X3;Z0]([c;X3])[C;X4;Z1]||[c;X3:999][NH0;X3;Z0]([cX3])[C;X4;Z1]||[c;X3:999][NH0;X3;Z0]([c;X3])[c;X3]||[c;X3:999][nH0;X3;Z0]([C;X4;Z1])[c;X3]||[c;X3:999][nH0;X3;Z0]([c;X3])[c;X3]";
		String amine= amine1+ "||" + amine2+ "||" + amine3;
		String anhydride= "[C:999](=O)O[C](=O)";
		String ester= "[C:999](=O)[O][C;!$(C=O)]||[C:999](=O)[O][c]";
		String thioester= "[C;X3:999](=O)[S;X2][C]";
		
			switch (group) {
			case ACETAL:
				return "[C;Z2:999]([O][C])([O][C])";
			case ACETYLENE:
				return  "[C:999]#[C]";
			case ACID:
				return acid;
			case ACID_HALIDE:
				return acid_halide;
			case ALCOHOL:
				return  "[#6;Z1:999][OH]";
			case ALDEHYDE:
				return  aldehyde;
			case ALLENE:
				return  "[C:999]=[C]=[C]";
			case AMIDE1:
				return  amide1;
			case AMIDE2:
				return  amide2;
			case AMIDE3:
				return  amide3;
			case AMIDE:
				return  amide;
			case AMIDZ:
				return  "[C:999][N!R][C!R]=[O]";
			case AMINE1:
				return  amine1;
			case AMINE2:
				return  amine2;
			case AMINE3:
				return  amine3;
			case AMINE:
				return  amine;
			case AMINE_OXIDE:
				return  "[C:999][N+;X4]([O-])([#6])[#6]";
			case ANHYDRIDE:
				return anhydride;
			case AZIDE:
				return "[#6:999]N=[N+]=[N-]";
			case AZIRIDINE:
				return "[C:999]1[N+0;X3]C1";
			case AZO:
				return "[#6,#1:999][N;R0;z1]=[N;R0;z1][#6,#1]";
			case BROMIDE:
				return "[#6:999][Br;v1]";
			case C_SULFONATE:
				return  "[#6:999][S+0;v6](=O)(=O)O";
			case CARBAMATE_C:
				return  "[C:999](N(C)(C))(=O)OC";
			case CARBAMATE_H:
				return  "[C!R:999](=O)(O)[NH1!R]";
			case CARBONIUM:
				return  "[#6:999][#6+;X3]";
			case CARBONYL:
				return  aldehyde+"||"+ketone;
			case CARBOXYL:
				return  acid + "||" + ester + "||" + amide + "||" + acid_halide + "||" +thioester + "||" + anhydride;
			case CHLORIDE:
				return  "[#6:999][Cl;v1]" ;
			case CYANO:
				return  "[C:999]#N";
			case DIAZO:
				return  "[C:999]=[N+;X2;v4]=[N-;X1;v2]";
			case DISULFIDE:
				return  "[#6:999][S;v2][S;v2][#6]" ;
			case DITHIOACETAL:
				return  "[CH1;Z2:999](S[*;Z1])S[*;Z1]";
			case DITHIOKETAL:
				return  "[C;Z2:999](S[*;Z1])(C)(C)S[*;Z1]";
			case ENAMINE:
				return  "[#6;z0:999]=[#6;z0][#7;z0]";
			case ENOL_ETHER:
				return  "[#6;z1:999](=[#6;z0])[#8;z0][#6]";
			case EPISULFIDE:
				return  "[C:999]1[S;v2]C1";
			case EPOXIDE:
				return  "[C:999]1[O]C1";
			case ESTER:
				return ester;
			case ESTERX:
				return  "[#6:999]O[C;!R]=O";
			case ETHER:
				return  "[#6:999]O[#6]";
			case FLUORIDE:
				return  "[#6:999][F;v1]";
			case FUNCTIONAL:
				return "";
			case GEM_DIHALIDE:
				return  "[CX4:999]([F,Cl,Br,I])[F,Cl,Br,I]";
			case GLYCOL:
				return  "[C;z1:999]([O;z0])[C;z1][O;z0]";
			case HALIDE:
				return  "[#6:999][Br;v1]||[#6:999][Cl;v1]||[#6:999][F;v1]||[#6:999][I;v1]";
			case HALOAMINE:
				return  "[#6:999][N;X3][F,Cl,Br,I]";
			case HALOHYDRIN:
				return  "[#6;z1:999]([F,Cl,Br,I])[#6][#8;z0]";
			case HEMIACETAL:
				return  "[CH1;Z2:999]([OH])[O;Z1]";
			case HYDRATE:
				return  "[C:999]([OH])[OH]";
			case HYDRAZONE:
				return  "[#6:999][#6]=NN";
			case HYDROXYLAMINE:
				return  "[#6:999][N][OH]";
			case IMINE:
				return  "[C:999]=N[#1,#6]";
			case IODIDE:
				return  "[#6:999][I;v1]";
			case ISOCYANATE:
				return  "[#6:999]N=C=O";
			case ISOCYANIDE:
				return  "[#6:999][N+]#[C-]";
			case KETONE:
				return  ketone;
			case LACTAM:
				return  "[C;z2;R:999](=O)[NH1;R;z0]";
			case LACTONE:
				return  "[#6;z2;R:999](=O)[#8;R;z0]";
			case METHYLENE:
				return  "[A:999]=[CH2]" ;
			case N_CARBAMATE:
				return  "[#6:999][N;z0]C(=O)O[C;z1]";
			case N_UREA:
				return  "[#6:999][N;Z0][C;Z3](=O)[N;Z0]";
			case N_UREA_C:
				return  "[#6:999][NH0;Z0][C;Z3](=O)[NH0;Z0]";
			case N_UREA_H:
				return  "[#6:999][N;!H0;Z0][C;Z3](=O)[N;Z0]||[#6:999][N;Z0][C;Z3](=O)[N;!H0;Z0]";
			case NITRITE:
				return  "[#6:999]ON=O";
			case NITRO:
				return  "[#6:999][N+](=O)[O-]";
			case NITROSO:
				return  "[#6:999]N=O";
			case O_CARBAMATE:
				return  "[#6:999]OC(=O)N";
			case O_CARBONATE:
				return  "[A:999]OC(=O)O[C;z1]";
			case O_SULFONATE:
				return  "[#6:999]O[S+0;v6](=O)(=O)O[#1,#6]";
			case OLEFIN:
				return  "[C:999]=C";
			case OXIME:
				return  "[#6:999]=N[OH]";
			case PEROXIDE:
				return  "[#6:999]OO[#6,#1]";
			case PHOSPHINE:
				return  "[#6:999]P([#6,#1])[#6,#1]";
			case PHOSPHONATE:
				return  "[#6:999]OP";
			case SELENIDE:
				return  "[#6:999][Se+0;v2][#6]";
			case SILYLENOLETHER:
				return  "[C:999](=C)O[Si]([CH3])([CH3])[CH3]";
			case SULFIDE:
				return  "[#6:999][S+0;v2][#6]";
			case SULFONE:
				return  "[#6:999][S+0;v6;X4](=O)(=O)[#6]";
			case SULFOXIDE:
				return  "[#6:999][S+0;v4](=O)[#6]";
			case THIOCYANATE:
				return  "[#6:999]SC#N";
			case THIOESTER:
				return thioester;
			case THIOL:
				return  "[#6;Z1:999][SH1;v2]";
			case TRIALKYLSILOXY:
				return  "[#6:999]O[Si;i0;X4]";
			case TRIALKYLSILYL:
				return  "[#6:999][Si;i0;X4]";
			case TRIHALIDE:
				return  "[CX4:999]([F,Cl,Br,I])([F,Cl,Br,I])[F,Cl,Br,I]";
			case VIC_DIHALIDE:
				return  "[#6:999]C([F,Cl,Br,I])C[F,Cl,Br,I]";
			case VINYLSILANE:
				return  "[C:999](=C)[Si;X4]";
			case ISOPROPYL:
				return  "[CH1:999]([CH3])[CH3]";
			case METHYL:
				 return  "[CH3:999]";
			case PHENYL:
				return  "[C:999]1=[CH1][CH1]=[CH1][CH1]=[CH1]1";
			case T_BUTYL:
				return  "[CH1:999]([CH3])([CH3])[CH3]";
			default:
				return null;
			}
	}
	
	
	/**
	 * @param group
	 * @return
	 */
	public String getSmartsOriginal(FunctionalGroup group) {
		
		String acid = "[*:999][C](=O)[OH]";
		String acid_halide = "[*:999][C](=O)[F,Cl,Br,I]";
		String aldehyde = "[*:999][CH1](=O)[#6]";
		String amide1 = "[*:999][C](=O)[NH2]";
		String amide2 = "[*:999][C](=O)[NH1]";
		String amide3 = "[*:999][C](=O)[NH0]";
		String amide = amide1 + "||" + amide2 + "||" + amide3;
		String amine1 = "[*:999][NH2;+0;X3]";
		String amine2 = "[*:999][NH1;+0;X3]";
		String amine3 = "[*:999][NH0;+0;X3]";
		String amine = amine1 + "||" + amine2 + "||" + amine3;
		String anhydride = "[A:999][C](=O)O[C](=O)[A]";
		String ester = "[*:999][C;!R;z2](=O)[O;!R][C;!R]";
		String ketone = "[#6][*:999](=O)[#6]";
		String thioester = "[*:999][C;z{2-3}](=O)S[#6]";
		
		String acid_centered = "[C:999](=O)[OH]";
		String acid_halide_centered = "[C:999](=O)[F,Cl,Br,I]";
		String aldehyde_centered = "[CH1:999](=O)";
		String amide1_centered = "[#6:999](=O)[NH2]";
		String amide2_centered = "[#6:999](=O)[NH1]";
		String amide3_centered = "[#6:999](=O)[NH0]";
		String amide_centered = amide1_centered + "||" + amide2_centered + "||" + amide3_centered;
		String amine1_centered = "[#6:999][NH2;+0;X3]";
		String amine2_centered = "[#6:999][NH1;+0;X3]";
		String amine3_centered = "[#6:999][NH0;+0;X3]";
		String amine_centered = amine1 + "||" + amine2 + "||" + amine3;
		String anhydride_centered = "[C:999](=O)O[C](=O)[A]";
		String ester_centered = "[#6:999][C;!R;z2:999](=O)[O;!R][C;!R]";
		String ketone_centered = "[#6:999](=O)[#6]";
		String thioester_centered = "[C;z{2-3}:999](=O)S[#6]";
			switch (group) {
			case ACETAL:
				return carbonCentered ? "[CH;Z2]([OH0])[OH0]" : "[*:999]([CH;Z2]([OH0])[OH0])";
			case ACETYLENE:
				return carbonCentered ? "[C:999]#[C]" : "[*:999][C]#[C]";
			case ACID:
				return carbonCentered ? acid_centered : acid;
			case ACID_HALIDE:
				return carbonCentered ? acid_halide_centered : acid_halide;
			case ALCOHOL:
				return carbonCentered ? "[#6;Z1:999][OH]" : "[*;Z1:999][OH]";
			case ALDEHYDE:
				return carbonCentered ? aldehyde_centered : aldehyde;
			case ALLENE:
				return carbonCentered ? "[C:999]=[C]=[C]" : "[*:999][C]=[C]=[C]";
			case AMIDE1:
				return carbonCentered ? amide1_centered : amide1;
			case AMIDE2:
				return carbonCentered ? amide2_centered: amide2;
			case AMIDE3:
				return carbonCentered ? amide3_centered : amide3;
			case AMIDE:
				return carbonCentered ? amide_centered : amide;
			case AMIDZ:
				return carbonCentered ? "[C:999][N!R][C!R]=[O]" : "[C:999][N!R][C!R]=[O]";
			case AMINE1:
				return carbonCentered ? amine1_centered : amine1;
			case AMINE2:
				return carbonCentered ? amine2_centered : amine2;
			case AMINE3:
				return carbonCentered ? amine3_centered : amine3;
			case AMINE:
				return carbonCentered ? amine_centered : amine;
			case AMINE_OXIDE:
				return carbonCentered ? "[*:999][N+;X4]([O-])([#6])[#6]" : "[*:999][N+;X4]([O-])([#6])[#6]";
			case ANHYDRIDE:
				return carbonCentered ? anhydride_centered : anhydride;
			case AZIDE:
				return carbonCentered ? "[#6:999]N=[N+]=[N-]" : "[*:999]N=[N+]=[N-]";
			case AZIRIDINE:
				return carbonCentered ? "[C:999]1[N+0;X3]C1" : "[*:999]C1[N+0;X3]C1";
			case AZO:
				return carbonCentered ? "[#6,#1:999][N;R0;z1]=[N;R0;z1][#6,#1]" : "[#6,#1:999][N;R0;z1]=[N;R0;z1][#6,#1]";
			case BROMIDE:
				return carbonCentered ? "[#6:999][Br;v1]" : "[*:999][Br;v1]";
			case C_SULFONATE:
				return carbonCentered ? "[#6:999][S+0;v6](=O)(=O)O" : "[*:999][S+0;v6](=O)(=O)O";
			case CARBAMATE_C:
				return carbonCentered ? "[C:999](N(C)(C))(=O)OC" : "[*:999][C](N(C)(C))(=O)OC";
			case CARBAMATE_H:
				return carbonCentered ? "[C!R:999](=O)(O)[NH1!R]" : "[*:999][C!R](=O)(O)[NH1!R]";
			case CARBONIUM:
				return carbonCentered ? "[#6:999][#6+;X3]" : "[*:999][#6+;X3]";
			case CARBONYL:
				return carbonCentered ? aldehyde_centered+"||"+ketone_centered : aldehyde+"||"+ketone;
			case CARBOXYL:
				return  carbonCentered ? 
						acid_centered + "||" + ester_centered + "||" + amide_centered + "||" + acid_halide_centered + "||" +thioester_centered + "||" + anhydride_centered :
						acid + "||" + ester + "||" + amide + "||" + acid_halide + "||" +thioester + "||" + anhydride;
			case CHLORIDE:
				return carbonCentered ? "[#6:999][Cl;v1]" : "[*:999][Cl;v1]";
			case CYANO:
				return carbonCentered ? "[C:999]#N" : "[*:999][C]#N";
			case DIAZO:
				return carbonCentered ? "[C:999]=[N+;X2;v4]=[N-;X1;v2]" : "[C:999]=[N+;X2;v4]=[N-;X1;v2]";
			case DISULFIDE:
				return carbonCentered ? "[#6:999][S;v2][S;v2][#6]" : "[*:999][S;v2][S;v2][#6]";
			case DITHIOACETAL:
				return carbonCentered ? "[CH1;Z2:999](S[*;Z1])S[*;Z1]" : "[*:999][CH1;Z2](S[*;Z1])S[*;Z1]";
			case DITHIOKETAL:
				return carbonCentered ? "[C;Z2:999](S[*;Z1])(C)(C)S[*;Z1]" : "[*:999][C;Z2](S[*;Z1])(C)(C)S[*;Z1]";
			case ENAMINE:
				return carbonCentered ? "[#6;z0:999]=[#6;z0][#7;z0]" : "[*:999][#6;z0]=[#6;z0][#7;z0]";
			case ENOL_ETHER:
				return carbonCentered ? "[#6;z1:999](=[#6;z0])[#8;z0][#6]" : "[*:999][#6;z1](=[#6;z0])[#8;z0][#6]";
			case EPISULFIDE:
				return carbonCentered ? "[C:999]1[S;v2]C1" : "[*:999]C1[S;v2]C1";
			case EPOXIDE:
				return carbonCentered ? "[C:999]1[O]C1" : "[*:999]C1[O]C1";
			case ESTER:
				return carbonCentered ? ester_centered : ester;
			case ESTERX:
				return carbonCentered ? "[#6:999]O[C;!R]=O" : "[#6:999]O[C;!R]=O";
			case ETHER:
				return carbonCentered ? "[#6:999]O[#6]" : "[#6:999]O[#6]";
			case FLUORIDE:
				return carbonCentered ? "[#6:999][F;v1]" : "[*:999][F;v1]";
			case FUNCTIONAL:
				return "";
			case GEM_DIHALIDE:
				return carbonCentered ? "[CX4:999]([F,Cl,Br,I])[F,Cl,Br,I]" : "[CX4:999]([F,Cl,Br,I])[F,Cl,Br,I]";
			case GLYCOL:
				return carbonCentered ? "[C;z1:999]([O;z0])[C;z1][O;z0]": "[*:999][C;z1]([O;z0])[C;z1][O;z0]";
			case HALIDE:
				return carbonCentered ? "[#6:999][Br;v1]||[#6:999][Cl;v1]||[#6:999][F;v1]||[#6:999][I;v1]" :
					"[*:999][Br;v1]||[*:999][Cl;v1]||[*:999][F;v1]||[*:999][I;v1]";
			case HALOAMINE:
				return carbonCentered ? "[#6:999][N;X3][F,Cl,Br,I]" : "[*:999][N;X3][F,Cl,Br,I]";
			case HALOHYDRIN:
				return carbonCentered ? "[#6;z1:999]([F,Cl,Br,I])[#6][#8;z0]" : "[*:999][#6;z1]([F,Cl,Br,I])[#6][#8;z0]";
			case HEMIACETAL:
				return carbonCentered ? "[CH1;Z2:999]([OH])[O;Z1]" : "[*:999][CH1;Z2]([OH])[O;Z1]";
			case HYDRATE:
				return carbonCentered ? "[C:999]([OH])[OH]" : "[*:999]C([OH])[OH]";
			case HYDRAZONE:
				return carbonCentered ? "[#6:999][#6]=NN" : "[#6:999][#6]=NN";
			case HYDROXYLAMINE:
				return carbonCentered ? "[#6:999][N;R0][OH]" : "[*:999][N;R0][OH]";
			case IMINE:
				return carbonCentered ? "[C:999]=N[#1,#6]" : "[*:999][C]=N[#1,#6]";
			case IODIDE:
				return carbonCentered ? "[#6:999][I;v1]" : "[*:999][I;v1]";
			case ISOCYANATE:
				return carbonCentered ? "[#6:999]N=C=O" : "[*:999]N=C=O";
			case ISOCYANIDE:
				return carbonCentered ? "[#6:999][N+]#[C-]" : "[*:999][N+]#[C-]";
			case KETONE:
				return carbonCentered ? ketone_centered : ketone;
			case LACTAM:
				return carbonCentered ? "[C;z2;R:999](=O)[NH1;R;z0]" : "[*:999][C;z2;R](=O)[NH1;R;z0]";
			case LACTONE:
				return carbonCentered ? "[#6;z2;R:999](=O)[#8;R;z0]" : "[*:999][#6;z2;R](=O)[#8;R;z0]";
			case METHYLENE:
				return carbonCentered ? "[A:999]=[CH2]" : "[A:999]=[CH2]";
			case N_CARBAMATE:
				return carbonCentered ? "[#6:999][N;z0]C(=O)O[C;z1]" : "[*:999][N;z0]C(=O)O[C;z1]";
			case N_UREA:
				return carbonCentered ? "[#6:999][N;Z0][C;Z3](=O)[N;Z0]" : "[*:999][N;Z0][C;Z3](=O)[N;Z0]";
			case N_UREA_C:
				return carbonCentered ? "[#6:999][NH0;Z0][C;Z3](=O)[NH0;Z0]" : "[*:999][NH0;Z0][C;Z3](=O)[NH0;Z0]";
			case N_UREA_H:
				return carbonCentered ? "[#6:999][N;!H0;Z0][C;Z3](=O)[N;Z0]||[#6:999][N;Z0][C;Z3](=O)[N;!H0;Z0]" :
					"[*:999][N;!H0;Z0][C;Z3](=O)[N;Z0]||[*:999][N;Z0][C;Z3](=O)[N;!H0;Z0]";
			case NITRITE:
				return carbonCentered ? "[#6:999]ON=O" : "[*:999]ON=O";
			case NITRO:
				return carbonCentered ? "[#6:999][N+](=O)[O-]" : "[*:999][N+](=O)[O-]";
			case NITROSO:
				return carbonCentered ? "[#6:999]N=O" : "[*:999]N=O";
			case O_CARBAMATE:
				return carbonCentered ? "[#6:999]OC(=O)N" : "[*:999]OC(=O)N";
			case O_CARBONATE:
				return carbonCentered ? "[A:999]OC(=O)O[C;z1]" : "[A:999]OC(=O)O[C;z1]";
			case O_SULFONATE:
				return carbonCentered ? "[#6:999]O[S+0;v6](=O)(=O)O[#1,#6]" : "[*:999]O[S+0;v6](=O)(=O)O[#1,#6]";
			case OLEFIN:
				return carbonCentered ? "[C:999]=C" : "[A:999]C=C";
			case OXIME:
				return carbonCentered ? "[#6:999]=N[OH]" : "[*:999]=N[OH]";
			case PEROXIDE:
				return carbonCentered ? "[#6:999]OO[#6,#1]" : "[*:999]OO[#6,#1]";
			case PHOSPHINE:
				return carbonCentered ? "[#6:999]P([#6,#1])[#6,#1]" : "[*:999]P([#6,#1])[#6,#1]";
			case PHOSPHONATE:
				return carbonCentered ? "[#6:999]OP" : "[*:999]OP";
			case SELENIDE:
				return carbonCentered ? "[#6:999][Se+0;v2][#6]" : "[*:999][Se+0;v2][#6]";
			case SILYLENOLETHER:
				return carbonCentered ? "[C:999](=C)O[Si]([CH3])([CH3])[CH3]" : "[*:999]C(=C)O[Si]([CH3])([CH3])[CH3]";
			case SULFIDE:
				return carbonCentered ? "[#6:999][S+0;v2][#6]" : "[*:999][S+0;v2][#6]";
			case SULFONE:
				return carbonCentered ? "[#6:999][S+0;v6;X4](=O)(=O)[#6]" : "[*:999][S+0;v6;X4](=O)(=O)[#6]";
			case SULFOXIDE:
				return carbonCentered ? "[#6:999][S+0;v4](=O)[#6]" : "[*:999][S+0;v4](=O)[#6]";
			case THIOCYANATE:
				return carbonCentered ? "[#6:999]SC#N" : "[*:999]SC#N";
			case THIOESTER:
				return carbonCentered ? thioester_centered : thioester;
			case THIOL:
				return carbonCentered ? "[#6;Z1:999][SH1;v2]" : "[*;Z1:999][SH1;v2]";
			case TRIALKYLSILOXY:
				return carbonCentered ? "[#6:999]O[Si;i0;X4]" : "[*:999]O[Si;i0;X4]";
			case TRIALKYLSILYL:
				return carbonCentered ? "[#6:999][Si;i0;X4]" : "[*:999][Si;i0;X4]";
			case TRIHALIDE:
				return carbonCentered ? "[CX4:999]([F,Cl,Br,I])([F,Cl,Br,I])[F,Cl,Br,I]" : "[CX4:999]([F,Cl,Br,I])([F,Cl,Br,I])[F,Cl,Br,I]";
			case VIC_DIHALIDE:
				return carbonCentered ? "[#6:999]C([F,Cl,Br,I])C[F,Cl,Br,I]" : "[*:999]C([F,Cl,Br,I])C[F,Cl,Br,I]";
			case VINYLSILANE:
				return carbonCentered ? "[C:999](=C)[Si;X4]" : "[*:999]C(=C)[Si;X4]";
			case ISOPROPYL:
				return carbonCentered ? "[CH1:999]([CH3])[CH3]" : "[*:999][CH1]([CH3])[CH3]";
			case METHYL:
				return carbonCentered ? "[CH3:999]" : "[*:999][CH3]";
			case PHENYL:
				return carbonCentered ? "[C:999]1=[CH1][CH1]=[CH1][CH1]=[CH1]1" : "[*:999][C]1=[CH1][CH1]=[CH1][CH1]=[CH1]1";
			case T_BUTYL:
				return carbonCentered ? "[CH1:999]([CH3])([CH3])[CH3]" : "[*:999][CH1]([CH3])([CH3])[CH3]";
			default:
				return null;
			}
	}
	/**
	 * @param a
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getDonating(IAtom a, IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();
		for (FunctionalGroup fun : donating) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac)) {
					Mappings mapping = ptrn.matchAll(ac).uniqueAtoms();
					res.add(mapping);
				}
			}
		}

		//vinyl_D
		for (FunctionalGroup fun : donating) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				String newExp = "";
				if (carbonCentered) {
					newExp = "[C:999]=C" + exp.replace(":999", "");
				}
				else {
					newExp = "[C:999]C=C" + exp.replace(":999", "");
				}
				Pattern ptrn = SmartsPattern.create(newExp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac)) {
					Mappings mapping = ptrn.matchAll(ac).uniqueAtoms();
					res.add(mapping);
				}
			}
		}
		

		return getUniqueMappings(res, a, location);
	}

	/**
	 * @param a
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getExpandableWithDrawing(IAtom a, IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();
		for (FunctionalGroup fun : expandable_withdrawing) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac)) { 
					Mappings mapping = ptrn.matchAll(ac).uniqueAtoms();
					res.add(mapping);
				}
			}
		}

		//vinyl_W
		for (FunctionalGroup fun : expandable_withdrawing) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				String newExp = "";
				if (carbonCentered) {
					newExp = "[C:999]=C" + exp.replace(":999", "");
				}
				else {
					newExp = "[C:999]C=C" + exp.replace(":999", "");
				}
				Pattern ptrn = SmartsPattern.create(newExp, Smarts.FLAVOR_CACTVS);
				//System.out.println(exp + " X "+ptrn.matches(ac) );
				if (ptrn.matches(ac)) {
					Mappings mapping = ptrn.matchAll(ac).uniqueAtoms();
					res.add(mapping);
				}
			}
		}
		return getUniqueMappings(res, a, location);
	}

	/**
	 * @param a
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getGoodLeaving(IAtom a, IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();

		for (FunctionalGroup fun : good_leaving) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac)) {
					Mappings mapping = ptrn.matchAll(ac).uniqueAtoms();
					res.add(mapping);
				}
			}
		}

		return getUniqueMappings(res, a, location);
	}

	/**
	 * @param a
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getLeaving(IAtom a, IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();

		for (FunctionalGroup fun : leaving) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac)) {
					Mappings mapping = ptrn.matchAll(ac).uniqueAtoms();
					res.add(mapping);
				}
			}
		}
		return getUniqueMappings(res, a, location);
	}

	/**
	 * @param a
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getNonExpandableWithDrawing(IAtom a, IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();

		for (FunctionalGroup fun : non_expandable_withdrawing) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				//System.out.println(exp + " yN "+ptrn.matches(ac) );
				if (ptrn.matches(ac)) {
					Mappings mapping = ptrn.matchAll(ac).uniqueAtoms();
					res.add(mapping);
				}
			}
		}
		
		return getUniqueMappings(res, a, location);
	}

	/**
	 * @param a
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getWithDrawing(IAtom a, IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();
		for (FunctionalGroup fun : withdrawing) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				//System.out.println(exp + " w "+ptrn.matches(ac) );
				if (ptrn.matches(ac)) {
					Mappings mapping = ptrn.matchAll(ac).uniqueAtoms();
					res.add(mapping);
				}
			}
		}

		//vinyl_W
		for (FunctionalGroup fun : expandable_withdrawing) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				String newExp = "";
				if (carbonCentered) {
					newExp = "[C:999]=C" + exp.replace(":999", "");
				}
				else {
					newExp = "[C:999]C=C" + exp.replace(":999", "");
				}
				Pattern ptrn = SmartsPattern.create(newExp, Smarts.FLAVOR_CACTVS);
				//System.out.println(exp + " we "+ptrn.matches(ac) );
				if (ptrn.matches(ac)) {
					Mappings mapping = ptrn.matchAll(ac).uniqueAtoms();
					res.add(mapping);
				}
			}
		}
		return getUniqueMappings(res, a, location);
		
		
		/*List<Map<IAtom, IAtom>> res = new ArrayList<Map<IAtom, IAtom>>();
		
		res.addAll(getNonExpandableWithDrawing(a, ac, location));
		res.addAll(getExpandableWithDrawing(a, ac, location));

		return res;*/
	}

	//C=C-D [D = withdrawing group, q.v.]
	/**
	 * @param a
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getVinylD(IAtom a, IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();
		
		for (FunctionalGroup fun : donating) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				String newExp = "";
				if (carbonCentered) {
					newExp = "[C:999]=C" + exp.replace(":999", "");
				}
				else {
					newExp = "[C:999]C=C" + exp.replace(":999", "");
				}
				Pattern ptrn = SmartsPattern.create(newExp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac)) {
					Mappings mapping = ptrn.matchAll(ac).uniqueAtoms();
					res.add(mapping);
				}
			}
		}
		
		/*for (Mappings mapping : temp) {
			IAtomContainer query = mapping.getQuery();
			//add the 2 carbons and the 2 bonds in the withdrawing pattern
			IAtom a1 = new Atom("C");
			//a1.setIsAromatic(false);
			//a1.setIsInRing(false);
			IAtom a2 = new Atom("C");
			//a2.setIsAromatic(false);
			//a2.setIsInRing(false);
			IBond b1 = new Bond();
			b1.setAtom(a1, 0);
			b1.setAtom(a2, 1);
			b1.setOrder(IBond.Order.DOUBLE);
			query.addAtom(a1);
			query.addAtom(a2);
			query.addBond(b1);
			//b1.setIsAromatic(false);
			//b1.setIsInRing(false);
			IAtomContainer target = mapping.getTarget();
			Iterator<int[]> itr = mapping.getIterable().iterator();
			List<int[]> temp2 = new ArrayList<int[]>();
			while (itr.hasNext()) {
				int[] aam = itr.next();
				boolean break1 = false;
				for (int i = 0; i < aam.length; i++) {
					IAtom centerSmarts = query.getAtom(i);
					if (centerSmarts.getProperty(CDKConstants.ATOM_ATOM_MAPPING) != null) {
						IAtom center = target.getAtom(aam[i]);
						for (IBond alphab : ac.getConnectedBondsList(center)) {
							if (alphab.getOrder().equals(IBond.Order.SINGLE) && alphab.getOther(center).getSymbol().equals("C")) {
								IAtom alpha = alphab.getOther(center);
								for (IBond betab : ac.getConnectedBondsList(alpha)) {
									if (betab.getOrder().equals(IBond.Order.DOUBLE) && betab.getOther(center).getSymbol().equals("C")) {
										IAtom beta = alphab.getOther(alpha);
										centerSmarts.setProperty(CDKConstants.ATOM_ATOM_MAPPING, 998);
										beta.setProperty(CDKConstants.ATOM_ATOM_MAPPING, 999);
										temp2.add(aam);
										break1 = true;
										//add bond between center atom in withdrawing and carbon in vinyl C-D
										IBond b2 = new Bond();
										b2.setAtom(a2, 0);
										b2.setAtom(centerSmarts, 1);
										b2.setOrder(IBond.Order.SINGLE);
										//b2.setIsAromatic(false);
										//b2.setIsInRing(false);
										query.addBond(b2);
										int[] newMapping = new int[aam.length + 2];
									    for(int j = 0; j < aam.length; j++){
									    	newMapping[j] = aam[j];
									    }
									    newMapping[aam.length+1] = ac.indexOf(beta);
									    newMapping[aam.length+2] = ac.indexOf(alpha);
									    temp2.add(newMapping);
										break;
									}
								}
								if (break1) break;
							}
						}
						if (break1) break;;
					}
				}
			}
			Mappings newMappings = new Mappings(query, target, temp2);
			res.add(newMappings);
		}*/
		return getUniqueMappings(res, a, location);
	}

	//C=C-D [D = donating group, q.v.]
	/**
	 * @param a
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getVinylW(IAtom a, IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();
		
		for (FunctionalGroup fun : expandable_withdrawing) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				String newExp = "";
				if (carbonCentered) {
					newExp = "[C:999]=C" + exp.replace(":999", "");
				}
				else {
					newExp = "[C:999]C=C" + exp.replace(":999", "");
				}
				Pattern ptrn = SmartsPattern.create(newExp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac)) {
					Mappings mapping = ptrn.matchAll(ac).uniqueAtoms();
					res.add(mapping);
				}
			}
		}
		
		/*for (Mappings mapping : temp) {
			IAtomContainer query = mapping.getQuery();
			//add the 2 carbons and the 2 bonds in the donating pattern
			IAtom a1 = new Atom("C");
			//a1.setIsAromatic(false);
			//a1.setIsInRing(false);
			IAtom a2 = new Atom("C");
			//a2.setIsAromatic(false);
			//a2.setIsInRing(false);
			IBond b1 = new Bond();
			b1.setAtom(a1, 0);
			b1.setAtom(a2, 1);
			b1.setOrder(IBond.Order.DOUBLE);
			query.addAtom(a1);
			query.addAtom(a2);
			query.addBond(b1);
			//b1.setIsAromatic(false);
			//b1.setIsInRing(false);
			IAtomContainer target = mapping.getTarget();
			Iterator<int[]> itr = mapping.getIterable().iterator();
			List<int[]> temp2 = new ArrayList<int[]>();
			while (itr.hasNext()) {
				int[] aam = itr.next();
				boolean break1 = false;
				for (int i = 0; i < aam.length; i++) {
					IAtom centerSmarts = query.getAtom(i);
					if (centerSmarts.getProperty(CDKConstants.ATOM_ATOM_MAPPING) != null) {
						IAtom center = target.getAtom(aam[i]);
						for (IBond alphab : ac.getConnectedBondsList(center)) {
							if (alphab.getOrder().equals(IBond.Order.SINGLE) && alphab.getOther(center).getSymbol().equals("C")) {
								IAtom alpha = alphab.getOther(center);
								for (IBond betab : ac.getConnectedBondsList(alpha)) {
									if (betab.getOrder().equals(IBond.Order.DOUBLE) && betab.getOther(center).getSymbol().equals("C")) {
										IAtom beta = alphab.getOther(alpha);
										centerSmarts.setProperty(CDKConstants.ATOM_ATOM_MAPPING, 998);
										beta.setProperty(CDKConstants.ATOM_ATOM_MAPPING, 999);
										temp2.add(aam);
										break1 = true;
										//add bond between center atom in donating and carbon in vinyl C-D
										IBond b2 = new Bond();
										b2.setAtom(a2, 0);
										b2.setAtom(centerSmarts, 1);
										b2.setOrder(IBond.Order.SINGLE);
										//b2.setIsAromatic(false);
										//b2.setIsInRing(false);
										query.addBond(b2);
										int[] newMapping = new int[aam.length + 2];
									    for(int j = 0; j < aam.length; j++){
									    	newMapping[j] = aam[j];
									    }
									    newMapping[aam.length+1] = ac.indexOf(beta);
									    newMapping[aam.length+2] = ac.indexOf(alpha);
									    temp2.add(newMapping);
										break;
									}
								}
								if (break1) break;
							}
						}
						if (break1) break;;
					}
				}
			}
			Mappings newMappings = new Mappings(query, target, temp2);
			res.add(newMappings);
		}*/
		return getUniqueMappings(res, a, location);
	}

	/**
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getDonating(IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();
		for (FunctionalGroup fun : donating) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac))
					res.add(ptrn.matchAll(ac).uniqueAtoms());
			}
		}

		//vinyl_D
		for (FunctionalGroup fun : donating) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				String newExp = "";
				if (carbonCentered) {
					newExp = "[C:999]=C" + exp.replace(":999", "");
				}
				else {
					newExp = "[C:999]C=C" + exp.replace(":999", "");
				}
				Pattern ptrn = SmartsPattern.create(newExp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac))
					res.add(ptrn.matchAll(ac).uniqueAtoms());
			}
		}
		

		return getUniqueMappings(res, location);
	}

	/**
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getExpandableWithDrawing(IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();

		for (FunctionalGroup fun : expandable_withdrawing) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac)) {
					res.add(ptrn.matchAll(ac).uniqueAtoms());
					}
			}
		}

		//vinyl_W
		for (FunctionalGroup fun : expandable_withdrawing) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				String newExp = "";
				if (carbonCentered) {
					newExp = "[C:999]=C" + exp.replace(":999", "");
				}
				else {
					newExp = "[C:999]C=C" + exp.replace(":999", "");
				}
				Pattern ptrn = SmartsPattern.create(newExp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac)) {
					res.add(ptrn.matchAll(ac).uniqueAtoms());
					}
			}
		}
		return getUniqueMappings(res, location);
	}

	/**
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getGoodLeaving(IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();

		for (FunctionalGroup fun : good_leaving) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac))
					res.add(ptrn.matchAll(ac).uniqueAtoms());
			}
		}

		return getUniqueMappings(res, location);
	}

	/**
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getLeaving(IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();

		for (FunctionalGroup fun : leaving) { 
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac)){
					res.add(ptrn.matchAll(ac).uniqueAtoms());
				}
			}
		}
		return getUniqueMappings(res, location);
	}

	/**
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getNonExpandableWithDrawing(IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();

		for (FunctionalGroup fun : non_expandable_withdrawing) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac))
					res.add(ptrn.matchAll(ac).uniqueAtoms());
			}
		}
		return getUniqueMappings(res, location);
	}

	/**
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getWithDrawing(IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();

		for (FunctionalGroup fun : withdrawing) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac)) 
					res.add(ptrn.matchAll(ac).uniqueAtoms());
			}
		}
		
		//vinyl_W
		for (FunctionalGroup fun : expandable_withdrawing) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				String newExp = "";
				if (carbonCentered) {
					newExp = "[C:999]=C" + exp.replace(":999", "");
				}
				else {
					newExp = "[C:999]C=C" + exp.replace(":999", "");
				}
				Pattern ptrn = SmartsPattern.create(newExp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac)) {
					res.add(ptrn.matchAll(ac).uniqueAtoms());
				}
			}
		}
		return getUniqueMappings(res, location);
		
	
		/*List<Map<IAtom, IAtom>> res = new ArrayList<Map<IAtom, IAtom>>();

		res.addAll(getNonExpandableWithDrawing(ac, location));
		res.addAll(getExpandableWithDrawing(ac, location));

		return res;*/
	}
	
	/**
	 * @param ac
	 * @param location
	 * @return
	 */
	public boolean hasWithDrawing(IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();

		for (FunctionalGroup fun : withdrawing) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac))
					res.add(ptrn.matchAll(ac).uniqueAtoms());
			}
		}
		
		//vinyl_W
		for (FunctionalGroup fun : expandable_withdrawing) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				String newExp = "";
				if (carbonCentered) {
					newExp = "[C:999]=C" + exp.replace(":999", "");
				}
				else {
					newExp = "[C:999]C=C" + exp.replace(":999", "");
				}
				Pattern ptrn = SmartsPattern.create(newExp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac)) {
					res.add(ptrn.matchAll(ac).uniqueAtoms());
				}
			}
		}
		return getUniqueMappings(res, location).size() > 0 ? true : false;
		
		
		
		/*List<Map<IAtom, IAtom>> res = new ArrayList<Map<IAtom, IAtom>>();

		res.addAll(getNonExpandableWithDrawing(ac, location));
		res.addAll(getExpandableWithDrawing(ac, location));
		
		return res.size() > 0 ? true : false;*/
	}
	
	
	/**
	 * @param ac, atom a
	 * @param location
	 * @return
	 */
	public boolean hasWithDrawing(IAtom a, IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();

		for (FunctionalGroup fun : withdrawing) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				
				Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
				//System.out.println(exp + " tN "+ptrn.matches(ac) );
				if (ptrn.matches(ac)) {
					Mappings mapping = ptrn.matchAll(ac).uniqueAtoms();
					res.add(mapping);
				}
			}
		}

		//vinyl_W
		for (FunctionalGroup fun : expandable_withdrawing) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				String newExp = "";
				if (carbonCentered) {
					newExp = "[C:999]=C" + exp.replace(":999", "");
				}
				else {
					newExp = "[C:999]C=C" + exp.replace(":999", "");
				}
				Pattern ptrn = SmartsPattern.create(newExp, Smarts.FLAVOR_CACTVS);
				//System.out.println(exp + " X "+ptrn.matches(ac) );
				if (ptrn.matches(ac)) {
					Mappings mapping = ptrn.matchAll(ac).uniqueAtoms();
					res.add(mapping);
				}
			}
		}
		return getUniqueMappings(res, a, location).size() > 0 ? true : false;
		
		
		/*List<Map<IAtom, IAtom>> res = new ArrayList<Map<IAtom, IAtom>>();

		res.addAll(getNonExpandableWithDrawing(a, ac, location));
		res.addAll(getExpandableWithDrawing(a, ac, location));

		return res.size() > 0 ? true : false;*/
	}


	//C=C-D [D = withdrawing group, q.v.]
	/**
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getVinylD(IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();
		//List<Mappings> temp = getWithDrawing(ac);
		
		for (FunctionalGroup fun : donating) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				String newExp = "";
				if (carbonCentered) {
					newExp = "[C:999]=C" + exp.replace(":999", "");
				}
				else {
					newExp = "[C:999]C=C" + exp.replace(":999", "");
				}
				Pattern ptrn = SmartsPattern.create(newExp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac))
					res.add(ptrn.matchAll(ac).uniqueAtoms());
			}
		}
		
		/*for (Mappings mapping : temp) {
			IAtomContainer query = mapping.getQuery();
			//add the 2 carbons and the 2 bonds in the withdrawing pattern
			IAtom a1 = new Atom("C");
			//a1.setIsAromatic(false);
			//a1.setIsInRing(false);
			IAtom a2 = new Atom("C");
			//a2.setIsAromatic(false);
			//a2.setIsInRing(false);
			IBond b1 = new Bond();
			b1.setAtom(a1, 0);
			b1.setAtom(a2, 1);
			b1.setOrder(IBond.Order.DOUBLE);
			query.addAtom(a1);
			query.addAtom(a2);
			query.addBond(b1);
			//b1.setIsAromatic(false);
			//b1.setIsInRing(false);
			IAtomContainer target = mapping.getTarget();
			Iterator<int[]> itr = mapping.getIterable().iterator();
			List<int[]> temp2 = new ArrayList<int[]>();
			while (itr.hasNext()) {
				int[] aam = itr.next();
				boolean break1 = false;
				for (int i = 0; i < aam.length; i++) {
					IAtom centerSmarts = query.getAtom(i);
					if (centerSmarts.getProperty(CDKConstants.ATOM_ATOM_MAPPING) != null) {
						IAtom center = target.getAtom(aam[i]);
						for (IBond alphab : ac.getConnectedBondsList(center)) {
							if (alphab.getOrder().equals(IBond.Order.SINGLE) && alphab.getOther(center).getSymbol().equals("C")) {
								IAtom alpha = alphab.getOther(center);
								for (IBond betab : ac.getConnectedBondsList(alpha)) {
									if (betab.getOrder().equals(IBond.Order.DOUBLE) && betab.getOther(center).getSymbol().equals("C")) {
										IAtom beta = alphab.getOther(alpha);
										centerSmarts.setProperty(CDKConstants.ATOM_ATOM_MAPPING, 998);
										beta.setProperty(CDKConstants.ATOM_ATOM_MAPPING, 999);
										temp2.add(aam);
										break1 = true;
										//add bond between center atom in withdrawing and carbon in vinyl C-D
										IBond b2 = new Bond();
										b2.setAtom(a2, 0);
										b2.setAtom(centerSmarts, 1);
										b2.setOrder(IBond.Order.SINGLE);
										//b2.setIsAromatic(false);
										//b2.setIsInRing(false);
										query.addBond(b2);
										int[] newMapping = new int[aam.length + 2];
									    for(int j = 0; j < aam.length; j++){
									    	newMapping[j] = aam[j];
									    }
									    newMapping[aam.length+1] = ac.indexOf(beta);
									    newMapping[aam.length+2] = ac.indexOf(alpha);
									    temp2.add(newMapping);
										break;
									}
								}
								if (break1) break;
							}
						}
						if (break1) break;;
					}
				}
			}
			Mappings newMappings = new Mappings(query, target, temp2);
			res.add(newMappings);
		}*/
		return getUniqueMappings(res, location);
	}

	//C=C-D [D = donating group, q.v.]
	/**
	 * @param ac
	 * @param location
	 * @return
	 */
	public List<Map<IAtom, IAtom>> getVinylW(IAtomContainer ac, int location) {
		List<Mappings> res = new ArrayList<Mappings>();
		//List<Mappings> temp = getDonating(ac);
		
		for (FunctionalGroup fun : expandable_withdrawing) {
			String smarts = getSmarts(fun);
			for (String exp : smarts.split("\\|\\|")) {
				String newExp = "";
				if (carbonCentered) {
					newExp = "[C:999]=C" + exp.replace(":999", "");
				}
				else {
					newExp = "[C:999]C=C" + exp.replace(":999", "");
				}
				Pattern ptrn = SmartsPattern.create(newExp, Smarts.FLAVOR_CACTVS);
				if (ptrn.matches(ac))
					res.add(ptrn.matchAll(ac).uniqueAtoms());
			}
		}
		
		/*for (Mappings mapping : temp) {
			IAtomContainer query = mapping.getQuery();
			//add the 2 carbons and the 2 bonds in the donating pattern
			IAtom a1 = new Atom("C");
			//a1.setIsAromatic(false);
			//a1.setIsInRing(false);
			IAtom a2 = new Atom("C");
			//a2.setIsAromatic(false);
			//a2.setIsInRing(false);
			IBond b1 = new Bond();
			b1.setAtom(a1, 0);
			b1.setAtom(a2, 1);
			b1.setOrder(IBond.Order.DOUBLE);
			query.addAtom(a1);
			query.addAtom(a2);
			query.addBond(b1);
			//b1.setIsAromatic(false);
			//b1.setIsInRing(false);
			IAtomContainer target = mapping.getTarget();
			Iterator<int[]> itr = mapping.getIterable().iterator();
			List<int[]> temp2 = new ArrayList<int[]>();
			while (itr.hasNext()) {
				int[] aam = itr.next();
				boolean break1 = false;
				for (int i = 0; i < aam.length; i++) {
					IAtom centerSmarts = query.getAtom(i);
					if (centerSmarts.getProperty(CDKConstants.ATOM_ATOM_MAPPING) != null) {
						IAtom center = target.getAtom(aam[i]);
						for (IBond alphab : ac.getConnectedBondsList(center)) {
							if (alphab.getOrder().equals(IBond.Order.SINGLE) && alphab.getOther(center).getSymbol().equals("C")) {
								IAtom alpha = alphab.getOther(center);
								for (IBond betab : ac.getConnectedBondsList(alpha)) {
									if (betab.getOrder().equals(IBond.Order.DOUBLE) && betab.getOther(center).getSymbol().equals("C")) {
										IAtom beta = alphab.getOther(alpha);
										centerSmarts.setProperty(CDKConstants.ATOM_ATOM_MAPPING, 998);
										beta.setProperty(CDKConstants.ATOM_ATOM_MAPPING, 999);
										temp2.add(aam);
										break1 = true;
										//add bond between center atom in donating and carbon in vinyl C-D
										IBond b2 = new Bond();
										b2.setAtom(a2, 0);
										b2.setAtom(centerSmarts, 1);
										b2.setOrder(IBond.Order.SINGLE);
										//b2.setIsAromatic(false);
										//b2.setIsInRing(false);
										query.addBond(b2);
										int[] newMapping = new int[aam.length + 2];
									    for(int j = 0; j < aam.length; j++){
									    	newMapping[j] = aam[j];
									    }
									    newMapping[aam.length+1] = ac.indexOf(beta);
									    newMapping[aam.length+2] = ac.indexOf(alpha);
									    temp2.add(newMapping);
										break;
									}
								}
								if (break1) break;
							}
						}
						if (break1) break;;
					}
				}
			}
			Mappings newMappings = new Mappings(query, target, temp2);
			res.add(newMappings);
		}*/
		return getUniqueMappings(res, location);
	}

	/**
	 * @param ac
	 * @return
	 */
	public List<Mappings> getProtected(IAtomContainer ac) {
		List<Mappings> res = new ArrayList<Mappings>();
		// TODO define SMARTS for PROTECTED:
		String[] smarts = new String[0];
		// smarts[0] = ""
		for (String exp : smarts) {
			Pattern ptrn = SmartsPattern.create(exp, Smarts.FLAVOR_CACTVS);
			if (ptrn.matches(ac))
				res.add(ptrn.matchAll(ac).uniqueAtoms());
		}
		return res;
	}

	/**
	 * @param ac
	 * @return
	 */
	public List<List<IAtom>> getParticipating(IAtomContainer ac) {
		List<List<IAtom>> res = new ArrayList<List<IAtom>>();

		for (IAtom atom : ac.atoms()) {
			if ((boolean) atom.getProperty(SliceConstants.PARTICIPATING)) {
				GraphTransversal gt = new GraphTransversal();
				Set<IAtom> sphere = new HashSet<IAtom>();
				Set<IAtom> result = new HashSet<IAtom>();
				gt.getGroupWithSpecificProperty(sphere, SliceConstants.PARTICIPATING, ac, result);
				res.add(new ArrayList<IAtom>(result));
			}
		}

		return res;
	}

	/**
	 * @return
	 */
	public boolean isCarbonCentered() {
		return carbonCentered;
	}

	/**
	 * @param carbonCentered
	 */
	public void setCarbonCentered(boolean carbonCentered) {
		this.carbonCentered = carbonCentered;
	}
	
	/**
	 * @param mappings
	 * @param atom
	 * @param location
	 * @return
	 */
	private List<Map<IAtom, IAtom>> getUniqueMappings(List<Mappings> mappings, IAtom atom, int location) {

		List<Map<IAtom, IAtom>> results = new ArrayList<Map<IAtom, IAtom>>();
		for (Mappings mapping : mappings) {
			for (Map<IAtom, IAtom> map : mapping.toAtomMap()) {
				if (map.containsValue(atom)) {
					if (checkMapping(map.values(), atom, location)) {
						results.add(map);
						break;
					}
				}
			}
		}
		//System.out.println(results.size());
		return results;
	}
	
	/**
	 * @param mappings
	 * @param location
	 * @return
	 */
	private List<Map<IAtom, IAtom>> getUniqueMappings(List<Mappings> mappings, int location) {
		List<Map<IAtom, IAtom>> results = new ArrayList<Map<IAtom, IAtom>>();
		for (Mappings mapping : mappings) {
			for (Map<IAtom, IAtom> map : mapping.toAtomMap()) {
				if (checkMapping(map.values(), location)) {
					results.add(map);
					break;
				}
			}
		}
		return results;
	}
	
	/**
	 * @param set
	 * @param atom
	 * @param location
	 * @return
	 */
	private boolean checkMapping(Collection<IAtom> set, IAtom atom, int location) {
		boolean isCompatible = false;
		//First element of the set is the center
		if((boolean) atom.getProperty(location) == true)
			isCompatible = true;
		/*for (IAtom a : set) {
			System.out.println(a);
			if (!a.equals(atom)) {
				if ((boolean) a.getProperty(location) != true) {
					isCompatible = false;
					break;
				}
			}
		}*/
		return isCompatible;
	}
	
	/**
	 * @param set
	 * @param location
	 * @return
	 */
	private boolean checkMapping(Collection<IAtom> set, int location) {

		boolean isCompatible = false;
		//First element of the set is the center
		IAtom center = set.iterator().next();
		if((boolean) center.getProperty(location) == true)
			isCompatible = true;
		/*if(location == SliceConstants.ONPATH) {
			isCompatible = false;
			for (IAtom a : set)
				System.out.println("-"+ a);
			for (IAtom a : set) {
				if ((boolean) a.getProperty(location) == true) {
					isCompatible = true;
					break;
				}
			}
		}
		else {
			for (IAtom a : set) 
				System.out.println("+"+a.getProperty(location));
			for (IAtom a : set) {
				a.setProperty(a, set);
				if ((boolean) a.getProperty(location) != true) {
					isCompatible = false;
					break;
				}
			}
		}*/
		return isCompatible;
	}
	
}
