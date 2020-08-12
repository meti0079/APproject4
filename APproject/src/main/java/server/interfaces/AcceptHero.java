package server.interfaces;

import server.cardspackage.Card;
import server.playModel.PlayerModel;

public interface AcceptHero {
public void accept(HeroVisitor v, PlayerModel p, int d, Card x);
}
