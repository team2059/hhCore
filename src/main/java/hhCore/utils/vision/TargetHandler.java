package hhCore.utils.vision;

public class TargetHandler {

    public static class VisionTarget {
        private double x_offset, y_offset;
        private double approx_distance;

        /**
         * Nested class for easier handling of vision targets
         * @param x x_offset provided from Limelight
         * @param y y_offset provided from Limelight
         */
        public VisionTarget(double x, double y) {
            this.x_offset = x;
            this.y_offset = y;
        }

        public double calculateDistance(double y_offsetAxis, double targetHeight, double robotHeight) {
            return (targetHeight - robotHeight) / Math.tan(y_offsetAxis);
        }
    }

}
