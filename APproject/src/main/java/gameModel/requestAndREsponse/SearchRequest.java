package gameModel.requestAndREsponse;

public class SearchRequest {
	int tocken;
	String message;
	public SearchRequest(int tocken, String message) {
		super();
		this.tocken = tocken;
		this.message = message;
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
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
