package fr.univcotedazur.polytech.si4.fsm.project;

public class FactoryController {

	protected String boisson;
	protected String price;
	protected int sugar;
	protected int size;
	protected String temperature;
	
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
	
	public void setTemperature(String temperature) {
		this.temperature=temperature;
	}

}
