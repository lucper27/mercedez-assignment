package com.swapi.adapter.infraestructure.client.swapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapiPersonWrapperDTO {

    private String _id;

    private String uid;

    private String description;

    private int __v;

    private SwapiPersonDTO properties;

    public SwapiPersonWrapperDTO() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public SwapiPersonDTO getProperties() {
        return properties;
    }

    public void setProperties(SwapiPersonDTO properties) {
        this.properties = properties;
    }
}
