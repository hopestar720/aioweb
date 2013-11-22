package com.xhsoft.framework.base.action;

import java.lang.reflect.ParameterizedType;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.xhsoft.framework.base.entity.AbstractEntity;
import com.xhsoft.framework.base.service.AppServiceHelper;
import com.xhsoft.framework.base.service.IBaseService;
//import com.xhsoft.framework.common.lucene.Lucene;
//import com.xhsoft.framework.common.lucene.LuceneUtil;
import com.xhsoft.framework.common.page.Page;
//import com.xhsoft.framework.common.security.SpringSecurityUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.util.ValueStack;


public class BaseAction<T extends AbstractEntity> extends ActionSupport implements Preparable,
		ModelDriven<T> {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -523098378110935838L;
	
	public final static String PARAMS_KEY_PAGE_NO = "pn";
	public final static String PARAMS_KEY_PAGE_LIMIT = "limit";
	public final static String PARAMS_KEY_PAGE_OBJECT = "page";
	protected final static int PAGE_LIMIT = 10;

	public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	// 放置查询参数的Map对象
	private Map<String, String> qm = new HashMap<String, String>();

	// list页面定义
	public static final String LIST = "list";
	//索引页面定义
	public static final String INDEX = "index";

	// logger for subclass
	private static final Log logger = LogFactory.getLog(BaseAction.class);

	private int messageCount = 0;

	// the model
	protected T entity;

	// model's Class
	protected Class<T> entityClass;

	// model's ClassName
	protected String entityClassName;

	// list页面显示的对象列表
	protected List<T> entities;
	
	@SuppressWarnings("unchecked")
	public BaseAction() {
		super();
		// 通过反射取得Entity的Class.
		try {
			entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			entityClassName = entityClass.getSimpleName();
		} catch (RuntimeException e) {
		}
	}
	
	public Map<String, String> getQm() {
		return qm;
	}

	public void setQm(Map<String, String> qm) {
		this.qm = qm;
	}
	
	/**
	 * 实现Preparable接口方法，准备填充实体对象
	 * @see Preparable
	 */
	public void prepare() throws Exception {
		if (entityClass == null) {
			return;
		}
		if (getSid() != null) {
			entity = getBaseService().findByPrimaryKey(getSid());
			if (entity == null)
				throw new IllegalArgumentException("id not exist");
		} else {
			entity = entityClass.newInstance();
		}
		qm.clear();
	}

	/**
	 * 从参数中取得主键参数值,可在子类重载
	 */
	protected Long getSid() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String sid = request.getParameter("sid");
		if (StringUtils.isNotEmpty(sid)) {
			return new Long(sid);
		} else {
			return null;
		}
	}
	
	/**
	 * 实现ModelDriven接口方法，返回模型对象
	 * @see ModelDriven
	 */
	public T getModel() {
		return entity;
	}
	
	/**
	* 替换文件上传中出现的错误信息
	* */
	@Override
	public void addActionError(String anErrorMessage){
		//这里要先判断一下，是我们要替换的错误，才处理
		if (anErrorMessage.startsWith("the request was rejected because its size")) {
			if (this.messageCount == 0) {
				//这些只是将原信息中的文件大小提取出来。
				Matcher m = Pattern.compile("\\d+").matcher(anErrorMessage);
				String s1 = "";
				if (m.find()) {
					s1 = m.group();
				}
				String s2 = "";
				if (m.find()) {
					s2 = m.group();
				}
				//偷梁换柱，将信息替换掉
				super.addActionError(getText("struts.action.message.error.upload.before") + s1
						+ getText("struts.action.message.error.upload.after") + s2
						+ getText("struts.action.message.error.upload.end"));
				this.messageCount++;
			}
		} else {//不是则不管它
			super.addActionError(anErrorMessage);
		}
	}
	
	@Override
	public void addFieldError(String fieldName, String errorMessage) {
		super.addFieldError(fieldName, errorMessage);
	}
	
	
	protected void saveBizMessage(String message) {
		if (StringUtils.isEmpty(message)) {
			logger.error("biz message can't empty,please check i18n config!");
			message = "<font color='red'>Undefine message,please contact to Admin!</font>";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		logger.debug("save message:" + message);
		session.setAttribute("message", message);

	}
	
	protected void saveBizError(String error) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		logger.debug("save error:" + error);
		session.setAttribute("error", error);
	}
	
	protected Object findBean(String beanId) {
		return AppServiceHelper.findBean(beanId);
	}
	
	/**
	 * 取得Service接口对象
	 * 默认以实体对象类名的前缀小写形式+Service获取对应的Service对象实例
	 * 详见初始化构造函数
	 */
	@SuppressWarnings("unchecked")
	protected IBaseService<T> getBaseService() {
		IBaseService<T> baseService = (IBaseService<T>) findBean(StringUtils.uncapitalize(entityClassName) + "Service");
		logger.debug("baseService=" + baseService);
		return baseService;
	}
	
	/**
	 * 在调用Serivce的分页方法之前，预处理分页相关参数
	 * @param request
	 * @return
	 * @throws Exception
	 * @author xiali2
	 * @since  2008-9-16
	 */
	protected Map<String, Object> preparePageParams(HttpServletRequest request) throws Exception {
		String query = request.getQueryString();
		logger.debug("query=" + query);
		String pageURL = request.getServletPath();
		if (query != null && query.length() > 0) {
			pageURL += "?" + query;
		}
		request.setAttribute("contextURL", URLEncoder.encode(pageURL, "UTF-8"));
		logger.debug("pageURL=" + request.getContextPath() + pageURL);
		request.setAttribute("pageURL", request.getContextPath() + pageURL);
		logger.debug("pageURL=" + pageURL);
		Map<String, Object> params = new HashMap<String, Object>();
		Set<Map.Entry<String, String>> p = qm.entrySet();
		for (Map.Entry<String, String> me : p) {
			String name = me.getKey();
			String value = new String(me.getValue().trim().getBytes("ISO8859-1"), "UTF-8");
			if (StringUtils.isNotEmpty(value)) {
				params.put(name, value);
			}
		}
		logger.debug("params=" + params);
		int pageNo = 1;
		if (!StringUtils.isEmpty(qm.get(PARAMS_KEY_PAGE_NO))) {
			try {
				pageNo = Integer.parseInt(qm.get(PARAMS_KEY_PAGE_NO));
			} catch (RuntimeException e) {
			}
		}
		params.put(PARAMS_KEY_PAGE_NO, new Integer(pageNo));
		int limit = PAGE_LIMIT;
		if (!StringUtils.isEmpty(qm.get(PARAMS_KEY_PAGE_LIMIT))) {
			try {
				limit = Integer.parseInt(qm.get(PARAMS_KEY_PAGE_LIMIT));
			} catch (RuntimeException e) {
			}
		}
		params.put(PARAMS_KEY_PAGE_LIMIT, new Integer(limit));
		return params;
	}
	
	/**
	 * 分页查询方法
	 * @return
	 * @throws Exception
	 */
	public String findByPage() throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			Map<String, Object> params = this.preparePageParams(request);
