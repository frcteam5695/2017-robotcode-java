package org.usfirst.frc.team5695.robot.component;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.Validate;

import org.usfirst.frc.team5695.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.XboxController;

public class ComponentManager implements IElement{
	private boolean init = false;
	
	private MotorDrive motorDrive;
	private Controller controller;
	private MotorClimb motorClimb;
	private SolenoidGear gearSol;
	private SolenoidBallGate ballSol;
	
	
	
	public void init(){
		if(init){throw Robot.EXalreadyInit();}
		init = true;
		(motorDrive = new MotorDrivePWM()).init();
		(controller = new Controller()).init();
		(motorClimb = new MotorClimb()).init();
		(gearSol = new SolenoidGear()).init();
		(ballSol = new SolenoidBallGate()).init();
		
	}
	
	public MotorDrive getMotorDrive(){
		if(!init){throw Robot.EXnotInit();}
		return motorDrive;
	}
	
	public Controller getController(){
		if(!init){throw Robot.EXnotInit();}
		return controller;
	}
	
	public MotorClimb getMotorClimb(){
		if(!init){throw Robot.EXnotInit();}
		return motorClimb;
	}
	
	public SolenoidGear getSolenoidGear(){
		if(!init){throw Robot.EXnotInit();}
		return gearSol;
	}
	
	public SolenoidBallGate getSolenoidBallGate(){
		if(!init){throw Robot.EXnotInit();}
		return ballSol;
	}
	@Override
	public void clean() {
		if(!init){throw Robot.EXnotInit();}
		init = false;
		
		motorDrive.clean();
		controller.clean();
		motorClimb.clean();
		gearSol.clean();
		ballSol.clean();
		
		motorDrive = null;
		controller = null;
		motorClimb = null;
		gearSol = null;
		ballSol = null;
		
		
	}


		
		
	}
	




	

