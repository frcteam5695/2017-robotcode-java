package org.usfirst.frc.team5695.robot.utility;

import org.usfirst.frc.team5695.robot.Robot;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard {

	private Robot robot;
	
	public Dashboard(Robot robot){
		this.robot = robot;
	}
	
	
	public int getAuton(){
		int ind = (int)SmartDashboard.getNumber("Custom\\Auton", 0);
		return (ind) < robot.getAutonManager().getAutonAmount() ? ind : 0;
	}
	
	public void sendAutonDesc(int id){
		SmartDashboard.putString("Custom\\AutonDesc", 
				robot.getAutonManager().getAutons().get(id).getName());
	
	//	SmartDashboard.putString(key, value).putData("Custom\\AutonDesc", );
	}
	
	
}
