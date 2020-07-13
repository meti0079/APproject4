package Cardspackage.Spells;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class ArcaneShot extends Spell implements Acceptable{

	public ArcaneShot() {
		this.Set_Name("Arcane Shot");
		this.Set_Class("Hunter");
		this.Set_Mana(1);
		this.Set_Rarity("common");
		this.setDescription("Deal 2 damage.");
		
	}

	@Override
	public boolean  accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		if(taeget == null ||attackerP.getBattleGroundCard().contains(taeget) || attackerP.getHero().equals(taeget))
			return false;
		v.visitArcaneShot(this, taeget,  attackerP, targetP);		
		return true;
	}
		
}
