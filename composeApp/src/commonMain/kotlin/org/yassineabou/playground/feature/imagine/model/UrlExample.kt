package org.yassineabou.playground.feature.imagine.model

class IdGenerator {

    private var nextId = 0

    fun generatedId(): Int {
        nextId++
        return nextId
    }

}

data class UrlExample(
    val id: Int = IdGenerator().generatedId(),
    val url: String = "",
    val description: String = ""
)

val Flux_1_Compact_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/705f60c9-72b8-4927-836a-8dd7ba6ff7b7/original=true,quality=90/FLUX[time(Ymd)]_00002_.jpeg",
        description = "reflection, man, solo, male focus, black hair, tree"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/b6b3c47b-b8cd-421d-82be-bcaacec926e6/original=true,quality=90/FLUX[time(Ymd)]_00005_.jpeg",
        description = "building, house, scenery, no humans, nature, forest"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/a417f6de-5509-473c-9c2c-95eeecd955cd/original=true,quality=90/FLUX[time(Ymd)]_00008_.jpeg",
        description = "partial nudity, horns, male, man, solo, main focus"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/aa337518-06f5-4ab3-b2cc-87796b15c296/original=true,quality=90/FLUX[time(Ymd)]_00014_.jpeg",
        description = "outdoors, building, no humans, tree, road"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/eac0d612-2e7b-4e9b-b716-dc2704f14eb1/original=true,quality=90/FLUX[time(Ymd)]_00018_.jpeg",
        description = "tree, car, ground vehicle, motor vehicle, vehicle focus, night"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/a39ec587-55b9-4899-9188-62585fd692a9/original=true,quality=90/FLUX[time(Ymd)]_00022_.jpeg",
        description = "woman, solo, green theme, door, standing, barefoot"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/29902c11-96d7-430c-a59a-bb0a8f2a396e/original=true,quality=90/FLUX[time(Ymd)]_00024_.jpeg",
        description = "hat, holding, dual wielding, cartoon, handgun, cowboy hat"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/ea98b800-be0e-4c46-9840-ded24908a557/original=true,quality=90/FLUX[time(Ymd)]_00080_.jpeg",
        description = "cloud, cloud sky, sky, cloak, monochrome, solo"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/8fdd558c-ed7c-480d-98ec-6eb5bae9eac8/original=true,quality=90/FLUX[time(Ymd)]_00081_.jpeg",
        description = "sexy attire, woman, horns, red eyes, breasts, solo"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/8fdd558c-ed7c-480d-98ec-6eb5bae9eac8/original=true,quality=90/FLUX[time(Ymd)]_00081_.jpeg",
        description = "sexy attire, woman, horns, red eyes, breasts, solo"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/b3624fe7-55c3-4644-895a-a97fa9654e77/original=true,quality=90/FLUX[time(Ymd)]_00087_.jpeg",
        description = "man, armor, weapon, male focus, male, shadow"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/67090e46-554e-4e02-bece-20da2f187134/original=true,quality=90/FLUX[time(Ymd)]_00092_.jpeg",
        description = "cartoon, street, outdoors, road, no humans, solo"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/25a98fd5-028a-4a89-9051-9ecbb16be7f9/original=true,quality=90/FLUX[time(Ymd)]_00093_.jpeg",
        description = "forest, cartoon, backpack, bag, rock, no humans"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/1dc6e3f7-6453-4f10-96a0-d8c5c7f2c534/original=true,quality=90/FLUX[time(Ymd)]_00098_.jpeg",
        description = "sexy attire, woman, solo, flower, day, poke ball"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/63f34ca8-0105-4240-8244-96c3decf6f2f/original=true,quality=90/FLUX[time(Ymd)]_00101_.jpeg",
        description = "bird, animal, flower, no humans, nature, blurry"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/1b55696b-c8b9-49b9-9b8f-df2cb62e68fa/original=true,quality=90/FLUX[time(Ymd)]_00102_.jpeg",
        description = "umbrella, motor vehicle, day, ground vehicle, holding, shadow"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/844c0c3d-d957-4e5c-af7b-5889d8e00c38/original=true,quality=90/FLUX[time(Ymd)]_00107_.jpeg",
        description = "weapon related violence, hat, holding, handgun, cowboy hat, no humans"
    ),
)

val AlbedoBase_XL_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/a468112c-0a67-4e4f-8462-8f0ab1e56bad/width=450/00269-9.jpeg",
        description = "Envision a scene that transcends the boundaries of photography, where a woman's deep melancholy is as tangible as the space she occupies. She is adrift inside a space station, her presence a poignant contrast against the mechanical backdrop. Her gaze, lost in the vastness of space, speaks volumes of unspoken stories. Focus intently on her hands - pale, almost ethereal, they are captured in an emotive gesture, perhaps tracing the cold glass of the window or floating gracefully in zero gravity. These hands are not just part of her; they are storytellers in their own right. The image should be infused with the distinct qualities of a shot taken on a Canon 5D Mark IV with Kodak Ektar film - the colors are deep and resonant, the details are sharp yet possess an organic quality, and there's a subtle grain that adds a layer of depth and authenticity. This isn't just a photograph; it's a moment of raw emotion, frozen in time, inviting the viewer to not just see, but to feel."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/2b4ff8d6-ee5e-4922-aa10-dc189c741ffc/width=450/00270-9.jpeg",
        description = "Create an 8K anime illustration, fusing John Singer Sargent's artistry with anime style. Focus on a Gatsby-like man, capturing Sargent's emotional depth and anime's vibrancy. His attire should blend Roaring Twenties elegance with anime flair. The background, a lavish Gatsby-esque scene, should merge Sargent's detail-oriented realism with anime's dynamic exaggeration. Ensure every detail, from expression to setting, weaves a story that's both classically nostalgic and uniquely modern."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/363759c7-75cf-45ab-bd3e-3f4ce37c77a8/width=450/00271-9.jpeg",
        description = "A close up fantastic image of a woman's outline containing the entire galaxy inside of her, as she stands in a field covered in snow, a soft aura surrounding her"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/487bc1a7-f19a-43c6-8229-269ad85fb55a/width=450/00272-9.jpeg",
        description = "8k UHD professional romantic anime illustration, showcasing a boy and girl on a cafe date, extremely delicate detail. The scene in professional detail and perfect proportions, captures their tender affection. Focus on expressive eyes and fashionable attire, set in a cozy, modern-yet-whimsical cafe. Use soft, warm lighting to create a romantic, inviting atmosphere, telling a charming, heartwarming story. Blushing emotional faces. professional post cleaned high quality illustration."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/83990fff-6f6f-4453-84ca-82ed3e7d82b2/width=450/00273-9.jpeg",
        description = "A photo of an anthropomorphic kitten, charming and lifelike, holding a 'Review' sign. Focus on natural allure with adorable, expressive features, blending kitten innocence and human-like awareness. Detail the fur, eyes, and posture meticulously. The 'Review' sign should match its playful character. Use soft lighting and a simple background to highlight the kitten and sign. Aim to engage viewers emotionally, portraying a unique, endearing narrative."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/5d67cd43-051c-47d5-acc4-386bc660e82e/width=450/00288-9.jpeg",
        description = "Imagine an ultra-detailed oil painting that thrusts you into the heart of a surreal, dark fantasy dreamscape, masterfully rendered in the iconic style of Frank Frazetta. At the center of this apocalyptic hellscape stands a formidable, muscular figure, shrouded in a dark hood, with enormous black wings unfurled menacingly. His demonic plate armor, intricately designed and exuding malevolence, catches the faint, eerie light. His face, a dark void shrouded in shadow, is featureless except for two fiery red eyes that burn with an infernal intensity. He is engaged in a ferocious battle against a horde of demonic beasts that swarm in from all sides. Each beast, a grotesque amalgamation of nightmare and flesh, falls before the sweeping arcs of his massive, gleaming sword, wielded with a master's precision. In the background, jagged mountains rise ominously, silhouetted against a tempestuous, roiling sky. The ground is a macabre tapestry of decapitated demons, scattered skulls, and winding rivers of blood, completing this nightmarish vista with a visceral sense of dread and awe."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/9791dfd2-b9ef-45b2-8f40-8c79bc60b054/width=450/00291-9.jpeg",
        description = "Picture a high-quality, macro level close-up photograph capturing the enigmatic beauty of a crow's head in sharp detail. The crow's wise eyes are the focal point, deep and reflective, mirroring the entire world within their gaze. They hold an almost mystical quality, reflecting a miniature panorama of the surrounding landscape at sunset. The warm, golden hues of the fading sun bathe the scene, accentuating the intricate textures of the crow's feathers and adding a soft, ethereal glow to the image. The crow is looking directly into the camera, its gaze piercing and direct, creating an intense connection with the viewer. In its eyes, the faraway ocean and islands are glimpsed, subtly blurred in the background, enhancing the sense of depth and isolation. This image is framed with a careful balance of zoom and depth of field, ensuring the crowâ\u0080\u0099s face is in sharp focus while the background fades into a dreamy bokeh, emphasizing the profound and solitary figure of the crow against the vastness of the world."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/4e9d9453-c550-448f-bdab-eba5bcba5e4a/width=450/00295-9.jpeg",
        description = "Envision a full body shot of a girl, a breathtaking embodiment of nature's splendor. Her figure is a delicate yet powerful fusion of the earth's elements, each aspect rendered with mesmerizing intricacy. Her hair flows like a cascade of autumn leaves, shimmering with hues of gold and amber, while her skin mimics the rich, fertile earth, textured with subtle hints of green, as if moss and tiny sprouts are emerging from it. Her arms and legs are like elegant vines, adorned with a myriad of tiny flowers, dewdrops, and fresh buds, showcasing the vibrant vitality of spring. Veins of shimmering water course through her, resembling flowing streams that reflect the light in a dance of sparkling blues and whites. Her eyes, deep and expressive, hold the serene azure of the sky, with flecks of white clouds drifting within. Around her, a gentle breeze seems to carry whispers of leaves and petals, adding a dynamic sense of movement to the image. This portrayal captures not just the visual beauty of nature's elements but their essence, creating a harmonious and ethereal representation of a girl who is one with nature."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/31a9ded0-3eab-4409-9002-a265fe6c2609/width=450/00296-9.jpeg",
        description = "Imagine a photograph capturing an extraordinary and surreal subject: a transparent apple, crystal clear and perfectly formed, revealing a highly detailed, tumultuous miniature sea raging within. The apple sits boldly in the center of the frame, its smooth, glass-like surface reflecting light and offering a window into the dynamic scene inside. Within, the stormy sea is a marvel of miniaturization - tiny waves crest and crash with realistic ferocity, and if one looks closely, minute flashes of lightning and swirls of wind can be discerned, adding to the tempest's drama. The background of the photo is intentionally simple, perhaps a soft, neutral color or a subtle gradient, ensuring that all attention is drawn to the striking contrast between the serene exterior of the apple and the wild, chaotic seascape it contains. The lighting is key, illuminating the apple in a way that highlights the intricate details of the storm inside while maintaining the overall clarity and impact of the image."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/f81da9ea-6b86-4dac-bc24-e8f1747879fe/width=450/00297-9.jpeg",
        description = "Visualize a captivating long shot of an elegantly poised woman, extremely detailed face, fully immersed in observing a surreal Salvador Dalí masterpiece in a dimly lit art studio. The studio, a haven of artistic endeavor, is filled with an eclectic collection of paintings, each whispering tales of dreams and illusions. A skylight above bathes the room in a soft, natural glow, contrasting with the vibrant neon light that casts an ethereal ambiance across the space. The woman, dressed in attire that speaks of refined taste, stands in full view, her silhouette gracefully outlined by the interplay of the neon and natural light. This scene, captured with the technical prowess of a Canon 5D Mark IV, resonates with the rich, vivid colors and fine grain texture of Kodak Ektar film, rendering every detail in stunning clarity and depth."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/b0ec02e5-e1bf-41cf-a295-cfb223caac21/width=450/00300-9.jpeg",
        description = "Envision a heartwarming scene straight out of a Pixar animation, a 32k UHD photo that has extremely high details, In a cozy, softly-lit tower room, adorned with whimsical trinkets and glowing with the warm hues of sunset filtering through the window, a young woman gazes dreamily at a vibrant red rose. The rose, a gift from the king's brave and charming son, glimmers with a magical sheen, each petal rendered in Pixar's signature style - vivid, lifelike, yet imbued with a touch of fantasy. The woman, designed with Pixar's distinctive blend of realism and caricature, has expressive eyes filled with hope and affection. As she tenderly touches the rose, her face lights up with an animated smile, reflecting her longing and the sweet memory of their encounter at the ball. The scene is filled with rich, saturated colors and soft lighting, creating an ambiance of romantic anticipation, while the intricate details of the room and the rose showcase Pixar's mastery in crafting visually stunning and emotionally captivating narratives."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/e3259e97-9269-49cf-bc30-5fd86fed831a/width=450/00305-9.jpeg",
        description = "extremely detailed 16k UHD RAW photo of a seductive woman with a candle in a pitch-black dark background, taken by a professional photographer with DSLR"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/338b5bd0-57df-4697-9c95-febb29082d27/width=450/00308-9.jpeg",
        description = "macro-level extremely detailed 32k UHD RAW photo depicting a perfect salamander entirely made of crystal, showcasing a multifaceted translucent body with a prismatic effect as light passes through, taken by a national geography photographer with DSLR, beautiful sunlight"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/ca31d97b-574d-41e2-9f8b-c1a291895435/width=450/00315-9.jpeg",
        description = "Painting, dreamscape of an Enchanted Library within a giant tree, at Dawn, Whimsical, Solarpunk, Warm Illumination, fluid, Oil on canvas, Focal length 35mm, Lark filter, Fibonacci spiral"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/1bf65792-d27a-4e92-8644-e894ac24f297/width=450/00319-9.jpeg",
        description = "serene aesthetic, luxurious minimalist, spacious treehouse with panoramic windows overlooking a solarpunk forest village at sunrise, view from canopy level, earth-toned furnishings, living plant walls, gentle breeze outside with falling leaves, intricate, high resolution, hyper-realistic, bright, hopeful"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/0e927287-156d-4547-9204-3a7d8ca9f11d/width=450/00321-9.jpeg",
        description = "Deep sea creature, Phase One XF, 100MP, 120mm macro, ISO 100, 1/30s, strobe lighting, Capture One, DxO PhotoLab, Topaz Gigapixel AI"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/dbba157a-c428-44b3-9032-67a409ce01ad/width=450/00323-9.jpeg",
        description = "mountain peak, man, meditation, balancing, sunrise, stillness, form-fitting activewear, muscular physique, short hair, serenity, discipline, mindfulness, alpine setting, mist, silhouetted, golden light, minimalist, static pose, wellness lifestyle, majestic scene, rock, clear sky, diffused light, outdoor photography, inner peace, focus, strength, cool tones"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/18300168-3562-4f77-a822-ce092f56e224/width=450/00341-9.jpeg",
        description = "street performer, male, vibrant graffiti backdrop, breakdance freeze, urban dance, dynamism, outdoor, neon lights, baggy clothes, sneakers, athleticism, young adult, intense expression, open eyes, concrete jungle, nighttime, multiethnic, mid-motion, edgy, street wear, crowd gathering"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/79d736de-6f97-4e84-ac10-d015f2d86f09/width=450/00328-9.jpeg",
        description = "Close-up, man, dawn setting, indoor, tailored suit, red tie, sitting, sharp focus foreground, city skyline through window, office desk, medium shot, determined expression, salt-and-pepper hair, professional makeup, cufflinks, hand on document, cool color tone, detailed background, deep depth of field, business attire, middle-aged, morning light, man_hand_model"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/b4367807-c916-4c7c-9f0c-28018e9c3795/width=450/00365-9.jpeg",
        description = "lifelike webcam jpeg selfie of an incredibly beautiful girl, melancholy looks"
    )
)

