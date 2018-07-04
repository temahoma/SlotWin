package com.luyunfeng.outsource.slotwin.mvp.shop;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.cage.library.infrastructure.text.StringUtils;
import com.luyunfeng.outsource.slotwin.R;
import com.luyunfeng.outsource.slotwin.bean.BaseBouns;
import com.luyunfeng.outsource.slotwin.mvp.base.BaseMvpActivity;
import com.luyunfeng.outsource.slotwin.shop.BaseShop;
import com.luyunfeng.outsource.slotwin.shop.ShopBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * http://papimo.jp/h/00001616/hit/view/717/20180623
 * http://www.paradiso.jp/machine_data/sp/10017/detail.php?h=10017&c=c749ef54b188453ad32b67f20af230a1&k=S20&d=1561
 * http://639982.p-moba.net/game_machine_detail.php?id=237
 * http://pleforce.co.jp/holldata/
 */
public class ShopActivity extends BaseMvpActivity<ShopContract.IView, ShopContract.IPresenter> implements ShopContract.IView {

    TextView tv_shop_name;
    Button btn_date;
    TextView tv_date;
    EditText et_machine_number;
    Button btn_search;

    BaseShop baseShop;

    Calendar selectedDate = Calendar.getInstance();
    Calendar maxDate = Calendar.getInstance();
    Calendar minDate = Calendar.getInstance();

    @Override
    public void initialized() {

        Bundle bundle = getIntent().getExtras();

        String shop_id = bundle.getString("shop_id");
        String shop_name = bundle.getString("shop_name");
        String shop_url = bundle.getString("shop_url");
        String shop_website = bundle.getString("shop_website");

        baseShop = new ShopBuilder()
                .setWebsite(shop_website)
                .setName(shop_name)
                .setUrl(shop_url)
                .build();

        minDate.add(Calendar.MONTH, -1);

        tv_shop_name.setText(shop_name);

        displayDate(selectedDate);

        prestener.setShop(baseShop);
    }

    private void displayDate(Calendar selectedDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String date = df.format(selectedDate.getTime());
        tv_date.setText(date);
    }

    @Override
    public void display(List<? extends BaseBouns> bounsList) {

    }

    @Override
    public void empty() {

    }


    @Override
    public void setupViews() {
        tv_shop_name = findViewById(R.id.tv_shop_name);
        btn_date = findViewById(R.id.btn_date);
        tv_date = findViewById(R.id.tv_date);
        et_machine_number = findViewById(R.id.et_machine_number);
        btn_search = findViewById(R.id.btn_search);

        btn_date.setOnClickListener(this);
        btn_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_date:
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ShopActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                selectedDate.set(Calendar.YEAR, year);
                                selectedDate.set(Calendar.MONTH, month);
                                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                displayDate(selectedDate);
                            }
                        },
                        selectedDate.get(Calendar.YEAR),
                        selectedDate.get(Calendar.MONTH),
                        selectedDate.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());
                datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());

                datePickerDialog.show();
                break;
            case R.id.btn_search:
                String machineNumber = et_machine_number.getText().toString();
                if (StringUtils.isValid(machineNumber)) {
                    prestener.setTarget(machineNumber, selectedDate);
                    prestener.readData();
                }
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop;
    }

    @Override
    protected void initPresenter() {
        prestener = new ShopPresenter();
    }
}
