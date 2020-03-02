package hhCore.utils.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.geometry.Translation2d;

public class LimeLight {

    private NetworkTable table;

    public LimeLight() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
    }

    public LimeLight(String key) {
        table = NetworkTableInstance.getDefault().getTable(key);
    }

    /**
     * @return Whether the limelight has any valid targets (0 or 1)
     */
    public boolean isTargetFound() {
        return table.getEntry("tv").getDouble(0.0) != 0.0;
    }

    /**
     * @return Horizontal Offset From Crosshair To Target (-27 degrees to 27
     *         degrees)
     */
    public double getYawToTarget() {
        return table.getEntry("tx").getDouble(0.0);
    }

    /**
     * @return Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5
     *         degrees)
     */
    public double getPitchToTarget() {
        return table.getEntry("ty").getDouble(0.0);
    }

    /**
     * @return Side length of shortest side of the fitted bounding box (pixels)
     */
    public double getTargetWidth() {
        return table.getEntry("tshort").getDouble(0.0);
    }

    /**
     * @return Side length of longest side of the fitted bounding box (pixels)
     */
    public double getTargetLength() {
        return table.getEntry("tlong").getDouble(0.0);
    }

    /**
     * @return Aspect ratio of width to height of the fitted bounding box
     */
    public double getTargetAspectRatio() {
        return getTargetWidth() / getTargetLength();
    }

    /**
     * @return Target Area (0% of image to 100% of image)
     */
    public double getTargetArea() {
        return table.getEntry("ta").getDouble(0.0);
    }

    /**
     * @return Skew or rotation (-90 degrees to 0 degrees)
     */
    public double getSkew() {
        return table.getEntry("ts").getDouble(0.0);
    }

    /**
     * @return The pipeline’s latency contribution (ms) Add at least 11ms for image
     *         capture latency.
     */
    public double getPipelineLatency() {
        return table.getEntry("tl").getDouble(0.0);
    }

    private void resetPipelineLatency() {
        table.getEntry("tl").setValue(0.0);
    }

    /**
     * @param pipeline Pipeline index 0-9. Note that this does nothing if the
     *                 limelight is set to override
     */
    public void setPipeline(int pipeline) {
        if (pipeline < 0) {
            throw new IllegalArgumentException("Pipeline can not be less than zero");
        } else if (pipeline > 9) {
            throw new IllegalArgumentException("Pipeline can not be greater than nine");
        }
        table.getEntry("pipeline").setValue(pipeline);
    }

    /**
     * @return Pipeline index 0-9
     */
    public int getPipeline() {
        return (int) table.getEntry("pipeline").getDouble(0.0);
    }

    // *************** Advanced Usage with Raw Contours *********************

    public double[] getCornerX() {
        return table.getEntry("tcornx").getDoubleArray(new double[0]);
    }

    public double[] getCornerY() {
        return table.getEntry("tcorny").getDoubleArray(new double[0]);
    }

    // SolvePnP outputs

    public double getPnPTranslationX() {
        return table.getEntry("camtran").getDoubleArray(new double[] { 0, 0, 0, 0, 0, 0 })[0];
    }

    public double getPnPTranslationY() {
        return table.getEntry("camtran").getDoubleArray(new double[] { 0, 0, 0, 0, 0, 0 })[1];
    }

    public double getPnPPitch() {
        return table.getEntry("camtran").getDoubleArray(new double[] { 0, 0, 0, 0, 0, 0 })[3];
    }

    public double getPnPYaw() {
        return table.getEntry("camtran").getDoubleArray(new double[] { 0, 0, 0, 0, 0, 0 })[4];
    }

    public void forceBlinkLED() {
        table.getEntry("ledMode").setNumber(2);
    }

    public void forceOffLED() {
        table.getEntry("ledMode").setNumber(1);
    }

    public void forceOnLED() {
        table.getEntry("ledMode").setNumber(3);
    }

    public void usePipelineLED() {
        table.getEntry("ledMode").setNumber(0);
    }

    public void setDriverMode() {
        table.getEntry("camMode").setNumber(1);
    }

    public void setVisionMode() {
        table.getEntry("camMode").setNumber(0);
    }

    /**
     * Describes a vision target (position and angle)
     */
    public static class VisionTarget {

        public Translation2d position;
        public double angle;

        public VisionTarget(Translation2d position, double angle) {
            this.position = position;
            this.angle = angle;
        }
    }
}
