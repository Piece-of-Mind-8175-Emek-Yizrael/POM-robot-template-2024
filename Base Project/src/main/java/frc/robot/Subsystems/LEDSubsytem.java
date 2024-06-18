package frc.robot.Subsystems;

import static frc.robot.Constants.LEDConstants.DEFAULT_BLINK_COUNT;
import static frc.robot.Constants.LEDConstants.DEFAULT_COLOR;
import static frc.robot.Constants.LEDConstants.LED_COUNT;
import static frc.robot.Constants.LEDConstants.LED_PWM_PORT;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSubsytem extends SubsystemBase {
    /**The singelton instance. */
    private static LEDSubsytem instance;

    private AddressableLED mLeds;
    private AddressableLEDBuffer mLedBuffer;

    /**Next starting value in rainbow iteration*/
    private int rainbowIndex;

    /**Constructs leds and sets them to chosen default color.
     * private to enforce singelton.
     */
    private LEDSubsytem(){
        mLeds = new AddressableLED(LED_PWM_PORT);
        mLedBuffer = new AddressableLEDBuffer(LED_COUNT);
        mLeds.setLength(LED_COUNT);
        mLeds.start();
        setLedsCommand(DEFAULT_COLOR);
    }

    /**
     * @return The singelton instance;
     */
    public static LEDSubsytem getInstance(){
        if(instance == null){
            instance = new LEDSubsytem();
        }
        return instance;
    }

    /**
     * Sets the leds to a specific color.
     * @param color The color to show.
     * @param count How many leds to show in that color.
     * @param offset How many LEDs to skip before starting.
     */
    public void set(Color color, int count, int offset){
        for(int i = offset; i < mLedBuffer.getLength() && i < offset + count; i++){
            mLedBuffer.setLED(i, color);
        }
        mLeds.setData(mLedBuffer);
    }

    /**
     * Sets the leds to a specific color.
     * @param color The color to show.
     */
    public void set(Color color){
        set(color, 0, 0);
    }

    /**
     * Sets the leds to a specific color.
     * @param color The color to show.
     * @param count How many leds to show in that color.
     */
    public void set(Color color, int count){
        set(color, count, 0);
    }

    /**Turns all the leds off */
    public void turnOff(){
        set(Color.kBlack);
    }
    /**
     * Turns LEDs off.
     * @param count How many LEDs to turn off.
     * @param offset How many LEDs to skip before starting.
     */
    public void turnOff(int count, int offset){
        set(Color.kBlack, count, offset);
    }

    /**
     * Split the LEDs to segments, and light each segment in different color. 
     * @param ledCount How many LEDs to include.
     * @param offset How many LEDs to skip before starting.
     * @param colors The colors and their amount.
     */
    public void split(int ledCount, int offset, Color... colors){
        int segment = ledCount / colors.length;
        for(int i = 0; i < colors.length; i++){
            set(colors[i], segment, i * segment);
        }
    }

    /**
     * Split the LEDs to segments, and light each segment in different color
     * @param colors Each color for 1 segment.
     */
    public void split(Color... colors){
        split(LED_COUNT, 0, colors);
    }

    /**Shows the LEDs as rainbow. each call the rainbow starts in the next color. */
    public void rainbow(){
        for (var i = 0; i < mLedBuffer.getLength(); i++) {
            final var hue = (rainbowIndex + (i * LED_COUNT / mLedBuffer.getLength())) % LED_COUNT;
            mLedBuffer.setHSV(i, hue, 255, 128);
        }
        rainbowIndex += 3;
        rainbowIndex %= LED_COUNT;
        mLeds.setData(mLedBuffer);
    }

    /**
     * Generates instant command that changes the leds color.
     * @param color Color to show.
     * @return The generated command.
     */
    public Command setLedsCommand(Color color){
        return runOnce(() -> set(color));
    }

    /**
     * Generates a command that shows the LEDs as moving rainbow.
     * @return The generated rainbow command.
     */
    public Command rainbowCommand(){
        return run(this::rainbow);
    }

    /**
     * Generates a command that splits the leds to segments as the amount of colors.
     * @param colors Each color shown in a segment.
     * @return The generated command.
     */
    public Command splitCommand(Color... colors){
        return runOnce(() -> split(colors));
    }
    /**
     * Generates a command that blink the LEDs.
     * @param color Color to blink
     * @param onTime How many time in seconds should be on. 
     * @param offTime How many time in seconds should be off.
     * @param blinks How many times should it blink, -1 for infinity.
     * @param nextColor What color to leave when command finishes. null for Default color.
     * @return The Command
     */
    public Command blinkCommand(Color color, double onTime, double offTime, int blinks, Color nextColor){
        return new Command() {
            boolean lastOn = false;
            Timer timer = new Timer();
            @Override
            public void initialize() {
                timer.restart();
                lastOn = false;
            }

            @Override
            public void execute() {
                double timeInCycle  = timer.get() % (onTime + offTime);
                if(timeInCycle < onTime && !lastOn){
                    set(color);
                    lastOn = true;
                }
                else if(timeInCycle > onTime && lastOn){
                    turnOff();
                    lastOn = false;
                }
            }
            @Override
            public void end(boolean interrupted) {
                set(nextColor != null ? nextColor : DEFAULT_COLOR);
            }

            @Override
            public boolean isFinished() {
                return blinks != -1 ? timer.get() > ((onTime + offTime) * blinks) : false;
            }
        };
    }

    /**
     * Generates a command that blink the LEDs and at the end shows default color.
     * @param color Color to blink
     * @param onTime How many time in seconds should be on. 
     * @param offTime How many time in seconds should be off.
     * @param blinks How many times should it blink, -1 for infinity.
     * @return The Command
     */

    public Command blinkCommand(Color color, double onTime, double offTime, int blinks){
        return blinkCommand(color, onTime, offTime, blinks, null);
    }
    /**
     * Generates a command that blink the LEDs and at the end shows default color.
     * @param color Color to blink
     * @param blinks How many times should it blink, -1 for infinity.
     * @return The Command
     */

    public Command blinkCommand(Color color, int blinks){
        return blinkCommand(color, 0.1, 0.1, blinks, null);
    }
    /**
     * Generates a command that blink the LEDs 5 times and at the end shows default color.
     * @param color Color to blink
     * @return The Command
     */

    public Command blinkCommand(Color color){
        return blinkCommand(color, DEFAULT_BLINK_COUNT);
    }
    
}
