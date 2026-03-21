SELECT id, prompt, image_data, image_model_name, generated_at
FROM generated_images
ORDER BY generated_at DESC;