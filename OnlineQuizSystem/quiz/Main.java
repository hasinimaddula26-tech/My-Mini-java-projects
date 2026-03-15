package quiz;

import quiz.models.Question;
import quiz.services.QuizService;

public class Main {

    public static void main(String[] args) {

        Question[] questions = new Question[] {

            new Question(
                    "What is the size of int in Java?",
                    new String[]{"8 bit", "16 bit", "32 bit", "64 bit"},
                    "32 bit"
            ),

            new Question(
                    "Which method is entry point of Java program?",
                    new String[]{"start()", "main()", "run()", "init()"},
                    "main()"
            ),

            new Question(
                    "Which is NOT an access modifier?",
                    new String[]{"public", "private", "protected", "package"},
                    "package"
            )
        };

        QuizService quiz = new QuizService(questions);

        quiz.start();

        quiz.showResults();
    }
}