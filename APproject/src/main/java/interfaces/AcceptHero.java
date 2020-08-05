package interfaces;

import Cardspackage.Card;
import playModel.PlayerModel;

public interface AcceptHero {
public void accept(HeroVisitor v, PlayerModel p, int d, Card x);
}
