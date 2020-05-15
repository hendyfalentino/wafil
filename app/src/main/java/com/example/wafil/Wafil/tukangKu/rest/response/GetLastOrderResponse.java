package com.example.wafil.Wafil.tukangKu.rest.response;

import com.google.gson.annotations.SerializedName;

public class GetLastOrderResponse extends BaseResponse {
    @SerializedName("data")
    private Order order;

    public Order getOrder() {
        return order;
    }

    public class Order{
        @SerializedName("user")
        private int userId;

        @SerializedName("tukang_name")
        private String tukangName;

        @SerializedName("address")
        private String address;

        @SerializedName("workaddress")
        private String workaddress;

        @SerializedName("tukang")
        private int tukangId;

        @SerializedName("worktype")
        private int worktype;

        public int getWorktype() {
            return worktype;
        }

        public String getAddress() {
            return address;
        }

        public String getTukangName() {
            return tukangName;
        }

        public int getUserId() {
            return userId;
        }

        public int getTukangId() {
            return tukangId;
        }

        public String getWorkaddress() {
            return workaddress;
        }
    }
}
