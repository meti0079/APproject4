package server.cardspackage.Minions;

import server.cardspackage.Minion;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;

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
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitLeperGnome(this, taeget, attackerP, targetP);
		return super.accept(v, taeget, attackerP, targetP,mapper );
	}
}
