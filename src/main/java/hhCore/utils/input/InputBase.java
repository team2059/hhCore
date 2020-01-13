package hhCore.utils.input;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public abstract class InputBase {

    public abstract HHJoystick[] getJoysticks();

    public HHJoystick getJoystick(int joystick) {
        return getJoysticks()[joystick];
    }

    public HHJoystick getJoystick(String name) {
        for(int i = 0; i < getJoysticks().length; i++) {
            if(getJoystick(i).getJoystickName().equals(name)) {
                return getJoystick(i);
            }
        }

        return null;
    }

    public JoystickButton getJoystickButton(int joystick, int button) {
        return getJoystick(joystick).getJoystickButton(button);
    }

    public JoystickButton getJoystickButton(String name, int button) {
        return getJoystick(name).getJoystickButton(button);
    }
}
