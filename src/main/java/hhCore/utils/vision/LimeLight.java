package hhCore.utils.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimeLight {

    private NetworkTable table;

    public LimeLight() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
    }

    /**
     * @return if limelight has found a target
     */
    public boolean isTargetFound() {
        return table.getEntry("tv").getDouble(0.0) != 0.0;
    }

    /**
     * @return crosshair offset from center on x axis
     */
    public double getHorizontalOffset() {
        return table.getEntry("tx").getDouble(0.0);
    }

    /**
     * @return crosshair offset from center on y axis
     */
    public double getVerticalOffset() {
        return table.getEntry("ty").getDouble(0.0);
    }



}
