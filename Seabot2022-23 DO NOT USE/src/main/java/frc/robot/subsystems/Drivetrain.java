// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  // Talons (Switched to SRX's)
      WPI_TalonSRX _rghtFront = new WPI_TalonSRX(1);
      WPI_TalonSRX _rghtFollower = new WPI_TalonSRX(10);
      WPI_TalonSRX _leftFront = new WPI_TalonSRX(2);
      WPI_TalonSRX _leftFollower = new WPI_TalonSRX(20);
  //Diff Drive
      DifferentialDrive differentialDrive = new DifferentialDrive(_leftFront, _rghtFront);


  
  // ekkis Bokkis ekkis
  XboxController xbox = new XboxController(0);
  


  public Drivetrain() {
    
    _rghtFollower.follow(_rghtFront);
    _leftFollower.follow(_leftFront);
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
