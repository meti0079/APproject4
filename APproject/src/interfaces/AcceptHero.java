package interfaces;

import Cardspackage.Card;
import playModel.Player;

public interface AcceptHero {
public void accept(HeroVisitor v, Player p, int d, Card x);
}
