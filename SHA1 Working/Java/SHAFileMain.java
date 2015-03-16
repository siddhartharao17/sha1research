import java.io.FileInputStream;

public class SHAFileMain
{  
  public static void main(String args[]) throws Exception
  { 
  
    String file = "fx.jpg";  
    FileInputStream filename = new FileInputStream(file);
    
    /* Reference created of class MySHA */
    MySHA context;
    
    int i, j; 
    byte[] Message_Digest = new byte[20];
    byte[] charArray = new byte[64];
    
    
    /* Object created */
    context = new MySHA();
    
    
    int bytesRead = 0;
     while ((bytesRead = filename.read(charArray)) != -1) {
                  
            context.SHA1Input(charArray, bytesRead);
     }
    
    
    context.SHA1Result(Message_Digest);

    System.out.print("\nResult #:\t");
    
    for(i = 0; i < 20 ; ++i)
      System.out.printf("%02X ", Message_Digest[i]);
    
      System.out.println();
    
    filename.close();
  }
}