val Blank_Canvas_XL_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/39f3e37e-5e2b-4d4d-864d-0d68fdf6851e/width=450/00232-1566567995.jpeg",
        description = "pixar disney cartoon of a (cute baby quokka in a tuxedo), symmetrical, highly detailed, 8k, digital painting, oil painting, illustration, concept art, sharp focus, volumetric lighting, epic Composition, cgsociety, artstation"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/b3b1a981-4f77-4b9e-b12e-77e66d5963e3/width=450/00174-3400376488.jpeg",
        description = "dslr photo of a girl in a fusion of medieval and cyberpunk elements, with a mix of technology and nature, (highres, highly detailed:1.2), cinematic lighting, vibrant colors"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/dd3a3943-7d0d-4141-93a9-536d90e552a5/width=450/00055-117951337.jpeg",
        description = "(best quality, masterpiece, colorful, dynamic angle, highest detailed) upper body photo, fashion photography of cute, freckled vixen, holding a black wolf, (ultrahigh resolution textures), in dynamic pose, bokeh, glowing web, (intricate details, hyperdetailed:1.15), detailed, moonlight passing through hair, perfect night, fantasy background, (official art, extreme detailed, highest detailed), HDR+"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/11afff00-c18b-411a-832c-1cc887714dc5/width=450/00165-2194255573.jpeg",
        description = "(\"RCNZ\" <lora:Harrlogos_v1.1:1> text logo), a handsome man in a suit in the style of pixar"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/29b8208c-b2bc-433a-9781-be3209e373de/width=450/00235-4272690840.jpeg",
        description = "(Makoto Shinkai, Greg Rutkowski), (anime:2) japanese ink painting of (a samurai with a chinese dragon in background:1.6), symmetrical, highly detailed, 8k, illustration, concept art, sharp focus, volumetric lighting, epic Composition, artstation"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/659ee544-7841-4938-a181-a188f862c17b/width=450/00079-595996412.jpeg",
        description = "Jane Eyre with headphones, natural skin texture, 24mm, 4k textures, soft cinematic light, adobe lightroom, photolab, hdr, intricate, elegant, highly detailed, sharp focus, ((((cinematic look)))), soothing tones, insane details, intricate details, hyperdetailed, low contrast, soft cinematic light, dim colors, exposure blend, hdr, faded"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/9fbe6fe3-0407-4f8f-8a15-aad989dcde45/width=450/00122-1080307757.jpeg",
        description = "(best quality, masterpiece, colorful, dynamic angle, highest detailed)upper body photo, fashion photography of cute succubus girl, gothic, large demon red wings (high resolution textures), long green hair, (abstract art), half demon, crimson cat iris, cat eyes, vampire very long fangs, (intricate details, hyperdetailed:1.15), detailed, moonlight passing through hair, (official art, extreme detailed, highest detailed), HDR+"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/b82ca020-fef2-4f9b-b972-895c420ad518/width=450/00225-1050469636.jpeg",
        description = "pixar (disney) cartoon ((at night, starry sky))of (a teddy bear wearing (mirrored sunglasses) holding a petrol can), walking away from an explosion, exploding building, symmetrical, chromatic fantasy, highly detailed, 8k, digital painting, oil painting, illustration, concept art, sharp focus, volumetric lighting, epic Composition, cgsociety, artstation"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/018db10b-4cd9-4cf2-af27-08c04f9069b5/width=450/00094-4240748221.jpeg",
        description = "European 26 year old woman with red/blue bob hair and elf ears holding a flaming sword, photographed on a Sony A7R V, 55mm F/1.4 cine lens, thorough, analog style, realistic, real life, extremely beautiful, highly detailed, ultra-detailed, incredibly detailed, (masterpiece, best quality, sharp focus, HDR, movie spotlight, death light, moonlit, volumetric fog, warm grin, jacket neon colours focus eyes and face, perfect detail, fullbody, street view"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/a7ec96b5-63c1-4ea4-bc40-9889661ea4a4/width=450/00218-3246841783.jpeg",
        description = "Masterpiece ((monsterpunk zombie)) pixar cartoon that looks like (samuel l jackson:0.8), symmetrical, highly detailed, 8k, digital painting, oil painting, illustration, concept art, sharp focus, volumetric lighting, epic Composition, cgsociety, artstation"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/0f04990a-618f-4fd8-b764-11fca6aa74ef/width=450/00106-162854708.jpeg",
        description = "(small young petite chibi beautiful exotic sexy creaturename form:1.3), (beautiful fuzzy fluffy creaturename fur or skin or scales:1.3)"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/6b96151a-cc9e-41d5-b707-f3dba3c6cd8e/width=450/00168-2833870407.jpeg",
        description = "(anime:1.5), Japanese ink painting, cute girl, wizard hat, kimono robe, thigh-highs, boots, happy, dynamic pose, \"HAPPY NEW YEAR!\", 2024, landscape, sunrise, winter, red tone color, bloom effect, ambient occlusion, Bokeh, depth of field"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/f56c5bf3-3ceb-4583-924a-5a4051bb0cd4/width=450/00199-1287613592.jpeg",
        description = "(anime:1.5) liquid environment, splashes, drips, colorful bubbles, beautiful girl, seductive, poster quality, sublime art, intricate and suggestive, (highres, highly detailed:1.2), cinematic lighting, vibrant colors"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/22bb91ae-2b94-4eac-9ea1-759de4c01016/width=450/00171-1239936333.jpeg",
        description = "(anime:1.5), detailed illustrations of a girl in a fusion of medieval and cyberpunk elements, with a mix of technology and nature, (highres, highly detailed:1.2), cinematic lighting, vibrant colors"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/bf0dc8b7-d2e1-44e2-b9e3-dd1f353a3ae2/width=450/00066-3663106096.jpeg",
        description = "Masterpiece pixar nickelodeon cartoon of ((a cute retriever puppy, fluffy)), puppy in a cage (behind bars), sadness, hope, digital painting, artstation, concept art, sharp focus, illustration, volumetric lighting, epic Composition, 8k, oil painting, cgsociety"
    )
)

val DreamShaper_XL_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/fb00718b-227c-462c-ad03-1371f63e3213/original=true,quality=90/31072150-554464390-In%20Casey%20Baugh's%20evocative%20style,%20art%20of%20a%20beautiful%20young%20girl%20cyborg%20with%20long%20brown%20hair,%20futuristic,%20scifi,%20intricate,%20elega.jpeg",
        description = "In Casey Baugh's evocative style, art of a beautiful young girl cyborg with long brown hair, futuristic, scifi, intricate, elegant, highly detailed, majestic, Baugh's brushwork infuses the painting with a unique combination of realism and abstraction, greg rutkowski, surreal gold filigree, broken glass, (masterpiece, sidelighting, finely detailed beautiful eyes: 1.2), hdr, realistic painting, natural skin, textured skin, closed mouth, crystal eyes, butterfly filigree, chest armor, eye makeup, robot joints, long hair moved by the wind, window facing to another world, Baugh's distinctive style captures the essence of the girl's enigmatic nature, inviting viewers to explore the depths of her soul, award winning art <lora:aesthetic_anime_v1s:1>"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/d2d6d082-85ae-4cd5-a31e-ae6075e7e612/original=true,quality=90/31072152-3346112079-cinematic%20film%20still,%20close%20up,%20photo%20of%20redheaded%20girl%20near%20grasses,%20fictional%20landscapes,%20(intense%20sunlight_1.4),%20realist%20deta.jpeg",
        description = "cinematic film still, close up, photo of redheaded girl near grasses, fictional landscapes, (intense sunlight:1.4), realist detail, brooding mood, ue5, detailed character expressions, light amber and red, amazing quality, wallpaper, analog film grain, jacket"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/21dacc34-1463-4147-a192-0688984392ff/original=true,quality=90/31072155-1136711377-cinematic%20film%20still,%20Storm%20Trooper,%20colored%20lights,%20amazing%20quality,%20wallpaper,%20analog%20film%20grain%20_lora_aesthetic_anime_v1s_0.5.jpeg",
        description = "cinematic film still, Storm Trooper, colored lights, amazing quality, wallpaper, analog film grain <lora:aesthetic_anime_v1s:0.5> <lora:add-detail-xl:1.1>"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/ff42a320-b3ab-47bb-aa82-614b8c02496d/original=true,quality=90/31072103-1136711378-anime%20style,%201girl,%20blunt%20bangs,%20high%20ponytail,%20silver%20hair,%20pointy%20ears,%20in%20the%20depths%20of%20a%20bioluminescent%20alien%20jungle,%20[evang.jpeg",
        description = "anime style, 1girl, blunt bangs, high ponytail, silver hair, pointy ears, in the depths of a bioluminescent alien jungle, [evangelion:cyberpunk edgerunners:0.5], reflective transparent iridescent opaque clothing, long sleeves, flowing dress, long skirt, very aesthetic, highres, 4k, 8k, intricate detail, cinematic lighting, amazing quality, amazing shading, detailed Illustration, official artwork, wallpaper, official art, extremely detailed eyes and face, beautiful detailed eyes, from below, full body, thigh gap, <lora:aesthetic_anime_v1s:1>"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/210afd5c-157d-482c-9808-fe190167a9c0/original=true,quality=90/31072611-2067885438-(masterpiece,%20best%20quality,%20ultra-detailed,%20best%20shadow),%20cinematic%20film%20still,%20photo%20of%20a%20man%20wearing%20a%20high%20tech%20scifi%20armor,.jpeg",
        description = "(masterpiece, best quality, ultra-detailed, best shadow), cinematic film still, photo of a man wearing a high tech scifi armor, mecha armor, male focus, armor, solo, facial hair, cape, beard, looking at viewer, blue eyes, blurry background, power armor, knee protection, standing, brown hair, science fiction <lora:aesthetic_anime_v1s:1.2>"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/d691e954-92af-4e8f-af91-92d6c64167db/original=true,quality=90/31072109-2946499990-cinematic%20film%20still,%20close%20up,%20a%20robot%20woman%20stands%20tall,%20half-human%20half%20machine,%20amongst%20an%20ancient%20Greek%20gallery%20of%20painting.jpeg",
        description = "cinematic film still, close up, a robot woman stands tall, half-human half machine, amongst an ancient Greek gallery of paintings and marble, religious symbolism, quantum wavetracing, high fashion editorial, glsl shaders, semiconductors and electronic computer hardware, amazing quality, wallpaper, analog film grain, perfect face skin <lora:aesthetic_anime_v1s:1.1>"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/75218d56-c319-4038-9224-baccc79769a5/original=true,quality=90/31072111-2084045070-anime%20girl,%20night,%20blue%20light%20behind%20her,%20%20((Galaxy,%20Lens%20flare)),%20short%20hair,%20flower%20field,%20night%20sky,%20cinematic%20shot.%20Wallpape.jpeg",
        description = "anime girl, night, blue light behind her, ((Galaxy, Lens flare)), short hair, flower field, night sky, cinematic shot. Wallpaper. (Blue color schema), detailed background, a city in the distance"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/2fe17de1-49d5-4285-911a-302538e6ad9c/original=true,quality=90/31072113-2084045052-80's%20anime%20screencap,%20girl%20wearing%20a%20cropped%20top%20and%20short%20shorts,%20artistic%20rendition%20with%20wide%20brush%20strokes,%20anime%20comic.jpeg",
        description = "80's anime screencap, girl wearing a cropped top and short shorts, artistic rendition with wide brush strokes, anime comic"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/b827a6f1-4e2f-4b15-8983-e58c8ca244df/original=true,quality=90/31072117-1592846095-cinematic%20film%20still,%20close%20up,%20photo%20of%20a%20cute%20Pok%C3%A9mon,%20in%20the%20style%20of%20hyper-realistic%20fantasy,,%20sony%20fe%2012-24mm%20f_2.8%20gm,%20clo.jpeg",
        description = "cinematic film still, close up, photo of a cute Pokémon, in the style of hyper-realistic fantasy,, sony fe 12-24mm f/2.8 gm, close up, 32k uhd, light navy and light amber, kushan empirem alluring, perfect skin, seductive, amazing quality, wallpaper, analog film grain <lora:aesthetic_anime_v1s:0.5> <lora:add-detail-xl:1.1>"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/08b71e78-50b0-4193-a3cb-d12fddf9cc85/original=true,quality=90/31072119-2067885437-masterpiece,%20best%20quality,_cinematic%20film%20still,%20realistic,%20portrait,%20solo,%20white%20mecha%20robot,%20cape,%20science%20fiction,%20torn%20cloth.jpeg",
        description = "masterpiece, best quality, cinematic film still, realistic, portrait, solo, white mecha robot, cape, science fiction, torn clothes, glowing, standing, robot joints, mecha, armor, cowboy shot, (floating cape), intense sunlight, silver dragonborn, outdoors, landscape, nature highres, 4k, 8k, intricate detail, cinematic lighting, amazing quality, wallpaper <lora:aesthetic_anime_v1s:1.1>"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/58775640-5c1c-4058-98ca-1638b90f27b1/original=true,quality=90/31072123-1850492237-Photo%20of%20a%20girl,cinematic%20film%20still,super%20saiyan,%20full%20plate%20armor,%20ony%20fe%2012-24mm%20f_2.8%20gm,%20close%20up,%2032k%20uhd,%20light%20navy%20and.jpeg",
        description = "Photo of a girl,cinematic film still,super saiyan, full plate armor, ony fe 12-24mm f/2.8 gm, close up, 32k uhd, light navy and light amber, amazing quality, wallpaper, analog film grain"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/97e3aeb8-26d8-4c1f-b3d4-70d2badd5061/original=true,quality=90/31072135-554464374-photo%20of%20a%20Cat%20poised%20gracefully%20atop%20an%20ancient%20oak%20tree,%20autumn%20leaves%20fluttering%20around,%20golden%20hour%20casting%20long%20shadows,%20ba.jpeg",
        description = "photo of a Cat poised gracefully atop an ancient oak tree, autumn leaves fluttering around, golden hour casting long shadows, backlit, sharp focus on feline, bokeh effect on background foliage, cinematic film still,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/36ffb43b-6c42-4a55-b628-6dbb33409bc0/original=true,quality=90/31072131-433200653537535-In%20Casey%20Baugh's%20evocative%20style,%20a%20Gothic%20girl%20emerges%20from%20the%20depths%20of%20darkness,%20her%20essence%20a%20captivating%20blend%20of%20mystery.jpeg",
        description = "In Casey Baugh's evocative style, a Gothic girl emerges from the depths of darkness, her essence a captivating blend of mystery and allure. With piercing eyes and flowing ebony hair, she exudes an enigmatic presence that draws viewers into her world. Baugh's brushwork infuses the painting with a unique combination of realism and abstraction, highlighting the girl's delicate features and contrasting them against a backdrop of deep, rich hues. The interplay of light and shadow adds depth and dimension to the artwork, creating a hauntingly beautiful portrayal of this Gothic muse. Baugh's distinctive style captures the essence of the girl's enigmatic nature, inviting viewers to explore the depths of her soul. Signature"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/d75f3826-7361-4363-8eb7-4c7619032b90/original=true,quality=90/31072133-939365161-mysterious%20silhouette%20forest%20woman,%20by%20Minjae%20Lee,%20Carne%20Griffiths,%20Emily%20Kell,%20Geoffroy%20Thoorens,%20Aaron%20Horkey,%20Jordan%20Grimmer,.jpeg",
        description = "mysterious silhouette forest woman, by Minjae Lee, Carne Griffiths, Emily Kell, Geoffroy Thoorens, Aaron Horkey, Jordan Grimmer, Greg Rutkowski, amazing depth, masterwork, surreal, geometric patterns, intricately detailed, bokeh, perfect balanced, deep fine borders, artistic photorealism , smooth, great masterwork by head of prompt engineering <lora:add-detail-xl:2>"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/6bbe19c4-dacf-4463-9621-e6f55cdc386b/original=true,quality=90/31072143-2067885445-a%20winged%20Valkyrie%20wielding%20a%20weapon,%20splatter%20fashion,%20in%20the%20style%20of%20light%20and%20shadow,%20ink%20illustrations,%20by%20Yoji%20Shinkawa%20%20_l.jpeg",
        description = "a winged Valkyrie wielding a weapon, splatter fashion, in the style of light and shadow, ink illustrations, by Yoji Shinkawa <lora:aesthetic_anime_v1s:1>"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/0e24e4db-1f61-47ec-8d4b-8121f2726b16/original=true,quality=90/31072153-1311043933-glowneon,%20glowing%20samurai%20emitting%20sparks%20and%20electricity,%20dark%20red%20and%20orange,%20glowing%20eyes,%20cinematic%20film%20still%20_lora_glowneo.jpeg",
        description = "glowneon, glowing samurai emitting sparks and electricity, dark red and orange, glowing eyes, cinematic film still <lora:glowneon_xl_v1:1> <lora:add-detail-xl:0.8>"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/1c440994-5167-4c1a-ae03-ec37458fd8e9/original=true,quality=90/31072139-4042674297-cinematic%20film%20still,%20close%20up,%20photo%20of%20a%20red%20Dragonborn,%20in%20the%20style%20of%20hyper-realistic%20d&d,%20full%20plate,%20%20sony%20fe%2012-24mm%20f_2.jpeg",
        description = "cinematic film still, close up, photo of a red Dragonborn, in the style of hyper-realistic d&d, full plate, sony fe 12-24mm f/2.8 gm, close up, 32k uhd, light navy and light amber, kushan empire, amazing quality, wallpaper, analog film grain <lora:aesthetic_anime_v1s:0.5> <lora:add-detail-xl:1.1>"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/46e0aa38-5591-4707-8f41-292b15837c1d/original=true,quality=90/31072145-2022514653-by%20Tsutomu%20Nihei,(strange%20but%20extremely%20beautiful_1.4),(masterpiece,%20best%20quality_1.4),in%20the%20style%20of%20nicola%20samori,The%20Joker,.jpeg",
        description = "by Tsutomu Nihei,(strange but extremely beautiful:1.4),(masterpiece, best quality:1.4),in the style of nicola samori,The Joker,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/9b6613ec-b09b-4de2-8679-cee0fd0ad0b7/original=true,quality=90/31072147-2039823409429-photo%20of%20the%20warrior%20Aragorn%20from%20Lord%20of%20the%20Rings,%20film%20grain,%20cinematic%20film%20still,%208k%20hd,%20portrait.jpeg",
        description = "photo of the warrior Aragorn from Lord of the Rings, film grain, cinematic film still, 8k hd, portrait"
    )
)

