package org.usfirst.frc.team5695.robot.autonomous;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.usfirst.frc.team5695.robot.Robot;
import org.usfirst.frc.team5695.robot.component.ComponentManager;
import org.usfirst.frc.team5695.robot.utility.Validate;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;

public final class AutonMode {
	private final List<Step> steps;
	private final String name;
	
	private AutonMode(List<Step> steps, String name){
		this.steps = Collections.unmodifiableList(steps);
		
		this.name = name;
		
	}
	public String getName(){
		return name;
	}
	
	
	public List<Step> getSteps(){
		return steps;
	}
	
	public void start(){
		 new Thread(()->{
				Iterator<Step> ii = steps.iterator();
				while(ii.hasNext() && RobotState.isAutonomous()){
					ii.next().run();
				}
				
			}).start();
	}
	

	
	
	public static Builder builder(Robot robot, String name){
		Validate.notNull(robot);
		return new Builder(robot,name);
	}
	
	
	public static class Builder{
		private Robot robot;
		private ComponentManager comp;
		private List<Step> steps = new LinkedList<>();
		private String name = "Empty";
		
		private Builder(Robot bot, String name){
			robot = bot;
			this.name = name;
			Validate.notNull(robot,"Robot cAant be null");
			comp = robot.getComponentManager();
		}
		
		public Builder addStep(Step step){
			steps.add(step);
			return this;
			
		}
		public Builder addSteps(Step... step){
			steps.addAll(Arrays.asList(step));
			return this;
			
		}
		
		public Builder driveForward(double speed){
			steps.add( ()->{comp.getMotorDrive().driveForward(speed);});
			return this;
		}
		
		public Builder driveStrafe(double speed){
			steps.add( ()->{comp.getMotorDrive().driveStrafe(speed);});
			return this;
		}
		
		public Builder driveTurn(double speed){
			steps.add( ()->{comp.getMotorDrive().driveTurn(speed);});
			return this;
		}
		
		public Builder driveHalt(){
			steps.add( ()->{comp.getMotorDrive().halt();});
			return this;
		}
		
		public Builder gearOpen(){
			steps.add( ()->{comp.getSolenoidGear().open();});
			return this;
		}
		
		public Builder gearClose(){
			steps.add( ()->{comp.getSolenoidGear().close();});
			return this;
		}
		
		public Builder ballGateOpen(){
			steps.add( ()->{comp.getSolenoidBallGate().open();});
			return this;
		}
		
		public Builder ballGateClose(){
			steps.add( ()->{comp.getSolenoidBallGate().close();});
			return this;
		}
		
		public Builder delaySecond(double seconds){
			steps.add( ()-> {
				
				try{Thread.sleep((long) (seconds*1000));}
				catch(Exception c){}
				});
			return this;
		}
		
		public Builder delay(long ms){
			steps.add( ()-> {
				try{Thread.sleep(ms);}
				catch(Exception c){}
			});
			return this;
		}
		
		
		
		
		public AutonMode build(){
			return new AutonMode(steps,name);
		}
	}

}
