package com.btl.tracnghiem.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    private int _id;
    private int numExam;
    private String subject;
    private String question;
    private String ans_a;
    private String ans_b;
    private String ans_c;
    private String ans_d;
    private String result;
    private String Image;
    private String answer;

    public Question(int _id, int numExam, String subject, String question, String ans_a, String ans_b, String ans_c, String ans_d, String result, String image,String answer) {
        this._id = _id;
        this.numExam = numExam;
        this.subject = subject;
        this.question = question;
        this.ans_a = ans_a;
        this.ans_b = ans_b;
        this.ans_c = ans_c;
        this.ans_d = ans_d;
        this.result = result;
        Image = image;
        this.answer=answer;
    }

    public Question() {
    }

    protected Question(Parcel in) {
        _id = in.readInt();
        numExam = in.readInt();
        subject = in.readString();
        question = in.readString();
        ans_a = in.readString();
        ans_b = in.readString();
        ans_c = in.readString();
        ans_d = in.readString();
        result = in.readString();
        Image = in.readString();
        answer = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getNumExam() {
        return numExam;
    }

    public void setNumExam(int numExam) {
        this.numExam = numExam;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAns_a() {
        return ans_a;
    }

    public void setAns_a(String ans_a) {
        this.ans_a = ans_a;
    }

    public String getAns_b() {
        return ans_b;
    }

    public void setAns_b(String ans_b) {
        this.ans_b = ans_b;
    }

    public String getAns_c() {
        return ans_c;
    }

    public void setAns_c(String ans_c) {
        this.ans_c = ans_c;
    }

    public String getAns_d() {
        return ans_d;
    }

    public void setAns_d(String ans_d) {
        this.ans_d = ans_d;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeInt(numExam);
        dest.writeString(subject);
        dest.writeString(question);
        dest.writeString(ans_a);
        dest.writeString(ans_b);
        dest.writeString(ans_c);
        dest.writeString(ans_d);
        dest.writeString(result);
        dest.writeString(Image);
        dest.writeString(answer);
    }
}
