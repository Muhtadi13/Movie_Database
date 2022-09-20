package util;

import java.io.Serializable;

public class AdderUtil implements Serializable {

    private String prodCompany;

    private  Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getProdCompany() {
        return prodCompany;
    }

    public void setProdCompany(String prodCompany) {
        this.prodCompany = prodCompany;
    }
}
