// (1) 회원정보 수정
function update(userid, event) {
    let data = $("#profileUpdate").serialize();

    $.ajax({
        type:"put",
        url:"api/user/${userid}",
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json"
    }).done(res=>{
        console.log("성공");
    }).fail(error=>{
        console.log("실패");
    })
}