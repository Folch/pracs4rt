/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter.threads;

import controller.filter.FilterController;

/**
 *
 * @author albert
 */
public class FilterThread {

    protected FilterController filter;
    protected int start, end;

    public FilterThread() {
        this.filter = null;
        this.start = -1;
        this.end = -1;
    }

    public void set(FilterController aThis, int inici, int end) {
        this.filter = aThis;
        this.start = inici;
        this.end = end;
    }
}
