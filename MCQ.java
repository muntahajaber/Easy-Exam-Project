/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.io.Serializable;
public class MCQ extends Question implements Serializable{

   private int correctAnswer;
   private String[] choices;

   public MCQ(String text, String qID, double pGrade, int correctAnswer, String[] choices){
      super (text, qID, pGrade);
      this.correctAnswer = correctAnswer;
      this.choices = choices;
   }

   public MCQ(Question q){
   
         this(q.text, q.qID, q.pGrade, ((MCQ) q).correctAnswer, ((MCQ) q).choices);   
   }

   
   public String formattedQ(){
   
    String question = String.format("Choose the correct answer:%n%s:%s%n", this.qID, this.text);

        for (int i = 0; i < choices.length; i++) {

            question = question.concat((i + 1) + ":".concat(choices[i]).concat("\n"));

        }
        return question;}
   

   public String formattedQwithA(){
   
      return String.format("%sCorrect Answer is: %s%n",this.formattedQ(),this.choices[correctAnswer]); // change array
   
   
   }
   
   public void setCorrectAnswer( int correctAnswer){ // add set
   this.correctAnswer=correctAnswer;}
   
   public int  getCorrectAnswer( ){ // add get
   return correctAnswer;}
   
   public void setChoices(String [] choices){ // add set
   this.choices=choices;}
   
   public String [] getChoices(String [] choices){ // add get
   return choices;}
   
   


}
