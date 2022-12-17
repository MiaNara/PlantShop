/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicObj;

import dbaccess.accountDao;
import java.util.ArrayList;



/**
 *
 * @author lthut
 */
public class testConnection {
    public static void main(String[] args) throws Exception {
        //insert account
//        accountDao.insertAccount("trang2@gmail.com", "123456", "trang", "012345678", 1,0);
        //update Status;
//          accountDao.updateAccountStatus("admin@gmail.com", 1); 
        
//             get All accounts
        ArrayList<Account> acc = new ArrayList();
              acc =  accountDao.getAllAcounts();
        if(!acc.isEmpty()){
            for(Account account : acc){
                System.out.println(account.getAccid() +" "+ account.getEmail()+" "+ account.getFullname()+" "+account.getPhone()+" "+ account.getStatus()+" "+ account.getRole());
            };
        }
        else System.out.println("Login fail");

           
}
}
