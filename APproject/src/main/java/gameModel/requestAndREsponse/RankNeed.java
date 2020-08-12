package gameModel.requestAndREsponse;

import java.util.ArrayList;

public class RankNeed {
	ArrayList<String> top;
	ArrayList<String> me;
	public RankNeed() {
	}
	public ArrayList<String> getTop() {
		return top;
	}
	public void setTop(ArrayList<String> top) {
		this.top = top;
	}
	public ArrayList<String> getMe() {
		return me;
	}
	public void setMe(ArrayList<String> me) {
		this.me = me;
	}
	public RankNeed(ArrayList<String> top, ArrayList<String> me) {
		super();
		this.top = top;
		this.me = me;
	}


}
