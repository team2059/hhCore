package hhCore.utils.input;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public abstract class InputBase {

    public abstract AbstractInput[] getJoysticks();

    public AbstractInput[] joysticks = getJoysticks();

    public AbstractInput getJoystick(int joystick) {
        return joysticks[joystick];
    }

    public JoystickButton getJoystickButton(int joystick, int button) {
        return joysticks[joystick].joystickButtons[button - 1];
    }
}