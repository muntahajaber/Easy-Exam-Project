/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.io.Serializable;

public abstract class Question implements Serializable {
protected String text;
protected String qID;
double pGrade;

public Question(String text,String qID, double pGrade){
this.text=text;
this.qID=qID;
this.pGrade=pGrade;
}

public Question(Question q){
this.text=q.text;
this.qID=q.qID;
this.pGrade=q.pGrade;
}

public abstract String formattedQ();

public abstract String formattedQwithA();

public String getText(){
return text;}

public void setText(String text){
this.text=text;}

public String getqID(){
return qID;}

public void setqID(String qID){
this.qID=qID;
}

public void setGrade(double pGrade){
this.pGrade=pGrade;
}

public double getpGrade(){
return pGrade;}



}     

