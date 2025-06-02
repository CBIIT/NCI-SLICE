package com.nih.slice.cdk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.config.Elements;
import org.openscience.cdk.graph.PathTools;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IChemObject;
import org.openscience.cdk.interfaces.IRing;

import com.nih.slice.config.SliceType.ChemicalObject;
import com.nih.slice.config.SliceType.Loc;
import com.nih.slice.parser.Value;

public class ExtractSubjectFromAtomContainer {
	
	/**
	 * @param ref
	 * @param start
	 * @param end
	 * @return
	 */
	public IAtomContainer getAppendages(IAtomContainer ref, Value start, Value end) {
		IAtomContainer select = DefaultChemObjectBuilder.getInstance().newAtomContainer();
		//getChemObject(ChemicalObject obj, Map<Integer, Boolean> location)
		return select;
	}
	
	/**
	 * @param ref
	 * @param start
	 * @param end
	 * @return
	 */
	public IAtomContainer getAppendages(IAtomContainer ref, IChemObject start, IChemObject end) {
		//System.out.println("appendage :"+ ((IAtom) start).getIndex()+" "+((IAtom) end).getIndex());
		IAtomContainer select = DefaultChemObjectBuilder.getInstance().newAtomContainer();
		
		/*List<IAtom> path = PathTools.getShortestPath(ref, (IAtom) start, (IAtom) end);
		IAtomContainer app = DefaultChemObjectBuilder.getInstance().newAtomContainer();
		for (IAtom atom : path)
			app.addAtom(atom);
		for(IBond bond : ref.bonds()) {
			if(path.contains(bond.getAtom(0)) && path.contains(bond.getAtom(1))) {
				app.addBond(bond);
			}
		}
		
		System.out.println("appendage SLECT:"+ app);
		
	       System.out.println("beg target");
	        for(IAtom a : app.atoms())
			{
	        	if(a.getSymbol() != "H")
	        		System.out.print(a.getSymbol()+ a.getIndex()+ " ");
			}
			System.out.println();
			for(IBond a : app.bonds())
			{
				if(a.getBegin().getSymbol() != "H" && a.getEnd().getSymbol() != "H")
				System.out.println(a.getBegin().getIndex()+ "- "+ a.getEnd().getIndex());
			}
			System.out.println("end target");*/
		if (start instanceof IAtom && end instanceof IAtom) {
			compute(ref, select, (IAtom) start, (IAtom) end, new ArrayList<IAtom>());
			select.addAtom((IAtom) end);
			IBond b = ref.getBond((IAtom) start, (IAtom) end);
        	select.addBond(b);
		}
		return select;
	}
	
	/**
	 * @param ref
	 * @param target
	 * @param start
	 * @param end
	 * @param path
	 */
	private void compute(IAtomContainer ref, IAtomContainer target, IAtom start, IAtom end, List<IAtom> path) {

       // System.out.println("appendage :"+ ((IAtom) start).getIndex()+" "+((IAtom) end).getIndex());
		if (start.equals(end)) {
            path.add(start);
            return;
        }
        if (path.contains(start)) return;
        path.add(start);
        List<IAtom> nbrs = ref.getConnectedAtomsList(start);
        for (IAtom nbr : nbrs)	{
        	//System.out.println(start.getIndex()+"--------"+nbr.getIndex());
        	if(!nbr.equals(end) )
        		compute(ref, target, nbr, end, path);
        }
        /*System.out.println("path : ");
        for (int i = 0; i < path.size(); i++)
        	System.out.print(path.get(i).getIndex() + " ");
        System.out.println();*/
        //OLD VERSION
        //path.remove(path.size() - 1);
        //if (!path.contains(end)) path.add(end);
        for (int i = 0; i < path.size(); i++) {
        	IAtom atom = path.get(i);
            if (!target.contains(atom))
            	target.addAtom(atom);
            if (i > 0) {
	            	List<IAtom> nbrst = ref.getConnectedAtomsList(atom);
	                for (IAtom nbr : nbrst)	{
	                	//System.out.println(":"+ nbr.getIndex());
	                	if(target.contains(nbr)) {
		                	IBond b = ref.getBond(atom,nbr);
		                	if(!target.contains(b))
		                		target.addBond(b);
		                }
	                }
            }
        }
       /* System.out.println("beg target");
        for(IAtom a : target.atoms())
		{
			System.out.print(a.getSymbol()+ a.getIndex()+ " ");
		}
		System.out.println();
		for(IBond a : target.bonds())
		{
			System.out.print(a.getBegin().getIndex()+ "- "+ a.getEnd().getIndex());
		}
		System.out.println("end target");*/
        
	}
	
