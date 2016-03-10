package is.aiga.bordid;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    public static ImageView profile_image;
    public Button buttonLogout, buttonConfigure, buttonRestaurantConfigure;
    public static TextView name, email, phoneNumber, restaurant_name;
    public static int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Back arrow enabled
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // TextViews found
        name = (TextView) findViewById(R.id.profile_name);
        email = (TextView) findViewById(R.id.profile_email);
        phoneNumber = (TextView) findViewById(R.id.profile_phoneNumber);
        restaurant_name = (TextView) findViewById(R.id.profile_restaurant_name);

        // Filling TextViews with user information
        name.setText(SaveSharedPreference.getName(this));
        email.setText(SaveSharedPreference.getEmail(this));
        phoneNumber.setText(SaveSharedPreference.getPhoneNumber(this));
        restaurant_name.setText(SaveSharedPreference.getRestaurantName(this));

        // Buttons initialized
        buttonLogout = (Button) findViewById(R.id.button_logout);
        buttonLogout.setOnClickListener(this);
        buttonConfigure = (Button) findViewById(R.id.profile_configure);
        buttonConfigure.setOnClickListener(this);
        buttonRestaurantConfigure = (Button) findViewById(R.id.profile_configure_restaurant);
        buttonRestaurantConfigure.setOnClickListener(this);
        profile_image = (ImageView) findViewById(R.id.profile_photo);
        if(!SaveSharedPreference.getProfileImage(this).equals("")) Picasso.with(this).load(SaveSharedPreference.getProfileImage(this)).into(profile_image);
        profile_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_logout:
                SaveSharedPreference.clearUser(this);
                finish();
                break;

                // Start a dialog to configure the profile
            case R.id.profile_configure:
                FragmentManager fm = getFragmentManager();
                MyDialogFragment dialogFragment = new MyDialogFragment();
                dialogFragment.show(fm, "Sample Fragment");
                break;

                // Start a dialog to configure the profile
            case R.id.profile_configure_restaurant:
                FragmentManager fm2 = getFragmentManager();
                EditRestDialogFragment dialogFragment2 = new EditRestDialogFragment();
                dialogFragment2.show(fm2, "Sample Fragment");
                break;

                // Start a dialog to change profile image
            case R.id.profile_photo:
                FragmentManager fm3 = getFragmentManager();
                UploadImageDialogFragment dialogFragment3 = new UploadImageDialogFragment();
                dialogFragment3.show(fm3, "Sample Fragment");

            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // this takes the user 'back', as if they pressed the left-facing triangle icon on the main android toolbar.
                // if this doesn't work as desired, another possibility is to call `finish()` here.
                this.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}






//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            userName = extras.getString("username");
//            Toast.makeText(ProfileActivity.this, "Logged in as: " + userName, Toast.LENGTH_LONG).show();
//        }


//// Get user information
//public class UserInformation extends AsyncTask<Void, Void, String> {
//
//    private final String mUserName;
//
//    UserInformation(String username) {
//        mUserName = username;
//    }
//
//    @Override
//    protected String doInBackground(Void... params) {
//
//        Service service = new Service(); // Service class is used to validate username and password
//
//        HashMap<String,String> data = new HashMap<>();
//        data.put(UPLOAD_KEY, mUserName); // UPLOAD_KEY = "username", keyword for server POST request
//
//        String result = service.sendPostRequest(UPLOAD_URL, data); // Posts a String to server, String created by HashMap, eg. username=john:123456
//
//        return result;
//    }
//
//    @Override
//    protected void onPostExecute(final String s) {
//        Log.d("IED", "GetUser: " + s);
//
//        int i = Integer.parseInt(s.split(":")[0]);
//        String n = s.split(":")[1];
//        String e = s.split(":")[2];
//        String p = s.split(":")[3];
//
//        id = i;
//        name.setText(n);
//        email.setText(e);
//        phoneNumber.setText(p);
//
//    }
//}
