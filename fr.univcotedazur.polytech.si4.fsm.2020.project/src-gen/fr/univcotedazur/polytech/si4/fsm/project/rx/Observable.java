/** Generated by YAKINDU Statechart Tools code generator. */
package fr.univcotedazur.polytech.si4.fsm.project.rx;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> {

	private List<Observer<T>> observers = new ArrayList<>();

	public void next(T value) {
		for (Observer<T> observer : observers) {
			observer.next(value);
		}
	}

	public boolean subscribe(Observer<T> observer) {
		return observer != null ? this.observers.add(observer) : false;
	}

	public boolean unsubscribe(Observer<T> observer) {
		return observer != null ? this.observers.remove(observer) : false;
	}
}
