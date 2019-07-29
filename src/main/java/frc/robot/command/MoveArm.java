package frc.robot.command;

import static frc.robot.Config.SmartDashboardKeys.UserEditable.TOLERANCE;
import static frc.robot.Hardware.armMotor;
import static frc.robot.Hardware.turnTable;
import static frc.robot.Robot.GOD_SUBSYSTEM;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Marius Juston
 **/
public class MoveArm extends Command {

  private final int target;

  public MoveArm(int target) {
    super(GOD_SUBSYSTEM);
    this.target = target;
  }

  @Override
  protected void initialize() {
    armMotor.set(ControlMode.Position, target);
    System.out.printf("Moving Arm to %d%n", target);
  }

  @Override
  protected void execute() {
    armMotor.set(ControlMode.MotionMagic, target);
  }

  @Override
  protected void end() {
    armMotor.set(ControlMode.PercentOutput, 0);
    System.out.printf("Stopping Arm. Arm Stopped at %d%n",armMotor.getSelectedSensorPosition());
  }

  @Override
  protected boolean isFinished() {
    return Math.abs(armMotor.getSelectedSensorPosition() - target) <= SmartDashboard.getNumber(TOLERANCE, 50);
  }
}
