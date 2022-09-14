package com.example.poten.Board.model



data class BoardResponse (
    var boardId: Long? = null,
    var writer: UserResponse? = null,
    var club: ClubResponse? = null,
    var content: String? = null,
    var hearts: List<HeartBoardResponse>? = null,
    var comment: List<CommentResponse>? = null,
    var pics : FileResponse ?= null,
    var createdTime: String? = null,
    var modifiedTime: String? = null,

    var  heartsNum :Integer ?=null,
    var  commentsNum :Integer ?=null,
)


data class ClubResponse (
    var clubId: Long? = null,
    var clubName: String? = null,
    var manager: UserResponse? = null,
    var clubDesc: String? = null,
    var region: String? = null,
    var field: String? = null,
    var activityType: String? = null,
    var follows: List<UserResponse>? = null,
    var hearts: List<HeartClubResponse>? = null,
    var boards: List<BoardResponse>? = null,
    var posters: List<PosterResponse>? = null,
    var members: List<UserResponse>? = null,
    var waitings: List<UserResponse>? = null,
    var createdTime: String? = null,

    var  followersNum :Integer ?=null,
    var  heartsNum :Integer ?=null,
    var  membersNum :Integer ?=null,
)

data class BoolResponse(
    var result: Boolean? = null
)


data class FileResponse (
    var fileName: String? = null,
)
data class CommentResponse (
    var commentId: Long? = null,
    var writer: UserResponse? = null,
    var board: BoardResponse? = null,
    var content: String? = null,
    var createdTime: String? = null,
    var modifiedTime: String? = null
)


data class BoardResponseList (
    val boardResponseList : List<BoardResponse>
)


data class HeartBoardResponse (
    var heartId: Long? = null,
    var lover: UserResponse? = null,
    var board: BoardResponse? = null
)


data class HeartClubResponse (
    var heartId: Long? = null,
    var lover: UserResponse? = null,
    var club: ClubResponse? = null
)


data class PosterResponse (
    var posterId: Long? = null,
    var club: ClubResponse? = null,
    var writer: UserResponse? = null,
    var title: String? = null,
    var content: String? = null,
    var deadlineDate: String? = null,
    var pics : FileResponse ?= null,
    var createdTime: String? = null,
    var modifiedTime: String? = null,
    var dday: Integer ? = null
)


data class PosterResponseList (
    val posterResponseList: List<PosterResponse>? = null
)


data class SessionResponse (
    val userResponse: UserResponse? = null,
    val sessionId: String? = null
)


data class UserResponse(
    var id: Long,
    var email: String,
    var name: String,
)