package com.nih.slice.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nih.slice.Slice;
import com.nih.slice.Slice.Author;
import com.nih.slice.Slice.Condition;
import com.nih.slice.Slice.Setup;
import com.nih.slice.Slice.Modification;
import com.nih.slice.Slice.Reference;
import com.nih.slice.Slice.SliceMolecule;
import com.nih.slice.Slice.SliceReaction;

public class SliceFileParser {
	
	public Map<Integer, Slice> parse(String filepath) {
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader;
		try {
			reader = xmlInputFactory.createXMLStreamReader(
				    new FileInputStream(Paths.get(filepath).toFile()));
			return parse(reader);
		} catch (FileNotFoundException | XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Map<Integer, Slice> parse(InputStream stream) {
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader;
			try {
				reader = xmlInputFactory.createXMLStreamReader(stream);
				return parse(reader);
			} catch (XMLStreamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	
	public Map<Integer, Slice> parse(XMLStreamReader reader) throws XMLStreamException {
		Map<Integer, Slice> allSliceTransform = new HashMap<Integer, Slice>();
		 int eventType = reader.getEventType();
	        Slice scObj = null;
	        Modification modif = null;
	        List<Modification> modifs = new ArrayList<Modification>();
	        int defaultID = 99999;
	        int defaultModifID = 0;
	        List<Author> authors = null;
	        Setup setup = null;
	        Reference ref = null;
	        List<Reference> refs = null;
	        Condition condition = new Condition();
	        List<Condition> conditions = null;
	        List<String> reagents = null;
	        List<String> solvents = null;
	        List<String> temperatures = null;
	        List<String> times = null;
	        List<String> si = null;
	        List<SliceReaction> reactions = null;
	        SliceReaction reaction = null;
	        List<SliceMolecule> reactants = null;
	        List<SliceMolecule> agents = null;
			List<SliceMolecule> products = null;
			SliceMolecule mol = null;
	        while (reader.hasNext()) {
	        	eventType = reader.next();
	            if (eventType == XMLEvent.START_ELEMENT) {
	            	switch (reader.getName().getLocalPart()) {
	            		case "transform":
	            			scObj = new Slice();
	            			String id = reader.getAttributeValue(null, "id");
	            			String name = reader.getAttributeValue(null, "name");
	            			String version = reader.getAttributeValue(null, "version");
	            			if (id.length() > 0) {
	            				scObj.setId(Integer.parseInt(id));
	            			}
	            			else {
	            				scObj.setId(defaultID);
	            				defaultID++;
	            			}
	            			if (version.length() > 0) {
	            				scObj.setVersion(Integer.parseInt(version));
	            			}
	    					scObj.setName(name);
	    					break;
	    					
	            		case "modification":
	            			modif = new Modification();
	            			authors = new ArrayList<Author>();
	            			String id2 = reader.getAttributeValue(null, "id");
	            			String type = reader.getAttributeValue(null, "type");
	            			String date = reader.getAttributeValue(null, "date");
	            			if (id2.length() > 0) {
	            				modif.setId(Integer.parseInt(id2));
	            			}
	            			else {
	            				modif.setId(defaultModifID);
	            				defaultModifID++;
	            			}
	            			modif.setType(type);
	            			modif.setDate(date);
	            			//System.out.println(reader.getAttributeValue(null, "history"));
	            			break;
	            		case "author":
	            			String aName = reader.getAttributeValue(null, "name");
	            			Author author = new Author();
	            			author.setName(aName);
	            			//System.out.println("authors :"+authors);
							//authors.add(author);
	            		case "setup":
	            			setup = new Setup();
	            			break;
	            		case "yield":
	            			 eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 setup.setYield(reader.getText());
	                         }
	                         break;
	            		case "reliability":
	            			 eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 setup.setReliability(reader.getText());
	                         }
	                         break;
	            		case "reputation":
	            			 eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 setup.setReputation(reader.getText());
	                         }
	                         break;
	            		case "homoselectivity":
	            			 eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 setup.setHomoselectivity(reader.getText());
	                         }
	                         break;
	            		case "heteroselectivity":
	            			 eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 setup.setHeteroselectivity(reader.getText());
	                         }
	                         break;
	            		case "orientationalSelectivity":
	            			 eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 setup.setOrientationalSelectivity(reader.getText());
	                         }
	                         break;
	            		case "conditionFlexibility":
	            			 eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 setup.setConditionFlexibility(reader.getText());
	                         }
	                         break;
	            		case "thermodynamics":
	            			 eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 setup.setThermodynamics(reader.getText());
	                         }
	                         break;
	            		case "bibliography":
	            			refs = new ArrayList<Reference>();
	            			break;
	            		case "reference":
	            			ref = new Reference();
	            			break;
	            		case "title":
	            			 eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 ref.setTitle(reader.getText());
	                         }
	            			break;
	            		case "journal":
	            			 eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 ref.setJournal(reader.getText());
	                         }
	            			break;
	            		case "year":
	            			 eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 ref.setYear(reader.getText());
	                         }
	            			break;
	            		case "volume":
	            			 eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 ref.setVolume(reader.getText());
	                         }
	            			break;
	            		case "part":
	            			 eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 ref.setPart(reader.getText());
	                         }
	            			break;
	            		case "pages":
	            			 eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 ref.setPages(reader.getText());
	                         }
	            			break;
	            		case "link":
	            			 eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 ref.setLink(reader.getText());
	                         }
	            			break;
	            		case "editor":
	            			 eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 ref.setEditor(reader.getText());
	                         }
	            			break;
	            		case "publisher":
	            			 eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 ref.setPublisher(reader.getText());
	                         }
	            			break;
	            		case "city":
	            			 eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 ref.setCity(reader.getText());
	                         }
	            			break;
	            		case "conditions":
	            			conditions = new ArrayList<Condition>();
	            			break;
	            		case "condition":
	            			condition = new Condition();
	            			condition.setId(Integer.parseInt(reader.getAttributeValue(null, "id")));
	            			condition.setName(reader.getAttributeValue(null, "name"));
	            			break;
	            		case "step":
	            			eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 condition.setStep(reader.getText());
	                         }
	            			break;
	            		case "reagents":
	            			reagents = new ArrayList<String>();
	            			break;
	            		case "solvents":
	            			solvents = new ArrayList<String>();
	            			break;
	            		case "temperatures":
	            			temperatures = new ArrayList<String>();
	            			break;
	            		case "times":
	            			times = new ArrayList<String>();
	            			break;
	            		case "informations":
	            			si = new ArrayList<String>();
	            			break;
	            		case "reagent":
	            			eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 reagents.add(reader.getText());
	                         }
	            			break;
	            		case "solvent":
	            			eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 solvents.add(reader.getText());
	                         }
	            			break;
	            		case "temperature":
	            			eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 temperatures.add(reader.getText());
	                         }
	            			break;
	            		case "time":
	            			eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 times.add(reader.getText());
	                         }
	            			break;
	            		case "information":
	            			eventType = reader.next();
	                         if (eventType == XMLEvent.CHARACTERS) {
	                        	 si.add(reader.getText());
	                         }
	            			break;
	            		case "reactions":
	            			 reactions = new ArrayList<SliceReaction>();
	            			break;
	            		case "reaction":
	            			 reaction = new SliceReaction();
	            			 reaction.setId(Integer.parseInt(reader.getAttributeValue(null, "id")));
	            			break;
	            		case "reactants":
	            			reactants = new ArrayList<SliceMolecule>();
	            			break;
	            		case "reactant":
	            			mol = new SliceMolecule();
	            			mol.setId(reader.getAttributeValue(null, "id"));
	            			break;
	            		case "agents":
	            			agents = new ArrayList<SliceMolecule>();
	            			break;
	            		case "agent":
	            			mol = new SliceMolecule();
	            			mol.setId(reader.getAttributeValue(null, "id"));
	            			break;
	            		case "products":
	            			products = new ArrayList<SliceMolecule>();
	            			break;
	            		case "product":
	            			mol = new SliceMolecule();
	            			mol.setId(reader.getAttributeValue(null, "id"));
	            			break;
	            		case "smarts":
	            			eventType = reader.next();
	            			String smarts = "";
	            			while (eventType == XMLEvent.CHARACTERS) {
	            				smarts += reader.getText();
	            				eventType = reader.next();
	            			}
	            			smarts = smarts.replaceAll("\"", "");
	            			
	            				 /*if (logic.startsWith("\"")) {
	            					 logic = logic.substring(1);
	            				 }
	            				 if (logic.endsWith("\"")) {
	            					 logic = logic.substring(0, logic.length());
	            				 }*/
	            				 eventType = reader.next();
	            				mol.setSmarts(smarts);

	            			break;
	            		case "logic":
	            			eventType = reader.next();
	            			String logic = "";
	            			while (eventType == XMLEvent.CHARACTERS) {
	            				logic += reader.getText();
	            				eventType = reader.next();
	            			}
	            			logic = logic.replaceAll("\"", "");
	            			
	            				 /*if (logic.startsWith("\"")) {
	            					 logic = logic.substring(1);
	            				 }
	            				 if (logic.endsWith("\"")) {
	            					 logic = logic.substring(0, logic.length());
	            				 }*/
	            				 //System.out.println("LOGIC " +logic);
	            				 eventType = reader.next();
	            				 if (!logic.equals("undefined"))
	            					 mol.setLogic(logic);
	            			break;
	            		default:
	            			 if (eventType == XMLEvent.CHARACTERS) {
	            				 //System.out.println(reader.getText());
	            			 }
	            			break;
	            			
	            	}
	            }
	            if (eventType == XMLEvent.END_ELEMENT) {
	                // if </staff>
	                if (reader.getName().getLocalPart().equals("transform")) {
	                	allSliceTransform.put(scObj.getId(), scObj);
	                }
	                if (reader.getName().getLocalPart().equals("history")) {
	                	scObj.setModifications(modifs);
	                }
	                if (reader.getName().getLocalPart().equals("modification")) {
	                	modif.setAuthors(authors);
	                	modifs.add(modif);
	                }
	                if (reader.getName().getLocalPart().equals("setup")) {
	                	scObj.setSetup(setup);
	                }
	                if (reader.getName().getLocalPart().equals("bibliography")) {
	                	scObj.setReferences(refs);
	                }
	                if (reader.getName().getLocalPart().equals("reference")) {
	                	ref.setAuthors(authors);
	                	refs.add(ref);
	                }
	                if (reader.getName().getLocalPart().equals("reagents")) {
	                	condition.setReagents(reagents);
	                }
	                if (reader.getName().getLocalPart().equals("solvents")) {
	                	condition.setSolvents(solvents);
	                }
	                if (reader.getName().getLocalPart().equals("temperatures")) {
	                	condition.setTemperatures(temperatures);
	                }
	                if (reader.getName().getLocalPart().equals("times")) {
	                	condition.setTimes(times);
	                }
	                if (reader.getName().getLocalPart().equals("informations")) {
	                	condition.setSupInf(si);
	                }
	                if (reader.getName().getLocalPart().equals("conditions")) {
	                	scObj.setConditions(conditions);
	                }
	                if (reader.getName().getLocalPart().equals("reactant")) {
	                	reactants.add(mol);
	                }
	                if (reader.getName().getLocalPart().equals("reactants")) {
	                	reaction.setReactants(reactants);
	                }
	                if (reader.getName().getLocalPart().equals("agent")) {
	                	agents.add(mol);
	                }
	                if (reader.getName().getLocalPart().equals("agents")) {
	                	reaction.setAgents(agents);
	                }
	                if (reader.getName().getLocalPart().equals("product")) {
	                	products.add(mol);
	                }
	                if (reader.getName().getLocalPart().equals("products")) {
	                	reaction.setProducts(products);
	                }
	                if (reader.getName().getLocalPart().equals("reaction")) {
	                	reactions.add(reaction);
	                }
	                if (reader.getName().getLocalPart().equals("reactions")) {
	                	scObj.setReactions(reactions);
	                }
	            }
	        }
		return allSliceTransform;
	}

	
	public Map<Integer, Slice> parse_OLD(String filepath) {
		Map<Integer, Slice> allSlice = new HashMap<Integer, Slice>();
		try {
			File inputFile = new File(filepath);

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList list = doc.getElementsByTagName("transform");

			for (int i = 0; i < list.getLength(); i++) {
				int id = -1;
				Slice scObj = new Slice();
				Node ntransform = list.item(i);

				if (ntransform.getNodeType() == Node.ELEMENT_NODE) {
					Element eTransform = (Element) ntransform;
					id = Integer.parseInt(eTransform.getAttribute("id"));
					scObj.setId(id);
					scObj.setName(eTransform.getAttribute("name"));
					scObj.setVersion(Integer.parseInt(eTransform.getAttribute("version")));
					
					NodeList transformChildren =  eTransform.getChildNodes();
					
					for (int j = 0; j < transformChildren.getLength(); j++) {
						Node node = transformChildren.item(j);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element elt = (Element) node;
							//System.out.println(elt.getNodeName());
							if (elt.getNodeName().equals("history")) {
								List<Modification> modifs = new ArrayList<Modification>();
								NodeList modifList = elt.getElementsByTagName("modification");

								for (int k = 0; k < modifList.getLength(); k++) {
									Modification modif = new Modification();
									Node nModif = modifList.item(k);

									if (nModif.getNodeType() == Node.ELEMENT_NODE) {
										Element eModif = (Element) nModif;
										modif.setId(Integer.parseInt(eModif.getAttribute("id")));
										modif.setType(eModif.getAttribute("type"));
										modif.setDate(eModif.getAttribute("date"));

										List<Author> authors = new ArrayList<Author>();
										NodeList authorList = ((Element) eModif.getElementsByTagName("authors").item(0)).getElementsByTagName("author");

										for (int l = 0; l < authorList.getLength(); l++) {
											Author author = new Author();
											Node nAuthor = authorList.item(l);

											if (nAuthor.getNodeType() == Node.ELEMENT_NODE) {
												Element eAuthor = (Element) nAuthor;
												if (eAuthor.getAttribute("institute").length() > 0)
													author.setInstitute(eAuthor.getAttribute("institute"));
												if (eAuthor.getAttribute("id").length() > 0)
													author.setId(Integer.parseInt(eAuthor.getAttribute("id")));
												author.setName(eAuthor.getAttribute("name"));
												authors.add(author);
											}
										}
										modif.setAuthors(authors);
										modifs.add(modif);
									}
								}
								scObj.setModifications(modifs);
							}
							else if (elt.getNodeName().equals("references")) {
								List<Reference> refs = new ArrayList<Reference>();
								NodeList refList = elt.getElementsByTagName("reference");

								for (int k = 0; k < refList.getLength(); k++) {
									Reference ref = new Reference();
									Node nRef = refList.item(k);

									if (nRef.getNodeType() == Node.ELEMENT_NODE) {
										Element eRef = (Element) nRef;
										ref.setId(Integer.parseInt(eRef.getAttribute("id")));
										if (eRef.getElementsByTagName("book").item(0) != null)
											ref.setBook(eRef.getElementsByTagName("book").item(0).getTextContent());
										if (eRef.getElementsByTagName("journal").item(0) != null)
											ref.setJournal(eRef.getElementsByTagName("journal").item(0).getTextContent());
										if (eRef.getElementsByTagName("month").item(0) != null)
											ref.setMonth(eRef.getElementsByTagName("month").item(0).getTextContent());
										if (eRef.getElementsByTagName("pages").item(0) != null)
											ref.setPages(eRef.getElementsByTagName("pages").item(0).getTextContent());
										if (eRef.getElementsByTagName("title").item(0) != null)
											ref.setTitle(eRef.getElementsByTagName("title").item(0).getTextContent());
										if (eRef.getElementsByTagName("year").item(0) != null)
											ref.setYear(eRef.getElementsByTagName("year").item(0).getTextContent());

										List<Author> authors = new ArrayList<Author>();
										if (eRef.getElementsByTagName("authors").getLength() > 0) {
											NodeList authorList = ((Element) eRef.getElementsByTagName("authors").item(0)).getElementsByTagName("author");
											//System.out.println(eAuthor.getNodeName() + " " +eAuthor.getAttribute("id"));
											//System.out.println(eAuthor.getNodeName() + " " +eAuthor.getAttribute("institute"));
											//System.out.println(eAuthor.getNodeName() + " " +eAuthor.getAttribute("name"));
											for (int l = 0; l < authorList.getLength(); l++) {
												Author author = new Author();
												Node nAuthor = authorList.item(l);

												if (nAuthor.getNodeType() == Node.ELEMENT_NODE) {
													Element eAuthor = (Element) nAuthor;
													if (eAuthor.getAttribute("institute").length() > 0)
														author.setInstitute(eAuthor.getAttribute("institute"));
													if (eAuthor.getAttribute("id").length() > 0)
														author.setId(Integer.parseInt(eAuthor.getAttribute("id")));
													author.setName(eAuthor.getAttribute("name"));
													authors.add(author);
												}
											}
											ref.setAuthors(authors);
										}
										refs.add(ref);
									}
								}
								scObj.setReferences(refs);
							}
							else if (elt.getNodeName().equals("setup")) {
								Setup cond = new Setup();
								cond.setConditionFlexibility(elt.getElementsByTagName("conditionSelectivity").item(0).getTextContent());
								cond.setHeteroselectivity(elt.getElementsByTagName("heteroselectivity").item(0).getTextContent());
								cond.setHomoselectivity(elt.getElementsByTagName("homoselectivity").item(0).getTextContent());
								cond.setOrientationalSelectivity(elt.getElementsByTagName("orientationalSelectivity").item(0).getTextContent());
								cond.setReliability(elt.getElementsByTagName("reliability").item(0).getTextContent());
								cond.setReputation(elt.getElementsByTagName("reputation").item(0).getTextContent());
								cond.setThermodynamics(elt.getElementsByTagName("thermodynamics").item(0).getTextContent());
								cond.setYield(elt.getElementsByTagName("yield").item(0).getTextContent());
								scObj.setSetup(cond);
							}
							else if (elt.getNodeName().equals("reactions")) {
								List<SliceReaction> reactions = new ArrayList<SliceReaction>();
								NodeList reactionsList = elt.getElementsByTagName("reaction");
								
								for (int k = 0; k < reactionsList.getLength(); k++) {
									SliceReaction reaction = new SliceReaction();
									Node nReaction = reactionsList.item(k);
									if (nReaction.getNodeType() == Node.ELEMENT_NODE) {
										Element eReaction = (Element) nReaction;
										reaction.setId(Integer.parseInt(eReaction.getAttribute("subtype")));
										reaction.setConditions(eReaction.getAttribute("conditions"));
										reaction.setActualConditions(eReaction.getAttribute("actualConditions"));

										List<SliceMolecule> mols = new ArrayList<SliceMolecule>();
										NodeList molList = ((Element) eReaction.getElementsByTagName("reactants").item(0)).getElementsByTagName("reactant");

										for (int l = 0; l < molList.getLength(); l++) {
											SliceMolecule mol = new SliceMolecule();
											Node nMol = molList.item(l);

											if (nMol.getNodeType() == Node.ELEMENT_NODE) {
												Element eMol = (Element) nMol;
												mol.setId(eMol.getAttribute("id"));
												mol.setSmarts(eMol.getElementsByTagName("smarts").item(0).getTextContent());
												mol.setLogic(eMol.getElementsByTagName("logic").item(0).getTextContent());
												mols.add(mol);
											}
										}
										reaction.setReactants(mols);
										
										 mols = new ArrayList<SliceMolecule>();
										molList = ((Element) eReaction.getElementsByTagName("products").item(0)).getElementsByTagName("product");

											for (int l = 0; l < molList.getLength(); l++) {
												SliceMolecule mol = new SliceMolecule();
												Node nMol = molList.item(l);

												if (nMol.getNodeType() == Node.ELEMENT_NODE) {
													Element eMol = (Element) nMol;
													mol.setId(eMol.getAttribute("id"));
													mol.setSmarts(eMol.getElementsByTagName("smarts").item(0).getTextContent());
													mol.setLogic(eMol.getElementsByTagName("logic").item(0).getTextContent());
													mols.add(mol);
												}
											}
											reaction.setProducts(mols);
										reactions.add(reaction);
									}
								}
								scObj.setReactions(reactions);
							}
						}
					}
				}
				//System.out.println(scObj.toString());
				if (id != -1)
					allSlice.put(id, scObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allSlice;
	}
}