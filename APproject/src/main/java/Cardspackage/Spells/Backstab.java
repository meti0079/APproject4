package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.PlayerModel;

public class Backstab extends Spell  implements Acceptable{
	public Backstab() {
		this.Set_Name("Backstab");
		this.Set_Class("Rouge");
		this.Set_Mana(0);
		this.Set_Rarity("common");
		this.setDescription("Deal 2 damage to enemy hero.");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitBackstab(this, taeget, attackerP, targetP);
		return true;}
	
}
