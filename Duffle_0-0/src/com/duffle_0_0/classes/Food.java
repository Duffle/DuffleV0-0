package com.duffle_0_0.classes;

public class Food {
	long id;
	String name;
	String type;
	double quantity;
	int calories;
	int protein;
	int fat;
	
	public Food() {
		name = "NO_NAME";
		type = "NO TYPE";
		quantity = -1.0;
		calories = -1;
		protein = -1;
		fat = -1;
	}
	
	Food(String n, String t, double q, int c, int p, int f) {
		name = n;
		type = t;
		quantity = q;
		calories = c;
		protein = p;
		fat = f;
	}
	
	Food(String n, String t, String q, String c, String p, String f) {
		name = n;
		type = t;
		quantity = Double.parseDouble(q);
		calories = Integer.parseInt(c);
		protein = Integer.parseInt(p);
		fat = Integer.parseInt(f);
	}
	
	public void printAll() {
		System.out.println("Attributes:");
		System.out.println("Name = " + name);
		System.out.println("Type = " + type);
		System.out.println("Calories = " + calories);
		System.out.println("Protein = " + protein);
		System.out.println("Fat = " + fat);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public int getProtein() {
		return protein;
	}
	public void setProtein(int protein) {
		this.protein = protein;
	}
	public int getFat() {
		return fat;
	}
	public void setFat(int fat) {
		this.fat = fat;
	}
	public long getID() {
		return id;
	}
	public void setID(long id) {
		this.id = id;
	}
	
	public String toString() {
		return this.id + ". " + this.name + " - " + this.calories;
	}

}
