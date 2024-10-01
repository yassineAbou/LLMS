package org.yassineabou.playground.feature.imageGen.model

import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.eight
import llms.composeapp.generated.resources.eighteen
import llms.composeapp.generated.resources.eleven
import llms.composeapp.generated.resources.fifteen
import llms.composeapp.generated.resources.five
import llms.composeapp.generated.resources.four
import llms.composeapp.generated.resources.fourteen
import llms.composeapp.generated.resources.nine
import llms.composeapp.generated.resources.nineteen
import llms.composeapp.generated.resources.one
import llms.composeapp.generated.resources.seven
import llms.composeapp.generated.resources.seventeen
import llms.composeapp.generated.resources.six
import llms.composeapp.generated.resources.sixteen
import llms.composeapp.generated.resources.ten
import llms.composeapp.generated.resources.thirteen
import llms.composeapp.generated.resources.three
import llms.composeapp.generated.resources.twelve
import llms.composeapp.generated.resources.twenty
import llms.composeapp.generated.resources.two
import org.jetbrains.compose.resources.DrawableResource

data class Photo(val id: Int, val drawable: DrawableResource, val description: String)


val randomSizedPhotos = mutableListOf(
    Photo(id = 0, drawable = Res.drawable.one, description = "A closeup of fantastical image of a moon knight, clad in flowing, flowing robes, wielding an ornate, ornate blade. Their eyes are filled with power and determination, as they wield the sword in a fluid, dynamic motion. The moon's reflection highlights their magical power"),
    Photo(id = 1, drawable = Res.drawable.two, description = "score_9, score_8_up, score_7_up,  san, 1girl, full body, squatting, solo, short hair, brown hair, gray eyes, headband, face paint, earrings, white sleeveless top, navy under dress, arm cuffs, fur headdress, fur cape, light-skinned female, female focus, looking at viewer, smiling, forest, (blush:1.1)  <lora:pony/twilight_style:0.8> concept art, realistic, <lora:pony/expressiveh:0.8> expressiveh, <lora:pony/kenva:0.8> knva, <lora:pony/san_pony_v4> <lora:sdxl/sinfully_stylish_sdxl>"),
    Photo(id = 2, drawable = Res.drawable.three, description = "masterpiece, best quality, very aesthetic, absurdres wlop miimalism cinematic lighting 1girl, aged up walking in the alley black suit white collar shirt perspective from above cyberpunk, neon"),
    Photo(id = 3, drawable = Res.drawable.four, description =  "score_9, score_8_up, score_7_up,  ((first person perspective)), landscape photo, tron, heaven, ascendance, god rays, light, gold, sky,  advntr, underwater, alien, colorful, ((zdzislaw beksinski)),translucent,transparent,reelmech, mechanical parts, cable, wires, machinery, joints, body suit"),
    Photo(id = 4, drawable =  Res.drawable.five, description =  "very dark focused flash photo, amazing quality, masterpiece, best quality, hyper detailed, ultra detailed, UHD, perfect anatomy, portrait, dof, hyper-realism, majestic, awesome, inspiring,Capture the thrilling showdown between the ancient mummy and the colossal sand boss in an epic battle amidst swirling dust and desert sands. Embrace the action and chaos as these formidable forces clash in the heart of the dunes. cinematic composition, soft shadows, national geographic style"),
    Photo(id = 5, drawable = Res.drawable.six, description =  "detailed realistic close up of a strawberry shaped like a kitten, sitting, natural light"),
    Photo(
        id = 6, drawable = Res.drawable.seven, description =  "Bosstyle, Silhouette of a Woman fighting a giant DARK mononoke Monster Boss resembling a ethereal fox with seven tails: Capture the essence of nostalgia and color in this vintage photograph. Dominating the scene is a meticulously detailed, translucent dark-red Blood filled firefox, adorned with vibrant patterns, blending seamlessly with its surroundings. Its long tail, composed of intricately woven ribbons in a spectrum of colors, trails behind, adding to the spectacle of the moment. Amidst the quietude of the night, its presence exudes a sense of ancestral strength and resilience. Behind it, the low-toned hues of the Milky Way cast a mesmerizing backdrop, a cosmic tapestry weaving tales of both past and future. In this moment, the convergence of tradition and technology, of ancient wisdom and industrial progress, hangs palpably in the air., atmospheric haze, Film grain, cinematic film still, shallow depth of field, highly detailed, high budget, cinemascope, moody, epic, OverallDetail, 2000s vintage RAW photo, photorealistic, candid camera, color graded cinematic, eye catchlights, atmospheric lighting, imperfections, natural, shallow dof"),
    Photo(id = 7, drawable = Res.drawable.eight, description =  "detailed background,( Calm spring night landscape), amongst lush greenery, beautiful view, creeping phlox in full bloom, creeping phlox, early morning, sunrise sky, beautiful clouds, dappled sunlight, outdoor seating, one lamp, Tranquil Lake, Boat on a Lake, depth of field, masterpiece, best quality, ultra-detailed, very aesthetic, illustration, perfect composition, intricate details, absurdres, moody lighting, wisps of light, no humans"),
    Photo(id = 8, drawable = Res.drawable.nine, description = "a group of rats in the nursing home gather around a table for a game of cards, with one rat wearing a monocle and another puffing on a cigar. The rendering style is reminiscent of a classic oil painting.  <lora:add-detail-xl:1>  <lora:MJ52:0.5>"),
    Photo(id = 9, drawable = Res.drawable.ten, description = "score_9,score_8_up,score_7_up, AUTOMATON, giant rock golem with plants growing on shoulders, rough rocks, rough stone, unpolished, dirty, ancient, STANDING"),
    Photo(id = 10, drawable = Res.drawable.eleven, description =  "wolf wearing sheep costume, many sheep in the background,"),
    Photo(id = 11, drawable = Res.drawable.twelve, description =  "<lora:GEN 0.1v:0.8>,<lora:Expressive_H-000001>,<lora:modern_anime_screencap_v1_0:0.8>,source_anime,score_9,score_8_up,score_7_up,score_6_up,dark background,low light, 1girl,solo,<lora:age_slider_v4:1.2>,adult,tall female,black hair,medium hair,bangs,full bangs,straight hair,red eyes,pale skin,skindentation,petite,toned,(thick thighs:0.6),medium breasts,(angry:0.6),(half-closed eyes:0.8),fighting stance,holding katana,stance,black baggy pants,white top,high collar,one eye closed,crouch,glowing eyes,belt,harness,looking at viewer,flaming eye,black background"),
    Photo(id =12, drawable = Res.drawable.thirteen, description = "score_9, score_8_up, score_7_up, BREAK, 1girl, portrait, beautiful, tiefling shaman, white hair, (long hair:1.3), side bangs, horns, barbarian, gladiator, war paint, tribal markings, long tail, blue skin, pink eyes, glowing eyes, (smug:0.85), (big breast:1.25), dramatic lights <lora:Fant5yP0ny:0.6> <lora:fantasy_world_pony:0.9> <lora:Cherry-Gig:0.55>"),
    Photo(
        id = 13, drawable = Res.drawable.fourteen, description =  "A TV show poster for a parody series titled \"Breaking Bread,\" playing on the name of the famous show Breaking Bad. The poster features a stern-looking baker in a flour-dusted apron, with a rolling pin slung over his shoulder like a weapon. He stands in the middle of a rustic bakery, surrounded by stacks of bread loaves, pastries, and bags of flour, all arranged to mimic the iconic desert backdrop from the original show. Behind him, the bakery's chalkboard menu lists items like \"Cinnamon Swirl Cartel\" and \"Blueberry Muffin Meth,\" hinting at the culinary chaos that’s about to unfold. The baker’s expression is intense, yet comically serious, as if he's about to take on the world with nothing but his dough and determination The title \"Breaking Bread\" is displayed in bold, stylized text at the top, with the \"Breaking\" in a gritty, cracked font, while \"Bread\" is written in warm, golden letters resembling freshly baked loaves. The tagline beneath reads, \"Baking is a Dangerous Game,\" adding to the pun-filled humor. The overall color scheme of the poster is a mix of warm, bakery hues with the gritty, dark tones reminiscent of the original show, blending the intense drama of Breaking Bad with the lighthearted, culinary twist of \"Breaking Bread.\""),
    Photo(id = 14, drawable = Res.drawable.fifteen, description =  "image of a hand offering money to a fishmonger cat, holding fish, 8k, ultra detailed, at the market, <lora:cashbaba:0.6>  <lora:xl_more_art-full_v1:0.5>"),
    Photo(id = 15, drawable = Res.drawable.sixteen, description = "score_9, score_8_up, score_7_up, score_6_up, score_5_up, score_4_up,   <lora:r3dgl0wXLP:0.7> r3dgl0w, red eyes, red background, 1girl, dark, horns"),
    Photo(id = 16, drawable = Res.drawable.seventeen, description = "linquivera,  Ink illustration, (anime:0.5), brown tones, aged black red paper, inkpunk, moonlight,surreal, a ghost standing on a boat in swampy wetlands, (at a distance), Will-o'-the-wisp, moonlit, lonely, solitude, windy, tall trees, willows, willowy,  OverallDetail, extremely detailed, UHD,(long exposure , dystopian but extremely beautiful:1.4),<lora:- SDXL - letitflrsh_let_it_flourish_V1.0:0.8> ,<lora:Cute_Collectible:1>, <lora:XL_boss_battle:0.6>  <lora:add-detail-xl:1>,  <lora:Concept Art Twilight Style SDXL_LoRA_Pony Diffusion V6 XL:1>,  <lora:MJ52:1>"),
    Photo(id = 17, drawable = Res.drawable.eighteen, description = "(best quality, 8K, high resolution, masterpiece), ultra detailed, (3D CGI), black sunglasses, trendy, fashionable, silver and black stylized angry muscle doberman on a black background, countryside advertising, winning photo"),
    Photo(id = 18, drawable = Res.drawable.nineteen, description = "intensely focused Viking woman warrior with curly hair hurling a burning meteorite from her hand towards the viewer, the glowing sphere leaves the woman's body getting closer to the viewer leaving a trail of smoke and sparks, intense battlegrounds in snowy conditions, army banners, swords and shields on the ground <lora:Dever_Flux_Enhancer:0.5>"),
    Photo(id = 19, drawable = Res.drawable.twenty, description = "score_9, score_8_up, score_7_up, score_6_up, 1girl, beautiful, white hair, long hair, bangs, fair skin, big breasts, leather armor, sword, forest, dappled sunlight, upper body, looking at viewer")
)
