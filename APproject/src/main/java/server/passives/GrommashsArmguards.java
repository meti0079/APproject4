package server.passives;

import javax.persistence.Entity;

import server.cardspackage.Card;
import server.interfaces.PassiveVisitor;
import server.playModel.PlayerModel;
@Entity
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
