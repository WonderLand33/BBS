var HH = function (W, D, Q) {
    if (!W.XMLHttpRequest || !W.FileReader)
        return;

    Q = function (i) {
        return D.querySelector(i)
    };

    Q.D = {
        m: function (i) {
            return D.createElement(i)
        },
        a: function (p, i) {
            !i && (i = p) && (p = D.body);
            return p.appendChild(i)
        }
    };

    var pace = Q('#pace');

    if (!pace) {
        pace = Q.D.m('pace');
        pace.style.cssText = 'position:absolute;position:fixed;top:0;left:0;height:2px;background:#F92672;z-index:1';
        pace.id = 'pace';
        Q.D.a(pace);
    }

   h = {
       U:'',
       handleFile: function(file) {

           if (file.length == 0) {
               alert('选张图吧~ (๑• . •๑)');
               return;
           }

            var f = function () {

               if (file.type.indexOf('image') != 0) {
                   alert('这不是一个图像或音频！');
                   return;
               }
               if (!file.size > 1000000) {
                   alert('请上传小于2MB大小的图像！');
                   return;
               }

               var xhr = new XMLHttpRequest();

               if (xhr.upload)
                   xhr.upload.onprogress = function (e) {
                       pace.style.cssText += ';width:' + e.loaded / e.total * 100 + '%;';
                   };

               // 文件上传成功或是失败
               xhr.onreadystatechange = function () {
                   if (xhr.readyState == 4) {

                       var d = JSON.parse(xhr.responseText);

                       if (d.error)
                           return alert(d.error);


                       var imgUrl = 'http://ww2.sinaimg.cn/large/' + xhr.responseText.match(/[\w]{24,32}/) + '.jpg';
                        debugger
                       h.U=imgUrl;
                       var I = Q('#headImg');
                       if (I) {
                           I.src=imgUrl;
                       } else {
                           W.open(imgUrl, '_blank');
                       }

                       pace.style.cssText += ';width:0;';

                   }
               };
               xhr.open('POST', 'http://x.mouto.org/wb/x.php?up&_r=' + Math.random(), false); //直男:i.mouto.org
               xhr.send(file);

           };
           f();
       }
   }

    return h;

}(window, document)