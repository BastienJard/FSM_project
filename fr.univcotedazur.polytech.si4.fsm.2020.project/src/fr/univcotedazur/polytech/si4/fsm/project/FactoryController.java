package fr.univcotedazur.polytech.si4.fsm.project;


public class FactoryController {

	protected Drink boisson;
	protected double insertedCoin, price;
	protected int sugar =1;
	protected int size =1;
	protected int temperature =2 ;
	protected int timeValue;

	
	public void setBoisson(Drink boisson) {
		this.boisson=boisson;
	
	}
	
	public void setSugar(int sugar) {
		this.sugar=sugar;
	}
	
	public void setSize(int size) {
		this.size=size;
	}
	
	public void setTemperature(int temperature) {
		this.temperature=temperature;
	}
	
	public void prepare() {
		boisson.calculateTime(sugar, size, temperature);
		timeValue = boisson.totalTime;
		}
	
	public void increaseCoin(double coin) {		
		this.insertedCoin += coin;
	}
	
	public void calculatePrice(boolean option1, boolean option2,boolean option3, boolean cup) {
		if(boisson!=null) {
			if(boisson.name.equals("soup")) {
				price = boisson.price + (option1 ? 0.3 : 0) + (option2 ? 0.1 : 0) + (option3 ? 0.6 : 0) - (cup ? 0.1 : 0);
			}else {
				price = boisson.price  + (option1 ? 0.1 : 0) + (option2 ? 0.1 : 0) + (option3 ? 0.6 : 0) - (cup ? 0.1 : 0);
			}
		}
		else {
			price=0;
		}
	}

}
