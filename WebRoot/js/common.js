/**
 * 解析url，來整合，不是很理想，安全問題待加強
 * 用戶可以隨意更改，這兒簡單處理判斷下
 */
function parseUrl(debug)
{
	/*if (true)
	{
		var name = "飞燕";
    	name = encodeURI(name);
    	//alert("name --- "+name);
    	var url = "http://localhost:8080/carplat/car.html?ui=0&ur=1&un="+name+"&up=http://localhost:8080/carplat/images/1.jpg";
    	$("#demo").attr("href",url);
		return url;
	}*/
	var array = new Array();
	var href = location.href;
	var a = href.split("?");	
	if (a.length < 2)
	{
		//location.href = "404.html";
		array[0] = "admin";
		array[1] = 0;
		array[2] = "admin";
		return array;
	}
	var b = a[1];
	array[0] = b;
	var c = b.split("&");	
	if (c.length < 4)
	{
		location.href = "404.html";
		return;
	}
	var role = c[1].split("=")[1];
	array[1] = role;
	var uname = c[2].split("=")[1];	
	//var username = decodeURI(uname);	
	var username = decodeURI(decodeURI(uname));
	array[2] = username;
	//var uimg = c[3].split("=")[1];
	//array[3] = uimg;
	var uimg = c[3].split("=")[1];
	array[3] = decodeURIComponent(decodeURI(uimg));
	return array;
}

function isNull( str ){
	if ( str == "" ) return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
} 