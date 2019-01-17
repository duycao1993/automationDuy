import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class JavaProgramTest {
    public static void main(String args[]){
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("Put your lenght;");
                int arrayLenght = sc.nextInt();

                //System.out.println("Put your element");
                String[] arrayTest = new String[arrayLenght];
                for(int i = 0; i < arrayLenght; i++){
                    System.out.println("Put your element");
                    arrayTest[i] = sc.nextLine();
                }

                System.out.println(Arrays.toString(arrayTest));
            } catch (Exception e){
                System.out.println(e);
            }
//
//
////            for(int firstIndex : firstWordIndex){
////                for(int secondIndex : secondWordIndex){
////                    if(min > Math.abs(firstIndex-secondIndex)){
////                        min = Math.abs(firstIndex-secondIndex);
////                    }
////                }
////            }
//
//           // System.out.println("The closest distant between 2 words \"" + firstWord + "\" and \"" + secondWord + "\" is " + min);
//
//        } catch (Exception e){
//            System.out.println(e);
//        }
    }
}
