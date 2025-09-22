package com.repsrv.csweb.core.model;

import java.util.List;

import javax.validation.Valid;

public class ValidListWrapper <T> {

    @Valid
    private List<T> list;

    public ValidListWrapper(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }
}