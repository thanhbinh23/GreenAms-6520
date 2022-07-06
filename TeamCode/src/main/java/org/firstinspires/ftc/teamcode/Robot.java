package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.DriveBase;

enum DriveState{
   Arcade,
   TankDrive
}

public class Robot {
   private final Gamepad controller;
   private final DriveBase driveBase;
   private final Telemetry telemetry;
   private DriveState driveState = DriveState.Arcade;

   private final double maxspeed = 0.8;

   public Robot(OpMode opMode) {
       controller = opMode.gamepad1;

       telemetry = opMode.telemetry;

       driveBase = new DriveBase(opMode);
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

