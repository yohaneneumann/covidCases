/*Course: Higher Diploma in Computing (Full-time)
Module: Software Development + Testing
Teacher: Clare Doherty
Task: Assignment 2
Author: Yohane Gamiz Ancel Neumann
Student Number:L00157217
This program will allow a user to enter the details for the 5 highest counties and write them to file called Covid.txt.*/

//Imports packages containing predefined classes
import java.util.*;
import java.io.*;

//Program's name
public class CovidArrayListFile
{
   public static void main(String []args)
   {
      
      //Creates ArrayList for Covid objects
      ArrayList<Covid> covidList = new ArrayList<>();
      //Allows to define a block of code to be tested for errors while it is being executed
      try{
      //Creates objects to open file and write to it
      FileOutputStream fos = new FileOutputStream("Covid.txt");
      ObjectOutputStream os = new ObjectOutputStream(fos);
      //Creates instance of Scanner class to get values from the keyboard entered by the user
      Scanner keyboardIn = new Scanner(System.in);
      //Declaring instance variables
      String countyName;
      int noCases, noMales, noFemales, medianAge, option;
      
      //The for loop limits the user to enter details for a maximum of 5 counties (counties number 1-5)
      for (int i = 1; i < 6; i++) 
      {
         System.out.print("Enter county number "+(i)+ " name: ");//Asks for the name of the county according to its number (1-5)
         countyName = keyboardIn.nextLine();
                         
         System.out.print("Enter total number of cases: ");//Gets the total number of cases in county
         noCases = keyboardIn.nextInt();
         while(noCases < 0)//Creates a while loop until the user enters a valid number
         {
            System.out.println("Please, enter a positive number: ");//Error message when the user types a negative number
            noCases = keyboardIn.nextInt();
         }
         
         System.out.print("Enter number of male cases: ");//Gets the number of male cases in a county
         noMales = keyboardIn.nextInt();
         while(noMales < 0 || noMales > noCases)//Creates a while loop until the user enters a valid number
         {
            System.out.println("Please, make sure that:\n- You are entering a positive number;\n- The number of male cases is not exceeding the number of total cases.");//Error message when the user types a negative number
            System.out.print("Enter number of male cases: ");
            noMales = keyboardIn.nextInt();
         }
         
         System.out.print("Enter number of female cases: ");//Gets the number of female cases in a county
         noFemales = keyboardIn.nextInt();
         while(noFemales < 0 || noFemales > noCases || noMales + noFemales > noCases || noMales + noFemales < noCases)//Creates a while loop until the user enters a valid number
         {
            //Error message warning the user to check the value entered
            System.out.println("Please, make sure that:\n- You are entering a positive number;\n- The number of female cases is not exceeding the number of total cases;\n- Male cases + female cases = total number of cases.");
            System.out.print("\nEnter number of female cases: ");
            noFemales = keyboardIn.nextInt();
         }
                    
         System.out.print("Enter the median age: ");//Gets the meadian age of a county
         medianAge = keyboardIn.nextInt();
         while(medianAge < 0)
            {
               System.out.println("Please, enter a positive number: ");//Error message when the user types a negative number
               medianAge = keyboardIn.nextInt();
            }
         keyboardIn.nextLine();
               
         //Adds Covid object to ArrayList
         covidList.add(new Covid(countyName, noCases, noMales, noFemales, medianAge));
         
      }//Closes for loop      
      
      //Writes ArrayList out to file
      os.writeObject(covidList);
     
      //Closes file and output stream
      fos.close();
      os.close();
      System.out.println("\nFile successfully created.");//Sets a message when the user finishes writting all the data and the file is created

      }//Closes try block
      
      //Allows to define a block of code to be executed, if an error occurs in the try block
      catch(InputMismatchException ex)//Check if the user entered numeric values when it should
      {
         System.out.println("Number of male and females cases and median age must be numeric values - Please check your input.");
      }
      catch(FileNotFoundException ex)//Sets a error message for FileNotFoundException and makes the program more usable
      {
         System.out.println("Problem writing to the file Covid.txt");
      }
      catch(IOException ex)//Sets a error message for IOException makes the program more usable
      {
         System.out.println("Problem writing to the file Covid.txt");
      }
      
   }//Closes main method
   
}//Closes class