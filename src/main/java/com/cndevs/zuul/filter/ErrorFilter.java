package com.cndevs.zuul.filter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ErrorFilter extends ZuulFilter {
	
	private static Logger log=LoggerFactory.getLogger(ErrorFilter.class);
	

	@Override
	public Object run() {
		
		
		RequestContext ctx=RequestContext.getCurrentContext();
		
		Throwable th=ctx.getThrowable();
		log.error("this is a errorFilter:{}",th.getCause().getMessage());
		ctx.set("error.status_code",HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		ctx.set("error.exception",th.getCause());
		return null;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "error";
	}

}
