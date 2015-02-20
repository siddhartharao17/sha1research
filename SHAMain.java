

public class SHAMain{

public final String TEST1 =  "abc";
public final String TEST2a = "abcdbcdecdefdefgefghfghighijhi";


public final String TEST2b = "jkijkljklmklmnlmnomnopnopq";
public final String TEST2  = TEST2a + TEST2b;
public final String TEST3  = "a";
public final String TEST4a = "01234567012345670123456701234567";
public final String TEST4b = "01234567012345670123456701234567";
    /* an exact multiple of 512 bits */
public final String TEST4  = TEST4a + TEST4b;


String[] testarray =
{
    TEST1,
    TEST2,
    TEST3,
    TEST4
};


public long[] repeatcount = { 1, 1, 1000000, 10 };


String[] resultarray =
{
    "A9 99 3E 36 47 06 81 6A BA 3E 25 71 78 50 C2 6C 9C D0 D8 9D",
    "84 98 3E 44 1C 3B D2 6E BA AE 4A A1 F9 51 29 E5 E5 46 70 F1",
    "34 AA 97 3C D4 C4 DA A4 F6 1E EB 2B DB AD 27 31 65 34 01 6F",
    "DE A3 56 A2 CD DD 90 C7 A7 EC ED C5 EB B5 63 93 4F 46 04 52"
};

public static void main(String args[]){

/**
	Reference created of class MySHA
*/
    MySHA context;
    
    int i, j, err;
    long[] Message_Digest = new long[20];

    /*
     *  Perform SHA-1 tests
     */
    for(j = 0; j < 4; ++j)
    {
        System.out.println( "\nTest" + j+1 + repeatcount[j] + "," + testarray[j] + "\n");
                
        context = new MySHA();	/* Object created */
        
      /**  err = context.SHA1Reset();
        if (err)
        {
            System.out.println("SHA1Reset Error %d.\n", err );
            break;    /* out of for j loop
        }*/

        for(i = 0; i < repeatcount[j]; ++i)
        {

	try{
            err = context.SHA1Input(testarray[j], context.length(testarray[j]));
            }catch(Exception e){
            
                System.out.println("SHA1Input Error" + e +"\n");
                break;    /* out of for i loop */
           
        }

	try{
        	err = SHA1Result(Message_Digest);
        }catch(Exception e){
        
            System.out.println("SHA1Result Error" + e + "could not compute message digest.\n");
            break;
        }
        
            System.out.println("\t");
            for(i = 0; i < 20 ; ++i)
            {
                System.out.format("%02X ", Message_Digest[i]);
            }
            System.out.println("\n");
        
        System.out.println("Should match:\n");
        System.out.println("\t"+ resultarray[j] +"\n");
    }

    /* Test some error returns */
    
    try{
    err = context.SHA1Input(testarray[1], 1);
    }catch(Exception e){
    System.out.println ("\nError"+ e + ".\n");
    }
    
   /** try{
    err = context.S
    }catch(Exception err){
    System.out.println ("\nError %d. Should be %d.\n", err, shaNull );
    }*/
    
    
    //return 0;
}

}
}
