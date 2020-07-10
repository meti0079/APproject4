package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;

public class Shieldbearer extends Minion implements Acceptable {

	public Shieldbearer() {
		this.Set_Name("Shieldbearer");
		this.Set_Class("Neutral");
		this.Set_Rarity("Common");
		this.Set_Mana(1);
		this.setHp(4);
		this.setAttack(0);
		this.setDescription("Taunt");
		this.setTaunt(true);
		}

	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		
	}

}
