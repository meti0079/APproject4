package interfaces;

import Cardspackage.Card;
import playModel.PlayerModel;

public interface PassiveVisitor {
public void visitAlchemistsStone(PlayerModel me, PlayerModel enemy , Card x);
public void visitBandofScarabs(PlayerModel me, PlayerModel enemy , Card x);
public void visitCaltrops(PlayerModel me, PlayerModel enemy , Card x);
public void visitCapturedFlag(PlayerModel me, PlayerModel enemy , Card x);
public void visitEntrenchment(PlayerModel me, PlayerModel enemy , Card x);
public void visitGlyphofWarding(PlayerModel me, PlayerModel enemy , Card x);
public void visitGrommashsArmguards(PlayerModel me, PlayerModel enemy , Card x);
public void visitStargazing(PlayerModel me, PlayerModel enemy , Card x);
public void visitOffCard(PlayerModel me, PlayerModel enemy , Card x);
public void visitManaJump(PlayerModel me, PlayerModel enemy , Card x);
}

