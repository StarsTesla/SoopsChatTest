var friends = new Map(); //<friendName, ......>
var windows = new Set();
var curFriend = null;  //当前正在"聊天"的朋友
var me = null;  //我
//初始化个人信息
$.ajax({
	url: "http://localhost:8080/api/user/getMe",
	method: "GET",
	success: function (res) {
		console.log(res);
		me = res;

		$("#Myinfo").find("img").attr("src", me.avatar.realName);
		$("#Myinfo").find("#MyName").text(me.username);
	}
})
//初始化所有friend
$.ajax({
	url: "http://localhost:8080/api/friend/getFriends",
	method: "GET",
	success: function (res) {
		console.log("get friends success");
		$.each(res, function (i, item) {

			item.line = "Offline";
			item.newMsg = 0;
			friends.set(item.username, item);

			//初始化所有好友聊天框显示代码
			var chatBox = $("<div></div>");
			var messageBox = $("#messageBox");
			//chatBox.text(item.username);
			messageBox.append(chatBox);
			chatBox.addClass(item.username);  //加上类名来进行好友标示
			chatBox.hide();


			$.ajax({
				url: "http://localhost:8080/api/chat/getChatHistory",
				data: {
					"friendName": item.username,
				},
				method: "GET",
				success: function (res) {
					if (res.length <= 0) {
						$("#messageBox").find("." + item.username).append("<center class='mt-5'>没有其他聊天记录了</center>");
					}
					console.log(res);
					$.each(res.reverse(), function (i, msg) {
						//TODO 判断发的人是谁
						if (msg.fromUser === me.username) //
						{
							var temple = $("#buble-template").html(); //生成一个气泡
							var div = $('<div></div>');
							div.html(temple);
							div.find("p").text(me.username);
							div.find("img").attr("src", me.avatar.realName);
							div.find("span").text(msg.content);
							chatBox.append(div);
						} else {
							var temple = $("#buble-template").html(); //生成一个气泡
							var div = $('<div></div>');
							div.html(temple);
							div.find("p").text(msg.fromUser);
							div.find("img").attr("src", (friends.get(msg.fromUser).avatar.realName));
							div.find("span").text(msg.content);
							chatBox.append(div);
						}


					})

				}
			})


		})
		console.log(friends);

	},
	error: function () {
		console.log("Error of getting friends");
	},
	fail: function () {
		console.log("Fail of getting friends");
	}
})


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

	if (respond.type === "image") {
		//TODO 做一个img样式显示到对话框
	} else if (respond.type === "text") {
		var temple = $("#buble-template").html(); //生成一个气泡
		//生成前端显示
		var div = $('<div></div>');
		div.html(temple);
		div.find("p").text(respond.fromUser);
		div.find("img").attr("src", (friends.get(respond.fromUser).avatar.realName));
		div.find("span").text(respond.content);
		var box = $("#messageBox").find("." + respond.fromUser);
		box.append(div);
		$("#messageBox").scrollTop($("#messageBox").prop('scrollHeight') - $("#messageBox").height());
		$("#message-toSend").val("");

		if (!windows.has(respond.fromUser)) {
			var box = $("#friendBox");
			var temple = $("#leftWindowtemplate").html();
			var div = $("<div></div>");
			div.html(temple);
			div.find(".avatar,.rounded-circle").find("img")
				.attr("src", friends.get(respond.fromUser).avatar.realName);
			div.find(".d-block")
				.text(respond.fromUser);
			div.addClass(respond.fromUser);
			windows.add(div.find(".d-block").text());

			if (friends.get(respond.fromUser).line === "Online") {
				div.find(".col,.ml-n2").find("span").removeClass("text-danger");
				div.find(".col,.ml-n2").find("span").addClass("text-success");
				div.find(".col,.ml-n2").find("small").text("Online");
				div.find(".badge").text(friends.get(respond.fromUser).newMsg);
			}

			if (friends.get(respond.fromUser).newMsg > 0) {
				div.find(".badge").removeClass("badge-light");
				div.find(".badge").addClass("badge-danger");

			}

			box.append(div);
			box.scrollTop(box.prop('scrollHeight') - box.height());

			if (curFriend != null) {
				$("#messageBox").find("." + curFriend).hide();
				//TODO 框色消失
			}

			curFriend = respond.fromUser;

			$("#messageBox").find("." + respond.fromUser).show();
			//TODO 框色显示
			$("#input-container").show();
			$("#messageBox").show();
		}


		//修改左边好友选项中新消息条数
		if (curFriend != respond.fromUser) {
			$("#friendBox").find("." + respond.fromUser).find(".badge").removeClass("badge-light");
			$("#friendBox").find("." + respond.fromUser).find(".badge").addClass("badge-danger");
			var cur = $("#friendBox").find("." + respond.fromUser).find(".badge").text();
			cur++;
			$("#friendBox").find("." + respond.fromUser).find(".badge").text(cur);
		}

	} else if (respond.type === "line") {
		friends.get(respond.fromUser).line = respond.content;
		if (respond.content === "Online") {
			$("#friendBox").find("." + respond.fromUser).find(".text-sm,.text-danger").addClass("text-success");
			$("#friendBox").find("." + respond.fromUser).find(".text-sm,.text-danger,.text-success").removeClass("text-danger");
			$("#friendBox").find("." + respond.fromUser).find("small").text("Online");


			$("#modal-addwindow").find("." + respond.fromUser).find("#addW-info").find("span").addClass("text-success");
			$("#modal-addwindow").find("." + respond.fromUser).find("#addW-info").find("span").removeClass("text-danger");
			$("#modal-addwindow").find("." + respond.fromUser).find("#addW-info").find("small").text("Online");

			$("#alert-line").find("strong").text(respond.fromUser);
			$("#alert-line").find(".badge").text(respond.content);
			$("#alert-line").removeClass("alert-danger");
			$("#alert-line").addClass("alert-success");
			$("#alert-line").show().delay(3000).fadeOut();
		} else {
			$("#friendBox").find("." + respond.fromUser).find(".text-sm,.text-success").addClass("text-danger");
			$("#friendBox").find("." + respond.fromUser).find(".text-sm,.text-danger,.text-success").removeClass("text-success");
			$("#friendBox").find("." + respond.fromUser).find("small").text("Offline");

			$("#modal-addwindow").find("." + respond.fromUser).find("#addW-info").find("span").addClass("text-danger");
			$("#modal-addwindow").find("." + respond.fromUser).find("#addW-info").find("span").removeClass("text-success");
			$("#modal-addwindow").find("." + respond.fromUser).find("#addW-info").find("small").text("Offline");

			$("#alert-line").find("strong").text(respond.fromUser);
			$("#alert-line").find(".badge").text(respond.content);
			$("#alert-line").removeClass("alert-success");
			$("#alert-line").addClass("alert-danger");
			$("#alert-line").show().delay(3000).fadeOut();
		}


	}
}

