package gameModel.requestAndREsponse;

public class StartMatchRequest {
	private int tocken;
	private String state;
	public StartMatchRequest(int tocken, String state) {
		super();
		this.tocken = tocken;
		this.state = state;
	}
	public StartMatchRequest() {
	}
	public int getTocken() {
		return tocken;
	}
	public void setTocken(int tocken) {
		this.tocken = tocken;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
