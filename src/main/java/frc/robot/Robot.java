// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
//import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


// Mahesh 2023 Mecanum Test drive codebase with Talon SRX
public class Robot extends TimedRobot {
  private static final int kFrontLeftChannel = 20;// 2;
  private static final int kRearLeftChannel = 2;// 3;
  private static final int kFrontRightChannel = 8; //1;
  private static final int kRearRightChannel = 9; // 0;

  private static final int firstJoystickChannel = 0;
  private static final int secondJoystickChannel = 0;

  private MecanumDrive m_robotDrive;
  private Joystick m_stick;
  private Joystick m_stick2; 

  WPI_TalonSRX frontLeft ; // = new WPI_TalonSRX(kFrontLeftChannel);
    WPI_TalonSRX rearLeft ; // = new WPI_TalonSRX(kRearLeftChannel);
    WPI_TalonSRX frontRight ; // = new WPI_TalonSRX(kFrontRightChannel);
    WPI_TalonSRX rearRight ; // = new WPI_TalonSRX(kRearRightChannel);
    


  @Override 
  public void robotInit() {
    this.frontLeft = new WPI_TalonSRX(kFrontLeftChannel);
    this.rearLeft = new WPI_TalonSRX(kRearLeftChannel);
    this.frontRight = new WPI_TalonSRX(kFrontRightChannel);
    this.rearRight = new WPI_TalonSRX(kRearRightChannel);

    


    // Invert the right side motors.
    // You may need to change or remove this to match your robot.
    // frontRight.setInverted( false);
    // rearRight.setInverted(true);


    m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

    m_stick = new Joystick(firstJoystickChannel);
    m_stick2 = new Joystick(secondJoystickChannel); 
    
  }

  @Override
  public void teleopPeriodic() {
    // Use the joystick X axis for forward movement, Y axis for lateral
    // movement, and Z axis for rotation.
    

    //m_robotDrive.driveCartesian(-y, -x, -rx);
    SmartDashboard.putNumber("Cartesian Y", m_stick.getY()); 
    SmartDashboard.putNumber("Cartesian X", m_stick.getX()); 
    SmartDashboard.putNumber("Cartesian Z", m_stick.getZ()); 
    SmartDashboard.putNumber("Degrees", m_stick.getDirectionDegrees()); 
    SmartDashboard.putNumber("Radians ", m_stick.getDirectionRadians()); 
    SmartDashboard.putNumber("Magnitude ", m_stick.getMagnitude()); 
    SmartDashboard.putNumber("POV for Degrees : The POV angles start at 0 in the up direction, and increase clockwise (e.g. right is 90, upper-left is 315)  ", m_stick.getPOV()); 
    SmartDashboard.putNumber("POV Count  ", m_stick.getPOVCount() ); 

    
    m_robotDrive.driveCartesian(m_stick.getX(), m_stick.getY(), m_stick.getZ(),  Rotation2d.fromDegrees( m_stick.getDirectionDegrees())  );

    //m_robotDrive.drivePolar(x, null, rx);

  }
}
