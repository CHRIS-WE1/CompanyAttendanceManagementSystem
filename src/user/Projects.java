package user;

import java.io.Serializable;

public class Projects implements Serializable {

    private static final long serialVersionUID = 2146795999121977778L;
    private String name;
    private Double point;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void PrintMes(){
        System.out.println(" 名称："+name+" 类型："+type+" 分值："+point);
    }

}

