package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class OasisSnapjaw extends Minion implements Acceptable{
	public OasisSnapjaw() {
		this.Set_Name("Oasis Snapjaw");
		this.Set_Class("Neutral");
		this.Set_Rarity("common");
		this.Set_Mana(4);
		this.setHp(7);
		this.setAttack(2);	
	}

	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		super.accept(v, taeget, attackerP, targetP);
			v.visitOasisSnapjaw(this, taeget, attackerP, targetP);
			return true;
	}
}
