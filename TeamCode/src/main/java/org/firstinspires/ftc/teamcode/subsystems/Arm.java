package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.Constants.ARM;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {
    public HardwareMap hardwareMap;
    public DcMotor turretMotor, rotateMotor, clawMotor;
    private boolean ArmUp = false;

    public Arm(OpMode opmode) {
        this.hardwareMap = opmode.hardwareMap;
    }

    public void initArm() {
        turretMotor = hardwareMap.dcMotor.get(ARM.TURRET_MOTOR_ID);
        rotateMotor = hardwareMap.dcMotor.get(ARM.ROTATE_MOTOR_ID);
        clawMotor = hardwareMap.dcMotor.get(ARM.CLAW_MOTOR_ID);

        turretMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rotateMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        clawMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rotateMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        turretMotor.setPower(0);
        rotateMotor.setPower(0);
        clawMotor.setPower(0);
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

    public void moveArm(boolean moveLeft, boolean moveRight, boolean triangleButtonIsPressed, boolean squareButtonIsPressed, boolean circleButtonIsPressed ){
        if(triangleButtonIsPressed){
            if(!this.isBusy()){
                if(ArmUp){
                    this.setArmPosition(0);
                    ArmUp = false;
                }  else {
                    this.setArmPosition(1);
                    ArmUp = true;
                }
            }
        }

        if (moveLeft) {
            this.setTurretMotor(ARM.ARM_SPEED);
        }
        else if (moveRight) {
            this.setTurretMotor(-ARM.ARM_SPEED);
        }

        if(squareButtonIsPressed){
            clawMotor.setPower(ARM.CLAW_SPEED);
        } else if (circleButtonIsPressed) {
            clawMotor.setPower(-ARM.CLAW_SPEED);
        }
    }
}
