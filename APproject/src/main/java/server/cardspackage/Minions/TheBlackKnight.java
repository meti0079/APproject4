package server.cardspackage.Minions;

import javax.persistence.Entity;

import server.cardspackage.Minion;
import server.interfaces.Acceptable;
import server.interfaces.Visitor;
import server.playModel.Mapper;
import server.playModel.PlayerModel;
@Entity
public class TheBlackKnight extends Minion implements Acceptable{
	public TheBlackKnight() {
		this.Set_Name("The Black Knight");
		this.Set_Class("Neutral");
		this.Set_Rarity("legendary");
		this.Set_Mana(6);
		this.setHp(5);
		this.setAttack(4);	
		this.setBattlecry(true);
		this.setTaunt(true);
		this.setDescription("Battlecry: Destroy an enemy minion with Taunt.");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		v.visitTheBlackKnight(this, taeget, attackerP, targetP);
		return super.accept(v, taeget, attackerP, targetP,mapper );
		}
		}
