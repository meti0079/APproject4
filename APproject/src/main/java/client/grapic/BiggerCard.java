package client.grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.google.gson.Gson;

import client.Client;
import client.Controller;
import client.model.Card;
import gameModel.requestAndREsponse.AddCardToDeck;


public class BiggerCard extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private JButton addToMyDeck;
	private JButton addToEnemyDeck;
	private JButton cancel;
	private Card card;
	public BiggerCard(Card card, Controller controller) {
		this.card=card;
		initial();
		readImage();
		initialButtonAddToDeck(controller);
		initialButtonAddToEnemy(controller);
		initialButtonCancel();
	}
	private void initial() {
		setPreferredSize(new Dimension(1000	, 1000));
		setSize(new Dimension(1000, 1000));
		setLayout(null);
		setBackground(Color.GRAY);
	}
	private void initialButtonCancel() {
		cancel=new JButton("cancel");
		cancel.setBounds(680, 300, 100, 60);
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		add(cancel);
	}
	private void readImage() {
		File input_file = new File("src\\main\\java\\card image\\"+card.getName()+".png"); 
		image = new BufferedImage(100, 150, 
				BufferedImage.TYPE_INT_ARGB);
		try {image = ImageIO.read(input_file);
		} catch (IOException e) {e.printStackTrace();}
	}
	private void initialButtonAddToEnemy(Controller controlle) {
		addToEnemyDeck=new JButton("add to enemy deck");
		addToEnemyDeck.setBounds(680 , 100, 200, 60);
		addToEnemyDeck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String message="ADDTOENEMYDECK>>"+new Gson().toJson(new AddCardToDeck(controlle.getUser().getTocken(), card.getName()))+"#";
					Client.WriteMessage(message);
					setVisible(false);
				} catch (Exception e) {e.printStackTrace();}
			}
		});
		add(addToEnemyDeck);
	}
	private void initialButtonAddToDeck(Controller controller) {
		addToMyDeck=new JButton("add to my deck");
		addToMyDeck.setBounds(680 , 200, 200, 60);
		addToMyDeck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String message="ADDTOMYDECK>>"+new Gson().toJson(new AddCardToDeck(controller.getUser().getTocken(), card.getName()))+"#";
					Client.WriteMessage(message);
					setVisible(false);
				} catch (Exception e1) {e1.printStackTrace();}
			}
		});
		add(addToMyDeck);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 20, 20,600, 600,null);
		renderDetails(g);
		renderDetailsMinion(g);

	}
	private void renderDetails(Graphics g) {
		g.setFont(new Font("Tahoma", Font.BOLD, 15));
		g.drawString("name  :   " + card.getName(), 20, 670);
		g.drawString("mana  :   " + card.getMana(), 20, 700);
		g.drawString("class :   " + card.getCardClass(), 20, 730);
		g.drawString("rarity :   " + card.getRarity(), 20, 760);
		g.drawString("type :   " + card.getType(), 20, 790);
		g.drawString("description :  " + card.getDescription(), 20,820);
	}
	private void renderDetailsMinion(Graphics g) {
		if(card.getType().equalsIgnoreCase("minion") ||card.getType().equalsIgnoreCase("weapon")) {
			g.drawString("attack  :   " + card.getAttack(), 20, 850);
			g.drawString("HP  :  " + card.getHP(), 20, 880);			
		}
	}
	}
