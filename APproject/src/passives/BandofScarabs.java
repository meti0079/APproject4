package passives;

import Cardspackage.Card;
import interfaces.PassiveVisitor;
import playModel.Player;

public class BandofScarabs extends Passive{
public BandofScarabs() {
setDescription("Enemy minions have -1 Attack");
setName( "Band of Scarabs");


}

@Override
public void accept(PassiveVisitor v, Player me, Player enemy, Card x) {
	v.visitBandofScarabs(me, enemy, x);
}


}
