package fr.univcotedazur.polytech.si4.fsm.project;

import javax.swing.JLabel;


public class Tea extends Drink{

	protected boolean option1 =false;
	public Tea(String name, double price, JLabel messagesToUser) {
		super(name, price, messagesToUser);
		super.option1 = "Nuage de lait (+0.10€)";
		super.option2 = "Sirop d'érable (+0.10€)";
		super.option3  = "";
		
	}
	
	@Override
	public void doStep1() {
		super.textToPrint = "<html>Récupération et positionnement<br>d’un sachet<br>Démarrage du chauffage de l’eau";
		messagesToUser.setText(super.textToPrint);	
	}

	@Override
	public void doStep2() {
		super.textToPrint = "<html>Chauffage de l'eau";
		if(!cupAdded) {
			super.textToPrint += "<br>Positionnement du gobelet";
		}
		messagesToUser.setText(super.textToPrint);
		super.textToPrint = "<html>Remplissage du récipient";
	}

	
	@Override
	public void doStep3(boolean option1,boolean option2,boolean option3) {
		super.textToPrint = "<html>Remplissage du récipient" + (option2 ? "<br>Ajout du sirop d'érable":"<br>Ajout du sucre");
		messagesToUser.setText(super.textToPrint);
	}
	
	
	@Override
	public void doStep4() {
		super.textToPrint = "Attente de l'infusion";
		messagesToUser.setText(super.textToPrint);
	}

	@Override
	public void doStep5(boolean option1) {
		super.textToPrint = "<html>Retrait du sachet" + (option1 ? "<br>Ajout d'un nuage de lait":"") ;
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
