package fr.univcotedazur.polytech.si4.fsm.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import fr.univcotedazur.polytech.si4.fsm.project.recette.RecetteStatemachine;

public abstract class Boisson {
	protected String name;
	protected double price;
	protected int totalTime;
	protected JLabel messagesToUser;
	protected Timer recetteTimer;
	protected RecetteStatemachine recetteFSM;
	
	public Boisson(String name, double price, int totalTime, JLabel messagesToUser, RecetteStatemachine recetteFSM) {
		this.name = name;
		this.price = price;
		this.totalTime = totalTime;
		this.messagesToUser = messagesToUser;
		this.recetteTimer = new Timer(0, null);
		this.recetteFSM = recetteFSM;
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
