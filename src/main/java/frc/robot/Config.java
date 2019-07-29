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
  }

  public static final class PCM {

    public static final int VACUUM = 4;
  }


  public static final class SmartDashboardKeys {


    public static class UserEditable {

      public static final String LINEAR_ACTUATOR_MOVE_DOWN_TIMEOUT = "UserEditable/Linear Actuator Move Down Timeout";
      public static final String LINEAR_ACTUATOR_MOVE_UP_TIMEOUT = "UserEditable/Linear Actuator Move Up Timeout";
      public static final String LINEAR_ACTUATOR = "UserEditable/Linear Actuator Speed";
      public static final String TOTAL_ROTATION_TIME = "UserEditable/Total Rotation Time";
      public static final String TOTAL_VIBRATION_TIME = "UserEditable/Total Vibration Time";
      public static final String TURRET_START_POSITION = "UserEditable/Turret Start Position";
      public static final String TURRET_END_POSITION = "UserEditable/Turret End Position";
      public static final String TOLERANCE = "UserEditable/Tolerance";
      public static final String ARM_START_POSITION = "UserEditable/Arm Start Position";
      public static final String ARM_END_POSITION = "UserEditable/Arm End Position";
      public static final String ROTATE_CAN_180_DEGREES = "UserEditable/Rotate Can 180 Degrees";
      public static final String ROTATE_CAN_0_DEGREES = "UserEditable/Rotate CAN 0 Degrees";

    }

    public static class Hardware {

      public static final String TURN_TABLE_POWER = "Hardware/TurnTable Power";
      public static final String TURN_TABLE_POSITION = "Hardware/TurnTable Position";
      public static final String LINEAR_ACTUATOR_POWER = "Hardware/Linear Actuator Power";
      public static final String CAN_SPINNER_POWER = "Hardware/CanSpinner Power";
      public static final String CAN_SPINNER_POSITION = "Hardware/CanSpinner Position";
      public static final String ARM_MOTOR_POWER = "Hardware/ArmMotor Power";
      public static final String ARM_MOTOR_POSITION = "Hardware/ArmMotor Position";
      public static final String MOTOR_VIBRATOR = "Hardware/MotorVibrator";
      public static final String VACUUM = "Hardware/Vacuum";

    }
  }
}
