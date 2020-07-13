package interfaces;

import playModel.Player;

public interface HeroPowerVisitor {
public void visitMagePower(Object target, Player me, Player enemy);
public void visitRougePower(Object target, Player me, Player enemy);
public void visitHunterPower(Object target, Player me, Player enemy);
public void visitPriestPower(Object target, Player me, Player enemy);
public void visitWarlockPower(Object target, Player me, Player enemy);
}
