package Cardspackage.Spells;

import javax.swing.JOptionPane;

import Cardspackage.Spell;
import interfaces.Acceptable;
import interfaces.Visitor;
import playModel.Mapper;
import playModel.PlayerModel;

public class HolySmite  extends Spell implements Acceptable{

	public HolySmite() {
		this.Set_Name("Holy Smite");
		this.Set_Class("Priest");
		this.Set_Mana(1);
		this.Set_Rarity("common");
		this.setDescription("Deal 3 damage to a minion.");
	}

	@Override
	public boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper) {
		if(taeget== null ||attackerP.getHero().equals(taeget) || attackerP.getBattleGroundCard().contains(taeget)) {
			JOptionPane.showMessageDialog(null, "chose a valid target");
			return false;			
		}
		v.visitHolySmite(this, taeget, attackerP, targetP);
		return true;}

}
