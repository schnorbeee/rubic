package hu.sch.rubicchip.services;

import hu.sch.rubicchip.utils.ChipSide;

import java.util.Map;

public interface ResolverService {

    void solve(Map<Integer, Map<Integer, ChipSide>> fullTableMap);

    Map<Integer, ChipSide> rotatePreview(Map<Integer, ChipSide> original);
}
