var curPage = 1; //当前页码
var total,pageSize,totalPage; //总记录数，每页显示数，总页数
var phone;

//获取分页条
function getPageBar(){
    //页码大于最大页数
    if(curPage>totalPage) curPage=totalPage;
    //页码小于1
    if(curPage<1) curPage=1;
    pageStr = "<span style='font-size: 16px; margin-right: 10px;'>共"+'<span style="color: #880000; margin: 3px;">'+total+'</span>'+"条</span><span style='font-size: 16px'>"+curPage
        +"/"+totalPage+"</span>";

    //如果是第一页
    if(curPage==1){
    }else{
        pageStr += "<span style='font-size: 16px; margin-right: 10px;'><a href='javascript:void(0)' rel='1'>首页</a></span><span style='font-size: 16px; margin-right: 10px;'><a href='javascript:void(0)' rel='"+(curPage-1)+"'>上一页</a></span>";
    }

    //如果是最后页
    if(curPage < totalPage){
        pageStr += "<span style='font-size: 16px; margin-right: 10px;'><a href='javascript:void(0)' rel='"+(parseInt(curPage)+1)+"'> 下一页</a></span><span style='font-size: 16px; margin-right: 10px;'><a href='javascript:void(0)' rel='"+totalPage+"'>尾页</a></span>";
    }else{
    }
    pageStr += "<span>" +
                    "<input class='jumpPage' value='' style='width: 80px; '/>"+
                    "<button class='btn-primary go ' style='width: 60px; margin-left: 5px; '>GO</button>"+
                "</span>"

    $("#pagecount").html(pageStr);
}




//时间封装类
function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    var Y = date.getFullYear() + '-';
    var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    var D = (date.getDate() < 10 ? '0'+date.getDate() : date.getDate()) + ' ';
    var h =	(date.getHours() < 10 ? '0'+date.getHours() : date.getHours()) + ':';
    var m =	(date.getMinutes() < 10 ? '0'+date.getMinutes() : date.getMinutes()) + ':';
    var s = (date.getSeconds() < 10 ? '0'+date.getSeconds() : date.getSeconds());
    return Y+M+D+h+m+s;
}