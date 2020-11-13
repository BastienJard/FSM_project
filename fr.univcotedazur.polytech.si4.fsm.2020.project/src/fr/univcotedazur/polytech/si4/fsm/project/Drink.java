package fr.univcotedazur.polytech.si4.fsm.project;

import javax.swing.JLabel;


public abstract class Drink {
	protected String name;
	protected double price;
	protected JLabel messagesToUser;
	protected int totalTime, timeStep1, timeStep2, timeStep3, timeStep4, timeStep5;
	protected Boolean cupAdded, opt1, opt2, opt3;
	protected String option1, option2,option3;
	
	public Drink(String name, double price, JLabel messagesToUser, Boolean cupAdded, Boolean opt1, Boolean opt2, Boolean opt3) {
		this.name = name;
		this.price = price;
		this.messagesToUser = messagesToUser;
		this.cupAdded = cupAdded;
		this.opt1=opt1;
		this.opt2=opt2;
		this.opt3=opt3;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getTotalTime() {
		return totalTime;
	}
	
	public JLabel getMessagesToUser() {
		return messagesToUser;
	}
	
	public abstract void doStep1();
	public abstract void doStep2();
	public abstract void doStep3();
	public void doStep4() {}
	public void doStep5() {}
	public abstract void calculateTime(int size, int sugar, int temp);

	public void setPrice(double d) {
		this.price = d;
	}
}
