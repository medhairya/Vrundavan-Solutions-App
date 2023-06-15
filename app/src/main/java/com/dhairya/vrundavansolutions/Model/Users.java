package com.dhairya.vrundavansolutions.Model;

public class Users {

    public Users() {}

    private String Name, Password, Phone, Shop_Name, Shop_Address;

    public Users(String name, String password, String phone, String shop_Name, String shop_Address) {
        Name = name;
        Password = password;
        Phone = phone;
        Shop_Name = shop_Name;
        Shop_Address = shop_Address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getShop_Name() {
        return Shop_Name;
    }

    public void setShop_Name(String shop_Name) {
        Shop_Name = shop_Name;
    }

    public String getShop_Address() {
        return Shop_Address;
    }

    public void setShop_Address(String shop_Address) {
        Shop_Address = shop_Address;
    }
}