package Cardspackage.Minions;

import Cardspackage.Minion;
import interfaces.Acceptable;
import interfaces.Visitor;

public class LeperGnome extends Minion  implements Acceptable{
	
		public LeperGnome() {
			this.Set_Name("Leper Gnome");
			this.Set_Class("Neutral");
			this.Set_Rarity("common");
				this.Set_Mana(1);
			this.setHp(1);
			this.setAttack(1);	
			this.setDeathrattle(true);
			this.setDescription("Deathrattle: Deal 2 damage to the enemy hero.");
		}

		@Override
		public void accept(Visitor v) {
			// TODO Auto-generated method stub
			
		}}
