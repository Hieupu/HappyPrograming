/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author MSI
 */
public class MentorSkill {
    private String SkillId;
    private int mentorId; 

    public MentorSkill() {
    }

    public MentorSkill(String SkillId, int mentorId) {
        this.SkillId = SkillId;
        this.mentorId = mentorId;
    }

    public String getSkillId() {
        return SkillId;
    }

    public void setSkillId(String SkillId) {
        this.SkillId = SkillId;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }
    
}

