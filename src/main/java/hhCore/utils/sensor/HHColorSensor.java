package hhCore.utils.sensor;

import edu.wpi.first.wpilibj.I2C;

import com.revrobotics.ColorSensorV3;

/**
 * Wrapper around the ColorSensorV3 that maps the port to the I2C port
 * on the RoboRio. This class is mainly used in ColorMatcher and isn't
 * meant to be invoked directly.
 */
public class HHColorSensor extends ColorSensorV3 {

    /**
     * Maps the ColorSesnorV3 to the I2C port.
     */
    public HHColorSensor() {
        super(I2C.Port.kOnboard);
    }
}