package fr.univcotedazur.polytech.si4.fsm.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import fr.univcotedazur.polytech.si4.fsm.project.recette.RecetteStatemachine;

public class Coffee extends Boisson{

	public Coffee(String name, JProgressBar progressBar, double price, FactoryController controller, JLabel messagesToUser, RecetteStatemachine recetteFSM, Boolean cupAdded) {
		super(name,progressBar, price,  controller, messagesToUser, recetteFSM, cupAdded);
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
	
	ActionListener advance = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			totalProgress++;
			progressBar.setValue(totalProgress);
			
		}
	};

	@Override
	public void doEtape1() {
		totalTime = (int)controller.timeValue;
		progressTimer = new Timer(totalTime/100, advance);
		messagesToUser.setText("<html>Préparation dosette<br>Démarrage du chauffage de l’eau");
		recetteTimer = new Timer((int)(0.2*totalTime),doNext);
		recetteTimer.start();
		progressTimer.start();
		
	}

	@Override
	public void doEtape2() {
		recetteTimer.stop();
		if(cupAdded) {
			messagesToUser.setText("<html>Chauffage de l'eau");
		}else {
			messagesToUser.setText("<html>Chauffage de l'eau<br>Positionnement du gobelet");
		}
		recetteTimer = new Timer((int)(0.5*totalTime),doNext);
		recetteTimer.start();
	}

	@Override
	public void doEtape3() {
		recetteTimer.stop();
		messagesToUser.setText("<html>Remplissage du récipient<br>Ajout du sucre");
		recetteTimer = new Timer((int)(0.3*totalTime),finRecette);
		recetteTimer.start();
	}
	
	@Override
	public void fin() {
		progressTimer.stop();
		progressBar.setValue(100);
		recetteTimer.stop();
	}

}
