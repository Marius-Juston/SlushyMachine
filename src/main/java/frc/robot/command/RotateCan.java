package frc.robot.command;

import static frc.robot.Config.SmartDashboardKeys.UserEditable.ROTATE_CAN_0_DEGREES;
import static frc.robot.Config.SmartDashboardKeys.UserEditable.ROTATE_CAN_180_DEGREES;
import static frc.robot.Config.SmartDashboardKeys.UserEditable.TOLERANCE;
import static frc.robot.Hardware.canSpinner;
import static frc.robot.Robot.GOD_SUBSYSTEM;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Marius Juston
 **/
public class RotateCan extends TimedCommand {

  private boolean goingTo180Degrees = true;

  public RotateCan(double rotatingCanTime) {
    super(rotatingCanTime, GOD_SUBSYSTEM);
  }


  @Override
  protected void initialize() {
    GOD_SUBSYSTEM.setRotateCan(0);
    System.out.println("Rotating Can");
  }

  @Override
  protected void execute() {
    double target = SmartDashboard.getNumber(goingTo180Degrees ? ROTATE_CAN_180_DEGREES : ROTATE_CAN_0_DEGREES, 0);
    canSpinner.set(ControlMode.Position, target);

    if (Math.abs(canSpinner.getSelectedSensorPosition() - target) <= 200) {
      goingTo180Degrees = !goingTo180Degrees;
    }

    System.out.printf("Can Spinner output:%f target:%f%n ", canSpinner.getMotorOutputPercent(), target);
  }

  @Override
  protected void end() {
    GOD_SUBSYSTEM.setRotateCan(0);
    System.out.printf("Stop Rotating Can %f%n", timeSinceInitialized());
  }
}
