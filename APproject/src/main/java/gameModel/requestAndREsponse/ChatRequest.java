package gameModel.requestAndREsponse;

public class ChatRequest {
	String messag;
	int tocken;
	public ChatRequest(String messag, int tocken) {
		super();
		this.messag = messag;
		this.tocken = tocken;
	}

	public String getMessag() {
		return messag;
	}
	public void setMessag(String messag) {
		this.messag = messag;
	}
	public int getTocken() {
		return tocken;
	}
	public void setTocken(int tocken) {
		this.tocken = tocken;
	}

}
