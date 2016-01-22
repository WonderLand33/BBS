
$(function(){

    marked.setOptions({
        highlight: function (code) {
            $.ljs($._host+'/static/plugin/md/register.js');
            return hljs.highlightAuto(code).value;
        }
    });

    var loadMc163 = function(){
        if(window.d)
            d.init();
        else
            $.ljs($._host+'/static/js/m.js');
    }


    /**
     * left
     */
    $.post($._host + "/getTypes.json", {}).done(function (r) {
        var color = ["sp", "ps", "ht" ,"fl","sa","cs"];
        var _hh, _mm = '<a>';
        $(r).each(function(i,x){
            _hh = $(_mm).addClass(color[i]).addClass('a-fadein').text(x.name).attr("href", "#t/"+x.id).attr("title", x.desc);
            $('#h').append(_hh);
        });
        pop();
    });
    window.initLeft=function(i){
        document.title = $(this).hasClass('home')?"吹水吧": $(this).text();
        M.empty();
        window.init(1,i);
    }

    /**
     * right
     */

    var uif = function () {
        $.post($._host + '/loginInfo.json').done(function (r) {
            window.er = r;
            if (er != '' && er != 'undefined' && er != null) {
                $.get($._host + "/static/template/user.php", function (_d) {
                    _d = _d.trim().format(r);
                    _d = _d.replace(/img r/, 'img src');
                    $('#u').html(_d);
                });
            } else {
                $.get($._host + "/static/template/login.php", function (r) {
                    $('#u').empty().append(r);
                });
            }
        });
    },lL = function () {
        $.get($._host + "/static/template/login.php", function (r) {
            $('#u').empty().append(r);
        });
    }, lR = function () {
        $.get($._host + "/static/template/register.php", function (r) {
            $('#u').empty().append(r);
        });
    }
    uif();


    $.get($._host + "/static/template/link.json", function (r) {
        $('#link').append('<ul class="hp c"> <li class="hr"><h2>/*链接*/</h2></li>' +
            '<li class="hr"><h2></h2></li></ul>');
        var li='<li><a href="{url}" target="_blank">'+
            '<h3 class="link_h">{name}</h3>'+
            '<span>{description}</span>'+
            '</a></li>',htm='';
        $.each(r.friend,function(i,x){
            htm+=li.format(x);
        });
        $('.hr:eq(0)').after(htm);
    });

    $(document).on('click', '#loginBtn', function () {
        if (!$('#Ul')[0].checkValidity()) {
            $('#Ul')[0].reportValidity();//tips
            return;
        }
        var param = $('#Ul').serialize();
        $.post($._host + "/login.json", param).done(function (r) {
            if (r.code < 0) {
                alert(r.msg);
            } else {
                $.get($._host + "/static/template/user.php", function (dom) {
                    var _d = dom.trim();
                    _d = _d.format(r.user);
                    _d = _d.replace(/img r/, 'img src');
                    $('#u').html(_d);
                });
            }
        });

    });

     // 一大波委托来袭
    $(document).on('click', '.Greg', function () {lR();});
    $(document).on('click', '.Glogin', function () {lL();});
    $(document).on('click', '.close', function () {lL();});

    $(document).on('click', '#joinBtn', function () {
        if (!$('#Ur')[0].checkValidity()) {
            $('#Ur')[0].reportValidity();//tips
            return;
        }
        var param = $('#Ur').serialize();
        $.post($._host + '/register.json', param).done(function (r) {
            if(r.errCode<0){
                alert(r.errMsg);
            }else{
                uif();
            }
        });
    });


    /**
     * M
     */

    window.M=$('#m'),cur=1;M.empty();
    //$(M).html('<p class="loading">载入中...</p>');
    window.init = function(p,t){
        $.post($._host+'/getList.htm',{currentPageNo:p,pageSize:20,typeId:t}).done(function(r) {
            r = r.trim();
            var art = $.trim(r),_ct = new Date().getTime();
            art = $(art).attr('f',_ct);
            M.append($(art));
            $("#m ul[f='"+_ct+"']").find("li img").each(function(i,x){
                $(x).attr("src",$(x).attr("r"));
            });

            $('.d').each(function(i,x){
                $(x).text($.re_date(Number($(x).attr("t"))));
            });

            /*var nodata = '<p class="loading empty">没有更多啦OwQ</p>';
            $(r).find('.loading.empty')
            if($('.loading.empty').length == 0){

            }*/

            window.onscroll=function(){
                if($('.loading.empty').length > 0)
                    return window.onscroll=null;
                if($(this).scrollTop() + $(window).height() + 20 >= $(document).height() && $(this).scrollTop() > 20){
                    window.init(cur+=1);
                }
            }

        });
    };


    window.read = function(id){
        window.onscroll=null;
        window.M=$('#m');
        M.html('<p class="loading">载入中...</p>');var coder,msg = '<li id="{id}" class="a-fadein">' +
            '<b>{nikename}<img class="avatar" src="{headImg}"></b>' +
            '<span class="ua">{ua}</span>' +
            '<p class="entry">{msg}</p><i>{l}</i><p class="ctrl c">' +
            '<span class="date">{createTime}</span><span data-name="{nikename}" class="fo">回复</span>' +
            '<span class="plus" cid="{id}">{up}</span></p></li>',rm;
        $.ajax({
            type: 'POST',
            url: $._host+'/getArticle.json',
            data: {"id":id},
            success: function(r){
                //r = r.trim();
                if (r && r != '') {
                    var _d,_s;
                    r.createTime= $.re_date(Number(new Date(r.createTime))/1000);
                    M.empty();
                    ""
                    _d = "<div class=\"P a-fadein\">\n" +
                        "                  <h1>{title}</h1>\n" +
                        "                  <div class=\"entry\">{body}</div>\n" +
                        "                  <p class=\"ctrl c\">\n" +
                        "                  <b><img class=\"avatar\" src=\"{headImg}\">{nikename}</b>\n" +
                        "                  <span class=\"date\">{createTime}</span>\n" +
                        "                  <span class=\"plus\" pid=\"{id}\">{up}</span>\n" +
                        "                  <span class=\"ua\">{ua}</span>\n" +
                        "                  <span></span>\n" +
                        "                  </p>\n" +
                        "                  </div>";

                    r.body = $.md(r.body);r.body=r.body.enTxt().enHtml();
                    r.title=r.title.enTxt();_s = _d.format(r);M.html(_s);
                    $(M).find('p>img').after('<br/>');
                    coder=$('pre code');if(coder) coder.addClass("hljs");
                    document.title = r.title;
                } else {
                    M.innerHTML = '<p class="loading">服务器挂了 OwQ</p>';
                }

                //加载评论
                var wp = '<ul class="C"><li class="loading">载入中...</li></ul>';
                M.append(wp);
                $.post($._host+'/message.json', {aid: id}, function (vv) {
                    if(vv.length==0){
                        $('.C>.loading').text('沙发还在，还不快抢？');
                    }
                    else{
                        $('.C .loading').remove();
                        $(vv).each(function(i,x){
                            x.createTime= $.re_date(new Date(x.createTime).getTime()/1000);
                            x.l='#'+(i+1);
                            x.msg= $.md(x.msg).enTxt().enHtml();
                            rm=msg.format(x);$('.C').append(rm);
                        });
                        $('.fo').click(function(){
                            var name = $(this).attr('data-name');
                            //if(name===$('.user_link:eq(0)').find('k').text()) return;
                            $('textarea').val($('textarea').val()+'@'+$(this).attr('data-name')+' ');
                            $('textarea').focus();
                        });
                    }
                });

                //框
                $.get($._host + "/static/template/reply.php", function (r) {
                    setTimeout(function(){
                        $.ljs($._host+"/static/plugin/up.js");
                    },1000);
                    M.append(r);$('.smile.c li').click(function(){
                        $('textarea').val($('textarea').val()+$(this).text());
                    });
                    $.ph();
                    $('#replyBtn').click(function(){
                        var data = {aid:id,msg:$('#Msg').val(),ua: $.ua()};
                        $.post($._host+'/reply.json',data,function(r){
                            if(r.errCode>0){
                                $('textarea').val('');
                                _l = Number($('.C i').last().text().replace('#',''))+1;
                                r.reply.headImg=$('.user_link:eq(0)').find('img').attr('src');
                                r.reply.nikename=$('.user_link:eq(0)').find('k').text();
                                r.reply.up=0;
                                r.reply.createTime= $.re_date(new Date(r.reply.createTime).getTime()/1000);
                                r.reply.l='#'+_l;
                                r.reply.msg =$.md(r.reply.msg).enTxt().enHtml();
                                rm=msg.format(r.reply);
                                $('.C>.loading').remove();
                                $('.C').append(rm);
                            }else{ alert(r.errMsg);}
                        });
                    });
                });
                if($('.mc').length>0)
                    loadMc163();
            },
            dataType: 'json',
            async: true
        });

    }

    var
        ROOT='',F='',hs,title
    en=encodeURIComponent,
        de=decodeURIComponent,
        pop=function(){
            hs = location.hash;
            if(hs){
                ROOT=de(hs.substr(3));
                F=hs.substr(1,1);

                if(F==="t"){
                    $('#h a').removeClass('a');
                    window.initLeft(ROOT);
                    $('#h a[href="'+hs+'"]').addClass('a');
                    title = $('#h a[href="'+hs+'"]').text();
                }else if(F==="p") {
                    window.read(ROOT);
                }else if(hs==="#add"){
                    title = "#新的话题";
                    add();
                }else{
                    $('#h a').removeClass('a');
                    window.initLeft('');
                    $('#h .home').addClass('a');
                    title = "吹水吧";
                }
                document.title = title;
            }else{
                window.init()
            }
        };
    window.onhashchange=pop;



    $(document).on('click', '#m', function(e){
        e=e||window.event;
        obj=e.srcElement||e.target;
        if(obj.className=='plus'){
            var id;
            if(id=obj.getAttribute('pid'))
                $.post($._host + "/aup.json",{aid: id});
            else if(id=obj.getAttribute('cid'))
                $.post($._host + "/rup.json",{rid: id});
            obj.innerHTML=obj.innerHTML*1+1;
            obj.className='plus a';
        }
    })


    console.log("@橙子chan 2015/9/9");


});

