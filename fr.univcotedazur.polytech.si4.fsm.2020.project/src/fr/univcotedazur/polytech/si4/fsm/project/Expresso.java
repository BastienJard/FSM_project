package fr.univcotedazur.polytech.si4.fsm.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import fr.univcotedazur.polytech.si4.fsm.project.recette.RecetteMachineStatemachine;

public class Expresso extends Boisson{
	
	public Expresso(String name, double price,  FactoryController controller, JLabel messagesToUser, RecetteMachineStatemachine recetteFSM, Boolean cupAdded) {
		super(name, price,  controller, messagesToUser, recetteFSM, cupAdded);
	}
	
	ActionListener doNext = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			recetteFSM.raiseNext();
		}
	};
	
	ActionListener finRecette = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			recetteFSM.raiseFinRecette();
		}
	};
	

	@Override
	public void doEtape1() {
		messagesToUser.setText("<html>Broyage des grains, chauffage de l'eau");
		
	}

	@Override
	public void doEtape2() {
		if(cupAdded) {
			messagesToUser.setText("<html>Tassage des grains");
		}else {
			messagesToUser.setText("<html>Positionnement du goblet, tassage des grains");
		}
		
		
	}

	@Override
	public void doEtape3() {
		messagesToUser.setText("<html>Ajout du sucre, remplissage du récipient");
		
	}

	@Override
	public void fin() {
	}
	
	@Override
	public void calculateTime(int sugar, int size, int temp) {
		timeStep1 = (int)(Math.exp(temp)*1000 + (int) Math.exp(size)*750);
		timeStep3 = (int) Math.exp(size)*1500;
		timeStep2 = 3000;
		timeStep4 =0;
		timeStep5 = 0;
		totalTime= timeStep1+timeStep2 +timeStep3+timeStep4+timeStep5;
		
	}

}
