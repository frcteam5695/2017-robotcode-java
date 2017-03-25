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
	
	private boolean init = false;// Is the manager initialized
	private List<AutonMode> modes ;// List of possible autons
	private Robot robot;
	private ComponentManager components;// Used to access the motors,senors, etc
	public final Command cmd; //Used to supply common Auton commands
	
	
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
	
	public String[] getAutonNames(){
		Validate.isTrue(init,ERRORnot);
		String[] descs = new String[modes.size()];
		for(int i = 0; i< modes.size(); i++){
			descs[i] = modes.get(i).getName();
		}
		return descs;
	}
	
	public int getAutonAmount(){
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
	 * A class of preset common commands for autonomous modes
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
		
		
		/***
		 * Performs a command over a time period.
		 * **/
		public Step timedStep(Step step, int ms){
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
						cmd.timedStep(cmd.driveForward(0.3),5000),
						cmd.driveHalt()
						).build(),
				
				makeAuton("Drop gear {Middle}").addSteps(
						cmd.timedStep(cmd.driveForward(.3),2750),
						cmd.driveHalt(),
						cmd.timedStep(cmd.gearOpen(), 1500),
						cmd.timedStep(cmd.driveForward(-0.3),1500),
						cmd.driveHalt(),
						cmd.ballGateClose()
						).build(),
				
				makeAuton("Drop Gear {Left}").addSteps(
						cmd.timedStep(cmd.driveForward(.3),2750),
						cmd.driveHalt(),
						cmd.timedStep(cmd.driveTurn(-.3), 2000),/// turning
						cmd.driveHalt(),
						cmd.timedStep(cmd.driveForward(.3),2750),
						cmd.driveHalt(),
						cmd.timedStep(cmd.gearOpen(),1500),
						cmd.timedStep(cmd.driveForward(-0.3), 500),
						cmd.gearClose(),
						cmd.driveHalt()
						).build(),
				
				makeAuton("Right gear position").addSteps(
						cmd.timedStep(cmd.driveForward(.3),2750),
						cmd.driveHalt(),
						cmd.timedStep(cmd.driveTurn(.3), 2000),/// turning
						cmd.driveHalt(),
						cmd.timedStep(cmd.driveForward(.3),2750),
						cmd.driveHalt(),
						cmd.timedStep(cmd.gearOpen(),1500),
						cmd.timedStep(cmd.driveForward(-0.3), 500),
						cmd.gearClose(),
						cmd.driveHalt()
						).build()
				};
		}
	}
// ()-> {},
	
			
			
			
			
			

	
