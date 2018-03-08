package hhCore.subsystems.drive;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class HHDrive extends Subsystem {

	private double xSensitivity = 0.0;
	private double ySensitivity = 0.0;
	private double zSensitivity = 0.0;
	private double xHighSpeed = 1.0;
	private double yHighSpeed = 1.0;
	private double zHighSpeed = 1.0;
	private double xLowSpeed = 0.5;
	private double yLowSpeed = 0.5;
	private double zLowSpeed = 0.5;
	private double deadzone = 0.0;
	private double x, y, z;
	private static boolean isPID = false;

	public abstract void driveBase(double x, double y);

	/**
	 * driveBase() allows for driving with a twist joystick.
	 * @param x x axis of joystick
	 * @param y y axis of joystick
	 * @param z z axis of joystick
	 */
	public void setIsPID(boolean b) {
		isPID = b;
	}
	
	public void pidDrive(double x, double y) {
		if (isPID == true) {
			driveBase(x, y);
			System.out.println("the x:" + x + " the y: " + y);
		}
	}
	
	public void driveBase(double x, double y, double z) {
		if (isPID == false) {
			driveBase(x + z, y);
		}
	}

	/**
	 * deadzoneDrive() allows for driving with a deadzone in the joystick.
	 * @param x x axis speed
	 * @param y	y axis speed
	 */
	private void deadzoneDrive(double x, double y) {
		this.x = (Math.abs(x) > deadzone) ? x : 0.0;
		this.y = (Math.abs(y) > deadzone) ? y : 0.0;
		driveBase(this.x, this.y);
	}

	/**
	 * deadzoneDrive() allows for driving with a deadzone in the joystick.
	 * @param x x axis speed
	 * @param y	y axis speed
	 * @param z z axis speed
	 */
	private void deadzoneDrive(double x, double y, double z) {
		this.x = (Math.abs(x) > deadzone) ? x : 0.0;
		this.y = (Math.abs(y) > deadzone) ? y : 0.0;
		this.z = (Math.abs(z) > deadzone) ? z : 0.0;
		driveBase(this.x, this.y, this.z);
	}

	/**
	 * sensitivityDrive() drive robot with adjusted sensitivity.
	 * @param x x axis speed
	 * @param y y axis speed
	 */
	private void sensitivityDrive(double x, double y) {
		deadzoneDrive(Control.sensitivity(x, xSensitivity), Control.sensitivity(y, ySensitivity));
	}

	/**
	 * sensitivityDrive() drive robot with adjusted sensitivity.
	 * @param x x axis speed
	 * @param y y axis speed
	 * @param z z axis speed
	 */
	private void sensitivityDrive(double x, double y, double z) {
		deadzoneDrive(Control.sensitivity(x,xSensitivity),
				Control.sensitivity(y,ySensitivity),
				Control.sensitivity(z,zSensitivity));
	}

	/**
	 * drive() drive the robot with deadzone, highspeed, and sensitivity.
	 * @param x x axis speed
	 * @param y y axis speed
	 */
	public void drive(double x, double y) {
		sensitivityDrive(x * xHighSpeed, y * yHighSpeed);
	}

	/**
	 * drive drive the robot with with deadzone, lowspeed, highspeed, and sensitivity.
	 * @param x x axis speed
	 * @param y y axis speed
	 * @param highspeed set highspeed
	 */
	public void drive(double x, double y, boolean highspeed) {
		if(highspeed) {
			sensitivityDrive(x * xHighSpeed, y * yHighSpeed);
		} else {
			sensitivityDrive(x * xLowSpeed, y * yLowSpeed);
		}
	}

	/**
	 * drive() drive the robot with deadzone, highspeed, and sensitivity.
	 * @param x x axis speed
	 * @param y y axis speed
	 * @param z z axis speed
	 */
	public void drive(double x, double y, double z) {
		sensitivityDrive(x * xHighSpeed, y * yHighSpeed, z * zHighSpeed);
	}

	/**
	 * drive drive the robot with with deadzone, lowspeed, highspeed, and sensitivity.
	 * @param x x axis speed
	 * @param y y axis speed
	 * @param z z axis speed
	 * @param highspeed set highspeed
	 */
	public void drive(double x, double y, double z, boolean highspeed) {
		if(highspeed) {
			sensitivityDrive(x * xHighSpeed, y * yHighSpeed, z * zHighSpeed);
		} else {
			sensitivityDrive(x * xLowSpeed, y * yLowSpeed, z * zLowSpeed);
		}
	}

	/**
	 * setxSensitivity() set non linear sensitivity for x axis.
	 * @param xSensitivity double from -1.0 to 1.0. Negative more sensitive at smaller values.
	 */
	public void setxSensitivity(double xSensitivity) {
		this.zSensitivity = xSensitivity;
	}

	/**
	 * setySensitivity() set non linear sensitivity for y axis.
	 * @param ySensitivity double from -1.0 to 1.0. Negative more sensitive at smaller values.
	 */
	public void setySensitivity(double ySensitivity) {
		this.ySensitivity = ySensitivity;
	}

	/**
	 * setzSensitivity() set non linear sensitivity for z axis.
	 * @param zSensitivity double from -1.0 to 1.0. Negative more sensitive at smaller values.
	 */
	public void setzSensitivity(double zSensitivity) {
		this.zSensitivity = zSensitivity;
	}

	/**
	 * setxHighSpeed() sets the max high speed value of x axis.
	 * @param xHighSpeed double from 0.0 to 1.0
	 */
	public void setxHighSpeed(double xHighSpeed) {
		this.xHighSpeed = xHighSpeed;
	}

	/**
	 * setyHighSpeed() sets the max high speed value of y axis.
	 * @param yHighSpeed double from 0.0 to 1.0
	 */
	public void setyHighSpeed(double yHighSpeed) {
		this.yHighSpeed = yHighSpeed;
	}

	/**
	 * setzHighSpeed() sets the max high speed value of z axis.
	 * @param zHighSpeed double from 0.0 to 1.0
	 */
	public void setzHighSpeed(double zHighSpeed) {
		this.zHighSpeed = zHighSpeed;
	}

	/**
	 * setxLowSpeed() sets the max low speed value of x axis.
	 * @param xLowSpeed double from 0.0 to 1.0
	 */
	public void setxLowSpeed(double xLowSpeed) {
		this.xLowSpeed = xLowSpeed;
	}

	/**
	 * setyLowSpeed() sets the max low speed value of y axis.
	 * @param yLowSpeed double from 0.0 to 1.0
	 */
	public void setyLowSpeed(double yLowSpeed) {
		this.yLowSpeed = yLowSpeed;
	}

	/**
	 * setzLowSpeed() sets the max low speed value of z axis.
	 * @param zLowSpeed double from 0.0 to 1.0
	 */
	public void setzLowSpeed(double zLowSpeed) {
		this.zLowSpeed = zLowSpeed;
	}

	/**
	 * setDeadzone() set the deadzone area for a joystick.
	 * @param deadzone double from 0.0 to 1.0
	 */
	public void setDeadzone(double deadzone) {
		this.deadzone = deadzone;
	}
}
