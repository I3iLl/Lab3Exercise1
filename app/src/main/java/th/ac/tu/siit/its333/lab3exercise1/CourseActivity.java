package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;


public class CourseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
    }

    public void retClicked(View v) {
        Intent res = new Intent();
        double grade = 0.0;

        EditText code = (EditText)findViewById(R.id.etCode);
        EditText credit = (EditText)findViewById(R.id.etCR);
        RadioGroup rgGrade = (RadioGroup)findViewById(R.id.rgGrade);

        switch (rgGrade.getCheckedRadioButtonId()){
            case R.id.rbA:
                grade = 4.0;
                break;
            case R.id.rbBP:
                grade = 3.5;
                break;
            case R.id.rbB:
                grade = 3.0;
                break;
            case R.id.rbCP:
                grade = 2.5;
                break;
            case R.id.rbC:
                grade = 2.0;
                break;
            case R.id.rbDP:
                grade = 1.5;
                break;
            case R.id.rbD:
                grade = 1.0;
                break;
            case R.id.rbF:
                grade = 0;
                break;

        }
        String s = credit.getText().toString();
        int iCredit = Integer.parseInt(s);

        res.putExtra("code", code.getText().toString());
        res.putExtra("credit", iCredit);
        res.putExtra("grade", grade);

        setResult(RESULT_OK, res);
        finish();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
