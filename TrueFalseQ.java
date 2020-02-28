/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

/**
 *
 * @author shammaaas
 */
public class TrueFalseQ extends Question{

private boolean correctAnswer;

public TrueFalseQ(String text, String qID, double pGrade, boolean correctAnswer){
super(text,qID,pGrade);

this.correctAnswer=correctAnswer;

}


public  TrueFalseQ(Question q){// ctor
 this(q.text, q.qID, q.pGrade, ((TrueFalseQ) q).correctAnswer);
}



public String formattedQ(){

return String.format("True or False:%n%s:%s%n",this.qID,this.text);

}
 
public String formattedQwithA(){
return String.format("%sCorrect Answer is: %s%n",formattedQ(),correctAnswer);

}

public void setCorrectAnswer(boolean correctAnswer){
   this.correctAnswer = correctAnswer;}
   
    public boolean getCorrectAnswer(){
   return correctAnswer;}


}



