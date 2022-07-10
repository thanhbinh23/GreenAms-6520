package org.firstinspires.ftc.teamcode.subsystems;



import com.qualcomm.robotcore.eventloop.opmode.OpMode;
		import com.qualcomm.robotcore.hardware.DcMotor;
		import com.qualcomm.robotcore.hardware.HardwareMap;
		import org.firstinspires.ftc.teamcode.Constants;


public class Intake {
	public DcMotor intakeMotor;
	public HardwareMap hardwareMap;
	public Intake (OpMode opmode){
		hardwareMap = opmode.hardwareMap;
	}
	public void initIntakeMotor(){
		intakeMotor = hardwareMap.dcMotor.get("IntakeMotor");
	}

	public void drive(){
		intakeMotor.setPower(Constants.MOTOR.SPEEDINTAKE);
	}

}