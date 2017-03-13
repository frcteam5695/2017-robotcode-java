package org.usfirst.frc.team5695.robot.component;

import org.usfirst.frc.team5695.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;

 public interface MotorDrive extends IElement {



	public void init();
	
	/**
	 * Rotation[ -1.0,1.0]
	 * -1  = Left
	 *  1 = right 
	 * @param strafe the sideways motion of the drive [-1,1] 
	 * -1 is left 
	 *  1 is Right
	 * @param forward the forward motion of the drive [-1,1] 
	 * -1 is backwards
	 *  1  is forwards
	 * **/
	public void drive(double strafe, double forward, double rotation);
	
	/***
	 * @param strafe the sideways motion of the drive [-1,1] 
	 * -1 is left & 1 is Right
	 * @param forward the forward motion of the drive [-1,1] 
	 * -1 is backwards
	 * 1  is forwards
	 * */
	public void driveLat(double strafe, double forward);
	
	/***
	 * @param forward the forward motion of the drive [-1,1] 
	 * -1 is backwards
	 * 1  is forwards
	 * */
	public void driveForward( double forward);
	
	/***
	 * @param strafe the sideways motion of the drive [-1,1] 
	 * -1 is left 
	 *  1 is Right
	 * */
	public void driveStrafe( double strafe);
	/**
	 *@param rotation [ -1.0,1.0]
	 *  -1  = Left
	 *  1 = ri ght 
	 * **/
	public void driveTurn(double rotation);
	
	/**
	 * Stops all of the drive motors
	 * **/
	public void halt();
	
	@Override
	public void clean() ;

}
