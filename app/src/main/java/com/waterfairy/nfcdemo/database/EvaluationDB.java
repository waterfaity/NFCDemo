package com.waterfairy.nfcdemo.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.PipedReader;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by water_fairy on 2017/7/19.
 * 995637517@qq.com
 */

@Entity
public class EvaluationDB {

    public static int SUBJECT_ENGLISH = 1;
    public static int SUBJECT_CHINESE = 2;
    public static int SUBJECT_MATH = 3;

    public static final int TYPE_LISTEN = 1;
    public static final int TYPE_READ_ALOUD = 2;
    public static final int TYPE_READ = 3;
    public static final int TYPE_SPEAK = 4;

    @Id(autoincrement = true )
    private Long id;
    private long teacherId;
    private long studentId;
    private int score;
    private int subject;
    private int type;
    private long time;
    @Generated(hash = 471752464)
    public EvaluationDB(Long id, long teacherId, long studentId, int score,
            int subject, int type, long time) {
        this.id = id;
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.score = score;
        this.subject = subject;
        this.type = type;
        this.time = time;
    }
    @Generated(hash = 2012657269)
    public EvaluationDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getTeacherId() {
        return this.teacherId;
    }
    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }
    public long getStudentId() {
        return this.studentId;
    }
    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
    public int getScore() {
        return this.score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getSubject() {
        return this.subject;
    }
    public void setSubject(int subject) {
        this.subject = subject;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }

}
