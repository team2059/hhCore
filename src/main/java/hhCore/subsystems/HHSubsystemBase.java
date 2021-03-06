package hhCore.subsystems;

import hhCore.config.RobotState;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class HHSubsystemBase extends SubsystemBase {
    private String mName;

    public HHSubsystemBase(String name) {
        super();
        this.mName = name;
    }

    public abstract void update(RobotState robotState);

    public String getName() {
        return mName;
    }

    @Override
    public String toString() {
        return mName;
    }

    public void stop() { }

    public String getStatus() {
        return null;
    }
}
