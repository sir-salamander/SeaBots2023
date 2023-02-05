// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ultraSonicSensor extends SubsystemBase {
  /** Creates a new ultraSonicSensor. */
  //Creates MB1013 Sensor
  private static final AnalogInput mb1013 = new AnalogInput(0);

  //Turn Voltage into distance
  private static final double VOLTS_TO_DIST = 1.0;

  public static double getVoltage() {
    return mb1013.getVoltage();
  }

  public static double getDistance() {
    return getVoltage() * VOLTS_TO_DIST;
  }

  //smartdashboard updates
  public static void updateDashboard() {
    SmartDashboard.putNumber("Distance (volts)", getVoltage());
    SmartDashboard.putNumber("Distance (real)", getDistance());
  }
  
  public ultraSonicSensor() {
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