	/**
	 * get atom by atom mapping number
	 * @param ac
	 * @param obj
	 * @param num
	 * @return
	 */
	public IChemObject getChemObjectByID(IAtomContainer ac, ChemicalObject obj, int num) {
		switch (obj) 
        { 
        case ATOM: 
        	for (IAtom a : ac.atoms()) {
        		if ((int)a.getProperty(CDKConstants.ATOM_ATOM_MAPPING) == num) {
        			return a;
        		}
        	}
            return ac.getAtom(num); 
		case BOND: 
            return ac.getBond(num);
		default: 
			return null;
        } 
	}
	
	/**
	 * get the bond associated with 2 atoms by using their atom mapping number
	 * @param ac
	 * @param num1
	 * @param num2
	 * @return
	 */
	public IBond getBondUsingAtomMappingNumbers(IAtomContainer ac, int num1, int num2) {
		for (IBond b : ac.bonds()) {
			int m1 = b.getAtom(0).getProperty(CDKConstants.ATOM_ATOM_MAPPING);
			int m2 = b.getAtom(1).getProperty(CDKConstants.ATOM_ATOM_MAPPING);
			if ((num1 == m1 || num1 == m2) && (num2 == m1 || num2 == m2)) {
				return b;
			}
		}
		return null;
	}
	
	/**
	 * get atoms alpha, or beta, or gamma
	 * @param ac
	 * @param loc
	 * @param ref
	 * @param conf (applied to chemobject in loc)
	 * @return
	 */
	public List<IAtom> getChemObjectByLocs(IAtomContainer ac, Loc loc, IAtom ref, Map<Integer, Boolean> conf) {
		switch (loc) 
        { 
        case ALPHA: 
            return getAlpha(ac, ref, conf); 
		case BETA: 
			return getBeta(ac, ref, conf); 
		case GAMMA: 
			return getGamma(ac, ref, conf); 
		default: 
			return null;
        } 
	}
	
	/**
	 * get atoms alpha, or beta, or gamma
	 * @param ac
	 * @param atomTypeTo (atomtype to ref)
	 * @param conf (applied to chemobject refering to atomTypeTo loc)
	 * @param ref
	 * @param conf
	 * @return
	 */
	public List<IAtom> getChemObjectByLocs(IAtomContainer ac, Elements atomTypeTo, Loc loc, IAtom ref, Map<Integer, Boolean> conf) {
		switch (loc) 
        { 
        case ALPHA: 
            return getAlpha(ac, atomTypeTo, ref, conf); 
		case BETA: 
			return getBeta(ac, atomTypeTo, ref, conf); 
		case GAMMA: 
			return getGamma(ac, atomTypeTo, ref, conf); 
		default: 
			return null;
        } 
	}
	
	/**
	 * get atom by symbol
	 * @param ac
	 * @param atomType
	 * @return
	 */
	public List<IAtom> getChemObjectByAtomType(IAtomContainer ac, Elements atomType) {
		List<IAtom> res = new ArrayList<IAtom>();
		for (IAtom atom : ac.atoms()) {
			if (atom.getSymbol().equals(atomType.symbol())) 
				res.add(atom);
		}
		return res;
	}
	
