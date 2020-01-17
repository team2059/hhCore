package hhCore.utils;

import hhCore.utils.sensor.HHColorSensor;

import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;

/**
 * Uses HHColorSensor to match incoming color values with passed in
 * colors. You are easily able to change what color the sensor trys
 * to match with and at what confidence level it should return.
 */
public class ColorMatcher {

    private final HHColorSensor colorSensor = new HHColorSensor();
    private final ColorMatch colorMatcher = new ColorMatch();

    private double confidenceThreshold;

    /**
     * Adds the target colors to the color matcher.
     * @param targetColors array of colors that you want to try to match against
     */
    public ColorMatcher(Color[] targetColors) {
        for (int i = 0; i < targetColors.length; i++) {
            colorMatcher.addColorMatch(targetColors[i]);
        }
    }

    /**
     * Adds the target colors to the color matcher and sets the confidence threshold.
     * @param targetColors array of colors that you want to try to match against
     * @param confidenceThreshold confidence threshold
     */
    public ColorMatcher(Color[] targetColors, double confidenceThreshold) {
        this(targetColors);
        this.confidenceThreshold = confidenceThreshold;

        colorMatcher.setConfidenceThreshold(confidenceThreshold);
    }

    /**
     * Compares the raw color from the sensor with the passed in target colors to find the closest
     * color that matches. You may need to turn on the builtin LED for better results
     * @return raw matched color value
     */
    public Color getColor() {
        final Color detectedColor = colorSensor.getColor();
        final ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

        return match.color;
    }

    /**
     * Gets the current confidence threshold 
     * @return the current confidence threshold
     */
    public double getConfidenceThreshold() {
        return confidenceThreshold;
    }

    /**
     * Gets the color sensor used by color matcher
     * @return the current color sensor
     */
    public HHColorSensor getColorSensor() {
        return colorSensor;
    }
}