package hu.sch.rubicchip.services.impl;

import hu.sch.rubicchip.services.HeuristicsService;
import hu.sch.rubicchip.services.ResolverService;
import hu.sch.rubicchip.services.impl.shared.GeneralChipManipulator;
import hu.sch.rubicchip.utils.ChipSide;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ResolverServiceImpl extends GeneralChipManipulator implements ResolverService {

    private final HeuristicsService heuristicsService;

    @Autowired
    public ResolverServiceImpl(HeuristicsService heuristicsService) {
        this.heuristicsService = heuristicsService;
    }

    @Override
    public void solve(Map<Integer, Map<Integer, ChipSide>> fullTableMap) {
        int[][] placement = new int[3][3];
        List<Integer> chipIds = new ArrayList<>(fullTableMap.keySet());

        // Heuristics: sort the chips according to the fewest possible matches
        chipIds.sort(Comparator.comparingInt(integer -> heuristicsService.possibleConnections(integer, fullTableMap)));

        if (solveBoard(0, placement, chipIds, fullTableMap)) {
            applySolution(placement, fullTableMap);
            log.info("MEGTALÁLT MEGOLDÁS:");
        } else {
            log.info("NINCS MEGOLDÁS.");
        }
    }

    // Recursive algorithm with backtracking
    private boolean solveBoard(int index, int[][] placement, List<Integer> remainingChips,
            Map<Integer, Map<Integer, ChipSide>> fullTableMap) {
        if (index == 9) {
            return true;
        }
        int row = index / 3;
        int col = index % 3;
        for (Integer chipId : new ArrayList<>(remainingChips)) {
            Map<Integer, ChipSide> original = fullTableMap.get(chipId);
            Map<Integer, ChipSide> rotated = original;

            for (int rot = 0; rot < 4; rot++) {
                if (fits(row, col, placement, rotated, fullTableMap)) {
                    fullTableMap.put(chipId, rotated);
                    placement[row][col] = chipId;
                    remainingChips.remove(chipId);
                    if (solveBoard(index + 1, placement, remainingChips, fullTableMap)) {
                        return true;
                    }
                    remainingChips.add(chipId);
                    placement[row][col] = 0;
                    fullTableMap.put(chipId, original);
                }
                rotated = rotatePreview(rotated);
            }
        }
        return false;
    }

    private boolean fits(int row, int col, int[][] placement, Map<Integer, ChipSide> candidate,
            Map<Integer, Map<Integer, ChipSide>> fullTableMap) {
        // left neighbor check
        if (col > 0 && placement[row][col - 1] != 0) {
            int leftChipId = placement[row][col - 1];
            ChipSide leftNeighborRight = fullTableMap.get(leftChipId).get(2);
            ChipSide currentLeft = candidate.get(4);

            if (!matchesRight(leftNeighborRight, currentLeft)) {
                return false;
            }
        }

        // upper neighbor check
        if (row > 0 && placement[row - 1][col] != 0) {
            int topChipId = placement[row - 1][col];
            ChipSide topNeighborBottom = fullTableMap.get(topChipId).get(3);
            ChipSide currentTop = candidate.get(1);

            if (!matchesDown(topNeighborBottom, currentTop)) {
                return false;
            }
        }

        return true;
    }

    private void applySolution(int[][] placement, Map<Integer, Map<Integer, ChipSide>> fullTableMap) {
        Map<Integer, Map<Integer, ChipSide>> solved = new HashMap<>();

        int index = 1;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                int chipId = placement[r][c];
                solved.put(index++, fullTableMap.get(chipId));
            }
        }

        fullTableMap.putAll(solved);
    }
}