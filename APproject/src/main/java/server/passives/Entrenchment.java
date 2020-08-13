package server.passives;

import javax.persistence.Entity;

import server.cardspackage.Card;
import server.interfaces.PassiveVisitor;
import server.playModel.PlayerModel;
@Entity
public class Entrenchment extends Passive{
public Entrenchment() {
setName( "Entrenchment");
setDescription("Your minions have +2 Health.");

}

@Override
public void accept(PassiveVisitor v, PlayerModel me, PlayerModel enemy, Card x) {
	v.visitEntrenchment(me, enemy, x);
}


}
