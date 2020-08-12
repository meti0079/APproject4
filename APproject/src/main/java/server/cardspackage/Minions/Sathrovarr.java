package server.cardspackage.Minions;

import server.cardspackage.Minion;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;

public class Sathrovarr extends Minion  implements Acceptable{

	public Sathrovarr() {

		this.Set_Name("Sathrovarr");
		this.Set_Class("Neutral");
		this.Set_Rarity("legendary");
			this.Set_Mana(9);
		this.setHp(5);
		this.setAttack(5);
		this.setDescription("Battlecry:Choose a friendly minion. Add a copy of "
				+ "it to your hand, deck and battlefield.");
		this.setBattlecry(true);
	
	
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitSathrovarr(this, taeget, attackerP, targetP);
		return super.accept(v, taeget, attackerP, targetP,mapper );
	}

}
