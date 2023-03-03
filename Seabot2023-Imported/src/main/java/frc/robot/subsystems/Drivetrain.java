// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  // Talons (Switched to SRX's)
      WPI_TalonSRX _rghtFront = new WPI_TalonSRX(1);
      WPI_VictorSPX _rghtFollower = new WPI_VictorSPX(10);
      WPI_TalonSRX _leftFront = new WPI_TalonSRX(2);
      WPI_VictorSPX _leftFollower = new WPI_VictorSPX(20);
      
  //motorcontroller groups
  public MotorControllerGroup rightM = new MotorControllerGroup(_rghtFront, _rghtFollower);
  public MotorControllerGroup leftM = new MotorControllerGroup(_leftFront, _leftFollower);
  //Diff Drive
      DifferentialDrive differentialDrive = new DifferentialDrive(leftM, rightM);
  //Encoders
      Encoder leftEncoder = new Encoder(0, 1);
      Encoder rightEncoder = new Encoder(2, 3);
  //ekkis Bokkis ekkis
      XboxController xbox = new XboxController(0);
  


  public Drivetrain() {
    //Commit to smartDashboard encoder speeds
    SmartDashboard.putBoolean("Direction Left", leftEncoder.getDirection());
    SmartDashboard.putBoolean("Direction Right", rightEncoder.getDirection());
    SmartDashboard.putNumber("Distance Left", leftEncoder.getDistance());
    SmartDashboard.putNumber("Distance Right", rightEncoder.getDistance());
    
  }

  public void arcadeDrive(double movespeed, double rotatespeed) {
    differentialDrive.arcadeDrive(xbox.getRawAxis(2), xbox.getLeftY());
  }
    //gradle sucks 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
