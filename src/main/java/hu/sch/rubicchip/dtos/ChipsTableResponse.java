package hu.sch.rubicchip.dtos;

import hu.sch.rubicchip.utils.ChipSide;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Data
@ToString
@EqualsAndHashCode
@Schema(description = "Chips Table response")
public class ChipsTableResponse {

    @NotNull(message = "Game id is required")
    @Schema(description = "gameId required field")
    private Long gameId;

    @NotBlank(message = "Table is required")
    @Schema(description = "table required field")
    private String table;

    @NotNull(message = "Table into memory is required, like Map<Integer, Map<Integer, ChipSide>>")
    @Schema(description = "tableMap required field")
    private Map<Integer, Map<Integer, ChipSide>> tableMap;
}
