package smile.com.spaceglove;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by smilecs on 4/17/15.
 */
public class custom extends Fragment{
    EditText ed, texting;
    Button type;
    TCPClient z;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*z = new TCPClient(getIntent().getStringExtra("host"), Integer.parseInt(getIntent().getStringExtra("port")));
        Thread th = new Thread(z);
        th.start();*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.custom, container, false);

        ed = (EditText) v.findViewById(R.id.command);
        Button b = (Button) v.findViewById(R.id.send);
        b.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String j = ed.getText().toString();
                z.sendMessage("launch," + j + "," + "0");
            }
        });
        texting = (EditText) v.findViewById(R.id.texting);
        type = (Button) v.findViewById(R.id.type);
        type.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tx = texting.getText().toString();
                z.sendMessage("text,"+tx+",0");

            }
        });
        return v;
    }
}
