package com.example.projekatmobilneaplikacije.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class BudgetItem implements Parcelable {
    private Long id;
    private double price;

    public BudgetItem(Long id, double price) {
        this.id = id;
        this.price = price;
    }

    public BudgetItem() {
    }

    protected BudgetItem(Parcel in) {
        id = in.readLong();

        price = in.readDouble();
    }

    public static final Creator<BudgetItem> CREATOR = new Creator<BudgetItem>() {
        @Override
        public BudgetItem createFromParcel(Parcel in) {
            return new BudgetItem(in);
        }

        @Override
        public BudgetItem[] newArray(int size) {
            return new BudgetItem[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }

        parcel.writeDouble(price);
    }
}

