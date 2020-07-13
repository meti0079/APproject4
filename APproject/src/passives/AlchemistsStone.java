package passives;

import Cardspackage.Card;
import interfaces.PassiveVisitor;
import playModel.Player;

public class AlchemistsStone extends Passive{

	public AlchemistsStone() {
	setDescription("After you play an odd-Cost card"+
			"\n reduce the Cost of cards in your hand by (1).");
	setName("Alchemist's Stone");
	}

	@Override
	public void accept(PassiveVisitor v, Player me, Player enemy, Card x) {
		v.visitAlchemistsStone(me, enemy, x);
	}


}