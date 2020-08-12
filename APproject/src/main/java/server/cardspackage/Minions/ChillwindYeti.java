package server.cardspackage.Minions;

import server.cardspackage.Minion;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;

public class ChillwindYeti  extends Minion implements Acceptable{
	public ChillwindYeti() {
		this.Set_Name("Chillwind Yeti");
		this.Set_Class("Neutral");
		this.Set_Rarity("rare");
		this.Set_Mana(4);
		this.setHp(5);
		this.setAttack(4);	
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitChillwindYeti(this, taeget, attackerP, targetP);
		return super.accept(v, taeget, attackerP, targetP,mapper );
	}
}
