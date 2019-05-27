package cn.marve1ous.model;

import javax.validation.constraints.*;
import java.io.Serializable;

public class User implements Serializable {
    @NotNull
    @Size(min = 36, max = 36)
    private String uuid;
    @Min(100001)
    @Max(999999)
    private int id;
    @Size(min = 4, max = 20)
    private String name;
    @Size(min = 8, max = 16)
    private String pwd;

    public User(String uuid, int id, String name, String pwd) {
        this.uuid = uuid;
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid='" + uuid + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
