package com.app.bingoonline.model;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TicketModel {
    private Map<String, Integer> b;
    private Map<String, Integer> i;
    private Map<String, Integer> n;
    private Map<String, Integer> g;
    private Map<String, Integer> o;

    public Map<String, Integer> getB() {
        return b;
    }

    public void setB(Map<String, Integer> b) {
        this.b = b;
    }

    public Map<String, Integer> getI() {
        return i;
    }

    public void setI(Map<String, Integer> i) {
        this.i = i;
    }

    public Map<String, Integer> getN() {
        return n;
    }

    public void setN(Map<String, Integer> n) {
        this.n = n;
    }

    public Map<String, Integer> getG() {
        return g;
    }

    public void setG(Map<String, Integer> g) {
        this.g = g;
    }

    public Map<String, Integer> getO() {
        return o;
    }

    public void setO(Map<String, Integer> o) {
        this.o = o;
    }
}
