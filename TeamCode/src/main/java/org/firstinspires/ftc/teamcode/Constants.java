package org.firstinspires.ftc.teamcode;

public class Constants {
    public static final class MOTOR {
        public static final String RIGHTMASTER = "rm";
        public static final String RIGHTFOLLOW = "rf";
        public static final String LEFTMASTER = "lm";
        public static final String LEFTFOLLOW = "lf";
        public static final double d = 0.28;
    }
    
    public static final class ARM {
        public static final String TURRET_MOTOR_ID = "Turret";
        public static final String ROTATE_MOTOR_ID = "Rotate";
        public static final String CLAW_MOTOR_ID = "Claw";

        public static final double TURRET_MOVE_LEFT = -0.5;
        public static final double TURRET_MOVE_RIGHT = 0.5;

        public static final double ARM_MOVE_UP = -1;
        public static final double ARM_MOVE_DOWN = 0;

        public static final double CLAW_OPEN = 0.5;
        public static final double CLAW_CLOSE = -0.5;

        public static final double TICKS_TO_DEGREE = 864.0/364;
    }
}
