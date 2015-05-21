package smile.com.spaceglove;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends Fragment {
   // RelativeLayout lowestLayout;
    Button custom, mouse, speech;
    TextView x, y;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.activity_main, container, false);

        custom = (Button) v.findViewById(R.id.custom);
        custom.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                custom c = new custom();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.mainContent, c).commit();


            }
        });
        speech = (Button) v.findViewById(R.id.speech);
        speech.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        mouse = (Button) v.findViewById(R.id.mouse);
        mouse.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    return v;
    }
}