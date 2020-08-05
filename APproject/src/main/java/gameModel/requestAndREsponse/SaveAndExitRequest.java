package gameModel.requestAndREsponse;

public class SaveAndExitRequest {
	int tocken;

	public SaveAndExitRequest(int tocken) {
		super();
		this.tocken = tocken;
	}

	public int getTocken() {
		return tocken;
	}
	public void setTocken(int tocken) {
		this.tocken = tocken;
	}
	
}
