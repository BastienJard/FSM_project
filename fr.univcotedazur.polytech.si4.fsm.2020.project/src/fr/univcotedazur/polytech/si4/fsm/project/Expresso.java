package fr.univcotedazur.polytech.si4.fsm.project;

import javax.swing.JLabel;

public class Expresso extends Drink{
	
	public Expresso(String name, double price, JLabel messagesToUser) {
		super(name, price, messagesToUser);
		super.option1 = "Nuage de lait (+0.10€)";
		super.option2 = "Sirop d'érable (+0.10€)";
		super.option3 = "Glace vanillée mixée (+0.60€)";
		
	}
	

	@Override
	public void doStep1() {
		super.textToPrint = "<html>Broyage des grains, chauffage de l'eau";
		super.messagesToUser.setText(textToPrint);
	}

	@Override
	public void doStep2() {
		super.textToPrint = "<html>Tassage des grains";
		if(!cupAdded) {
			super.textToPrint+= "<br>Positionnement du gobelet";
		}
		messagesToUser.setText(super.textToPrint);
		
	}

	@Override
	public void doStep3(boolean option1,boolean option2,boolean option3) {
		super.textToPrint = "<html>Remplissage du récipient" + (option1 ? "<br>Ajout d'un nuage de lait":"")+ (option2 ? "<br>Ajout du sirop d'érable":"<br>Ajout du sucre")+ (option3 ? "<br>Ajout de la glace vanillée mixée":"");
		messagesToUser.setText(super.textToPrint);
	}
	

	@Override
	public void calculateTime(int sugar, int size, int temp) {
		timeStep1 = (int)(Math.exp(temp)*1000 + (int) Math.exp(size)*750);
		timeStep3 = (int) Math.exp(size)*1500;
		timeStep2 = 3000;
		timeStep4 = 0;
		timeStep5 = 0;
		totalTime= timeStep1+timeStep2 +timeStep3+timeStep4+timeStep5;
		
	}

}
