// (1) 회원정보 수정
function update(userid, event) {
    event.preventDefault(); //폼태그 액션을 막기!!
    let data = $("#profileUpdate").serialize();

    $.ajax({
        type: "put",
        url: `/api/user/${userid}`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json"
    }).done(res=>{ //httpStatus 상태코드 200번대
        console.log("성공",res);
        location.href=`/user/${userid}`;
    }).fail(error=>{//httpStatus 상태코드 200번대가 아닐때
        alert(JSON.stringify(error.responseJSON.data));
    })
}