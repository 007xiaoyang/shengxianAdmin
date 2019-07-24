function formatDateTime(inputTime,x) {
    var date = new Date(inputTime);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' +m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' +d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    //return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
    if(x==1){
        return y + '年' + m + '月' + d + '日';
    }else if(x==2){
        return d + ' ';
    } else if(x == 3) {
        return y + '-' + m + '-' + d + ' ' ;
    } else if (x==4) {
        return (y+2) + '-' + m + '-' + d + ' ' ;
    } else if(x==5) {
        return y + '年' + m + '月' + d + '日 ' + h + '时'+minute+'分';
    } else {
        return y + '-' + m + '-' + d + ' ' + h + '时'+minute+'分';
    }
};

function showImage(obj) {
    $("#fancybox").attr("href",obj.firstChild.src);
    $("#fancybox").click();
}
