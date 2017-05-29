package com.dotengine.linsir.lin_webrtc_demo;

import android.content.Intent;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.webrtc.MediaStream;
import org.webrtc.VideoRenderer;
import org.webrtc.VideoRendererGui;

import java.util.List;

public class MainActivity extends AppCompatActivity implements WebRtcClient.RtcListener {


    private final static int VIDEO_CALL_SENT = 666;
    private static final String VIDEO_CODEC_VP9 = "VP9";
    private static final String AUDIO_CODEC_OPUS = "opus";


    private static final int LOCAL_X_CONNECTING = 0;
    private static final int LOCAL_Y_CONNECTING = 0;
    private static final int LOCAL_WIDTH_CONNECTING = 100;
    private static final int LOCAL_HEIGHT_CONNECTING = 100;

    private static final int LOCAL_X_CONNECTED = 72;
    private static final int LOCAL_Y_CONNECTED = 72;
    private static final int LOCAL_WIDTH_CONNECTED = 25;
    private static final int LOCAL_HEIGHT_CONNECTED = 25;

    private static final int REMOTE_X = 0;
    private static final int REMOTE_Y = 0;
    private static final int REMOTE_WIDTH = 100;
    private static final int REMOTE_HEIGHT = 100;
    private VideoRendererGui.ScalingType scalingType = VideoRendererGui.ScalingType.SCALE_ASPECT_FILL;

    private GLSurfaceView vsv;
    private VideoRenderer.Callbacks localRender;
    private VideoRenderer.Callbacks remoteRender;
    private WebRtcClient client;
    private String mSocketAddress;
    private String callerId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSocketAddress = "http://" + getResources().getString(R.string.host);
        mSocketAddress += (":" + getResources().getString(R.string.port) + "/");

        vsv = (GLSurfaceView) findViewById(R.id.glview_call);
        vsv.setPreserveEGLContextOnPause(true);
        vsv.setKeepScreenOn(true);
        VideoRendererGui.setView(vsv, new Runnable() {
            @Override public void run() {
                init();
            }
        });

        remoteRender = VideoRendererGui.create(REMOTE_X,
                REMOTE_Y,
                REMOTE_WIDTH,
                REMOTE_HEIGHT,
                scalingType,
                false);

        localRender = VideoRendererGui.create(LOCAL_X_CONNECTING,
                LOCAL_Y_CONNECTING,
                LOCAL_WIDTH_CONNECTING,
                LOCAL_HEIGHT_CONNECTING,
                scalingType,
                true);

        final Intent intent = getIntent();
        final String action = intent.getAction();

        if (Intent.ACTION_VIEW.equals(action)) {
            final List<String> segments = intent.getData().getPathSegments();
            callerId = segments.get(0);
        }


    }

    private void init() {
        Point displaySize = new Point();
        getWindowManager().getDefaultDisplay().getSize(displaySize);
        PeerConnectionParameters params = new PeerConnectionParameters(
                true, false, displaySize.x, displaySize.y, 30, 1, VIDEO_CODEC_VP9, true, 1, AUDIO_CODEC_OPUS, true);

        client = new WebRtcClient(this, mSocketAddress, params, VideoRendererGui.getEGLContext());


        startCam();
    }


    @Override protected void onPause() {
        super.onPause();
        vsv.onPause();
        if (client != null) {
            client.onPause();
        }
    }


    @Override protected void onResume() {
        super.onResume();
        vsv.onResume();
        if (client != null) {
            client.onResume();
        }
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        if (client != null) {
            client.onResume();
        }
    }

    @Override public void onCallReady(String callId) {
        if (client != null) {
            try {
                answer(callId);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else {
            call(callId);
        }
    }


    public void answer(String callerId) throws JSONException {
        client.sendMessage(callerId,"init",null);
        startCam();

    }


    public void call(String callId){
        Intent msg = new Intent(Intent.ACTION_SEND);
        msg.putExtra(Intent.EXTRA_TEXT,mSocketAddress+callerId);
        msg.setType("text/plain");
        startActivityForResult(Intent.createChooser(msg,"Call someone :"),VIDEO_CALL_SENT);


    }

    @Override public void onStatusChanged(final String newStatus) {
        runOnUiThread(new Runnable() {
            @Override public void run() {
                Toast.makeText(getApplicationContext(), newStatus, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override public void onLocalStream(MediaStream localStream) {
        localStream.videoTracks.get(0).addRenderer(new VideoRenderer(localRender));
        VideoRendererGui.update(localRender,
                LOCAL_X_CONNECTED,
                LOCAL_Y_CONNECTING,
                LOCAL_WIDTH_CONNECTING,
                LOCAL_HEIGHT_CONNECTING,
                scalingType);
    }

    @Override public void onAddRemoteStream(MediaStream romoteStream, int endPoint) {
        VideoRendererGui.update(remoteRender,
                REMOTE_X,
                REMOTE_Y,
                REMOTE_WIDTH,
                REMOTE_HEIGHT,
                scalingType
        );


        VideoRendererGui.update(localRender,
                LOCAL_X_CONNECTED,
                LOCAL_Y_CONNECTED,
                LOCAL_WIDTH_CONNECTED,
                LOCAL_HEIGHT_CONNECTED,
                scalingType);


    }

    @Override public void onRemoveRemoteStream(int endPoint) {
        VideoRendererGui.update(localRender,
                LOCAL_X_CONNECTING, LOCAL_Y_CONNECTING,
                LOCAL_WIDTH_CONNECTING, LOCAL_HEIGHT_CONNECTING,
                scalingType);
    }


    public void startCam() {

        client.start("android_test");
    }


}
