package Cardspackage.Weapons;

import Cardspackage.Weapon;
import interfaces.Acceptable;
import interfaces.Visitor;

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
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}

}
