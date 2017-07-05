package br.com.contaazul.model;


public class Municipality {

	private int year;
	
	private int id;
	
	private String name;
	
	private String state; 
	
	private int accidentsWithCategoryTypical;
	
	private int accidentsWithCategoryPath;
	
	private int accidentsWithCategoryWorkRelated;
	
	private int deathQuantity;
	
	private int accidentsWithoutCategory;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getAccidentsWithCategoryTypical() {
		return accidentsWithCategoryTypical;
	}

	public void setAccidentsWithCategoryTypical(int accidentsWithCategoryTypical) {
		this.accidentsWithCategoryTypical = accidentsWithCategoryTypical;
	}

	public int getAccidentsWithCategoryPath() {
		return accidentsWithCategoryPath;
	}

	public void setAccidentsWithCategoryPath(int accidentsWithCategoryPath) {
		this.accidentsWithCategoryPath = accidentsWithCategoryPath;
	}

	public int getAccidentsWithCategoryWorkRelated() {
		return accidentsWithCategoryWorkRelated;
	}

	public void setAccidentsWithCategoryWorkRelated(
			int accidentsWithCategoryWorkRelated) {
		this.accidentsWithCategoryWorkRelated = accidentsWithCategoryWorkRelated;
	}

	public int getDeathQuantity() {
		return deathQuantity;
	}

	public void setDeathQuantity(int deathQuantity) {
		this.deathQuantity = deathQuantity;
	}

	public int getAccidentsWithoutCategory() {
		return accidentsWithoutCategory;
	}

	public void setAccidentsWithoutCategory(int accidentsWithoutCategory) {
		this.accidentsWithoutCategory = accidentsWithoutCategory;
	}
	
	private Double workRelatedAccidentsIncrease;

	public Double getWorkRelatedAccidentsIncrease() {
		if (workRelatedAccidentsIncrease.isNaN()) return 0.0; // 0/0 (zero over zero)
		return workRelatedAccidentsIncrease;
	}

	public void setWorkRelatedAccidentsIncrease(Double workRelatedAccidentsIncrease) {
		this.workRelatedAccidentsIncrease = workRelatedAccidentsIncrease;
	}
	
}
