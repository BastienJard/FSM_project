package fr.univcotedazur.polytech.si4.fsm.project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.univcotedazur.polytech.si4.fsm.project.drinkingMachine.DrinkingMachineStatemachine;
import fr.univcotedazur.polytech.si4.fsm.project.recipe.RecipeMachineStatemachine;



public class DrinkFactoryMachine extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2030629304432075314L;
	private JPanel contentPane;
	private JLabel messagesToUser, drinkChooseLabel,moneyInsertedLabel, priceLabel, recipeLabel, lblSugar;
	private JSlider sugarSlider, sizeSlider, temperatureSlider;
	protected DrinkingMachineStatemachine drinkingMachineFSM;
	private RecipeMachineStatemachine recipeFSM;
	protected FactoryController controller;
	private Hashtable<Integer, JLabel> temperatureTable;
	private BufferedImage myPicture;
	private Boolean isNFCDone = false, isPaiementLiquideDone = false, cupAdded = false;
	private JProgressBar progressBar;
	private JButton buttonForPicture, coffeeButton, expressoButton, teaButton, nfcBiiiipButton;
	private int progression = 0;
	private int sugarReserve = 50;
	private int coffeeReserve = 100;
	private int expressoReserve = 100;
	private int teaReserve = 100;
	private JFormattedTextField NFCField;
	private JCheckBox option1, option2, option3;
	private HashMap<Object, Customer> mapOfCustomer = new HashMap<>();
	
	/**
	 * @wbp.nonvisual location=311,475
	 */
	
	private final ImageIcon imageIcon = new ImageIcon();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrinkFactoryMachine frame = new DrinkFactoryMachine();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		
		});
		
	}
	
	public void saveFile() {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
		try {
		docBuilder = dbFactory.newDocumentBuilder();
 
        Document doc = docBuilder.newDocument();
        Element racine = doc.createElement("datas");
        doc.appendChild(racine);
        
        Element stocks = doc.createElement("stocks");
        racine.appendChild(stocks);
        
        Element sugar = doc.createElement("sugar");
        sugar.appendChild(doc.createTextNode(""+sugarReserve));
        stocks.appendChild(sugar);
        
        Element coffee = doc.createElement("coffee");
        coffee.appendChild(doc.createTextNode(""+coffeeReserve));
        stocks.appendChild(coffee);
        
        Element tea = doc.createElement("tea");
        tea.appendChild(doc.createTextNode(""+teaReserve));
        stocks.appendChild(tea);
        
        Element expresso = doc.createElement("expresso");
        expresso.appendChild(doc.createTextNode(""+expressoReserve));
        stocks.appendChild(expresso);
        
        Element customers = doc.createElement("customers");
        racine.appendChild(customers);
        
        
        Iterator<HashMap.Entry<Object, Customer>> iterator = mapOfCustomer.entrySet().iterator();
        while (iterator.hasNext()) {

        	HashMap.Entry<Object, Customer> entry = iterator.next();
        	Element customer = doc.createElement("customer");
            customers.appendChild(customer);
            
            Attr id = doc.createAttribute("id");
            id.setValue(""+ entry.getKey());
            customer.setAttributeNode(id);
            
            Element numberOfNFCPayement = doc.createElement("numberOfNFCPayement");
            numberOfNFCPayement.appendChild(doc.createTextNode(""+ entry.getValue().getNumberOfNFCPayement()));
            customer.appendChild(numberOfNFCPayement);
            
            Element averageCostOfDrinks = doc.createElement("averageCostOfDrinks");
            averageCostOfDrinks.appendChild(doc.createTextNode(""+entry.getValue().getAverageCost()));
            customer.appendChild(averageCostOfDrinks);
            
            Element listOfExpenses = doc.createElement("listOfExpenses");
            customer.appendChild(listOfExpenses);
            
            for(Double expense:entry.getValue().getListOfExpenses()) {
            	
            	Element expenseItem = doc.createElement("expenseItem");
            	expenseItem.appendChild(doc.createTextNode(""+ expense));
                listOfExpenses.appendChild(expenseItem);
            }
        }
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult resultat = new StreamResult(new File("src\\fr\\univcotedazur\\polytech\\si4\\fsm\\project\\data.xml"));
        
        transformer.transform(source, resultat);
        
        
		
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void errorPayment() {
		messagesToUser.setText("<html>Erreur, vous ne pouvez<br> pas changer de moyen de paiement");
	}
	
	public void updateMoney() {
		isPaiementLiquideDone=true;
		BigDecimal bd = new BigDecimal(controller.insertedCoin);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		moneyInsertedLabel.setText("<html>Monnaie : " + bd.doubleValue() + " €");
		if(controller.boisson!=null) {
			if(controller.insertedCoin>=controller.price) {
				drinkingMachineFSM.raiseConfirmationMoney();
			}
		}
	}
	
	public void returnMoney() {
		double returnMoney = controller.insertedCoin - controller.price;
		controller.insertedCoin = returnMoney;
		BigDecimal bd = new BigDecimal(returnMoney);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		messagesToUser.setText("<html>Rendue de monnaie : " + bd.doubleValue() + " €" );
	}
	
	public void paymentNFC() {
		isNFCDone = true;
		BigDecimal bd = new BigDecimal(controller.price);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		if(!mapOfCustomer.containsKey(NFCField.getValue())) {
			Customer customer = new Customer();
			mapOfCustomer.put(NFCField.getValue(), customer);
		}
		mapOfCustomer.get(NFCField.getValue()).calculateAverageCost();
		if(mapOfCustomer.get(NFCField.getValue()).getNumberOfNFCPayement()>10 && controller.boisson.getPrice()<mapOfCustomer.get(NFCField.getValue()).getAverageCost()) {
			messagesToUser.setText("<html>Plus de 10 achats : <br>celui-ci est gratuit");
			mapOfCustomer.get(NFCField.getValue()).resetNumberOfNFCPayement();
		}else if(mapOfCustomer.get(NFCField.getValue()).getNumberOfNFCPayement()>10 && controller.boisson.getPrice()>=mapOfCustomer.get(NFCField.getValue()).getAverageCost()) {
			messagesToUser.setText("<html>Prix trop élevé pour avoir la réduction <br> Paiement de " + bd + " € accepté");
		}else {
			messagesToUser.setText("Paiement de " + bd + " € accepté");
		}
		mapOfCustomer.get(NFCField.getValue()).increaseNFCCount(controller.boisson.getPrice());
	}
	
	public void cancelNFC() {
		isNFCDone = false;
		messagesToUser.setText("<html>Annulation du paiment NFC");
	}
	
	public void cancelMoneyPayment() {
		isPaiementLiquideDone=false;
		messagesToUser.setText("<html>Rendue de monnaie : " + controller.insertedCoin + " €" );
	}
	/**
	 * Create the frame.
	 * 
	 */
	public void waiting() {
		controller.insertedCoin = 0.0;
		controller.setBoisson(null);
		messagesToUser.setText("<html>Votre Commande :");
		drinkChooseLabel.setText("Boisson :");
		moneyInsertedLabel.setText("<html>Monnaie : " + controller.insertedCoin + " €");
		priceLabel.setText("Prix : " + 0.0 + " €");
	}
	
	public void waitingPayment() {
		messagesToUser.setText("<html>Votre Commande :");
	}
	
	public void updateSlider() {
		controller.setSize(sizeSlider.getValue());
		controller.setSugar(sugarSlider.getValue());
		controller.setTemperature(temperatureSlider.getValue());
	}
	
	public void updateOptions() {
		controller.option1=option1.isSelected();
		controller.option2=option2.isSelected();
		controller.option3=option3.isSelected();
		updatePrice();
	}
	
	public void updateDrink() {
		drinkChooseLabel.setText("Boisson : " + controller.boisson.name);
		option1.setText(controller.boisson.option1);
		option2.setText(controller.boisson.option2);
		if(!controller.boisson.option3.equals("")) {
			option3.setVisible(true);
			option3.setText(controller.boisson.option3);
		}
		else {
			option3.setVisible(false);
			option3.setSelected(false);
			controller.option3=false;
		}
		updatePrice();
		if(controller.insertedCoin>=controller.price) {
			drinkingMachineFSM.raiseConfirmationMoney();
		}
	
	}
	
	public void firstUpdateDrink() {
		drinkChooseLabel.setText("Boisson : " + controller.boisson.name);
		
		option1.setVisible(true);
		option1.setText(controller.boisson.option1);
		option2.setVisible(true);
		option2.setText(controller.boisson.option2);
		if(!controller.boisson.option3.equals("")) {
			option3.setVisible(true);
			option3.setText(controller.boisson.option3);
		}
		updatePrice();
		if(controller.insertedCoin>=controller.price) {
			drinkingMachineFSM.raiseConfirmationMoney();
		}
		
	}
	
	public void cleaningText() {
		cupAdded = false;
		messagesToUser.setText("<html>Nettoyage de la machine<br> en cours");
		progressBar.setValue(0);
		sugarSlider.setValue(1);
		sizeSlider.setValue(1);
		temperatureSlider.setValue(2);
		controller.setBoisson(null);
		controller.insertedCoin = 0.0;
	
		lblSugar.setText("Sugar");
		try {
			myPicture = ImageIO.read(new File("./picts/vide2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		buttonForPicture.setIcon(new ImageIcon(myPicture));
		progressBar.setValue(0);
		progression =0;
		NFCField.setValue(null);
		nfcBiiiipButton.setEnabled(false);
		option1.setVisible(false);
		option1.setSelected(false);
		option2.setVisible(false);
		option2.setSelected(false);
		option3.setVisible(false);
		option3.setSelected(false);
	}
	
	
	public void readCard() {
		isNFCDone = true;
		messagesToUser.setText("<html>Lecture de la carte<br> en cours ...");
	}
	
	public void doReset() {
		if(isNFCDone) {
			cancelNFC();
		}else if (isPaiementLiquideDone) {
			cancelMoneyPayment();
		}else {
			messagesToUser.setText("<html>Machine prête");
		}
		sugarSlider.setValue(1);
		sizeSlider.setValue(1);
		temperatureSlider.setValue(2);
		controller.setBoisson(null);
		controller.insertedCoin = 0.0;
		lblSugar.setText("Sugar");
		
		drinkChooseLabel.setText("");
		moneyInsertedLabel.setText("");
		priceLabel.setText("");
		cupAdded = false;
		try {
			myPicture = ImageIO.read(new File("./picts/vide2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		NFCField.setValue(null);
		nfcBiiiipButton.setEnabled(false);
		option1.setVisible(false);
		option1.setSelected(false);
		option2.setVisible(false);
		option2.setSelected(false);
		option3.setVisible(false);
		option3.setSelected(false);
		buttonForPicture.setIcon(new ImageIcon(myPicture));
	}
	
	public void checkReserves() {
		if(coffeeReserve<10) {
			coffeeButton.setEnabled(false);
		}else {
			coffeeButton.setEnabled(true);
		}
		if(expressoReserve<10) {
			expressoButton.setEnabled(false);
		}else {
			expressoButton.setEnabled(true);
		}
		if(teaReserve<10) {
			teaButton.setEnabled(false);
		}else {
			teaButton.setEnabled(true);
		}
	}
	
	public void updateReserves() {
		switch (controller.boisson.getName()) {
			case "coffee":
				coffeeReserve = coffeeReserve-10;
				sugarReserve = sugarReserve - (controller.sugar*1);
				break;
			case "expresso":
				expressoReserve = expressoReserve-10;
				sugarReserve = sugarReserve - (controller.sugar*1);
				break;
			case "tea":
				teaReserve = teaReserve-10;
				sugarReserve = sugarReserve - (controller.sugar*1);
				break;
			default:
				sugarReserve = sugarReserve - (controller.sugar*1);
		}
		saveFile();
	}
	
	public void refundReserves() {
		sugarReserve = 50;
		coffeeReserve = 100;
		teaReserve = 100;
		expressoReserve = 100;
		saveFile();
	}
	
	public void updatePrice() {
		controller.calculatePrice();
		BigDecimal bd = new BigDecimal(controller.price);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		priceLabel.setText("Prix : " + bd + " €");
	}
	
	public void prepareDrink() {
		drinkChooseLabel.setText("");
		moneyInsertedLabel.setText("");
		priceLabel.setText("");
		isNFCDone=false;
		isPaiementLiquideDone=false;
		controller.prepare();
		BigDecimal bd = new BigDecimal(controller.timeValue/1000);
		bd = bd.setScale(0, BigDecimal.ROUND_UP);
		messagesToUser.setText("<html>Temps de préparation : " + bd.doubleValue() + "s");
		if(!cupAdded) {
			try {
			myPicture = ImageIO.read(new File("./picts/gobeletPolluant.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		buttonForPicture.setIcon(new ImageIcon(myPicture));
		}
		recipeFSM.setIncreaseTime((int)controller.timeValue/100);
		recipeFSM.setTimeStep1(controller.boisson.timeStep1);
		recipeFSM.setTimeStep2(controller.boisson.timeStep2);
		recipeFSM.setTimeStep3(controller.boisson.timeStep3);
		recipeFSM.setTimeStep4(controller.boisson.timeStep4);
		recipeFSM.setTimeStep5(controller.boisson.timeStep5);
		recipeFSM.raiseBeginRecipe();
	}
	
	public void addYourCup() {
		if(!cupAdded) {
			cupAdded = true;
			if(controller.boisson != null) {
				controller.boisson.setPrice(controller.boisson.getPrice() - 0.1);
				BigDecimal bd = new BigDecimal(controller.boisson.getPrice());
				bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
				priceLabel.setText("Prix : " + bd + " €");
				updateMoney();
			}
		}
		
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("./picts/ownCup.jpg"));
		} catch (IOException ee) {
			ee.printStackTrace();
		}
		buttonForPicture.setIcon(new ImageIcon(myPicture));
	}
	
	public void drinkReady() {
		messagesToUser.setText("<html>Votre boisson est prête");
		recipeLabel.setText("");
	}
	
	public void progressDrink() {
		progression++;
		progressBar.setValue(progression);
	}
	
	public DrinkFactoryMachine() {
		
		controller = new FactoryController();
		drinkingMachineFSM= new DrinkingMachineStatemachine();
		TimerService timer = new TimerService();
		drinkingMachineFSM.setTimerService(timer);
		drinkingMachineFSM.getCleaningText().subscribe(e -> this.cleaningText());
		drinkingMachineFSM.getDoReset().subscribe(e -> this.doReset());
		drinkingMachineFSM.getWaiting().subscribe(e -> this.waiting());
		drinkingMachineFSM.getErrorPayment().subscribe(e -> this.errorPayment());
		drinkingMachineFSM.getWaitingDrink().subscribe(e -> this.drinkReady());
		drinkingMachineFSM.getIncreaseMoney().subscribe(e -> this.updateMoney());
		drinkingMachineFSM.getReadCard().subscribe(e -> this.readCard());
		drinkingMachineFSM.getPaymentNFC().subscribe(e -> this.paymentNFC());
		drinkingMachineFSM.getPrepareDrink().subscribe(e -> this.prepareDrink());
		drinkingMachineFSM.getReturnMoney().subscribe(e -> this.returnMoney());
		drinkingMachineFSM.getUpdateDrink().subscribe(e -> this.updateDrink());
		drinkingMachineFSM.getUpdateSlider().subscribe(e -> this.updateSlider());
		drinkingMachineFSM.getUpdateOptions().subscribe(e -> this.updateOptions());
		drinkingMachineFSM.getUpdateReserves().subscribe(e -> this.updateReserves());
		drinkingMachineFSM.getCheckReserves().subscribe(e -> this.checkReserves());
		drinkingMachineFSM.getRefundReserves().subscribe(e -> this.refundReserves());
		drinkingMachineFSM.getFirstUpdateDrink().subscribe(e -> this.firstUpdateDrink());
		drinkingMachineFSM.getAddYourCup().subscribe(e -> this.addYourCup());
		drinkingMachineFSM.enter();
		
		//myFSM.getListeners().add(new DrinkFactoryMachineControlerInterface(this));
		
		recipeFSM = new RecipeMachineStatemachine();
		TimerService timer2 = new TimerService();
		recipeFSM.setTimerService(timer2);
		recipeFSM.getStep1().subscribe(e -> controller.boisson.doStep1());
		recipeFSM.getStep2().subscribe(e -> controller.boisson.doStep2());
		recipeFSM.getStep3().subscribe(e -> controller.boisson.doStep3(controller.option1,controller.option2, controller.option3));
		recipeFSM.getStep4().subscribe(e -> controller.boisson.doStep4());
		recipeFSM.getStep5().subscribe(e -> controller.boisson.doStep5(controller.option1,controller.option2, controller.option3));
		recipeFSM.getIncreaseProgressBar().subscribe(e -> this.progressDrink());
		recipeFSM.getWaiting().subscribe(e -> {
			drinkingMachineFSM.raiseDrinkReady();
		});
		recipeFSM.enter();
		//recetteFSM.getSCInterface().getListeners().add(new RecetteMachineControllerInterface(this));
		
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		int str =0;
		Element root =null;
	    try {
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         File fileXML = new File("src\\fr\\univcotedazur\\polytech\\si4\\fsm\\project\\data.xml");
	         Document xml = builder.parse(fileXML);
	         root = xml.getDocumentElement();
	         xml.getDocumentElement().normalize();
	         XPathFactory xpf = XPathFactory.newInstance();
	         XPath path = xpf.newXPath();
	         
	         String sugarPath = "/datas/stocks/sugar";
	         sugarReserve = ((Double)path.evaluate(sugarPath, root,XPathConstants.NUMBER)).intValue();
	         
	         String teaPath = "/datas/stocks/tea";
	         teaReserve = ((Double)path.evaluate(teaPath, root,XPathConstants.NUMBER)).intValue();
	         
	         String coffeePath = "/datas/stocks/coffee";
	         coffeeReserve = ((Double)path.evaluate(coffeePath, root,XPathConstants.NUMBER)).intValue();
	         
	         String expressoPath = "/datas/stocks/expresso";
	         expressoReserve = ((Double)path.evaluate(expressoPath, root,XPathConstants.NUMBER)).intValue();
	         
	         NodeList nList = xml.getElementsByTagName("customer");
	         for(int i=0; i<nList.getLength(); i++) {
	        	 Node customerNode = nList.item(i);
	        	 Element customerElement = (Element) customerNode;
	        	 Customer customer = new Customer();
	        	 customer.setaverageCostOfDrinks(Double.parseDouble(customerElement.getElementsByTagName("averageCostOfDrinks").item(0).getTextContent()));
	        	 customer.setNumberOfNFCPayment(Integer.parseInt(customerElement.getElementsByTagName("numberOfNFCPayement").item(0).getTextContent()));
	        	 NodeList expenses = customerElement.getElementsByTagName("expenseItem");
	        	 for(int j=0;j<expenses.getLength(); j++) {
	        		 Node expense = expenses.item(j);
	        		 if (expense.getNodeType() == Node.ELEMENT_NODE) {
			        	 Element expenseElement = (Element) expense;
			        	 customer.addExpense(Double.parseDouble(expense.getTextContent()));
	        		 }
	        	 }
	        	 mapOfCustomer.put(Integer.parseInt(customerElement.getAttribute("id")),customer);
	         }
	         

	      } catch (ParserConfigurationException e) {
	         e.printStackTrace();
	      } catch (SAXException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (XPathExpressionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
	      }
		
		setForeground(Color.WHITE);
		setFont(new Font("Cantarell", Font.BOLD, 22));
		setBackground(Color.DARK_GRAY);
		setTitle("Drinking Factory Machine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 650);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		messagesToUser = new JLabel("<html>Machine prête");
		messagesToUser.setForeground(Color.WHITE);
		messagesToUser.setHorizontalAlignment(SwingConstants.LEFT);
		messagesToUser.setVerticalAlignment(SwingConstants.TOP);
		messagesToUser.setToolTipText("message to the user");
		messagesToUser.setBackground(Color.WHITE);
		messagesToUser.setBounds(126, 34, 165, 175);
		
		recipeLabel = new JLabel("");
		recipeLabel.setForeground(Color.WHITE);
		recipeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		recipeLabel.setVerticalAlignment(SwingConstants.TOP);
		recipeLabel.setToolTipText("message to the user");
		recipeLabel.setBackground(Color.WHITE);
		recipeLabel.setBounds(126, 64, 165, 175);
		
		drinkChooseLabel = new JLabel("");
		drinkChooseLabel.setForeground(Color.WHITE);
		drinkChooseLabel.setHorizontalAlignment(SwingConstants.LEFT);
		drinkChooseLabel.setVerticalAlignment(SwingConstants.TOP);
		drinkChooseLabel.setToolTipText("message to the user");
		drinkChooseLabel.setBackground(Color.WHITE);
		drinkChooseLabel.setBounds(126, 94, 165, 175);
		
		priceLabel = new JLabel("");
		priceLabel.setForeground(Color.WHITE);
		priceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		priceLabel.setVerticalAlignment(SwingConstants.TOP);
		priceLabel.setToolTipText("message to the user");
		priceLabel.setBackground(Color.WHITE);
		priceLabel.setBounds(126, 114, 165, 175);
		
		moneyInsertedLabel = new JLabel("");
		moneyInsertedLabel.setForeground(Color.WHITE);
		moneyInsertedLabel.setHorizontalAlignment(SwingConstants.LEFT);
		moneyInsertedLabel.setVerticalAlignment(SwingConstants.TOP);
		moneyInsertedLabel.setToolTipText("message to the user");
		moneyInsertedLabel.setBackground(Color.WHITE);
		moneyInsertedLabel.setBounds(126, 134, 165, 175);
		
		contentPane.add(messagesToUser);
		contentPane.add(recipeLabel);
		contentPane.add(drinkChooseLabel);
		contentPane.add(priceLabel);
		contentPane.add(moneyInsertedLabel);

		JLabel lblCoins = new JLabel("Coins");
		lblCoins.setForeground(Color.WHITE);
		lblCoins.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoins.setBounds(538, 12, 44, 15);
		contentPane.add(lblCoins);

		coffeeButton = new JButton("Coffee");
		coffeeButton.setForeground(Color.WHITE);
		coffeeButton.setBackground(Color.DARK_GRAY);
		coffeeButton.setBounds(12, 34, 96, 25);
		
		contentPane.add(coffeeButton);
	

		expressoButton = new JButton("Expresso");
		expressoButton.setForeground(Color.WHITE);
		expressoButton.setBackground(Color.DARK_GRAY);
		expressoButton.setBounds(12, 71, 96, 25);
		contentPane.add(expressoButton);

		teaButton = new JButton("Tea");
		teaButton.setForeground(Color.WHITE);
		teaButton.setBackground(Color.DARK_GRAY);
		teaButton.setBounds(12, 108, 96, 25);
		contentPane.add(teaButton);

		JButton soupButton = new JButton("Soup");
		soupButton.setForeground(Color.WHITE);
		soupButton.setBackground(Color.DARK_GRAY);
		soupButton.setBounds(12, 145, 96, 25);
		contentPane.add(soupButton);
		soupButton.setEnabled(false);
		
		option1 = new JCheckBox("Option (+0.60€)");
		option1.setForeground(Color.WHITE);
		option1.setBackground(Color.DARK_GRAY);
		option1.setBounds(108, 180, 190, 25);
		option1.setVisible(false);
		contentPane.add(option1);
		
		option2 = new JCheckBox("Option2 (+0.60€)");
		option2.setForeground(Color.WHITE);
		option2.setBackground(Color.DARK_GRAY);
		option2.setBounds(108, 200, 190, 25);
		option2.setVisible(false);
		contentPane.add(option2);
		
		option3 = new JCheckBox("Option3 (+0.60€)");
		option3.setForeground(Color.WHITE);
		option3.setBackground(Color.DARK_GRAY);
		option3.setBounds(108, 220, 190, 25);
		option3.setVisible(false);
		contentPane.add(option3);

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setValue(0);
		progressBar.setForeground(Color.LIGHT_GRAY);
		progressBar.setBackground(Color.DARK_GRAY);
		progressBar.setBounds(12, 254, 622, 26);
		
		contentPane.add(progressBar);

		sugarSlider = new JSlider();
		sugarSlider.setValue(1);
		sugarSlider.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sugarSlider.setBackground(Color.DARK_GRAY);
		sugarSlider.setForeground(Color.WHITE);
		sugarSlider.setPaintTicks(true);
		sugarSlider.setMinorTickSpacing(1);
		sugarSlider.setMajorTickSpacing(1);
		sugarSlider.setMaximum(4);
		sugarSlider.setBounds(301, 51, 200, 36);
		contentPane.add(sugarSlider);

		sizeSlider = new JSlider();
		sizeSlider.setPaintTicks(true);
		sizeSlider.setValue(1);
		sizeSlider.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sizeSlider.setBackground(Color.DARK_GRAY);
		sizeSlider.setForeground(Color.WHITE);
		sizeSlider.setMinorTickSpacing(1);
		sizeSlider.setMaximum(2);
		sizeSlider.setMajorTickSpacing(1);
		sizeSlider.setBounds(301, 125, 200, 36);
		contentPane.add(sizeSlider);
	
		temperatureSlider = new JSlider();
		temperatureSlider.setPaintLabels(true);
		temperatureSlider.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		temperatureSlider.setValue(2);
		temperatureSlider.setBackground(Color.DARK_GRAY);
		temperatureSlider.setForeground(Color.WHITE);
		temperatureSlider.setPaintTicks(true);
		temperatureSlider.setMajorTickSpacing(1);
		temperatureSlider.setMaximum(3);
		temperatureSlider.setBounds(301, 188, 200, 54);
		

		temperatureTable = new Hashtable<Integer, JLabel>();
		temperatureTable.put(0, new JLabel("20°C"));
		temperatureTable.put(1, new JLabel("35°C"));
		temperatureTable.put(2, new JLabel("60°C"));
		temperatureTable.put(3, new JLabel("85°C"));
		for (JLabel l : temperatureTable.values()) {
			l.setForeground(Color.WHITE);
		}
		temperatureSlider.setLabelTable(temperatureTable);

		contentPane.add(temperatureSlider);

		JButton icedTeaButton = new JButton("Iced Tea");
		icedTeaButton.setForeground(Color.WHITE);
		icedTeaButton.setBackground(Color.DARK_GRAY);
		icedTeaButton.setBounds(12, 182, 96, 25);
		contentPane.add(icedTeaButton);
		icedTeaButton.setEnabled(false);

		lblSugar = new JLabel("Sugar");
		lblSugar.setForeground(Color.WHITE);
		lblSugar.setBackground(Color.DARK_GRAY);
		lblSugar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSugar.setBounds(350, 34, 96, 15);
		contentPane.add(lblSugar);

		JLabel lblSize = new JLabel("Size");
		lblSize.setForeground(Color.WHITE);
		lblSize.setBackground(Color.DARK_GRAY);
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblSize.setBounds(380, 113, 44, 15);
		contentPane.add(lblSize);

		JLabel lblTemperature = new JLabel("Temperature");
		lblTemperature.setForeground(Color.WHITE);
		lblTemperature.setBackground(Color.DARK_GRAY);
		lblTemperature.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperature.setBounds(363, 173, 96, 15);
		contentPane.add(lblTemperature);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		lblCoins.setLabelFor(panel);
		panel.setBounds(538, 25, 96, 97);
		contentPane.add(panel);

		JButton money50centsButton = new JButton("0.50 €");
		money50centsButton.setForeground(Color.WHITE);
		money50centsButton.setBackground(Color.DARK_GRAY);
		panel.add(money50centsButton);

		JButton money10centsButton = new JButton("0.10 €");
		money10centsButton.setForeground(Color.WHITE);
		money10centsButton.setBackground(Color.DARK_GRAY);
		panel.add(money10centsButton);

		JButton money5centsButton = new JButton("0.05 €");
		money5centsButton.setForeground(Color.WHITE);
		money5centsButton.setBackground(Color.DARK_GRAY);
		panel.add(money5centsButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(538, 145, 96, 40);
		contentPane.add(panel_1);

		nfcBiiiipButton = new JButton("biiip");
		nfcBiiiipButton.setForeground(Color.WHITE);
		nfcBiiiipButton.setBackground(Color.DARK_GRAY);
		nfcBiiiipButton.setEnabled(false);
		panel_1.add(nfcBiiiipButton);

		JLabel lblNfc = new JLabel("NFC");
		lblNfc.setForeground(Color.WHITE);
		lblNfc.setHorizontalAlignment(SwingConstants.CENTER);
		lblNfc.setBounds(541, 125, 41, 15);
		contentPane.add(lblNfc);
		
		JLabel idNFC = new JLabel("id (only Numbers)");
		idNFC.setForeground(Color.WHITE);
		idNFC.setHorizontalAlignment(SwingConstants.CENTER);
		idNFC.setBounds(525, 182, 110, 15);
		contentPane.add(idNFC);
		
		NFCField = new JFormattedTextField(NumberFormat.getNumberInstance());
		NFCField.setForeground(Color.WHITE);
		NFCField.setBackground(Color.DARK_GRAY);
		NFCField.setBounds(552, 200, 65, 15);
		contentPane.add(NFCField);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 292, 622, 15);
		contentPane.add(separator);

		JButton addCupButton = new JButton("Add cup");
		addCupButton.setForeground(Color.WHITE);
		addCupButton.setBackground(Color.DARK_GRAY);
		addCupButton.setBounds(45, 336, 96, 25);
		contentPane.add(addCupButton);
		
		JButton refundReservesButton = new JButton("Refund Reserves");
		refundReservesButton.setForeground(Color.WHITE);
		refundReservesButton.setBackground(Color.DARK_GRAY);
		refundReservesButton.setBounds(20, 400, 150, 25);
		contentPane.add(refundReservesButton);

		myPicture = null;
		try {
			myPicture = ImageIO.read(new File("./picts/vide2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		buttonForPicture = new JButton(new ImageIcon(myPicture));
		buttonForPicture.setBounds(203, 319, 230, 260);
		contentPane.add(buttonForPicture);
		

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(538, 217, 96, 33);
		contentPane.add(panel_2);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setBackground(Color.DARK_GRAY);
		panel_2.add(cancelButton);

		
		// listeners
		sizeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				drinkingMachineFSM.raiseSlider();
			}
		});
		

		temperatureSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				drinkingMachineFSM.raiseSlider();
			}
		});
		
		sugarSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				drinkingMachineFSM.raiseSlider();
				if(sugarReserve<sugarSlider.getValue()*1) {
					sugarSlider.setValue(sugarReserve);
				}
			}
		});
		
		option1.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				drinkingMachineFSM.raiseOptions();
			}
		});
		
		option2.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				drinkingMachineFSM.raiseOptions();
			}
		});
		
		option3.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				drinkingMachineFSM.raiseOptions();
			}
		});
		
		coffeeButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				controller.boisson = new Coffee("coffee", 0.35, recipeLabel, cupAdded);
				if(cupAdded) {
					controller.boisson.setPrice(0.25);
				}
				drinkingMachineFSM.raiseDrinkButton();
			}
		});
		
		expressoButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				controller.boisson = new Expresso("expresso", 0.50, recipeLabel, cupAdded);
				if(cupAdded) {
					controller.boisson.setPrice(0.40);
				}
				drinkingMachineFSM.raiseDrinkButton();
			}
		});
		
		
		teaButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				controller.boisson = new Tea("tea", 0.40, recipeLabel, cupAdded);
				if(cupAdded) {
					controller.boisson.setPrice(0.30);
				}
				drinkingMachineFSM.raiseDrinkButton();
			}
		});
		
		addCupButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				drinkingMachineFSM.raiseAddCupButton();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				drinkingMachineFSM.raiseCancelButton();
			}
		});
		
		nfcBiiiipButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				drinkingMachineFSM.raiseNFCButton();
			}
		});
		
		NFCField.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				if(NFCField.getValue()!=null) {
					nfcBiiiipButton.setEnabled(true);
				}
			}
		});
		
		money10centsButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				if(!isNFCDone) {
					controller.increaseCoin(0.10);
				}
				drinkingMachineFSM.raiseMoneyButton();
			}
				
		}); 
		
		money50centsButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				if(!isNFCDone) {
					controller.increaseCoin(0.50);
				}
				drinkingMachineFSM.raiseMoneyButton();
			}
		}); 
		
		money5centsButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				if(!isNFCDone) {
					controller.increaseCoin(0.05);
				}
				drinkingMachineFSM.raiseMoneyButton();
				
			}
		});
		
		buttonForPicture.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				drinkingMachineFSM.raiseTakeCup();
			}
		});
		
		refundReservesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drinkingMachineFSM.raiseRefundReservesButton();
			}
		});

	}


}
