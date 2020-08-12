package server.interfaces;

import server.playModel.PlayerModel;

public interface HeroPowerVisitor {
public boolean visitMagePower(Object target, PlayerModel me, PlayerModel enemy);
public boolean visitRougePower(Object target, PlayerModel me, PlayerModel enemy);
public boolean visitHunterPower(Object target, PlayerModel me, PlayerModel enemy);
public boolean visitPriestPower(Object target, PlayerModel me, PlayerModel enemy);
public boolean visitWarlockPower(Object target, PlayerModel me, PlayerModel enemy);
}
