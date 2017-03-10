package org.usfirst.frc.team5695.robot.component;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class SolenoidBallGate extends AbstractSolenoid{
	protected SolenoidBallGate(){
		super(2,3);
	}
}