val Pony_Realism_Url_Example = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/f31fde4b-6e95-435d-9b30-36086e07aa68/original=true,quality=90/02176.jpeg",
        description = "score_9, score_8_up, score_7_up, asian girl, makeup, goth, emo, portrait, close-up, messy bedroom, supported on bedside table, clutter tattoos, solo, sexy curves, revealing outfit, a latex minidress, depth of field, highly detailed, high contrast, film grain, Rim Lighting"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/46a15e01-41be-4dbf-b63e-bacb189cac2b/original=true,quality=90/02185.jpeg",
        description = "score_9, score_8_up, score_7_up, face close up, alternative girl, watching over black sunglasses, jacket, necklace, neon light reflections on skin, ear ring, makeup, skin imperfection, short hair, beanie, neon lights background, low light, depth of field, highly detailed, high contrast, film grain, Rim Lighting, Long Exposure, DSLR"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/5d19fa68-0a24-43a4-9f47-302c299be442/original=true,quality=90/02163.jpeg",
        description = "score_9, score_8_up, score_7_up, female, furry, cow, cleavage, hybrid, skimpy, chubby, fat, cleaning, bartender, behind counter, tavern, smirk, medieval fantasy, depth of field, highly detailed, high contrast, film grain, Rim Lighting, Rays of Shimmering Light"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/0f245ae4-d580-4bc0-b5f0-74023070a321/original=true,quality=90/02187.jpeg",
        description = "score_9, score_8_up, score_7_up, solo, furry, cat, hybrid, curvy, laying in a sofa, tree house, sunny, medieval fantasy, wearing dress, low light, depth of field, highly detailed, high contrast, film grain, Rim Lighting, Long Exposure, DSLR"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/83d406c0-d188-4c69-96fc-284b972548a2/original=true,quality=90/02172.jpeg",
        description = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/83d406c0-d188-4c69-96fc-284b972548a2/original=true,quality=90/02172.jpeg"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/48a6bd63-9a6e-45a4-82bc-8f353e49021d/original=true,quality=90/02189.jpeg",
        description = "score_9, score_8_up, score_7_up, scene, huge castle, moss, mist, fog, mistery, dark fantasy, low light, depth of field, highly detailed, high contrast, film grain, Rim Lighting, Long Exposure, DSLR"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/e95eed9d-f5e8-4cf2-86c4-cef47ced9520/original=true,quality=90/02173.jpeg",
        description = "score_9, score_8_up, score_7_up, solo, furry, creature, cute, sleeping, next to a fireplace, interior, winter, christmas, depth of field, highly detailed, high contrast, film grain, Rim Lighting"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/fb22ba35-e980-4a14-b52b-2b25880cc4ce/original=true,quality=90/02190.jpeg",
        description = "score_9, score_8_up, score_7_up, scene, emerald on a table, tavern, close-up, energy, epic, moss, crystal, purple and blue theme, dark fantasy, low light, depth of field, highly detailed, high contrast, film grain, Rim Lighting, Long Exposure, DSLR, Product-View"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/75934faf-bfee-488f-ae82-15aa26d3f5be/original=true,quality=90/02164.jpeg",
        description = "score_9, score_8_up, score_7_up, furry, close-up, minotaur, angry, yelling, teeth, chained, dungeon, dark, low light, candles, depth of field, highly detailed, high contrast, film grain, Rim Lighting, Rays of Shimmering Light"
    ),
    UrlExample(
        url = "",
        description = ""
    )
)

val Ultraspice_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/dd741fe9-d930-4681-81f8-9eb3368ea45e/original=true,quality=90/00325-3820806867.jpeg",
        description = "Little hedgehog in the fallen leaves in autumn, close-up, 8k resolution, HD photography, real photo, soft light, ultra-high definition, best quality, extreme details"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/89997586-7ec3-49d2-aac2-48cfc5f9f4a7/original=true,quality=90/00401-3594270652.jpeg",
        description = "macro, fill the frame, from the side, photorealism, extremely detailed, best quality, A transparent neon glass crystal lily, glowing internally with a multicolored florescent galaxy of stars, swirls of light shine within, on a mossy log, dark background"
    )
)

val ICBINP_XL_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/727f5218-e33b-4da2-8bcf-7129ae4a9e5b/original=true,quality=90/00132-1077380802.jpeg",
        description = "A striking photograph portraying Mork (Robin Williams) transformed into an orc warrior, blending whimsy with fantasy in the style of the imaginative compositions by Boris Vallejo. Monstrous visage, menacing tusks, fierce expression, tribal markings, rugged armor, battle scars, towering stature, primal energy, wilderness backdrop, dramatic lighting, intense gaze, savage demeanor, fantastical realism, dynamic composition, mythical creature, imaginative transformation, epic adventure, immersive storytelling, otherworldly allure."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/838b001c-6112-4e56-b1a5-74a114da8927/original=true,quality=90/00053-2042476072.jpeg",
        description = "aw photo, (18yo redhead girl:1.2), makeup, graphic eyeliner, rouge, (choker:0.9), realistic skin texture, oversize knit sweater, (red:0.8), softcore, warm lighting, cosy atmosphere, instagram style, high quality dslr snapshot, high definition film grain photo taken at f/16, ISO 100, with a 200mm lens, global illumination, fibonacci"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/a9d1dad8-6073-47fb-b8b6-e15bfb70c862/original=true,quality=90/00047-126742706.jpeg",
        description = "A colorful portrait of an elderly man with a weathered face, inspired by Steve McCurryâ\u0080\u0099s photography. Warm tones, detailed wrinkles, piercing blue eyes, traditional clothing, soft background blur, natural light, textured skin, gentle smile, rich cultural elements, vivid colors, deep shadows, strong presence, expressive face, vibrant details, storytelling."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/d002cdb9-0078-4a43-856e-8720e254bdcd/original=true,quality=90/00005-885206500.jpeg",
        description = "A Victorian-era servant woman poses for a head and shoulder portrait in a photograph style. Sepia tones, 19th-century aesthetics, stern expression, plain bonnet, lace collar, simple dress, dark background, soft diffused lighting, slight vignette, subtle shadows, worn textures, detailed fabric, Julia Margaret Cameron inspiration, natural skin texture, melancholic mood, timeless atmosphere."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/7753e29a-caa5-4009-94e8-5df8863a4262/original=true,quality=90/00068-3163738484.jpeg",
        description = "Jane Austen-inspired Regency era fashion, wearing an elegant empire waist gown with intricate embroidery, lace trim, and puffed sleeves, her hair styled in delicate ringlets adorned with a ribbon, standing in a grand Georgian drawing room filled with period furniture and a warm fireplace, holding a leather-bound book, with a serene and graceful expression, cinematic lighting, 8k, highly detailed, award-winning, trending on artstation, Zeiss lens"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/1e2021a6-af6c-480e-b8e0-e367241b7506/original=true,quality=90/00104-1616131801.jpeg",
        description = "A serene portrait photograph of a chipmunk (monk in orange robe:1.2), embodying tranquility and wisdom amidst nature, reminiscent of the peaceful compositions by photographer Konsta Punkka. Serene gaze, folded paws, humble robes, tranquil demeanor, serene expression, peaceful woodland setting, dappled sunlight, natural textures, gentle demeanor, contemplative atmosphere, spiritual presence, harmonious balance, minimalist composition, quiet introspection, connection with nature, timeless simplicity."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/269d1b8b-ce5e-44dc-bf8c-4b5631499c81/original=true,quality=90/00107-2373714733.jpeg",
        description = "high quality dslr snapshot, high definition film grain photo taken at f/16, ISO 100, global illumination, cinematic photo of a woman sitting at a cafe. 35mm photograph, film, bokeh, professional, 4k, highly detailed"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/cf0aa3d9-9ba4-41f2-8f42-85bc17cf85b9/original=true,quality=90/00090-2464470326.jpeg",
        description = "A mesmerizing landscape photograph depicting the aurora dancing over an alpine lake, inspired by the ethereal beauty of the northern lights and the pristine landscapes captured by landscape photographer Max Rive. Brilliant aurora colors, reflected in the calm lake waters, towering alpine peaks, snow-capped mountains, starry sky, silhouetted trees, mirrored symmetry, otherworldly atmosphere, surreal glow, mystical allure, remote wilderness, serene tranquility, dramatic contrast, awe-inspiring spectacle, celestial magic"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/b04c719f-2666-4ee1-a101-b96e57081f82/original=true,quality=90/00057-2351940094.jpeg",
        description = "A dreamy, soft-focus photograph capturing a romantic Jane Austen movie scene, in the style of Agnes Cecile. Delicate watercolors, misty background, Regency-era couple, tender embrace, period clothing, flowing dress, dappled sunlight, ethereal glow, gentle expressions, intricate lace, muted pastels, serene countryside, timeless romance, poetic atmosphere, wistful mood"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/120434c8-19e4-43a0-8f85-4c224d7f46d0/original=true,quality=90/00124-1039574624.jpeg",
        description = "A cinematic photograph capturing the iconic silhouette of Batman perched atop a Gotham City rooftop, inspired by the dark and atmospheric style of Christopher Nolan's Batman films. Moody lighting, brooding shadows, billowing cape, stoic pose, vigilant gaze, urban skyline, gritty textures, monochromatic palette, dramatic angles, intense atmosphere, mysterious ambiance, symbolic presence, masked identity, urban legend, timeless heroism, dynamic composition, gritty realism."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/78548b79-adaa-4c96-9a31-e6526a77696c/original=true,quality=90/00042-2613703586.jpeg",
        description = "A dramatic black-and-white portrait of a woman gazing intently into the camera, inspired by Richard Avedon's style. High contrast, sharp focus, deep shadows, soft lighting, textured skin, detailed eyes, minimalist background, strong jawline, slight smile, natural hair, subtle makeup, classic elegance, intense emotion, timeless beauty"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/ac2635c8-1b4e-4658-909d-44b10c44736a/original=true,quality=90/00120-3984436578.jpeg",
        description = "A captivating photograph featuring an astronaut mouse aboard a futuristic spaceship, evoking the wonder of space exploration and the charm of whimsical fantasy, akin to the imaginative works of artist Sam Falconer. Tiny spacesuit, cosmic backdrop, futuristic spacecraft, gleaming control panels, illuminated buttons, vast expanse of stars, weightless atmosphere, determined posture, curious gaze, playful details, sci-fi adventure, fantastical elements, surreal juxtaposition, boundless curiosity, universal exploration, mesmerizing composition."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/b30899f5-4da1-4506-b770-c7bf1faece99/original=true,quality=90/00126-742022292.jpeg",
        description = "extreme Close-up portrait of a mysterious girl in red clothing, alluring, sexy pose, amazing shallow depth, (dramatic lights:1.4), hyperrealism, geometric patterns, intricately detailed, bokeh, perfect balanced, deep fine borders, artistic photorealism , smooth, great masterwork, 35mm film, masterpiece, high quality, detailed skin with visible pores, insane details, 8k, high definition, dslr, photo in phst artstyle"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/7dd2d1c1-af76-443a-8f63-8584da4b560b/original=true,quality=90/00065-940079325.jpeg",
        description = "A magical photograph of a snowy forest scene inspired by C.S. Lewis, reminiscent of \"The Chronicles of Narnia.\" Soft snow, tall pine trees, lamppost glowing, gentle snowfall, magical light, winter twilight, footprints in snow, warm glow, frosted branches, ethereal atmosphere, mysterious path, enchanting silence, nostalgic feel, subtle blue tones, timeless wonder."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/6866660f-0e22-4681-bee2-7f26fa075c4f/original=true,quality=90/00121-4102675003.jpeg",
        description = "cinematic still A portrait of a futuristic spaceship commander girl in a sci-fi setting. She stands in front of a huge window on a main command post with a mezmorizing view of deep space. She wears skin-tight and sexy uniform made of shiny and glossy latex. Her friendly and seductive smile inspires confidence and establishes her as an undisputed leader. Her athletic and slender body shines with light reflections that highlight her (((huge breast))), ((muscular arms)) and 6-pack abs, inspired, cinematic color spread, sharp focus, stunning, highly detailed, creative, dynamic, agile, positive emotional, cute, pretty, attractive, confident, passionate, beautiful, cheerful, dramatic, fancy, charming, best, modern, sleek, magnificent, intricate . emotional, harmonious, vignette, 4k epic detailed, shot on kodak, 35mm photo, sharp focus, high budget, cinemascope, moody, epic, gorgeous, film grain, grainy, (masterpiece), (best quality), (ultra-detailed)"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/2f14baf3-8216-4675-b96c-76718ab4840b/original=true,quality=90/00062-3895999379.jpeg",
        description = "A whimsical photograph of a spellcaster mouse in a miniature enchanted forest, inspired by the works of Tim Walker. Magical realism, tiny robe, wizard hat, glowing wand, mystical runes, bioluminescent fungi, twinkling fairy lights, misty atmosphere, textured fur, curious eyes, intricate details, rich colors, soft focus, enchanted plants, dreamy lighting, fantastical scene."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/3efea765-e5f0-49ce-9b57-489c27a5bbe0/original=true,quality=90/00077-1318163353.jpeg",
        description = "cinematic, aesthetic, a 30yo redhead woman, black armour, grinning, cyberpunk, 4k, HDR, dusk, lens flare"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/acebb402-7a53-4103-8bff-aad8ad58a3de/original=true,quality=90/00041-3748166032.jpeg",
        description = "A charming otter poses in an elaborate Victorian outfit, exuding regality and whimsy. Otter with whiskers, ornate Victorian suit, velvet textures, lace cravat, top hat, detailed waistcoat, ruffled sleeves, regal posture, soft focus background, warm lighting, muted colors, intricate embroidery, whimsical expression, studio portrait style, reminiscent of Annie Leibovitz's fantastical portraits, period costume details, sophisticated yet playful, rich textures, timeless elegance."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/67bab11d-0f9a-433f-a886-14ceb706ba29/original=true,quality=90/00063-2674634879.jpeg",
        description = "close up Portrait photo of muscular bearded guy in a worn mech suit, intricate, (steel metal [rust]), elegant, sharp focus, photo by greg rutkowski, soft lighting, vibrant colors, masterpiece, ((streets)), detailed face\n"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/eb658394-b439-4b05-ba72-c1554c2871e2/original=true,quality=90/00087-855475119.jpeg",
        description = "high quality close up dslr snapshot of a happy man walking through a park, high definition film grain photo taken at f/16, ISO 100, with a 200mm lens, global illumination, fibonacci"
    )
)

