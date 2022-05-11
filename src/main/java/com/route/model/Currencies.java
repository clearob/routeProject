
package com.route.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Currencies {

    @SerializedName("AWG")
    @Expose
    private Awg awg;

    public Awg getAwg() {
        return awg;
    }

    public void setAwg(Awg awg) {
        this.awg = awg;
    }

}
