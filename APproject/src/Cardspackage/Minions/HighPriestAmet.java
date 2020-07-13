package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class HighPriestAmet extends Minion  implements Acceptable{
	public HighPriestAmet() {
		this.Set_Name("High Priest Amet");
		this.Set_Class("Priest");
		this.Set_Rarity("legendary");
		this.Set_Mana(4);
		this.setHp(7);
		this.setAttack(2);	
		this.setDescription("whenever you summon this set a minion Health equal to this .");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		super.accept(v, taeget, attackerP, targetP);
		v.visitHighPriestAmet(this, taeget, attackerP, targetP);
			return true;
	}
}
