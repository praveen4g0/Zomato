package qubeCenimas.sharedBox_API.Utilities.ExtentReports;


import com.relevantcodes.extentreports.ExtentReports;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.

public class ExtentManager {

    private static ExtentReports extent;
    private static String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
    public synchronized static ExtentReports getReporter(){
        if(extent == null){
            //Set HTML reporting file location
            String workingDir = System.getProperty("user.dir");
            extent = new ExtentReports(workingDir+"\\ExtentReports\\"+"ExtentReportResults_"+timeStamp+".html", true);
        }
        return extent;
    }
}