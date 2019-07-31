/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Config.SmartDashboardKeys.Hardware.ARM_MOTOR_POSITION;
import static frc.robot.Config.SmartDashboardKeys.Hardware.ARM_MOTOR_POWER;
import static frc.robot.Config.SmartDashboardKeys.Hardware.CAN_SPINNER_POSITION;
import static frc.robot.Config.SmartDashboardKeys.Hardware.CAN_SPINNER_POWER;
import static frc.robot.Config.SmartDashboardKeys.Hardware.LINEAR_ACTUATOR_POWER;
import static frc.robot.Config.SmartDashboardKeys.Hardware.MOTOR_VIBRATOR;
import static frc.robot.Config.SmartDashboardKeys.Hardware.VACUUM;
import static frc.robot.Config.SmartDashboardKeys.UserEditable.ARM_END_POSITION;
import static frc.robot.Config.SmartDashboardKeys.UserEditable.ARM_START_POSITION;
import static frc.robot.Config.SmartDashboardKeys.UserEditable.LINEAR_ACTUATOR;
import static frc.robot.Config.SmartDashboardKeys.UserEditable.LINEAR_ACTUATOR_MOVE_DOWN_TIMEOUT;
import static frc.robot.Config.SmartDashboardKeys.UserEditable.LINEAR_ACTUATOR_MOVE_UP_TIMEOUT;
import static frc.robot.Config.SmartDashboardKeys.UserEditable.ROTATE_CAN_0_DEGREES;
import static frc.robot.Config.SmartDashboardKeys.UserEditable.ROTATE_CAN_180_DEGREES;
import static frc.robot.Config.SmartDashboardKeys.UserEditable.TOLERANCE;
import static frc.robot.Config.SmartDashboardKeys.UserEditable.TOTAL_ROTATION_TIME;
import static frc.robot.Config.SmartDashboardKeys.UserEditable.TOTAL_VIBRATION_TIME;
import static frc.robot.Config.SmartDashboardKeys.UserEditable.TURRET_END_POSITION;
import static frc.robot.Config.SmartDashboardKeys.UserEditable.TURRET_START_POSITION;
import static frc.robot.Hardware.armMotor;
import static frc.robot.Hardware.canSpinner;
import static frc.robot.Hardware.linearActuator;
import static frc.robot.Hardware.motorVibrator;
import static frc.robot.Hardware.vacuum;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Config.Timing;
import frc.robot.command.MoveArm;
import frc.robot.command.MoveLinearActuator;
import frc.robot.command.RotateCan;
import frc.robot.command.SetVacuum;
import frc.robot.command.VibrateCan;
import frc.robot.subsystem.GodSubsystem;

//import static frc.robot.Hardware.vacuum;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as
 * described in the TimedRobot documentation. If you change the name of this class or the package after creating this
 * project, you must also update the build.gradle file in the project.
 */
public class Robot extends TimedRobot {

  public static final GodSubsystem GOD_SUBSYSTEM = new GodSubsystem();


  private CommandGroup fullSequence;

  /**
   * This function is run when the robot is first started up and should be used for any initialization code.
   */
  @Override
  public void robotInit() {
    DriverStation.getInstance().InAutonomous(true);
    Hardware.init();

    initShuffleboard();

    if (isSimulation()) {
      initSequencing();
    }
  }

  @Override
  public void disabledInit() {
    System.out.println("Disabled Init");
  }

