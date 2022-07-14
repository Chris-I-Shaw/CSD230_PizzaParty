/*
Chris Shaw
Pizza Party Application
CSD 230
1/7/20
 */

package edu.lwtech.pizzaparty;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

//Code from zybook activity, small adjustments for comments and eliminating unused variables
public class MainActivity extends AppCompatActivity {

    // Fields for the widgets
    private EditText mNumAttendEditText;
    private TextView mNumPizzasTextView;
    private RadioGroup mHowHungryRadioGroup;

        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign the widgets to fields
        mNumAttendEditText = findViewById(R.id.attendEditText);
        mNumPizzasTextView = findViewById(R.id.answerTextView);
        mHowHungryRadioGroup = findViewById(R.id.hungryRadioGroup);
    }

    public void calculateClick(View view) {

        // Get how many are attending the party
        // Try/Catch sets attendance to 0 if no entry is made
        int numAttend;

        try {
            String numAttendStr = mNumAttendEditText.getText().toString();
            numAttend = Integer.parseInt(numAttendStr);
        }
        catch (NumberFormatException ex) {
            numAttend = 0;
        }

        // Get hunger level selection from radio buttons
        int checkedId = mHowHungryRadioGroup.getCheckedRadioButtonId();

        PizzaCalculator.HungerLevel hungerLevel = PizzaCalculator.HungerLevel.RAVENOUS;

        if (checkedId == R.id.lightRadioButton) {
            hungerLevel = PizzaCalculator.HungerLevel.LIGHT;
        }
        else if (checkedId == R.id.mediumRadioButton) {
            hungerLevel = PizzaCalculator.HungerLevel.MEDIUM;
        }

        // Calculates and shows the number of pizzas needed
        PizzaCalculator calc = new PizzaCalculator(numAttend, hungerLevel);
        int totalPizzas = calc.totalPizzas();
        String totalText = getString(R.string.total_pizzas);
        mNumPizzasTextView.setText(totalText + " " + totalPizzas);
    }
}