val Swam_Pony_XL_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/c2b9f7f4-6d43-4acf-851d-abe9897782eb/original=true,quality=90/00418-sdimg.jpeg",
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/8aac16e0-1e00-4013-aa19-011b2794021b/original=true,quality=90/00419-sdimg.jpeg",
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/34b8a73d-a993-4153-a163-e2c85f8e73ca/original=true,quality=90/00421-sdimg.jpeg",
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/3d0fedf9-4d00-4ec3-9fa6-803088b53c72/original=true,quality=90/tmpzn8hkaha.jpeg",
    )
)

val Juggernaut_XL_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/2582e6de-2637-4a40-80f9-41f64b6b54ea/original=true,quality=90/00002-beautiful%20lady,%20(freckles),%20big%20smile,%20ruby%20eyes,%20long%20curly%20hair,%20dark%20makeup,%20hyperdetailed%20photography,%20soft%20light,%20head%20and%20(1).jpeg",
        description = "beautiful lady, (freckles), big smile, ruby eyes, long curly hair, dark makeup, hyperdetailed photography, soft light, head and shoulders portrait, cover"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/26efc0ec-9902-4a0b-852f-357836d30d94/original=true,quality=90/00021-Leica%20portrait%20of%20a%20gremlin%20skateboarding,%20coded%20patterns,%20sparse%20and%20simple,%20uhd%20image,%20urbancore,%20sovietwave,%20period%20snapshot.jpeg",
        description = "Leica portrait of a gremlin skateboarding, coded patterns, sparse and simple, uhd image, urbancore, sovietwave, period snapshot"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/7292cc1b-b504-4a42-94a4-9d6f498da995/original=true,quality=90/00014-Leica%20Hasselblad%20portrait,%20hyperdetailed%20Photography,%20a%20Native%20American%20man%20walks%20proudly%20confidently%20in%20traditional%20clothing%20wi.jpeg",
        description = "Leica Hasselblad portrait, hyperdetailed Photography, a Native American man walks proudly confidently in traditional clothing with an Indian feather hat on his head a wolf accompanies him in the streets of New York in the middle of the buildings in the middle of the road. Large view"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/83b436cf-737e-480d-9a11-4ed36f1f44aa/original=true,quality=90/00038-A%20hyperdetailed%20photograph%20of%20a%20Cat%20dressed%20as%20a%20mafia%20boss%20holding%20a%20fish%20walking%20down%20a%20Japanese%20fish%20market%20with%20an%20angry%20fac.jpeg",
        description = "A hyperdetailed photograph of a Cat dressed as a mafia boss holding a fish walking down a Japanese fish market with an angry face, 8k resolution, best quality, beautiful photograph, dynamic lighting,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/88a93e3b-8119-42ee-ac29-aff05685f77d/original=true,quality=90/00043-A%20beautiful%20portrait%20photograph%20of%20a%20dragon%20with%20diamond%20and%20gemstone%20scales,%20opal%20eyes,%20cinematic,%20gem,%20diamond,%20crystal,%20fanta.jpeg",
        description = "A beautiful portrait photograph of a dragon with diamond and gemstone scales, opal eyes, cinematic, gem, diamond, crystal, fantasy art, hyperdetailed photograph, shiny scales, 8k resolution,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/3cf8eabc-5674-4216-9d4b-2c92a67391af/original=true,quality=90/00056-Michael%20Jackson%20singing%20at%20a%20standing%20microphone%20on%20a%20super-wide%20stage%20in%20a%20really%20big%20arena,%20by%20conrad%20roset,%20greg%20rutkowski,%20m.jpeg",
        description = "Michael Jackson singing at a standing microphone on a super-wide stage in a really big arena, by conrad roset, greg rutkowski, makoto shinkai, low poly, abstract, style of black and white drawing with red pops of color"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/1e5e5850-2baa-4a4d-b72d-e6f04f4cac06/original=true,quality=90/00071-An%20evil%20demon%20crawling%20out%20of%20a%20mirror%20with%20a%20horrifying%20reflection%20on%20a%20polished%20wood%20floor,%20mirror%20has%20a%20((silver%20frame)),%208k.jpeg",
        description = "An evil demon crawling out of a mirror with a horrifying reflection on a polished wood floor, mirror has a ((silver frame)), 8k resolution, best quality, hyperdetailed photograph, lowlight"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/8866f1df-3069-424d-b68d-4ce712836721/original=true,quality=90/00095-moody%20aesthetic,%20beautiful%20cozy,%20cramped%20bedroom%20with%20floor%20to%20ceiling%20glass%20windows%20overlooking%20a%20cyberpunk%20city%20at%20night,%20view.jpeg",
        description = "moody aesthetic, beautiful cozy, cramped bedroom with floor to ceiling glass windows overlooking a cyberpunk city at night, view from top of skyscraper, white bedsheets, bookshelves, thunderstorm outside with torrential rain, detailed, high resolution, photorrealistic, dark, gloomy,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/877b038e-a7dd-4d94-b4e3-4a684e3d2866/original=true,quality=90/00099-(((Perfect%20Face)))((cowboy%20shot)),%20(((masterpiece))),%20(((best%20quality))),%20((ultra-detailed)),%20(highly%20detailed%20CG%20illustration),.jpeg",
        description = "(((Perfect Face)))((cowboy shot)), (((masterpiece))), (((best quality))), ((ultra-detailed)), (highly detailed CG illustration), ((an extremely musculine and handsome)), cinematic light,(((1mechanical Ape))),solo,((upper torso masculine flesh hanging by wires)),((Hanging by wires and tubes)), (machine made joints:1.2),((mechanical limbs)),(blood vessels connected to tubes),(mechanical vertebra attaching to back),((mechanical cervial attaching to neck)), ((realistic hair)), (standing), (wires and cables attaching to neck:1.2),(wires and cables on head:1.2),(character focus),science fiction, extreme detailed, colorful, highest detailed, trousers, Greek Male, Sexy Muscular,Game of Thrones,male,Movie Still,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/630c74a4-48de-4b13-b165-0a4004f0ffdb/original=true,quality=90/00109-a%20beautiful%20cottage,%20scotland%20coastal%20village,%20(sharp%20focus_1.2),%20extremely%20detailed,%20(photorealistic_1.4),%20(RAW%20image,%208k%20high.jpeg",
        description = "a beautiful cottage, scotland coastal village, (sharp focus:1.2), extremely detailed, (photorealistic:1.4), (RAW image, 8k high resolution:1.2), RAW candid cinema, 16mm, color graded Portra 400 film, ultra realistic, cinematic film still, subsurface scattering, ray tracing, (volumetric lighting"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/d40bbf6f-a0fd-47d8-8595-232eebd363cf/original=true,quality=90/00114-photograph,%20landscape%20of%20a%20Mythical%20Grotto%20from%20inside%20of%20a%20Harare,%20at%20Twilight,%20Depressing,%20Cloudpunk,%20Cold%20Lighting,%20dynamic,.jpeg",
        description = "photograph, landscape of a Mythical Grotto from inside of a Harare, at Twilight, Depressing, Cloudpunk, Cold Lighting, dynamic, Nikon d850, Depth of field 270mm, Amaro, Golden ratio"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/3b9bd256-e666-4bd6-9296-a377d5377ca1/original=true,quality=90/00125-A%20woman%20wearing%20a%20hat%20poses%20for%20a%20picture,%20in%20the%20style%20of%20oshare%20kei,%20black,%20wide%20lens,%20shiny_%20glossy,%20solapunk,%20dark%20silver,%20r.jpeg",
        description = "A woman wearing a hat poses for a picture, in the style of oshare kei, black, wide lens, shiny/ glossy, solapunk, dark silver, rim light"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/8ef7cd5d-0ee2-4299-a840-19f68664057f/original=true,quality=90/00128-Portrait%20of%20a%20kind%20of%20a%20young%20man,%20twenty%20years%20with%20brown%20hair,%20childish,%20droopy%20eyes,%20unconventional%20beauty,%20moody%20atmosphere.jpeg",
        description = "Portrait of a kind of a young man, twenty years with brown hair, childish, droopy eyes, unconventional beauty, moody atmosphere by Saul Leiter"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/1a30ff49-1881-4313-a4c4-69d1ba15dd0c/original=true,quality=90/00137-Medium%20shot,%20Adorable%20creature%20with%20big%20reflective%20eyes,%20moody%20lighting,%20best%20quality,%20full%20body%20portrait,%20real%20picture,%20intrica.jpeg",
        description = "Medium shot, Adorable creature with big reflective eyes, moody lighting, best quality, full body portrait, real picture, intricate details, depth of field, in a forest, fujifilm xt3, outdoors, bright day, beautiful lighting, raw photo, 8k uhd, film grain, unreal engine 5, ray tracing"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/8e9f617d-ac15-41d8-8982-cfc2b58b91f8/original=true,quality=90/00160-Cyberpunk,%20sci-fi,%20dark-fantasy,%20kodak%20portrait%20400,%208k,%20soft%20light,%20volumetric%20lighting,%20highly%20detailed,%20britt%20marling%20style%203.jpeg",
        description = "Cyberpunk, sci-fi, dark-fantasy, kodak portrait 400, 8k, soft light, volumetric lighting, highly detailed, britt marling style 3/4, portrait photo of a war man cyborg robot in a chemical laboratory + face, face is skullbone with scares, intricate, elegant, highly detailed, devil-armor, 2D motifs detailed dark fantasy digital painting, artstation, concept art, smooth, sharp focus, illustration, art by Otomo Katsuhiro and ShirÅ\u008D Masamune and Oshii Mamoru. Cosmic light in backfront"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/8a7e90f0-0a7a-47b1-8fa2-b848f7ba101e/original=true,quality=90/00164-photograph,%20a%20path%20in%20the%20woods%20with%20leaves%20and%20the%20sun%20shining%20,%20by%20Julian%20Allen,%20dramatic%20autumn%20landscape,%20ears,%20park,%20take%20o.jpeg",
        description = "photograph, a path in the woods with leaves and the sun shining , by Julian Allen, dramatic autumn landscape, ears, park, take off, peace, rich cold moody colours, hi resolution, oaks"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/37b90ff5-fc98-4976-bcf3-54d20ce7cd37/original=true,quality=90/00165-Coqui%20frog%20in%20batman%20uniform,%208k.jpeg",
        description = "Coqui frog in batman uniform, 8k"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/1548a476-ef93-4079-adbc-dc2c8140df20/original=true,quality=90/00176-a%20torso%20with%20a%20TV%20instead%20of%20a%20head.jpeg",
        description = "a torso with a TV instead of a head"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/cebec3ef-67c5-4aac-a10c-e16ca9293db3/original=true,quality=90/00195-dark%20aesthetic%20photo,%20an%20Iranian%20girl%20amidst%20a%20chaotic%20street%20protest%20in%20Tehran,%20The%20scene%20is%20filled%20with%20tension%20and%20unrest,%20as.jpeg",
        description = "dark aesthetic photo, an Iranian girl amidst a chaotic street protest in Tehran, The scene is filled with tension and unrest, as flames from fire flares illuminate the night sky in the background. Standing boldly on the roof of a car."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/9a4e4ad2-d731-4dba-a719-3d4035c67798/original=true,quality=90/00197-A%20sad%20man%20standing%20in%20front%20of%20a%20burnt%20down%20home%20in%20a%20forest,%20stunning%20details,%208k.jpeg",
        description = "A sad man standing in front of a burnt down home in a forest, stunning details, 8k"
    )
)

