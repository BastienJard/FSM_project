/** Generated by YAKINDU Statechart Tools code generator. */
package fr.univcotedazur.polytech.si4.fsm.project.drinkingMachine;

import fr.univcotedazur.polytech.si4.fsm.project.IStatemachine;
import fr.univcotedazur.polytech.si4.fsm.project.ITimed;
import fr.univcotedazur.polytech.si4.fsm.project.ITimerService;
import fr.univcotedazur.polytech.si4.fsm.project.rx.Observable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DrinkingMachineStatemachine implements IStatemachine, ITimed {
	public enum State {
		MAIN_REGION_START,
		MAIN_REGION_WAITINGTAKEDRINK,
		MAIN_REGION_PREPAREDRINK,
		MAIN_REGION_GESTIONCOMMANDE,
		MAIN_REGION_GESTIONCOMMANDE_CHOOSEGESTION_WAITING,
		MAIN_REGION_GESTIONCOMMANDE_RESETTIMER_RESET,
		MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_WAITINGPAYMENT,
		MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYMENTNFC,
		MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_INSERTEDMONEY,
		MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYED,
		MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_READCARD,
		MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYMENTMONEY,
		$NULLSTATE$
	};
	
	private final State[] stateVector = new State[3];
	
	private int nextStateIndex;
	
	private ITimerService timerService;
	
	private final boolean[] timeEvents = new boolean[6];
	
	private BlockingQueue<Runnable> inEventQueue = new LinkedBlockingQueue<Runnable>();
	private boolean isExecuting;
	
	protected boolean getIsExecuting() {
		synchronized(DrinkingMachineStatemachine.this) {
			return isExecuting;
		}
	}
	
	protected void setIsExecuting(boolean value) {
		synchronized(DrinkingMachineStatemachine.this) {
			this.isExecuting = value;
		}
	}
	public DrinkingMachineStatemachine() {
		for (int i = 0; i < 3; i++) {
			stateVector[i] = State.$NULLSTATE$;
		}
		
		clearInEvents();
		
		setIsThereBoisson(false);
		
		isExecuting = false;
	}
	
	public synchronized void enter() {
		if (timerService == null) {
			throw new IllegalStateException("Timer service must be set.");
		}
		
		
		if (getIsExecuting()) {
			return;
		}
		isExecuting = true;
		enterSequence_main_region_default();
		isExecuting = false;
	}
	
	public synchronized void exit() {
		if (getIsExecuting()) {
			return;
		}
		isExecuting = true;
		exitSequence_main_region();
		isExecuting = false;
	}
	
	/**
	 * @see IStatemachine#isActive()
	 */
	public synchronized boolean isActive() {
		return stateVector[0] != State.$NULLSTATE$||stateVector[1] != State.$NULLSTATE$||stateVector[2] != State.$NULLSTATE$;
	}
	
	/** 
	* Always returns 'false' since this state machine can never become final.
	*
	* @see IStatemachine#isFinal()
	*/
	public synchronized boolean isFinal() {
		return false;
	}
	private void clearInEvents() {
		cancelButton = false;
		drinkButton = false;
		slider = false;
		nFCButton = false;
		drinkReady = false;
		moneyButton = false;
		confirmationMoney = false;
		takeCup = false;
		timeEvents[0] = false;
		timeEvents[1] = false;
		timeEvents[2] = false;
		timeEvents[3] = false;
		timeEvents[4] = false;
		timeEvents[5] = false;
	}
	
	private void runCycle() {
		if (timerService == null) {
			throw new IllegalStateException("Timer service must be set.");
		}
		
		
		if (getIsExecuting()) {
			return;
		}
		isExecuting = true;
		nextEvent();
		do { 
			for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
				switch (stateVector[nextStateIndex]) {
				case MAIN_REGION_START:
					main_region_Start_react(true);
					break;
				case MAIN_REGION_WAITINGTAKEDRINK:
					main_region_WaitingTakeDrink_react(true);
					break;
				case MAIN_REGION_PREPAREDRINK:
					main_region_PrepareDrink_react(true);
					break;
				case MAIN_REGION_GESTIONCOMMANDE_CHOOSEGESTION_WAITING:
					main_region_GestionCommande_chooseGestion_Waiting_react(true);
					break;
				case MAIN_REGION_GESTIONCOMMANDE_RESETTIMER_RESET:
					main_region_GestionCommande_resetTimer_reset_react(true);
					break;
				case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_WAITINGPAYMENT:
					main_region_GestionCommande_paiementGestion_WaitingPayment_react(true);
					break;
				case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYMENTNFC:
					main_region_GestionCommande_paiementGestion_PaymentNFC_react(true);
					break;
				case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_INSERTEDMONEY:
					main_region_GestionCommande_paiementGestion_InsertedMoney_react(true);
					break;
				case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYED:
					main_region_GestionCommande_paiementGestion_Payed_react(true);
					break;
				case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_READCARD:
					main_region_GestionCommande_paiementGestion_ReadCard_react(true);
					break;
				case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYMENTMONEY:
					main_region_GestionCommande_paiementGestion_PaymentMoney_react(true);
					break;
				default:
					// $NULLSTATE$
				}
			}
			
			clearInEvents();
			nextEvent();
		} while ((((((((((((((cancelButton || drinkButton) || slider) || nFCButton) || drinkReady) || moneyButton) || confirmationMoney) || takeCup) || timeEvents[0]) || timeEvents[1]) || timeEvents[2]) || timeEvents[3]) || timeEvents[4]) || timeEvents[5]));
		
		isExecuting = false;
	}
	
	protected void nextEvent() {
		if(!inEventQueue.isEmpty()) {
			inEventQueue.poll().run();
			return;
		}
	}
	/**
	* Returns true if the given state is currently active otherwise false.
	*/
	public synchronized boolean isStateActive(State state) {
	
		switch (state) {
		case MAIN_REGION_START:
			return stateVector[0] == State.MAIN_REGION_START;
		case MAIN_REGION_WAITINGTAKEDRINK:
			return stateVector[0] == State.MAIN_REGION_WAITINGTAKEDRINK;
		case MAIN_REGION_PREPAREDRINK:
			return stateVector[0] == State.MAIN_REGION_PREPAREDRINK;
		case MAIN_REGION_GESTIONCOMMANDE:
			return stateVector[0].ordinal() >= State.
					MAIN_REGION_GESTIONCOMMANDE.ordinal()&& stateVector[0].ordinal() <= State.MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYMENTMONEY.ordinal();
		case MAIN_REGION_GESTIONCOMMANDE_CHOOSEGESTION_WAITING:
			return stateVector[0] == State.MAIN_REGION_GESTIONCOMMANDE_CHOOSEGESTION_WAITING;
		case MAIN_REGION_GESTIONCOMMANDE_RESETTIMER_RESET:
			return stateVector[1] == State.MAIN_REGION_GESTIONCOMMANDE_RESETTIMER_RESET;
		case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_WAITINGPAYMENT:
			return stateVector[2] == State.MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_WAITINGPAYMENT;
		case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYMENTNFC:
			return stateVector[2] == State.MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYMENTNFC;
		case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_INSERTEDMONEY:
			return stateVector[2] == State.MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_INSERTEDMONEY;
		case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYED:
			return stateVector[2] == State.MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYED;
		case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_READCARD:
			return stateVector[2] == State.MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_READCARD;
		case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYMENTMONEY:
			return stateVector[2] == State.MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYMENTMONEY;
		default:
			return false;
		}
	}
	
	public synchronized void setTimerService(ITimerService timerService) {
		this.timerService = timerService;
	}
	
	public ITimerService getTimerService() {
		return timerService;
	}
	
	public synchronized void raiseTimeEvent(int eventID) {
		inEventQueue.add(new Runnable() {
			@Override
			public void run() {
				timeEvents[eventID] = true;
			}
		});
		runCycle();
	}
	
	private boolean cancelButton;
	
	
	public void raiseCancelButton() {
		synchronized(DrinkingMachineStatemachine.this) {
			inEventQueue.add(new Runnable() {
				@Override
				public void run() {
					cancelButton = true;
				}
			});
			runCycle();
		}
	}
	
	private boolean drinkButton;
	
	
	public void raiseDrinkButton() {
		synchronized(DrinkingMachineStatemachine.this) {
			inEventQueue.add(new Runnable() {
				@Override
				public void run() {
					drinkButton = true;
				}
			});
			runCycle();
		}
	}
	
	private boolean slider;
	
	
	public void raiseSlider() {
		synchronized(DrinkingMachineStatemachine.this) {
			inEventQueue.add(new Runnable() {
				@Override
				public void run() {
					slider = true;
				}
			});
			runCycle();
		}
	}
	
	private boolean nFCButton;
	
	
	public void raiseNFCButton() {
		synchronized(DrinkingMachineStatemachine.this) {
			inEventQueue.add(new Runnable() {
				@Override
				public void run() {
					nFCButton = true;
				}
			});
			runCycle();
		}
	}
	
	private boolean drinkReady;
	
	
	public void raiseDrinkReady() {
		synchronized(DrinkingMachineStatemachine.this) {
			inEventQueue.add(new Runnable() {
				@Override
				public void run() {
					drinkReady = true;
				}
			});
			runCycle();
		}
	}
	
	private boolean moneyButton;
	
	
	public void raiseMoneyButton() {
		synchronized(DrinkingMachineStatemachine.this) {
			inEventQueue.add(new Runnable() {
				@Override
				public void run() {
					moneyButton = true;
				}
			});
			runCycle();
		}
	}
	
	private boolean confirmationMoney;
	
	
	public void raiseConfirmationMoney() {
		synchronized(DrinkingMachineStatemachine.this) {
			inEventQueue.add(new Runnable() {
				@Override
				public void run() {
					confirmationMoney = true;
				}
			});
			runCycle();
		}
	}
	
	private boolean takeCup;
	
	
	public void raiseTakeCup() {
		synchronized(DrinkingMachineStatemachine.this) {
			inEventQueue.add(new Runnable() {
				@Override
				public void run() {
					takeCup = true;
				}
			});
			runCycle();
		}
	}
	
	private boolean cleaningText;
	
	
	protected void raiseCleaningText() {
		synchronized(DrinkingMachineStatemachine.this) {
			cleaningText = true;
			cleaningTextObservable.next(null);
		}
	}
	
	private Observable<Void> cleaningTextObservable = new Observable<Void>();
	
	public Observable<Void> getCleaningText() {
		return cleaningTextObservable;
	}
	
	private boolean doReset;
	
	
	protected void raiseDoReset() {
		synchronized(DrinkingMachineStatemachine.this) {
			doReset = true;
			doResetObservable.next(null);
		}
	}
	
	private Observable<Void> doResetObservable = new Observable<Void>();
	
	public Observable<Void> getDoReset() {
		return doResetObservable;
	}
	
	private boolean prepareDrink;
	
	
	protected void raisePrepareDrink() {
		synchronized(DrinkingMachineStatemachine.this) {
			prepareDrink = true;
			prepareDrinkObservable.next(null);
		}
	}
	
	private Observable<Void> prepareDrinkObservable = new Observable<Void>();
	
	public Observable<Void> getPrepareDrink() {
		return prepareDrinkObservable;
	}
	
	private boolean waiting;
	
	
	protected void raiseWaiting() {
		synchronized(DrinkingMachineStatemachine.this) {
			waiting = true;
			waitingObservable.next(null);
		}
	}
	
	private Observable<Void> waitingObservable = new Observable<Void>();
	
	public Observable<Void> getWaiting() {
		return waitingObservable;
	}
	
	private boolean updateSlider;
	
	
	protected void raiseUpdateSlider() {
		synchronized(DrinkingMachineStatemachine.this) {
			updateSlider = true;
			updateSliderObservable.next(null);
		}
	}
	
	private Observable<Void> updateSliderObservable = new Observable<Void>();
	
	public Observable<Void> getUpdateSlider() {
		return updateSliderObservable;
	}
	
	private boolean updateDrink;
	
	
	protected void raiseUpdateDrink() {
		synchronized(DrinkingMachineStatemachine.this) {
			updateDrink = true;
			updateDrinkObservable.next(null);
		}
	}
	
	private Observable<Void> updateDrinkObservable = new Observable<Void>();
	
	public Observable<Void> getUpdateDrink() {
		return updateDrinkObservable;
	}
	
	private boolean waitingDrink;
	
	
	protected void raiseWaitingDrink() {
		synchronized(DrinkingMachineStatemachine.this) {
			waitingDrink = true;
			waitingDrinkObservable.next(null);
		}
	}
	
	private Observable<Void> waitingDrinkObservable = new Observable<Void>();
	
	public Observable<Void> getWaitingDrink() {
		return waitingDrinkObservable;
	}
	
	private boolean readCard;
	
	
	protected void raiseReadCard() {
		synchronized(DrinkingMachineStatemachine.this) {
			readCard = true;
			readCardObservable.next(null);
		}
	}
	
	private Observable<Void> readCardObservable = new Observable<Void>();
	
	public Observable<Void> getReadCard() {
		return readCardObservable;
	}
	
	private boolean increaseMoney;
	
	
	protected void raiseIncreaseMoney() {
		synchronized(DrinkingMachineStatemachine.this) {
			increaseMoney = true;
			increaseMoneyObservable.next(null);
		}
	}
	
	private Observable<Void> increaseMoneyObservable = new Observable<Void>();
	
	public Observable<Void> getIncreaseMoney() {
		return increaseMoneyObservable;
	}
	
	private boolean returnMoney;
	
	
	protected void raiseReturnMoney() {
		synchronized(DrinkingMachineStatemachine.this) {
			returnMoney = true;
			returnMoneyObservable.next(null);
		}
	}
	
	private Observable<Void> returnMoneyObservable = new Observable<Void>();
	
	public Observable<Void> getReturnMoney() {
		return returnMoneyObservable;
	}
	
	private boolean paymentNFC;
	
	
	protected void raisePaymentNFC() {
		synchronized(DrinkingMachineStatemachine.this) {
			paymentNFC = true;
			paymentNFCObservable.next(null);
		}
	}
	
	private Observable<Void> paymentNFCObservable = new Observable<Void>();
	
	public Observable<Void> getPaymentNFC() {
		return paymentNFCObservable;
	}
	
	private boolean waitingPayment;
	
	
	protected void raiseWaitingPayment() {
		synchronized(DrinkingMachineStatemachine.this) {
			waitingPayment = true;
			waitingPaymentObservable.next(null);
		}
	}
	
	private Observable<Void> waitingPaymentObservable = new Observable<Void>();
	
	public Observable<Void> getWaitingPayment() {
		return waitingPaymentObservable;
	}
	
	private boolean errorPayment;
	
	
	protected void raiseErrorPayment() {
		synchronized(DrinkingMachineStatemachine.this) {
			errorPayment = true;
			errorPaymentObservable.next(null);
		}
	}
	
	private Observable<Void> errorPaymentObservable = new Observable<Void>();
	
	public Observable<Void> getErrorPayment() {
		return errorPaymentObservable;
	}
	
	private boolean isThereBoisson;
	
	public synchronized boolean getIsThereBoisson() {
		synchronized(DrinkingMachineStatemachine.this) {
			return isThereBoisson;
		}
	}
	
	public void setIsThereBoisson(boolean value) {
		synchronized(DrinkingMachineStatemachine.this) {
			this.isThereBoisson = value;
		}
	}
	
	/* Entry action for state 'Start'. */
	private void entryAction_main_region_Start() {
		timerService.setTimer(this, 0, 3500, false);
	}
	
	/* Entry action for state 'reset'. */
	private void entryAction_main_region_GestionCommande_resetTimer_reset() {
		timerService.setTimer(this, 1, (45 * 1000), false);
	}
	
	/* Entry action for state 'PaymentNFC'. */
	private void entryAction_main_region_GestionCommande_paiementGestion_PaymentNFC() {
		timerService.setTimer(this, 2, 10, true);
	}
	
	/* Entry action for state 'Payed'. */
	private void entryAction_main_region_GestionCommande_paiementGestion_Payed() {
		timerService.setTimer(this, 3, (2 * 1000), false);
	}
	
	/* Entry action for state 'ReadCard'. */
	private void entryAction_main_region_GestionCommande_paiementGestion_ReadCard() {
		timerService.setTimer(this, 4, (3 * 1000), false);
	}
	
	/* Entry action for state 'PaymentMoney'. */
	private void entryAction_main_region_GestionCommande_paiementGestion_PaymentMoney() {
		timerService.setTimer(this, 5, 10, true);
	}
	
	/* Exit action for state 'Start'. */
	private void exitAction_main_region_Start() {
		timerService.unsetTimer(this, 0);
	}
	
	/* Exit action for state 'reset'. */
	private void exitAction_main_region_GestionCommande_resetTimer_reset() {
		timerService.unsetTimer(this, 1);
	}
	
	/* Exit action for state 'PaymentNFC'. */
	private void exitAction_main_region_GestionCommande_paiementGestion_PaymentNFC() {
		timerService.unsetTimer(this, 2);
	}
	
	/* Exit action for state 'Payed'. */
	private void exitAction_main_region_GestionCommande_paiementGestion_Payed() {
		timerService.unsetTimer(this, 3);
	}
	
	/* Exit action for state 'ReadCard'. */
	private void exitAction_main_region_GestionCommande_paiementGestion_ReadCard() {
		timerService.unsetTimer(this, 4);
	}
	
	/* Exit action for state 'PaymentMoney'. */
	private void exitAction_main_region_GestionCommande_paiementGestion_PaymentMoney() {
		timerService.unsetTimer(this, 5);
	}
	
	/* 'default' enter sequence for state Start */
	private void enterSequence_main_region_Start_default() {
		entryAction_main_region_Start();
		nextStateIndex = 0;
		stateVector[0] = State.MAIN_REGION_START;
	}
	
	/* 'default' enter sequence for state WaitingTakeDrink */
	private void enterSequence_main_region_WaitingTakeDrink_default() {
		nextStateIndex = 0;
		stateVector[0] = State.MAIN_REGION_WAITINGTAKEDRINK;
	}
	
	/* 'default' enter sequence for state PrepareDrink */
	private void enterSequence_main_region_PrepareDrink_default() {
		nextStateIndex = 0;
		stateVector[0] = State.MAIN_REGION_PREPAREDRINK;
	}
	
	/* 'default' enter sequence for state GestionCommande */
	private void enterSequence_main_region_GestionCommande_default() {
		enterSequence_main_region_GestionCommande_chooseGestion_default();
		enterSequence_main_region_GestionCommande_resetTimer_default();
		enterSequence_main_region_GestionCommande_paiementGestion_default();
	}
	
	/* 'default' enter sequence for state Waiting */
	private void enterSequence_main_region_GestionCommande_chooseGestion_Waiting_default() {
		nextStateIndex = 0;
		stateVector[0] = State.MAIN_REGION_GESTIONCOMMANDE_CHOOSEGESTION_WAITING;
	}
	
	/* 'default' enter sequence for state reset */
	private void enterSequence_main_region_GestionCommande_resetTimer_reset_default() {
		entryAction_main_region_GestionCommande_resetTimer_reset();
		nextStateIndex = 1;
		stateVector[1] = State.MAIN_REGION_GESTIONCOMMANDE_RESETTIMER_RESET;
	}
	
	/* 'default' enter sequence for state WaitingPayment */
	private void enterSequence_main_region_GestionCommande_paiementGestion_WaitingPayment_default() {
		nextStateIndex = 2;
		stateVector[2] = State.MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_WAITINGPAYMENT;
	}
	
	/* 'default' enter sequence for state PaymentNFC */
	private void enterSequence_main_region_GestionCommande_paiementGestion_PaymentNFC_default() {
		entryAction_main_region_GestionCommande_paiementGestion_PaymentNFC();
		nextStateIndex = 2;
		stateVector[2] = State.MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYMENTNFC;
	}
	
	/* 'default' enter sequence for state InsertedMoney */
	private void enterSequence_main_region_GestionCommande_paiementGestion_InsertedMoney_default() {
		nextStateIndex = 2;
		stateVector[2] = State.MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_INSERTEDMONEY;
	}
	
	/* 'default' enter sequence for state Payed */
	private void enterSequence_main_region_GestionCommande_paiementGestion_Payed_default() {
		entryAction_main_region_GestionCommande_paiementGestion_Payed();
		nextStateIndex = 2;
		stateVector[2] = State.MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYED;
	}
	
	/* 'default' enter sequence for state ReadCard */
	private void enterSequence_main_region_GestionCommande_paiementGestion_ReadCard_default() {
		entryAction_main_region_GestionCommande_paiementGestion_ReadCard();
		nextStateIndex = 2;
		stateVector[2] = State.MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_READCARD;
	}
	
	/* 'default' enter sequence for state PaymentMoney */
	private void enterSequence_main_region_GestionCommande_paiementGestion_PaymentMoney_default() {
		entryAction_main_region_GestionCommande_paiementGestion_PaymentMoney();
		nextStateIndex = 2;
		stateVector[2] = State.MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYMENTMONEY;
	}
	
	/* 'default' enter sequence for region main region */
	private void enterSequence_main_region_default() {
		react_main_region__entry_Default();
	}
	
	/* 'default' enter sequence for region chooseGestion */
	private void enterSequence_main_region_GestionCommande_chooseGestion_default() {
		react_main_region_GestionCommande_chooseGestion__entry_Default();
	}
	
	/* 'default' enter sequence for region resetTimer */
	private void enterSequence_main_region_GestionCommande_resetTimer_default() {
		react_main_region_GestionCommande_resetTimer__entry_Default();
	}
	
	/* 'default' enter sequence for region paiementGestion */
	private void enterSequence_main_region_GestionCommande_paiementGestion_default() {
		react_main_region_GestionCommande_paiementGestion__entry_Default();
	}
	
	/* Default exit sequence for state Start */
	private void exitSequence_main_region_Start() {
		nextStateIndex = 0;
		stateVector[0] = State.$NULLSTATE$;
		
		exitAction_main_region_Start();
	}
	
	/* Default exit sequence for state WaitingTakeDrink */
	private void exitSequence_main_region_WaitingTakeDrink() {
		nextStateIndex = 0;
		stateVector[0] = State.$NULLSTATE$;
	}
	
	/* Default exit sequence for state PrepareDrink */
	private void exitSequence_main_region_PrepareDrink() {
		nextStateIndex = 0;
		stateVector[0] = State.$NULLSTATE$;
	}
	
	/* Default exit sequence for state GestionCommande */
	private void exitSequence_main_region_GestionCommande() {
		exitSequence_main_region_GestionCommande_chooseGestion();
		exitSequence_main_region_GestionCommande_resetTimer();
		exitSequence_main_region_GestionCommande_paiementGestion();
	}
	
	/* Default exit sequence for state Waiting */
	private void exitSequence_main_region_GestionCommande_chooseGestion_Waiting() {
		nextStateIndex = 0;
		stateVector[0] = State.$NULLSTATE$;
	}
	
	/* Default exit sequence for state reset */
	private void exitSequence_main_region_GestionCommande_resetTimer_reset() {
		nextStateIndex = 1;
		stateVector[1] = State.$NULLSTATE$;
		
		exitAction_main_region_GestionCommande_resetTimer_reset();
	}
	
	/* Default exit sequence for state WaitingPayment */
	private void exitSequence_main_region_GestionCommande_paiementGestion_WaitingPayment() {
		nextStateIndex = 2;
		stateVector[2] = State.$NULLSTATE$;
	}
	
	/* Default exit sequence for state PaymentNFC */
	private void exitSequence_main_region_GestionCommande_paiementGestion_PaymentNFC() {
		nextStateIndex = 2;
		stateVector[2] = State.$NULLSTATE$;
		
		exitAction_main_region_GestionCommande_paiementGestion_PaymentNFC();
	}
	
	/* Default exit sequence for state InsertedMoney */
	private void exitSequence_main_region_GestionCommande_paiementGestion_InsertedMoney() {
		nextStateIndex = 2;
		stateVector[2] = State.$NULLSTATE$;
	}
	
	/* Default exit sequence for state Payed */
	private void exitSequence_main_region_GestionCommande_paiementGestion_Payed() {
		nextStateIndex = 2;
		stateVector[2] = State.$NULLSTATE$;
		
		exitAction_main_region_GestionCommande_paiementGestion_Payed();
	}
	
	/* Default exit sequence for state ReadCard */
	private void exitSequence_main_region_GestionCommande_paiementGestion_ReadCard() {
		nextStateIndex = 2;
		stateVector[2] = State.$NULLSTATE$;
		
		exitAction_main_region_GestionCommande_paiementGestion_ReadCard();
	}
	
	/* Default exit sequence for state PaymentMoney */
	private void exitSequence_main_region_GestionCommande_paiementGestion_PaymentMoney() {
		nextStateIndex = 2;
		stateVector[2] = State.$NULLSTATE$;
		
		exitAction_main_region_GestionCommande_paiementGestion_PaymentMoney();
	}
	
	/* Default exit sequence for region main region */
	private void exitSequence_main_region() {
		switch (stateVector[0]) {
		case MAIN_REGION_START:
			exitSequence_main_region_Start();
			break;
		case MAIN_REGION_WAITINGTAKEDRINK:
			exitSequence_main_region_WaitingTakeDrink();
			break;
		case MAIN_REGION_PREPAREDRINK:
			exitSequence_main_region_PrepareDrink();
			break;
		case MAIN_REGION_GESTIONCOMMANDE_CHOOSEGESTION_WAITING:
			exitSequence_main_region_GestionCommande_chooseGestion_Waiting();
			break;
		default:
			break;
		}
		
		switch (stateVector[1]) {
		case MAIN_REGION_GESTIONCOMMANDE_RESETTIMER_RESET:
			exitSequence_main_region_GestionCommande_resetTimer_reset();
			break;
		default:
			break;
		}
		
		switch (stateVector[2]) {
		case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_WAITINGPAYMENT:
			exitSequence_main_region_GestionCommande_paiementGestion_WaitingPayment();
			break;
		case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYMENTNFC:
			exitSequence_main_region_GestionCommande_paiementGestion_PaymentNFC();
			break;
		case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_INSERTEDMONEY:
			exitSequence_main_region_GestionCommande_paiementGestion_InsertedMoney();
			break;
		case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYED:
			exitSequence_main_region_GestionCommande_paiementGestion_Payed();
			break;
		case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_READCARD:
			exitSequence_main_region_GestionCommande_paiementGestion_ReadCard();
			break;
		case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYMENTMONEY:
			exitSequence_main_region_GestionCommande_paiementGestion_PaymentMoney();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region chooseGestion */
	private void exitSequence_main_region_GestionCommande_chooseGestion() {
		switch (stateVector[0]) {
		case MAIN_REGION_GESTIONCOMMANDE_CHOOSEGESTION_WAITING:
			exitSequence_main_region_GestionCommande_chooseGestion_Waiting();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region resetTimer */
	private void exitSequence_main_region_GestionCommande_resetTimer() {
		switch (stateVector[1]) {
		case MAIN_REGION_GESTIONCOMMANDE_RESETTIMER_RESET:
			exitSequence_main_region_GestionCommande_resetTimer_reset();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region paiementGestion */
	private void exitSequence_main_region_GestionCommande_paiementGestion() {
		switch (stateVector[2]) {
		case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_WAITINGPAYMENT:
			exitSequence_main_region_GestionCommande_paiementGestion_WaitingPayment();
			break;
		case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYMENTNFC:
			exitSequence_main_region_GestionCommande_paiementGestion_PaymentNFC();
			break;
		case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_INSERTEDMONEY:
			exitSequence_main_region_GestionCommande_paiementGestion_InsertedMoney();
			break;
		case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYED:
			exitSequence_main_region_GestionCommande_paiementGestion_Payed();
			break;
		case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_READCARD:
			exitSequence_main_region_GestionCommande_paiementGestion_ReadCard();
			break;
		case MAIN_REGION_GESTIONCOMMANDE_PAIEMENTGESTION_PAYMENTMONEY:
			exitSequence_main_region_GestionCommande_paiementGestion_PaymentMoney();
			break;
		default:
			break;
		}
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region__entry_Default() {
		enterSequence_main_region_Start_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region_GestionCommande_chooseGestion__entry_Default() {
		enterSequence_main_region_GestionCommande_chooseGestion_Waiting_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region_GestionCommande_resetTimer__entry_Default() {
		enterSequence_main_region_GestionCommande_resetTimer_reset_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region_GestionCommande_paiementGestion__entry_Default() {
		enterSequence_main_region_GestionCommande_paiementGestion_WaitingPayment_default();
	}
	
	private boolean react() {
		return false;
	}
	
	private boolean main_region_Start_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (timeEvents[0]) {
				exitSequence_main_region_Start();
				raiseWaiting();
				
				setIsThereBoisson(false);
				
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
	
	private boolean main_region_WaitingTakeDrink_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (takeCup) {
				exitSequence_main_region_WaitingTakeDrink();
				raiseCleaningText();
				
				enterSequence_main_region_Start_default();
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
	
	private boolean main_region_PrepareDrink_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (drinkReady) {
				exitSequence_main_region_PrepareDrink();
				raiseWaitingDrink();
				
				enterSequence_main_region_WaitingTakeDrink_default();
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
			if (cancelButton) {
				exitSequence_main_region_GestionCommande();
				raiseDoReset();
				
				enterSequence_main_region_Start_default();
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
	
	private boolean main_region_GestionCommande_chooseGestion_Waiting_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (slider) {
				exitSequence_main_region_GestionCommande_chooseGestion_Waiting();
				raiseUpdateSlider();
				
				enterSequence_main_region_GestionCommande_chooseGestion_Waiting_default();
			} else {
				if (drinkButton) {
					exitSequence_main_region_GestionCommande_chooseGestion_Waiting();
					raiseUpdateDrink();
					
					setIsThereBoisson(true);
					
					enterSequence_main_region_GestionCommande_chooseGestion_Waiting_default();
				} else {
					did_transition = false;
				}
			}
		}
		return did_transition;
	}
	
	private boolean main_region_GestionCommande_resetTimer_reset_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (timeEvents[1]) {
				exitSequence_main_region_GestionCommande();
				raiseDoReset();
				
				enterSequence_main_region_Start_default();
				react();
			} else {
				if ((drinkButton || (slider || (nFCButton || moneyButton)))) {
					exitSequence_main_region_GestionCommande_resetTimer_reset();
					enterSequence_main_region_GestionCommande_resetTimer_reset_default();
				} else {
					did_transition = false;
				}
			}
		}
		return did_transition;
	}
	
	private boolean main_region_GestionCommande_paiementGestion_WaitingPayment_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (nFCButton) {
				exitSequence_main_region_GestionCommande_paiementGestion_WaitingPayment();
				raiseReadCard();
				
				enterSequence_main_region_GestionCommande_paiementGestion_ReadCard_default();
				main_region_GestionCommande_react(false);
			} else {
				if (moneyButton) {
					exitSequence_main_region_GestionCommande_paiementGestion_WaitingPayment();
					raiseIncreaseMoney();
					
					enterSequence_main_region_GestionCommande_paiementGestion_InsertedMoney_default();
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
	
	private boolean main_region_GestionCommande_paiementGestion_PaymentNFC_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (((timeEvents[2]) && (getIsThereBoisson()))) {
				exitSequence_main_region_GestionCommande_paiementGestion_PaymentNFC();
				raisePaymentNFC();
				
				enterSequence_main_region_GestionCommande_paiementGestion_Payed_default();
				main_region_GestionCommande_react(false);
			} else {
				if (moneyButton) {
					exitSequence_main_region_GestionCommande_paiementGestion_PaymentNFC();
					raiseErrorPayment();
					
					enterSequence_main_region_GestionCommande_paiementGestion_PaymentNFC_default();
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
	
	private boolean main_region_GestionCommande_paiementGestion_InsertedMoney_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (moneyButton) {
				exitSequence_main_region_GestionCommande_paiementGestion_InsertedMoney();
				raiseIncreaseMoney();
				
				enterSequence_main_region_GestionCommande_paiementGestion_InsertedMoney_default();
				main_region_GestionCommande_react(false);
			} else {
				if (nFCButton) {
					exitSequence_main_region_GestionCommande_paiementGestion_InsertedMoney();
					raiseErrorPayment();
					
					enterSequence_main_region_GestionCommande_paiementGestion_InsertedMoney_default();
					main_region_GestionCommande_react(false);
				} else {
					if (confirmationMoney) {
						exitSequence_main_region_GestionCommande_paiementGestion_InsertedMoney();
						enterSequence_main_region_GestionCommande_paiementGestion_PaymentMoney_default();
						main_region_GestionCommande_react(false);
					} else {
						did_transition = false;
					}
				}
			}
		}
		if (did_transition==false) {
			did_transition = main_region_GestionCommande_react(try_transition);
		}
		return did_transition;
	}
	
	private boolean main_region_GestionCommande_paiementGestion_Payed_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (timeEvents[3]) {
				exitSequence_main_region_GestionCommande();
				raisePrepareDrink();
				
				enterSequence_main_region_PrepareDrink_default();
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
	
	private boolean main_region_GestionCommande_paiementGestion_ReadCard_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (timeEvents[4]) {
				exitSequence_main_region_GestionCommande_paiementGestion_ReadCard();
				raiseWaitingPayment();
				
				enterSequence_main_region_GestionCommande_paiementGestion_PaymentNFC_default();
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
	
	private boolean main_region_GestionCommande_paiementGestion_PaymentMoney_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (((timeEvents[5]) && (getIsThereBoisson()))) {
				exitSequence_main_region_GestionCommande_paiementGestion_PaymentMoney();
				raiseReturnMoney();
				
				enterSequence_main_region_GestionCommande_paiementGestion_Payed_default();
				main_region_GestionCommande_react(false);
			} else {
				if (nFCButton) {
					exitSequence_main_region_GestionCommande_paiementGestion_PaymentMoney();
					raiseErrorPayment();
					
					enterSequence_main_region_GestionCommande_paiementGestion_PaymentMoney_default();
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
