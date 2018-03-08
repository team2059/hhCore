package hhCore.commands.drive;

import edu.wpi.first.wpilibj.command.PIDCommand;
import hhCore.subsystems.drive.HHSensorDrive;

public abstract class HHPIDDriveStraight<T extends HHSensorDrive> extends PIDCommand {

	public abstract T getDriveBase();
	private double maxSpeed = 1.0;

	/**
	 * Instantiates a {@link PIDCommand} that will use the given p, i and d values.
	 * @param inches the number of inches to drive
	 * @param name the name of the command
	 * @param p the proportional value
	 * @param i the integral value
	 * @param d the derivative value
	 */
	public HHPIDDriveStraight(double inches, String name, double p, double i, double d) {
		super(name, p, i, d);
		setPIDStart(inches);
	}

	/**
	 * Instantiates a {@link PIDCommand} that will use the given p, i and d values.  It will also space the time
	 * between PID loop calculations to be equal to the given period.
	 * @param inches the number of inches to drive
	 * @param name the name
	 * @param p the proportional value
	 * @param i the integral value
	 * @param d the derivative value
	 * @param period the time (in seconds) between calculations
	 */
	public HHPIDDriveStraight(double inches, String name, double p, double i, double d, double period) {
		super(name, p, i , d, period);
		setPIDStart(inches);
	}

	/**
	 * Instantiates a {@link PIDCommand} that will use the given p, i and d values.
	 * It will use the class name as its name.
	 * @param inches the number of inches to drive
	 * @param p the proportional value
	 * @param i the integral value
	 * @param d the derivative value
	 */
	public HHPIDDriveStraight(double inches, double p, double i, double d) {
		super(p, i, d);
		setPIDStart(inches);
	}

	/**
	 * Instantiates a {@link PIDCommand} that will use the given p, i and d values.
	 * It will use the class name as its name..
	 * It will also space the time
	 * between PID loop calculations to be equal to the given period.
	 * @param inches the number of inches to drive
	 * @param p the proportional value
	 * @param i the integral value
	 * @param d the derivative value
	 * @param period the time (in seconds) between calculations
	 */
	public HHPIDDriveStraight(double inches, double p, double i, double d, double period) {
		super(p, i, d, period);
		setPIDStart(inches);
	}

	public void setPIDStart(double inches) {
		setTimeout(inches/12);
		setSetpoint(inches);
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	protected void initialize() {
		getDriveBase().setIsPID(true);
		getDriveBase().resetLeftEncoder();
		getDriveBase().resetRightEncoder();
	}

	@Override
	protected double returnPIDInput() {
		return getDriveBase().getLeftEncoder();
	}

	@Override
	protected void usePIDOutput(double speed) {
		getDriveBase().driveForward(speed * maxSpeed);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(getSetpoint() - getPosition()) < 1;
	}

	protected void end() {
		getDriveBase().setIsPID(false);
		setTimeout(0);
	}

	protected void interrupted() {
		end();
	}
}