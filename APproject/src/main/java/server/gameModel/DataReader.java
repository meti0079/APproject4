package server.gameModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import server.cardspackage.Card;
import server.hero.Heros;
import server.hero.heroPower.HeroPower;

public class DataReader {
	public static DataReader dataReader;
	
	private SessionFactory sessionFactory=buildSessionFactory();
	private	Gson gson;

	public void save(Object player) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(player);
		session.getTransaction().commit();
		session.close();
	}
	public<T> T loud(Class<T> classt,Object name) {
		Session session=sessionFactory.openSession();
		T t=(T) session.get(classt, (Serializable)name);
		session.close();
		return t;
	}
	public<T> List<T> loudAll(Class<T> classt) {
		Session session=sessionFactory.openSession();
		return session.createQuery("from "+classt.getName(),classt).getResultList();
	}
	public void update(Player player) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.update(player);
		session.getTransaction().commit();
		session.close();
	}
	public void delete(Player player) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.delete(player);
		session.getTransaction().commit();
		session.close();
	}
	

	private DataReader(){
		initialGson();
	}
	
	public static DataReader initial() {
		if(dataReader==null)
			dataReader=new DataReader();
		return dataReader;
	}
	
	public String toString(Player player) {
		String na=System.getProperty("user.dir")+"\\src\\main\\java\\pll\\"+player.get_name();
		return na;
	}
	public void makeProfile(Enemy enemy, Player player) throws IOException {
		save(player);
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
//	public Player readPlayer(String name) throws IOException {
//		File f=new File(System.getProperty("user.dir")+"\\src\\main\\java\\pll\\"+name);
//		Scanner s=new Scanner(f);
//		String se="";
//		while(s.hasNext()) {
//			se+=s.nextLine(); 
//		}
//		Player player=gson.fromJson(se, Player.class);	
//		player.setTocken(new SecureRandom().nextInt());
//		return player;
//	}
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
//	public void DeletPlayer(Player player) throws IOException {
//		String ss=JOptionPane.showInputDialog(null, "enter your password");
//		if(ss.equals(player.get_pass())) {
//			if(JOptionPane.showConfirmDialog(null, "account deleted")==JOptionPane.OK_OPTION) {
//				Logger.getinsist().deletAccount(player.get_name());
//				JOptionPane.showConfirmDialog(null, "account deleted","good luck",JOptionPane.OK_OPTION);
//				System.exit(0);
//			}
//		}else
//			JOptionPane.showMessageDialog(null, "the password incorrect");
//	}	
	private void initialGson() {
		GsonBuilder gsonBilder=new GsonBuilder();
		gsonBilder.registerTypeAdapter(Card.class, new AbstractAdapter<Card>());
		gsonBilder.registerTypeAdapter(Heros.class, new AbstractAdapter<Heros>());
		gsonBilder.registerTypeAdapter(HeroPower.class, new AbstractAdapter<HeroPower>());
		gsonBilder.setPrettyPrinting();
		gson=gsonBilder.create();
	}
	
	private SessionFactory buildSessionFactory() {
		final ServiceRegistry registry=new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sessionFactory=new MetadataSources(registry).buildMetadata().buildSessionFactory();
		return sessionFactory;
	}	
}

