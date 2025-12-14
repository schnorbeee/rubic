package hu.sch.rubicchip.services;

import hu.sch.rubicchip.utils.ChipSide;

import java.util.Map;

public interface MixerService {

    void setRandomPosition(Map<Integer, Map<Integer, ChipSide>> fullChipsMap);

    void shuffleOrientations(Map<Integer, Map<Integer, ChipSide>> fullChipsMap);
}
