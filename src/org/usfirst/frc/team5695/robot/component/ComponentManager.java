package org.usfirst.frc.team5695.robot.component;

import org.usfirst.frc.team5695.robot.Robot;

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
		(motorClimb = new MotorClimbPWM()).init();
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
	




	

