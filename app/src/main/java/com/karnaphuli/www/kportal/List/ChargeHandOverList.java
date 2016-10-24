package com.karnaphuli.www.kportal.List;

import com.karnaphuli.www.kportal.model.PendingCHOApplicationView;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by pt on 9/23/16.
 */
public class ChargeHandOverList{
        @SerializedName("PendingCHOApplicationView")
        @Expose
        private ArrayList<PendingCHOApplicationView> pendingCHOApplicationView = new ArrayList<PendingCHOApplicationView>();

        /**
         *
         * @return
         * The pendingCHOApplicationView
         */
        public ArrayList<PendingCHOApplicationView> getPendingCHOApplicationView() {
            return pendingCHOApplicationView;
        }

        /**
         *
         * @param pendingCHOApplicationView
         * The PendingCHOApplicationView
         */
        public void setPendingCHOApplicationView(ArrayList<PendingCHOApplicationView> pendingCHOApplicationView) {
            this.pendingCHOApplicationView = pendingCHOApplicationView;
        }
}
