package hhCore.utils.control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Wrapper around WPI_TalonSRX that includes a default motor configuration and makes
 * it easier to change the configuration of the motor. This is just a base configuration
 * for a CAN Talon SRX.
 */
public class HHTalonSRX extends WPI_TalonSRX {

    private ControlMode controlMode;
    private double feedForwardValue;

    private int feedbackTimeout = 30; // ms

    private boolean inverted = false;

    /**
     * Sets up the Talon SRX with the default settings on the device number provided.
     * @param deviceNumber CAN ID of controller
     */
    public HHTalonSRX(int deviceNumber) {
        super(deviceNumber);
    }

    /**
     * Sets up the Talon SRX with the default settings on the device number provided
     * and sets the control mode.
     * @param deviceNumber device ID of motor controller
     * @param controlMode set the motor controller control mode
     */
    public HHTalonSRX(int deviceNumber, ControlMode controlMode) {
        super(deviceNumber);
        this.controlMode = controlMode;
        this.configFactoryDefault();
    }

    /**
     * Set the invert state
     * @param invert invert state, defaults to false
     */
    public void setInverted(boolean invert) {
        this.inverted = invert;
        this.setInverted(invert);
    }

    /**
     * Returns invert status of the motor
     * @return inverted
     */
    public boolean getInverted() {
        return this.inverted;
    }

    /**
     * Returns the Feedback Timeout Value
     * @return feedBack timeout
     */
    public int getFeedbackTimeout() {
        return feedbackTimeout;
    }

    /**
     * Sets the Feedback Timeout value, defaults to 30
     * @param feedbackTimeout timeout in ms
     */
    public void setFeedbackTimeout(int feedbackTimeout) {
        this.feedbackTimeout = feedbackTimeout;
    }

}
