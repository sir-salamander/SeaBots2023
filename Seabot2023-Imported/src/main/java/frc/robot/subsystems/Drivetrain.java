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
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  // Talons (Switched to SRX's)
      WPI_TalonSRX _rghtFront = new WPI_TalonSRX(1);
      WPI_VictorSPX _rghtFollower = new WPI_VictorSPX(10);
      WPI_TalonSRX _leftFront = new WPI_TalonSRX(2);
      WPI_VictorSPX _leftFollower = new WPI_VictorSPX(20);
  //arm motors
  Talon armMotorTalon = new Talon(2);
  Talon armMotorTalon2 = new Talon(3);
  //grabber motors
  Talon grabberLeftTalon = new Talon(0);
  Talon grabberRightTalon = new Talon(1);   
  //motorcontroller groups
  public MotorControllerGroup rightM = new MotorControllerGroup(_rghtFront, _rghtFollower);
  public MotorControllerGroup leftM = new MotorControllerGroup(_leftFront, _leftFollower);
  public MotorControllerGroup armM = new MotorControllerGroup(armMotorTalon, armMotorTalon2);
  //Diff Drive
      DifferentialDrive differentialDrive = new DifferentialDrive(leftM, rightM);
  //Encoders
      Encoder leftEncoder = new Encoder(0, 1);
      Encoder rightEncoder = new Encoder(2, 3);
  //ekkis Bokkis ekkis
      public XboxController xbox = new XboxController(0);
  


  public Drivetrain() {
    //invert grabber left
    grabberLeftTalon.setInverted(true);
    
    //Commit to smartDashboard encoder speeds
    SmartDashboard.putBoolean("Direction Left", leftEncoder.getDirection());
    SmartDashboard.putBoolean("Direction Right", rightEncoder.getDirection());
    SmartDashboard.putNumber("Distance Left", leftEncoder.getDistance());
    SmartDashboard.putNumber("Distance Right", rightEncoder.getDistance());
  }

  public void arcadeDrive(double movespeed, double rotatespeed) {
    differentialDrive.arcadeDrive(xbox.getRawAxis(2), xbox.getLeftY());
   //grabber
    if (xbox.getRawButton(8)) {
      grabberLeftTalon.set(.5);
      grabberRightTalon.set(.5);
    }
    else if (xbox.getRawButton (7)) {
      grabberLeftTalon.set(-.5);
      grabberRightTalon.set(-.5);
    }
    else {
      grabberLeftTalon.set(0);
      grabberRightTalon.set(0);
    }

    //arm
     if (xbox.getRawButton(1)) {
      armM.set(1);
    }
    else if (xbox.getRawButton(2)) {
      armM.set(-1);
    }
    else {
      armM.set(0);
    }
  }
    //gradle sucks 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }
}
