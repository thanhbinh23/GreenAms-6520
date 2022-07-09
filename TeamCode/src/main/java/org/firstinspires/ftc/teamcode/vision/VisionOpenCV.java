package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

public class VisionOpenCV {
    private int CameraViewID;
    private OpenCvCamera webcam;

    public VisionOpenCV(OpMode opmode) {
        CameraViewID = opmode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewID", "id", opmode.hardwareMap.appContext.getPackageName());
        WebcamName webcamName = opmode.hardwareMap.get(WebcamName.class, "Webcam 1");
        webcam = OpenCvCameraFactory.getInstance().createWebcam(webcamName);
    }

    public void pauseViewport() { webcam.pauseViewport();}

    public void resumeViewport() { webcam.resumeViewport();}

    public void attachPipeline(OpenCvPipeline pipeline) { webcam.setPipeline(pipeline);}

    public void streamWebcam() {
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
    }
}
