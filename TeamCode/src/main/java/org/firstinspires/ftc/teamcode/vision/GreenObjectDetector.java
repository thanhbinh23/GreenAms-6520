package org.firstinspires.ftc.teamcode.vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class GreenObjectDetector extends OpenCvPipeline {
    // HSV value: [[40, 60], [172, 255], [0, 157]]
    Mat mask = new Mat();
    Mat blurredImage = new Mat();
    Mat hsvImage = new Mat();
    Mat morphOutput = new Mat();
    Mat hierarchy = new Mat();
    List<MatOfPoint> contours = new ArrayList<>();

    @Override
    public Mat processFrame(Mat input) {
        // Reduce the noise
        Imgproc.blur(input, blurredImage, new Size(7, 7));

        // Convert to hsv
        Imgproc.cvtColor(blurredImage, hsvImage, Imgproc.COLOR_RGB2HSV);

        // HSV range
        Scalar hsvLow = new Scalar(40, 172, 0);
        Scalar hsvHigh = new Scalar(60, 255, 157);

        Core.inRange(hsvImage, hsvLow, hsvHigh, mask);

        Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(24, 24));
        Mat erosionElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(12,12));

        Imgproc.erode(mask, morphOutput, erosionElement);
        Imgproc.erode(mask, morphOutput, erosionElement);

        Imgproc.dilate(mask, morphOutput, dilateElement);
        Imgproc.dilate(mask, morphOutput, dilateElement);

        Imgproc.findContours(mask, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);

        int maxContour = 0;
        for (int i = 1; i<contours.size(); i++){
            if (contours.get(i).size().area() > contours.get(maxContour).size().area()){
                maxContour = i;
            }
        }
        Imgproc.drawContours(input,contours, maxContour, new Scalar(250, 0, 0));

                //Imgproc.rectangle(mask,)

        return morphOutput;
    }
}