//时间模版 yyyy-MM-dd HH:mm:ss
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

function switchWindow(e) {
	var friendName = $(e).text();
	friendName = friendName.replace(/\s*/g, "");
	if (curFriend != null) {
		$("#messageBox").find("." + curFriend).hide();
	}
	$("#messageBox").find("." + friendName).show();
	if (curFriend != friendName && $("#friendBox").find("." + friendName).find(".badge").hasClass("badge-danger")) {
		$("#friendBox").find("." + friendName).find(".badge").removeClass("badge-danger");
		$("#friendBox").find("." + friendName).find(".badge").addClass("badge-light");
		$("#friendBox").find("." + friendName).find(".badge").text(0);


	}

	curFriend = friendName;
}

$("#modal-addwindow").on("show.bs.modal", function () {
	var box = $("#modal-friend-Box");
	box.empty();
	friends.forEach(function (value, key) {
		//初始化所有朋友

		var temple = $("#addWindowtemplate").html();
		var div = $('<div></div>');
		div.html(temple);
		div.find("#addW-avatar").find("a").find("img").attr("src", value.avatar.realName);
		div.find("#addW-info").find("a").text(key);
		if (value.line === "Online") {
			div.find("#addW-info").find("div").find("span").removeClass("text-danger");
			div.find("#addW-info").find("div").find("span").addClass("text-success");
			div.find("#addW-info").find("div").find("small").text("Online");
		}
		div.addClass(key);
		box.append(div);
	});


})

