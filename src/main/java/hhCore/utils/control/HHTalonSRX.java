package hhCore.utils.control;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class HHTalonSRX extends WPI_TalonSRX {
    public HHTalonSRX(int deviceNumber) {
        super(deviceNumber);

        resetConfig();
    }

    public void resetConfig() {
        this.configFactoryDefault();
    }
    
}