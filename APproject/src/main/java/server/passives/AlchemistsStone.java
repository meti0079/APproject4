package server.passives;

import javax.persistence.Entity;

import server.cardspackage.Card;
import server.interfaces.PassiveVisitor;
import server.playModel.PlayerModel;
@Entity
public class AlchemistsStone extends Passive{

	public AlchemistsStone() {
	setDescription("After you play an odd-Cost card"+
			"\n reduce the Cost of cards in your hand by (1).");
	setName("Alchemist's Stone");
	}

	@Override
	public void accept(PassiveVisitor v, PlayerModel me, PlayerModel enemy, Card x) {
		v.visitAlchemistsStone(me, enemy, x);
	}


}