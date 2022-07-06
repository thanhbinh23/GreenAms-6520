package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static org.firstinspires.ftc.teamcode.Constants.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveBase {
    private DcMotor rightMaster, rightFollow, leftMaster, leftFollow;

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

    public void drive(double left, double right) {
        driveAll(right, right, left, left);
    }

    public void driveArcade(double forwardPower, double turnPower){
       double L = (2*forwardPower-turnPower* MOTOR.d)/2;
       double R = turnPower*MOTOR.d+forwardPower;
       drive(L,R);
    }
}
