// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.ultraSonicSensor;


//https://3015rangerrobotics.github.io/pathplannerlib/PathplannerLib.json




/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kRedAuto = "Red Auto";
  private static final String kBlueAuto = "Blue Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_Chooser = new SendableChooser<>();
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  boolean runAuto = true;
  public boolean dAuto = false;
  

  //pneumatics
  //new compressor
  Compressor pcmCompressor;
  boolean pressureSwitch;
  double current;
  //solenoid
  DoubleSolenoid DoublePCM;
  // xbox
  public XboxController xbox = new XboxController(0);
  //timer 
  Timer timer  = new Timer();
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    CameraServer.startAutomaticCapture();
    m_robotContainer = new RobotContainer();
    m_Chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_Chooser.addOption("Red Auto", kRedAuto);
    m_Chooser.addOption("Blue Auto", kBlueAuto);

    SmartDashboard.putData("Auto Choices", m_Chooser);
    pcmCompressor = new Compressor(3, PneumaticsModuleType.CTREPCM);
    pcmCompressor.enableDigital();
    pressureSwitch = pcmCompressor.getPressureSwitchValue();
    current = pcmCompressor.getCurrent();
    DoublePCM  = new DoubleSolenoid(3, PneumaticsModuleType.CTREPCM, 1, 2);
  }
  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("Distance (volts)", ultraSonicSensor.getVoltage());
	  SmartDashboard.putNumber("Distance (real)", ultraSonicSensor.getDistance());
    SmartDashboard.putBoolean("pressureSwitch", pressureSwitch);
    SmartDashboard.putNumber("comp current", current);
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_Chooser.getSelected();
    System.out.println("Auto Selected: " + m_autoSelected);
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
    timer.reset();
    timer.start();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  if (timer.get() < 5) {
    RobotContainer.M_DRIVETRAIN.arcadeDrive(-.7, 0);
  }
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    //set start to reversed
    DoublePCM.set(Value.kReverse);
  } 

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
  // extend piston
    if (xbox.getRawButton(2)) {
    DoublePCM.set(Value.kForward);
    System.err.println("forward");
    }
    // retract piston
    if (xbox.getRawButton(1)) {
    DoublePCM.set(Value.kReverse);
    System.err.println("back");
    }
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
