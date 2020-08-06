package gameModel.requestAndREsponse;

public class EditDeckRequest {
	int tocken;
	String heroName;
	String deckName;
	public EditDeckRequest() {
		// TODO Auto-generated constructor stub
	}
	public EditDeckRequest(int tocken, String heroName, String deckName) {
		super();
		this.tocken = tocken;
		this.heroName = heroName;
		this.deckName = deckName;
	}
	/**
	 * @return the tocken
	 */
	public int getTocken() {
		return tocken;
	}
	/**
	 * @param tocken the tocken to set
	 */
	public void setTocken(int tocken) {
		this.tocken = tocken;
	}
	/**
	 * @return the heroName
	 */
	public String getHeroName() {
		return heroName;
	}
	/**
	 * @param heroName the heroName to set
	 */
	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}
	/**
	 * @return the deckName
	 */
	public String getDeckName() {
		return deckName;
	}
	/**
	 * @param deckName the deckName to set
	 */
	public void setDeckName(String deckName) {
		this.deckName = deckName;
	} 
}
