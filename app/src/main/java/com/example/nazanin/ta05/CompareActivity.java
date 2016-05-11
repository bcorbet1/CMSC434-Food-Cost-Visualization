package com.example.nazanin.ta05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.w3c.dom.Text;

public class CompareActivity extends AppCompatActivity {
    BarChartView _barChart;

    String[] nations = {"Afghanistan","Albania","Algeria","American Samoa","Andorra","Angola",
                        "Anguilla","Antarctica","Antigua and Barbuda","Argentina","Armenia","Aruba",
                        "Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh",
                        "Barbados","Belarus","Belgium","Belize","Benin","Bermuda","Bhutan",
                        "Bolivia","Bosnia and Herzegovina","Botswana","Bouvet Island","Brazil",
                        "British Indian Ocean Territory","Brunei Darussalam","Bulgaria",
                        "Burkina Faso","Burundi","Cambodia","Cameroon","Canada","Cape Verde",
                        "Cayman Islands","Central African Republic","Chad","Chile","China",
                        "Christmas Island","Cocos (Keeling Islands)","Colombia","Comoros","Congo",
                        "Cook Islands","Costa Rica","Cote D'Ivoire (Ivory Coast)",
                        "Croatia (Hrvatska","Cuba","Cyprus","Czech Republic","Denmark",
                        "Djibouti","Dominica","Dominican Republic","East Timor","Ecuador","Egypt",
                        "El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia",
                        "Falkland Islands (Malvinas)","Faroe Islands","Fiji","Finland","France",
                        "France, Metropolitan","French Guiana","French Polynesia",
                        "French Southern Territories","Gabon","Gambia","Georgia","Germany","Ghana",
                        "Gibraltar","Greece","Greenland","Grenada","Guadeloupe","Guam","Guatemala",
                        "Guinea","Guinea-Bissau","Guyana","Haiti","Heard and McDonald Islands",
                        "Honduras","Hong Kong","Hungary","Iceland","India","Indonesia","Iran",
                        "Iraq","Ireland","Israel","Italy","Jamaica","Japan","Jordan","Kazakhstan",
                        "Kenya","Kiribati","Korea (North)","Korea (South)","Kuwait","Kyrgyzstan",
                        "Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein",
                        "Lithuania","Luxembourg","Macau","Macedonia","Madagascar","Malawi",
                        "Malaysia","Maldives","Mali","Malta","Marshall Islands","Martinique",
                        "Mauritania","Mauritius","Mayotte","Mexico","Micronesia","Moldova","Monaco",
                        "Mongolia","Montserrat","Morocco","Mozambique","Myanmar","Namibia","Nauru",
                        "Nepal","Netherlands","Netherlands Antilles","New Caledonia","New Zealand",
                        "Nicaragua","Niger","Nigeria","Niue","Norfolk Island",
                        "Northern Mariana Islands","Norway","Oman","Pakistan","Palau","Panama",
                        "Papua New Guinea","Paraguay","Peru","Philippines","Pitcairn","Poland",
                        "Portugal","Puerto Rico","Qatar","Reunion","Romania","Russian Federation",
                        "Rwanda","Saint Kitts and Nevis","Saint Lucia",
                        "Saint Vincent and The Grenadines","Samoa","San Marino",
                        "Sao Tome and Principe","Saudi Arabia","Senegal","Seychelles",
                        "Sierra Leone","Singapore","Slovak Republic","Slovenia","Solomon Islands",
                        "Somalia","South Africa","S. Georgia and S. Sandwich Isls.","Spain",
                        "Sri Lanka","St. Helena","St. Pierre and Miquelon","Sudan","Suriname",
                        "Svalbard and Jan Mayen Islands","Swaziland","Sweden","Switzerland",
                        "Syria","Taiwan","Tajikistan","Tanzania","Thailand","Togo","Tokelau",
                        "Tonga","Trinidad and Tobago","Tunisia","Turkey","Turkmenistan",
                        "Turks and Caicos Islands","Tuvalu","Uganda","Ukraine",
                        "United Arab Emirates","United Kingdom","United States",
                        "US Minor Outlying Islands","Uruguay","Uzbekistan","Vanuatu",
                        "Vatican City State (Holy See)","Venezuela","Viet Nam",
                        "Virgin Islands (British)","Virgin Islands (US)",
                        "Wallis and Futuna Islands","Western Sahara","Yemen","Yugoslavia","Zaire",
                        "Zambia","Zimbabwe"};

    double[] data = new double[nations.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        _barChart = (BarChartView)findViewById(R.id.viewBarChart);

        final Spinner firstSpinner = initiateSpinner(R.id.firstSpinner, nations);
        final Spinner secondSpinner = initiateSpinner(R.id.secondSpinner, nations);

        final double maxValue = 60;
        double minValue = 0.10;

        // create fake data
        for (int i = 0; i < nations.length; i++) {
            data[i] = Math.random() * (maxValue - minValue) + minValue;
        }

        // set us index
        int usIndex = -1;
        for (int i = 0; i < nations.length; i++) {
            if (nations[i].equals("United States")) {
                usIndex = i;
                break;
            }
        }

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int firstSelection = firstSpinner.getSelectedItemPosition();
                int secondSelection = secondSpinner.getSelectedItemPosition();

                String[] labels = {
                        nations[firstSelection],
                        nations[secondSelection]
                };

                double[] values = {
                        data[firstSelection],
                        data[secondSelection]
                };

                _barChart.setData(values, maxValue, labels);
            }
            public void onNothingSelected(AdapterView<?> parentView) { }
        };

        firstSpinner.setOnItemSelectedListener(listener);
        secondSpinner.setOnItemSelectedListener(listener);

        firstSpinner.setSelection(usIndex);
    }

    public void home(View view) {
        startActivity(new Intent(CompareActivity.this, MainActivity.class));
    }

    protected Spinner initiateSpinner(int id, String[] data) {
        Spinner spinner = (Spinner) findViewById(id);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CompareActivity.this,
                android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return spinner;
    }
}
