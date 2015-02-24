

public class SHAMain{


public static void main(String args[]){

String testarray = "abc";


String resultarray = "A9 99 3E 36 47 06 81 6A BA 3E 25 71 78 50 C2 6C 9C D0 D8 9D";

/**
	Reference created of class MySHA
*/
    //SHAMain s1 = new SHAMain();
    
    MySHA context;
    
    int i, j; 
    
    int[] Message_Digest = new int[20];

    /*
     *  Perform SHA-1 tests
     */
                
        context = new MySHA();	/* Object created */
        
    
        context.SHA1Input(testarray);
    
       	context.SHA1Result(Message_Digest);
         
            System.out.println("\t");
            for(i = 0; i < 20 ; ++i)
            {
                System.out.format("%02X ", Message_Digest[i]);
            }
            System.out.println("\n");
        
        System.out.println("Should match:\n");
        System.out.println("\t"+ resultarray +"\n");
    }


}

