package com.cafe24.shoppingmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cafe24.shoppingmall.dto.JSONResult;
import com.cafe24.shoppingmall.util.APIServerURL;
import com.cafe24.shoppingmall.vo.ProductVo;
import com.cafe24.shoppingmall.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public JSONResult checkOverlapId(String checkId) {
		
		String endpoint = APIServerURL.getContextURL()+"/user/checkid/{id}";
		
		ResponseEntity<JSONResult> response = restTemplate.exchange(
				endpoint, HttpMethod.GET, null ,
				  new ParameterizedTypeReference<JSONResult>(){}, checkId);
		
		JSONResult jsonResult = response.getBody();
		
		return jsonResult;
	}
	
	public List<UserVo> getUserList() {
		
		String endpoint = APIServerURL.getContextURL()+"/user/list";
		
		ResponseEntity<JSONResult> response = restTemplate.exchange(
				endpoint, HttpMethod.GET, null ,
				  new ParameterizedTypeReference<JSONResult>(){});
		@SuppressWarnings("unchecked")
		List<UserVo> userList = (List<UserVo>)response.getBody().getData();
		
		return userList;
	}

	public JSONResult join(UserVo userVo) {
		
		String endpoint = APIServerURL.getContextURL()+"/user/join";
		

		HttpEntity<UserVo> request = new HttpEntity<>(userVo);
			      
		ResponseEntity<JSONResult> response = restTemplate
			            .exchange(endpoint, HttpMethod.POST, request, JSONResult.class);
			      
		return response.getBody();
		
	}

	public UserVo login(UserVo userVo) {
		String endpoint = APIServerURL.getContextURL()+"/user/login";
		
		
		HttpEntity<UserVo> request = new HttpEntity<>(userVo);
			      
		ResponseEntity<JSONResult<UserVo>> response = restTemplate
			            .exchange(endpoint, HttpMethod.POST, request,
			            		new ParameterizedTypeReference<JSONResult<UserVo>>(){});
			
		return response.getBody().getData();
	}
	
	
	
	
	
}
