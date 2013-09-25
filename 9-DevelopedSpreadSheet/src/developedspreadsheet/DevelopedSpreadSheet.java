/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package developedspreadsheet;

import java.util.StringTokenizer;

/**
 *
 * @author Sachintha
 */
public class DevelopedSpreadSheet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cell[][] theCell = new Cell[3][3];
        theCell[0][0] = new Cell("A1");
        theCell[0][1] = new Cell("B1");
        theCell[0][2] = new Cell("C1");
        theCell[1][0] = new Cell("A2");
        theCell[1][1] = new Cell("B2");
        theCell[1][2] = new Cell("C2");
        theCell[2][0] = new Cell("A3");
        theCell[2][1] = new Cell("B3");
        theCell[2][2] = new Cell("C3");

        theCell[0][0].setInput("Sachintha");
        theCell[0][0].Function(theCell);
        theCell[0][0].ShowString();

        theCell[0][1].setInput("Hashan");
        theCell[0][1].Function(theCell);
        theCell[0][1].ShowString();

        theCell[1][1].setInput("Gamlath");
        theCell[1][1].Function(theCell);
        theCell[1][1].ShowString();

        theCell[0][2].setInput("=A1+B1+B2");
        theCell[0][2].Function(theCell);
        theCell[0][2].ShowString();

        System.out.println();

         theCell[1][0].setInput(10);
        theCell[1][0].Function(theCell);
        theCell[1][0].ShowNumaric();

        theCell[1][1].setInput(34);
        theCell[1][1].Function(theCell);
        theCell[1][1].ShowNumaric();

        theCell[2][1].setInput(14);
        theCell[2][1].Function(theCell);
        theCell[2][1].ShowNumaric();

        theCell[1][2].setInput("=A2+B2-B3");
        theCell[1][2].Function(theCell);
        theCell[1][2].ShowNumaric();

        System.out.println("\nAfter changing the value of A2;");

        theCell[1][0].setInput(90);
        theCell[1][0].Function(theCell);
        theCell[1][0].ShowNumaric();
        theCell[1][2].Function(theCell);
        theCell[1][2].ShowNumaric();

        System.out.println("\nAfter changing the formula of C2;");

        theCell[1][2].setInput("=B3");
        theCell[1][2].Function(theCell);
        theCell[1][2].ShowNumaric();
    }

    public static String getCellType(int col,int row, Cell theCell[][])
    {
        return theCell[row][col].getType();
    }

    public static double getCellNumaricalData(int col,int row, Cell theCell[][])
    {
        return theCell[row][col].getNumber();
    }

    public static String getCellStringData(int col,int row, Cell theCell[][])
    {
        return theCell[row][col].getStr();
    }

}

class Cell
{
    private String cellName;
    private double number;
    private String str;
    private String type;
    private String[] subStr = new String[3];
    private char[] token = new char[2];
    private double[] num = new double[3];
    private String[] str1 = new String[3];
    private int col;
    private int row;
    private String resultStr;
    private double resultNumber = 0;

    public Cell(String cellName)
    {
       this.cellName = cellName;
    }

    public void setInput(double number)
   {
      this.number = number;
      type = "Numaric";
   }

   public void setInput(String str)
   {
      this.str = str;
      type = "String";
   }

   public String getType()
   {
       return type;
   }

   public Double getNumber()
   {
       return number;
   }

   public String getStr()
   {
       return str;
   }

   public void Function(Cell theCell[][])
   {
      if (type.equals("String"))
      {
          if (str.charAt(0) == '=')
          {
             int j = 0;
             for (int i = 0; i<str.length(); i++)
             {
                 if(str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/')
                 {
                     token[j] = str.charAt(i);
                     j++;
                 }
             }
  
              StringTokenizer st = new StringTokenizer(str, "+-/*=");
              subStr[0] = new String();
              subStr[0] = st.nextToken();
              for (int i = 1; i <= j; i++)
              {
                  if(st.hasMoreTokens())
                  {
                     subStr[i] = new String();
                     subStr[i] = st.nextToken();
                  }
              }

              str1[0] = null;

              for (int i = 0; i <= j; i++)
              {
                  col = (int)subStr[i].charAt(0) - 65;
                  row = (Integer.parseInt("" + subStr[i].charAt(1))) - 1;
                  String t = DevelopedSpreadSheet.getCellType(col, row, theCell);
                  if (t.equals("String"))
                  {
                      str1[i] = new String();
                      str1[i] =  DevelopedSpreadSheet.getCellStringData(col, row, theCell);
                  }
                  else
                  {
                      num[i] = DevelopedSpreadSheet.getCellNumaricalData(col, row, theCell);
                  }
              }

              if (str1[0] != null)
              {
                 resultStr = str1[0];
                 for (int i = 1; i <= j ; i++)
                 {
                     resultStr = resultStr + str1[i];     
                 }
              }
              else
              {
                  resultNumber = resultNumber + num[0];
                  for (int i = 1; i<=j; i++)
                  {
                      int k = i-1;
                      if (token[k] == '+')
                      {
                          resultNumber = resultNumber + num[i];
                      }
                      if (token[k] == '-')
                      {
                          resultNumber = resultNumber - num[i];
                      }
                      if (token[k] == '*')
                      {
                          resultNumber = resultNumber * num[i];
                      }
                      if (token[k] == '/')
                      {
                          resultNumber = resultNumber / num[i];
                      }
                  }
              }
          }
          else
          {
             resultStr = str;
          }
      }
      else
      {
          resultNumber = number;
      }
   }

   public void ShowNumaric()
   {
       System.out.println(cellName + " : " + resultNumber);
       resultNumber = 0;
   }
   public void ShowString()
   {
       System.out.println(cellName + " : " + resultStr);
   }
}
