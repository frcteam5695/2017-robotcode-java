package org.usfirst.frc.team5695.robot;

import java.util.concurrent.ExecutorService;

import org.usfirst.frc.team5695.robot.autonomous.AutonManager;
import org.usfirst.frc.team5695.robot.autonomous.AutonMode;
import org.usfirst.frc.team5695.robot.autonomous.StepCommands;
import org.usfirst.frc.team5695.robot.component.ComponentManager;
import org.usfirst.frc.team5695.robot.component.Controller;
import org.usfirst.frc.team5695.robot.utility.Dashboard;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public final class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	
	
	
	private ComponentManager components;
	private AutonManager autonManager;
	private Dashboard dashboard;

	
	private int currentMode = 0;
	private boolean running = false;
	

	

	

	
	public ComponentManager getComponentManager(){
		return components;
	}
	public  AutonManager getAutonManager(){
		return autonManager;
	}
	public void cleanUp(){
		components.dispose();
	}
	
	public void disabledInit(){
		
	}
	
	public void disabledPeriodic(){
		
	}
	
	public void robotPeriodic(){
		currentMode = dashboard.getAuton();
		dashboard.sendAutonDesc(currentMode);
	}
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		dashboard = new Dashboard(this);
		
	
		(components = new ComponentManager()).init();;
		StepCommands.init(components);
		
		(autonManager = new AutonManager(this)).init();;
		
		running  = true;
		//mainLoop.start();
		
		
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser); 
		/*while(running){
			currentMode = dashboard.getAuton();
			dashboard.sendAutonDesc(currentMode);
		
		
		
			Timer.delay(0.02);
		}*/
		
		/////
	
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	
	public void stopRobot(){
		running  = false;
		StepCommands.dispose();
		autonManager.dispose();
	}
	

	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	
	public void teleopPeriodic() {
		Controller ce = components.getController();
		components.getSolenoidBallGate().setOpen(ce.getButtonX());
		components.getSolenoidGear().setOpen(ce.getButtonB());
		
		components.getMotorClimb().setEnabled(ce.getButtonY());
		components.getMotorDrive().drive(ce.getAxisX(Hand.kRight),ce.getAxisY(Hand.kLeft), ce.getAxisX(Hand.kLeft));
		
	}

	/**
	 * This function is called periodically during test mode
	 */

	@Override
	public void teleopInit(){
		
	}
	
	public static IllegalStateException EXalreadyInit(){
		return new IllegalStateException("Already Initialized.");
	}
	
	public static IllegalStateException EXnotInit(){
		return new IllegalStateException("Not yet Initialized.");
	}
}

