CREATE TABLE user (
                      id              INTEGER PRIMARY KEY DEFAULT 1 CHECK (id = 1),
                      google_sub_id   TEXT NOT NULL,
                      email           TEXT NOT NULL,
                      username        TEXT NOT NULL,
                      profile_pic_url TEXT,
                      created_at      TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
);