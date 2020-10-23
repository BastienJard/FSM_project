package fr.univcotedazur.polytech.si4.fsm.project;

import fr.univcotedazur.polytech.si4.fsm.project.recette.IRecetteStatemachine.SCInterfaceListener;

public class RecetteMachineControllerInterface implements SCInterfaceListener{
	private DrinkFactoryMachine theGui;
	
	public RecetteMachineControllerInterface(DrinkFactoryMachine gui) {
		theGui=gui;
	}

	@Override
	public void onEtape1Raised() {
		theGui.controller.boisson.doEtape1();
		
	}

	@Override
	public void onEtape2Raised() {
		theGui.controller.boisson.doEtape2();
		
	}

	@Override
	public void onEtape3Raised() {
		theGui.controller.boisson.doEtape3();
		
	}

	@Override
	public void onEnAttenteRaised() {
		theGui.controller.boisson.fin();
		theGui.myFSM.raiseBoissonPrete();
		
	}

	@Override
	public void onEtape4Raised() {
		theGui.controller.boisson.doEtape4();
		
	}

	@Override
	public void onEtape5Raised() {
		theGui.controller.boisson.doEtape5();
		
	}
	
}