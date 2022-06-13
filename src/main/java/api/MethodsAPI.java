package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import util.UserJsonHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@Path("methods")
public class MethodsAPI {

    private static UserJsonHandler userJsonHandler = new UserJsonHandler();


    // using methods from "UserJsonhandler" to handle data before sending with api
    // Also configuring cors filter for the api endpoint


    @GET
    @Produces("application/json")
    @Path("users")
    public static Response allUsers() throws IOException {
        System.out.println("users reached!");
        final File file = new File("src/main/res/users.json");
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity(file)
                .build();
    }

    @GET
    @Produces("application/json")
    @Path("userswithreversednames")
    public static Response usersWithReversedNames() throws IOException {
        // behöver göras om till json
        List<User> usersArrayReverse = userJsonHandler.getUsersWithReversedNames();
        String usersArrayReverseJson = userJsonHandler.listToJsonArray(usersArrayReverse);
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .entity(usersArrayReverseJson)
                .build();
    }

    @POST
    @Path("reversestring")
    public static Response reverseString(@FormParam("string") String string) {
        return Response.ok(
                //response
        ).build();
    }

    @POST
    @Path("ispalindrome")
    public static Response isPalindrome(@FormParam("string") String string) {
        return Response.ok(
                //response
        ).build();
    }

    @POST
    @Path("padnumberwithzeroes")
    public static Response padNumberWithZeroes(@FormParam("number") int number) {
        return Response.ok(
                //response
        ).build();
    }

    @POST
    @Path("findnthlargestnumber")
    public static Response findNthLargestNumber(@FormParam("numbers") List<Integer> numbers,
                                                @FormParam("nthlargestnumber") int nthLargestNumber) {
        return Response.ok(
                //response
        ).build();
    }

}
