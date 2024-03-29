package frc.robot.command;

import static frc.robot.Config.SmartDashboardKeys.UserEditable.LINEAR_ACTUATOR;
import static frc.robot.Robot.GOD_SUBSYSTEM;

import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Marius Juston
 **/
public class MoveLinearActuator extends TimedCommand {


  private final boolean goingUp;

  public MoveLinearActuator(double timeout, boolean goingUp) {
    super(timeout, GOD_SUBSYSTEM);

    this.goingUp = goingUp;
  }

  public MoveLinearActuator(double timeout) {
    this(timeout, false);
  }

  @Override
  protected void initialize() {
    GOD_SUBSYSTEM.setLinearActuator(0);
    System.out.println("Moving Linear Actuator");
  }

  @Override
  protected void execute() {

    double power = SmartDashboard.getNumber(LINEAR_ACTUATOR, 1);

    if (goingUp) {
      power = -power;
    }

    System.out.println("Setting actuator power " + power);
    GOD_SUBSYSTEM.setLinearActuator(power);

  }

  @Override
  protected void end() {
    System.out.printf("Stopping Linear Actuator %f%n", timeSinceInitialized());
    GOD_SUBSYSTEM.setLinearActuator(0);
  }
}
