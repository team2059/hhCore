package hhCore.utils.input.controllers;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import hhCore.utils.input.AbstractInput;

public class ButtonBox extends AbstractInput {

  public ButtonBox(int port) {
    super(port);
  }

  @Override
  public JoystickButton[] getJoystickButtons() {
    return null;
  }
}
