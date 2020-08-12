package server.interfaces;

import server.cardspackage.Card;
import server.playModel.PlayerModel;

public interface HeroVisitor {
	public void visitMage(PlayerModel p, int d, Card x);
	public void visitRouge(PlayerModel p, int d, Card x);
	public void visitHunter(PlayerModel p, int d, Card x);
	public void visitWarlock(PlayerModel p, int d, Card x);
	public void visitPriest(PlayerModel p, int d, Card x);
}