function sendText() {
	var temple = $("#buble-template").html();
	//console.log(temple);
	var content = $("#message-toSend").val();
	//content = content.replace(/\s*/g,"");
	if (content != "") {

		var now = new Date().Format('yyyy-MM-dd HH:mm:ss');
		var message = {
			'content': content,
			'fromUser': '',
			'toUser': curFriend,
			'createdOn': now,
			'type': 'text'
		}
		message = JSON.stringify(message);
		console.log(message);
		ws.send(message);

		//生成前端显示
		var div = $('<div></div>');
		div.html(temple);
		div.find("span").text(content);
		div.find("p").text(me.username);
		div.find("img").attr("src", (me.avatar.realName));
		var box = $("#messageBox").find("." + curFriend);
		box.append(div);
		$("#messageBox").scrollTop($("#messageBox").prop('scrollHeight') - $("#messageBox").height());
		$("#message-toSend").val("");
	} else {
		alert("输入不能为空");
	}
}

$("#message-toSend").on("keydown", function (e) {
	if (e.keyCode == 13) {
		sendText();
	}
})

function sendFile(base64) {
	// var content = $("#msgBox").val();
	var now = new Date().Format('yyyy-MM-dd HH:mm:ss');
	//console.log( typeof base64);
	var message = {
		'content': base64,
		'fromUser': '',
		'toUser': curFriend,
		'createdOn': now,
		'type': 'image'
	}
	//console.log(message);

	message = JSON.stringify(message);

	//  console.log(typeof message);
	ws.send(message);

	//$("#msgBox").val("");
}

// 以下代码由 w晚风 提供
// 来自： https://www.jianshu.com/p/b806e01d34bd
// 第一个参数就是原来的字符串，第二个是宽度，第三个就是回调方法
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

// 以上代码由 w晚风 提供
// 来自： https://www.jianshu.com/p/b806e01d34bd

function addWindow(e) {
	var friendName = $(e).parent().parent().find("#addW-info").find("a").text();
	if (!windows.has(friendName)) {
		var box = $("#friendBox");
		var temple = $("#leftWindowtemplate").html();
		var div = $("<div></div>");
		div.html(temple);
		div.find(".avatar,.rounded-circle").find("img")
			.attr("src", $(e)
				.parent()
				.parent()
				.find("#addW-avatar")
				.find("img").attr("src"));
		div.find(".d-block")
			.text($(e)
				.parent()
				.parent()
				.find("#addW-info")
				.find("a").text());
		div.addClass(friendName);
		windows.add(div.find(".d-block").text());

		if (friends.get(friendName).line === "Online") {
			div.find(".col,.ml-n2").find("span").removeClass("text-danger");
			div.find(".col,.ml-n2").find("span").addClass("text-success");
			div.find(".col,.ml-n2").find("small").text("Online");
			div.find(".badge").text(friends.get(friendName).newMsg);
		}

		if (friends.get(friendName).newMsg > 0) {
			div.find(".badge").removeClass("badge-light");
			div.find(".badge").addClass("badge-danger");

		}

		box.append(div);
		box.scrollTop(box.prop('scrollHeight') - box.height());

		if (curFriend != null) {
			$("#messageBox").find("." + curFriend).hide();
			//TODO 框色消失
		}
		curFriend = friendName;


		$("#messageBox").find("." + friendName).show();
		//TODO 框色显示
		$("#input-container").show();
		$("#messageBox").show();

	} else {
		alert("请不要重复添加了");
	}


}

function deleteWindow(e) {
	//TODO 优化代码
	var ppp = $(e).parent().parent().parent().parent();
	ppp.remove(); //移除左边好友选项
	//console.log(ppp.html());
	var friendName = $(e).parent().parent().find(".col,.ml-n2").find("a").text();
	friendName = friendName.replace(/\s*/g, ""); //去除多余空格
	//console.log(friendName);
	windows.delete(friendName);  //从集合中移除窗口，但窗口代码并没有移除

	//$("#friendBox").find("." + friendName).remove(); //左边好友选项移除

	if (friendName === curFriend) //如果是当前好友，就隐藏一下
	{
		curFriend = null;
		$("#messageBox").hide();
		$("#input-container").hide();
	}


}

function deleteFriend(e) {
	var friendName = $(e).parent().parent()
		.find("#addW-info").find("a").text();
	$.ajax({
		url: "http://localhost:8080/api/friend/deleteFriend",
		data: {"friendName": friendName},
		method: "POST",
		success: function (res) {
			console.log(res);
			alert(res.msg);
		}
	})

	$("#messageBox").find("." + friendName).remove(); //移除聊天框代码
	$("#friendBox").find("." + friendName).remove(); //移除好友选项代码
	if (friendName == curFriend) {
		$("#messageBox").hide();
		$("#input-container").hide();
	}

	friends.delete(friendName);
	windows.delete(friendName);


}

