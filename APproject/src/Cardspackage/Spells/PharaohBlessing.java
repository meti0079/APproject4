package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class PharaohBlessing extends Spell implements Acceptable{

	public PharaohBlessing() {
		this.Set_Name("Pharaoh's Blessing");
		this.Set_Class("Neutral");
		this.Set_Mana(6);
		this.Set_Rarity("Rare");
		this.setDescription("Give a minion +4/+4, Divine Shield, and Taunt.");
		this.setDivineShield(true);
		this.setTaunt(true);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		v.visitPharaohBlessing(this, taeget, attackerP, targetP);
		return true;}

}
