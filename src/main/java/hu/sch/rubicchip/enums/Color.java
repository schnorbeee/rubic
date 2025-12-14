package hu.sch.rubicchip.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Color {

    KEK("kek", "KéééK"),
    ZOLD("zold", "ZöölD"),
    PIROS("piros", "PiroS"),
    SARGA("sarga", "SárgA");

    private final String name;

    private final String nameToDraw;

    public static Color fromValue(String name) {
        return Arrays.stream(values())
                .filter(d -> d.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown color type: " + name));
    }
}
