package lambdaapp;
// This class contains the functions to support this application
// The handleRequest method will instantiate this class and call
//     its methods as needed
import appdata.RequestData;
import appdata.ResponseData;

public class AppFunctions {

    // return a ResponseData object and Received a RequestData object
    //    RequestData is class this application defined to receive data when called
    //    ResponseData is a class this application defined to return data when done
    public ResponseData appMain(RequestData aRequest) {
        // Display message delimiting line
        System.out.println("-".repeat(100));

        // Display greeting using data from the RequestData object passed into the method
        System.out.println("Hello there " + aRequest.getFirstName() + " " + aRequest.getLastName());
        System.out.println("Welcome to the BD Unit_3 Preparedness Task AWS Lambda App");
        System.out.println("I see were born on " + aRequest.getDob());

        // Display message delimiting line
        System.out.println("-".repeat(100));

        // Instantiate a ResponseData object to br returned from this method
        ResponseData theResponse = new ResponseData(aRequest.getFirstName() + " " + aRequest.getLastName()
                                                  , aRequest.getDob());

        // Return the ResponseData object
        return theResponse;
    }
}
