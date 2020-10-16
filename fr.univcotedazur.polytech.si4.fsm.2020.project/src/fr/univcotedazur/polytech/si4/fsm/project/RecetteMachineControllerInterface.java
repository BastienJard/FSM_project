package fr.univcotedazur.polytech.si4.fsm.project;

import fr.univcotedazur.polytech.si4.fsm.project.recette.IRecetteStatemachine.SCInterfaceListener;

public class RecetteMachineControllerInterface implements SCInterfaceListener{
	private DrinkFactoryMachine theGui;
	
	public RecetteMachineControllerInterface(DrinkFactoryMachine gui) {
		theGui=gui;
	}

	@Override
	public void onEtape1Raised() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEtape2Raised() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEtape3Raised() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnAttenteRaised() {
		// TODO Auto-generated method stub
		
	}
	
}