package hhCore.utils;

import hhCore.utils.sensor.HHColorSensor;

import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;

public class ColorMatcher {

    private final HHColorSensor colorSensor = new HHColorSensor();
    private final ColorMatch colorMatcher = new ColorMatch();

    private double confidenceThreshold = 0.80;

    public ColorMatcher(Color[] targetColors) {

        // Initialize target colors in the color matcher
        for (int i = 0; i < targetColors.length; i++) {
            colorMatcher.addColorMatch(targetColors[i]);
        }

        colorMatcher.setConfidenceThreshold(confidenceThreshold);
    }

    public Color getColor() {
        final Color detectedColor = colorSensor.getColor();
        final ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);

        return match.color;
    }

    public double getConfidenceThreshold() {
        return confidenceThreshold;
    }

    public void setConfidenceThreshold(double confidenceThreshold) {
        this.confidenceThreshold = confidenceThreshold;
    }
}