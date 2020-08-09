package gameModel.requestAndREsponse;

public class NextTurnRequest {
	private int tocken;


	public NextTurnRequest(int tocken) {
		this.tocken = tocken;
	}
	public int getTocken() {
		return tocken;
	}
	public void setTocken(int tocken) {
		this.tocken = tocken;
	}


}
