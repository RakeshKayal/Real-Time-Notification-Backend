package com.chatapp.service;

import com.chatapp.dto.request.GroupChatRequestDTO;
import com.chatapp.exception.ChatException;
import com.chatapp.exception.UserException;
import com.chatapp.model.Chat;
import com.chatapp.model.User;

import java.util.List;
import java.util.UUID;

public interface ChatService {

    Chat createChat(User reqUser, UUID userId2) throws UserException;

    Chat findChatById(UUID id) throws ChatException;

    List<Chat> findAllByUserId(UUID userId) throws UserException;

    Chat createGroup(GroupChatRequestDTO req, User reqUser) throws UserException;

    Chat addUserToGroup(UUID userId, UUID chatId, User reqUser) throws UserException, ChatException;

    Chat renameGroup(UUID chatId, String groupName, User reqUser) throws UserException, ChatException;

    Chat removeFromGroup(UUID chatId, UUID userId, User reqUser) throws UserException, ChatException;

    void deleteChat(UUID chatId, UUID userId) throws UserException, ChatException;

    Chat markAsRead(UUID chatId, User reqUser) throws ChatException, UserException;

}
