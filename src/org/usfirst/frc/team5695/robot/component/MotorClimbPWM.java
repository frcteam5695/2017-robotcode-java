package org.usfirst.frc.team5695.robot.component;

import org.usfirst.frc.team5695.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;

public class MotorClimbPWM implements MotorClimb {
	private boolean init = false;
	
	private TalonSRX left, right;
	private RobotDrive drive;
	
	protected MotorClimbPWM(){}
	@Override
	public void init(){
		if(init){throw Robot.EXalreadyInit();}
		init = true;
	left = new TalonSRX(4);
	right = new TalonSRX(5);
	
	
		
	

	}

	public void enable(){
		this.setEnabled(true);
		
	}
	
	
	public void disable(){
		this.setEnabled(false);
		
	}
	public void setEnabled(boolean on){
		if(!init){throw Robot.EXnotInit();}
		double in = on? -1: 0;
		left.set(in);
		right.set(in);
		
	}
	
	public boolean getSpeed(){
		if(!init){throw Robot.EXnotInit();}
		return (left.get() + right.get()) != 0.0;
	}
	
	@Override
	public void clean() {
		if(!init){throw Robot.EXnotInit();}
		init = false;
		
		
		left = null;
		right = null;
	
		
	}
}
