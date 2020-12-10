import parser.ParserException;
import parser.XmlJsonParser;
import Model.ProjectList;

import java.io.File;

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
      filename = ((Project) obj).getTitle();

  }

  //TODO en metode som kan tilføje til filer (s. 734 i bogen)
  // Jeg tror ændringer skal gøre at filerne bliver overskrevet, men det skal vi lige snakke om
  // Skal hvert projekt ikke bare have sin egen fil?
}
