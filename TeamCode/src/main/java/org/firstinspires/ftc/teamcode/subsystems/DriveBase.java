package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.Constants.AUTONOMOUS.ticksPerMeter;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class DriveBase {
    //the amount of power to each motor when joystick is fully to one side
    public static final double turnGoal = 0.4;
    public static final double forwardGoal = 0.6;
    private DcMotorEx rightMaster, rightFollow, leftMaster, leftFollow;
    private Telemetry telemetry;
    private HardwareMap hardwareMap;

    public DriveBase(OpMode opMode) {
        this.telemetry = opMode.telemetry;
        this.hardwareMap = opMode.hardwareMap;
    }

    public void init() {

        rightMaster = (DcMotorEx) hardwareMap.dcMotor.get("rm");
        rightFollow = (DcMotorEx) hardwareMap.dcMotor.get("rf");
        leftMaster = (DcMotorEx) hardwareMap.dcMotor.get("lm");
        leftFollow = (DcMotorEx) hardwareMap.dcMotor.get("lf");

        rightMaster.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFollow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMaster.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFollow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightFollow.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMaster.setDirection(DcMotorSimple.Direction.REVERSE);

        rightMaster.setTargetPositionTolerance((int) (0.025 * ticksPerMeter));
        leftFollow.setTargetPositionTolerance((int) (0.025 * ticksPerMeter));
        rightFollow.setTargetPositionTolerance((int) (0.025 * ticksPerMeter));
        leftMaster.setTargetPositionTolerance((int) (0.025 * ticksPerMeter));
    }

    public void driveAll(double rightMasterSpeed, double rightFollowerSpeed, double leftMasterSpeed, double leftFollowerSpeed) {
        rightMaster.setPower(rightMasterSpeed);
        rightFollow.setPower(rightFollowerSpeed);
        leftMaster.setPower(leftMasterSpeed);
        leftFollow.setPower(leftFollowerSpeed);

        //telemetry.addData("rightMaster, rightFollow, leftMaster, leftFollow", "%.1 %.1 %.1 %.1", rightMasterSpeed, rightFollowerSpeed, leftMasterSpeed, leftFollowerSpeed);
    }

    public void drive(double left, double right, boolean boost) {
        if (boost) {
            driveAll(right, right, left, left);
        } else {
            driveAll(right * 0.8, right * 0.8, left * 0.8, left * 0.8);
        }

    }

    public void drive(double left, double right) {
        drive(left, right, true);
    }

    public void driveArcade(double forwardPower, double turnPower) {
        double turnPart = turnPower * turnGoal;
        double forwardPart = forwardPower * forwardGoal;
        double L = forwardPart - turnPart;
        double R = forwardPart + turnPart;
        drive(L, R);

    }

    public void driveDistance(double distanceToDrive, double powerToDrive) {

        leftMaster.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMaster.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFollow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFollow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMaster.setTargetPosition((int) (distanceToDrive * ticksPerMeter));
        rightMaster.setTargetPosition((int) (distanceToDrive * ticksPerMeter));
        leftFollow.setTargetPosition((int) (distanceToDrive * ticksPerMeter));
        rightFollow.setTargetPosition((int) (distanceToDrive * ticksPerMeter));

        leftMaster.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFollow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMaster.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFollow.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMaster.setPower(powerToDrive);
        leftFollow.setPower(powerToDrive);
        rightMaster.setPower(powerToDrive);
        rightFollow.setPower(powerToDrive);

        while (isBusy()) ;


    }

    public void turnDistance(double angleToTurn, double powerToDrive) {
        double distancetoDrive = angleToTurn * Constants.MOTOR.d / 2;

        leftMaster.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFollow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMaster.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFollow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMaster.setTargetPosition((int) (distancetoDrive * ticksPerMeter));
        leftFollow.setTargetPosition((int) (distancetoDrive * ticksPerMeter));
        rightMaster.setTargetPosition((int) (-distancetoDrive * ticksPerMeter));
        rightFollow.setTargetPosition((int) (-distancetoDrive * ticksPerMeter));

        leftMaster.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFollow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMaster.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFollow.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftMaster.setPower(powerToDrive);
        leftFollow.setPower(powerToDrive);
        rightMaster.setPower(powerToDrive);
        rightFollow.setPower(powerToDrive);

        while (isBusy()) {
            telemetry.addData("LeftPosition", leftMaster.getCurrentPosition());
            telemetry.addData("RightPosition", rightMaster.getCurrentPosition());
            telemetry.addData("Left Target", leftMaster.getTargetPosition());
            telemetry.addData("Right Position", rightMaster.getTargetPosition());
            telemetry.update();
        }
    }

    public double getPosition() {
        return leftFollow.getCurrentPosition();
    }

    public boolean isBusy() {
        return leftFollow.isBusy() || leftMaster.isBusy() || rightFollow.isBusy() || rightMaster.getPowerFloat();
    }
}