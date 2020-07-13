package passives;

import Cardspackage.Card;
import interfaces.PassiveVisitor;
import playModel.Player;

public class Caltrops extends Passive{
public Caltrops() {
setName("Caltrops");
setDescription("After your opponent plays a minion, deal 1 damage to it.");
}

@Override
public void accept(PassiveVisitor v, Player me, Player enemy, Card x) {
	v.visitCaltrops(me, enemy, x);
}


}
