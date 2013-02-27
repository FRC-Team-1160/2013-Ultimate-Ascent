package com.team1160.feathersMcGraw;

import com.team1160.feathersMcGraw.api.Printer;
import com.team1160.feathersMcGraw.commands.RobotCommand;
import com.team1160.feathersMcGraw.drive.DriveTrain;
import com.team1160.feathersMcGraw.input.InputManager;
import com.team1160.feathersMcGraw.input.InputState;
import com.team1160.feathersMcGraw.output.OutputManager;
import com.team1160.feathersMcGraw.teleop.TeleopManager;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.IterativeRobot;

/*
 * Welcom to team 1160's robot code. This code is for the 2013 frc robotics 
 * game: ultimate ascet, and our robot code named feathers Mc Graw. For more info
 * visit us at:
 * 
 * Github:  https://github.com/FRC-Team-1160
 * Website: http://www.titaniumrobotics.com/
 * 
 * Hopefully there will be info up there about our robot code and electrical
 * where we sourced stuff etc... no promises though they were not there when
 * I wrote this code. Also this code will be commented hopefully to the point
 * where you can understand why we did what we did... how ever some parts of
 * it might not make sense unless you have seen our bot. Any way this was kinda
 * long winded but what ever enjoy
 * 
 * @Author Wallace
 */
public class team1160 extends IterativeRobot {
	
    InputManager im;
    TeleopManager tm;
    OutputManager om;
    
    InputState is;
    RobotCommand cmd;
    
    DriveTrain dt;

    AnalogChannel test = new AnalogChannel(4);
    
    long lastPrintTime;
	
        public void robotInit(){
//        	System.out.println("In robo init good luck...");
//        	im = InputManager.getInstance();
//            tm = TeleopManager.getInstance();
//            om= OutputManager.getInstance();
//            is = new InputState();
//            cmd = new RobotCommand();
//            lastPrintTime = System.currentTimeMillis();
        }
        
        public void teleopInit(){
        }
        
        public void teleopPeriodic(){   
            // This is the main loop the overall function of our robot in three lines
        	is = im.getInputState();      // An input state is created by the input manager
            cmd = tm.getCommand(is);      // This is then fed to the teleop manager to create a robot command
            om.setOutput(cmd);            // This is then set to be output
            
            // This displays our data on the console for debugging / calibration purposes
            // Thinking about changing it to smart dashboard but more for a later time
            
            if(System.currentTimeMillis() - lastPrintTime > 5000){  
                Printer.print();
                lastPrintTime = System.currentTimeMillis();
            }
        }
        
	public void autonomousInit(){
		lastPrintTime = System.currentTimeMillis();
        }
	public void autonomousPeriodic(){
		double v = test.getVoltage();
		double x = (.84*v*v)+(14.91*v)-11.187+4.5;
		if(System.currentTimeMillis() - lastPrintTime > 3000){
			System.out.println("Length");
			System.out.println(x + " Inches");
			System.out.println("Pot");
			System.out.println(v + " Volts");
			lastPrintTime = System.currentTimeMillis();
		}
	}
	
	public void testInit(){
                   }
	
	public void testPeriodic(){

	}
			
	public void disabledInit(){
		
	}	
}
