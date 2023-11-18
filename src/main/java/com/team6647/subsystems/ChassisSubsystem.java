/**
 * Written by Juan Pablo Gutiérrez
 */
package com.team6647.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ChassisSubsystem extends SubsystemBase {

  private static WPI_VictorSPX frontLeft = new WPI_VictorSPX(0);
  private static WPI_VictorSPX frontRight = new WPI_VictorSPX(1);
  private static WPI_VictorSPX backLeft = new WPI_VictorSPX(2);
  private static WPI_VictorSPX backRight = new WPI_VictorSPX(3);

  private static MotorControllerGroup leftControllerGroup = new MotorControllerGroup(frontLeft, backLeft);
  private static MotorControllerGroup rightControllerGroup = new MotorControllerGroup(frontRight, backRight);

  private static DifferentialDrive chassis;

  private double leftSpeed, rightSpeed;

  /** Creates a new ChassisSubsystem. */
  public ChassisSubsystem() {
    // Restore all motors to factory defaults
    frontLeft.configFactoryDefault();
    frontRight.configFactoryDefault();

    backRight.configFactoryDefault();
    backLeft.configFactoryDefault();

    // Sets all motors to coast
    setMotorsIdleMode(NeutralMode.Coast);

    // Inverts one side of the motors to match directions
    leftControllerGroup.setInverted(true);

    // Declares differential drive, while referencing both motorController groups
    chassis = new DifferentialDrive(leftControllerGroup, rightControllerGroup);

  }

  @Override
  public void periodic() {
    publishData();
  }

  /* Set motor idle mode */
  private void setMotorsIdleMode(NeutralMode idleMode) {
    frontLeft.setNeutralMode(idleMode);
    frontRight.setNeutralMode(idleMode);
    backLeft.setNeutralMode(idleMode);
    frontLeft.setNeutralMode(idleMode);
  }

  // Publish to SmartDashboard for debugging
  private void publishData() {
    SmartDashboard.putNumber("LeftSpeed", leftSpeed);
    SmartDashboard.putNumber("RightSpeed", rightSpeed);
  }

  /* Different drive modes */
  public void tankDrive(double leftSpeed, double rightSpeed) {
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
    chassis.tankDrive(leftSpeed, rightSpeed);
  }

  public void arcadeDrive(double linearSpeed, double rotSpeed) {
    this.leftSpeed = linearSpeed;
    this.rightSpeed = rotSpeed;
    chassis.arcadeDrive(linearSpeed, rotSpeed);
  }

  public void curvatureDrive(double rightSpeed, double leftSpeed) {
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
    chassis.curvatureDrive(leftSpeed, rightSpeed, true);
  }
}
