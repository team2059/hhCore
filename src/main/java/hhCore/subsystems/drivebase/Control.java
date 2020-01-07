package hhCore.subsystems.drivebase;

public class Control {
	public static double sensitivity(double raw, double constant) {
		return constant * Math.pow(raw, 3) + (1 - constant) * raw;
	}
}
