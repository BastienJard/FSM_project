package fr.univcotedazur.polytech.si4.fsm.project;

import javax.swing.JLabel;


public class Tea extends Drink{

	public Tea(String name, double price, JLabel messagesToUser, boolean cupAdded) {
		super(name, price, messagesToUser, cupAdded);
		super.option1 = "Nuage de lait (+0.10€)";
		super.option2 = "Sirop d'érable (+0.10€)";
		super.option3  = "";
	}
	
	@Override
	public void doStep1() {
		messagesToUser.setText("<html>Récupération et positionnement<br>d’un sachet<br>Démarrage du chauffage de l’eau");
		
	}

	@Override
	public void doStep2() {
		if(cupAdded) {
			messagesToUser.setText("<html>Chauffage de l'eau");
		}else {
			messagesToUser.setText("<html>Chauffage de l'eau<br>Positionnement du gobelet");
		}
		
	}

	@Override
	public void doStep3(boolean opt1, boolean opt2, boolean opt3) {
		if(opt2) {
			messagesToUser.setText("<html>Ajout du sirop d'érable<br>Remplissage du récipient");
		}else {
			messagesToUser.setText("<html>Ajout du sucre<br>Remplissage du récipient");
		}
	}
	
	@Override
	public void doStep4() {
		messagesToUser.setText("<html>Attente de l'infusion");
		
	}
	
	@Override
	public void doStep5(boolean opt1, boolean opt2, boolean opt3) {
		if(opt1) {
			messagesToUser.setText("<html>Retrait du sachet<br>Ajout d'un nuage de lait");
		}else {
			messagesToUser.setText("<html>Retrait du sachet");
		}
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
