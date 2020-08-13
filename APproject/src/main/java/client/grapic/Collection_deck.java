package client.grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.google.gson.Gson;

import client.ClientMain;
import client.Controller;
import client.model.DeckInfo;
import gameModel.requestAndREsponse.EditDeckRequest;
import gameModel.requestAndREsponse.NewDeck;
import gameModel.requestAndREsponse.SaveAndExitRequest;
import server.gameModel.Deck;
import server.gameModel.DataReader;
import server.gameModel.Logger;

public class Collection_deck extends JPanel{

	private static final long serialVersionUID = 1L;

	private ArrayList< JButton> allBut;
	private JButton enemyBut;
	private Collection_herospanel x;
	private CollectionPanel y;
	public Collection_deck(Collection_herospanel x,CollectionPanel y, Controller  controller) throws Exception {
		initial();
		this.x=x;
		this.y=y;
		allBut=new ArrayList<>();
		initialAddDeckButton(controller);
		initialChangeNameButton(controller);
		initialSelectHeroButton(controller);
		initialEnemyButton();
	}
	private void initialEnemyButton() {
		enemyBut=new JButton("enemy deck   "+ y.getEnemyHero()+"    size : "+y.getEnemyDeck().size());
		enemyBut.setBounds(10, 60, 280, 40);
		enemyBut.setFont(new Font("tahoma", Font.BOLD, 15));
		enemyBut.setBackground(new Color(165, 62, 22));
		add(enemyBut);
	}
	private void initialAddDeckButton(Controller controller) {
		JButton b= new JButton(new ImageIcon("src\\main\\java\\button image\\add.png"));
		b.setBounds(25,780, 200, 50);
		b.setContentAreaFilled(false);
		b.setBorder(BorderFactory.createEmptyBorder());
		b.setFont(new Font("Tahoma", Font.BOLD, 15));
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DeckInfo s = new DeckInfo();
					while(true ) {
						Boolean flag=false;
						String name=JOptionPane.showInputDialog("enter your deck name")+"";
						for(DeckInfo a : y.deckinfo) {
							if(a.getName().equalsIgnoreCase(name)) {
								flag=true;
							}
						}
						if(flag==false) {
							s.setName(name);
							break;
						}
					}
					String [] myhero=new String[y.getHeros().size()];
					for(int i=0 ;i< y.getHeros().size();i++)
						myhero[i]=y.getHeros().get(i).getname();
					String n="";
					n = (String)JOptionPane.showInputDialog(null, "select deck hero ",
							"select", JOptionPane.QUESTION_MESSAGE, null,myhero, myhero[0]);
					s.setHeroName(n);


					String message="NEWDECK>>"+new Gson().toJson(new NewDeck(s, controller.getUser().getTocken()))+"#";
					ClientMain.WriteMessage(message);

				} catch (Exception e1) {e1.printStackTrace();}
			}
		});
		add(b);
	}
	private void initialSelectHeroButton(Controller controller) {
		JButton edit1=new JButton(new ImageIcon("src\\main\\java\\button image\\edit.png"));
		edit1.setContentAreaFilled(false);
		edit1.setBorder(BorderFactory.createEmptyBorder());
		edit1.setBounds(25, 830, 100, 50);
		edit1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String [] myhero=new String[y.getHeros().size()];
					for(int i=0 ;i< y.getHeros().size();i++)
						myhero[i]=y.getHeros().get(i).getname();
					String n="";
					n = (String)JOptionPane.showInputDialog(null, "select deck hero ",
							"select", JOptionPane.QUESTION_MESSAGE, null,myhero, myhero[0]);
					if(n.equalsIgnoreCase("")) {
					}else {
						String message="EDITHERODECK>>"+new Gson().toJson(new EditDeckRequest(controller.getUser().getTocken(), n, ""))+"#";
						ClientMain.WriteMessage(message);}
				} catch (Exception e1) {e1.printStackTrace();}
			}
		});
		add(edit1);
	}
	private void initialChangeNameButton(Controller controller) {
		JButton edit=new JButton(new ImageIcon("src\\main\\java\\button image\\edit2.png"));
		edit.setContentAreaFilled(false);
		edit.setBorder(BorderFactory.createEmptyBorder());
		edit.setBounds(155, 830, 100, 50);
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Boolean flag=false;
					String name=JOptionPane.showInputDialog("enter your deck name")+"";
					for(DeckInfo a : y.deckinfo) {
						if(a.getName().equalsIgnoreCase(name)) {
							flag=true;
						}
					}
					if(flag==false) {
						String message="EDITNAMEDECK>>"+new Gson().toJson(new EditDeckRequest(controller.getUser().getTocken(), "", name))+"#";
						ClientMain.WriteMessage(message);
					}	
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		add(edit);
	}
	public JButton getEnemyBut() {
		return enemyBut;
	}
	public void update(Controller controller) {
		enemyBut.setText("enemy deck  "+ y.getEnemyHero()+"   size : "+y.getEnemyDeck().size());
		this.updateBut(x, y, controller);
		this.repaint();
		this.revalidate();
	}
	@Override	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon =new ImageIcon("src\\main\\java\\button image\\2.png");
		g.drawImage(icon.getImage(), 5, 0, null);
		drawDeckPlace(g);
	}
	private void drawDeckPlace(Graphics g) { 
		g.drawRoundRect(10, 105, 280, 40, 5, 5);
		g.drawRoundRect(10, 150, 280, 40, 5, 5);
		g.drawRoundRect(10, 195, 280, 40, 5, 5);
		g.drawRoundRect(10, 240, 280, 40, 5, 5);
		g.drawRoundRect(10, 285, 280, 40, 5, 5);
		g.drawRoundRect(10, 330, 280, 40, 5, 5);
		g.drawRoundRect(10, 375, 280, 40, 5, 5);
		g.drawRoundRect(10, 420, 280, 40, 5, 5);
		g.drawRoundRect(10, 465, 280, 40, 5, 5);
		g.drawRoundRect(10, 510, 280, 40, 5, 5);
		g.drawRoundRect(10, 555, 280, 40, 5, 5);
		g.drawRoundRect(10, 600, 280, 40, 5, 5);
		g.drawRoundRect(10, 645, 280, 40, 5, 5);
		g.drawRoundRect(10, 690, 280, 40, 5, 5);
		g.drawRoundRect(10, 735, 280, 40, 5, 5);
	}
	public void updateBut(Collection_herospanel x, CollectionPanel y, Controller controller) {
		for (JButton jButton : allBut) {
			remove(jButton);
		}
		allBut.removeAll(allBut);
		int i=1;
		for(DeckInfo s: y.getDeckinfo()) {
			makeDeck(s, i, controller);
			i++;
		}
	}
	public void makeDeck(DeckInfo s , int i, Controller controller) {
		JButton b= new JButton(s.getName()+"       "+s.getHeroName()+ "   size :"+ s.getSize());
		b.setFont(new Font("tahoma", Font.BOLD, 15));
		b.setBackground(new Color(165, 42, 42));
		b.setBounds(10, 45*i+60, 280, 40);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String message="CHANGEDECK>>"+new Gson().toJson(new EditDeckRequest(controller.getUser().getTocken(), s.getHeroName(), s.getName()))+"#";
					ClientMain.WriteMessage(message);
				}catch (Exception e1) {}
			}
		});
		add(b);
		allBut.add(b);
	}
	private void initial() {
		setLayout(null);
		setPreferredSize(new Dimension(300, 900));
		setBackground(new Color(162, 82,45));
	}
}
