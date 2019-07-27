/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Config.Timing;
import frc.robot.command.MoveArm;
import frc.robot.command.MoveLinearActuator;
import frc.robot.command.MoveTurret;
import frc.robot.command.RotateCan;
import frc.robot.command.SetVacuum;
import frc.robot.command.VibrateCan;
import frc.robot.subsystem.GodSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as
 * described in the TimedRobot documentation. If you change the name of this class or the package after creating this
 * project, you must also update the build.gradle file in the project.
 */
public class Robot extends TimedRobot {

  public static final GodSubsystem GOD_SUBSYSTEM = new GodSubsystem();
  public static final String LINEAR_ACTUATOR_MOVE_DOWN_TIMEOUT = "Linear Actuator Move Down Timeout";
  public static final String LINEAR_ACTUATOR_MOVE_UP_TIMEOUT = "Linear Actuator Move Up Timeout";
  private CommandGroup fullSequence;

  /**
   * This function is run when the robot is first started up and should be used for any initialization code.
   */
  @Override
  public void robotInit() {
    DriverStation.getInstance().InAutonomous(true);
    Hardware.init();

    initShuffleboard();

    initSequencing();

    System.out.println("Hello");

  }

  @Override
  public void disabledInit() {
  }

  private void initSequencing() {
    fullSequence = new CommandGroup("Full Sequence");

    double moveDownTimeout = SmartDashboard.getNumber(LINEAR_ACTUATOR_MOVE_DOWN_TIMEOUT, 2);
    double moveUpTimeout = SmartDashboard.getNumber(LINEAR_ACTUATOR_MOVE_UP_TIMEOUT, 2);
    double rotatingCanTime = SmartDashboard.getNumber("Total Rotation Time", 120);

    fullSequence.addSequential(new MoveLinearActuator(moveDownTimeout));
    fullSequence.addSequential(new SetVacuum(true));
    fullSequence.addSequential(new MoveLinearActuator(moveUpTimeout, true));
    fullSequence.addSequential(new MoveTurret());
    fullSequence.addSequential(new MoveArm());
    fullSequence.addSequential(new MoveLinearActuator(moveDownTimeout));

    fullSequence.addSequential(new RotateCan(rotatingCanTime));
    fullSequence.addSequential(new MoveLinearActuator(moveUpTimeout, true));
    fullSequence.addSequential(new MoveArm());
    fullSequence.addSequential(new MoveTurret());
    fullSequence.addSequential(new MoveLinearActuator(moveDownTimeout));
    fullSequence.addSequential(new VibrateCan(30));

    fullSequence.addSequential(new SetVacuum(false));
    fullSequence.addSequential(new MoveLinearActuator(moveUpTimeout, true));

    fullSequence.setRunWhenDisabled(true);

  }

  private void initShuffleboard() {
    SendableChooser<Double> beverageChooser = new SendableChooser<>();

    Timing[] timings = Timing.values();
    for (Timing beverage : timings) {
      beverageChooser.addOption(beverage.name(), beverage.getTime());
    }

    if (timings.length > 0) {
      beverageChooser.setDefaultOption(timings[0].name(), timings[0].getTime());
    }

    SmartDashboard.putData("Beverage Chooser", beverageChooser);

    SmartDashboard.putNumber(LINEAR_ACTUATOR_MOVE_DOWN_TIMEOUT, 1.0);
    SmartDashboard.putNumber(LINEAR_ACTUATOR_MOVE_UP_TIMEOUT, 1.0);
  }


  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    System.out.println("Autonomous Init");

    fullSequence.start();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }

}
