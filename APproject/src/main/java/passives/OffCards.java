package passives;

import Cardspackage.Card;
import interfaces.PassiveVisitor;
import playModel.PlayerModel;

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
