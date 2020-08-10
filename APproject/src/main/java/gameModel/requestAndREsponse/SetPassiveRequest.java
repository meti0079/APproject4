package gameModel.requestAndREsponse;

public class SetPassiveRequest {
	String passiveName;
	int tocken;
	public SetPassiveRequest(String passiveName, int tocken) {
		this.passiveName = passiveName;
		this.tocken = tocken;
	}
	public String getPassiveName() {
		return passiveName;
	}
	public void setPassiveName(String passiveName) {
		this.passiveName = passiveName;
	}
	public int getTocken() {
		return tocken;
	}
	public void setTocken(int tocken) {
		this.tocken = tocken;
	}

}
