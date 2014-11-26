package com.udev.merchants;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class MerchantActivity extends Activity {
	TextView tvcheckout;
	CheckBox checkBox;
	Button checkout;
	OnCheckedChangeListener chkBoxListner;
	HashMap<Integer, String> itemsOrdered = new HashMap<Integer, String>();
	StringBuffer sb=new StringBuffer();
	Double total=0.0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_merchant);   //
		tvcheckout = (TextView) findViewById(R.id.txtCheckout);
		checkout = (Button) findViewById(R.id.btnChk);
		checkout.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	float sz = tvcheckout.getTextSize();
            	tvcheckout.setTextSize(20);
            	dispItemsOrdered(sb);
            	tvcheckout.setTextSize(sz);
            }
        });
	    chkBoxListner = new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				int id = buttonView.getId();
				String iName = buttonView.getText().toString();
				Double mPrice=getPrice(id);
				StringBuffer strbuf=new StringBuffer();;

				if ( isChecked ) {
					total += mPrice;
					itemsOrdered.put(id, iName);
				} else {
					itemsOrdered.remove(id);
					if ( 0 == itemsOrdered.size()) {
						total = 0.0;												
					} else if ( total > mPrice ) {
						total -= mPrice;						
					}					
				}
				strbuf.append("Check Out - Total :"+String.format("%.2f", total));
				dispItemsOrdered(strbuf);
			}
	    };
	    checkBox = (CheckBox) findViewById(R.id.btnBJD);
	    checkBox.setOnCheckedChangeListener(chkBoxListner);
	    checkBox = (CheckBox)findViewById(R.id.btnCPB);
	    checkBox.setOnCheckedChangeListener(chkBoxListner);
	    checkBox = (CheckBox)findViewById(R.id.btnFTF);
	    checkBox.setOnCheckedChangeListener(chkBoxListner);
	    checkBox = (CheckBox)findViewById(R.id.btnSTF);
	    checkBox.setOnCheckedChangeListener(chkBoxListner);
	    checkBox = (CheckBox)findViewById(R.id.btnMPT);
	    checkBox.setOnCheckedChangeListener(chkBoxListner);
	    checkBox = (CheckBox)findViewById(R.id.btnOrderBNS);
	    checkBox.setOnCheckedChangeListener(chkBoxListner);
	    checkBox = (CheckBox)findViewById(R.id.btnOrderKPC);
	    checkBox.setOnCheckedChangeListener(chkBoxListner);
	}
	
	public double getPrice(int id) {
		Double mPrice=14.95;
        switch (id) {
	        case R.id.btnBJD:
	        	mPrice=14.95;
	            break;
	        case R.id.btnCPB:
	        	mPrice=3.95;
	            break;
	        case R.id.btnFTF:
	        	mPrice=9.95;
	            break;
	        case R.id.btnSTF:
	        	mPrice=4.95;
	            break;
	        case R.id.btnMPT:
	        	mPrice=9.95;
	            break;
	        case R.id.btnOrderBNS:
	        	mPrice=9.95;
	            break;
	        case R.id.btnOrderKPC:
	        	mPrice=12.95;
	            break;
	        default:
	        	mPrice=10.95;
	            break;
        }
        return mPrice;	
	}
	
	public void dispItemsOrdered(StringBuffer sblder) {
		String str="";
		Iterator itr = itemsOrdered.keySet().iterator();
	    while( itr.hasNext()) {
	          str += "\n" + (String)itemsOrdered.get(itr.next());
	    }
        str += "\nTotal + Tax:" + String.format("%.2f", total * 1.09);
		tvcheckout.setText(str);
	}

}