	//TODO
	/**
	 * @param ac
	 * @param obj
	 * @return
	 */
	public IAtomContainer getChemObjectBySet(IAtomContainer ac, ChemicalObject obj) {
		switch (obj) 
        { 
        case ATOM: 
		case BOND:
		case RING: 
		case SET: 
		default: 
			return null;
        } 
	}

	
	/**
	 * get alpha atoms.
	 * @param ref
	 * @param source
	 * @param config
	 * @return
	 */
	public static List<IAtom> getAlpha(IAtomContainer ref, IAtom source, Map<Integer, Boolean> config) {
		List<IAtom> alphas = ref.getConnectedAtomsList(source);
		List<IAtom> newAlphas = new ArrayList<IAtom>(alphas);
		newAlphas.remove(source);
		for (IAtom alpha : alphas) {
			//ignore hydrogen
			if (alpha.getSymbol().equals("H")){
				newAlphas.remove(alpha);
				continue;
			}
			if (config.containsKey(SliceConstants.ONPATH)) {
				if (config.get(SliceConstants.ONPATH) == false || config.get(SliceConstants.OFFPATH)  == false) {
					if (!(boolean)alpha.getProperty(SliceConstants.ONPATH).equals(config.get(SliceConstants.ONPATH)))
						newAlphas.remove(alpha);
					if (!(boolean)alpha.getProperty(SliceConstants.OFFPATH).equals(config.get(SliceConstants.OFFPATH)))
						newAlphas.remove(alpha);
				}
			}
			if (config.containsKey(SliceConstants.ONRING) ) {
				if (config.get(SliceConstants.ONRING) == false || config.get(SliceConstants.OFFRING)  == false) {
					if (!(boolean)alpha.getProperty(SliceConstants.ONRING).equals(config.get(SliceConstants.ONRING)))
						newAlphas.remove(alpha);
					if (!(boolean)alpha.getProperty(SliceConstants.OFFRING).equals(config.get(SliceConstants.OFFRING)))
						newAlphas.remove(alpha);
				}
			}
			if (config.containsKey(SliceConstants.ONTHEBRIDGE)) {
				if (config.get(SliceConstants.ONTHEBRIDGE) == false || config.get(SliceConstants.OFFTHEBRIDGE)  == false) {
					if (!(boolean)alpha.getProperty(SliceConstants.ONTHEBRIDGE).equals(config.get(SliceConstants.ONTHEBRIDGE)))
						newAlphas.remove(alpha);
					if (!(boolean)alpha.getProperty(SliceConstants.OFFTHEBRIDGE).equals(config.get(SliceConstants.OFFTHEBRIDGE)))
						newAlphas.remove(alpha);
				}
			}
			
		}
		return newAlphas;
	}
	
	/**
	 * get beta atoms.
	 * @param ref
	 * @param source
	 * @param config
	 * @return
	 */
	public static List<IAtom> getBeta(IAtomContainer ref, IAtom source, Map<Integer, Boolean> config) {
		List<IAtom> alphas = ref.getConnectedAtomsList(source);
		List<IAtom> newBetas = new ArrayList<IAtom>();
		for (IAtom alpha : alphas) {
			List<IAtom> betas = ref.getConnectedAtomsList(alpha);
			betas.remove(source);
			betas.removeAll(alphas);
			newBetas.addAll(betas);
			for (IAtom beta : betas) {
				//ignore hydrogen
				if (beta.getSymbol().equals("H")){
					newBetas.remove(alpha);
					continue;
				}
				if (config.containsKey(SliceConstants.ONPATH)) {
					if (config.get(SliceConstants.ONPATH) == false || config.get(SliceConstants.OFFPATH) == false) {
						if (!(boolean) beta.getProperty(SliceConstants.ONPATH).equals(config.get(SliceConstants.ONPATH)))
							newBetas.remove(beta);
						if (!(boolean) beta.getProperty(SliceConstants.OFFPATH)
								.equals(config.get(SliceConstants.OFFPATH)))
							newBetas.remove(beta);
					}
				}
				if (config.containsKey(SliceConstants.ONRING)) {
					if (config.get(SliceConstants.ONRING) == false || config.get(SliceConstants.OFFRING) == false) {
						if (!(boolean) beta.getProperty(SliceConstants.ONRING).equals(config.get(SliceConstants.ONRING)))
							newBetas.remove(beta);
						if (!(boolean) beta.getProperty(SliceConstants.OFFRING)
								.equals(config.get(SliceConstants.OFFRING)))
							newBetas.remove(beta);
					}
				}
				if (config.containsKey(SliceConstants.ONTHEBRIDGE)) {
					if (config.get(SliceConstants.ONTHEBRIDGE) == false
							|| config.get(SliceConstants.OFFTHEBRIDGE) == false) {
						if (!(boolean) beta.getProperty(SliceConstants.ONTHEBRIDGE)
								.equals(config.get(SliceConstants.ONTHEBRIDGE)))
							newBetas.remove(beta);
						if (!(boolean) beta.getProperty(SliceConstants.OFFTHEBRIDGE)
								.equals(config.get(SliceConstants.OFFTHEBRIDGE)))
							newBetas.remove(beta);
					}
				}
			}	
		}
		return newBetas;
	}
	
