package org.usfirst.frc.team5695.robot.component;

import org.usfirst.frc.team5695.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;

public class MotorClimb implements IElement {
	private boolean init = false;
	
	private CANTalon left, right;
	private RobotDrive drive;
	private  MotorSafetyHelper safeLeft,safeRight;
	
	protected MotorClimb(){}
	@Override
	public void init(){
		if(init){throw Robot.EXalreadyInit();}
		init = true;
		(safeLeft = new  MotorSafetyHelper(left = new CANTalon(4))) .setSafetyEnabled(true);
		(safeRight = new  MotorSafetyHelper(right = new CANTalon(5))) .setSafetyEnabled(true);;
	
	
		
	

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
