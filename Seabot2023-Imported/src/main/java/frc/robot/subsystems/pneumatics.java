// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class pneumatics extends SubsystemBase {
  /** Creates a new pneumatics. */
  //new compressor
  Compressor pcmCompressor = new Compressor(3, PneumaticsModuleType.CTREPCM);
  boolean enabled = pcmCompressor.isEnabled();
  boolean pressureSwitch = pcmCompressor.getPressureSwitchValue();
  double current = pcmCompressor.getCurrent();
  //solenoid
  DoubleSolenoid DoublePCM = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);
  // xbox
  private XboxController xbox = new XboxController(0);

  public pneumatics() {
    //set start to reversed
    DoublePCM.set(Value.kReverse);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // extend piston
    if (xbox.getRawButton(2)) {
    DoublePCM.set(Value.kForward);
    }
    // retract piston
    if (xbox.getRawButton(1)) {
    DoublePCM.set(Value.kReverse);
    }
  }
}
