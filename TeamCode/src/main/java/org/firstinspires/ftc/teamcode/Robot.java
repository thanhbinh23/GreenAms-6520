package org.firstinspires.ftc.teamcode;

//import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.DriveBase;

enum DriveState{
   Arcade,
   TankDrive
}

public class Robot {
   private Gamepad controller;
   private final DriveBase driveBase;
   private Telemetry telemetry;
   private DriveState driveState = DriveState.Arcade;
   private static Robot instance;


   private Robot(OpMode opMode) {
       setup(opMode);


       driveBase = new DriveBase(opMode);
   }

    public static Robot getInstance(OpMode opMode) {
        if(instance == null){
            instance = new Robot(opMode);

        }
        instance.setup(opMode);
        return instance;
    }

    public void setup(OpMode opMode){
       controller = opMode.gamepad1;
       telemetry = opMode.telemetry;
    }

    public void auto1() {
       driveBase.driveDistance(0.3, 0.5);
       driveBase.turnDistance(56.4,-0.5);
       driveBase.driveDistance(Math.sqrt(40*40+60*60),0.5);
       driveBase.turnDistance(180,0.5);
       driveBase.driveDistance(Math.sqrt(40*40+60*60),0.5);
       driveBase.turnDistance(56.4,-0.5);
       driveBase.driveDistance(1.2,0.8);
   }

   public void auto2(){
       driveBase.turnDistance(90,-0.5);
       driveBase.driveDistance(0.3,0.5);
       driveBase.turnDistance(135,0.5);
       driveBase.driveDistance(1,0.5);
       driveBase.turnDistance(45,0.5);
       driveBase.driveDistance(2,0.8);
   }

   public void init() {
       driveBase.init();
   }


   public void start() {

   }

   public void teleop() {

       if(controller.options){
           if(driveState == DriveState.TankDrive){
               driveState = DriveState.Arcade;
           } else {
               driveState = DriveState.TankDrive;
           }
       }

       switch (driveState){
           case TankDrive:
               driveBase.drive(controller.left_stick_y, controller.right_stick_y, controller.left_bumper);
               break;

           case Arcade:
               driveBase.driveArcade(controller.left_stick_y, -controller.right_stick_x, controller.left_bumper);
               break;
       }

   }


}

