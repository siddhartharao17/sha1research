
class Test
{

/*public static void main(String[] args)
{
     Test test1 = new Test();
     String s = "abc";
     System.out.println(s);

}*/

/**public static void main(String[] args)
{
String str = "testString";
char[] charArray = str.toCharArray();

for (int i=0; i < charArray.length; i++)
	System.out.print(charArray[i] + "  ");

char ch; int hexToInt;
String[] hex = new String[charArray.length];

for (int j=0; j < charArray.length; j++){	
ch = charArray[j];

//hex[j] = String.format("%x", (int)ch);
hex[j] = Integer.toHexString((int)ch);
hexToInt = Integer.parseInt(hex[j],16);

}

System.out.print("\n\n");
for (int k=0; k < hex.length; k++)
	System.out.print(hex[k] + "  ");

}*/

public static void main(String[] args){

/*char a = 'A';
//short b = 3;
int c = (int)a & 0xFF;

System.out.println(c);*/


int a  = 0x67452301;
int b = a << 5;
System.out.printf("%1$x",b);
}
}
