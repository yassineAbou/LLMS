CREATE TABLE chat_messages (
                               id        INTEGER PRIMARY KEY AUTOINCREMENT,
                               chat_id   TEXT    NOT NULL,
                               message   TEXT    NOT NULL,

    -- @@{ field=is_user, adapter=default, propertyType=Boolean }
                               is_user   INTEGER NOT NULL,

                               timestamp TEXT DEFAULT CURRENT_TIMESTAMP,

                               FOREIGN KEY(chat_id) REFERENCES chats(id) ON DELETE CASCADE
);

CREATE INDEX idx_chat_messages_chat_id ON chat_messages(chat_id);