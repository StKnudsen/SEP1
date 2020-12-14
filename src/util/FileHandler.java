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
  public static void newSave(ProjectList projects) throws IOException
  {
    String filename = "projectList.bin";
    File file = new File(filename);

    FileOutputStream fos = new FileOutputStream(file);
    ObjectOutputStream out = new ObjectOutputStream(fos);

    out.writeObject(projects);
    out.close();
    System.out.println("Saved file to: " + file.getAbsolutePath());
  }

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


