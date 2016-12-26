package com.skapp.lj.mates;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by rhinoPHS
 **/
public class UserClass {
    private String uid;
    private String name;
    private String email;
    private String photoUrl;
    private String type;
    private String gender;
    private String age;
    private String regionSi;
    private String regionGu;
    private String regionDong;
    private String QuestionOneAnswer;
    private String QuestionTwoAnswer;
    private String QuestionThreeAnswer;
    private String QuestionFourAnswer;
    private String QuestionFiveAnswer;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRegionSi() {
        return regionSi;
    }

    public void setRegionSi(String regionSi) {
        this.regionSi = regionSi;
    }

    public String getRegionGu() {
        return regionGu;
    }

    public void setRegionGu(String regionGu) {
        this.regionGu = regionGu;
    }

    public String getRegionDong() {
        return regionDong;
    }

    public void setRegionDong(String regionDong) {
        this.regionDong = regionDong;
    }

    public String getQuestionOneAnswer() {
        return QuestionOneAnswer;
    }

    public void setQuestionOneAnswer(String questionOneAnswer) {
        QuestionOneAnswer = questionOneAnswer;
    }

    public String getQuestionTwoAnswer() {
        return QuestionTwoAnswer;
    }

    public void setQuestionTwoAnswer(String questionTwoAnswer) {
        QuestionTwoAnswer = questionTwoAnswer;
    }

    public String getQuestionThreeAnswer() {
        return QuestionThreeAnswer;
    }

    public void setQuestionThreeAnswer(String questionThreeAnswer) {
        QuestionThreeAnswer = questionThreeAnswer;
    }

    public String getQuestionFourAnswer() {
        return QuestionFourAnswer;
    }

    public void setQuestionFourAnswer(String questionFourAnswer) {
        QuestionFourAnswer = questionFourAnswer;
    }

    public String getQuestionFiveAnswer() {
        return QuestionFiveAnswer;
    }

    public void setQuestionFiveAnswer(String questionFiveAnswer) {
        QuestionFiveAnswer = questionFiveAnswer;
    }

    public UserClass() {
    }

    public UserClass(String uid, String name, String email, String photoUrl, String type, String gender, String age, String regionSi, String regionGu, String regionDong, String questionOneAnswer, String questionTwoAnswer, String questionThreeAnswer, String questionFourAnswer, String questionFiveAnswer) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.photoUrl = photoUrl;
        this.type = type;
        this.gender = gender;
        this.age = age;
        this.regionSi = regionSi;
        this.regionGu = regionGu;
        this.regionDong = regionDong;
        QuestionOneAnswer = questionOneAnswer;
        QuestionTwoAnswer = questionTwoAnswer;
        QuestionThreeAnswer = questionThreeAnswer;
        QuestionFourAnswer = questionFourAnswer;
        QuestionFiveAnswer = questionFiveAnswer;
    }
    public void writeNewUser(String uid, String name, String email, String photoUrl, String type, String gender, String age, String regionSi, String regionGu, String regionDong, String questionOneAnswer, String questionTwoAnswer, String questionThreeAnswer, String questionFourAnswer, String questionFiveAnswer) {
        UserClass user = new UserClass(uid,name,email,photoUrl,type,gender,age,regionSi,regionGu,regionDong,questionOneAnswer,questionTwoAnswer,questionThreeAnswer,questionFourAnswer,questionFiveAnswer);
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(uid).setValue(user);
    }
}
