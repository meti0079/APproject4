package server.passives;

import server.cardspackage.Card;
import server.interfaces.PassiveVisitor;
import server.playModel.PlayerModel;

public class BandofScarabs extends Passive{
public BandofScarabs() {
setDescription("Enemy minions have -1 Attack");
setName( "Band of Scarabs");


}

@Override
public void accept(PassiveVisitor v, PlayerModel me, PlayerModel enemy, Card x) {
	v.visitBandofScarabs(me, enemy, x);
}


}
