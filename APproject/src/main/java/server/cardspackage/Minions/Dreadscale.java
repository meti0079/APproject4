package server.cardspackage.Minions;

import javax.persistence.Entity;

import server.cardspackage.Minion;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;
@Entity
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
public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
	v.visitDreadscale(this, taeget, attackerP, targetP);
	return super.accept(v, taeget, attackerP, targetP,mapper );
}



}
