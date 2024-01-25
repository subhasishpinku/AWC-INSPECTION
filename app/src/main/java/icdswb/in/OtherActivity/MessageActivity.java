package icdswb.in.OtherActivity;

import android.content.Context;
import android.widget.Toast;

public class MessageActivity {
    Context context;
    public MessageActivity(Context context){
    this.context = context;
    }

    public void showmessage(Context context){
        this.context = context;
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }



}
