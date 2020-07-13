package interfaces;

import Cardspackage.Card;
import playModel.Player;

public interface PassiveVisitor {
public void visitAlchemistsStone(Player me, Player enemy , Card x);
public void visitBandofScarabs(Player me, Player enemy , Card x);
public void visitCaltrops(Player me, Player enemy , Card x);
public void visitCapturedFlag(Player me, Player enemy , Card x);
public void visitEntrenchment(Player me, Player enemy , Card x);
public void visitGlyphofWarding(Player me, Player enemy , Card x);
public void visitGrommashsArmguards(Player me, Player enemy , Card x);
public void visitStargazing(Player me, Player enemy , Card x);
}
