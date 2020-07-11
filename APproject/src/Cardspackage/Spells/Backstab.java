package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class Backstab extends Spell  implements Acceptable{
	public Backstab() {
		this.Set_Name("Backstab");
		this.Set_Class("Rouge");
		this.Set_Mana(0);
		this.Set_Rarity("common");
		this.setDescription("Deal 2 damage to an undamaged minion.");
		this.setNeedTarget(false);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		v.visitBackstab(this, taeget, attackerP, targetP);
		return true;}
	
}
