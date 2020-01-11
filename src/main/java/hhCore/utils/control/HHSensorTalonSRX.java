package hhCore.utils.control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import hhCore.config.Gains;

import java.util.Optional;

/**
 * Wrapper around WPI_TalonSRX that includes a default motor configuration and makes
 * it easier to change the configuration of the motor. This is intended to specifically
 * be used with motion magic, however, you can use it with regular control systems too.
 */
public class HHSensorTalonSRX extends HHTalonSRX {

    private int feedbackTimeout = 30;
    private int PIDLoopIndex = 0;
    private int motorLoopIndex = 0;
    private int motorSlotIndex = 0;
    private int sensorPosition = 0;

    private int maxSensorCruiseVelocity;
    private int maxSensorAcceleration;

    private Gains gains = new Gains(0, 0 , 0 , 0);

    /**
     * Sets up the Talon SRX with the default settings on the device number provided.
     * @param deviceNumber device number of the Talon SRX
     */
    public HHSensorTalonSRX(int deviceNumber, FeedbackDevice device, Boolean sensorPhase,
                            double kP, double kI, double kD, double kF, int maxVelocity, int maxAcceleration) {
        super(deviceNumber);

        // Clean out the default CTRE settings
        resetConfig();

        //config Feedback Device
        configSelectedFeedbackSensor(device);

        // Set the Sensor Phase
        setSensorPhase(sensorPhase);

        // Set the peak and nominal outputs
        this.configNominalOutputForward(0, feedbackTimeout);
        this.configNominalOutputReverse(0, feedbackTimeout);
        this.configPeakOutputForward(1, feedbackTimeout);
        this.configPeakOutputReverse(-1, feedbackTimeout);

        // Set Motion Magic gains in slot0 and FPID values
        this.selectProfileSlot(motorSlotIndex, motorLoopIndex);
        this.gains = new Gains(kP, kI, kD, kF);

        this.setSelectedSensorPosition(sensorPosition, PIDLoopIndex, feedbackTimeout);

        // Set acceleration and cruise velocity
        setSensorCruiseVelocity(maxVelocity);
        setSensorAcceleration(maxAcceleration);

        setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0);
        setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic);
    }


    /**
     * Reset the configuration to the CTRE factory default
     */
    public void resetConfig() {
        this.configFactoryDefault();
    }

    public void setGains(Gains gains) {
        this.config_kF(motorSlotIndex, gains.f, feedbackTimeout);
        this.config_kP(motorSlotIndex, gains.p, feedbackTimeout);
        this.config_kI(motorSlotIndex, gains.i, feedbackTimeout);
        this.config_kD(motorSlotIndex, gains.d, feedbackTimeout);
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
        this.maxSensorCruiseVelocity = sensorCruiseVelocity;
        configMotionCruiseVelocity(sensorCruiseVelocity);
    }

    /**
     * Set the sensor acceleration.
     * @param sensorAcceleration sensor acceleration in raw sensor units per 100 ms per second. Default: 100000
     */
    public void setSensorAcceleration(int sensorAcceleration) {
        this.maxSensorAcceleration = sensorAcceleration;
        configMotionAcceleration(sensorAcceleration);
    }

    /**
     * get the gains object for the motor controller
     * @return Gains object
     */
    public Gains getGains() {
        return gains;
    }
 }