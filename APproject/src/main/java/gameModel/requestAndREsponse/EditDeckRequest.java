package gameModel.requestAndREsponse;

public class EditDeckRequest {
	int tocken;
	String heroName;
	String deckName;
	public EditDeckRequest() {
	}
	public EditDeckRequest(int tocken, String heroName, String deckName) {
		super();
		this.tocken = tocken;
		this.heroName = heroName;
		this.deckName = deckName;
	}
	public int getTocken() {
		return tocken;
	}
	public void setTocken(int tocken) {
		this.tocken = tocken;
	}
	public String getHeroName() {
		return heroName;
	}
	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}
	public String getDeckName() {
		return deckName;
	}
	public void setDeckName(String deckName) {
		this.deckName = deckName;
	} 
}
