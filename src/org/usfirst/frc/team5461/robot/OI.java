package org.usfirst.frc.team5461.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private Joystick joystick = new AveragingJoystick(0);
	
	public Joystick getJoystick() {
		return joystick;
	}
}

