package hhCore.utils.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public abstract class InputBase {

    public abstract HHJoystick[] getJoysticks();

    public HHJoystick getJoystick(int joystick) {
        return getJoysticks()[joystick];
    }

    public HHJoystick getJoystick(String name) {
        HHJoystick joystick = null;

        for(int i = 0; i < getJoysticks().length; i++) {
            if (getJoystick(i).getJoystickName().equals(name)) {
                joystick = getJoystick(i);
            } else {
                throw new IllegalArgumentException(String.format("No Joystick %name", name));
            }
        }

        return joystick;
    }

    public JoystickButton getJoystickButton(int joystick, int button) {
        return getJoystick(joystick).getJoystickButton(button);
    }

    public JoystickButton getJoystickButton(String name, int button) {
        JoystickButton joystickButton = null;
        
        try {
            joystickButton = getJoystick(name).getJoystickButton(button);
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
        }

        return joystickButton;
    }
}
