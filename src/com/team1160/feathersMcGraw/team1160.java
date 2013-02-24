package com.team1160.feathersMcGraw;

import com.team1160.feathersMcGraw.api.DigitalServo;
import com.team1160.feathersMcGraw.api.Printer;
import com.team1160.feathersMcGraw.commands.RobotCommand;
import com.team1160.feathersMcGraw.input.InputManager;
import com.team1160.feathersMcGraw.input.InputState;
import com.team1160.feathersMcGraw.teleop.TeleopManager;
import com.team1160.feathersMcGraw.output.OutputManager;
import edu.wpi.first.wpilibj.Gyro;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    
    
    DigitalServo grip;
    DigitalServo arm;
    SmartDashboard sd;
    Joystick js;
    
    Servo test;
    Gyro g;
    
    
    long lastPrintTime;
	
        public void robotInit(){
//            im = InputManager.getInstance();
//            tm = TeleopManager.getInstance();
//            om= OutputManager.getInstance();
//            is = new InputState();
//            cmd = new RobotCommand();
//            lastPrintTime = System.currentTimeMillis();
                        g = new Gyro(1);
            System.out.println("gyro added");
            System.out.println("paerfhaeuhgfaruhlsrhtge;ohsugh;zdkuh");            
        }
        
        public void teleopInit(){
 
         lastPrintTime = System.currentTimeMillis();
        g.reset();
        }
        
        public void teleopPeriodic(){
//            is = im.getInputState();
//            cmd = tm.getCommand(is);
//            om.setOutput(cmd);
//            if(System.currentTimeMillis() - lastPrintTime > 3000){
//                Printer.print();
//                lastPrintTime = System.currentTimeMillis();
//            }
             System.out.println(g.getAngle()+" Degrees. Running for: " + (System.currentTimeMillis()-lastPrintTime));
        }
        
	public void autonomousInit(){
                test.free();
                System.out.println("test freed");
        
        }
	public void autoPeriodic(){
		
	}
	
	public void testInit(){
                   }
	
	public void testPeriodic(){

	}
			
	public void disabledInit(){
		
	}
	
	
}
