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

    //    Kezdeti becslések a heurisztikához
    //                          sides:  1.	    2.	    3.	    4.
    //           chips:   1. 	        S, K	P, K	Z, S	P, Z
    //                    2.            Z, P	S, K	Z, S	K, P
    //                    3.	        P, Z	K, P	Z, S	K, S
    //                    4.	        S, Z	K, P 	S, P	Z, K
    //                    5.	        S, Z	P, Z	K, S	P, K
    //                    6.	        S, Z	P, K	S, K	Z, P
    //                    7.	        P, S	K, Z	P, K	Z, S
    //                    8.	        S, K	P, Z	S, P	Z, K
    //                    9.	        K, S	Z, K	S, P	Z, P
    //
    //         Lehetséges oldalak száma és zárójelben csipek indexe (előfordulása)
    //              Kritikus kapcsolat: S, P && P, S és Z, K && K, Z
    //                    S, K = 4 (1, 2, 6, 8)       &&      K, S = 3 (3, 5, 9)
    //                    P, K = 4 (1, 5, 6, 7)       &&      K, P = 3 (2, 3, 4)
    //                    Z, S = 4 (1, 2, 3, 7)       &&      S, Z = 3 (4, 5, 6)
    //                    P, Z = 4 (1, 3, 5, 8)       &&      Z, P = 3 (2, 6, 9)
    //                    S, P = 3 (4, 8, 9)          &&      P, S = 1 (7)
    //                    Z, K = 3 (4, 8, 9)          &&      K, Z = 1 (7)

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

    private void initFullChipsSideMap(ChipsRequest chipsRequest) {
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