package Model;

public class Requirement extends Job
{
  private String type;

  public Requirement(String title, String status, String type)
  {
    super(title,status);
    this.type = type;
  }

  public Requirement(String title, String status)
  {
    super(title,status);
  }

  /* Kommer til at virke
  public Task<Task> getTasks(){
    return null;
  }
   */




}