	/**
	 * get gamma atoms.
	 * @param ref
	 * @param source
	 * @param config
	 * @return
	 */
	public static List<IAtom> getGamma(IAtomContainer ref, IAtom source, Map<Integer, Boolean> config) {
		List<IAtom> alphas = ref.getConnectedAtomsList(source);
		List<IAtom> newGammas = new ArrayList<IAtom>();
		for (IAtom alpha : alphas) {
			List<IAtom> betas = ref.getConnectedAtomsList(alpha);
			betas.remove(source);
			betas.removeAll(alphas);
			for (IAtom beta : betas) {
				List<IAtom> gammas = ref.getConnectedAtomsList(beta);
				gammas.remove(source);
				gammas.removeAll(alphas);
				gammas.removeAll(betas);
				newGammas.addAll(gammas);
				for (IAtom gamma : gammas) {
					//ignore hydrogen
					if (gamma.getSymbol().equals("H")){
						newGammas.remove(alpha);
						continue;
					}
					if (config.containsKey(SliceConstants.ONPATH)) {
						if (config.get(SliceConstants.ONPATH) == false || config.get(SliceConstants.OFFPATH)  == false) {
							if (!(boolean)gamma.getProperty(SliceConstants.ONPATH).equals(config.get(SliceConstants.ONPATH)))
								newGammas.remove(gamma);
							if (!(boolean)gamma.getProperty(SliceConstants.OFFPATH).equals(config.get(SliceConstants.OFFPATH)))
								newGammas.remove(gamma);
						}
					}
					if (config.containsKey(SliceConstants.ONRING)) {
						if (config.get(SliceConstants.ONRING) == false || config.get(SliceConstants.OFFRING)  == false) {
							if (!(boolean)gamma.getProperty(SliceConstants.ONRING).equals(config.get(SliceConstants.ONRING)))
								newGammas.remove(gamma);
							if (!(boolean)gamma.getProperty(SliceConstants.OFFRING).equals(config.get(SliceConstants.OFFRING)))
								newGammas.remove(gamma);
						}
					}
					if (config.containsKey(SliceConstants.ONTHEBRIDGE)) {
						if (config.get(SliceConstants.ONTHEBRIDGE) == false || config.get(SliceConstants.OFFTHEBRIDGE)  == false) {
							if (!(boolean)gamma.getProperty(SliceConstants.ONTHEBRIDGE).equals(config.get(SliceConstants.ONTHEBRIDGE)))
								newGammas.remove(gamma);
							if (!(boolean)gamma.getProperty(SliceConstants.OFFTHEBRIDGE).equals(config.get(SliceConstants.OFFTHEBRIDGE)))
								newGammas.remove(gamma);
						}
					}
				}
			}	
		}
		return newGammas;
	}
	
