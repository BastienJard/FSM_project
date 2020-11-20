package fr.univcotedazur.polytech.si4.fsm.project;

import javax.swing.JLabel;

public class Expresso extends Drink{
	
	public Expresso(String name, double price, JLabel messagesToUser, boolean cupAdded) {
		super(name, price, messagesToUser, cupAdded);
		super.option1 = "Nuage de lait (+0.10€)";
		super.option2 = "Sirop d'érable (+0.10€)";
		super.option3 = "Glace vanillée mixée (+0.60€)";
		super.textToPrint = "<html>Broyage des grains, chauffage de l'eau";
	}
	

	@Override
	public void doStep1() {
		super.messagesToUser.setText(textToPrint);
		super.textToPrint = "<html>Tassage des grains";
	}

	@Override
	public void doStep2() {
		if(!cupAdded) {
			super.textToPrint+= "<br>Positionnement du gobelet";
		}
		messagesToUser.setText(super.textToPrint);
		super.textToPrint = "<html>Remplissage du récipient";
	}

	@Override
	public void doStep3(boolean opt1, boolean opt2, boolean opt3) {
		messagesToUser.setText(super.textToPrint);
	}
	
@Override
public void addOption1() {
	super.textToPrint= super.textToPrint +"<br>Ajout d'un nuage de lait";
}
@Override
public void addOption2() {
	super.textToPrint+= "<br>Ajout de la glace vanille mixée";
}
@Override
public void addOption3() {
	super.textToPrint+= "<br>Ajout du sirop d'érable";
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
