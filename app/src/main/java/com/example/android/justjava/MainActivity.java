package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int totalCupsOfCoffee = 0;
    int priceOfOneCup = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String priceMessage = "Total: $" + calculatePrice(totalCupsOfCoffee);
        priceMessage += "\nThank you!";
        displayMessage(priceMessage);
    }

    private int calculatePrice(int quantity) {
        return quantity * priceOfOneCup;
    }
    /**
     * This method is called when the pluse button is clicked.
     */
    public void increment(View view) {
        totalCupsOfCoffee += 1;
        display(totalCupsOfCoffee);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        totalCupsOfCoffee -= 1;
        if (totalCupsOfCoffee < 0) {
            totalCupsOfCoffee = 0;
        }
        display(totalCupsOfCoffee);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}