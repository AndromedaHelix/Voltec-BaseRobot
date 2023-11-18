/**
 * Written by Juan Pablo Gutiérrez
 */

package com.team6647;

import com.team6647.subsystems.ChassisSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private ChassisSubsystem chassisSubsystem = new ChassisSubsystem();
  private CommandXboxController driverController1 = new CommandXboxController(0);

  SendableChooser<Command> driveChooser = new SendableChooser<>();

  private final Command tankDriveCommand = new RunCommand(
      () -> chassisSubsystem.tankDrive(driverController1.getLeftY(), driverController1.getRightY()),
      chassisSubsystem);

  private final Command arcadeDriveCommand = new RunCommand(
      () -> chassisSubsystem.arcadeDrive(driverController1.getLeftY(),
          -driverController1.getRightX()),
      chassisSubsystem);

  private final Command triggerDriveCommand = new RunCommand(
      () -> chassisSubsystem.arcadeDrive(
          (driverController1.getRightTriggerAxis() - driverController1.getLeftTriggerAxis()) * -1,
          driverController1.getLeftX() * -1),
      chassisSubsystem);

  public RobotContainer() {
    driveChooser.setDefaultOption("Tank Drive", tankDriveCommand);
    driveChooser.addOption("Arcade Drive", arcadeDriveCommand);
    driveChooser.addOption("Trigger Drive", triggerDriveCommand);
    SmartDashboard.putData(driveChooser);

    chassisSubsystem.setDefaultCommand(chooserCommand());

    configureBindings();
  }

  private void configureBindings() {
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }

  /* Retunrs driveChooser selected drive command */
  public Command chooserCommand() {
    return driveChooser.getSelected();
  }

}
