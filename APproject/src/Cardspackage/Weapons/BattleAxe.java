package Cardspackage.Weapons;

import Cardspackage.Weapon;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Player;

public class BattleAxe extends Weapon implements Acceptable{

	public BattleAxe() {
		Set_Name("Battle Axe");
		setDurability(2);
		Set_Class("Mage");
		Set_Mana(1);
		Set_Rarity("Common");
		setAttack(2);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP) {
		super.accept(v, taeget, attackerP, targetP);
		v.visitBattleAxe(this, taeget, attackerP, targetP);
		return true;
		
	}

}
