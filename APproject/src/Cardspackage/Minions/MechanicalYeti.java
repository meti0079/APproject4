package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Visitor;
import playModel.PlayerModel;

public class MechanicalYeti extends Minion{
	public MechanicalYeti() {
		this.Set_Name("Mechanical Yeti");
		this.Set_Class("Neutral");
		this.Set_Rarity("epic");
		this.Set_Mana(4);
		this.setHp(4);
		this.setAttack(2);
		this.setDescription("");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		return	super.accept(v, taeget, attackerP, targetP);

	}
}
