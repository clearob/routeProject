
package com.route.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Languages {

    @SerializedName("nld")
    @Expose
    private String nld;
    @SerializedName("pap")
    @Expose
    private String pap;

    public String getNld() {
        return nld;
    }

    public void setNld(String nld) {
        this.nld = nld;
    }

    public String getPap() {
        return pap;
    }

    public void setPap(String pap) {
        this.pap = pap;
    }

}
