
package org.usfirst.frc.team5461.robot;

import java.io.File;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team5461.robot.subsystems.ExerciseAveragingJoystick;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExerciseAveragingJoystick exerciseAveJoy = new ExerciseAveragingJoystick();
	public static OI oi;

    Command autonomousCommand;
//    SendableChooser chooser;
    
    static Logger logger = LoggerFactory.getLogger(Robot.class);
    DataLogger dataLogger;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		
		// Set dataLogger and Time information
		TimeZone.setDefault(TimeZone.getTimeZone("America/Denver"));
		
		File logDirectory = null;
		if (logDirectory == null) logDirectory = findLogDirectory(new File("/u"));
		if (logDirectory == null) logDirectory = findLogDirectory(new File("/v"));
		if (logDirectory == null) logDirectory = findLogDirectory(new File("/x"));
		if (logDirectory == null) logDirectory = findLogDirectory(new File("/y"));
		if (logDirectory == null) {
			logDirectory = new File("/home/lvuser/logs");
		    if (!logDirectory.exists())
		    {
			    logDirectory.mkdir();
		    }
		}
		if (logDirectory != null && logDirectory.isDirectory())
		{
			String logMessage = String.format("Log directory is %s\n", logDirectory);
			System.out.print (logMessage);
			EventLogging.writeToDS(logMessage);
			EventLogging.setup(logDirectory);
			dataLogger = new DataLogger(logDirectory);
			dataLogger.setMinimumInterval(1000);
		}

		logger.info ("Starting robotInit");
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public File findLogDirectory (File root) {
		// does the root directory exist?
		if (!root.isDirectory()) return null;
		
		File logDirectory = new File(root, "logs");
		if (!logDirectory.isDirectory()) return null;
		
		return logDirectory;
	}
}
