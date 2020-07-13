package passives;

import Cardspackage.Card;
import interfaces.PassiveVisitor;
import playModel.Player;
public class ManaJump extends Passive{
public ManaJump() {
	
			setDescription("you statr with 1 more mana ");
			setName( "Mana Jump");
			}
		

		@Override
		public void accept(PassiveVisitor v, Player me, Player enemy, Card x) {
			v.visitManaJump(me, enemy, x);
		}

	}


