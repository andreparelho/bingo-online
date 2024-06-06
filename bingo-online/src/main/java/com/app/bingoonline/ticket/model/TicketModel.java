package com.app.bingoonline.ticket.model;

import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public class TicketModel {
    private Map<String, List<Integer>> b;
    private Map<String, List<Integer>> i;
    private Map<String, List<Integer>> n;
    private Map<String, List<Integer>> g;
    private Map<String, List<Integer>> o;

    public Map<String, List<Integer>> getB() {
        return b;
    }

    public void setB(Map<String, List<Integer>> b) {
        this.b = b;
    }

    public Map<String, List<Integer>> getI() {
        return i;
    }

    public void setI(Map<String, List<Integer>> i) {
        this.i = i;
    }

    public Map<String, List<Integer>> getN() {
        return n;
    }

    public void setN(Map<String, List<Integer>> n) {
        this.n = n;
    }

    public Map<String, List<Integer>> getG() {
        return g;
    }

    public void setG(Map<String, List<Integer>> g) {
        this.g = g;
    }

    public Map<String, List<Integer>> getO() {
        return o;
    }

    public void setO(Map<String, List<Integer>> o) {
        this.o = o;
    }
}