	/**
	 * get alpha atoms. Hydrogen are excluded if element is not an hydrogen
	 * @param ref
	 * @param elt
	 * @param source
	 * @param config
	 * @return
	 */
	public static List<IAtom> getAlpha(IAtomContainer ref, Elements elt, IAtom source, Map<Integer, Boolean> config) {
		List<IAtom> alphas = ref.getConnectedAtomsList(source);
		List<IAtom> newAlphas = new ArrayList<IAtom>(alphas);
		newAlphas.remove(source);
		for (IAtom alpha : alphas) {
			if (!alpha.getSymbol().equals(elt.symbol())) {
				newAlphas.remove(alpha);
				continue;
			}
			//ignore hydrogen
			if (!elt.equals(Elements.Hydrogen)) {
				if (alpha.getSymbol().equals("H")){
					newAlphas.remove(alpha);
					continue;
				}
			}
			if (config.containsKey(SliceConstants.ONPATH)) {
				if (config.get(SliceConstants.ONPATH) == false || config.get(SliceConstants.OFFPATH)  == false) {
					if (!(boolean)alpha.getProperty(SliceConstants.ONPATH).equals(config.get(SliceConstants.ONPATH)))
						newAlphas.remove(alpha);
					if (!(boolean)alpha.getProperty(SliceConstants.OFFPATH).equals(config.get(SliceConstants.OFFPATH)))
						newAlphas.remove(alpha);
				}
			}
			if (config.containsKey(SliceConstants.ONRING)) {
				if (config.get(SliceConstants.ONRING) == false || config.get(SliceConstants.OFFRING)  == false) {
					if (!(boolean)alpha.getProperty(SliceConstants.ONRING).equals(config.get(SliceConstants.ONRING)))
						newAlphas.remove(alpha);
					if (!(boolean)alpha.getProperty(SliceConstants.OFFRING).equals(config.get(SliceConstants.OFFRING)))
						newAlphas.remove(alpha);
				}
			}
			if (config.containsKey(SliceConstants.ONTHEBRIDGE)) {
				if (config.get(SliceConstants.ONTHEBRIDGE) == false || config.get(SliceConstants.OFFTHEBRIDGE)  == false) {
					if (!(boolean)alpha.getProperty(SliceConstants.ONTHEBRIDGE).equals(config.get(SliceConstants.ONTHEBRIDGE)))
						newAlphas.remove(alpha);
					if (!(boolean)alpha.getProperty(SliceConstants.OFFTHEBRIDGE).equals(config.get(SliceConstants.OFFTHEBRIDGE)))
						newAlphas.remove(alpha);
				}
			}
		}
		return newAlphas;
	}
	
	/**
	 * get beta atoms. Hydrogen are excluded if element is not an hydrogen
	 * @param ref
	 * @param elt
	 * @param source
	 * @param config
	 * @return
	 */
	public static List<IAtom> getBeta(IAtomContainer ref, Elements elt, IAtom source, Map<Integer, Boolean> config) {
		List<IAtom> alphas = ref.getConnectedAtomsList(source);
		List<IAtom> newBetas = new ArrayList<IAtom>();
		for (IAtom alpha : alphas) {
			List<IAtom> betas = ref.getConnectedAtomsList(alpha);
			betas.remove(source);
			betas.removeAll(alphas);
			newBetas.addAll(betas);
			for (IAtom beta : betas) {
				if (!beta.getSymbol().equals(elt.symbol())) {
					newBetas.remove(alpha);
					continue;
				}
				//ignore hydrogen
				if (!elt.equals(Elements.Hydrogen)) {
					if (beta.getSymbol().equals("H")){
						newBetas.remove(alpha);
						continue;
					}
				}
				if (config.containsKey(SliceConstants.ONPATH)) {
					if (config.get(SliceConstants.ONPATH) == false || config.get(SliceConstants.OFFPATH) == false) {
						if (!(boolean) beta.getProperty(SliceConstants.ONPATH).equals(config.get(SliceConstants.ONPATH)))
							newBetas.remove(beta);
						if (!(boolean) beta.getProperty(SliceConstants.OFFPATH)
								.equals(config.get(SliceConstants.OFFPATH)))
							newBetas.remove(beta);
					}
				}
				if (config.containsKey(SliceConstants.ONRING)) {
					if (config.get(SliceConstants.ONRING) == false || config.get(SliceConstants.OFFRING) == false) {
						if (!(boolean) beta.getProperty(SliceConstants.ONRING).equals(config.get(SliceConstants.ONRING)))
							newBetas.remove(beta);
						if (!(boolean) beta.getProperty(SliceConstants.OFFRING)
								.equals(config.get(SliceConstants.OFFRING)))
							newBetas.remove(beta);
					}
				}
				if (config.containsKey(SliceConstants.ONTHEBRIDGE)) {
					if (config.get(SliceConstants.ONTHEBRIDGE) == false
							|| config.get(SliceConstants.OFFTHEBRIDGE) == false) {
						if (!(boolean) beta.getProperty(SliceConstants.ONTHEBRIDGE)
								.equals(config.get(SliceConstants.ONTHEBRIDGE)))
							newBetas.remove(beta);
						if (!(boolean) beta.getProperty(SliceConstants.OFFTHEBRIDGE)
								.equals(config.get(SliceConstants.OFFTHEBRIDGE)))
							newBetas.remove(beta);
					}
				}
			}	
		}
		return newBetas;
	}
	
