package com.EC327.Schedulr;

import android.accounts.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.*;

import com.example.testapp.R;
/*import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
*/
public class MainActivity extends Activity{
	/*
	 // https://developers.google.com/drive/scopes
    private static final String AUTH_TOKEN_TYPE = "oauth2:https://www.googleapis.com/auth/calendar";

    // https://code.google.com/apis/console/
    private static final String CLIENT_ID = "222537654383-9c1n1uvkhn50g1r5v90hgg06brqsneq5.apps.googleusercontent.com";
    
    private AccountManager accountManager;
    private Account[] accounts;
    private String authName;
    private String authToken;
    */
	/*
	GoogleAccountCredential credential;
	private static final String PREF_ACCOUNT_NAME = "nodroids327@gmail.com";
	final HttpTransport transport = AndroidHttp.newCompatibleTransport();
	final JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
	Calendar service;
	*/
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Calendar cal = Calendar.getInstance();              
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", true);
        intent.putExtra("rrule", "FREQ=YEARLY");
        intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
        intent.putExtra("title", "A Test Event from android app");
        startActivity(intent);
        
        /*accountManager = AccountManager.get(this);
        accounts = accountManager.getAccountsByType("com.google");

        if (accounts == null || accounts.length == 0) {
            // TODO
        } else if (accounts.length == 1) {
            processAccountSelected(accounts[0]);
        } *///else if (accounts.length > 1) {
           // showDialog(MyConstants.DIALOG_ACCOUNTCHOSER);
        //}
        Log.i("HI", "blah");
    }
    /*
        private void processAccountSelected(final Account account) {
            if (account != null) {
                authName = account.name.toString();
                if (true){//!Tools.isEmpty(authName)) {
                    Toast.makeText(this, authName, Toast.LENGTH_LONG).show();

                    accountManager.getAuthToken(account, AUTH_TOKEN_TYPE, null, this,
                            new AccountManagerCallback<Bundle>() {

                                public void run(final AccountManagerFuture<Bundle> future) {
                                    try {
                                        authToken = future.getResult().getString(
                                                AccountManager.KEY_AUTHTOKEN);
                                        processTokenReceived();
                                    } catch (OperationCanceledException exception) {
                                        // TODO
                                    } catch (Exception exception) {
                                        Log.d(this.getClass().getName(), exception.getMessage());
                                    }
                                }
                            }, null);
                }
            }
        }

        private void processTokenReceived() {
            if (true){ //!Tools.isEmpty(authToken)) {
                final HttpTransport transport = AndroidHttp.newCompatibleTransport();
                final JsonFactory jsonFactory = new GsonFactory();
                GoogleCredential credential = new GoogleCredential();
                credential.setAccessToken(authToken);
                Calendar calendar = new com.google.api.services.calendar.Calendar.Builder(
                		transport, jsonFactory, credential).setApplicationName("Shedulr")
                        .build();
                
            }
        }*/
        
       /* // Get credentials
        credential =
                GoogleAccountCredential.usingOAuth2(this, Collections.singleton(CalendarScopes.CALENDAR));
        SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
        credential.setSelectedAccountName(settings.getString(PREF_ACCOUNT_NAME, null));
        // Calendar client
        service = new com.google.api.services.calendar.Calendar.Builder(
        		transport, jsonFactory, credential).setApplicationName("Shedulr")
                .build();
        Event event = new Event();
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600000);
        DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
        event.setStart(new EventDateTime().setDateTime(start));
        DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
        event.setEnd(new EventDateTime().setDateTime(end));
        event.setSummary("Appointment");
        event.setLocation("BU");
        Event createdEvent;
		try {
			createdEvent = service.events().insert("primary", event).execute();
			Log.i("Calender ID: ", createdEvent.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        */
        
 


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
