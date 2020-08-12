package gameModel.requestAndREsponse;

public class ChangeBattlegroundThem {

	private int tocken;
	private String name;

	public ChangeBattlegroundThem() {
	}

	public ChangeBattlegroundThem(int tocken, String name) {
		super();
		this.tocken = tocken;
		this.name = name;
	}

	public int getTocken() {
		return tocken;
	}

	public void setTocken(int tocken) {
		this.tocken = tocken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

}
