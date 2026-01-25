package org.yassineabou.llms.feature.imagine.data.model

import androidx.compose.runtime.Immutable
import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_flux
import llms.composeapp.generated.resources.ic_gemini
import llms.composeapp.generated.resources.ic_llm7
import llms.composeapp.generated.resources.ic_openai
import llms.composeapp.generated.resources.ic_sdxl_turbo
import llms.composeapp.generated.resources.ic_seedream


@Immutable
data class ImageModelSection(
    val title: String,
    val subtitle: String,
    val models: List<ImageModel>
)

@Immutable
object ImageGenModelList {

    val allModels: List<ImageModel> by lazy {
        highEfficiencyModels + mediumEfficiencyModels + premiumModels
    }

    val defaultModel: ImageModel by lazy {
        highEfficiencyModels.first()
    }

    /**
     * High Efficiency Models: 3,000+ images per pollen
     */
    val highEfficiencyModels = listOf(
        ImageModel(
            title = "Flux Schnell",
            description = "Fast high-quality image generation. ~5,000 images/pollen 🔥",
            image = Res.drawable.ic_flux,
            modelName = "flux",
            efficiency = "~5K/pollen"
        ),
        ImageModel(
            title = "Z-Image Turbo",
            description = "Fast 6B Flux with 2x upscaling. ~5,000 images/pollen 🔥",
            image = Res.drawable.ic_llm7,
            modelName = "zimage",
            efficiency = "~5K/pollen"
        ),
        ImageModel(
            title = "SDXL Turbo",
            description = "Single-step real-time generation. ~3,300 images/pollen",
            image = Res.drawable.ic_sdxl_turbo,
            modelName = "turbo",
            efficiency = "~3.3K/pollen"
        ),
        ImageModel(
            title = "NanoBanana",
            description = "Gemini 2.5 Flash Image generation. ~33K images/pollen",
            image = Res.drawable.ic_gemini,
            modelName = "nanobanana",
            efficiency = "~33K/pollen"
        )
    )

    /**
     * Medium Efficiency Models: 25-125 images per pollen
     */
    val mediumEfficiencyModels = listOf(
        ImageModel(
            title = "GPT Image 1 Mini",
            description = "OpenAI's image generation model. ~125 images/pollen",
            image = Res.drawable.ic_openai,
            modelName = "gptimage",
            efficiency = "~125/pollen"
        ),
        ImageModel(
            title = "FLUX.2 Klein 4B",
            description = "Fast image generation & editing. ~125 images/pollen",
            image = Res.drawable.ic_flux,
            modelName = "klein",
            efficiency = "~125/pollen"
        ),
        ImageModel(
            title = "FLUX.2 Klein 9B",
            description = "Higher quality generation & editing. ~83 images/pollen",
            image = Res.drawable.ic_flux,
            modelName = "klein-large",
            efficiency = "~83/pollen"
        ),
        ImageModel(
            title = "Seedream 4.0",
            description = "ByteDance ARK, better quality. ~33 images/pollen",
            image = Res.drawable.ic_seedream,
            modelName = "seedream",
            efficiency = "~33/pollen"
        ),
        ImageModel(
            title = "FLUX.1 Kontext",
            description = "In-context editing & generation. ~25 images/pollen",
            image = Res.drawable.ic_flux,
            modelName = "kontext",
            efficiency = "~25/pollen"
        ),
        ImageModel(
            title = "Seedream 4.5 Pro",
            description = "ByteDance ARK 4K, Multi-Image. ~25 images/pollen",
            image = Res.drawable.ic_seedream,
            modelName = "seedream-pro",
            efficiency = "~25/pollen"
        ),
        ImageModel(
            title = "GPT Image 1.5",
            description = "OpenAI's advanced image generation. ~31 images/pollen",
            image = Res.drawable.ic_openai,
            modelName = "gptimage-large",
            efficiency = "~31/pollen"
        )
    )

    /**
     * Premium Models: Highest quality
     */
    val premiumModels = listOf(
        ImageModel(
            title = "NanoBanana Pro",
            description = "Gemini 3 Pro Image (4K, Thinking). ~8 images/pollen ⭐",
            image = Res.drawable.ic_gemini,
            modelName = "nanobanana-pro",
            efficiency = "~8/pollen"
        )
    )

    /**
     * Grouped models for UI display
     */
    val groupedModels: List<ImageModelSection> by lazy {
        listOf(
            ImageModelSection(
                title = "🔥 Best Value",
                subtitle = "3,000+ images per pollen",
                models = highEfficiencyModels
            ),
            ImageModelSection(
                title = "⚡ Balanced",
                subtitle = "25-125 images per pollen",
                models = mediumEfficiencyModels
            ),
            ImageModelSection(
                title = "⭐ Premium",
                subtitle = "Highest quality",
                models = premiumModels
            )
        )
    }

