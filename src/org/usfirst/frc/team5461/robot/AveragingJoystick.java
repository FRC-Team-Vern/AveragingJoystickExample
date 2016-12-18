package org.usfirst.frc.team5461.robot;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import edu.wpi.first.wpilibj.Joystick;

public class AveragingJoystick extends Joystick {
	
	static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;
	private static final Logger logger = Logger.getLogger(AveragingJoystick.class.getName());
	
	private static final int AVERAGE_SIZE = 10;
	private ArrayDeque<Double> mLeftJoyStack = new ArrayDeque<>();
	private ArrayDeque<Double> mRightJoyStack = new ArrayDeque<>();

	public AveragingJoystick(int port) {
		super(port);
		try {
			setupLogger();
		} catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
		}
		initializeLeftRightStacks();
	}
	
	private void setupLogger() throws IOException {
		// suppress the logging output to the console
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
                rootLogger.removeHandler(handlers[0]);
        }

        logger.setLevel(Level.INFO);
        fileTxt = new FileHandler("/home/lvuser/AveragingJoystick.txt");

        // create a TXT formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);
	}
	
	private void initializeLeftRightStacks() {
		for (int i=0; i<AVERAGE_SIZE; ++i) {
			mLeftJoyStack.add(0.0);
			mRightJoyStack.add(0.0);
		}
	}
	
	@Override
	public double getRawAxis(int axis) {
		double returnVal = super.getRawAxis(axis);
		if (axis == Joystick.AxisType.kY.value) {  // Left Value
			// Update left and right values
			double currentLeftVal = super.getRawAxis(Joystick.AxisType.kY.value);
//			logger.info("Left Val: " + Double.toString(currentLeftVal));
			mLeftJoyStack.add(currentLeftVal);
			mLeftJoyStack.pop();
			double currentRightVal = super.getRawAxis(Joystick.AxisType.kTwist.value);
//			logger.info("Right Val: " + Double.toString(currentRightVal));
			mRightJoyStack.add(currentRightVal);
			mRightJoyStack.pop();

			// Return average left value
			// Use Java 8 streams
			double leftSum = mLeftJoyStack.stream().mapToDouble(Double::doubleValue).sum();
			String leftAve = Double.toString(leftSum / mLeftJoyStack.size());
			double rightSum = mRightJoyStack.stream().mapToDouble(Double::doubleValue).sum();
			String rightAve = Double.toString(rightSum / mRightJoyStack.size());
			logger.info(Double.toString(currentLeftVal) + "," + leftAve + "," + Double.toString(currentRightVal) + "," + rightAve);
			returnVal = leftSum / mLeftJoyStack.size();
		} else if (axis == Joystick.AxisType.kTwist.value) { // Right Value
			// Return right value
			double rightSum = mRightJoyStack.stream().mapToDouble(Double::doubleValue).sum();
			returnVal = rightSum / mRightJoyStack.size();
		}
		return returnVal;
	}
}
