/** Generated by YAKINDU Statechart Tools code generator. */
package fr.univcotedazur.polytech.si4.fsm.project.drinkingmachine;

import fr.univcotedazur.polytech.si4.fsm.project.IStatemachine;
import fr.univcotedazur.polytech.si4.fsm.project.ITimerCallback;
import java.util.List;


public interface IDrinkingMachineStatemachine extends ITimerCallback,IStatemachine {
	public interface SCInterface {
	
		public void raiseCancelButton();
		
		public void raiseBoissonButton();
		
		public void raiseSlider();
		
		public void raiseNFCButton();
		
		public void raiseBoissonPrete();
		
		public void raiseCoinButton();
		
		public void raiseConfirmationLiquide();
		
		public boolean isRaisedNettoyageText();
		
		public boolean isRaisedDoReset();
		
		public boolean isRaisedPrepareBoisson();
		
		public boolean isRaisedEnAttente();
		
		public boolean isRaisedUpdateSlider();
		
		public boolean isRaisedUpdateBoisson();
		
		public boolean isRaisedFinishText();
		
		public boolean isRaisedLectureCarte();
		
		public boolean isRaisedIncreaseCoin();
		
		public boolean isRaisedRenduMonnaie();
		
		public boolean isRaisedPaiementNFC();
		
		public boolean isRaisedAttentePaiement();
		
		public boolean isRaisedErreurPaiment();
		
		public boolean getIsThereBoisson();
		
		public void setIsThereBoisson(boolean value);
		
	public List<SCInterfaceListener> getListeners();
	}
	
	public interface SCInterfaceListener {
	
		public void onNettoyageTextRaised();
		public void onDoResetRaised();
		public void onPrepareBoissonRaised();
		public void onEnAttenteRaised();
		public void onUpdateSliderRaised();
		public void onUpdateBoissonRaised();
		public void onFinishTextRaised();
		public void onLectureCarteRaised();
		public void onIncreaseCoinRaised();
		public void onRenduMonnaieRaised();
		public void onPaiementNFCRaised();
		public void onAttentePaiementRaised();
		public void onErreurPaimentRaised();
		}
	
	public SCInterface getSCInterface();
	
}
