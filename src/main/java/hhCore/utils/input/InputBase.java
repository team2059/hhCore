package hhCore.utils.input;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public abstract class InputBase {

    public abstract AbstractInput[] getJoysticks();

    public AbstractInput getJoystick(int joystick) {
        return getJoysticks()[joystick];
    }

    public JoystickButton getJoystickButton(int joystick, int button) {
        return getJoysticks()[joystick].getJoystickButtons()[button - 1];
    }
}