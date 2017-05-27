package com.dotengine.linsir.lin_webrtc_demo;

/**
 *  Created by linSir 
 *  date at 2017/5/26.
 *  describe: PeerConnection的连接类
 */

public class PeerConnectionParameters {

    public final boolean videoCallEnabled;
    public final boolean loopback;
    public final int videoWidth;
    public final int videHeight;
    public final int videoFps;
    public final int videoStartBitrate;

    public final String vidoCodec;
    public final boolean videoCodecHwAcceleration;
    public final int audioStartBitrate;
    public final String audioCodec;
    public final boolean cpuOveruserDetection;


    public PeerConnectionParameters(boolean videoCallEnabled, boolean loopback, int videoWidth, int videHeight, int videoFps, int videoStartBitrate, String vidoCodec, boolean videoCodecHwAcceleration, int audioStartBitrate, String audioCodec, boolean cpuOveruserDetection) {
        this.videoCallEnabled = videoCallEnabled;
        this.loopback = loopback;
        this.videoWidth = videoWidth;
        this.videHeight = videHeight;
        this.videoFps = videoFps;
        this.videoStartBitrate = videoStartBitrate;
        this.vidoCodec = vidoCodec;
        this.videoCodecHwAcceleration = videoCodecHwAcceleration;
        this.audioStartBitrate = audioStartBitrate;
        this.audioCodec = audioCodec;
        this.cpuOveruserDetection = cpuOveruserDetection;
    }
}
