package frc.robot;

/**
 * @author Marius Juston
 **/
public class Config {

  public enum Timing {
    COKE(1), PEPSI(2.0);

    private final double v;

    Timing(double v) {
      this.v = v;
    }

    public double getTime() {
      return v;
    }
  }

  public static final class CanID {

    public static final int TURN_TABLE = 10;
    public static final int LINEAR_ACTUATOR = 1;
    public static final int CAN_SPINNER = 2;
    public static final int ARM_MOTOR = 3;
    public static final int MOTOR_VIBRATOR = 4;
    public static final int PDP = 5;

  }

  public static final class PCM {

    public static final int VACUUM = 4;
  }
}
