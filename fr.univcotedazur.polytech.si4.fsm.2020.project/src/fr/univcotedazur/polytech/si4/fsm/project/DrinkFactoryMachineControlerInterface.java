package fr.univcotedazur.polytech.si4.fsm.project;

import fr.univcotedazur.polytech.si4.fsm.project.drinkingmachine.IDrinkingMachineStatemachine.SCInterfaceListener;

public class DrinkFactoryMachineControlerInterface implements SCInterfaceListener{
	private DrinkFactoryMachine theGui;
	
	public DrinkFactoryMachineControlerInterface(DrinkFactoryMachine GUI) {
		this.theGui=GUI;
	}
		
	@Override
	public void onNettoyageTextRaised() {
		theGui.nettoyageText();
	}
	@Override
	public void onDoResetRaised() {
		theGui.doReset();
	}
	@Override
	public void onPrepareBoissonRaised() {
		theGui.prepareBoisson();
	}
	@Override
	public void onEnAttenteRaised() {
		
	}
	@Override
	public void onUpdateBoissonRaised() {
		
	}
	@Override
	public void onFinishTextRaised() {
		theGui.boissonPrete();
	}
}
