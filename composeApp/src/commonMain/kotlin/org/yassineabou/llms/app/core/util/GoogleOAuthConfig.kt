package org.yassineabou.llms.app.core.util

/*
 * ──────────────────────────────────────────────────────────────────────────────
 *  CREATE WEB CLIENT OAUTH 2.0 CREDENTIALS
 *  1. Go to Google Cloud Console → APIs & Services → Credentials
 *     (https://console.cloud.google.com/apis/credentials)
 *  2. Click “Create Credentials → OAuth client ID”.
 *  3. Choose “Web application”.
 *  4. Add authorized redirect URIs (e.g., http://localhost:8080/callback).
 *  5. Click “Create” and copy the generated CLIENT_ID and CLIENT_SECRET.
 *  6. Paste the values into the CLIENT_ID and CLIENT_SECRET constants below.
 * ──────────────────────────────────────────────────────────────────────────────
 */
object GoogleOAuthConfig {
    const val CLIENT_ID     = ""
    const val CLIENT_SECRET = ""
}