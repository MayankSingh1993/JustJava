package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.price_text_view);
//        orderSummaryTextView.setText(message);
//    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrders(View view) {

        EditText editText = (EditText) findViewById(R.id.editText);
        String name = editText.getText().toString();
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.checkBox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.checkBox1);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        //Log.v("MainActivity","Has Whipped Cream : "+hasWhippedCream);
        //String priceMessage =calculatePrice(quantity,10);
        String priceMessage = createOrderSummary(5, 1, 2, name, hasWhippedCream, hasChocolate);
//       displayMessage(priceMessage);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject, name));
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    private String createOrderSummary(int priceOfOrder, int whippedCreamPrice, int chocolatePrice, String name, boolean addWhippedCream, boolean addChocolate) {
        if (addChocolate && addWhippedCream) {
            return getString(R.string.order_summary_name, name) + "\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream) + "\n" + getString(R.string.order_summary_chocolate, addChocolate) + "\n" + getString(R.string.order_summary_quantity, quantity) + "\n" + getString(R.string.order_summary_price) + quantity * (priceOfOrder + whippedCreamPrice + chocolatePrice) + "\n" + getString(R.string.thank_you);
        } else if (addWhippedCream) {
            return getString(R.string.order_summary_name, name) + "\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream) + "\n" + getString(R.string.order_summary_chocolate, addChocolate) + "\n" + getString(R.string.order_summary_quantity, quantity) + "\n" + getString(R.string.order_summary_price) + quantity * (priceOfOrder + whippedCreamPrice) + "\n" + getString(R.string.thank_you);
        } else if (addChocolate) {
            return getString(R.string.order_summary_name, name) + "\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream) + "\n" + getString(R.string.order_summary_chocolate, addChocolate) + "\n" + getString(R.string.order_summary_quantity, quantity) + "\n" + getString(R.string.order_summary_price) + quantity * (priceOfOrder + chocolatePrice) + "\n" + getString(R.string.thank_you);
        }
        return getString(R.string.order_summary_name, name) + "\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream) + "\n" + getString(R.string.order_summary_chocolate, addChocolate) + "\n" + getString(R.string.order_summary_quantity, quantity) + "\n" + getString(R.string.order_summary_price) + quantity * priceOfOrder + "\n" + getString(R.string.thank_you);
    }


    public void decrement(View view) {

        // quantity=2*quantity;

        if (quantity <= 1) {
            Toast.makeText(this, R.string.below_one_coffee_message, Toast.LENGTH_LONG).show();
        } else {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }

    }

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        } else if (quantity == 100) {
            Toast.makeText(this, R.string.above_100_coffee_message, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
//    private String calculatePrice(int quantity, int pricePerCup) {
//        int price = quantity * pricePerCup;
//        return "Total :$" + price + "\nthank you!";
//    }

}