package fr.univcotedazur.polytech.si4.fsm.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import fr.univcotedazur.polytech.si4.fsm.project.recette.RecetteStatemachine;

public class Tea extends Boisson{

	public Tea(String name, double price, FactoryController controller, JLabel messagesToUser, RecetteStatemachine recetteFSM) {
		super(name, price, controller, messagesToUser, recetteFSM);
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
		totalTime = (int)controller.timeValue;
		messagesToUser.setText("<html>Récupération et positionnement<br>d’un sachet<br>Démarrage du chauffage de l’eau");
		recetteTimer = new Timer((int)(0.2*totalTime),doNext);
		recetteTimer.start();
		
	}

	@Override
	public void doEtape2() {
		recetteTimer.stop();
		messagesToUser.setText("<html>Chauffage de l'eau<br>Positionnement du gobelet");
		recetteTimer = new Timer((int)(0.3*totalTime),doNext);
		recetteTimer.start();
		
	}

	@Override
	public void doEtape3() {
		recetteTimer.stop();
		messagesToUser.setText("<html>Ajout du sucre<br>Remplissage du récipient");
		recetteTimer = new Timer((int)(0.2*totalTime),doNext);
		recetteTimer.start();
		
	}
	
	@Override
	public void doEtape4() {
		recetteTimer.stop();
		messagesToUser.setText("<html>Attente de l'infusion");
		recetteTimer = new Timer((int)(0.3*totalTime),doNext);
		recetteTimer.start();
		
	}
	
	@Override
	public void doEtape5() {
		recetteTimer.stop();
		messagesToUser.setText("<html>Retrait du sachet");
		recetteTimer = new Timer((int)(0.2*totalTime),finRecette);
		recetteTimer.start();
		
	}

	@Override
	public void fin() {
		recetteTimer.stop();
	}

}
