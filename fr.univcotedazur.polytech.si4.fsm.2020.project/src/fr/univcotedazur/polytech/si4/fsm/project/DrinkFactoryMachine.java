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
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import fr.univcotedazur.polytech.si4.fsm.project.drinkingmachine.DrinkingMachineStatemachine;
import fr.univcotedazur.polytech.si4.fsm.project.recette.RecetteStatemachine;

public class DrinkFactoryMachine extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2030629304432075314L;
	private JPanel contentPane;
	private JLabel messagesToUser, boissonChoose,coinInsert, labelForPictures, priceLabel, recetteLabel;
	private JSlider sugarSlider, sizeSlider, temperatureSlider;
	protected DrinkingMachineStatemachine myFSM;
	private RecetteStatemachine recetteFSM;
	protected FactoryController controller;
	private Hashtable<Integer, JLabel> temperatureTable;
	private BufferedImage myPicture;
	private Boolean isNFCDone = false, isPaiementLiquideDone = false;
	private JProgressBar progressBar;
	
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
	
	public void errorPaiement() {
		messagesToUser.setText("<html>Erreur, vous ne pouvez<br> pas changer de moyen de paiement");
	}
	
	public void updateCoin() {
		isPaiementLiquideDone=true;
		BigDecimal bd = new BigDecimal(controller.insertedCoin);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		coinInsert.setText("<html>Monnaie : " + bd.doubleValue() + " €");
		if(controller.boisson!=null) {
			if(controller.insertedCoin>=controller.boisson.price) {
				myFSM.raiseConfirmationLiquide();
			}
		}
	}
	
	public void rendueMonnaie() {
		double rendue = controller.insertedCoin - controller.boisson.price;
		controller.insertedCoin = rendue;
		BigDecimal bd = new BigDecimal(rendue);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		messagesToUser.setText("<html>Rendue de monnaie : " + bd.doubleValue() + " €" );
	}
	
	public void paiementNFC() {
		isNFCDone = true;
		messagesToUser.setText("Paiement de " + controller.boisson.price + " € accepté");
	}
	
	public void annulationNFC() {
		isNFCDone = false;
		messagesToUser.setText("<html>Annulation du paiment NFC");
	}
	
	public void annulationPaiementLiquide() {
		isPaiementLiquideDone=false;
		messagesToUser.setText("<html>Rendue de monnaie : " + controller.insertedCoin + " €" );
	}
	/**
	 * Create the frame.
	 * 
	 */
	public void enAttente() {
		controller.insertedCoin = 0.0;
		controller.setBoisson(null);
		messagesToUser.setText("<html>Votre Commande :");
		boissonChoose.setText("Boisson :");
		coinInsert.setText("<html>Monnaie : " + controller.insertedCoin + " €");
		priceLabel.setText("Prix : " + 0.0 + " €");
	}
	
	public void attentePaiement() {
		messagesToUser.setText("<html>Votre Commande :");
	}
	
	public void updateSlider() {
		controller.setSize(sizeSlider.getValue());
		controller.setSugar(sugarSlider.getValue());
		controller.setTemperature(temperatureSlider.getValue());
	}
	
	public void updateBoisson() {
		boissonChoose.setText("Boisson : " + controller.boisson.name);
		priceLabel.setText("Prix : " + controller.boisson.price + " €");
		if(controller.insertedCoin>=controller.boisson.price) {
			myFSM.raiseConfirmationLiquide();
		}
	}
	
	public void nettoyageText() {
		messagesToUser.setText("<html>Nettoyage de la machine<br> en cours");
		progressBar.setValue(0);
		try {
			myPicture = ImageIO.read(new File("./picts/vide2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		labelForPictures.setIcon(new ImageIcon(myPicture));
	}
	
	
	public void lectureCarte() {
		isNFCDone = true;
		messagesToUser.setText("<html>Lecture de la carte<br> en cours ...");
	}
	
	public void doReset() {
		if(isNFCDone) {
			annulationNFC();
		}else if (isPaiementLiquideDone) {
			annulationPaiementLiquide();
		}else {
			messagesToUser.setText("<html>Machine prête");
		}
		sugarSlider.setValue(1);
		sizeSlider.setValue(1);
		temperatureSlider.setValue(2);
		controller.setBoisson(null);
		controller.insertedCoin = 0.0;
		
		boissonChoose.setText("");
		coinInsert.setText("");
		priceLabel.setText("");
	}
	
	
	public void prepareBoisson() {
		boissonChoose.setText("");
		coinInsert.setText("");
		priceLabel.setText("");
		isNFCDone=false;
		isPaiementLiquideDone=false;
		controller.prepare();
		BigDecimal bd = new BigDecimal(controller.timeValue/1000);
		bd = bd.setScale(0, BigDecimal.ROUND_UP);
		messagesToUser.setText("<html>Temps de préparation : " + bd.doubleValue() + "s");
		
		try {
			myPicture = ImageIO.read(new File("./picts/gobeletPolluant.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		labelForPictures.setIcon(new ImageIcon(myPicture));
		recetteFSM.raiseBeginRecette();
	}
	
	public void boissonPrete() {
		messagesToUser.setText("<html>Votre boisson est prête");
		recetteLabel.setText("");
	}
	
	public DrinkFactoryMachine() {
		
		controller = new FactoryController();
		myFSM= new DrinkingMachineStatemachine();
		TimerService timer = new TimerService();
		myFSM.setTimer(timer);
		myFSM.init();
		myFSM.enter();
		myFSM.getSCInterface().getListeners().add(new DrinkFactoryMachineControlerInterface(this));
		
		recetteFSM= new RecetteStatemachine();
		TimerService timer2 = new TimerService();
		recetteFSM.setTimer(timer2);
		recetteFSM.init();
		recetteFSM.enter();
		recetteFSM.getSCInterface().getListeners().add(new RecetteMachineControllerInterface(this));
		
		
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
		
		recetteLabel = new JLabel("");
		recetteLabel.setForeground(Color.WHITE);
		recetteLabel.setHorizontalAlignment(SwingConstants.LEFT);
		recetteLabel.setVerticalAlignment(SwingConstants.TOP);
		recetteLabel.setToolTipText("message to the user");
		recetteLabel.setBackground(Color.WHITE);
		recetteLabel.setBounds(126, 64, 165, 175);
		
		boissonChoose = new JLabel("");
		boissonChoose.setForeground(Color.WHITE);
		boissonChoose.setHorizontalAlignment(SwingConstants.LEFT);
		boissonChoose.setVerticalAlignment(SwingConstants.TOP);
		boissonChoose.setToolTipText("message to the user");
		boissonChoose.setBackground(Color.WHITE);
		boissonChoose.setBounds(126, 94, 165, 175);
		
		priceLabel = new JLabel("");
		priceLabel.setForeground(Color.WHITE);
		priceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		priceLabel.setVerticalAlignment(SwingConstants.TOP);
		priceLabel.setToolTipText("message to the user");
		priceLabel.setBackground(Color.WHITE);
		priceLabel.setBounds(126, 114, 165, 175);
		
		coinInsert = new JLabel("");
		coinInsert.setForeground(Color.WHITE);
		coinInsert.setHorizontalAlignment(SwingConstants.LEFT);
		coinInsert.setVerticalAlignment(SwingConstants.TOP);
		coinInsert.setToolTipText("message to the user");
		coinInsert.setBackground(Color.WHITE);
		coinInsert.setBounds(126, 134, 165, 175);
		
		contentPane.add(messagesToUser);
		contentPane.add(recetteLabel);
		contentPane.add(boissonChoose);
		contentPane.add(priceLabel);
		contentPane.add(coinInsert);

		JLabel lblCoins = new JLabel("Coins");
		lblCoins.setForeground(Color.WHITE);
		lblCoins.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoins.setBounds(538, 12, 44, 15);
		contentPane.add(lblCoins);

		JButton coffeeButton = new JButton("Coffee");
		coffeeButton.setForeground(Color.WHITE);
		coffeeButton.setBackground(Color.DARK_GRAY);
		coffeeButton.setBounds(12, 34, 96, 25);
		contentPane.add(coffeeButton);
		
	

		JButton expressoButton = new JButton("Expresso");
		expressoButton.setForeground(Color.WHITE);
		expressoButton.setBackground(Color.DARK_GRAY);
		expressoButton.setBounds(12, 71, 96, 25);
		contentPane.add(expressoButton);

		JButton teaButton = new JButton("Tea");
		teaButton.setForeground(Color.WHITE);
		teaButton.setBackground(Color.DARK_GRAY);
		teaButton.setBounds(12, 108, 96, 25);
		contentPane.add(teaButton);

		JButton soupButton = new JButton("Soup");
		soupButton.setForeground(Color.WHITE);
		soupButton.setBackground(Color.DARK_GRAY);
		soupButton.setBounds(12, 145, 96, 25);
		contentPane.add(soupButton);

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

		JLabel lblSugar = new JLabel("Sugar");
		lblSugar.setForeground(Color.WHITE);
		lblSugar.setBackground(Color.DARK_GRAY);
		lblSugar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSugar.setBounds(380, 34, 44, 15);
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
		panel_1.setBounds(538, 154, 96, 40);
		contentPane.add(panel_1);

		JButton nfcBiiiipButton = new JButton("biiip");
		nfcBiiiipButton.setForeground(Color.WHITE);
		nfcBiiiipButton.setBackground(Color.DARK_GRAY);
		panel_1.add(nfcBiiiipButton);

		JLabel lblNfc = new JLabel("NFC");
		lblNfc.setForeground(Color.WHITE);
		lblNfc.setHorizontalAlignment(SwingConstants.CENTER);
		lblNfc.setBounds(541, 139, 41, 15);
		contentPane.add(lblNfc);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 292, 622, 15);
		contentPane.add(separator);

		JButton addCupButton = new JButton("Add cup");
		addCupButton.setForeground(Color.WHITE);
		addCupButton.setBackground(Color.DARK_GRAY);
		addCupButton.setBounds(45, 336, 96, 25);
		contentPane.add(addCupButton);

		myPicture = null;
		try {
			myPicture = ImageIO.read(new File("./picts/vide2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		labelForPictures = new JLabel(new ImageIcon(myPicture));
		labelForPictures.setBounds(175, 319, 286, 260);
		contentPane.add(labelForPictures);

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
				myFSM.raiseSlider();
				controller.setSize(sizeSlider.getValue());
			}
		});
		

		temperatureSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				myFSM.raiseSlider();
				controller.setTemperature(temperatureSlider.getValue());
			}
		});
		
		sugarSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				myFSM.raiseSlider();
				controller.setSugar(sugarSlider.getValue());
			}
		});
		
		coffeeButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				controller.boisson = new Coffee("coffee",progressBar, 0.35,controller, recetteLabel,recetteFSM);
				myFSM.raiseBoissonButton();
			}
		});
		
		expressoButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				controller.boisson = new Expresso("expresso",progressBar, 0.50,controller, recetteLabel,recetteFSM);
				myFSM.raiseBoissonButton();
			}
		});
		
		
		teaButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				controller.boisson = new Tea("tea",progressBar, 0.40, controller, recetteLabel, recetteFSM);
				myFSM.raiseBoissonButton();
			}
		});
		
		addCupButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BufferedImage myPicture = null;
				try {
					myPicture = ImageIO.read(new File("./picts/ownCup.jpg"));
				} catch (IOException ee) {
					ee.printStackTrace();
				}
				labelForPictures.setIcon(new ImageIcon(myPicture));
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				myFSM.raiseCancelButton();
			}
		});
		
		nfcBiiiipButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				myFSM.raiseNFCButton();
			}
		});
		
		money10centsButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				if(!isNFCDone) {
					controller.increaseCoin(0.10);
				}
				myFSM.raiseCoinButton();
			}
				
		}); 
		
		money50centsButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				if(!isNFCDone) {
					controller.increaseCoin(0.50);
				}
				myFSM.raiseCoinButton();
			}
		}); 
		
		money5centsButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed( ActionEvent e) {
				if(!isNFCDone) {
					controller.increaseCoin(0.05);
				}
				myFSM.raiseCoinButton();
				
			}
		}); 

	}


}
