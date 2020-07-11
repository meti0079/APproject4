package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class MurlocWarleader extends Minion implements Acceptable {
	public MurlocWarleader() {
		this.Set_Name("Murloc Warleader");
		this.Set_Class("Neutral");
		this.Set_Rarity("epic");
			this.Set_Mana(3);
		this.setHp(3);
		this.setAttack(3);	
		setDescription("Your other Murlocs have +2 Attack.");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		if(super.accept(v, taeget, attackerP, targetP)) {
			v.visitMurlocWarleader(this, taeget, attackerP, targetP);
			return true;
		}
		return false;
	}

}