val AAM_XL_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/50447032-1af6-4492-89c0-9c19cd69da43/original=true,quality=90/31073672-2067885435-masterpiece,%20best%20quality,%20hatsune%20miku,%20white%20gown,%20angel,%20angel%20wings,%20golden%20halo,%20dark%20background,%20upper%20body,%20closed%20mouth,.jpeg",
        description = "masterpiece, best quality, hatsune miku, white gown, angel, angel wings, golden halo, dark background, upper body, closed mouth, looking at viewer, arms behind back, blue theme, night, highres, 4k, 8k, intricate detail, cinematic lighting, amazing quality, amazing shading, soft lighting, Detailed Illustration, anime style, wallpaper"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/4c19f1d9-454c-4298-98d1-bc82273aa9e8/original=true,quality=90/01211-2540058664-1girl,%20mecha%20suit,%20samurai%20face%20mask,%20menpo,%20upper%20body,%20underboob,%20portrait,%20white%20orange%20armor,%20blonde%20shimmering%20hair,%208K,%20RA.jpeg",
        description = "1girl, mecha suit, samurai face mask, menpo, upper body, underboob, portrait, white orange armor, blonde shimmering hair, 8K, RAW, best quality, masterpiece, ultra high res, colorful, (medium wide shot), (dynamic perspective), sharp focus , (depth of field, bokeh:1.3), extremely detailed eyes and face, beautiful detailed eyes,large breasts,black gold, trimmed gear,In a futuristic weapons factory, ((masterpiece, best quality)), niji, from side, upper body, hips"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/d246a617-76ba-4892-8845-96b5ea2f2453/original=true,quality=90/31073591-684726996372595-mika%20pikazo,%20(flat%20color_1.1),(colorful_1.3),(masterpiece_1.2),%20best%20quality,%20masterpiece,%20original,%20extremely%20detailed%20wallpape.jpeg",
        description = "mika pikazo, (flat color:1.1),(colorful:1.3),(masterpiece:1.2), best quality, masterpiece, original, extremely detailed wallpaper, looking at viewer,1girl,solo,floating colorful water"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/4e109e74-0538-4208-8379-d4ea05fe76de/original=true,quality=90/31073698-140963649-(red%20and%20black%20mecha),%20gundam,%20masterpiece,best%20quality,ultra-detailed,%20anime%20screencap,extremely%20detailed,intricate%20details,hig.jpeg",
        description = "(red and black mecha), gundam, masterpiece,best quality,ultra-detailed, anime screencap,extremely detailed,intricate details,highres,cowboy shot, samurai armor"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/e8cfc163-68b6-4653-a54c-08ca95b6c1d6/original=true,quality=90/31073613-684726996372601-In%20the%20dystopian%20cityscape%20of%20cyberpunk,%20a%20mysterious%20figure%20shrouded%20in%20shadows%20stands,%20exuding%20an%20air%20of%20annoyance.%20The%20dusky.jpeg",
        description = "In the dystopian cityscape of cyberpunk, a mysterious figure shrouded in shadows stands, exuding an air of annoyance. The dusky fade adds an enigmatic touch to the scene as the character gazes out of the window with intense focus. The play of shadows and light creates a cinematic atmosphere, leaving viewers intrigued by the untold story behind this captivating moment. The artist's attention to detail elevates the emotional depth, making this artwork a masterpiece in the realm of cyberpunk aesthetics."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/1183ce5b-4d83-4886-b28b-1139603096dc/original=true,quality=90/31073769-1731359567-by%20Studio%20Ghibli%20and%20Alphonse%20Mucha,%20thick%20(science%20fiction%20scene_1.1)%20,%20looking%20away%20from%20camera,%20city,%20Selective%20focus.jpeg",
        description = "by Studio Ghibli and Alphonse Mucha, thick (science fiction scene:1.1) , looking away from camera, city, Selective focus"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/ac321a51-cb0c-4ce9-8eef-0a79b3ce6af4/original=true,quality=90/31073604-684726996372598-anime%20girl,%20night,%20blue%20light%20behind%20her,%20((Galaxy,%20Lens%20flare)),%20short%20hair,%20flower%20field,%20night%20sky,%20cinematic%20shot.%20Wallpaper.jpeg",
        description = "anime girl, night, blue light behind her, ((Galaxy, Lens flare)), short hair, flower field, night sky, cinematic shot. Wallpaper. (Blue color schema), detailed background, a city in the distance"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/6ea3abba-11d3-4608-a06f-1cdca9cc32a4/original=true,quality=90/31073607-684726996372601-80's%20anime%20screencap,%20girl%20wearing%20a%20cropped%20top%20and%20short%20shorts,%20artistic%20rendition%20with%20wide%20brush%20strokes,%20anime%20comic.jpeg",
        description = "80's anime screencap, girl wearing a cropped top and short shorts, artistic rendition with wide brush strokes, anime comic"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/4c006c85-869e-4dd1-9900-6538f3c702db/original=true,quality=90/31073627-18303833-Girl,%20long%20straight%20brown%20hair%20with%20bangs,%20blue%20eyes,%20looks%20at%20the%20viewer,%20blush,%20dressed%20in%20a%20black%20coat,%20wears%20a%20black%20backpac.jpeg",
        description = "Girl, long straight brown hair with bangs, blue eyes, looks at the viewer, blush, dressed in a black coat, wears a black backpack on her back, flowing hair, wind, stands outside in the snow, snow, winter, snow is falling, blizzard, hands in pockets, it's light outside, no sun, cloudy, pastel colors, in detail"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/ea2b731a-ca76-4c72-8ba9-f674728388fb/original=true,quality=90/sdxl_00454_.jpeg",
        description = "woman, fine white clothes, curiosity. Cute, shy, expressive eyes, full body shot, dark eyes, ancient mechanical devices in the background, Emily Balivet, Tim Blandin, James C. Christensen, Carne Griffiths, (1girl, portrait:0.5)"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/79582af5-7da1-4abb-b814-0421de7d78c8/original=true,quality=90/31073865-2067885436-masterpiece,%20best%20quality,%20hatsune%20miku,%20(mecha%20suit_0.8),%20tight%20suit,%20upper%20body,%20closed%20mouth,%20looking%20at%20viewer,%20arms%20behind.jpeg",
        description = "masterpiece, best quality, hatsune miku, (mecha suit:0.8), tight suit, upper body, closed mouth, looking at viewer, arms behind back, highres, 4k, 8k, intricate detail, cinematic lighting, amazing quality, amazing shading, soft lighting, Detailed Illustration, anime style, wallpaper"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/e286f323-1082-47e7-8be3-3b4a2e9161c2/original=true,quality=90/31073569-684726996372595-1girl%20,%20masterpiece,%20best%20quality,%20highres,%204k,%208k,%20intricate%20detail,%20cinematic%20lighting,%20amazing%20quality,%20amazing%20shading,%20soft.jpeg",
        description = "1girl , masterpiece, best quality, highres, 4k, 8k, intricate detail, cinematic lighting, amazing quality, amazing shading, soft lighting, Detailed Illustration, anime style, wallpaper, blonde, (idol clothes), ((white shirt)), short black skirt, white mini hat, sleeveless, musical note, rose hat ornament, (singing), hand on own chest, long hair, black bowtie, white hat, floating hair, bang, looking at viewer, open mouth, soldier, breasts, black high heel boots, layered skirt, mini skirt, gold shiny line on shirt"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/2d2e9315-307d-4afa-b6b5-4a012f06b0a8/original=true,quality=90/31073575-684726996372593-1girl,%20upper%20body,%20standing,%20playing%20guitar,%20medium%20hair,%20brown%20hair,%20red%20eyes,%20black%20glasses,%20black%20jacket,%20sleeves%20rolled%20up,.jpeg",
        description = "1girl, upper body, standing, playing guitar, medium hair, brown hair, red eyes, black glasses, black jacket, sleeves rolled up, outdoors, city, street, night, night sky"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/021981bf-54a5-4de8-a4eb-2f1f82dd95b1/original=true,quality=90/31073593-684726996372595-a%20stunning%20art,%20abstract,%20flowery,%20predominantly%20white,%20dynamic%20pose,%20centered,%20key%20visual,%20intricate,%20highly%20detailed,%20breathta.jpeg",
        description = "a stunning art, abstract, flowery, predominantly white, dynamic pose, centered, key visual, intricate, highly detailed, breathtaking beauty, precise lineart, vibrant, comprehensive, cinematic<lora:xl_more_art-full_v1:0.5>"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/0083fcdf-01c6-489d-a14b-f15159c072ae/original=true,quality=90/31073612-684726996372601-1boy,%20adult,%20handsome,%20broad%20shoulders,%20finely%20detailed%20eyes%20and%20detailed%20face,%20fantasy,%20night,%20dark%20theme,%20cinematic%20lighting,.jpeg",
        description = "1boy, adult, handsome, broad shoulders, finely detailed eyes and detailed face, fantasy, night, dark theme, cinematic lighting, colorful, portrait, flying petal, Flowery meadow, cloudy sky, building, moon, light, dutch angle, cowboy shot"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/069a54cc-098f-4c00-a1b4-2fd9a6dc80d9/original=true,quality=90/31073719-140963663-anime%20screencap%20from%20studio%20ghibli%20,%20san%20_(mononoke%20hime_),%201girl,%20armlet,%20bangs,%20black%20hair,%20black%20undershirt,%20breasts,%20cape,%20c.jpeg",
        description = "anime screencap from studio ghibli , san \\(mononoke hime\\), 1girl, armlet, bangs, black hair, black undershirt, breasts, cape, circlet, earrings, facepaint, floating hair, forest, fur cape, green eyes, jewelry, looking at viewer, medium breasts, nature, necklace, outdoors, parted bangs, shirt, short hair, sleeveless, sleeveless shirt, solo, tooth necklace, tree, upper body, white shirt , ((masterpiece))"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/c95d33db-2b70-4e1c-bcfd-ca8773f2d7f0/original=true,quality=90/31073754-3314572644-1boy,%20male%20focus,%20military%20tags,%20gloves,%20%20%20jacket,%20dark-skinned%20male,%20dark%20skin,%20facial%20hair,%20night,%20beard,%20smile,%20sunglasses,%20p.jpeg",
        description = "1boy, male focus, military tags, gloves, jacket, dark-skinned male, dark skin, facial hair, night, beard, smile, sunglasses, portrait, ((masterpiece))"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/f567e11a-790c-42b2-b3b4-5f287ff323df/original=true,quality=90/31073746-17748028598470-anime%20screencap%20by%20kyoani,%20masterpiece,%20best%20quality,%20(girl),%20beautiful%20detailed%20eyes,%20looking%20at%20viewer,%20upper%20body,%20blue%20hair,.jpeg",
        description = "anime screencap by kyoani, masterpiece, best quality, (girl), beautiful detailed eyes, looking at viewer, upper body, blue hair, shy, cat ears, very detailed, high resolution, sharp, sharp image, 4k, 8k,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/b0ff3d89-6c6a-4448-bf19-a16982591c2d/original=true,quality=90/31073689-2540058663-1girl,%20mecha%20suit,%20samurai%20face%20mask,%20menpo,%20upper%20body,%20underboob,%20portrait,%20white%20orange%20armor,%20blonde%20shimmering%20hair,%208K,%20RA.jpeg",
        description = "1girl, mecha suit, samurai face mask, menpo, upper body, underboob, portrait, white orange armor, blonde shimmering hair, 8K, RAW, best quality, masterpiece, ultra high res, colorful, (medium wide shot), (dynamic perspective), sharp focus , (depth of field, bokeh:1.3), extremely detailed eyes and face, beautiful detailed eyes,large breasts,black gold, trimmed gear,In a futuristic weapons factory, ((masterpiece, best quality)), niji, from side, upper body, hips"
    )
)

val Quiet_Goodnight_Xl_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/c4eaa433-8f3b-4d77-82c2-6caa93fc688a/original=true,quality=90/00170-3393356726.jpeg",
        description = "(best quality, masterpiece, colorful, highest detailed) (anime), 1girl wearing a dress made of fractals surrounded by a magical swirling aura"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/2da8d1f8-2643-432b-b8bc-5312083bc5b4/original=true,quality=90/00166-3288535961.jpeg",
        description = "(best quality, masterpiece, colorful, highest detailed) (anime), 8k, very wide shot, volumetrics dtx, portrait, magical of stars more soft body particles, beautiful eyes, (dreadlocks:0.5) in a ponytail, (Violet head:1), vibrant colors, highly detailed"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/ef0c4ea8-30c4-4ae2-8d80-4916019c497b/original=true,quality=90/00158-2512415385.jpeg",
        description = "(best quality, masterpiece, colorful, dynamic angle, highest detailed) a samurai standing in front of a chinese dragon"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/cdb81fae-d439-438b-bdba-e7709eae92af/original=true,quality=90/00162-4062979292.jpeg",
        description = "(best quality, masterpiece, colorful, dynamic angle, highest detailed) (anime), 8k, very wide shot, volumetrics dtx, portrait, magical of stars more soft body particles, beautiful eyes, (dreadlocks:0.5) in a ponytail, (Violet head:1), vibrant colors, highly detailed"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/e2d30bbb-bece-4cc7-9db5-78f9686a9049/original=true,quality=90/00151-1714122634.jpeg",
        description = "(best quality, masterpiece, colorful, dynamic angle, highest detailed) Tohsaka Rin \\(fate/extra\\), cowboy shot, fashion photography of cute, intense long hair, Tohsaka Rin,(ultrahigh resolution textures), in dynamic pose, bokeh, glowing web, light passing through hair, (abstract background:1.3), (official art)"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/1a5737e2-dbb0-4178-b35b-708a2b0c53c5/original=true,quality=90/00147-1714122634.jpeg",
        description = "amane kanata, 1girl, angel wings, star halo, solo, angel, feathered wings, wings, virtual youtuber, polearm, gloves, thighhighs, blue thighhighs, multicolored hair, weapon, halo, holding, smile, blue hair, purple eyes, castle, white wings, holding polearm, skirt, feathers, blue skirt, streaked hair, short hair, holding weapon, spear, grey hair, open mouth, bangs, colored inner hair, black gloves, looking at viewer, :d, hair ornament, frills, pink hair, outdoors, pleated skirt, sky, hair over one eye, cloud, single hair intake, shirt, day, tower, frilled skirt, (masterpiece,best quality)"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/7c8cfde9-3847-440f-8e5f-3c47e4bd910b/original=true,quality=90/00141-3584621409.jpeg",
        description = "best quality, masterpiece, girl singing, ((glowing music notes flying through the air))"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/f87c85ad-de41-4af7-8c49-d19782e0c057/original=true,quality=90/00132-833909120.jpeg",
        description = "(photo_\\\\(medium\\\\):1.2), by Antonio J. Manzanedo, by Jeremy Lipking, 1girl, looking at the camera, (facing the viewer:1.1), (photorealistic:1.2), hyperrealistic, photorealism, close-up, head and shoulders, purple hair with rainbow streaks, short hair, ponytail, bangs, blue eyes, friendly, eyelashes, steampunk, sleeveless, subsurface skin scattering, snowy mountain background, symmetry, depth of field, 8k, vfx, hdr, rtx"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/cab7acfe-6582-445a-bcdd-b02a02f5dd9e/original=true,quality=90/00115-1637983104.jpeg",
        description = "(anime) medium shot of Gandalf the Grey, an ancient wizard of the mystics in a deep forest. the dappled volumetric lighting cuts through the fog and haze to make an eerie cinematic scene"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/24796bc0-adcb-4fc6-b345-a0a75b5a0b99/original=true,quality=90/00105-3393880302.jpeg",
        description = "(anime) a mythical cat like beast chonk roaming through an apocalyptic ruined city"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/96272411-525c-43db-b42f-05b551ce7ffb/original=true,quality=90/00102-503719355.jpeg",
        description = "(anime) liquid environment, splashes, drips, colorful bubbles, beautiful girl, poster quality, sublime art, intricate and suggestive, (highres, highly detailed:1.2), cinematic lighting, vibrant colors"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/a73b75ad-c827-45dd-8aa6-367c0a4bf4c1/original=true,quality=90/00006-1739166646.jpeg",
        description = "best quality anime of a beautiful irish landscape\n"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/34bc48ec-8c53-433f-b80b-373943952c20/original=true,quality=90/00005-1891968174.jpeg",
        description = "best quality anime of a beautiful irish woman and a leprechaun in the emerald green isles, line art with a splash of bokeh"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/444183dc-6493-49a0-ba70-55e1f66d10ed/original=true,quality=90/00120-1524158122.jpeg",
        description = "Jane Eyre with headphones, natural skin texture, 24mm, 4k textures, soft cinematic light, adobe lightroom, photolab, hdr, intricate, elegant, highly detailed, sharp focus, ((((cinematic look)))), soothing tones, insane details, intricate details, hyperdetailed, low contrast, soft cinematic light, dim colors, exposure blend, hdr, faded"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/ee14a681-d3b8-4a6b-9692-f90121d919f3/original=true,quality=90/00018-2143915750.jpeg",
        description = "masterpiece, best quality, absurdres, 1girl, solo, purple eyes, side ponytail, black choker, arm strap, armlet, black dress, halter dress, belt, sleeveless, single glove, thigh strap, squatting, indoors, stone floor, stone walls, dungeon,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/d6626eb6-69ed-4229-9f23-5709c12205e2/original=true,quality=90/00007-3261348498.jpeg",
        description = "(masterpiece),(best quality), (dynamic angle:1.3),(solo), (colorful),(fashion),cyberpunk, 1girl, purple eyes,detailed eyes, (light blue and pink hair),(streaked hair:0.7),long hair,(twintails:1.2), patterns,detailed decorations,(zentangle), detailed background, (yellow coat),yellow,colorful coat, white shirt, blue short pants, (cyberpunk city:1.1),(cyberpunk street:1.1),night sky,billboard, anime,(cowboy shot), normal light"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/d18f6f38-47a5-4b4b-a40e-7d8e0b5cacd2/original=true,quality=90/00003-4070258594.jpeg",
        description = "high quality, logo style, Yabusame, black sumi-e silhouette of a beautiful woman, sophisticated, simple, by yukisakura, high quality,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/d48b88de-2c80-4653-8e7d-5f798eb5b41b/original=true,quality=90/00001-1604678507.jpeg",
        description = "scarf,looking at viewer,blush,winter clothes,coat,blurry,pov,hatsune miku,, masterpiece, best quality,"
    )
)

val WAI_ANI_NSFW_PONYXL_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/1d7dd335-5e1a-46b6-84fb-38bc0121348f/original=true,quality=90/01557-1484035381-score_9,%20score_8_up,%20score_7_up,%20source_anime,_surreal,%20_1girl,Kpop%20idol,%20very%20long%20hair,%20floating%20hair,glowing%20hair,%20_%20night%20sc.jpeg",
        description = "score_9, score_8_up, score_7_up, source_anime, surreal, 1girl,Kpop idol, very long hair, floating hair,glowing hair, night scene,moon in the sky,purple hues,starry night,dreamlike atmosphere,glowing edges,mystical,high contrast,ethereal light,sitting on a tree branch,light particles,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/e409faef-6f7d-4895-9c48-d5201f389b88/original=true,quality=90/01482-1697017415-score_9,%20score_8_up,%20score_7_up,%20source_anime,_1girl,%20rabbit%20ears,%20white%20hair,%20sweatshirt,%20pleated%20skirt,%20candy,%20rabbit%20plush,%20o.jpeg",
        description = "score_9, score_8_up, score_7_up, source_anime, 1girl, rabbit ears, white hair, sweatshirt, pleated skirt, candy, rabbit plush, on bed, messy room, cute & girly,stars decorated lamp, twin tails short hair"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/2aa52127-ac71-4e46-91ba-d8d3e28a402d/original=true,quality=90/01478-1001554341-score_9,%20score_8_up,%20score_7_up,%20source_anime,_%20flame%20fairy,%20freckled,%20(fire%20glow_1.2),%20(flying_1.3)%20(over%20fire_1.2),%20huge%20yello.jpeg",
        description = "score_9, score_8_up, score_7_up, source_anime, flame fairy, freckled, (fire glow:1.2), (flying:1.3) (over fire:1.2), huge yellow moon hanging over volcano, fire glowing butterflies, fireflies, fire in background, wide smile, transparent fire dress, barefoot, smog, (fire around:2),"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/957cbd25-c60e-4ae3-9773-e2d2afeca174/original=true,quality=90/01434-889709292-score_9,%20score_8_up,%20score_7_up,%20source_anime,_mature%20female,%20_%20angel%20wings,%20crop%20top,%20head%20wings,%20garter%20belt,%20garter%20straps,%20l.jpeg",
        description = "score_9, score_8_up, score_7_up, source_anime, mature female, angel wings, crop top, head wings, garter belt, garter straps, long sleeves, thong, white thighhighs, white skirt, cleavage, pink belt, small boobs, (underboob:0.85), pink panties, pinup, posing, floating, blue eyes, dark green hair, blunt bangs, hair ornament, makeup,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/88a95537-9f94-426d-b022-130ab6fd4ce5/original=true,quality=90/01535-3524610965-score_9,%20score_8_up,%20score_7_up,%20source_anime,_%20intricate%20details,%20extreme%20detail,%20very%20aesthetic,%20intricate%20details,%20abstract,.jpeg",
        description = "score_9, score_8_up, score_7_up, source_anime, intricate details, extreme detail, very aesthetic, intricate details, abstract, surreal, dynamic lighting, ray tracing, extreme contrast, the most adorable, solo, female, anthro, pony, cute, shy, blush, sitting, outside, blue sky, wide angle shot, nighttime, beautiful sky with stars and cosmos, looking upward, Detailed face, detailed face, beautiful, Expressive, Cookie Crumbles_(mlp)"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/c4805a5b-1534-47c0-be40-a69f3d015713/original=true,quality=90/01476-2767441769-score_9,%20score_8_up,%20score_7_up,%20source_anime,_%201%20girl,%20fox%20girl,%20fluffy%20tail,%20orange%20hair,%20short%20hair,%20beautiful%20hair%20ornament,.jpeg",
        description = "score_9, score_8_up, score_7_up, source_anime, 1 girl, fox girl, fluffy tail, orange hair, short hair, beautiful hair ornament, black kimono, golden print, sitting, holding glass, mamosa glass, cowboyshot, fur trimmed coat, off shoulder, medium breasts, light smile, crossed legs, japanese traditional themed background"
    )
)

