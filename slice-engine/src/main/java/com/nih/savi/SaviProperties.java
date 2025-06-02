package com.nih.savi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.aromaticity.Aromaticity;
import org.openscience.cdk.config.Isotopes;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.graph.AllPairsShortestPaths;
import org.openscience.cdk.graph.Cycles;
import org.openscience.cdk.inchi.InChIGeneratorFactory;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.interfaces.IIsotope;
import org.openscience.cdk.interfaces.IRing;
import org.openscience.cdk.interfaces.IRingSet;
import org.openscience.cdk.interfaces.IStereoElement;
import org.openscience.cdk.interfaces.IAtomType.Hybridization;
import org.openscience.cdk.interfaces.IBond.Order;
import org.openscience.cdk.isomorphism.UniversalIsomorphismTester;
import org.openscience.cdk.isomorphism.matchers.Expr;
import org.openscience.cdk.isomorphism.matchers.QueryAtomContainer;
import org.openscience.cdk.isomorphism.matchers.QueryAtomContainerCreator;
import org.openscience.cdk.isomorphism.matchers.QueryBond;
import org.openscience.cdk.isomorphism.mcss.RMap;
import org.openscience.cdk.qsar.IMolecularDescriptor;
import org.openscience.cdk.qsar.descriptors.molecular.FMFDescriptor;
import org.openscience.cdk.qsar.descriptors.molecular.RotatableBondsCountDescriptor;
import org.openscience.cdk.qsar.result.DoubleResult;
import org.openscience.cdk.qsar.result.IntegerResult;
import org.openscience.cdk.ringsearch.AllRingsFinder;
import org.openscience.cdk.smarts.SmartsPattern;
import org.openscience.cdk.tools.CDKHydrogenAdder;
import org.openscience.cdk.tools.manipulator.AtomContainerManipulator;
import org.openscience.cdk.tools.manipulator.RingSetManipulator;

public class SaviProperties {
	private int hBondDonors = 0;
	private int hBondAcceptors = 0;
	private int rotatablebonds = 0;
	private double xlogP = 0;
	private double weight = 0;
	private int lipinskifailures3 = 0;
	private int lipinskifailures5 = 0;
	private int heavyAtomCount = 0;
	private double fsp3 = 0;
	private int nsp2 = 0;
	private int nsp3 = 0;
	private int seCount = 0;
	double tpsa = 0;
	private double fmf = 0;
	private int sssrCount = 0;
	private int aromaticRingCount = 0;
	private int aliphaticRingCount = 0;
	private String inchi;
	private String inchikey;
	private IAtomContainer ac;

	private String elementName = "*";
	private boolean includeTerminals = false;
	private boolean excludeAmides = true;
	private boolean checkAromaticity = true;
	private boolean salicylFlag = false;
	private static HashMap<String, Double> tpsaMap;
	// private static final String[] NAMES = {"XLogP"};

	public void calculate(IAtomContainer atomContainer) {

		// calculate independant properties
		IMolecularDescriptor fmfd = new FMFDescriptor();
		fmf = ((DoubleResult) fmfd.calculate(atomContainer).getValue()).doubleValue();

		// clone
		try {
			ac = (IAtomContainer) atomContainer.clone();
			AtomContainerManipulator.percieveAtomTypesAndConfigureUnsetProperties(ac);
			CDKHydrogenAdder hAdder = CDKHydrogenAdder.getInstance(ac.getBuilder());
			hAdder.addImplicitHydrogens(ac);
			AtomContainerManipulator.convertImplicitToExplicitHydrogens(ac);
		} catch (CloneNotSupportedException e) {
			// return getDummyDescriptorValue(e);
		} catch (CDKException e) {
			// return getDummyDescriptorValue(e);
		}

		// calculate sssr
		IRingSet sssrRs = Cycles.sssr(ac).toRingSet();
		sssrCount = sssrRs.getAtomContainerCount();

		stereoElementCount(atomContainer.stereoElements());
		calculateBondProperties(atomContainer);
		calculateAtomProperties(atomContainer, sssrRs);

		// calculate ro3 and ro5
		calculateROProperties();
		// calculate ringProperties
		calculateRingProperties(sssrRs);

		atomContainer.setProperty("SSSRCount", aromaticRingCount);
		atomContainer.setProperty("aromaticSSSRCount", aromaticRingCount);
		atomContainer.setProperty("aliphaticSSSRCount", aromaticRingCount);

	}

