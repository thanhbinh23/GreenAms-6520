package org.firstinspires.ftc.teamcode.subsystems;

        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.hardware.DcMotorEx;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;
        import com.qualcomm.robotcore.hardware.HardwareMap;

        import static org.firstinspires.ftc.teamcode.Constants.*;
        import static org.firstinspires.ftc.teamcode.Constants.AUTONOMOUS.*;

        import org.firstinspires.ftc.robotcore.external.Telemetry;
        import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class DriveBase {
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
        leftMaster = (DcMotorEx) hardwareMap.dcMotor.get( "lm");
        leftFollow = (DcMotorEx) hardwareMap.dcMotor.get( "lf");

        rightMaster.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFollow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMaster.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFollow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

       leftFollow.setDirection(DcMotorSimple.Direction.REVERSE);
       leftMaster.setDirection(DcMotorSimple.Direction.REVERSE);

        rightMaster.setTargetPositionTolerance((int) (0.025*ticksPerMeter));
        leftFollow.setTargetPositionTolerance((int) (0.025*ticksPerMeter));
        rightFollow.setTargetPositionTolerance((int) (0.025*ticksPerMeter));
        leftMaster.setTargetPositionTolerance((int) (0.025*ticksPerMeter));
    }

    public void driveAll(double rightMasterSpeed, double rightFollowerSpeed, double leftMasterSpeed, double leftFollowerSpeed) {
        rightMaster.setPower(rightMasterSpeed);
        rightFollow.setPower(rightFollowerSpeed);
        leftMaster.setPower(leftMasterSpeed);
        leftFollow.setPower(leftFollowerSpeed);
    }
   public void drive(double left, double right, boolean boost) {
       if(boost){
           driveAll(right, right, left, left);
           telemetry.addData("Right - Left: ", String.valueOf(right), String.valueOf(left));
       } else {
           driveAll(right*0.8, right*0.8, left*0.8, left*0.8);
           telemetry.addData("Right - Left: ", String.valueOf(right), String.valueOf(left));
       }
   }

    private double mappingDeadZone(double input){
       double absoluteValue = Math.abs(input);
       double sign = Math.signum(input);
       double max = Math.max(0, absoluteValue-0.1);
       return max*1.1111111111*sign;
    }

    //the amount of power to each motor when joystick is fully to one side
    public static final double turnGoal = 0.4;
    public static final double forwardGoal = 0.6;
    public void driveArcade(double forwardPower, double turnPower){
       double tp = mappingDeadZone(turnPower);
       double fp = mappingDeadZone(forwardPower);

       double turnPart = turnPower*turnGoal;
       double forwardPart = forwardPower*forwardGoal;
       double L = forwardPart - turnPart;
       double R = forwardPart + turnPart;                     
       drive(L,(R));

    }

}
