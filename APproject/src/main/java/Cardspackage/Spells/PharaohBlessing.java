package Cardspackage.Spells;

import javax.swing.JOptionPane;

import Cardspackage.Spell;
import hero.Heros;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.PlayerModel;

public class PharaohBlessing extends Spell implements Acceptable{

	public PharaohBlessing() {
		this.Set_Name("Pharaoh's Blessing");
		this.Set_Class("Neutral");
		this.Set_Mana(6);
		this.Set_Rarity("Rare");
		this.setDescription("Give a minion +4/+4, Divine Shield, and Taunt.");
		this.setDivineShield(true);
		this.setTaunt(true);
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		if(taeget==null || taeget instanceof Heros || targetP.getBattleGroundCard().contains(taeget) ) {
			JOptionPane.showMessageDialog(null, "chose a valid target");			
			return false;
		}
		v.visitPharaohBlessing(this, taeget, attackerP, targetP);
		return true;
		}

}
