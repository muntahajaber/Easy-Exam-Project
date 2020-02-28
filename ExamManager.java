/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;


public class ExamManager{// start of ExamManager
private QuestionBank qBank;

public ExamManager (String courseName){

qBank = new QuestionBank(courseName);


String Fname = courseName.toLowerCase() + ".ser";
File f = new File(Fname);
if (!f.exists()) {
 System.out.println("not found");
 return;}
 
 try {// error handling here becouse there is no handling in main 
 
 qBank.loadQuestions(Fname);
} catch (FileNotFoundException ex) {

System.err.println("error: " + ex.getMessage());

} catch (IOException ex) {

System.err.println("error: " + ex.getMessage());

} catch (ClassNotFoundException ex) {

System.err.println("error: " + ex.getMessage());}
}// end of ExamManager





public boolean addNewQuestion(Question q) {// start of addNewQuestion

return qBank.addQuestion(q);

}// end of addNewQuestion



public void removeChapterQuestions(String chapterName) {// start of removeChapterQuestions
Question[] qu = qBank.getqList();
int s = qBank.countChapterQuestions(chapterName);
String[] Ids = new String[s];

chapterName=chapterName.toLowerCase();

for (int i= 0, j=0; i < qu.length && j<s ; i++) {

if (qu[i] != null) {

if (qu[i].getqID().substring(0, qu[i].getqID().indexOf('_')).toLowerCase().equals(chapterName)) {

Ids[j++] = qu[i].getqID();}
}}
qBank.removeAllQuestion(Ids); 

}// end of removeChapterQuestions







public double generateExam (String header, int n, String chapterName, boolean withAnswers ) throws FileNotFoundException {// start of generateExam

        String fileName = qBank.getCourseName().toLowerCase() + "Exam.txt";
        PrintWriter w = new PrintWriter(new FileOutputStream(fileName));
        double grades = 0;
        try {
                        w.println(header);
            w.println();
            Question[] random ;
            
            if (chapterName.equals("")){
            random = qBank.getRandomQuestions(n); }
            
            else random = qBank.getChapterQuestions(n, chapterName);

       for (int i=0;i<random.length;i++) {

                grades += random[i].getpGrade();
                if (withAnswers) {

                    w.println(random[i].formattedQwithA());
                } else {
                              
                    w.println(random[i].formattedQ());
                }
                w.println();

            }

        } finally {
            w.close();
        }

        return grades;
    }//end of generateExam

    public void exportQBank() throws IOException {//start

        qBank.saveQuestions();
    }//end 
}