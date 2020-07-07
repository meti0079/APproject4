package playModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import GAME.DeckReader;
import GAME.Decks;
import GAME.Gamestate;

public class Mapper {

	
	
	
	private DeckReader deckReader;
	private static Mapper map;
private Gamestate game;
	private Mapper() throws Exception {
		game=Gamestate.getinsist();
		readFromFile();
	}
	public static Mapper getinsist() throws Exception {
		if (map==null) {
			map=new Mapper();
		}
		return map;
	}
	public void addToHand(Player p) {
		
		
		
		
	}
	
	public Player readMe() throws Exception {
		if(game.getState().equals("enemy")) {
			Player x=new Player(game.getPlayer().getMyDeck(), 0);
			return x;
		}else if(game.getState().equals("Deck")) {
			Decks s=new  Decks();
			s.setHeroDeck("mage");
			s.setDeck(deckReader.cardFactory("friend"));
			Player x= new Player(s, 0);
			return x;
		}else {
			return null;
		}
		
	}
	
	private void readFromFile() throws FileNotFoundException {
		File f1=new File("C:\\Users\\MohammadMehdi\\git\\repository2\\APproject\\src\\main\\deckreader.json");
		Scanner s=new Scanner(f1);
		String se="";
		while(s.hasNext()) {
			se+=s.nextLine(); 
		}
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		deckReader=gson.fromJson(se, DeckReader.class);	
	}
	public Player readEnemy() throws Exception {
		if(game.getState().equals("enemy")) {
			Player x=new Player(game.getEnemy().getEnemyDeck(), 1);
			return x;
		}else if(game.getState().equals("Deck")) {
			Decks s=new  Decks();
			s.setHeroDeck("mage");
			s.setDeck(deckReader.cardFactory("enemy"));
			Player x= new Player(s, 1);
			return x;
		}else {
			return null;
		}	}
}
