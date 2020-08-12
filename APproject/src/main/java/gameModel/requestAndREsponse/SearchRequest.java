package gameModel.requestAndREsponse;

public class SearchRequest {
	int tocken;
	String message;
	public SearchRequest(int tocken, String message) {
		super();
		this.tocken = tocken;
		this.message = message;
	}

	public int getTocken() {
		return tocken;
	}
	public String getMessage() {
		return message;
	}

}
