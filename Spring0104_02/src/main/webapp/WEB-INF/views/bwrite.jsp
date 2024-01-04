<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Pages - Login</title>
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&display=swap&subset=korean" rel="stylesheet">
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="css/write.css">
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <link href="/css/summernote/summernote-lite.css" rel="stylesheet">
  <script src="/js/summernote/summernote-lite.js"></script>
  <script src="/js/summernote/lang/summernote-ko-KR.js"></script>
</head>
<body>
 <script>
  	$(function(){
  		$('#summernote').summernote({
 			 height: 500, //최대 글쓰기 높이              
 			  minHeight: 500,//최소 높이            
 			  maxHeight: 500,//최대 높이             
 			  focus: true,   
 			  lang:"ko-KR",
 			  placeholder:"최대2000자까지 입력하실수 있습니다.",
 			// 추가 부분
             toolbar: [
			// [groupName, [list of button]]
			['fontname', ['fontname']],
			['fontsize', ['fontsize']],
			['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
			['color', ['forecolor','color']],
			['table', ['table']],
			['para', ['ul', 'ol', 'paragraph']],
			['height', ['height']],
			['insert',['picture','link','video']],
			['view', [ 'codeview','fullscreen', 'help']]
			],
			fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
			callbacks: { //여기 부분이 이미지를 첨부하는 부분
				  onImageUpload : function(files) {
				  //이미지 업로드 함수호출 - 매개변수 :이미지파일,현재위치
				  uploadSummernoteImageFile(files[0],this);
				  },
				  onPaste: function (e) {
				   var clipboardData = e.originalEvent.clipboardData;
				   if (clipboardData && clipboardData.items &&
				   clipboardData.items.length) {
				       var item = clipboardData.items[0];
				if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
				  e.preventDefault();
				       }
				   }
				 }
				} //callbacks
  		});
  	});//jquery
  	function uploadSummernoteImageFile(file,this_editor){
  		var form_data = new FormData();//form객체 선언
  		form_data.append("file",file);
  		
  		//ajax 전송
  		$.ajax({
  			url:"/summernoteUpload",
  			type:"post",
  			data:form_data,
  			cache:false,
  			contentType:false,
  			enctype:"multipart/form-data",
  			processData:false,
  			success:function(data){ //업로드된 url링크 주소를 data에 전송
  				console.log("파일저장위치 :"+data)
  				$(this_editor).summernote('editor.insertImage',data);
  			},
  			error:function(){
  				alert("실패")
  			}
  		});
  	}//uploadFile
  	</script>
<section>
    <h1>관리자 글쓰기</h1>
    <hr>

    <form action="write" name="writeFrm" method="post" enctype="multipart/form-data">
      <table>
        <colgroup>
          <col width="15%">
          <col width="85%">
        </colgroup>
        <tr>
          <th>작성자</th>
          <td>
            <input type="text" name="id" value="aaa">
          </td>
        </tr>
        <tr>
          <th>제목</th>
          <td>
            <input type="text" name="btitle">
          </td>
        </tr>
        <tr>
          <th>내용</th>
          <td>
            <textarea name="bcontent" id="summernote" cols="50" rows="10"></textarea>
          </td>
        </tr>
        <tr>
          <th>이미지 표시</th>
          <td>
            <input type="file" name="file" id="file">
          </td>
        </tr>
      </table>
      <hr>
      <div class="button-wrapper">
        <button type="submit" class="write">작성완료</button>
        <button type="button" class="cancel" onclick="javascript:location.href='blist'">취소</button>
      </div>
    </form>

  </section>

</body>
</html>