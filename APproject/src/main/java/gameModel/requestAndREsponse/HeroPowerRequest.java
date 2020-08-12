package gameModel.requestAndREsponse;

public class HeroPowerRequest {
	int tocken;
	int x,y;
	public HeroPowerRequest( int tocken, int x, int y) {
		this.tocken = tocken;
		this.x = x;
		this.y = y;
	}
	public int getTocken() {
		return tocken;
	}
	public void setTocken(int tocken) {
		this.tocken = tocken;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
