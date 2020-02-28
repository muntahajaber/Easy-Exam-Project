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
public class FillBlankQ extends Question{  //implements Serializable{

   private String correctAnswer;

   public FillBlankQ(String text, String qID, double pGrade, String correctAnswer){
      super (text, qID, pGrade);
      this.correctAnswer = correctAnswer;
   }

   public  FillBlankQ(Question q){//ctor
    
       this(q.text, q.qID, q.pGrade, ((FillBlankQ) q).correctAnswer);
   
   } 


   public String formattedQ(){
     return String.format("Fill the follwing blank:%n%s:%s%n",this.qID,this.text);
   }
 
   public String formattedQwithA(){
   
     return String.format("%sCorrect Answer is: %s%n",formattedQ(),correctAnswer);
   
   }
   public void setCorrectAnswer(String correctAnswer){
   this.correctAnswer = correctAnswer;}
   
    public String getCorrectAnswer(){
   return correctAnswer;}

} 

