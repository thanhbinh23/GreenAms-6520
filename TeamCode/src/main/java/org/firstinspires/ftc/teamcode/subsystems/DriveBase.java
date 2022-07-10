package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static org.firstinspires.ftc.teamcode.Constants.*;
import static org.firstinspires.ftc.teamcode.Constants.AUTONOMOUS.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveBase {
   public DcMotor rightMaster, rightFollow, leftMaster, leftFollow;

   private Telemetry telemetry;
   private HardwareMap hardwareMap;

   public DriveBase(OpMode opMode) {
       this.telemetry = opMode.telemetry;
       this.hardwareMap = opMode.hardwareMap;
   }

   public void init() {

       rightMaster = hardwareMap.dcMotor.get("rm");
       rightFollow = hardwareMap.dcMotor.get("rf");
       leftMaster = hardwareMap.dcMotor.get( "lm");
       leftFollow = hardwareMap.dcMotor.get( "lf");

       rightMaster.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       rightFollow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       leftMaster.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       leftFollow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

       rightFollow.setDirection(DcMotorSimple.Direction.REVERSE);
       rightMaster.setDirection(DcMotorSimple.Direction.REVERSE);

   }

   public void driveAll(double rightMasterSpeed, double rightFollowerSpeed, double leftMasterSpeed, double leftFollowerSpeed) {
       rightMaster.setPower(rightMasterSpeed);
       rightFollow.setPower(rightFollowerSpeed);
       leftMaster.setPower(leftMasterSpeed);
       leftFollow.setPower(leftFollowerSpeed);

       //telemetry.addData("rightMaster, rightFollow, leftMaster, leftFollow", "%.1 %.1 %.1 %.1", rightMasterSpeed, rightFollowerSpeed, leftMasterSpeed, leftFollowerSpeed);
   }

   public void drive(double left, double right, boolean boost) {
       if(boost){
           driveAll(right, right, left, left);
       } else {
           driveAll(right*0.8, right*0.8, left*0.8, left*0.8);
       }

   }

   public void driveArcade(double forwardPower, double turnPower, boolean boost){
      double L = (2*forwardPower-turnPower* MOTOR.d)/2;
      double R = turnPower*MOTOR.d+forwardPower;

      drive(L,R,boost);

   }

    public void driveDistance(double distanceToDrive, double powerToDrive){

       leftMaster.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       rightMaster.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       leftFollow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       rightFollow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

       leftMaster.setTargetPosition((int)(distanceToDrive*ticksPerMeter));
       rightMaster.setTargetPosition((int)(distanceToDrive*ticksPerMeter));
       leftFollow.setTargetPosition((int)(distanceToDrive*ticksPerMeter));
       rightFollow.setTargetPosition((int)(distanceToDrive*ticksPerMeter));

       leftMaster.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       leftFollow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       rightMaster.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       rightFollow.setMode(DcMotor.RunMode.RUN_TO_POSITION);

       leftMaster.setPower(powerToDrive);
       leftFollow.setPower(powerToDrive);
       rightMaster.setPower(powerToDrive);
       rightFollow.setPower(powerToDrive);

       while(isBusy());


    }

    public void turnDistance(double angleToTurn, double powerToDrive){
       double distancetoDrive = angleToTurn*MOTOR.d/2;

       leftMaster.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       leftFollow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       rightMaster.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       rightFollow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

       leftMaster.setTargetPosition((int)(distancetoDrive*ticksPerMeter));
       leftFollow.setTargetPosition((int)(distancetoDrive*ticksPerMeter));
       rightMaster.setTargetPosition((int)(-distancetoDrive*ticksPerMeter));
       rightFollow.setTargetPosition((int)(-distancetoDrive*ticksPerMeter));

        leftMaster.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFollow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMaster.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFollow.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMaster.setPower(powerToDrive);
        leftFollow.setPower(powerToDrive);
        rightMaster.setPower(powerToDrive);
        rightFollow.setPower(powerToDrive);

        while(isBusy());

    }
    public boolean isBusy(){
       return leftFollow.isBusy() || leftMaster.isBusy() || rightFollow.isBusy() || rightMaster.getPowerFloat();
    }

}

