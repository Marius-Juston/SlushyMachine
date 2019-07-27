package frc.robot.command;

import static frc.robot.Robot.GOD_SUBSYSTEM;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * @author Marius Juston
 **/
public class VibrateCan extends TimedCommand {

  public VibrateCan(double timeout) {
    super(timeout, GOD_SUBSYSTEM);
  }

  @Override
  protected void initialize() {
    GOD_SUBSYSTEM.setVibration(true);
  }

  @Override
  protected void end() {
    GOD_SUBSYSTEM.setVibration(false);
  }
}
