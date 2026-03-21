INSERT INTO user(id, google_sub_id, email, username, profile_pic_url, created_at)
VALUES (1, :googleSubId, :email, :username, :profilePicUrl, :createdAt)
    ON CONFLICT(id) DO UPDATE SET
    google_sub_id = excluded.google_sub_id,
                           email = excluded.email,
                           username = excluded.username,
                           profile_pic_url = excluded.profile_pic_url,
                           created_at = excluded.created_at;