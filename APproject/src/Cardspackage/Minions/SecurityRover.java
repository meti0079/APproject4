package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class SecurityRover extends Minion implements Acceptable{

	public SecurityRover() {
		this.Set_Name("Security Rover");
		this.Set_Class("Neutral");
		this.Set_Rarity("rare");
		this.Set_Mana(6);
		this.setHp(6);
		this.setAttack(2);
		this.setDescription("Whenever this minion takes damage, summon a 2/3 Mech with Taunt.");
		this.setTaunt(true);	
	}

	@Override
	public boolean  accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		if(super.accept(v, taeget, attackerP, targetP)) {
			v.visitSecurityRover(this, taeget, attackerP, targetP);
			return true;
		}
		return false;
	}

}
