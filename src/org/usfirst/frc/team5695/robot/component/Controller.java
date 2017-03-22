package org.usfirst.frc.team5695.robot.component;

import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc.team5695.robot.Robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class Controller implements IElement {
	private boolean init = false;

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
			return deadzone(controller.getX(hand));	
		}
		
		public double getAxisY(Hand hand){
			if(!init){throw Robot.EXnotInit();}
			return deadzone(controller.getY(hand));	
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
		
		public double deadzone(double input){
			return deadzone1(input,0.1,0.3);
		}
		
		
		/**
		 * @param input - [0,1] the value to send to the motor
		 * 
		 * @param deadzone - the minimal input to accept
		 * 
		 * @param resistance - this is the curvature of the graph
		 * 	when resistance = deadzone, the graph is linear, when larger the graph become more 
		 * exponential. resistance is not allowed to be lower than the deadzone.
		 * 
		 * */
		private double deadzone1(double input, double deadzone, double resistance){
			
			// The minimal value so that the graph is at least linear
			double minVal = Math.max(deadzone, resistance);
			
			// the exponent to modify the input with
			double exp = Math.log(deadzone) / Math.log(minVal);
			
			/* if the input is larger than the  deadzone then
			 * return the modified value; else return 0
			 */
			 
			return Math.abs(input) > deadzone ? Math.pow(input, exp) : 0;
			
		
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
