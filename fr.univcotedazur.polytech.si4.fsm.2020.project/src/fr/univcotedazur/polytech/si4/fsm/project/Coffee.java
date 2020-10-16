package fr.univcotedazur.polytech.si4.fsm.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import fr.univcotedazur.polytech.si4.fsm.project.recette.RecetteStatemachine;

public class Coffee extends Boisson{

	public Coffee(String name, int price, int totalTime, JLabel messagesToUser, RecetteStatemachine recetteFSM) {
		super(name, price, totalTime, messagesToUser, recetteFSM);
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
		messagesToUser.setText("<html>Préparation dosette");
		recetteTimer.setDelay(totalTime*33/100);
		recetteTimer.addActionListener(doNext);
		recetteTimer.start();
		
	}

	@Override
	public void doEtape2() {
		recetteTimer.stop();
		messagesToUser.setText("<html>Chauffage de l'eau");
		recetteTimer.restart();
		recetteTimer.setDelay(totalTime*33/100);
		recetteTimer.addActionListener(doNext);
		recetteTimer.start();
	}

	@Override
	public void doEtape3() {
		recetteTimer.stop();
		messagesToUser.setText("<html>Remplissage du récipient");
		recetteTimer.restart();
		recetteTimer.setDelay(totalTime*33/100);
		recetteTimer.addActionListener(finRecette);
		recetteTimer.start();
	}
	
	@Override
	public void fin() {
		recetteTimer.stop();
		recetteTimer.restart();
	}

}
