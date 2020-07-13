package passives;

import Cardspackage.Card;
import interfaces.PassiveVisitor;
import playModel.Player;

public class GlyphofWarding extends Passive{
	public GlyphofWarding() {
		setName("Glyph of Warding");
		setDescription("Enemy minions cost (1) more.");
	}

	@Override
	public void accept(PassiveVisitor v, Player me, Player enemy, Card x) {
		v.visitGlyphofWarding(me, enemy, x);
	}

}
