//See the Class SleeveDetector (which now contiains this class)

package org.firstinspires.ftc.teamcode;

/**
 * This is an example of vision using EasyOpenCv.This example will show
 * how to convert color spaces,draw rectangles, and extract the value
 * inside the rectangle.
 * For full docs: https://opencv-java-tutorials.readthedocs.io/
 * For the library: https://github.com/OpenFTC/EasyOpenCV
 */

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvPipeline;

/**
 * This is the pipeline for our vision system,a pipeline
 * is a distinct steps to process and analyze the input(camera frames)
 */

public class PowerPlayPipeline() extends OpenCvPipeline {
    boolean viewportPaused;

    // These are the mats we need, I will be explaining them as we go
    private Mat matYCrCb = new Mat();
    private Mat matCr_center = new Mat();
    private Mat matCb_center = new Mat();
    private Mat center_block = new Mat();

    //These will store the Cb values
    public double center_Cb;
    public double center_Cr;

    //These will be the points for our rectangle
    int[] center_rect = {
            1,
            2,
            3,
            4
    };

    /**
     * This will create the rectangles
     *
     * @param frame     the input mat
     * @param points    the points for the rectangle
     * @param color     the color of the rectangle when it is displayed on screen
     * @param thickness the thickness of the rectangle
     */
    public Mat drawRectangle(Mat frame, int[] points, Scalar color, int thickness) {

        Imgproc.rectangle(
                frame,
                new Point(
                        points[0],
                        points[1]),

                new Point(
                        points[2],
                        points[3]),
                new Scalar(57,255,20), thickness);

        //submat simply put is cropping the mat
        return frame.submat(points[1], points[3], points[0], points[2]);

    }

    @Override
    public Mat processFrame(Mat input) {
        /**
         *input which is in RGB is the frame the camera gives
         *We convert the input frame to the color space matYCrCb
         *Then we store this converted color space in the mat matYCrCb
         *For all the color spaces go to
         *https://docs.opencv.org/3.4/d8/d01/group__imgproc__color__conversions.html
         */
        Imgproc.cvtColor(input, matYCrCb, Imgproc.COLOR_RGB2YCrCb);

        center_block = drawRectangle(matYCrCb, center_rect, new Scalar(0, 255, 0), 1);

        /**
         *This will extract the value of the CB channel in both rectangles
         *0 is the Y channel, 1 is the Cr, 2 is Cb
         */
        Core.extractChannel(center_block, matCb_center, 2); //IMPORTANT NUMBER HERE for color
        Core.extractChannel(center_block, matCr_center, 1);

        /**
         *We now average value and extract it
         * so now left is the Cb value of the left rectangle and
         * right is the Cb value of the right rectangle
         */
        Scalar center_Cb_mean = Core.mean(matCb_center);
        Scalar center_Cr_mean = Core.mean(matCr_center);
        center_Cb = center_Cb_mean.val[0];
        center_Cr = center_Cr_mean.val[0];

        return input;
    }

    @Override
    /**
     * From this:
     * https://github.com/OpenFTC/EasyOpenCV/blob/master/examples/src/main/java/org/firstinspires/ftc/teamcode/WebcamExample.java
     *
     */
    public void onViewportTapped()
    {
        /*
         * The viewport (if one was specified in the constructor) can also be dynamically "paused"
         * and "resumed". The primary use case of this is to reduce CPU, memory, and power load
         * when you need your vision pipeline running, but do not require a live preview on the
         * robot controller screen. For instance, this could be useful if you wish to see the live
         * camera preview as you are initializing your robot, but you no longer require the live
         * preview after you have finished your initialization process; pausing the viewport does
         * not stop running your pipeline.
         *
         * Here we demonstrate dynamically pausing/resuming the viewport when the user taps it
         */

        viewportPaused = !viewportPaused;

        if(viewportPaused)
        {
            webcam.pauseViewport();
        }
        else
        {
            webcam.resumeViewport();
        }
    }


}
