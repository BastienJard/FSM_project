package fr.univcotedazur.polytech.si4.fsm.project;

import javax.swing.JLabel;


public abstract class Drink {
	protected String name;
	protected double price;
	protected JLabel messagesToUser;
	protected int totalTime, timeStep1, timeStep2, timeStep3, timeStep4, timeStep5;
	protected String option1, option2,option3, textToPrint;
	protected boolean cupAdded;
	
	public Drink(String name, double price, JLabel messagesToUser) {
		this.name = name;
		this.price = price;
		this.messagesToUser = messagesToUser;

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
	public abstract void doStep3(boolean option1,boolean option2,boolean option3);
	public void doStep4() {}
	public void doStep5(boolean option1) {}
	public abstract void calculateTime(int size, int sugar, int temp);


	public void setPrice(double d) {
		this.price = d;
	}
}
