package hhCore.subsystems.drivebase;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.Timer;

public abstract class HHSensorDrive<T extends GyroBase> extends HHDrive {
	private double correction = 0.1;

	public abstract T gyro();
	public abstract Encoder leftEncoder();
	public abstract Encoder rightEncoder();
	public abstract double getLeftEncoder();
	public abstract double getRightEncoder();
	private double lastLeftEncoder = 0.0;
	private double lastRightEncoder = 0.0;
	private double lastLeftVelocity = 0.0;
	private double lastRightVelocity = 0.0;
	private double lastLeftAcceleration = 0.0;
	private double lastRightAcceleration = 0.0;
	private double lastTime = Timer.getFPGATimestamp();
	private double gyroOffset = 0.0;
	/**
	 * resetLeftEncoder() resets left encoder to zero count.
	 */
	public void resetLeftEncoder() {
		leftEncoder().reset();
	}

	/**
	 * resetRightEncoder() resets right encoder to zero count.
	 */
	public void resetRightEncoder() {
		rightEncoder().reset();
	}

	/**
	 * resetGyro() resets gyro to zero value.
	 */
	public void resetGyro() {
		gyro().reset();
	}

	/**
	 * setGyro() sets the gyro to a given value.
	 * @param angle angle of robot in degrees
	 */
	public void setGyro(double angle) {
		gyroOffset = getGyro() - angle;
	}

	/**
	 * getGyro() returns angle of the robot.
	 * @return angle of robot in degrees
	 */
	public double getGyro() {
		return (gyro().getAngle() % 360) - gyroOffset;
	}

	/**
	 * setCorrection() setter for correction value of driving.
	 * @param correction speed to correct the robot
	 */
	public void setCorrection(double correction) {
		this.correction = -correction;
	}

	/**
	 * driveForward() make the robot drive in a straight line with encoders.
	 * @param speed robot drive speed
	 */
	public void driveForward(double speed) {
		if (getRightEncoder() - getLeftEncoder() > 1) {
			pidDrive(-correction, speed);
		} else if (getLeftEncoder() - getRightEncoder() > 1) {
			pidDrive(correction, speed);
		} else {
			pidDrive(0, speed);
		}
	}

	/**
	 * init() add to robot init to initialize velocity and acceleration tracking.
	 */
	public void init() {
		lastTime = Timer.getFPGATimestamp();
		resetLeftEncoder();
		resetRightEncoder();
		lastLeftEncoder = 0.0;
		lastRightEncoder = 0.0;
		lastLeftVelocity = 0.0;
		lastRightVelocity = 0.0;
		lastLeftAcceleration = 0.0;
		lastRightAcceleration = 0.0;
	}

	/**
	 * update() add to robot periodic to update velocity and acceleration.
	 */
	public void update() {
		double currentTime = Timer.getFPGATimestamp();
		double dt = currentTime - lastTime;

		// Update velocity
		double dl = getLeftEncoder() - lastLeftEncoder;
		double dr = getRightEncoder()- lastRightEncoder;
		double lVelocity = dl/dt;
		double rVelocity = dr/dt;

		// Update acceleration
		double dla = lVelocity - lastLeftVelocity;
		double dra = rVelocity - lastRightVelocity;
		double lAcceleration = dla/dt;
		double rAcceleration = dra/dt;

		//Update with new values
		lastTime = currentTime;
		lastLeftEncoder = getLeftEncoder();
		lastRightEncoder = getRightEncoder();
		lastLeftVelocity = lVelocity;
		lastRightVelocity = rVelocity;
		lastLeftAcceleration = lAcceleration;
		lastRightAcceleration = rAcceleration;
	}

	/**
	 * getLeftVelocity() returns left side robot velocity.
	 * @return velocity of left side of robot
	 */
	public double getLeftVelocity() {
		return lastLeftVelocity;
	}

	/**
	 * getRightVelocity() returns right side robot velocity.
	 * @return velocity of right side of robot
	 */
	public double getRightVelocity() {
		return lastRightVelocity;
	}

	/**
	 * getLeftAcceleration() returns left side robot acceleration.
	 * @return acceleration of left side of robot
	 */
	public double getLeftAcceleration() {
		return lastLeftAcceleration;
	}

	/**
	 * getRightAcceleration() returns right side robot acceleration.
	 * @return acceleration of right side of robot
	 */
	public double getRightAcceleration() {
		return lastRightAcceleration;
	}
}
