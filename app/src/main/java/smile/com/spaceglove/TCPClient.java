package smile.com.spaceglove;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by kebe on 4/17/15.
 */public class TCPClient implements Runnable{

    private static final String TAG = "TCPClient";
   // private final Handler mHandler;
    private String ipNumber;
    BufferedReader in;
    PrintWriter out;
    private int port;
    Socket socket;
    //private MessageCallback   listener        = null            ;
//    private boolean mRun = false;


    /**
     * TCPClient class constructor, which is created in AsyncTasks after the button click.
     * @param mHandler Handler passed as an argument for updating the UI with sent messages
     * @param command  Command passed as an argument, e.g. "shutdown -r" for restarting computer
     * @param ipNumber String retrieved from IpGetter class that is looking for ip number.
     * @param listener Callback interface object
     */
    public TCPClient(String ipNumber, int port) {
        //this.listener         = listener;
        this.ipNumber         = ipNumber;
       this.port = port;

        //this.mHandler         = mHandler;
    }

    /**
     * Public method for sending the message via OutputStream object.
     * @param message Message passed as an argument and sent via OutputStream object.
     */
    public void sendMessage(String message){
        try {
            this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            //Create BufferedReader object for receiving messages from server.
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            if (out != null && !out.checkError()) {
                out.println(message);
                out.flush();
                //  mHandler.sendEmptyMessageDelayed(MainActivity.SENDING, 1000);
                Log.d(TAG, "Sent Message: " + message);

            }
        }       //         mHandler.sendEmptyMessageDelayed(MainActivity.SENT, 3000);
        catch (IOException i){
            i.getMessage();
        }
    }

    /**
     * Public method for stopping the TCPClient object ( and finalizing it after that ) from AsyncTask
     */
    public void stopClient(){
        Log.d(TAG, "Client stopped!");
    //    mRun = false;
    }
    public void run() {
      //  Log.d("socket", "connected");

  //      mRun = true;

        try {
            // Creating InetAddress object from ipNumber passed via constructor from IpGetter class.
            InetAddress serverAddress = InetAddress.getByName(ipNumber);

            Log.d(TAG, "Connecting...");

             socket = new Socket(serverAddress, port);



        } catch (Exception e) {

            Log.d(TAG, "Error", e);
 //           mHandler.sendEmptyMessageDelayed(MainActivity.ERROR, 2000);

        }



    }
}