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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.lang.RuntimeException;



public class TestApp {
	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.print("Enter course name: ");
		String cName = input.next();
		// if cName.ser is not found then qBank is empty
		ExamManager examManager = new ExamManager(cName);

		int num;
		do {
			System.out.println("\n1. Add a new Multible Choice question" + " \n2. Add a new True or False question"
					+ " \n3. Add a new Fill a Blank  question" + "\n4. Generate Random Exam "
					+ "\n5. Generate Chapter Exam" + "\n6. Delete Chapter Questions " + "\n7. Save questions and Exit");
			System.out.println("ENTER Your Choice:");
			num = input.nextInt();

			Question q = null;
			switch (num) {
			case 1:
				try{ // ask user to enter MCQ info
                                    
                                
				System.out.print(" Enter Question ID use the following format chapter name_q#");
				String id = input.next();
                                int find=id.indexOf("_");
                                if(find==-1)
                                    throw new RongIDException("Rong ID format Enter as The following Format(ch1_2)");
				System.out.print("Entere Question Text");
				String text = input.next();
				System.out.print("Entere Possiable grade");
				double pg = input.nextDouble();
				System.out.print("Entere Choices");
				String answers[] = new String[4];
				for (int i = 0; i < 4; i++) {
					System.out.print("choice #" + (i + 1));
					answers[i] = input.next();
				}
				System.out.print("Entere index of correct answers");
				int cAnswer = input.nextInt();
				q = new MCQ(text, id, pg, cAnswer, answers);

				if (examManager.addNewQuestion(q))
					System.out.print("Done");
				else
					System.out.print("No more place or question already exist");
				// String answers []= {"We cannot override private methods","We cannot override
				// protected methods","We cannot override private methods", "None"};
				// q=new MCQ("Which of the following is true about inheritance in Java?\n",
				// "ch04_1", 1.0,0,answers);
				examManager.addNewQuestion(q);
                                
				break;
                               
                                }catch(InputMismatchException ex){
                                System.out.println("The input is inccorrect");
                                }catch(RongIDException ex ){
                                System.out.println(ex.getMessage());}
			case 2:
				// ask user to enter T/FQ info
				System.out.print(" Enter Question ID use the following format chapter name_q#");
				String id1 = input.next();
				System.out.print("Entere Question Text");
				String text1 = input.next();
				System.out.print("Entere Possiable grade");
				double pg1 = input.nextDouble();
				System.out.print("Entere Correct Answer");
				boolean answer1 = input.nextBoolean();
				q = new TrueFalseQ(text1, id1, pg1, answer1);
				if (examManager.addNewQuestion(q))
					System.out.print("Done");
				else
					System.out.print("No more place or question already exist");

				break;
			case 3:
				// ask user to enter Fill Blank info
				System.out.print(" Enter Question ID use the following format chapter name_q#");
				String id2 = input.next();
				System.out.print("Entere Question Text");
				String text2 = input.next();
				System.out.print("Entere Possiable grade");
				double pg2 = input.nextDouble();
				System.out.print("Entere Correct Answer");
				String answer2 = input.next();
				q = new FillBlankQ(text2, id2, pg2, answer2);
				q=new FillBlankQ ("We cannot override ------------- methods?\n", "ch04_3",
				 1.0,"private");
				 examManager.addNewQuestion(q);
				break;
			case 4:
				System.out.print(" Enter number of questions");
				int n = input.nextInt();
				System.out.print("Entere Exam Header ");
				String header = input.next();
				double total = 0;
				try {
					total = examManager.generateExam(header, n, "", false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.print("Error while generating Exam");

					e.printStackTrace();
				}

				System.out.print("Exam sucessufly geneated and the total is" + total);

				break;
			case 5:
				System.out.print(" Enter number of questions");
				int n1 = input.nextInt();
				System.out.print("Entere Exam Header and chapter name ");
				String header1 = input.next();
				String chapterName = input.next();
				double total1 = 0;
				try {
					total1 = examManager.generateExam(header1, n1, chapterName, false);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.print("Error while generating Exam");
					e.printStackTrace();
				}

				System.out.print("Exam sucessufly geneated and the total is" + total1);
				break;

			case 6:
				System.out.print("Entere chapter name ");
				String chapterName1 = input.next();
				examManager.removeChapterQuestions(chapterName1);

			}// end switch
		} while (num != 7);
		System.out.print("Thank you for using our application, all changes will be saved for next session");

		try {
			examManager.exportQBank();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("error while saving questions of this course");
		}

	}

}

