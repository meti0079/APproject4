package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class KronxDragonhoof extends Minion implements Acceptable{
	public KronxDragonhoof() {
		this.Set_Name("Kronx Dragonhoof");
		this.Set_Class("Neutral");
		this.Set_Rarity("legendary");
		this.Set_Mana(6);
		this.setHp(6);
		this.setAttack(6);	
		this.setDescription("Battlecry: Draw Galakrond. If you're already Galakrond, unleash a Devastation.");
		this.setBattlecry(true);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		if(super.accept(v, taeget, attackerP, targetP)) {
			v.visitKronxDragonhoof(this, taeget, attackerP, targetP);
			return true;
		}
		return false;
	}
}
