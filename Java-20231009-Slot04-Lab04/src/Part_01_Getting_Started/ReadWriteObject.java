package Part_01_Getting_Started;

import java.io.*;
import java.util.Date;

public class ReadWriteObject {
    public static void main(String[] args) {
        Date currentDate = new Date();
//write object
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream("object.dat")));
// write a String object
            oos.writeObject("The current Date and Time is ");
            oos.writeObject(new Date());
            System.out.println("Object writed!");
            oos.flush();
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//read object
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.dat"));
            Date d = (Date) ois.readObject();
            System.out.println("The data read from file: " + d);
            ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
