/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Mentor extends Account{
    private String id;
    private String ava;
    private String job;
    private String intro;
    private ArrayList<Skill> skill;
    private ArrayList<Rating> rate;
    private String achive;

    /**
     *
     * @param id
     * @param ava
     * @param job
     * @param intro
     * @param skill
     * @param rate
     * @param achive
     */
    public Mentor(String id, String ava, String job, String intro, ArrayList<Skill> skill, ArrayList<Rating> rate, String achive) {
        this.id = id;
        this.ava = ava;
        this.job = job;
        this.intro = intro;
        this.skill = skill;
        this.rate = rate;
        this.achive = achive;
    }
    
    
}