    val inspiration = listOf(
        UrlExample(//1
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/cc242d6c-f960-4274-aa1d-f22a71e705ef/original=true,quality=90/2DD7DD35D931B2293D7B08F39CC371.jpeg",
            prompt = "AtomicHeartTwinsCosplay in absolute darkness, profoundly no light, holding black-pink heart shaped orbstaff, pointed hat, translucent skin, Describe the captivating scene captured in the vintage photograph featuring a Bedouin artist skillfully swallowing a massive sword in the style of Final Fantasy, amidst a mesmerized audience. Provide details about the artist's attire, the sword's intricate design, and the expressions of the onlookers as they witness this extraordinary performance., amazing quality, masterpiece, best quality, hyper detailed, ultra detailed, UHD, perfect anatomy, portrait, dof, hyper-realism, majestic, awesome, inspiring, closeup, an weathered outworn old Fantasy cape, smooth, Closeup, by Dring, rust paint peelz, atmospheric haze, cinamatic composition, soft shadows, national geographic style"
        ),
        UrlExample( //2
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/db5208dd-da08-40c7-8ed4-bd67508740ac/original=true,quality=90/JQR7RTW5AS505JA2PZBJCC6CQ0.jpeg",
            prompt = "A close-up of a face adorned with intricate black and blue patterns. The left side of the face is predominantly yellow, with symbols and doodles, while the right side is dark, featuring mechanical elements. The eye on the left is a striking shade of yellow, contrasting sharply with the surrounding patterns. The face is partially covered by a hooded garment, cinematic_1940s cinematic_octane"
        ),
        UrlExample( //3
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/27e77f3a-29c4-4cab-bcde-dd9eab587000/original=true,quality=90/00001-3118275117-best%20quality,%20(photorealistic_1.4),%201girl,%20(black%20shirt_1.2),%20%20(grey%20long%20pants_1.2),(white%20suspenders_1.4),%20perfect%20female%20figu.jpeg",
            prompt = "best quality, (photorealistic:1.4), 1girl, (black shirt:1.2),  (grey long pants:1.2),(white suspenders:1.4), perfect female figure, full body, from back, white background, short hair, slim"
        ),
        UrlExample( // 4
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/402ed7f8-ba25-421d-a68a-b11ae31af91f/original=true,quality=90/00125-282176597.jpeg",
            prompt = "linquivera,  Ink illustration, (anime:0.5), brown tones, aged black red paper, inkpunk, moonlight,surreal, a ghost standing on a boat in swampy wetlands, (at a distance), Will-o'-the-wisp, moonlit, lonely, solitude, windy, tall trees, willows, willowy,  OverallDetail, extremely detailed, UHD,(long exposure , dystopian but extremely beautiful:1.4),<lora:- SDXL - letitflrsh_let_it_flourish_V1.0:0.8> ,<lora:Cute_Collectible:1>, <lora:XL_boss_battle:0.6>  <lora:add-detail-xl:1>,  <lora:Concept Art Twilight Style SDXL_LoRA_Pony Diffusion V6 XL:1>,  <lora:MJ52:1>"
        ),
        UrlExample( //5
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/d2cf2d81-92de-4b3b-8fe0-492ae1044d34/original=true,quality=90/garden_of_the_fox-artsy_vibe-flux-fp8-20250324121908.jpeg",
            prompt = "famous artwork, alcohol ink and watercolor on traditional washi paper, in a secluded, moonlit garden deep within an ancient forest and bathed in silver moonlight, a mythical silver-furred kitsune fox spirit sits regally upon a quaint wooden bridge arching over a tranquil koi pond, its presence both enchanting and enigmatic, its nine resplendent fox tails unfurl in a grand, mesmerizing display, fanning out behind it like an ethereal peacock wheel, each tail flowing like silk, shimmering with delicate strands of celestial light, and tipped with a faint, ghostly luminescence, its magical golden eyes glow intensely, casting an enchanting aura as they pierce the night. the scene is dreamlike, filled with cascading wisteria, intricately detailed herbs and wildflowers, and delicate misty fog drifting through the air, a few glowing will-oâ\u0080\u0099-wisps float gently around the kitsune, illuminating the moss-covered stones and the mirror-like koi pond beneath with their soft, mystical glow. the kitsuneâ\u0080\u0099s tails dominate the composition, arranged in an elegant, symmetrical arc, their delicate, flowing texture highlighted by the contrast of moonlight and shadow, the atmosphere is surreal and enchanting, with a color palette of mint green, teal, deep indigo, ethereal blues, and soft lavender, golden accents from the kitsuneâ\u0080\u0099s piercing gaze and the floating wisps create a striking contrast against the cool, dreamy hues, petals from the wisteria drift lazily through the air, caught in a soft breeze, their descent mirrored in the rippling pond, where the kitsuneâ\u0080\u0099s spectral reflection appears even more otherworldly. a cinematic, painterly shot, slightly elevated angle, capturing the entire mystical landscape in breathtaking detail, intricate hand-painted textures and masterful use of light and shadow create a delicate balance between whimsy and haunting beauty, atmospheric fog softens the edges of the scenery, lending an otherworldly, timeless quality, the entire scene is rendered in handcrafted alcohol ink and watercolor, with soft, flowing brushstrokes, infused with a sense of ancient magic and mythical legends. inspired by studio ghibli, brian froud, and kay nielsen, accurate reflections, ovg, studio ghibli dark fairytale, <lora:style-dark_ghibli-flux-by_arsmachina:0.8> <lora:faetastic_details-flux-by_faeia:0.64>"
        ),
        UrlExample( //6
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/4c0ccb9b-8426-4e33-b4fe-bbca293efc32/original=true,quality=90/Mazz_Earth_1440.jpeg",
            prompt = "Female ninja on the dragon's head. She is holding a ninja parachute made of dragon leather in her hand. Created using Stable Diffusion and Photoshop."
        ),
        UrlExample( //7
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/c53c6b0f-ab0f-4970-8911-b9bfd66e6f21/original=true,quality=90/00011-2261475806.jpeg",
            prompt = "score_9, score_8_up, score_7_up, score_6_up, 1girl, beautiful, white hair, long hair, bangs, fair skin, big breasts, leather armor, sword, forest, dappled sunlight, upper body, looking at viewer"
        ),
        UrlExample( //8
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/afcab5fa-e580-489a-9ea8-bca23534be08/original=true,quality=90/00031-174790514.jpeg",
            prompt = "realistic, solo, cat, cute, sleeping, light_particles, nature, mountain, depth of field, the best quality, amazing quality, high quality, masterpiece,"
        ),
        UrlExample( //9
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/dcdc3cc5-f7f7-4eba-9901-9116eeed6203/original=true,quality=90/00095-2025-02-26-NC-IL.jpeg",
            prompt = "<lora:ck-shadow-circuit-IL:0.78>, masterpiece, best quality, good quality, very aesthetic, absurdres, newest, 8K, depth of field, focused subject, close up, stylized, in gold and neon shades, wabi sabi, 1girl, rainbow angel wings,  looking at viewer, dynamic angle, from below, from side, relaxing  <lora:MoriiMee_Gothic_Niji_Style_Illustrious_r1:0.45> <lora:ck-nc-cyberpunk-IL-000011:0.4> <lora:ck-neon-retrowave-IL:0.8>"
        ),
        UrlExample( //10
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/e0e11f20-d392-4703-a708-600828585251/original=true,quality=90/image_00035_.jpeg",
            prompt = "The image is a digital illustration in a detailed, vibrant, high contrast, semi-realistic art style with 90s retro futuristic anime elements without noise or comic halftone effect. The subject is a woman with dark shoulder length hair and green eyes. Her face showing a serious expression. The theme of her body is a vivid red. Her body is slender and athletic. Her body is cyborg with mechanical parts integrated into it. Her arms and chest are fully mechanical. she is wearing a red futuristic, cybernetic, high-tech suit with a vivid red color, reminiscent of military or space gear. the background features a greenish, (dark), abandined, industrial setting with metallic pipes and machinery, creating a sense of a dystopian or sci-fi environment. The compostition and the poses are dynamic. The composition is dominated by cool tones, with a muted, slightly gritty texture that enhances the gritty, futuristic atmosphere."
        ),
        UrlExample( //11
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/079b552b-9ef8-419d-e104-82b2a6be4400/original=true,quality=90/295008.jpeg",
            prompt = "(RAW photo, best quality), (realistic, photo-realistic:1.3), masterpiece, an extremely delicate and beautiful, extremely detailed, CG, unity , 2k wallpaper, Amazing, finely detail, light smile, extremely detailed CG unity 8k wallpaper, huge filesize, ultra-detailed, highres, absurdres, soft light, (((medium hair:1.3), short bang, pink hair, floating hair novafrogstyle)), beautiful detailed girl, detailed fingers, extremely detailed eyes and face, beautiful detailed nose, beautiful detailed eyes, long eyelashes, light on face, looking at viewer, (closed mouth:1.2), 1girl, cute, young, mature face, (full body:1.3), ((small breasts)), realistic face, realistic body, beautiful detailed thigh, (ulzzang-6500-v1.1:0.8), <lora:koreanDollLikeness_v15:0.4>, business suit, cross-laced clothes, collared shirt, open clothes, in office, detailed office, open cardigan, black thighhighs, miniskirt, black underwear, unbuttoned shirt,"
        ),
        UrlExample( //12
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/0537ea36-62dd-4aab-8884-6da0e928e303/original=true,quality=90/00067-2768675309.jpeg",
            prompt = "a group of rats in the nursing home gather around a table for a game of cards, with one rat wearing a monocle and another puffing on a cigar. The rendering style is reminiscent of a classic oil painting.  <lora:add-detail-xl:1>  <lora:MJ52:0.5>"
        ),
        UrlExample( //13
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/9f50cbc3-98f4-4eb1-8bf6-34b40b30ad41/original=true,quality=90//WV4JPNYT4HVGJGAEZYR9535490.jpeg",
            prompt = "nistyle, in the style of ck-mgs, intricate linework with expressive contrasts, soft lighting with dynamic highlights, image of a dusty desert with a 1930s female explorer, blonde hair, safari hat and jacket, standing looking up at an Egyptian pyramid, silhouetted at sunset, sparse clouds"
        ),
        UrlExample( //14
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/2eb7f731-4136-4295-a0f4-9e140c3a6c96/original=true,quality=90/MH89TW2FX6AC87JC9JH7HKC6Z0.jpeg",
            prompt = "A humanoid (black cat) wearing red samurai armor. He is wearing a white headband with the Japanese rising sun on it. Holding a katana He is in a field. Full body Cinematic lighting, volumetric lighting, 8k quality"
        ),
        UrlExample( //15
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/62927ff3-ee08-41d5-8532-b9cdb221a54b/original=true,quality=90/RavenScript%20.jpeg",
            prompt = "1girl, arm up, bangs, black background, blood, closed mouth, covered eyes, dress, floating hair, greyscale, hooded cloak, long hair, long sleeves, looking at viewer, monochrome, one eye covered, simple background, solo, stuffed toy, thorns, witch hat <lora:RavenScript :1>"
        ),
        UrlExample( //16
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/0b977657-c60e-4a6d-9ffa-b81d3e90fa37/original=true,quality=90//JNEPB89TBE1WGCHTFEEJTQDZE0.jpeg",
            prompt = "in the style of ck-mgs, nistyle, Special Ink-drawing mode, intricate linework with expressive contrasts, Inkplash art on rice paper, sepia, henna , Silhouette Art, magnificent, inksplash closeup portrait of stunning japanese female sc-fi soldier, helmet, mirrored visor reflecting jungle environment"
        ),
        UrlExample( //17
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/bc6061bd-5bae-408a-9718-84232693d223/original=true,quality=90//7G3C340K5EEQ6TRYQJNHVHKDD0.jpeg",
            prompt = "in the style of ck-mgs, nistyle, Special Ink-drawing mode, intricate linework with expressive contrasts, Inkplash art on rice paper, sepia, henna , Silhouette Art, magnificent, inksplash closeup portrait of Chinese woman punting, sunset, calm lake, redlection"
        ),
        UrlExample( //18
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/4cea4d6e-8cfe-4f68-ac9b-69a441f5d8f2/original=true,quality=90/ComfyUI-ExtraMD-Large__00075_.jpeg",
            prompt = "yfg-sm0rg, Bask in the realm of abstract expressionism as a mysterious figure cloaked in a vibrant red shawl steps into view against an ebony void. Her head hangs low, enveloped in a cloud of bewitching curiosity. In stark contrast with her crimson surroundings, crisp white lines dance alongside her form, accentuating each delicate curve as if painted by an ethereal brushstroke. A sense of profound contemplation radiates from within as she stands on the edge of a transformative moment, encapsulated in a timeless swirl of emotions unraveling before our eyes. Let illuminate this striking canvas through the melding of bold colors and expressive movement, forever immortalizing this introspective scene of raw passion and introspection."
        ),
        UrlExample( //19
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/18612c96-c21c-4bd7-aef0-21b9b57f4b7e/original=true,quality=90/250320070218_Upscaled_Detailed_00002_.jpeg",
            prompt = "(atmosphere, masterpiece, high_quality, absurdres, very aesthetic, blurry_background, grainy_texture, film_grain, light particles textured clothing, textured hair, painterly, watercolor_\\(medium\\), colored_pencil_\\(medium\\), blending) (w1tchyc0ttagec0re, petting fox in foreground, 1girl, solo, long_hair, blush, looking_at_viewer, smile, open_mouth, brown_hair, photoshop_(medium), shirt, brown_eyes, green_eyes, jacket, ponytail, flower, :d, food, teeth, indoors, cup, window, mask, night, chair, table, high_ponytail, bottle, plant, bowl, lantern, mug, potted_plant, mask_on_head, fox_mask, coffee, shelf, kitchen, jar, shop, mask_removed, counter, inuyasha)"
        ),
        UrlExample( //20
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/b2aed23d-92ee-4ff9-88a2-386685d51d99/original=true,quality=90/DY4BYFHDSZ5NC651KMW19XQCX0.jpeg",
            prompt = "A apoclypse surreal cyberpunk setting under a gritty bleaked beige and blue-turqoise misty desert sunset, a Closeup Portrait of an eerie horned hkgod sci-fi shinobi nun-scout with billowing octopus backpack in a sandstorm, their holographic crystalline mask glinting like shattered rainbows. Iridescent black amber teal energy veins pulse across their form. The atmosphere is thick with heat distortion, glowing particles floating in the air. dark atmospheric lighting, white cybernetic armor. Wielding a massive sss great sword with intricate translucent engravings, hknuns"
        ),
        UrlExample( //21
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/5602f0eb-82ad-42eb-a536-a5c720e011cf/original=true,quality=90/ComfyUI_00002_390.jpeg",
            prompt = "A strikingly symbolic surreal composition portraying a single tree split into two contrasting halves, forming the profile of a human face, where one side is barren and lifeless while the other thrives with lush greenery. The left half of the image presents a bleak dystopian landscape, filled with towering smokestacks belching thick, dark clouds into the sky, a sea of overflowing garbage bags piled beneath, and a cracked, ashen road stretching endlessly. The skeletal branches of the tree mirror the decay, devoid of leaves, twisted and lifeless, blending into the smog-filled atmosphere. On the right side, a vibrant utopian paradise emerges, with rolling green fields stretching toward lush forested mountains, illuminated by a soft, golden glow. The tree here is full of life, its rich green foliage thriving under a bright blue sky, where a radiant rainbow arcs gracefully, casting a hopeful aura over the pristine natural landscape. The stark contrast between industrial destruction and environmental harmony conveys a profound visual metaphor of human impact, nature’s resilience, and the choice between devastation and renewal in a hyper-detailed, thought-provoking surrealist art style. <lora:FluxDFaeTasticDetails:0.25> <lora:fechin-FLUX:0.75>"
        ),
        UrlExample( //22
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/680922a8-d5bd-4cd7-836d-eb6ae1042b8e/original=true,quality=90/2025-01-27_21-23-06-ponyDiffusionV6XL_v6StartWithThisOne-1197238889.jpeg",
            prompt = "score_9, score_8_up, score_7_up, monster girl, llamia, slit pupils, green eyes, horns, kunoichi, long hair, action pose, forest,"
        ),
        UrlExample( //23
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/45428a1a-edb8-4375-8ffa-f5b33387f065/original=true,quality=90/CM7FMWEJQGVC3J46AAQQQGGA10.jpeg",
            prompt = "A neon samurai, his body adorned with holographic armor that shifts in color with every step, walks through the neon-drenched streets of a cybernetic Tokyo. His katana, humming with digitized energy, slices through the rain, leaving a trail of flickering blue data in its wake. His helmet bears no visor, only an ever-changing LED mask that reflects the emotions of a warrior who has forgotten his own face. , pinkpixelicious, aidmamj6.1,"
        ),
        UrlExample( //24
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/033ae5e2-6fde-40f6-9597-84397db13fec/original=true,quality=90/06.jpeg",
            prompt = "elegant woman, futuristic explorer, flowing high-fashion attire, exploring an alien world, floating monoliths, mysterious energy, surreal atmosphere, dreamlike lighting, intricate alien architecture, glowing glyphs, Remedios Varo inspired, sci-fi fantasy, cinematic composition, ultra-detailed textures, hyper-realistic, ethereal mood, cinematic, rich film texture, high-contrast, deep tonal range, --ar 1:2 --quality 2 --style raw --p --stylize 80"
        ),
        UrlExample( //25
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/c1334e29-105b-4396-91d7-be57299e25f2/original=true,quality=90/20250324075023%201156582717%20by%20Joe%20Madureira%20and%20Johan%20Messely%20in%20the%20style%20of%20Peter%20Holme%20III,%20silhouette%20_lora_silhouette_2.00_.jpeg",
            prompt = "by Joe Madureira and Johan Messely in the style of Peter Holme III, silhouette <lora:silhouette:2.00>"
        ),
        UrlExample( //26
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/c52d4c1e-bd3d-4c8f-9b21-6556bd60a5d6/original=true,quality=90/NKA8BDHMW3W3X3DH58MX3GVJ50.jpeg",
            prompt = "A close-up portrait of a demon woman with molten lava-like cracks on her skin, her fiery eyes glowing intensely. Her horns are jagged and sharp, resembling molten stone, and her expression is one of wrath and destruction, with flames flickering around her face, anime style, women, demon women, cute face, perfect body, perfect smile, REALNIME, Dark Neon Fantasy."
        ),
        UrlExample( //27
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/c39338b4-fceb-48bc-8227-fdeb2f57344b/original=true,quality=90/02063-1522453998-masterpiece,%20best%20quality,%20absurdres,%20simple%20background,%20white%20background,%20_lora_Depresso_IL-000008_1_%20depresso,%20blue%20body,%20purp.jpeg",
            prompt = "masterpiece, best quality, absurdres, simple background, white background, <lora:Depresso_IL-000008:1> depresso, blue body, purple hair, black tail, pink eyes, solo, one eye closed, ikea shark, hugging ikea shark, chibi, standing, sparkle"
        ),
        UrlExample( //28
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/58f848f7-ed07-4155-b207-c2cd9893a45a/original=true,quality=90/00274-761478725.jpeg",
            prompt = "InkSplash,splatter ink,1boy,solo,male focus,red hair,sandals,teeth,might guy \\(naruto\\),fire,full body,aura,red eyes,spiked hair,pants,jacket,belt,outstretched arms,clenched teeth,scar,long sleeves,looking at viewer,<lora:InkSplash1llust:0.8>,InkSplash,splatter ink, masterpiece,best quality,ultra-high resolution,exquisite ink brushwork,dynamic and fluid ink strokes,cinematic ink wash aesthetics,highly detailed brush textures,expressive and spontaneous ink flow,fine-grained paper texture integration,masterful balance of bold and delicate strokes,deep atmospheric ink diffusion,controlled ink splashes for dramatic effect,refined tonal layering for depth,intricate shading with natural ink wash,seamless blending of wet and dry brush techniques,calligraphic precision in linework,flowing fabric with ink-splattered motion,ultra-detailed character features with artistic stylization,striking and elegant poses,composition with sweeping ink dynamics,rich and immersive artistic atmosphere,ink-diffused lighting for soft yet striking contrast,expressive silhouette dynamics,harmonious balance of black,white,and gray tones,handcrafted stroke realism,fine ink gradients for emotional depth,meticulously layered ink textures,AAA-level traditional ink artistry,painterly elegance with cinematic impact.,black_background,noir_vesper,"
        ),
        UrlExample( //29
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/ca0525a0-4839-4a7c-9eee-caffabaad6b0/original=true,quality=90/%E6%B0%B4%E5%BD%A9%E6%8F%92%E7%94%BB_00477_.jpeg",
            prompt = "A mythical ape 'Xingxing' from Shan Hai Jing straddling glowing geothermal veins, cracked earth revealing flowing magma calligraphy in subterranean cavern, centered watercolor with mineral deposit textures. White-furred primate in seismic listening stance, palms pressed to ground triggering jade-green energy ripples through crystal clusters. Amber eyes projecting holographic mountain ranges onto cavern walls, silver-tipped ears vibrating with tectonic frequencies. Archaic glyphs along limbs pulsing in sync with magma flow, claw trails crystallizing airborne ash into floating seal scripts. Semi-transparent mane absorbing mineral vapors into chromatic halos, xuan paper texture fused with stratified rock layers. Burnt sienna and malachite tones illuminated by cinnabar-lit fissures and azurite crystal growths on shoulder joints"
        ),
        UrlExample( //30
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/459c5d32-19a8-4568-9852-4fc323f6b50a/original=true,quality=90/ComfyUI-ExtraMD-Large__00361_.jpeg",
            prompt = "yfg-F@ce, YFG-Zaat, A neon-lit cityscape at dusk, a vibrant orange-pink sky blending into deep purples, contrasts with modern skyscrapers in shades of glass and steel. A large frog sits perched on a rock in the foreground, its skin a mottled green-brown that blends seamlessly with the urban foliage, while its bright red eyes gleam like tiny rubies in the fading light. The frog's slender legs are folded beneath it, as if in quiet contemplation of the cityscape unfolding before it. In the distance, towering skyscrapers stretch towards the sky, their reflective surfaces glinting softly with a warm, golden light that casts no shadows, only subtle gradations of tone and texture."
        ),
        UrlExample( //31
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/c145c1be-6532-47a6-a436-f5bda14e99b4/original=true,quality=90/Q2B96FK1TDYQDHK32SH2VXRD70.jpeg",
            prompt = "A golden road winds through a misty valley towards towering mountains. The rising sun peeks over the horizon, painting the sky in vivid hues of pink, orange, and gold. Ancient white cherry trees line the path, their blossoms awakening to the dawn's first light, petals glowing with an ethereal radiance. Dew-kissed grass sparkles as sunbeams dance across the landscape. Wispy clouds catch the morning light, transforming into floating islands of color. In the distance, the mountains' peaks emerge from shadow, their crystal spires glinting in the new day's brilliance. As the world stirs to life, luminous flowers unfurl along the roadside, their soft glow harmonizing with the cherry blossoms' increasing luminescence."
        ),
        UrlExample( //32
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/1cc4449f-0ebb-4566-adc8-50001d57ef0e/original=true,quality=90/civchan-ilustmix-v4-illustrious-by_gzees-20250401081101.jpeg",
            prompt = "smooth_quality_illustrious-pos, (realistic:1.2), (very awa:1.2), (masterpiece:1.2), (best quality:1.2), (amazing quality:1.2), (very aesthetic:1.1), high resolution, absurdres, ultra-detailed, hdr, civchan, the cutest girl in the world, wearing a (maid costume:1.1), shoulder length pink hair, heart hands, glowing heart eyes, smiling, glimmer, sparkles, (lots of floating hearts:1.2), looking at viewer, cozy café, rim lighting, crepuscular rays, indoors, table, patrons in the background, food, curtains, circuit board sticking in a strawberry cake, cup of coffee with latte art, chalkboard with restaurant menu and hearts on the wall, little vase with flowers on the table, illuminated dust particles, \"someone\" asked me to add that i was definitely not forced with a knife to write \"cutest girl in the world\" about her, there, done, *sigh*, ovg, artist:moriimee, <lora:maid_cafe-illustrious-by_bolero537:1.0> <lora:civchan-illustrious-by_civchan:1.0> <lora:style-moriimee_gothic_niji-illustrious-by_tentacles_riders:0.6> <lora:dramatic_lighting_slider-illustrious-by_klaabu:3.0>"
        ),
        UrlExample( //33
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/96058c3d-5ddb-42b9-85c1-ff9139b2aab0/original=true,quality=90/12.jpeg",
            prompt = "samurai girl wearing a kimono with shoulder armor, she's holding a katana glowing with red energy, her long hair is flowing in the wind, she stands in a dojo training for her next battle. (maximum ultra high definition image quality and rendering:3), maximum image detail, maximum realistic render, (((ultra realist style))), realist side lighting, , 8K high definition, realist soft lighting, <lora:FluxMythG0thicL1nes:0.6> <lora:FluxMythR3alisticF:0.5> <lora:RetroAnimeFluxV1:0.6> <lora:OB半写实肖像画V2.0:0.6>"
        ),
        UrlExample( //34
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/5658c4ff-331f-40c3-8932-476af8ba249e/original=true,quality=90/imgmeta_b_19.jpeg",
            prompt = "The image features a close-up of a person with their eyes closed, and vibrant, neon-colored streaks flowing from their eyes down their face and hand. The colors include shades of purple, pink, green, and blue, creating a striking contrast against the subject's skin tone. The person has freckles on their cheeks and nose, adding texture to their complexion. Their hair is dark and appears slightly tousled. The camera captures the subject from a frontal angle, focusing closely on their face and upper body. The lighting is soft and even, highlighting the details of the subject's skin and the vivid colors of the streaks. The light source seems to be positioned in front of the subject, ensuring that the colors are bright and well-defined. Shadows are minimal, maintaining the clarity of the image. The background is completely black, which isolates the subject and makes the colors pop even more. This stark contrast draws all attention to the subject and the artistic elements of the image. There are no other objects or distractions in the background, keeping the focus solely on the person and the neon streaks. The composition is centered, with the subject's face taking up most of the frame. The hand gently touching the mouth adds an element of intrigue and emotion to the image. The lines of the neon streaks lead the viewer's eye across the face and down the hand, creating a dynamic flow within the composition. The overall effect is both artistic and emotive, evoking a sense of mystery and creativity. BREAK anime, d13s3lp2nk, bboil artist style, OBdonman, Anime art, <lora:FLUX\\Anime Art V3:0.2>, <lora:FLUX\\Synesthesia:0.2>, <lora:FLUX\\RetroAnimeS1.1:0.5>, <lora:FLUX\\Flux-jijia:0.1>, <lora:FLUX\\It's a LoRA for portraits, BUT! actually specialized in backgrounds. v1.0:0.1>, <lora:FLUX\\GLSHS:0.2>, <lora:FLUX\\Dieselpunk Delight - s0_9 g4:0.4>, <lora:FLUX\\OBdonman, anime0style portrait:0.1>"
        ),
        UrlExample( //35
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/e38cb413-a9f1-4e1b-90bc-1d42c4d52e26/original=true,quality=90/(1).jpeg",
            prompt = "Smooth_Quality, solo, paimon \\(genshin impact\\), empty eyes, half-closed eyes, annoyed, star-shaped pupils, halo, smoking cigarette, blowing smoke, close-up portrait, short hair, bangs, hair ornament, hair between eyes, white hair, long sleeves, dress, scarf, cape, thighhighs, spot color, shaded face, black and white, from side, dynamic angle, looking at viewer, crossed arms,   <lora:Mature_Comics_Style_-_NoobAI:1>,"
        ),
        UrlExample( //36
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/9c3becd8-0dbc-407b-a5c8-0f47d14ce1a7/original=true,quality=90/0109.jpeg",
            prompt = "A futuristic eco-city covered in massive vertical gardens,where skyscrapers intertwine with lush greenery. A boy wearing a futuristic outfit adorned with nature-inspired patterns sits on a rooftop platform,overlooking aerial walkways and flowing water features surrounded by vegetation. The golden hues of sunset cast dynamic shadows,creating a harmonious yet mysterious atmosphere.,<lora:kcyberpunk-01:0.8>,kcyberpunk,"
        ),
        UrlExample( //37
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/f04bb7a9-e6ac-4f31-b8b2-ce95c08db81e/original=true,quality=90/0123.jpeg",
            prompt = "First-person view standing atop a rocky hill,looking towards the distant evil city of Mordor. A jagged mountain range frames the scene,with a dark,sprawling city at the center. The Eye of Sauron looms large above the city,radiating an intense,fiery glow that contrasts with the desolate,smoky atmosphere. The foreground has barren,cracked earth and sparse vegetation,while the sky is filled with swirling dark clouds. (vivid style, cinematic),<lora:klordoftherings-01:0.8>,klordoftherings,"
        ),
        UrlExample( //38
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/21cb9dc2-26e8-4af5-b4c4-8d7bc86912b8/original=true,quality=90/00011-2004384211.jpeg",
            prompt = "masterpiece, best quality, amazing quality BREAK zelda1x, tunicleggings, smile, riding epona, white background <lora:Zelda:1>"
        ),
        UrlExample( //39
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/1c4e7587-b093-4f2f-92e2-cc0876571f2a/original=true,quality=90/00469.jpeg",
            prompt = "<lora:lcm-lora-sdxl:1>,<lora:add-detail-xl:1>,<lora:When_Life_Gives_You_Lemons:1>,wl_lemons,a butterfly,"
        ),
        UrlExample( //40
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/907c4d7a-1d3c-4e39-ac36-35e67e21f89c/original=true,quality=90//MNG4KVZPA59PNP681HAMZAM7T0.jpeg",
            prompt = "intricate linework with expressive contrasts, soft lighting with dynamic highlights, a tranquil large lake in the uplands, sakura trees, blossom, mist, dawn, reflection, with mount fuji in the distance, no humans, In the style of ff-td"
        ),
        UrlExample( //41
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/3b67bfa5-3fda-4655-968e-df68ac215fcb/original=true,quality=90/052HA8K3KB48M43AJN3RZW26M0.jpeg",
            prompt = "A Vintage Sci-fi head photo featuring a surreal, abstract Busty woman set against a vibrant, cosmic background. The woman is a sleek, featureless humanoid with a metallic, reflective head that resembles a helmet or mask. The head is a polished, iridescent surface that reflects and refracts light, showing a mesmerizing mix of colors including deep blues, purples, and hints of gold, suggesting a blend of nebulae and cosmic phenomena. The body is clad in a dark, sleek suit, which appears to be made of a material that matches the reflective quality of the head."
        ),
        UrlExample( //42
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/d131f9ec-3a02-41d2-aeaa-642b96e8601e/original=true,quality=90/ComfyUI_2025-04-01_00065.jpeg",
            prompt = "[Base Prompt] 1girl, CivChan, oll13gr4v3, peace sign, grass, squatting, tombstone, pink hair, purple eyes, thigh highs, black boots, grinning,  masterpiece, best quality, amazing detail, high definition // [Regional Prompting] [Region 1] tombstone, logo, civitai logo, C, [Region 2] text, tombstone, RIP, CivBot,"
        ),
        UrlExample( //43
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/a15b19a0-ba97-4b2f-8c21-068235956c18/original=true,quality=90/DNXA9TEQJG6HADWV9P3BQRBBX0.jpeg",
            prompt = "masterpiece, best quality, good quality, very aesthetic, absurdres, newest, 8K, depth of field, focused subject, dynamic angle, side view,  1girl,solo, CivChan, android,  holofoil glitter, faint, glowing, ethereal, neon hair, glowing hair, long hair, purple eyes, futuristic uniform, cleavage, standing looking sweet, stance, coy,  looking at viewer, relaxing, sci-fi"
        ),
        UrlExample( //44
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/0e32388a-f856-43fe-a9c6-9160aa986f03/original=true,quality=90/02062-1984367578.jpeg",
            prompt = "(indoors,indoor,isometric view,side view,tilt-shift lens:2), (fna_style,miniature,miniature,diorama,miniature,diorama,PVC,plastic:2),sunset,winter giant (randomly multi stack up,multi layer of cheese cake cubic vertical:2.5)  is located center, people are climbing with ropes, (in wooden square plate:3) (on table at study:3) <lora:Fantasy_Novel_Art_Style:1> <lora:Rostpunk:0.3>"
        ),
        UrlExample( //45
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/45c2f3da-c558-49d5-9242-357024cacb98/original=true,quality=90/00047-3364518230.jpeg",
            prompt = "masterpiece,best quality,amazing quality, CivChan, purple eyes, pink hair, peeking out, wall, looking at viewer, maid, smirk,medium hair, <lora:CivChan:1>"
        ),
        UrlExample( //46
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/f570fad2-1880-4978-a1cd-8f1e4e6e5494/original=true,quality=90/2025-02-10-181028.jpeg",
            prompt = "very awa,  <lora:748cmSDXL:0.3>, 748cmstyle, <lora:748cm-NoobEPS100-lokr-v03-000080:0.6>, 748cm, <lora:ponyv6_noobV1_2_adamW-000017:0.35>, (taro-k:0.5), (opossumachine:0.3), (maccha \\mochancc\\:0.25), <lora:noobai_ep11_stabilizer_v0.114_fp16:0.4>,  <lora:XXX667:0.75>, xxx667_illu, <lora:114558v4df2fsdf5:0.8>, 15546+456868,  1girl, long hair, black hair, facing away, mechanical wings, mechanical spine,  black hole, from behind,  no lineart, realistic, blurry,  masterpiece, [best quality:0.25], newest, absurdres, very aesthetic, best quality, year 2024, newest,"
        ),
        UrlExample( //47
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/6deb9162-bb67-4109-8242-24ca90d4627f/original=true,quality=90/00099-3309316608.jpeg",
            prompt = "<lora:CivChan:1>, 1girl, civchan, solo, (eating spaghetti), spaghettie hanging from mouth, slurping, Kawaii, wholesome, cute, adorable, blush, sitting at table, nom nom nom, fangs, uwu, excited, happy, ecstatic, giggling, giddy, indoors, purple ribbon, puffy sleeves, Dynamic angle, best quality"
        ),
        UrlExample( //48
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/34271741-5cc9-408e-a765-daf26e91027d/original=true,quality=90/RZ231THVTKJVQ7RS06CHQ3PQV0.jpeg",
            prompt = "masterpiece, best quality, abusrdres, amazing quality, 1girl, solo, CivChan, purple eyes, pink hair, maid, handsomize, meme, beautiful face, lips, meme, parody, inside, house, blurry background, Dutch angle"
        ),
        UrlExample( //49
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/c0ad3c0b-0fae-4107-b93e-11a294939041/original=true,quality=90/8AEJJ0CBKJZFJBN8KJ2XZNCFK0-1.jpeg",
            prompt = "cinna flow, Ornate egg-shaped headpiece, face partially visible through cracked egg shell. Detailed, delicate lace-like patterns and embellishments encircle and decorate the egg-shaped head. Pale skin with light blue eyes, detailed eyelashes and subtle blush. Small, delicate, almost doll-like facial features. Pale-toned lips, serene expression. Pale, light grayish-blue hair, styled intricately with embellishments. Soft, pastel colors, with accents of cool gray and blue. The cracked-egg shell is a light beige color, with intricate details and textures. Delicate pearl-like embellishments and ornate designs incorporated in the shell. Hands gently hold the egg-shaped head. Dark gray background, evoking a dreamlike and ethereal atmosphere. Gothic, surreal art style. Perspective is close-up, focusing on the detailed features and textures. Intricate, detailed decorations on the egg add depth and visual interest. Mood is contemplative and mysterious, with a soft, melancholy tone."
        ),
        UrlExample( //50
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/1ea74dcc-8b5a-443f-850a-56a73e21fd3a/original=true,quality=90/MetaSave_00172_%20(1).jpeg",
            prompt = "A tiny alien snail, its shell made of prismatic, ever-shifting crystal that reflects the light in dazzling colors. Its antennae have tiny glowing orbs at the tips, and its slimy, semi-transparent body leaves behind a faint glowing trail. It tilts its head curiously, examining the person holding it. The background is an alien garden filled with softly glowing plants and bokeh light specks floating like fireflies."
        ),
        UrlExample( //51
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/5a0d24d9-0838-4016-9ae0-6b7e24d55424/original=true,quality=90/Upscaled_Ultimate_ComfyUI_FLUX1_False_372343871558686.jpeg",
            prompt = "A chaotic cityscape bathed in the neon glow of streetlights is torn apart by a wild street race, cars launching off ramps and skidding through intersections at breakneck speed. In the foreground, a tricked-out muscle car with oversized rear tires and flaming exhaust burns rubber, leaving a trail of smoke and sparks. The city skyline behind is shattered, as if the force of the speed has cracked reality itself. A metal skull with a checkered bandana grins wickedly from the hood of the lead car, its empty sockets reflecting the chaos. <lora:aidmaImageUpgrader-FLUX-V0.2:0.5><lora:PinkieFluxProUltraFantasia><lora:FluxDFaeTasticDetails:0.7>"
        ),
        UrlExample( //52
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/922def6d-6110-4706-bdeb-335dc8349a9e/original=true,quality=90/00168-934616762-Happy%20t-rex%20wearing%20an%20apron%20is%20making%20breakfast%20in%20a%20kitchen%20close%20to%20an%20exploding%20volcano,%20the%20t-rex%20is%20happy%20while%20holding%20a.jpeg",
            prompt = "Happy t-rex wearing an apron is making breakfast in a kitchen close to an exploding volcano, the t-rex is happy while holding a pan with some slcies of meat, with a very detailed background, dynamic angle, dust particles,colorful happy scene, <lora:pp-storybook_rank2_bf16:1> ppstorybook, whimsical, fantastical, stylized"
        ),
        UrlExample( //53
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/6f6e3500-a280-492d-bdc4-c39a650cd38e/original=true,quality=90/BGF2BT63KAKS1RVP3N3E645KH0.jpeg",
            prompt = "ppstorybook, The image features a cartoon cat with a fur texture that resembles that of a Persian cat, characterized by its long, bushy tail and thick, white whiskers. The cat has a somewhat grumpy expression, with its eyes narrowed and a slight frown, giving it an angry or frustrated look. Its ears are perked up, and it has a small, pink nose. The cat's arms are raised in a defensive or aggressive posture, with the hands spread apart. The background is plain white, which makes the cat stand out prominently. The text above the cat reads \"Go here, I'll embrace you\"."
        ),
        UrlExample( //54
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/21e4ec2a-01d6-4856-98b6-5adedbd8fb25/original=true,quality=90/4WN2P80B4TBGGQQJNDP68QVTG0.jpeg",
            prompt = "<lora:ck-shadow-circuit-IL:0.78>, Frieren, smiling, holding black cat, enchanted forest,no hand in frame, masterpiece, best quality, good quality, very aesthetic, absurdres, newest, 8K, depth of field, focused subject, close up, stylized, wabi sabi, 1girl,  looking at viewer, dynamic angle, from below, from side, relaxing <lora:MoriiMee_Gothic_Niji_Style_Illustrious_r1:0.45> <lora:ck-nc-cyberpunk-IL-000011:0.4> <lora:ck-neon-retrowave-IL:0.8> , in the style of cksc, in the style of cknc, artist:moriimee, in the style of ck-rw"
        ),
        UrlExample( //55
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/5724edfa-783b-4a5d-8e72-2388cdebfd3c/original=true,quality=90/2024-09-08_090603_Flux_00002_.jpeg",
            prompt = "bmstyle anime \"A human head made of translucent, crystallized material shatters outward, with shards transforming into a burst of vibrant flowers. The flowers are varied in color and species, blooming mid-air as if born from the explosion. Soft light refracts through the crystal fragments, creating a surreal, dreamlike atmosphere."
        ),
        UrlExample( //56
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/91895e3e-ebe7-4094-af06-958b055522d9/original=true,quality=90/Upscaled_Ultimate_ComfyUI_FLUX1_False_1044856319646929.jpeg",
            prompt = "The Matrix, but All Characters are Cats â\u0080\u0093 In the stark, green-tinged office hallway, a sleek black cat in a tiny trench coat leaps gracefully into the air, dodging bullets that streak past in slow motion. Its feline body twists with impossible precision, each bullet leaving a rippling distortion in the air behind it. The suited agentsâ\u0080\u0094tall, imposing tabby cats with dark sunglasses perched on their whiskered nosesâ\u0080\u0094stand at the end of the hall, their paws gripping tiny pistols with perfectly steady aim. The walls are a sterile, fluorescent-lit white, but faint scratch marks and tufts of fur litter the floor, revealing the battle that has already taken place. As the airborne cat completes its mid-air spin, its emerald-green eyes flash with defiance, reflecting the binary code subtly scrolling across the glassy floor beneath it. The scene is frozen in time, a moment of impossible elegance and absurd feline grace. , detailed background  Fantastic lighting. Detailed shadows.intricate details, vivid colors, hyper-detailed, ultra-sharp, , <lora:aidmaImageUpgrader-FLUX-V0.2:0.5><lora:PinkieFluxProUltraFantasia><lora:FluxDFaeTasticDetails:0.7>"
        ),
        UrlExample( //57
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/591c855d-d676-4286-ba2a-482e502e070d/original=true,quality=90/civitai.jpeg",
            prompt = "A tranquil winter garden scene in the meticulous gongbi style, depicting a lone scholar contemplating snowflakes falling on plum blossoms. The composition features delicate white plum flowers blooming on gnarled branches against a backdrop of elegant garden rocks dusted with snow. Rendered with fine mineral pigments on silk, the artwork employs precise brushwork to capture the scholar's contemplative expression and the intricate details of his traditional robes. The scene embodies the Confucian virtue of perseverance through hardship, with the plum blossoms symbolizing resilience and purity. Subtle ink wash techniques create atmospheric depth, with negative space suggesting falling snow. The color palette consists of muted whites, pale pinks, deep browns, and subtle indigo accents. A small red seal mark in the corner bears classical calligraphy, enhancing the scholarly atmosphere of this refined Song dynasty-inspired masterpiece. <lora:ck-shadow-circuit-000021:0.5> <lora:RM_Animefy_v1.0M:0.5> <lora:FluxDFaeTasticDetails:0.5> <lora:flux_semifluid_pigments:0.6> <lora:MushyFunk:0.5> <lora:flux_dev:1>"
        ),
        UrlExample( //58
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/93c19223-1394-47d0-81b1-21e71cae64ad/original=true,quality=90/47CQRT9AESX78ENE8WM7BM2690.jpeg",
            prompt = "cinematic film still, close up, a robot woman stands tall, half-human half machine, amongst an ancient Greek gallery of paintings and marble, religious symbolism, quantum wavetracing, high fashion editorial, glsl shaders, semiconductors and electronic computer hardware, amazing quality, wallpaper, analog film grain, perfect face skin <lora:aesthetic_anime_v1s:1.1>"
        ),
        UrlExample( //59
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/1e8f5c74-acd7-48af-ba23-3a7de14db372/original=true,quality=90/00195-2024-10-30-Neurocore-pastel-Cyberpunk.jpeg",
            prompt = "<lora:ck-nc-pastel-cyberpunk:1>, A digital artwork in the style of cknc, A half-body shot of a beautiful cyborg girl in her futuristic workshop. She has neon pink hair cascading down her shoulders, contrasting sharply with her black lipstick. The top of her face, from under the nose to the forehead, is removed like a mask, revealing a detailed mechanical skull underneath. With intense focus, she uses a glowing soldering iron to carefully repair the cracks on a detached skull, which rests in her other hand. Her exposed robotic eye glows a brilliant blue, illuminating the intricate cybernetic components beneath the skull. The workshop is cluttered with futuristic tools, wires, and neon-lit gadgets, all bathed in vibrant cyberpunk hue, electric pinks, deep blues, and fluorescent greens. The neon light reflects off her sleek metallic parts, casting dynamic shadows, while the scene radiates a blend of gritty industrial and high-tech futurism. The art style is highly detailed, in the vein of futurist anime, capturing the beauty of the cyborg girl and the mechanical complexity of her exposed skull."
        ),
        UrlExample( //60
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/d1417789-9c18-4d99-825c-428f4a3a2a45/original=true,quality=90/civitai.jpeg",
            prompt = "A majestic humpback whale breaches the surface of a shimmering, turquoise Arctic sea, its massive form silhouetted against the ethereal glow of the Northern Lights. Iridescent green and purple auroras dance across the night sky, casting an otherworldly light on the whale's glistening skin. Nearby, a pod of narwhals glides gracefully, their spiraled tusks gleaming in the celestial radiance. Floating ice floes dot the water's surface, reflecting the auroral display and creating a dreamlike mosaic. In the distance, jagged ice cliffs loom, their crystalline faces etched with millennia of history. A curious Arctic fox perches atop a nearby ice chunk, its white fur tinged with the surreal colors of the aurora. Schools of silvery Arctic cod dart beneath the surface, their scales catching flashes of the celestial light show. The scene captures the raw beauty and harmony of Arctic marine life, rendered in stunning 8K detail, with an atmosphere that blends the magical with the majestic. Intricate patterns of refracted light play across the whale's barnacle-encrusted skin, while the auroral glow casts long, ethereal shadows across the icy seascape. <lora:Anime v1.3:0.4> <lora:Illustration_Style_IV:0.5> <lora:flux_dev:1>"
        ),
        UrlExample( //61
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/6fafd4ec-a94f-4814-977a-efa563423039/original=true,quality=90/00296-4091974088.jpeg",
            prompt = "a 18 y.o french pretty modeling,(from_above:1.2),from_side,soft lighting,high resolution,professional grade,RAW photography,evocative composition,Cinematic Lighting,moody lighting,(freckles:0.7),perfect eyes,depth of field,virtual background,eyes_focus,"
        ),
        UrlExample( //62
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/5c5e3a49-bd03-479d-bd0b-0a025ec70b8a/original=true,quality=90/00089-753783114.jpeg",
            prompt = "1girl, action pose, fox_mask, fox fire, mechanical eye, holding sword, school uniform, harness, thigh pouch, silhouette art, glitch art, virtual, colored shadow, geometry, monochrome, local color adaptation, hdr, (disintegration effect:1.4), (chromatic aberration, polarized:1.2), (blurry:1.4), masterpiece, best quality, amazing quality, very aesthetic, absurdres, newest,"
        ),
        UrlExample( //63
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/b2baa4ca-0a05-4091-afd3-2f5b9d549485/original=true,quality=90/13456453.jpeg",
            prompt = "A female warrior of ice and vengeance, the Frostbound Valkyrie emerges from the frozen tundras clad in glistening armor forged from enchanted permafrost. Her braided silver hair carries the chill of eternal winter, and her breath fogs the air with each exhale. Her piercing ice-blue eyes shine with the determination of a thousand battles. In her hand, she wields a colossal frost-encrusted warhammer, its head shimmering with a frozen aura capable of shattering even the strongest defenses. A spectral wolf, composed of swirling snow and ice, stalks at her side, its eyes glowing with a feral light. Wherever she treads, frost blooms and the whispers of fallen warriors drift on the icy wind. <lora:flux.1_lora_flyway_Epic-detail_v2:0.85> <lora:FluxMythAn1meL1nes:0.8> <lora:MoriiMee_Gothic_Niji_Style__FLUX_LoRA_Test_2:0.65>"
        ),
        UrlExample( //64
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/f56da912-0192-48fb-848a-36b04cccadf4/original=true,quality=90/KKEGR9K0YAFV9EKCEVQRC7FXP0.jpeg",
            prompt = "*Channel_42* *It is pitch black; you are likely to be eaten by a Grue* *neon* *glitchy* *drone* *crystaline* *Shutting Down* *Going Dark* *Offline*"
        ),
        UrlExample( //65
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/790f26cf-8f35-425a-85bd-67742f621ca6/original=true,quality=90/19C5038GB55P466ZXBM752V7Z0.jpeg",
            prompt = "Midday in a Greek Island Village The whitewashed terraces of a Greek island village gleam under the relentless midday sun. A woven sun hat, long abandoned, rests on a stone ledge, its brim fluttering in the warm breeze. The deep blue Aegean Sea stretches endlessly below, shimmering beneath the bright sky. Pink bougainvillea cascades down sun-bleached walls, their petals drifting lazily across empty streets. A wooden door stands ajar, revealing only shadows within. The scent of salt and jasmine fills the air, but the village remains utterly still, as if waiting. The scene is bathed in warm golden and orange hues, with soft light reflecting off the rippling water. The foreground features grand buildings with ornate domes and towers, surrounded by lush greenery. A bustling waterfront with small boats and bridges connects various districts, filled with people dressed in elegant attire. The background displays rolling hills and distant mountains under a sky dotted with fluffy clouds. The overall aesthetic combines a painterly impressionism with hyperrealist details, evoking a sense of wonder and vibrant life."
        ),
        UrlExample( //66
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/ed81ad27-3a5e-424d-b163-5d3dc037ac02/original=true,quality=90//4PKXPC2EWP7NXJ6N3T7K31ASG0.jpeg",
            prompt = "in the style of ck-mgs, nistyle, Special Ink-drawing mode, intricate linework with expressive contrasts, Inkplash art on rice paper, sepia, henna , Silhouette Art, magnificent, inksplash image of stunning japanese woman, gold and red cheongsam, sitting in front of tori gate, facing viewer, dappled sunlight"
        ),
        UrlExample( //67
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/6bbe19c4-dacf-4463-9621-e6f55cdc386b/original=true,quality=90/31072143-2067885445-a%20winged%20Valkyrie%20wielding%20a%20weapon,%20splatter%20fashion,%20in%20the%20style%20of%20light%20and%20shadow,%20ink%20illustrations,%20by%20Yoji%20Shinkawa%20%20_l.jpeg",
            prompt = "masterpiece, best quality, newest, absurdres, highres, night, realistic, dim light, dark, 1girl, spotlight,glowing eyes,"
        ),
        UrlExample( //68
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/98e5513a-e259-4a68-a79f-5abe999a4cff/original=true,quality=90/Illustrious_Metadata_0002.jpeg",
            prompt = "embedding:IllusP0s, marill_\\(pokemon\\), eating spaghetti, no humans, cute, aiming,<lora:spaghetti_eating.safetensors:0.5:0.5>"
        ),
        UrlExample( //69
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/aea1c7ef-ae10-4e6a-b5d3-2be066e73491/original=true,quality=90/ciro's_cars-artsy_vibe-flux-fp8-20250314233311.jpeg",
            prompt = "professional magazine photo, cinematic composition, 8k, 4k, 1950s retro-futurism, science fiction, perfect reflections, ray tracing, a highly detailed, high-resolution photo of an exciting luxurious, retro-futuristic supercar gleaming under the neon glow of a vintage gas station, a vision of 1950s-inspired atomic-age design, fused with cutting-edge technology, its sleek, aerodynamic bodywork features flowing curves, pronounced tailfins, and a bubble-shaped canopy, reminiscent of classic mid-century concept cars, polished chrome trim reflects the ambient light, while its vibrant metallic paint - indigo blue with polished chrome accents - shimmers with a soft pearlescent finish, whitewall tires with turbine-styled hubcaps complete its striking silhouette, the car's headlights are on, casting bright beams onto the rain-slick pavement, the light refracting in shimmering reflections. the setting is an atmospheric retro-futuristic gas station, bold red and white signage displays the text: \"Red Rocket\" in classic americana typography, its iconic towering rocket-shaped sign glowing softly against the twilight sky, red vintage fuel pumps stand in a row, showcasing the golden age of optimistic engineering, the concrete forecourt is slick from a recent rain shower, casting shimmering reflections of neon, the sky is a rich blend of twilight hues, with the last golden light of sunset fading into a deep blue, the first stars appearing above, the composition is cinematic, shot at a dramatic low angle to emphasize the power and elegance of the car, the car is positioned in the lower third of the frame, drawing the eye toward the red rocket sign in the background, giving the image a sense of grandeur and depth, captured with a hasselblad h6d-100c medium format camera on kodak ektar 100 film, f/2.8 aperture, iso 200, the soft film grain enhances the vintage aesthetic, while the crisp details ensure a modern, high-end car magazine quality, the lighting is a mix of soft natural dusk light and the warm artificial glow of gas station signage, combining to create a dynamic, luxurious atmosphere, a perfect blend of classic americana and retro-futuristic optimism, framed with masterful precision to evoke a sense of nostalgia and wonder, <lora:faetastic_details-flux-by_faeia:1.0> arsmoviestill, 80s fantasy movie still, <lora:style-80s_fantasy_movie-v3-flux-by_arsmachina:0.88> <lora:futucars-flux-by_ciro_negrogni:1.0>"
        ),
        UrlExample( //70
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/e2b34767-83ca-4b48-b395-038b0cd53c2e/original=true,quality=90/R1BQ5DA0SG72GP0XE3X99TWXY0.jpeg",
            prompt = "CivChan NEEDS YOUR BUZZ, solo, (floating, midair), [bent over], reaching out to viewer, dangling legs, spring, outside, meadow, carrying basket, (bee costume bee onesie), insect wings, purple eyes, pink hair, curvy, wind, happy, wink, red pupil, (takashia \\(akimototakashia\\):0.5), (sam yang:0.5), (ciloranko:0.5), (moshimoshibe:0.5), masterpiece, newest, highres, absurdres, amazing quality, best quality, ultra-detailed"
        ),
        UrlExample( //71
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/065d59c2-82a6-4ba3-bf44-4685752ec65e/original=true,quality=90/R956FS4SSWM6GTQ31Y5P9K0SD0.jpeg",
            prompt = "Now I have no regrets, I have no regrets from birth, you have no regrets, I also have no regrets, why ask my mind? In the end, there is no regret, heaven and earth have no joy, life and death have no regrets, without regret ask my mind, ask my mind there is no regret. ink painting, ink art, splash, traditional media, classic painting, colorful, scenery, very aesthetic, epic, majestic, fantasy art, dreamy, perspective, moody, magical, intricate details, highly detailed, ultra-detailed, absurdres, beautiful, painterly, detailed, textural, artistic, vivid, vibrant, aidmamj6.1, aidmainkstyle"
        ),
        UrlExample( //72
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/b4ce1dae-7e2d-4584-a47f-0d2bb2751766/original=true,quality=90/00103-2752284259.jpeg",
            prompt = "masterpiece, best quality, 1girl, solo, N4nashiCas, brown eyes, brown hair, long hair, streaked sidelocks, ahoge, cherry earrings, black choker, oversized hoodie, beige hoodie, animal hood, wide sleeves, long skirt, high-waist skirt, plaid skirt, brown skirt, indoors, cafe, sitting, table, cup, coffee mug, arm rest, smile, head tilt, <lora:ChamNanashiMumeiIllustriousXL:1>"
        ),
        UrlExample( //73
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/592b946f-4042-4a15-9793-8e30f07ea41a/original=true,quality=90/02003-1985768229%20catTowerNoobaiXL_v17Vpred%20Euler%20a.jpeg",
            prompt = "masterpiece, best quality, safe, girl, CivChan, solo, petite, pink hair, medium hair, (hair hair between eyes:1.2), purple eyes, snake mouth, looking at viewer, happy, :d, blush, medium breasts, (shaded face:0.8), black maid, short sleeves, maid headdress, thighhighs, white legwear, standing, hand on own cheek, holding, baseball bat over shoulder, cowboy shot, dark background <lora:CivChan:1>"
        ),
        UrlExample( //74'
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/ba745a8d-475e-4060-9590-870302076a56/original=true,quality=90/(1).jpeg",
            prompt = "Smooth Quality, 1girl, solo, cat girl, classroom, >_<, holding drawing of chibi cat girl with >_< expression, classroom,  <lora:Smooth_Lighting_Enhancer:0.5>\u200B\u200B\u200B <lora:Smooth_Booster_v2:1>\u200B\u200B"
        ),
        UrlExample( //75
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/343580fd-e994-456b-bc70-cad1723d94c6/original=true,quality=90/00081-776641034.jpeg",
            prompt = "art nouveau style, Aether Shadow Phoenix of Moon, Celestial Palace, Graphite in Robert McGinnis style, Whimsical, Galactic, intricate and detailed, Pop Art Colors, Minotaur with Wand of Cosmic Dust, Galactic Gateway, Hyperrealism in Bernie Wrightson style, Nimble, Galactic, intricate and detailed, Night Colors, Zombie Dragon of Spirit mystifying, Crystal Lagoon, Frame in Jessica Rossier style, Melancholic, Abstract, trending on deviantart, Complimentary-Colors, Mind Flayer of Water, Haunted Mansion, Hand-Drawn in Tetsuya Nomura style, Minimalist, Cosmic, masterpiece, trending on artstation, Yellow Monochrome, Sylph of Memory, Post-Apocalyptic City, Comic Book in Madhouse style, Neon, Sculptural, surreal masterpiece, Warm Tones, Yokai of Nature, Vampire Castle, Street Art in Charlie Bowater style, Prismatic, Nebulous, trending on deviantart, Metallic Colors, Electric Sphinx of Lava eating, Retro Arcade, Hand-Drawn in Peter Mohrbacher style, Cosmic, Intricate, intricate and detailed, Rainbow,, elegant, decorative, curvilinear forms, nature-inspired, ornate, detailed"
        ),
        UrlExample( //76
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/4a543a8a-c57d-448b-9be2-2110604ffe77/original=true,quality=90/ComfyUI_00002_368.jpeg",
            prompt = "A hyper-detailed, biomechanical butterfly with wings that appear almost porcelain-like, transitioning from a pure, ghostly white at the center to inky black at the tips, adorned with fine, golden veins that glow faintly. The insect’s thorax and lower body morph into an intricate mass of elongated, sinewy tendrils resembling neural pathways or delicate roots, cascading downward into an abyss of darkness. The background consists of an ethereal, almost liquid expanse of swirling white and gray textures, resembling flowing marble or organic tissue, infused with an enigmatic, glowing energy. The butterfly exudes an eerie yet mesmerizing presence, as if it is a celestial being suspended between worlds, both fragile and powerful in its surreal elegance. <lora:FluxDFaeTasticDetails:0.25> <lora:fechin-FLUX:0.75>"
        ),
        UrlExample( //77
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/eeaa0c5e-8d13-4efc-aca0-776ecb45d44b/original=true,quality=90/P2T08BBD4BVQ51RP95685BJ000.jpeg",
            prompt = "score_9, score_8_up, score_7_up,score_9, score_8_up, score_7_up, scenery, apocalyptic world, BREAK, cats and dogs fighting with laser guns, ((High fantasy art style)), ((Masterpiece), (absurdres), (shadowy), (mysterious)"
        ),
        UrlExample( //78
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/7bccdae8-bafa-4dde-9721-beb6551b12b0/original=true,quality=90/2025-04-07T11.32.07_1.jpeg",
            prompt = "A close-up scene of an Indian woman’s face and neck, her brown Rajasthani skin glowing with a soft, oily, glossy sheen under golden light. She wears a transparent, intricate golden veil draped over her head, detailed like fine lace, shimmering with delicate patterns. Her black eyes are deep and calm, framed with sharp black eyeliner extending elegantly. Her lips are full, painted rich red, contrasting her luminous skin. Traditional Rajasthani clothing wraps her neck and shoulders, adorned with sparkling gold ornaments — layered necklaces, ornate earrings, and forehead jewelry, each piece reflecting warm light with detailed craftsmanship. A soft white cotton flower rests within the frame near her neck, its texture delicate against the brilliance of her jewelry. Behind her, the background stretches into a blurred wheat paddy, golden fields swaying softly, merging with the sunlit atmosphere. The scene radiates timeless beauty, nature, and the regal grace of Rajasthani tradition."
        ),
        UrlExample( //79
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/00a52cf8-d4dc-4c06-8cf4-b7b58330bc8b/original=true,quality=90/ZDEQPXHJSAWJ50AEJ3X8GP02P0.jpeg",
            prompt = "Studio Ghibli Dark Fairytale, intricate cyborg Portrait of a female Robotic entity with Long billowing Hair Made of leafs, black hollow eerie head, No Face, DARK moody"
        ),
        UrlExample( //80
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/d9f5d6cc-007d-429d-ba20-5f156bebbc4d/original=true,quality=90/glif-h-v-fluxpro1-1-ultra-aspect-ratio-style-et-878b-ttop37sumpo580xdvk9ync8i.jpeg",
            prompt = "This is a high-resolution photograph of a woman with a striking, futuristic appearance. Her face and body are covered in a glowing, pixelated pattern of vibrant colors, including red, orange, yellow, green, blue, and white, which creates a mesmerizing, almost holographic effect. The colors are arranged in a complex, abstract design, with some areas of the pattern appearing more densely packed than others. Her skin is smooth and flawless, accentuated by the contrast of the glowing pixels. The woman has a slender build and is wearing a form-fitting, black bodysuit that mirrors the pixelated pattern, seamlessly blending with the light show. Her dark hair is pulled back into a sleek, low bun, and her eyes are strikingly blue, complemented by thick, dark eyelashes. She has full, red lips that stand out against the colorful background. The background is a dark, neutral color, which allows the glowing pixels to be the focal point. The lighting is soft but intense, casting shadows that enhance the depth and dimension of the image. The overall style of the photograph is modern and avant-garde, blending elements of technology and fashion into a visually stunning and thought-provoking composition."
        ),
        UrlExample( //81
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/6e377e9f-02b4-41cb-966b-7b479e0cbedc/original=true,quality=90/34B1KWNCVEEYYE23YDJY7AP4Q0.jpeg",
            prompt = "CivChan, purple eyes, pink hair, , score_9, score_8_up, score_7_up, Female, PG, Physical characteristics: Elegant facial features, pale skin, striking purple eyes, flowing pink hair composed of heart shapes, full body, full body shot Clothing and accessories: Red, heart-shaped elements enveloping her body and head; intricate earrings visible, Position: Reclining or emerging from the playing card, with one hand extended gracefully, Background: Dark backdrop with smoky, ethereal effects, scattered red hearts and poker chips enhancing the thematic composition, Main element: Queen of Hearts playing card, creatively designed with a vivid, surreal touch, Art style: Hyper-realistic with fantasy elements, emphasizing red tones and a luxurious, dramatic atmosphere,"
        ),
        UrlExample( //82
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/c4a73263-813c-4c35-a81a-3d949894eb79/original=true,quality=90/w1z_m4ch1n4__gentleman_cyborg_robot_with_a_fish_bowl_head__with_a_small_goldfish_2050777441.jpeg",
            prompt = "w1z_m4ch1n4. gentleman cyborg robot with a fish bowl head, with a small goldfish"
        ),
        UrlExample( //83
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/a5af4555-8227-48c8-9f5d-d70e12f5e421/original=true,quality=90/image_0387.jpeg",
            prompt = "front view, samsung king mackerel, Robotic Terror, Large Format Photography, selective color, subdued nightlight, leica m3 analog, muted low grain, ((a close-up portrait)) robocop, a dilapidated Awful with mechanical Peplum dress, stained with blood and dust. advanced cinematic perfect light metal textures, scars, damaged implants. futuristic dystopian atmosphere, Edgy lighting with cold glare. darksyber aesthetic, photorealistic style, portrait, (tuvaluan:1.15) , (Gray eyes:1.05) , ( pink lips:1.05) , (thin lips:1.05) , long face-shape, (bushy hair style:1.05) , (multicolored hair color:1.05) , (Knotty hair length:1.05) , A close up view of the face of a robot. The robot's face is covered in Amaranth and white paint. The eyes of the Journalist are covered in Flamboyant deep purple paint. There is a Absurd circle in the center of the head that is black in color. There are ridges of the metal around the robot's neck and around the neck. The figure's neck is made up of a series of ridges and bumps that run vertically. The face of the figure is covered by the silhouette paint."
        ),
        UrlExample( //84
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/ba3520a9-6e78-420d-8a17-f15f08ffe69f/original=true,quality=90/TASMDRSMTP60S8G1TBDFTWJCW0.jpeg",
            prompt = "masterpiece, best quality, very aesthetic, absurdres, newest, detailed skin, 1girl, solo, 28 years old, CivChan, purple eyes, pink hair, pastel colors,  lying on her bed, hugging a pink cat plushie,  animal plushies on bed, closed eyes, drooling on her pillow, detailed background, bedroom, on bed,"
        ),
        UrlExample( //85
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/52d9b87e-71e0-4709-a56b-7b7035c0b87a/original=true,quality=90/00010.jpeg",
            prompt = "smooth positive, masterpiece, best quality, good quality, newest, highres, absurdres, 8K, bright colors, multicolored background, from above, neon palette, wide angle,  1girl, (glitch effect:1.2), CivChan, crazy smile, purple eyes, pink hair, maid headdress, maid, civitailogo on screen, coming out of monitor, dutch angle, (through screen:1.5), (coming out of television), speech bubble, asking buzz, yellow lightning bolt symbol, masterpiece, best quality, good quality, newest, highres, absurdres, 8K, bright colors, <lora:add-detail-xl:2> <lora:CivChan:0.8> <lora:civitlogoIllustrious:1>"
        ),
        UrlExample( //86
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/e8633718-a54c-407f-b279-d020998419fb/original=true,quality=90/Best_0855.jpeg",
            prompt = "masterpiece, best quality, amazing quality solo, holding, closed_mouth, sitting, outdoors, sky, day, cloud, water, blurry, blue_sky, tree, orange_eyes, no_humans, blurry_background, fish, reflection, mountain, animal_focus, lake, fishing_rod, reflective_water, fishing, holding_fishing_rod, fishing_line,A digital illustration shoot from the side about a cute cartoon fish character sitting on a wooden pier by a calm lake, holding a fishing rod. the image also shows a serene mountain landscape with tall trees and a clear blue sky. on the middle of the image, a no human, furry, blue and orange fish with large, expressive eyes and a happy expression is sitting on the wooden pier. the fish appears to be a chubby, cartoonish creature with a slim body and a closed mouth. it is facing the viewer with its eyes looking to the side. the creature is holding the fishing rod in its right hand and its left hand is resting on the edge of the water. the background features a mountain range with a few clouds in the sky, and the water is calm and still. the lighting is soft and natural, creating a peaceful and serene atmosphere. solo, looking at viewer, closed mouth, sitting, brown eyes, outdoors, sky, day, cloud, holding, tree, blue sky, water, tree branch, holding stick, mountain, fish, pond, holding fishing rod"
        ),
        UrlExample( //87
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/087c31e8-e8d2-4077-a4b9-1bc6231c512a/original=true,quality=90//BQBDSNK5AV0V80P75XECN6KNH0.jpeg",
            prompt = "This is a highly detailed, hyper-realistic CGI artwork depicting an otherworldly, futuristic figure. The subject is a skeletal woman with a human skull for a head, adorned with intricate, ornate accessories. Her face is a hollow, white skull with glowing, amber eyes that give an eerie, lifelike appearance. Her skin is painted with a fine, intricate pattern of gold and black lines, adding to the mechanical aesthetic. She wears a helmet-like headpiece that features a stylized, ornate design with a central skull motif, flanked by large, intricate wings of a butterfly, colored in a gradient from deep orange to a fiery red. These wings are adorned with intricate, glowing details, enhancing the fantastical, otherworldly feel of the image. Her hair is long and dark, cascading down her shoulders, blending seamlessly with the ornate headpiece. The figure's body is covered in a sleek, black and gold armor with a futuristic, mechanical appearance. The armor features intricate, glowing circuits and a chest piece adorned with a glowing, golden lion's head. Small, glowing butterflies are scattered around her, adding to the surreal atmosphere., vantablack"
        ),
        UrlExample( //88
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/10972de9-390a-41aa-9a9c-8d71a9614e0f/original=true,quality=90/1296437700-747490595.jpeg",
            prompt = "A hyper-realistic cgi profile of a skeletal wolf with gaping jaws sharp teeth and a yellow eye  disheveled fur fused half with a (human:1.1) set against a dark smoky background. The lighting accentuates the bone's texture and eerie atmosphere., <lora:- NAI - ac_illustration_V1.0-step00011000:.2>  <lora:- NAI - PdJ_highres_counterweight_V.2.0-step00021000:0.7>"
        ),
        UrlExample( //89
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/adf5e409-c090-4b6e-8b62-74624041cd89/original=true,quality=90/1HDMCNFEDX4PQ9HZXJYJTBCXW0.jpeg",
            prompt = "masterpiece, best quality, good quality, very awa, newest, highres, absurdres, 1girl, solo, dress, standing, flower, outdoors, water, white flower, pink flower, scenery, reflection, rain, dark, ripples, yellow flower, puddle, colorful, abstract, standing on liquidi¼\u008C very Wide Shot, limited palette,"
        ),
        UrlExample( //90
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/a70536ec-ad55-4dcf-b780-51fb51e842bc/original=true,quality=90/1296436366-1559547555.jpeg",
            prompt = "masterpiece, holy moly, look at that cat picture!, side view, cinematic, fantasy, epic scale, angle, dynamic , <lora:- iLL - master_of_slob_omnom_V1.0-step00003500:.8>"
        ),
        UrlExample( //91
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/caa2baeb-3ea6-4ff5-9cbb-24e61fc4157e/original=true,quality=90/00150-842265430.jpeg",
            prompt = "Yoshi in a red kart speeding through a swirling, psychedelic landscape of iridescent balloons and warped surfaces, vibrant colors, dynamic lighting, highly detailed, 8k resolution, cinematic lighting, hyperrealistic, --ar 16:9 --zoom 1.5 --style sdxl Yoshi's Balloon Bonanza aidmafluxpro1.1,Fantasy detailersâ\u0080\u008Bâ\u0080\u008Bâ\u0080\u008B"
        ),
        UrlExample( //92
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/1ddacad5-23b0-4e24-90f5-233c2054558e/original=true,quality=90/W3GMZSX41WY2XGMTNJYHZQ3W10.jpeg",
            prompt = "Ultra-realistic 8K resolution, Octane render, Unreal Engine 5 masterpiece, dark academia aesthetic, enchanted glass terrarium containing: two hybrid heliotrope-roses with translucent ruby gemstone petals (vitreous refraction), metallic obsidian stems (bioluminescent vascular system), liquid mercury dewdrops suspended mid-fall, spiral cerulean nebula core emitting plasma tendrils, fractured amber glass veins pulsing with trapped starlight, quantum foam escaping through dimensional cracks, non-Euclidean spacetime geometry, vantablack void background with gravitational lensing distortion, chrono-crystalline lattice framework, volumetric photon mapping event horizon glow, subsurface scattering in petal nanostructures, temporal anomaly causing timeline overlaps, hyperdetailed microbiology under gemstone epidermis, gothic biodigitalpunk style blending Zdzisław Beksiński and Ernst Haeckel, photorealistic macro photography meets celestial cartography, trending on ArtStation --ar 16:9 --v 6.0 --no cartoonish, flat colors, symmetry, human elements"
        ),
        UrlExample( //93
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/3072c857-86df-471e-92c4-b3caf8eb61bd/original=true,quality=90/2025-04-02T16.11.39_3.jpeg",
            prompt = "(masterpiece, best quality:1.3), pink hair, purple eyes, cat_ears, yandere, gentle smile, medium breasts, loving_gaze, looking_at_viewer, BehindCatgirl, CatGirlStyle, dress, city, outside, park, fountain, bench, CivChan, sakura, cherry blossoms, pinkneoncybercore, purse, cute, night"
        ),
        UrlExample( //94
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/4c68122c-6266-4e7c-8ddc-84b5f1e0a401/original=true,quality=90/Upscaled_Ultimate_ComfyUI_FLUX1_False_790952063352168.jpeg",
            prompt = "A bright yellow duck, wearing oversized mirrored sunglasses and a tropical flower necklace, balances expertly on a surfboard, cutting through the crest of an enormous wave. The water is a swirling mix of blues and greens, foamy spray frozen in time as it arcs around the duckâ\u0080\u0099s outstretched wings. A small crowd of bewildered seagulls watches mid-flight, their heads tilted in disbelief. The horizon stretches endlessly in the background, where the setting sun casts a golden glow across the oceanâ\u0080\u0099s surface.<lora:XRSTYLE_FLUX:0.5><lora:black_fantasy_1.0:0.5><lora:FluxDFaeTasticDetails:0.7>"
        ),
        UrlExample( //95
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/d77bedcc-36af-4ec0-9f93-6602905f7be6/original=true,quality=90/KJ16S6NV6MBACBKWZKWEAJD6R0.jpeg",
            prompt = "A mysterious, hooded figure draped in a long, flowing blue cloak sits solemnly on the edge of a rugged, rocky cliff. The figure’s face is hidden in deep shadow, adding an aura of enigma and solitude. The background features a vast, dark starry sky with countless shimmering stars, dominated by a large, glowing, bluish moon, casting a faint ethereal light. The rocky formations are detailed, textured, and rugged, creating a stark contrast with the smooth, almost liquid-like fabric of the cloak. The overall mood is surreal, melancholic, and otherworldly, with deep shadows and cool, muted color tones emphasizing mystery and cosmic loneliness anime, cyberpunk"
        ),
        UrlExample( //96
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/65fbb75b-5fca-4d46-be38-766e8def3eed/original=true,quality=90/3.jpeg",
            prompt = "R3alisticF, samurai girl wearing a kimono with shoulder armor, she's holding a katana glowing with red energy, her long hair is flowing in the wind, she stands in a dojo training for her next battle. (maximum ultra high definition image quality and rendering:3), maximum image detail, maximum realistic render, (((ultra realist style))), realist side lighting, , 8K high definition, realist soft lighting, <lora:FluxMythR3alisticF:1>"
        ),
        UrlExample( //97
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/1334a603-7ea2-4202-a8c1-bdd5a9548a18/original=true,quality=90/00160-2555575246.jpeg",
            prompt = "A silhouette of a person walking away from a birdcage, holding a string attached to a single red balloon, against a pastel sky with clouds, minimalist style, vector illustration -- The Price of Freedom aidmafluxpro1.1,Fantasy detailersâ\u0080\u008Bâ\u0080\u008Bâ\u0080\u008B"
        ),
        UrlExample( //98
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/bfe3336e-5411-4471-babd-02a2c71f6819/original=true,quality=90/00010-4282298451.jpeg",
            prompt = "best quality, masterpiece, absurdres, newest,(official style:1.1), anime coloring, <lora:CivBot_-_Evolved:0.8> 1boy, civbot, gigachad, sigma male, robot, male focus, solo, blue skin, colored skin, antennae, bald, glowing red eyes, professional clothes, suit, tie, <lora:The_Thinker_pose_Concept_for_Illustrious:0.8> th1nk3r, sitting, head rest, 1boy, male focus, office chair, ((bugs, insects, flies)),"
        ),
        UrlExample( //99
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/63c22b5d-4fe5-477c-8cdd-71d7313775e2/original=true,quality=90/00001-3405874669.jpeg",
            prompt = "masterpiece, best quality,high quality, newest, highres,8K,HDR,absurdres, 1boy, androgynous, solo, ponytail, long dark brown hair, glowing red eyes, slit pupils, evil smile expression, black and red checker patterned scarf, purple kimono, haori, hakama pants, katana, samurai fantasy battlefield, stormy sky, dynamic pose, intense lighting, detailed background, depth of field, dynamic composition, foreshortening, detailed background, dynamic pose, dynamic composition,dutch angle, detailed backgroud,foreshortening,blurry edges <lora:iLLMythG0thicL1nes:1> G0thicL1nes"
        ),
        UrlExample( //100
            url = "https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/40fc3fde-2a86-414e-afe7-b3bc6bedcbb1/original=true,quality=90/00039-1344656474-1girl,%20glowing%20scythe,%20full%20moon,%20mist,%20ethereal,%20dark%20fantasy,%20dramatic%20lighting,%20night%20sky,%20mysterious%20atmosphere,%20fantasy%20set.jpeg",
            prompt = "1girl, glowing scythe, full moon, mist, ethereal, dark fantasy, dramatic lighting, night sky, mysterious atmosphere, fantasy setting masterpiece, best quality, amazing quality, very aesthetic, absurdres, detailed eyes, newest, <lora:CivChan:1>, CivChan, purple eyes, pink hair, maid outfit, <lora:PinkieNeonAnimeIL:0.5>, neon illustration in the style of pinkneonanime,"
        )
    )
}