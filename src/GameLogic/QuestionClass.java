package GameLogic;

import java.util.Arrays;
import java.util.HashSet;

public class QuestionClass {

    /*
     * get question header will return the title of the question to be displayed by the GUI.
     */
    public static String[] getQuestionHeader() throws ClassNotFoundException {
        ;
        int[] result = new int[3];
        HashSet<Integer> used = new HashSet<Integer>();
        for (int i = 0; i < 3; i++) {
            int add = (int) (Math.random() * 5);
            while (used.contains(add)) {
                add = (int) (Math.random() * 5);
            }
            used.add(add);
            result[i] = add;
        }
        String[] questions = new String[3];
        for (int i = 0; i < 3; i++) {
            int questionId = result[i];
            questions[i] = localQuestionBase.questionBase().get(questionId);
        }
        return questions;
    }

    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println(Arrays.toString(getQuestionHeader()));
    }
}
