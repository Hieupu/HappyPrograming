/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author MSI
 */
public class Request {
    
    int id;
    String title;
    int fee;
    int mentorid;
    String time;
    int menteeid;
    String status;
    String sche;
    String detail;
    String skillid;

    public String getSkillid() {
        return skillid;
    }

    public void setSkillid(String skillid) {
        this.skillid = skillid;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getMentorid() {
        return mentorid;
    }

    public void setMentorid(int mentorid) {
        this.mentorid = mentorid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMenteeid() {
        return menteeid;
    }

    public void setMenteeid(int menteeid) {
        this.menteeid = menteeid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSche() {
        return sche;
    }

    public void setSche(String sche) {
        this.sche = sche;
    }

    public Request() {
    }

    public Request(int id, String title, int fee, int mentorid, String time, int menteeid, String status, String sche) {
        this.id = id;
        this.title = title;
        this.fee = fee;
        this.mentorid = mentorid;
        this.time = time;
        this.menteeid = menteeid;
        this.status = status;
        this.sche = sche;
    }
    
    
}
