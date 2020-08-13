package server.cardspackage.Minions;

import javax.persistence.Entity;

import server.cardspackage.Minion;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;
@Entity
public class BigGameHunter extends Minion {
	public BigGameHunter() {
		this.Set_Name("Big Game Hunter");
		this.Set_Class("Neutral");
		this.Set_Rarity("epic");
		this.Set_Mana(5);
		this.setHp(2);
		this.setAttack(4);
		this.setDescription("Battlecry: Destroy a minion with 7 or more Attack.");
		this.setBattlecry(true);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
			v.visitBigGameHunter(this, taeget, attackerP, targetP);
			return super.accept(v, taeget, attackerP, targetP,mapper );
	}

}
