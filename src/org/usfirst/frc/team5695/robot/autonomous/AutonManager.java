package org.usfirst.frc.team5695.robot.autonomous;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.usfirst.frc.team5695.robot.Robot;
import org.usfirst.frc.team5695.robot.component.ComponentManager;

public class AutonManager {
	
	private static final String ERRORnot = "AutonManager was not initialized!";
	private static final String ERRORis = "AutonManager was already initialized!";
	
	private boolean init = false;
	private List<AutonMode> modes ;
	private Robot robot;
	private ComponentManager components;
	
	
public final AutonMode
	
	driveForward,
	blankAuto, gearMid,
			
	gearRight,
	
	gearLeft
	
	
	;
	
	public AutonManager(Robot robot){
		Validate.notNull(robot,"Dependencies cannot be null");
		this.robot = robot;
		components = robot.getComponentManager();
		
		driveForward = AutonMode.builder(robot).setDescription("Drive forward")
				.driveForward(0.5)
				.delaySecond(3)
				.driveHalt()
				.build();
						
				blankAuto =  AutonMode.builder(robot).setDescription("The blank Auton").build();
						
				gearMid = AutonMode.builder(robot).setDescription("Middle gear position")
				.driveForward(.3)
				.delaySecond(2)
				.driveHalt()
				.gearOpen()
				.delay(500)
				.driveForward(-0.3)
				.delay(500)
				.driveHalt()
				.gearClose()
				.build();
						
				gearRight =  AutonMode.builder(robot).setDescription("Right gear position")
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
				.build();
				
				gearLeft =  AutonMode.builder(robot).setDescription("Left gear position")
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
				.build()
				
				;

	}
	
	public void init(){
		Validate.isTrue(!init,ERRORis);
		init = true;
		modes = Arrays.asList(makeModes());
	}
	
	public AutonMode getAuton(int id){
		Validate.isTrue(!init,ERRORnot);
		Validate.notNull(modes);
		Validate.isTrue(id< modes.size(), "Index is too large");
		return  modes.get(id);
		
	}
	
	public String[] getDesc(){
		Validate.isTrue(!init,ERRORnot);
		String[] descs = new String[modes.size()];
		for(int i = 0; i< modes.size(); i++){
			descs[i] = modes.get(i).getDescription();
		}
		return descs;
	}
	
	public int getSize(){
		return modes.size();
	}
	
	public List<AutonMode> getAutons(){
		return modes;
	}
	
	private AutonMode[] makeModes(){
		

		return new AutonMode[]{blankAuto,driveForward,gearMid,gearLeft,gearRight};
	}
	

	}
// ()-> {},
	
			
			
			
			
			

	
