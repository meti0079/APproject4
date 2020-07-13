package passives;

import Cardspackage.Card;
import interfaces.PassiveVisitor;
import playModel.Player;

public class Stargazing extends Passive{
	public Stargazing() {
		setName("Stargazing");
		setDescription("You can use your Hero Power twice each turn. It costs (1) less.");
	}

	@Override
	public void accept(PassiveVisitor v, Player me, Player enemy, Card x) {
		v.visitStargazing(me, enemy, x);
	}


}
