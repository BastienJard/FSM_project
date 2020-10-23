/** Generated by YAKINDU Statechart Tools code generator. */
package fr.univcotedazur.polytech.si4.fsm.project.recette;

import fr.univcotedazur.polytech.si4.fsm.project.IStatemachine;
import fr.univcotedazur.polytech.si4.fsm.project.ITimerCallback;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public interface IRecetteStatemachine extends ITimerCallback,IStatemachine {
	public interface SCInterface {
	
		public void raiseBeginRecette();
		
		public void raiseNext();
		
		public void raiseFinRecette();
		
		public boolean isRaisedEtape1();
		
		public boolean isRaisedEtape2();
		
		public boolean isRaisedEtape3();
		
		public boolean isRaisedEtape4();
		
		public boolean isRaisedEtape5();
		
		public boolean isRaisedEnAttente();
		
	public List<SCInterfaceListener> getListeners();
	}
	
	public interface SCInterfaceListener {
	
		public void onEtape1Raised();
		public void onEtape2Raised();
		public void onEtape3Raised();
		public void onEtape4Raised();
		public void onEtape5Raised();
		public void onEnAttenteRaised();
		}
	
	public SCInterface getSCInterface();
	
}