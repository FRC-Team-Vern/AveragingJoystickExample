package org.usfirst.frc.team5461.robot.commands;

import org.usfirst.frc.team5461.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleopJoystick extends Command {

    public TeleopJoystick() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.exerciseAveJoy);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	exerciseJoystick(Robot.oi.getJoystick());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private void exerciseJoystick(Joystick joy) {
    	double leftVal = -joy.getRawAxis(Joystick.AxisType.kY.value);
    	double rightVal = -joy.getRawAxis(Joystick.AxisType.kTwist.value);
//    	System.out.println("Final Left/Right: " + Double.toString(leftVal) + "/" + Double.toString(rightVal));
    }
}
