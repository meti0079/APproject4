package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Visitor;
import playModel.Player;

public class Sheep extends Minion {
	public Sheep() {
		this.Set_Name("Sheep");
		this.Set_Class("Neutral");
		this.Set_Rarity("free");
		this.Set_Mana(1);
		this.setHp(1);
		this.setAttack(1);
		this.setDescription("");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		super.accept(v, taeget, attackerP, targetP);
		v.visitSheep(this, taeget, attackerP, targetP);
		return true;

	}

}
