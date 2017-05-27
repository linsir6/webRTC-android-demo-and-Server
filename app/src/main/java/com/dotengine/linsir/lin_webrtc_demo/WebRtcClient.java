package com.dotengine.linsir.lin_webrtc_demo;

import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;
import org.webrtc.DataChannel;
import org.webrtc.IceCandidate;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;
import org.webrtc.VideoSource;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *  Created by linSir 
 *  date at 2017/5/26.
 *  describe: webRTC client
 */

public class WebRtcClient {

    private final static String TAG = WebRtcClient.class.getCanonicalName();


    private final static int MAX_PEER = 2;
    private boolean[] endPoints = new boolean[MAX_PEER];
    private PeerConnection factory;

    private HashMap<String, Peer> peers = new HashMap<>();
    private LinkedList<PeerConnection.IceServer> iceServers = new LinkedList<>();


    private PeerConnectionParameters pcParams;
    private MediaConstraints pcConstraints = new MediaConstraints();
    private MediaStream localMS;
    private VideoSource videoSource;

    private RtcListener mListener;

    private Socket client;


    public interface RtcListener {
        void onCallReady(String callId);

        void onStatusChanged(String newStatus);

        void onLocalStream(MediaStream localStream);

        void onAddRemoteStream(MediaStream romoteStream, int endPoint);

        void onRemoveRemoteStream(int endPoint);

    }


    private interface Command {

        void execute(String peerId, JSONObject payload) throws JSONException;

    }

    private class CreateOfferCommand implements Command {

        @Override public void execute(String peerId, JSONObject payload) throws JSONException {

            Peer peer = peers.get(peerId);
            peer.pc.createOffer(peer,pcConstraints);


        }
    }








    private class Peer implements SdpObserver,PeerConnection.Observer{

        private PeerConnection pc;
        private String id;
        private int endPoint;

        @Override public void onSignalingChange(final PeerConnection.SignalingState sdp) {

            try{
                JSONObject payload = new JSONObject();
                payload.put("type",sdp)
            }

        }

        @Override public void onIceConnectionChange(PeerConnection.IceConnectionState iceConnectionState) {

        }

        @Override public void onIceGatheringChange(PeerConnection.IceGatheringState iceGatheringState) {

        }

        @Override public void onIceCandidate(IceCandidate iceCandidate) {

        }

        @Override public void onAddStream(MediaStream mediaStream) {

        }

        @Override public void onRemoveStream(MediaStream mediaStream) {

        }

        @Override public void onDataChannel(DataChannel dataChannel) {

        }

        @Override public void onRenegotiationNeeded() {

        }

        @Override public void onCreateSuccess(SessionDescription sessionDescription) {

        }

        @Override public void onSetSuccess() {

        }

        @Override public void onCreateFailure(String s) {

        }

        @Override public void onSetFailure(String s) {

        }
    }




}


