val WAI_CUTE_Pony_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/e5bd222d-b394-496e-9233-00c51abc78d6/original=true,quality=90/00490-107484374-1girl,,%20score_9,%20score_8_up,%20score_7_up,_masterpiece,%20best%20quality,_1girl,%20%20long%20hair,%20blue%20hair,%20low%20twintails,%20gradient%20hair,.jpeg",
        description = "1girl,, score_9, score_8_up, score_7_up, masterpiece, best quality, 1girl,  long hair, blue hair, low twintails, gradient hair, blue eyes, heterochromia, gold eye, hair ribbons, diamond hair ornament, white shawl, black sweater, long white skirt, frills, ribbons, frill cuffs, fur trim, fur boots, hip pouch, sitting in a cafe, sitting on chair, leaning against table, elbow on table, hand on cheek, desserts on table, bright colors,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/a1acb9d8-7d5a-4cb5-8d9f-d17f5003c77d/original=true,quality=90/00496-4030005160-score_9,%20score_8_up,%20score_7_up,_masterpiece,%20best%20quality,_indoors,%201girl,%20smug,%20fangs,%20angel,%20angel%20wings,%20gothic,maid,.jpeg",
        description = "score_9, score_8_up, score_7_up, masterpiece, best quality, indoors, 1girl, smug, fangs, angel, angel wings, gothic,maid,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/72259827-ef58-459b-ba0c-389d467c1127/original=true,quality=90/00491-2728850931-1girl,,%20score_9,%20score_8_up,%20score_7_up,_masterpiece,%20best%20quality,_2girls,%20glasses,%20vfu,%20employee%20uniform,%20black%20hair,%20black%20dr.jpeg",
        description = "1girl,, score_9, score_8_up, score_7_up, masterpiece, best quality, 2girls, glasses, vfu, employee uniform, black hair, black dress, short sleeves, collared dress, white apron, upper body, blue triangular bandage, hand up, v, double v, happy, smile, looking at viewer, shelf, food, bread, basket, shop, indoors,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/e6866727-b9b3-4007-941d-3cfa4916a7c6/original=true,quality=90/00539-3148926695-score_9,%20score_8_up,%20score_7_up,_masterpiece,%20best%20quality,_cute%20round%20face,%20slender,%20ultra%20detailed%20eyes,%20ultra%20detailed%20hair,.jpeg",
        description = "score_9, score_8_up, score_7_up, masterpiece, best quality, cute round face, slender, ultra detailed eyes, ultra detailed hair, ultra beautiful BREAK 1girl, solo, vintage-inspired beret with flower pin, navy and red plaid dress, golden buttons down the front, ivory blouse with ruffle detailing and red bow tie, peach cardigan with cable knit pattern, (cable:-1), black tights, cowboy shot, smile, open mouth, looking at viewer, Detailed dark-green eyes, medium long hair, pink hair"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/04638cd4-8b65-493c-9242-4d875fbaf24e/original=true,quality=90/00594-554328398-score_9,%20score_8_up,%20score_7_up,_masterpiece,%20best%20quality,_1girl,%20solo,%20gothic%20drress,%20Idol%20costume,%20blue%20and%20white%20theme,%20whit.jpeg",
        description = "score_9, score_8_up, score_7_up, masterpiece, best quality, 1girl, solo, gothic drress, Idol costume, blue and white theme, white blouse, white collar, tie, open short-cape, short sleeve, blue tartan-check pattern (ruffle-skirt, multilayer-skirt), white basque-beret with ribbon, Fishnet stockings, glove, happy smile, laugh, open mouth, standing, cowboy shot, cityscape in tokyo, ultra detailed background, blue sky, bay side, panorama view, medium breasts, white hair, blue eyes"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/70e5335e-5b73-4cc7-be63-0c7faac63e7b/original=true,quality=90/00590-2239675514-score_9,%20score_8_up,%20score_7_up,_masterpiece,%20best%20quality,_1girl,%20solo,%20Harajuku-style%20Decora%20pank%20fashion,%20girl%20with%20layered%20c.jpeg",
        description = "score_9, score_8_up, score_7_up, masterpiece, best quality, 1girl, solo, Harajuku-style Decora pank fashion, girl with layered colorful clothing, multiple hair clips, knee-high socks with different patterns, carrying a plushie, standing in front of a graffiti wall, happy smile, laugh, closed mouth, standing, cowboy shot, looking at viewer, city, shibuya, depth of field, ultra detailed background, medium large breasts, (random color hair, multicolored hair:1.2), rainbow color eyes, bob with bangs, hair between eyes"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/54f96011-0ed8-41be-ad21-dda90a1bab6a/original=true,quality=90/00552-2762851236-score_9,%20score_8_up,%20score_7_up,_masterpiece,%20best%20quality,_1girl,%20solo,%20black%20theme,%20fit%20body,%20tight%20long%20sleeve%20and%20turtleneck.jpeg",
        description = "score_9, score_8_up, score_7_up, masterpiece, best quality, 1girl, solo, black theme, fit body, tight long sleeve and turtleneck (black sweater), black and red theme, (red) high-waisted ((maxi length skirt, very long A line skirt) with [plaid pattern]:1.4), (black) bucket hat with wide brim, (earrings, necklace), happy smile, laugh, open mouth, elegant pose, cowboy shot, looking at viewer, bay, sea, harbor, bay side, cityscape in tokyo, buildings, day, blue sky, panorama view, outdoor, day, depth of field, ultra detailed background, very wide, panorama view, sense of depth, magnificent view, medium breasts, pink hair, pink eyes, crochet braids, hair between eyes"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/9bfc7830-a41e-46e0-8b76-2315dfabe052/original=true,quality=90/00508-1779166499-score_9,%20score_8_up,%20score_7_up,_masterpiece,%20best%20quality,_palace,%20throne,%20red%20carpet%20cheeb_drg,%20chibi,%20pink%20scales,%20(gold%20unde.jpeg",
        description = "score_9, score_8_up, score_7_up, masterpiece, best quality, palace, throne, red carpet cheeb_drg, chibi, pink scales, (gold underscales:1.1), dragon, sitting, serious expression, white cape, yellow eyes, crown, facing viewer, (feathered wings:1.1), wings, (feathers:1.1) beautiful background, detailed scenery"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/a111a306-fa60-4a6d-9664-8934fe3a991e/original=true,quality=90/00504-2935092277-score_9,%20score_8_up,%20score_7_up,_masterpiece,%20best%20quality,_cowboy%20shot,%20night,%20fluffy,%20(feral),%20outdoors,%20furry,%20female,%20bright.jpeg",
        description = "score_9, score_8_up, score_7_up, masterpiece, best quality, cowboy shot, night, fluffy, (feral), outdoors, furry, female, bright green eyes, grey fur, grey ears, claws, (fluffy cheeks:0.4), grey hooded cape, hood up, (blue dress), cute, in the style of beatrix potter, cel-shading, fca style, village, night, rain, (dark:1.5), backlit, (side view:1.3), (upper body shot), look back"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/2398b22e-2b84-46ce-80d7-7bd4dac057c0/original=true,quality=90/00542-3695016122-score_9,%20score_8_up,%20score_7_up,_masterpiece,%20best%20quality,_1girl,%20solo,%20a%20cute%20girl,%20cute%20round%20face,%20jirai%20kei,plaid%20skirt,%20sh.jpeg",
        description = "score_9, score_8_up, score_7_up, masterpiece, best quality, 1girl, solo, a cute girl, cute round face, jirai kei,plaid skirt, shirt, long sleeves, bow, red skirt, white shoulder frilly shirt, outdoors, red bow, expressionless, closed mouth, standing, cowboy shot, looking at viewer, slender, kawaii, perfect symmetrical face, ultra cute girl, ultra cute face, ultra detailed eyes, ultra detailed hair, ultra cute, ultra beautiful, photoreal detailed background, photo realictic background, Peaceful countryside chapel surrounded by fields of wildflowers, depth of field, ultra detailed background, large breasts, (blonde hair:1.2), medium wavy hair, hair between eyes"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/620507c8-d7d6-423d-9768-d414718853be/original=true,quality=90/00503-3077619268-score_9,%20score_8_up,%20score_7_up,_masterpiece,%20best%20quality,_outdoors,%20full%20moon,%20cowboy%20shot,%20_1girl,%20demon,white%20hair,pale%20skin.jpeg",
        description = "score_9, score_8_up, score_7_up, masterpiece, best quality, outdoors, full moon, cowboy shot, 1girl, demon,white hair,pale skin, twintails, black bows,black demon wings,black dress,long eyelashes,short hair,collar,magic,"
    )
)

val AM_Pony_XL_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/5ca8ba25-d4b4-4ba2-b68d-c0257241b987/original=true,quality=90/02148-ForgeUI.jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, score_5_up, score_4_up, BREAK source_anime, 1girl, clothed, saber alter, indoors, potted plant, window, sunlight, by kasumi \\(skchkko\\), looking at viewer, serious, dutch angle, maid uniform, braid, double middle finger,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/d0796353-6c28-4b00-bb2e-096e708f22d8/original=true,quality=90/02139-ForgeUI.jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, score_5_up, score_4_up, BREAK source_anime,1girl, clothed, yoko littner, v, in beach, sunny , sea, sand, sun, by nilsunna, looking at viewer, happy, dutch angle, curvy"
    )
)

val Illust_Diffusion_XL_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/61e8a376-b4c7-4468-971d-2bcc213c7d2d/original=true,quality=90/image%20(172).jpeg",
        description = "A girl surrounded by black roses, full body, scared, black long hair with blunt bangs, in black dress with frills and lace, smile with face blush, aesthetic, beautiful color, (amazing quality:1.5), by modare, by ask, by ikky, depth of field, chromatic aberration, black theme, perspective, amazing lighting and shadow"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/c885e4ff-6912-434b-9faf-450ca4fc5fa5/original=true,quality=90/image%20-%202024-03-06T005042.920.jpeg",
        description = "a girl surrounded by black rose, full body, scared, black long hair with blunt bangs, smile with face blush, aesthetic, beautiful color, (amazing quality:1.5), by modare, by ask, by ikky, depth of field, chromatic aberration, black theme"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/6be1d260-933b-40c8-bb07-f43d8fb399f7/original=true,quality=90/image.jpeg",
        description = "1girl, solo, in k-pop style fashionable outfit with long sleeves, intricate hairstyle, cold and cruel look, black hair, facial mask with a bloody mouth pattern on it, stomach, croptop, bloomer pants, chains, belt, by novelance, by nnoelllll, by r1zen, by swav, beautiful color, amazing quality, detailed, 3d, photorealistic, best lighting and shadow, high contrast, at night, sci-fi cityscape"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/97a2a44e-4e95-4aab-94d8-ea99cac4cfce/original=true,quality=90/image%20(21).jpeg",
        description = "flat color, a girl named fern from sousou no freiren series, holding a huge magic wand, standing on the lawn, wind, day, amazing quality, very beautiful color, by iamuu, aesthetic, birds, beautiful scenery"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/e7c71991-7c30-4d2d-ae35-86550b93e2bb/original=true,quality=90/image%20(40).jpeg",
        description = "beautiful color, detailed, amazing quality, artist: 44c, 1girl, character: ganyu \\(genshin impact\\), solo, full body, indoors, sitting, looking at viewer, blush, smile, sideboob, black gloves, black pantyhose, detached sleeves, bare shoulders, white sleeves, tassel, vision \\(genshin impact\\), vase, paper, book, flower knot, gold trim, chinese knot, chair, window, white flower, thighlet, curtains, qingxin flower"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/6f612505-b0db-47a1-bcea-0ca53b89d98d/original=true,quality=90/image%20(20).jpeg",
        description = "by ikky, chun-li, amazing quality"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/fdd240b7-f879-4708-9b55-b45d63244cfb/original=true,quality=90/image%20(94).jpeg",
        description = "clean color, 1girl, solo, asuna, upper body, white background, cowboy shot, puts her hands on her hips, sad, beautiful color, best quality"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/769aa019-e8b6-4298-a923-3b8f6b312054/original=true,quality=90/image%20-%202024-03-01T214651.595.jpeg",
        description = "1girl, solo, character: lucy, white background, cowboy shot, hip cutout, sad expressions, pantyhose, best quality, beautiful color, by bigroll"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/c6c0eaa3-cf76-42d4-9980-1a2c9f7abce8/original=true,quality=90/image%20(3).jpeg",
        description = "strong batman, dark theme, best quality, by njer"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/58fbd1a8-e0a5-4889-826a-2e2411c67eeb/original=true,quality=90/image%20-%202024-03-04T002504.234.jpeg",
        description = "by yoneyama mai by James Gilleard 1girl, smile artoria pendragon \\(fate\\), fate \\(series\\) perspective medieval beautiful, aesthetic, detailed, beautiful color amazing quality, best quality, high quality"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/9b5984ec-87e2-4ad6-b4fc-9038839ce59e/original=true,quality=90/image%20-%202024-03-02T210145.936.jpeg",
        description = "strong batman, by ask, upper body"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/89f5a70b-28df-47ce-a20f-85b25957b8a5/original=true,quality=90/image%20(29).jpeg",
        description = "detailed, amazing quality, a girl in oversized clothes, lazily sitting on her bed, her room is intricately detailed, frame"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/7dcd4b74-c3ce-40ab-893c-401ed9174560/original=true,quality=90/image%20(19).jpeg",
        description = "flat color, Shiranui Maiï¼\u008C amazing quality"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/b8729963-e843-4407-8686-b61094939244/original=true,quality=90/image%20-%202024-03-03T211309.120.jpeg",
        description = "close-up view of a girl named echidna from re:zero series black long dress, is dancing elegantly, at day, best lighting and shadow, amazing quality, very beautiful color, delicate face, cinematic lighting, by sage joh"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/f0d90b4b-38dd-4f14-9cd2-48315af2b978/original=true,quality=90/image%20-%202024-03-03T165440.957.jpeg",
        description = "1girl, solo, koharu \\(blue archive\\), serafuku, skirt, best quality, beautiful color, beautiful, detailed, cinematic lighting, best lighting and shadow, amazing quality, blue sky, depth of field"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/6e9866dc-6086-4f8d-8cfa-5afe82d79aae/original=true,quality=90/image%20(6).jpeg",
        description = "by rella, amazing quality, masterpiece, best quality, absurdres, beautiful, detailed shadow, aesthetic, 1girl, solo, hatsune miku, long hair, detached sleeves, twintails, very long hair, open mouth, shirt, black skirt, smile, sleeveless shirt, blue necktie, white background, blue hair, blue eyes, arm up, looking at viewer, pleated skirt, star (symbol), thighhighs, bare shoulders, white shirt, cowboy shot, :d, floating hair, simple background, upper teeth only, collared shirt, outstretched arm, wide sleeves, bangs, hair between eyes, miniskirt, grey shirt, black sleeves, zettai ryouiki, light particles, hair ornament, small breasts, long sleeves"
    )
)

