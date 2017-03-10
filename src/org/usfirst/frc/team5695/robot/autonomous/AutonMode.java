package org.usfirst.frc.team5695.robot.autonomous;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import edu.wpi.first.wpilibj.NamedSendable;
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
	

	
	
	public static Builder builder(){
		return new Builder();
	}
	public static class Builder{
		private List<Step> steps = new LinkedList<>();
		private String desc = "Empty";
		
		public Builder addStep(Step step){
			steps.add(step);
			return this;
			
		}
		public Builder addSteps(Step... step){
			steps.addAll(Arrays.asList(step));
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
