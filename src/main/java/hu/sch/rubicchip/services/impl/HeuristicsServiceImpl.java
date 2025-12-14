package hu.sch.rubicchip.services.impl;

import hu.sch.rubicchip.services.HeuristicsService;
import hu.sch.rubicchip.services.impl.shared.GeneralChipManipulator;
import hu.sch.rubicchip.utils.ChipSide;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class HeuristicsServiceImpl extends GeneralChipManipulator implements HeuristicsService {

    public int possibleConnections(Integer chipId, Map<Integer, Map<Integer, ChipSide>> fullTableMap) {
        int count = 0;

        Map<Integer, ChipSide> thisChip = fullTableMap.get(chipId);

        for (Integer otherId : fullTableMap.keySet()) {
            if (otherId.equals(chipId)) {
                continue;
            }
            Map<Integer, ChipSide> otherChip = fullTableMap.get(otherId);
            if (canConnect(thisChip, otherChip)) {
                count++;
            }
        }
        return count;
    }

    private boolean canConnect(Map<Integer, ChipSide> chipA, Map<Integer, ChipSide> chipB) {
        for (Map<Integer, ChipSide> rotationA : rotations(chipA)) {
            for (Map<Integer, ChipSide> rotationB : rotations(chipB)) {
                if (matchesRight(rotationA.get(2), rotationB.get(4))) {
                    return true;
                }
                if (matchesDown(rotationA.get(3), rotationB.get(1))) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<Map<Integer, ChipSide>> rotations(Map<Integer, ChipSide> chip) {
        List<Map<Integer, ChipSide>> list = new ArrayList<>();
        Map<Integer, ChipSide> current = chip;
        for (int i = 0; i < 4; i++) {
            list.add(current);
            current = rotatePreview(current);
        }
        return list;
    }
}
