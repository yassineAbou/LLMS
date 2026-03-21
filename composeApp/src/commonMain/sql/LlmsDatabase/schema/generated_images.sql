CREATE TABLE generated_images (
                                  id               TEXT PRIMARY KEY,
                                  prompt           TEXT NOT NULL,
                                  image_data       BLOB NOT NULL,
                                  image_model_name TEXT NOT NULL,
                                  generated_at     TEXT DEFAULT CURRENT_TIMESTAMP
);