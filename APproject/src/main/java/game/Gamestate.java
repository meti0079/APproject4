package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Scanner;
import javax.swing.JOptionPane;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Cardspackage.Card;
import hero.Heros;
import hero.heroPower.HeroPower;

public class Gamestate {
	private	Gson gson;

	public Gamestate() throws Exception{
		initialGson();
	}
	public String toString(Player player) {
		String na=System.getProperty("user.dir")+"\\src\\main\\java\\pll\\"+player.get_name();
		return na;
	}
	public void makeProfile(Player player , Enemy enemy) throws IOException {
		player.setTocken(0);
		FileWriter f=new FileWriter(toString(player));
		String se=gson.toJson(player);
		f.write(se);
		f.close();
		FileWriter ff=new FileWriter(System.getProperty("user.dir")+"\\src\\main\\java\\pll\\"+player.get_name()+"enemy");
		String ss=gson.toJson(enemy);
		ff.write(ss);
		ff.close();
	}
	public Boolean checkName(String name ,String pass) throws IOException {
		File file=new File(System.getProperty("user.dir")+"\\src\\main\\java\\PLAYERSNAME\\playersname.txt");
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
		File file=new File(System.getProperty("user.dir")+"\\src\\main\\java\\PLAYERSNAME\\playersname.txt");
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
		if( !checkValid(name)) {
			FileWriter file=new FileWriter(System.getProperty("user.dir")+"\\src\\main\\java\\PLAYERSNAME\\playersname.txt",true);
			file.write(name+"\n");
			file.write(pass+"\n");
			file.close();			
		}
	}
	public Player readPlayer(String name) throws IOException {
		File f=new File(System.getProperty("user.dir")+"\\src\\main\\java\\pll\\"+name);
		Scanner s=new Scanner(f);
		String se="";
		while(s.hasNext()) {
			se+=s.nextLine(); 
		}
		Player player=gson.fromJson(se, Player.class);	
		player.setTocken(new SecureRandom().nextInt());
		return player;
	}
	public Enemy readEnemy(String name) throws FileNotFoundException {
		File f=new File(System.getProperty("user.dir")+"\\src\\main\\java\\pll\\"+name+"enemy");
		Scanner s=new Scanner(f);
		String se="";
		while(s.hasNext()) {
			se+=s.nextLine(); 
		}
		Enemy enemy=gson.fromJson(se, Enemy.class);
		return enemy;
	}
	public void SelectHero(Player player,String name) throws Exception {
		for (int i = 0; i < player.get_myheros().size(); i++) {
			String me=player.get_myheros().get(i).getname();
			if(me.contains(name)) {
				player.getMyDeck().setHeroDeck(player,(player.get_myheros().get(i).getname()));
				break;
			}
		}
	}
	public void DeletPlayer(Player player) throws IOException {
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
	private void initialGson() {
		GsonBuilder gsonBilder=new GsonBuilder();
		gsonBilder.registerTypeAdapter(Card.class, new AbstractAdapter<Card>());
		gsonBilder.registerTypeAdapter(Heros.class, new AbstractAdapter<Heros>());
		gsonBilder.registerTypeAdapter(HeroPower.class, new AbstractAdapter<HeroPower>());
		gsonBilder.setPrettyPrinting();
		gson=gsonBilder.create();
	}
}

