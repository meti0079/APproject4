package interfaces;

import Cardspackage.Card;
import playModel.Player;

public interface HeroVisitor {
	public void visitMage(Player p, int d, Card x);
	public void visitRouge(Player p, int d, Card x);
	public void visitHunter(Player p, int d, Card x);
	public void visitWarlock(Player p, int d, Card x);
	public void visitPriest(Player p, int d, Card x);
}
