package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Visitor;
import playModel.PlayerModel;

public class WaxElemental extends Minion{
	public WaxElemental() {
		this.Set_Name("Wax Elemental");
		this.Set_Class("Neutral");
		this.Set_Rarity("epic");
		this.Set_Mana(1);
		this.setHp(2);
		this.setAttack(0);
		this.setDescription("Tunt");
		this.setTaunt(true);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		v.visitWaxElemental(this, taeget, attackerP, targetP);
		return super.accept(v, taeget, attackerP, targetP);
	}

}
