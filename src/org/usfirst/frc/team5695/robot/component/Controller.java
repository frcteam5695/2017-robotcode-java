package org.usfirst.frc.team5695.robot.component;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team5695.robot.Robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class Controller implements IElement {
	private boolean init = false;
	public static final double DEADZONE = .1;

		private XboxController controller;
		
		protected Controller(){}
		
		public void init(){
			if(init){throw Robot.EXalreadyInit();}
			init = true;
			controller = new XboxController(0);
			
		
		}
		
		public boolean getButtonA(){
			if(!init){throw Robot.EXnotInit();}
			return controller.getAButton();
		}
		
		public boolean getButtonY(){
			if(!init){throw Robot.EXnotInit();}
			return controller.getYButton();
		}
		
		public boolean getButtonX(){
			if(!init){throw Robot.EXnotInit();}
			return controller.getXButton();
		}
		
		public boolean getButtonB(){
			if(!init){throw Robot.EXnotInit();}
			return controller.getBButton();
		}
		
		public boolean getButtonStart(){
			if(!init){throw Robot.EXnotInit();}
			return controller.getStartButton();
		}
		
		public double getAxisX(Hand hand){
			if(!init){throw Robot.EXnotInit();}
			return deadzone1(controller.getX(hand), DEADZONE);	
		}
		
		public double getAxisY(Hand hand){
			if(!init){throw Robot.EXnotInit();}
			return deadzone1(controller.getY(hand), DEADZONE);	
		}
		
		public double getAxisXRaw(Hand hand){
			if(!init){throw Robot.EXnotInit();}
			return controller.getX(hand);	
		}
		
		public double getAxisYRaw(Hand hand){
			if(!init){throw Robot.EXnotInit();}
			return controller.getY(hand);	
		}
		
		public double getTrigger(Hand hand){
			if(!init){throw Robot.EXnotInit();}
			return controller.getTriggerAxis(hand);	
		}
		
		public boolean getButtonBumper(Hand hand){
			if(!init){throw Robot.EXnotInit();}
			return controller.getBumper(hand);	
		}
		
		public boolean getButtonStick(Hand hand){
			if(!init){throw Robot.EXnotInit();}
			return controller.getStickButton(hand);
		}
		
		private double deadzone1(double input, double limit){
			double sign = Math.signum(input);
			double output = Math.max(1 / (1 - limit) * (input * sign - limit), 0)* sign;
			return output; 
		}
	
		public enum DPad{
			NORTH(0), 
	NORTH_WEST(315)	,NORTH_EAST(45),
WEST( 270),		NONE(-1),		EAST(90),
	SOUTH_WEST(315), SOUTH_EAST(135),
			SOUTH (180),

			
;
			private static final Map<Integer,DPad> cache = new HashMap<>();
			private int direction;
			private DPad(int dir){
				direction  = dir;
				putDer();
			}

			private void putDer(){
				cache.put(direction, this);
			}
			public static DPad of(int dir){
				return  cache.get(dir);
			}

			public int getDir(){
				return direction;
			}


		}
		public DPad getDPad(){
			return DPad.of(controller.getPOV());
		}
	

	

	@Override
	public void clean() {
		if(!init){throw Robot.EXnotInit();}
		init = false;
		
		controller = null;
		// TODO Auto-generated method stub

	}

}
