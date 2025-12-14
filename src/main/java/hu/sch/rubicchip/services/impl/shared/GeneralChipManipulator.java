package hu.sch.rubicchip.services.impl.shared;

import hu.sch.rubicchip.utils.ChipSide;

import java.util.HashMap;
import java.util.Map;

public abstract class GeneralChipManipulator {

    public Map<Integer, ChipSide> rotatePreview(Map<Integer, ChipSide> original) {
        Map<Integer, ChipSide> rotated = new HashMap<>();

        rotated.put(1, original.get(4));
        rotated.put(2, original.get(1));
        rotated.put(3, original.get(2));
        rotated.put(4, original.get(3));

        return rotated;
    }

    public boolean matchesRight(ChipSide leftNeighborRight, ChipSide currentLeft) {
        return leftNeighborRight.leftColor().equals(currentLeft.rightColor())
                && leftNeighborRight.rightColor().equals(currentLeft.leftColor());
    }

    public boolean matchesDown(ChipSide topNeighborBottom, ChipSide currentTop) {
        return topNeighborBottom.leftColor().equals(currentTop.rightColor())
                && topNeighborBottom.rightColor().equals(currentTop.leftColor());
    }
}
