package com.duffle_0_0.classes;

public class Workout {
	
	private String name;
	private enum type {cardio,yoga,plyometrics,lifting,sport};
	private type ty;
	private int cal;
	private long id;
	private String date;
	
	public Workout()
	{
		name="";
	}
	
	Workout(String n, String t)
	{
		if(t.equalsIgnoreCase("cardio"))
		{
			ty= type.cardio;
		}
		else if(t.equalsIgnoreCase("yoga"))
		{
			ty= type.yoga;
		}
		else if(t.equalsIgnoreCase("lifting"))
		{
			ty= type.lifting;
		}
		else if(t.equalsIgnoreCase("plyometrics"))
		{
			ty= type.plyometrics;
		}
		else if(t.equalsIgnoreCase("sport"))
		{
			ty= type.sport;
		}
		else{
			System.out.println("error making workout type");
		}
	}

	public void setWorkout(String n, String t)
	{
		System.out.println("in set workout");
		setName(n);
		setType(t);
		System.out.println("out set workout");
	}

	public void setName(String n)
	{
		System.out.println("in set name");
		this.name=n;
		System.out.println("out set name");
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setType(String t)
	{
		System.out.println("in setType");
		System.out.println("t= "+t);
		if(t.equalsIgnoreCase("cardio"))
		{
			this.ty= type.cardio;
		}
		else if(t.equalsIgnoreCase("yoga"))
		{
			this.ty= type.yoga;
		}
		else if(t.equalsIgnoreCase("lifting"))
		{
			this.ty= type.lifting;
		}
		else if(t.equalsIgnoreCase("plyometrics"))
		{
			this.ty= type.plyometrics;
		}
		else if(t.equalsIgnoreCase("sport"))
		{
			this.ty= type.sport;
		}
		else{
			System.out.println("error making workout type");
		}
		System.out.println("out set type");
	}

	public String getType() {
		return this.ty.toString();
	}

	public void setID(long i) {
		this.id=i;
	}
	
	public long getID() {
		return this.id;
	}
	
	public void setCal(int c){
		this.cal=c;
	}
	
	public int getCal(){
		return this.cal;
	}
	
	public void setDate(String date){
		this.date=date;
	}
	
	public String getDate(){
		return this.date;
	}

	public String toString(){
		return this.id + ". " + " [" + this.date + "] " + this.name;
	}
}
