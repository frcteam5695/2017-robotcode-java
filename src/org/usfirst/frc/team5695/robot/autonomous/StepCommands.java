package org.usfirst.frc.team5695.robot.autonomous;

import org.apache.commons.lang3.Validate;
import org.usfirst.frc.team5695.robot.component.ComponentManager;

import edu.wpi.first.wpilibj.RobotState;

public final class StepCommands {
	private static ComponentManager comp;
	
	
	public static void init(ComponentManager comp) {
		Validate.notNull(comp);
		StepCommands.comp = comp;
		
	}
	
	
	public static void dispose() {
		comp = null;
	}
	

	//////
	public static Step driveForward(double speed){
		return ()->{comp.getMotorDrive().driveForward(speed);};
		
	}
	
	public static Step driveStrafe(double speed){
		return ()->{comp.getMotorDrive().driveStrafe(speed);};
		
	}
	
	public static Step driveTurn(double speed){
		return  ()->{comp.getMotorDrive().driveTurn(speed);};
		
	}
	
	public static Step driveHalt(){
		return ()->{comp.getMotorDrive().halt();};
		
	}
	
	public static Step gearOpen(){
		return ()->{comp.getSolenoidGear().open();};
		
	}
	
	public static Step gearClose(){
		return ()->{comp.getSolenoidGear().close();};
		
	}
	
	public static Step ballGateOpen(){
		return ()->{comp.getSolenoidBallGate().open();};
		
	}
	
	public static Step ballGateClose(){
		return ()->{comp.getSolenoidBallGate().close();};
		
	}
	
	public static Step delaySecond(double seconds){
		return ()-> {
			
			try{Thread.sleep((long) (seconds*1000));}
			catch(Exception c){
				throw new AutonModeException("Timed step interupted",c);
			}
			};
		
	}	
	
	
	/***
	 * Performs a command over a time period.
	 * **/
	public static Step timedStep(Step step, int ms){
		final int delay = 50;//wait 50 ms each step
		return ()->{
			
			final int cycles = ms/delay;
			try {
				for(int i = 0; i< cycles; i++){
					if(!RobotState.isAutonomous())break;
				
						step.run();
				
						Thread.sleep(delay);
				
				}
			} catch (InterruptedException e) {
				throw new AutonModeException("Timed step interupted",e);
	
			}
			
		};
		
	}
	
	public static Step delay(long ms){
		 return()-> {
			try{Thread.sleep(ms);}
			catch(Exception c){
				throw new AutonModeException("Timed step interupted",c);
			}
		};
		
	}
	
	
	
	
	
	

}
