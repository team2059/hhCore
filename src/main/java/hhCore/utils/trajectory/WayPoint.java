package hhCore.utils.trajectory;

public class WayPoint {

  private CoordinatePoint point;
  private double curvatureAtPoint;
  private double TargetVelocityAtPoint;
  private double DistanceAlongPathAtPoint;

  public WayPoint(final CoordinatePoint p1) {
    this.point = p1;
  }

  public WayPoint(final double x, final double y) {
    this.point = new CoordinatePoint(x, y);
  }

  public CoordinatePoint getPoint() {
    return point;
  }

  public void setPoint(final CoordinatePoint point) {
    this.point = point;
  }

  public double getCurvatureAtPoint() {
    return curvatureAtPoint;
  }

  public void setCurvatureAtPoint(final double curvatureAtPoint) {
    this.curvatureAtPoint = curvatureAtPoint;
  }

  public double getTargetVelocityAtPoint() {
    return TargetVelocityAtPoint;
  }

  public void setTargetVelocityAtPoint(final double targetVelocityAtPoint) {
    TargetVelocityAtPoint = targetVelocityAtPoint;
  }

  public double getDistanceAlongPathAtPoint() {
    return DistanceAlongPathAtPoint;
  }

  public void setDistanceAlongPathAtPoint(final double distanceAlongPathAtPoint) {
    DistanceAlongPathAtPoint = distanceAlongPathAtPoint;
  }

  public String toString() {
    return ("Point:"
        + point.toString()
        + " CurvatureAtPoint:"
        + curvatureAtPoint
        + " TargetVelocityAtPoint:"
        + TargetVelocityAtPoint
        + " DistanceAlongPathAtPoint:"
        + DistanceAlongPathAtPoint);
  }
}
