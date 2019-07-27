package frc.robot.command;

import static frc.robot.Robot.GOD_SUBSYSTEM;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * @author Marius Juston
 **/
public class SetVacuum extends InstantCommand {

  public SetVacuum(boolean isActive) {
    super(GOD_SUBSYSTEM, () -> {
      System.out.printf("Setting vacuum %b%n", isActive);
      GOD_SUBSYSTEM.setVacuum(isActive);
    });
  }
}
