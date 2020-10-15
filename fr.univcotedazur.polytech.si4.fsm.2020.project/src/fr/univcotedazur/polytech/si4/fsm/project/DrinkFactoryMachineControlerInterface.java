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
	public void onUpdateBoissonRaised() {
		theGui.updateBoisson();
	}
	@Override
	public void onEnAttenteRaised() {
		theGui.enAttente();
	}
	
	@Override
	public void onUpdateSliderRaised() {
		theGui.updateSlider();
	}
	@Override
	public void onFinishTextRaised() {
		theGui.boissonPrete();
	}
	@Override
	public void onLectureCarteRaised() {
		theGui.lectureCarte();
	}

	@Override
	public void onIncreaseCoinRaised() {
		theGui.updateCoin();
		
	}

	@Override
	public void onRenduMonnaieRaised() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConfirmationNFCRaised() {
		// TODO Auto-generated method stub
		
	}
	
}
