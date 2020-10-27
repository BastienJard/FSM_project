package fr.univcotedazur.polytech.si4.fsm.project;

import javax.swing.JLabel;


public class Tea extends Boisson{

	public Tea(String name, double price, JLabel messagesToUser, Boolean cupAdded) {
		super(name, price, messagesToUser, cupAdded);
	}
	
	@Override
	public void doEtape1() {
		messagesToUser.setText("<html>Récupération et positionnement<br>d’un sachet<br>Démarrage du chauffage de l’eau");
		
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
		messagesToUser.setText("<html>Ajout du sucre<br>Remplissage du récipient");
		
	}
	
	@Override
	public void doEtape4() {
		messagesToUser.setText("<html>Attente de l'infusion");
		
	}
	
	@Override
	public void doEtape5() {
		messagesToUser.setText("<html>Retrait du sachet");
		
	}

	@Override
	public void fin() {
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
