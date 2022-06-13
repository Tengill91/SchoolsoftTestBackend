package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import sun.plugin.javascript.navig.Array;

import java.io.*;
import java.util.*;

public class UserJsonHandler {

    private final ClassLoader classLoader = this.getClass().getClassLoader();

    public String getUsers() {

        try{
            final File file = new File("src/main/res/users.json");
            final StringBuilder stringBuilder = new StringBuilder();
            final BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            System.out.println("stringbuilder: " + stringBuilder);
            return stringBuilder.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    // turns the users json string in to arraylList
    public List<User> getUsersToArray() throws IOException {
        String jsonString = getUsers();
        final ObjectMapper objectMapper = new ObjectMapper();
        List<User> userList = objectMapper.readValue(jsonString, new TypeReference<ArrayList<User>>(){});
        userList.forEach(user -> System.out.println(user));

        return userList;

    }

    // reversing firstname of arraylist with users
    public List<User> getUsersWithReversedNames() throws IOException {
        List<User> userList = getUsersToArray();
        StringBuilder stringBuilder = new StringBuilder();

        for (User user: userList) {
            String firstname = user.getFirstname();
            stringBuilder.append(user.getFirstname());
            stringBuilder = stringBuilder.reverse();
            String firstnameReverse = stringBuilder.toString();
            if (firstname.equalsIgnoreCase(firstnameReverse)){
                user.setFirstname(stringBuilder + " (Palindrome Ohoy!)");
            }else{
                user.setFirstname(stringBuilder.toString());
            }
            stringBuilder.setLength(0);
        }

        userList.forEach(user -> System.out.println(user));

        return userList;
    }

    // turning arraylist back into json
    public String listToJsonArray(List<User>list) throws IOException {
        final StringWriter sw = new StringWriter();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(sw, list);
        System.out.println(sw.toString());// toString() to convert to JSON

        sw.close();

        return sw.toString();
    }



}

