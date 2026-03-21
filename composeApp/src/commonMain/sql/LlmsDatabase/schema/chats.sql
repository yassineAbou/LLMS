-- Cascade-notify so flows watching chat_messages refresh on chat delete
-- @@{
--    cascadeNotify = {
--       delete = ["chat_messages"]
--    }
-- }
CREATE TABLE chats (
                       id              TEXT PRIMARY KEY,
                       title           TEXT    NOT NULL,
                       description     TEXT,
                       text_model_name TEXT    NOT NULL,

    -- @@{ field=is_bookmarked, adapter=default, propertyType=Boolean }
                       is_bookmarked   INTEGER NOT NULL DEFAULT 0,

                       created_at      TEXT    NOT NULL DEFAULT CURRENT_TIMESTAMP
);