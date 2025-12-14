package hu.sch.rubicchip.services.impl;

import hu.sch.rubicchip.dtos.ChipsRequest;
import hu.sch.rubicchip.dtos.ChipsTableResponse;
import hu.sch.rubicchip.mappers.ChipMapper;
import hu.sch.rubicchip.services.ChipService;
import hu.sch.rubicchip.services.DrawTableService;
import hu.sch.rubicchip.services.MixerService;
import hu.sch.rubicchip.services.ResolverService;
import hu.sch.rubicchip.utils.ChipSide;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class ChipServiceImpl implements ChipService {

    private final ChipMapper chipMapper;

    private final MixerService mixerService;

    private final ResolverService resolverService;

    private final DrawTableService drawTableService;

    private static final Map<Integer, Map<Integer, ChipSide>> FULL_TABLE_WITH_ORIENTATION = new ConcurrentHashMap<>();

    private static Long gameId = 0L;

    @Autowired
    private ChipServiceImpl(ChipMapper chipMapper, MixerService mixerService, ResolverService resolverService,
            DrawTableService drawTableService) {
        this.chipMapper = chipMapper;
        this.mixerService = mixerService;
        this.resolverService = resolverService;
        this.drawTableService = drawTableService;
    }

    @Override
    public ResponseEntity<ChipsTableResponse> startGame(ChipsRequest chipsRequest) {
        initFullChipsSideMap(chipsRequest);

        log.info("StartGame");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(chipMapper.toFullDto(gameId, drawTableService.getTable(FULL_TABLE_WITH_ORIENTATION),
                        FULL_TABLE_WITH_ORIENTATION));
    }

    @Override
    public ResponseEntity<ChipsTableResponse> shufflePositionAndOrientation() {
        mixerService.setRandomPosition(FULL_TABLE_WITH_ORIENTATION);
        mixerService.shuffleOrientations(FULL_TABLE_WITH_ORIENTATION);

        log.info("ShufflePositionAndOrientation");
        return ResponseEntity.ok(chipMapper.toFullDto(gameId, drawTableService.getTable(FULL_TABLE_WITH_ORIENTATION),
                FULL_TABLE_WITH_ORIENTATION));
    }

    @Override
    public ResponseEntity<ChipsTableResponse> solveRubic() {
        resolverService.solve(FULL_TABLE_WITH_ORIENTATION);

        log.info("SolveRubic");
        return ResponseEntity.ok(chipMapper.toFullDto(gameId, drawTableService.getTable(FULL_TABLE_WITH_ORIENTATION),
                FULL_TABLE_WITH_ORIENTATION));
    }

    private static void initFullChipsSideMap(ChipsRequest chipsRequest) {
        gameId++;
        FULL_TABLE_WITH_ORIENTATION.put(1, chipsRequest.getFirstChipSides());
        FULL_TABLE_WITH_ORIENTATION.put(2, chipsRequest.getSecondChipSides());
        FULL_TABLE_WITH_ORIENTATION.put(3, chipsRequest.getThirdChipSides());
        FULL_TABLE_WITH_ORIENTATION.put(4, chipsRequest.getFourthChipSides());
        FULL_TABLE_WITH_ORIENTATION.put(5, chipsRequest.getFifthChipSides());
        FULL_TABLE_WITH_ORIENTATION.put(6, chipsRequest.getSixthChipSides());
        FULL_TABLE_WITH_ORIENTATION.put(7, chipsRequest.getSeventhChipSides());
        FULL_TABLE_WITH_ORIENTATION.put(8, chipsRequest.getEighthChipSides());
        FULL_TABLE_WITH_ORIENTATION.put(9, chipsRequest.getNinthChipSides());
    }
}