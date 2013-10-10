package com.qsoft.BaseUI;

/**
 * User: binhtv
 * Date: 10/10/13
 * Time: 9:10 AM
 */
public class Friend
{
    private int name;
    private int phone;

    public Friend(int name, int phone)
    {
        this.name = name;
        this.phone = phone;
    }
    public int getName()
    {
        return name;
    }

    public void setName(int name)
    {
        this.name = name;
    }

    public int getPhone()
    {
        return phone;
    }

    public void setPhone(int phone)
    {
        this.phone = phone;
    }
}
