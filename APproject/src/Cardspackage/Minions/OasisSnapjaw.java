package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;

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
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}
}
