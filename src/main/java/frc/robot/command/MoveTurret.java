package frc.robot.command;

import static frc.robot.Robot.GOD_SUBSYSTEM;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Marius Juston
 **/
public class MoveTurret extends Command {

  public MoveTurret() {
    super(GOD_SUBSYSTEM);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
