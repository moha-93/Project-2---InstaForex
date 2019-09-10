package com.moha.instaforexapp.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Item {

        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("ActualTime")
        @Expose
        private Integer actualTime;
        @SerializedName("Comment")
        @Expose
        private String comment;
        @SerializedName("Pair")
        @Expose
        private String pair;
        @SerializedName("Period")
        @Expose
        private String period;
        @SerializedName("Price")
        @Expose
        private Double price;
        @SerializedName("Sl")
        @Expose
        private Double sl;
        @SerializedName("Tp")
        @Expose
        private Double tp;

        public Integer getId() {
                return id;
        }

        public Integer getActualTime() {
            return actualTime;
        }

        public String getComment() {
            return comment;
        }

        public String getPair() {
            return pair;
        }

        public String getPeriod() {
            return period;
        }

        public Double getPrice() {
            return price;
        }

        public Double getSl() {
            return sl;
        }

        public Double getTp() {
            return tp;
        }


}
