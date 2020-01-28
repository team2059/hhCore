package hhCore.config;

public class RobotState {
  public enum GamePeriod {
    AUTO,
    TELEOP,
    TEST,
    DISABLED
  }

  public GamePeriod gamePeriod = GamePeriod.DISABLED;

  public RobotState() {}
}
