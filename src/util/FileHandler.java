package util;

import Model.*;
import parser.ParserException;
import parser.XmlJsonParser;

import java.io.*;

public class FileHandler

{

  // En metode der gemmer vores projektliste til en XML, som kan loades ind på hjemmesiden.
  public static void saveXML(ProjectList projects)
  {

    //TODO

    XmlJsonParser theParser = new XmlJsonParser();
    File XMLfile;
    try
    {
      XMLfile = theParser.toXml(projects.getProjects(), "projects.xml");
    }
    catch (ParserException e)
    {
      System.out.println("XML parser error");
      e.printStackTrace();
    }
  }

  // En metode der kan oprette en ny fil og gemme data på nye projekter.
  // denne her laver mapper
  /*public static void newSave(Object obj)
  {
    String filename;
    if (obj instanceof Project)
    {
      Project project = (Project) obj;
      //Vi laver et nyt directory for hvert projekt, hvis dir ikke alle allerede eksisterer
      String dirPath = "SavedProjects/";
      String dirName = dirPath.concat((project).getTitle());
      filename = project.getTitle() + ".bin";

      File directory = new File(dirName);
      if (!directory.exists())
      {
        directory.mkdir();
      }

      //Når vi er sikre på at vores directory findes, skal vi skrive projektfilen
      DataOutputStream outputFile;
      try
      {
        FileOutputStream fstream = new FileOutputStream(
            dirName + "/" + filename);
        outputFile = new DataOutputStream(fstream);

        System.out.printf("writing to file");

        //Here goes an array to be written, men jeg skal lige tænke over det

        String stringTeamMembers = "";
        String stringRequirements = "";
        String title = project.getTitle();
        String description = project.getDescription();
        String customer = project.getCustomer();
        String creator = project.getProjectCreator().getName();

        for (TeamMember teamMember : project.getTeamMemberList())
        {
          stringTeamMembers += teamMember.getName() + ", ";
        }

        for (Requirement requirement : project.getRequirementList())
        {
          stringRequirements += requirement.getTitle() + ", ";
        }

        String outPutString =
            title + "\n" + description + "\n" + customer + "\n" + creator + "\n"
                + stringTeamMembers + "\n" + stringRequirements;

        outputFile.writeUTF(outPutString);
        //This is where the file closes again
        try
        {
          outputFile.close();
          System.out.printf("File saved");

        }

        //This is where all the errors are handled
        catch (FileNotFoundException e)
        {
          e.printStackTrace();
          System.out.println("File not found");
        }
        catch (IOException e)
        {
          e.printStackTrace();
          System.out.println("Writing is hard");
        }
      }
      catch (IOException e)
      {
        e.printStackTrace();
        System.out.println("Error closing file");
      }
    }

  }*/

  //En metode der gemmer til en Json fil fordi det måske er nemmere, men gemmer alting i én fil
  //Jeg tror virkelig ikke det er rigtigt, men jeg har ikke kunnet teste det endnu, så who knows?
  public static void saveJson(ProjectList projects)
  {
    XmlJsonParser jParser = new XmlJsonParser();

    File file;
    try
    {
      file = jParser.toJson(projects.getProjects() , "allProjects.json");
    }
    catch (ParserException e)
    {
      System.out.println("JSon parser error");
      e.printStackTrace();
    }
  }

  public static void saveJson(CustomerList customers)
  {
    XmlJsonParser jParser = new XmlJsonParser();

    File file;
    try
    {
      file = jParser.toJson(customers.getCustomers() , "allCustomers.json");
    }
    catch (ParserException e)
    {
      System.out.println("JSon parser error");
      e.printStackTrace();
    }
  }

  public static void saveJson(EmployeeList employees)
  {
    XmlJsonParser jParser = new XmlJsonParser();

    File file;
    try
    {
      file = jParser.toJson(employees.getEmployees() , "allEmployees.json");
    }
    catch (ParserException e)
    {
      System.out.println("JSon parser error");
      e.printStackTrace();
    }
  }
}


