import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class LexicalAnalyzer {
    
    static String[] keywords = {"else","if","int","return","void","while","float"};
    static String[] symbols = {"+","-","*","/","<",">","=",";",",","(",")","[","]","{","}"};
    static String[] doubleSymbols = {"<=",">=","==","!="};
    
    /*public static ArrayList<String> readFromFile(String name)
    {
        ArrayList<String> text = new ArrayList<String>();
        try
        {
            File inFile = new File(name);
            Scanner sc = new Scanner(inFile);
            while(sc.hasNextLine())
            {
                text.add(sc.nextLine());
            }
        }
        catch(Exception e)
        {
            System.out.println("Problem opening file!!");
        }

        return text;
    }*/
    
    public static void tokenizeCode(ArrayList<String> theCode)
    {
        
        boolean comment = false; /* comment */
        boolean line_comment = false; // line comment
        // Everything happens here!
        int y = 0;
        int commentOpenCounter = 1;// counter for the nested comment
        int commentCloseCounter = 1;
        //int commentDepth = -1;
        for(String line : theCode)
        {
            line_comment = false;
            int x = 0;
            //System.out.println("Next Line========================================================================");
            
            int commentDepth = -1;
            
            System.out.println("\nInput : " + line);
            while(x < line.length())
            {
                boolean found = false;
                
                if(x < line.length() - 1 && commentDepth !=0)
                {
                  if(line.substring(x, x+2).equals("/*"))
                  {
                  
                     comment = true;
                     found = true;
                     commentOpenCounter++;
                     //System.out.println(commentOpenCounter);
                     //y = x;
                     x+=2;
                     
                     
                  }
                  else if(line.substring(x, x+2).equals("//"))
                  {
                      line_comment = true;
                      found = true;
                      x+=2;
                  }
                  else if(line.substring(x, x +2).equals("*/"))
                  {
                     if(commentOpenCounter-commentCloseCounter != 0)
                     {
                        comment = false;
                        found = true;
                        commentCloseCounter++;
                        //System.out.println(commentCloseCounter);
                        x+=2;
                     }
                     else
                     {
                        commentDepth = 0;
                     }
                     /*else if(commentDepthCounter ==0)
                     {
                        comment = false;
                        found = true;
                        //line.replace(line.substring(y,x),"");
                        System.out.println(commentDepthCounter);
                        //System.out.println("after delete: " + line);
                        x++;
                      }*/ 
                        
                  }
                }
                
                if(!comment && !line_comment)
                {
                  if(!found)
                  {
                    for(String symbol : doubleSymbols)
                    {
                       if((x + symbol.length()) <= line.length())
                       {
                          if(line.substring(x, x+symbol.length()).equals(symbol)){
                               System.out.println("doubleSymbol: " + symbol);
                               x += 2;
                               found = true;
                          }
                       }
                    }
                  }
                  if(!found)
                  {
                     for(String word : keywords)
                     {
                        if((x + word.length()) <= line.length())
                        {
                            if(line.substring(x, x+word.length()).equals(word)){
                                 System.out.println("Keyword: " + word);
                                 x += (word.length());
                                 found = true;
                            }
                         }
                          
                     }
                  }
                  if(!found)
                  {
                     for(String symbol : symbols)
                     {
                        if((x + symbol.length()) <= line.length())
                        {
                            if(line.substring(x, x+symbol.length()).equals(symbol))
                            {
                                 System.out.println("Symbol: " + symbol);
                                 x++;
                                 found = true;
                            }
                        }
                            
                     }
                  }   
                  if(!found)
                  {
                     int l = 0;
                     while(line.charAt(x) >= 'a' && line.charAt(x) <= 'z' & x < line.length())
                     {
                        l++;
                        x++;
                     }
                     if (l > 0)
                     {
                        System.out.println("Identifier: " + line.substring(x - l, x));
                        found = true; 
                     }
                  }
                
                
                  if(!found)
                  {
                     if(x < line.length() && (line.charAt(x) >= '0' && line.charAt(x) <= '9'))
                     {
                        int l = 1;
                        x++;
                        boolean fl_num = false;
                        while(x < line.length() && ((line.charAt(x) >= '0' && line.charAt(x) <= '9') || (line.charAt(x) == '.') || (line.charAt(x) == 'E' && fl_num)))
                        {
                          if(line.charAt(x) == '.')
                             fl_num = true;
                          l++;
                           x++;
                        }
                       
                        System.out.println((fl_num ? "Float Number: " : "Int Number: ") + line.substring(x - l, x));
                        found = true; 
                      }
                    }
                
                }
            
                if(!found)
                {
                    if(line.charAt(x) != ' ' && !comment && !line_comment)
                        System.out.println("Unexpected Character: " + line.substring(x,x+1));
                    x++;
                }
            }
        }//end of for loop
        
        //System.out.println(theCode);
    }
    
    
    public static void main(String[] args)
    {
      
        ArrayList<String> text = new ArrayList<String>();
        try
        {
            File inFile = new File(args[0]);
            Scanner sc = new Scanner(inFile);
            while(sc.hasNextLine())
            {
                text.add(sc.nextLine());
            }
        }
        catch(Exception e)
        {
            System.out.println("Problem opening file!!");
        }
      
      
      
      
        /*System.out.println("Enter name of file to analyze.");
        Scanner rd = new Scanner(System.in);
        String name = rd.nextLine();
        ArrayList<String> code = readFromFile(name);*/
        tokenizeCode(text);
        
    }
    
}