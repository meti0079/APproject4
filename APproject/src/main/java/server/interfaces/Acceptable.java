package server.interfaces;

import server.playModel.Mapper;
import server.playModel.PlayerModel;

public interface Acceptable {
boolean accept(Visitor v, Object taeget, PlayerModel attackerP, PlayerModel targetP, Mapper mapper);
}
