/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.MentorAccount;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetInforMentorDBContext extends DBContext<MentorAccount> {
    
    public MentorAccount getmentorById(int id) throws SQLException {
        MentorAccount m = new MentorAccount();
        String query = "SELECT mentor.id, mentor.ava, account.fullname, Account.address, account.dob,account.phone, Account.mail, mentor.job, mentor.intro, mentor.achivement, mentor.accid "
                + " FROM account"
                + " JOIN mentor ON mentor.accId = account.id "
                + "WHERE mentor.id = ?";
        try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    m.setId(rs.getInt("id"));
                    m.setAva(rs.getString("ava"));
                    m.setFullName(rs.getString("fullname"));
                    m.setAddress(rs.getString("address"));
                    m.setDob(rs.getString("dob"));
                    m.setPhone(rs.getString("phone"));
                    m.setMail(rs.getString("mail"));
                    m.setJob(rs.getString("job"));
                    m.setIntro(rs.getString("intro"));
                    m.setAchivement(rs.getString("achivement"));
                    m.setAccid(rs.getInt("accid"));
                }
                
            }
            return m;
        }
    }
    
    public List<MentorAccount> getAllMentor() throws SQLException {
        ArrayList<MentorAccount> list = new ArrayList<>();
        String query = """
                       SELECT mentor.accid, mentor.ava, mentor.job, account.fullname, account.dob, mentor.intro , mentor.achivement
                                              FROM account
                                              JOIN mentor ON mentor.accId = account.id
                                              WHERE mentor.status = 'Accepted'""";
        try (PreparedStatement stm = connection.prepareStatement(query); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                MentorAccount s = new MentorAccount();
                s.setId(rs.getInt("accid"));
                s.setAva(rs.getString("ava"));
                s.setJob(rs.getString("job"));
                s.setFullName(rs.getString("fullName"));
                s.setDob(rs.getString("dob"));
                s.setIntro(rs.getString("intro"));
                s.setAchivement(rs.getString("achivement"));
                list.add(s);
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching skills from the database", e);
        }
        return list;
    }
    
    public MentorAccount getmentorByAccId(int id) throws SQLException {
        MentorAccount m = new MentorAccount();
        String query = "SELECT mentor.id, mentor.ava, account.fullname, Account.address, account.dob,account.phone, Account.mail, mentor.job, mentor.intro, mentor.achivement "
                + " FROM account"
                + " JOIN mentor ON mentor.accId = account.id "
                + "WHERE mentor.accId = ?";
        try (java.sql.Connection conn = connection; PreparedStatement stm = conn.prepareStatement(query)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    m.setId(rs.getInt("id"));
                    m.setAva(rs.getString("ava"));
                    m.setFullName(rs.getString("fullname"));
                    m.setAddress(rs.getString("address"));
                    m.setDob(rs.getString("dob"));
                    m.setPhone(rs.getString("phone"));
                    m.setMail(rs.getString("mail"));
                    m.setJob(rs.getString("job"));
                    m.setIntro(rs.getString("intro"));
                    m.setAchivement(rs.getString("achivement"));
                }
                
            }
            return m;
        }
    }
    
    public static void main(String[] args) throws SQLException {
        GetInforMentorDBContext db = new GetInforMentorDBContext();
        MentorAccount x = db.getmentorById(1123);
        System.out.println(x.getAva());
        
    }
    
}
