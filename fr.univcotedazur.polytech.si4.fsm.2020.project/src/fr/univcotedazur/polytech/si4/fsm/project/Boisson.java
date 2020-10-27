package fr.univcotedazur.polytech.si4.fsm.project;

import javax.swing.JLabel;


public abstract class Boisson {
	protected String name;
	protected double price;
	protected JLabel messagesToUser;
	protected int totalTime, timeStep1, timeStep2, timeStep3, timeStep4, timeStep5;
	protected Boolean cupAdded;
	
	public Boisson(String name, double price, JLabel messagesToUser, Boolean cupAdded) {
		this.name = name;
		this.price = price;
		this.messagesToUser = messagesToUser;
		this.cupAdded = cupAdded;
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
	
	public abstract void doEtape1();
	public abstract void doEtape2();
	public abstract void doEtape3();
	public void doEtape4() {}
	public void doEtape5() {}
	public abstract void fin();
	public abstract void calculateTime(int size, int sugar, int temp);

	public void setPrice(double d) {
		this.price = d;
	}
}
