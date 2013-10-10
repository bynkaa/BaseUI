package com.qsoft.BaseUI;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.*;

/**
 * User: binhtv
 * Date: 10/9/13
 * Time: 5:44 PM
 */
public class Profile extends Activity
{
    private static final int DATE_DIALOG_ID = 999;
    private static final int TIME_DIALOG_ID = 111;
    private static final String MY_ADDRESS = "address";
    private static final String MY_YEAR = "year";
    private static final String MY_MONTH = "month";
    private static final String MY_DAY = "day";
    private static final String MY_GENDER = "gender";
    private static final String MY_HOUR = "hour";
    private static final String MY_MINUTE = "minute";
    TextView tvAddress;
    TextView tvBirthday;
    TextView tvOnline;
    TextView tvGender;
    TextView tvGenderValue;
    RadioGroup rgGender;
    TextView tvTitle;
    CheckBox cbShowFriendList;
    ListView lvFriendList;
    Spinner spinnerAddress;
    Spinner spinnerGender;
    String userName;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String address;
    private String gender;
    private DatePicker datePicker;
    private TimePicker timePicker;

    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        userName = getIntent().getStringExtra(Login.EXTRA_NAME);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(userName);

        tvAddress = (TextView) findViewById(R.id.tv_adress_value);
        tvBirthday = (TextView) findViewById(R.id.tv_birthday_value);
        tvOnline = (TextView) findViewById(R.id.tv_online_value);
        tvGender = (TextView) findViewById(R.id.tv_gender);
        tvGenderValue = (TextView) findViewById(R.id.tv_gender_value);
        lvFriendList = (ListView) findViewById(R.id.lv_friend_list);
        spinnerAddress = (Spinner) findViewById(R.id.spinnerAddress);
        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        addListenerOnRgGender();
        addListenerOnTvAddress();
        addListenerOnTvGender();
        addSelectedItemSpinnerAddress();
        addSelectedItemSpinnerGender();
        addListenerOnCBShowFriendList();
        addListenerOnTvBirthday();
        addListenerOnTvOnline();
        if (savedInstanceState != null)
        {
            restoreState(savedInstanceState);
        }
        else
            startDefaultProfile();
    }

    private void restoreState(Bundle bundle)
    {
        address = bundle.getString(MY_ADDRESS);
        year = bundle.getInt(MY_YEAR);
        hour = bundle.getInt(MY_HOUR);
        day = bundle.getInt(MY_DAY);
        month = bundle.getInt(MY_MONTH);
        minute = bundle.getInt(MY_MINUTE);
        gender = bundle.getString(MY_GENDER);
        setProfile();

    }

    private void addListenerOnRgGender()
    {
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                int checkedRadioButton = rgGender.getCheckedRadioButtonId();
                String radioButtonSelected = "";
                switch (checkedRadioButton)
                {
                    case R.id.rbFemale:
                        radioButtonSelected = "Female";
                        break;
                    case R.id.rbMale:
                        radioButtonSelected = "Male";
                }
                if (!radioButtonSelected.isEmpty())
                {
                    tvGenderValue.setText(radioButtonSelected);
                    rgGender.setVisibility(View.GONE);
                }

            }
        });
    }

    private void addListenerOnTvOnline()
    {
        tvOnline.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDialog(TIME_DIALOG_ID);
            }
        });
    }

    private void addListenerOnTvBirthday()
    {
        tvBirthday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }

    private void addListenerOnTvGender()
    {
        tvGender.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                rgGender.setVisibility(View.VISIBLE);

            }
        });
    }

    private void addSelectedItemSpinnerGender()
    {
        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                tvGenderValue.setText(adapterView.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    private void addSelectedItemSpinnerAddress()
    {
        spinnerAddress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                tvAddress.setText(adapterView.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    private void addListenerOnTvAddress()
    {
        tvAddress.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                spinnerAddress.performClick();
            }
        });
    }

    private void addListenerOnCBShowFriendList()
    {
        cbShowFriendList = (CheckBox) findViewById(R.id.cb_show_friend_list);

        cbShowFriendList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (((CheckBox) view).isChecked())
                {
                    setListViewFriendList(true);
                }
                else
                {
                    setListViewFriendList(false);
                }
            }
        });
    }

    public ArrayList<Map<String, String>> getFriendListData()
    {
        ArrayList<Map<String, String>> friendList = new ArrayList<Map<String, String>>();
        friendList.add(getFriends(R.string.peter, R.string.peter_phone));
        friendList.add(getFriends(R.string.mary, R.string.mary_phone));
        friendList.add(getFriends(R.string.john, R.string.john_phone));
        return friendList;
    }

    public HashMap<String, String> getFriends(int nameId, int phoneId)
    {
        HashMap<String, String> friend = new HashMap<String, String>();
        friend.put("name", getResources().getString(nameId));
        friend.put("phone", getResources().getString(phoneId));
        return friend;
    }

    private void setListViewFriendList(boolean isChecked)
    {
        if (!isChecked)
        {
            lvFriendList.setAdapter(null);
        }
        else
        {
            String[] from = {"name", "phone"};
            int[] to = {android.R.id.text1, android.R.id.text2};
            ArrayList<Map<String, String>> friendList = getFriendListData();
            SimpleAdapter simpleAdapter = new SimpleAdapter(this, friendList, android.R.layout.simple_list_item_2, from, to);
            lvFriendList.setAdapter(simpleAdapter);
        }

    }

    private void startDefaultProfile(){
        address = "HA NOI";
        gender = "Male";
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day  = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        setProfile();

    }
    private void setProfile()
    {
        tvAddress.setText(address);
        tvBirthday.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year).append(" "));
        tvOnline.setText(new StringBuilder().append(pad(hour)).append(":").append(pad(minute)));
        tvGenderValue.setText(gender);
        datePicker.init(year,month,day,null);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch (id)
        {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,datePickerListener,year,month,day);
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,timePickerListener,hour,minute,false);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay)
        {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;
            // set selected date into text view
            tvBirthday.setText(new StringBuilder().append(day).append("/").
                    append(month).append("/").append(year).append(" "));
            // set selected date into date picker also
            datePicker.init(year,month,day,null);
        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener()
    {
        @Override
        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
        {
            hour = selectedHour;
            minute = selectedMinute;
            //
            tvOnline.setText(new StringBuilder().append(pad(hour)).append(":").append(pad(minute)));

            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);
        }

    };

    private static String pad(int c){
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);    //To change body of overridden methods use File | Settings | File Templates.
        outState.putString(MY_ADDRESS, address);
        outState.putInt(MY_YEAR, year);
        outState.putInt(MY_MONTH,month);
        outState.putInt(MY_DAY,day);
        outState.putInt(MY_HOUR,hour);
        outState.putInt(MY_MINUTE,minute);
        outState.putString(MY_GENDER, gender);
    }
}