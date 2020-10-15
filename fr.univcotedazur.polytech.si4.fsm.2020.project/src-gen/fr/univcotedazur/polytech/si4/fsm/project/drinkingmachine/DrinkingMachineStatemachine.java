/** Generated by YAKINDU Statechart Tools code generator. */
package fr.univcotedazur.polytech.si4.fsm.project.drinkingmachine;

import fr.univcotedazur.polytech.si4.fsm.project.ITimer;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DrinkingMachineStatemachine implements IDrinkingMachineStatemachine {
	protected class SCInterfaceImpl implements SCInterface {
	
		private List<SCInterfaceListener> listeners = new LinkedList<SCInterfaceListener>();
		
		public List<SCInterfaceListener> getListeners() {
			return listeners;
		}
		private boolean cancelButton;
		
		
		public void raiseCancelButton() {
			synchronized(DrinkingMachineStatemachine.this) {
				inEventQueue.add(
					new Runnable() {
						@Override
						public void run() {
							cancelButton = true;
							singleCycle();
						}
					}
				);
				runCycle();
			}
		}
		
		private boolean coffeeButton;
		
		
		public void raiseCoffeeButton() {
			synchronized(DrinkingMachineStatemachine.this) {
				inEventQueue.add(
					new Runnable() {
						@Override
						public void run() {
							coffeeButton = true;
							singleCycle();
						}
					}
				);
				runCycle();
			}
		}
		
		private boolean slider;
		
		
		public void raiseSlider() {
			synchronized(DrinkingMachineStatemachine.this) {
				inEventQueue.add(
					new Runnable() {
						@Override
						public void run() {
							slider = true;
							singleCycle();
						}
					}
				);
				runCycle();
			}
		}
		
		private boolean nFCButton;
		
		
		public void raiseNFCButton() {
			synchronized(DrinkingMachineStatemachine.this) {
				inEventQueue.add(
					new Runnable() {
						@Override
						public void run() {
							nFCButton = true;
							singleCycle();
						}
					}
				);
				runCycle();
			}
		}
		
		private boolean boissonPrete;
		
		
		public void raiseBoissonPrete() {
			synchronized(DrinkingMachineStatemachine.this) {
				inEventQueue.add(
					new Runnable() {
						@Override
						public void run() {
							boissonPrete = true;
							singleCycle();
						}
					}
				);
				runCycle();
			}
		}
		
		private boolean coinButton;
		
		
		public void raiseCoinButton() {
			synchronized(DrinkingMachineStatemachine.this) {
				inEventQueue.add(
					new Runnable() {
						@Override
						public void run() {
							coinButton = true;
							singleCycle();
						}
					}
				);
				runCycle();
			}
		}
		
		private boolean confirmationCoin;
		
		
		public void raiseConfirmationCoin() {
			synchronized(DrinkingMachineStatemachine.this) {
				inEventQueue.add(
					new Runnable() {
						@Override
						public void run() {
							confirmationCoin = true;
							singleCycle();
						}
					}
				);
				runCycle();
			}
		}
		
		private boolean nettoyageText;
		
		
		public boolean isRaisedNettoyageText() {
			synchronized(DrinkingMachineStatemachine.this) {
				return nettoyageText;
			}
		}
		
		protected void raiseNettoyageText() {
			synchronized(DrinkingMachineStatemachine.this) {
				nettoyageText = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onNettoyageTextRaised();
				}
			}
		}
		
		private boolean doReset;
		
		
		public boolean isRaisedDoReset() {
			synchronized(DrinkingMachineStatemachine.this) {
				return doReset;
			}
		}
		
		protected void raiseDoReset() {
			synchronized(DrinkingMachineStatemachine.this) {
				doReset = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onDoResetRaised();
				}
			}
		}
		
		private boolean prepareBoisson;
		
		
		public boolean isRaisedPrepareBoisson() {
			synchronized(DrinkingMachineStatemachine.this) {
				return prepareBoisson;
			}
		}
		
		protected void raisePrepareBoisson() {
			synchronized(DrinkingMachineStatemachine.this) {
				prepareBoisson = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onPrepareBoissonRaised();
				}
			}
		}
		
		private boolean enAttente;
		
		
		public boolean isRaisedEnAttente() {
			synchronized(DrinkingMachineStatemachine.this) {
				return enAttente;
			}
		}
		
		protected void raiseEnAttente() {
			synchronized(DrinkingMachineStatemachine.this) {
				enAttente = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onEnAttenteRaised();
				}
			}
		}
		
		private boolean updateSlider;
		
		
		public boolean isRaisedUpdateSlider() {
			synchronized(DrinkingMachineStatemachine.this) {
				return updateSlider;
			}
		}
		
		protected void raiseUpdateSlider() {
			synchronized(DrinkingMachineStatemachine.this) {
				updateSlider = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onUpdateSliderRaised();
				}
			}
		}
		
		private boolean updateBoisson;
		
		
		public boolean isRaisedUpdateBoisson() {
			synchronized(DrinkingMachineStatemachine.this) {
				return updateBoisson;
			}
		}
		
		protected void raiseUpdateBoisson() {
			synchronized(DrinkingMachineStatemachine.this) {
				updateBoisson = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onUpdateBoissonRaised();
				}
			}
		}
		
		private boolean finishText;
		
		
		public boolean isRaisedFinishText() {
			synchronized(DrinkingMachineStatemachine.this) {
				return finishText;
			}
		}
		
		protected void raiseFinishText() {
			synchronized(DrinkingMachineStatemachine.this) {
				finishText = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onFinishTextRaised();
				}
			}
		}
		
		private boolean lectureCarte;
		
		
		public boolean isRaisedLectureCarte() {
			synchronized(DrinkingMachineStatemachine.this) {
				return lectureCarte;
			}
		}
		
		protected void raiseLectureCarte() {
			synchronized(DrinkingMachineStatemachine.this) {
				lectureCarte = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onLectureCarteRaised();
				}
			}
		}
		
		private boolean increaseCoin;
		
		
		public boolean isRaisedIncreaseCoin() {
			synchronized(DrinkingMachineStatemachine.this) {
				return increaseCoin;
			}
		}
		
		protected void raiseIncreaseCoin() {
			synchronized(DrinkingMachineStatemachine.this) {
				increaseCoin = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onIncreaseCoinRaised();
				}
			}
		}
		
		private boolean renduMonnaie;
		
		
		public boolean isRaisedRenduMonnaie() {
			synchronized(DrinkingMachineStatemachine.this) {
				return renduMonnaie;
			}
		}
		
		protected void raiseRenduMonnaie() {
			synchronized(DrinkingMachineStatemachine.this) {
				renduMonnaie = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onRenduMonnaieRaised();
				}
			}
		}
		
		private boolean confirmationNFC;
		
		
		public boolean isRaisedConfirmationNFC() {
			synchronized(DrinkingMachineStatemachine.this) {
				return confirmationNFC;
			}
		}
		
		protected void raiseConfirmationNFC() {
			synchronized(DrinkingMachineStatemachine.this) {
				confirmationNFC = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onConfirmationNFCRaised();
				}
			}
		}
		
		protected void clearEvents() {
			cancelButton = false;
			coffeeButton = false;
			slider = false;
			nFCButton = false;
			boissonPrete = false;
			coinButton = false;
			confirmationCoin = false;
		}
		protected void clearOutEvents() {
		
		nettoyageText = false;
		doReset = false;
		prepareBoisson = false;
		enAttente = false;
		updateSlider = false;
		updateBoisson = false;
		finishText = false;
		lectureCarte = false;
		increaseCoin = false;
		renduMonnaie = false;
		confirmationNFC = false;
		}
		
	}
	
	
	protected SCInterfaceImpl sCInterface;
	
	private boolean initialized = false;
	
	public enum State {
		main_region_Demarrage,
		main_region_Pr_par_,
		main_region_En_pr_partation,
		main_region_GestionCommande,
		main_region_GestionCommande__region0_Boisson,
		main_region_GestionCommande__region0_PaimentNFC,
		main_region_GestionCommande__region0_EnAttente,
		main_region_GestionCommande__region0_Pay_,
		main_region_GestionCommande__region0_PaimentLiquide,
		$NullState$
	};
	
	private final State[] stateVector = new State[1];
	
	private int nextStateIndex;
	
	private ITimer timer;
	
	private final boolean[] timeEvents = new boolean[6];
	
	private BlockingQueue<Runnable> inEventQueue = new LinkedBlockingQueue<Runnable>();
	private boolean isRunningCycle = false;
	public DrinkingMachineStatemachine() {
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
			case main_region_Demarrage:
				main_region_Demarrage_react(true);
				break;
			case main_region_Pr_par_:
				main_region_Pr_par__react(true);
				break;
			case main_region_En_pr_partation:
				main_region_En_pr_partation_react(true);
				break;
			case main_region_GestionCommande__region0_Boisson:
				main_region_GestionCommande__region0_Boisson_react(true);
				break;
			case main_region_GestionCommande__region0_PaimentNFC:
				main_region_GestionCommande__region0_PaimentNFC_react(true);
				break;
			case main_region_GestionCommande__region0_EnAttente:
				main_region_GestionCommande__region0_EnAttente_react(true);
				break;
			case main_region_GestionCommande__region0_Pay_:
				main_region_GestionCommande__region0_Pay__react(true);
				break;
			case main_region_GestionCommande__region0_PaimentLiquide:
				main_region_GestionCommande__region0_PaimentLiquide_react(true);
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
		case main_region_Demarrage:
			return stateVector[0] == State.main_region_Demarrage;
		case main_region_Pr_par_:
			return stateVector[0] == State.main_region_Pr_par_;
		case main_region_En_pr_partation:
			return stateVector[0] == State.main_region_En_pr_partation;
		case main_region_GestionCommande:
			return stateVector[0].ordinal() >= State.
					main_region_GestionCommande.ordinal()&& stateVector[0].ordinal() <= State.main_region_GestionCommande__region0_PaimentLiquide.ordinal();
		case main_region_GestionCommande__region0_Boisson:
			return stateVector[0] == State.main_region_GestionCommande__region0_Boisson;
		case main_region_GestionCommande__region0_PaimentNFC:
			return stateVector[0] == State.main_region_GestionCommande__region0_PaimentNFC;
		case main_region_GestionCommande__region0_EnAttente:
			return stateVector[0] == State.main_region_GestionCommande__region0_EnAttente;
		case main_region_GestionCommande__region0_Pay_:
			return stateVector[0] == State.main_region_GestionCommande__region0_Pay_;
		case main_region_GestionCommande__region0_PaimentLiquide:
			return stateVector[0] == State.main_region_GestionCommande__region0_PaimentLiquide;
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
	
	public synchronized void raiseCancelButton() {
		sCInterface.raiseCancelButton();
	}
	
	public synchronized void raiseCoffeeButton() {
		sCInterface.raiseCoffeeButton();
	}
	
	public synchronized void raiseSlider() {
		sCInterface.raiseSlider();
	}
	
	public synchronized void raiseNFCButton() {
		sCInterface.raiseNFCButton();
	}
	
	public synchronized void raiseBoissonPrete() {
		sCInterface.raiseBoissonPrete();
	}
	
	public synchronized void raiseCoinButton() {
		sCInterface.raiseCoinButton();
	}
	
	public synchronized void raiseConfirmationCoin() {
		sCInterface.raiseConfirmationCoin();
	}
	
	public synchronized boolean isRaisedNettoyageText() {
		return sCInterface.isRaisedNettoyageText();
	}
	
	public synchronized boolean isRaisedDoReset() {
		return sCInterface.isRaisedDoReset();
	}
	
	public synchronized boolean isRaisedPrepareBoisson() {
		return sCInterface.isRaisedPrepareBoisson();
	}
	
	public synchronized boolean isRaisedEnAttente() {
		return sCInterface.isRaisedEnAttente();
	}
	
	public synchronized boolean isRaisedUpdateSlider() {
		return sCInterface.isRaisedUpdateSlider();
	}
	
	public synchronized boolean isRaisedUpdateBoisson() {
		return sCInterface.isRaisedUpdateBoisson();
	}
	
	public synchronized boolean isRaisedFinishText() {
		return sCInterface.isRaisedFinishText();
	}
	
	public synchronized boolean isRaisedLectureCarte() {
		return sCInterface.isRaisedLectureCarte();
	}
	
	public synchronized boolean isRaisedIncreaseCoin() {
		return sCInterface.isRaisedIncreaseCoin();
	}
	
	public synchronized boolean isRaisedRenduMonnaie() {
		return sCInterface.isRaisedRenduMonnaie();
	}
	
	public synchronized boolean isRaisedConfirmationNFC() {
		return sCInterface.isRaisedConfirmationNFC();
	}
	
	/* Entry action for state 'Demarrage'. */
	private void entryAction_main_region_Demarrage() {
		timer.setTimer(this, 0, 2500, false);
	}
	
	/* Entry action for state 'Préparé'. */
	private void entryAction_main_region_Pr_par_() {
		timer.setTimer(this, 1, (5 * 1000), false);
	}
	
	/* Entry action for state 'Boisson'. */
	private void entryAction_main_region_GestionCommande__region0_Boisson() {
		timer.setTimer(this, 2, 50, false);
	}
	
	/* Entry action for state 'PaimentNFC'. */
	private void entryAction_main_region_GestionCommande__region0_PaimentNFC() {
		timer.setTimer(this, 3, (3 * 1000), false);
	}
	
	/* Entry action for state 'EnAttente'. */
	private void entryAction_main_region_GestionCommande__region0_EnAttente() {
		timer.setTimer(this, 4, (45 * 1000), false);
	}
	
	/* Entry action for state 'Payé'. */
	private void entryAction_main_region_GestionCommande__region0_Pay_() {
		timer.setTimer(this, 5, (4 * 1000), false);
	}
	
	/* Exit action for state 'Demarrage'. */
	private void exitAction_main_region_Demarrage() {
		timer.unsetTimer(this, 0);
	}
	
	/* Exit action for state 'Préparé'. */
	private void exitAction_main_region_Pr_par_() {
		timer.unsetTimer(this, 1);
	}
	
	/* Exit action for state 'Boisson'. */
	private void exitAction_main_region_GestionCommande__region0_Boisson() {
		timer.unsetTimer(this, 2);
	}
	
	/* Exit action for state 'PaimentNFC'. */
	private void exitAction_main_region_GestionCommande__region0_PaimentNFC() {
		timer.unsetTimer(this, 3);
	}
	
	/* Exit action for state 'EnAttente'. */
	private void exitAction_main_region_GestionCommande__region0_EnAttente() {
		timer.unsetTimer(this, 4);
	}
	
	/* Exit action for state 'Payé'. */
	private void exitAction_main_region_GestionCommande__region0_Pay_() {
		timer.unsetTimer(this, 5);
	}
	
	/* 'default' enter sequence for state Demarrage */
	private void enterSequence_main_region_Demarrage_default() {
		entryAction_main_region_Demarrage();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Demarrage;
	}
	
	/* 'default' enter sequence for state Préparé */
	private void enterSequence_main_region_Pr_par__default() {
		entryAction_main_region_Pr_par_();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Pr_par_;
	}
	
	/* 'default' enter sequence for state En prépartation */
	private void enterSequence_main_region_En_pr_partation_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_En_pr_partation;
	}
	
	/* 'default' enter sequence for state GestionCommande */
	private void enterSequence_main_region_GestionCommande_default() {
		enterSequence_main_region_GestionCommande__region0_default();
	}
	
	/* 'default' enter sequence for state Boisson */
	private void enterSequence_main_region_GestionCommande__region0_Boisson_default() {
		entryAction_main_region_GestionCommande__region0_Boisson();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_GestionCommande__region0_Boisson;
	}
	
	/* 'default' enter sequence for state PaimentNFC */
	private void enterSequence_main_region_GestionCommande__region0_PaimentNFC_default() {
		entryAction_main_region_GestionCommande__region0_PaimentNFC();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_GestionCommande__region0_PaimentNFC;
	}
	
	/* 'default' enter sequence for state EnAttente */
	private void enterSequence_main_region_GestionCommande__region0_EnAttente_default() {
		entryAction_main_region_GestionCommande__region0_EnAttente();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_GestionCommande__region0_EnAttente;
	}
	
	/* 'default' enter sequence for state Payé */
	private void enterSequence_main_region_GestionCommande__region0_Pay__default() {
		entryAction_main_region_GestionCommande__region0_Pay_();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_GestionCommande__region0_Pay_;
	}
	
	/* 'default' enter sequence for state PaimentLiquide */
	private void enterSequence_main_region_GestionCommande__region0_PaimentLiquide_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_GestionCommande__region0_PaimentLiquide;
	}
	
	/* 'default' enter sequence for region main region */
	private void enterSequence_main_region_default() {
		react_main_region__entry_Default();
	}
	
	/* 'default' enter sequence for region null */
	private void enterSequence_main_region_GestionCommande__region0_default() {
		react_main_region_GestionCommande__region0__entry_Default();
	}
	
	/* Default exit sequence for state Demarrage */
	private void exitSequence_main_region_Demarrage() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_main_region_Demarrage();
	}
	
	/* Default exit sequence for state Préparé */
	private void exitSequence_main_region_Pr_par_() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_main_region_Pr_par_();
	}
	
	/* Default exit sequence for state En prépartation */
	private void exitSequence_main_region_En_pr_partation() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state GestionCommande */
	private void exitSequence_main_region_GestionCommande() {
		exitSequence_main_region_GestionCommande__region0();
	}
	
	/* Default exit sequence for state Boisson */
	private void exitSequence_main_region_GestionCommande__region0_Boisson() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_main_region_GestionCommande__region0_Boisson();
	}
	
	/* Default exit sequence for state PaimentNFC */
	private void exitSequence_main_region_GestionCommande__region0_PaimentNFC() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_main_region_GestionCommande__region0_PaimentNFC();
	}
	
	/* Default exit sequence for state EnAttente */
	private void exitSequence_main_region_GestionCommande__region0_EnAttente() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_main_region_GestionCommande__region0_EnAttente();
	}
	
	/* Default exit sequence for state Payé */
	private void exitSequence_main_region_GestionCommande__region0_Pay_() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_main_region_GestionCommande__region0_Pay_();
	}
	
	/* Default exit sequence for state PaimentLiquide */
	private void exitSequence_main_region_GestionCommande__region0_PaimentLiquide() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for region main region */
	private void exitSequence_main_region() {
		switch (stateVector[0]) {
		case main_region_Demarrage:
			exitSequence_main_region_Demarrage();
			break;
		case main_region_Pr_par_:
			exitSequence_main_region_Pr_par_();
			break;
		case main_region_En_pr_partation:
			exitSequence_main_region_En_pr_partation();
			break;
		case main_region_GestionCommande__region0_Boisson:
			exitSequence_main_region_GestionCommande__region0_Boisson();
			break;
		case main_region_GestionCommande__region0_PaimentNFC:
			exitSequence_main_region_GestionCommande__region0_PaimentNFC();
			break;
		case main_region_GestionCommande__region0_EnAttente:
			exitSequence_main_region_GestionCommande__region0_EnAttente();
			break;
		case main_region_GestionCommande__region0_Pay_:
			exitSequence_main_region_GestionCommande__region0_Pay_();
			break;
		case main_region_GestionCommande__region0_PaimentLiquide:
			exitSequence_main_region_GestionCommande__region0_PaimentLiquide();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region null */
	private void exitSequence_main_region_GestionCommande__region0() {
		switch (stateVector[0]) {
		case main_region_GestionCommande__region0_Boisson:
			exitSequence_main_region_GestionCommande__region0_Boisson();
			break;
		case main_region_GestionCommande__region0_PaimentNFC:
			exitSequence_main_region_GestionCommande__region0_PaimentNFC();
			break;
		case main_region_GestionCommande__region0_EnAttente:
			exitSequence_main_region_GestionCommande__region0_EnAttente();
			break;
		case main_region_GestionCommande__region0_Pay_:
			exitSequence_main_region_GestionCommande__region0_Pay_();
			break;
		case main_region_GestionCommande__region0_PaimentLiquide:
			exitSequence_main_region_GestionCommande__region0_PaimentLiquide();
			break;
		default:
			break;
		}
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region__entry_Default() {
		enterSequence_main_region_Demarrage_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region_GestionCommande__region0__entry_Default() {
		enterSequence_main_region_GestionCommande__region0_EnAttente_default();
	}
	
	private boolean react() {
		return false;
	}
	
	private boolean main_region_Demarrage_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (timeEvents[0]) {
				exitSequence_main_region_Demarrage();
				sCInterface.raiseEnAttente();
				
				enterSequence_main_region_GestionCommande_default();
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
	
	private boolean main_region_Pr_par__react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (timeEvents[1]) {
				exitSequence_main_region_Pr_par_();
				sCInterface.raiseNettoyageText();
				
				enterSequence_main_region_Demarrage_default();
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
	
	private boolean main_region_En_pr_partation_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.boissonPrete) {
				exitSequence_main_region_En_pr_partation();
				sCInterface.raiseFinishText();
				
				enterSequence_main_region_Pr_par__default();
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
	
	private boolean main_region_GestionCommande_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.cancelButton) {
				exitSequence_main_region_GestionCommande();
				sCInterface.raiseDoReset();
				
				enterSequence_main_region_Demarrage_default();
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
	
	private boolean main_region_GestionCommande__region0_Boisson_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (timeEvents[2]) {
				exitSequence_main_region_GestionCommande__region0_Boisson();
				enterSequence_main_region_GestionCommande__region0_EnAttente_default();
				main_region_GestionCommande_react(false);
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = main_region_GestionCommande_react(try_transition);
		}
		return did_transition;
	}
	
	private boolean main_region_GestionCommande__region0_PaimentNFC_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (timeEvents[3]) {
				exitSequence_main_region_GestionCommande__region0_PaimentNFC();
				sCInterface.raiseConfirmationNFC();
				
				enterSequence_main_region_GestionCommande__region0_Pay__default();
				main_region_GestionCommande_react(false);
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = main_region_GestionCommande_react(try_transition);
		}
		return did_transition;
	}
	
	private boolean main_region_GestionCommande__region0_EnAttente_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.nFCButton) {
				exitSequence_main_region_GestionCommande__region0_EnAttente();
				sCInterface.raiseLectureCarte();
				
				enterSequence_main_region_GestionCommande__region0_PaimentNFC_default();
				main_region_GestionCommande_react(false);
			} else {
				if (sCInterface.coffeeButton) {
					exitSequence_main_region_GestionCommande__region0_EnAttente();
					sCInterface.raiseUpdateBoisson();
					
					enterSequence_main_region_GestionCommande__region0_Boisson_default();
					main_region_GestionCommande_react(false);
				} else {
					if (timeEvents[4]) {
						exitSequence_main_region_GestionCommande();
						sCInterface.raiseDoReset();
						
						enterSequence_main_region_Demarrage_default();
						react();
					} else {
						if (sCInterface.slider) {
							exitSequence_main_region_GestionCommande__region0_EnAttente();
							sCInterface.raiseUpdateSlider();
							
							enterSequence_main_region_GestionCommande__region0_EnAttente_default();
							main_region_GestionCommande_react(false);
						} else {
							if (sCInterface.coinButton) {
								exitSequence_main_region_GestionCommande__region0_EnAttente();
								sCInterface.raiseIncreaseCoin();
								
								enterSequence_main_region_GestionCommande__region0_PaimentLiquide_default();
								main_region_GestionCommande_react(false);
							} else {
								did_transition = false;
							}
						}
					}
				}
			}
		}
		if (did_transition==false) {
			did_transition = main_region_GestionCommande_react(try_transition);
		}
		return did_transition;
	}
	
	private boolean main_region_GestionCommande__region0_Pay__react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (timeEvents[5]) {
				exitSequence_main_region_GestionCommande();
				sCInterface.raisePrepareBoisson();
				
				enterSequence_main_region_En_pr_partation_default();
				react();
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = main_region_GestionCommande_react(try_transition);
		}
		return did_transition;
	}
	
	private boolean main_region_GestionCommande__region0_PaimentLiquide_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.confirmationCoin) {
				exitSequence_main_region_GestionCommande__region0_PaimentLiquide();
				sCInterface.raiseRenduMonnaie();
				
				enterSequence_main_region_GestionCommande__region0_Pay__default();
				main_region_GestionCommande_react(false);
			} else {
				if (sCInterface.coinButton) {
					exitSequence_main_region_GestionCommande__region0_PaimentLiquide();
					sCInterface.raiseIncreaseCoin();
					
					enterSequence_main_region_GestionCommande__region0_PaimentLiquide_default();
					main_region_GestionCommande_react(false);
				} else {
					did_transition = false;
				}
			}
		}
		if (did_transition==false) {
			did_transition = main_region_GestionCommande_react(try_transition);
		}
		return did_transition;
	}
	
}
