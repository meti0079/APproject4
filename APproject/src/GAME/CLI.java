//package GAME;
//
//import java.awt.Container;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import javax.swing.text.StyledEditorKit.BoldAction;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import CArds.Cards;
//import Heroooo.Heros;
//import Heroooo.Mage;
//import Heroooo.Rouge;
//import Heroooo.Warlock;
//
//public class CLI {
//	Scanner sca=new Scanner(System.in);
//	players player;
//	Store stor;
//	Logger lg=new Logger();
//
//	ArrayList<String> dastor=new ArrayList<String>();
//	boolean ex=true;
//	boolean exiiiit=true;
//	Gson json=new GsonBuilder().setPrettyPrinting().create();
//	public CLI() throws Exception {
//		dastor.add("exit");
//		dastor.add("exit-a");
//		dastor.add("delete-player");
//		dastor.add("collections");
//		dastor.add("store");
//		dastor.add("ls-a-heros");
//		dastor.add("ls-m-heros");
//		dastor.add("select");
//		dastor.add("ls-a-cards");
//		dastor.add("ls-m-cards");
//		dastor.add("ls-n-cards");
//		dastor.add("add");
//		dastor.add("remove");
//		dastor.add("buy");
//		dastor.add("wallet");
//		dastor.add("sell");
//		dastor.add("ls-s");
//		dastor.add("ls-b");
//		dastor.add("hearthstone--help");
//		while(ex) {
//
//			boolean flag=true;
//			String s;
//			String name;
//			String pass;
//			while(flag) {
//				System.out.println("already have an account?(y/n)");
//				s=sca.next();
//				if(s.charAt(0)=='y' || s.charAt(0)=='Y') {
//					System.out.println("enter your username");
//					name=sca.next();
//
//					System.out.println("enter your password");
//					pass=sca.next();
//					while (!checkname(name, pass)) {
//						System.out.println("your username or password  is incorrect. tey again");
//						System.out.println("enter your username");
//						name=sca.next();
//						System.out.println("enter your password");
//						pass=sca.next();
//					}	
//
//					readplayer(name);
//
//					stor=player.getMystore();
//					flag=false;
//
//				}else if(s.charAt(0)=='n' ||s.charAt(0)=='N') {
//
//					System.out.println("enter your username");
//					name=sca.next();
//					System.out.println("enter your password");
//					pass=sca.next();
//					while(checkvalid(name)) {
//						System.out.println("this name is already used, try again  or  enter x to exit the game");
//						name=sca.next();
//						System.out.println("enter your password");
//						pass=sca.next();
//						if(name.equals("x"))
//							System.exit(0);
//					}
//					writename(name, pass);
//					File loog=new File(System.getProperty("user.dir")+"\\src\\LOGFILE\\"+name);
//					loog.getParentFile().mkdir();
//					loog.createNewFile();
//					this.player=new players(name, pass, 50);
//					lg.makelog(player.get_name(), player.get_pass());
//					lg.log(player.get_name(), "sign up ", " ");
//					stor=new Store();
//					player.setMystore(stor);
//					Login log=new Login(player);
//					flag=false;
//				}else {
//					System.out.println("its not true try again");
//				}
//			}
//			exiiiit=true;
//			while (exiiiit) {
//				String dar=sca.next();
//				int m=0;
//				for(int i=0;i<dastor.size();i++) {
//					if(dastor.get(i).contains(dar)) {
//						m=i;
//						break;
//					}else {
//						m=21;
//					}
//				}
//
//				switch (m) {
//				case 0: lg.log(player.get_name(), "sign out", ""); exiiiit=false;  makeprofile();   break;
//				case 1: lg.log(player.get_name(), "sign out", ""); ex=false;  exiiiit=false; makeprofile(); 
//				break;
//				case 2:   lg.deletaccount(player.get_name());    DeletPlayer(player);  break;
//				case 3:       GoCollection();  break;
//				case 4:       Gostor(); break;
//				case 5:       ShowHero(); break;
//				//				case 6:       ShowMyHero() ; break;
//				//				case 7:       SelectHero();break;
//				case 8:       MyCardd(); break;
//				case 9:       MyDeckd("1") ; break;
//				case 10:      HaveNotUse("1"); break;
//				case 11:      AddCard("1");  break;
//				case 12:      RemoveCard("1"); break;
//				case 13:      BuyCarddd(); break;
//				case 14:      SeeWallet(); break;
//								case 15:      SellMyCard(); break;
//				case 16:      SeeCardCanSell();  break;
//				case 17:      SeeCardCanBuy();   break;
//				case 18:      Help();  break;
//				case 21:      System.out.println("you have inter a valid order   .  please write  hearthstone--help  to see orders "); break;
//				default:       System.out.println("you have inter a valid order   .  please write  hearthstone--help  to see orders ");
//				break;
//				}
//			}	
//		}
//	}
//	public void DeletPlayer(players s) throws IOException {
//		System.out.println("enter your password");
//		String ssssss=sca.nextLine();
//		boolean fll=true;
//		while (fll) {
//			String passss=sca.nextLine();
//			if(passss.equals(player.get_pass())) {
//				System.out.println("account deleted");
//				System.exit(0);
//			}
//			System.out.println("not correct , try again.");
//		}
//	}
//
//	public void GoCollection() {
//		System.out.println("you have "+player.get_myCards().size()+" cards");
//		for (Cards s:player.get_myCards()) {
//			System.out.print(s.get_Name()+"   ");
//		}
//		try {
//			lg.log(player.get_name(), "go ", "Collection");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public void Gostor() throws IOException {
//		System.out.println(" you have "+stor.getBuyCard().size()+" cards in stor");
//		for(Cards s:stor.getBuyCard())
//			System.out.println(s.get_Name());
//		lg.log(player.get_name(), " go stor ", "see cards in store");
//	}
//	public void ShowHero() throws IOException {
//		for(Heros s:player.get_myheros())
//			System.out.print(s.getname()+" , ");
//		lg.log(player.get_name(), " list ", " heros name");
//	}
//	//	public void ShowMyHero() throws IOException {
//	//		System.out.println(player.get_hero().getname());
//	//		lg.log(player.get_name(), "see", " his or her hero");
//	//	}
//	//	public void SelectHero() throws IOException {
//	//		System.out.println("enter your hero");
//	//		String ss=sca.next();
//	//		for (int i = 0; i < player.get_myheros().size(); i++) {
//	//			String me=player.get_myheros().get(i).getname();
//	//			if(me.contains(ss)) {
//	//				player.ChangHero(player.get_myheros().get(i));
//	//				break;
//	//			}
//	//		}
//	//		lg.log(player.get_name(), "select hero", " selected"+player.get_hero().getname());
//	//	}
//	//	public void MyCardd() throws IOException {
//	//		for(Cards s:player.get_myCards())
//	//			System.out.print(s.get_Name()+" , ");
//	//		lg.log(player.get_name(), " list", "see his or her cards");
//	//	}
//	//	public void MyDeckd(String name) throws IOException {
//	//		for(Cards s:player.get_mydeck(name))
//	//			System.out.print(s.get_Name()+" , ");
//	//		System.out.println("Have  "+player.get_mydeck(name).size() +"  card");
//	//		lg.log(player.get_name(), "list", "see his or her deck");
//	//	}
//	//	public void HaveNotUse(String name) throws IOException {
//	//		ArrayList<Cards> neww=new ArrayList<Cards>();
//	//		for(Cards c:player.get_myCards())
//	//			neww.add(c);
//	//		for (int i = 0; i < player.get_mydeck(name).size(); i++) {
//	//			neww.remove(player.get_mydeck(name).get(i));
//	//		}
//	//		for(Cards ch:neww)
//	//			System.out.print(ch.get_Name()+" , ");
//	//		System.out.println(neww.size());
//	//
//	//		lg.log(player.get_name(), "list ", "cards that have but not use ");
//	//	}
//	public void AddCard(String name ) throws IOException {
//		System.out.println(" you can add this card to your hands");
//		ArrayList<Cards> neww=new ArrayList<Cards>();
//		for(Cards c:player.get_myCards())
//			neww.add(c);
//		for (int i = 0; i < player.get_mydeck(name).size(); i++) {
//			neww.remove(player.get_mydeck(name).get(i));
//		}
//		for(Cards s:neww)
//			System.out.print(s.get_Name()+" , ");
//		System.out.println("you cn just add "+neww.size()+" cards");
//		System.out.println("enter a card");
//		String ss=sca.nextLine();
//		String aa=sca.nextLine();
//		for (int i = 0; i < neww.size(); i++) {
//			String sa=neww.get(i).get_Name();
//			if(sa.contains(aa)) {
//				player.get_mydeck(name).add(neww.get(i));
//
//				lg.log(player.get_name(), "add ", " card"+aa);
//				System.out.println("add successful");
//				return;
//			}
//			if(i==player.get_myCards().size());
//			lg.log(player.get_name(), "error ", "not found :"+aa);
//		}
//
//	}
//
//	public void RemoveCard(String name) throws IOException {
//		System.out.println("enter a card");
//		String ss=sca.nextLine();
//		String se=sca.nextLine(); 
//		for (int i = 0; i < player.get_mydeck(name).size(); i++) {
//			String sa=player.get_mydeck(name).get(i).get_Name();
//			if(sa.equals(se)) {
//				player.get_mydeck(name).remove(player.get_mydeck(name).get(i));
//				lg.log(player.get_name(), "remove : ", "card :"+se);
//				System.out.println("removed successful");
//				return;
//			}
//			if(i==player.get_mydeck(name).size()-1)
//				lg.log(player.get_name(), "error : ", "not found card :"+se);
//		}
//	}
//	public void BuyCarddd() throws IOException {
//		System.out.println("for buy a card yo have to spend 2 Gems");
//		for(Cards s:stor.getBuyCard())
//			System.out.println(s.get_Name()+" , ");
//
//		System.out.println("enter a card");
//		String ss=sca.nextLine();
//		String aa=sca.nextLine();
//		for (int i = 0; i < player.getMystore().getBuyCard().size(); i++) {
//			String sa=player.getMystore().getBuyCard().get(i).get_Name();
//			if(sa.equals(aa)) {
//				player.buyacard(player.getMystore().getBuyCard().get(i));
//				lg.log(player.get_name(), "buy : ", "card :"+aa);
//				return;
//			}
//			if(i== player.getMystore().getBuyCard().size()-1)
//				lg.log(player.get_name(), "not found : ", "card :"+aa);
//		}
//	}
//	public void SeeWallet() throws IOException {
//		System.out.println(player.Gem);
//		lg.log(player.get_name(), "see ", "wallet :");
//	}
//	//	public void SellMyCard() throws IOException {
//	//		System.out.println("enter a card");
//	//		System.out.println("when sell a card yo give 1 Gem");
//	//		String ss=sca.nextLine();
//	//		String aa=sca.nextLine();
//	//		for (int i = 0; i < player.get_myCards().size(); i++) {
//	//			String sa=player.get_myCards().get(i).get_Name();
//	//			boolean not=false;
//	//			if(sa.equals(aa)) {
//	//				for (Cards s : player.get.get_hero().get_HeroCard()) {
//	//					if(s.get_Name().contains(sa))
//	//						not=true;
//	//				}
//	//				String cla=player.get_myCards().get(i).get_Class();
//	//				if(cla.contains("Rouge") ||cla.contains("Warlock") || cla.contains("Mage")|| not ) {
//	//					System.out.println("you cant sell this card");
//	//					lg.log(player.get_name(), "sell error : ", "you cant sell this card :"+aa+" because its not neutral");
//	//					return;
//	//				}else {
//	//
//	//					player.sellacard(player.get_myCards().get(i));
//	//					lg.log(player.get_name(), "sell ", "sell this card :"+aa);
//	//					return;
//	//				}
//	//			}
//	//			if(i==player.get_myCards().size()-1)
//	//				lg.log(player.get_name(), "sell error : ", "you cant sell  card :"+aa+" because dont have");
//	//		}
//	//	}
//
//	public void SeeCardCanSell() throws IOException {
//		System.out.println("you can sell this "+player.get_myCards().size()+"  cards : ");
//		for (Cards ss:player.get_myCards()) {
//			System.out.print(ss.get_Name()+" , ");	
//		}	
//		lg.log(player.get_name(), "to  look : ", " card  can sell:");
//	}
//	public void SeeCardCanBuy() throws IOException {
//		for(Cards s:player.getMystore().getBuyCard())
//			System.out.print(s.get_Name()+"  ");
//		lg.log(player.get_name(), "to  look : ", " card  can buy:");
//	}
//
//	public void Help() throws IOException {
//		System.out.println("enter   "+ "exit" +" to exit game .");
//		System.out.println("enter   "+ "exit-a" +"   to close game .");
//		System.out.println("enter   "+ "delete-player" +" to delet players");
//		System.out.println("enter   "+ "collections" +" to see"+" your cards");
//		System.out.println("enter   "+ "store" +" to see"+" your store");
//		System.out.println("enter   "+ "ls-a-heros" +" to see"+" all heros");
//		System.out.println("enter   "+ "ls-m-heros" +" to see"+" your hero");
//		System.out.println("enter   "+ "select" +" to chose"+" a hero");
//		System.out.println("enter   "+ "ls-a-cards" +" to see"+"  all card you have");
//		System.out.println("enter   "+ "ls-m-cards" +" to see"+" your deck");
//		System.out.println("enter   "+ "ls-n-cards" +" to see"+" cards that you have but not use.");
//		System.out.println("enter   "+ "add" +" to "+" add a card to your deck");
//		System.out.println("enter   "+ "remove" +" to "+" remove a card from your deck");
//		System.out.println("enter   "+ "buy" +" to "+" buy a card from store");
//		System.out.println("enter   "+ "wallet" +" to "+" see how much money you have");
//		System.out.println("enter   "+ "sell" +" to "+" sell a card");
//		System.out.println("enter   "+ "ls-s" +" to see"+" cards that you can sell them.");
//		System.out.println("enter   "+ "ls-b" +" to see"+"cards that you can buy them.");	
//		lg.log(player.get_name(), "to  look help ", " ");
//	}
//	@Override
//	public String toString() {
//		String na=System.getProperty("user.dir")+"\\src\\pll\\"+player.get_name();
//		return na;
//	}
//	public void makeprofile() throws IOException {
//		FileWriter f=new FileWriter(toString());
//		String se=json.toJson(player);
//		f.write(se);
//		f.close();
//	}
//
//	public Boolean checkname(String name ,String pass) throws IOException {
//		File file=new File(System.getProperty("user.dir")+"\\src\\PLAYERSNAME\\playersname.txt");
//		Scanner s=new Scanner(file);
//		boolean isther=false;
//		while (s.hasNext()) {
//			String line=s.nextLine();
//			if(line.startsWith(name)) {
//				String pa=s.nextLine();
//				if(pa.startsWith(pass))
//					isther=true;
//			}
//		}
//		s.close();
//		return isther;
//	}
//
//	public boolean checkvalid(String s) throws IOException {
//		boolean  re=false;
//		File file=new File(System.getProperty("user.dir")+"\\src\\PLAYERSNAME\\playersname.txt");
//		Scanner ss=new Scanner(file);
//
//		while (ss.hasNext()) {
//			String line=ss.nextLine();
//			if(line.startsWith(s) ) {
//				re=true;
//			}
//		}
//		ss.close();
//		return re;
//	}
//
//	public void writename(String name ,String pass) throws IOException {
//		FileWriter file=new FileWriter(System.getProperty("user.dir")+"\\src\\PLAYERSNAME\\playersname.txt",true);
//		file.write(name+"\n");
//		file.write(pass+"\n");
//		file.close();
//	}
//	public void readplayer(String name) throws IOException {
//		File f=new File(System.getProperty("user.dir")+"\\src\\pll\\"+name);
//		Scanner s=new Scanner(f);
//		String se="";
//		while(s.hasNext()) {
//			se+=s.nextLine(); 
//		}
//		player=json.fromJson(se, players.class);	
//		lg.log(player.get_name(), "sign in ", "");
//	}
//}
