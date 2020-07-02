package GAME;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Cardspackage.Cards;
import Cardspackage.Minion;
import Cardspackage.Spell;
import Cardspackage.Weapon;

public class DeckReader {
	private ArrayList< String> enemy;
	private ArrayList< String> friend;

	private Gson gson;


	public ArrayList<Cards> cardFactory(String name) {
		ArrayList< Cards> enemyDe=new ArrayList<>();
		ArrayList< Cards> friendDe=new ArrayList<>();
		gson=new Gson();

		if(name.equalsIgnoreCase("enemy")) {
			for (String string : enemy) {
				enemyDe.add(find(string));		
			}
			return enemyDe;
		}
		if (name.equalsIgnoreCase("friend")) {
			for (String string : friend) {
				friendDe.add(find(string));		
			}			
			return friendDe;
		}
		return null;
	}

	public ArrayList<String> getFriend() {
		return friend;
	}
	
	private Cards find(String name) {
		String spe="Arcane ShotAstral RiftBackstabBook of SpectersFriendly Smithgift"
				+ "Holy SmiteLearn DraconicPharaoh's BlessingPolymorphSprintStrength in NumbersSwarm of locusts";
		String mi="Big Game HunterBluegill WarriorChillwind YetiCurio CollectorDreadscale"
				+ "GruulHigh Priest Amet"
				+ "Kronx DragonhoofLeper GnomeMurloc RaiderMurloc Warleader"
				+ "Oasis Snapjaw"
				+ "SandbinderSathrovarrSea GiantSecurity RoverShieldbearer"
				+ "Swamp King DredThe Black KnightThrallmar FarseerTomb Warden";
		String we="Battle AxeBlood FuryHeavy Axe";

		File f=new File(System.getProperty("user.dir")+"\\src\\all cards\\"+name+".json");
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String se="";
		while(s.hasNext()) {
			se+=s.nextLine(); 
		}
		if(spe.contains(name)) {
			Spell x= gson.fromJson(se, Spell.class);
			return x;	
		}else if(mi.contains(name)) {
			Minion x=gson.fromJson(se, Minion.class);
			return x;
		}else {
			Weapon x= gson.fromJson(se, Weapon.class);
			return x;
		}
	}



}
