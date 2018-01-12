package hhCore.drive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;

public abstract class HHSensorDrive extends HHDrive {
	private double correction = 0.1;

	public abstract AnalogGyro gyro();
	public abstract Encoder leftEncoder();
	public abstract Encoder rightEncoder();
	public abstract double getLeftEncoder();
	public abstract double getRightEncoder();
	public abstract double getGyro();

	public void resetLeftEncoder() {
		leftEncoder().reset();
	}

	public void resetRightEncoder() {
		rightEncoder().reset();
	}

	public void resetGyro() {
		gyro().reset();
	}

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
