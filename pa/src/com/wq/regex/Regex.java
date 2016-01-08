package com.wq.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Regex
{
  public static void main(String[] args)
  {
    String txt="/people/";

    String re1="(\\/)";	// Any Single Character 1
    String re2="(p)";	// Any Single Character 2
    String re3="(e)";	// Any Single Character 3
    String re4="(o)";	// Any Single Character 4
    String re5="(p)";	// Any Single Character 5
    String re6="(l)";	// Any Single Character 6
    String re7="(e)";	// Any Single Character 7
    String re8="(\\/)";	// Any Single Character 8

    Pattern p = Pattern.compile(re1+re2+re3+re4+re5+re6+re7+re8,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    Matcher m = p.matcher(txt);
    if (m.find())
    {
        String c1=m.group(1);
        String c2=m.group(2);
        String c3=m.group(3);
        String c4=m.group(4);
        String c5=m.group(5);
        String c6=m.group(6);
        String c7=m.group(7);
        String c8=m.group(8);
        System.out.print("("+c1.toString()+")"+"("+c2.toString()+")"+"("+c3.toString()+")"+"("+c4.toString()+")"+"("+c5.toString()+")"+"("+c6.toString()+")"+"("+c7.toString()+")"+"("+c8.toString()+")"+"\n");
    }
  }
}
