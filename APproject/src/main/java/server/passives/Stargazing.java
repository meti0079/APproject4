package server.passives;

import javax.persistence.Entity;

import server.cardspackage.Card;
import server.interfaces.PassiveVisitor;
import server.playModel.PlayerModel;
@Entity
public class Stargazing extends Passive{
	public Stargazing() {
		setName("Stargazing");
		setDescription("You can use your Hero Power twice each turn. It costs (1) less.");
	}

	@Override
	public void accept(PassiveVisitor v, PlayerModel me, PlayerModel enemy, Card x) {
		v.visitStargazing(me, enemy, x);
	}


}
