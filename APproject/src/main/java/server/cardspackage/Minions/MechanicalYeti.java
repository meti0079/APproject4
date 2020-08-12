package server.cardspackage.Minions;

import server.cardspackage.Minion;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;

public class MechanicalYeti extends Minion{
	public MechanicalYeti() {
		this.Set_Name("Mechanical Yeti");
		this.Set_Class("Neutral");
		this.Set_Rarity("epic");
		this.Set_Mana(4);
		this.setHp(4);
		this.setAttack(2);
		this.setDescription("");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
	return super.accept(v, taeget, attackerP, targetP,mapper );
	

	}
}
