package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    int cr = 0;         // Credits
    double gp = 0.0;    // Grade points
    double gpa = 0.0;   // Grade point average

    List<String> listCodes;
    List<Integer> listCredits;
    List<Double> listGrades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<Double>();

        //Use listCodes.add("ITS333"); to add a new course code
        //Use listCodes.size() to refer to the number of courses in the list
    }

    public void actClicked(View v) {
        Intent i = new Intent(this, CourseActivity.class);
        startActivityForResult(i, 88);
    }

    public void resetClicked(View v) {
        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<Double>();

        calculate();
        TextView tvGPA = (TextView)findViewById(R.id.tvGPA);
        tvGPA.setText("0.00");
    }

    public void actListClicked(View v) {
        Intent j = new Intent(this, CourseListActivity.class);
        String s = "List of Courses \n";

        for(int i=0; i<listCodes.size(); i++ ){
            String temp;
            String grade="";

            if(listGrades.get(i)==4) { grade = "A"; }
            if(listGrades.get(i)==3.5) { grade = "B+"; }
            if(listGrades.get(i)==3) { grade = "B"; }
            if(listGrades.get(i)==2.5) { grade = "C+"; }
            if(listGrades.get(i)==2) { grade = "C"; }
            if(listGrades.get(i)==1.5) { grade = "D+"; }
            if(listGrades.get(i)==1) { grade = "D"; }
            if(listGrades.get(i)==0) { grade = "F"; }



            temp = listCodes.get(i) + " ( " + listCredits.get(i) + " credits) = " + grade + "\n";
            s = s.concat(temp);
        }

        j.putExtra("result", s);
        startActivity(j);
    }



    public void calculate(){
        int cr = 0;         // Credits
        double gp = 0.0;    // Grade points
        double gpa = 0.0;   // Grade point average

        for(int i=0; i<listCodes.size(); i++ ){
            gp += listCredits.get(i) * listGrades.get(i);
            cr += listCredits.get(i);
        }
        gpa = gp/cr;

        TextView tvGP = (TextView)findViewById(R.id.tvGP);
        TextView tvCR = (TextView)findViewById(R.id.tvCR);
        TextView tvGPA = (TextView)findViewById(R.id.tvGPA);

        tvGPA.setText(Double.toString(gpa));
        tvCR.setText(Integer.toString(cr));
        tvGP.setText(Double.toString(gp));

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Values from child activity

        if (requestCode == 88) {
            if (resultCode == RESULT_OK) {
                String code = data.getStringExtra("code");
                Integer credit = data.getIntExtra("credit", 0);
                Double grade = data.getDoubleExtra("grade", 0);

                listCodes.add(code);
                listCredits.add(credit);
                listGrades.add(grade);

                calculate();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
