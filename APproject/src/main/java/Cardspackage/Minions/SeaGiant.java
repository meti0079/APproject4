package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.PlayerModel;

public class SeaGiant extends Minion  implements Acceptable{
	public SeaGiant() {
		this.Set_Name("Sea Giant");
		this.Set_Class("Neutral");
		this.Set_Rarity("epic");
		this.Set_Mana(10);
		this.setHp(8);
		this.setAttack(8);
		this.setDescription("");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitSeaGiant(this, taeget, attackerP, targetP);
		return super.accept(v, taeget, attackerP, targetP,mapper );
	}
}
