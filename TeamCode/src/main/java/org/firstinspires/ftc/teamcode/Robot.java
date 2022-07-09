package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import static org.firstinspires.ftc.teamcode.Constants.ARM;

public class Robot {
    private Arm arm;
    private Gamepad FuntionalityGamepad;
    private int threshold = -125;

    private enum TurretState {
        LEFT,
        RIGHT,
        STATIONARY
    }

    private enum ArmState {
        UP,
        DOWN,
        HOLD,
        LEAVE_OTHER_SIDE
    }

    private enum ClawState {
        OPEN,
        CLOSE,
        HOLD
    }

    private TurretState turretState;
    private ArmState armState;
    private ClawState clawState;

    public Robot(OpMode opmode) {
        arm = new Arm(opmode);
        FuntionalityGamepad = opmode.gamepad1;
    }

    public void init() {
        arm.initArm();
    }

    public void teleop() {

        if(FuntionalityGamepad.dpad_left){
            turretState = TurretState.LEFT;
        } else if (FuntionalityGamepad.dpad_right){
            turretState = TurretState.RIGHT;
        } else {
            turretState = TurretState.STATIONARY;
        }

        if(FuntionalityGamepad.dpad_down){
            if (arm.getArmPosition()*ARM.TICKS_TO_DEGREE < threshold){
                armState = ArmState.DOWN;
            } else {
                armState = ArmState.LEAVE_OTHER_SIDE;
            }
        } else if (FuntionalityGamepad.dpad_up) {
            armState = ArmState.UP;
        } else {
            armState = ArmState.HOLD;
        }

        if(FuntionalityGamepad.square){
            clawState = ClawState.OPEN;
        } else if (FuntionalityGamepad.circle) {
            clawState = clawState.CLOSE;
        } else {
            clawState = clawState.HOLD;
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
