# MyApplicationLruCacheTest
图片的三级缓存

使用方式方法：
 //url 网络图片链接
 //mInageView 控件位置布局ID
 //默认初始化 图片
 //失败下载 图片
 ImageCacheManager.loadImage(url, mImageView, getBitmapFromRes(R.mipmap.ic_launcher), getBitmapFromRes(R.mipmap.ic_launcher));

 
 对一个可运行的快速开发框架来说，封装三级缓存是必须的。一步步完善框架：
 1、基于Volley 封装三级图片缓存，主要依赖一级内存缓存lrucache和二级磁盘缓存dislrucache。    //2016/5/23
