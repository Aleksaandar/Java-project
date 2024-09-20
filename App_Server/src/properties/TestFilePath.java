/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package properties;

/**
 *
 * @author Isidora
 */
import java.io.File;

public class TestFilePath {
    public static void main(String[] args) {
        String propertiesFilePath = "C:\\OktobarIspit\\App_Serverproperties\\database_properties.properties";
        File file = new File(propertiesFilePath);

        if (file.exists()) {
            System.out.println("File exists: " + propertiesFilePath);
        } else {
            System.out.println("File does not exist: " + propertiesFilePath);
        }
    }
}

