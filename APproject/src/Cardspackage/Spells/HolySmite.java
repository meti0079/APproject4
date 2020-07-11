package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class HolySmite  extends Spell implements Acceptable{

	public HolySmite() {
		this.Set_Name("Holy Smite");
		this.Set_Class("Priest");
		this.Set_Mana(1);
		this.Set_Rarity("common");
		this.setDescription("Deal 3 damage to a minion.");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		v.visitHolySmite(this, taeget, attackerP, targetP);
		return true;}

}
