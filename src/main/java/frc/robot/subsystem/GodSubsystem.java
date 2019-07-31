package frc.robot.subsystem;

import static frc.robot.Hardware.canSpinner;
import static frc.robot.Hardware.linearActuator;
import static frc.robot.Hardware.motorVibrator;
import static frc.robot.Hardware.vacuum;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import static frc.robot.Hardware.vacuum;

/**
 * @author Marius Juston
 **/
public class GodSubsystem extends Subsystem {

  public static final String VIBRATING_MOTOR_POWER = "Vibrating motor power";

  public GodSubsystem() {
    super("God Subsystem");
  }

  @Override
  protected void initDefaultCommand() {

  }

  public void setLinearActuator(double power) {
    linearActuator.set(ControlMode.PercentOutput, power);
  }

  public void setVacuum(boolean isActive) {
    vacuum.set(ControlMode.PercentOutput, isActive ? 1 : -1);
  }

  public void setVibration(boolean isVibrating) {
    motorVibrator.set(
        ControlMode.PercentOutput,
        isVibrating ? SmartDashboard.getNumber(VIBRATING_MOTOR_POWER, .25) : 0
    );
  }

  public void setRotateCan(int power) {
    canSpinner.set(ControlMode.PercentOutput, power);
  }
}
