package server.gameModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import gameModel.requestAndREsponse.RankNeed;
import server.cardspackage.Card;
import server.hero.Heros;
import server.hero.heroPower.HeroPower;


public class Rank {

	private Gson gson;
	public Rank() throws Exception {
		initialGson();
	}

	public RankNeed setRankNeed(Player player) throws JsonSyntaxException, FileNotFoundException {
		ArrayList<Player> sort=(ArrayList<Player>) sortPlayer().clone();
		ArrayList<String > top=new ArrayList<>();
		ArrayList<String > ME=new ArrayList<>();
		int me=-2;
		int size=sort.size();
		for (int i=0;i<size;i++) {
			if(sort.get(i).get_name().equals(player.get_name()))
				me=i;
			String result="             "+ (i+1)+"                                                                                                  "+sort.get(i).get_name()+"                                                                                                           "+sort.get(i).getCup();
			top.add(result);
			if(i==9)
				break;
		}
		if(size<=10 || (me>0 && me<=5)) {
		}else if((me>5 && me<=10)) {
			for (int i = me-5; i < 5+me; i++) { 
				String result="            "+ (i+1)+"                                                                                             "+sort.get(i).get_name()+"                                                                                                           "+sort.get(i).getCup();
				ME.add(result); 
			}
		}else {
			for (int i = 10; i < size; i++) {
				if(sort.get(i).get_name().equals(player.get_name()))
					me=i;
			}
			if(me!=-2)
			for (int i = me-5; i < 5+me; i++) {
				String result="            "+ (i+1)+"                                                                                               "+sort.get(i).get_name()+"                                                                                                          "+sort.get(i).getCup();
				ME.add(result);  
			}
		}
		return new RankNeed(top, ME);
	}
	public ArrayList<Player> sortPlayer() throws JsonSyntaxException, FileNotFoundException {
		ArrayList<Player> array=readAll();
		Player [] players=new Player[array.size()];
		int x=0;
		for(Player s : array) {
			players[x]=s;
			x++;
		}
		for(int i=0;i<array.size()-1;i++) {
			for(int j=array.size()-i-1;j>0;j--) {
				if(players[j].getCup()>players[j-1].getCup()) {	
					Player y=players[j-1];
					players[j-1]=players[j];
					players[j]=y;
				}
			}
		}
		ArrayList<Player> su=new ArrayList<>();
		for(Player s : players) {
			su.add(s);
		}
		return su;
	}
	private void initialGson() {
		GsonBuilder gsonBilder=new GsonBuilder();
		gsonBilder.registerTypeAdapter(Card.class, new AbstractAdapter<Card>());
		gsonBilder.registerTypeAdapter(Heros.class, new AbstractAdapter<Heros>());
		gsonBilder.registerTypeAdapter(HeroPower.class, new AbstractAdapter<HeroPower>());
		gsonBilder.setPrettyPrinting();
		gson=gsonBilder.create();
	}
	private String readFileString(File f) throws FileNotFoundException {
		Scanner sca=new Scanner(f);
		String t1="";
		while(sca.hasNext()) {
			t1+=sca.nextLine();
		}
		sca.close();
		return t1;
	}
	private ArrayList<Player> readAll() throws JsonSyntaxException, FileNotFoundException {
		ArrayList<Player> x= new ArrayList<>();
		File fa=new File(System.getProperty("user.dir")+"\\src\\main\\java\\pll");
		File[] dirr=fa.listFiles();
		if(dirr!=null) {
			for(File ch:dirr) {
				if(!ch.getName().contains("enemy")) {
					x.add(gson.fromJson(readFileString(ch), Player.class));
				}
			}
		}		
		return x;
	}
}
