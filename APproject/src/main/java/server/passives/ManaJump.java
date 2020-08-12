package server.passives;

import server.cardspackage.Card;
import server.interfaces.PassiveVisitor;
import server.playModel.PlayerModel;
public class ManaJump extends Passive{
public ManaJump() {
	
			setDescription("you statr with 1 more mana ");
			setName( "Mana Jump");
			}
		

		@Override
		public void accept(PassiveVisitor v, PlayerModel me, PlayerModel enemy, Card x) {
			v.visitManaJump(me, enemy, x);
		}

	}


