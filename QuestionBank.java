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
import java.io.*;
public class QuestionBank{
private String courseName;
private int nOfQ;
private Question[] qList;


public QuestionBank(String courseName){
this.courseName=courseName;
nOfQ=0;
qList =new Question[100];
}


public String getCourseName(){
return courseName;
}
public void setCourseName(String courseName){
this.courseName=courseName;}

public Question[] getqList(){
return qList;
}
public void setqList(Question[] qList){
this.qList=qList;
}



public int getnOfQ(){
return nOfQ;
}

public void setnOfQ(int nOfQ){
this.nOfQ=nOfQ;}



public int findQuestion(Question q){// satrt of findQuestion*
int i=0;
for(i=0;i<nOfQ;i++){ 
if(qList[i].getText().equals(q.getText())){// add to lower case
return i;}

if(qList[i].getqID().equals(q.getqID())){
return i;
}
}
return -1;
}// end of findQuestion




public boolean addQuestion(Question q){// start of addQuestion
if(nOfQ==qList.length){// added
return false;
}
if(findQuestion(q) == -1){
if( q instanceof FillBlankQ)
qList[nOfQ++]=new FillBlankQ((FillBlankQ)q);
if(q instanceof MCQ)
qList[nOfQ++]=new MCQ((MCQ)q);
if(q instanceof TrueFalseQ)
qList[nOfQ++]=new TrueFalseQ((TrueFalseQ)q);
return true;}

return false;

}// end of addQuestion


public Question[] getRandomQuestions(int n) {

        if (n <= 0 || n > nOfQ) {

            throw new IllegalArgumentException("The required number of questions is invalid ");
        }

        Question[] randomQuestions = new Question[n];

        /*
         
         1- loop   n  times
         2- generate random index in the correct range (0 to nOfQ-1)  --> no duplicates
         3- pick up the question   qList[randomIndex] and store it in the randomQuestion array
         */
        int min = 0;
        int max = nOfQ - 1;
        int[] indices = new int[n];
        int counter = 0;
        boolean repeate = true;
        for (int i = 0; i < n; i++) {
            int randomIndex = 0;//dummy value
            while (repeate) {
                randomIndex = min + (int) (Math.random() * (max - min + 1));
                boolean isFound = false;
                for (int j = 0; j < counter; j++) {

                    if (indices[j] == randomIndex) {
                        isFound = true;
                        break;
                    }

                }//end for

                if (!isFound) {

                    indices[counter++] = randomIndex;
                    repeate = false;

                }

            }

            repeate = true;//reset 

            /* randomQuestions[i] = qList[randomIndex]; //--> aggregation */
            if (qList[randomIndex] instanceof FillBlankQ) {

                randomQuestions[i] = new FillBlankQ(qList[randomIndex]);
            } else if (qList[randomIndex] instanceof MCQ) {

                randomQuestions[i] = new MCQ(qList[randomIndex]);
            } else {
                randomQuestions[i] = new TrueFalseQ(qList[randomIndex]);
            }

        }//end for

        return randomQuestions;
    }//end getRandomQuestions


public int countChapterQuestions(String cName){//start of countChapterQuestion**
 cName = cName.toLowerCase();

        int num = 0;
        for (int i = 0; i < nOfQ; i++) {
            String ID = qList[i].getqID(); 
String ch = ID.substring(0, ID.indexOf('_')).toLowerCase();

            if (ch.equals(cName)) {
                num++;
            }
        }
        return num;
}


public void removeAllQuestion(String ids[]){// start of removeAllQuestion
  
        for (String id : ids) {

            for (int i = 0; i < nOfQ; i++) {

                if (qList[i].qID.toLowerCase().equals(id.toLowerCase())) {

                    for (int j = i; j < nOfQ - 1; j++) {

                        qList[j] = qList[j + 1];
                    }
                    qList[--nOfQ] = null;
                    break; 
                }

            }
        }
    } //end of removeAllQuestion



/*for(int i=0;i<nOfQ;i++)
if(ids[i].equals(qList[i].getqID()))
ids[i]=ids[i+1];
ids[--nOfQ]=null;*/

//





public int loadQuestions(String fname)throws IOException , FileNotFoundException,  ClassNotFoundException{// start of loadQuestions

  if (!(fname.substring(0, fname.indexOf('.')).toLowerCase().equals(courseName.toLowerCase()))) {

            throw new IllegalArgumentException("File Name does't match the course !");
        }

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fname));

        int num = 0;
        while (true) {

            try {

                addQuestion((Question) ois.readObject());
                num++;

            } catch (EOFException ex) {

                System.out.println(num + " questions have been loadded successfully !");
               
                break;

            }
        }

        ois.close();
        return num;

    }// end  of loadQuestions






public void saveQuestions() throws IOException, FileNotFoundException  {// start of saveQuestions

 ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(courseName.toLowerCase() + ".txt"));

try {
for (int i=0;i<nOfQ;i++)
ostream.writeObject(qList[i].formattedQwithA());
}finally{
ostream.close();
}
System.out.println(nOfQ+" questions saved successfully :)!");//
} // end of saveQuestions





public Question[] getChapterQuestions(int n, String cName) {//start of  getChapterQuestions
   
      
      int counter = countChapterQuestions(cName);// or  
     /* for (int i = 0; i < nOfQ; i++) {
      
         if (qList[i].qID.substring(0, qList[i].qID.indexOf('_')).toLowerCase().equals(cName.toLowerCase())) {
         
            count++;
         }
      
      }*/
   
     
      int size = 0;
      
      if(counter < n)
         size=counter;
      else 
         size=n;
      
      Question[] arrayQuestions = new Question[size];
   
      for (int i = 0, j = 0; i < nOfQ && j<size ; i++) {
      
         if (qList[i].qID.substring(0, qList[i].qID.indexOf('_')).toLowerCase().equals(cName.toLowerCase())) {
         
            
            if (qList[i] instanceof FillBlankQ) {
            
               arrayQuestions[j++] = new FillBlankQ(qList[i]);}
            else if (qList[i] instanceof MCQ) {
            
               arrayQuestions[j++] = new MCQ(qList[i]);}
            else {
               arrayQuestions[j++] = new TrueFalseQ(qList[i]);
            }
         
         }
      }
   
      return arrayQuestions;
   }//end  of  getChapterQuestions

}