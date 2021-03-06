package server.passives;

import javax.persistence.Entity;

import server.cardspackage.Card;
import server.interfaces.PassiveVisitor;
import server.playModel.PlayerModel;
@Entity
public class Caltrops extends Passive{
public Caltrops() {
setName("Caltrops");
setDescription("After your opponent plays a minion, deal 1 damage to it.");
}

@Override
public void accept(PassiveVisitor v, PlayerModel me, PlayerModel enemy, Card x) {
	v.visitCaltrops(me, enemy, x);
}


}
