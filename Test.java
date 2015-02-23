
class Test
{

/**public static String stringToHex(String base)
    {
     StringBuffer buffer = new StringBuffer();
     int intValue;
     for(int x = 0; x < base.length(); x++)
         {
         int cursor = 0;
         intValue = base.charAt(x);
         String binaryChar = new String(Integer.toBinaryString(base.charAt(x)));
         for(int i = 0; i < binaryChar.length(); i++)
             {
             if(binaryChar.charAt(i) == '1')
                 {
                 cursor += 1;
             }
         }
         if((cursor % 2) > 0)
             {
             intValue += 128;
         }
         buffer.append(Integer.toHexString(intValue) + " ");
     }
     return buffer.toString();
}

public static void main(String[] args)
    {
    Test test1 = new Test();
     String s = "abc";
     System.out.println(s);
     System.out.println(test1.stringToHex(s));
}*/

public static void main(String[] args)
{
String str = "testString";
char[] charArray = str.toCharArray();

for (int i=0; i < charArray.length; i++)
	System.out.print(charArray[i] + "  ");

char ch;
String[] hex = new String[charArray.length];
for (int j=0; j < charArray.length; j++){	
ch = charArray[j];
hex[j] = String.format("%x", (int) ch);
}

System.out.print("\n\n");
for (int k=0; k < hex.length; k++)
	System.out.print(hex[k] + "  ");

}
}
