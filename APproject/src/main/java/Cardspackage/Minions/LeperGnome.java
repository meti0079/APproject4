package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.PlayerModel;

public class LeperGnome extends Minion  implements Acceptable{

	public LeperGnome() {
		this.Set_Name("Leper Gnome");
		this.Set_Class("Neutral");
		this.Set_Rarity("common");
		this.Set_Mana(1);
		this.setHp(1);
		this.setAttack(1);	
		this.setDeathrattle(true);
		this.setDescription("Deathrattle: Deal 2 damage to the enemy hero.");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP) {
			v.visitLeperGnome(this, taeget, attackerP, targetP);
			return super.accept(v, taeget, attackerP, targetP);
	}
}