	private void calculateBondProperties(IAtomContainer atomContainer) {
		// calculate rotatable bonds
		try {
			IMolecularDescriptor rotata = new RotatableBondsCountDescriptor();
			Object[] rotatableBondsParams = { includeTerminals, excludeAmides };
			rotata.setParameters(rotatableBondsParams);
			rotatablebonds = ((IntegerResult) rotata.calculate(atomContainer.clone()).getValue()).intValue();
		} catch (CloneNotSupportedException | CDKException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void calculateAtomProperties(IAtomContainer atomContainer, IRingSet sssrRs) {
		// determine hydrogenNaturalMass (used to calculate MW)
		double hydrogenNaturalMass = 0;
		try {
			IIsotope hydrogen = Isotopes.getInstance().getMajorIsotope("H");
			hydrogenNaturalMass = Isotopes.getInstance().getNaturalMass(hydrogen);
		} catch (IOException e) {
			// return getDummyDescriptorValue(e);
		}

		List<String> profiles = new ArrayList<String>();

		IRingSet atomRingSet = null;
		if (checkAromaticity) {
			try {
				Aromaticity.cdkLegacy().apply(ac);
			} catch (CDKException e) {
				// return getDummyDescriptorValue(e);
			}
		}

		// calculate the set of all rings
		IRingSet allRs = null;
		try {
			allRs = (new AllRingsFinder()).findAllRings(ac);
		} catch (CDKException e) {
			// return getDummyDescriptorValue(e);
		}

		// SmilesParser sp = new SmilesParser(DefaultChemObjectBuilder.getInstance());
		String symbol = "";
		int bondCount = 0;
		int atomCount = ac.getAtomCount();
		int hsCount = 0;
		double xlogPOld = 0;
		IBond.Order maxBondOrder = IBond.Order.SINGLE;
		List<Integer> hBondAcceptorsList = new ArrayList<Integer>();
		List<Integer> hBondDonorsList = new ArrayList<Integer>();
		int checkAminoAcid = 1;// if 0 no check, if >1 check
		IAtom atomi = null;

		// iterate over all atoms of this AtomContainer; use label atomloop to allow for
		// labelled continue
		atomloop: for (int i = 0; i < atomCount; i++) {
			atomi = (IAtom) ac.getAtom(i);
			// Problem fused ring systems
			atomRingSet = sssrRs.getRings(atomi);
			atomi.setProperty("IS_IN_AROMATIC_RING", false);
			atomi.setProperty(CDKConstants.PART_OF_RING_OF_SIZE, 0);
			// logger.debug("atomRingSet.size "+atomRingSet.size());
			if (atomRingSet.getAtomContainerCount() > 0) {
				if (atomRingSet.getAtomContainerCount() > 1) {
					Iterator<IAtomContainer> containers = RingSetManipulator.getAllAtomContainers(atomRingSet)
							.iterator();
					atomRingSet = sssrRs.getBuilder().newInstance(IRingSet.class);
					while (containers.hasNext()) {
						// XXX: we're already in the SSSR, but then get the esential cycles
						// of this atomRingSet... this code doesn't seem to make sense as
						// essential cycles are a subset of SSSR and can be found directly
						atomRingSet.add(Cycles.essential(containers.next()).toRingSet());
					}
					// logger.debug(" SSSRatomRingSet.size "+atomRingSet.size());
				}
				for (int j = 0; j < atomRingSet.getAtomContainerCount(); j++) {
					if (j == 0) {
						atomi.setProperty(CDKConstants.PART_OF_RING_OF_SIZE,
								((IRing) atomRingSet.getAtomContainer(j)).getRingSize());
					}

					if (((IRing) atomRingSet.getAtomContainer(j)).contains(atomi)) {
						if (((IRing) atomRingSet.getAtomContainer(j)).getRingSize() >= 6
								&& atomi.getFlag(CDKConstants.ISAROMATIC)) {
							atomi.setProperty("IS_IN_AROMATIC_RING", true);
						}
						if (((IRing) atomRingSet.getAtomContainer(j))
								.getRingSize() < (Integer) atomi.getProperty(CDKConstants.PART_OF_RING_OF_SIZE)) {
							atomi.setProperty(CDKConstants.PART_OF_RING_OF_SIZE,
									((IRing) atomRingSet.getAtomContainer(j)).getRingSize());
						}
					}
				}
			} // else{
				// logger.debug();
				// }

			// Calculate MW
			if (elementName.equals("*")) {
				try {
					weight += Isotopes.getInstance().getNaturalMass(atomi);
					Integer implicitHydrogenCount = atomi.getImplicitHydrogenCount();
					if (implicitHydrogenCount == CDKConstants.UNSET) {
						implicitHydrogenCount = 0;
					}
					weight += (implicitHydrogenCount * hydrogenNaturalMass);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (elementName.equals("H")) {
				if (atomi.getSymbol().equals(elementName)) {
					weight += hydrogenNaturalMass;
				} else {
					weight += (atomi.getImplicitHydrogenCount() * hydrogenNaturalMass);
				}
			} else {

				if (atomi.getSymbol().equals(elementName)) {
					try {
						weight += Isotopes.getInstance().getNaturalMass(atomi);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			// calculate nsp2 and nsp3
			if (atomi.getSymbol().equals("C")) {
				if (atomi.getHybridization() == Hybridization.SP2)
					nsp2++;
				else if (atomi.getHybridization() == Hybridization.SP3)
					nsp3++;
			}

			// Config TPSA properties
			if (atomi.getSymbol().equals("N") || atomi.getSymbol().equals("O") || atomi.getSymbol().equals("S")
					|| atomi.getSymbol().equals("P")) {
				int singleBondCount = 0;
				int doubleBondCount = 0;
				int tripleBondCount = 0;
				int aromaticBondCount = 0;
				double maxBondOrder1 = 0;
				double bondOrderSum = 0;
				int hCount = 0;
				int isIn3MemberRing = 0;

				// counting the number of single/double/triple/aromatic bonds
				List<IBond> connectedBonds = ac.getConnectedBondsList(atomi);
				for (IBond connectedBond : connectedBonds) {
					if (connectedBond.getFlag(CDKConstants.ISAROMATIC))
						aromaticBondCount++;
					else if (connectedBond.getOrder() == Order.SINGLE)
						singleBondCount++;
					else if (connectedBond.getOrder() == Order.DOUBLE)
						doubleBondCount++;
					else if (connectedBond.getOrder() == Order.TRIPLE)
						tripleBondCount++;
				}
				int formalCharge = atomi.getFormalCharge();
				List<IAtom> connectedAtoms = ac.getConnectedAtomsList(atomi);
				int numberOfNeighbours = connectedAtoms.size();

				// EXPLICIT hydrogens: count the number of hydrogen atoms
				for (int neighbourIndex = 0; neighbourIndex < numberOfNeighbours; neighbourIndex++)
					if (((IAtom) connectedAtoms.get(neighbourIndex)).getSymbol().equals("H"))
						hCount++;
				// IMPLICIT hydrogens: count the number of hydrogen atoms and adjust other atom
				// profile properties
				Integer implicitHAtoms = atomi.getImplicitHydrogenCount();
				if (implicitHAtoms == CDKConstants.UNSET) {
					implicitHAtoms = 0;
				}

				for (int hydrogenIndex = 0; hydrogenIndex < implicitHAtoms; hydrogenIndex++) {
					hCount++;
					numberOfNeighbours++;
					singleBondCount++;
				}
				// Calculate bond order sum using the counters of single/double/triple/aromatic
				// bonds
				bondOrderSum += singleBondCount * 1.0;
				bondOrderSum += doubleBondCount * 2.0;
				bondOrderSum += tripleBondCount * 3.0;
				bondOrderSum += aromaticBondCount * 1.5;
				// setting maxBondOrder
				if (singleBondCount > 0)
					maxBondOrder1 = 1.0;
				if (aromaticBondCount > 0)
					maxBondOrder1 = 1.5;
				if (doubleBondCount > 0)
					maxBondOrder1 = 2.0;
				if (tripleBondCount > 0)
					maxBondOrder1 = 3.0;

				// isIn3MemberRing checker
				if (allRs.contains(atomi)) {
					IRingSet rsAtom = allRs.getRings(atomi);
					for (int ringSetIndex = 0; ringSetIndex < rsAtom.getAtomContainerCount(); ringSetIndex++) {
						IRing ring = (IRing) rsAtom.getAtomContainer(ringSetIndex);
						if (ring.getRingSize() == 3)
							isIn3MemberRing = 1;
					}
				}
				// create a profile of the current atom (atoms[atomIndex]) according to the
				// profile definition in the constructor
				String profile = atomi.getSymbol() + "+" + maxBondOrder1 + "+" + bondOrderSum + "+" + numberOfNeighbours
						+ "+" + hCount + "+" + formalCharge + "+" + aromaticBondCount + "+" + isIn3MemberRing + "+"
						+ singleBondCount + "+" + doubleBondCount + "+" + tripleBondCount;
				// logger.debug("tpsa profile: "+ profile);
				profiles.add(profile);
			}

			// count heavy atoms
			if (!atomi.getSymbol().equals("H"))
				heavyAtomCount++;

			// Calculate hBondAcceptors and hBondDonors
			// looking for suitable nitrogen atoms
			if (atomi.getSymbol().equals("N") && atomi.getFormalCharge() <= 0) {

				// excluding nitrogens that are adjacent to an oxygen
				List<IBond> bonds = ac.getConnectedBondsList(atomi);
				int nPiBonds = 0;
				for (IBond bond : bonds) {
					if (bond.getOther(atomi).getSymbol().equals("O"))
						continue atomloop;
					if (IBond.Order.DOUBLE.equals(bond.getOrder()))
						nPiBonds++;
				}

				// if the nitrogen is aromatic and there are no pi bonds then it's
				// lone pair cannot accept any hydrogen bonds
				if (atomi.getFlag(CDKConstants.ISAROMATIC) && nPiBonds == 0)
					continue;

				hBondAcceptors++;
			}
			// looking for suitable oxygen atoms
			else if (atomi.getSymbol().equals("O") && atomi.getFormalCharge() <= 0) {
				// excluding oxygens that are adjacent to a nitrogen or to an aromatic carbon
				List<IBond> neighbours = ac.getConnectedBondsList(atomi);
				for (IBond bond : neighbours) {
					IAtom neighbor = bond.getOther(atomi);
					if (neighbor.getSymbol().equals("N") || (neighbor.getSymbol().equals("C") && neighbor.isAromatic()
							&& bond.getOrder() != IBond.Order.DOUBLE))
						continue atomloop;
					;
				}
				hBondAcceptors++;
			}
			// checking for O and N atoms where the formal charge is >= 0
			if ((atomi.getSymbol().equals("O") || atomi.getSymbol().equals("N")) && atomi.getFormalCharge() >= 0) {
				// implicit hydrogens
				Integer implicitH = atomi.getImplicitHydrogenCount();
				if (implicitH == CDKConstants.UNSET)
					implicitH = 0;
				if (implicitH > 0) {
					hBondDonors++;
					continue atomloop; // we skip the explicit hydrogens part cause we found implicit hydrogens
				}
				// explicit hydrogens
				List<IAtom> neighbours = ac.getConnectedAtomsList(atomi);
				for (Object neighbour : neighbours) {
					if (((IAtom) neighbour).getSymbol().equals("H")) {
						hBondDonors++;
						continue atomloop;
					}
				}
			}
		}

		// calculate fsp3
		fsp3 = nsp3 / (double) (nsp2 + nsp3);

		// calculate the tpsa for the AtomContainer ac
		configTPSAMap();
		tpsa = 0;
		for (int profileIndex = 0; profileIndex < profiles.size(); profileIndex++) {
			if (tpsaMap.containsKey(profiles.get(profileIndex))) {
				tpsa += (Double) tpsaMap.get(profiles.get(profileIndex));
				// logger.debug("tpsa contribs: " + profiles.elementAt(profileIndex) + "\t" +
				// ((Double)map.get(profiles.elementAt(profileIndex))).doubleValue());
			}
		}
		profiles.clear(); // remove all profiles from the profiles-Vector
		// logger.debug("tpsa: " + tpsa);

		// calculate xLogP
		xlogP = 0;
		for (int i = 0; i < atomCount; i++) {
			atomi = (IAtom) ac.getAtom(i);
			if (xlogPOld == xlogP & i > 0 & !symbol.equals("H")) {
				// logger.debug("\nXlogPAssignmentError: Could not assign atom number:"+(i-1));
			}

			xlogPOld = xlogP;
			symbol = atomi.getSymbol();
			bondCount = ac.getConnectedBondsCount(atomi);
			hsCount = getHydrogenCount(ac, atomi);
			maxBondOrder = ac.getMaximumBondOrder(atomi);
			if (!symbol.equals("H")) {
				// logger.debug("i:"+i+" Symbol:"+symbol+" "+" bondC:"+bondCount+"
				// Charge:"+atoms[i].getFormalCharge()+" hsC:"+hsCount+" maxBO:"+maxBondOrder+"
				// Arom:"+atoms[i].getFlag(CDKConstants.ISAROMATIC)+"
				// AtomTypeX:"+getAtomTypeXCount(ac, atoms[i])+" PiSys:"+getPiSystemsCount(ac,
				// atoms[i])+" C=:"+getDoubleBondedCarbonsCount(ac, atoms[i])+"
				// AromCc:"+getAromaticCarbonsCount(ac,atoms[i])+"
				// RS:"+((Integer)atoms[i].getProperty(CDKConstants.PART_OF_RING_OF_SIZE)).intValue()+"\t");
			}
			if (symbol.equals("C")) {
				if (bondCount == 2) {
					// C sp
					if (hsCount >= 1) {
						xlogP += 0.209;
						// logger.debug("XLOGP: 38 0.209");
					} else {
						if (maxBondOrder == IBond.Order.DOUBLE) {
							xlogP += 2.073;
							// logger.debug("XLOGP: 40 2.037");
						} else if (maxBondOrder == IBond.Order.TRIPLE) {
							xlogP += 0.33;
							// logger.debug("XLOGP: 39 0.33");
						}
					}
				}
				if (bondCount == 3) {
					// C sp2
					if ((Boolean) atomi.getProperty("IS_IN_AROMATIC_RING")) {
						if (getAromaticCarbonsCount(ac, atomi) >= 2 && getAromaticNitrogensCount(ac, atomi) == 0) {
							if (hsCount == 0) {
								if (getAtomTypeXCount(ac, atomi) == 0) {
									xlogP += 0.296;
									// logger.debug("XLOGP: 34 0.296");
								} else {
									xlogP -= 0.151;
									// logger.debug("XLOGP: 35 C.ar.x -0.151");
								}
							} else {
								xlogP += 0.337;
								// logger.debug("XLOGP: 32 0.337");
							}
							// } else if (getAromaticCarbonsCount(ac, atoms[i]) < 2 &&
							// getAromaticNitrogensCount(ac, atoms[i]) > 1) {
						} else if (getAromaticNitrogensCount(ac, atomi) >= 1) {
							if (hsCount == 0) {
								if (getAtomTypeXCount(ac, atomi) == 0) {
									xlogP += 0.174;
									// logger.debug("XLOGP: 36 C.ar.(X) 0.174");
								} else {
									xlogP += 0.366;
									// logger.debug("XLOGP: 37 0.366");
								}
							} else if (getHydrogenCount(ac, atomi) == 1) {
								xlogP += 0.126;
								// logger.debug("XLOGP: 33 0.126");
							}
						}
						// NOT aromatic, but sp2
					} else {
						if (hsCount == 0) {
							if (getAtomTypeXCount(ac, atomi) == 0) {
								if (getPiSystemsCount(ac, atomi) <= 1) {
									xlogP += 0.05;
									// logger.debug("XLOGP: 26 0.05");
								} else {
									xlogP += 0.013;
									// logger.debug("XLOGP: 27 0.013");
								}
							} else if (getAtomTypeXCount(ac, atomi) == 1) {
								if (getPiSystemsCount(ac, atomi) == 0) {
									xlogP -= 0.03;
									// logger.debug("XLOGP: 28 -0.03");
								} else {
									xlogP -= 0.027;
									// logger.debug("XLOGP: 29 -0.027");
								}
							} else if (getAtomTypeXCount(ac, atomi) == 2) {
								if (getPiSystemsCount(ac, atomi) == 0) {
									xlogP += 0.005;
									// logger.debug("XLOGP: 30 0.005");
								} else {
									xlogP -= 0.315;
									// logger.debug("XLOGP: 31 -0.315");
								}
							}
						}
						if (hsCount == 1) {
							if (getAtomTypeXCount(ac, atomi) == 0) {
								if (getPiSystemsCount(ac, atomi) == 0) {
									xlogP += 0.466;
									// logger.debug("XLOGP: 22 0.466");
								}
								if (getPiSystemsCount(ac, atomi) == 1) {
									xlogP += 0.136;
									// logger.debug("XLOGP: 23 0.136");
								}
							} else {
								if (getPiSystemsCount(ac, atomi) == 0) {
									xlogP += 0.001;
									// logger.debug("XLOGP: 24 0.001");
								}
								if (getPiSystemsCount(ac, atomi) == 1) {
									xlogP -= 0.31;
									// logger.debug("XLOGP: 25 -0.31");
								}
							}
						}
						if (hsCount == 2) {
							xlogP += 0.42;
							// logger.debug("XLOGP: 21 0.42");
						}
						if (getIfCarbonIsHydrophobic(ac, atomi)) {
							xlogP += 0.211;
							// logger.debug("XLOGP: Hydrophobic Carbon 0.211");
						}
					} // sp2 NOT aromatic
				}

				if (bondCount == 4) {
					// C sp3
					if (hsCount == 0) {
						if (getAtomTypeXCount(ac, atomi) == 0) {
							if (getPiSystemsCount(ac, atomi) == 0) {
								xlogP -= 0.006;
								// logger.debug("XLOGP: 16 -0.006");
							}
							if (getPiSystemsCount(ac, atomi) == 1) {
								xlogP -= 0.57;
								// logger.debug("XLOGP: 17 -0.57");
							}
							if (getPiSystemsCount(ac, atomi) >= 2) {
								xlogP -= 0.317;
								// logger.debug("XLOGP: 18 -0.317");
							}
						} else {
							if (getPiSystemsCount(ac, atomi) == 0) {
								xlogP -= 0.316;
								// logger.debug("XLOGP: 19 -0.316");
							} else {
								xlogP -= 0.723;
								// logger.debug("XLOGP: 20 -0.723");
							}
						}
					}
					if (hsCount == 1) {
						if (getAtomTypeXCount(ac, atomi) == 0) {
							if (getPiSystemsCount(ac, atomi) == 0) {
								xlogP += 0.127;
								// logger.debug("XLOGP: 10 0.127");
							}
							if (getPiSystemsCount(ac, atomi) == 1) {
								xlogP -= 0.243;
								// logger.debug("XLOGP: 11 -0.243");
							}
							if (getPiSystemsCount(ac, atomi) >= 2) {
								xlogP -= 0.499;
								// logger.debug("XLOGP: 12 -0.499");
							}
						} else {
							if (getPiSystemsCount(ac, atomi) == 0) {
								xlogP -= 0.205;
								// logger.debug("XLOGP: 13 -0.205");
							}
							if (getPiSystemsCount(ac, atomi) == 1) {
								xlogP -= 0.305;
								// logger.debug("XLOGP: 14 -0.305");
							}
							if (getPiSystemsCount(ac, atomi) >= 2) {
								xlogP -= 0.709;
								// logger.debug("XLOGP: 15 -0.709");
							}
						}
					}
					if (hsCount == 2) {
						if (getAtomTypeXCount(ac, atomi) == 0) {
							if (getPiSystemsCount(ac, atomi) == 0) {
								xlogP += 0.358;
								// logger.debug("XLOGP: 4 0.358");
							}
							if (getPiSystemsCount(ac, atomi) == 1) {
								xlogP -= 0.008;
								// logger.debug("XLOGP: 5 -0.008");
							}
							if (getPiSystemsCount(ac, atomi) == 2) {
								xlogP -= 0.185;
								// logger.debug("XLOGP: 6 -0.185");
							}
						} else {
							if (getPiSystemsCount(ac, atomi) == 0) {
								xlogP -= 0.137;
								// logger.debug("XLOGP: 7 -0.137");
							}
							if (getPiSystemsCount(ac, atomi) == 1) {
								xlogP -= 0.303;
								// logger.debug("XLOGP: 8 -0.303");
							}
							if (getPiSystemsCount(ac, atomi) == 2) {
								xlogP -= 0.815;
								// logger.debug("XLOGP: 9 -0.815");
							}
						}
					}
					if (hsCount > 2) {
						if (getAtomTypeXCount(ac, atomi) == 0) {
							if (getPiSystemsCount(ac, atomi) == 0) {
								xlogP += 0.528;
								// logger.debug("XLOGP: 1 0.528");
							}
							if (getPiSystemsCount(ac, atomi) == 1) {
								xlogP += 0.267;
								// logger.debug("XLOGP: 2 0.267");
							}
						} else {
							// if (getNitrogenOrOxygenCount(ac, atomi) == 1) {
							xlogP -= 0.032;
							// logger.debug("XLOGP: 3 -0.032");
						}
					}
					if (getIfCarbonIsHydrophobic(ac, atomi)) {
						xlogP += 0.211;
						// logger.debug("XLOGP: Hydrophobic Carbon 0.211");
					}
				} // csp3

			} // C

			if (symbol.equals("N")) {
				// NO2
				if (ac.getBondOrderSum(atomi) >= 3.0 && getOxygenCount(ac, atomi) >= 2
						&& maxBondOrder == IBond.Order.DOUBLE) {
					xlogP += 1.178;
					// logger.debug("XLOGP: 66 1.178");
				} else {
					if (getPresenceOfCarbonil(ac, atomi) >= 1) {
						// amidic nitrogen
						if (hsCount == 0) {
							if (getAtomTypeXCount(ac, atomi) == 0) {
								xlogP += 0.078;
								// logger.debug("XLOGP: 57 0.078");
							}
							if (getAtomTypeXCount(ac, atomi) == 1) {
								xlogP -= 0.118;
								// logger.debug("XLOGP: 58 -0.118");
							}
						}
						if (hsCount == 1) {
							if (getAtomTypeXCount(ac, atomi) == 0) {
								xlogP -= 0.096;
								hBondDonorsList.add(i);
								// logger.debug("XLOGP: 55 -0.096");
							} else {
								xlogP -= 0.044;
								hBondDonorsList.add(i);
								// logger.debug("XLOGP: 56 -0.044");
							}
						}
						if (hsCount == 2) {
							xlogP -= 0.646;
							hBondDonorsList.add(i);
							// logger.debug("XLOGP: 54 -0.646");
						}
					} else {// NO amidic nitrogen
						if (bondCount == 1) {
							// -C#N
							if (getCarbonsCount(ac, atomi) == 1) {
								xlogP -= 0.566;
								// logger.debug("XLOGP: 68 -0.566");
							}
						} else if (bondCount == 2) {
							// N sp2
							if ((Boolean) atomi.getProperty("IS_IN_AROMATIC_RING")) {
								xlogP -= 0.493;
								// logger.debug("XLOGP: 67 -0.493");
								if (checkAminoAcid != 0) {
									checkAminoAcid += 1;
								}
							} else {
								if (getDoubleBondedCarbonsCount(ac, atomi) == 0) {
									if (getDoubleBondedNitrogenCount(ac, atomi) == 0) {
										if (getDoubleBondedOxygenCount(ac, atomi) == 1) {
											xlogP += 0.427;
											// logger.debug("XLOGP: 65 0.427");
										}
									}
									if (getDoubleBondedNitrogenCount(ac, atomi) == 1) {
										if (getAtomTypeXCount(ac, atomi) == 0) {
											xlogP += 0.536;
											// logger.debug("XLOGP: 63 0.536");
										}
										if (getAtomTypeXCount(ac, atomi) == 1) {
											xlogP -= 0.597;
											// logger.debug("XLOGP: 64 -0.597");
										}
									}
								} else if (getDoubleBondedCarbonsCount(ac, atomi) == 1) {
									if (getAtomTypeXCount(ac, atomi) == 0) {
										if (getPiSystemsCount(ac, atomi) == 0) {
											xlogP += 0.007;
											// logger.debug("XLOGP: 59 0.007");
										}
										if (getPiSystemsCount(ac, atomi) == 1) {
											xlogP -= 0.275;
											// logger.debug("XLOGP: 60 -0.275");
										}
									} else if (getAtomTypeXCount(ac, atomi) == 1) {
										if (getPiSystemsCount(ac, atomi) == 0) {
											xlogP += 0.366;
											// logger.debug("XLOGP: 61 0.366");
										}
										if (getPiSystemsCount(ac, atomi) == 1) {
											xlogP += 0.251;
											// logger.debug("XLOGP: 62 0.251");
										}
									}
								}
							}
						} else if (bondCount == 3) {
							// N sp3
							if (hsCount == 0) {
								// if (rs.contains(atomi)&&ringSize>3) {
								if (atomi.getFlag(CDKConstants.ISAROMATIC) || (sssrRs.contains(atomi)
										&& (Integer) atomi.getProperty(CDKConstants.PART_OF_RING_OF_SIZE) > 3
										&& getPiSystemsCount(ac, atomi) >= 1)) {
									if (getAtomTypeXCount(ac, atomi) == 0) {
										xlogP += 0.881;
										// logger.debug("XLOGP: 51 0.881");
									} else {
										xlogP -= 0.01;
										// logger.debug("XLOGP: 53 -0.01");
									}
								} else {
									if (getAtomTypeXCount(ac, atomi) == 0) {
										if (getPiSystemsCount(ac, atomi) == 0) {
											xlogP += 0.159;
											// logger.debug("XLOGP: 49 0.159");
										}
										if (getPiSystemsCount(ac, atomi) > 0) {
											xlogP += 0.761;
											// logger.debug("XLOGP: 50 0.761");
										}
									} else {
										xlogP -= 0.239;
										// logger.debug("XLOGP: 52 -0.239");
									}
								}
							} else if (hsCount == 1) {
								if (getAtomTypeXCount(ac, atomi) == 0) {
									// like pyrrole
									if (atomi.getFlag(CDKConstants.ISAROMATIC) || (sssrRs.contains(atomi)
											&& (Integer) atomi.getProperty(CDKConstants.PART_OF_RING_OF_SIZE) > 3
											&& getPiSystemsCount(ac, atomi) >= 2)) {
										xlogP += 0.545;
										hBondDonorsList.add(i);
										// logger.debug("XLOGP: 46 0.545");
									} else {
										if (getPiSystemsCount(ac, atomi) == 0) {
											xlogP -= 0.112;
											hBondDonorsList.add(i);
											// logger.debug("XLOGP: 44 -0.112");
										}
										if (getPiSystemsCount(ac, atomi) > 0) {
											xlogP += 0.166;
											hBondDonorsList.add(i);
											// logger.debug("XLOGP: 45 0.166");
										}
									}
								} else {
									if (sssrRs.contains(atomi)) {
										xlogP += 0.153;
										hBondDonorsList.add(i);
										// logger.debug("XLOGP: 48 0.153");
									} else {
										xlogP += 0.324;
										hBondDonorsList.add(i);
										// logger.debug("XLOGP: 47 0.324");
									}
								}
							} else if (hsCount == 2) {
								if (getAtomTypeXCount(ac, atomi) == 0) {
									if (getPiSystemsCount(ac, atomi) == 0) {
										xlogP -= 0.534;
										hBondDonorsList.add(i);
										// logger.debug("XLOGP: 41 -0.534");
									}
									if (getPiSystemsCount(ac, atomi) == 1) {
										xlogP -= 0.329;
										hBondDonorsList.add(i);
										// logger.debug("XLOGP: 42 -0.329");
									}

									if (checkAminoAcid != 0) {
										checkAminoAcid += 1;
									}
								} else {
									xlogP -= 1.082;
									hBondDonorsList.add(i);
									// logger.debug("XLOGP: 43 -1.082");
								}
							}
						}
					}
				}
			}
			if (symbol.equals("O")) {
				if (bondCount == 1 && maxBondOrder == IBond.Order.DOUBLE) {
					xlogP -= 0.399;
					if (!getPresenceOfHydroxy(ac, atomi)) {
						hBondAcceptorsList.add(i);
					}
					// logger.debug("XLOGP: 75 A=O -0.399");
				} else if (bondCount == 1 && hsCount == 0
						&& (getPresenceOfNitro(ac, atomi) || getPresenceOfCarbonil(ac, atomi) == 1)
						|| getPresenceOfSulfat(ac, atomi)) {
					xlogP -= 0.399;
					if (!getPresenceOfHydroxy(ac, atomi)) {
						hBondAcceptorsList.add(i);
					}
					// logger.debug("XLOGP: 75 A=O -0.399");
				} else if (bondCount >= 1) {
					if (hsCount == 0 && bondCount == 2) {
						if (getAtomTypeXCount(ac, atomi) == 0) {
							if (getPiSystemsCount(ac, atomi) == 0) {
								xlogP += 0.084;
								// logger.debug("XLOGP: 72 R-O-R 0.084");
							}
							if (getPiSystemsCount(ac, atomi) > 0) {
								xlogP += 0.435;
								// logger.debug("XLOGP: 73 R-O-R.1 0.435");
							}
						} else if (getAtomTypeXCount(ac, atomi) == 1) {
							xlogP += 0.105;
							// logger.debug("XLOGP: 74 R-O-X 0.105");
						}
					} else {
						if (getAtomTypeXCount(ac, atomi) == 0) {
							if (getPiSystemsCount(ac, atomi) == 0) {
								xlogP -= 0.467;
								hBondDonorsList.add(i);
								hBondAcceptorsList.add(i);
								// logger.debug("XLOGP: 69 R-OH -0.467");
							}
							if (getPiSystemsCount(ac, atomi) == 1) {
								xlogP += 0.082;
								hBondDonorsList.add(i);
								hBondAcceptorsList.add(i);
								// logger.debug("XLOGP: 70 R-OH.1 0.082");
							}
						} else if (getAtomTypeXCount(ac, atomi) == 1) {
							xlogP -= 0.522;
							hBondDonorsList.add(i);
							hBondAcceptorsList.add(i);
							// logger.debug("XLOGP: 71 X-OH -0.522");
						}
					}
				}
			}
			if (symbol.equals("S")) {
				if ((bondCount == 1 && maxBondOrder == IBond.Order.DOUBLE)
						|| (bondCount == 1 && atomi.getFormalCharge() == -1)) {
					xlogP -= 0.148;
					// logger.debug("XLOGP: 78 A=S -0.148");
				} else if (bondCount == 2) {
					if (hsCount == 0) {
						xlogP += 0.255;
						// logger.debug("XLOGP: 77 A-S-A 0.255");
					} else {
						xlogP += 0.419;
						// logger.debug("XLOGP: 76 A-SH 0.419");
					}
				} else if (bondCount == 3) {
					if (getOxygenCount(ac, atomi) >= 1) {
						xlogP -= 1.375;
						// logger.debug("XLOGP: 79 A-SO-A -1.375");
					}
				} else if (bondCount == 4) {
					if (getDoubleBondedOxygenCount(ac, atomi) >= 2) {
						xlogP -= 0.168;
						// logger.debug("XLOGP: 80 A-SO2-A -0.168");
					}
				}
			}
			if (symbol.equals("P")) {
				if (getDoubleBondedSulfurCount(ac, atomi) >= 1 && bondCount >= 4) {
					xlogP += 1.253;
					// logger.debug("XLOGP: 82 S=PA3 1.253");
				} else if (getOxygenCount(ac, atomi) >= 1
						|| getDoubleBondedOxygenCount(ac, atomi) == 1 && bondCount >= 4) {
					xlogP -= 0.447;
					// logger.debug("XLOGP: 81 O=PA3 -0.447");
				}
			}
			if (symbol.equals("F")) {
				if (getPiSystemsCount(ac, atomi) == 0) {
					xlogP += 0.375;
					// logger.debug("XLOGP: 83 F.0 0.512");
				} else if (getPiSystemsCount(ac, atomi) == 1) {
					xlogP += 0.202;
					// logger.debug("XLOGP: 84 F.1 0.202");
				}
			}
			if (symbol.equals("Cl")) {
				if (getPiSystemsCount(ac, atomi) == 0) {
					xlogP += 0.512;
					// logger.debug("XLOGP: 85 Cl.0 0.512");
				} else if (getPiSystemsCount(ac, atomi) >= 1) {
					xlogP += 0.663;
					// logger.debug("XLOGP: 86 Cl.1 0.663");
				}
			}
			if (symbol.equals("Br")) {
				if (getPiSystemsCount(ac, atomi) == 0) {
					xlogP += 0.85;
					// logger.debug("XLOGP: 87 Br.0 0.85");
				} else if (getPiSystemsCount(ac, atomi) == 1) {
					xlogP += 0.839;
					// logger.debug("XLOGP: 88 Br.1 0.839");
				}
			}
			if (symbol.equals("I")) {
				if (getPiSystemsCount(ac, atomi) == 0) {
					xlogP += 1.05;
					// logger.debug("XLOGP: 89 I.0 1.05");
				} else if (getPiSystemsCount(ac, atomi) == 1) {
					xlogP += 1.109;
					// logger.debug("XLOGP: 90 I.1 1.109");
				}
			}

			// Halogen pair 1-3
			int halcount = getHalogenCount(ac, atomi);
			if (halcount == 2) {
				xlogP += 0.137;
				// logger.debug("XLOGP: Halogen 1-3 pair 0.137");
			} else if (halcount == 3) {
				xlogP += (3 * 0.137);
				// logger.debug("XLOGP: Halogen 1-3 pair 0.411");
			} else if (halcount == 4) {
				xlogP += (6 * 0.137);
				// logger.debug("XLOGP: Halogen 1-3 pair 1.902");
			}

			// sp2 Oxygen 1-5 pair
			if (getPresenceOfCarbonil(ac, atomi) == 2) {// sp2 oxygen 1-5 pair
				if (!sssrRs.contains(atomi)) {
					xlogP += 0.580;
					// logger.debug("XLOGP: sp2 Oxygen 1-5 pair 0.580");
				}
			}
		}
		// logger.debug("XLOGP: Before Correction:"+xlogP);
		int[][] pairCheck = null;
		// //logger.debug("Acceptors:"+hBondAcceptors.size()+"
		// Donors:"+hBondDonors.size());
		if (hBondAcceptorsList.size() > 0 && hBondDonorsList.size() > 0) {
			pairCheck = initializeHydrogenPairCheck(new int[atomCount][atomCount]);
		}
		AllPairsShortestPaths apsp = new AllPairsShortestPaths(ac);
		for (int i = 0; i < hBondAcceptorsList.size(); i++) {
			for (int j = 0; j < hBondDonorsList.size(); j++) {
				if (checkRingLink(sssrRs, ac, ac.getAtom(hBondAcceptorsList.get(i)))
						|| checkRingLink(sssrRs, ac, ac.getAtom(hBondDonorsList.get(j).intValue()))) {
					int dist = apsp.from(ac.getAtom(hBondAcceptorsList.get(i)))
							.distanceTo(ac.getAtom(hBondDonorsList.get(j)));
					// //logger.debug("
					// Acc:"+checkRingLink(rs,ac,atoms[((Integer)hBondAcceptors.get(i)).intValue()])
					// +" S:"+atoms[((Integer)hBondAcceptors.get(i)).intValue()].getSymbol()
					// +" Nr:"+((Integer)hBondAcceptors.get(i)).intValue()
					// +" Don:"+checkRingLink(rs,ac,atoms[((Integer)hBondDonors.get(j)).intValue()])
					// +" S:"+atoms[((Integer)hBondDonors.get(j)).intValue()].getSymbol()
					// +" Nr:"+((Integer)hBondDonors.get(j)).intValue()
					// +" i:"+i+" j:"+j+" path:"+path.size());
					if (checkRingLink(sssrRs, ac, ac.getAtom(hBondAcceptorsList.get(i)))
							&& checkRingLink(sssrRs, ac, ac.getAtom(hBondDonorsList.get(j).intValue()))) {
						if (dist == 3 && pairCheck[hBondAcceptorsList.get(i)][hBondDonorsList.get(j)] == 0) {
							xlogP += 0.429;
							pairCheck[hBondAcceptorsList.get(i)][hBondDonorsList.get(j)] = 1;
							pairCheck[hBondDonorsList.get(j)][hBondAcceptorsList.get(i)] = 1;
							// logger.debug("XLOGP: Internal HBonds 1-4 0.429");
						}
					} else {
						if (dist == 4 && pairCheck[hBondAcceptorsList.get(i)][hBondDonorsList.get(j)] == 0) {
							xlogP += 0.429;
							pairCheck[hBondAcceptorsList.get(i)][hBondDonorsList.get(j)] = 1;
							pairCheck[hBondDonorsList.get(j)][hBondAcceptorsList.get(i)] = 1;
							// logger.debug("XLOGP: Internal HBonds 1-5 0.429");
						}
					}
				}
			}
		}

		UniversalIsomorphismTester universalIsomorphismTester = new UniversalIsomorphismTester();
		if (checkAminoAcid > 1) {
			// alpha amino acid
			QueryAtomContainer aminoAcid = QueryAtomContainerCreator
					.createBasicQueryContainer(createAminoAcid(ac.getBuilder()));

			Iterator bonds = aminoAcid.bonds().iterator();
			IAtom bondAtom0 = null;
			IAtom bondAtom1 = null;
			while (bonds.hasNext()) {
				IBond bond = (IBond) bonds.next();
				bondAtom0 = bond.getBegin();
				bondAtom1 = bond.getEnd();
				if ((bondAtom0.getSymbol().equals("C") && bondAtom1.getSymbol().equals("N"))
						|| (bondAtom0.getSymbol().equals("N") && bondAtom1.getSymbol().equals("C"))
								&& bond.getOrder() == IBond.Order.SINGLE) {
					aminoAcid.removeBond(bondAtom0, bondAtom1);
					QueryBond qbond = new QueryBond(bondAtom0, bondAtom1, Expr.Type.SINGLE_OR_AROMATIC);
					aminoAcid.addBond(qbond);
					break;
				}
			}

			// AtomContainer aminoacid = sp.parseSmiles("NCC(=O)O");
			try {
				if (universalIsomorphismTester.isSubgraph(ac, aminoAcid)) {
					List list = universalIsomorphismTester.getSubgraphAtomsMap(ac, aminoAcid);
					RMap map = null;
					IAtom atom1 = null;
					for (int j = 0; j < list.size(); j++) {
						map = (RMap) list.get(j);
						atom1 = ac.getAtom(map.getId1());
						if (atom1.getSymbol().equals("O") && ac.getMaximumBondOrder(atom1) == IBond.Order.SINGLE) {
							if (ac.getConnectedBondsCount(atom1) == 2 && getHydrogenCount(ac, atom1) == 0) {
							} else {
								xlogP -= 2.166;
								// logger.debug("XLOGP: alpha amino acid -2.166");
								break;
							}
						}
					}
				}
			} catch (CDKException e) {
				// return getDummyDescriptorValue(e);
			}
		}

		IAtomContainer paba = createPaba(ac.getBuilder());
		// p-amino sulphonic acid
		try {
			if (universalIsomorphismTester.isSubgraph(ac, paba)) {
				xlogP -= 0.501;
				// logger.debug("XLOGP: p-amino sulphonic acid -0.501");
			}
		} catch (CDKException e) {
			// return getDummyDescriptorValue(e);
		}

		// salicylic acid
		if (salicylFlag) {
			IAtomContainer salicilic = createSalicylicAcid(ac.getBuilder());
			try {
				if (universalIsomorphismTester.isSubgraph(ac, salicilic)) {
					xlogP += 0.554;
					// logger.debug("XLOGP: salicylic acid 0.554");
				}
			} catch (CDKException e) {
				// return getDummyDescriptorValue(e);
			}
		}

		// ortho oxygen pair
		SmartsPattern orthopair = SmartsPattern.create("OccO");
		if (orthopair.matches(ac)) {
			xlogP -= 0.268;
			// logger.debug("XLOGP: Ortho oxygen pair -0.268");
		}
	}

	private void stereoElementCount(Iterable<IStereoElement> stereo) {
		for (IStereoElement se : stereo) {
			seCount++;
		}
	}

	private void calculateRingProperties(IRingSet sssrRs) {
		for (IAtomContainer ac2 : sssrRs.atomContainers()) {
			if (ac2.getAtom(0).isAromatic()) {
				aromaticRingCount += 1;
			} else
				aliphaticRingCount += 1;
		}
	}

	private void calculateROProperties() {
		lipinskifailures3 = 0;
		lipinskifailures5 = 0;

		if (xlogP > 3.0) {
			lipinskifailures3 += 1;
			if (xlogP > 5.0)
				lipinskifailures5 += 1;
		}
		if (hBondAcceptors > 3.0) {
			lipinskifailures3 += 1;
			if (hBondAcceptors > 10.0)
				lipinskifailures5 += 1;
		}
		if (hBondDonors > 3) {
			lipinskifailures3 += 1;
			if (hBondDonors > 5.0)
				lipinskifailures5 += 1;
		}
		if (weight > 300.0) {
			lipinskifailures3 += 1;
			if (weight > 500.0)
				lipinskifailures5 += 1;
		}
		if (rotatablebonds > 3.0) {
			lipinskifailures3 += 1;
			if (rotatablebonds > 10.0)
				lipinskifailures5 += 1;
		}
	}

	private void configTPSAMap() {
		if (tpsaMap == null) {
			tpsaMap = new HashMap<String, Double>();
			// contributions:
			// every contribution is given by an atom profile;
			// positions in atom profile strings are: symbol, max-bond-order,
			// bond-order-sum,
			// number-of-neighbours, Hcount, formal charge, aromatic-bonds,
			// is-in-3-membered-ring,
			// single-bonds, double-bonds, triple-bonds.

			tpsaMap.put("N+1.0+3.0+3+0+0+0+0+3+0+0", new Double(3.24)); // 1
			tpsaMap.put("N+2.0+3.0+2+0+0+0+0+1+1+0", new Double(12.36)); // 2
			tpsaMap.put("N+3.0+3.0+1+0+0+0+0+0+0+1", new Double(23.79)); // 3
			tpsaMap.put("N+2.0+5.0+3+0+0+0+0+1+2+0", new Double(11.68)); // 4
			tpsaMap.put("N+3.0+5.0+2+0+0+0+0+0+1+1", new Double(13.6)); // 5
			tpsaMap.put("N+1.0+3.0+3+0+0+0+1+3+0+0", new Double(3.01)); // 6
			tpsaMap.put("N+1.0+3.0+3+1+0+0+0+3+0+0", new Double(12.03)); // 7
			tpsaMap.put("N+1.0+3.0+3+1+0+0+1+3+0+0", new Double(21.94)); // 8
			tpsaMap.put("N+2.0+3.0+2+1+0+0+0+1+1+0", new Double(23.85)); // 9
			tpsaMap.put("N+1.0+3.0+3+2+0+0+0+3+0+0", new Double(26.02)); // 10
			tpsaMap.put("N+1.0+4.0+4+0+1+0+0+4+0+0", new Double(0.0)); // 11
			tpsaMap.put("N+2.0+4.0+3+0+1+0+0+2+1+0", new Double(3.01)); // 12
			tpsaMap.put("N+3.0+4.0+2+0+1+0+0+1+0+1", new Double(4.36)); // 13
			tpsaMap.put("N+1.0+4.0+4+1+1+0+0+4+0+0", new Double(4.44)); // 14
			tpsaMap.put("N+2.0+4.0+3+1+1+0+0+2+1+0", new Double(13.97)); // 15
			tpsaMap.put("N+1.0+4.0+4+2+1+0+0+4+0+0", new Double(16.61)); // 16
			tpsaMap.put("N+2.0+4.0+3+2+1+0+0+2+1+0", new Double(25.59)); // 17
			tpsaMap.put("N+1.0+4.0+4+3+1+0+0+4+0+0", new Double(27.64)); // 18
			tpsaMap.put("N+1.5+3.0+2+0+0+2+0+0+0+0", new Double(12.89)); // 19
			tpsaMap.put("N+1.5+4.5+3+0+0+3+0+0+0+0", new Double(4.41)); // 20
			tpsaMap.put("N+1.5+4.0+3+0+0+2+0+1+0+0", new Double(4.93)); // 21
			tpsaMap.put("N+2.0+5.0+3+0+0+2+0+0+1+0", new Double(8.39)); // 22
			tpsaMap.put("N+1.5+4.0+3+1+0+2+0+1+0+0", new Double(15.79)); // 23
			tpsaMap.put("N+1.5+4.5+3+0+1+3+0+0+0+0", new Double(4.1)); // 24
			tpsaMap.put("N+1.5+4.0+3+0+1+2+0+1+0+0", new Double(3.88)); // 25
			tpsaMap.put("N+1.5+4.0+3+1+1+2+0+1+0+0", new Double(14.14)); // 26

			tpsaMap.put("O+1.0+2.0+2+0+0+0+0+2+0+0", new Double(9.23)); // 27
			tpsaMap.put("O+1.0+2.0+2+0+0+0+1+2+0+0", new Double(12.53)); // 28
			tpsaMap.put("O+2.0+2.0+1+0+0+0+0+0+1+0", new Double(17.07)); // 29
			tpsaMap.put("O+1.0+1.0+1+0+-1+0+0+1+0+0", new Double(23.06)); // 30
			tpsaMap.put("O+1.0+2.0+2+1+0+0+0+2+0+0", new Double(20.23)); // 31
			tpsaMap.put("O+1.5+3.0+2+0+0+2+0+0+0+0", new Double(13.14)); // 32

			tpsaMap.put("S+1.0+2.0+2+0+0+0+0+2+0+0", new Double(25.3)); // 33
			tpsaMap.put("S+2.0+2.0+1+0+0+0+0+0+1+0", new Double(32.09)); // 34
			tpsaMap.put("S+2.0+4.0+3+0+0+0+0+2+1+0", new Double(19.21)); // 35
			tpsaMap.put("S+2.0+6.0+4+0+0+0+0+2+2+0", new Double(8.38)); // 36
			tpsaMap.put("S+1.0+2.0+2+1+0+0+0+2+0+0", new Double(38.8)); // 37
			tpsaMap.put("S+1.5+3.0+2+0+0+2+0+0+0+0", new Double(28.24)); // 38
			tpsaMap.put("S+2.0+5.0+3+0+0+2+0+0+1+0", new Double(21.7)); // 39

			tpsaMap.put("P+1.0+3.0+3+0+0+0+0+3+0+0", new Double(13.59)); // 40
			tpsaMap.put("P+2.0+3.0+3+0+0+0+0+1+1+0", new Double(34.14)); // 41
			tpsaMap.put("P+2.0+5.0+4+0+0+0+0+3+1+0", new Double(9.81)); // 42
			tpsaMap.put("P+2.0+5.0+4+1+0+0+0+3+1+0", new Double(23.47)); // 43
		}
	}

	/**
	 * Method initialise the HydrogenpairCheck with a value
	 *
	 * @param pairCheck value
	 * @return void
	 */
	private int[][] initializeHydrogenPairCheck(int[][] pairCheck) {
		for (int i = 0; i < pairCheck.length; i++) {
			for (int j = 0; j < pairCheck[0].length; j++) {
				pairCheck[i][j] = 0;
			}
		}
		return pairCheck;
	}

	/**
	 * Check if atom or neighbour atom is part of a ring
	 *
	 * @param ac   Description of the Parameter
	 * @param atom Description of the Parameter
	 * @return The hydrogenCount value
	 */
	private boolean checkRingLink(IRingSet ringSet, IAtomContainer ac, IAtom atom) {
		List<IAtom> neighbours = ac.getConnectedAtomsList(atom);
		if (ringSet.contains(atom)) {
			return true;
		}
		for (IAtom neighbour : neighbours) {
			if (ringSet.contains(neighbour)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the hydrogenCount attribute of the XLogPDescriptor object.
	 *
	 * @param ac   Description of the Parameter
	 * @param atom Description of the Parameter
	 * @return The hydrogenCount value
	 */
	private int getHydrogenCount(IAtomContainer ac, IAtom atom) {
		List<IAtom> neighbours = ac.getConnectedAtomsList(atom);
		int hcounter = 0;
		for (IAtom neighbour : neighbours) {
			if (neighbour.getSymbol().equals("H")) {
				hcounter += 1;
			}
		}
		return hcounter;
	}

	/**
	 * Gets the HalogenCount attribute of the XLogPDescriptor object.
	 *
	 * @param ac   Description of the Parameter
	 * @param atom Description of the Parameter
	 * @return The alogenCount value
	 */
	private int getHalogenCount(IAtomContainer ac, IAtom atom) {
		List<IAtom> neighbours = ac.getConnectedAtomsList(atom);
		int acounter = 0;
		for (IAtom neighbour : neighbours) {
			if (neighbour.getSymbol().equals("F") || neighbour.getSymbol().equals("I")
					|| neighbour.getSymbol().equals("Cl") || neighbour.getSymbol().equals("Br")) {
				acounter += 1;
			}
		}
		return acounter;
	}

	/**
	 * Gets the atomType X Count attribute of the XLogPDescriptor object.
	 *
	 * @param ac   Description of the Parameter
	 * @param atom Description of the Parameter
	 * @return The nitrogenOrOxygenCount value
	 */
	private int getAtomTypeXCount(IAtomContainer ac, IAtom atom) {
		List<IAtom> neighbours = ac.getConnectedAtomsList(atom);
		int nocounter = 0;
		IBond bond;
		for (IAtom neighbour : neighbours) {
			if ((neighbour.getSymbol().equals("N") || neighbour.getSymbol().equals("O"))
					&& !(Boolean) neighbour.getProperty("IS_IN_AROMATIC_RING")) {
				// if (ac.getMaximumBondOrder(neighbours[i]) == 1.0) {
				bond = ac.getBond(neighbour, atom);
				if (bond.getOrder() != IBond.Order.DOUBLE) {
					nocounter += 1;
				}
			}
		}
		return nocounter;
	}

	/**
	 * Gets the aromaticCarbonsCount attribute of the XLogPDescriptor object.
	 *
	 * @param ac   Description of the Parameter
	 * @param atom Description of the Parameter
	 * @return The aromaticCarbonsCount value
	 */
	private int getAromaticCarbonsCount(IAtomContainer ac, IAtom atom) {
		List<IAtom> neighbours = ac.getConnectedAtomsList(atom);
		int carocounter = 0;
		for (IAtom neighbour : neighbours) {
			if (neighbour.getSymbol().equals("C") && neighbour.getFlag(CDKConstants.ISAROMATIC)) {
				carocounter += 1;
			}
		}
		return carocounter;
	}

	/**
	 * Gets the carbonsCount attribute of the XLogPDescriptor object.
	 *
	 * @param ac   Description of the Parameter
	 * @param atom Description of the Parameter
	 * @return The carbonsCount value
	 */
	private int getCarbonsCount(IAtomContainer ac, IAtom atom) {
		List<IAtom> neighbours = ac.getConnectedAtomsList(atom);
		int ccounter = 0;
		for (IAtom neighbour : neighbours) {
			if (neighbour.getSymbol().equals("C")) {
				if (!neighbour.getFlag(CDKConstants.ISAROMATIC)) {
					ccounter += 1;
				}
			}
		}
		return ccounter;
	}

	/**
	 * Gets the oxygenCount attribute of the XLogPDescriptor object.
	 *
	 * @param ac   Description of the Parameter
	 * @param atom Description of the Parameter
	 * @return The carbonsCount value
	 */
	private int getOxygenCount(IAtomContainer ac, IAtom atom) {
		List<IAtom> neighbours = ac.getConnectedAtomsList(atom);
		int ocounter = 0;
		for (IAtom neighbour : neighbours) {
			if (neighbour.getSymbol().equals("O")) {
				if (!neighbour.getFlag(CDKConstants.ISAROMATIC)) {
					ocounter += 1;
				}
			}
		}
		return ocounter;
	}

	/**
	 * Gets the doubleBondedCarbonsCount attribute of the XLogPDescriptor object.
	 *
	 * @param ac   Description of the Parameter
	 * @param atom Description of the Parameter
	 * @return The doubleBondedCarbonsCount value
	 */
	private int getDoubleBondedCarbonsCount(IAtomContainer ac, IAtom atom) {
		List<IAtom> neighbours = ac.getConnectedAtomsList(atom);
		IBond bond;
		int cdbcounter = 0;
		for (IAtom neighbour : neighbours) {
			if (neighbour.getSymbol().equals("C")) {
				bond = ac.getBond(neighbour, atom);
				if (bond.getOrder() == IBond.Order.DOUBLE) {
					cdbcounter += 1;
				}
			}
		}
		return cdbcounter;
	}

	/**
	 * Gets the doubleBondedOxygenCount attribute of the XLogPDescriptor object.
	 *
	 * @param ac   Description of the Parameter
	 * @param atom Description of the Parameter
	 * @return The doubleBondedOxygenCount value
	 */
	private int getDoubleBondedOxygenCount(IAtomContainer ac, IAtom atom) {
		List<IAtom> neighbours = ac.getConnectedAtomsList(atom);
		IBond bond;
		int odbcounter = 0;
		boolean chargeFlag = false;
		if (atom.getFormalCharge() >= 1) {
			chargeFlag = true;
		}
		for (IAtom neighbour : neighbours) {
			if (neighbour.getSymbol().equals("O")) {
				bond = ac.getBond(neighbour, atom);
				if (chargeFlag && neighbour.getFormalCharge() == -1 && bond.getOrder() == IBond.Order.SINGLE) {
					odbcounter += 1;
				}
				if (!neighbour.getFlag(CDKConstants.ISAROMATIC)) {
					if (bond.getOrder() == IBond.Order.DOUBLE) {
						odbcounter += 1;
					}
				}
			}
		}
		return odbcounter;
	}

	/**
	 * Gets the doubleBondedSulfurCount attribute of the XLogPDescriptor object.
	 *
	 * @param ac   Description of the Parameter
	 * @param atom Description of the Parameter
	 * @return The doubleBondedSulfurCount value
	 */
	private int getDoubleBondedSulfurCount(IAtomContainer ac, IAtom atom) {
		List<IAtom> neighbours = ac.getConnectedAtomsList(atom);
		IBond bond;
		int sdbcounter = 0;
		for (IAtom neighbour : neighbours) {
			if (neighbour.getSymbol().equals("S")) {
				if (atom.getFormalCharge() == 1 && neighbour.getFormalCharge() == -1) {
					sdbcounter += 1;
				}
				bond = ac.getBond(neighbour, atom);
				if (!neighbour.getFlag(CDKConstants.ISAROMATIC)) {
					if (bond.getOrder() == IBond.Order.DOUBLE) {
						sdbcounter += 1;
					}
				}
			}
		}
		return sdbcounter;
	}

	/**
	 * Gets the doubleBondedNitrogenCount attribute of the XLogPDescriptor object.
	 *
	 * @param ac   Description of the Parameter
	 * @param atom Description of the Parameter
	 * @return The doubleBondedNitrogenCount value
	 */
	private int getDoubleBondedNitrogenCount(IAtomContainer ac, IAtom atom) {
		List<IAtom> neighbours = ac.getConnectedAtomsList(atom);
		IBond bond;
		int ndbcounter = 0;
		for (IAtom neighbour : neighbours) {
			if (neighbour.getSymbol().equals("N")) {
				bond = ac.getBond(neighbour, atom);
				if (!neighbour.getFlag(CDKConstants.ISAROMATIC)) {
					if (bond.getOrder() == IBond.Order.DOUBLE) {
						ndbcounter += 1;
					}
				}
			}
		}
		return ndbcounter;
	}

	/**
	 * Gets the aromaticNitrogensCount attribute of the XLogPDescriptor object.
	 *
	 * @param ac   Description of the Parameter
	 * @param atom Description of the Parameter
	 * @return The aromaticNitrogensCount value
	 */
	private int getAromaticNitrogensCount(IAtomContainer ac, IAtom atom) {
		List<IAtom> neighbours = ac.getConnectedAtomsList(atom);
		int narocounter = 0;
		for (IAtom neighbour : neighbours) {
			if (neighbour.getSymbol().equals("N") && (Boolean) neighbour.getProperty("IS_IN_AROMATIC_RING")) {
				narocounter += 1;
			}
		}
		return narocounter;
	}

	// a piSystem is a double or triple or aromatic bond:
	/**
	 * Gets the piSystemsCount attribute of the XLogPDescriptor object.
	 *
	 * @param ac   Description of the Parameter
	 * @param atom Description of the Parameter
	 * @return The piSystemsCount value
	 */
	private int getPiSystemsCount(IAtomContainer ac, IAtom atom) {
		List neighbours = ac.getConnectedAtomsList(atom);
		int picounter = 0;
		List bonds = null;
		for (int i = 0; i < neighbours.size(); i++) {
			IAtom neighbour = (IAtom) neighbours.get(i);
			bonds = ac.getConnectedBondsList(neighbour);
			for (int j = 0; j < bonds.size(); j++) {
				IBond bond = (IBond) bonds.get(j);
				if (bond.getOrder() != IBond.Order.SINGLE && !bond.getOther(neighbour).equals(atom)
						&& !neighbour.getSymbol().equals("P") && !neighbour.getSymbol().equals("S")) {
					picounter += 1;
				} /*
					 * else if (bonds[j].getOther(neighbours[i])!=atom &&
					 * !neighbours[i].getSymbol().equals("P") &&
					 * !neighbours[i].getSymbol().equals("S") && bonds[j].getOther
					 * (neighbours[i]).getFlag(CDKConstants.ISAROMATIC)){ picounter += 1; }
					 */
			}
		}
		return picounter;
	}

	/**
	 * Gets the presenceOf Hydroxy group attribute of the XLogPDescriptor object.
	 *
	 * @param ac   Description of the Parameter
	 * @param atom Description of the Parameter
	 * @return The presenceOfCarbonil value
	 */
	private boolean getPresenceOfHydroxy(IAtomContainer ac, IAtom atom) {
		IAtom neighbour0 = (IAtom) ac.getConnectedAtomsList(atom).get(0);
		List first = null;
		if (neighbour0.getSymbol().equals("C")) {
			first = ac.getConnectedAtomsList(neighbour0);
			for (int i = 0; i < first.size(); i++) {
				IAtom conAtom = (IAtom) first.get(i);
				if (conAtom.getSymbol().equals("O")) {
					if (ac.getBond(neighbour0, conAtom).getOrder() == IBond.Order.SINGLE) {
						if (ac.getConnectedBondsCount(conAtom) > 1 && getHydrogenCount(ac, conAtom) == 0) {
							return false;
						} else {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Gets the presenceOfN=O attribute of the XLogPDescriptor object.
	 *
	 * @param ac   Description of the Parameter
	 * @param atom Description of the Parameter
	 * @return The presenceOfNitor [boolean]
	 */
	private boolean getPresenceOfNitro(IAtomContainer ac, IAtom atom) {
		List neighbours = ac.getConnectedAtomsList(atom);
		List second = null;
		IBond bond = null;
		// int counter = 0;
		for (int i = 0; i < neighbours.size(); i++) {
			IAtom neighbour = (IAtom) neighbours.get(i);
			if (neighbour.getSymbol().equals("N")) {
				second = ac.getConnectedAtomsList(neighbour);
				for (int b = 0; b < second.size(); b++) {
					IAtom conAtom = (IAtom) second.get(b);
					if (conAtom.getSymbol().equals("O")) {
						bond = ac.getBond(neighbour, conAtom);
						if (bond.getOrder() == IBond.Order.DOUBLE) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Gets the presenceOfSulfat A-S(O2)-A attribute of the XLogPDescriptor object.
	 *
	 * @param ac   Description of the Parameter
	 * @param atom Description of the Parameter
	 * @return The presenceOfSulfat [boolean]
	 */
	private boolean getPresenceOfSulfat(IAtomContainer ac, IAtom atom) {
		List<IAtom> neighbours = ac.getConnectedAtomsList(atom);
		// org.openscience.cdk.interfaces.IAtom[] second = null;
		// IBond bond = null;
		// int counter = 0;
		for (IAtom neighbour : neighbours) {
			if (neighbour.getSymbol().equals("S") && getOxygenCount(ac, neighbour) >= 2
					&& ac.getConnectedBondsCount(neighbour) == 4) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the presenceOfCarbonil attribute of the XLogPDescriptor object.
	 *
	 * @param ac   Description of the Parameter
	 * @param atom Description of the Parameter
	 * @return The presenceOfCarbonil value
	 */
	private int getPresenceOfCarbonil(IAtomContainer ac, IAtom atom) {
		List neighbours = ac.getConnectedAtomsList(atom);
		List second = null;
		IBond bond = null;
		int counter = 0;
		for (int i = 0; i < neighbours.size(); i++) {
			IAtom neighbour = (IAtom) neighbours.get(i);
			if (neighbour.getSymbol().equals("C")) {
				second = ac.getConnectedAtomsList(neighbour);
				for (int b = 0; b < second.size(); b++) {
					IAtom conAtom = (IAtom) second.get(b);
					if (conAtom.getSymbol().equals("O")) {
						bond = ac.getBond(neighbour, conAtom);
						if (bond.getOrder() == IBond.Order.DOUBLE) {
							counter += 1;
						}
					}
				}
			}
		}
		return counter;
	}

	/**
	 * Gets the ifCarbonIsHydrophobic attribute of the XLogPDescriptor object. C
	 * must be sp2 or sp3 and, for all distances C-1-2-3 only C atoms are permitted
	 *
	 * @param ac   Description of the Parameter
	 * @param atom Description of the Parameter
	 * @return The ifCarbonIsHydrophobic value
	 */
	private boolean getIfCarbonIsHydrophobic(IAtomContainer ac, IAtom atom) {
		List first = ac.getConnectedAtomsList(atom);
		List second = null;
		List third = null;
		// org.openscience.cdk.interfaces.IAtom[] fourth = null;
		if (first.size() > 0) {
			for (int i = 0; i < first.size(); i++) {
				IAtom firstAtom = (IAtom) first.get(i);
				if (firstAtom.getSymbol().equals("C") || firstAtom.getSymbol().equals("H")) {
				} else {
					return false;
				}
				second = ac.getConnectedAtomsList(firstAtom);
				if (second.size() > 0) {
					for (int b = 0; b < second.size(); b++) {
						IAtom secondAtom = (IAtom) second.get(b);
						if (secondAtom.getSymbol().equals("C") || secondAtom.getSymbol().equals("H")) {
						} else {
							return false;
						}
						third = ac.getConnectedAtomsList(secondAtom);
						if (third.size() > 0) {
							for (int c = 0; c < third.size(); c++) {
								IAtom thirdAtom = (IAtom) third.get(c);
								if (thirdAtom.getSymbol().equals("C") || thirdAtom.getSymbol().equals("H")) {
								} else {
									return false;
								}
								// fourth = ac.getConnectedAtoms(third[c]);
								// if (fourth.length > 0) {
								// for (int d = 0; d < fourth.length; d++) {
								// if (fourth[d].getSymbol().equals("C") || fourth[d].getSymbol().equals("H")) {
								// } else {
								// return false;
								// }
								// }
								// } else {
								// return false;
								// }
							}
						} else {
							return false;
						}
					}
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	private IAtomContainer createPaba(IChemObjectBuilder builder) {
		// SMILES CS(=O)(=O)c1ccc(N)cc1
		IAtomContainer container = builder.newInstance(IAtomContainer.class);
		IAtom atom1 = builder.newInstance(IAtom.class, "C");
		container.addAtom(atom1);
		IAtom atom2 = builder.newInstance(IAtom.class, "S");
		container.addAtom(atom2);
		IAtom atom3 = builder.newInstance(IAtom.class, "O");
		container.addAtom(atom3);
		IAtom atom4 = builder.newInstance(IAtom.class, "O");
		container.addAtom(atom4);
		IAtom atom5 = builder.newInstance(IAtom.class, "C");
		atom5.setHybridization(Hybridization.SP2);
		container.addAtom(atom5);
		IAtom atom6 = builder.newInstance(IAtom.class, "C");
		atom6.setHybridization(Hybridization.SP2);
		container.addAtom(atom6);
		IAtom atom7 = builder.newInstance(IAtom.class, "C");
		atom7.setHybridization(Hybridization.SP2);
		container.addAtom(atom7);
		IAtom atom8 = builder.newInstance(IAtom.class, "C");
		atom8.setHybridization(Hybridization.SP2);
		container.addAtom(atom8);
		IAtom atom9 = builder.newInstance(IAtom.class, "N");
		container.addAtom(atom9);
		IAtom atom10 = builder.newInstance(IAtom.class, "C");
		atom10.setHybridization(Hybridization.SP2);
		container.addAtom(atom10);
		IAtom atom11 = builder.newInstance(IAtom.class, "C");
		atom11.setHybridization(Hybridization.SP2);
		container.addAtom(atom11);
		IBond bond1 = builder.newInstance(IBond.class, atom1, atom2, Order.SINGLE);
		container.addBond(bond1);
		IBond bond2 = builder.newInstance(IBond.class, atom2, atom3, Order.DOUBLE);
		container.addBond(bond2);
		IBond bond3 = builder.newInstance(IBond.class, atom2, atom4, Order.DOUBLE);
		container.addBond(bond3);
		IBond bond4 = builder.newInstance(IBond.class, atom2, atom5, Order.SINGLE);
		container.addBond(bond4);
		IBond bond5 = builder.newInstance(IBond.class, atom5, atom6, Order.DOUBLE);
		bond5.setFlag(CDKConstants.ISAROMATIC, true);
		container.addBond(bond5);
		IBond bond6 = builder.newInstance(IBond.class, atom6, atom7, Order.SINGLE);
		bond6.setFlag(CDKConstants.ISAROMATIC, true);
		container.addBond(bond6);
		IBond bond7 = builder.newInstance(IBond.class, atom7, atom8, Order.DOUBLE);
		bond7.setFlag(CDKConstants.ISAROMATIC, true);
		container.addBond(bond7);
		IBond bond8 = builder.newInstance(IBond.class, atom8, atom9, Order.SINGLE);
		container.addBond(bond8);
		IBond bond9 = builder.newInstance(IBond.class, atom8, atom10, Order.SINGLE);
		bond9.setFlag(CDKConstants.ISAROMATIC, true);
		container.addBond(bond9);
		IBond bond10 = builder.newInstance(IBond.class, atom10, atom11, Order.DOUBLE);
		bond10.setFlag(CDKConstants.ISAROMATIC, true);
		container.addBond(bond10);
		IBond bond11 = builder.newInstance(IBond.class, atom5, atom11, Order.SINGLE);
		bond11.setFlag(CDKConstants.ISAROMATIC, true);
		container.addBond(bond11);

		return container;
	}

	private IAtomContainer createAminoAcid(IChemObjectBuilder builder) {
		// SMILES NCC(=O)O
		IAtomContainer container = builder.newInstance(IAtomContainer.class);
		IAtom atom1 = builder.newInstance(IAtom.class, "N");
		container.addAtom(atom1);
		IAtom atom2 = builder.newInstance(IAtom.class, "C");
		container.addAtom(atom2);
		IAtom atom3 = builder.newInstance(IAtom.class, "C"); // carbonyl
		container.addAtom(atom3);
		IAtom atom4 = builder.newInstance(IAtom.class, "O"); // carbonyl
		container.addAtom(atom4);
		IAtom atom5 = builder.newInstance(IAtom.class, "O");
		container.addAtom(atom5);
		container.addBond(builder.newInstance(IBond.class, atom1, atom2, Order.SINGLE));
		container.addBond(builder.newInstance(IBond.class, atom2, atom3, Order.SINGLE));
		container.addBond(builder.newInstance(IBond.class, atom3, atom4, Order.DOUBLE));
		container.addBond(builder.newInstance(IBond.class, atom3, atom5, Order.SINGLE));
		return container;
	}

	private IAtomContainer createSalicylicAcid(IChemObjectBuilder builder) {
		// SMILES O=C(O)c1ccccc1O
		IAtomContainer container = builder.newInstance(IAtomContainer.class);
		IAtom atom1 = builder.newInstance(IAtom.class, "C");
		container.addAtom(atom1);
		IAtom atom2 = builder.newInstance(IAtom.class, "O");
		container.addAtom(atom2);
		IAtom atom3 = builder.newInstance(IAtom.class, "O");
		container.addAtom(atom3);
		IAtom atom4 = builder.newInstance(IAtom.class, "C");
		atom4.setHybridization(Hybridization.SP2);
		container.addAtom(atom4);
		IAtom atom5 = builder.newInstance(IAtom.class, "C");
		atom5.setHybridization(Hybridization.SP2);
		container.addAtom(atom5);
		IAtom atom6 = builder.newInstance(IAtom.class, "C");
		atom6.setHybridization(Hybridization.SP2);
		container.addAtom(atom6);
		IAtom atom7 = builder.newInstance(IAtom.class, "C");
		atom7.setHybridization(Hybridization.SP2);
		container.addAtom(atom7);
		IAtom atom8 = builder.newInstance(IAtom.class, "C");
		atom8.setHybridization(Hybridization.SP2);
		container.addAtom(atom8);
		IAtom atom9 = builder.newInstance(IAtom.class, "C");
		atom9.setHybridization(Hybridization.SP2);
		container.addAtom(atom9);
		IAtom atom10 = builder.newInstance(IAtom.class, "O");
		container.addAtom(atom10);
		IBond bond1 = builder.newInstance(IBond.class, atom1, atom2, Order.DOUBLE);
		container.addBond(bond1);
		IBond bond2 = builder.newInstance(IBond.class, atom1, atom3, Order.SINGLE);
		container.addBond(bond2);
		IBond bond3 = builder.newInstance(IBond.class, atom1, atom4, Order.SINGLE);
		container.addBond(bond3);
		IBond bond4 = builder.newInstance(IBond.class, atom4, atom5, Order.DOUBLE);
		bond4.setFlag(CDKConstants.ISAROMATIC, true);
		container.addBond(bond4);
		IBond bond5 = builder.newInstance(IBond.class, atom5, atom6, Order.SINGLE);
		bond5.setFlag(CDKConstants.ISAROMATIC, true);
		container.addBond(bond5);
		IBond bond6 = builder.newInstance(IBond.class, atom6, atom7, Order.DOUBLE);
		bond6.setFlag(CDKConstants.ISAROMATIC, true);
		container.addBond(bond6);
		IBond bond7 = builder.newInstance(IBond.class, atom7, atom8, Order.SINGLE);
		bond7.setFlag(CDKConstants.ISAROMATIC, true);
		container.addBond(bond7);
		IBond bond8 = builder.newInstance(IBond.class, atom8, atom9, Order.DOUBLE);
		bond8.setFlag(CDKConstants.ISAROMATIC, true);
		container.addBond(bond8);
		IBond bond9 = builder.newInstance(IBond.class, atom9, atom4, Order.SINGLE);
		bond9.setFlag(CDKConstants.ISAROMATIC, true);
		container.addBond(bond9);
		IBond bond10 = builder.newInstance(IBond.class, atom9, atom10, Order.SINGLE);
		container.addBond(bond10);

		return container;
	}

	private void getInchi(IAtomContainer ac) {
		InChIGeneratorFactory factory;
		try {
			factory = InChIGeneratorFactory.getInstance();
			inchi = factory.getInChIGenerator(ac).getInchi();
		} catch (CDKException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getInchiKey(IAtomContainer ac) {
		InChIGeneratorFactory factory;
		try {
			factory = InChIGeneratorFactory.getInstance();
			inchikey = factory.getInChIGenerator(ac).getInchiKey();
		} catch (CDKException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the parameters attribute
	 * 0:elementName ; 1:includeTerminals ; 2:excludeAmides ; 3:checkAromaticity ; 4:salicylFlag
	 */
	public void setParameters(Object[] params) throws CDKException {
		if (params.length != 5) {
			throw new CDKException("Expects 5 parameters");
		}
		if (!(params[0] instanceof String)) {
			throw new CDKException("The first parameter must be of type String");
		}
		if (!(params[1] instanceof Boolean)) {
			throw new CDKException("The second parameter must be of type Boolean");
		}
		if (!(params[2] instanceof Boolean)) {
			throw new CDKException("The third parameter must be of type Boolean");
		}
		if (!(params[3] instanceof Boolean)) {
			throw new CDKException("The fourth parameter must be of type Boolean");
		}
		if (!(params[4] instanceof Boolean)) {
			throw new CDKException("The fifth parameter must be of type Boolean");
		}
		// ok, all should be fine
		elementName = (String) params[0];
		includeTerminals = (Boolean) params[1];
		excludeAmides = (Boolean) params[2];
		checkAromaticity = (Boolean) params[3];
		salicylFlag = (Boolean) params[4];
	}

	/**
	 * Gets the parameters attribute.
	 *
	 * @return The parameters value
	 * @see #setParameters
	 */
	public Object[] getParameters() {
		// return the parameters as used for the descriptor calculation
		Object[] params = new Object[5];
		params[0] = elementName;
		params[1] = includeTerminals;
		params[2] = excludeAmides;
		params[3] = checkAromaticity;
		params[4] = salicylFlag;

		return params;
	}

	public int gethBondDonors() {
		return hBondDonors;
	}

	public int gethBondAcceptors() {
		return hBondAcceptors;
	}

	public int getRotatablebonds() {
		return rotatablebonds;
	}

	public double getXlogP() {
		return xlogP;
	}

	public double getWeight() {
		return weight;
	}

	public int getLipinskifailures3() {
		return lipinskifailures3;
	}

	public int getLipinskifailures5() {
		return lipinskifailures5;
	}

	public int getHeavyAtomCount() {
		return heavyAtomCount;
	}

	public double getFsp3() {
		return fsp3;
	}

	public int getNsp2() {
		return nsp2;
	}

	public int getNsp3() {
		return nsp3;
	}

	public double getTpsa() {
		return tpsa;
	}

	public int getSeCount() {
		return seCount;
	}

	public double getFmf() {
		return fmf;
	}

	public int getSssrCount() {
		return sssrCount;
	}

	public int getAromaticRingCount() {
		return aromaticRingCount;
	}

	public int getAliphaticRingCount() {
		return aliphaticRingCount;
	}

	public String getInchi() {
		return inchi;
	}

	public String getInchikey() {
		return inchikey;
	}

}
