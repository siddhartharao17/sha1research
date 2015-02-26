

public class MySHA{


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
    long[] Intermediate_Hash = new long[5]; /* Message Digest  */

    long Length_Low;            /* Message length in bits      */
    long Length_High;           /* Message length in bits      */

                               /* Index into message block array   */
    int Message_Block_Index;
    short[] Message_Block = new short[64];      /* 512-bit message blocks      */

//    int Computed;               /* Is the digest computed?         */
//    int Corrupted;             /* Is the message digest corrupted? */
    

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

     Intermediate_Hash[0]   = 0x67452301;
     Intermediate_Hash[1]   = 0xEFCDAB89;
     Intermediate_Hash[2]   = 0x98BADCFE;
     Intermediate_Hash[3]   = 0x10325476;
     Intermediate_Hash[4]   = 0xC3D2E1F0;

//     Computed   = 0;
//     Corrupted  = 0;
}

/*public long SHA1Reset(){

     MySHA();
}*/


int SHA1Result(short[] Message_Digest)
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

    if (Computed == 0)
    {*/
        SHA1PadMessage();
        
        for(i=0; i<64; ++i)
        {
            /* message may be sensitive, clear it out */
            Message_Block[i] = 0;
        }
        
        Length_Low = 0;    /* and clear length */
        Length_High = 0;
        //Computed = 1;

    //}

    for(i = 0; i < 20; ++i)
    {
        long x = Intermediate_Hash[i>>2] >> 8 * ( 3 - ( i & 0x03 ) );
        Message_Digest[i] = (short)x;
    }

    return 0;
}

/**public String toHex(String arg) {
    //return String.format("%x", new BigInteger(1, arg.getBytes("UTF-8")));
    String hexString = Hex.encodeHexString(arg.getBytes("UTF-8"));
    return hexString;
}*/

/**
************String to Hex******************

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


int SHA1Input(String message_array/*, long length*/)
{

    char[] charArray = message_array.toCharArray();
    int[] hex = new int[charArray.length];
    
    /**if (length != 0)
    {
        return 0;
    }

    if (message_array != 0)
    {
        return 1;
    }*/

/*    if (Computed > 0)
    {
        Corrupted = 3;
        return 3;
    }

    if (Corrupted > 0)
    {
         return Corrupted;
    }*/
    
    char ch;
    //while(/*length--length != 0 && */ Corrupted != 0)
    for (int i = 0; i < charArray.length /*&& Corrupted != 0*/; i++ )
    {
    
    ch = charArray[i];
    //hex[i] = String.format("%02x", (int) ch);

    hex[i] = (int)ch & 0xFF;
    
    
    
    Message_Block[Message_Block_Index++] = (short)hex[i]; /*Long.decode(hex[i])*/  /*(message_array & 0xFF)*/
    

    Length_Low += 8;
    if (Length_Low == 0)
    {
        Length_High++;
        if (Length_High == 0)
        {
            /* Message is too long */
            //Corrupted = 1;
        }
    }

    if (Message_Block_Index == 64)
    {
        SHA1ProcessMessageBlock();
    }

    //message_array++;
    }

    return 0;
}


void SHA1ProcessMessageBlock()
{
    final long[] K =    {       /* Constants defined in SHA-1  */ 
                            0x5A827999,
                            0x6ED9EBA1,
                            0x8F1BBCDC,
                            0xCA62C1D6
                            };
    int      t;                 /* Loop counter                */
    long      temp;              /* Temporary word value        */
    long[]    W = new long[80];   /* Word sequence               */
    long     A, B, C, D, E;     /* Word buffers                */

    /*
     *  Initialize the first 16 words in the array W
     */
    for(t = 0; t < 16; t++)
    {
        W[t] = Message_Block[t * 4] << 24;
        W[t] |= Message_Block[t * 4 + 1] << 16;
        W[t] |= Message_Block[t * 4 + 2] << 8;
        W[t] |= Message_Block[t * 4 + 3];
    }

    for(t = 16; t < 80; t++)
    {
                        long x = W[t - 3] ^ W[t - 8] ^ W[t - 14] ^ W[t - 16];
                        W[t] = ((x << 1) | (x >>> 31));                
    }

    A = Intermediate_Hash[0];
    B = Intermediate_Hash[1];
    C = Intermediate_Hash[2];
    D = Intermediate_Hash[3];
    E = Intermediate_Hash[4];

/** First 20 rounds */
    for(t = 0; t < 20; t++)
    {
        temp =  (A << 5) + ((B & C) | ((~B) & D)) + E + W[t] + K[0];
        E = D;
        D = C;
        C = (B >>> 2);


        B = A;
     
        A = temp;
    }

/** Next 20 rounds */
    for(t = 20; t < 40; t++)
    {
        temp = (A << 5) + (B ^ C ^ D) + E + W[t] + K[1];
        E = D;
        D = C;
        C = (B >>> 2);
        B = A;
        A = temp;
    }

/** Next 20 rounds */
    for(t = 40; t < 60; t++)
    {
        temp = (A << 5) + ((B & C) | (B & D) | (C & D)) + E + W[t] + K[2];
        E = D;
        D = C;
        C = (B >>> 2);
        B = A;
        A = temp;
    }

/** Next 20 rounds */
    for(t = 60; t < 80; t++)
    {
        temp = (A << 5) + (B ^ C ^ D) + E + W[t] + K[3];
        E = D;
        D = C;
        C = (B >>> 2);
        B = A;
        A = temp;
    }

    Intermediate_Hash[0] += A;
    Intermediate_Hash[1] += B;
    Intermediate_Hash[2] += C;
    Intermediate_Hash[3] += D;
    Intermediate_Hash[4] += E;

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
    
    if (Message_Block_Index > 55)
    {
        Message_Block[Message_Block_Index++] = 0x80;
        while(Message_Block_Index < 64)
        {
            Message_Block[Message_Block_Index++] = 0;
        }

        SHA1ProcessMessageBlock();

        while(Message_Block_Index < 56)
        {
            Message_Block[Message_Block_Index++] = 0;
        }
    }
    else
    {
        Message_Block[Message_Block_Index++] = 0x80;
        while(Message_Block_Index < 56)
        {

            Message_Block[Message_Block_Index++] = 0;
        }
    }

    /*
     *  Store the message length as the last 8 octets
     */
    
    long y = Length_High >> 24;
    Message_Block[56] = (short)y;
    
    long z = Length_High >> 16;
    Message_Block[57] = (short)z;
    
    long w = Length_High >> 8;
    Message_Block[58] = (short)w;
    Message_Block[59] = (short)Length_High;
    
    
    y = Length_Low >> 24;
    Message_Block[60] = (short)y;
    
    z = Length_Low >> 16;
    Message_Block[61] = (short)z;
    
    w = Length_Low >> 8;
    Message_Block[62] = (short)w;
    Message_Block[63] = (short)Length_Low;

    SHA1ProcessMessageBlock();
}

}
