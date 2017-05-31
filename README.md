# Android-webRTC and NodeJS client

> 一个webRTC Demo，你可以非常简单的运行起来它，之后我会继续优化的，现在只能实现P2P的聊天功能


需要依赖：

```
compile 'com.android.support:appcompat-v7:22.0.0'
compile 'com.github.nkzawa:socket.io-client:0.4.2'    //webSocket相关
compile 'io.pristine:libjingle:8871@aar'    //webRTC官方aar包
```


如何运行它：

首先我们的服务端需要依赖于nodeJS

1. ``git clone git@github.com:linsir6/webRTC-android-demo-and-Server.git``
2. 用Android-studio打开Android，需要修改``app->strings.xml``下面的``host``和``prot``，分别修改成电脑的局域网下的ip和运行Node服务端的端口号
3. ``cd NodeServer``
4. ``npm install``
5. ``npm start``
6. ``先在手机上打开apk``
7. 在浏览器端打开``http://127.0.0.1:3000/``
8. 点击``star Camera``
9. 点击``call``

![如何启动](https://ws1.sinaimg.cn/large/006tKfTcly1fg2fttsj5pj31kw13iteg.jpg)


![浏览器端效果图](https://ws1.sinaimg.cn/large/006tKfTcly1fg2fui4783j31kw13hq7i.jpg)

![手机端效果图](https://ws2.sinaimg.cn/large/006tKfTcly1fg2fwwtcfaj30e00p2aan.jpg)



