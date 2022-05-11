
package com.route.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Native {

    @SerializedName("nld")
    @Expose
    private Nld nld;
    @SerializedName("pap")
    @Expose
    private Pap pap;

    public Nld getNld() {
        return nld;
    }

    public void setNld(Nld nld) {
        this.nld = nld;
    }

    public Pap getPap() {
        return pap;
    }

    public void setPap(Pap pap) {
        this.pap = pap;
    }

}
