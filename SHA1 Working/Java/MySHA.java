import java.util.Scanner;

public class MySHA{
  
  Scanner in = new Scanner(System.in);
  /**public enum Msg{
    shaSuccess = 0,
    shaNull,            /* Null pointer parameter
    shaInputTooLong,    /* input data too long
    shaStateError       /* called Input after Result 
    };*/
  
  /**
   int SHA1Reset(  SHA1Context *);
   int SHA1Input(  SHA1Context *,
   const uint8_t *,
   unsigned int);
   int SHA1Result( SHA1Context *,
   uint8_t Message_Digest[SHA1HashSize]);
   
   /* Local Function Prototyptes 
   void SHA1PadMessage(SHA1Context *);
   void SHA1ProcessMessageBlock(SHA1Context *);
   
   
   */
  
  //public final long SHA1HashSize = 20;
  
  int[] Intermediate_Hash = {
    0x67452301,
    0xEFCDAB89,
    0x98BADCFE,
    0x10325476,
    0xC3D2E1F0
  }; /* Message Digest  */
  
  int Length_Low;            /* Message length in bits      */
  int Length_High;           /* Message length in bits      */
  
  /* Index into message block array   */
  int Message_Block_Index;
  byte[] Message_Block = new byte[64];      /* 512-bit message blocks      */
  
  //int Computed;               /* Is the digest computed?         */
  //int Corrupted;             /* Is the message digest corrupted? */
  
  
  /**int SHA1Reset(SHA1Context *context)
    {
    if (!context)
    {
    return shaNull;
    }
    
    context->Length_Low             = 0;
    context->Length_High            = 0;
    context->Message_Block_Index    = 0;
    
    context->Intermediate_Hash[0]   = 0x67452301;
    context->Intermediate_Hash[1]   = 0xEFCDAB89;
    context->Intermediate_Hash[2]   = 0x98BADCFE;
    context->Intermediate_Hash[3]   = 0x10325476;
    context->Intermediate_Hash[4]   = 0xC3D2E1F0;
    
    context->Computed   = 0;
    context->Corrupted  = 0;

    return shaSuccess;
    }*/
  
  
  public MySHA(){ 
    
    Length_Low = 0;
    Length_High = 0;
    Message_Block_Index = 0;
    
//     Computed   = 0;
//     Corrupted  = 0;
    
  }
  
