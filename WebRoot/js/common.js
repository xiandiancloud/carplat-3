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

function getPos(index){
	var myObj =
    {
        'left': 0,
        'top': 0
    };
	if (index == 1)
	{
		myObj.left = 460;
		myObj.top = -470;
	}
	if (index == 2)
	{
		myObj.left = 460;
		myObj.top = -540;
	}
	if (index == 3)
	{
		myObj.left = 320;
		myObj.top = -622;
	}
	if (index == 4)
	{
		myObj.left = 240;
		myObj.top = -622;
	}
	if (index == 5)
	{
		myObj.left = 150;
		myObj.top = -622;
	}
	if (index == 6)
	{
		myObj.left = 70;
		myObj.top = -622;
	}
	if (index == 7)
	{
		myObj.left = -10;
		myObj.top = -622;
	}
	if (index == 8)
	{
		myObj.left = -175;
		myObj.top = -580;
	}
	if (index == 9)
	{
		myObj.left = -207;
		myObj.top = -580;
	}
	if (index == 10)
	{
		myObj.left = -290;
		myObj.top = -580;
	}
	if (index == 11)
	{
		myObj.left = -325;
		myObj.top = -580;
	}
	else if(index == 12)
	{
		myObj.left = -395;
		myObj.top = -580;
	}
	else if(index == 13)
	{
		myObj.left = -430;
		myObj.top = -580;
	}
	else if(index == 14)
	{
		myObj.left = -433;
		myObj.top = -450;
	}
	else if(index == 15)
	{
		myObj.left = -398;
		myObj.top = -450;
	}
	else if(index == 16)
	{
		myObj.left = -330;
		myObj.top = -450;
	}
	else if(index == 17)
	{
		myObj.left = -295;
		myObj.top = -450;
	}
	else if(index == 18)
	{
		myObj.left = -212;
		myObj.top = -450;
	}
	else if(index == 19)
	{
		myObj.left = -180;
		myObj.top = -450;
	}
	else if(index == 20)
	{
		myObj.left = -60;
		myObj.top = -460;
	}
	else if(index == 21)
	{
		myObj.left = -25;
		myObj.top = -460;
	}
	else if(index == 22)
	{
		myObj.left = 35;
		myObj.top = -460;
	}
	else if(index == 23)
	{
		myObj.left = 70;
		myObj.top = -460;
	}
	else if(index == 24)
	{
		myObj.left = 120;
		myObj.top = -460;
	}
	else if(index == 25)
	{
		myObj.left = 155;
		myObj.top = -460;
	}
	else if(index == 26)
	{
		myObj.left = 205;
		myObj.top = -460;
	}
	else if(index == 27)
	{
		myObj.left = 205;
		myObj.top = -390;
	}
	else if(index == 28)
	{
		myObj.left = 155;
		myObj.top = -390;
	}
	else if(index == 29)
	{
		myObj.left = 120;
		myObj.top = -390;
	}
	else if(index == 30)
	{
		myObj.left = 70;
		myObj.top = -390;
	}
	else if(index == 31)
	{
		myObj.left = 35;
		myObj.top = -390;
	}
	else if(index == 32)
	{
		myObj.left = -25;
		myObj.top = -390;
	}
	else if(index == 33)
	{
		myObj.left = -60;
		myObj.top = -390;
	}
	else if(index == 34)
	{
		myObj.left = -180;
		myObj.top = -380;
	}
	else if(index == 35)
	{
		myObj.left = -212;
		myObj.top = -380;
	}
	else if(index == 36)
	{
		myObj.left = -295;
		myObj.top = -380;
	}
	else if(index == 37)
	{
		myObj.left = -330;
		myObj.top = -380;
	}
	else if(index == 38)
	{
		myObj.left = -398;
		myObj.top = -380;
	}
	else if(index == 39)
	{
		myObj.left = -433;
		myObj.top = -380;
	}
	else if(index == 40)
	{
		myObj.left = -433;
		myObj.top = -150;
	}
	else if(index == 41)
	{
		myObj.left = -398;
		myObj.top = -150;
	}
	else if(index == 42)
	{
		myObj.left = -330;
		myObj.top = -150;
	}
	else if(index == 43)
	{
		myObj.left = -295;
		myObj.top = -150;
	}
	else if(index == 44)
	{
		myObj.left = -212;
		myObj.top = -150;
	}
	else if(index == 45)
	{
		myObj.left = -180;
		myObj.top = -150;
	}
	else if(index == 46)
	{
		myObj.left = -40;
		myObj.top = -150;
	}
	else if(index == 47)
	{
		myObj.left = -3;
		myObj.top = -150;
	}
	else if(index == 48)
	{
		myObj.left = 125;
		myObj.top = -160;
	}
	else if(index == 49)
	{
		myObj.left = 215;
		myObj.top = -180;
	}
	else if(index == 50)
	{
		myObj.left = 330;
		myObj.top = -360;
	}
	return myObj;
}