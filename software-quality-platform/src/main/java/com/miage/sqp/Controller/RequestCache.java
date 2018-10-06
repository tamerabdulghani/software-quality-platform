/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.sqp.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.authenticator.SavedRequest;

/**
 *
 * @author kimphuong
 */
public interface RequestCache {
    void saveRequest(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Returns the saved request, leaving it cached.
	 *
	 * @param request the current request
	 * @return the saved request which was previously cached, or null if there is none.
	 */
	SavedRequest getRequest(HttpServletRequest request, HttpServletResponse response);

	/**
	 * Returns a wrapper around the saved request, if it matches the current request. The
	 * saved request should be removed from the cache.
	 *
	 * @param request
	 * @param response
	 * @return the wrapped save request, if it matches the original, or null if there is
	 * no cached request or it doesn't match.
	 */
	HttpServletRequest getMatchingRequest(HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * Removes the cached request.
	 *
	 * @param request the current request, allowing access to the cache.
	 */
	void removeRequest(HttpServletRequest request, HttpServletResponse response);
    
}
