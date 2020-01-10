package hhCore.utils.sensor;

import edu.wpi.first.wpilibj.I2C;

import com.revrobotics.ColorSensorV3;

public class HHColorSensor extends ColorSensorV3 {

    public HHColorSensor() {
        super(I2C.Port.kOnboard);
    }
}