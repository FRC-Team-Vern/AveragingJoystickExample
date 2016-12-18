package org.usfirst.frc.team5461.robot.subsystems;

import org.usfirst.frc.team5461.robot.commands.TeleopJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ExerciseAveragingJoystick extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new TeleopJoystick());
    }
}

