/*Course: Higher Diploma in Computing (Full-time)
Module: Software Development + Testing
Teacher: Clare Doherty
Task: Assignment 2
Author: Yohane Gamiz Ancel Neumann
Student Number:L00157217
This code will allow a user to work with the data from the CovidArrayListFile program (Covid.txt) as follows on a menu-based system.*/

//Imports packages containing predefined classes
import java.util.*;
import java.io.*;

//Program's name
public class ReadCovidMenu
{
   public static void main(String[]args)
   {
      //Allows to define a block of code to be tested for errors while it is being executed
      try{
      //Creates instance of Scanner class to get values from the keyboard entered by the user
      Scanner keyboardIn = new Scanner(System.in);
      //Creates objects to open file and read from it
      FileInputStream fis = new FileInputStream("Covid.txt");
      ObjectInputStream os = new ObjectInputStream(fis);
      
      //Brings data back in from file and store it in ArrayList
      ArrayList<Covid> covidList = (ArrayList<Covid>) os.readObject();
      //Declaring instance variables
      String countyName;
      int noCases, noMales, noFemales, medianAge, option;
      //Creates a loop to display the menu-based system while the user does not enter 0
      do
      {
         //Displays Menu
         System.out.println("\n\t\t Covid Processing System ");
         System.out.println("\n1. View all data");
         System.out.println("2. View data by county");
         System.out.println("3. Find county with highest number of cases");
         System.out.println("4. Calculate average age");
         System.out.println("5. Find % number of males and % number of females by county");
         System.out.println("6. Write all details of the county with the lowest number of cases to new file");
         System.out.println("0. Exit System");
         System.out.print("\nPlease, choose an option: ");
         option = keyboardIn.nextInt();
         //It holds  a number of possible execution paths
         switch(option)
         {
            case 1:
            //This method shows all the data for all the 5 counties
            viewAllData(covidList);
            break;
            case 2:
            keyboardIn.nextLine();
            //Allows the user to enter a county name
            System.out.print("Enter the name of the county that you would like to check: ");
            countyName = keyboardIn.nextLine();
            //Searches and displays the details of the county entered by the user
            searchCounty(covidList, countyName);          
            break;
            case 3:
            //Finds and displays the county with the highest number of cases
            highestCases(covidList);
            break;
            case 4:
            //Calculates and displays the average age of all counties
            averageAge(covidList);
            break;
            case 5:
            keyboardIn.nextLine();
            //Allows the user to enter a county name
            System.out.print("Enter the name of the county that you would like to check: ");
            countyName = keyboardIn.nextLine();
            //Calculates and displays the percentage number of males and percentage number of females cases of the county chosen by the user
            calculatePercentage(covidList, countyName);
            break;
            case 6:
            //Finds and displays the county with the lowest number of cases
            lowestCases(covidList);
            break;
            case 0:
            //Exits the system
            System.out.println("\nExited the system.");//Tells the user they exited the system
            System.exit(0);
            default:
            //Error message when the user types an invalid number
            System.out.println("\nInvalid option. \nPlease choose an option from 1-6 or enter 0 to exit the system.");
         }
      }while(option != 0);//The user exits the system if it enters number 0
      }//Closes try block
      //Allows to define a block of code to be executed, if an error occurs in the try block
      catch(InputMismatchException ex)//Check whether the user entered a numeric value as an option and displays an error message
      {
         System.out.println("To choose an option, please enter only numeric values.");
      }
      catch(FileNotFoundException ex)//Sets a error message for FileNotFoundException and makes the program  more usable for the user
      {
         System.out.println("Problem reading from the file Covid.txt");
      }
      catch(IOException ex)//Sets a error message for IOException and makes the program  more usable for the user
      {
         System.out.println("Problem reading from the file Covid.txt");
      }
      catch(ClassNotFoundException ex)//Sets a error message for ClassNotFoundException and makes the program  more usable for the user
      {
         System.out.println("Problem reading from the file Covid.txt");
      }
      
  }//Closes main method 
  
  //Methods
  //This method shows all the data for all the 5 counties in file
  public static void viewAllData(ArrayList<Covid> covidList) 
  {
    for(Covid cov: covidList)//For each loop
    {
       System.out.println(cov);
    }
  }//Closes viewAllData
      