  /*public long SHA1Reset(){
   * 
   MySHA();
   }*/
  
  
  int SHA1Result(byte[] Message_Digest)
  {
    
    int i;
    
    /*    if (Message_Digest.equals(null))
     {
     return 1;
     }
     
     if (Corrupted > 0)
     {
     return Corrupted;
     }
     
     if (Computed == 0)*/
    //{
    
    SHA1PadMessage();
    
    /* message may be sensitive, clear it out */
    for(i=0; i < 64; ++i)
      Message_Block[i] = 0;
    
    Length_Low = 0;    /* and clear length */
    Length_High = 0;
    //Computed = 1;
    
    //}
    
    for(i = 0; i < 20; ++i)
    {
      int x = Intermediate_Hash[i>>2] >> 8 * ( 3 - ( i & 0x03 ) );
      //System.out.printf("\n%x >> 8 * ( 3 - ( %d & 0x03) )", Intermediate_Hash[i>>2], i);
      //in.nextLine();
      
      Message_Digest[i] = (byte)x;
      //Message_Digest[i] = test1;
      //System.out.printf("\n\n%02x",(byte)x);
      //in.nextLine();
      
    }
    
    return 0;
  }
  
  
  /**
   ************String to Hex******************
   * 
   toHex(String str){
   //String str = "testString";
   char[] charArray = str.toCharArray();
   
   /*for (int i=0; i < charArray.length; i++)
   System.out.print(charArray[i] + "  ");
   
   char ch;
   String[] hex = new String[charArray.length];
   
   for (int j=0; j < charArray.length; j++){ 
   ch = charArray[j];
   hex[j] = String.format("%x", (int) ch);
   return 
   }
   
   /*System.out.print("\n\n");
   for (int k=0; k < hex.length; k++)
   System.out.print(hex[k] + "  ");
   
   }
   
   }*/
  
  
  int SHA1Input(byte[] message_array, int length)
  {
    
    //char[] charArray = message_array.toCharArray();
    //int[] hex = new int[length];
    
    /**if (length == 0)
      {
      return 0;
      }
      
      if (message_array.equals(null))
      {
      return 1;
      }*/
    
    /*if (Computed > 0)
     {
     Corrupted = 3;
     return 3;
     }
     
     if (Corrupted > 0)
     {
     return Corrupted;
     }*/
     
    //System.out.print(message_array[0]);
    //System.out.print(length);
    //in.nextLine();
    
    byte ch;
    int hex;
    //while(/*length--length != 0 && */ Corrupted != 0)
    for (int i = 0; i < length /*&& Corrupted != 0*/; ++i )
    {
      //System.out.println("Enters for loop");
      //in.nextLine();
      
      ch = message_array[i];
      
      //System.out.print(ch);
      //in.nextLine();
      //hex[i] = String.format("%02x", (int) ch);
      
      hex = (int)ch & 0xFF;
      
      /*System.out.printf("hex value is %x, %1$X\n", test );
       System.out.printf("hex value is %1$#x, %1$#X\n", test );
       System.out.print(test);*/
      
      //System.out.printf("%x", hex);
      //in.nextLine();
      
      //hex = test << 8;
      
      //System.out.print(hex);
      ////in.nextLine();   
      
      Message_Block[Message_Block_Index++] = (byte)hex; 
      
      /*byte xyz = (byte)hex;
      System.out.printf("%x", xyz);
      in.nextLine();*/
      
      Length_Low += 8;
      //System.out.print(Length_Low);
      //in.nextLine();
      
      /*if (Length_Low == 0)
       {
       Length_High++;
       if (Length_High == 0)
       {
       /* Message is too long 
       Corrupted = 1;
       }
       }*/
      
      if (Message_Block_Index == 64)
        SHA1ProcessMessageBlock();
       
    } 
    return 0;
  }
  
  
  void SHA1ProcessMessageBlock()
  {
    //System.out.println("In SHA1ProcessMessageBlock method");
    
    final int K[] = {       /* Constants defined in SHA-1   */
      0x5A827999,
      0x6ED9EBA1,
      0x8F1BBCDC,
      0xCA62C1D6
    };
    
    int      t;                 /* Loop counter                */
    int      temp;              /* Temporary word value        */
    int[]    W = new int[80];   /* Word sequence               */
    int      A, B, C, D, E;     /* Word buffers                */
    
    /*
     *  Initialize the first 16 words in the array W
     */
    int test; 
    for(t = 0; t < 16; t++)
    {
      //System.out.printf("---------------Iteration: %d-----------------\n\n",t);
      test = Message_Block[t * 4] << 24;
      W[t] = test;
      /*System.out.printf("%x\n", test);
      /in.nextLine();*/
      
      test = (Message_Block[t * 4 + 1] << 16) & 0xFFFFFF;
      W[t] |= test;
      /*System.out.printf("val of test is %x\n", test);
      System.out.printf("Value of W[t] is %x\n", W[t]);
      in.nextLine();*/
      
      test = (Message_Block[t * 4 + 2] << 8) & 0xFFFF;
      W[t] |= test;
      /*System.out.printf("%x\n", test);
      System.out.printf("%x\n", W[t]);
      in.nextLine();*/
      
      test = Message_Block[t * 4 + 3] & 0xFF;
      W[t] |= test;
      /*System.out.printf("%x\n", test);
      System.out.printf("%x\n", W[t]);
      in.nextLine();*/
    }
    
    for(t = 16; t < 80; t++)
    {
      int x = W[t - 3] ^ W[t - 8] ^ W[t - 14] ^ W[t - 16];
      W[t] = (x << 1 | x >>> 31);                
    }
    
    A = Intermediate_Hash[0];
    B = Intermediate_Hash[1];
    C = Intermediate_Hash[2];
    D = Intermediate_Hash[3];
    E = Intermediate_Hash[4];
    
    int q,y,e,r;
    /** First 20 rounds */
    for(t = 0; t < 20; t++)
    {
      //System.out.printf("Loop1 -> Entered Round%d...\n\n",t);
      
      q = (A << 5 | A >>> 27) ;
      y = ((B & C) | ((~B) & D));
      e = E ;
      r = W[t] + K[0];
      
      temp =  q + y + e + r;
      E = D;
      D = C;
      C = (B << 30 | B >>> 2);
      B = A;
      A = temp;
      
      /*System.out.printf("%x\n", q);
      System.out.printf("%x\n", y);
      System.out.printf("%x\n", e);
      System.out.printf("%x\n", r);
      System.out.printf("%x\n", temp);   
      in.nextLine();*/
    }
    
    /** Next 20 rounds */
    for(t = 20; t < 40; t++)
    {
      //System.out.printf("\n\nLoop2 -> Entered Round%d...\n\n",t);
      temp = (A << 5 | A >>> 27) + (B ^ C ^ D) + E + W[t] + K[1];
      
      /*System.out.printf("%x\n", temp);
      in.nextLine();*/
      
      E = D;
      D = C;
      C = (B << 30 | B >>> 2);
      B = A;
      A = temp;
    }
    
    /** Next 20 rounds */
    for(t = 40; t < 60; t++)
    {
      //System.out.printf("Loop3 -> Entered Round%d...\n\n",t);
      temp = (A << 5 | A >>> 27) + ((B & C) | (B & D) | (C & D)) + E + W[t] + K[2];
      E = D;
      D = C;
      C = (B << 30 | B >>> 2);
      B = A;
      A = temp;
      /*System.out.printf("%x", temp);
      in.nextLine();*/
    }
    
    /** Next 20 rounds */
    for(t = 60; t < 80; t++)
    {
      //System.out.printf("Loop4 -> Entered Round%d...\n\n",t);
      temp = (A << 5 | A >>> 27) + (B ^ C ^ D) + E + W[t] + K[3];
      E = D;
      D = C;
      C = (B << 30 | B >>> 2);
      B = A;
      A = temp;
      /*System.out.printf("%x", temp);
      in.nextLine();*/
    }
    
    Intermediate_Hash[0] += A;
    //System.out.printf("%x",Intermediate_Hash[0]);
    Intermediate_Hash[1] += B;
    //System.out.printf("%x",Intermediate_Hash[1]);
    Intermediate_Hash[2] += C;
    //System.out.printf("%x",Intermediate_Hash[2]);
    Intermediate_Hash[3] += D;
    //System.out.printf("%x",Intermediate_Hash[3]);
    Intermediate_Hash[4] += E;
    //System.out.printf("%x",Intermediate_Hash[4]);

    Message_Block_Index = 0;
  }
  
  
  void SHA1PadMessage()
  {
    /*
     *  Check to see if the current message block is too small to hold
     *  the initial padding bits and length.  If so, we will pad the
     *  block, process it, and then continue padding into a second
     *  block.
     */
    
    //System.out.println("Inside SHA1PadMessage method");
    
    if (Message_Block_Index > 55)
    {
      Message_Block[Message_Block_Index++] = (byte)0x80;
      
      while(Message_Block_Index < 64)
        Message_Block[Message_Block_Index++] = 0;
      
      SHA1ProcessMessageBlock();
      
      while(Message_Block_Index < 56)
        Message_Block[Message_Block_Index++] = 0;
    }
    
    else
    {
      Message_Block[Message_Block_Index++] = (byte)0x80;
      
      //byte abc = (byte)0x80;
      //System.out.printf("%x", abc);
      //System.out.printf("%x", W[t]);
      //in.nextLine();
      
      while(Message_Block_Index < 56)
        Message_Block[Message_Block_Index++] = 0;
    }
    
    /* Store the message length as the last 8 octets */
    
    int y = Length_High >> 24;
    Message_Block[56] = (byte)y;
    
    int z = Length_High >> 16;
    Message_Block[57] = (byte)z;
    
    int w = Length_High >> 8;
    Message_Block[58] = (byte)w;
    Message_Block[59] = (byte)Length_High;
     
    y = Length_Low >> 24;
    Message_Block[60] = (byte)y;
    
    z = Length_Low >> 16;
    Message_Block[61] = (byte)z;
    
    w = Length_Low >> 8;
    Message_Block[62] = (byte)w;
    Message_Block[63] = (byte)Length_Low; 
    
    SHA1ProcessMessageBlock();    
  }
}


