
package com.route.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Name {

    @SerializedName("common")
    @Expose
    private String common;
    @SerializedName("official")
    @Expose
    private String official;
    @SerializedName("native")
    @Expose
    private Native _native;

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official;
    }

    public Native getNative() {
        return _native;
    }

    public void setNative(Native _native) {
        this._native = _native;
    }

}
