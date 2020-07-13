package passives;

import Cardspackage.Card;
import interfaces.PassiveVisitor;
import playModel.Player;

public class OffCards extends Passive{
public OffCards() {
		setDescription("Spending 1 less mana on each card");
		setName( "Off Cards");
		}
	
	@Override
	public void accept(PassiveVisitor v, Player me, Player enemy, Card x) {
		v.visitOffCard(me, enemy, x);		
	}

}
