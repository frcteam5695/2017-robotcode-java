package org.usfirst.frc.team5695.robot.autonomous;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team5695.robot.Robot;
import org.usfirst.frc.team5695.robot.component.ComponentManager;
import org.usfirst.frc.team5695.robot.utility.Validate;

import edu.wpi.first.wpilibj.RobotState;

public class AutonManager {
	
	private static final String ERRORnot = "AutonManager was not initialized!";
	private static final String ERRORis = "AutonManager was already initialized!";
	
	private boolean init = false;
	private List<AutonMode> modes ;
	private Robot robot;
	private ComponentManager components;
	public final Command cmd;
	
	
/*public AutonMode
	
	driveForward,
	blankAuto, gearMid,
			
	gearRight,
	
	gearLeft
	
	
	;*/
	
	public AutonManager(Robot robot){
		Validate.notNull(robot,"Dependencies cannot be null");
		this.robot = robot;
		components = robot.getComponentManager();
		cmd = new Command(components);
		

	}
	
	public void init(){
		Validate.isTrue(!init,ERRORis);
		init = true;
		modes = Arrays.asList(makeModes());
	}
	
	public AutonMode getAuton(int id){
		Validate.isTrue(init,ERRORnot);
		Validate.notNull(modes);
		Validate.isTrue(id< modes.size(), "Index is too large");
		return  modes.get(id);
		
	}
	
	public String[] getDesc(){
		Validate.isTrue(init,ERRORnot);
		String[] descs = new String[modes.size()];
		for(int i = 0; i< modes.size(); i++){
			descs[i] = modes.get(i).getName();
		}
		return descs;
	}
	
	public int getSize(){
		return modes.size();
	}
	
	public List<AutonMode> getAutons(){
		return modes;
	}
	
	public AutonMode.Builder makeAuton(String name)
	{
		return  new AutonMode.Builder(robot, name);
	}
	
	/***
	 * A class of preset common commands for auton
	 * */
	public class Command{
		final ComponentManager comp;
		private Command(ComponentManager comp){this.comp = comp;}
		
		//////
		public Step driveForward(double speed){
			return ()->{comp.getMotorDrive().driveForward(speed);};
			
		}
		
		public Step driveStrafe(double speed){
			return ()->{comp.getMotorDrive().driveStrafe(speed);};
			
		}
		
		public Step driveTurn(double speed){
			return  ()->{comp.getMotorDrive().driveTurn(speed);};
			
		}
		
		public Step driveHalt(){
			return ()->{comp.getMotorDrive().halt();};
			
		}
		
		public Step gearOpen(){
			return ()->{comp.getSolenoidGear().open();};
			
		}
		
		public Step gearClose(){
			return ()->{comp.getSolenoidGear().close();};
			
		}
		
		public Step ballGateOpen(){
			return ()->{comp.getSolenoidBallGate().open();};
			
		}
		
		public Step ballGateClose(){
			return ()->{comp.getSolenoidBallGate().close();};
			
		}
		
		public Step delaySecond(double seconds){
			return ()-> {
				
				try{Thread.sleep((long) (seconds*1000));}
				catch(Exception c){}
				};
			
		}	
		
		public Step timedStep(Step step, int ms){
			return ()->{
				final int cycles = ms/50;
				try {
					for(int i = 0; i< cycles; i++){
						if(!RobotState.isAutonomous())break;
					
							step.run();
					
							Thread.sleep(50);
					
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.err.println("Timed step interupted");
				}
				
			};
			
		}
		
		public Step delay(long ms){
			 return()-> {
				try{Thread.sleep(ms);}
				catch(Exception c){}
			};
			
		}
		
	}
	
	private AutonMode[] makeModes(){
		

		return new AutonMode[]{
				makeAuton("Blank").build(),
				 
				 makeAuton("Drive forward").addSteps(
						 	cmd.timedStep(cmd.driveForward(0.5),3000),
						 	cmd.driveHalt()
						 )
				 

					.build(),
					
			makeAuton("Middle gear position").addSteps(
					cmd.timedStep(cmd.driveForward(.3),2000),
					cmd.driveHalt(),
					cmd.timedStep(cmd.gearOpen(), 500),
					cmd.timedStep(cmd.driveForward(-0.3),1500),
					cmd.driveHalt(),
					cmd.ballGateClose()
					).build(),
					
					makeAuton("Left gear position").addSteps(
							cmd.timedStep(cmd.driveForward(0.4), 4000),
							cmd.driveHalt(),
							cmd.timedStep(cmd.driveStrafe(0.3), 1000),
							cmd.timedStep(cmd.driveForward(0.4),1 ),
							cmd.driveHalt(),
							cmd.timedStep(cmd.gearOpen(),1500),
							cmd.timedStep(cmd.driveForward(0.3), 500),
							cmd.timedStep(cmd.driveHalt(), 500),
							cmd.driveHalt()
							
							).build(),
					
					
			makeAuton("Right gear position").addSteps(
					cmd.timedStep(cmd.driveForward(0.4), 4000),
					cmd.driveHalt(),
					cmd.timedStep(cmd.driveStrafe(-0.3), 1000),
					cmd.timedStep(cmd.driveForward(0.4),1 ),
					cmd.driveHalt(),
					cmd.timedStep(cmd.gearOpen(),1500),
					cmd.timedStep(cmd.driveForward(0.3), 500),
					cmd.timedStep(cmd.driveHalt(), 500),
					cmd.driveHalt()
					).build()
			
				};
	}
	

	}
// ()-> {},
	
			
			
			
			
			

	
