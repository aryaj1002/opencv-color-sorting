package org.firstinspires.ftc.teamcode.OpenCv;

import android.util.Log;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.Arrays;

public class ColorDetectionBlockPipeline extends OpenCvPipeline {

    public int random = 1;

    public double blueAvg;

    public double greenAvg;

    public double redAvg;

    @Override
    public Mat processFrame(Mat input) {
        Rect detectionFrame = new Rect(new Point(132, 75), new Point(165, 115));

        Imgproc.rectangle(input, detectionFrame, new Scalar(255, 0, 0), 3);

        Mat sub = input.submat(detectionFrame);

        Mat red = sub.clone();

        Mat blue = sub.clone();

        Mat green = sub.clone();

        Core.extractChannel(sub, red, 0);

        Core.extractChannel(sub, green, 1);

        Core.extractChannel(sub, blue, 2);

        redAvg = Core.mean(red).val[0];

        greenAvg = Core.mean(green).val[0];

        blueAvg = Core.mean(blue).val[0];

        if (blueAvg > greenAvg && blueAvg > redAvg) {
            random = 3;
        } else if (greenAvg > blueAvg && greenAvg > redAvg) {
            random = 2;
        } else if (redAvg > greenAvg && redAvg > blueAvg) {
            random = 1;
        }

        return input;
    }

    public int getRandom() {
        return random;
    }

    public String toStringVals() {
        return (redAvg + ", " + greenAvg + ", " + blueAvg);
    }

    public int avg1() {
        return 0;
    }
}
