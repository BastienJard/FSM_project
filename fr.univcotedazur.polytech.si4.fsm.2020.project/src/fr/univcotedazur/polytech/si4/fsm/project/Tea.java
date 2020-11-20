package fr.univcotedazur.polytech.si4.fsm.project;

import javax.swing.JLabel;


public class Tea extends Drink{

	public Tea(String name, double price, JLabel messagesToUser, boolean cupAdded) {
		super(name, price, messagesToUser, cupAdded);
		super.option1 = "Nuage de lait (+0.10€)";
		super.option2 = "Sirop d'érable (+0.10€)";
		super.option3  = "";
		super.textToPrint = "<html>Récupération et positionnement<br>d’un sachet<br>Démarrage du chauffage de l’eau";
	}
	
	@Override
	public void doStep1() {
		messagesToUser.setText(super.textToPrint);
		super.textToPrint = "<html>Chauffage de l'eau";
		
	}

	@Override
	public void doStep2() {
		if(!cupAdded) {
			super.textToPrint += "<br>Positionnement du gobelet";
		}
		messagesToUser.setText(super.textToPrint);
		super.textToPrint = "<html>Remplissage du récipient";
	}

	@Override
	public void doStep3(boolean opt1, boolean opt2, boolean opt3) {
		messagesToUser.setText(super.textToPrint);
		super.textToPrint = "<html>Attente de l'infusion";
	}
	
	@Override
	public void addOption3() {
		super.textToPrint+= "<br>Ajout du sirop d'érable";
	}
	
	@Override
	public void doStep4() {
		messagesToUser.setText(super.textToPrint);
		super.textToPrint = "<html>Retrait du sachet";
		
	}
	
	public void addOption1() {
		super.textToPrint+= "<br>Ajout d'un nuage de lait";
	}
	
	@Override
	public void doStep5(boolean opt1, boolean opt2, boolean opt3) {
		messagesToUser.setText(super.textToPrint);
	}

	@Override
	public void calculateTime(int sugar, int size, int temp) {
		timeStep1 = 2000;
		timeStep2 =  (int)(Math.exp(temp)*1000 + (int) Math.exp(size)*500);
		timeStep3 = (int) Math.exp(size)*2000;
		timeStep4 = (int) Math.exp(size)*2000;
		timeStep5 = 2000;
		totalTime= timeStep1+timeStep2 +timeStep3+timeStep4+timeStep5;
		
	}

}
