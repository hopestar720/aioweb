package com.xhsoft.framework.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RegexRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

import com.xhsoft.framework.security.model.UserDetails;
import com.xhsoft.framework.uam.entity.Resource;
import com.xhsoft.framework.uam.entity.Role;
import com.xhsoft.framework.uam.service.ResourceService;
import com.xhsoft.framework.uam.service.RoleService;


public class SecurityMetadataSourceExtendImpl implements SecurityMetadataSourceExtend {
	
	private static final Log logger = LogFactory.getLog(SecurityMetadataSourceExtendImpl.class);

	private RoleService roleService; // ��ɫ������

	private ResourceService resourceService; // ��Դ������

	private RequestMatcher requestMatcher; // ƥ�����

	private String matcher; // �����ʶ

	private SessionRegistry sessionRegistry; // session���

	private static Map<String, Collection<ConfigAttribute>> kv = new ConcurrentHashMap<String, Collection<ConfigAttribute>>(); // ��Դ����

	public RoleService getRoleService() {
		return roleService;
	}

	@javax.annotation.Resource(name = "roleService")
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public ResourceService getResourceService() {
		return resourceService;
	}

	@javax.annotation.Resource(name = "resourceService")
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public SessionRegistry getSessionRegistry() {
		return sessionRegistry;
	}

	@javax.annotation.Resource(name = "sessionRegistry")
	public void setSessionRegistry(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	// ��ʼ������ʱ������ݿ��ж�ȡ��Դ
	@PostConstruct
	public void init() {
		load();
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> attributes = new HashSet<ConfigAttribute>();
		for (Map.Entry<String, Collection<ConfigAttribute>> entry : kv
				.entrySet()) {
			attributes.addAll(entry.getValue());
		}
		return attributes;
	}

	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		HttpServletRequest request = ((FilterInvocation) object).getRequest();

		// DEBUGģʽ��ʾ������Դ·��
		if (logger.isDebugEnabled())
			logger.debug("��ǰ����URL: " + request.getRequestURI());

		// ��������뵱ǰ��Դƥ�����ȷ��
		Iterator<String> iterator = kv.keySet().iterator();
		while (iterator.hasNext()) {
			String uri = iterator.next();
			if (matcher.toLowerCase().equals("ant")) {
				requestMatcher = new AntPathRequestMatcher(uri);
			}
			if (matcher.toLowerCase().equals("regex")) {
				requestMatcher = new RegexRequestMatcher(uri, request
						.getMethod(), true);
			}
			if (requestMatcher.matches(request))
				return kv.get(uri);
		}
		return null;
	}

	/**
	 * ����������Դ��Ȩ�޵Ĺ�ϵ
	 */
	@SuppressWarnings("unchecked")
	public void load() {
		List<Resource> resources = this.getResourceService().findAllResources(null);
		for (Resource resource : resources) {
			//if (resource.getMold().equals("1"))
				kv.put(resource.getUri(), list2Collection(resource.getRoles()));
		}
	}

	/**
	 * ��List<Role>����ת��Ϊ�����Ҫ��Collection<ConfigAttribute>����
	 * 
	 * @param roles
	 * @return Collection<ConfigAttribute>
	 */
	private Collection<ConfigAttribute> list2Collection(List<Role> roles) {
		List<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
		for (Role role : roles)
			list.add(new SecurityConfig(role.getName()));
		return list;
	}

	public void setMatcher(String matcher) {
		this.matcher = matcher;
	}

	public void expireNow() {
		kv.clear();
		load();
		shotOff();
	}

	/** ��ȫ���û��߳�ϵͳ,�������µ�¼ */
	private void shotOff() {
		List<Object> users = sessionRegistry.getAllPrincipals();
		if (logger.isDebugEnabled())
			logger.debug("��ǰ�û���: " + users.size());
		// ���������û�
		for (Object o : users) {
			if (logger.isDebugEnabled()) {
				UserDetails user = (UserDetails) o;
				logger.debug("��ǰ�û���: " + user.getUsername());
			}
			for (SessionInformation information : sessionRegistry
					.getAllSessions(o, false)) {
				information.expireNow();
				sessionRegistry.removeSessionInformation(information
						.getSessionId());
			}
		}
	}
}
