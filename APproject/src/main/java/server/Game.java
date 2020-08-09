package server;

import java.io.IOException;

import game.Logger;
import playModel.Mapper;
import playModel.PlayerModel;

public class Game {
	User user1;
	User user2;
	PlayerModel me;
	PlayerModel enemy;
	Mapper mapper;
	int round=60;
	private Logger log;
	String text;
	public Game(User user) {
		try {
			log=Logger.getinsist();
			mapper=new Mapper(user1.getGameState(),user,user);
			user1=user;
			user2=user;
			me=mapper.readMe(user.getGameState(),user);
			enemy=mapper.readEnemy(user.getGameState(), user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Game(User user1, User user2) {
		try {
			log=Logger.getinsist();
			mapper=new Mapper(user1.getGameState(),user1,user2);
			this.user1=user1;
			this.user2=user2;
			me=mapper.readMe(user1.getGameState(),user1);
			enemy=mapper.readEnemy(user2.getGameState(), user2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}















	private void firstRound() {
		round--;
		mapper.manaSet(me, enemy);
	}
	protected void nextTurn(int turn) throws Exception {
		if(round%2==turn) {
		if(round==60) {
			initialPassive(enemy);
			firstRound();
			return;
		}
		try {
			log.log(game.getPlayer().get_name(), "clicked end turn ", "");
			mapper.isFinished(me, enemy,turn,text);
		} catch (Exception e) {}
		mapper.readDeck(me, enemy,user1.getGameState(),user1,user2);
		if(round%2==1) {
			player1Turn();
		}else {
			player2Turn();
		}
		round--;
		mapper.manaSet(me, enemy);
		
		
		
		
		
		
		
		}
		
		updatePanel();
	}
	
	private void player1Turn() {
		mapper.nextTurn(me, enemy);
		addToHand(me.getTurn()+1);
	}	
	private void player2Turn() {
		mapper.nextTurn(enemy, me);
		addToHand(enemy.getTurn()+1);
	}
	private void addToHand(int turn) {	
		try {
			mapper.readDeck(me, enemy,user1.getGameState(),user1,user2);
			if(turn==1) {
				mapper.addToHand(me, enemy,user1.getGameState());
				log.log( me.getName(), me.getName(), "summon card : "+me.getHand().get(me.getHand().size()-1).get_Name());
			}else {
				mapper.addToHand(enemy, me,user1.getGameState());
				log.log(enemy.getName(), enemy.getName(), "sdraw card : "+enemy.getHand().get(enemy.getHand().size()-1).get_Name());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}











































	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	public User getUser2() {
		return user2;
	}
	public void setUser2(User user2) {
		this.user2 = user2;
	}
	public PlayerModel getMe() {
		return me;
	}
	public void setMe(PlayerModel me) {
		this.me = me;
	}
	public PlayerModel getEnemy() {
		return enemy;
	}
	public void setEnemy(PlayerModel enemy) {
		this.enemy = enemy;
	}
	public Mapper getMapper() {
		return mapper;
	}
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
}
