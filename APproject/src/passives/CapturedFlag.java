package passives;

import Cardspackage.Card;
import interfaces.PassiveVisitor;
import playModel.Player;

public class CapturedFlag extends Passive{

	public CapturedFlag() {
setName( "Captured Flag");
setDescription( "Your minions have +1/+1.");


	}

	@Override
	public void accept(PassiveVisitor v, Player me, Player enemy, Card x) {
		v.visitCapturedFlag(me, enemy, x);
	}


}
