package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.Constants.ARM;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {
    public HardwareMap hardwareMap;
    public DcMotor turretMotor;
    private DcMotor rotateMotor;

    public Arm(OpMode opmode) {
        this.hardwareMap = opmode.hardwareMap;
    }

    public void initArm() {
        turretMotor = hardwareMap.dcMotor.get(ARM.TURRET_MOTOR_ID);
        rotateMotor = hardwareMap.dcMotor.get(ARM.ROTATE_MOTOR_ID);

        turretMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rotateMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        turretMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotateMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        turretMotor.setPower(0);
        rotateMotor.setPower(0);
    }

    public void setArmPosition( int desiredPosition) {
        rotateMotor.setTargetPosition(desiredPosition);
    }

    public void setTurretMotor(double turretSpeed) {
        turretMotor.setPower(turretSpeed);
    }

    public boolean isBusy() {
        return rotateMotor.isBusy();
    }

    public void moveArm(boolean moveLeft, boolean moveRight, boolean squareButton){
        if (!this.isBusy()) {
            if (squareButton) {
                this.setArmPosition(1);
            }
        }

        this.setArmPosition(0);
        
        if (moveLeft) {
            this.setTurretMotor(ARM.ARM_SPEED);
        }
        else if (moveRight) {
            this.setTurretMotor(-ARM.ARM_SPEED);
        }
    }
}
