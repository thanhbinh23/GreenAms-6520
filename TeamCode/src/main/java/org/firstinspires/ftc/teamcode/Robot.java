package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.DriveBase;
import org.firstinspires.ftc.teamcode.subsystems.Arm;

import org.firstinspires.ftc.teamcode.Constants.ARM;

enum DriveState{
   Arcade,
   TankDrive
}

enum TurretState {
    LEFT,
    RIGHT,
    STATIONARY
}

enum ArmState {
    UP,
    DOWN,
    HOLD,
    LEAVE_OTHER_SIDE
}

enum ClawState {
    OPEN,
    CLOSE,
    HOLD
}

public class Robot {
   private final Gamepad controller;
   private final DriveBase driveBase;
   private final Telemetry telemetry;
   private DriveState driveState = DriveState.Arcade;

   private final double maxspeed = 0.8;
   private Arm arm;
   private Gamepad gamepad;
   private int threshold = -125;

   private TurretState turretState;
   private ArmState armState;
   private ClawState clawState;

   public Robot(OpMode opMode) {
       controller = opMode.gamepad1;
       gamepad = opMode.gamepad2;

       arm = new Arm(opMode);
       driveBase = new DriveBase(opMode);

       telemetry = opMode.telemetry;
   }

   public void init() {
       driveBase.init();
       arm.initArm();
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

       if(gamepad.dpad_left){
            turretState = TurretState.LEFT;
        } else if (gamepad.dpad_right){
            turretState = TurretState.RIGHT;
        } else {
            turretState = TurretState.STATIONARY;
        }

        if(gamepad.dpad_down){
            if (arm.getArmPosition()*ARM.TICKS_TO_DEGREE < threshold){
                armState = ArmState.DOWN;
            } else {
                armState = ArmState.LEAVE_OTHER_SIDE;
            }
        } else if (gamepad.dpad_up) {
            armState = ArmState.UP;
        } else {
            armState = ArmState.HOLD;
        }

        if(gamepad.square){
            clawState = ClawState.OPEN;
        } else if (gamepad.circle) {
            clawState = clawState.CLOSE;
        } else {
            clawState = clawState.HOLD;
        }

       switch (driveState){
        case TankDrive:
                driveBase.drive(controller.left_stick_y, controller.right_stick_y, controller.left_bumper);
            break;

        case Arcade:
            driveBase.driveArcade(controller.left_stick_y, -controller.right_stick_x);
            break;
       }

        switch(turretState){
            case LEFT:
                arm.setTurretMotor(ARM.TURRET_MOVE_LEFT);
                break;
            case RIGHT:
                arm.setTurretMotor(ARM.TURRET_MOVE_RIGHT);
                break;
            case STATIONARY:
                arm.setTurretMotor(0);
                break;
        }

        switch (armState){
            case UP:
                arm.setArmMotor(ARM.ARM_MOVE_UP);
                break;
            case DOWN:
                arm.setArmMotor(ARM.ARM_MOVE_DOWN);
                break;
            case HOLD:
                arm.setArmMotor(-0.3);
                break;
            case LEAVE_OTHER_SIDE:
                arm.setArmMotor(1);
                break;
        }

        switch (clawState) {
            case OPEN:
                arm.setClawMotor(ARM.CLAW_OPEN);
                break;
            case CLOSE:
                arm.setClawMotor(ARM.CLAW_CLOSE);
                break;
            case HOLD:
                arm.setClawMotor(0);
                break;
    }

   }
}

