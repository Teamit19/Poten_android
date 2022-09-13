package com.example.poten.Home

class Post(//    var postId : String ?= "", // 글 고유 id
        var clubname : String ?= "", //  동아리 이름
        var username : String ?= "", // 작성자 name
        var image_time_posted : String ?= "", // post 업로드한 시간
        var profile_photo: Long ?= null, // 프로필 사진 url
        var post_image: Long ?= null, // 첨부 사진 url

        var heart_count : Int ?= 0, // 좋아요 수
        //    var hearts : MutableMap<String, Boolean> = HashMap(),  // 좋아요 한 목록
        var speech_count :  Int ?= 0, // 댓글 수

        var image_caption : String ?= "", // 내용 글
)