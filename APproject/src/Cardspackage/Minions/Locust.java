package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Visitor;
import playModel.PlayerModel;

public class Locust extends Minion{
	public Locust() {
		this.Set_Name("Locust");
		this.Set_Class("Neutral");
		this.Set_Rarity("common");
		this.Set_Mana(1);
		this.setHp(1);
		this.setAttack(1);
		this.setRush(true);
	}
	
	

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
			v.visitLocust(this, taeget, attackerP, targetP);
			return super.accept(v, taeget, attackerP, targetP);
	}

}
