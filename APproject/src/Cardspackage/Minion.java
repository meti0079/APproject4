package Cardspackage;


public abstract  class Minion extends Cards{
	private int HP;
	private int attack;
	public Minion() {
		this.setType("Minion");

	}

	public int getAttack() {
		return this.attack;
	}

	@Override
	public int getHp() {
		return this.HP;
	}
//
	@Override
	public void setAttack(int x) {
		attack=x;
	}
//
	@Override
	public void setHp(int x) {
		HP=x;
	}
//
	@Override
	public String getType() {
		return "Minion" ;
	}
	public Cards copy() {
//		Minion s=new Minion();
//		s.setAttack(this.getAttack());
//		s.Set_Class(this.getClass()+"");
//		s.setHp(this.getHp());
//		s.Set_Mana(this.get_Mana());
//		s.Set_Name(this.get_Name());
//		s.Set_Rarity(this.get_Rarity());
//		s.setBattlecry(this.isBattlecry());
//		s.setDeathrattle(this.isDeathrattle());
//		s.setDescription(this.getDescription());
//		s.setDivineShield(this.isDivineShield());
//		s.setQuest(this.isQuest());
//		s.setRush(this.isRush());
//		s.setTaunt(this.isTaunt());
//		s.setWindfury(this.isWindfury());
		return null;
	}


}
