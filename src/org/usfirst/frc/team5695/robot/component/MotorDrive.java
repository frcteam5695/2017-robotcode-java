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
	
	public void drive(double x, double y, double rotation){
		if(!init){throw Robot.EXnotInit();}
			robotDrive.mecanumDrive_Cartesian(x, y, rotation,0.0);
	}
	
	public void driveLat(double x, double y){
		if(!init){throw Robot.EXnotInit();}
		drive(x,y,0);
	}
	
	public void driveTurn(double rotation){
		if(!init){throw Robot.EXnotInit();}
		drive(0,0,rotation);
	
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
