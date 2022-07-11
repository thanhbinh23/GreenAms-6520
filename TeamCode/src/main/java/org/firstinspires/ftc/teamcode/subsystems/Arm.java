

package org.firstinspires.ftc.teamcode.subsystems;

<<<<<<< HEAD
import static org.firstinspires.ftc.teamcode.Constants.ARM;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
=======
        import static org.firstinspires.ftc.teamcode.Constants.ARM;

        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorEx;
        import com.qualcomm.robotcore.hardware.HardwareMap;
        import com.qualcomm.robotcore.util.ElapsedTime;

        import org.firstinspires.ftc.robotcore.external.Telemetry;
        import org.firstinspires.ftc.teamcode.Constants;
>>>>>>> AutoUpgrade

public class Arm {
    private HardwareMap hardwareMap;
    private Telemetry telemetry;
<<<<<<< HEAD
    private DcMotor turretMotor, armMotor, clawMotor;
=======
    private DcMotorEx turretMotor, armMotor, clawMotor;
    private ElapsedTime runtime = new ElapsedTime();
>>>>>>> AutoUpgrade

    public Arm(OpMode opMode) {
        this.telemetry = opMode.telemetry;
        this.hardwareMap = opMode.hardwareMap;
    }

    public void initArm() {
<<<<<<< HEAD
        turretMotor = hardwareMap.dcMotor.get(ARM.TURRET_MOTOR_ID);
        armMotor = hardwareMap.dcMotor.get(ARM.ROTATE_MOTOR_ID);
        clawMotor = hardwareMap.dcMotor.get(ARM.CLAW_MOTOR_ID);
=======
        turretMotor = (DcMotorEx) hardwareMap.dcMotor.get(ARM.TURRET_MOTOR_ID);
        armMotor = (DcMotorEx) hardwareMap.dcMotor.get(ARM.ROTATE_MOTOR_ID);
        clawMotor = (DcMotorEx) hardwareMap.dcMotor.get(ARM.CLAW_MOTOR_ID);
>>>>>>> AutoUpgrade

        turretMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        clawMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        turretMotor.setPower(0);
        armMotor.setPower(0);
        clawMotor.setPower(0);
<<<<<<< HEAD
=======

        turretMotor.setTargetPositionTolerance((int)(Math.PI/90 * ARM.CORE_HEX_TICKS_TO_RADIAN));
>>>>>>> AutoUpgrade
    }

    public void setTurretMotor(double turretSpeed) {
        turretMotor.setPower(turretSpeed);
    }

    public void setArmMotor(double ArmSpeed){
        armMotor.setPower(ArmSpeed);
    }

<<<<<<< HEAD
    public void setClawMotor(double clawSpeed){
        clawMotor.setPower(clawSpeed);
=======
    public void setClawMotorWithdistance(double clawDistance,double clawSpeed){

        clawMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        clawMotor.setTargetPosition((int)(clawDistance));
        clawMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        clawMotor.setPower(clawSpeed);
        while (clawMotor.isBusy());
    }
    public void setClawMotor(double clawSpeed){

            clawMotor.setPower(clawSpeed);
        }

    public void TurretTurnAngle(double angle, double speed){
        turretMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turretMotor.setTargetPosition((int)(angle*ARM.CORE_HEX_TICKS_TO_RADIAN));
        turretMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        turretMotor.setPower(speed);


    }

    public void ArmMoving(double angle, double speed){
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setTargetPosition((int)(angle*ARM.CORE_HEX_TICKS_TO_RADIAN));
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setPower(speed);
>>>>>>> AutoUpgrade
    }

    public int getArmPosition(){
        telemetry.addData("Motor Position:", armMotor.getCurrentPosition()* ARM.TICKS_TO_DEGREE);
        return armMotor.getCurrentPosition();
    }
<<<<<<< HEAD
=======
    public boolean isTurretBusy(){
        return  turretMotor.isBusy();}

    public boolean isArmBusy(){
        return armMotor.isBusy();}
>>>>>>> AutoUpgrade

}

