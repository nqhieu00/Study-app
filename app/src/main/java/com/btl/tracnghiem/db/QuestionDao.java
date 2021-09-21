package com.btl.tracnghiem.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.btl.tracnghiem.model.Exam;
import com.btl.tracnghiem.model.Question;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.security.auth.Subject;

public class QuestionDao {
    private DataBaseHelper dbHelper;
    public QuestionDao(Context context){
       try {
            dbHelper=new DataBaseHelper(context);
       } catch (IOException e) {
           e.printStackTrace();
       }

    }
    public static final String TABLE_QUESTION="Question";
    public static final String KEY_QUESTION_ID="_id";
    public static final String KEY_QUESTION_NUMEXAM="num_exam";
    public static final String KEY_QUESTION_SUBJECT="subject";
    public static final String KEY_QUESTION_QUESTION="question";
    public static final String KEY_QUESTION_ANSA="ans_a";
    public static final String KEY_QUESTION_ANSB="ans_b";
    public static final String KEY_QUESTION_ANSC="ans_c";
    public static final String KEY_QUESTION_ANSD="ans_d";
    public static final String KEY_QUESTION_RESULT="result";
    public static final String KEY_QUESTION_IMAGE="image";
    public static final String KEY_QUESTION_ANSWER="answer";


    public ArrayList<Question> getQuestion(String subject,int numExam) {
        ArrayList<Question> questionArrayList=new ArrayList<>();
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        String selectQuery=String.format("SELECT * FROM %s WHERE %s like '%s' and %s = %d",
                TABLE_QUESTION,KEY_QUESTION_SUBJECT,subject,KEY_QUESTION_NUMEXAM,numExam);
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                Question question=new Question();
                question.set_id(cursor.getInt(0));
                question.setNumExam(cursor.getInt(1));
                question.setSubject(cursor.getString(2));
                question.setQuestion(cursor.getString(3));
                question.setAns_a(cursor.getString(4));
                question.setAns_b(cursor.getString(5));
                question.setAns_c(cursor.getString(6));
                question.setAns_d(cursor.getString(7));
                question.setResult(cursor.getString(8));
                question.setImage(cursor.getString(9));
                question.setAnswer(cursor.getString(10));
                questionArrayList.add(question);
            }
            while (cursor.moveToNext());


        }
        dbHelper.close();

        return questionArrayList;
    }
    public int getScores(int numExam,String subject){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery=String.format("SELECT COUNT(*) FROM %s WHERE %s like '%s' and %s = %d and %s like %s",
                TABLE_QUESTION,KEY_QUESTION_SUBJECT,subject,KEY_QUESTION_NUMEXAM,numExam,KEY_QUESTION_RESULT,KEY_QUESTION_ANSWER);
        Cursor cursor=db.rawQuery(selectQuery,null);
        while (cursor.moveToNext()){
            return cursor.getInt(0);
        }
        return 0;
    }
    public ArrayList<Exam> getExam(String subject){
        ArrayList<Exam> examArrayList=new ArrayList<>();
        String Query="SELECT "+KEY_QUESTION_NUMEXAM +" FROM "+ TABLE_QUESTION+" WHERE "+ KEY_QUESTION_SUBJECT +" LIKE '"+subject+"'" +
                " GROUP BY " + KEY_QUESTION_NUMEXAM;
        Cursor cursor=dbHelper.getReadableDatabase().rawQuery(Query, null);
        while (cursor.moveToNext()){
            Exam exam=new Exam(cursor.getString(0));
            examArrayList.add(exam);
        }
        dbHelper.close();
        return examArrayList;
    }
}
