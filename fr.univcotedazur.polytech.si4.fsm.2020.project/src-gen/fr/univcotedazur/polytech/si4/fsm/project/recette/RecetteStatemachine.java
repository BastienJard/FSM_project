/** Generated by YAKINDU Statechart Tools code generator. */
package fr.univcotedazur.polytech.si4.fsm.project.recette;

import fr.univcotedazur.polytech.si4.fsm.project.ITimer;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RecetteStatemachine implements IRecetteStatemachine {
	protected class SCInterfaceImpl implements SCInterface {
	
		private List<SCInterfaceListener> listeners = new LinkedList<SCInterfaceListener>();
		
		public List<SCInterfaceListener> getListeners() {
			return listeners;
		}
		private boolean beginRecette;
		
		
		public void raiseBeginRecette() {
			synchronized(RecetteStatemachine.this) {
				inEventQueue.add(
					new Runnable() {
						@Override
						public void run() {
							beginRecette = true;
							singleCycle();
						}
					}
				);
				runCycle();
			}
		}
		
		private boolean next;
		
		
		public void raiseNext() {
			synchronized(RecetteStatemachine.this) {
				inEventQueue.add(
					new Runnable() {
						@Override
						public void run() {
							next = true;
							singleCycle();
						}
					}
				);
				runCycle();
			}
		}
		
		private boolean finRecette;
		
		
		public void raiseFinRecette() {
			synchronized(RecetteStatemachine.this) {
				inEventQueue.add(
					new Runnable() {
						@Override
						public void run() {
							finRecette = true;
							singleCycle();
						}
					}
				);
				runCycle();
			}
		}
		
		private boolean etape1;
		
		
		public boolean isRaisedEtape1() {
			synchronized(RecetteStatemachine.this) {
				return etape1;
			}
		}
		
		protected void raiseEtape1() {
			synchronized(RecetteStatemachine.this) {
				etape1 = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onEtape1Raised();
				}
			}
		}
		
		private boolean etape2;
		
		
		public boolean isRaisedEtape2() {
			synchronized(RecetteStatemachine.this) {
				return etape2;
			}
		}
		
		protected void raiseEtape2() {
			synchronized(RecetteStatemachine.this) {
				etape2 = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onEtape2Raised();
				}
			}
		}
		
		private boolean etape3;
		
		
		public boolean isRaisedEtape3() {
			synchronized(RecetteStatemachine.this) {
				return etape3;
			}
		}
		
		protected void raiseEtape3() {
			synchronized(RecetteStatemachine.this) {
				etape3 = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onEtape3Raised();
				}
			}
		}
		
		private boolean enAttente;
		
		
		public boolean isRaisedEnAttente() {
			synchronized(RecetteStatemachine.this) {
				return enAttente;
			}
		}
		
		protected void raiseEnAttente() {
			synchronized(RecetteStatemachine.this) {
				enAttente = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onEnAttenteRaised();
				}
			}
		}
		
		protected void clearEvents() {
			beginRecette = false;
			next = false;
			finRecette = false;
		}
		protected void clearOutEvents() {
		
		etape1 = false;
		etape2 = false;
		etape3 = false;
		enAttente = false;
		}
		
	}
	
	
	protected SCInterfaceImpl sCInterface;
	
	private boolean initialized = false;
	
	public enum State {
		main_region_DebutRecette,
		main_region_Etape1,
		main_region_Etape2,
		main_region_Etape3,
		main_region_EnAttente,
		$NullState$
	};
	
	private final State[] stateVector = new State[1];
	
	private int nextStateIndex;
	
	private ITimer timer;
	
	private final boolean[] timeEvents = new boolean[1];
	
	private BlockingQueue<Runnable> inEventQueue = new LinkedBlockingQueue<Runnable>();
	private boolean isRunningCycle = false;
	public RecetteStatemachine() {
		sCInterface = new SCInterfaceImpl();
	}
	
	public synchronized void init() {
		this.initialized = true;
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		for (int i = 0; i < 1; i++) {
			stateVector[i] = State.$NullState$;
		}
		clearEvents();
		clearOutEvents();
	}
	
	public synchronized void enter() {
		if (!initialized) {
			throw new IllegalStateException(
				"The state machine needs to be initialized first by calling the init() function."
			);
		}
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		enterSequence_main_region_default();
	}
	
	public synchronized void runCycle() {
		if (!initialized)
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		
		if (isRunningCycle) {
			return;
		}
		isRunningCycle = true;
		
		clearOutEvents();
	
		Runnable task = getNextEvent();
		if (task == null) {
			task = getDefaultEvent();
		}
		
		while (task != null) {
			task.run();
			clearEvents();
			task = getNextEvent();
		}
		
		isRunningCycle = false;
	}
	
	protected synchronized void singleCycle() {
		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
			switch (stateVector[nextStateIndex]) {
			case main_region_DebutRecette:
				main_region_DebutRecette_react(true);
				break;
			case main_region_Etape1:
				main_region_Etape1_react(true);
				break;
			case main_region_Etape2:
				main_region_Etape2_react(true);
				break;
			case main_region_Etape3:
				main_region_Etape3_react(true);
				break;
			case main_region_EnAttente:
				main_region_EnAttente_react(true);
				break;
			default:
				// $NullState$
			}
		}
	}
	
	protected Runnable getNextEvent() {
		if(!inEventQueue.isEmpty()) {
			return inEventQueue.poll();
		}
		return null;
	}
	
	protected Runnable getDefaultEvent() {
		return new Runnable() {
			@Override
			public void run() {
				singleCycle();
			}
		};
	}
	
	public synchronized void exit() {
		exitSequence_main_region();
	}
	
	/**
	 * @see IStatemachine#isActive()
	 */
	public synchronized boolean isActive() {
		return stateVector[0] != State.$NullState$;
	}
	
	/** 
	* Always returns 'false' since this state machine can never become final.
	*
	* @see IStatemachine#isFinal()
	*/
	public synchronized boolean isFinal() {
		return false;
	}
	/**
	* This method resets the incoming events (time events included).
	*/
	protected void clearEvents() {
		sCInterface.clearEvents();
		for (int i=0; i<timeEvents.length; i++) {
			timeEvents[i] = false;
		}
	}
	
	/**
	* This method resets the outgoing events.
	*/
	protected void clearOutEvents() {
		sCInterface.clearOutEvents();
	}
	
	/**
	* Returns true if the given state is currently active otherwise false.
	*/
	public synchronized boolean isStateActive(State state) {
	
		switch (state) {
		case main_region_DebutRecette:
			return stateVector[0] == State.main_region_DebutRecette;
		case main_region_Etape1:
			return stateVector[0] == State.main_region_Etape1;
		case main_region_Etape2:
			return stateVector[0] == State.main_region_Etape2;
		case main_region_Etape3:
			return stateVector[0] == State.main_region_Etape3;
		case main_region_EnAttente:
			return stateVector[0] == State.main_region_EnAttente;
		default:
			return false;
		}
	}
	
	/**
	* Set the {@link ITimer} for the state machine. It must be set
	* externally on a timed state machine before a run cycle can be correctly
	* executed.
	* 
	* @param timer
	*/
	public synchronized void setTimer(ITimer timer) {
		this.timer = timer;
	}
	
	/**
	* Returns the currently used timer.
	* 
	* @return {@link ITimer}
	*/
	public ITimer getTimer() {
		return timer;
	}
	
	public synchronized void timeElapsed(int eventID) {
		inEventQueue.add(new Runnable() {
			@Override
			public void run() {
				timeEvents[eventID] = true;
				singleCycle();
			}
		});
		runCycle();
	}
	
	public SCInterface getSCInterface() {
		return sCInterface;
	}
	
	public synchronized void raiseBeginRecette() {
		sCInterface.raiseBeginRecette();
	}
	
	public synchronized void raiseNext() {
		sCInterface.raiseNext();
	}
	
	public synchronized void raiseFinRecette() {
		sCInterface.raiseFinRecette();
	}
	
	public synchronized boolean isRaisedEtape1() {
		return sCInterface.isRaisedEtape1();
	}
	
	public synchronized boolean isRaisedEtape2() {
		return sCInterface.isRaisedEtape2();
	}
	
	public synchronized boolean isRaisedEtape3() {
		return sCInterface.isRaisedEtape3();
	}
	
	public synchronized boolean isRaisedEnAttente() {
		return sCInterface.isRaisedEnAttente();
	}
	
	/* Entry action for state 'EnAttente'. */
	private void entryAction_main_region_EnAttente() {
		timer.setTimer(this, 0, (10 * 1000), false);
	}
	
	/* Exit action for state 'EnAttente'. */
	private void exitAction_main_region_EnAttente() {
		timer.unsetTimer(this, 0);
	}
	
	/* 'default' enter sequence for state DebutRecette */
	private void enterSequence_main_region_DebutRecette_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_DebutRecette;
	}
	
	/* 'default' enter sequence for state Etape1 */
	private void enterSequence_main_region_Etape1_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Etape1;
	}
	
	/* 'default' enter sequence for state Etape2 */
	private void enterSequence_main_region_Etape2_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Etape2;
	}
	
	/* 'default' enter sequence for state Etape3 */
	private void enterSequence_main_region_Etape3_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Etape3;
	}
	
	/* 'default' enter sequence for state EnAttente */
	private void enterSequence_main_region_EnAttente_default() {
		entryAction_main_region_EnAttente();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_EnAttente;
	}
	
	/* 'default' enter sequence for region main region */
	private void enterSequence_main_region_default() {
		react_main_region__entry_Default();
	}
	
	/* Default exit sequence for state DebutRecette */
	private void exitSequence_main_region_DebutRecette() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Etape1 */
	private void exitSequence_main_region_Etape1() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Etape2 */
	private void exitSequence_main_region_Etape2() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state Etape3 */
	private void exitSequence_main_region_Etape3() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state EnAttente */
	private void exitSequence_main_region_EnAttente() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_main_region_EnAttente();
	}
	
	/* Default exit sequence for region main region */
	private void exitSequence_main_region() {
		switch (stateVector[0]) {
		case main_region_DebutRecette:
			exitSequence_main_region_DebutRecette();
			break;
		case main_region_Etape1:
			exitSequence_main_region_Etape1();
			break;
		case main_region_Etape2:
			exitSequence_main_region_Etape2();
			break;
		case main_region_Etape3:
			exitSequence_main_region_Etape3();
			break;
		case main_region_EnAttente:
			exitSequence_main_region_EnAttente();
			break;
		default:
			break;
		}
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region__entry_Default() {
		enterSequence_main_region_DebutRecette_default();
	}
	
	private boolean react() {
		return false;
	}
	
	private boolean main_region_DebutRecette_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.beginRecette) {
				exitSequence_main_region_DebutRecette();
				sCInterface.raiseEtape1();
				
				enterSequence_main_region_Etape1_default();
				react();
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = react();
		}
		return did_transition;
	}
	
	private boolean main_region_Etape1_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.next) {
				exitSequence_main_region_Etape1();
				sCInterface.raiseEtape2();
				
				enterSequence_main_region_Etape2_default();
				react();
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = react();
		}
		return did_transition;
	}
	
	private boolean main_region_Etape2_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.next) {
				exitSequence_main_region_Etape2();
				sCInterface.raiseEtape3();
				
				enterSequence_main_region_Etape3_default();
				react();
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = react();
		}
		return did_transition;
	}
	
	private boolean main_region_Etape3_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.finRecette) {
				exitSequence_main_region_Etape3();
				sCInterface.raiseEnAttente();
				
				enterSequence_main_region_EnAttente_default();
				react();
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = react();
		}
		return did_transition;
	}
	
	private boolean main_region_EnAttente_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (timeEvents[0]) {
				exitSequence_main_region_EnAttente();
				enterSequence_main_region_DebutRecette_default();
				react();
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = react();
		}
		return did_transition;
	}
	
}
