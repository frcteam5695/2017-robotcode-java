package org.usfirst.frc.team5695.robot.autonomous;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team5695.robot.Robot;
import org.usfirst.frc.team5695.robot.component.ComponentManager;
import org.usfirst.frc.team5695.robot.utility.Validate;

public class AutonManager {
	
	private static final String ERRORnot = "AutonManager was not initialized!";
	private static final String ERRORis = "AutonManager was already initialized!";
	
	private boolean init = false;
	private List<AutonMode> modes ;
	private Robot robot;
	private ComponentManager components;
	
	
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
	private AutonMode[] makeModes(){
		

		return new AutonMode[]{
				makeAuton("Blank").build(),
				 
				 makeAuton("Drive forward")
					.driveForward(0.5)
					.delaySecond(3)
					.driveHalt()
					.build(),
					
			makeAuton("Middle gear position")
					.driveForward(.3)
					.delaySecond(2)
					.driveHalt()
					.gearOpen()
					.delay(500)
					.driveForward(-0.3)
					.delay(500)
					.driveHalt()
					.gearClose()
					.build(),
					
					makeAuton("Left gear position")
					.driveForward(.4)
					.delaySecond(4)
					.driveHalt()
					.driveStrafe(0.3)
					.delaySecond(1)
					.driveForward(.4)
					.delaySecond(1)
					.driveHalt()
					.gearOpen()
					.delay(250)
					.driveForward(0.3)
					.delay(250)
					.driveHalt()
					.delay(500)
					.gearClose()
					.delay(250)
					.build(),
					
			makeAuton("Right gear position")
				.driveForward(.4)
				.delaySecond(4)
				.driveHalt()
				.driveTurn(-0.3)
				.delaySecond(1)
				.driveForward(0.4)
				.delaySecond(1)
				.driveHalt()
				.gearOpen()
				.delay(250)
				.driveForward(-0.3)
				.delay(250)
				.driveHalt()
				.delay(500)
				.gearClose()
				.delay(250)
				.build()
				};
	}
	

	}
// ()-> {},
	
			
			
			
			
			

	
