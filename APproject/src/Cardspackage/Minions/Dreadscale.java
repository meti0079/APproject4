package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.PlayerModel;

public class Dreadscale extends Minion  implements Acceptable{
public Dreadscale() {
	this.Set_Name("Dreadscale");
	this.Set_Class("Warlock");
	this.Set_Rarity("legendary");
		this.Set_Mana(3);
	this.setHp(2);
	this.setAttack(4);
	this.setDescription(" At the end of your turn Deal 1 damage to each minion.");

	
}

@Override
public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
		v.visitDreadscale(this, taeget, attackerP, targetP);
		return super.accept(v, taeget, attackerP, targetP);
}



}
