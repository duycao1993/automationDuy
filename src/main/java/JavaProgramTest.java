
import java.io.*;
import java.util.Scanner;

public class JavaProgramTest {
    public static void main(String args[]){
//        Scanner sc = new Scanner(System.in);
//        Integer inputNumber;
//
//        System.out.println("Please fill your input:");
//        inputNumber = sc.nextInt();
//
//        System.out.println("Your number is: " + inputNumber);
        StringBuilder doc = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("F:/test.txt")));
            doc = new StringBuilder();
            String line = br.readLine();
            while(line != null){
                doc.append(line).append(System.lineSeparator());
                line = br.readLine();
            }
            System.out.println(doc);
            br.close();
        } catch (Exception e){
            System.out.println(e);
        }
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("F:/test1.txt")));
            bw.append(doc);
            bw.close();
        } catch (Exception e){
            System.out.println(e);
        }


    }
}
