package hhCore.utils.control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class HHTalonSRX extends WPI_TalonSRX {

    private ControlMode mControlMode;
    private double feedForwardValue;

    private int feedbackTimeout = 30; // ms

    private boolean inverted = false;

    /**
     * Base constructor
     * @param deviceNumber CAN ID of controller
     */
    public HHTalonSRX(int deviceNumber) {
        super(deviceNumber);
    }

    /**
     * Constructor for motor controller
     * @param deviceNumber device ID of motor controller
     * @param mControlMode
     */
    public HHTalonSRX(int deviceNumber, ControlMode mControlMode) {
        super(deviceNumber);
        this.mControlMode = mControlMode;
        this.configFactoryDefault();
    }

    /**
     * Set the invert state
     * @param invert invert state, defaults to false
     */
    public void setInverted(boolean invert) {
        this.inverted = invert;
    }

    /**
     * returns invert status of the motor
     * @return inverted
     */
    public boolean getInverted() {
        return this.inverted;
    }

    /**
     * returns the Feedback Timeout Value
     * @return feedBack timeout
     */
    public int getFeedbackTimeout() {
        return feedbackTimeout;
    }

    /**
     * set the Feedback Timeout value, defaults to 30
     * @param feedbackTimeout timeout in ms
     */
    public void setFeedbackTimeout(int feedbackTimeout) {
        this.feedbackTimeout = feedbackTimeout;
    }

}
