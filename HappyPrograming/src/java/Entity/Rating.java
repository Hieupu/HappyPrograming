/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Rating {
    private String id;
    private double point;
    private String cmt;
    private Mentor mentor;
    private Mentee mentee;
    private Date time;

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public double getPoint() {
        return point;
    }

    /**
     *
     * @param point
     */
    public void setPoint(double point) {
        this.point = point;
    }

    /**
     *
     * @return
     */
    public String getCmt() {
        return cmt;
    }

    /**
     *
     * @param cmt
     */
    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    /**
     *
     * @return
     */
    public Mentor getMentor() {
        return mentor;
    }

    /**
     *
     * @param mentor
     */
    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    /**
     *
     * @return
     */
    public Mentee getMentee() {
        return mentee;
    }

    /**
     *
     * @param mentee
     */
    public void setMentee(Mentee mentee) {
        this.mentee = mentee;
    }

    /**
     *
     * @return
     */
    public Date getTime() {
        return time;
    }

    /**
     *
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }
    
}
