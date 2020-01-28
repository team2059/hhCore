package hhCore.utils.control;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import hhCore.config.Gains;

/**
 * Wrapper around WPI_TalonSRX that includes a default motor configuration and makes it easier to
 * change the configuration of the motor. This is intended to specifically be used with motion
 * magic, however, you can use it with regular control systems too.
 */
public class HHSensorTalonSRX extends HHTalonSRX {

  private int feedbackTimeout = 30;
  private int motorLoopIndex = 0;
  private int motorSlotIndex = 0;
  private int maxSensorCruiseVelocity;
  private int maxSensorAcceleration;

  private Gains gains = new Gains(0, 0, 0, 0);

  /**
   * Sets up the Talon SRX with the default settings on the device number provided.
   *
   * @param deviceNumber device number of Talon SRX
   * @param device feedback device
   * @param sensorPhase sensor phase
   * @param kP kP value
   * @param kI kI value
   * @param kD kD value
   * @param kF kF value
   * @param maxVelocity max sensor velocity
   * @param maxAcceleration max sensor acceleration
   */
  public HHSensorTalonSRX(
      int deviceNumber,
      FeedbackDevice device,
      Boolean sensorPhase,
      double kP,
      double kI,
      double kD,
      double kF,
      int maxVelocity,
      int maxAcceleration) {
    super(deviceNumber);

    // Clean out the default CTRE settings
    resetConfig();

    // Configure Feedback Device
    configSelectedFeedbackSensor(device);

    // Set the Sensor Phase
    setSensorPhase(sensorPhase);

    // Set the peak and nominal outputs
    this.configNominalOutputForward(0, feedbackTimeout);
    this.configNominalOutputReverse(0, feedbackTimeout);
    this.configPeakOutputForward(1, feedbackTimeout);
    this.configPeakOutputReverse(-1, feedbackTimeout);

    // Set Motion Magic gains in slot0 and FPID values
    this.selectProfileSlot(0, 0);
    this.gains = new Gains(kP, kI, kD, kF);

    this.setSelectedSensorPosition(0, 0, feedbackTimeout);

    // Set acceleration and cruise velocity
    setSensorCruiseVelocity(maxVelocity);
    setSensorAcceleration(maxAcceleration);

    setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0);
    setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic);
  }

  /** Reset the configuration to the CTRE factory default */
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
   *
   * @param device feedback device from CTRE
   */
  public void setConfigSelectedFeedbackSensor(FeedbackDevice device) {
    this.configSelectedFeedbackSensor(device, motorLoopIndex, feedbackTimeout);
  }

  /**
   * Sets the status frame period. You can have multiple status frames.
   *
   * @param frame status frame from CTRE
   */
  public void setStatusFramePeriod(StatusFrameEnhanced frame) {
    this.setStatusFramePeriod(frame, 10, feedbackTimeout);
  }

  /**
   * Sets the feedback timeout used globally in the motor.
   *
   * @param feedbackTimeout feedback timeout in milliseconds. Default: 30
   */
  public void setFeedbackTimeout(int feedbackTimeout) {
    this.feedbackTimeout = feedbackTimeout;
  }

  /**
   * Sets the sensor cruise velocity.
   *
   * @param sensorCruiseVelocity cruise velocity in raw sensor units per 100 ms.
   */
  public void setSensorCruiseVelocity(int sensorCruiseVelocity) {
    this.maxSensorCruiseVelocity = sensorCruiseVelocity;
    configMotionCruiseVelocity(sensorCruiseVelocity);
  }

  /**
   * Sets the sensor acceleration.
   *
   * @param sensorAcceleration sensor acceleration in raw sensor units per 100 ms per second.
   */
  public void setSensorAcceleration(int sensorAcceleration) {
    this.maxSensorAcceleration = sensorAcceleration;
    configMotionAcceleration(sensorAcceleration);
  }

  /**
   * Gets the gains object for the motor controller
   *
   * @return Gains object
   */
  public Gains getGains() {
    return gains;
  }
}
