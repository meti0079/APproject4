package server.passives;

import javax.persistence.Entity;

import server.cardspackage.Card;
import server.interfaces.PassiveVisitor;
import server.playModel.PlayerModel;
@Entity
public class OffCards extends Passive{
public OffCards() {
		setDescription("Spending 1 less mana on each card");
		setName( "Off Cards");
		}
	
	@Override
	public void accept(PassiveVisitor v, PlayerModel me, PlayerModel enemy, Card x) {
		v.visitOffCard(me, enemy, x);		
	}

}
