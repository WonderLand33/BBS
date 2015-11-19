var music163 = function (w) {
    $.lss($._host + '/static/css/mc.css');
    if (!$('audio')[0]) {
        w.Audio = new Audio()
    }
    w.Audio.addEventListener("ended", function () {
        if ($$(".mc.play")) {
            $$(".mc.play").className = "mc"
        }
    }, false);
    _remote = function (method, url, async, data, callback) {
        var x = new XMLHttpRequest();
        x.open(method, url, async);
        x.onreadystatechange = function () {
            if (x.readyState === 4) {
                if (x.status === 200) {
                    var data = {
                        text: x.responseText,
                        xml: x.responseXML
                    };
                    callback.call(this, data);
                }
            }
        }

        if (method.toLowerCase() === "post") {
            x.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            //x.setRequestHeader("Content-Length", data.length);
            //x.setRequestHeader("Connection", "close");
        }

        x.send(data);

        return x;
    },
        _event = function (d) {
            d.onclick = function () {
                if (this.className == "mc") {
                    var i;
                    if (i = $$(".mc.play,.mc.pause")) {
                        i.className = "mc"
                    }
                    this.className = "mc play";
                    w.Audio.src = d.getAttribute("i");
                    w.Audio.play()
                } else {
                    if (this.className == "mc play") {
                        this.className = "mc pause";
                        w.Audio.pause()
                    } else {
                        this.className = "mc play";
                        w.Audio.play()
                    }
                }
                return false;
            }
        }

    d = {
        init: function () {
            for (var d = $$$('.mc'), m = 0; m < d.length; m++) {
                var n = d[m].href.match(/[0-9]{5,12}$/), cur = d[m];
                cur.setAttribute('id',n);
                _event(cur);
                _remote('POST', $._host + '/w/wyy.json', true, 'id=' + n, function (data) {
                    var d = eval('(' + data.text + ')');
                    document.getElementById(d.id).setAttribute('i', d.url);
                    document.getElementById(d.id).innerHTML = "「" + d.name + "」" + d.singer;

                })
            }
        }
    };

    d.init();
    return d;

}(window)