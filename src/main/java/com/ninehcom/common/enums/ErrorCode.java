/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ninehcom.common.enums;

/**
 *
 * @author Shenjizhe
 */
public enum ErrorCode {

    Success(0, "成功"),
    Fail(1, "失败"),
    // 短信中心错误码
    SMSProviderFail(10000, "发送失败，短信运营商内部错误"),
    SMSNFail(10001, "sn无效"),
    SMNumberLimitFail(10002, "已达单日单号码可发送总条数上限"),
    SMPlatformLimitFail(10003, "已达单日单平台单号码可发送总条数上限"),
    SMIntevalFail(10004, "60秒后后可再次对此手机号发送短信"),
    // 用户接口错误码
    UserInsertDBFail(20001, "用户数据插入数据库失败"),
    UserUpdateDBFail(20002, "用户数据更新数据库失败"),
    VersionTooOldFail(20003, "服务器不兼容当前版本，需要强制升级"),
    VersionTypeNotExistFail(20004, "当前类型不存在"),
    VersionUploadURLNotFound(20005, "系统中没有配置版本升级路径"),
    VersionFormatFail(20006, "版本格式错误"),
    UserNotExist(20007, "用户不存在"),
    // 用户中心错误码
    UserSignedInToday(20101, "用户今天已经签到"),
    UserSignedFail(20102, "用户签到数据添加失败"),
    UserSigneSearchFail(20103, "用户签到排名数据添加失败"),
    UserSigneSearchUpdateFail(20104, "用户签到信息更新失败"),
    UserUnSigned(20105, "用户不存在或者从未签到"),
    UserNickNameAlreadyChanged(20106, "昵称修改次数超限"),
    SensitiveWordContains(20107, "含有敏感词"),
    NickNameLimitConfigEmpty(20108, "昵称限制次数配置为空"),
    GetFeedbackFail(20109, "取得用户反馈失败"),
    SendFeedbackFail(20110, "发送用户反馈失败"),
    ReviewFeedbackFail(20111, "审阅用户反馈失败"),
    ScoreAddToDBFail(20112, "添加积分数据到数据库失败"),
    ScoreSubToDBFail(20113, "扣除积分数据到数据库失败"),
    NoScoreForSubFail(20114, "用户当天没有积分可扣除"),
    ScoreValidateFail(20115, "积分或者经验不能为负值"),
    AttentionPlayerDBFail(20116, "关注球员数据库更新失败"),
    CancelAttentionPlayerDBFail(20117, "取消关注球员数据库更新失败"),
    PlayerAttentioned(20118, "球员已经关注"),
    PlayerUnAttentioned(20119, "球员还未关注"),

    //新闻评论部分
    UidError(20007,"用户不存在"),
    NewsCommentSensitiveRequestError(12,"敏感词服务连接失败"),
    NewsCommentLengthError(71000,"评论内容最多为600字"),
    NewsCommentAddError(71001,"新闻评论添加失败"),
    NewsCommentTargetNewsIsNull(71002,"评论所属的新闻不存在"),
    NewsCommentTargetCommentIsNull(71003,"评论所属的新闻评论不存在"),
    NewsCommentIsNotFullUidIsNull(71004,"原评论信息不完整，缺失评论人id"),
    NewsCommentUidError(71005,"被评论的原评论用户id不正确"),
    NewsCommentCountUpdateError(71006,"更新新闻评论数失败"),
    NewsCommentIsPraised(71007,"您已经赞过啦！"),
    NewsCommentNotPraised(71008,"取消点赞失败,您之前没有点过赞"),
    NewsCommentNotifyError(71009,"新闻评论通知创建失败"),
    NewsCommentContainSensitive(71010,"含有敏感词"),
    NewsCommentIsNull(71011,"目标评论不存在"),
    NewsCommentMessageError(71012,"新闻评论的通知,创建失败"),
    NewsCommentAddUidNull(71013,"被评论的原评论用户id不能为空"),
    NewsComeentIsNotInNews(71014,"传入的评论引用id与传入的新闻id不匹配"),
    NewsCommentNewIdError(71015,"传入的评论id和新闻id不匹配"),
    NewsCommentPraiseIdError(71016,"取消点赞失败，相应的赞记录与传入的praise_id不匹配"),
    NewsCommentPraiseUserIdNull(71017,"此处的用户id不能为空"),
    NewsCommentIdNull(71018,"此处的评论id不能为空"),
    NewsIsNull(71019,"请求的新闻不存在"),
    NewsCommentIsClose(71020,"当前新闻评论功能未开通")
    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
