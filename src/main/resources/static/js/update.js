// (1) 회원정보 수정
function update(userid, event) {
    event.preventDefault();
    let data = $("#profileUpdate").serialize();

    $.ajax({
        type: "put",
        url: `/api/user/${userid}`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json"
    }).done(res=>{
        console.log("성공");
        location.href=`/user/${userid}`;
    }).fail(error=>{
        console.log("실패");
    })
}