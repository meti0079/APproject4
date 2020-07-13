package hero.heroPower;

import interfaces.HeroPowerVisitor;
import playModel.Player;

public class HunterPower extends HeroPower{
	public HunterPower() {
		this.setDescriptoin("after ypr opponent play a card 1 dame to it");
		this.setMana(2);
		this.setMaxUse(1);
		this.setUsed(false);
	}


	public void accept(HeroPowerVisitor v, Object target, Player me, Player enemy) {
		v.visitHunterPower(target, me, enemy);
	}


}
