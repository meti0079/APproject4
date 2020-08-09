package Cardspackage.Spells;

import javax.swing.JOptionPane;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.PlayerModel;

public class ArcaneShot extends Spell implements Acceptable{

	public ArcaneShot() {
		this.Set_Name("Arcane Shot");
		this.Set_Class("Hunter");
		this.Set_Mana(1);
		this.Set_Rarity("common");
		this.setDescription("Deal 2 damage.");
		
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		
		if(taeget == null ||attackerP.getBattleGroundCard().contains(taeget) || attackerP.getHero().equals(taeget)) {
			JOptionPane.showMessageDialog(null, "chose a valid target");
			return false;			
		}
		v.visitArcaneShot(this, taeget,  attackerP, targetP);		
		return true;
	}
		
}
