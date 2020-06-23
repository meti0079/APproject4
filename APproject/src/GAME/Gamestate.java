package GAME;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Cardspackage.Cards;



public class Gamestate {
	Scanner sca=new Scanner(System.in);
	private	Players player;
	private Store store;
	private	Gson gson;;
	public static Gamestate game;
	private ArrayList<Passive> passives;
	private String backCard="ca.png";
	private String backBattleGround="nattle.jpg";
	private Passive playPassive; 


	public Passive getPlayPassive() {
		return playPassive;
	}

	public void setPlayPassive(Passive playPassive) {
		this.playPassive = playPassive;
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

	private Gamestate() throws Exception{
		passives=new ArrayList<>();
		GsonBuilder gsonBilder=new GsonBuilder();
		gsonBilder.registerTypeAdapter(Cards.class, new AbstractAdapter<Cards>());
		gsonBilder.setPrettyPrinting();
		gson=gsonBilder.create();
	}

	public ArrayList<Passive> getPassives() {
		return passives;
	}

	public void addPassives(Passive a) {
		passives.add(a);
	}
	public static Gamestate getinsist() throws Exception {
		if(game==null) {
			game=new Gamestate();
		}
		return game;
	}
	public Players getPlayer() {
		return player;
	}

	public void setPlayer(Players player) {	
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
		player=gson.fromJson(se, Players.class);	
		
		//		lg.log(player.get_name(), "sign in ", "");
	}
	public void SelectHero(String name) throws Exception {
		for (int i = 0; i < player.get_myheros().size(); i++) {
			String me=player.get_myheros().get(i).getname();
			if(me.contains(name)) {
				player.getMyDeck().setHeroDeck((player.get_myheros().get(i).getname()));
				break;
			}
		}
		//	lg.log(player.get_name(), "select hero", " selected"+player.get_hero().getname());
	}


}
