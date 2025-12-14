package hu.sch.rubicchip.services;

import hu.sch.rubicchip.utils.ChipSide;

import java.util.Map;

public interface DrawTableService {

    String getTable(Map<Integer, Map<Integer, ChipSide>> fullTableMap);
}
