package frc.robot.POM_lib.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

public class MicroSwitch {
    DigitalInput input;
    boolean normallyOpen;
    public MicroSwitch(int channel, boolean isNormallyOpen){
        input = new DigitalInput(channel);
        this.normallyOpen = isNormallyOpen;
    }
    public MicroSwitch(int channel){
        this(channel, true);
    }
    public boolean get(){
        return normallyOpen ^ input.get(); //if normally open, reverse. else not. 
    }
    public DigitalInput getInput(){
        return input;
    }
}
