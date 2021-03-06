package server.gameModel;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.cardspackage.Card;
import server.hero.Heros;
import server.hero.heroPower.HeroPower;

public class DeckReader {
	private ArrayList< String> enemy;
	private ArrayList< String> friend;
	private String reward;
//	private Gson gson;

	public ArrayList<Card> cardFactory(String name) {
		ArrayList< Card> enemyDe=new ArrayList<>();
		ArrayList< Card> friendDe=new ArrayList<>();
//		GsonBuilder gsonBilder=new GsonBuilder();
//		gsonBilder.registerTypeAdapter(Card.class, new AbstractAdapter<Card>());
//		gsonBilder.registerTypeAdapter(Heros.class, new AbstractAdapter<Heros>());
//		gsonBilder.registerTypeAdapter(HeroPower.class, new AbstractAdapter<HeroPower>());
//		gsonBilder.setPrettyPrinting();
//		gson=gsonBilder.create();
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
	public  Card find(String name) {
//		GsonBuilder gsonBilder=new GsonBuilder();
//		gsonBilder.registerTypeAdapter(Card.class, new AbstractAdapter<Card>());
//		gsonBilder.registerTypeAdapter(Heros.class, new AbstractAdapter<Heros>());
//		gsonBilder.registerTypeAdapter(HeroPower.class, new AbstractAdapter<HeroPower>());
//		gsonBilder.setPrettyPrinting();
//		gson=gsonBilder.create();
//		try {
//		File f=new File(System.getProperty("user.dir")+"\\src\\main\\java\\all cards\\"+name+".json");
//		Scanner s = new Scanner(f);
//		String se="";
//		while(s.hasNext()) {
//			se+=s.nextLine(); 
//		}
//		Card x= gson.fromJson(se, Card.class);
//		return x;	
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		return DataReader.initial().loud(Card.class, name);
	}
	public String getReward() {
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
	}

}

//		String spe="Arcane ShotAstral RiftBackstabBook of SpectersFriendly Smithgift"
//				+ "Holy SmiteLearn DraconicPharaoh's BlessingPolymorphSprintStrength in NumbersSwarm of locusts";
//		String mi="Big Game HunterBluegill WarriorChillwind YetiCurio CollectorDreadscale"
//				+ "GruulHigh Priest Amet"
//				+ "Kronx DragonhoofLeper GnomeMurloc RaiderMurloc Warleader"
//				+ "Oasis Snapjaw"
//				+ "SandbinderSathrovarrSea GiantSecurity RoverShieldbearer"
//				+ "Swamp King DredThe Black KnightThrallmar FarseerTomb Warden";
//		String we="Battle AxeBlood FuryHeavy Axe";