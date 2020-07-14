package GAME;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Cardspackage.Card;
import hero.Heros;
import hero.heroPower.HeroPower;
import passives.Passive;



public class Gamestate {
	Scanner sca=new Scanner(System.in);
	private String state; 
	private	Player player;
	private Store store;
	private	Gson gson;
	public static Gamestate game;
	private String backCard="ca.png";
	private String backBattleGround="nattle1.jpg";
	private Enemy enemy;
	private Object attacker;	
	

	public static Gamestate getinsist() throws Exception {
		if(game==null) {
			game=new Gamestate();
		}
		return game;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {	
		this.player = player;
	}
	public Store getStor() {
		return store;
	}
	public void setStor(Store stor) {
		this.store = stor;
	}
	@Override
	public String toString() {
		String na=System.getProperty("user.dir")+"\\src\\pll\\"+player.get_name();
		return na;
	}
	public void makeProfile() throws IOException {
		FileWriter f=new FileWriter(toString());
		String se=gson.toJson(player);
		f.write(se);
		f.close();
		FileWriter ff=new FileWriter(System.getProperty("user.dir")+"\\src\\pll\\"+player.get_name()+"enemy");
		String ss=gson.toJson(enemy);
		ff.write(ss);
		ff.close();
	}
	public Boolean checkName(String name ,String pass) throws IOException {
		File file=new File(System.getProperty("user.dir")+"\\src\\PLAYERSNAME\\playersname.txt");
		Scanner s=new Scanner(file);
		boolean isther=false;
		while (s.hasNext()) {
			String line=s.nextLine();
			if(line.startsWith(name)) {
				String pa=s.nextLine();
				if(pa.startsWith(pass))
					isther=true;
			}
		}
		s.close();
		return isther;
	}
	public boolean checkValid(String s) throws IOException {
		boolean  re=false;
		File file=new File(System.getProperty("user.dir")+"\\src\\PLAYERSNAME\\playersname.txt");
		Scanner ss=new Scanner(file);
		while (ss.hasNext()) {
			String line=ss.nextLine();
			if(line.startsWith(s) ) {
				re=true;
			}
		}
		ss.close();
		return re;
	}
	public void writeName(String name ,String pass) throws IOException {
		FileWriter file=new FileWriter(System.getProperty("user.dir")+"\\src\\PLAYERSNAME\\playersname.txt",true);
		file.write(name+"\n");
		file.write(pass+"\n");
		file.close();
	}
	public void readPlayer(String name) throws IOException {
		File f=new File(System.getProperty("user.dir")+"\\src\\pll\\"+name);
		Scanner s=new Scanner(f);
		String se="";
		while(s.hasNext()) {
			se+=s.nextLine(); 
		}
		player=gson.fromJson(se, Player.class);	
	}
	public void readEnemy(String name) throws FileNotFoundException {
		File f=new File(System.getProperty("user.dir")+"\\src\\pll\\"+name+"enemy");
		Scanner s=new Scanner(f);
		String se="";
		while(s.hasNext()) {
			se+=s.nextLine(); 
		}
		enemy=gson.fromJson(se, Enemy.class);
	}
	public void SelectHero(String name) throws Exception {
		for (int i = 0; i < player.get_myheros().size(); i++) {
			String me=player.get_myheros().get(i).getname();
			if(me.contains(name)) {
				player.getMyDeck().setHeroDeck((player.get_myheros().get(i).getname()));
				break;
			}
		}
	}
	public void DeletPlayer() throws IOException {
		String ss=JOptionPane.showInputDialog(null, "enter your password");
		if(ss.equals(player.get_pass())) {
			if(JOptionPane.showConfirmDialog(null, "account deleted")==JOptionPane.OK_OPTION) {
				Logger.getinsist().deletAccount(player.get_name());
				JOptionPane.showConfirmDialog(null, "account deleted","good luck",JOptionPane.OK_OPTION);
				System.exit(0);
			}
		}else
			JOptionPane.showMessageDialog(null, "the password incorrect");
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBackCard() {
		return backCard;
	}
	public void setBackCard(String backCard) {
		this.backCard = backCard;
	}
	public String getBackBattleground() {
		return backBattleGround;
	}
	public void setBackBattleground(String backBattleground) {
		this.backBattleGround = backBattleground;
	}
	public Enemy getEnemy() {
		return enemy;
	}
	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	public Object getAttacker() {
		return attacker;
	}
	public void setAttacker(Object attacker) {
		this.attacker = attacker;
	}
	private Gamestate() throws Exception{
		initialGson();
	}
	private void initialGson() {
		GsonBuilder gsonBilder=new GsonBuilder();
		gsonBilder.registerTypeAdapter(Card.class, new AbstractAdapter<Card>());
		gsonBilder.registerTypeAdapter(Heros.class, new AbstractAdapter<Heros>());
		gsonBilder.registerTypeAdapter(HeroPower.class, new AbstractAdapter<HeroPower>());
		gsonBilder.setPrettyPrinting();
		gson=gsonBilder.create();
	}
}

