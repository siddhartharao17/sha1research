import java.io.FileInputStream;

public class SHATextMain
{  
  public static void main(String args[]) throws Exception
  { 
  
	
    String testarray = "abc";
    String resultarray = "A9 99 3E 36 47 06 81 6A BA 3E 25 71 78 50 C2 6C 9C D0 D8 9D";
    
    //String testarray = "a";
    //String resultarray = "86 F7 E4 37 FA A5 A7 FC E1 5D 1D DC B9 EA EA EA 37 76 67 B8";
    
    //String testarray = "abcdbcdecdefdefgefghfghighijhi";
    //String resultarray = " f9537c23893d2014f365adf8ffe33b8eb0297ed1";
    
    //String testarray = "0123456701234567012345670123456701234567012345670123456701234567";
    //String resultarray = "e0c094e867ef46c350ef54a7f59dd60bed92ae83";
    
    /* Reference created of class MySHA */
    MySHA context;
    
    int i, j; 
    byte[] Message_Digest = new byte[20];
    byte[] charArray = new byte[64];
    
    /* Object created */
    context = new MySHA(); 
    
    /* Records bytes and byte count */
    charArray = testarray.getBytes();
    int bytesRead = charArray.length;
    
    /* Perform SHA-1 tests */
    context.SHA1Input(charArray, bytesRead);
    context.SHA1Result(Message_Digest);

    System.out.print("\nResult #:\t");
    
    for(i = 0; i < 20 ; ++i)
      System.out.printf("%02X ", Message_Digest[i]);
    
      System.out.println();
    System.out.println("\nShould Match #:\t"+resultarray);
    
  }
}
