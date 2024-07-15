/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Entity.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author MSI
 */
public class AccountDBContext extends DBContext<Account>{
    
   public int getAccountIdByMentorId(int mentorId) {
        String query = "SELECT accid FROM mentor JOIN Account ON mentor.accid = Account.id WHERE mentor.id = ?";
        int accid = -1;

         try (PreparedStatement stm = connection.prepareStatement(query)) {
            stm.setInt(1, mentorId);
            ResultSet resultSet = stm.executeQuery();

            if (resultSet.next()) {
                accid = resultSet.getInt("accid");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accid;
    }
    public static void main(String[] args) throws SQLException {
        AccountDBContext x = new AccountDBContext();
        int  n = x.getAccountIdByMentorId(1135);
        System.out.println(n);
    }
}
