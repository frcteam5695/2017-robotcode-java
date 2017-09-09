package org.usfirst.frc.team5695.robot.autonomous;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.usfirst.frc.team5695.robot.Robot;
import org.usfirst.frc.team5695.robot.component.ComponentManager;
import org.usfirst.frc.team5695.robot.utility.Disposable;
import org.usfirst.frc.team5695.robot.utility.Validate;

import edu.wpi.first.wpilibj.RobotState;

public class AutonManager implements Disposable{
	
	private static final String ERRORnot = "AutonManager was not initialized!";
	private static final String ERRORis = "AutonManager was already initialized!";
	
	private boolean init = false;// Is the manager initialized
	private List<AutonMode> modes ;// List of possible autons
	private Robot robot;
	private ComponentManager components;// Used to access the motors,senors, etc
	private ExecutorService service;
	private volatile AutonRuntime currentMode;
	
	
	public synchronized void runAutoMode(AutonMode mode) {
		Validate.notNull(mode);
		service.execute(new AutonRuntime(mode));
		
	}
	
	
	public AutonManager(Robot robot){
		Validate.notNull(robot,"Dependencies cannot be null");
		this.robot = robot;
		components = robot.getComponentManager();
	
		

	}
	
	public void init(){
		Validate.isTrue(!init,ERRORis);
		init = true;
		modes = Arrays.asList(makeModes());
	}
	
	public AutonRuntime getCurrentRuntime() {
		return currentMode;
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
		return  AutonMode.newBuilder(robot,name);
	}
	
	public class AutonRuntime implements Runnable{
		private final AutonMode mode;
		private final AutonManager manager = AutonManager.this;
		
		private volatile boolean interupted;
		private volatile Iterator<Step> runtime;
		
		
		public AutonRuntime(AutonMode mode) {
			this.mode = mode;
		}
		public boolean isReady() {
			return !interupted && RobotState.isAutonomous();
		}
		
		public boolean isRunning() {
			return runtime!= null;
			
		}
		
		public boolean interupted() {
			return interupted;
		
		}
		
		public void interupt() {
			interupted = true;
		}
		
		
		public AutonMode getMode() {
			return mode;
		}
		
		public void run(){
			interupted = false;
			
			if(manager.currentMode != null) {
				manager.currentMode.interupt();
			}
			
			
			manager.currentMode = this;
			runtime = mode.getSteps().iterator();
			
			while(runtime.hasNext() && isReady()){
				try {
					runtime.next().run();
				}catch(Throwable t) {
					interupted = true;
					break;
			
				}
			}
			runtime = null;
			manager.currentMode = null;
					
			
		}
	
		
	}
	
	
	/***
	 * A class of preset common commands for autonomous modes
	 * */
	
	
	private AutonMode[] makeModes(){
		
	
		return new AutonMode[]{
				makeAuton("Blank").build(),
				
				makeAuton("Drive forward").addSteps(
						StepCommands.timedStep(StepCommands.driveForward(0.3),5000),
						StepCommands.driveHalt()
						).build(),
				
				makeAuton("Drop gear {Middle}").addSteps(
						StepCommands.timedStep(StepCommands.driveForward(.3),2750),
						StepCommands.driveHalt(),
						StepCommands.timedStep(StepCommands.gearOpen(), 1500),
						StepCommands.timedStep(StepCommands.driveForward(-0.3),1500),
						StepCommands.driveHalt(),
						StepCommands.ballGateClose()
						).build(),
				
				makeAuton("Drop Gear {Left}").addSteps(
						StepCommands.timedStep(StepCommands.driveForward(.3),2750),
						StepCommands.driveHalt(),
						StepCommands.timedStep(StepCommands.driveTurn(-.3), 2000),/// turning
						StepCommands.driveHalt(),
						StepCommands.timedStep(StepCommands.driveForward(.3),2750),
						StepCommands.driveHalt(),
						StepCommands.timedStep(StepCommands.gearOpen(),1500),
						StepCommands.timedStep(StepCommands.driveForward(-0.3), 500),
						StepCommands.gearClose(),
						StepCommands.driveHalt()
						).build(),
				
				makeAuton("Drop gear {Right}").addSteps(
						StepCommands.timedStep(StepCommands.driveForward(.3),2750),
						StepCommands.driveHalt(),
						StepCommands.timedStep(StepCommands.driveTurn(.3), 2000),/// turning
						StepCommands.driveHalt(),
						StepCommands.timedStep(StepCommands.driveForward(.3),2750),
						StepCommands.driveHalt(),
						StepCommands.timedStep(StepCommands.gearOpen(),1500),
						StepCommands.timedStep(StepCommands.driveForward(-0.3), 500),
						StepCommands.gearClose(),
						StepCommands.driveHalt()
						).build()
				};
		}


	@Override
	public void dispose() {
		service.shutdownNow();
		service = null;
		init = false;
		components = null;
		robot = null;
		currentMode = null;
		modes.clear();
		modes = null;
		
	}
}

	
// ()-> {},
	
			
			
			
			
			

	
