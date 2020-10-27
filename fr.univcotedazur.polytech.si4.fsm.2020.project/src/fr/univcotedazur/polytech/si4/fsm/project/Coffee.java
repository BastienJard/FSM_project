package fr.univcotedazur.polytech.si4.fsm.project;


import javax.swing.JLabel;

public class Coffee extends Boisson{

	public Coffee(String name, double price, JLabel messagesToUser, Boolean cupAdded) {
		super(name, price, messagesToUser, cupAdded);
	}
	
	@Override
	public void doEtape1() {
		messagesToUser.setText("<html>Préparation dosette<br>Démarrage du chauffage de l’eau");
		
	}

	@Override
	public void doEtape2() {
		if(cupAdded) {
			messagesToUser.setText("<html>Chauffage de l'eau");
		}else {
			messagesToUser.setText("<html>Chauffage de l'eau<br>Positionnement du gobelet");
		}
	}

	@Override
	public void doEtape3() {
		messagesToUser.setText("<html>Remplissage du récipient<br>Ajout du sucre");
	}
	
	@Override
	public void fin() {
	}

	@Override
	public void calculateTime(int sugar, int size, int temp) {
		timeStep1 = 2000;
		timeStep2 = (int)(Math.exp(temp)*1000 + (int) Math.exp(size)*500);
		timeStep3 = (int) Math.exp(size)*2000;
		timeStep4 =0;
		timeStep5 = 0;
		totalTime= timeStep1+timeStep2 +timeStep3+timeStep4+timeStep5;
		
	}

}
