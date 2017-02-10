package com.example.helpdesk.services;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConversationClientImpl extends AbstractRestClient{

	private String loginToken = "";
	
	public ConversationClientImpl(){
	}
	
	@Override
	protected String appendToPath(String currentPath) {
		return currentPath;
	}
	
	public Boolean createConversationAndAddMessage(String jsonRequest){
		
		return true;
	}

}
