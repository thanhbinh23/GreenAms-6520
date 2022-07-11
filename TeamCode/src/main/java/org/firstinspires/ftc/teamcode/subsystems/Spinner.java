package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants.SPINNER;



public class Spinner {
    private Telemetry telemetry;
    private HardwareMap hardwareMap;
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor spinnerMotor;

    public Spinner(OpMode opMode){
        this.telemetry = opMode.telemetry;
        this.hardwareMap = opMode.hardwareMap;
    }

    public void init(){
        spinnerMotor = hardwareMap.dcMotor.get(SPINNER.SPINNER_ID);
    }

    public void spin(double speed){
        spinnerMotor.setPower(speed);
        telemetry.addData("Spinner Speed:", speed);
    }

    public void spinAuto( double time, double speed){
        runtime.reset();
        while(runtime.seconds() <= time){
            spinnerMotor.setPower(speed);
        }

    }
}