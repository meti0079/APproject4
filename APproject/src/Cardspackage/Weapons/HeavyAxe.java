package Cardspackage.Weapons;

import Cardspackage.Weapon;
import interfaces.Acceptable;
import interfaces.Visitor;

public class HeavyAxe extends Weapon implements Acceptable{

	public HeavyAxe() {

		this.Set_Name("Heavy Axe");
		this.Set_Class("Neutral");
		this.Set_Rarity("common");
			this.Set_Mana(1);
		this.setAttack(1);
		this.setDurability(3);
	}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}

}
