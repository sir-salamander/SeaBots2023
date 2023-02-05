// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/*This is for the arm and Grabber of the robot*/

public class arm_Grabber extends SubsystemBase {
  /** Creates a new arm_Grabber. */
  //arm motor
  Talon armMotorTalon = new Talon(0);
  //grabber motors
  Talon grabberLeftTalon = new Talon(1);
  Talon grabberRightTalon = new Talon(2);
  //arm encoder
  Encoder armEncoder = new Encoder(4, 5);

  public arm_Grabber() {
    
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