	/**
	 * get gamma atoms. Hydrogen are excluded if element is not an hydrogen
	 * @param ref
	 * @param elt
	 * @param source
	 * @param config
	 * @return
	 */
	public static List<IAtom> getGamma(IAtomContainer ref, Elements elt, IAtom source, Map<Integer, Boolean> config) {
		List<IAtom> alphas = ref.getConnectedAtomsList(source);
		List<IAtom> newGammas = new ArrayList<IAtom>();
		for (IAtom alpha : alphas) {
			List<IAtom> betas = ref.getConnectedAtomsList(alpha);
			betas.remove(source);
			betas.removeAll(alphas);
			for (IAtom beta : betas) {
				List<IAtom> gammas = ref.getConnectedAtomsList(beta);
				gammas.remove(source);
				gammas.removeAll(alphas);
				gammas.removeAll(betas);
				newGammas.addAll(gammas);
				for (IAtom gamma : gammas) {
					if (!gamma.getSymbol().equals(elt.symbol())) {
						newGammas.remove(alpha);
						continue;
					}
					//ignore hydrogen
					if (!elt.equals(Elements.Hydrogen)) {
						if (gamma.getSymbol().equals("H")){
							newGammas.remove(alpha);
							continue;
						}
					}
					if (config.containsKey(SliceConstants.ONPATH)) {
						if (config.get(SliceConstants.ONPATH) == false || config.get(SliceConstants.OFFPATH)  == false) {
							if (!(boolean)gamma.getProperty(SliceConstants.ONPATH).equals(config.get(SliceConstants.ONPATH)))
								newGammas.remove(gamma);
							if (!(boolean)gamma.getProperty(SliceConstants.OFFPATH).equals(config.get(SliceConstants.OFFPATH)))
								newGammas.remove(gamma);
						}
					}
					if (config.containsKey(SliceConstants.ONRING)) {
						if (config.get(SliceConstants.ONRING) == false || config.get(SliceConstants.OFFRING)  == false) {
							if (!(boolean)gamma.getProperty(SliceConstants.ONRING).equals(config.get(SliceConstants.ONRING)))
								newGammas.remove(gamma);
							if (!(boolean)gamma.getProperty(SliceConstants.OFFRING).equals(config.get(SliceConstants.OFFRING)))
								newGammas.remove(gamma);
						}
					}
					if (config.containsKey(SliceConstants.ONTHEBRIDGE)) {
						if (config.get(SliceConstants.ONTHEBRIDGE) == false || config.get(SliceConstants.OFFTHEBRIDGE)  == false) {
							if (!(boolean)gamma.getProperty(SliceConstants.ONTHEBRIDGE).equals(config.get(SliceConstants.ONTHEBRIDGE)))
								newGammas.remove(gamma);
							if (!(boolean)gamma.getProperty(SliceConstants.OFFTHEBRIDGE).equals(config.get(SliceConstants.OFFTHEBRIDGE)))
								newGammas.remove(gamma);
						}
					}
				}
			}	
		}
		return newGammas;
	}
}
