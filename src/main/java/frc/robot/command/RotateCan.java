package frc.robot.command;

import static frc.robot.Robot.GOD_SUBSYSTEM;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * @author Marius Juston
 **/
public class RotateCan extends TimedCommand {

  private final double numberOfCompleteRotation;

  public RotateCan(double rotatingCanTime, double numberOfCompleteRotation) {
    super(rotatingCanTime, GOD_SUBSYSTEM);
    this.numberOfCompleteRotation = numberOfCompleteRotation;
  }

  public RotateCan(double rotatingCanTime) {
    this(rotatingCanTime, 1);
  }

  @Override
  protected void execute() {
    super.execute();
  }

  @Override
  protected void end() {
    GOD_SUBSYSTEM.setRotateCan(0);
  }
}
