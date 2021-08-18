/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dto;

/**
 *
 * @author tasmi
 */
public class UserDTO {
    private String userid;
    private String pwd;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public UserDTO(String userid, String pwd) {
        this.userid = userid;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "userid=" + userid + ", pwd=" + pwd + '}';
    }
    
    
    
}
