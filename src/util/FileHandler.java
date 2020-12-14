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
  public static void Save(Object list) throws IOException
  {
        String filename = "";
      if (list instanceof ProjectList)
        filename = "projectList.bin";
      if (list instanceof CustomerList)
        filename = "customerList.bin";
      if (list instanceof EmployeeList)
        filename = "employeeList.bin";

    File file = new File(filename);

    FileOutputStream fos = new FileOutputStream(file);
    ObjectOutputStream out = new ObjectOutputStream(fos);

    out.writeObject(list);
    out.close();
    System.out.println("Saved file to: " + file.getAbsolutePath());
  }

}


