package smile.com.spaceglove;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by smilecs on 5/14/15.
 */
public class ipconfig extends Activity {

    TextView x, y;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x = (TextView)findViewById(R.id.deltaX);
        y = (TextView) findViewById(R.id.deltaY);
        submit = (Button) findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("host", x.getText().toString());
                i.putExtra("port", y.getText().toString());
                startActivity(i);
            }
        });

    }
}
