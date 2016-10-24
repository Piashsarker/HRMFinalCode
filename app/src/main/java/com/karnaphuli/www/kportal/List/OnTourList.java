package com.karnaphuli.www.kportal.List;

import com.karnaphuli.www.kportal.model.TourView;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by pt on 9/21/16.
 */
public class OnTourList {
    @SerializedName("TourView")
    @Expose
    private ArrayList<TourView> tourView = new ArrayList<TourView>();

    /**
     *
     * @return
     * The tourView
     */
    public ArrayList<TourView> getTourView() {
        return tourView;
    }

    /**
     *
     * @param tourView
     * The TourView
     */
    public void setTourView(ArrayList<TourView> tourView) {
        this.tourView = tourView;
    }
}
