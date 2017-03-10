package org.usfirst.frc.team5695.robot.component;

import org.usfirst.frc.team5695.robot.Robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;

 public class MotorDrive implements IElement {

	private boolean init = false;
	private RobotDrive robotDrive;
	private TalonSRX frontLeft, frontRight, backLeft, backRight;
	
	
	protected MotorDrive(){}

	public void init(){
		if(init){throw Robot.EXalreadyInit();}
		init = true;
		frontLeft = new TalonSRX(0);
		backLeft = new TalonSRX(3);
		
		(frontRight = new TalonSRX(1)).setInverted(true);
		(backRight = new TalonSRX(2)).setInverted(true);
				
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
	public void drive(double strafe, double forward, double rotation){
		if(!init){throw Robot.EXnotInit();}
			robotDrive.mecanumDrive_Cartesian(strafe, forward, rotation,0.0);
	}
	
	/***
	 * @param strafe the sideways motion of the drive [-1,1] 
	 * -1 is left & 1 is Right
	 * @param forward the forward motion of the drive [-1,1] 
	 * -1 is backwards
	 * 1  is forwards
	 * */
	public void driveLat(double strafe, double forward){
		if(!init){throw Robot.EXnotInit();}
		drive(strafe,forward,0);
	}
	
	/***
	 * @param forward the forward motion of the drive [-1,1] 
	 * -1 is backwards
	 * 1  is forwards
	 * */
	public void driveForward( double forward){
		if(!init){throw Robot.EXnotInit();}
		drive(0,forward,0);
	}
	
	/***
	 * @param strafe the sideways motion of the drive [-1,1] 
	 * -1 is left 
	 *  1 is Right
	 * */
	public void driveStrafe( double strafe){
		if(!init){throw Robot.EXnotInit();}
		drive(strafe,0,0);
	}
	
	/**
	 *@param rotation [ -1.0,1.0]
	 *  -1  = Left
	 *  1 = ri ght 
	 * **/
	public void driveTurn(double rotation){
		if(!init){throw Robot.EXnotInit();}
		drive(0,0,rotation);
	
	}
	
	/**
	 * Stops all of the drive motors
	 * **/
	public void halt(){
		drive(0,0,0);
	}
	
	@Override
	public void clean() {
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
