package hhCore.utils.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class HHJoystick extends Joystick {

    private JoystickButton[] joystickButtons;

    private String joystickName;

    public HHJoystick(int port, int buttonCount, String name) {
        super(port);

        this.joystickName = name;

        for(int i = 0; i < buttonCount; i++) {
            joystickButtons[i] = new JoystickButton(this, i + 1);
        }
    }

    public HHJoystick(int port, int buttonCount) {
        this(port, buttonCount, null);
    }

    public HHJoystick(int port, String name) {
        this(port, 12, name);
    }

    public HHJoystick(int port) {
        this(port, 12,null);
    }

    public JoystickButton getJoystickButton(int button) {
        return joystickButtons[button - 1];
    }

    public String getJoystickName() {
        return joystickName;
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
}
