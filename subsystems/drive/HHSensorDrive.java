package hhCore.subsystems.drive;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GyroBase;

public abstract class HHSensorDrive<T extends GyroBase> extends HHDrive {
	private double correction = 0.1;

	public abstract T gyro();
	public abstract Encoder leftEncoder();
	public abstract Encoder rightEncoder();
	public abstract double getLeftEncoder();
	public abstract double getRightEncoder();
	public abstract double getGyro();

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
	 * setCorrection() setter for correction value of driving.
	 * @param correction speed to correct the robot
	 */
	public void setCorrection(double correction) {
		this.correction = correction;
	}

	/**
	 * driveForward() make the robot drive in a straight line with encoders.
	 * @param speed robot drive speed
	 */
	public void driveForward(double speed) {
		if (getRightEncoder() - getLeftEncoder() > 1) {
			drive(correction, speed);
		} else if (getLeftEncoder() - getRightEncoder() > 1) {
			drive(-correction, speed);
		} else {
			drive(0, speed);
		}
	}
}
