import parser.ParserException;
import parser.XmlJsonParser;
import Model.ProjectList;

import java.io.*;

public class FileHandler

{

 // En metode der gemmer vores projektliste til en XML, som kan loades ind på hjemmesiden.
  public static void saveXML(ProjectList list)
  {
    XmlJsonParser theParser = new XmlJsonParser();
    try
    {
      File file = theParser.toXml(list.getProjects(), "projects.xml");
    }
    catch (ParserException e)
    {
      e.printStackTrace();
      System.out.println("XML parser error");
    }
  }

  // En metode der kan oprette en ny fil og gemme data på nye projekter
  public static void newSave(Object obj)
  {
    String filename;
    if(obj instanceof Project)
    {
      filename = ((Project) obj).getTitle();
      FileOutputStream fstream = null; //TODO Jeg skal lige tjekke hvordan man gør det der med filnavnet
      try
      {
        fstream = new FileOutputStream(filename);
      }
      catch (FileNotFoundException e)
      {
        e.printStackTrace();
        System.out.println("File not found");
      }
      DataOutputStream outputFile = new DataOutputStream(fstream);

      System.out.printf("writing to file");

      //Here goes an array to be written, men jeg skal lige tænke over det
     /* for (int i = 0; i < ; i++)
      {

      }*/

      //This is where the file closes again
      try
      {
        outputFile.close();
        System.out.printf("File saved");
      }
      catch (IOException e)
      {
        e.printStackTrace();
        System.out.println("Error closing file");
      }

    }
  }

  //TODO en metode som kan tilføje til filer (s. 734 i bogen)
  // Jeg tror ændringer skal gøre at filerne bliver overskrevet, men det skal vi lige snakke om
  // Skal hvert projekt ikke bare have sin egen fil?
}
