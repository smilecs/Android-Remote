package smile.com.spaceglove;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by smilecs on 4/17/15.
 */
public class speech extends Fragment {
    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    TCPClient j;

    public speech (){
Object p = getArguments().get("l");
        this.j = (TCPClient) p;
          }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       super.onCreateView(inflater, container, savedInstanceState);
       View v = inflater.inflate(R.layout.speech, container, false);
        txtSpeechInput = (TextView) v.findViewById(R.id.txtSpeechInput);
        btnSpeak = (ImageButton) v.findViewById(R.id.btnSpeak);
        try {
            getActivity().getActionBar().hide();
        }catch (NullPointerException npe){
            npe.getMessage();
        }
        btnSpeak.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                promptSpeechInput();

            }
        });
        return v;
    }

    //setContentView(R.layout.speech);



    private void promptSpeechInput(){
      Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));
        try{
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
                    }
        catch(ActivityNotFoundException a){
            Toast.makeText(getActivity().getApplicationContext(), getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == getActivity().RESULT_OK && null != data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0).toString());

                    String t[] = txtSpeechInput.getText().toString().split(" ");
                    Log.d("test", t[0]);
    switch (t[0]){
        case "close":
     j.sendMessage(t[0]+",0,0");
        break;
        case "shut":
            j.sendMessage("shutdown,0,0");
        break;
        default:
                j.sendMessage(t[0] + "," + t[1] + ",0");
                    }
                }
            }
        }
    }
}

