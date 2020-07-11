package interfaces;

import playModel.Player;

public interface Acceptable {
public boolean accept(Visitor v, Object taeget, Player attackerP, Player targetP);
}
