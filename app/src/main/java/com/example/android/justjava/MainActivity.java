/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */
package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    private static int quantity;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantity = 0;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText editText = (EditText) findViewById(R.id.name);
        name = editText.getText().toString();
        String message = " coffee order \n name: " + name;
        int price = 20;
        boolean cream, coca;
        cream = ((CheckBox) findViewById(R.id.cream)).isChecked();
        coca = ((CheckBox) findViewById(R.id.coca)).isChecked();
        if (coca) {
            price += 12;
            message = message + "\n added coca.";
        }
        if (cream) {
            price += 15;
            message = message + "\n added cream.";
        }
        message = message + "\n Total=" + (NumberFormat.getCurrencyInstance().format(quantity * price)) + "\n Thank You!";
       /* displayOrder(message);*/
        email(message);
    }

    public void incr(View view) {
        quantity++;
        display(quantity);
    }

    public void decr(View view) {
        quantity--;
        if (quantity < 0) {
            quantity++;
        }
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    /*private  void displayOrder(String message){
        TextView order = (TextView) findViewById(R.id.order_sum);
        order.setText(message);}*/
    public  void email(String message){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "engineersbynature@gmail.com" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "coffee order by "+name);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
