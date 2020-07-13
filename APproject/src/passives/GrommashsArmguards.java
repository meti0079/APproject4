package passives;

import Cardspackage.Card;
import interfaces.PassiveVisitor;
import playModel.Player;

public class GrommashsArmguards extends Passive{
	public GrommashsArmguards() {
		setName("Grommash's Armguards");
		setDescription("Your weapons cost (1).");

	}

	@Override
	public void accept(PassiveVisitor v, Player me, Player enemy, Card x) {
		v.visitGrommashsArmguards(me, enemy, x);
	}


}
