package grapic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Cardspackage.Cards;
import GAME.Gamestate;
import GAME.Logger;

public class Collection_search extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextField text;
	private JButton ok;
	private JButton g1;
	private JButton g2;
	private JButton g3;
	private JButton g4;
	private JButton g5;
	private JButton g6;
	private JButton g7;
	private JButton g8;
	private JButton g9;
	private JButton g10;
	private JButton g0;
	private JButton all;
	private JButton have;
	private JButton notHave;
	private ArrayList<JButton> but=new ArrayList<>();
	private Gamestate game;
	private ArrayList<JLabel> current;
	private Logger log;

	public Collection_search(MainFrame f) throws Exception {
		setPreferredSize(new Dimension(1500, 2000));
		game=Gamestate.getinsist();
		log=Logger.getinsist();
		text=new JTextField("search",50);
		ok=new JButton("Ok");
		g0=new JButton(new ImageIcon("src\\button image\\gem0.png"));	
		g1=new JButton(new ImageIcon("src\\button image\\gem1.png"));	
		g2=new JButton(new ImageIcon("src\\button image\\gem2.png"));	
		g3=new JButton(new ImageIcon("src\\button image\\gem3.png"));	
		g4=new JButton(new ImageIcon("src\\button image\\gem4.png"));	
		g5=new JButton(new ImageIcon("src\\button image\\gem5.png"));	
		g6=new JButton(new ImageIcon("src\\button image\\gem6.png"));	
		g7=new JButton(new ImageIcon("src\\button image\\gem7.png"));	
		g8=new JButton(new ImageIcon("src\\button image\\gem8.png"));	
		g9=new JButton(new ImageIcon("src\\button image\\gem9.png"));	
		g10=new JButton(new ImageIcon("src\\button image\\gem10.png"));	
		all=new JButton("All");	
		have=new JButton("i have");	
		notHave=new JButton("i  dont have");	

		but.add(g0);
		but.add(g1);
		but.add(g2);
		but.add(g3);
		but.add(g4);
		but.add(g5);
		but.add(g6);
		but.add(g7);
		but.add(g8);
		but.add(g9);
		but.add(g10);
		for(JButton b : but) {
			b.setContentAreaFilled(false);
			b.setBorder(BorderFactory.createEmptyBorder());
		}
		//		///// listener
		g0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked search button ", "0 gem");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				manaFilter(0, f);	setPreferredSize(new Dimension(1500, 2000));
				revalidate();	repaint();
			}
		});
		g1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked search button ", "1 gem");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				manaFilter(1, f);	setPreferredSize(new Dimension(1500, 2000));
				revalidate();	repaint();
			}
		});
		g2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked search button ", "2 gem");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				manaFilter(2, f);	setPreferredSize(new Dimension(1500, 2000));
				revalidate();	repaint();
			}
		});
		g3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked search button ", "3 gem");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				manaFilter(3, f);	setPreferredSize(new Dimension(1500, 2000));
				revalidate();	repaint();
			}
		});
		g4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked search button ", "4 gem");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				manaFilter(4, f);	setPreferredSize(new Dimension(1500, 2000));
				revalidate();	repaint();
			}
		});
		g5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked search button ", "5 gem");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				manaFilter(5, f);	setPreferredSize(new Dimension(1500, 2000));
				revalidate();	repaint();
			}
		});
		g6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked search button ", "6 gem");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				manaFilter(6, f);	setPreferredSize(new Dimension(1500, 2000));
				revalidate();	repaint();
			}
		});
		g7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked search button ", "7 gem");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				manaFilter(7, f);	setPreferredSize(new Dimension(1500, 2000));
				revalidate();	repaint();
			}
		});
		g8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked search button ", "8 gem");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				manaFilter(8, f);	setPreferredSize(new Dimension(1500, 2000));
				revalidate();	repaint();
			}
		});
		g9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked search button ", "9 gem");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				manaFilter(9, f);	setPreferredSize(new Dimension(1500, 2000));
				revalidate();		repaint();	
			}
		});
		g10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked search button ", "10 gem");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				setPreferredSize(new Dimension(1500, 2000));
				manaFilter(10, f);
				revalidate();	repaint();

			}
		});
		//////// button filter
		all.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked search button ", "show all cards");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				setPreferredSize(new Dimension(1500, 3500));
				setBackground(new Color(0, 0, 51));
				setCard(f, "all");
				revalidate();	repaint();
			}
		});
		have.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked search button ", "show cards that have");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				setPreferredSize(new Dimension(1500,2500));
				setCard(f, "sum");
				revalidate();	repaint();
			}
		});
		notHave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					log.log(game.getPlayer().get_name(), "clicked search button ", "show cards that dont have");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				setPreferredSize(new Dimension(1500,2000));
				setCard(f, "not");
				revalidate();
				repaint();
			}
		});

		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setPreferredSize(new Dimension(1500, 2000));
				String su=text.getText();
				nameFilter(su, f);
				revalidate();
				repaint();
				try {
					log.log(game.getPlayer().get_name(), "clicked search button ", "want to show cards with name filter!! searched : "+su);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		add(g0);
		add(g1);
		add(g2);
		add(g3);
		add(g4);
		add(g5);
		add(g6);
		add(g7);
		add(g8);
		add(g9);
		add(g10);
		add(all);
		add(have);
		add(notHave);
		add(text);
		add(ok);
		current=new ArrayList<>();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon =new ImageIcon("src\\backgrund image\\search.jpg");
		g.drawImage(icon.getImage(), 0, 0, null);
	}
	private void manaFilter(int x,MainFrame f) {
		for(int i=current.size()-1;i>=0;i--) {
			remove(current.get(i));
			current.remove(current.get(i));
		}
		for(Cards s : game.getPlayer().get_myCards()) {
			if(s.get_Mana()==x) {
				final JLabel lp =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+".png"));
				lp.addMouseListener(new MouseListener() {			
					@Override
					public void mouseReleased(MouseEvent arg0) {
					}
					@Override
					public void mousePressed(MouseEvent arg0) {
						int x=JOptionPane.showConfirmDialog(null, "do you want to add this to yor deck",
								"add card to deck", JOptionPane.OK_CANCEL_OPTION);
						if(x==JOptionPane.OK_OPTION) {
							if(game.getPlayer().getMyDeck().addCardToDeck(s)){	
								try {
									log.log(game.getPlayer().get_name(), "add card to deck", "card is : "+s.get_Name());
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}else {
								try {
									log.log(game.getPlayer().get_name(), "cant add card to deck", "card is : "+s.get_Name());
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						}
					}
					@Override
					public void mouseExited(MouseEvent arg0) {
					}
					@Override
					public void mouseEntered(MouseEvent arg0) {
					}
					@Override
					public void mouseClicked(MouseEvent arg0) {
					}
				});
				current.add(lp);
			}
		}
		for(Cards s2 : game.getPlayer().getMyStore().getBuyCard()) {
			if(s2.get_Mana()==x) {
				final JLabel lp1 =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s2.get_Name()+"1.png"));
				lp1.addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent arg0) {
					}
					@Override
					public void mousePressed(MouseEvent arg0) {
						int x=JOptionPane.showConfirmDialog(null, "you  dont have this card do yo want to buy this",
								"confirm", JOptionPane.OK_CANCEL_OPTION);
						if(x==JOptionPane.OK_OPTION) {

							try {
								log.log(game.getPlayer().get_name(), "go to shop to buy card", "card is : "+s2.get_Name());
								Shop sh=new Shop((MainFrame)f);	
								f.setContentPane(sh);
								f.repaint();
								f.revalidate();
							} catch (Exception e) {
								System.out.println("serch panel");
								e.printStackTrace();
							}
							f.pack();
							f.setLocationRelativeTo(null);
						}
					}
					@Override
					public void mouseExited(MouseEvent arg0) {
					}
					@Override
					public void mouseEntered(MouseEvent arg0) {
					}
					@Override
					public void mouseClicked(MouseEvent arg0) {
					}
				});
				current.add(lp1);
			}
		}
		for(JLabel lg: current) {
			add(lg);	
		}
	}
	private void setCard(MainFrame f,String text) {
		for(int i=current.size()-1;i>=0;i--) {
			remove(current.get(i));
			current.remove(current.get(i));
		}
		if(text.equalsIgnoreCase("all")) {
			addHave(f);
			addnothave(f);
		}else if(text.equalsIgnoreCase("sum")) {
			addHave(f);
		}else {
			addnothave(f);
		}
		for(JLabel lg: current) 
			add(lg);	
	}
	private void addHave(MainFrame f) {
		for(Cards s : game.getPlayer().get_myCards()) {
			final JLabel lp =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+".png"));
			lp.addMouseListener(new MouseListener() {			
				@Override
				public void mouseReleased(MouseEvent arg0) {
				}
				@Override
				public void mousePressed(MouseEvent arg0) {
					int x=JOptionPane.showConfirmDialog(null, "do you want  add this to your deck",
							"add card to deck", JOptionPane.OK_CANCEL_OPTION);
					if(x==JOptionPane.OK_OPTION) {
						if(game.getPlayer().getMyDeck().addCardToDeck(s)){
							try {
								log.log(game.getPlayer().get_name(), "add card to deck", "card is : "+s.get_Name());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {
				}
				@Override
				public void mouseClicked(MouseEvent arg0) {
				}
			});
			current.add(lp);
		}
	}
	private void addnothave(MainFrame f) {
		for(Cards s2 : game.getPlayer().getMyStore().getBuyCard()) {
			final JLabel lp1 =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s2.get_Name()+"1.png"));
			lp1.addMouseListener(new MouseListener() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
				}
				@Override
				public void mousePressed(MouseEvent arg0) {
					int x=JOptionPane.showConfirmDialog(null, "you  dont have this card do yo want to buy this",
							"confirm", JOptionPane.OK_CANCEL_OPTION);
					if(x==JOptionPane.OK_OPTION) {

						try {
							log.log(game.getPlayer().get_name(), "go to shop to buy card", "card is : "+s2.get_Name());
							Shop sh=new Shop((MainFrame)f);	
							f.setContentPane(sh);
							f.repaint();
							f.revalidate();
						} catch (Exception e) {
							System.out.println("serch panel");
							e.printStackTrace();
						}
						f.pack();
						f.setLocationRelativeTo(null);
					}
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
				}
				@Override
				public void mouseEntered(MouseEvent arg0) {
				}
				@Override
				public void mouseClicked(MouseEvent arg0) {
				}
			});
			current.add(lp1);
		}
	}
	private void nameFilter(String x,MainFrame f) {
		for(int i=current.size()-1;i>=0;i--) {
			remove(current.get(i));
			current.remove(current.get(i));
		}
		for(Cards s : game.getPlayer().get_myCards()) {
			if(s.get_Name().contains(x)) {

				final JLabel lp =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s.get_Name()+".png"));
				lp.addMouseListener(new MouseListener() {			
					@Override
					public void mouseReleased(MouseEvent arg0) {
					}
					@Override
					public void mousePressed(MouseEvent arg0) {
						int x=JOptionPane.showConfirmDialog(null, "do you want to add this to yor deck",
								"add card to deck", JOptionPane.OK_CANCEL_OPTION);
						if(x==JOptionPane.OK_OPTION) {
							if(game.getPlayer().getMyDeck().addCardToDeck(s)){	
								try {
									log.log(game.getPlayer().get_name(), "add card to deck", "card is : "+s.get_Name());
								} catch (IOException e) {
									e.printStackTrace();
								}

							}
						}
					}
					@Override
					public void mouseExited(MouseEvent arg0) {
					}
					@Override
					public void mouseEntered(MouseEvent arg0) {
					}
					@Override
					public void mouseClicked(MouseEvent arg0) {
					}
				});
				current.add(lp);
			}
		}
		for(Cards s2 : game.getPlayer().getMyStore().getBuyCard()) {
			if(s2.get_Name().contains(x)) {
				final JLabel lp1 =new JLabel(new ImageIcon( System.getProperty("user.dir")+"\\src\\card image\\"+s2.get_Name()+"1.png"));
				lp1.addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent arg0) {
					}
					@Override
					public void mousePressed(MouseEvent arg0) {
						int x=JOptionPane.showConfirmDialog(null, "you  dont have this card do you want to buy this????",
								"confirm", JOptionPane.OK_CANCEL_OPTION);
						if(x==JOptionPane.OK_OPTION) {
							try {
								log.log(game.getPlayer().get_name(), "go to shop to buy card", "card is : "+s2.get_Name());
								Shop sh=new Shop((MainFrame)f);	
								f.setContentPane(sh);
								f.repaint();
								f.revalidate();
							} catch (Exception e) {
								System.out.println("serch panel");
								e.printStackTrace();
							}
							f.pack();
							f.setLocationRelativeTo(null);
						}
					}
					@Override
					public void mouseExited(MouseEvent arg0) {
					}
					@Override
					public void mouseEntered(MouseEvent arg0) {
					}
					@Override
					public void mouseClicked(MouseEvent arg0) {
					}
				});
				current.add(lp1);
			}
		}
		for(JLabel lg: current) {
			add(lg);	
		}
	}
}
