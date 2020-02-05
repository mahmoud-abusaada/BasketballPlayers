package com.abusada.basketballplayers.service.models;

import java.util.ArrayList;
import java.util.List;

public class Response {
    private List<Data> data = new ArrayList<>();
    private Meta meta;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
