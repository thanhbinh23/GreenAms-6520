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

    public DriveBase(OpMode opMode){
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

    public void drive(double rms, double rfs, double lms, double lfs){
        rightMaster.setPower(rms);
        rightFollow.setPower(rfs);
        leftMaster.setPower(lms);
        leftFollow.setPower(lfs);

        telemetry.addData("rightMaster, rightFollow, leftMaster, leftFollow", "%.1 %.1 %.1 %.1", rms, rfs, lms, lfs);
    }

    private void calculatePower(double x, double y, double r){
        double lfp = y + x + r;
        double lbp = y - x + r;
        double rfp = y - x - r;
        double rbp = y + x - r;

        drive(lfp, lbp, rfp, rbp);
    }
}
