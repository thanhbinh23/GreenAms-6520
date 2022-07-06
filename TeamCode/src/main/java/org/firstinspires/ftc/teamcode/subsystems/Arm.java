package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {
    public HardwareMap hardwareMap;
    public int desiredPosition;
    public DcMotor turretMotor;
    private DcMotor rotateMotor;

    public Arm(OpMode opmode) {
        this.hardwareMap = opmode.hardwareMap;
    }

    public void initArm() {
        turretMotor = hardwareMap.dcMotor.get("Intake vertical motor");
        rotateMotor = hardwareMap.dcMotor.get("Intake horizontal motor");

        turretMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rotateMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        turretMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotateMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        turretMotor.setPower(0);
        rotateMotor.setPower(0);
    }

    public void setPosition() {
        rotateMotor.setTargetPosition(desiredPosition);
    }

    public void setTurretMotor(double turretSpeed) {
        turretMotor.setPower(turretSpeed);
    }

    public boolean isBusy() {
        return rotateMotor.isBusy();
    }

}
