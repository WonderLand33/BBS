/**
 *  @卜卜口 6666666
 */

var UP=function(W,D,$){
	if(!W.XMLHttpRequest||!W.FileReader)
		return;

	$=function(i){return D.querySelector(i)};
	$.D={
		m:function(i){return D.createElement(i)},
		d:function(o){return o.parentNode.removeChild(o)},
		a:function(p,i){!i&&(i=p)&&(p=D.body); return p.appendChild(i)},
		b:function(p,i){p.insertBefore(i,p.childNodes[0])},
		c:function(i){return i.cloneNode(1)}
	};

	var 
	_html=$('html'),
	pace=$('#pace');

	if(!pace){
		pace=$.D.m('pace');
		pace.style.cssText='position:absolute;position:fixed;top:0;left:0;height:2px;background:#F92672;z-index:1';
		pace.id='pace';
		$.D.a(pace);
	}

	/*
	_html.ondragenter=function(e){_html.className='drop';};
	_html.ondragleave=function(e){_html.className='';};
	*/
	_html.ondragover=function(e){
		e.preventDefault();
	};
	_html.ondrop=function(e){
		e.preventDefault();
		handleFile(e.dataTransfer.files);
	};
	/*
	$('#dragF').onchange=function(){
		handleFile(this.files)
	};
	*/
	_html.onpaste=function(e){
		// 添加到事件对象中的访问系统剪贴板的接口
		var clipboardData=e.clipboardData;
		if(!clipboardData)
			return;
		var items=clipboardData.items;

		if(!items)
			return;


		item=items[0];
			// 保存在剪贴板中的数据类型

		var types=clipboardData.types||[];

		for(var i=0;i<types.length;i++){
			if(types[i]==='Files'){
				item=items[i];
				break;
			}
		}
		if(item&&item.kind==='file'&&item.type.match(/^image\//i)){           
			handleFile(item.getAsFile());
		}
	};

	var handleFile=function(files){
		console.log(files);
		if(files.length==undefined)
			files=[files];
		
		if(files.length==0){
			//alert('如果拖图像进来我会很高兴哟~');
			return;
		}
		
		var 
		now=0,max=files.length,
		f=function(){

			var file=files[now];
			//console.log(file);
			if(file.type.indexOf('image')!=0){
				alert('这不是一个图像或音频！');
				return;
			}
			if(!file.size>2000000){
				alert('请上传小于2MB大小的图像！');
				return;
			}



			//alert('上传中...');


			var xhr=new XMLHttpRequest();

			if(xhr.upload)
				xhr.upload.onprogress=function(e){
					pace.style.cssText+=';width:'+e.loaded/e.total*100+'%;';
				};
			
			// 文件上传成功或是失败
			xhr.onreadystatechange=function(e){
				if(xhr.readyState==4){
					
					
					var d=JSON.parse(xhr.responseText);

					if(d.error)
						return alert(d.error);

					//console.log(d);

					//location.href='#!aff/'+d.pid;

					var imgUrl='http://ww2.sinaimg.cn/large/'+xhr.responseText.match(/[\w]{24,32}/)+'.jpg';

					
					var I=D.getElementsByTagName('textarea');
					
					if(I.length){
						I=I[0];
						MK = '![图片描述](*)';
						I.value+=(I.value?'\n':'')+MK.replace('*',imgUrl)+'\n';
						try{I.onkeydown();}catch(e){console.log(e)}
					}else{
						W.open(imgUrl,'_blank');
					}

					pace.style.cssText+=';width:0;';

					//$.D.d(pace);

					now++;

					if(now>=max){

					}else{
						f();
					}
				}
			};
			xhr.open('POST','http://x.mouto.org/wb/x.php?up&_r='+Math.random(),1);
			xhr.send(file);
			
		};
		f();
	};
	return handleFile;
}(window,document);