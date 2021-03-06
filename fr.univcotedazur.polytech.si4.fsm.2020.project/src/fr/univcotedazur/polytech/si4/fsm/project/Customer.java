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
		if(listOfExpenses.size()==0) {
			return 0;
		}
		double total=0;
		for(int i=0; i<listOfExpenses.size();i++) {
			total += listOfExpenses.get(i);
		}
		averageCostOfDrinks = total/listOfExpenses.size();
		return averageCostOfDrinks;
	}
	
	ArrayList<Double> getListOfExpenses(){
		return this.listOfExpenses;
	}
	
	void setaverageCostOfDrinks(double averageCostOfDrinks) {
		this.averageCostOfDrinks = averageCostOfDrinks;
	}
	
	void setNumberOfNFCPayment(int numberOfNFCPayment) {
		this.numberOfNFCPayement = numberOfNFCPayment;
	}
	
	void addExpense(Double expense) {
		this.listOfExpenses.add(expense);
	}
}
