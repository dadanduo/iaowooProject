package com.iaowoo.mobile.Ui.classification.Model;

import java.io.Serializable;
import java.util.List;

/**
 * @author ArcherYc
 * @date on 2018/9/12  下午3:43
 * @mail 247067345@qq.com
 */
public class VideoEntity {


    /**
     * head : {"traceId":null,"service":null,"version":null,"security":null,"type":"RESPONSE","direction":null}
     * body : {"comment":null,"content":{"id":null,"createTime":null,"updateTime":null,"logicDelete":0,"pageNum":1,"pageSize":15,"operator":null,"total":13,"pages":1,"list":[{"video":{"id":2,"createTime":"2018-11-06 10:31:23","updateTime":"2018-11-13 14:16:09","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509313843961266176","authorId":"509313843889963008","productId":"SH_456453645014138880","audioId":"509313843789299712","activityId":"509313843755745280","activityType":1,"status":0,"title":"难念的经","describes":"难念的经难念的经难念的经难念的经难念的经难念的经难念的经难念的经","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532661580234752.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532683327700992.gif","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-12/511532861027778560.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":3,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509313843961266176"},"music":{"id":2,"createTime":"2018-11-06 10:31:22","updateTime":"2018-11-12 13:29:33","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"509313843789299712","title":"难念的经","describes":null,"author":"111audio","coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532502058270720.png","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/https://test-files.shzhuoji.com/audioPlayUrl","createUser":"975952","updateUser":"270150"},"author":{"id":2,"createTime":"2018-11-06 10:31:22","updateTime":"2018-11-12 13:29:33","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"509313843889963008","nickname":"大棚哥","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532938190389248.png","createUser":"975952","updateUser":"270150"},"statistics":{"videoId":"509313843961266176","commentNum":45,"praiseNum":3},"productInfos":[{"productId":"SH_456453645014138880","name":"SWAROVSKI 施华洛世奇 天鹅 链坠 SWAN 镀白金色 锁骨链项链","originalPrice":990,"detail":"无","sellPrice":888,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456453529062604800.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456453645014138880","type":3}],"activity":{"id":2,"createTime":"2018-11-06 10:31:22","updateTime":"2018-11-06 10:31:22","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"activityId":"509313843755745280","title":null,"describes":null,"activityUrl":null,"activityUrlType":"1","createUser":"975952","updateUser":"975952"}},{"video":{"id":5,"createTime":"2018-11-06 15:53:52","updateTime":"2018-11-13 14:15:23","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509395001927532545","authorId":"","productId":"SH_456457857424949248,SH_456470453247016960","audioId":"","activityId":"","activityType":4,"status":1,"title":"测试3","describes":"测试2，，，，","originCover":"https://test-files.shzhuoji.com/images/bannerPictureImg/2018-06-13/456455580454748160.jpg","cover":"","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/bannerPictureImg/2018-06-15/457132188786229248.jpg","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509395001927532545"},"music":null,"author":null,"statistics":{"videoId":"509395001927532545","commentNum":3,"praiseNum":2},"productInfos":[{"productId":"SH_456457857424949248","name":"SWAROVSKI 施华洛世奇 时尚天鹅 镀白金色 SWAN 天鹅手镯手链","originalPrice":1490,"detail":"唯美手链","sellPrice":1290,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456457743281160192.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456457857424949248","type":3},{"productId":"SH_456470453247016960","name":"SWAROVSKI 施华洛世奇 SWAN镀玫瑰金色女士天鹅项链","originalPrice":990,"detail":"无","sellPrice":888,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456470345721839616.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456470453247016960","type":3}],"activity":null},{"video":{"id":7,"createTime":"2018-11-07 11:50:36","updateTime":"2018-11-13 14:15:26","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509696168301690880","authorId":"509696168209416192","productId":"SH_456457857424949248","audioId":"509696168154890240","activityId":null,"activityType":2,"status":1,"title":"product-id","describes":"product-id","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510118996486389760.jpg","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-09/510383546301415424.png","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-08/510119105068531712.ogv","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509696168301690880"},"music":{"id":5,"createTime":"2018-11-07 11:50:36","updateTime":"2018-11-08 15:51:34","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"509696168154890240","title":"product-id","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456554058338009088.jpeg","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-08/510118976634748928.mp3","createUser":"975952","updateUser":"975952"},"author":{"id":5,"createTime":"2018-11-07 11:50:36","updateTime":"2018-11-08 15:51:34","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"509696168209416192","nickname":"product-id","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510119186115067904.jpg","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"509696168301690880","commentNum":3,"praiseNum":2},"productInfos":[{"productId":"SH_456457857424949248","name":"SWAROVSKI 施华洛世奇 时尚天鹅 镀白金色 SWAN 天鹅手镯手链","originalPrice":1490,"detail":"唯美手链","sellPrice":1290,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456457743281160192.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456457857424949248","type":3}],"activity":null},{"video":{"id":8,"createTime":"2018-11-07 11:51:22","updateTime":"2018-11-13 14:15:28","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509696363231969280","authorId":"509696363219386368","productId":null,"audioId":"509696363202609152","activityId":"509696363185831936","activityType":3,"status":1,"title":"acticity","describes":"acticity","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510121164157222912.jpg","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510146222225358848.jpg","videoFileName":"未知文件.mp4","playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-08/510124064266780672.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509696363231969280"},"music":{"id":6,"createTime":"2018-11-07 11:51:22","updateTime":"2018-11-12 16:00:34","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"509696363202609152","title":"acticity","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510121095643267072.jpg","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":"未知文件.mp3","playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-08/510121127788412928.mp3","createUser":"975952","updateUser":"975952"},"author":{"id":6,"createTime":"2018-11-07 11:51:22","updateTime":"2018-11-08 16:00:01","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"509696363219386368","nickname":"acticitybabab","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510121302036578304.jpg","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"509696363231969280","commentNum":1,"praiseNum":2},"productInfos":null,"activity":{"id":4,"createTime":"2018-11-07 11:51:22","updateTime":"2018-11-13 16:48:45","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"activityId":"509696363185831936","title":"aaaa","describes":null,"activityUrl":"https://test-files.shzhuoji.com/dd","activityUrlType":"1","createUser":"975952","updateUser":"975952"}},{"video":{"id":9,"createTime":"2018-11-07 13:25:06","updateTime":"2018-11-13 14:15:32","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509719951636955136","authorId":"509719951628566528","productId":"SH_456457857424949248,SH_456470453247016960","audioId":"509719951607595008","activityId":null,"activityType":2,"status":1,"title":"222","describes":"222","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510148709225332736.jpg","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510148726216458240.jpg","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-08/510148765315760128.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509719951636955136"},"music":{"id":7,"createTime":"2018-11-07 13:25:06","updateTime":"2018-11-08 17:50:08","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"509719951607595008","title":"222","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456554058338009088.jpeg","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-08/510148695602233344.mp3","createUser":"975952","updateUser":"975952"},"author":{"id":7,"createTime":"2018-11-07 13:25:06","updateTime":"2018-11-08 17:50:08","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"509719951628566528","nickname":"111","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510148803370680320.png","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"509719951636955136","commentNum":1,"praiseNum":2},"productInfos":[{"productId":"SH_456457857424949248","name":"SWAROVSKI 施华洛世奇 时尚天鹅 镀白金色 SWAN 天鹅手镯手链","originalPrice":1490,"detail":"唯美手链","sellPrice":1290,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456457743281160192.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456457857424949248","type":3},{"productId":"SH_456470453247016960","name":"SWAROVSKI 施华洛世奇 SWAN镀玫瑰金色女士天鹅项链","originalPrice":990,"detail":"无","sellPrice":888,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456470345721839616.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456470453247016960","type":3}],"activity":null},{"video":{"id":10,"createTime":"2018-11-08 16:30:51","updateTime":"2018-11-13 14:15:33","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"510129085003857920","authorId":"510129084991275008","productId":null,"audioId":"510129084966109184","activityId":null,"activityType":1,"status":1,"title":"babab","describes":"aaaa","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510129000484438016.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510129013981708288.jpg","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-08/510129049310330880.ogv","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=510129085003857920"},"music":{"id":8,"createTime":"2018-11-08 16:30:51","updateTime":"2018-11-08 16:30:51","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"510129084966109184","title":"aaa","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510128963377430528.jpg","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-08/510128988316762112.mp3","createUser":"975952","updateUser":"975952"},"author":{"id":8,"createTime":"2018-11-08 16:30:51","updateTime":"2018-11-08 16:30:51","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"510129084991275008","nickname":"nannan","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510129053915676672.jpg","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"510129085003857920","commentNum":1,"praiseNum":2},"productInfos":null,"activity":null},{"video":{"id":12,"createTime":"2018-11-12 15:33:00","updateTime":"2018-11-13 14:15:35","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"511564078800437248","authorId":"511564078779465728","productId":"SH_456457857424949248","audioId":"511564078498447360","activityId":null,"activityType":2,"status":1,"title":"testProduct","describes":"testProduct","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565652717207552.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565670140346368.png","videoFileName":"Intermission-Walk-in_512kb.mp4","playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-12/511565703262765056.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=511564078800437248"},"music":{"id":10,"createTime":"2018-11-12 15:33:00","updateTime":"2018-11-12 15:39:40","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"511564078498447360","title":"testProduct1","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565603685793792.png","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":"music.mid","playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-12/511565629950525440.mid","createUser":"975952","updateUser":"975952"},"author":{"id":10,"createTime":"2018-11-12 15:33:00","updateTime":"2018-11-12 15:39:40","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"511564078779465728","nickname":"testProduct1","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565742315929600.png","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"511564078800437248","commentNum":0,"praiseNum":2},"productInfos":[{"productId":"SH_456457857424949248","name":"SWAROVSKI 施华洛世奇 时尚天鹅 镀白金色 SWAN 天鹅手镯手链","originalPrice":1490,"detail":"唯美手链","sellPrice":1290,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456457743281160192.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456457857424949248","type":3}],"activity":null},{"video":{"id":13,"createTime":"2018-11-12 15:43:19","updateTime":"2018-11-13 14:15:37","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"511566675624067072","authorId":"511566675607289856","productId":null,"audioId":"511566675586318336","activityId":"511566675305299968","activityType":3,"status":1,"title":"testActivity12","describes":"testActivity12","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511566428332097536.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511566525350543360.png","videoFileName":"Intermission-Walk-in_512kb.mp4","playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-12/511566554563870720.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=511566675624067072"},"music":{"id":11,"createTime":"2018-11-12 15:43:19","updateTime":"2018-11-12 15:43:19","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"511566675586318336","title":"testActivity12","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511566397633986560.png","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":"song.mp3","playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-12/511566486666477568.mp3","createUser":"975952","updateUser":"975952"},"author":{"id":11,"createTime":"2018-11-12 15:43:19","updateTime":"2018-11-12 15:43:19","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"511566675607289856","nickname":"testActivity12","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511566659819929600.png","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"511566675624067072","commentNum":0,"praiseNum":2},"productInfos":null,"activity":{"id":5,"createTime":"2018-11-12 15:43:19","updateTime":"2018-11-13 16:48:49","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"activityId":"511566675305299968","title":"testActivity12","describes":null,"activityUrl":"aaa","activityUrlType":"1","createUser":"975952","updateUser":"975952"}},{"video":{"id":3,"createTime":"2018-11-06 15:53:52","updateTime":"2018-11-13 14:15:38","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509395001927532544","authorId":"509395001893978112","productId":"type=3&templateId=SH_472132376210702336&sharePage=2","audioId":"509395001868812288","activityId":"509395001633931264","activityType":2,"status":0,"title":"搞笑视频","describes":"哈哈哈哈哈哈","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511540089889751040.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511522144459423744.png","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-12/511540125881073664.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":1,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509395001927532544"},"music":{"id":3,"createTime":"2018-11-06 15:53:52","updateTime":"2018-11-12 13:58:28","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"509395001868812288","title":"嘿嘿嘿","describes":null,"author":"1112audio","coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511522194182897664.jpg","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-12/511522696077508608.mp3","createUser":"975952","updateUser":"270150"},"author":{"id":3,"createTime":"2018-11-06 15:53:52","updateTime":"2018-11-12 13:58:28","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"509395001893978112","nickname":"哈哈哈","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511522156878757888.jpg","createUser":"975952","updateUser":"270150"},"statistics":{"videoId":"509395001927532544","commentNum":0,"praiseNum":1},"productInfos":[],"activity":{"id":3,"createTime":"2018-11-06 15:53:52","updateTime":"2018-11-06 15:53:52","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"activityId":"509395001633931264","title":null,"describes":null,"activityUrl":null,"activityUrlType":"2","createUser":"975952","updateUser":"975952"}},{"video":{"id":6,"createTime":"2018-11-07 11:49:23","updateTime":"2018-11-13 14:15:25","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509695864013324288","authorId":"509695863992352768","productId":null,"audioId":"509695863644225536","activityId":null,"activityType":1,"status":1,"title":"video-122233","describes":"video-1","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-09/510389208800559104.jpg","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-09/510389411112812544.jpg","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-08/510118040826806272.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":1,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509695864013324288"},"music":{"id":4,"createTime":"2018-11-07 11:49:23","updateTime":"2018-11-09 09:45:02","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"509695863644225536","title":"video-1","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456554058338009088.jpeg","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-09/510389020509863936.mid","createUser":"975952","updateUser":"975952"},"author":{"id":4,"createTime":"2018-11-07 11:49:23","updateTime":"2018-11-08 15:19:44","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"509695863992352768","nickname":"video-133","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510111109441650688.png","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"509695864013324288","commentNum":0,"praiseNum":1},"productInfos":null,"activity":null},{"video":{"id":11,"createTime":"2018-11-12 14:51:40","updateTime":"2018-11-13 17:09:26","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"511553676398034944","authorId":"511553676377063424","productId":null,"audioId":"511553676356091904","activityId":null,"activityType":1,"status":1,"title":"testVideo","describes":"testVideo","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565396319404032.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565418758930432.png","videoFileName":"Intermission-Walk-in.ogv","playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-12/511565502997331968.ogv","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":0,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=511553676398034944"},"music":{"id":9,"createTime":"2018-11-12 14:51:40","updateTime":"2018-11-12 15:38:47","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"511553676356091904","title":"testVideo1","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565295425421312.png","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":"horse.mp3","playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-12/511565333731999744.mp3","createUser":"975952","updateUser":"975952"},"author":{"id":9,"createTime":"2018-11-12 14:51:40","updateTime":"2018-11-12 15:38:47","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"511553676377063424","nickname":"testVideoNickName","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565449368961024.png","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"511553676398034944","commentNum":4,"praiseNum":0},"productInfos":null,"activity":null},{"video":{"id":14,"createTime":"2018-11-13 16:02:04","updateTime":"2018-11-13 16:02:04","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"511933778638143488","authorId":"511933778625560576","productId":"SH_502160197096046592","audioId":"511933778583617536","activityId":null,"activityType":2,"status":1,"title":"霸道总裁-明道","describes":"王子变青蛙，呱呱呱~~~~","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511933600170508288.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511933626124861440.png","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-13/511933490246189056.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":0,"createUser":"270150","updateUser":"270150","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=511933778638143488"},"music":{"id":12,"createTime":"2018-11-13 16:02:04","updateTime":"2018-11-13 16:02:04","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"511933778583617536","title":"变形计","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511933540686888960.png","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-13/511933474500771840.mp3","createUser":"270150","updateUser":"270150"},"author":{"id":12,"createTime":"2018-11-13 16:02:04","updateTime":"2018-11-13 16:02:04","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"511933778625560576","nickname":"明道","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511933772183109632.png","createUser":"270150","updateUser":"270150"},"statistics":{"videoId":"511933778638143488","commentNum":0,"praiseNum":0},"productInfos":[{"productId":"SH_502160197096046592","name":"王向阳测试OK","originalPrice":122,"detail":"商品描述已经改了","sellPrice":145,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-10-17/502160093920362496.png","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_502160197096046592","type":3}],"activity":null},{"video":{"id":15,"createTime":"2018-11-13 17:27:50","updateTime":"2018-11-13 17:27:50","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"511955366179766272","authorId":"511955366167183360","productId":null,"audioId":"511955366150406144","activityId":"511955365844221952","activityType":3,"status":1,"title":"testActivity10","describes":"testActivity10","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511955199221301248.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511955211997151232.png","videoFileName":"Intermission-Walk-in_512kb.mp4","playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-13/511955247258664960.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":0,"createUser":"975952","updateUser":"975952","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=511955366179766272"},"music":{"id":13,"createTime":"2018-11-13 17:27:50","updateTime":"2018-11-13 17:27:50","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"511955366150406144","title":"testActivity10","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511955142338150400.png","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":"music.mid","playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-13/511955168313475072.mid","createUser":"975952","updateUser":"975952"},"author":{"id":13,"createTime":"2018-11-13 17:27:50","updateTime":"2018-11-13 17:27:50","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"511955366167183360","nickname":"testActivity10","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511955351294181376.png","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"511955366179766272","commentNum":0,"praiseNum":0},"productInfos":null,"activity":{"id":6,"createTime":"2018-11-13 17:27:50","updateTime":"2018-11-13 17:27:50","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"activityId":"511955365844221952","title":"testActivity10","describes":null,"activityUrl":"http://www.baidu.com","activityUrlType":"2","createUser":"975952","updateUser":"975952"}}]}}
     * tail : {"channel":null,"product":null,"system":null}
     * describe : 操作成功！
     * code : 00000000
     * costs : null
     */

