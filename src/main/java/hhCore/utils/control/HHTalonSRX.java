package hhCore.utils.control;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

public class HHTalonSRX extends WPI_TalonSRX {

    private int feedbackTimeout = 30;
    private int PIDLoopIndex = 0;
    private int motorLoopIndex = 0;
    private int motorSlotIndex = 0;
    private int sensorPosition = 0;
    private int sensorCruiseVelocity = 35000;   // raw sensor units per 100 ms
    private int sensorAcceleration = 100000;    // raw sensor units per 100 ms per second

    private boolean inverted = true;

    private double kF = 0;
    private double kP = 0;
    private double kI = 0;
    private double kD = 0;

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

    public void resetConfig() {
        this.configFactoryDefault();
    }

    public void setConfigSelectedFeedbackSensor(FeedbackDevice device) {
        this.configSelectedFeedbackSensor(device, motorLoopIndex, feedbackTimeout);
    }

    public void setStatusFramePeriod(StatusFrameEnhanced frame) {
        this.setStatusFramePeriod(frame, 10, feedbackTimeout);
    }

    public void setFeedbackTimeout(int feedbackTimeout) {
        this.feedbackTimeout = feedbackTimeout;
    }

    public void setPIDLoopIndex(int PIDLoopIndex) {
        this.PIDLoopIndex = PIDLoopIndex;
    }

    public void setMotorLoopIndex(int motorLoopIndex) {
        this.motorLoopIndex = motorLoopIndex;
    }

    public void setMotorSlotIndex(int motorSlotIndex) {
        this.motorSlotIndex = motorSlotIndex;
    }

    public void setSensorPosition(int sensorPosition) {
        this.sensorPosition = sensorPosition;
    }

    public void setSensorCruiseVelocity(int sensorCruiseVelocity) {
        this.sensorCruiseVelocity = sensorCruiseVelocity;
    }

    public void setSensorAcceleration(int sensorAcceleration) {
        this.sensorAcceleration = sensorAcceleration;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public void setKF(double kF) {
        this.kF = kF;
    }

    public void setKP(double kP) {
        this.kP = kP;
    }

    public void setKI(double kI) {
        this.kI = kI;
    }

    public void setKD(double kD) {
        this.kD = kD;
    }
 }
