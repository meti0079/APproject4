package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class gift extends Spell implements Acceptable{
	public gift() {
		this.Set_Name("gift");
		this.Set_Class("Warlock");
		this.Set_Mana(2);
		this.Set_Rarity("legendary");
		this.setDescription("gift 1 Hp to hero");
		this.setNeedTarget(false);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		v.visitgift(this, taeget, attackerP, targetP);
		return true;	
	}
}