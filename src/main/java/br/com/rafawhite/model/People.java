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

	@Override
	public String toString() {
		return id + ", name=" + name + ", age=" + age + ", weight=" + weight + ", height=" + height;
	}
	
}