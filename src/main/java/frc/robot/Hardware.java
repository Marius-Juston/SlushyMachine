package frc.robot;

import static frc.robot.Config.CanID.ARM_MOTOR;
import static frc.robot.Config.CanID.CAN_SPINNER;
import static frc.robot.Config.CanID.LINEAR_ACTUATOR;
import static frc.robot.Config.CanID.MOTOR_VIBRATOR;
import static frc.robot.Config.CanID.VACUUM;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * @author Marius Juston
 **/
public class Hardware {

  public static final TalonSRX linearActuator = new TalonSRX(LINEAR_ACTUATOR);
  public static final TalonSRX canSpinner = new TalonSRX(CAN_SPINNER);
  public static final TalonSRX armMotor = new TalonSRX(ARM_MOTOR);
  public static final TalonSRX motorVibrator = new TalonSRX(MOTOR_VIBRATOR);
  public static final TalonSRX vacuum = new TalonSRX(VACUUM);


  public static void init() {
    linearActuator.setNeutralMode(NeutralMode.Brake);
    canSpinner.setNeutralMode(NeutralMode.Brake);
    armMotor.setNeutralMode(NeutralMode.Brake);
    motorVibrator.setNeutralMode(NeutralMode.Brake);

    armMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
    canSpinner.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

//    turnTable.setSensorPhase(false);
//    turnTable.setInverted(false);
    armMotor.setSensorPhase(false);
    armMotor.setInverted(false);
//    canSpinner.setSensorPhase(false);
//    canSpinner.setInverted(false);

    armMotor.setStatusFramePeriod(StatusFrame.Status_10_Targets, 10, 0);
    canSpinner.setStatusFramePeriod(StatusFrame.Status_1_General, 10, 0);

    linearActuator.configPeakOutputForward(1);
    linearActuator.configPeakOutputReverse(-1);

    canSpinner.configPeakOutputForward(1);
    canSpinner.configPeakOutputReverse(-1);

    armMotor.configPeakOutputForward(.25);
    armMotor.configPeakOutputReverse(-.25);
    armMotor.configClosedLoopPeakOutput(0, .25);

    motorVibrator.configPeakOutputForward(1);
    motorVibrator.configPeakOutputReverse(-1);

    armMotor.selectProfileSlot(0, 0);
    canSpinner.selectProfileSlot(0, 0);

    armMotor.config_kP(0, 1, 10);
    armMotor.config_kI(0, 0.0002, 10);
//    armMotor.config_kD(0, 0, 10);
//    armMotor.config_kF(0, 1.65533981, 10);
//    armMotor.configClosedLoopPeakOutput(0, 1);
//    armMotor.configMotionCruiseVelocity(618);
//    armMotor.configMotionAcceleration(1000);
    canSpinner.config_kP(0, .25, 10);

    canSpinner.setSensorPhase(true);
    canSpinner.setInverted(false);

//    armMotor.configForwardSoftLimitThreshold();
//    armMotor.configReverseSoftLimitThreshold();
    armMotor.configReverseSoftLimitEnable(false, 10);
    armMotor.configForwardSoftLimitEnable(false, 10);
//
//    canSpinner.configForwardSoftLimitThreshold();
//    canSpinner.configReverseSoftLimitThreshold();
    canSpinner.configReverseSoftLimitEnable(false, 10);
    canSpinner.configForwardSoftLimitEnable(false, 10);

    armMotor.setSelectedSensorPosition(0);
    canSpinner.setSelectedSensorPosition(0);

  }
}
