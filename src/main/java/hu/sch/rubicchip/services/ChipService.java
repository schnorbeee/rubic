package hu.sch.rubicchip.services;

import hu.sch.rubicchip.dtos.ChipsRequest;
import hu.sch.rubicchip.dtos.ChipsTableResponse;
import org.springframework.http.ResponseEntity;

public interface ChipService {

    ResponseEntity<ChipsTableResponse> startGame(ChipsRequest chipsRequest);

    ResponseEntity<ChipsTableResponse> shufflePositionAndOrientation();

    ResponseEntity<ChipsTableResponse> solveRubic();
}
