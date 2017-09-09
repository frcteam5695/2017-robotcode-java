package org.usfirst.frc.team5695.robot.autonomous;

import java.util.ArrayList;
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
	
	private AutonMode(Builder builder){
		Validate.notNull(builder,"AutonMode Builder cannot be null");
		this.steps = Collections.unmodifiableList(new ArrayList<>(builder.steps));
		this.name = builder.name;
		
	}
	public String getName(){
		return name;
	}
	
	
	public List<Step> getSteps(){
		return steps;
	}
	
	

	public static Builder newBuilder( Robot robot,String name) {
		return new Builder(robot,name);
	}
	
	
	public static class Builder{
		private Robot robot;
		private ComponentManager comp;
		private List<Step> steps = new LinkedList<>();
		private String name = "Empty";
		
		protected Builder(Robot bot, String name){
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
		
		public AutonMode build(){
			return new AutonMode(this);
		}
	}

}
