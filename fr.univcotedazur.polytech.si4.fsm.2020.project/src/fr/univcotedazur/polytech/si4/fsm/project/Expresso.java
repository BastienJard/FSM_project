package fr.univcotedazur.polytech.si4.fsm.project;

import javax.swing.JLabel;

public class Expresso extends Drink{
	
	public Expresso(String name, double price, JLabel messagesToUser, Boolean cupAdded, Boolean opt1, Boolean opt2, Boolean opt3) {
		super(name, price, messagesToUser, cupAdded, opt1, opt2, opt3);
	}
	

	@Override
	public void doStep1() {
		messagesToUser.setText("<html>Broyage des grains, chauffage de l'eau");
		
	}

	@Override
	public void doStep2() {
		if(cupAdded) {
			messagesToUser.setText("<html>Tassage des grains");
		}else {
			messagesToUser.setText("<html>Positionnement du goblet, tassage des grains");
		}
		
		
	}

	@Override
	public void doStep3() {
		if(opt1 && !opt3 && !opt2) {
			messagesToUser.setText("<html>Remplissage du récipient<br>Ajout du sucre<br>Ajout d'un nuage de lait");
		}else if(opt1 && opt3 && !opt2){
			messagesToUser.setText("<html>Remplissage du récipient<br>Ajout du sucre<br>Ajout d'un nuage de lait<br>Ajout de la glace vanille mixée");
		}else if(opt1 && opt3 && opt2){
			messagesToUser.setText("<html>Remplissage du récipient<br>Ajout du sirop d'érable<br>Ajout d'un nuage de lait<br>Ajout de la glace vanille mixée");
		}else if(opt1 && !opt3 && opt2){
			messagesToUser.setText("<html>Remplissage du récipient<br>Ajout du sirop d'érable<br>Ajout d'un nuage de lait");
		}else if(!opt1 && opt3 && !opt2){
			messagesToUser.setText("<html>Remplissage du récipient<br>Ajout du sucre<br>Ajout de la glace vanille mixée");
		}else if(!opt1 && !opt3 && opt2){
			messagesToUser.setText("<html>Remplissage du récipient<br>Ajout du sirop d'érable");
		}else if(!opt1 && opt3 && opt2){
			messagesToUser.setText("<html>Remplissage du récipient<br>Ajout du sirop d'érable<br>Ajout de la glace vanille mixée");
		}else {
			messagesToUser.setText("<html>Remplissage du récipient<br>Ajout du sucre");
		}
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