    private HeadBean head;
    private BodyBean body;
    private TailBean tail;
    private String describe;
    private String code;
    private Object costs;

    public HeadBean getHead() {
        return head;
    }

    public void setHead(HeadBean head) {
        this.head = head;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public TailBean getTail() {
        return tail;
    }

    public void setTail(TailBean tail) {
        this.tail = tail;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getCosts() {
        return costs;
    }

    public void setCosts(Object costs) {
        this.costs = costs;
    }

    public static class HeadBean {
        /**
         * traceId : null
         * service : null
         * version : null
         * security : null
         * type : RESPONSE
         * direction : null
         */

        private Object traceId;
        private Object service;
        private Object version;
        private Object security;
        private String type;
        private Object direction;

        public Object getTraceId() {
            return traceId;
        }

        public void setTraceId(Object traceId) {
            this.traceId = traceId;
        }

        public Object getService() {
            return service;
        }

        public void setService(Object service) {
            this.service = service;
        }

        public Object getVersion() {
            return version;
        }

        public void setVersion(Object version) {
            this.version = version;
        }

        public Object getSecurity() {
            return security;
        }

        public void setSecurity(Object security) {
            this.security = security;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getDirection() {
            return direction;
        }

        public void setDirection(Object direction) {
            this.direction = direction;
        }
    }

    public static class BodyBean {
        /**
         * comment : null
         * content : {"id":null,"createTime":null,"updateTime":null,"logicDelete":0,"pageNum":1,"pageSize":15,"operator":null,"total":13,"pages":1,"list":[{"video":{"id":2,"createTime":"2018-11-06 10:31:23","updateTime":"2018-11-13 14:16:09","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509313843961266176","authorId":"509313843889963008","productId":"SH_456453645014138880","audioId":"509313843789299712","activityId":"509313843755745280","activityType":1,"status":0,"title":"难念的经","describes":"难念的经难念的经难念的经难念的经难念的经难念的经难念的经难念的经","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532661580234752.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532683327700992.gif","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-12/511532861027778560.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":3,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509313843961266176"},"music":{"id":2,"createTime":"2018-11-06 10:31:22","updateTime":"2018-11-12 13:29:33","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"509313843789299712","title":"难念的经","describes":null,"author":"111audio","coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532502058270720.png","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/https://test-files.shzhuoji.com/audioPlayUrl","createUser":"975952","updateUser":"270150"},"author":{"id":2,"createTime":"2018-11-06 10:31:22","updateTime":"2018-11-12 13:29:33","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"509313843889963008","nickname":"大棚哥","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532938190389248.png","createUser":"975952","updateUser":"270150"},"statistics":{"videoId":"509313843961266176","commentNum":45,"praiseNum":3},"productInfos":[{"productId":"SH_456453645014138880","name":"SWAROVSKI 施华洛世奇 天鹅 链坠 SWAN 镀白金色 锁骨链项链","originalPrice":990,"detail":"无","sellPrice":888,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456453529062604800.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456453645014138880","type":3}],"activity":{"id":2,"createTime":"2018-11-06 10:31:22","updateTime":"2018-11-06 10:31:22","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"activityId":"509313843755745280","title":null,"describes":null,"activityUrl":null,"activityUrlType":"1","createUser":"975952","updateUser":"975952"}},{"video":{"id":5,"createTime":"2018-11-06 15:53:52","updateTime":"2018-11-13 14:15:23","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509395001927532545","authorId":"","productId":"SH_456457857424949248,SH_456470453247016960","audioId":"","activityId":"","activityType":4,"status":1,"title":"测试3","describes":"测试2，，，，","originCover":"https://test-files.shzhuoji.com/images/bannerPictureImg/2018-06-13/456455580454748160.jpg","cover":"","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/bannerPictureImg/2018-06-15/457132188786229248.jpg","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509395001927532545"},"music":null,"author":null,"statistics":{"videoId":"509395001927532545","commentNum":3,"praiseNum":2},"productInfos":[{"productId":"SH_456457857424949248","name":"SWAROVSKI 施华洛世奇 时尚天鹅 镀白金色 SWAN 天鹅手镯手链","originalPrice":1490,"detail":"唯美手链","sellPrice":1290,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456457743281160192.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456457857424949248","type":3},{"productId":"SH_456470453247016960","name":"SWAROVSKI 施华洛世奇 SWAN镀玫瑰金色女士天鹅项链","originalPrice":990,"detail":"无","sellPrice":888,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456470345721839616.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456470453247016960","type":3}],"activity":null},{"video":{"id":7,"createTime":"2018-11-07 11:50:36","updateTime":"2018-11-13 14:15:26","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509696168301690880","authorId":"509696168209416192","productId":"SH_456457857424949248","audioId":"509696168154890240","activityId":null,"activityType":2,"status":1,"title":"product-id","describes":"product-id","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510118996486389760.jpg","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-09/510383546301415424.png","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-08/510119105068531712.ogv","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509696168301690880"},"music":{"id":5,"createTime":"2018-11-07 11:50:36","updateTime":"2018-11-08 15:51:34","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"509696168154890240","title":"product-id","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456554058338009088.jpeg","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-08/510118976634748928.mp3","createUser":"975952","updateUser":"975952"},"author":{"id":5,"createTime":"2018-11-07 11:50:36","updateTime":"2018-11-08 15:51:34","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"509696168209416192","nickname":"product-id","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510119186115067904.jpg","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"509696168301690880","commentNum":3,"praiseNum":2},"productInfos":[{"productId":"SH_456457857424949248","name":"SWAROVSKI 施华洛世奇 时尚天鹅 镀白金色 SWAN 天鹅手镯手链","originalPrice":1490,"detail":"唯美手链","sellPrice":1290,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456457743281160192.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456457857424949248","type":3}],"activity":null},{"video":{"id":8,"createTime":"2018-11-07 11:51:22","updateTime":"2018-11-13 14:15:28","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509696363231969280","authorId":"509696363219386368","productId":null,"audioId":"509696363202609152","activityId":"509696363185831936","activityType":3,"status":1,"title":"acticity","describes":"acticity","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510121164157222912.jpg","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510146222225358848.jpg","videoFileName":"未知文件.mp4","playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-08/510124064266780672.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509696363231969280"},"music":{"id":6,"createTime":"2018-11-07 11:51:22","updateTime":"2018-11-12 16:00:34","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"509696363202609152","title":"acticity","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510121095643267072.jpg","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":"未知文件.mp3","playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-08/510121127788412928.mp3","createUser":"975952","updateUser":"975952"},"author":{"id":6,"createTime":"2018-11-07 11:51:22","updateTime":"2018-11-08 16:00:01","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"509696363219386368","nickname":"acticitybabab","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510121302036578304.jpg","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"509696363231969280","commentNum":1,"praiseNum":2},"productInfos":null,"activity":{"id":4,"createTime":"2018-11-07 11:51:22","updateTime":"2018-11-13 16:48:45","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"activityId":"509696363185831936","title":"aaaa","describes":null,"activityUrl":"https://test-files.shzhuoji.com/dd","activityUrlType":"1","createUser":"975952","updateUser":"975952"}},{"video":{"id":9,"createTime":"2018-11-07 13:25:06","updateTime":"2018-11-13 14:15:32","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509719951636955136","authorId":"509719951628566528","productId":"SH_456457857424949248,SH_456470453247016960","audioId":"509719951607595008","activityId":null,"activityType":2,"status":1,"title":"222","describes":"222","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510148709225332736.jpg","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510148726216458240.jpg","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-08/510148765315760128.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509719951636955136"},"music":{"id":7,"createTime":"2018-11-07 13:25:06","updateTime":"2018-11-08 17:50:08","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"509719951607595008","title":"222","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456554058338009088.jpeg","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-08/510148695602233344.mp3","createUser":"975952","updateUser":"975952"},"author":{"id":7,"createTime":"2018-11-07 13:25:06","updateTime":"2018-11-08 17:50:08","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"509719951628566528","nickname":"111","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510148803370680320.png","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"509719951636955136","commentNum":1,"praiseNum":2},"productInfos":[{"productId":"SH_456457857424949248","name":"SWAROVSKI 施华洛世奇 时尚天鹅 镀白金色 SWAN 天鹅手镯手链","originalPrice":1490,"detail":"唯美手链","sellPrice":1290,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456457743281160192.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456457857424949248","type":3},{"productId":"SH_456470453247016960","name":"SWAROVSKI 施华洛世奇 SWAN镀玫瑰金色女士天鹅项链","originalPrice":990,"detail":"无","sellPrice":888,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456470345721839616.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456470453247016960","type":3}],"activity":null},{"video":{"id":10,"createTime":"2018-11-08 16:30:51","updateTime":"2018-11-13 14:15:33","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"510129085003857920","authorId":"510129084991275008","productId":null,"audioId":"510129084966109184","activityId":null,"activityType":1,"status":1,"title":"babab","describes":"aaaa","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510129000484438016.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510129013981708288.jpg","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-08/510129049310330880.ogv","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=510129085003857920"},"music":{"id":8,"createTime":"2018-11-08 16:30:51","updateTime":"2018-11-08 16:30:51","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"510129084966109184","title":"aaa","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510128963377430528.jpg","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-08/510128988316762112.mp3","createUser":"975952","updateUser":"975952"},"author":{"id":8,"createTime":"2018-11-08 16:30:51","updateTime":"2018-11-08 16:30:51","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"510129084991275008","nickname":"nannan","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510129053915676672.jpg","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"510129085003857920","commentNum":1,"praiseNum":2},"productInfos":null,"activity":null},{"video":{"id":12,"createTime":"2018-11-12 15:33:00","updateTime":"2018-11-13 14:15:35","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"511564078800437248","authorId":"511564078779465728","productId":"SH_456457857424949248","audioId":"511564078498447360","activityId":null,"activityType":2,"status":1,"title":"testProduct","describes":"testProduct","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565652717207552.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565670140346368.png","videoFileName":"Intermission-Walk-in_512kb.mp4","playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-12/511565703262765056.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=511564078800437248"},"music":{"id":10,"createTime":"2018-11-12 15:33:00","updateTime":"2018-11-12 15:39:40","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"511564078498447360","title":"testProduct1","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565603685793792.png","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":"music.mid","playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-12/511565629950525440.mid","createUser":"975952","updateUser":"975952"},"author":{"id":10,"createTime":"2018-11-12 15:33:00","updateTime":"2018-11-12 15:39:40","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"511564078779465728","nickname":"testProduct1","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565742315929600.png","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"511564078800437248","commentNum":0,"praiseNum":2},"productInfos":[{"productId":"SH_456457857424949248","name":"SWAROVSKI 施华洛世奇 时尚天鹅 镀白金色 SWAN 天鹅手镯手链","originalPrice":1490,"detail":"唯美手链","sellPrice":1290,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456457743281160192.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456457857424949248","type":3}],"activity":null},{"video":{"id":13,"createTime":"2018-11-12 15:43:19","updateTime":"2018-11-13 14:15:37","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"511566675624067072","authorId":"511566675607289856","productId":null,"audioId":"511566675586318336","activityId":"511566675305299968","activityType":3,"status":1,"title":"testActivity12","describes":"testActivity12","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511566428332097536.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511566525350543360.png","videoFileName":"Intermission-Walk-in_512kb.mp4","playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-12/511566554563870720.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=511566675624067072"},"music":{"id":11,"createTime":"2018-11-12 15:43:19","updateTime":"2018-11-12 15:43:19","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"511566675586318336","title":"testActivity12","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511566397633986560.png","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":"song.mp3","playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-12/511566486666477568.mp3","createUser":"975952","updateUser":"975952"},"author":{"id":11,"createTime":"2018-11-12 15:43:19","updateTime":"2018-11-12 15:43:19","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"511566675607289856","nickname":"testActivity12","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511566659819929600.png","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"511566675624067072","commentNum":0,"praiseNum":2},"productInfos":null,"activity":{"id":5,"createTime":"2018-11-12 15:43:19","updateTime":"2018-11-13 16:48:49","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"activityId":"511566675305299968","title":"testActivity12","describes":null,"activityUrl":"aaa","activityUrlType":"1","createUser":"975952","updateUser":"975952"}},{"video":{"id":3,"createTime":"2018-11-06 15:53:52","updateTime":"2018-11-13 14:15:38","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509395001927532544","authorId":"509395001893978112","productId":"type=3&templateId=SH_472132376210702336&sharePage=2","audioId":"509395001868812288","activityId":"509395001633931264","activityType":2,"status":0,"title":"搞笑视频","describes":"哈哈哈哈哈哈","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511540089889751040.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511522144459423744.png","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-12/511540125881073664.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":1,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509395001927532544"},"music":{"id":3,"createTime":"2018-11-06 15:53:52","updateTime":"2018-11-12 13:58:28","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"509395001868812288","title":"嘿嘿嘿","describes":null,"author":"1112audio","coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511522194182897664.jpg","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-12/511522696077508608.mp3","createUser":"975952","updateUser":"270150"},"author":{"id":3,"createTime":"2018-11-06 15:53:52","updateTime":"2018-11-12 13:58:28","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"509395001893978112","nickname":"哈哈哈","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511522156878757888.jpg","createUser":"975952","updateUser":"270150"},"statistics":{"videoId":"509395001927532544","commentNum":0,"praiseNum":1},"productInfos":[],"activity":{"id":3,"createTime":"2018-11-06 15:53:52","updateTime":"2018-11-06 15:53:52","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"activityId":"509395001633931264","title":null,"describes":null,"activityUrl":null,"activityUrlType":"2","createUser":"975952","updateUser":"975952"}},{"video":{"id":6,"createTime":"2018-11-07 11:49:23","updateTime":"2018-11-13 14:15:25","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509695864013324288","authorId":"509695863992352768","productId":null,"audioId":"509695863644225536","activityId":null,"activityType":1,"status":1,"title":"video-122233","describes":"video-1","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-09/510389208800559104.jpg","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-09/510389411112812544.jpg","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-08/510118040826806272.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":1,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509695864013324288"},"music":{"id":4,"createTime":"2018-11-07 11:49:23","updateTime":"2018-11-09 09:45:02","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"509695863644225536","title":"video-1","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456554058338009088.jpeg","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-09/510389020509863936.mid","createUser":"975952","updateUser":"975952"},"author":{"id":4,"createTime":"2018-11-07 11:49:23","updateTime":"2018-11-08 15:19:44","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"509695863992352768","nickname":"video-133","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510111109441650688.png","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"509695864013324288","commentNum":0,"praiseNum":1},"productInfos":null,"activity":null},{"video":{"id":11,"createTime":"2018-11-12 14:51:40","updateTime":"2018-11-13 17:09:26","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"511553676398034944","authorId":"511553676377063424","productId":null,"audioId":"511553676356091904","activityId":null,"activityType":1,"status":1,"title":"testVideo","describes":"testVideo","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565396319404032.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565418758930432.png","videoFileName":"Intermission-Walk-in.ogv","playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-12/511565502997331968.ogv","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":0,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=511553676398034944"},"music":{"id":9,"createTime":"2018-11-12 14:51:40","updateTime":"2018-11-12 15:38:47","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"511553676356091904","title":"testVideo1","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565295425421312.png","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":"horse.mp3","playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-12/511565333731999744.mp3","createUser":"975952","updateUser":"975952"},"author":{"id":9,"createTime":"2018-11-12 14:51:40","updateTime":"2018-11-12 15:38:47","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"511553676377063424","nickname":"testVideoNickName","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565449368961024.png","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"511553676398034944","commentNum":4,"praiseNum":0},"productInfos":null,"activity":null},{"video":{"id":14,"createTime":"2018-11-13 16:02:04","updateTime":"2018-11-13 16:02:04","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"511933778638143488","authorId":"511933778625560576","productId":"SH_502160197096046592","audioId":"511933778583617536","activityId":null,"activityType":2,"status":1,"title":"霸道总裁-明道","describes":"王子变青蛙，呱呱呱~~~~","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511933600170508288.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511933626124861440.png","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-13/511933490246189056.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":0,"createUser":"270150","updateUser":"270150","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=511933778638143488"},"music":{"id":12,"createTime":"2018-11-13 16:02:04","updateTime":"2018-11-13 16:02:04","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"511933778583617536","title":"变形计","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511933540686888960.png","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-13/511933474500771840.mp3","createUser":"270150","updateUser":"270150"},"author":{"id":12,"createTime":"2018-11-13 16:02:04","updateTime":"2018-11-13 16:02:04","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"511933778625560576","nickname":"明道","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511933772183109632.png","createUser":"270150","updateUser":"270150"},"statistics":{"videoId":"511933778638143488","commentNum":0,"praiseNum":0},"productInfos":[{"productId":"SH_502160197096046592","name":"王向阳测试OK","originalPrice":122,"detail":"商品描述已经改了","sellPrice":145,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-10-17/502160093920362496.png","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_502160197096046592","type":3}],"activity":null},{"video":{"id":15,"createTime":"2018-11-13 17:27:50","updateTime":"2018-11-13 17:27:50","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"511955366179766272","authorId":"511955366167183360","productId":null,"audioId":"511955366150406144","activityId":"511955365844221952","activityType":3,"status":1,"title":"testActivity10","describes":"testActivity10","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511955199221301248.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511955211997151232.png","videoFileName":"Intermission-Walk-in_512kb.mp4","playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-13/511955247258664960.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":0,"createUser":"975952","updateUser":"975952","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=511955366179766272"},"music":{"id":13,"createTime":"2018-11-13 17:27:50","updateTime":"2018-11-13 17:27:50","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"511955366150406144","title":"testActivity10","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511955142338150400.png","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":"music.mid","playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-13/511955168313475072.mid","createUser":"975952","updateUser":"975952"},"author":{"id":13,"createTime":"2018-11-13 17:27:50","updateTime":"2018-11-13 17:27:50","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"511955366167183360","nickname":"testActivity10","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511955351294181376.png","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"511955366179766272","commentNum":0,"praiseNum":0},"productInfos":null,"activity":{"id":6,"createTime":"2018-11-13 17:27:50","updateTime":"2018-11-13 17:27:50","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"activityId":"511955365844221952","title":"testActivity10","describes":null,"activityUrl":"http://www.baidu.com","activityUrlType":"2","createUser":"975952","updateUser":"975952"}}]}
         */

        private Object comment;
        private ContentBean content;

        public Object getComment() {
            return comment;
        }

        public void setComment(Object comment) {
            this.comment = comment;
        }

        public ContentBean getContent() {
            return content;
        }

        public void setContent(ContentBean content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * id : null
             * createTime : null
             * updateTime : null
             * logicDelete : 0
             * pageNum : 1
             * pageSize : 15
             * operator : null
             * total : 13
             * pages : 1
             * list : [{"video":{"id":2,"createTime":"2018-11-06 10:31:23","updateTime":"2018-11-13 14:16:09","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509313843961266176","authorId":"509313843889963008","productId":"SH_456453645014138880","audioId":"509313843789299712","activityId":"509313843755745280","activityType":1,"status":0,"title":"难念的经","describes":"难念的经难念的经难念的经难念的经难念的经难念的经难念的经难念的经","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532661580234752.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532683327700992.gif","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-12/511532861027778560.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":3,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509313843961266176"},"music":{"id":2,"createTime":"2018-11-06 10:31:22","updateTime":"2018-11-12 13:29:33","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"509313843789299712","title":"难念的经","describes":null,"author":"111audio","coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532502058270720.png","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/https://test-files.shzhuoji.com/audioPlayUrl","createUser":"975952","updateUser":"270150"},"author":{"id":2,"createTime":"2018-11-06 10:31:22","updateTime":"2018-11-12 13:29:33","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"509313843889963008","nickname":"大棚哥","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532938190389248.png","createUser":"975952","updateUser":"270150"},"statistics":{"videoId":"509313843961266176","commentNum":45,"praiseNum":3},"productInfos":[{"productId":"SH_456453645014138880","name":"SWAROVSKI 施华洛世奇 天鹅 链坠 SWAN 镀白金色 锁骨链项链","originalPrice":990,"detail":"无","sellPrice":888,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456453529062604800.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456453645014138880","type":3}],"activity":{"id":2,"createTime":"2018-11-06 10:31:22","updateTime":"2018-11-06 10:31:22","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"activityId":"509313843755745280","title":null,"describes":null,"activityUrl":null,"activityUrlType":"1","createUser":"975952","updateUser":"975952"}},{"video":{"id":5,"createTime":"2018-11-06 15:53:52","updateTime":"2018-11-13 14:15:23","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509395001927532545","authorId":"","productId":"SH_456457857424949248,SH_456470453247016960","audioId":"","activityId":"","activityType":4,"status":1,"title":"测试3","describes":"测试2，，，，","originCover":"https://test-files.shzhuoji.com/images/bannerPictureImg/2018-06-13/456455580454748160.jpg","cover":"","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/bannerPictureImg/2018-06-15/457132188786229248.jpg","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509395001927532545"},"music":null,"author":null,"statistics":{"videoId":"509395001927532545","commentNum":3,"praiseNum":2},"productInfos":[{"productId":"SH_456457857424949248","name":"SWAROVSKI 施华洛世奇 时尚天鹅 镀白金色 SWAN 天鹅手镯手链","originalPrice":1490,"detail":"唯美手链","sellPrice":1290,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456457743281160192.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456457857424949248","type":3},{"productId":"SH_456470453247016960","name":"SWAROVSKI 施华洛世奇 SWAN镀玫瑰金色女士天鹅项链","originalPrice":990,"detail":"无","sellPrice":888,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456470345721839616.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456470453247016960","type":3}],"activity":null},{"video":{"id":7,"createTime":"2018-11-07 11:50:36","updateTime":"2018-11-13 14:15:26","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509696168301690880","authorId":"509696168209416192","productId":"SH_456457857424949248","audioId":"509696168154890240","activityId":null,"activityType":2,"status":1,"title":"product-id","describes":"product-id","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510118996486389760.jpg","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-09/510383546301415424.png","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-08/510119105068531712.ogv","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509696168301690880"},"music":{"id":5,"createTime":"2018-11-07 11:50:36","updateTime":"2018-11-08 15:51:34","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"509696168154890240","title":"product-id","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456554058338009088.jpeg","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-08/510118976634748928.mp3","createUser":"975952","updateUser":"975952"},"author":{"id":5,"createTime":"2018-11-07 11:50:36","updateTime":"2018-11-08 15:51:34","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"509696168209416192","nickname":"product-id","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510119186115067904.jpg","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"509696168301690880","commentNum":3,"praiseNum":2},"productInfos":[{"productId":"SH_456457857424949248","name":"SWAROVSKI 施华洛世奇 时尚天鹅 镀白金色 SWAN 天鹅手镯手链","originalPrice":1490,"detail":"唯美手链","sellPrice":1290,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456457743281160192.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456457857424949248","type":3}],"activity":null},{"video":{"id":8,"createTime":"2018-11-07 11:51:22","updateTime":"2018-11-13 14:15:28","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509696363231969280","authorId":"509696363219386368","productId":null,"audioId":"509696363202609152","activityId":"509696363185831936","activityType":3,"status":1,"title":"acticity","describes":"acticity","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510121164157222912.jpg","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510146222225358848.jpg","videoFileName":"未知文件.mp4","playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-08/510124064266780672.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509696363231969280"},"music":{"id":6,"createTime":"2018-11-07 11:51:22","updateTime":"2018-11-12 16:00:34","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"509696363202609152","title":"acticity","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510121095643267072.jpg","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":"未知文件.mp3","playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-08/510121127788412928.mp3","createUser":"975952","updateUser":"975952"},"author":{"id":6,"createTime":"2018-11-07 11:51:22","updateTime":"2018-11-08 16:00:01","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"509696363219386368","nickname":"acticitybabab","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510121302036578304.jpg","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"509696363231969280","commentNum":1,"praiseNum":2},"productInfos":null,"activity":{"id":4,"createTime":"2018-11-07 11:51:22","updateTime":"2018-11-13 16:48:45","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"activityId":"509696363185831936","title":"aaaa","describes":null,"activityUrl":"https://test-files.shzhuoji.com/dd","activityUrlType":"1","createUser":"975952","updateUser":"975952"}},{"video":{"id":9,"createTime":"2018-11-07 13:25:06","updateTime":"2018-11-13 14:15:32","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509719951636955136","authorId":"509719951628566528","productId":"SH_456457857424949248,SH_456470453247016960","audioId":"509719951607595008","activityId":null,"activityType":2,"status":1,"title":"222","describes":"222","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510148709225332736.jpg","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510148726216458240.jpg","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-08/510148765315760128.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509719951636955136"},"music":{"id":7,"createTime":"2018-11-07 13:25:06","updateTime":"2018-11-08 17:50:08","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"509719951607595008","title":"222","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456554058338009088.jpeg","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-08/510148695602233344.mp3","createUser":"975952","updateUser":"975952"},"author":{"id":7,"createTime":"2018-11-07 13:25:06","updateTime":"2018-11-08 17:50:08","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"509719951628566528","nickname":"111","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510148803370680320.png","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"509719951636955136","commentNum":1,"praiseNum":2},"productInfos":[{"productId":"SH_456457857424949248","name":"SWAROVSKI 施华洛世奇 时尚天鹅 镀白金色 SWAN 天鹅手镯手链","originalPrice":1490,"detail":"唯美手链","sellPrice":1290,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456457743281160192.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456457857424949248","type":3},{"productId":"SH_456470453247016960","name":"SWAROVSKI 施华洛世奇 SWAN镀玫瑰金色女士天鹅项链","originalPrice":990,"detail":"无","sellPrice":888,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456470345721839616.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456470453247016960","type":3}],"activity":null},{"video":{"id":10,"createTime":"2018-11-08 16:30:51","updateTime":"2018-11-13 14:15:33","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"510129085003857920","authorId":"510129084991275008","productId":null,"audioId":"510129084966109184","activityId":null,"activityType":1,"status":1,"title":"babab","describes":"aaaa","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510129000484438016.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510129013981708288.jpg","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-08/510129049310330880.ogv","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=510129085003857920"},"music":{"id":8,"createTime":"2018-11-08 16:30:51","updateTime":"2018-11-08 16:30:51","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"510129084966109184","title":"aaa","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510128963377430528.jpg","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-08/510128988316762112.mp3","createUser":"975952","updateUser":"975952"},"author":{"id":8,"createTime":"2018-11-08 16:30:51","updateTime":"2018-11-08 16:30:51","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"510129084991275008","nickname":"nannan","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510129053915676672.jpg","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"510129085003857920","commentNum":1,"praiseNum":2},"productInfos":null,"activity":null},{"video":{"id":12,"createTime":"2018-11-12 15:33:00","updateTime":"2018-11-13 14:15:35","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"511564078800437248","authorId":"511564078779465728","productId":"SH_456457857424949248","audioId":"511564078498447360","activityId":null,"activityType":2,"status":1,"title":"testProduct","describes":"testProduct","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565652717207552.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565670140346368.png","videoFileName":"Intermission-Walk-in_512kb.mp4","playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-12/511565703262765056.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=511564078800437248"},"music":{"id":10,"createTime":"2018-11-12 15:33:00","updateTime":"2018-11-12 15:39:40","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"511564078498447360","title":"testProduct1","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565603685793792.png","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":"music.mid","playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-12/511565629950525440.mid","createUser":"975952","updateUser":"975952"},"author":{"id":10,"createTime":"2018-11-12 15:33:00","updateTime":"2018-11-12 15:39:40","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"511564078779465728","nickname":"testProduct1","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565742315929600.png","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"511564078800437248","commentNum":0,"praiseNum":2},"productInfos":[{"productId":"SH_456457857424949248","name":"SWAROVSKI 施华洛世奇 时尚天鹅 镀白金色 SWAN 天鹅手镯手链","originalPrice":1490,"detail":"唯美手链","sellPrice":1290,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456457743281160192.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456457857424949248","type":3}],"activity":null},{"video":{"id":13,"createTime":"2018-11-12 15:43:19","updateTime":"2018-11-13 14:15:37","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"511566675624067072","authorId":"511566675607289856","productId":null,"audioId":"511566675586318336","activityId":"511566675305299968","activityType":3,"status":1,"title":"testActivity12","describes":"testActivity12","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511566428332097536.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511566525350543360.png","videoFileName":"Intermission-Walk-in_512kb.mp4","playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-12/511566554563870720.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":2,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=511566675624067072"},"music":{"id":11,"createTime":"2018-11-12 15:43:19","updateTime":"2018-11-12 15:43:19","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"511566675586318336","title":"testActivity12","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511566397633986560.png","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":"song.mp3","playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-12/511566486666477568.mp3","createUser":"975952","updateUser":"975952"},"author":{"id":11,"createTime":"2018-11-12 15:43:19","updateTime":"2018-11-12 15:43:19","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"511566675607289856","nickname":"testActivity12","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511566659819929600.png","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"511566675624067072","commentNum":0,"praiseNum":2},"productInfos":null,"activity":{"id":5,"createTime":"2018-11-12 15:43:19","updateTime":"2018-11-13 16:48:49","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"activityId":"511566675305299968","title":"testActivity12","describes":null,"activityUrl":"aaa","activityUrlType":"1","createUser":"975952","updateUser":"975952"}},{"video":{"id":3,"createTime":"2018-11-06 15:53:52","updateTime":"2018-11-13 14:15:38","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509395001927532544","authorId":"509395001893978112","productId":"type=3&templateId=SH_472132376210702336&sharePage=2","audioId":"509395001868812288","activityId":"509395001633931264","activityType":2,"status":0,"title":"搞笑视频","describes":"哈哈哈哈哈哈","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511540089889751040.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511522144459423744.png","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-12/511540125881073664.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":1,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509395001927532544"},"music":{"id":3,"createTime":"2018-11-06 15:53:52","updateTime":"2018-11-12 13:58:28","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"509395001868812288","title":"嘿嘿嘿","describes":null,"author":"1112audio","coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511522194182897664.jpg","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-12/511522696077508608.mp3","createUser":"975952","updateUser":"270150"},"author":{"id":3,"createTime":"2018-11-06 15:53:52","updateTime":"2018-11-12 13:58:28","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"509395001893978112","nickname":"哈哈哈","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511522156878757888.jpg","createUser":"975952","updateUser":"270150"},"statistics":{"videoId":"509395001927532544","commentNum":0,"praiseNum":1},"productInfos":[],"activity":{"id":3,"createTime":"2018-11-06 15:53:52","updateTime":"2018-11-06 15:53:52","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"activityId":"509395001633931264","title":null,"describes":null,"activityUrl":null,"activityUrlType":"2","createUser":"975952","updateUser":"975952"}},{"video":{"id":6,"createTime":"2018-11-07 11:49:23","updateTime":"2018-11-13 14:15:25","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509695864013324288","authorId":"509695863992352768","productId":null,"audioId":"509695863644225536","activityId":null,"activityType":1,"status":1,"title":"video-122233","describes":"video-1","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-09/510389208800559104.jpg","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-09/510389411112812544.jpg","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-08/510118040826806272.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":1,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509695864013324288"},"music":{"id":4,"createTime":"2018-11-07 11:49:23","updateTime":"2018-11-09 09:45:02","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"509695863644225536","title":"video-1","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456554058338009088.jpeg","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-09/510389020509863936.mid","createUser":"975952","updateUser":"975952"},"author":{"id":4,"createTime":"2018-11-07 11:49:23","updateTime":"2018-11-08 15:19:44","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"509695863992352768","nickname":"video-133","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-08/510111109441650688.png","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"509695864013324288","commentNum":0,"praiseNum":1},"productInfos":null,"activity":null},{"video":{"id":11,"createTime":"2018-11-12 14:51:40","updateTime":"2018-11-13 17:09:26","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"511553676398034944","authorId":"511553676377063424","productId":null,"audioId":"511553676356091904","activityId":null,"activityType":1,"status":1,"title":"testVideo","describes":"testVideo","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565396319404032.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565418758930432.png","videoFileName":"Intermission-Walk-in.ogv","playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-12/511565502997331968.ogv","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":0,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=511553676398034944"},"music":{"id":9,"createTime":"2018-11-12 14:51:40","updateTime":"2018-11-12 15:38:47","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"511553676356091904","title":"testVideo1","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565295425421312.png","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":"horse.mp3","playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-12/511565333731999744.mp3","createUser":"975952","updateUser":"975952"},"author":{"id":9,"createTime":"2018-11-12 14:51:40","updateTime":"2018-11-12 15:38:47","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"511553676377063424","nickname":"testVideoNickName","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511565449368961024.png","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"511553676398034944","commentNum":4,"praiseNum":0},"productInfos":null,"activity":null},{"video":{"id":14,"createTime":"2018-11-13 16:02:04","updateTime":"2018-11-13 16:02:04","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"511933778638143488","authorId":"511933778625560576","productId":"SH_502160197096046592","audioId":"511933778583617536","activityId":null,"activityType":2,"status":1,"title":"霸道总裁-明道","describes":"王子变青蛙，呱呱呱~~~~","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511933600170508288.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511933626124861440.png","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-13/511933490246189056.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":0,"createUser":"270150","updateUser":"270150","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=511933778638143488"},"music":{"id":12,"createTime":"2018-11-13 16:02:04","updateTime":"2018-11-13 16:02:04","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"511933778583617536","title":"变形计","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511933540686888960.png","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-13/511933474500771840.mp3","createUser":"270150","updateUser":"270150"},"author":{"id":12,"createTime":"2018-11-13 16:02:04","updateTime":"2018-11-13 16:02:04","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"511933778625560576","nickname":"明道","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511933772183109632.png","createUser":"270150","updateUser":"270150"},"statistics":{"videoId":"511933778638143488","commentNum":0,"praiseNum":0},"productInfos":[{"productId":"SH_502160197096046592","name":"王向阳测试OK","originalPrice":122,"detail":"商品描述已经改了","sellPrice":145,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-10-17/502160093920362496.png","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_502160197096046592","type":3}],"activity":null},{"video":{"id":15,"createTime":"2018-11-13 17:27:50","updateTime":"2018-11-13 17:27:50","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"511955366179766272","authorId":"511955366167183360","productId":null,"audioId":"511955366150406144","activityId":"511955365844221952","activityType":3,"status":1,"title":"testActivity10","describes":"testActivity10","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511955199221301248.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511955211997151232.png","videoFileName":"Intermission-Walk-in_512kb.mp4","playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-13/511955247258664960.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":0,"createUser":"975952","updateUser":"975952","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=511955366179766272"},"music":{"id":13,"createTime":"2018-11-13 17:27:50","updateTime":"2018-11-13 17:27:50","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"511955366150406144","title":"testActivity10","describes":null,"author":null,"coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511955142338150400.png","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":"music.mid","playUrl":"https://test-files.shzhuoji.com/images/videoAudioFile/2018-11-13/511955168313475072.mid","createUser":"975952","updateUser":"975952"},"author":{"id":13,"createTime":"2018-11-13 17:27:50","updateTime":"2018-11-13 17:27:50","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"511955366167183360","nickname":"testActivity10","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-13/511955351294181376.png","createUser":"975952","updateUser":"975952"},"statistics":{"videoId":"511955366179766272","commentNum":0,"praiseNum":0},"productInfos":null,"activity":{"id":6,"createTime":"2018-11-13 17:27:50","updateTime":"2018-11-13 17:27:50","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"activityId":"511955365844221952","title":"testActivity10","describes":null,"activityUrl":"http://www.baidu.com","activityUrlType":"2","createUser":"975952","updateUser":"975952"}}]
             */

            private Object id;
            private Object createTime;
            private Object updateTime;
            private int logicDelete;
            private int pageNum;
            private int pageSize;
            private Object operator;
            private int total;
            private int pages;
            private List<ListBean> list;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public int getLogicDelete() {
                return logicDelete;
            }

            public void setLogicDelete(int logicDelete) {
                this.logicDelete = logicDelete;
            }

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public Object getOperator() {
                return operator;
            }

            public void setOperator(Object operator) {
                this.operator = operator;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean  implements Serializable {
                /**
                 * video : {"id":2,"createTime":"2018-11-06 10:31:23","updateTime":"2018-11-13 14:16:09","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"videoId":"509313843961266176","authorId":"509313843889963008","productId":"SH_456453645014138880","audioId":"509313843789299712","activityId":"509313843755745280","activityType":1,"status":0,"title":"难念的经","describes":"难念的经难念的经难念的经难念的经难念的经难念的经难念的经难念的经","originCover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532661580234752.png","cover":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532683327700992.gif","videoFileName":null,"playUrl":"https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-12/511532861027778560.mp4","playUrlLowbr":"","downloadUrl":"","dynamicCover":"","praiseNum":3,"createUser":"975952","updateUser":"983674","praiseStatus":0,"shareUrl":"https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509313843961266176"}
                 * music : {"id":2,"createTime":"2018-11-06 10:31:22","updateTime":"2018-11-12 13:29:33","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"audioId":"509313843789299712","title":"难念的经","describes":null,"author":"111audio","coverLarge":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532502058270720.png","coverThumb":"","coverHd":"","coverMedium":"","audioFileName":null,"playUrl":"https://test-files.shzhuoji.com/https://test-files.shzhuoji.com/audioPlayUrl","createUser":"975952","updateUser":"270150"}
                 * author : {"id":2,"createTime":"2018-11-06 10:31:22","updateTime":"2018-11-12 13:29:33","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"authorId":"509313843889963008","nickname":"大棚哥","headImgUrl":"https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532938190389248.png","createUser":"975952","updateUser":"270150"}
                 * statistics : {"videoId":"509313843961266176","commentNum":45,"praiseNum":3}
                 * productInfos : [{"productId":"SH_456453645014138880","name":"SWAROVSKI 施华洛世奇 天鹅 链坠 SWAN 镀白金色 锁骨链项链","originalPrice":990,"detail":"无","sellPrice":888,"mainImage":"https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456453529062604800.jpg","productUrl":"https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456453645014138880","type":3}]
                 * activity : {"id":2,"createTime":"2018-11-06 10:31:22","updateTime":"2018-11-06 10:31:22","logicDelete":0,"pageNum":null,"pageSize":null,"operator":null,"activityId":"509313843755745280","title":null,"describes":null,"activityUrl":null,"activityUrlType":"1","createUser":"975952","updateUser":"975952"}
                 */

                private VideoBean video;
                private MusicBean music;
                private AuthorBean author;
                private StatisticsBean statistics;
                private ActivityBean activity;
                private List<ProductInfosBean> productInfos;

                public VideoBean getVideo() {
                    return video;
                }

                public void setVideo(VideoBean video) {
                    this.video = video;
                }

                public MusicBean getMusic() {
                    return music;
                }

                public void setMusic(MusicBean music) {
                    this.music = music;
                }

                public AuthorBean getAuthor() {
                    return author;
                }

                public void setAuthor(AuthorBean author) {
                    this.author = author;
                }

                public StatisticsBean getStatistics() {
                    return statistics;
                }

                public void setStatistics(StatisticsBean statistics) {
                    this.statistics = statistics;
                }

                public ActivityBean getActivity() {
                    return activity;
                }

                public void setActivity(ActivityBean activity) {
                    this.activity = activity;
                }

                public List<ProductInfosBean> getProductInfos() {
                    return productInfos;
                }

                public void setProductInfos(List<ProductInfosBean> productInfos) {
                    this.productInfos = productInfos;
                }

                public static class VideoBean  implements Serializable{
                    /**
                     * id : 2
                     * createTime : 2018-11-06 10:31:23
                     * updateTime : 2018-11-13 14:16:09
                     * logicDelete : 0
                     * pageNum : null
                     * pageSize : null
                     * operator : null
                     * videoId : 509313843961266176
                     * authorId : 509313843889963008
                     * productId : SH_456453645014138880
                     * audioId : 509313843789299712
                     * activityId : 509313843755745280
                     * activityType : 1
                     * status : 0
                     * title : 难念的经
                     * describes : 难念的经难念的经难念的经难念的经难念的经难念的经难念的经难念的经
                     * originCover : https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532661580234752.png
                     * cover : https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532683327700992.gif
                     * videoFileName : null
                     * playUrl : https://test-files.shzhuoji.com/images/videoVideoFile/2018-11-12/511532861027778560.mp4
                     * playUrlLowbr :
                     * downloadUrl :
                     * dynamicCover :
                     * praiseNum : 3
                     * createUser : 975952
                     * updateUser : 983674
                     * praiseStatus : 0
                     * shareUrl : https://dev-api.shbs008.com/customer_center/query_video_infos?videoId=509313843961266176
                     */

                    private int id;
                    private String createTime;
                    private String updateTime;
                    private int logicDelete;
                    private Object pageNum;
                    private Object pageSize;
                    private Object operator;
                    private String videoId;
                    private String authorId;
                    private String productId;
                    private String audioId;
                    private String activityId;
                    private int activityType;
                    private int status;
                    private String title;
                    private String describes;
                    private String originCover;
                    private String cover;
                    private Object videoFileName;
                    private String playUrl;
                    private String playUrlLowbr;
                    private String downloadUrl;
                    private String dynamicCover;
                    private int praiseNum;
                    private String createUser;
                    private String updateUser;
                    private int praiseStatus;
                    private String shareUrl;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getCreateTime() {
                        return createTime;
                    }

                    public void setCreateTime(String createTime) {
                        this.createTime = createTime;
                    }

                    public String getUpdateTime() {
                        return updateTime;
                    }

                    public void setUpdateTime(String updateTime) {
                        this.updateTime = updateTime;
                    }

                    public int getLogicDelete() {
                        return logicDelete;
                    }

                    public void setLogicDelete(int logicDelete) {
                        this.logicDelete = logicDelete;
                    }

                    public Object getPageNum() {
                        return pageNum;
                    }

                    public void setPageNum(Object pageNum) {
                        this.pageNum = pageNum;
                    }

                    public Object getPageSize() {
                        return pageSize;
                    }

                    public void setPageSize(Object pageSize) {
                        this.pageSize = pageSize;
                    }

                    public Object getOperator() {
                        return operator;
                    }

                    public void setOperator(Object operator) {
                        this.operator = operator;
                    }

                    public String getVideoId() {
                        return videoId;
                    }

                    public void setVideoId(String videoId) {
                        this.videoId = videoId;
                    }

                    public String getAuthorId() {
                        return authorId;
                    }

                    public void setAuthorId(String authorId) {
                        this.authorId = authorId;
                    }

                    public String getProductId() {
                        return productId;
                    }

                    public void setProductId(String productId) {
                        this.productId = productId;
                    }

                    public String getAudioId() {
                        return audioId;
                    }

                    public void setAudioId(String audioId) {
                        this.audioId = audioId;
                    }

                    public String getActivityId() {
                        return activityId;
                    }

                    public void setActivityId(String activityId) {
                        this.activityId = activityId;
                    }

                    public int getActivityType() {
                        return activityType;
                    }

                    public void setActivityType(int activityType) {
                        this.activityType = activityType;
                    }

                    public int getStatus() {
                        return status;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getDescribes() {
                        return describes;
                    }

                    public void setDescribes(String describes) {
                        this.describes = describes;
                    }

                    public String getOriginCover() {
                        return originCover;
                    }

                    public void setOriginCover(String originCover) {
                        this.originCover = originCover;
                    }

                    public String getCover() {
                        return cover;
                    }

                    public void setCover(String cover) {
                        this.cover = cover;
                    }

                    public Object getVideoFileName() {
                        return videoFileName;
                    }

                    public void setVideoFileName(Object videoFileName) {
                        this.videoFileName = videoFileName;
                    }

                    public String getPlayUrl() {
                        return playUrl;
                    }

                    public void setPlayUrl(String playUrl) {
                        this.playUrl = playUrl;
                    }

                    public String getPlayUrlLowbr() {
                        return playUrlLowbr;
                    }

                    public void setPlayUrlLowbr(String playUrlLowbr) {
                        this.playUrlLowbr = playUrlLowbr;
                    }

                    public String getDownloadUrl() {
                        return downloadUrl;
                    }

                    public void setDownloadUrl(String downloadUrl) {
                        this.downloadUrl = downloadUrl;
                    }

                    public String getDynamicCover() {
                        return dynamicCover;
                    }

                    public void setDynamicCover(String dynamicCover) {
                        this.dynamicCover = dynamicCover;
                    }

                    public int getPraiseNum() {
                        return praiseNum;
                    }

                    public void setPraiseNum(int praiseNum) {
                        this.praiseNum = praiseNum;
                    }

                    public String getCreateUser() {
                        return createUser;
                    }

                    public void setCreateUser(String createUser) {
                        this.createUser = createUser;
                    }

                    public String getUpdateUser() {
                        return updateUser;
                    }

                    public void setUpdateUser(String updateUser) {
                        this.updateUser = updateUser;
                    }

                    public int getPraiseStatus() {
                        return praiseStatus;
                    }

                    public void setPraiseStatus(int praiseStatus) {
                        this.praiseStatus = praiseStatus;
                    }

                    public String getShareUrl() {
                        return shareUrl;
                    }

                    public void setShareUrl(String shareUrl) {
                        this.shareUrl = shareUrl;
                    }
                }

                public static class MusicBean implements Serializable {
                    /**
                     * id : 2
                     * createTime : 2018-11-06 10:31:22
                     * updateTime : 2018-11-12 13:29:33
                     * logicDelete : 0
                     * pageNum : null
                     * pageSize : null
                     * operator : null
                     * audioId : 509313843789299712
                     * title : 难念的经
                     * describes : null
                     * author : 111audio
                     * coverLarge : https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532502058270720.png
                     * coverThumb :
                     * coverHd :
                     * coverMedium :
                     * audioFileName : null
                     * playUrl : https://test-files.shzhuoji.com/https://test-files.shzhuoji.com/audioPlayUrl
                     * createUser : 975952
                     * updateUser : 270150
                     */

                    private int id;
                    private String createTime;
                    private String updateTime;
                    private int logicDelete;
                    private Object pageNum;
                    private Object pageSize;
                    private Object operator;
                    private String audioId;
                    private String title;
                    private Object describes;
                    private String author;
                    private String coverLarge;
                    private String coverThumb;
                    private String coverHd;
                    private String coverMedium;
                    private Object audioFileName;
                    private String playUrl;
                    private String createUser;
                    private String updateUser;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getCreateTime() {
                        return createTime;
                    }

                    public void setCreateTime(String createTime) {
                        this.createTime = createTime;
                    }

                    public String getUpdateTime() {
                        return updateTime;
                    }

                    public void setUpdateTime(String updateTime) {
                        this.updateTime = updateTime;
                    }

                    public int getLogicDelete() {
                        return logicDelete;
                    }

                    public void setLogicDelete(int logicDelete) {
                        this.logicDelete = logicDelete;
                    }

                    public Object getPageNum() {
                        return pageNum;
                    }

                    public void setPageNum(Object pageNum) {
                        this.pageNum = pageNum;
                    }

                    public Object getPageSize() {
                        return pageSize;
                    }

                    public void setPageSize(Object pageSize) {
                        this.pageSize = pageSize;
                    }

                    public Object getOperator() {
                        return operator;
                    }

                    public void setOperator(Object operator) {
                        this.operator = operator;
                    }

                    public String getAudioId() {
                        return audioId;
                    }

                    public void setAudioId(String audioId) {
                        this.audioId = audioId;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public Object getDescribes() {
                        return describes;
                    }

                    public void setDescribes(Object describes) {
                        this.describes = describes;
                    }

                    public String getAuthor() {
                        return author;
                    }

                    public void setAuthor(String author) {
                        this.author = author;
                    }

                    public String getCoverLarge() {
                        return coverLarge;
                    }

                    public void setCoverLarge(String coverLarge) {
                        this.coverLarge = coverLarge;
                    }

                    public String getCoverThumb() {
                        return coverThumb;
                    }

                    public void setCoverThumb(String coverThumb) {
                        this.coverThumb = coverThumb;
                    }

                    public String getCoverHd() {
                        return coverHd;
                    }

                    public void setCoverHd(String coverHd) {
                        this.coverHd = coverHd;
                    }

                    public String getCoverMedium() {
                        return coverMedium;
                    }

                    public void setCoverMedium(String coverMedium) {
                        this.coverMedium = coverMedium;
                    }

                    public Object getAudioFileName() {
                        return audioFileName;
                    }

                    public void setAudioFileName(Object audioFileName) {
                        this.audioFileName = audioFileName;
                    }

                    public String getPlayUrl() {
                        return playUrl;
                    }

                    public void setPlayUrl(String playUrl) {
                        this.playUrl = playUrl;
                    }

                    public String getCreateUser() {
                        return createUser;
                    }

                    public void setCreateUser(String createUser) {
                        this.createUser = createUser;
                    }

                    public String getUpdateUser() {
                        return updateUser;
                    }

                    public void setUpdateUser(String updateUser) {
                        this.updateUser = updateUser;
                    }
                }

                public static class AuthorBean  implements Serializable{
                    /**
                     * id : 2
                     * createTime : 2018-11-06 10:31:22
                     * updateTime : 2018-11-12 13:29:33
                     * logicDelete : 0
                     * pageNum : null
                     * pageSize : null
                     * operator : null
                     * authorId : 509313843889963008
                     * nickname : 大棚哥
                     * headImgUrl : https://test-files.shzhuoji.com/images/videoPictureImg/2018-11-12/511532938190389248.png
                     * createUser : 975952
                     * updateUser : 270150
                     */

                    private int id;
                    private String createTime;
                    private String updateTime;
                    private int logicDelete;
                    private Object pageNum;
                    private Object pageSize;
                    private Object operator;
                    private String authorId;
                    private String nickname;
                    private String headImgUrl;
                    private String createUser;
                    private String updateUser;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getCreateTime() {
                        return createTime;
                    }

                    public void setCreateTime(String createTime) {
                        this.createTime = createTime;
                    }

                    public String getUpdateTime() {
                        return updateTime;
                    }

                    public void setUpdateTime(String updateTime) {
                        this.updateTime = updateTime;
                    }

                    public int getLogicDelete() {
                        return logicDelete;
                    }

                    public void setLogicDelete(int logicDelete) {
                        this.logicDelete = logicDelete;
                    }

                    public Object getPageNum() {
                        return pageNum;
                    }

                    public void setPageNum(Object pageNum) {
                        this.pageNum = pageNum;
                    }

                    public Object getPageSize() {
                        return pageSize;
                    }

                    public void setPageSize(Object pageSize) {
                        this.pageSize = pageSize;
                    }

                    public Object getOperator() {
                        return operator;
                    }

                    public void setOperator(Object operator) {
                        this.operator = operator;
                    }

                    public String getAuthorId() {
                        return authorId;
                    }

                    public void setAuthorId(String authorId) {
                        this.authorId = authorId;
                    }

                    public String getNickname() {
                        return nickname;
                    }

                    public void setNickname(String nickname) {
                        this.nickname = nickname;
                    }

                    public String getHeadImgUrl() {
                        return headImgUrl;
                    }

                    public void setHeadImgUrl(String headImgUrl) {
                        this.headImgUrl = headImgUrl;
                    }

                    public String getCreateUser() {
                        return createUser;
                    }

                    public void setCreateUser(String createUser) {
                        this.createUser = createUser;
                    }

                    public String getUpdateUser() {
                        return updateUser;
                    }

                    public void setUpdateUser(String updateUser) {
                        this.updateUser = updateUser;
                    }
                }

                public static class StatisticsBean implements Serializable {
                    /**
                     * videoId : 509313843961266176
                     * commentNum : 45
                     * praiseNum : 3
                     */

                    private String videoId;
                    private int commentNum;
                    private int praiseNum;

                    public String getVideoId() {
                        return videoId;
                    }

                    public void setVideoId(String videoId) {
                        this.videoId = videoId;
                    }

                    public int getCommentNum() {
                        return commentNum;
                    }

                    public void setCommentNum(int commentNum) {
                        this.commentNum = commentNum;
                    }

                    public int getPraiseNum() {
                        return praiseNum;
                    }

                    public void setPraiseNum(int praiseNum) {
                        this.praiseNum = praiseNum;
                    }
                }

                public static class ActivityBean implements Serializable {
                    /**
                     * id : 2
                     * createTime : 2018-11-06 10:31:22
                     * updateTime : 2018-11-06 10:31:22
                     * logicDelete : 0
                     * pageNum : null
                     * pageSize : null
                     * operator : null
                     * activityId : 509313843755745280
                     * title : null
                     * describes : null
                     * activityUrl : null
                     * activityUrlType : 1
                     * createUser : 975952
                     * updateUser : 975952
                     */

                    private int id;
                    private String createTime;
                    private String updateTime;
                    private int logicDelete;
                    private Object pageNum;
                    private Object pageSize;
                    private Object operator;
                    private String activityId;
                    private String  title;
                    private Object describes;
                    private String activityUrl;
                    private int activityUrlType;
                    private String createUser;
                    private String updateUser;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getCreateTime() {
                        return createTime;
                    }

                    public void setCreateTime(String createTime) {
                        this.createTime = createTime;
                    }

                    public String getUpdateTime() {
                        return updateTime;
                    }

                    public void setUpdateTime(String updateTime) {
                        this.updateTime = updateTime;
                    }

                    public int getLogicDelete() {
                        return logicDelete;
                    }

                    public void setLogicDelete(int logicDelete) {
                        this.logicDelete = logicDelete;
                    }

                    public Object getPageNum() {
                        return pageNum;
                    }

                    public void setPageNum(Object pageNum) {
                        this.pageNum = pageNum;
                    }

                    public Object getPageSize() {
                        return pageSize;
                    }

                    public void setPageSize(Object pageSize) {
                        this.pageSize = pageSize;
                    }

                    public Object getOperator() {
                        return operator;
                    }

                    public void setOperator(Object operator) {
                        this.operator = operator;
                    }

                    public String getActivityId() {
                        return activityId;
                    }

                    public void setActivityId(String activityId) {
                        this.activityId = activityId;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public Object getDescribes() {
                        return describes;
                    }

                    public void setDescribes(Object describes) {
                        this.describes = describes;
                    }

                    public String getActivityUrl() {
                        return activityUrl;
                    }

                    public void setActivityUrl(String activityUrl) {
                        this.activityUrl = activityUrl;
                    }

                    public int getActivityUrlType() {
                        return activityUrlType;
                    }

                    public void setActivityUrlType(int activityUrlType) {
                        this.activityUrlType = activityUrlType;
                    }

                    public String getCreateUser() {
                        return createUser;
                    }

                    public void setCreateUser(String createUser) {
                        this.createUser = createUser;
                    }

                    public String getUpdateUser() {
                        return updateUser;
                    }

                    public void setUpdateUser(String updateUser) {
                        this.updateUser = updateUser;
                    }
                }

                public static class ProductInfosBean  implements Serializable{
                    /**
                     * productId : SH_456453645014138880
                     * name : SWAROVSKI 施华洛世奇 天鹅 链坠 SWAN 镀白金色 锁骨链项链
                     * originalPrice : 990
                     * detail : 无
                     * sellPrice : 888
                     * mainImage : https://test-files.shzhuoji.com/images/sharedProductMainImg/2018-06-13/456453529062604800.jpg
                     * productUrl : https://share-m.shzhuoji.com/#/home/product_details?type=3&templateId=SH_456453645014138880
                     * type : 3
                     */

                    private String productId;
                    private String name;
                    private int originalPrice;
                    private String detail;
                    private int sellPrice;
                    private String mainImage;
                    private String productUrl;
                    private int type;

                    public String getProductId() {
                        return productId;
                    }

                    public void setProductId(String productId) {
                        this.productId = productId;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getOriginalPrice() {
                        return originalPrice;
                    }

                    public void setOriginalPrice(int originalPrice) {
                        this.originalPrice = originalPrice;
                    }

                    public String getDetail() {
                        return detail;
                    }

                    public void setDetail(String detail) {
                        this.detail = detail;
                    }

                    public int getSellPrice() {
                        return sellPrice;
                    }

                    public void setSellPrice(int sellPrice) {
                        this.sellPrice = sellPrice;
                    }

                    public String getMainImage() {
                        return mainImage;
                    }

                    public void setMainImage(String mainImage) {
                        this.mainImage = mainImage;
                    }

                    public String getProductUrl() {
                        return productUrl;
                    }

                    public void setProductUrl(String productUrl) {
                        this.productUrl = productUrl;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }
                }
            }
        }
    }

    public static class TailBean {
        /**
         * channel : null
         * product : null
         * system : null
         */

        private Object channel;
        private Object product;
        private Object system;

        public Object getChannel() {
            return channel;
        }

        public void setChannel(Object channel) {
            this.channel = channel;
        }

        public Object getProduct() {
            return product;
        }

        public void setProduct(Object product) {
            this.product = product;
        }

        public Object getSystem() {
            return system;
        }

        public void setSystem(Object system) {
            this.system = system;
        }
    }
}
