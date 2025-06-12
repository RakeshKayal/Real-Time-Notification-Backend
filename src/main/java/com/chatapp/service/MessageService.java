package com.chatapp.service;

import com.chatapp.dto.request.SendMessageRequestDTO;
import com.chatapp.exception.ChatException;
import com.chatapp.exception.MessageException;
import com.chatapp.exception.UserException;
import com.chatapp.model.Message;
import com.chatapp.model.User;

import java.util.List;
import java.util.UUID;

public interface MessageService {

    Message sendMessage(SendMessageRequestDTO req, UUID userId) throws UserException, ChatException;

    List<Message> getChatMessages(UUID chatId, User reqUser) throws UserException, ChatException;

    Message findMessageById(UUID messageId) throws MessageException;

    void deleteMessageById(UUID messageId, User reqUser) throws UserException, MessageException;

}
