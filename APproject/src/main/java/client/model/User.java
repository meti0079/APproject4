package client.model;

public class User {
	String name;
	int tocken;
	int gem;
	int cup;
	int turn;

	public User(String name, int tocken, int gem, int cup) {
		super();
		this.name = name;
		this.tocken = tocken;
		this.gem = gem;
		this.cup = cup;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTocken() {
		return tocken;
	}
	public void setTocken(int tocken) {
		this.tocken = tocken;
	}
	public int getGem() {
		return gem;
	}
	public void setGem(int gem) {
		this.gem = gem;
	}
	public int getCup() {
		return cup;
	}
	public void setCup(int cup) {
		this.cup = cup;
	}	
}
