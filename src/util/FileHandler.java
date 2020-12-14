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
    XmlJsonParser theParser = new XmlJsonParser();
    try
    {
      theParser.toXml(projects.getProjects(), "projects.xml");
    }
    catch (ParserException e)
    {
      e.printStackTrace();
    }
  }

  // En metode der kan oprette en ny fil og gemme data på nye projekter.
  // denne her laver mapper
  public static void save(Object list) throws IOException
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

  public static ProjectList projectListLoad() throws IOException, ClassNotFoundException
  {
    File file = new File("projectList.bin");
    FileInputStream fis = new FileInputStream(file);
    ObjectInputStream in = new ObjectInputStream(fis);

    ProjectList projectList = (ProjectList) in.readObject();
    in.close();
    System.out.println("Project list loaded");
    return projectList;
  }

  public static EmployeeList employeeListLoad() throws IOException, ClassNotFoundException
  {
    File file = new File("employeeList.bin");
    FileInputStream fis = new FileInputStream(file);
    ObjectInputStream in = new ObjectInputStream(fis);

    EmployeeList employeeList = (EmployeeList)  in.readObject();
    in.close();
    System.out.println("Employee list loaded");
    return employeeList;
  }

  public static CustomerList customerListLoad() throws IOException, ClassNotFoundException
  {
    File file = new File("customerList.bin");
    FileInputStream fis = new FileInputStream(file);
    ObjectInputStream in = new ObjectInputStream(fis);

    CustomerList customerList = (CustomerList)  in.readObject();
    in.close();
    System.out.println("Employee list loaded");
    return customerList;
  }
}