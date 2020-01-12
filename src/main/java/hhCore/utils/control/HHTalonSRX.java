package hhCore.utils.control;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

/**
 * Wrapper around WPI_TalonSRX that includes a default motor configuration and makes
 * it easier to change the configuration of the motor. This is intended to specifically
 * be used with motion magic, however, you can use it with regular control systems too.
 */
public class HHTalonSRX extends WPI_TalonSRX {

    private int feedbackTimeout = 30;
    private int PIDLoopIndex = 0;
    private int motorLoopIndex = 0;
    private int motorSlotIndex = 0;
    private int sensorPosition = 0;
    private int sensorCruiseVelocity = 35000;
    private int sensorAcceleration = 100000;

    private boolean inverted = true;

    private double kF = 0;
    private double kP = 0;
    private double kI = 0;
    private double kD = 0;

    /**
     * Sets up the Talon SRX with the default settings on the device number provided.
     * @param deviceNumber device number of the Talon SRX
     */
    public HHTalonSRX(int deviceNumber) {
        super(deviceNumber);

        // Clean out the default CTRE settings
        resetConfig();

        this.setInverted(inverted);

        // Set the peak and nominal outputs
        this.configNominalOutputForward(0, feedbackTimeout);
        this.configNominalOutputReverse(0, feedbackTimeout);
        this.configPeakOutputForward(1, feedbackTimeout);
        this.configPeakOutputReverse(-1, feedbackTimeout);

        // Set Motion Magic gains in slot0 and FPID values
        this.selectProfileSlot(motorSlotIndex, motorLoopIndex);
        this.config_kF(motorSlotIndex, kF, feedbackTimeout);
        this.config_kP(motorSlotIndex, kP, feedbackTimeout);
        this.config_kI(motorSlotIndex, kI, feedbackTimeout);
        this.config_kD(motorSlotIndex, kD, feedbackTimeout);

        this.setSelectedSensorPosition(sensorPosition, PIDLoopIndex, feedbackTimeout);

        // Set acceleration and cruise velocity
        this.configMotionCruiseVelocity(sensorCruiseVelocity, feedbackTimeout);
        this.configMotionAcceleration(sensorAcceleration, feedbackTimeout);

        setConfigSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0);
        setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic);
    }

    /**
     * Reset the configuration to the CTRE factory default
     */
    public void resetConfig() {
        this.configFactoryDefault();
    }


    /**
     * Sets the selected feedback sensor
     * @param device feedback device from CTRE
     */
    public void setConfigSelectedFeedbackSensor(FeedbackDevice device) {
        this.configSelectedFeedbackSensor(device, motorLoopIndex, feedbackTimeout);
    }

    /**
     * Sets the status frame period. You can have multiple status frames.
     * @param frame status frame from CTRE
     */
    public void setStatusFramePeriod(StatusFrameEnhanced frame) {
        this.setStatusFramePeriod(frame, 10, feedbackTimeout);
    }

    /**
     * Sets the feedback timeout used globally in the motor.
     * @param feedbackTimeout feedback timeout in milliseconds. Default: 30
     */
    public void setFeedbackTimeout(int feedbackTimeout) {
        this.feedbackTimeout = feedbackTimeout;
    }

    /**
     * Sets the PID loop index.
     * @param PIDLoopIndex PID loop index. Default: 0
     */
    public void setPIDLoopIndex(int PIDLoopIndex) {
        this.PIDLoopIndex = PIDLoopIndex;
    }
    

    /**
     * Set the motor loop index.
     * @param motorLoopIndex motor loop index. Default: 0
     */
    public void setMotorLoopIndex(int motorLoopIndex) {
        this.motorLoopIndex = motorLoopIndex;
    }

    /**
     * Set the motor slot index.
     * @param motorSlotIndex motor slot index. Default: 0
     */
    public void setMotorSlotIndex(int motorSlotIndex) {
        this.motorSlotIndex = motorSlotIndex;
    }

    /**
     * Set the sensor position.
     * @param sensorPosition sensor position. Default: 0
     */
    public void setSensorPosition(int sensorPosition) {
        this.sensorPosition = sensorPosition;
    }

    /**
     * Set the sensor cruise velocity.
     * @param sensorCruiseVelocity cruise velocity in raw sensor units per 100 ms. Default: 35000
     */
    public void setSensorCruiseVelocity(int sensorCruiseVelocity) {
        this.sensorCruiseVelocity = sensorCruiseVelocity;
    }

    /**
     * Set the sensor acceleration.
     * @param sensorAcceleration sensor acceleration in raw sensor units per 100 ms per second. Default: 100000
     */
    public void setSensorAcceleration(int sensorAcceleration) {
        this.sensorAcceleration = sensorAcceleration;
    }

    /**
     * Set the inverted state.
     * @param inverted inverted state. Default: true
     */
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    /**
     * Set the kF value.
     * @param kF kF value. Default: 0
     */
    public void setKF(double kF) {
        this.kF = kF;
    }

    /**
     * Set the kP value.
     * @param kP kP value. Default: 0
     */
    public void setKP(double kP) {
        this.kP = kP;
    }

    /**
     * Set the kI value.
     * @param kI kI value. Default: 0
     */
    public void setKI(double kI) {
        this.kI = kI;
    }

    /**
     * Set the kD value.
     * @param kD kD value. Default: 0
     */
    public void setKD(double kD) {
        this.kD = kD;
    }
 }