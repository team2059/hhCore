package hhCore.utils.control;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Wrapper around CANSparkMax that includes a default motor configuration and makes
 * it easier to change the configuration of the motor.
 */
public class HHSparkMax extends CANSparkMax {

    private int smartCurrentLimitValue = 40;

    private boolean inverted = false;

    /**
     * Sets up the CAN Spark Max with the default settings on the device number 
     * provided and sets the default motor type to brushless.
     * @param deviceNumber device number of the Talon SRX
     */
    public HHSparkMax(int deviceNumber) {
        super(deviceNumber, MotorType.kBrushless);

        resetConfig();
        this.setSmartCurrentLimit(smartCurrentLimitValue);
        this.setInverted(inverted);
    }

    /**
     * Reset the configuration to the Rev Robotics factory default.
     */
    public void resetConfig() {
        this.restoreFactoryDefaults();
    }

    /** 
     * Overrides the default motor type to brushed.
     */
    public void setMotorBrushed() {
        this.setMotorType(MotorType.kBrushed);
    }

    /**
     * Sets the smart current limit value.
     * @param smartCurrentLimitValue smart current limit. Default: 40
     */
    public void setSmartCurrentLimitValue(int smartCurrentLimitValue) {
        this.smartCurrentLimitValue = smartCurrentLimitValue;
    }

       /**
     * Set the inverted state.
     * @param inverted inverted state. Default: true
     */
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }
}