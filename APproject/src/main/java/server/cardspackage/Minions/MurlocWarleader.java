package server.cardspackage.Minions;

import server.cardspackage.Minion;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;

public class MurlocWarleader extends Minion implements Acceptable {
	public MurlocWarleader() {
		this.Set_Name("Murloc Warleader");
		this.Set_Class("Neutral");
		this.Set_Rarity("epic");
			this.Set_Mana(3);
		this.setHp(3);
		this.setAttack(3);	
		setDescription("Your other Murlocs have +2 Attack.");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitMurlocWarleader(this, taeget, attackerP, targetP);
		return super.accept(v, taeget, attackerP, targetP,mapper );
		}
		

}
