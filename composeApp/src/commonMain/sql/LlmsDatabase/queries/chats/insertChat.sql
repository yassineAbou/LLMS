INSERT INTO chats(id, title, description, text_model_name, is_bookmarked, created_at)
VALUES (:id, :title, :description, :textModelName, :isBookmarked, :createdAt)
    ON CONFLICT(id) DO UPDATE SET
    title = excluded.title,
                           description = excluded.description,
                           text_model_name = excluded.text_model_name,
                           is_bookmarked = excluded.is_bookmarked,
                           created_at = excluded.created_at;