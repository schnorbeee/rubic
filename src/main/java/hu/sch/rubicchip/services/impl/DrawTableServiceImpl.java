package hu.sch.rubicchip.services.impl;

import hu.sch.rubicchip.enums.Color;
import hu.sch.rubicchip.services.DrawTableService;
import hu.sch.rubicchip.utils.ChipSide;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DrawTableServiceImpl implements DrawTableService {

    private static final String LEFT_BORDER_SPACE = "|         ";
    private static final String RIGHT_BORDER_SPACE = "         |\n";
    private static final String LEFT_BORDER = "|   ";
    private static final String RIGHT_BORDER = "   |\n";
    private static final String EMPTY_SPACE = "             ";
    private static final String EMPTY_SPACE_WITH_BORDER = "      |      ";
    private static final String EQUALITY = "=";
    private static final String UP_DOWN_BORDER = "-------------------------------------------------------------------------------\n";
    private static final String MIDDLE_BORDER = "--------------||----------------------||----------------------||---------------\n";
    private static final String ROD = ",";

    @Override
    public String getTable(Map<Integer, Map<Integer, ChipSide>> fullTableMap) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Color> firstChipColors = getChipColorList(1, fullTableMap);
        List<Color> secondChipColors = getChipColorList(2, fullTableMap);
        List<Color> thirdChipColors = getChipColorList(3, fullTableMap);
        List<Color> fourthChipColors = getChipColorList(4, fullTableMap);
        List<Color> fifthChipColors = getChipColorList(5, fullTableMap);
        List<Color> sixthChipColors = getChipColorList(6, fullTableMap);
        List<Color> seventhChipColors = getChipColorList(7, fullTableMap);
        List<Color> eighthChipColors = getChipColorList(8, fullTableMap);
        List<Color> ninthChipColors = getChipColorList(9, fullTableMap);

        stringBuilder.append(UP_DOWN_BORDER);
        chipRowAppender(stringBuilder, firstChipColors, secondChipColors, thirdChipColors);
        stringBuilder.append(MIDDLE_BORDER);
        chipRowAppender(stringBuilder, fourthChipColors, fifthChipColors, sixthChipColors);
        stringBuilder.append(MIDDLE_BORDER);
        chipRowAppender(stringBuilder, seventhChipColors, eighthChipColors, ninthChipColors);
        stringBuilder.append(UP_DOWN_BORDER);

        log.info("Actual table: \n" + stringBuilder);
        return stringBuilder.toString();
    }

    private List<Color> getChipColorList(Integer chipIndex, Map<Integer, Map<Integer, ChipSide>> fullTableMap) {
        List<Color> chipColors = new ArrayList<>();
        for (Map.Entry<Integer, Map<Integer, ChipSide>> chipEntry : fullTableMap.entrySet()) {
            if (chipEntry.getKey().equals(chipIndex)) {
                for (Map.Entry<Integer, ChipSide> sideEntry : chipEntry.getValue().entrySet()) {
                    chipColors.addAll(List.of(sideEntry.getValue().leftColor(), sideEntry.getValue().rightColor()));
                }
            }
        }
        return chipColors;
    }

    private void chipRowAppender(StringBuilder stringBuilder, List<Color> firstChipInRowColors,
            List<Color> secondChipInRowColors, List<Color> thirdChipInRowColors) {
        upperAndBottomAppender(stringBuilder, firstChipInRowColors, secondChipInRowColors,
                thirdChipInRowColors, 0, 1);
        leftAndRightSideAppender(stringBuilder, firstChipInRowColors, secondChipInRowColors,
                thirdChipInRowColors, 7, 2);
        leftAndRightSideAppender(stringBuilder, firstChipInRowColors, secondChipInRowColors,
                thirdChipInRowColors, 6, 3);
        upperAndBottomAppender(stringBuilder, firstChipInRowColors, secondChipInRowColors,
                thirdChipInRowColors, 5, 4);
    }

    private void leftAndRightSideAppender(StringBuilder stringBuilder, List<Color> firstChipInRowColors,
            List<Color> secondChipInRowColors, List<Color> thirdChipInRowColors,
            Integer leftIndex, Integer rightIndex) {
        stringBuilder
                .append(LEFT_BORDER + firstChipInRowColors.get(leftIndex).getNameToDraw()
                        + EMPTY_SPACE + firstChipInRowColors.get(rightIndex).getNameToDraw()
                        + EQUALITY + secondChipInRowColors.get(leftIndex).getNameToDraw()
                        + EMPTY_SPACE + secondChipInRowColors.get(rightIndex).getNameToDraw()
                        + EQUALITY + thirdChipInRowColors.get(leftIndex).getNameToDraw()
                        + EMPTY_SPACE + thirdChipInRowColors.get(rightIndex).getNameToDraw()
                        + RIGHT_BORDER);
    }

    private void upperAndBottomAppender(StringBuilder stringBuilder, List<Color> firstChipInRowColors,
            List<Color> secondChipInRowColors, List<Color> thirdChipInRowColors,
            Integer leftIndex, Integer rightIndex) {
        stringBuilder
                .append(LEFT_BORDER_SPACE + firstChipInRowColors.get(leftIndex).getNameToDraw()
                        + ROD + firstChipInRowColors.get(rightIndex).getNameToDraw()
                        + EMPTY_SPACE_WITH_BORDER + secondChipInRowColors.get(leftIndex).getNameToDraw()
                        + ROD + secondChipInRowColors.get(rightIndex).getNameToDraw()
                        + EMPTY_SPACE_WITH_BORDER + thirdChipInRowColors.get(leftIndex).getNameToDraw()
                        + ROD + thirdChipInRowColors.get(rightIndex).getNameToDraw()
                        + RIGHT_BORDER_SPACE);
    }
}
