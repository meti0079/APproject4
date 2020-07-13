package hero.heroPower;

import interfaces.HeroPowerVisitor;
import playModel.Player;

public class WarlockPower extends HeroPower{
	public WarlockPower() {
		this.setDescriptoin("spend 2 mana and do: 1.increase 1 attack and hp to a card  2,add 1 card to player cards");
		this.setMana(2);
		this.setMaxUse(1);
		this.setUsed(false);
	}

	public void accept(HeroPowerVisitor v, Object target, Player me, Player enemy) {
		v.visitWarlockPower(target, me, enemy);
	}
}
