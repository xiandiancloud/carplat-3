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
		myObj.left = 445;
		myObj.top = -488;
	}
	if (index == 2)
	{
		myObj.left = 445;
		myObj.top = -550;
	}
	if (index == 3)
	{
		myObj.left = 320;
		myObj.top = -627;
	}
	if (index == 4)
	{
		myObj.left = 240;
		myObj.top = -627;
	}
	if (index == 5)
	{
		myObj.left = 155;
		myObj.top = -627;
	}
	if (index == 6)
	{
		myObj.left = 70;
		myObj.top = -627;
	}
	if (index == 7)
	{
		myObj.left = -10;
		myObj.top = -627;
	}
	if (index == 8)
	{
		myObj.left = -190;
		myObj.top = -605;
	}
	if (index == 9)
	{
		myObj.left = -225;
		myObj.top = -605;
	}
	if (index == 10)
	{
		myObj.left = -305;
		myObj.top = -605;
	}
	if (index == 11)
	{
		myObj.left = -340;
		myObj.top = -605;
	}
	else if(index == 12)
	{
		myObj.left = -410;
		myObj.top = -605;
	}
	else if(index == 13)
	{
		myObj.left = -445
		myObj.top = -605;
	}
	else if(index == 14)
	{
		myObj.left = -448;
		myObj.top = -457;
	}
	else if(index == 15)
	{
		myObj.left = -415;
		myObj.top = -457;
	}
	else if(index == 16)
	{
		myObj.left = -345;
		myObj.top = -457;
	}
	else if(index == 17)
	{
		myObj.left = -312;
		myObj.top = -457;
	}
	else if(index == 18)
	{
		myObj.left = -230;
		myObj.top = -457;
	}
	else if(index == 19)
	{
		myObj.left = -195;
		myObj.top = -457;
	}
	else if(index == 20)
	{
		myObj.left = -75;
		myObj.top = -470;
	}
	else if(index == 21)
	{
		myObj.left = -40;
		myObj.top = -470;
	}
	else if(index == 22)
	{
		myObj.left = 18;
		myObj.top = -470;
	}
	else if(index == 23)
	{
		myObj.left = 52;
		myObj.top = -470;
	}
	else if(index == 24)
	{
		myObj.left = 105;
		myObj.top = -470;
	}
	else if(index == 25)
	{
		myObj.left = 138;
		myObj.top = -470;
	}
	else if(index == 26)
	{
		myObj.left = 190;
		myObj.top = -470;
	}
	else if(index == 27)
	{
		myObj.left = 190;
		myObj.top = -410;
	}
	else if(index == 28)
	{
		myObj.left = 138;
		myObj.top = -410;
	}
	else if(index == 29)
	{
		myObj.left = 105;
		myObj.top = -410;
	}
	else if(index == 30)
	{
		myObj.left = 52;
		myObj.top = -410;
	}
	else if(index == 31)
	{
		myObj.left = 18;
		myObj.top = -410;
	}
	else if(index == 32)
	{
		myObj.left = -40;
		myObj.top = -410;
	}
	else if(index == 33)
	{
		myObj.left = -75;
		myObj.top = -410;
	}
	else if(index == 34)
	{
		myObj.left = -195;
		myObj.top = -395;
	}
	else if(index == 35)
	{
		myObj.left = -230;
		myObj.top = -395;
	}
	else if(index == 36)
	{
		myObj.left = -312;
		myObj.top = -395;
	}
	else if(index == 37)
	{
		myObj.left = -345;
		myObj.top = -395;
	}
	else if(index == 38)
	{
		myObj.left = -415;
		myObj.top = -395;
	}
	else if(index == 39)
	{
		myObj.left = -448;
		myObj.top = -395;
	}
	else if(index == 40)
	{
		myObj.left = -448;
		myObj.top = -155;
	}
	else if(index == 41)
	{
		myObj.left = -413;
		myObj.top = -155;
	}
	else if(index == 42)
	{
		myObj.left = -342;
		myObj.top = -155;
	}
	else if(index == 43)
	{
		myObj.left = -308;
		myObj.top = -155;
	}
	else if(index == 44)
	{
		myObj.left = -227;
		myObj.top = -155;
	}
	else if(index == 45)
	{
		myObj.left = -193;
		myObj.top = -155;
	}
	else if(index == 46)
	{
		myObj.left = -52;
		myObj.top = -155;
	}
	else if(index == 47)
	{
		myObj.left = -20;
		myObj.top = -155;
	}
	else if(index == 48)
	{
		myObj.left = 112;
		myObj.top = -168;
	}
	else if(index == 49)
	{
		myObj.left = 200;
		myObj.top = -190;
	}
	else if(index == 50)
	{
		myObj.left = 335;
		myObj.top = -365;
	}
	return myObj;
}