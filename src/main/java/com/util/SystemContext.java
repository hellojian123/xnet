package com.util;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;

public class SystemContext {
	
	/**
	 * 显示的最大帖子数
	 */
	public static final int POSTS_MAX_SIZE=10;
	
	public static final int PAGE_SIZE = 10;
	/**
	 * 精华帖显示条数
	 */
	public static final int JINGPAGESIZE = 10;
	/**
	 * 热门帖显示条数						
	 */
	public static final int HOTPAGESIZE = 10;
	/**
	 * 最新帖显示条数
	 */
	public static final int NEWPAGESIZE = 10;
	/**
	 *帖子列表页面分页显示条数 
	 */
	public static final int SNSBBSPAGESIZE = 20;
	/**
	 * 帖子详情回复条数
	 */
	public static final int SNSBBSREPLYPAGESIZE = 10;
	/**
	 * 博客日志每页显示条数
	 */
	public static final int BLOGDAILYPAGESIZE = 15;//15
	/**
	 * 博客日志详细页面评论每页显示条数
	 */
	public static final int BLOGDAILYCOMMENTPAGESIZE = 10;
	/**
	 * 博客心情每页显示条数
	 */
	public static final int BLOGMOODPAGESIZE = 10;
	/**
  	 * 博客留言每页显示条数
	 */
	public static final int BLOGMESSAGEPAGESIZE = 10;
	
	/**
	 * 博客个人中心显示条数
	 */
	public static final int BLOGCENTERPAGESIZE = 10;
	
	public static final String BBS_FROM_SOURCE_BBS = "BBS";
	public static final String BBS_FROM_SOURCE_CIRCLE = "CIRCLE";
	
	public static final String BBS_TOP_MSG="BBS_TOPMSG";
	public static final String BLOG_TOP_MSG="BLOG_TOPMSG";
	public static final String INDEX_TOP_MSG="INDEX_TOPMSG";
	public static final String OTHER_TOP_MSG="OTHER_TOPMSG";
	public static final String CIRCLE_TOP_MSG="CIRCLE_TOPMSG";
	public static final String VOTE_TOPMSG = "VOTE_TOPMSG";
	public static final String MSG_TOPMSG = "MSG_TOPMSG";
	public static final String FRIEND_TOPMSG = "FRIEND_TOPMSG";
	
	/**
	 * 获取一个无条件的Condition
	 * @return
	 */
	public static Condition getNormalCondition(){
		return Cnd.where("1", "=", "1");
	}
}
