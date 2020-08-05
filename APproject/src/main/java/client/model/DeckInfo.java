package client.model;

public class DeckInfo {
	String name;
	float play;
	int won;
	int useDeck;
	float average;
	String heroName;
	String bestCard;
	public DeckInfo(String name, float play, int won, int useDeck, float average, String heroName, String bestCard) {
		super();
		this.name = name;
		this.play = play;
		this.won = won;
		this.useDeck = useDeck;
		this.average = average;
		this.heroName = heroName;
		this.bestCard = bestCard;
	}
	public DeckInfo() {
		// TODO Auto-generated constructor stub
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
