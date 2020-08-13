package server.cardspackage.Minions;

import javax.persistence.Entity;

import server.cardspackage.Minion;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;
@Entity
public class TombWarden  extends Minion implements Acceptable{


	public TombWarden() {
		this.Set_Name("Tomb Warden");
		this.Set_Class("Neutral");
		this.Set_Rarity("common");
		this.Set_Mana(8);
		this.setHp(6);
		this.setAttack(3);
		this.setDescription("Taunt . Battlecry: Summon a copy of this minion");
		this.setBattlecry(true);
		this.setTaunt(true);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitTombWarden(this,  taeget,attackerP, targetP);		
		return super.accept(v, taeget, attackerP, targetP,mapper );

	}

}
