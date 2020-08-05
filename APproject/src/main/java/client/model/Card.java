package client.model;

public class Card {
	private int mana;
	private String name;
	private String rarity;
	private String cardClass;
	private String description;
	private String type;
	
	
	public Card(int mana, String name, String rarity, String cardClass, String description, String type) {
		this.mana = mana;
		this.name = name;
		this.rarity = rarity;
		this.cardClass = cardClass;
		this.description = description;
		this.type = type;
	}
	public Card() {
		}
	public int getMana() {
		return mana;
	}
	public void setMana(int mana) {
		this.mana = mana;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRarity() {
		return rarity;
	}
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	public String getCardClass() {
		return cardClass;
	}
	public void setCardClass(String cardClass) {
		this.cardClass = cardClass;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int gemCost() {
		if(this.getRarity().equalsIgnoreCase("rare"))
			return 2;
		if(this.getRarity().equalsIgnoreCase("epic"))
			return 3;
		if(this.getRarity().equalsIgnoreCase("legendary"))
			return 4;
		if(this.getRarity().equalsIgnoreCase("common"))
			return 1;
		return 1;
	}
	
	
}
