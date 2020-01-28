package hhCore;

import hhCore.utils.input.AbstractInput;
import hhCore.utils.input.InputBase;
import hhCore.utils.input.controllers.ButtonBox;
import hhCore.utils.input.controllers.Extreme3DPro;

public class TestInputBase extends InputBase {

  @Override
  public AbstractInput[] getJoysticks() {
    return new AbstractInput[] {new Extreme3DPro(0), new ButtonBox(0)};
  }

  public TestInputBase() {
    getJoystick(0).getRawButton(0);
    //        getJoystickButton(0, 1).
    //        getJoystick(0).joystickButtons[0].whenPressed()
  }
}
