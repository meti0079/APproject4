package server.cardspackage.Minions;

import server.cardspackage.Minion;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;

public class CurioCollector extends Minion implements Acceptable{

	public CurioCollector() {
		this.Set_Name("Curio Collector");
		this.Set_Class("Neutral");
		this.Set_Rarity("rare");
		this.Set_Mana(5);
		this.setHp(4);
		this.setAttack(4);	
		this.setDescription("Whenever you draw a card, gain +1/+1.");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitCurioCollector(this, taeget, attackerP, targetP);
		return super.accept(v, taeget, attackerP, targetP,mapper );
		
		
	}

}
