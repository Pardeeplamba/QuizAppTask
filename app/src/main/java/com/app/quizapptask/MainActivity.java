package com.app.quizapptask;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD,next;
    Button submitBtn;

    int score=0;
    int totalQuestion = HardCodedQuizQuestions.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalQuestionsTextView=findViewById(R.id.totalquestions);;
        questionTextView=findViewById(R.id.question);
        submitBtn=findViewById(R.id.sumbit);
        ansA=findViewById(R.id.optiona);
        ansB=findViewById(R.id.optionb);
        ansC=findViewById(R.id.optionc);
        ansD=findViewById(R.id.optiond);
        next=findViewById(R.id.next);
submitBtn.setOnClickListener(this);
        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        next.setOnClickListener(this);
        loadNewQuestion();

    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.BLUE);
        ansB.setBackgroundColor(Color.BLUE);
        ansC.setBackgroundColor(Color.BLUE);
        ansD.setBackgroundColor(Color.BLUE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.sumbit){
            if(selectedAnswer.equals(HardCodedQuizQuestions.correctAnswers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();


        }else{
            //choices button clicked
            selectedAnswer  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);

        }
        if(clickedButton.getId()==R.id.next)
        {
            currentQuestionIndex++;
            loadNewQuestion();        }
    }

    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }

        questionTextView.setText(HardCodedQuizQuestions.question[currentQuestionIndex]);
        ansA.setText(HardCodedQuizQuestions.choices[currentQuestionIndex][0]);
        ansB.setText(HardCodedQuizQuestions.choices[currentQuestionIndex][1]);
        ansC.setText(HardCodedQuizQuestions.choices[currentQuestionIndex][2]);
        ansD.setText(HardCodedQuizQuestions.choices[currentQuestionIndex][3]);
        totalQuestionsTextView.setText(score+" out of "+totalQuestion);

    }

    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of "+ totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();


    }

    void restartQuiz(){
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
    }

}