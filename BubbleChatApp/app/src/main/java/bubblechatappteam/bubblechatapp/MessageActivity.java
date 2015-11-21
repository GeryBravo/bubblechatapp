package bubblechatappteam.bubblechatapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

/**
 * Created by gerybravo on 2015.11.21..
 */
public class MessageActivity extends ActionBarActivity {

    private ImageView bubble, profileMini;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_activity);
        bubble = (ImageView) findViewById(R.id.ivBubble);
        profileMini = (ImageView) findViewById(R.id.ivProfilePicMini);

        bubble.setImageResource(R.drawable.bubble);
        profileMini.setImageResource(R.drawable.blank_profile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.message_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id)
        {
            case R.id.action_friend_list:
                Log.d("MenuItem","action_friend_list");
                break;
            case R.id.action_profile:
                Log.d("MenuItem","action_profile");
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
