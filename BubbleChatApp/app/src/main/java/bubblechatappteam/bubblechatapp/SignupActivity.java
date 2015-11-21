package bubblechatappteam.bubblechatapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.List;

import bubblechatappteam.bubblechatapp.helper.LoginHelper;


public class SignupActivity extends ActionBarActivity {

    EditText etUsername, etPassword, getEtPasswordAgain;
    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etUsername = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);
        getEtPasswordAgain = (EditText) findViewById(R.id.passwordAgain);
        buttonRegister = (Button) findViewById(R.id.register);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginHelper loginHelper = new LoginHelper();
                if (!loginHelper.validateUsername(etUsername.getText().toString())) {
                    etUsername.setError(getString(R.string.ErrorUsername));
                    return;
                }
                if (!loginHelper.validatePassword(etPassword.getText().toString())) {
                    etPassword.setError(getString(R.string.ErrorPassword));
                    return;
                }
                if (!TextUtils.equals(etPassword.getText().toString(), getEtPasswordAgain.getText().toString())) {
                    etPassword.setError(getString(R.string.ErrorPasswordNoMatch));
                    return;
                }

                buttonRegister.setEnabled(false);
                buttonRegister.setText("REGISTRATION...");

                ParseQuery<ParseObject> query = ParseQuery.getQuery("user");
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> users, ParseException e) {
                        Log.d("query", "done");
                        if (e == null) {
                            Log.d("ParseQuery user: ", "succes");
                            Boolean taken = false;
                            for (ParseObject user : users) {
                                if (TextUtils.equals(etUsername.getText().toString(), user.getString("username"))) {
                                    etUsername.setError(getString(R.string.ErrorUsernameAlreadyTaken));
                                    taken = true;
                                }
                            }
                            if (!taken) {
                                ParseObject userToRegister = new ParseObject("user");
                                userToRegister.put("username", etUsername.getText().toString());
                                userToRegister.put("password", etPassword.getText().toString());
                                userToRegister.put("isonline", false);
                                userToRegister.saveInBackground();
                                Toast.makeText(SignupActivity.this, "Succesfully registration!", Toast.LENGTH_SHORT).show();
                            }
                            buttonRegister.setEnabled(true);
                            buttonRegister.setText(getText(R.string.RegisterBtn));
                        } else {
                            Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                            Toast.makeText(SignupActivity.this, "Check your internet connection!", Toast.LENGTH_SHORT).show();
                            buttonRegister.setEnabled(true);
                            buttonRegister.setText(getText(R.string.RegisterBtn));
                        }
                    }
                });
            }
        });
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
