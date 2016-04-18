package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCupsOfCoffee = 0;
    int priceOfOneCup = 5;
    int costOfWhippedCream = 1;
    int costOfChocolate = 2;
    Boolean addWhippedCream = false;
    Boolean addChocolate = false;
    String emailSubject = "JustJava order for ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        String nameOfCustomer;
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        if (whippedCreamCheckBox != null) {
            addWhippedCream = whippedCreamCheckBox.isChecked();
        }

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        if (chocolateCheckBox != null) {
            addChocolate = chocolateCheckBox.isChecked();
        }

        EditText nameTextView = (EditText) findViewById(R.id.name_field);
        if (nameTextView != null) {
            nameOfCustomer = nameTextView.getText().toString();
            emailSubject += nameOfCustomer;
        } else
            nameOfCustomer = "Name not found";

        Uri emailUri = Uri.parse("mailto:");
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(emailUri);

        //Todo - need to fix the emailSubject
        intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject + nameOfCustomer);

        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(nameOfCustomer));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    //   composeEmail(nameOfCustomer, createOrderSummary(nameOfCustomer));

    /**
     * This returns the Total price of the order
     */
    private int calculatePrice() {

        int basePrice = 0;
        basePrice = priceOfOneCup;

        if (addChocolate) {
            basePrice += costOfChocolate;
        }
        if (addWhippedCream) {
            basePrice += costOfWhippedCream;
        }
        return numberOfCupsOfCoffee * basePrice;
    }

    /**
     * This method is called when the pluse button is clicked.
     */
    public void increment(View view) {
        numberOfCupsOfCoffee++;
        displayQuantity(numberOfCupsOfCoffee);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        numberOfCupsOfCoffee--;
        if (numberOfCupsOfCoffee < 0) {
            numberOfCupsOfCoffee = 0;
        }
        displayQuantity(numberOfCupsOfCoffee);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
  /*  private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
        orderSummaryTextView.setTextSize(24);
    }*/
    private String createOrderSummary(String nameOfCustomer) {
        calculatePrice();
        String orderSummary = nameOfCustomer;
        orderSummary += "\nAdd whipped cream? " + addWhippedCream;
        orderSummary += "\nAdd chocolate? " + addChocolate;
        orderSummary += "\nQuantity: " + numberOfCupsOfCoffee;
        orderSummary += "\nTotal: $" + calculatePrice();
        orderSummary += "\nThank you!";
        return orderSummary;
    }

    public void composeEmail(String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else Toast.makeText(MainActivity.this, "There is a problem", Toast.LENGTH_SHORT).show();
    }
}