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
    private static final String MY_INFOR = "information";
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
    String userName;
    private Infor infor = new Infor();
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
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        addListenerOnRgGender();
        addListenerOnTvAddress();
        addListenerOnTvGender();
        addSelectedItemSpinnerAddress();
        addListenerOnCBShowFriendList();
        addListenerOnTvBirthday();
        addListenerOnTvOnline();
        if (savedInstanceState != null)
        {
            infor = (Infor) savedInstanceState.getSerializable(MY_INFOR);
            setProfile();
        }
        else
            startDefaultProfile();
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
                    infor.setGender(radioButtonSelected);
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

        infor.setAddress("HA NOI");
        infor.setGender("Male");
        Calendar calendar = Calendar.getInstance();
        infor.setYear(calendar.get(Calendar.YEAR));
        infor.setMonth(calendar.get(Calendar.MONTH));
        infor.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        infor.setHour(calendar.get(Calendar.HOUR_OF_DAY));
        infor.setMinute(calendar.get(Calendar.MINUTE));
        setProfile();

    }
    private void setProfile()
    {
        tvAddress.setText(infor.getAddress());
        tvBirthday.setText(new StringBuilder().append(infor.getDay()).append("/").append(infor.getMonth())
                .append("/").append(infor.getYear()).append(" "));
        tvOnline.setText(new StringBuilder().append(pad(infor.getHour())).append(":").append(pad(infor.getMinute())));
        tvGenderValue.setText(infor.getGender());
        datePicker.init(infor.getYear(),infor.getMonth(),infor.getDay(),null);
        timePicker.setCurrentHour(infor.getHour());
        timePicker.setCurrentMinute(infor.getMinute());
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        switch (id)
        {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,datePickerListener,infor.getYear(),infor.getMonth(),infor.getDay());
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,timePickerListener,infor.getHour(),infor.getMinute(),false);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay)
        {
            infor.setYear(selectedYear);
            infor.setMonth(selectedMonth);
            infor.setDay(selectedDay);
            // set selected date into text view
            tvBirthday.setText(new StringBuilder().append(infor.getDay()).append("/").append(infor.getMonth())
                    .append("/").append(infor.getYear()).append(" "));
            // set selected date into date picker also
            datePicker.init(infor.getYear(),infor.getMonth(),infor.getDay(),null);
        }
    };

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener()
    {
        @Override
        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
        {
            infor.setHour(selectedHour);
            infor.setMinute(selectedMinute);
            //
            tvOnline.setText(new StringBuilder().append(pad(infor.getHour())).append(":").append(pad(infor.getMinute())));

            timePicker.setCurrentHour(infor.getHour());
            timePicker.setCurrentMinute(infor.getMinute());
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
        outState.putSerializable(MY_INFOR,infor);
    }
}