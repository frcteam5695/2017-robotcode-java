package org.usfirst.frc.team5695.robot.component;

import org.usfirst.frc.team5695.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class AbstractSolenoid implements IElement{
	private boolean init = false;
	private DoubleSolenoid sol;
	private boolean open = false;
	private final int forward, reverse;
	
	protected AbstractSolenoid(final int forward, final int reverse){
		this.forward  = forward;
		this.reverse = reverse;
	}
	
	@Override
	public void init() {
		if(init){throw Robot.EXalreadyInit();}
		init = true;
		sol = new DoubleSolenoid(forward, reverse);
		
	}
	
	public void open(){
		if(!init){throw Robot.EXnotInit();}
		setOpen(true);
	}
	public void close(){
		if(!init){throw Robot.EXnotInit();}
		setOpen(false);
	}
	
	private static Value bool2val(boolean bool){
		
		return bool ? Value.kForward : Value.kReverse;
	}
	public void setOpen(boolean open){
		if(!init){throw Robot.EXnotInit();}
		this.open  = open; 
		sol.set(bool2val(open));
	}
	
	public boolean isOpen(){
		if(!init){throw Robot.EXnotInit();}
		return open;
	}
	
	
	@Override
	public void clean() {
		if(!init){throw Robot.EXnotInit();}
		init = false;
		
		sol.free();
		sol = null;
		// TODO Auto-generated method stub
		
	}

}
