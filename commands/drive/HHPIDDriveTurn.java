package hhCore.commands.drive;

import edu.wpi.first.wpilibj.command.PIDCommand;
import hhCore.subsystems.drive.HHSensorDrive;

public abstract class HHPIDDriveTurn<T extends HHSensorDrive> extends PIDCommand {

	public abstract T getDriveBase();
	private double maxSpeed = 1.0;

	/**
	 * Instantiates a {@link PIDCommand} that will use the given p, i and d values.
	 * @param angle the angle to drive to
	 * @param name the name of the command
	 * @param p the proportional value
	 * @param i the integral value
	 * @param d the derivative value
	 */
	public HHPIDDriveTurn(double angle, String name, double p, double i, double d) {
		super(name, p, i, d);
		setPIDStart(angle);
	}

	/**
	 * Instantiates a {@link PIDCommand} that will use the given p, i and d values.  It will also space the time
	 * between PID loop calculations to be equal to the given period.
	 * @param angle the angle to drive to
	 * @param name the name
	 * @param p the proportional value
	 * @param i the integral value
	 * @param d the derivative value
	 * @param period the time (in seconds) between calculations
	 */
	public HHPIDDriveTurn(double angle, String name, double p, double i, double d, double period) {
		super(name, p, i , d, period);
		setPIDStart(angle);
	}

	/**
	 * Instantiates a {@link PIDCommand} that will use the given p, i and d values.
	 * It will use the class name as its name.
	 * @param angle the angle to drive to
	 * @param p the proportional value
	 * @param i the integral value
	 * @param d the derivative value
	 */
	public HHPIDDriveTurn(double angle, double p, double i, double d) {
		super(p, i, d);
		setPIDStart(angle);
	}

	/**
	 * Instantiates a {@link PIDCommand} that will use the given p, i and d values.
	 * It will use the class name as its name..
	 * It will also space the time
	 * between PID loop calculations to be equal to the given period.
	 * @param angle the angle to drive to
	 * @param p the proportional value
	 * @param i the integral value
	 * @param d the derivative value
	 * @param period the time (in seconds) between calculations
	 */
	public HHPIDDriveTurn(double angle, double p, double i, double d, double period) {
		super(p, i, d, period);
		setPIDStart(angle);
	}

	public void setPIDStart(double angle) {
		setTimeout(angle/30);
		setSetpoint(angle);
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	@Override
	protected double returnPIDInput() {
		return getDriveBase().getGyro();
	}

	@Override
	protected void usePIDOutput(double speed) {
		getDriveBase().drive(speed * maxSpeed, 0);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(getSetpoint()-getPosition()) < 0.5 || isTimedOut();
	}

}