function add() {
    setTimeout(function(){
        $.ljs($._host+"/static/plugin/up.js");
    },1000); setTimeout(function(){
        $.ljs($._host+"/static/plugin/up.js");
    },1000);
    M.html('<p class="loading">载入中...</p>');

    $.get($._host + "/static/template/push.php", function (r) {
        M.html($(r.trim()).addClass('a-fadein'));
        $.ph();
        $.post($._host + "/getTypes.json", {}).done(function (r) {
            var color = ["sp", "ps", "ht", "fl", "sa", "cs"];
            var _hh, _mm = '<b>';
            $(r).each(function (i, x) {
                _hh = $(_mm).addClass(color[i]).text(x.name).attr("k", x.id);
                $('.A-tag').append(_hh);
                $('.A-tag b').click(function () {
                    $('.A-tag .a').removeClass('a');
                    $(this).addClass('a');
                    $('input[name="tag"]').val($(this).attr('k'));
                });
            });
        });
    });
}

$(document).on('click', '#A .btn', function () {
    var tag = $('input[name="tag"]').val();
    if (!tag || tag == "" || tag == "undefined") {
        alert('请选择文章类型');
        return;
    }

    if (!$('#A')[0].checkValidity()) {//验证不通过
        $('#A')[0].reportValidity();//tips
        return;
    }

    var param = {
        tag: tag,
        title: $('input[name="title"]').val(),
        message: $('textarea[name="message"]').val(),
        ua: $.ua(navigator.userAgent)
    }

    $.post($._host+'/add.json',param).done(function(c){
        if(c.errCode==-1){
            alert(c.errMsg);
            console.log(c);
        }else{
            location.hash = '#p/'+ c.id;
        }

    });

});


$(document).on('click','#headImg',function(){
    var headImgFile = $('#headImgFile');

    if(headImgFile.length===0){
        var input = $(document.createElement('input'));
        input.attr("id", "headImgFile");
        input.attr("type", "file");
        input.css("display", "none");
        $('body').append(input);
        headImgFile = input;
    }

    $.ljs($._host+"/static/js/h.js");
    headImgFile.trigger('click');

    headImgFile.on('change',function(){
       HH.handleFile(headImgFile[0].files[0]);
        $.post($._host+'/setHeadImg.json',{url:HH.U});
    })

});