//			params.put("isDeleteState", 0);
			//添加数据过滤开始		
		
			//获取当前登录用户名
	    	/*String username=SpringSecurityUtils.getCurrentUserName();
	    	
	    	if(!username.equals("")&&username!=null){
	    		//获取当前实体类
		    	String table=entity.getClass().toString();
		    	
		    	//查询当前用户名与当前实体类有无规则限制
		    	List<Lucene> rulelist=LuceneUtil.searchIndexByNameAndTable(username,table.substring(table.indexOf("com.gytech.framework")),"1");
		    	
				for(Lucene l:rulelist)
				{
					if(l.getMatchType().equals("_eq")){
						params.put(l.getColumn(), l.getMatchValue());
					}else{
						params.put(l.getMatchType()+l.getColumn(), l.getMatchValue());
					}
				}
			}*/
			//添加数据过滤结束
	    	
			Page<T> page = getBaseService().findByPage(params, (Integer) params.get(PARAMS_KEY_PAGE_NO),
					(Integer) params.get(PARAMS_KEY_PAGE_LIMIT));
			entities = page.getResultList();
			request.setAttribute(PARAMS_KEY_PAGE_OBJECT, page);
			onFindByPage(request);
			
		} catch (Exception de) {
			de.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 定义一个回调方法，默认基类没有任何实现，子类根据需要覆写该方法实现为findByPage方法补充额外数据
	 * 主要用于分页页面初始化用于转移相关的对象
	 * @param request
	 * @author xiali2
	 * @since  2008-7-31
	 */
	protected void onFindByPage(HttpServletRequest request) {

	}
	
	/**
	 * 列表显示所有记录(考虑显示效率问题一般少用，而是采用分页显示方式)
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		entities = getBaseService().findAll();
		return SUCCESS;
	}
	
	/**
	 * 显示新增或修改的表单
	 */
	@Override
	public String input() throws Exception {
		return INPUT;
	}
	
	/**
	 * 新增或修改对象保存
	 * 如果页面没有主键参数值，则表示是创建对象；如果页面有主键参数值，表示更新对象
	 * @return
	 * @throws Exception
	 */
	public String save()throws Exception{
		logger.debug("entity =" + entity);
		HttpServletRequest request = ServletActionContext.getRequest();
		if (getSid() == null) {
			getBaseService().insert(entity);
			request.setAttribute("method", "insert");
			saveBizMessage(getText("tips.insert.success", new String[] { String.valueOf(entity.getSid()) }));
		} else {
			getBaseService().update(entity);
			request.setAttribute("method", "update");
			saveBizMessage(getText("tips.update.success", new String[] { String.valueOf(entity.getSid()) }));
		}
		return SUCCESS;
	}

	/**
	 * 删除对象
	 * 传入待删除对象参数的数组，然后调用Service层批量删除方法
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] sids = request.getParameterValues("_sid");
		Collection<T> deleteEntities = new HashSet<T>();
		if (sids != null && sids.length > 0) {
			for (String sid : sids) {
				T entity = getBaseService().findByPrimaryKey(new Long(sid));
				if (entity != null) {
					deleteEntities.add(entity);
				}
			}
		}
		try {
			getBaseService().deleteAll(deleteEntities);
		} catch (Exception de) {
			de.printStackTrace();
		}
		request.setAttribute("method", "delete");
		saveBizMessage(getText("tips.delete.success", new String[] { String.valueOf(deleteEntities.size()) }));
		return SUCCESS;
	}
	
	/**
	 * 默认执行函数
	 */
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	/**
	 * 向显示页面返回对象列表.
	 */
	public List<T> getEntities() {
		return entities;
	}
	
	/**
	 * 获取request对象
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获取response对象
	 * @return
	 */
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**获取session对象*/
	protected HttpSession getSession(boolean... create) {
		boolean cr = false;
		if (create.length > 0) {
			cr = create[0];
		}
		return ServletActionContext.getRequest().getSession(cr);
	}

	/**设置Session属性*/
	protected void setSessionAttribute(String name, Object value) {
		getSession().setAttribute(name, value);
	}

	/**获取Session属性 */
	protected Object getSessionAttribute(String name) {
		return getSession().getAttribute(name);
	}

	/**删除Session属性*/
	protected void removeSessionAttribute(String name) {
		getSession().removeAttribute(name);
	}

	/**
	 * 获取ValueStack对象
	 * @return
	 */
	protected ValueStack getValueStack() {
		ActionContext ac = ActionContext.getContext();
		return ac.getValueStack();
	}

	/**获取服务器路径*/
	protected String getRealPath(String url) {
		return ServletActionContext.getServletContext().getRealPath(url);
	}
	
	public void beforeAnsy(){
		getResponse().setCharacterEncoding("UTF-8");
		getResponse().setHeader("Cache-Control","no-cache");
		getResponse().setHeader("Pragma","no-cache");
		getResponse().setDateHeader("Expires",0);
	}

}
