package com.wy.shop.shejimoshi;

/**
 * describe:
 *
 * @author wangyu
 * @date 2019/4/19
 * @time 9:36
 */
public class Student  implements Cloneable {

    private String uid;

    private String  name;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Student(String uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
