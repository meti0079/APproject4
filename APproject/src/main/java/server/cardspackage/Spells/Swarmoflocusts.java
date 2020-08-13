package server.cardspackage.Spells;

import javax.persistence.Entity;

import server.cardspackage.Spell;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;
@Entity
public class Swarmoflocusts extends Spell implements Acceptable{

	public Swarmoflocusts() {
		
		this.Set_Name("Swarm of locusts");
		this.Set_Class("Neutral");
		this.Set_Mana(6);
		this.Set_Rarity("Rare");
		this.setDescription("summon seven 1/1 Locusts with Rush.");
		this.setRush(true);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitSwarmoflocusts(this, taeget, attackerP, targetP);
		return true;}
}
