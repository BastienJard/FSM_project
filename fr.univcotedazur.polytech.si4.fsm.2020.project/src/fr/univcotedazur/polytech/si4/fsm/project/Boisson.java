package fr.univcotedazur.polytech.si4.fsm.project;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import fr.univcotedazur.polytech.si4.fsm.project.recette.RecetteStatemachine;

public abstract class Boisson {
	protected String name;
	protected double price;
	protected FactoryController controller;
	protected JLabel messagesToUser;
	protected Timer recetteTimer, progressTimer;
	protected RecetteStatemachine recetteFSM;
	protected int totalTime, totalProgress;
	protected JProgressBar progressBar;
	
	public Boisson(String name,JProgressBar progressBar, double price, FactoryController controller, JLabel messagesToUser, RecetteStatemachine recetteFSM) {
		this.name = name;
		this.price = price;
		this.controller = controller;
		this.messagesToUser = messagesToUser;
		this.recetteTimer = new Timer(0, null);
		this.progressTimer = new Timer(0, null);
		this.recetteFSM = recetteFSM;
		this.totalProgress =0;
		this.progressBar = progressBar;
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
}
