package hu.sch.rubicchip.utils;

import hu.sch.rubicchip.enums.Color;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ChipSide required component")
public record ChipSide(@Schema(description = "leftColor required field") Color leftColor,
                       @Schema(description = "rightColor required field") Color rightColor) {
}