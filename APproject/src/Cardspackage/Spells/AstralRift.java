package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class AstralRift extends Spell implements Acceptable{
	public AstralRift() {
		this.Set_Name("Astral Rift");
		this.Set_Class("Mage");
		this.Set_Rarity("rare");
			this.Set_Mana(2);
		this.setDescription("Add 2 random minions to your hand.");
		this.setNeedTarget(false);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		v.visitAstralRift(this, taeget, attackerP, targetP);
		return true;}

}
