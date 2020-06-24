package grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import Cardspackage.Cards;
import GAME.Decks;
import GAME.Gamestate;
import GAME.Logger;

public class BiggerCard extends JPanel {
	private BufferedImage image;
	private Cards card;
	private  JButton addToMyDeck;
	private JButton addToEnemyDeck;
	private JButton cancel;
	CollectionPanel panel;
	Collection_deck deckbord;
	Collection_herospanel deckpanel;
	public BiggerCard(Cards card, CollectionPanel pan, Collection_deck declbord, Collection_herospanel deckpanel) {
		setPreferredSize(new Dimension(1000	, 1000));
		setSize(new Dimension(1000, 1000));
		setLayout(null);
		setBackground(Color.GRAY);
		this.card=card;
		this.panel=pan;
		this.deckbord=declbord;
		this.deckpanel=deckpanel;
		
		File input_file = new File("src\\card image\\"+card.get_Name()+".png"); 
		image = new BufferedImage(100, 150, 
				BufferedImage.TYPE_INT_ARGB);
		try {
			image = ImageIO.read(input_file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		addToEnemyDeck=new JButton("add to enemy deck");
		addToEnemyDeck.setBounds(68 , 100, 200, 60);
		addToEnemyDeck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(Gamestate.getinsist().getPlayer().getEnemyDeck().addCardToDeck(card))
						Logger.getinsist().log(Gamestate.getinsist().getPlayer().get_name(), "add card to deck", card.get_Name());					
					
					panel.setEnemyDeck();
					deckbord.getEnemyBut().setText("enemy deck  "+ Gamestate.getinsist().getPlayer().getEnemyDeck().getHeroDeck().getname()+"   size : "+Gamestate.getinsist().getPlayer().getEnemyDeck().getDeck().size());
					setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});

		addToMyDeck=new JButton("add to my deck");
		addToMyDeck.setBounds(680 , 200, 200, 60);
		addToMyDeck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(Gamestate.getinsist().getPlayer().getMyDeck().addCardToDeck(card))
						Gamestate.getinsist().getPlayer().getMyDeck().addUsethisDeck(0);
					Gamestate.getinsist().getPlayer().getMyDeck().addWin(0);
					Logger.getinsist().log(Gamestate.getinsist().getPlayer().get_name(), "add card to deck", card.get_Name());					
					setVisible(false);
					panel.setdeck();
					deckbord.updateBut(BiggerCard.this.deckpanel, panel);
					deckbord.repaint();
					deckbord.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		add(addToEnemyDeck);
		add(addToMyDeck);
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

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 20, 20,600, 600,null);
		g.setFont(new Font("Tahoma", Font.BOLD, 15));
		g.drawString("name  :   " + card.get_Name(), 20, 670);
		g.drawString("mana  :   " + card.get_Mana(), 20, 700);
		g.drawString("class :   " + card.get_Class(), 20, 730);
		g.drawString("rarity :   " + card.get_Rarity(), 20, 760);
		g.drawString("type :   " + card.getType(), 20, 790);
		g.drawString("description :  " + card.getDescription(), 20,820);
		if(card.getType().equalsIgnoreCase("minion") ||card.getType().equalsIgnoreCase("weapon")) {
			g.drawString("attack  :   " + card.getAttack(), 20, 850);
			g.drawString("HP  :  " + card.getHp(), 20, 880);			
		}






	}
}
