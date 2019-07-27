package frc.robot.command;

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

    double power = SmartDashboard.getNumber("Linear Actuator Power", .1);

    if (goingUp) {
      power = -power;
    }

    GOD_SUBSYSTEM.setLinearActuator(power);
  }

  @Override
  protected void end() {
    System.out.println("Stopping Linear Actuator");
    GOD_SUBSYSTEM.setLinearActuator(0);
  }
}
