package fr.univcotedazur.polytech.si4.fsm.project;


import javax.swing.JLabel;

public class Coffee extends Drink{

	public Coffee(String name, double price, JLabel messagesToUser, Boolean cupAdded, Boolean opt1, Boolean opt2, Boolean opt3) {
		super(name, price, messagesToUser, cupAdded, opt1, opt2, opt3);
	}
	
	@Override
	public void doStep1() {
		messagesToUser.setText("<html>Préparation dosette<br>Démarrage du chauffage de l’eau");
		
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
		timeStep1 = 2000;
		timeStep2 = (int)(Math.exp(temp)*1000 + (int) Math.exp(size)*500);
		timeStep3 = (int) Math.exp(size)*2000;
		timeStep4 =0;
		timeStep5 = 0;
		totalTime= timeStep1+timeStep2 +timeStep3+timeStep4+timeStep5;
		
	}

}
