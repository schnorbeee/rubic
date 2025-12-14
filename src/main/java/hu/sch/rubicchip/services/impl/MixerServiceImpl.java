package hu.sch.rubicchip.services.impl;

import hu.sch.rubicchip.services.MixerService;
import hu.sch.rubicchip.services.ResolverService;
import hu.sch.rubicchip.utils.ChipSide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class MixerServiceImpl implements MixerService {

    private final ResolverService resolverService;

    @Autowired
    public MixerServiceImpl(ResolverService resolverService) {
        this.resolverService = resolverService;
    }

    @Override
    public void setRandomPosition(Map<Integer, Map<Integer, ChipSide>> fullChipsMap) {
        List<Map<Integer, ChipSide>> chipSides = new ArrayList<>();
        Map<Integer, ChipSide> firstChipSideMap = fullChipsMap.get(1);
        Map<Integer, ChipSide> secondChipSideMap = fullChipsMap.get(2);
        Map<Integer, ChipSide> thirdChipSideMap = fullChipsMap.get(3);
        Map<Integer, ChipSide> fourthChipSideMap = fullChipsMap.get(4);
        Map<Integer, ChipSide> fifthChipSideMap = fullChipsMap.get(5);
        Map<Integer, ChipSide> sixthChipSideMap = fullChipsMap.get(6);
        Map<Integer, ChipSide> seventhChipSideMap = fullChipsMap.get(7);
        Map<Integer, ChipSide> eighthChipSideMap = fullChipsMap.get(8);
        Map<Integer, ChipSide> ninthChipSideMap = fullChipsMap.get(9);
        chipSides.add(firstChipSideMap);
        chipSides.add(secondChipSideMap);
        chipSides.add(thirdChipSideMap);
        chipSides.add(fourthChipSideMap);
        chipSides.add(fifthChipSideMap);
        chipSides.add(sixthChipSideMap);
        chipSides.add(seventhChipSideMap);
        chipSides.add(eighthChipSideMap);
        chipSides.add(ninthChipSideMap);

        List<Integer> randomizedKeys = new ArrayList<>(fullChipsMap.keySet());
        Collections.shuffle(randomizedKeys);

        int newKey = 1;
        for (Integer oldKey : randomizedKeys) {
            fullChipsMap.put(newKey, chipSides.get(oldKey - 1));
            newKey++;
        }
    }

    @Override
    public void shuffleOrientations(Map<Integer, Map<Integer, ChipSide>> fullChipsMap) {
        for (Map.Entry<Integer, Map<Integer, ChipSide>> chipEntry : fullChipsMap.entrySet()) {
            rotateChip(fullChipsMap, chipEntry.getKey(), chipEntry.getValue());
        }
    }

    private void rotateChip(Map<Integer, Map<Integer, ChipSide>> fullChipsMap, Integer chipIndex,
            Map<Integer, ChipSide> sides) {
        Map<Integer, ChipSide> rotated = resolverService.rotatePreview(sides);
        fullChipsMap.put(chipIndex, rotated);
    }
}
