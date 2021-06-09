/*Course: Higher Diploma in Computing (Full-time)
Module: Software Development + Testing
Teacher: Clare Doherty
Task: Assignment 2
Author: Yohane Gamiz Ancel Neumann
Covid class to record daily covid case numbers*/

//Serializable interface belongs to the java.io package and it must be imported
import java.io.Serializable;

//Objects that are written to an object stream must belong to a class that implements the Serializable interface.
public class Covid implements Serializable//In order to serialize an object the Covid class must be serializable
{
   //Declares properties/instance variables
   private String countyName;
   private int noCases;
   private int noMales;
   private int noFemales;
   private int medianAge;
   
   //Constructors
   //Constructor 1 - User defined default
   public Covid()
   {
      countyName = " ";
      noCases = 0;
      noMales = 0;
      noFemales = 0;
      medianAge = 0;
   }
   
   //Constructor 2 - Parameter defined constructor
   public Covid(String county, int cases, int males, int females, int age)//Constructor overloading
   {
      countyName = county;
      noCases = cases;
      noMales = males;
      noFemales = females;
      medianAge = age;
   }
   
   //Methods
   //Sets methods (Mutator methods)
   public void setCountyName(String county)
   {
      countyName = county;
   }
   public void setNoCases(int cases)
   {
      noCases = cases;
   }
   public void setNoMales(int males)
   {
      noMales = males;
   }
   public void setNoFemales(int females)
   {
      noFemales = females;
   }
   public void setAge(int age)
   {
      medianAge = age;
   }
   //Accessor methods
   public String getCountyName()//County name
   {
      return countyName;
   }
   public int getNoCases()//Total number of cases
   {
      return noCases;
   }
   public int getNoMales()//Number of male cases
   {
      return noMales;
   }
   public int getNoFemales()//Number of female cases
   {
      return noFemales;
   }
   public int getAge()//Median age of a county
   {
      return medianAge;
   }
   public String toString()//It will display all the data on file when required
   {
      return "\nCounty name: "+countyName+ "\t\tTotal number of cases: " +noCases+ "\t\tNumber of male cases: "+noMales+ "\t\tNumber of female cases: "+noFemales+ "\t\tMeadian age: "+medianAge;
   }
}//Closes class