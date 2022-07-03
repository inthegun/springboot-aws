var main = {
    init() {  // init : function () { }
        let _this = this;
        $('#btn-save').on('click',function () {
            _this.save();
        });
        $('#btn-update').on('click',function() { // btn-update 란 id 를 가진 HTML 엘리먼트에 click 이벤트가 발생할때 update function 실행
            _this.update();
        });
        $('#btn-delete').on('click',function() {
            _this.delete();
        });
    },
    save() {
        let data = {
            title : $('#title').val(),
            author : $('#author').val(),
            content : $('#content').val()
        };

        $.ajax({
            type : 'POST',
            url : '/api/v1/posts',
            dataType : 'json',
            contentType : 'application/json;charset=utf-8',
            data : JSON.stringify(data)
        }).done(function(){
            alert('글이 등록되었습니다.');
            window.location.href = '/'; // 글 등록이 성공하면 메인페이지(/) 로 이동
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    },
    update() { // 2
        let data = {
            title : $('#title').val(),
            content : $('#content').val()
        };
        let id = $('#id').val();

        $.ajax({
            type : 'PUT', // Controller 있는 API에ㅓㅅ 이미 @PutMapping 으로 선언했기 때문에 [ REST API 규약 ]
            url : '/api/v1/posts/'+id, // 어느 게시물을 수정할지 URL Path 로 구분
            dataType: 'json',
            contentType: 'application/json;charset=utf-8',
            data : JSON.stringify(data),
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href='/';
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    },
    delete(){
        let id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url : '/api/v1/posts/'+id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8'
        }).done(function(){
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

};
main.init();

/*
* 굳이 변수의 속성으로 function 을 추가한 이유
* index.js 예: var init = function () { }; var save = function () { } ; init();
* index.mustache 에서 새로운 js가 추가되어 index.js 와 같은 init , save function이 있다면
* 나중에 로딩된 js의 init ,save 가 먼저 로딩된 js 의 function을 덮어쓰게 된다.
* 이러한 문제를 피하기 위해선 index.js 만의 유효범위를 만들어 사용
* 방법은 var index 객체를 만들어 해당 객체에서 필요한 모든 function을 선언
*
* */