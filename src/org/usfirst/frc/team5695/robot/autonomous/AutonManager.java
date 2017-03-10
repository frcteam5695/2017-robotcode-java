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
	
	
	public AutonManager(Robot robot){
		this.robot = robot;
	

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
		ComponentManager components = robot.getComponentManager();
		return new AutonMode[]{
				AutonMode.builder().setDescription("Do nothing")
				.build(),
				//////////////////////////////////////////
				AutonMode.builder().setDescription("TheFirst")
				.addSteps( 
						()->{ components.getMotorDrive().driveLat(1, 1);},
						
						()->{
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								
								e.printStackTrace();
							}
							Timer.delay(3);
						},
						
						()->{ components.getMotorDrive().driveLat(0,0);}
					
						).build(),
				///////////////////////////////////////////////////
				/// more stuff here
				
			};
	}
	
	
	

	
}
