import java.util.Scanner;
import java.io.*;
import java.text.*;

class Main{

  public static void main(String[] args)  {
    driverExam test = new driverExam();

    test.userAnswers();
    test.checkUserAnswers();
    boolean testPassed=test.passed();
    test.printResults(testPassed);  
  }
}

class driverExam
{
  char answer;
  int correct = 0;
  double percentCorrect;

  char [] questionAnswers= new char[20];

  public void userAnswers () 
  { 
    int question = 1;
    while(question<=20)
    {
      System.out.println("What is your answer for question " + question + ": ");

      Scanner input = new Scanner(System.in);

      answer = input.next().charAt(0);

      if(answer != 'A' && answer != 'B' && answer != 'C' && answer != 'D')
      {
        System.out.println("Invalid input");
      }else{
        questionAnswers[question-1] = answer;
        question++;
      }
    }
    
  }

  public void checkUserAnswers()
  {
     try
      {
        Scanner reader = new Scanner(new FileInputStream("dmvanswers.txt"));
        while(reader.hasNext())
        {
          for(int i = 0; i < questionAnswers.length; i++)
          {
          String s = reader.next();
          char c = s.charAt(0);
          if(questionAnswers[i] == c)
          {
            correct++;
          }
          }
        }
      }
      catch(FileNotFoundException e)
        {
          System.out.println("\n Oops! File not found");
        }
    System.out.print("You have this many correct: " + correct + "\n");
    percentCorrect =  ((correct)*100)/(20);
    System.out.print("You got " + percentCorrect + "% on your test! \n");
  }

  public boolean passed ()
  {
    if(correct > 14 || percentCorrect > 75.0)
    {
      System.out.println("You passed!");
      return true;
    }else
    {
      System.out.println("You failed!");
      return false;
    }
  }

  public void printResults(boolean testPassed)
  {
      PrintWriter myFileOut; 
      
      try
      {
        myFileOut = new PrintWriter("report.txt" );
        if(testPassed)
        {
          myFileOut.println("Student passed exam!");
        }else
        {
          myFileOut.println("Student failed exam!");
        }
        myFileOut.println("Student answered " + correct + " questions correctly.");
        myFileOut.println("Student received a " + percentCorrect + "%");
        myFileOut.close();
     
      }
      catch(FileNotFoundException e)
      {
        System.out.println("\n Oops! File not found");
      } 
      
  }

}