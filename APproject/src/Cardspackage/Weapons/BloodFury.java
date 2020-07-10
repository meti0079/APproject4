package Cardspackage.Weapons;

import Cardspackage.Weapon;
import interfaces.Acceptable;
import interfaces.Visitor;

public class BloodFury extends Weapon implements Acceptable{

	public BloodFury() {

		this.Set_Name("Blood Fury");
		this.Set_Class("Neutral");
		this.Set_Rarity("common");
			this.Set_Mana(3);
			this.setDurability(8);
			this.setAttack(3);
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}

}
