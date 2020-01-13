package hhCore;

import hhCore.utils.input.HHJoystick;
import hhCore.utils.input.InputBase;

public class TestInputBase extends InputBase {

    private HHJoystick[] joysticks = new HHJoystick[] {
            new HHJoystick(0, "drive"),
            new HHJoystick(1, 6)
    };

    @Override
    public HHJoystick[] getJoysticks() {
        return joysticks;
    }

    TestInputBase() {
        // Define button functionality here...
        // getJoystickButton(1, 5).whileHeld(new Command);
        // getJoystickButton("drive", 2).whileHeld(new Command);
    }
}
