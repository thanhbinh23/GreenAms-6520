package org.firstinspires.ftc.teamcode;

public class Constants {
    public static final class MOTOR {
        public static final String RIGHTMASTER = "rm";
        public static final String RIGHTFOLLOW = "rf";
        public static final String LEFTMASTER = "lm";
        public static final String LEFTFOLLOW = "lf";

        public static final double d = 0.33;
    }
    public static final class AUTONOMOUS{
        public static final double wheelDiameter = 0.09;
        public static final double ticksPerMeter = 336/(Math.PI* wheelDiameter);

    }
}