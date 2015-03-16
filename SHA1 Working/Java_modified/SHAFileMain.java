//import java.io.DataInputStream;
import java.io.FileInputStream;

public class SHAFileMain
{  
  public static void main(String args[]) throws Exception
  { 
    
    String file = "fx.jpg";
    FileInputStream filename = new FileInputStream(file);
    //DataInputStream srcfile = new DataInputStream(filename);
    
    
    /* Reference created of class MySHA */
    MySHA context;
    
    int i, j; 
    short[] Message_Digest = new short[20];
    byte[] charArray = new byte[128];
    
    /* Object created */
    context = new MySHA(); 
   
    
    int bytesRead = 0;
     while ((bytesRead = filename.read(charArray)) != -1) {
     //for(i = 0; srcfile.readShort() != 0 && i < 64 ;i++){
     	//	    charArray[i] = srcfile.readShort();
          //          shortRead = charArray.length;
                    context.SHA1Input(charArray, bytesRead);
     }
     
    context.SHA1Result(Message_Digest);

    System.out.print("\nResult #:\t");
    
    for(i = 0; i < 20 ; ++i)
      System.out.printf("%04X ", Message_Digest[i]);
    
   System.out.println();
   
   filename.close();
  }
}
