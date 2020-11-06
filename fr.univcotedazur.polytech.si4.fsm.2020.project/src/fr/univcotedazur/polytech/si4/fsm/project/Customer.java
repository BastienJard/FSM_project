package fr.univcotedazur.polytech.si4.fsm.project;

import java.util.ArrayList;

public class Customer {
	private int numberOfNFCPayement=0;
	private double averageCostOfDrinks=0;
	private ArrayList<Double> listOfExpenses = new ArrayList<>();
	
	public Customer() {}
	
	int getNumberOfNFCPayement() {
		return numberOfNFCPayement;
	}
	
	void increaseNFCCount(double newPrice) {
		numberOfNFCPayement++;
		listOfExpenses.add(newPrice);
	}
	
	void resetNumberOfNFCPayement() {
		numberOfNFCPayement = 0;
		averageCostOfDrinks = 0;
		listOfExpenses.clear();
	}
	
	double getAverageCost() {
		return averageCostOfDrinks;
	}
	
	double calculateAverageCost() {
		double total=0;
		for(int i=0; i<listOfExpenses.size();i++) {
			total += listOfExpenses.get(i);
		}
		averageCostOfDrinks = total/listOfExpenses.size();
		return averageCostOfDrinks;
	}
}
