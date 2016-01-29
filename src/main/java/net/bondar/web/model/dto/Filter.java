package net.bondar.web.model.dto;

/**
 * Created by AzeraL on 18.01.2016.
 */
public class Filter {
    private int selectNumber;
    private String textFilter;

    public Filter() {
    }

    public Filter(int selectNumber, String textFilter) {
        this.selectNumber = selectNumber;
        this.textFilter = textFilter;
    }

    public int getSelectNumber() {
        return selectNumber;
    }

    public void setSelectNumber(int selectNumber) {
        this.selectNumber = selectNumber;
    }

    public String getTextFilter() {
        return textFilter;
    }

    public void setTextFilter(String textFilter) {
        this.textFilter = textFilter;
    }
}
