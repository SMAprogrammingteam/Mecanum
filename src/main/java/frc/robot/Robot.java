// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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
  private static final int kFrontRightChannel = 14; //1;
  private static final int kRearRightChannel = 9; // 0;

  private static final int kJoystickChannel = 0;

  private MecanumDrive m_robotDrive;
  private Joystick m_stick;
  WPI_TalonSRX frontLeft;
  WPI_TalonSRX rearLeft;// = new WPI_TalonSRX(kRearLeftChannel);
  WPI_TalonSRX frontRight;// = new WPI_TalonSRX(kFrontRightChannel);
  WPI_TalonSRX rearRight;// = new WPI_TalonSRX(kRearRightChannel);

  @Override 
  public void robotInit() {
    frontLeft = new WPI_TalonSRX(kFrontLeftChannel);
    rearLeft = new WPI_TalonSRX(kRearLeftChannel);
    frontRight = new WPI_TalonSRX(kFrontRightChannel);
    rearRight = new WPI_TalonSRX(kRearRightChannel);
    


    // Invert the right side motors.
    // You may need to change or remove this to match your robot.
    frontRight.setInverted( false);
    rearRight.setInverted(true);
  
    
    m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);

    m_stick = new Joystick(kJoystickChannel);
   }

  @Override
  public void teleopPeriodic() 
  {
    // Use the joystick X axis for forward movement, Y axis for lateral
    // movement, and Z axis for rotation.
    // m_robotDrive.driveCartesian(-m_stick.getY(), -m_stick.getX(), -m_stick.getZ());
    double sin, cos, max, x, y, z;
    x = m_stick.getX();
    y = m_stick.getY();
    z = m_stick.getZ();

    double theta = m_stick.getDirectionRadians();
    double power = m_stick.getMagnitude();

    //double theta = m_robotDrive.driveCartesian(-m_stick.getY(), -m_stick.getX(), -m_stick.getZ());
    sin = Math.sin(theta - Math.PI/4);
    cos = Math.cos(theta - Math.PI/4);
    // double theta = 1/(Math.tan(sin/cos));
    max = Math.max(Math.abs(sin), Math.abs(cos));
    
    double turn = 0;
    double lF = (power * (cos/max)) + turn;
    double rF = (power * (sin/max)) - turn;
    double lR = (power * (sin/max)) + turn;
    double rR = (power * (cos/max))  - turn;

    if ((power + Math.abs(turn)) > 1)
    {
      lF /= power + turn;
      rF /= power + turn;
      lR /= power + turn;
      rR /= power + turn;
    }

      this.frontLeft.set(lF);
      this.frontRight.set(rF);
      this.rearLeft.set(lR);
      this.rearRight.set(rR);
      
      //m_robotDrive.driveCartesian(-m_stick.getY(), -m_stick.getX(), -m_stick.getZ());

    SmartDashboard.putNumber("Cartesian Y Radian", m_stick.getDirectionRadians()); 
    SmartDashboard.putNumber("Cartesian X", m_stick.getX()); 
    SmartDashboard.putNumber("Cartesian Z", m_stick.getZ()); 
    SmartDashboard.putNumber("Directional Degrees", m_stick.getDirectionDegrees()); 
    SmartDashboard.putNumber("Directional Radians", m_stick.getDirectionRadians()); 
    SmartDashboard.putNumber("Magnitude", m_stick.getMagnitude()); 
    SmartDashboard.putNumber("Throttle", m_stick.getThrottle()); 

  }
}