  private void initSequencing() {
    fullSequence = new CommandGroup("Full Sequence");

    double moveDownTimeout = SmartDashboard.getNumber(LINEAR_ACTUATOR_MOVE_DOWN_TIMEOUT, 2.75);
    double moveUpTimeout = SmartDashboard.getNumber(LINEAR_ACTUATOR_MOVE_UP_TIMEOUT, 2.75);

    double rotatingCanTimeout = SmartDashboard.getNumber(TOTAL_ROTATION_TIME, 120);
    double vibrateCanTimeout = SmartDashboard.getNumber(TOTAL_VIBRATION_TIME, 30);

    int armEndPosition = (int) SmartDashboard.getNumber(ARM_END_POSITION, -470);
    int armStartPosition = (int) SmartDashboard.getNumber(ARM_START_POSITION, 70);

    fullSequence.addSequential(new MoveLinearActuator(2));
    fullSequence.addSequential(new SetVacuum(true));
    fullSequence.addSequential(new WaitCommand(1));
    fullSequence.addSequential(new MoveLinearActuator(2.5, true));
    fullSequence.addSequential(new MoveArm(armEndPosition));
    fullSequence.addSequential(new MoveLinearActuator(4.5));

    fullSequence.addSequential(new RotateCan(rotatingCanTimeout));
    fullSequence.addSequential(new MoveLinearActuator(4.6, true));
    fullSequence.addSequential(new MoveArm(armStartPosition));
    fullSequence.addSequential(new MoveLinearActuator(1.5));
    fullSequence.addSequential(new WaitCommand(1));
    fullSequence.addSequential(new SetVacuum(false));
    fullSequence.addSequential(new MoveLinearActuator(1.75, true));
    fullSequence.addSequential(new VibrateCan(vibrateCanTimeout));


    if (isSimulation()) {
      fullSequence.setRunWhenDisabled(true);
      fullSequence.start();
    }

  }

  private void initShuffleboard() {
    SendableChooser<Timing> beverageChooser = new SendableChooser<>();

    Timing[] timings = Timing.values();
    if (timings.length > 0) {
      beverageChooser.setDefaultOption(timings[0].name(), timings[0]);
    }

    for (int i = 1; i < timings.length; i++) {
      beverageChooser.addOption(timings[i].name(), timings[i]);
    }

    SmartDashboard.putData("Beverage Chooser", beverageChooser);

    SmartDashboard.putNumber(LINEAR_ACTUATOR_MOVE_DOWN_TIMEOUT, 2.75);
    SmartDashboard.putNumber(LINEAR_ACTUATOR_MOVE_UP_TIMEOUT, 2.75);
    SmartDashboard.putNumber(LINEAR_ACTUATOR, 1);

    SmartDashboard.putNumber(TOTAL_VIBRATION_TIME, 30);
    SmartDashboard.putNumber(TOTAL_ROTATION_TIME, 30);

    SmartDashboard.putNumber(TOLERANCE, 50);

    SmartDashboard.putNumber(TURRET_END_POSITION, 0);
    SmartDashboard.putNumber(TURRET_START_POSITION, 0);

    SmartDashboard.putNumber(ARM_END_POSITION, -440);
    SmartDashboard.putNumber(ARM_START_POSITION, 0);

    SmartDashboard.putNumber(ROTATE_CAN_180_DEGREES, 2055);
//    SmartDashboard.putNumber(ROTATE_CAN_180_DEGREES, 4110);
    SmartDashboard.putNumber(ROTATE_CAN_0_DEGREES, 25);

    getHardwareValues();
  }

  private void getHardwareValues() {
    SmartDashboard.putNumber(LINEAR_ACTUATOR_POWER, linearActuator.getMotorOutputPercent());

    SmartDashboard.putNumber(CAN_SPINNER_POWER, canSpinner.getMotorOutputPercent());
    SmartDashboard.putNumber(CAN_SPINNER_POSITION, canSpinner.getSelectedSensorPosition());

    SmartDashboard.putNumber(ARM_MOTOR_POWER, armMotor.getMotorOutputPercent());
    SmartDashboard.putNumber(ARM_MOTOR_POSITION, armMotor.getSelectedSensorPosition());

    SmartDashboard.putNumber(MOTOR_VIBRATOR, motorVibrator.getMotorOutputPercent());

    SmartDashboard.putNumber(VACUUM, vacuum.getMotorOutputPercent());

  }

  @Override
  public void robotPeriodic() {
    getHardwareValues();

    Scheduler.getInstance().run();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    System.out.println("Autonomous Init");
    initSequencing();

    fullSequence.start();
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    System.out.println("Teleop Init");

//    new RotateCan(10).start();

//    new MoveLinearActuator(1.5).start();
//    new SetVacuum(true).start();

//    new MoveArm((int) SmartDashboard.getNumber(ARM_END_POSITION, -500)).start();

    initSequencing();

//    fullSequence.start();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testInit() {
    System.out.println("Test Init");
//    initSequencing();

//    new MoveArm((int) SmartDashboard.getNumber(ARM_END_POSITION, -600)).start();

//    fullSequence.start();
  }

  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }

}
