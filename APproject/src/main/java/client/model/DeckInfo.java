package client.model;

public class DeckInfo {
	String name;
	float play;
	int won;
	int useDeck;
	float average;
	String heroName;
	String bestCard;
	int size;
	int cup;

	
	public DeckInfo(String name, float play, int won, int useDeck, float average, String heroName, String bestCard,
			int size, int cup) {
		super();
		this.name = name;
		this.play = play;
		this.won = won;
		this.useDeck = useDeck;
		this.average = average;
		this.heroName = heroName;
		this.bestCard = bestCard;
		this.size = size;
		this.cup = cup;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public DeckInfo() {
		// TODO Auto-generated constructor stub
	}
	public int getCup() {
		return cup;
	}

	public void setCup(int cup) {
		this.cup = cup;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPlay() {
		return play;
	}

	public void setPlay(float play) {
		this.play = play;
	}

	public int getWon() {
		return won;
	}

	public void setWon(int won) {
		this.won = won;
	}

	public int getUseDeck() {
		return useDeck;
	}

	public void setUseDeck(int useDeck) {
		this.useDeck = useDeck;
	}

	public float getAverage() {
		return average;
	}

	public void setAverage(int average) {
		this.average = average;
	}

	public String getHeroName() {
		return heroName;
	}

	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}

	public String getBestCard() {
		return bestCard;
	}
	
	public void setBestCard(String bestCard) {
		this.bestCard = bestCard;
	}
	
}
