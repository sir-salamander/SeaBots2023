// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  // Talons
      Talon leftFrontTalon = new Talon(0);
      Talon rightFrontTalon = new Talon(1);
      Talon leftBackTalon = new Talon(2);
      Talon rightBackTalon = new Talon(3);

      // Diff Drive
      MotorControllerGroup leftMotors = new MotorControllerGroup(leftFrontTalon, leftBackTalon);
      MotorControllerGroup rightMotors = new MotorControllerGroup(rightFrontTalon, rightBackTalon);

      DifferentialDrive differentialDrive = new DifferentialDrive(leftMotors, rightMotors);

  

  XboxController xbox = new XboxController(0);
  


  public Drivetrain() {
   
  }

  public void arcadeDrive(double movespeed, double rotatespeed) {
    differentialDrive.arcadeDrive(xbox.getLeftY(), xbox.getRawAxis(2));
  }
    //gradle sucks 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
