toastr.options = {
  "closeButton": true,
  "debug": true,
  "progressBar": true,
  "positionClass": "toast-top-center",
  "showDuration": "400",
  "hideDuration": "1000",
  "timeOut": "2000",
  "extendedTimeOut": "1000",
  "showEasing": "swing",
  "hideEasing": "linear",
  "showMethod": "fadeIn",
  "hideMethod": "fadeOut"
}
$(function(){
    var index = parent.layer.getFrameIndex(window.name);
    $(".btn-outline").click(function(){
        parent.layer.close(index); //执行关闭
    })
    $(document).on("click",".btn-whites",function(){
        $("#fileuploads").click();
    });
});

$(function(){
    var index = parent.layer.getFrameIndex(window.name);
    $(".btn-outline").click(function(){
        parent.layer.close(index); //执行关闭
    });
});

