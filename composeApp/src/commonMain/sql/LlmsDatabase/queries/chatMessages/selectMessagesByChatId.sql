SELECT id, chat_id, message, is_user, timestamp
FROM chat_messages
WHERE chat_id = :chatId
ORDER BY timestamp ASC;