import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

class QuizCard{
    private String question;
    private String answer;

    public QuizCard(String q, String a){
        question = q;
        answer = a;
    }
    public String getQuestion(){
        return question;
    }
    public String getAnswer(){
        return answer;
    }
}