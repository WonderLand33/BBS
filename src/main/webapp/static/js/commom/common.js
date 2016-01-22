
$(function () {


  $.ajaxSetup({
    cache: false, //禁止ajax缓存
    timeout: 30000,
   xhrFields: {
      onprogress: function (e) {
        var $pace = $('#pace');
        if (e.lengthComputable) {
          if ($pace.length === 0) {
            var pace = document.createElement('pace');
            pace.style.cssText = 'position:absolute;position:fixed;top:0;left:0;height:2px;background:#F92672;z-index:1';
            pace.id = 'pace';
            $('body').append(pace);
            $pace = $(pace);
          }
          $pace.css('width', e.loaded / e.total * 100 + '%');
          if (e.loaded === e.total) {
            $pace.css('width', 0);
          }
        }
      }
    }
  });


  // ajax 拦截
  $(document).ajaxError(function (event, jqxhr, settings, thrownError) {
    // console.log(xhr.status)
    if(thrownError === 'parsererror') {
      console.log('Requested JSON parse failed.');
    } else if(thrownError === 'timeout') {
      alert("服务器连接超时 QAQ.");
      return;
    } else if(jqxhr.status === 0) {
      alert("网络故障,请检查您的网络! T.T");
      return;
    } else {
      console.log('Uncaught Error.n' + jqxhr.responseText);
    }

  });

  try{
    document.querySelector('html');
  }catch(e){
    document.getElementById('m').innerHTML='<p class="loading">请更新现代浏览器。</p>';
  }




  $.extend({
    re_date:function(i){ //asText
      var n=new Date(),t=new Date(i*1e3);
      var s=parseInt((n-t)/1e3);
      if(s<30)return '刚刚';
      if(s<60)return s+'秒前';
      if(s<3600)return parseInt(s/60)+'分前';
      if((s<86400))return parseInt(s/3600)+'时前';
      if((s<60*60*24*7))return parseInt(s/86400)+'天前';
      if((s<60*60*24*30))return parseInt(s/604800)+'周前';
      if(t>new Date(n.getFullYear()+'-01-01'))return (t.getMonth()+1)+'月'+t.getDate()+'日';//'+(t.getHours()+1)+':'+(t.getMinutes()+1)
      return t.getFullYear()+'年'+(t.getMonth()+1)+'月'+t.getDate()+'日'//' '+(t.getHours()+1)+':'+(t.getMinutes()+1);
    },
    ua:function(i){
      i=i||navigator.userAgent;
      if(i.match(/Windows Phone/i))return 'Mango';
      if(i.match(/Windows CE/i))return 'winCE';
      if(i.match(/ipad/i))return 'iPad';
      if(i.match(/iPod/i))return 'Touch';
      if(i.match(/iphone/i))return 'iPhone';
      if(i.match(/android/i))return 'Android';
      if(i.match(/Ubuntu/i))return 'Ubuntu';
      if(i.match(/Mac OS X/i))return 'Mac OS X';
      if(i.match(/360/i))return 'Shit!';
      if(i.match(/opera minf/i))return 'Opera mini';
      if(i.match(/Chrome/i))return 'Cr';
      if(i.match(/Safarf/i))return 'Safari';
      if(i.match(/Opera/i))return 'Opera';
      if(i.match(/UCWEB/i))return 'UC';
      if(i.match(/PHP/i))return 'PHP';
      return '';
    },
    ex:function(i){
        return i.replace(/```(|[\w]+)[\r\n]{0,}([\W.\S]*?)```/mg,function(i,a,b){
          return '<pre><code class="'+a+'">'+b.replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/\'/g,"&#39;").replace(/\"/g,"&quot;")+'</code></pre>'
        })
    },
    md:function(code){
     return marked(code,function(e, c){
        if (e && e!=null){ throw e;
          console.error('marked exception:\n'+c);
        }else{
          return c;
        }

      })
    },
    ljs:function(i){

      if($('script[src="'+i+'"]').length>0) return;

       var j = document.createElement('script');
       j.src = i;
       $('head').append(j);
    },
    lss:function(i){
      var j = document.createElement('link');
      j.href = i;
      j.rel='stylesheet';
      $('head').append(j);
    },
    br:function(k){
        //return k.replace('\n', "");
    },
    ph:function(){
      var textAreas = document.getElementsByTagName('textarea');
      Array.prototype.forEach.call(textAreas, function(elem) {
        elem.placeholder = elem.placeholder.replace(/\\n/g, '\n');
      });
    }
  });
});

//end document init

/**
 * 模版方法，将字符串中预留空位以给定值填充。 "a{0}c".format("b") = abc;
 * "a{pa}c{pb}".format({pa:"PA", pb:"PB"}) = aPAcPB;
 * "a{0}c{1}d{0}e{2}f".format(["A", "B", "C"]); = aAcBdAeCf;
 */
String.prototype.format = function (value) {
  return this.replace(/\{([\w\.]*)\}/g, function (str, key) {
    var keys = key.split("."), v = value[keys.shift()];
    for (var i = 0, l = keys.length; i < l; i++) v = v[keys[i]];
    return (typeof v !== "undefined" && v !== null) ? v : "";
  });
};

String.prototype.enTxt=function(){
  return (this||'')
      //.replace(/(^\s*)|(\s*$)/g,'')
      //.replace(/&/g,'&amp;')
      //.replace(/</g,'&lt;')
      //.replace(/>/g,'&gt;')
     //.replace(/ /g,'&nbsp;')
     // .replace(/\'/g,'&#39;')
      //.replace(/\"/g,'&quot;')
      //.replace(/\n/g,'<br>')
  //.replace(/"|&|'|<|>|[\x00-\x20]|[\x7F-\xFF]|[\u0100-\u2700]/g,function($0){var c=$0.charCodeAt(0), r=['&#'];c=(c==0x20)?0xA0:c;r.push(c);r.push(';');return r.join('')})
};
String.prototype.enHtml=function(){
  return this
      //.replace(/(^\s*)|(\s*$)/g,'')
    //.replace(/(http\:\/\/|)(ww[0-9]{1}\.sinaimg\.cn\/)(?:[\w]{4,10})(\/[\w]{16,32}\.)(gif|jpg|jpeg|png)/g,"http://$2bmiddle$3$4")
     //.replace(/(http\:\/\/[0-9A-Za-z\/.#&!?%:;=_]+\.)(gif|jpg|jpeg|png)/g,"<img src=\"$1$2\">")
      .replace(/\<a href\=\"http:\/\/music\.163\.com\/\#\/song\?id\=([0-9]{5,12})[\?\w\.\=]*\"\>http:\/\/music\.163\.com\/\#\/song\?id\=([0-9]{5,12})[\?\w\.\=]*\<\/a\>/g,'<a href="//music.163.com/#/song?id=$1" target="_blank" class="mc">http://music.163.com/#/song?id=$1</a>')
      .replace(/(^|[^\"\'\]>])(http|ftp|mms|rstp|news|https|telnet)\:\/\/([0-9A-Za-z\/.#&!?%:;=\-_]+)/g,'$1<a href="$2://$3" rel="external nofollow noreferer" class="link" target="_blank">$2://$3</a>')
      .replace(/(@)([\u4e00-\u9fa5\u0800-\u4e00A-Za-z0-9\-_]{2,32})/ig,"<span class=\"at\">$1$2</span>")
      .replace(/\n/g,'<br>')
};



