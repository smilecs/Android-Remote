package smile.com.spaceglove;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by smilecs on 4/17/15.
 */
public class test extends Activity {
TCPClient j;
    int xPos = -1;
    int yPos = -1;
    private int mActivePointerId = 0;
    Button left, right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        left = (Button) findViewById(R.id.left);
        right = (Button) findViewById(R.id.right);
        //new Thread(new TCPClient("192.168.137.233 ", 5000)).start();
        j = new TCPClient(getIntent().getStringExtra("host"), Integer.parseInt(getIntent().getStringExtra("port")));
        Thread th = new Thread(j);
        th.start();
        left.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                j.sendMessage("mouse,left,0");
            }
        });
        right.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                j.sendMessage("mouse,right,0");
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);
        int index = MotionEventCompat.getActionIndex(event);

           switch (action) {
            case (MotionEvent.ACTION_DOWN):
                xPos = (int) MotionEventCompat.getX(event, index);
                yPos = (int) MotionEventCompat.getY(event, index);
                mActivePointerId = MotionEventCompat.getPointerId(event, 0);


                Log.d(" debug", "Action was DOWN");
                break;
            case (MotionEvent.ACTION_MOVE):
                Log.d("X", String.valueOf(xPos));
                Log.d("Y", String.valueOf(yPos));
                final int pointerIndex = MotionEventCompat.findPointerIndex(event, mActivePointerId);
                final float x = MotionEventCompat.getX(event, pointerIndex);
                final float y = MotionEventCompat.getY(event, pointerIndex);

                //double degree = (Math.toDegrees(Math.atan2(y-yPos, x-xPos )));

                float degree = (float) Math.toDegrees(Math.atan2(y-yPos, x-xPos));

                if(degree < 0){
                    degree += 360;
                }


                Log.d("1st degree", String.valueOf(degree));
                final float distance = x / y ;
                //Math.abs(distance);

                j.sendMessage("angle," + String.valueOf(Math.abs(Math.round(degree) + 90)) + "," + String.valueOf(Math.abs(Math.round(5))));

                Log.d("degree", String.valueOf(degree));
                Log.d("distance", String.valueOf(distance));
                Log.d(" debug", "Action was MOVE");
                break;
            case (MotionEvent.ACTION_UP):
                Log.d(" debug", "Action was UP");
                break;
            case (MotionEvent.ACTION_CANCEL):
                Log.d(" debug", "Action was CANCEL");
                break;
            case (MotionEvent.ACTION_OUTSIDE):
                Log.d(" debug", "Movement occurred outside bounds " +
                        "of current screen element");
                break;

        }
        return super.onTouchEvent(event);
    }
}
