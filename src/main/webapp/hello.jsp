<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>websocket测试</title>
    <script type="text/javascript">
        var url = "ws://" + window.location.host + "<%=request.getContextPath() %>/hello";
        //打开webSocket
        var sock = new WebSocket(url);
        //处理链接开启事件
        sock.onopen = function () {
            console.log("开启链接");
            sayHello();
        }
        //处理消息
        sock.onmessage = function (e) {
            console.log("收到消息：", e.data);
            setTimeout(function () {
                sayHello()
            }, 2000);
        }
        //链接关闭事件
        sock.onclose = function () {
            console.log("关闭链接");
        }

        //发送消息
        function sayHello() {
            console.log("发送消息");
            sock.send("hello world");
        }
    </script>
</head>
<body>
socket测试

</body>
</html>
