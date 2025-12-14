package hu.sch.rubicchip.dtos;

import hu.sch.rubicchip.utils.ChipSide;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Chips request")
public class ChipsRequest {

    @NotNull(message = "First chip sides is required, like Map<Integer, ChipSide>")
    @Schema(description = "firstChipSides required field")
    private Map<Integer, ChipSide> firstChipSides;

    @NotNull(message = "Second chip sides is required, like Map<Integer, ChipSide>")
    @Schema(description = "secondChipSides required field")
    private Map<Integer, ChipSide> secondChipSides;

    @NotNull(message = "Third chip sides is required, like Map<Integer, ChipSide>")
    @Schema(description = "thirdChipSides required field")
    private Map<Integer, ChipSide> thirdChipSides;

    @NotNull(message = "Fourth chip sides is required, like Map<Integer, ChipSide>")
    @Schema(description = "fourthChipSides required field")
    private Map<Integer, ChipSide> fourthChipSides;

    @NotNull(message = "Fifth chip sides is required, like Map<Integer, ChipSide>")
    @Schema(description = "fifthChipSides required field")
    private Map<Integer, ChipSide> fifthChipSides;

    @NotNull(message = "Sixth chip sides is required, like Map<Integer, ChipSide>")
    @Schema(description = "sixthChipSides required field")
    private Map<Integer, ChipSide> sixthChipSides;

    @NotNull(message = "Seventh chip sides is required, like Map<Integer, ChipSide>")
    @Schema(description = "seventhChipSides required field")
    private Map<Integer, ChipSide> seventhChipSides;

    @NotNull(message = "Eighth chip sides is required, like Map<Integer, ChipSide>")
    @Schema(description = "eighthChipSides required field")
    private Map<Integer, ChipSide> eighthChipSides;

    @NotNull(message = "Ninth chip sides is required, like Map<Integer, ChipSide>")
    @Schema(description = "ninthChipSides required field")
    private Map<Integer, ChipSide> ninthChipSides;
}
