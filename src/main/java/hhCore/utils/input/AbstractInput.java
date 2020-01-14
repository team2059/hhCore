package hhCore.utils.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public abstract class AbstractInput extends Joystick {

    public abstract JoystickButton[] getJoystickButtons();

    public AbstractInput(int port) {
        super(port);
    }

    public JoystickButton[] joystickButtons = getJoystickButtons();
}