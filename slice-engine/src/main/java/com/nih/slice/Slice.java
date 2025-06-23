package com.nih.slice;

import java.util.List;

public class Slice {

	int id;
	String name;
	int version;
	List<Modification> modifications;
	List<Reference> references;
	List<Condition> conditions;
	Setup setup;
	List<SliceReaction> reactions;
	
	/**
	 * @param id
	 * @param name
	 * @param version
	 * @param modifications
	 * @param references
	 * @param setup
	 * @param conditions
	 * @param reaction
	 */
	public Slice(int id, String name, int version, List<Modification> modifications, List<Reference> references,
			Setup setup, List<Condition> conditions, List<SliceReaction> reactions) {
		super();
		this.id = id;
		this.name = name;
		this.version = version;
		this.modifications = modifications;
		this.references = references;
		this.setup = setup;
		this.conditions = conditions;
		this.reactions = reactions;
	}

	public Slice() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<Modification> getModifications() {
		return modifications;
	}

	public void setModifications(List<Modification> modifications) {
		this.modifications = modifications;
	}

	public List<Reference> getReferences() {
		return references;
	}

	public void setReferences(List<Reference> references) {
		this.references = references;
	}
	
	public List<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}

	public Setup getSetup() {
		return setup;
	}

	public void setSetup(Setup setup) {
		this.setup = setup;
	}

	public List<SliceReaction> getReactions() {
		return reactions;
	}

	public void setReactions(List<SliceReaction> reactions) {
		this.reactions = reactions;
	}

	@Override
	public String toString() {
		return "Slice [id=" + id + ", name=" + name + ", version=" + version + ", modifications=" + modifications
				+ ", references=" + references + ", conditions=" + conditions + ", setup=" + setup + ", reactions="
				+ reactions + ", getId()=" + getId() + ", getName()=" + getName() + ", getVersion()=" + getVersion()
				+ ", getModifications()=" + getModifications() + ", getReferences()=" + getReferences()
				+ ", getConditions()=" + getConditions() + ", getSetup()=" + getSetup() + ", getReactions()="
				+ getReactions() + "]";
	}



	public static class SliceReaction {
		int id;
		List<SliceMolecule> reactants;
		List<SliceMolecule> agents;
		List<SliceMolecule> products;
		String conditions;
		String actualConditions;
		
		
		/**
		 * @param id
		 * @param reactants
		 * @param agents
		 * @param products
		 * @param conditions
		 * @param actualConditions
		 */
		public SliceReaction(int id, List<SliceMolecule> reactants, List<SliceMolecule> agents,
				List<SliceMolecule> products, String conditions, String actualConditions) {
			super();
			this.id = id;
			this.reactants = reactants;
			this.agents = agents;
			this.products = products;
			this.conditions = conditions;
			this.actualConditions = actualConditions;
		}
		public SliceReaction() {
			// TODO Auto-generated constructor stub
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public List<SliceMolecule> getReactants() {
			return reactants;
		}
		public void setReactants(List<SliceMolecule> reactants) {
			this.reactants = reactants;
		}
		public List<SliceMolecule> getAgents() {
			return agents;
		}
		public void setAgents(List<SliceMolecule> agents) {
			this.agents = agents;
		}
		public List<SliceMolecule> getProducts() {
			return products;
		}
		public void setProducts(List<SliceMolecule> products) {
			this.products = products;
		}
		public String getConditions() {
			return conditions;
		}
		public void setConditions(String conditions) {
			this.conditions = conditions;
		}
		public String getActualConditions() {
			return actualConditions;
		}
		public void setActualConditions(String actualConditions) {
			this.actualConditions = actualConditions;
		}
		
		@Override
		public String toString() {
			return "SliceReaction [id=" + id + ", reactants=" + reactants + ", agents=" + agents + ", products="
					+ products + ", conditions=" + conditions + ", actualConditions=" + actualConditions + "]";
		}
		
	}
	
	public static class SliceMolecule {
		String id;
		String smarts;
		String logic;
		
		/**
		 * @param id
		 * @param smarts
		 * @param logic
		 */
		public SliceMolecule(String id, String smarts, String logic) {
			super();
			this.id = id;
			this.smarts = smarts;
			this.logic = logic;
		}
		
		public SliceMolecule() {
			// TODO Auto-generated constructor stub
		}

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getSmarts() {
			return smarts;
		}
		public void setSmarts(String smarts) {
			this.smarts = smarts;
		}
		public String getLogic() {
			return logic;
		}
		public void setLogic(String logic) {
			this.logic = logic;
		}

		@Override
		public String toString() {
			return "SliceMolecule [id=" + id + ", smarts=" + smarts + ", logic=" + logic + "]";
		}	
		
	}
	
	public static class Setup {
		String yield;
		String reliability;
		String reputation;
		String homoselectivity;
		String heteroselectivity;
		String orientationalSelectivity;
		String conditionFlexibility;
		String thermodynamics;
		
		/**
		 * @param yield
		 * @param reliability
		 * @param reputation
		 * @param homoselectivity
		 * @param heteroselectivity
		 * @param orientationalSelectivity
		 * @param conditionFlexibility
		 * @param thermodynamics
		 */
		public Setup(String yield, String reliability, String reputation, String homoselectivity,
				String heteroselectivity, String orientationalSelectivity, String conditionFlexibility,
				String thermodynamics) {
			super();
			this.yield = yield;
			this.reliability = reliability;
			this.reputation = reputation;
			this.homoselectivity = homoselectivity;
			this.heteroselectivity = heteroselectivity;
			this.orientationalSelectivity = orientationalSelectivity;
			this.conditionFlexibility = conditionFlexibility;
			this.thermodynamics = thermodynamics;
		}
		
		public Setup() {
			// TODO Auto-generated constructor stub
		}

		public String getYield() {
			return yield;
		}
		public void setYield(String yield) {
			this.yield = yield;
		}
		public String getReliability() {
			return reliability;
		}
		public void setReliability(String reliability) {
			this.reliability = reliability;
		}
		public String getReputation() {
			return reputation;
		}
		public void setReputation(String reputation) {
			this.reputation = reputation;
		}
		public String getHomoselectivity() {
			return homoselectivity;
		}
		public void setHomoselectivity(String homoselectivity) {
			this.homoselectivity = homoselectivity;
		}
		public String getHeteroselectivity() {
			return heteroselectivity;
		}
		public void setHeteroselectivity(String heteroselectivity) {
			this.heteroselectivity = heteroselectivity;
		}
		public String getOrientationalSelectivity() {
			return orientationalSelectivity;
		}
		public void setOrientationalSelectivity(String orientationalSelectivity) {
			this.orientationalSelectivity = orientationalSelectivity;
		}
		public String getConditionFlexibility() {
			return conditionFlexibility;
		}
		public void setConditionFlexibility(String conditionSelectivity) {
			this.conditionFlexibility = conditionSelectivity;
		}
		public String getThermodynamics() {
			return thermodynamics;
		}
		public void setThermodynamics(String thermodynamics) {
			this.thermodynamics = thermodynamics;
		}

		@Override
		public String toString() {
			return "Condition [yield=" + yield + ", reliability=" + reliability + ", reputation=" + reputation
					+ ", homoselectivity=" + homoselectivity + ", heteroselectivity=" + heteroselectivity
					+ ", orientationalSelectivity=" + orientationalSelectivity + ", conditionSelectivity="
					+ conditionFlexibility + ", thermodynamics=" + thermodynamics + "]";
		}
		
		
	}
	
	public static class Modification {
		int id;
		String type;
		String date;
		List<Author> authors;
		
		/**
		 * @param id
		 * @param type
		 * @param date
		 * @param authors
		 */
		public Modification(int id, String type, String date, List<Author> authors) {
			super();
			this.id = id;
			this.type = type;
			this.date = date;
			this.authors = authors;
		}
		
		public Modification() {
			// TODO Auto-generated constructor stub
		}

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public List<Author> getAuthors() {
			return authors;
		}
		public void setAuthors(List<Author> authors) {
			this.authors = authors;
		}

		@Override
		public String toString() {
			return "Modification [id=" + id + ", type=" + type + ", date=" + date + ", authors=" + authors + "]";
		}
		
		
	}
	
	public static class Reference {
		int id;
		String title;
		String journal;
		String volume;
		String part;
		String book;
		String year;
		String month;
		String pages;
		String link;
		String editor;
		String publisher;
		String city;
		List<Author> authors;
		

		
		/**
		 * @param id
		 * @param title
		 * @param journal
		 * @param volume
		 * @param part
		 * @param book
		 * @param year
		 * @param month
		 * @param pages
		 * @param link
		 * @param editor
		 * @param publisher
		 * @param city
		 * @param authors
		 */
		public Reference(int id, String title, String journal, String volume, String part, String book, String year,
				String month, String pages, String link, String editor, String publisher, String city,
				List<Author> authors) {
			super();
			this.id = id;
			this.title = title;
			this.journal = journal;
			this.volume = volume;
			this.part = part;
			this.book = book;
			this.year = year;
			this.month = month;
			this.pages = pages;
			this.link = link;
			this.editor = editor;
			this.publisher = publisher;
			this.city = city;
			this.authors = authors;
		}

		public Reference() {
			// TODO Auto-generated constructor stub
		}

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getJournal() {
			return journal;
		}
		public void setJournal(String journal) {
			this.journal = journal;
		}
		public String getBook() {
			return book;
		}
		public void setBook(String book) {
			this.book = book;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public String getMonth() {
			return month;
		}
		public void setMonth(String month) {
			this.month = month;
		}
		public String getPages() {
			return pages;
		}
		public void setPages(String pages) {
			this.pages = pages;
		}
		public List<Author> getAuthors() {
			return authors;
		}
		public void setAuthors(List<Author> authors) {
			this.authors = authors;
		}

		public String getVolume() {
			return volume;
		}

		public void setVolume(String volume) {
			this.volume = volume;
		}

		public String getPart() {
			return part;
		}

		public void setPart(String part) {
			this.part = part;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public String getEditor() {
			return editor;
		}

		public void setEditor(String editor) {
			this.editor = editor;
		}

		public String getPublisher() {
			return publisher;
		}

		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		@Override
		public String toString() {
			return "Reference [id=" + id + ", title=" + title + ", journal=" + journal + ", volume=" + volume
					+ ", part=" + part + ", book=" + book + ", year=" + year + ", month=" + month + ", pages=" + pages
					+ ", link=" + link + ", editor=" + editor + ", publisher=" + publisher + ", city=" + city
					+ ", authors=" + authors + "]";
		}
		
	}
	
	public static class Author {
		int id;
		String name;
		String institute;
		
		/**
		 * @param id
		 * @param name
		 * @param institute
		 */
		public Author(int id, String name, String institute) {
			super();
			this.id = id;
			this.name = name;
			this.institute = institute;
		}
		public Author() {
			// TODO Auto-generated constructor stub
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getInstitute() {
			return institute;
		}
		public void setInstitute(String institute) {
			this.institute = institute;
		}
		@Override
		public String toString() {
			return "Author [id=" + id + ", name=" + name + ", institute=" + institute + "]";
		}
		
		
	}
	
	public static class Condition {
		int id;
		String name;
		String step;
		List<String> reagents;
		List<String> solvents;
		List<String> temperatures;
		List<String> times;
		List<String> supInf;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getStep() {
			return step;
		}
		public void setStep(String step) {
			this.step = step;
		}
		public List<String> getReagents() {
			return reagents;
		}
		public void setReagents(List<String> reagents) {
			this.reagents = reagents;
		}
		public List<String> getSolvents() {
			return solvents;
		}
		public void setSolvents(List<String> solvents) {
			this.solvents = solvents;
		}
		public List<String> getTemperatures() {
			return temperatures;
		}
		public void setTemperatures(List<String> temperatures) {
			this.temperatures = temperatures;
		}
		public List<String> getTimes() {
			return times;
		}
		public void setTimes(List<String> times) {
			this.times = times;
		}
		public List<String> getSupInf() {
			return supInf;
		}
		public void setSupInf(List<String> supInf) {
			this.supInf = supInf;
		}
		@Override
		public String toString() {
			return "Condition [id=" + id + ", name=" + name + ", step=" + step + ", reagents=" + reagents
					+ ", solvents=" + solvents + ", temperatures=" + temperatures + ", times=" + times + ", supInf="
					+ supInf + "]";
		}
		
	}
	
}
