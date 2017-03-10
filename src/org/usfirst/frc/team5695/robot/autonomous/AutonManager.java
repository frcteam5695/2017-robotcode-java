package org.usfirst.frc.team5695.robot.autonomous;

import org.apache.commons.lang3.Validate;
import org.usfirst.frc.team5695.robot.Robot;
import org.usfirst.frc.team5695.robot.component.ComponentManager;

import com.google.common.collect.ImmutableList;

import edu.wpi.first.wpilibj.Timer;

public class AutonManager {
	
	private static final String ERRORnot = "AutonManager was not initialized!";
	private static final String ERRORis = "AutonManager was already initialized!";
	
	private boolean init = false;
	private ImmutableList<AutonMode> modes ;
	private Robot robot;
	private ComponentManager components;
	
	public AutonManager(Robot robot){
		Validate.notNull(robot,"Dependencies cannot be null");
		this.robot = robot;
		components = robot.getComponentManager();

	}
	
	public void init(){
		Validate.isTrue(!init,ERRORis);
		init = true;
		modes = ImmutableList.copyOf(makeModes());
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
	
	public ImmutableList<AutonMode> getAutons(){
		return modes;
	}
	
	private AutonMode[] makeModes(){
		Validate.isTrue(!init,ERRORnot);

		return new AutonMode[]{blankAuto,driveForward,gearMid,gearLeft,gearRight};
	}
	
// ()-> {},
	public final AutonMode
	
	driveForward = AutonMode.builder().setDescription("Drive forward").addSteps( 
					()->{ components.getMotorDrive().driveForward(.5);},
					()->{Timer.delay(3);},
					()->{ components.getMotorDrive().halt();}
					).build(),
			
	blankAuto =  AutonMode.builder().setDescription("The blank Auton").build(),
			
	gearMid = AutonMode.builder().setDescription("Middle gear position").addSteps(
					()-> components.getMotorDrive().driveLat(0, .3),
					()-> {Timer.delay(2);},
					()-> {components.getMotorDrive().halt();},
					()-> {components.getSolenoidGear().setOpen(true);},
					()-> {Timer.delay(.5);},
					()-> {components.getMotorDrive().driveForward(-.3);},
					()-> {Timer.delay(.5);},
					()-> {components.getMotorDrive().halt();},
					()-> {components.getSolenoidGear().setOpen(false);}
					
					).build(),
			
	gearRight =  AutonMode.builder().setDescription("Right gear position").addSteps(
			()-> {components.getMotorDrive().driveForward(.4);},
			()-> {Timer.delay(4);},
			()-> {components.getMotorDrive().halt();},
			()-> {components.getMotorDrive().driveTurn(-0.3);},
			()-> {Timer.delay(1);},
			()-> {components.getMotorDrive().driveForward(0.4);},
			()-> {Timer.delay(1);},
			()-> {components.getMotorDrive().halt();},
			()-> {components.getSolenoidGear().open();},
			()-> {Timer.delay(.25);},
			()-> {components.getMotorDrive().driveForward(-.3);},
			()-> {Timer.delay(.25);},
			()-> {components.getMotorDrive().halt();},
			()-> {Timer.delay(.5);},
			()-> {components.getSolenoidGear().close();},
			()-> {Timer.delay(.25);}
			).build(),
	gearLeft =  AutonMode.builder().setDescription("Left gear position").addSteps(
			()-> {components.getMotorDrive().driveForward(.4);},
			()-> {Timer.delay(4);},
			()-> {components.getMotorDrive().halt();},
			()-> {components.getMotorDrive().driveTurn(0.3);},
			()-> {Timer.delay(1);},
			()-> {components.getMotorDrive().driveForward(0.4);},
			()-> {Timer.delay(1);},
			()-> {components.getMotorDrive().halt();},
			()-> {components.getSolenoidGear().open();},
			()-> {Timer.delay(.25);},
			()-> {components.getMotorDrive().driveForward(-.3);},
			()-> {Timer.delay(.25);},
			()-> {components.getMotorDrive().halt();},
			()-> {Timer.delay(.5);},
			()-> {components.getSolenoidGear().close();},
			()-> {Timer.delay(.25);}
			
					
		).build()
	
	
	;
	
			
			
			
			
			

	
}