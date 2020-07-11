package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class Shieldbearer extends Minion implements Acceptable {

	public Shieldbearer() {
		this.Set_Name("Shieldbearer");
		this.Set_Class("Neutral");
		this.Set_Rarity("Common");
		this.Set_Mana(1);
		this.setHp(4);
		this.setAttack(0);
		this.setDescription("Taunt");
		this.setTaunt(true);
		}

	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		if(super.accept(v, taeget, attackerP, targetP)) {
			v.visitShieldbearer(this, taeget, attackerP, targetP);
			return true;
		}
		return false;
	}

}
