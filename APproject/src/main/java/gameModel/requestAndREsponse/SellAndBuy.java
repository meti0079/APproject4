package gameModel.requestAndREsponse;

public class SellAndBuy {
String name;
int tocken;
/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
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
public SellAndBuy(String name, int tocken) {
	super();
	this.name = name;
	this.tocken = tocken;
}


}
