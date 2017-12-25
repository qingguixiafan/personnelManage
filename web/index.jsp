<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
    <button onclick="clickEvent()">点击测试</button>
  </body>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
  function clickEvent() {
    $.ajax({
      type: 'POST',
      url: 'http://localhost:8686/personnelManage/testServlet.action',
      dataType: 'json',
      contentType: 'application/x-www-form-urlencoded',
      success: function(data){
        console.log(data);
      },
      error: function(jqXHR){
        console.log("fail");
      },
    })
  }

  function clickEvent_Low() {
    //然后给服务器发送用户输入的内容,因为采用ajax异步发送数据,
    //所以使用XmlHttp对象
    xmlHttp = creatXMLHttp();
    //给服务器发送数据
    var url = "http://localhost:8686/personnelManage/testServlet.action";
    xmlHttp.open("GET",url,true);
    //xmlHttp绑定回调方法
    //xmlHttp 状态0-4,我们只关心4(complete)
    xmlHttp.onreadystatechange=callback;
    xmlHttp.send(null);
  }

  //获取XmlHttp对象
  function creatXMLHttp(){
    var xmlHttp;
    if (window.XMLHttpRequest){
      xmlHttp=new XMLHttpRequest();
    }
    if(window.ActiveXObject){
      xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
      if(!xmlHttp){
        xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
      }
    }
    return xmlHttp;
  }
  //回调函数
  function callback() {
    //4代表成功
    if(xmlHttp.readyState == 4){
      //200代表服务器响应成功
      if(xmlHttp.status == 200){
        //交互成功 获得响应的数据 是文本格式
        var result = xmlHttp.responseText;
        //解析数据
//        var json = eval("("+result+")");
        var json = JSON.parse(result);
        console.log("返回数据:"+result);
        console.log(json);
        console.log(typeof json);
        console.log(json.name);
      }
    }
  }
</script>
</html>
