package hhCore.utils;

public class Dampener {
  /**
   * positive() dampens motor speed for positive speeds.
   *
   * @param speed motor speed
   * @param position position of contraption
   * @param begin start point of dampening
   * @param end emd point of dampening
   * @return new motor speed
   */
  public static double positive(double speed, double position, double begin, double end) {
    if (speed > 0)
      return Math.max(
          Math.min((-position / (end - begin) + (end / (end - begin))) * speed, speed), 0.0);
    else return speed;
  }

  /**
   * negative() dampens motor speed for negative speeds.
   *
   * @param speed motor speed
   * @param position position of contraption
   * @param begin start point of dampening
   * @param end emd point of dampening
   * @return new motor speed
   */
  public static double negative(double speed, double position, double begin, double end) {
    if (speed < 0)
      return Math.min(
          Math.max((-position / (end - begin) + (end / (end - begin))) * speed, speed), 0.0);
    else return speed;
  }

  /**
   * bidirectional() dampen motor speed in both directions.
   *
   * @param speed motor speed
   * @param position position of contraption
   * @param beginPositive beginning of top dampening point
   * @param endPositive end of top damping point
   * @param beginNegative beginning of bottom dampening point
   * @param endNegative end of top damping point
   * @return new motor speed
   */
  public static double bidirectional(
      double speed,
      double position,
      double beginPositive,
      double endPositive,
      double beginNegative,
      double endNegative) {
    return positive(
        negative(speed, position, beginNegative, endNegative),
        position,
        beginPositive,
        endPositive);
  }
}
