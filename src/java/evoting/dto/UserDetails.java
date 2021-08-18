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
public class UserDetails {
    private String adahar_no;
    private String password;
    private String username;
    private String address;
    private String city;
    private String email;
    private long mobile_no;//table me 8 columns h but hm 7 members lenge qk usertype hmesha voter hi hoga ...admin ki entry hm direct DB krenge
    private String gender;

    public UserDetails() {
    }

    
    
    public UserDetails(String adahar_no, String password, String username, String address, String city, String email, long mobile_no, String gender) {
        this.adahar_no = adahar_no;
        this.password = password;
        this.username = username;
        this.address = address;
        this.city = city;
        this.email = email;
        this.mobile_no = mobile_no;
        this.gender = gender;
    }

    public String getAdahar_no() {
        return adahar_no;
    }

    public void setAdahar_no(String adahar_no) {
        this.adahar_no = adahar_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(long mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserDetails{" + "adahar_no=" + adahar_no + ", password=" + password + ", username=" + username + ", address=" + address + ", city=" + city + ", email=" + email + ", mobile_no=" + mobile_no + ", gender=" + gender + '}';
    }
    
}
