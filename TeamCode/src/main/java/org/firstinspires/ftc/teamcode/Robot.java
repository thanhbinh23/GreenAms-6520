package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.DriveBase;
import org.firstinspires.ftc.teamcode.subsystems.Arm;

import org.firstinspires.ftc.teamcode.Constants.ARM;
import org.firstinspires.ftc.teamcode.subsystems.Spinner;

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

enum SpinnerState {
    SPIN_LEFT,
    SPIN_RIGHT,
    SPIN_IDLE
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

enum SpinnerState {
    SPIN_LEFT,
    SPIN_RIGHT,
    SPIN_IDLE
}

public class Robot {
    private  Gamepad controller, gamepad;
    private  Telemetry telemetry;
    private OpMode opMode;

    private final DriveBase driveBase;
    private final Spinner spinner;
    private Arm arm;
    private Arm turret; //check class if correct

    private int threshold = -125;

    private TurretState turretState;
    private ArmState armState;
    private ClawState clawState;
    private SpinnerState spinnerState;
    private DriveState driveState = DriveState.Arcade;
    private static Robot instance;

    public Robot(OpMode opMode) {
        controller = opMode.gamepad1;
        gamepad = opMode.gamepad2;
        this.opMode = opMode;

        arm = new Arm(opMode);
        driveBase = new DriveBase(opMode);
        spinner = new Spinner(opMode);
        turret = new Arm(opMode);

        telemetry = opMode.telemetry;
    }

    public static Robot getInstance(OpMode opMode) {
        if(instance == null){
            instance = new Robot(opMode);}
        instance.setup(opMode);
        return instance;
    }
    public void setup(OpMode opMode){
        controller = opMode.gamepad1;
        telemetry = opMode.telemetry;}

    public void init() {
        driveBase.init();
        arm.initArm();
        spinner.init();
        turret.initArm();
    }

    public void start() {
        telemetry.addData("GART", 99999);
    }

    public void redAllianceRight() {
        driveBase.driveDistance(0.15, 0.5);
        driveBase.turnDistance(-0.98,0.4);
        turret.TurretTurnAngle(Math.PI*0.75,0.65);
        //while(turret.isBusy());
        arm.ArmMoving(-Math.PI/7, 0.6);
        while (arm.isArmBusy());
        driveBase.driveDistance(0.24,0.5);
        while(driveBase.isBusy() && turret.isTurretBusy());
        arm.setClawMotorWithdistance(-600,-0.30);
        driveBase.turnDistance(-Math.PI/7,0.7);
        driveBase.driveDistance(-1.4,1);
        turret.TurretTurnAngle(-Math.PI*0.7,0.7);
        while(turret.isTurretBusy());


    }

    public void blueAllianceLeft(){
        driveBase.driveDistance(0.15, 0.5);
        driveBase.turnDistance(0.98,0.4);
        arm.ArmMoving(-Math.PI/7, 0.6);
        while (arm.isArmBusy());
        turret.TurretTurnAngle(Math.PI*0.73,0.65);
        //while(turret.isBusy());
        while (turret.isTurretBusy());
        driveBase.driveDistance(0.24,0.5);
        while(driveBase.isBusy() && turret.isTurretBusy());
        arm.setClawMotorWithdistance(-600,-0.3);
        driveBase.turnDistance(Math.PI/7,0.7);
        driveBase.driveDistance(-1.4,1);
        turret.TurretTurnAngle(-Math.PI*0.65,0.7);
        while(turret.isTurretBusy());



    }
//    }
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
            if (gamepad.right_bumper){
                armState = ArmState.LEAVE_OTHER_SIDE;
            } else {
                armState = ArmState.DOWN;
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

        if(gamepad.right_trigger != 0){
            spinnerState = SpinnerState.SPIN_RIGHT;
        } else if (gamepad.left_trigger != 0) {
            spinnerState = SpinnerState.SPIN_LEFT;
        } else {
            spinnerState = SpinnerState.SPIN_IDLE;
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
                arm.setTurretMotor(Constants.ARM.TURRET_MOVE_LEFT);
                break;
            case RIGHT:
                arm.setTurretMotor(Constants.ARM.TURRET_MOVE_RIGHT);
                break;
            case STATIONARY:
                arm.setTurretMotor(0);
                break;
        }

        switch (armState){
            case UP:
                arm.setArmMotor(Constants.ARM.ARM_MOVE_UP);
                break;
            case DOWN:
                arm.setArmMotor(Constants.ARM.ARM_MOVE_DOWN);
                break;
            case HOLD:
                arm.setArmMotor(-0.1);
                break;
            case LEAVE_OTHER_SIDE:
                arm.setArmMotor(1);
                break;
        }

        switch (clawState) {
            case OPEN:
                arm.setClawMotor(Constants.ARM.CLAW_OPEN);
                break;
            case CLOSE:
                arm.setClawMotor(Constants.ARM.CLAW_CLOSE);
                break;
            case HOLD:
                arm.setClawMotor(0);
                break;
        }

        switch (spinnerState){
            case SPIN_RIGHT:
                spinner.spin(gamepad.left_trigger);
                break;
            case SPIN_LEFT:
                spinner.spin(gamepad.right_trigger);
                break;
            case SPIN_IDLE:
                spinner.spin(0);
                break;
        }

    }
}