val Blender_Mix_Pony_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/71a91991-a1d5-4a91-ae37-cb0bafb036f2/original=true,quality=90/00046-1432149219.jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, BREAK 1girl, solo, aerith gainsborough, portrait, blue background, depth of field, shadow,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/44b507cc-5c67-46ed-8894-064d2826d8bd/original=true,quality=90/00103-3433393823.jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, BREAK 1girl, solo, velma dace dinkley, sweater, glasses, freckles, black background, backlighting, scared, upper body, portrait, makeup,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/e9512a66-e4ce-4537-8507-3c2d9f4ac5a3/original=true,quality=90/00088-468645142.jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, BREAK 1girl, solo, jinx \\(league of legends\\), portrait, looking at viewer, purple background, sidelighting, backlighting, turtleneck sweater, 3d, realistic, sleeveless, necklace, makeup, glowing,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/4bc81210-5a3e-4edd-ba5e-b423136734f1/original=true,quality=90/00095-3835180838.jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, BREAK 1girl, harley quinn, jacket, goggles on head, depth of field, parted lips, 3d, realistic"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/1531ff1f-4d25-43f5-a212-2248464d83fb/original=true,quality=90/00099-3013099240.jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, BREAK 1girl, solo, princess zelda, sundress, flower field, depth of field, light particles, lens flare, sunset, 3d, realistic, looking to the side,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/5c597b3c-73b0-4159-a629-1180c5e5781b/original=true,quality=90/00054-3147162336.jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, BREAK 1girl, solo, tifa lockhart, sports bra, sweatpants, black pants, long hair, sitting, leg up, shadow, midriff,  <lora:chromaticAberrationPDXL2_v3:0.7> chromatic aberration, shiny skin, depth of field, sidelighting, realistic, 3d, white background, sweat, looking to the side, simple background,"
    )
)

val DucHaiten_GameArt_Unreal_Pony_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/7b6481d1-f45e-42e8-8f74-dd9ee6298b8e/original=true,quality=90/00018-3942505617.jpeg",
        description = "score_9, score_8_up, score_7_up, Hilde from \"Soul Calibur\" surveys her kingdom from atop a castle wall, her armor glinting in the sunset. Her regal and commanding presence, combined with her dedication to her people, marks her as a noble warrior queen."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/70360787-45b6-453c-8c7c-0001e8645a83/original=true,quality=90/00023-3942505624.jpeg",
        description = "score_9, score_8_up, score_7_up, Trying to promote his olive oil, Hakan slips on a spill during a demonstration, sliding comically across the floor. His good-natured laugh and thumbs-up reassure everyone he's fine, turning a slip-up into a humorous highlight."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/7b8508e1-5c3e-439f-8228-46cc9eca2283/original=true,quality=90/00020-3942505619.jpeg",
        description = "score_9, score_8_up, score_7_up, Cerebella from \"Skullgirls\" performs under the big top, her living hat Vice-Versa enhancing her dazzling acrobatic moves. Her infectious energy and showmanship make her a standout in the chaotic ensemble of fighters."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/b2635a24-86bb-47fd-bbfe-ef6e1a1fd30e/original=true,quality=90/00008-1317919312.jpeg",
        description = "score_9, score_8_up, score_7_up, i want the whole image to be created in 3D anime style, solo, looking at viewer, simple background, shirt, black hair, 1boy, upper body, male focus, necktie, collared shirt, vest, facial hair, sleeves rolled up, realistic, manly, undercut"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/c5f3a74b-0c38-4726-9b6d-f09978348b0d/original=true,quality=90/00007-1317919311.jpeg",
        description = "score_9, score_8_up, score_7_up, i want the whole image to be created in 3D anime style, solo, looking at viewer, red eyes, 1boy, male focus, horns, teeth, no humans, mask, glowing, colored skin, sharp teeth, glowing eyes, red skin, demon"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/9818692e-e58f-45e3-bdc3-3794e66839fd/original=true,quality=90/00022-3942505621.jpeg",
        description = "score_9, score_8_up, score_7_up, Asuka Kazama from \"Tekken\" practices in an old dojo, her movements fluid and precise. The sunlight streaming through the wooden slats highlights her concentration and martial arts prowess, showing her as a guardian of tradition and fighter spirit."
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/6bac4907-c7e0-487c-80a0-3b49aaacc56b/original=true,quality=90/00005-1317919309.jpeg",
        description = "score_9, score_8_up, score_7_up, i want the whole image to be created in 3D anime style, solo, long hair, looking at viewer, blue eyes, simple background, 1boy, jewelry, closed mouth, white hair, male focus, earrings, glasses, blurry, lips, cross, portrait, black-framed eyewear, realistic, cross earrings"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/11cb0506-cb2f-4d62-ae7d-eb0b1c95255d/original=true,quality=90/00006-1317919310.jpeg",
        description = "score_9, score_8_up, score_7_up, i want the whole image to be created in 3D anime style, 1girl, solo, long hair, looking at viewer, black hair, brown eyes, jewelry, closed mouth, upper body, braid, earrings, looking back, from behind, blurry, black eyes, lips, single braid, tattoo, blurry background, piercing, forehead, freckles, realistic, nose, back tattoo"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/2e1352a8-4644-4c1b-b44a-2e6977dc7c39/original=true,quality=90/00003-1317919307.jpeg",
        description = "score_9, score_8_up, score_7_up, i want the whole image to be created in 3D anime style, 1girl, solo, long hair, breasts, looking at viewer, blush, bangs, blue eyes, black hair, hat, hair between eyes, bare shoulders, twintails, jewelry, medium breasts, closed mouth, earrings, outdoors, detached sleeves, choker, water, star (symbol), leotard, wet, covered navel, witch hat, blue headwear, hat ornament, fur collar, bodystocking, blue leotard, star earrings, mona (genshin impact)"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/2dbf16fd-e26b-403d-8f46-2730c8e55bb8/original=true,quality=90/00000-1317919304.jpeg",
        description = "score_9, score_8_up, score_7_up, i want the whole image to be created in 3D anime style, solo, long hair, looking at viewer, simple background, black hair, red eyes, 1boy, closed mouth, upper body, male focus, lips, eyepatch, black background, portrait"
    )
)


val TUNIX_Pony_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/1425ba91-5e81-4eb6-9628-493e77d435f2/original=true,quality=90/WoW3.jpeg",
        description = "cinematic film still score_9, score_8_up, score_7_up, BREAK, rating_safe, solo, female, elf, night, dungeon, portrait, portal, magic, clock, action pose, lens flare, detailed, world of warcraft, <lora:WarcraftStyle_v2:.3> . shallow depth of field, vignette, highly detailed, high budget Hollywood movie, bokeh, cinemascope, moody, epic, gorgeous, film grain, grainy"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/201a5cfd-e267-4c05-bf1e-35929d7ffc99/original=true,quality=90/WoW2.jpeg",
        description = "cinematic film still score_9, score_8_up, score_7_up, BREAK, rating_safe, solo, female, chronomancer, elf, night, alien planet, magick, clock, action pose, lens flare, detailed, world of warcraft, <lora:WarcraftStyle_v2:.3> . shallow depth of field, vignette, highly detailed, high budget Hollywood movie, bokeh, cinemascope, moody, epic, gorgeous, film grain, grainy"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/225c843c-8aa2-42d4-a914-8bca6536e189/original=true,quality=90/WoW1.jpeg",
        description = "score_9, score_8_up, score_7_up, BREAK, rating_safe, solo, female, magic user, night, dungeon, purple, magick, dr who, action pose, lens flare, detailed, world of warcraft, 43stl1ght1ng, low light, darkness, dramatic lighting, <lora:hand 4:1>, <lora:MJ52:.3>, <lora:CinematicStyle_v1:.44>, <lora:DramaticLight:.3>"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/da827b36-ab6b-4f79-a6e1-3f41d83e5adc/original=true,quality=90/00044-3360098821.jpeg",
        description = "score_9, score_8_up, score_7_up, BREAK, flora fauna, creature next to a river, fantasy, rpg"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/6dd9363e-48c7-456d-bf90-24d0dcb0bccf/original=true,quality=90/00042-3869999648.jpeg",
        description = "score_9, score_8_up, score_7_up, BREAK, giant mushroom house, fantasy, rpg"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/279f1e5c-1ebd-47c4-bc99-40618d053923/original=true,quality=90/00041-2039823896.jpeg",
        description = "score_9, score_8_up, score_7_up, BREAK, medieval hunter resting looking at the sunset, from behind, fantasy, rpg"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/5f00556e-d38b-4492-a3f2-6fea9ecda178/original=true,quality=90/00023-4021408092.jpeg",
        description = "score_9, score_8_up, score_7_up, BREAK, abandoned dark library, environment,"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/51c2621a-0391-4c58-9c4e-8ef52037d6c6/original=true,quality=90/00009-1149444937.jpeg",
        description = "score_9, score_8_up, score_7_up, BREAK, medieval fantasy, goblin, campfire, relaxing, rim lighting, depth of field, high quality, bokeh"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/cc318133-f6b8-42d7-b8fc-fe7315f8280d/original=true,quality=90/00007-3485997227.jpeg",
        description = "score_9, score_8_up, score_7_up, BREAK, cozy tavern, scenery, fireplace, warm, rim lighting, depth of field, high quality, bokeh"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/5cadd86e-fb1b-4ba7-a701-29b65315831b/original=true,quality=90/00024-2334711186.jpeg",
        description = "score_9, score_8_up, score_7_up, BREAK, a horned creature sitting in a field, tired, sun rising, (dark fantasy:1.2), soft light"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/84b3ab87-f6f9-4850-8a7a-b3af22bf56da/original=true,quality=90/00019-1515355343.jpeg",
        description = "score_9, score_8_up, score_7_up, BREAK, giant, monster, in a cornfield, medieval, epic shot, low-angle view, (dark fantasy:1.2), soft light"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/e0b9703e-ed38-4fd1-8c12-6c82de7bbccc/original=true,quality=90/00001-3279094528.jpeg",
        description = "score_9, score_8_up, score_7_up, BREAK, water Elemental, water elemental dragon, swirling water body, transparent, ethereal, majestic, detailed digital art, expressive eyes, roaring, cloudy sky background, magical elements"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/cb1c4824-fc60-4872-8cb9-e80027c3f454/original=true,quality=90/00021-4223263433.jpeg",
        description = "score_9, score_8_up, score_7_up, BREAK, portrait of a elf hunter sitting in a tree branch, in a fastasy forest"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/f6664bb2-b58c-4d44-8623-dc0b0ad5226a/original=true,quality=90/00011-3804131847.jpeg",
        description = "score_9, score_8_up, score_7_up, BREAK, medieval, male guard, helm, front view, detailed armor, intricate, gold, firm, castle, rim lighting, depth of field, high quality, bokeh"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/506a8d4f-c0d1-4b03-bbb3-3ef1d07b10b3/original=true,quality=90/00004-435924554.jpeg",
        description = "score_9, score_8_up, score_7_up, BREAK, elf girl, red hair, (arms tied above head:1.2), hands tied, (standing in dark corner:1.2), bright green eyes, detailed eyes, petite body, slim thighs, thin waist, small breast, (head tilt:1.25), (contemptuous:0.8), sarcastic expression, (mischievous expression:1.2), naughty, smug expression, a bit scared, (questioning look:1.2), arrogance, (smug expression:0.8), exotic, (black:1.5), bodysuit, dark colors clothes, dark colors outfit, cleavage, ribbons on legs, short skirt, simple solid knitted thigh highs, long gloves, looking at viewer, eye contact, arrogant face, adorable, (long braided ponytail hair with side bang:1.2), 1girl, semi realistic, pov, (high angle shot:1.5), (pov male fat body:1.5), (male pov body wearing medieval armor:1.5), (view from above:1.25), (full body shot:1.1), source_cartoon, dramatic lighting, detailed background, background fantasy setting, medieval dungeon, (in small dark cellar:1.2), one prison window, moonlight, brick wall"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/0a5f43cb-f2bf-47b3-98bf-ba687c7bc167/original=true,quality=90/01690.jpeg",
        description = "score_9, score_8_up, score_7_up, BREAK, rating_safe, medieval female warrior, detailer, on one knee, sword , fantasy, rpg, detailed, depth of field, vignette, highly detailed, moody, epic, gorgeous, film grain, grainy"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/e31c3b3c-3476-4bec-ba5d-92acf418bc2a/original=true,quality=90/01861.jpeg",
        description = "score_9, score_8_up, score_7_up, BREAK, rating_safe, dark elf, dark dungeon, interior, bedroom eyes, evil, sorcerer, skimpy, red theme, glowing eyes, looking at viewer, magick on hand, lens flare, portrait, dark fantasy, rpg, detailed, depth of field, vignette, highly detailed, moody, epic, gorgeous, film grain, grainy"
    )
)


val Fustercluck_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/66774418-8c69-46e6-8a28-1dd49fc7328b/width=450/00169-2367887771.jpeg",
        description = "cartoon of (a badass rebel rabbit dressed as a biker gang, leather jacket, mohawk:2.5), looking at viewer, symmetrical, chromatic fantasy, highly detailed, 8k, digital painting, oil painting, illustration, concept art, sharp focus, volumetric lighting, epic Composition, cgsociety, artstation"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/f1e3da40-72c3-4897-b4cf-4f4a8d92ed3c/width=450/00171-2706813141.jpeg",
        description = "pixar disney cartoon of (a cute baby giraffe in a big city :1.2), looking at viewer, symmetrical, chromatic fantasy, highly detailed, 8k, digital painting, oil painting, illustration, concept art, sharp focus, volumetric lighting, epic Composition, cgsociety, artstation"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/7ec8ac11-f7a2-416d-9eae-139550860bd7/width=450/00093-301542712.jpeg",
        description = "Masterpiece pixar nickelodeon cartoon of (a violent dwarf holding a rose, love), symmetrical, best quality, digital painting, artstation, concept art, sharp focus, illustration, volumetric lighting, epic Composition, 8k, oil painting, cgsociety, subsurface scattering, global illumination, chiaroscuro"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/6ae85b1f-d8d6-4640-9912-3eebcb542e41/width=450/00122-2624322162.jpeg",
        description = "(pixar) disney cartoon of a cute baby polar bear cub, symmetrical, highly detailed, 8k, digital painting, oil painting, illustration, concept art, sharp focus, volumetric lighting, epic Composition, cgsociety, artstation"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/22ed6807-5b83-494d-b449-a37cdadacb02/width=450/00133-689071257.jpeg",
        description = "Masterpiece pixar disney cartoon of a military general, highly decorated, badges, badges, extra badges, highly detailed, digital painting, artstation, concept art, sharp focus, illustration, volumetric lighting, epic Composition, 8k, oil painting, cgsociety"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/c3db670d-a3c1-49b1-b098-f0bc9be415bf/width=450/00178-1238277016.jpeg",
        description = "(Makoto Shinkai, Greg Rutkowski), (anime:2) of (a samurai with a chinese dragon in background:1.6), symmetrical, highly detailed, 8k, illustration, concept art, sharp focus, volumetric lighting, epic Composition, artstation"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/41e11208-6b95-4c0c-8dea-954dee7661b3/width=450/00127-2654537652.jpeg",
        description = "pixar disney cartoon of a (cute baby quokka in a tuxedo), symmetrical, highly detailed, 8k, digital painting, oil painting, illustration, concept art, sharp focus, volumetric lighting, epic Composition, cgsociety, artstation"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/75f4e0ad-368d-486d-8331-82a8fd88489f/width=450/00134-1861490646.jpeg",
        description = "pixar (disney) cartoon of (mr pebble, canadian lumberjack, short), symmetrical, chromatic fantasy, highly detailed, 8k, digital painting, oil painting, illustration, concept art, sharp focus, volumetric lighting, epic Composition, cgsociety, artstation"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/a1148cb6-4489-440b-a3ae-2c44ed0e2fee/width=450/00148-1490483033.jpeg",
        description = "pixar (disney) cartoon of (the saddest teddy bear in the world, sadness, misery, abandoned, alone), teddy bear sitting on a bed, symmetrical, chromatic fantasy, highly detailed, 8k, digital painting, oil painting, illustration, concept art, sharp focus, volumetric lighting, epic Composition, cgsociety, artstation"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/e4e52cad-f4da-4d25-9edd-1cbe0c58123e/width=450/00156-476205018.jpeg",
        description = "((pixar)) disney cartoon of (a chinese dragon as a chef), symmetrical, chromatic fantasy, highly detailed, 8k, digital painting, oil painting, illustration, concept art, sharp focus, volumetric lighting, epic Composition, cgsociety, artstation"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/add4c430-b045-4e4e-910b-4979e676d4b0/width=450/00158-2588755805.jpeg",
        description = "(Marvel comic book style anime:2.5) of (1girl, blonde, holding electric orbs, magic:1.4), symmetrical, highly detailed, 8k, illustration, concept art, sharp focus, volumetric lighting, epic Composition, artstation"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/094de9b0-584e-4596-82f4-3cc917d907fa/width=450/00145-3039809412.jpeg",
        description = "pixar (disney) cartoon ((at night, starry sky))of (a teddy bear wearing (mirrored sunglasses) holding a petrol can, walking away from an explosion, exploding building), symmetrical, chromatic fantasy, highly detailed, 8k, digital painting, oil painting, illustration, concept art, sharp focus, volumetric lighting, epic Composition, cgsociety, artstation"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/c08d4618-3179-4f95-977f-ba13448e4a21/width=450/00164-4166950672.jpeg",
        description = "(anime:2.5) of (1girl dressed as an owl:1.4), symmetrical, highly detailed, 8k, illustration, concept art, sharp focus, volumetric lighting, epic Composition, artstation"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/0bcdbc8a-aa12-4b61-a7a5-e43d20429ecc/width=450/00113-1553215097.jpeg",
        description = "Masterpiece ((monsterpunk zombie)) pixar cartoon that looks like (samuel l jackson:0.8), symmetrical, highly detailed, 8k, digital painting, oil painting, illustration, concept art, sharp focus, volumetric lighting, epic Composition, cgsociety, artstation"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/0bed9b96-b29e-4e97-b846-289eec0be18b/width=450/00116-1844268890.jpeg",
        description = "Masterpiece pixar nickelodeon cartoon of a (cow with a big stereo with speakers), swiss alps, symmetrical, highly detailed, 8k, digital painting, oil painting, illustration, concept art, sharp focus, volumetric lighting, epic Composition, cgsociety, artstation"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/935e293e-0065-447a-b6b2-cb5a66509f06/width=450/00087-1360617448.jpeg",
        description = "Masterpiece pixar nickelodeon cartoon of ((a cute retriever puppy, fluffy)), puppy in a cage (behind bars), sadness, hope, digital painting, artstation, concept art, sharp focus, illustration, volumetric lighting, epic Composition, 8k, oil painting, cgsociety"
    )
)

