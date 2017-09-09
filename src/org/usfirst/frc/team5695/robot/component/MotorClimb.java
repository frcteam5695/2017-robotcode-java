package org.usfirst.frc.team5695.robot.component;

import org.usfirst.frc.team5695.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.TalonSRX;

public interface MotorClimb extends IElement {

	
	public void init();

	public void enable();
	
	
	public void disable();
	public void setEnabled(boolean on);
	
	public boolean getSpeed();
	
	@Override
	public void dispose();
}
