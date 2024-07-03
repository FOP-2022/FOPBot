package fopbot;

import lombok.Builder;
import org.jetbrains.annotations.Nullable;

import java.awt.Color;
import java.awt.Point;
import java.util.function.BiFunction;

/**
 * A color profile for drawing the world.
 *
 * @param backgroundColorLight  the background color in light mode
 * @param backgroundColorDark   the background color in dark mode
 * @param fieldColorLight       the default background color for {@link Field}s in light mode
 * @param fieldColorDark        the default background color for {@link Field}s in dark mode
 * @param customFieldColorPattern a function for creating custom field color patterns, like chess boards
 * @param outerBorderColorLight the color of the outer border in light mode
 * @param outerBorderColorDark  the color of the outer border in dark mode
 * @param innerBorderColorLight the color of the inner border in light mode (not wall)
 * @param innerBorderColorDark  the color of the inner border in dark mode (not wall)
 * @param wallColorLight        the color of {@link Wall}s in light mode
 * @param wallColorDark         the color of {@link Wall}s in dark mode
 * @param coinColorLight        the color of {@link Coin}s in light mode
 * @param coinColorDark         the color of {@link Coin}s in dark mode
 * @param blockColorLight       the color of {@link Block}s in light mode
 * @param blockColorDark        the color of {@link Block}s in dark mode
 */
@Builder(toBuilder = true)
public record ColorProfile(
    Color backgroundColorLight,
    Color backgroundColorDark,
    Color fieldColorLight,
    Color fieldColorDark,
    @Nullable BiFunction<ColorProfile, Point, Color> customFieldColorPattern,
    Color outerBorderColorLight,
    Color outerBorderColorDark,
    Color innerBorderColorLight,
    Color innerBorderColorDark,
    Color wallColorLight,
    Color wallColorDark,
    Color coinColorLight,
    Color coinColorDark,
    Color blockColorLight,
    Color blockColorDark
) {
    /**
     * The default color profile.
     */
    public static ColorProfile DEFAULT = ColorProfile.builder()
        .backgroundColorLight(Color.WHITE)
        .backgroundColorDark(Color.BLACK)
        .fieldColorLight(Color.LIGHT_GRAY)
        .fieldColorDark(new Color(25, 25, 30))
        .outerBorderColorLight(Color.BLACK)
        .outerBorderColorDark(Color.WHITE)
        .innerBorderColorLight(Color.GRAY)
        .innerBorderColorDark(new Color(60, 60, 60))
        .wallColorLight(Color.BLACK)
        .wallColorDark(Color.WHITE)
        .coinColorLight(Color.RED)
        .coinColorDark(new Color(255, 140, 26))
        .blockColorLight(Color.BLACK)
        .blockColorDark(Color.WHITE)
        .build();

    /**
     * Returns {@code true} if the world is in dark mode.
     *
     * @return {@code true} if the world is in dark mode
     * @see GuiPanel#isDarkMode()
     */
    private boolean isDarkMode() {
        return World.getGlobalWorld().getGuiPanel().isDarkMode();
    }

    /**
     * Returns the background color of the world.
     *
     * @return the background color of the world
     */
    public Color getBackgroundColor() {
        return isDarkMode() ? backgroundColorDark : backgroundColorLight;
    }

    /**
     * Returns the default background color for {@link Field}s.
     *
     * @param fieldPosition the position of the field
     * @return the default background color for {@link Field}s
     */
    public Color getFieldColor(final Point fieldPosition) {
        return customFieldColorPattern != null
               ? customFieldColorPattern.apply(this, fieldPosition)
               : (isDarkMode() ? fieldColorDark : fieldColorLight);
    }

    /**
     * Returns the color of the outer border.
     *
     * @return the color of the outer border
     */
    public Color getOuterBorderColor() {
        return isDarkMode() ? outerBorderColorDark : outerBorderColorLight;
    }

    /**
     * Returns the color of the inner border.
     *
     * @return the color of the inner border
     */
    public Color getInnerBorderColor() {
        return isDarkMode() ? innerBorderColorDark : innerBorderColorLight;
    }

    /**
     * Returns the color of {@link Wall}s.
     *
     * @return the color of {@link Wall}s
     */
    public Color getWallColor() {
        return isDarkMode() ? wallColorDark : wallColorLight;
    }

    /**
     * Returns the color of {@link Coin}s.
     *
     * @return the color of {@link Coin}s
     */
    public Color getCoinColor() {
        return isDarkMode() ? coinColorDark : coinColorLight;
    }

    /**
     * Returns the color of {@link Block}s.
     *
     * @return the color of {@link Block}s
     */
    public Color getBlockColor() {
        return isDarkMode() ? blockColorDark : blockColorLight;
    }
}
