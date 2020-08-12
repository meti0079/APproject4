package server.passives;

import server.cardspackage.Card;
import server.interfaces.PassiveVisitor;
import server.playModel.PlayerModel;

public class CapturedFlag extends Passive{

	public CapturedFlag() {
setName( "Captured Flag");
setDescription( "Your minions have +1/+1.");


	}

	@Override
	public void accept(PassiveVisitor v, PlayerModel me, PlayerModel enemy, Card x) {
		v.visitCapturedFlag(me, enemy, x);
	}


}
