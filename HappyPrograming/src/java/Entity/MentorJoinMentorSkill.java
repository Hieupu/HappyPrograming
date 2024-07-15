/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author MSI
 */
public class MentorJoinMentorSkill {
    private int id;
    private String name;
    private String ava;
    private String job;
    private String intro;
    private String achivement;
    private String skillid;

    public MentorJoinMentorSkill() {
    }

    public MentorJoinMentorSkill(int id,String name, String ava, String job, String intro, String achivement, String skillid) {
        this.id = id;
        this.name = name;
        this.ava = ava;
        this.job = job;
        this.intro = intro;
        this.achivement = achivement;
        this.skillid = skillid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAva() {
        return ava;
    }

    public void setAva(String ava) {
        this.ava = ava;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAchivement() {
        return achivement;
    }

    public void setAchivement(String achivement) {
        this.achivement = achivement;
    }

    public String getSkillid() {
        return skillid;
    }

    public void setSkillid(String skillid) {
        this.skillid = skillid;
    }
    
    
}
