package passives;

import Cardspackage.Card;
import interfaces.PassiveVisitor;
import playModel.PlayerModel;

public class GrommashsArmguards extends Passive{
	public GrommashsArmguards() {
		setName("Grommash's Armguards");
		setDescription("Your weapons cost (1).");

	}

	@Override
	public void accept(PassiveVisitor v, PlayerModel me, PlayerModel enemy, Card x) {
		v.visitGrommashsArmguards(me, enemy, x);
	}


}
