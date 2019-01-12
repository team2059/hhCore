package hhCore.pneumatics;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public abstract class HHPneumatics {
	
	public static void enableSolenoid(boolean state, DoubleSolenoid solenoid) {
		if (state) {
			solenoid.set(DoubleSolenoid.Value.kForward);
		} else {
			solenoid.set(DoubleSolenoid.Value.kReverse);
		}
	}
}
