package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static org.firstinspires.ftc.teamcode.Constants.MOTOR;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveBase {
    private DcMotorEx rightMaster, rightFollow, leftMaster, leftFollow;

    private Telemetry telemetry;
    private HardwareMap hardwareMap;

    public DriveBase(OpMode opMode) {
        this.telemetry = opMode.telemetry;
        this.hardwareMap = opMode.hardwareMap;
    }

    public void init(boolean auto) {
        rightMaster = hardwareMap.get(DcMotorEx.class, MOTOR.RIGHTMASTER);
        rightFollow = hardwareMap.get(DcMotorEx.class, MOTOR.RIGHTFOLLOW);
        leftMaster = hardwareMap.get(DcMotorEx.class, MOTOR.LEFTMASTER);
        leftFollow = hardwareMap.get(DcMotorEx.class, MOTOR.LEFTFOLLOW);

        rightMaster.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFollow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMaster.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFollow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void drive(double rightMasterSpeed, double rightFollowerSpeed, double leftMasterSpeed, double leftFollowerSpeed) {
        rightMaster.setPower(rightMasterSpeed);
        rightFollow.setPower(rightFollowerSpeed);
        leftMaster.setPower(leftMasterSpeed);
        leftFollow.setPower(leftFollowerSpeed);

        telemetry.addData("rightMaster, rightFollow, leftMaster, leftFollow", "%.1 %.1 %.1 %.1", rightMasterSpeed, rightFollowerSpeed, leftMasterSpeed, leftFollowerSpeed);
    }

    public void driveController(double left, double right) {
        drive(right, right, left, left);
    }

    private void calculatePower(double x, double y, double r) {
        double leftFrontPower = y + x + r;
        double leftBackPower = y - x + r;
        double rightFrontPower = y - x - r;
        double rightBackPower = y + x - r;

        drive(leftFrontPower, leftBackPower, rightFrontPower, rightBackPower);
    }
}
