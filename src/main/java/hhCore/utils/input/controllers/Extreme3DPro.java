package hhCore.utils.input.controllers;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import hhCore.utils.input.AbstractInput;

public class Extreme3DPro extends AbstractInput {

    public JoystickButton[] joystickButtons = new JoystickButton[] {
            new JoystickButton(this, 1),
            new JoystickButton(this, 2),
            new JoystickButton(this, 3),
            new JoystickButton(this, 4),
            new JoystickButton(this, 5),
            new JoystickButton(this, 6),
            new JoystickButton(this, 7),
            new JoystickButton(this, 8),
            new JoystickButton(this, 9),
            new JoystickButton(this, 10),
            new JoystickButton(this, 11),
            new JoystickButton(this, 12)
    };

    public Extreme3DPro(int port) {
        super(port);
    }

    public double getRawX() {
        return this.getRawAxis(0);
    }

    public double getRawY() {
        return this.getRawAxis(1);
    }

    public double getRawZ() {
        return this.getRawAxis(2);
    }

    @Override
    public JoystickButton[] getJoystickButtons() {
        return joystickButtons;
    }
}