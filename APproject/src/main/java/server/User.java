package server;
import java.net.SocketAddress;

import server.gameModel.Enemy;
import server.gameModel.Player;

public class User {
	private String gameState;
	private	Player player;
	private String backCard="ca1.png";
	private String backBattleGround="nattle1.jpg";
	private Enemy enemy;
	private int potr;
	private SocketAddress address;
	public User(Player player, Enemy enemy, int potr, SocketAddress address) {
		this.player = player;
		this.enemy = enemy;
		this.potr = potr;
		this.address = address;
	}
	public String getGameState() {
		return gameState;
	}
	public void setGameState(String gameState) {
		this.gameState = gameState;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public String getBackCard() {
		return backCard;
	}
	public void setBackCard(String backCard) {
		this.backCard = backCard;
	}
	public String getBackBattleGround() {
		return backBattleGround;
	}
	public void setBackBattleGround(String backBattleGround) {
		this.backBattleGround = backBattleGround;
	}
	public Enemy getEnemy() {
		return enemy;
	}
	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	public int getPotr() {
		return potr;
	}
	public void setPotr(int potr) {
		this.potr = potr;
	}
	public SocketAddress getAddress() {
		return address;
	}
	public void setAddress(SocketAddress address) {
		this.address = address;
	}
}
