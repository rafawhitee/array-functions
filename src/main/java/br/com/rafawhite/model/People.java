package br.com.rafawhite.model;

// Only to Test
public class People {
	
	private static int LAST_ID = 0;
	
	private int id;
	
	private String name;
	
	private int age;
	
	private double weight;
	
	private double height;
	
	private boolean legalPerson;
	
	private Profile profile;
	
	public People() {
		populateId();
	}
	
	public People(String name, int age, double weight, double height, boolean legalPerson) {
		populateId();
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.legalPerson = legalPerson;
	}
	
	private void populateId() {
		id = LAST_ID;
		LAST_ID++;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public boolean isLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(boolean legalPerson) {
		this.legalPerson = legalPerson;
	}
	
	public String getGroupByMethod() {
		if(profile != null) {
			return age + "/" + profile.getDescription();
		}
		return null;
	}

	@Override
	public String toString() {
		String profileName = (profile != null) ? profile.getDescription() : "";
		return id + " " + profileName + ", name=" + name + ", age=" + age + ", weight=" + weight + ", height=" + height;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
}