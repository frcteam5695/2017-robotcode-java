package org.usfirst.frc.team5695.robot.component;

import org.usfirst.frc.team5695.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;

 public class MotorDriveCAN implements MotorDrive {

	private boolean init = false;
	private RobotDrive robotDrive;
	private CANTalon frontLeft, frontRight, backLeft, backRight;
	
	
	protected MotorDriveCAN(){}

	@Override
	public void init(){
		if(init){throw Robot.EXalreadyInit();}
		init = true;
		frontLeft = new CANTalon(0);
		backLeft = new CANTalon(3);
		
		(frontRight = new CANTalon(1)).setInverted(true);
		(backRight = new CANTalon(2)).setInverted(true);
				
		frontRight.setInverted(true);
		robotDrive = new RobotDrive(frontLeft, backLeft,frontRight,  backRight);
		
		
	}
	
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
	@Override
	public void drive(double strafe, double forward, double rotation){
		if(!init){throw Robot.EXnotInit();}
			robotDrive.mecanumDrive_Cartesian(strafe, -forward, rotation,0.0);
	}
	
	/***
	 * @param strafe the sideways motion of the drive [-1,1] 
	 * -1 is left & 1 is Right
	 * @param forward the forward motion of the drive [-1,1] 
	 * -1 is backwards
	 * 1  is forwards
	 * */
	@Override
	public void driveLat(double strafe, double forward){
		if(!init){throw Robot.EXnotInit();}
		drive(strafe,forward,0);
	}
	
	/***
	 * @param forward the forward motion of the drive [-1,1] 
	 * -1 is backwards
	 * 1  is forwards
	 * */
	@Override
	public void driveForward( double forward){
		if(!init){throw Robot.EXnotInit();}
		drive(0,forward,0);
	}
	
	/***
	 * @param strafe the sideways motion of the drive [-1,1] 
	 * -1 is left 
	 *  1 is Right
	 * */
	@Override
	public void driveStrafe( double strafe){
		if(!init){throw Robot.EXnotInit();}
		drive(strafe,0,0);
	}
	
	/**
	 *@param rotation [ -1.0,1.0]
	 *  -1  = Left
	 *  1 = right 
	 * **/
	@Override
	public void driveTurn(double rotation){
		if(!init){throw Robot.EXnotInit();}
		drive(0,0,rotation);
	
	}
	
	/**
	 * Stops all of the drive motors
	 * **/
	@Override
	public void halt(){
		drive(0,0,0);
	}
	
	/**
	 * 
	 * Tells the robot to strafe torwards a direction with a given speed.
	 * *****
	 * **/
	public void driveAngle(double angle, double speed){
		robotDrive.mecanumDrive_Polar(speed, angle,0);
	}
	
	@Override
	public void dispose() {
		if(!init){throw Robot.EXnotInit();}
		init = false;
		
		robotDrive.free();
		
		frontLeft = null;
		frontRight = null;
		backLeft = null;
		backRight = null;
		robotDrive = null;

	}

}
