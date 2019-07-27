package frc.robot.command;

import static frc.robot.Robot.GOD_SUBSYSTEM;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Marius Juston
 **/
public class MoveArm extends Command {

  public MoveArm() {
    super(GOD_SUBSYSTEM);
  }

  @Override
  protected void initialize() {
    System.out.println("Moving Arm");
  }

  @Override
  protected void end() {
    System.out.println("Stop Moving Arm");
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
