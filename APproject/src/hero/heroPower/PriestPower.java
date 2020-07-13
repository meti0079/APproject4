package hero.heroPower;

import interfaces.HeroPowerVisitor;
import playModel.Player;

public class PriestPower extends HeroPower{
	public PriestPower() {
		this.setDescriptoin("restore 4 health");
		this.setMana(2);
		this.setMaxUse(1);
		this.setUsed(false);
	}

	public void accept(HeroPowerVisitor v, Object target, Player me, Player enemy) {
		v.visitPriestPower(target, me, enemy);
	}
}
