package hu.sch.rubicchip.mappers;

import hu.sch.rubicchip.dtos.ChipsTableResponse;
import hu.sch.rubicchip.utils.ChipSide;
import org.mapstruct.Mapper;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface ChipMapper {

    ChipsTableResponse toFullDto(Long gameId, String table, Map<Integer, Map<Integer, ChipSide>> tableMap);
}