  //This method will display all details for the county chosen by the user
  public static void searchCounty(ArrayList<Covid> covidList, String countyName)
  {
     boolean covidFound = false;//Boolean flag
     for(int i = 0; i < covidList.size(); i++)
     {
        if(countyName.equalsIgnoreCase(covidList.get(i).getCountyName()))//.equals is used to match String values and Ignore Case will considerer capital letters and lower cases
        {
           System.out.println(covidList.get(i));
           covidFound = true;
        }
     }
     if(covidFound == false)
     {
        //Error message when the county name entered by the user does not exist on file
        System.out.println("\nNo results found on file for the county you are looking for. Please try again.");
     }
  }//Closes searchCounty
      
  //This method finds and displays the county with the highest number of cases
  public static void highestCases(ArrayList<Covid> covidList)
  {
     //Sets variable highestNoCases to number of cases held in 1st element of the ArrayList
     int highestNoCases = covidList.get(0).getNoCases();
     String county = " ";
     for(int i = 0; i < covidList.size(); i++)
     {
        if(covidList.get(i).getNoCases() > highestNoCases ) //Checks if any other element of ArrayList has higher number of cases than the 1st element
        {
           highestNoCases = covidList.get(i).getNoCases();//Overwrites the variable if higher number of cases is found
           county = covidList.get(i).getCountyName();
        }
     }
     System.out.println("\nThe county with the highest number of cases is " +county+ " with "+highestNoCases+ " cases.");
  }//Closes highestCases
    
  //Using the median age for each county, this method calculates and displays the average age of all counties
  public static void averageAge(ArrayList<Covid> covidList)
  {
     double average = 0;
     int sum = 0;
    
     for (int i=0; i < covidList.size() ; i++) 
     {
        sum = sum + covidList.get(i).getAge();
        average = (sum / covidList.size());
     }
     System.out.println("\nThe average age is " +average+ " years old.");
  }//Closes averageAge
     
  //This method calculates and displays the percentage number of males and percentage number of females of the county chosen by the user 
  public static void calculatePercentage(ArrayList<Covid> covidList, String countyName)
  {
     String county = " ";
     int noCases = 0;
     int noMales = 0;
     int noFemales = 0;
     double percentageMales = 0;
     double percentageFemales = 0;
     
     boolean covidFound = false;//Boolean flag
     for(int i = 0; i < covidList.size(); i++)
     {
        if(countyName.equalsIgnoreCase(covidList.get(i).getCountyName()))//.equals is used to match String values and Ignore Case will considerer capital letters and lower cases
        {
           county = covidList.get(i).getCountyName();
           noCases = covidList.get(i).getNoCases();
           noMales = covidList.get(i).getNoMales();
           noFemales = covidList.get(i).getNoFemales();
                                    
           percentageMales = (double)noMales/noCases * 100;
           percentageFemales = (double)noFemales/noCases * 100;
           covidFound = true;
        }
     }
     System.out.println("\nCounty " +county+ " has a total of "  +noCases+ " cases, which are "+percentageMales+ "% male and " +percentageFemales+ "% female cases.");
     if(covidFound == false)
     {
        //It provides an error message if the county entered by the user is not found on file.
        System.out.println("\nNo results found on file for the county you are looking for. Please try again.");
     }
  }//Closes calculatePercentage
   
  //This method finds and displays the lowest number of cases and write it to a file called LowestCases.txt
  public static void lowestCases(ArrayList<Covid> covidList) throws IOException //Allows the data to be written to a file called LowestCases.txt
  {
      //Sets variable lowestNoCases to number of cases held in 1st element of the ArrayList
      int lowestNoCases = covidList.get(0).getNoCases();
      String county = " ";
      for(int i = 0; i < covidList.size(); i++)
      {
         if(covidList.get(i).getNoCases() < lowestNoCases ) //Check if any other element of ArrayList has lower number of cases than the 1st element
         {
            lowestNoCases = covidList.get(i).getNoCases();//Overwrite the variable if lower number of cases is found
            county = covidList.get(i).getCountyName();
            //Creates objects of the classes
            FileWriter fw = new FileWriter("LowestCases.txt");
            PrintWriter pw = new PrintWriter(fw);         
            //Writes data out to a new text file
            pw.print(covidList.get(i));
            //Closes file
            fw.close();
         }
      }
      System.out.println("\nThe county with the lowest number of cases is " +county+ " with "+lowestNoCases+ " cases.\nAll details of county " +county+ " were written out to a new file called LowestCases.txt");
  }//Closes lowestCases

}//Closes class    
