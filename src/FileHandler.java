import parser.ParserException;
import parser.XmlJsonParser;
import Model.ProjectList;

import java.io.File;

public class FileHandler

{

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


}
