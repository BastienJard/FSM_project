package fr.univcotedazur.polytech.si4.fsm.project;

public class FactoryController {

	protected String boisson;
	protected String price;
	protected int sugar;
	protected int size;
	protected int temperature;
	protected float timeValue;
	
	public FactoryController() {
		
	}
	
	public void setBoisson(String boisson) {
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
		//en ms
		timeValue = (float)(Math.exp(this.temperature)*1000 + Math.exp(this.size)*100 + Math.exp(this.sugar)*10);
	}

}