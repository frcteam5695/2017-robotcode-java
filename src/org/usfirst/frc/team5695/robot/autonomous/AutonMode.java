package org.usfirst.frc.team5695.robot.autonomous;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.usfirst.frc.team5695.robot.Robot;
import org.usfirst.frc.team5695.robot.component.ComponentManager;

import com.google.common.collect.ImmutableList;

import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.tables.ITable;

public final class AutonMode {
	private final ImmutableList<Step> steps;
	private final String description;
	
	private AutonMode(List<Step> steps, String desc){
		this.steps = ImmutableList.copyOf(steps);
		description = desc;
		
	}
	public String getDescription(){
		return description;
	}
	
	
	public ImmutableList<Step> getSteps(){
		return steps;
	}
	
	public void start(){
		 new Thread(()->{
				Iterator<Step> ii = steps.iterator();
				while(ii.hasNext()){
					ii.next().run();
				}
				
			}).start();
	}
	

	
	
	public static Builder builder(Robot robot){
		return new Builder(robot);
	}
	
	
	public static class Builder{
		private Robot robot;
		private ComponentManager comp;
		private List<Step> steps = new LinkedList<>();
		private String desc = "Empty";
		
		private Builder(Robot bot){
			robot = bot;
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
			steps.add( ()-> {Timer.delay(seconds);});
			return this;
		}
		
		public Builder delay(double ms){
			steps.add( ()-> {Timer.delay(ms/1000);});
			return this;
		}
		
		
		
		public Builder setDescription(String desc){
		this.desc = desc;
			return this;
			
		}
		
		
		
		public AutonMode build(){
			return new AutonMode(steps,desc);
		}
	}

}
