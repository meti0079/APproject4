package gameModel.requestAndREsponse;

public class Kickrequest {
	String name;
	int tocken;
	public Kickrequest(String name, int tocken) {
		super();
		this.name = name;
		this.tocken = tocken;
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

}
