url = "ws://localhost:8080/chat"
ws = new WebSocket(url);

ws.onopen = function () {
    console.log("open");
}

ws.onclose = function () {
    console.log("close");
}

ws.onmessage = function (e) {
    // console.log(typeof e.data);
    var respond = JSON.parse(e.data);
    console.log(e.data);

    // var templ = $('#chatTemplate').html();
    // var div = $('<div></div>');
    // div.html(templ);
    // div.find('.chat-name').text(respond.fromUser);
    // div.find('.chat-timestamp').text(new Date(respond.createdOn).toLocaleString());
    // div.find('.chat-message').text(respond.content);
    // var room = $('#chatBox');
    // room.append(div);
    // room.scrollTop(room.prop('scrollHeight') - room.height());
    // console.log(raw);
}


//时间模版
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

var friend = "LB"; //测试好友

function send(base64) {
    // var content = $("#msgBox").val();
    var now = new Date().Format('yyyy-MM-dd HH:mm:ss');
    //console.log( typeof base64);
    var message = {
        'content': base64,
        'fromUser': '',
        'toUser': friend,
        'createdOn': now,
        'type': 'image'
    }
    //console.log(message);

    message = JSON.stringify(message);

    //  console.log(typeof message);
    ws.send(message);

    //$("#msgBox").val("");
}

function sendText() {
    var content = $("#msgBox").val();
    var now = new Date().Format('yyyy-MM-dd HH:mm:ss');

    var message = {
        'content': content,
        'fromUser': '',
        'toUser': friend,
        'createdOn': now,
        'type': 'text'
    }

    message = JSON.stringify(message);

    console.log(typeof message);
    ws.send(message);

    $("#msgBox").val("");
}


//第一个参数就是原来的字符串，第二个是宽度，第三个就是回调方法
function cutImageBase64(base64, w, callback) {
    var newImage = new Image();
    var quality = 0.6;    //压缩系数0-1之间
    newImage.src = base64;
    newImage.setAttribute("crossOrigin", 'Anonymous');  //url为外域时需要
    var imgWidth, imgHeight;
    newImage.onload = function () {
        imgWidth = this.width;
        imgHeight = this.height;
        var canvas = document.createElement("canvas");
        var ctx = canvas.getContext("2d");
        if (Math.max(imgWidth, imgHeight) > w) {
            if (imgWidth > imgHeight) {
                canvas.width = w;
                canvas.height = w * imgHeight / imgWidth;
            } else {
                canvas.height = w;
                canvas.width = w * imgWidth / imgHeight;
            }
        } else {
            canvas.width = imgWidth;
            canvas.height = imgHeight;
            quality = 0.6;
        }
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.drawImage(this, 0, 0, canvas.width, canvas.height);
        var base64 = canvas.toDataURL("image/jpeg", quality); //压缩语句
        // 如想确保图片压缩到自己想要的尺寸,如要求在50-150kb之间，请加以下语句，quality初始值根据情况自定
        while (base64.length / 1024 > 150) {
            quality -= 0.01;
            base64 = canvas.toDataURL("image/jpeg", quality);
        }
        // 防止最后一次压缩低于最低尺寸，只要quality递减合理，无需考虑
        while (base64.length / 1024 < 50) {
            quality += 0.001;
            base64 = canvas.toDataURL("image/jpeg", quality);
        }
        callback(base64);//必须通过回调函数返回，否则无法及时拿到该值
    }
}

function imgss(e) {
    let imgs = document.getElementById("imgs");
    var file = e.target.files[0];
    // 确认选择的文件是图片
    if (file.type.indexOf("image") == 0) {
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function (e) {
            // 图片base64化
            var newUrl = this.result;

            //这是原来的base64格式字符串
            var base64 = newUrl;

            //调用方法
            cutImageBase64(base64, 500, imageSend);

            function imageSend(base64) {
                send(base64);
            }

            //
            // //在这里根据回调回来的数据打印
            // function Imgbas(base64) {
            //     ws.send(base64);
            //     console.log(base64);
            // }

        };
    }
}
