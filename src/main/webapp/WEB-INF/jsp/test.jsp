<%--
  Created by IntelliJ IDEA.
  User: chenPeng
  Date: 2015/9/14
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>
    <link rel="stylesheet" href="">
    <script type="text/javascript">
        var state = {},
                x = new XMLHttpRequest(),
                $ = function (i) {return document.querySelector(i)}

        window.addEventListener("popstate", function () {
            D();//执行请求
        });

        var D = function(){
            var lash=location.hash.substring(2);
            $('h1').innerText = X(lash);
        }

        var X = function (u) {
            x.open("GET", u+'.json', false);
            x.send(null);
            return x.responseText;
        }

        if(!history.pushState){//判断浏览器不支持popstate事件
            var laHash=0;//记录历史url
            setInterval(function(){
                if(laHash!=location.hash){ //判断url更新
                    D();//执行请求
                    laHash=location.hash;//记录新的历史url
                }
            },100); //100毫秒运行一次
        }

    </script>
</head>
<body>
<h2>2015年9月14日10:23:42</h2>
<h1></h1>
</body>
</html>