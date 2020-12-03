package fr.univcotedazur.polytech.si4.fsm.project;

import javax.swing.JLabel;

public class Soup extends Drink{
	public Soup(String name, double price, JLabel messagesToUser) {
		super(name, price, messagesToUser);
		super.option1 ="Croutons (+0.30€)";
		super.option2 = "";
		super.option3 = "";
		
	}
	
	@Override
	public void doStep1() {
		super.textToPrint = "<html>Chauffage de l'eau";
		if(!cupAdded) {
			super.textToPrint += "<br>Positionnement du gobelet";
		}
		messagesToUser.setText(super.textToPrint);	
	}

	@Override
	public void doStep2() {
		super.textToPrint = "<html>Attente de la température adéquate<br>Récupération et versement d'une dose de soupe<br>Ajout des épices";
		messagesToUser.setText(super.textToPrint);
	}

	
	@Override
	public void doStep3(boolean option1,boolean option2,boolean option3) {
		super.textToPrint = "<html>Ecoulement de l'eau" + (option1 ? "<br>Ajout des croutons":"");
		messagesToUser.setText(super.textToPrint);
	}
	

	@Override
	public void calculateTime(int sugar, int size, int temp) {
		timeStep1 = 2000;
		timeStep2 =  (int)(Math.exp(temp)*1000 + (int) Math.exp(size)*500);
		timeStep3 = (int) Math.exp(size)*2000;
		timeStep4 = 0;
		timeStep5 = 0;
		totalTime= timeStep1+timeStep2 +timeStep3+timeStep4+timeStep5;
		
	}
}
