package passives;

import Cardspackage.Card;
import interfaces.PassiveVisitor;
import playModel.PlayerModel;

public class GlyphofWarding extends Passive{
	public GlyphofWarding() {
		setName("Glyph of Warding");
		setDescription("Enemy minions cost (1) more.");
	}

	@Override
	public void accept(PassiveVisitor v, PlayerModel me, PlayerModel enemy, Card x) {
		v.visitGlyphofWarding(me, enemy, x);
	}

}