val Cheyenne_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/c5601da4-9cf6-4582-9014-100ab6d15a97/width=450/36509-3687012024-(graphic%20novel%20illustration,%20flat%20colors,%202d_1.3),%20artwork,%20candid%20camera,%20medium%20shot,%20view%20of%20cheyenne,%20petite,%20small%20breasts,.jpeg",
        description = "(graphic novel illustration, flat colors, 2d:1.3), artwork, candid camera, medium shot, view of cheyenne, petite, small breasts, perky breasts, skinny curvy proportions, sexy, confident, long curvy black hair, braids, bangs,freckles, pale suede skin leather indian tunic, dress, heroic action pose, traditional outfits, wild, holding a cheyenne weapon, feather"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/8fe153a0-727d-42e4-bbcc-a5fb0ca6f653/width=450/36558-2049347266-horror%20theme,%20klimt%20composition,%20%20Japanese%20edo%20period%20theme,%20neo%20tokyo,%20cyberpunk,%20engineer,%20high%20technology,%20connected%20slaves,.jpeg",
        description = "horror theme, klimt composition,  Japanese edo period theme, neo tokyo, cyberpunk, engineer, high technology, connected slaves, corrupted politicians, yakuzas, cyborg samourai in action pose with traditionnal haircut, traditionnal outfits, wired slaves, traditionnal japanese edo patterns embroideries,  adorned with elementals edo magics,  Junji Ito graphics, sketch, graphic novels, complexe city background, sketch, Kawanabe Ky\u0001Msai, flat design"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/369a9863-706e-4288-98a2-1cc48c77ee10/width=450/36588-766669288-raw%20Pirates%20meeting%20in%201473%20in%20America%20colonisation,%20Barbades,%20graphic%20novel%20comics%20,%20Bar%20Pirate,%20women%20and%20men%20together,%20a%20lead.jpeg",
        description = "raw Pirates meeting in 1473 in America colonisation, Barbades, graphic novel comics , Bar Pirate, women and men together, a leader talking to the crowd, highly detailed, wide angle , 1girl, eating, punch face"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/f2cd8ee7-f6ab-4e80-b2dc-c9dd26b07a6f/width=450/36830-1670682985-red%20dead%20redemption,%20cowboy%20face,%20unshaved,%20a%20trip%20to%20tomorrow%20era,%20aftermath,standing,%20%20face%20detail,%20close%20view,%20cinematic,%20gra.jpeg",
        description = "red dead redemption, cowboy face, unshaved, a trip to tomorrow era, aftermath,standing,  face detail, close view, cinematic, graphic novel, vibrant, highly detailed, red black gradients, outline, detailed textures, sketch, drawing"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/5aadd90b-35e8-42b3-ad0c-cd664d5c3431/width=450/36222-1476423002-small%20breasts,%20cheyenne,%20graphic%20novels,captivating%20eyes,%20hard%20shadows,%20strong%20pale%20eyes,%20hat,%20wild%20west,%20deciduous%20face,%20hero%20p.jpeg",
        description = "small breasts, cheyenne, graphic novels,captivating eyes, hard shadows, strong pale eyes, hat, wild west, deciduous face, hero pose, petite, native outfits, bare shoulders, simple old paper background"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/b3370851-74c6-43fe-a244-fa2703767eed/width=450/36285-3188639817-Paris%201900,%20Dark%20Fantasy,%20Dark%20night,%20moonlight,%20strong%20rim%20lighting,%20hard%20shadows,_CROW%20MAN_,%20Newspaper%20title,%20uncanny%20ugly%20her.jpeg",
        description = "Paris 1900, Dark Fantasy, Dark night, moonlight, strong rim lighting, hard shadows,\"CROW MAN\", Newspaper title, uncanny ugly hero seated on the ledge, art nouveau architecture, iron structures, strong facial expression, large cape, thorns, intricate outfits, silver ornements,  looking the city under, dark cover, adventures, a trip to tomorrow era, hoodie, looking at camera, graphic novel, vibrant, vivid life, hero pose, dutch angle, detailed black eyes, ligne claire, retro art nouveau style"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/9a9665b9-1360-4d5e-8ce6-ec7cdd6997a2/width=450/36502-2647001610-rogue,%20dark%20fantasy,%20upper%20body,%20traditional%20native%20pale%20suede%20leather%20outfits,%20megalopole,%20dystopic,%20cropped%20framing,%20cinematic.jpeg",
        description = "rogue, dark fantasy, upper body, traditional native pale suede leather outfits, megalopole, dystopic, cropped framing, cinematic, graphic novel illustration, grungy, intricate textures, future background"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/bcfdd6ac-35ff-40ba-8533-c805c97b0650/width=450/36493-2232813245-Paris%201900,%20Dark%20Fantasy,%20_CROW%20MAN_,%20Newspaper%20title,%20uncanny%20ugly%20hero,%20dark%20cover,%20adventures,%20a%20trip%20to%20tomorrow%20era,%20graphi.jpeg",
        description = "Paris 1900, Dark Fantasy, \"CROW MAN\", Newspaper title, uncanny ugly hero, dark cover, adventures, a trip to tomorrow era, graphic novel, vibrant, vivid life, hero pose, dutch angle, detailed black eyes"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/d9d587bf-1ead-41f4-8d5f-ddb7c4ada546/width=450/36651-1452427885-Sea,%20the%20wave,%20a%20woman,%20graphic%20novels.jpeg",
        description = "Sea, the wave, a woman, graphic novels"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/15d7a153-44a0-48f1-ad2b-b23e7f63e620/width=450/36701-3903895185-Sea,%20the%20fishboat,%20a%20woman,%20graphic%20novels.jpeg",
        description = "Sea, the fishboat, a woman, graphic novels"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/f282925e-d55b-4591-b361-50dd5534d1b5/width=450/36741-3481780087-a%20page%20from%20a%20comic%20book,%20portrait%20of%20a%20old%20man%20waliking%20in%20the%20street%20,%20minimalist%20sketching,%20(murder%20in%20snow,%20a%20old%20man%20%20visit.jpeg",
        description = "a page from a comic book, portrait of a old man waliking in the street , minimalist sketching, (murder in snow, a old man  visiting old ghost town , strong face:1.4), detailed action, farming life in France, eerie, low key, comic panel layout, multiple point of views, close view, aerial view, large view, collage,( cows, grange:0.4), abandonned cars, curious farm workers, low lighting, dark fantasy, muted men, white inking, hard contrast, colored sketch"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/45f180bd-3d96-48e9-ba5f-69a57ba0f428/width=450/36757-860973243-science%20fiction%20theme,%20strange,%20sewers%20of%20Paris%201900,%20dark%20mood,%20low%20lighting,%20robotic%20orphans%20adventures,%20hero%20pose,%20detailed%20m.jpeg",
        description = "science fiction theme, strange, sewers of Paris 1900, dark mood, low lighting, robotic orphans adventures, hero pose, detailed muted face, peeling face paint, steampunk technology,  cosy place for robots orphans, warm fire place, shanti basecamp, intricate art nouveau buildings, the hidden place under the old bridge, graphic novel, vibrant, inked"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/e61d2e7b-1cd0-4737-8eb7-bba4b67e684d/width=450/36765-3706935489-cinematic%20view,%20wide%20angle,%20an%20seated%20man%20with%20a%20dark%20green%20jacket%20%20talking%20to%20a%20young%20asian%20lady%20in%20casual%20outfits%20and%20pale%20yel.jpeg",
        description = "cinematic view, wide angle, an seated man with a dark green jacket  talking to a young asian lady in casual outfits and pale yellow jacket, long hairs, he eat a sandwich, detective office with old telefon , some books and cases folders, close view , casual, daily life, graphic novels, vibrant,( style by  jenny saville, style by lucian freud:1), dark light, hard shadows, precise, delicate, ligne claire, sketch"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/7ceb07f8-926a-49a7-a66d-f8804c56ed81/width=450/36918-610771954-a%20page%20from%20comics,%20two%20people,%20red%20dead%20redemption%20meeting%20with%20indians%20Cheyenne,%20dark%20cowboy%20face,%20old%20cowboy,%20unshaved,%20beaut.jpeg",
        description = "a page from comics, two people, red dead redemption meeting with indians Cheyenne, dark cowboy face, old cowboy, unshaved, beautiful cheyenne woman, a trip to tomorrow era, aftermath,standing,  face detail, close view, cinematic, graphic novel, vibrant, highly detailed, black gradients, diligence on background, outline, detailed textures, sketch, drawing, multiple views, detailed brown eyes"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/dd69cb96-0cb0-4a54-95ef-ced5a7fb403a/width=450/37079-1570839111-cat,%20graphic%20novel%20illustration.jpeg",
        description = "cat, graphic novel illustration"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/dbce8792-38b3-4087-869e-acdfbd3f5c30/width=450/36261-1458205241-war%20assault,%20colored%20inked,%20comics.jpeg",
        description = "war assault, colored inked, comics"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/be089770-35f7-4725-99fa-0f1411dd6597/width=450/36182-598518295-cheyenne,%20detailed,%20sharp,%20HD,%20HDR,%20best%20quality,%20best%20resolution,%202D,%20colored%20Graphic%20Novel%20illustration,%20By%20Gibrat,%20cross%20hatc.jpeg",
        description = "cheyenne, detailed, sharp, HD, HDR, best quality, best resolution, 2D, colored Graphic Novel illustration, By Gibrat, cross hatching"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/6377c401-2b17-4e0a-ad55-d578ccdd5d8d/width=450/36177-1514320760-graphic%20novel%20illustration%20(vibrant_0.7)%20dark%20comic%20book%20ink%20style,%20A%20mountain%20medieval%20watchmen%20warrior%20hermit%20in%20a%20fur%20and%20bon.jpeg",
        description = "graphic novel illustration (vibrant:0.7) dark comic book ink style, A mountain medieval watchmen warrior hermit in a fur and bone intricate ensemble, stands at the peak, communicating with the spirits of nature, mongolian background, Dark and gritty, epic scene, dramatic lighting"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/738305ab-56d9-496c-9269-d0f0196d6814/width=450/36166-1679146659-Sea,%20the%20fishboat,%20a%20woman,%20graphic%20novels.jpeg",
        description = "Sea, the fishboat, a woman, graphic novels"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/4cdd64fc-498d-460a-aeb0-993c95177336/width=450/36158-4265094781-woman%20cheyenne,%20High%20contrast,%20minimalistic,%20colored%20black%20and%20grungy%20white,%20stark,%20dramatic,%20graphic%20novel%20illustration,%20cross.jpeg",
        description = "woman cheyenne, High contrast, minimalistic, colored black and grungy white, stark, dramatic, graphic novel illustration, cross hatching, seminole war, traditional outfits, landfields"
    )
)

val Pony_Diffusion_XL_Url_Examples = listOf(
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/a51d187c-b96c-4ca6-9f4b-40ac2ce33063/original=true,quality=90/00205-4245040871.jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, score_5_up, score_4_up, rating_suggestive, Millie from Helluva Boss"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/da94cb58-2410-4ab1-b0cd-17d76ee0bbe0/original=true,quality=90/00097-878055085.jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, score_5_up, score_4_up, rei ayanami from evangelion hugging asuka langley from evangelion, duo"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/2bd1880d-309d-41f6-93de-57e401a19742/original=true,quality=90/00433-1568710691.jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, score_5_up, score_4_up, score_9, score_9, sfw, (solo), anthro, male, protogen, high detailed fur, smile"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/410afe7a-13e1-479f-9420-f82277606f41/original=true,quality=90/00079-290733181.jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, score_5_up, score_4_up, frankie foster from Foster's Home for Imaginary Friends holding a cup of coffee in a coffee shop"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/07d7eba1-e02f-462c-ad77-a5e91c257a3c/width=450/00405-4054921432.jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, score_5_up, score_4_up, score_9, a photo of feral pony plushie standing on a real table"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/4b1d4162-4d60-4309-8644-01bd3e35cf57/width=450/00388-2180310718.jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, score_5_up, score_4_up, score_9, feral, future twilight sparkle, eyepatch"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/2134a392-39f6-4127-bba8-3c7d3cbb4f30/width=450/00044-1029115117.jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, score_5_up, score_4_up, anthro mouse gadget hackwrench, blonde hair"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/878ee113-8db8-4155-8967-4f77cfc4ab8c/width=450/00345-2679842406.jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, score_5_up, score_4_up, rating_safe, minimalist lineless art, pony, fluttershy, (wearing a green hoodie)"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/dd8d8a1d-5106-411d-a7ba-996d77d6ff0e/width=450/00056-4212032008%20(1).jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, score_5_up, score_4_up, source_furry, beautiful female anthro shark portrait, dramatic lighting, dark background"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/227f8314-1d23-47f6-a0a9-64c2f99b221d/width=450/00166-3777620754.jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, score_5_up, score_4_up, rating_safe, Zipp Storm, ((cute, little, fuzzy pony, fur)), (high quality, detailed, beautiful), shiny, adorable face, detailed beautiful eyes, diadema, sunlight, realistic, outstanding, countershading, detailed soft lighting, ear fluff, hoof on face, cinematic vintage photography"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/7a167c45-98c0-4bb2-a752-d0c2f59416bb/width=450/00319-1401169781.jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, score_5_up, score_4_up, pixel art, toriel from undertale"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/f1d3824f-5dae-4139-86cc-ad755fb93463/width=450/00310-3475198511.jpeg",
        description = "score_9, score_8_up, score_7_up, score_6_up, score_5_up, score_4_up, feral pony, show accurate"
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/ca18d37e-be8b-4a32-99d4-24e25b061a97/width=450/tmpwcaemsmp.jpeg",
    ),
    UrlExample(
        url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/4790674c-e16d-4dc7-b384-af4381fcfa3f/width=450/ponydiffwall2.jpeg",
    )
)



