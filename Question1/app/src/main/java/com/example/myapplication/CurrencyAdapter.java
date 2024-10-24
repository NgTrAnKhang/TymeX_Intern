package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;

public class CurrencyAdapter extends ArrayAdapter<String> {
    public CurrencyAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected, parent, false);
        }

        String currency = this.getItem(position);
        TextView tvSelected = convertView.findViewById(R.id.tv_selected);

        Log.d("CurrencyAdapter", "Currency at position " + position + ": " + currency);

        if (currency != null && !currency.isEmpty()) {
            tvSelected.setText(currency);
        } else {
            tvSelected.setText("No Currency");
        }

        return convertView;
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency, parent, false);
        }
        TextView tvCurrency = convertView.findViewById(R.id.tv_currency);
        String currency = this.getItem(position);
        if (currency != null) {
            tvCurrency.setText(currency);
        } else {
            tvCurrency.setText("");
        }
        return convertView;
    }

}
