/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Admin
 */
public class Mentee extends Account{
    private String ava;
    private int accid;
    public String getAva() {
        return ava;
    }
    public void setAva(String ava) {
        this.ava = ava;
    }

    public Mentee() {
    }

    public Mentee(String ava, int accid) {
        this.ava = ava;
        this.accid = accid;
    }

    
}
