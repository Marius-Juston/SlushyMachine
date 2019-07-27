package frc.robot;

import static frc.robot.Config.CanID.ARM_MOTOR;
import static frc.robot.Config.CanID.CAN_SPINNER;
import static frc.robot.Config.CanID.LINEAR_ACTUATOR;
import static frc.robot.Config.CanID.MOTOR_VIBRATOR;
import static frc.robot.Config.CanID.TURN_TABLE;
import static frc.robot.Config.PCM.VACUUM;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * @author Marius Juston
 **/
public class Hardware {

  public static final TalonSRX turntable = new TalonSRX(TURN_TABLE);
  public static final TalonSRX linearActuator = new TalonSRX(LINEAR_ACTUATOR);
  public static final TalonSRX canSpinner = new TalonSRX(CAN_SPINNER);
  public static final TalonSRX armMotor = new TalonSRX(ARM_MOTOR);
  public static final TalonSRX motorVibrator = new TalonSRX(MOTOR_VIBRATOR);
  public static final Solenoid vacuum = new Solenoid(VACUUM);


  public static void init() {

  }

}
